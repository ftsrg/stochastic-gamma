from time import time
import sys
sys.path.append('simulator-gen/.')

t0=time()

from serviceavailability_32_simulator import *
t1=time.time()
print("Initalization time: ",t1-t0)

import os
import traceback
from statistics import mean, median, variance
from pandas.core.config_init import tc_sim_interactive_doc

import torch

import pyro
import pyro.distributions as dist
import pyro.infer
import pyro.optim

DEBUG=False
meas_num=10

sim_runtime=[]
det_runtime=[]
stoch_runtime=[]
init_runtime=[]

def simulate2():
    if DEBUG:
        print("new sim ---------------------------------")
    global stochmodel, detmodel,det_runtime
    stochmodel.reset()
    stochmodel.generateEvents()
    sum_time=0.0
    while len(stochmodel.events) > 0 and stochmodel.time < simTime:
        stochmodel.getEarliestTime()
        event = stochmodel.popEvent()
        stochmodel.time = event.eventTime
        if DEBUG:
            print(event.eventSource.name + ' at time: ' + str(stochmodel.time))
        t0=time.time()
        event.eventCall()
        detmodel.getSystem().schedule()
        # evaluate end condition
        t1=time.time()
        sum_time=sum_time+(t1-t0)
        if detmodel.monitorOfEndConditionSystem_CarLeave_NewEvent.state != "run":
            break
    det_runtime.append(sum_time)
    # get the aspects and return from the simulations 
    #register the result of the analysis to the Pyro
    stochmodel.time
    state2num(detmodel.monitorOfAspectSystem_Failures_NewEvent.state)
    pyro.deterministic("Failures_newEvent_prob",torch.tensor(state2num(detmodel.monitorOfAspectSystem_Failures_NewEvent.state)))
    #return the result of the simulation
    return state2num(detmodel.monitorOfAspectSystem_Failures_NewEvent.state)
     

print("Start runtime measurements")
for sim_i in range(meas_num):
    print("Simulation iteration: ",sim_i)
    t0=time.time()
    simulate()
    t1=time.time()
    sim_runtime.append(t1-t0)
    #stoch_runtime.append(sim_runtime[sim_i]-det_runtime[sim_i])
print("Measurement finished")
print("Median simulation time: ",median(sim_runtime))
shutdownJVM()
#print("Median stochastic simulation time: ",median(stoch_runtime))
#print("Median deterministic simulation time: ",median(det_runtime))
"""
shutdownJVM()
for sim_i in range(meas_num):
    print("Initalization iteration: ",sim_i)
    t0=time.time()
    detmodel_=create_detmodel()
    stochmodel_ = StochasticEventGenerator(detmodel)
    t1=time.time()
    shutdownJVM()
    init_runtime.append(t1-t0)
print("Initalization time: ",init_runtime)
"""

