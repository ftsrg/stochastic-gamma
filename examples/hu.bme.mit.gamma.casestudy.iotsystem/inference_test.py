import sys
sys.path.append('simulator-gen/.')

from serviceavailability_32_simulator import *
import pyro


print("Run inference algorithm")
t0 = time.time()
inference = pyro.infer.Importance(model=simulate, num_samples=1000)
inference.run()
marginal = pyro.infer.EmpiricalMarginal(inference, "Failures_newEvent_prob")
ess=inference.get_ESS()
est_mean=marginal.mean.item()
est_var=marginal.variance.item()
ss=math.sqrt(est_var)#/num_samples
t1 = time.time()
print(f"Analysis finished in {(t1-t0):0.2f} s with ESS={ess:.2f}")
print(f"Estimated parameter is {est_mean:.4f} ± {ss:.4f}")
shutdownJVM()