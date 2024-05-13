from pyinstrument import Profiler
import pyro
#from serviceavailability_16_simulator import *
from inference_test_2 import cond_param_analysis
import traceback
from jpype import *

#inference=pyro.infer.Importance(model=simulate, num_samples=50)

profiler = Profiler()
profiler.start()
#print("run inference algorithm...")
#inference.run()
#d=[simulate() for i in range(50)]

try:
    cond_param_analysis(8000)
except Exception as err:
    print("Caught Python exception : ", err)
    traceback.print_exc()
finally:
    print("shutting down JVM...")
    shutdownJVM() 
    

profiler.stop()
profiler.print()
profiler.open_in_browser()