
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

simTime=10.0
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
	startJVM("""C:\\Users\\simon.nagy\\Programs\\stoch-gamma-dev-eclipse\\eclipse\\plugins\\org.eclipse.justj.openjdk.hotspot.jre.full.win32.x86_64_17.0.6.v20230204-1729\\jre\\bin\\server\\jvm.dll""", '-ea',"""-Djava.class.path=C:\\Users\\simon.nagy\\Projects\\stochastic-gamma-measurements\\stochastic-gamma-measurements\\hu.bme.mit.gamma.stochastic.casestudy.orion\\bin""")
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



if4 = JClass('hu.bme.mit.gamma.stochastic.casestudy.orion.interfaces.StateMachine_Interface_For_OrionInterface$Listener$Provided')
if4_s = JClass('hu.bme.mit.gamma.stochastic.casestudy.orion.scheduling.ElementaryComponentSchedulingInterface')

@JImplements([if4,if4_s])
class SwitchStateMachine_Interface_For_Orion():

	def configure(self,name,inport,calls,portarray,categorical,simulator,compCall,shname):
		self.name=name
		self.calls=calls
		self.categorical=categorical
		self.portarray=portarray
		self.event_cntr=0
		self.events=[]
		self.inport=inport
		if inport is not None:
			inport.registerListener(self)
		compCall.registerEnvironmentComponent(shname,self)
		self.simulator=simulator
		self.simulator.dists.append(categorical)

	def generateEvents(self):
		pass



	@JOverride
	def schedule(self):
		for event in self.events:
			event.callEvent()
		self.events.clear()

	@JOverride
	def isEventQueueEmpty(self):
		return (len(self.events)==0)
	
	
	#definition of the interface functions

	@JOverride
	def raiseOrionDisconn(self,):
		port=self.portarray[self.categorical.calc()]
		eventcalls=self.calls[port]#["OrionDisconn"]
		self.event_cntr=self.event_cntr+1
		for call in eventcalls:
			if call is not None:
				if IESC_SYNC:
					callEvent=lambda:call.raiseOrionDisconn();
					self.events.append(Event(self,self.simulator.time,callEvent))
				else:
					call.raiseOrionDisconn()

	@JOverride
	def raiseOrionDisconnCause(self,):
		port=self.portarray[self.categorical.calc()]
		eventcalls=self.calls[port]#["OrionDisconnCause"]
		self.event_cntr=self.event_cntr+1
		for call in eventcalls:
			if call is not None:
				if IESC_SYNC:
					callEvent=lambda:call.raiseOrionDisconnCause();
					self.events.append(Event(self,self.simulator.time,callEvent))
				else:
					call.raiseOrionDisconnCause()

	@JOverride
	def raiseOrionConnReq(self,):
		port=self.portarray[self.categorical.calc()]
		eventcalls=self.calls[port]#["OrionConnReq"]
		self.event_cntr=self.event_cntr+1
		for call in eventcalls:
			if call is not None:
				if IESC_SYNC:
					callEvent=lambda:call.raiseOrionConnReq();
					self.events.append(Event(self,self.simulator.time,callEvent))
				else:
					call.raiseOrionConnReq()

	@JOverride
	def raiseOrionAppData(self,):
		port=self.portarray[self.categorical.calc()]
		eventcalls=self.calls[port]#["OrionAppData"]
		self.event_cntr=self.event_cntr+1
		for call in eventcalls:
			if call is not None:
				if IESC_SYNC:
					callEvent=lambda:call.raiseOrionAppData();
					self.events.append(Event(self,self.simulator.time,callEvent))
				else:
					call.raiseOrionAppData()

	@JOverride
	def raiseOrionKeepAlive(self,):
		port=self.portarray[self.categorical.calc()]
		eventcalls=self.calls[port]#["OrionKeepAlive"]
		self.event_cntr=self.event_cntr+1
		for call in eventcalls:
			if call is not None:
				if IESC_SYNC:
					callEvent=lambda:call.raiseOrionKeepAlive();
					self.events.append(Event(self,self.simulator.time,callEvent))
				else:
					call.raiseOrionKeepAlive()

	@JOverride
	def raiseOrionConnConf(self,):
		port=self.portarray[self.categorical.calc()]
		eventcalls=self.calls[port]#["OrionConnConf"]
		self.event_cntr=self.event_cntr+1
		for call in eventcalls:
			if call is not None:
				if IESC_SYNC:
					callEvent=lambda:call.raiseOrionConnConf();
					self.events.append(Event(self,self.simulator.time,callEvent))
				else:
					call.raiseOrionConnConf()

	@JOverride
	def raiseOrionConnResp(self,):
		port=self.portarray[self.categorical.calc()]
		eventcalls=self.calls[port]#["OrionConnResp"]
		self.event_cntr=self.event_cntr+1
		for call in eventcalls:
			if call is not None:
				if IESC_SYNC:
					callEvent=lambda:call.raiseOrionConnResp();
					self.events.append(Event(self,self.simulator.time,callEvent))
				else:
					call.raiseOrionConnResp()
##	class Java:
#		implements = ["hu.bme.mit.gamma.stochastic.casestudy.orion.interfaces.StateMachine_Interface_For_OrionInterface$Listener$Provided"]


#
class StochasticEventGenerator():


	def __init__(self,detmodel):
		self.detmodel=detmodel
		self.time=0.0
		self.events=[]
		self.dists=[]
		# create Python objects from elementary stochastic components
		self.components=dict()
		self.timerMean=torch.tensor([0.000001])
		#0
		#0
		# definition of elementary stochastic components
		
		self.components.clear()
		self.components.update({ ".Orion()TimerKeepAliveReceiveTimeout_3" : PeriodicEventSource()})
		self.components.update({ ".Orion()TimerKapcsolodik_2" : PeriodicEventSource()})
		self.components.update({ ".Orion()TimerZarva_0" : PeriodicEventSource()})
		self.components.update({ ".Orion()TimerKeepAliveSendTimeout_1" : PeriodicEventSource()})
		self.components.update({ ".Orion()TimerKeepAliveReceiveTimeout_4" : PeriodicEventSource()})
		self.components.update({ ".Orion()TimerKapcsolodik_3" : PeriodicEventSource()})
		self.components.update({ ".Orion()TimerKeepAliveSendTimeout_0" : PeriodicEventSource()})
		
		
		
		
		self.components.update({ ".Orion().OrionSystem().Master()masterSlaveChannel" : SwitchStateMachine_Interface_For_Orion()})
		self.components.update({ ".Orion().OrionSystem().Master()slaveMasterChannel" : SwitchStateMachine_Interface_For_Orion()})
		
		
		# register input interfaces of elementary stochastic components
		
		self.components[".Orion()TimerKeepAliveReceiveTimeout_3"].configure(
				name  = ".Orion()TimerKeepAliveReceiveTimeout_3",
				calls = {'TimoeutKeepAliveReceiveTimeout_3' : {'NewEvent' : [(lambda:self.detmodel.getOrion().getOrionSystem().getTimoeutKeepAliveReceiveTimeout_3().raiseNewEvent())]}},
				rules = {'TimoeutKeepAliveReceiveTimeout_3' : {'NewEvent' : ContinuousRandomVariable(pyro.distributions.Normal(loc=self.timerMean[0],scale=torch.tensor(0.5)),"ContRandomVarriableTimerKeepAliveReceiveTimeout_327")}},
				portevents = 	{	"TimoeutKeepAliveReceiveTimeout_3" : [ "NewEvent"	]},
				simulator=self)
				
		self.components[".Orion()TimerKapcsolodik_2"].configure(
				name  = ".Orion()TimerKapcsolodik_2",
				calls = {'TimeoutKapcsolodik_2' : {'NewEvent' : [(lambda:self.detmodel.getOrion().getOrionSystem().getTimeoutKapcsolodik_2().raiseNewEvent())]}},
				rules = {'TimeoutKapcsolodik_2' : {'NewEvent' : ContinuousRandomVariable(pyro.distributions.Normal(loc=self.timerMean[0],scale=torch.tensor(0.5)),"ContRandomVarriableTimerKapcsolodik_228")}},
				portevents = 	{	"TimeoutKapcsolodik_2" : [ "NewEvent"	]},
				simulator=self)
				
		self.components[".Orion()TimerZarva_0"].configure(
				name  = ".Orion()TimerZarva_0",
				calls = {'TimeoutZarva_0' : {'NewEvent' : [(lambda:self.detmodel.getOrion().getOrionSystem().getTimeoutZarva_0().raiseNewEvent())]}},
				rules = {'TimeoutZarva_0' : {'NewEvent' : ContinuousRandomVariable(pyro.distributions.Normal(loc=self.timerMean[0],scale=torch.tensor(0.5)),"ContRandomVarriableTimerZarva_029")}},
				portevents = 	{	"TimeoutZarva_0" : [ "NewEvent"	]},
				simulator=self)
				
		self.components[".Orion()TimerKeepAliveSendTimeout_1"].configure(
				name  = ".Orion()TimerKeepAliveSendTimeout_1",
				calls = {'TimeoutKeepAliveSendTimeout_1' : {'NewEvent' : [(lambda:self.detmodel.getOrion().getOrionSystem().getTimeoutKeepAliveSendTimeout_1().raiseNewEvent())]}},
				rules = {'TimeoutKeepAliveSendTimeout_1' : {'NewEvent' : ContinuousRandomVariable(pyro.distributions.Normal(loc=self.timerMean[0],scale=torch.tensor(0.5)),"ContRandomVarriableTimerKeepAliveSendTimeout_130")}},
				portevents = 	{	"TimeoutKeepAliveSendTimeout_1" : [ "NewEvent"	]},
				simulator=self)
				
		self.components[".Orion()TimerKeepAliveReceiveTimeout_4"].configure(
				name  = ".Orion()TimerKeepAliveReceiveTimeout_4",
				calls = {'TimeoutKeepAliveReceiveTimeout_4' : {'NewEvent' : [(lambda:self.detmodel.getOrion().getOrionSystem().getTimeoutKeepAliveReceiveTimeout_4().raiseNewEvent())]}},
				rules = {'TimeoutKeepAliveReceiveTimeout_4' : {'NewEvent' : ContinuousRandomVariable(pyro.distributions.Normal(loc=self.timerMean[0],scale=torch.tensor(0.5)),"ContRandomVarriableTimerKeepAliveReceiveTimeout_431")}},
				portevents = 	{	"TimeoutKeepAliveReceiveTimeout_4" : [ "NewEvent"	]},
				simulator=self)
				
		self.components[".Orion()TimerKapcsolodik_3"].configure(
				name  = ".Orion()TimerKapcsolodik_3",
				calls = {'TimeoutKapcsolodik_3' : {'NewEvent' : [(lambda:self.detmodel.getOrion().getOrionSystem().getTimeoutKapcsolodik_3().raiseNewEvent())]}},
				rules = {'TimeoutKapcsolodik_3' : {'NewEvent' : ContinuousRandomVariable(pyro.distributions.Normal(loc=self.timerMean[0],scale=torch.tensor(0.5)),"ContRandomVarriableTimerKapcsolodik_332")}},
				portevents = 	{	"TimeoutKapcsolodik_3" : [ "NewEvent"	]},
				simulator=self)
				
		self.components[".Orion()TimerKeepAliveSendTimeout_0"].configure(
				name  = ".Orion()TimerKeepAliveSendTimeout_0",
				calls = {'TimeoutKeepAliveSendTimeout_0' : {'NewEvent' : [(lambda:self.detmodel.getOrion().getOrionSystem().getTimeoutKeepAliveSendTimeout_0().raiseNewEvent())]}},
				rules = {'TimeoutKeepAliveSendTimeout_0' : {'NewEvent' : ContinuousRandomVariable(pyro.distributions.Normal(loc=self.timerMean[0],scale=torch.tensor(0.5)),"ContRandomVarriableTimerKeepAliveSendTimeout_033")}},
				portevents = 	{	"TimeoutKeepAliveSendTimeout_0" : [ "NewEvent"	]},
				simulator=self)
				
		
		
		
		self.components[".Orion().OrionSystem().Master()masterSlaveChannel"].configure(
				name  = ".Orion().OrionSystem().Master()masterSlaveChannel",
				inport=self.detmodel.getOrion().getOrionSystem().getMaster().getMaster().getSend_StateMachine_Port(),
				calls={'Output' : [self.detmodel.getOrion().getOrionSystem().getMaster().getSlave().getStateMachine_Port(), ], 'Lossport' : [None]},
				portarray=["Output", "Lossport"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						0.9, 
										0.1
								])),
					name="MasterSlaveChannel34")
				,
				simulator=self,
				shname="masterSlaveChannel",
				compCall=self.detmodel.getOrion().getOrionSystem().getMaster())
				
		self.components[".Orion().OrionSystem().Master()slaveMasterChannel"].configure(
				name  = ".Orion().OrionSystem().Master()slaveMasterChannel",
				inport=self.detmodel.getOrion().getOrionSystem().getMaster().getSlave().getSend_StateMachine_Port(),
				calls={'Output' : [self.detmodel.getOrion().getOrionSystem().getMaster().getMaster().getStateMachine_Port(), ], 'Lossport' : [None]},
				portarray=["Output", "Lossport"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						0.9, 
										0.1
								])),
					name="SlaveMasterChannel35")
				,
				simulator=self,
				shname="slaveMasterChannel",
				compCall=self.detmodel.getOrion().getOrionSystem().getMaster())
				
		
		
		

	def reset(self):
		self.time=0
		self.events.clear()
		for dist in self.dists:
			dist.reset()
		self.timerMean[0]=pyro.sample("param_0",pyro.distributions.Uniform(low=torch.tensor(4.0),high=torch.tensor(6.0))).detach()
		#0
		self.detmodel.reset()#self.timerMean#1
		

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
		detmodel.getOrion().schedule()
		
		# evaluate end condition
		
		if detmodel.monitorOfEndConditionOrionSystemConnStatusConn.state != "run":
			# print debug end condition information
			if DEBUG:
				print("EndConditionOrionSystemConnStatusConn : ", detmodel.monitorOfEndConditionOrionSystemConnStatusConn.state)
			break
	
	
	#register the result of the analysis to the Pyro
	pyro.deterministic("SystemConnStatus_conn_mt",torch.tensor(stochmodel.time))
	
	
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
			empirical_marginal_SystemConnStatus_conn_mt = pyro.infer.EmpiricalMarginal(inference, "SystemConnStatus_conn_mt")
			# visualize results
			print("Results of the analysis: ")
			print("Estimated SystemConnStatus_conn_mt = ",round(empirical_marginal_SystemConnStatus_conn_mt.mean.item(),2))
			print("visualize results...")
			visualizeMarginal(inference,empirical_marginal_SystemConnStatus_conn_mt,'SystemConnStatus_conn_mt')
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



