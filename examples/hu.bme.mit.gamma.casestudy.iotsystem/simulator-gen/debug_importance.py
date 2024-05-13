import math
import warnings
import sys
import gc
import torch

import pyro
import pyro.poutine as poutine
from pyro.ops.stats import fit_generalized_pareto

#from pyro.infer.abstract_infer import TracePosterior

import numbers
import warnings
from abc import ABCMeta, abstractmethod
from collections import OrderedDict, defaultdict

import torch

import pyro.poutine as poutine
from pyro.distributions import Categorical, Empirical
from pyro.ops.stats import waic
from pyro.poutine.util import site_is_subsample


def sizeof(obj):
    size = sys.getsizeof(obj)
    if isinstance(obj, dict): return size + sum(map(sizeof, obj.keys())) + sum(map(sizeof, obj.values()))
    if isinstance(obj, (list, tuple, set, frozenset)): return size + sum(map(sizeof, obj))
    return size

def actualsize(input_obj):
    memory_size = 0
    ids = set()
    objects = [input_obj]
    while objects:
        new = []
        for obj in objects:
            if id(obj) not in ids:
                ids.add(id(obj))
                memory_size += sys.getsizeof(obj)
                new.append(obj)
        objects = gc.get_referents(*new)
    return memory_size

class TracePosterior(object, metaclass=ABCMeta):
    """
    Abstract TracePosterior object from which posterior inference algorithms inherit.
    When run, collects a bag of execution traces from the approximate posterior.
    This is designed to be used by other utility classes like `EmpiricalMarginal`,
    that need access to the collected execution traces.
    """

    def __init__(self, num_chains=1):
        self.num_chains = num_chains
        self._reset()

    def _reset(self):
        self.log_weights = []
        self.exec_traces = []
        self.chain_ids = []  # chain id corresponding to the sample
        self._idx_by_chain = [
            [] for _ in range(self.num_chains)
        ]  # indexes of samples by chain id
        self._categorical = None

    def marginal(self, sites=None):
        """
        Generates the marginal distribution of this posterior.

        :param list sites: optional list of sites for which we need to generate
            the marginal distribution.
        :returns: A :class:`Marginals` class instance.
        :rtype: :class:`Marginals`
        """
        return Marginals(self, sites)


    @abstractmethod
    def _traces(self, *args, **kwargs):
        """
        Abstract method implemented by classes that inherit from `TracePosterior`.

        :return: Generator over ``(exec_trace, weight)`` or
        ``(exec_trace, weight, chain_id)``.
        """
        raise NotImplementedError("Inference algorithm must implement ``_traces``.")

    def __call__(self, *args, **kwargs):
        # To ensure deterministic sampling in the presence of multiple chains,
        # we get the index from ``idxs_by_chain`` instead of sampling from
        # the marginal directly.
        random_idx = self._categorical.sample().item()
        chain_idx, sample_idx = (
            random_idx % self.num_chains,
            random_idx // self.num_chains,
        )
        sample_idx = self._idx_by_chain[chain_idx][sample_idx]
        trace = self.exec_traces[sample_idx].copy()
        for name in trace.observation_nodes:
            trace.remove_node(name)
        return trace
    
    
    def run(self, *args, **kwargs):
        """
        Calls `self._traces` to populate execution traces from a stochastic
        Pyro model.

        :param args: optional args taken by `self._traces`.
        :param kwargs: optional keywords args taken by `self._traces`.
        """
        self._reset()
        with poutine.block():
            for i, vals in enumerate(self._traces(*args, **kwargs)):
                if len(vals) == 2:
                    chain_id = 0
                    tr, logit = vals
                else:
                    tr, logit, chain_id = vals
                    assert chain_id < self.num_chains
                self.exec_traces.append(tr)
                self.log_weights.append(logit)
                self.chain_ids.append(chain_id)
                self._idx_by_chain[chain_id].append(i)
        self._categorical = Categorical(logits=torch.tensor(self.log_weights))
        return self


    def information_criterion(self, pointwise=False):
        """
        Computes information criterion of the model. Currently, returns only "Widely
        Applicable/Watanabe-Akaike Information Criterion" (WAIC) and the corresponding
        effective number of parameters.

        Reference:

        [1] `Practical Bayesian model evaluation using leave-one-out cross-validation and WAIC`,
        Aki Vehtari, Andrew Gelman, and Jonah Gabry

        :param bool pointwise: a flag to decide if we want to get a vectorized WAIC or not. When
            ``pointwise=False``, returns the sum.
        :returns: a dictionary containing values of WAIC and its effective number of
            parameters.
        :rtype: :class:`OrderedDict`
        """
        if not self.exec_traces:
            return {}
        obs_node = None
        log_likelihoods = []
        for trace in self.exec_traces:
            obs_nodes = trace.observation_nodes
            if len(obs_nodes) > 1:
                raise ValueError(
                    "Infomation criterion calculation only works for models "
                    "with one observation node."
                )
            if obs_node is None:
                obs_node = obs_nodes[0]
            elif obs_node != obs_nodes[0]:
                raise ValueError(
                    "Observation node has been changed, expected {} but got {}".format(
                        obs_node, obs_nodes[0]
                    )
                )

            log_likelihoods.append(
                trace.nodes[obs_node]["fn"].log_prob(trace.nodes[obs_node]["value"])
            )

        ll = torch.stack(log_likelihoods, dim=0)
        waic_value, p_waic = waic(
            ll, torch.tensor(self.log_weights, device=ll.device), pointwise
        )
        return OrderedDict([("waic", waic_value), ("p_waic", p_waic)])


def line_str(step,n_step):
    N=20
    str1=f"{step}/{n_step}:"
    for i in range(N):
        if i/float(N)<=step/float(n_step):
            str1=str1+"-"
        else:
            str1=str1+" "
    return str1

class Importance(TracePosterior):
    """
    :param model: probabilistic model defined as a function
    :param guide: guide used for sampling defined as a function
    :param num_samples: number of samples to draw from the guide (default 10)

    This method performs posterior inference by importance sampling
    using the guide as the proposal distribution.
    If no guide is provided, it defaults to proposing from the model's prior.
    """
    
    
    def __init__(self, model, guide=None, num_samples=None):
        """
        Constructor. default to num_samples = 10, guide = model
        """
        super().__init__()
        if num_samples is None:
            num_samples = 10
            warnings.warn(
                "num_samples not provided, defaulting to {}".format(num_samples)
            )
        if guide is None:
            # propose from the prior by making a guide from the model by hiding observes
            guide = poutine.block(model, hide_types=["observe"])
        self.num_samples = num_samples
        self.model = model
        self.guide = guide
    
    #@profile
    def _traces(self, *args, **kwargs):
        """
        Generator of weighted samples from the proposal distribution.
        """
        
        guide_trace = poutine.trace(self.guide).get_trace(*args, **kwargs)
        model_trace = poutine.trace(
            poutine.replay(self.model, trace=guide_trace)
        ).get_trace(*args, **kwargs)
        #print("Trace size: ",actualsize(guide_trace))
        log_weight = model_trace.log_prob_sum() - guide_trace.log_prob_sum()
        return (model_trace, log_weight)

    def get_log_normalizer(self):
        """
        Estimator of the normalizing constant of the target distribution.
        (mean of the unnormalized weights)
        """
        # ensure list is not empty
        if self.log_weights:
            log_w = torch.tensor(self.log_weights)
            log_num_samples = torch.log(torch.tensor(self.num_samples * 1.0))
            return torch.logsumexp(log_w - log_num_samples, 0)
        else:
            warnings.warn(
                "The log_weights list is empty, can not compute normalizing constant estimate."
            )

    #@profile
    def run(self, *args, **kwargs):
        print("Good run function")
        """
        Calls `self._traces` to populate execution traces from a stochastic
        Pyro model.

        :param args: optional args taken by `self._traces`.
        :param kwargs: optional keywords args taken by `self._traces`.
        """
        self._reset()
        #with poutine.block():
            #for i, vals in enumerate(self._traces(*args, **kwargs)):
        for i in range(self.num_samples):
                if i % 50 == 0:
                    l=line_str(i,self.num_samples)
                    print(f'[iter {l}]', end='\r')

                vals=self._traces(*args, **kwargs)
                if len(vals) == 2:
                    chain_id = 0
                    tr, logit = vals
                else:
                    tr, logit, chain_id = vals
                    assert chain_id < self.num_chains
                self.exec_traces.append(tr)
                self.log_weights.append(logit)
                self.chain_ids.append(chain_id)
                self._idx_by_chain[chain_id].append(i)
        self._categorical = Categorical(logits=torch.tensor(self.log_weights))
        return self
    def get_normalized_weights(self, log_scale=False):
        """
        Compute the normalized importance weights.
        """
        if self.log_weights:
            log_w = torch.tensor(self.log_weights)
            log_w_norm = log_w - torch.logsumexp(log_w, 0)
            return log_w_norm if log_scale else torch.exp(log_w_norm)
        else:
            warnings.warn(
                "The log_weights list is empty. There is nothing to normalize."
            )


    def get_ESS(self):
        """
        Compute (Importance Sampling) Effective Sample Size (ESS).
        """
        if self.log_weights:
            log_w_norm = self.get_normalized_weights(log_scale=True)
            ess = torch.exp(-torch.logsumexp(2 * log_w_norm, 0))
        else:
            warnings.warn(
                "The log_weights list is empty, effective sample size is zero."
            )
            ess = 0
        return ess
