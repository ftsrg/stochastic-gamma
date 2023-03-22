from simulator_jpype import simulate
from jpype import shutdownJVM
import traceback
import matplotlib.pyplot as plt
from statistics import median
import time
if __name__ == "__main__":
    try:
        tlist=list()
        for i in range(1):
            t0=time.time()
            v=simulate()
            t1=time.time()
            tlist.append(t1-t0)
            print("median for camera r16 c2 : ",median(tlist),", v = ",v)                                                             
    except Exception as err:                                        
        print("Exception occured during testing the simulation: ")  
        print(err)                                                  
        traceback.print_exc()                                       
    finally:                                                        
        print("shuting down the Py4J gateway")                      
        shutdownJVM()                                         
        print("exit") 