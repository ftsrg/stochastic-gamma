import sys
sys.path.append('simulator-gen/.')
from reliability_cond_simulator import *
import pyro
import math
import csv

num_samples=[100,200,500,1000,2000,5000,10000]


filename = "measurements/gps_cond_conv_meas_2.csv"

def model():
    result=simulate()


def cond_param_analysis(num_samples=1000):
    print(f"Run inference algorithm with {num_sample} samples")
    t0 = time.time()
    inference = pyro.infer.Importance(model=model, num_samples=num_samples)
    inference.run()
    marginal = pyro.infer.EmpiricalMarginal(inference, "param_0")
    ess=inference.get_ESS().item()
    est_mean=marginal.mean.item()
    est_var=marginal.variance.item()
    ss=math.sqrt(est_var)#/num_samples
    t1 = time.time()
    print(f"Analysis finished in {(t1-t0):.2f} s for {num_samples} samples with ESS={ess:.2f}")
    print(f"Estimated parameter is {est_mean:.4} ± {ss:.4}")
    #print(torch.sum(inference.get_normalized_weights()))
    #print(torch.sum(torch.exp(torch.tensor(inference.log_weights))))
    s,w=marginal._get_samples_and_weights()
    #print("Samples:")
    #print(s)
    #print(marginal._samples)
    #print("Weigths:")
    w=w.exp()
    #print(w)
    L=torch.tensor(float(num_samples))
    sL=math.sqrt(float(num_samples))
    u=s*w/w.sum()
    u_sum=u.sum()
    u_mean=u.mean()
    u_ss=(torch.pow(u-u_mean,2).sum()).sqrt()
    w_ss=w.var().sqrt().item()
    w_sum=w.sum().item()
    w_mean=w.mean().item()
    wf=s*w
    wf_sum=wf.sum().item()
    wf_mean=wf.mean().item()
    wf_ss=wf.var().sqrt().item()
    print(f" {wf_mean} ± {wf_ss} ")
    print(f" {w_mean} ± {w_ss} ")
    print(f"estimate1 {wf_sum/w_sum} in [{(wf_sum-2.0*wf_ss)/(w_sum+2.0*w_ss)},{(wf_sum+2.0*wf_ss)/(w_sum-2.0*w_ss)}] ")
    print(f"estimate2 {wf_mean/w_mean} in [{(wf_mean-2.0*wf_ss/sL)/(w_mean+2.0*w_ss/sL)},{(wf_mean+2.0*wf_ss/sL)/(w_mean-2.0*w_ss/sL)}] ")
    #print(f"Calc 2 results: {u_sum:.4} ± {u_ss:.4} with {w.sum()}")
    
    m2=inference.marginal(["param_0"])
    #print(vars(m2))
    #print(f" {m2._marginals['param_0'].mean.item()} ± {math.sqrt(m2._marginals['param_0'].variance.item()/num_samples)}")
    t1=torch.tensor([tr.nodes['param_0']['value'] for tr in inference.exec_traces])
    #t2=inference.get_normalized_weights()
    t2=torch.exp(torch.tensor(inference.log_weights))
    t2=t2#/t2.sum()
    #print(t1)
    #print(t2)
    t3=t1*t2
    #print(f" Results: {t3.mean().item():4.4} ± {math.sqrt(t3.var().item()/num_samples)}")
    return est_mean, ss, ess, w_mean, w_ss, wf_mean, wf_ss

header=['num_sample','est_mean', 'ss', 'ess', 'w_mean', 'w_ss', 'wf_mean', 'wf_ss']

with open(filename, 'w', newline='\n') as csvfile:
    csvwriter = csv.writer(csvfile, delimiter=',')
    csvwriter.writerow(header)
    for num_sample in num_samples:
        est_mean, ss, ess, w_mean, w_ss, wf_mean, wf_ss = cond_param_analysis(num_sample)
        data=[num_sample, est_mean, ss, ess, w_mean, w_ss, wf_mean, wf_ss]
        csvwriter.writerow(data)
    
