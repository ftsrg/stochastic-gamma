import sys
sys.path.append('simulator-gen/.')
from orion_performance_simulator import *
import pyro
from scipy.stats import binomtest, ttest_1samp
from scipy import stats
import math
import csv


num_samples=[100,200,500,1000,2000,5000,10000,20000,50000]
#num_samples=[100000,200000,500000]


filename = "measurements/orion_sim_conv_meas_3.csv"


def prob_analysis(num_samples=1000):
    t0=time.time()
    samples=[]
    for i in range(num_samples):
        samples.append(simulate())
    #print(samples[10:20])
    mu_est=sum(samples)/num_samples
    result = ttest_1samp(a=samples,popmean=mu_est, alternative='two-sided')
    t1=time.time()
    print(f"Analysis is finished in {(t1-t0):.2f} s for {num_samples} samples")
    print(f"mu_est = {mu_est:.4f}")
    print(f"Confidence interval:")
    ci=result.confidence_interval(confidence_level=0.99)
    print(f"[{ci.low:.4f}, {ci.high:.4f}]")
    return mu_est, ci.low, ci.high
    
    
print("run inference algorithm...")

header=["num_sample", "mu_est", "ci_low", "ci_high"]

with open(filename, 'w', newline='\n') as csvfile:
    csvwriter = csv.writer(csvfile, delimiter=',')
    csvwriter.writerow(header)
    for num_sample in num_samples:
        mu_est, ci_low, ci_high  = prob_analysis(num_sample)
        data=[num_sample, mu_est, ci_low, ci_high]
        csvwriter.writerow(data)
    