import sys
sys.path.append('simulator-gen/.')
from conditionalserviceavailability_simulator import *
import pyro
from pyro.infer import SVI, TraceEnum_ELBO, Trace_ELBO, TraceGraph_ELBO, TraceMeanField_ELBO
import math
import sys
import traceback
from jpype import *
import torch.distributions.constraints as constraints
dist = pyro.distributions.Bernoulli(torch.tensor(0.90))
import plotly.express as px
import pandas as pd
import csv
from scipy.stats import binomtest, ttest_1samp
from scipy import stats
import math

num_samples=[100,200,500,1000,2000,5000,10000]#,20000,50000]
#num_samples=[100000,200000,500000]


filename = "measurements/iot_svi_conv_meas_2.csv"


def model():
    result = simulate()
    pyro.sample("analysis result", dist, obs=torch.tensor(result))
"""
def guide():
    alpha_q = pyro.param(
        "alpha_q", 
        torch.tensor(10.0), 
        constraint=constraints.positive)
    beta_q = pyro.param(
        "beta_q", 
        torch.tensor(10.0), 
        constraint=constraints.positive)
    pyro.sample("param_0", 
        pyro.distributions.Beta(alpha_q, beta_q))

guide = pyro.infer.autoguide.AutoMultivariateNormal(model)   
"""

params=[]
def guide():
    alpha_q = pyro.param(
        "alpha_q", 
        torch.tensor(10.0), 
        constraint=constraints.positive)
    beta_q = pyro.param(
        "beta_q", 
        torch.tensor(10.0), 
        constraint=constraints.positive)
    failureProb=pyro.sample("param_0", 
        pyro.distributions.Beta(alpha_q, beta_q))#.expand([40])
    params.append(failureProb)
    dists=[]
    dists.append(RandomVariable(pyro.distributions.Normal(loc=torch.tensor(0.2),scale=torch.tensor(0.02)),"ContRandomVarriablesoftwareTimer0"))
    dists.append(RandomVariable(pyro.distributions.Normal(loc=torch.tensor(0.2),scale=torch.tensor(0.02)),"ContRandomVarriablesoftwareTimer1"))
    dists.append(RandomVariable(pyro.distributions.Exponential(torch.tensor(2.5)),"ContRandomVarriablecarArrival2"))
    dists.append(RandomVariable(pyro.distributions.Normal(loc=torch.tensor(0.3),scale=torch.tensor(0.1)),"ContRandomVarriablecarDelay3"))
    dists.append(RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([torch.tensor(0.9),torch.tensor(0.1)])),name="NetworkLoss4"))
    dists.append(RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([torch.tensor(0.9),torch.tensor(0.1)])),name="NetworkLoss5"))
    dists.append(RandomVariable(pyro.distributions.Bernoulli(failureProb),"DiscRandomVarriableimageBlur6"))
    dists.append(RandomVariable(pyro.distributions.Bernoulli(failureProb),"DiscRandomVarriableimageBlur7"))
    
    #for i in range(len(dists)):
    for i in pyro.plate("initial_samples",len(dists)):
        dists[i].reset()
        #dists[i].meta_cntr=0
       # dists[i].samples=pyro.sample(dists[i].name+"_sample_"+str(dists[i].meta_cntr),dists[i].dist.expand([dists[i].N]))


#guide = pyro.infer.autoguide.AutoDelta(model)
#guide = pyro.infer.autoguide.AutoNormal(pyro.poutine.block(model, hide=['DiscRandomVarriableimageBlur181_sample_0','DiscRandomVarriableimageBlur182_sample_0','DiscRandomVarriableimageBlur181_sample_1','DiscRandomVarriableimageBlur182_sample_1']))
def line_str(step,n_step):
    N=20
    str1=f"{step}/{n_step}:"
    for i in range(N):
        if i/float(N)<=step/float(n_step):
            str1=str1+"-"
        else:
            str1=str1+" "
    return str1

def train(model, guide, lr=0.5, n_steps=1001):
    print("Start training")
    pyro.clear_param_store()
    #adam_params = {"lr": lr}
    #optim = pyro.optim.Adam(adam_params)
    #svi = SVI(model, guide, adam, loss=Trace_ELBO())
    initial_lr = lr
    gamma = 0.005  # final learning rate will be gamma * initial_lr
    lrd = gamma ** (1 / n_steps)
    optim = pyro.optim.ClippedAdam({'lr': initial_lr, 'lrd': lrd})
    print("Create SVI")
    svi = SVI(model, guide, optim, loss=Trace_ELBO())
    data={"step":[],"loss":[]}
    print("Running steps")
    for step in range(n_steps):
        loss = svi.step()
        data["step"].append(step)
        data["loss"].append(loss)
        alpha_q = pyro.param("alpha_q").item()
        beta_q = pyro.param("beta_q").item()
        if step % 50 == 0:
            l=line_str(step,n_steps)
            print(f'[iter {l}]  loss: {loss:.4f}; params: {alpha_q:.2f} {beta_q:.2f}', end='\r')
    print("\n\r")
    #df = pd.DataFrame(data)
    #fig = px.line(df, x="step", y="loss", title='SVI Convergence')
    #fig.show()


def cond_param_analysis(num_samples=1000):
    t0=time.time()
    train(model, guide, n_steps=num_samples)
    alpha_q = pyro.param("alpha_q").item()
    beta_q = pyro.param("beta_q").item()
    inferred_mean = alpha_q / (alpha_q + beta_q)
    factor = beta_q / (alpha_q * (1.0 + alpha_q + beta_q))
    inferred_std = inferred_mean * math.sqrt(factor)
    t1=time.time()
    print(f"Analysis finished in {t1-t0} s")
    #est_param_0=autoguide_mle.median()["param_0"].item()
    print(f"Estimated param: {inferred_mean:.4f} ± {inferred_std:.4f}")
    return inferred_mean
    
    
header=["num_sample", "p_est", "ci_low", "ci_high"] 
R=10
    
if __name__ == "__main__":
    
    print("run inference algorithm...")
    
    try:    
        with open(filename, 'w', newline='\n') as csvfile:
            csvwriter = csv.writer(csvfile, delimiter=',')
            csvwriter.writerow(header)
            for num_sample in num_samples:
                results=[]
                for r in range(R):
                    inferred_mean  = cond_param_analysis(num_sample)
                    results.append(inferred_mean)
                p_est=sum(results)/R
                result = ttest_1samp(a=results,popmean=p_est, alternative='two-sided')
                ci=result.confidence_interval(confidence_level=0.99)
                data=[num_sample, p_est, ci.low, ci.high]
                print(f">>> Results: {data}")
                csvwriter.writerow(data)
    except Exception as err:
        print("Caught Python exception : ", err)
        traceback.print_exc()
    finally:
        print("shutting down JVM...")
        shutdownJVM() 
