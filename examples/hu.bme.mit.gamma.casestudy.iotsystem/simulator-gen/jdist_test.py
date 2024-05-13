import pyro
import torch
import pyro.distributions as dist
import pyro.contrib.gp as gp
from pyro.distributions.torch_distribution import TorchDistribution
from pyro.distributions.util import broadcast_shape

class JointDistribution(TorchDistribution):

    arg_constraints = {}  # nothing can be constrained

    def __init__(self, dists, validate_args=None):
        for dist in dists:
            if dist.event_shape != dists[0].event_shape:
                raise ValueError("components event_shape disagree: {} vs {}".format(
                    dist.event_shape, dists[0].event_shape))
        batch_shape = broadcast_shape(dists)
        self.dists = dists
        super().__init__(batch_shape, dists[0].event_shape, validate_args)

    @property
    def has_rsample(self):
        return True

    def expand(self, batch_shape):
        new_dists=[]
        for dist in self.dists:
            new_dists.append(dist.expand(batch_shape))
        return JointDistribution(new_dists)

    def sample(self, sample_shape=torch.Size()):
        samples=[]
        for dist in self.dists:
            samples.append(dist.sample(sample_shape))
        return torch.stack(samples)

    def rsample(self, sample_shape=torch.Size()):
        samples=[]
        for dist in self.dists:
            samples.append(dist.sample(sample_shape))
        return torch.stack(samples)

    def log_prob(self, value):
        log_prob=torch.tensor(0.0)
        for i in range(len(self.dists)):
            log_prob=log_prob+self.dists[i].log_prob(value[i]).sum()
            print(f"Logprob {i}: {self.dists[i].log_prob(value[i])} --- {log_prob}")
        return log_prob

    def mean(self):
        means=[]
        for dist in self.dists:
            means.append(dist.mean)
        return torch.stack(means)

    def variance(self):
        variances=[]
        for dist in self.dists:
            variances.append(dist.variance)
        return torch.stack(variances)

class RandomVariable():

    plate=pyro.plate("random_variable")
    dists=[]
    samples=[]
    dist=None
    insts=[]

    def ginit():
        RandomVariable.dist=JointDistribution(RandomVariable.dists)

    def greset():
        RandomVariable.cntr=0
        RandomVariable.samples=pyro.sample("samples_"+str(RandomVariable.cntr),RandomVariable.dist)

    def gsample():
        print("gsample----")
        RandomVariable.cntr=RandomVariable.cntr+1
        RandomVariable.samples=pyro.sample("samples_"+str(RandomVariable.cntr),RandomVariable.dist)
        for dist in RandomVariable.insts:
            dist.event_cntr=0

    def __init__(self,dist,name,N=40):
        self.dist=dist
        self.name=name
        self.event_cntr=-1
        self.N=N
        self.i=len(RandomVariable.dists)
        RandomVariable.dists.append(dist.expand([N]))
        RandomVariable.insts.append(self)
    
    def calc(self,event=0,time=0):
        self.event_cntr=self.event_cntr+1
        if self.event_cntr==self.N:
            RandomVariable.gsample()
        return RandomVariable.samples[self.i][int(self.event_cntr)].item()


e1=pyro.distributions.Exponential(0.0001)
e2=pyro.distributions.Exponential(0.1)
e3=pyro.distributions.Exponential(1000.0)
e4=pyro.distributions.Exponential(100000.0)

d=JointDistribution([e1,e2,e3,e4])

s=pyro.sample("s1",d)
print(d.log_prob(torch.tensor([2.0,2.0,2.0,2.0])))

rv1=RandomVariable(e1,"e1",3)
rv2=RandomVariable(e2,"e2",3)
rv3=RandomVariable(e3,"e3",3)
rv4=RandomVariable(e4,"e4",3)
RandomVariable.ginit()
RandomVariable.greset()
print(RandomVariable.samples)
print([i.name for i in RandomVariable.insts])

for i in range(3):
    print(f"rv1[{i}].calc = {rv1.calc()}")

for i in range(4):
    print(f"rv2[{i}].calc = {rv2.calc()}")

e1=pyro.distributions.Exponential(0.0001).expand([5])
e2=pyro.distributions.Exponential(0.1).expand([5])
e3=pyro.distributions.Exponential(1000.0).expand([5])
e4=pyro.distributions.Exponential(100000.0).expand([5])

d=JointDistribution([e1,e2,e3,e4])

print("e1 log_prob: ",e1.log_prob(torch.tensor([1.0,1.0,1.0,1.0,1.0])))
print("d log_prob: ",d.log_prob(torch.tensor([[1.0,1.0,1.0,1.0,1.0],
                                              [10.0,10.0,10.0,10.0,10.0],
                                              [10.0,100.0,100.0,100.0,100.0],
                                              [1.0,1.0,1.0,1.0,1.0]])))

print(f" mean = {d.mean()}, var = {d.variance()}")
