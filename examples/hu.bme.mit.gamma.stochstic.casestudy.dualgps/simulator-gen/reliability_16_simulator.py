
import pyro
import torch
import pyro.distributions as dist
import pyro.contrib.gp as gp
import numpy as np

import math
from math import exp
import statistics as stats

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
import hashlib


# turn on debug mode
DEBUG=False
# manually build the java code
BUILD=False
INIT_GEN=False
# synchronization of elementary stochastic components in synchronous composition
IESC_SYNC=False

# conversion between the time unit of elementary stochastic components and millisecond
time_conv=1.0 # 1000000000.0*60*60*1000

simTime=100.0
simNumber=10000

def visualizeMarginal(inference, marginal, name):
	sample_num=10000
	bin_num=100
	marginal_samples = torch.stack([torch.abs(marginal()) for _ in range(sample_num)])
	fig, a = plt.subplots()
	a.set_title( "Empirical marginal "+name+" (ESS:"+str(round(inference.get_ESS().item(),2))+", avg:"+str(round(marginal.mean.item(),2))+", stddev:"+str(round(marginal.variance.sqrt().item(),2))+")" )
	a.hist(marginal_samples.numpy(), color='b',bins=bin_num, density=1, label="Marginal of "+name)
	a.set_ylabel("Estimated density")
	a.set_xlabel("Value of "+name)

debug_diag_cntr=0
debug_diag=""
diag_hashes=set()
def dprint(*args, **kwargs):
	global debug_diag
	for a in args:
		debug_diag=debug_diag+str(a)
	debug_diag=debug_diag+"\n"

def dinit():
	global debug_diag
	debug_diag="""
	@startuml
	participant "Stochastic Models" as stochmodel
	participant "Deteministic Models" as detmodel
	participant "Analysis Case" as analysis
	"""

def dsave():
	global debug_diag, debug_diag_cntr
	debug_diag=debug_diag+("@enduml")
	#hash_str=hashlib.md5(debug_diag.encode()).hexdigest()
	#if hash_str not in diag_hashes:
	isExist = os.path.exists("debug_diag")
	if not isExist:
		os.makedirs("debug_diag")
	with open(f'debug_diag/diag{debug_diag_cntr}.plantuml', 'w') as f:
		f.write(debug_diag)
	debug_diag_cntr=debug_diag_cntr+1
	print(debug_diag)
	debug_diag=""

print('initiating Python-Java connection')

def create_detmodel():
	if BUILD:
		commands = ["""javac $(find C:\\Users\\simon\\Projects\\stochastic-gamma\\runtime-hu.bme.mit.gamma.environment.rcp.product -name "*.java")"""]
		for command in commands:
			if os.system(command) == 0:
				continue
			else:
				print( "ERROR")
				break
	startJVM("""C:\\Program Files\\Java\\jdk-20\\bin\\server\\jvm.dll""", '-ea',"""-Djava.class.path=C:\\Users\\simon\\git\\stochastic-gamma\\examples\\hu.bme.mit.gamma.stochstic.casestudy.dualgps\\bin""")
	detmodel = 0
	EntryPoint = JClass('javaenv.Reliability_16EntryPoint')
	detmodel = EntryPoint()
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



class DiscreteRandomVariable():
	def __init__(self,dist,name):
		self.dist=dist
		self.name=name
		self.event_cntr=0
	def calc(self,event=0,time=0):
		self.event_cntr=self.event_cntr+1
		return pyro.sample(self.name+"_sample_"+str(self.event_cntr),self.dist).item()-1.0


class RandomVariable():
	plate=pyro.plate("random_variable")
	def __init__(self,dist,name,N=1):
		self.dist=dist
		self.name=name
		self.event_cntr=N-1
		self.meta_cntr=-1
		self.N=N
	def sampleb(self):
		return pyro.sample(self.name+"_sample_"+str(self.meta_cntr),self.dist.expand([self.N]))
	def calc(self,event=0,time=0):
		self.event_cntr=self.event_cntr+1
		if self.N>0:
			if self.event_cntr==self.N:
				self.event_cntr=0
				self.meta_cntr=self.meta_cntr+1
				with RandomVariable.plate:
					self.samples=self.sampleb()
			return self.samples[self.event_cntr].item()
		else:
			with RandomVariable.plate:
				return pyro.sample(self.name+"_sample_"+str(self.event_cntr),self.dist).item()
	def reset(self):
		self.event_cntr=-1 # self.N-1
		self.meta_cntr=0#-1
		with RandomVariable.plate:
			if self.N>0:
				self.samples=self.sampleb()




#
# environment component classes


class Event():
	def __init__(self,eventSource,eventTime,eventCall,name="anonymous"):
		self.eventSource=eventSource
		self.eventTime=eventTime
		self.eventCall=eventCall
		self.name=name


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
					ename=port+"."+pevent
					while simulationtime < simTime:
						simulationtime=simulationtime+rule.calc(port+"."+pevent,simulationtime)
						#iterating through port connections
						for call in calls:
							self.simulator.events.append(Event(self,simulationtime,call,ename))




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
					ename=port+"."+pevent
					if time>=0:
						#iterating through port connections
						for call in calls:
							self.simulator.events.append(Event(self,time,call,ename))





#
class StochasticEventGenerator():


	def __init__(self,detmodel):
		self.detmodel=detmodel
		self.time=0.0
		self.events=[]
		self.dists=[]
		self.min_i=0
		# create Python objects from elementary stochastic components
		self.components=dict()
		#0
		# definition of elementary stochastic components
		
		self.components.clear()
		
		self.components.update({ "System.GPS1__Failure" : EventSource()})
		self.components.update({ "System.GPS2__Failure" : EventSource()})
		self.components.update({ "System.GPS3__Failure" : EventSource()})
		self.components.update({ "System.GPS4__Failure" : EventSource()})
		self.components.update({ "System.GPS5__Failure" : EventSource()})
		self.components.update({ "System.GPS6__Failure" : EventSource()})
		self.components.update({ "System.GPS7__Failure" : EventSource()})
		self.components.update({ "System.GPS8__Failure" : EventSource()})
		self.components.update({ "System.GPS9__Failure" : EventSource()})
		self.components.update({ "System.GPS10_Failure" : EventSource()})
		self.components.update({ "System.GPS11_Failure" : EventSource()})
		self.components.update({ "System.GPS12_Failure" : EventSource()})
		self.components.update({ "System.GPS13_Failure" : EventSource()})
		self.components.update({ "System.GPS14_Failure" : EventSource()})
		self.components.update({ "System.GPS15_Failure" : EventSource()})
		self.components.update({ "System.GPS16_Failure" : EventSource()})
		self.components.update({ "System.Voter_Failure" : EventSource()})
		
		
		
		
		
		# register input interfaces of elementary stochastic components
		
		
		
		self.components["System.GPS1__Failure"].configure(
				name  = "System.GPS1__Failure",
				calls = {'Faults' : {'Failure' : [(lambda:self.detmodel.getSystem().getGPS1_().getFaults().raiseFailure())]}},
				rules = {'Faults' : {'Failure' : RandomVariable(pyro.distributions.Exponential(torch.tensor(10.0)),"ContRandomVarriableGPS1__Failure0")}},
				portevents = 	{	"Faults" : [ "Failure"	]},
				simulator=self)
				
		
		self.components["System.GPS2__Failure"].configure(
				name  = "System.GPS2__Failure",
				calls = {'Faults' : {'Failure' : [(lambda:self.detmodel.getSystem().getGPS2_().getFaults().raiseFailure())]}},
				rules = {'Faults' : {'Failure' : RandomVariable(pyro.distributions.Exponential(torch.tensor(10.0)),"ContRandomVarriableGPS2__Failure1")}},
				portevents = 	{	"Faults" : [ "Failure"	]},
				simulator=self)
				
		
		self.components["System.GPS3__Failure"].configure(
				name  = "System.GPS3__Failure",
				calls = {'Faults' : {'Failure' : [(lambda:self.detmodel.getSystem().getGPS3_().getFaults().raiseFailure())]}},
				rules = {'Faults' : {'Failure' : RandomVariable(pyro.distributions.Exponential(torch.tensor(10.0)),"ContRandomVarriableGPS3__Failure2")}},
				portevents = 	{	"Faults" : [ "Failure"	]},
				simulator=self)
				
		
		self.components["System.GPS4__Failure"].configure(
				name  = "System.GPS4__Failure",
				calls = {'Faults' : {'Failure' : [(lambda:self.detmodel.getSystem().getGPS4_().getFaults().raiseFailure())]}},
				rules = {'Faults' : {'Failure' : RandomVariable(pyro.distributions.Exponential(torch.tensor(10.0)),"ContRandomVarriableGPS4__Failure3")}},
				portevents = 	{	"Faults" : [ "Failure"	]},
				simulator=self)
				
		
		self.components["System.GPS5__Failure"].configure(
				name  = "System.GPS5__Failure",
				calls = {'Faults' : {'Failure' : [(lambda:self.detmodel.getSystem().getGPS5_().getFaults().raiseFailure())]}},
				rules = {'Faults' : {'Failure' : RandomVariable(pyro.distributions.Exponential(torch.tensor(10.0)),"ContRandomVarriableGPS5__Failure4")}},
				portevents = 	{	"Faults" : [ "Failure"	]},
				simulator=self)
				
		
		self.components["System.GPS6__Failure"].configure(
				name  = "System.GPS6__Failure",
				calls = {'Faults' : {'Failure' : [(lambda:self.detmodel.getSystem().getGPS6_().getFaults().raiseFailure())]}},
				rules = {'Faults' : {'Failure' : RandomVariable(pyro.distributions.Exponential(torch.tensor(10.0)),"ContRandomVarriableGPS6__Failure5")}},
				portevents = 	{	"Faults" : [ "Failure"	]},
				simulator=self)
				
		
		self.components["System.GPS7__Failure"].configure(
				name  = "System.GPS7__Failure",
				calls = {'Faults' : {'Failure' : [(lambda:self.detmodel.getSystem().getGPS7_().getFaults().raiseFailure())]}},
				rules = {'Faults' : {'Failure' : RandomVariable(pyro.distributions.Exponential(torch.tensor(10.0)),"ContRandomVarriableGPS7__Failure6")}},
				portevents = 	{	"Faults" : [ "Failure"	]},
				simulator=self)
				
		
		self.components["System.GPS8__Failure"].configure(
				name  = "System.GPS8__Failure",
				calls = {'Faults' : {'Failure' : [(lambda:self.detmodel.getSystem().getGPS8_().getFaults().raiseFailure())]}},
				rules = {'Faults' : {'Failure' : RandomVariable(pyro.distributions.Exponential(torch.tensor(10.0)),"ContRandomVarriableGPS8__Failure7")}},
				portevents = 	{	"Faults" : [ "Failure"	]},
				simulator=self)
				
		
		self.components["System.GPS9__Failure"].configure(
				name  = "System.GPS9__Failure",
				calls = {'Faults' : {'Failure' : [(lambda:self.detmodel.getSystem().getGPS9_().getFaults().raiseFailure())]}},
				rules = {'Faults' : {'Failure' : RandomVariable(pyro.distributions.Exponential(torch.tensor(10.0)),"ContRandomVarriableGPS9__Failure8")}},
				portevents = 	{	"Faults" : [ "Failure"	]},
				simulator=self)
				
		
		self.components["System.GPS10_Failure"].configure(
				name  = "System.GPS10_Failure",
				calls = {'Faults' : {'Failure' : [(lambda:self.detmodel.getSystem().getGPS10().getFaults().raiseFailure())]}},
				rules = {'Faults' : {'Failure' : RandomVariable(pyro.distributions.Exponential(torch.tensor(10.0)),"ContRandomVarriableGPS10_Failure9")}},
				portevents = 	{	"Faults" : [ "Failure"	]},
				simulator=self)
				
		
		self.components["System.GPS11_Failure"].configure(
				name  = "System.GPS11_Failure",
				calls = {'Faults' : {'Failure' : [(lambda:self.detmodel.getSystem().getGPS11().getFaults().raiseFailure())]}},
				rules = {'Faults' : {'Failure' : RandomVariable(pyro.distributions.Exponential(torch.tensor(10.0)),"ContRandomVarriableGPS11_Failure10")}},
				portevents = 	{	"Faults" : [ "Failure"	]},
				simulator=self)
				
		
		self.components["System.GPS12_Failure"].configure(
				name  = "System.GPS12_Failure",
				calls = {'Faults' : {'Failure' : [(lambda:self.detmodel.getSystem().getGPS12().getFaults().raiseFailure())]}},
				rules = {'Faults' : {'Failure' : RandomVariable(pyro.distributions.Exponential(torch.tensor(10.0)),"ContRandomVarriableGPS12_Failure11")}},
				portevents = 	{	"Faults" : [ "Failure"	]},
				simulator=self)
				
		
		self.components["System.GPS13_Failure"].configure(
				name  = "System.GPS13_Failure",
				calls = {'Faults' : {'Failure' : [(lambda:self.detmodel.getSystem().getGPS13().getFaults().raiseFailure())]}},
				rules = {'Faults' : {'Failure' : RandomVariable(pyro.distributions.Exponential(torch.tensor(10.0)),"ContRandomVarriableGPS13_Failure12")}},
				portevents = 	{	"Faults" : [ "Failure"	]},
				simulator=self)
				
		
		self.components["System.GPS14_Failure"].configure(
				name  = "System.GPS14_Failure",
				calls = {'Faults' : {'Failure' : [(lambda:self.detmodel.getSystem().getGPS14().getFaults().raiseFailure())]}},
				rules = {'Faults' : {'Failure' : RandomVariable(pyro.distributions.Exponential(torch.tensor(10.0)),"ContRandomVarriableGPS14_Failure13")}},
				portevents = 	{	"Faults" : [ "Failure"	]},
				simulator=self)
				
		
		self.components["System.GPS15_Failure"].configure(
				name  = "System.GPS15_Failure",
				calls = {'Faults' : {'Failure' : [(lambda:self.detmodel.getSystem().getGPS15().getFaults().raiseFailure())]}},
				rules = {'Faults' : {'Failure' : RandomVariable(pyro.distributions.Exponential(torch.tensor(10.0)),"ContRandomVarriableGPS15_Failure14")}},
				portevents = 	{	"Faults" : [ "Failure"	]},
				simulator=self)
				
		
		self.components["System.GPS16_Failure"].configure(
				name  = "System.GPS16_Failure",
				calls = {'Faults' : {'Failure' : [(lambda:self.detmodel.getSystem().getGPS16().getFaults().raiseFailure())]}},
				rules = {'Faults' : {'Failure' : RandomVariable(pyro.distributions.Exponential(torch.tensor(10.0)),"ContRandomVarriableGPS16_Failure15")}},
				portevents = 	{	"Faults" : [ "Failure"	]},
				simulator=self)
				
		
		self.components["System.Voter_Failure"].configure(
				name  = "System.Voter_Failure",
				calls = {'Faults' : {'Failure' : [(lambda:self.detmodel.getSystem().getVoter().getFaults().raiseFailure())]}},
				rules = {'Faults' : {'Failure' : RandomVariable(pyro.distributions.Exponential(torch.tensor(20.0)),"ContRandomVarriableVoter_Failure16")}},
				portevents = 	{	"Faults" : [ "Failure"	]},
				simulator=self)
				
		
		
		
		
		

	def reset(self):
		self.time=0
		self.events.clear()
		for i in pyro.plate("initial_samples",len(self.dists)):
			self.dists[i].reset()
		self.detmodel.reset()
		"""self.detmodel.reset()"""

	def generateEvents(self):
		for component in list(self.components.values()):
			component.generateEvents()

	# shall be called after the getEarliestTime() function
	def popEvent(self):
		event=self.events[self.min_i]
		self.events.remove(event)
		return event

	def getEarliestTime(self):
		mintime=1000000000000000.0
		min_i=0
		for i in range (len(self.events)):
			if self.events[i].eventTime<mintime:
				min_i=i
				mintime=self.events[min_i].eventTime
		self.min_i=min_i
		return mintime-self.time

def guide():
	dists=[]
	
	dists.append[RandomVariable(pyro.distributions.Exponential(torch.tensor(10.0)),"ContRandomVarriableGPS1__Failure0")]
	dists.append[RandomVariable(pyro.distributions.Exponential(torch.tensor(10.0)),"ContRandomVarriableGPS2__Failure1")]
	dists.append[RandomVariable(pyro.distributions.Exponential(torch.tensor(10.0)),"ContRandomVarriableGPS3__Failure2")]
	dists.append[RandomVariable(pyro.distributions.Exponential(torch.tensor(10.0)),"ContRandomVarriableGPS4__Failure3")]
	dists.append[RandomVariable(pyro.distributions.Exponential(torch.tensor(10.0)),"ContRandomVarriableGPS5__Failure4")]
	dists.append[RandomVariable(pyro.distributions.Exponential(torch.tensor(10.0)),"ContRandomVarriableGPS6__Failure5")]
	dists.append[RandomVariable(pyro.distributions.Exponential(torch.tensor(10.0)),"ContRandomVarriableGPS7__Failure6")]
	dists.append[RandomVariable(pyro.distributions.Exponential(torch.tensor(10.0)),"ContRandomVarriableGPS8__Failure7")]
	dists.append[RandomVariable(pyro.distributions.Exponential(torch.tensor(10.0)),"ContRandomVarriableGPS9__Failure8")]
	dists.append[RandomVariable(pyro.distributions.Exponential(torch.tensor(10.0)),"ContRandomVarriableGPS10_Failure9")]
	dists.append[RandomVariable(pyro.distributions.Exponential(torch.tensor(10.0)),"ContRandomVarriableGPS11_Failure10")]
	dists.append[RandomVariable(pyro.distributions.Exponential(torch.tensor(10.0)),"ContRandomVarriableGPS12_Failure11")]
	dists.append[RandomVariable(pyro.distributions.Exponential(torch.tensor(10.0)),"ContRandomVarriableGPS13_Failure12")]
	dists.append[RandomVariable(pyro.distributions.Exponential(torch.tensor(10.0)),"ContRandomVarriableGPS14_Failure13")]
	dists.append[RandomVariable(pyro.distributions.Exponential(torch.tensor(10.0)),"ContRandomVarriableGPS15_Failure14")]
	dists.append[RandomVariable(pyro.distributions.Exponential(torch.tensor(10.0)),"ContRandomVarriableGPS16_Failure15")]
	dists.append[RandomVariable(pyro.distributions.Exponential(torch.tensor(20.0)),"ContRandomVarriableVoter_Failure16")]
	
	for dist in dists:
		dist.reset()
	# 


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
	
	# DEBUG variables
	AspectSystem_Communication_FailstopFreq=0
	
	if DEBUG:
		print("New simulation run --------------------------------------------------")
		dinit()
	
	# initialize the stochastic event generator
	stochmodel.reset()
	stochmodel.generateEvents()
	
	# schedule the asynchronous component
	detmodel.getSystem().schedule()
	
	if DEBUG and INIT_GEN:
		print("Initial events: ---------------------------------------------")
		dprint('note over stochmodel ')
		dprint('| Source of the event | Name of the event | Time of the event |')
		for event in stochmodel.events:
			print("      ESC name: ", event.eventSource.name + "   Event name: " + event.name +'   Time: ' + str(event.eventTime))
			dprint("|   ", event.eventSource.name + "   | " + event.name +'   | ' + str(event.eventTime)+ ' |')
		print("Simulation events: ---------------------------------------------")
		dprint('endnote')
		#dprint("== Simulation Starts ==")
	
	# run the simulator until there are stochastic events available and simulation time is not reached
	while len(stochmodel.events) > 0 and stochmodel.time <= simTime:
		

		
		# get the event with the earliest clock
		stochmodel.getEarliestTime()
		event = stochmodel.popEvent()
		
		# insert the event into the deterministic evaluator
		stochmodel.time = event.eventTime
		
		if stochmodel.time > simTime :
			if DEBUG:
				print("End condition is satisfied: ---------------------------------------------")
				print("       Out of time")
				dprint("== Simulation Ends: Time limit is reached ==")
			break
		
		# print debug event information
		if DEBUG:
			print("      ESC name: ", event.eventSource.name + "   Event name: " + event.name + '   Time: ' + str(event.eventTime))
			dprint(f'stochmodel -> detmodel : "{event.eventSource.name}  ::  {event.name} at {str(event.eventTime)}"')
			
		# raise the event
		event.eventCall()
		
		# schedule the deterministic evaluator
		detmodel.getSystem().schedule()
		

		# evaluate end condition
		#register the result of the analysis to the Pyro
		if DEBUG:
			# register the time only if the event is raised
			if int(detmodel.monitorOfAspectSystem_Communication_Failstop.freq) != AspectSystem_Communication_FailstopFreq :
				AspectSystem_Communication_FailstopFreq=int(detmodel.monitorOfAspectSystem_Communication_Failstop.freq)
				dprint(f'detmodel -> analysis : "Communication.failstop at time {stochmodel.time}"')
		
		if detmodel.monitorOfEndConditionSystem_Communication_Failstop.state != "run":
			# print debug end condition information
			if DEBUG:
				print("End condition is satisfied: ---------------------------------------------")
				print("      EndConditionSystem_Communication_Failstop : ", detmodel.monitorOfEndConditionSystem_Communication_Failstop.state)
				dprint('hnote over analysis ')
				dprint('EndConditionSystem_Communication_Failstop is reached')
				dprint("endnote")
			break
	
	
	#register the result of the analysis to the Pyro
	# register the time only if the event is raised
	if str(detmodel.monitorOfAspectSystem_Communication_Failstop.state) != "run" :
		pyro.deterministic("Communication_failstop_mt",torch.tensor(stochmodel.time))
	
	
	#register the conditions to the Pyro
	
	if DEBUG:
		print("Simulation is finished! ---------------------------------------------")
		dsave()
	
	if DEBUG: 
		if len(stochmodel.events) == 0:
			dprint('hnote over stochmodel ')
			dprint('no stochastic event')
			dprint("endnote")				
		
	
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
			# run simulation sampling
			
			print("Run simulation analysis...")
			t0=time.time()
			data_Communication_failstop_mt =  []
			for i in range(10000):
				Communication_failstop_mt=simulate()
				data_Communication_failstop_mt.append(Communication_failstop_mt)
			t1=time.time()
			# visualize results
			print(f"Analysis is finished in {t1-t0} s")
			print("Results of the analysis: ")
			print("Estimated Communication_failstop_mt = ",round(stats.mean(data_Communication_failstop_mt),4))
			print("visualize results...")
			#visualizeMarginal(inference,empirical_marginal_Communication_failstop_mt,'Communication_failstop_mt')
			plt.show()
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



