
import pyro
import torch
import pyro.distributions as dist
import pyro.contrib.gp as gp
import numpy as np

import math
from math import exp

import matplotlib.pyplot as plt
import matplotlib

# import datetime
# from influxdb import InfluxDBClient


import time
import os
import traceback
from jpype import JImplements, JOverride
from jpype import *
import jpype

DEBUG=False
BUILD=False
IESC_SYNC=False

simTime=100.0
simNumber=50

def visualizeMarginal(inference, marginal, name):
	sample_num=20000
	bin_num=100
	marginal_samples = torch.stack([torch.abs(marginal()) for _ in range(sample_num)])
	fig, a = plt.subplots()
	a.set_title( "Empirical marginal "+name+" (ESS:"+str(round(inference.get_ESS().item(),2))+", avg:"+str(round(marginal.mean.item(),2))+", stddev:"+str(round(marginal.variance.sqrt().item(),2))+")" )
	a.hist(marginal_samples.numpy(), color='b',bins=bin_num, density=1, label="Marginal of "+name)
	plt.ylabel("Estimated density")
	plt.xlabel("Value of "+name)
	plt.show()

print('initiating Python-Java connection')

def create_detmodel():
	if BUILD:
		commands = ["""javac $(find C:\\Users\\simon.nagy\\Projects\\runtime-hu.bme.mit.gamma.environment.rcp.product -name "*.java")"""]
		for command in commands:
			if os.system(command) == 0:
				continue
			else:
				print( "ERROR")
				break
	startJVM("""C:\\Users\\simon.nagy\\Programs\\stoch-gamma-dev-eclipse\\eclipse\\plugins\\org.eclipse.justj.openjdk.hotspot.jre.full.win32.x86_64_17.0.6.v20230204-1729\\jre\\bin\\server\\jvm.dll""", '-ea',"""-Djava.class.path=C:\\Users\\simon.nagy\\Projects\\stochastic-gamma-measurements\\stochastic-gamma-measurements\\hu.bme.mit.gamma.stochstic.casestudy.dualgps\\bin""")
	detmodel = 0
	DetModelEntryPoint = JClass('javaenv.DetModelEntryPoint')
	detmodel = DetModelEntryPoint()
	print('Python-Java connection established')
	return detmodel

detmodel=create_detmodel()



# python classes of random variables and distributions

class Dataset():

	def __init__(self,dbname,ip,port,query=None,script=None):
		if query is not None:
			client = InfluxDBClient(ip, int(port), database=dbname)
			result = client.query(query)
			points = result.get_points()
			self.points=points
		elif script is not None:
			exec(script)


# stochastic model classes

class ContinuousRandomVariable():
	def __init__(self,dist,name,N=1):
		self.dist=dist
		self.name=name
		self.event_cntr=N-1
		self.meta_cntr=-1
		self.N=N
	def calc(self,event=0,time=0):
		self.event_cntr=self.event_cntr+1
		if self.N>0:
			if self.event_cntr==self.N:
				self.event_cntr=0
				self.meta_cntr=self.meta_cntr+1
				self.samples=pyro.sample(self.name+"_sample_"+str(self.meta_cntr),self.dist.expand([self.N]))
			return self.samples[self.event_cntr].item()
		else:
			return pyro.sample(self.name+"_sample_"+str(self.event_cntr),self.dist).item()
	def reset(self):
		self.event_cntr=self.N-1
		self.meta_cntr=-1


class DiscreteRandomVariable():
	def __init__(self,dist,name):
		self.dist=dist
		self.name=name
		self.event_cntr=0
	def calc(self,event=0,time=0):
		self.event_cntr=self.event_cntr+1
		return pyro.sample(self.name+"_sample_"+str(self.event_cntr),self.dist).item()-1.0


class RandomVariable():
	def __init__(self,dist,name,N=1):
		self.dist=dist
		self.name=name
		self.event_cntr=N-1
		self.meta_cntr=-1
		self.N=N
	def calc(self,event=0,time=0):
		self.event_cntr=self.event_cntr+1
		if self.N>0:
			if self.event_cntr==self.N:
				self.event_cntr=0
				self.meta_cntr=self.meta_cntr+1
				
				self.samples=pyro.sample(self.name+"_sample_"+str(self.meta_cntr),self.dist.expand([self.N]))
			return self.samples[self.event_cntr].item()
		else:
			return pyro.sample(self.name+"_sample_"+str(self.event_cntr),self.dist).item()
	def reset(self):
		self.event_cntr=self.N-1
		self.meta_cntr=-1


#
# environment component classes


class Event():
	def __init__(self,eventSource,eventTime):
		self.eventSource=eventSource
		self.eventTime=eventTime
	def __init__(self,eventSource,eventTime,eventCall):
		self.eventSource=eventSource
		self.eventTime=eventTime
		self.eventCall=eventCall


class PeriodicEventSource():
	def configure(self,name,calls,rules,portevents,simulator):
		self.name=name
		self.calls=calls
		self.rules=rules
		self.portevents=portevents
		self.simulator=simulator
		ports=list(self.calls.keys())
		#iterating through ports
		for port in ports:
			pevents=self.portevents[port]
			#iterating through events
			for pevent in pevents:
				if pevent in self.rules[port] and pevent in self.calls[port]:
					rule=self.rules[port][pevent]
					self.simulator.dists.append(rule)

	def generateEvents(self):
		ports=list(self.calls.keys())
		#iterating through self.ports
		for port in ports:
			pevents=self.portevents[port]
			#iterating through events
			for pevent in pevents:
				if pevent in self.rules[port] and pevent in self.calls[port]:
					calls=self.calls[port][pevent]
					rule=self.rules[port][pevent]
					simulationtime=0.0
					while simulationtime < simTime:
						simulationtime=simulationtime+rule.calc(port+"."+pevent,simulationtime)
						#iterating through port connections
						for call in calls:
							self.simulator.events.append(Event(self,simulationtime,call))




class EventSource():
	def configure(self,name,calls,rules,portevents,simulator):
		self.name=name
		self.calls=calls
		self.rules=rules
		self.portevents=portevents
		self.simulator=simulator
		ports=list(self.calls.keys())
		
		#iterating through ports
		for port in ports:
			pevents=self.portevents[port]
			#iterating through events
			for pevent in pevents:
				if pevent in self.rules[port]:
					rule=self.rules[port][pevent]
					self.simulator.dists.append(rule)

	def generateEvents(self):
		ports=list(self.calls.keys())
		#iterating through ports
		for port in ports:
			pevents=self.portevents[port]
			#iterating through events
			for pevent in pevents:
				if pevent in self.rules[port] and pevent in self.calls[port]:
					rule=self.rules[port][pevent]
					calls=self.calls[port][pevent]
					time=rule.calc(port+"."+pevent,0.0)
					if time>=0:
						#iterating through port connections
						for call in calls:
							self.simulator.events.append(Event(self,time,call))





#
class StochasticEventGenerator():


	def __init__(self,detmodel):
		self.detmodel=detmodel
		self.time=0.0
		self.events=[]
		self.dists=[]
		# create Python objects from elementary stochastic components
		self.components=dict()
		#0
		# definition of elementary stochastic components
		
		self.components.clear()
		
		self.components.update({ ".System()GPS1_Failure" : EventSource()})
		self.components.update({ ".System()GPS2_Failure" : EventSource()})
		self.components.update({ ".System()Voter_Failure" : EventSource()})
		
		
		
		
		
		# register input interfaces of elementary stochastic components
		
		
		
		self.components[".System()GPS1_Failure"].configure(
				name  = ".System()GPS1_Failure",
				calls = {'Faults' : {'Failure' : [(lambda:self.detmodel.getSystem().getGPS1().getFaults().raiseFailure())]}},
				rules = {'Faults' : {'Failure' : ContinuousRandomVariable(pyro.distributions.Exponential(torch.tensor(10.0)),"ContRandomVarriableGPS1_Failure3")}},
				portevents = 	{	"Faults" : [ "Failure"	]},
				simulator=self)
				
		
		self.components[".System()GPS2_Failure"].configure(
				name  = ".System()GPS2_Failure",
				calls = {'Faults' : {'Failure' : [(lambda:self.detmodel.getSystem().getGPS2().getFaults().raiseFailure())]}},
				rules = {'Faults' : {'Failure' : ContinuousRandomVariable(pyro.distributions.Exponential(torch.tensor(10.0)),"ContRandomVarriableGPS2_Failure4")}},
				portevents = 	{	"Faults" : [ "Failure"	]},
				simulator=self)
				
		
		self.components[".System()Voter_Failure"].configure(
				name  = ".System()Voter_Failure",
				calls = {'Faults' : {'Failure' : [(lambda:self.detmodel.getSystem().getVoter().getFaults().raiseFailure())]}},
				rules = {'Faults' : {'Failure' : ContinuousRandomVariable(pyro.distributions.Exponential(torch.tensor(20.0)),"ContRandomVarriableVoter_Failure5")}},
				portevents = 	{	"Faults" : [ "Failure"	]},
				simulator=self)
				
		
		
		
		
		

	def reset(self):
		self.time=0
		self.events.clear()
		for dist in self.dists:
			dist.reset()
		self.detmodel.reset()

	def generateEvents(self):
		for component in list(self.components.values()):
			component.generateEvents()


	def popEvent(self):
		mintime=10000000000.0
		min_i=0
		for i in range (len(self.events)):
			if self.events[i].eventTime<mintime:
				min_i=i
				mintime=self.events[min_i].eventTime
		event=self.events[min_i]
		self.events.remove(event)
		return event




print("creating stochastic event generator")
stochmodel=0
try:
	stochmodel = StochasticEventGenerator(detmodel)
	print("stochastic event generator is successfully created")
except jpype.JException as ex:
		print("Caught base exception : ", str(ex))
		print(ex.stacktrace())
		shutdownJVM()
except Exception as ex:
		print("Caught python exception :", str(ex))
		traceback.print_exc()
		shutdownJVM()

def state2num(state):
	if state=="run":
		return 0.0
	else:
		return 1.0

def simulate():
		
	# global objects: stochastic event generator and deterministic evaluator
	global stochmodel, detmodel
	
	
	if DEBUG:
		print("new sim ---------------------------------")
	
	# initialize the stochastic event generator
	stochmodel.reset()
	stochmodel.generateEvents()
	
	# run the simulator until there are stochastic events available and simulation time is not reached
	while len(stochmodel.events) > 0 and stochmodel.time < simTime:
		
		# get the event with the earliest clock
		event = stochmodel.popEvent()
		
		# insert the event into the deterministic evaluator
		stochmodel.time = event.eventTime
		
		if stochmodel.time > simTime :
			if DEBUG:
				print("Out of time")
			break
		
		# print debug event information
		if DEBUG:
			print(event.eventSource.name + ' at time: ' + str(stochmodel.time))
			
		# raise the event
		event.eventCall()
		
		# schedule the deterministic evaluator
		detmodel.getSystem().schedule()
		
		# evaluate end condition
		
		if detmodel.monitorOfEndConditionSystemCommunicationFailstop.state != "run":
			# print debug end condition information
			if DEBUG:
				print("EndConditionSystemCommunicationFailstop : ", detmodel.monitorOfEndConditionSystemCommunicationFailstop.state)
			break
	
	
	#register the result of the analysis to the Pyro
	pyro.deterministic("Communication_failstop_mt",torch.tensor(stochmodel.time))
	
	
	#register the conditions to the Pyro
	
	# get the aspects and return from the simulations 
	
	#return the result of the simulation
	return stochmodel.time

if __name__ == "__main__":
	try:
		# dummy simulations for debugging
		if DEBUG:
			for i in range(10):
				print(simulate())
		else:
			# run importance sampling
			inference=pyro.infer.Importance(model=simulate, num_samples=50)
			print("run inference algorithm...")
			inference.run()
			empirical_marginal_Communication_failstop_mt = pyro.infer.EmpiricalMarginal(inference, "Communication_failstop_mt")
			# visualize results
			print("Results of the analysis: ")
			print("Estimated Communication_failstop_mt = ",round(empirical_marginal_Communication_failstop_mt.mean.item(),2))
			print("visualize results...")
			visualizeMarginal(inference,empirical_marginal_Communication_failstop_mt,'Communication_failstop_mt')
	except java.lang.RuntimeException as ex:
		print("Caught Java runtime exception : ", str(ex))
		print(ex.stacktrace())
	except jpype.JException as ex:
		print("Caught Jpype exception : ", str(ex))
		print(ex.stacktrace())
	except Exception as err:
		print("Caught Python exception : ", err)
		traceback.print_exc()
	finally:
		print("shutting down JVM...")
		shutdownJVM()
	print ("analysis is finished successfully")



