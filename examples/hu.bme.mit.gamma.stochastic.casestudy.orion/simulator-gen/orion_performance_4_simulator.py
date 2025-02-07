
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

simTime=300.0
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
	startJVM("""C:\\Program Files\\Java\\jdk-20\\bin\\server\\jvm.dll""", '-ea',"""-Djava.class.path=C:\\Users\\simon\\git\\stochastic-gamma\\examples\\hu.bme.mit.gamma.stochastic.casestudy.orion\\bin""")
	detmodel = 0
	EntryPoint = JClass('javaenv.Orion_Performance_4EntryPoint')
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
	def __init__(self,dist,name,N=30):
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


if57 = JClass('hu.bme.mit.gamma.stochastic.casestudy.orion.interfaces.SoftwareTimerInterface$Listener$Provided')
if57_s = JClass('hu.bme.mit.gamma.stochastic.casestudy.orion.scheduling.ElementaryComponentSchedulingInterface')

@JImplements([if57,if57_s])
class DelaySoftwareTimer():
	def configure(self,name,inport,calls,rules,simulator):
		self.name=name
		callitem=list(calls.items())[0]#only one out port
		self.calls=callitem[1]
		self.port=callitem[0]
		self.rules=list(rules.items())[0][1]#only one out port
		self.event_cntr=0
		self.inport=inport
		if inport is not None:
			inport.registerListener(self)
		self.simulator=simulator
		#iterating through ports
		for port in list(rules.keys()):
			pevents=list(rules[port].keys())
			#iterating through events
			for pevent in pevents:
				rule=rules[port][pevent]
				simulator.dists.append(rule)


	def generateEvents(self):
		pass

	@JOverride
	def schedule(self):
		pass

	@JOverride
	def isEventQueueEmpty(self):
		return True

	#definition of the interface functions
	
	@JOverride
	def raiseNewEvent(self,):
		time=self.rules["NewEvent"].calc(self.port+"."+"NewEvent",self.simulator.time)
		self.event_cntr=self.event_cntr+1
		failureTime=abs(time)+self.simulator.time
		if DEBUG:
			dprint(f'detmodel -> stochmodel : {self.name} :: {self.port}.NewEvent at {self.simulator.time}')
		for callitem in self.calls:
			callEvent=lambda:callitem.raiseNewEvent();
			self.simulator.events.append(Event(self,failureTime,callEvent,self.port+".NewEvent"))
##	class Java:
#		implements = ["hu.bme.mit.gamma.stochastic.casestudy.orion.interfaces.SoftwareTimerInterface$Listener$Provided"]


if58 = JClass('hu.bme.mit.gamma.stochastic.casestudy.orion.interfaces.StateMachine_Interface_For_OrionInterface$Listener$Provided')
if58_s = JClass('hu.bme.mit.gamma.stochastic.casestudy.orion.scheduling.ElementaryComponentSchedulingInterface')

@JImplements([if58,if58_s])
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
		if DEBUG:
			dprint(f'detmodel -> stochmodel : {self.name} :: {port}+.OrionDisconn at {self.simulator.time}')
		for call in eventcalls:
			if call is not None:
				if IESC_SYNC:
					if DEBUG:
						dprint(f'detmodel -> stochmodel : {self.name} :: {port}+.OrionDisconn at {self.simulator.time}')
					callEvent=lambda:call.raiseOrionDisconn();
					self.events.append(Event(self,self.simulator.time,callEvent,self.port+".OrionDisconn"))
				else:
					if DEBUG:
						dprint(f'detmodel <-> stochmodel : {self.name} :: {port}+.OrionDisconn at {self.simulator.time}')
					call.raiseOrionDisconn()

	@JOverride
	def raiseOrionDisconnCause(self,):
		port=self.portarray[self.categorical.calc()]
		eventcalls=self.calls[port]#["OrionDisconnCause"]
		self.event_cntr=self.event_cntr+1
		if DEBUG:
			dprint(f'detmodel -> stochmodel : {self.name} :: {port}+.OrionDisconnCause at {self.simulator.time}')
		for call in eventcalls:
			if call is not None:
				if IESC_SYNC:
					if DEBUG:
						dprint(f'detmodel -> stochmodel : {self.name} :: {port}+.OrionDisconnCause at {self.simulator.time}')
					callEvent=lambda:call.raiseOrionDisconnCause();
					self.events.append(Event(self,self.simulator.time,callEvent,self.port+".OrionDisconnCause"))
				else:
					if DEBUG:
						dprint(f'detmodel <-> stochmodel : {self.name} :: {port}+.OrionDisconnCause at {self.simulator.time}')
					call.raiseOrionDisconnCause()

	@JOverride
	def raiseOrionConnReq(self,):
		port=self.portarray[self.categorical.calc()]
		eventcalls=self.calls[port]#["OrionConnReq"]
		self.event_cntr=self.event_cntr+1
		if DEBUG:
			dprint(f'detmodel -> stochmodel : {self.name} :: {port}+.OrionConnReq at {self.simulator.time}')
		for call in eventcalls:
			if call is not None:
				if IESC_SYNC:
					if DEBUG:
						dprint(f'detmodel -> stochmodel : {self.name} :: {port}+.OrionConnReq at {self.simulator.time}')
					callEvent=lambda:call.raiseOrionConnReq();
					self.events.append(Event(self,self.simulator.time,callEvent,self.port+".OrionConnReq"))
				else:
					if DEBUG:
						dprint(f'detmodel <-> stochmodel : {self.name} :: {port}+.OrionConnReq at {self.simulator.time}')
					call.raiseOrionConnReq()

	@JOverride
	def raiseOrionAppData(self,):
		port=self.portarray[self.categorical.calc()]
		eventcalls=self.calls[port]#["OrionAppData"]
		self.event_cntr=self.event_cntr+1
		if DEBUG:
			dprint(f'detmodel -> stochmodel : {self.name} :: {port}+.OrionAppData at {self.simulator.time}')
		for call in eventcalls:
			if call is not None:
				if IESC_SYNC:
					if DEBUG:
						dprint(f'detmodel -> stochmodel : {self.name} :: {port}+.OrionAppData at {self.simulator.time}')
					callEvent=lambda:call.raiseOrionAppData();
					self.events.append(Event(self,self.simulator.time,callEvent,self.port+".OrionAppData"))
				else:
					if DEBUG:
						dprint(f'detmodel <-> stochmodel : {self.name} :: {port}+.OrionAppData at {self.simulator.time}')
					call.raiseOrionAppData()

	@JOverride
	def raiseOrionKeepAlive(self,):
		port=self.portarray[self.categorical.calc()]
		eventcalls=self.calls[port]#["OrionKeepAlive"]
		self.event_cntr=self.event_cntr+1
		if DEBUG:
			dprint(f'detmodel -> stochmodel : {self.name} :: {port}+.OrionKeepAlive at {self.simulator.time}')
		for call in eventcalls:
			if call is not None:
				if IESC_SYNC:
					if DEBUG:
						dprint(f'detmodel -> stochmodel : {self.name} :: {port}+.OrionKeepAlive at {self.simulator.time}')
					callEvent=lambda:call.raiseOrionKeepAlive();
					self.events.append(Event(self,self.simulator.time,callEvent,self.port+".OrionKeepAlive"))
				else:
					if DEBUG:
						dprint(f'detmodel <-> stochmodel : {self.name} :: {port}+.OrionKeepAlive at {self.simulator.time}')
					call.raiseOrionKeepAlive()

	@JOverride
	def raiseOrionConnConf(self,):
		port=self.portarray[self.categorical.calc()]
		eventcalls=self.calls[port]#["OrionConnConf"]
		self.event_cntr=self.event_cntr+1
		if DEBUG:
			dprint(f'detmodel -> stochmodel : {self.name} :: {port}+.OrionConnConf at {self.simulator.time}')
		for call in eventcalls:
			if call is not None:
				if IESC_SYNC:
					if DEBUG:
						dprint(f'detmodel -> stochmodel : {self.name} :: {port}+.OrionConnConf at {self.simulator.time}')
					callEvent=lambda:call.raiseOrionConnConf();
					self.events.append(Event(self,self.simulator.time,callEvent,self.port+".OrionConnConf"))
				else:
					if DEBUG:
						dprint(f'detmodel <-> stochmodel : {self.name} :: {port}+.OrionConnConf at {self.simulator.time}')
					call.raiseOrionConnConf()

	@JOverride
	def raiseOrionConnResp(self,):
		port=self.portarray[self.categorical.calc()]
		eventcalls=self.calls[port]#["OrionConnResp"]
		self.event_cntr=self.event_cntr+1
		if DEBUG:
			dprint(f'detmodel -> stochmodel : {self.name} :: {port}+.OrionConnResp at {self.simulator.time}')
		for call in eventcalls:
			if call is not None:
				if IESC_SYNC:
					if DEBUG:
						dprint(f'detmodel -> stochmodel : {self.name} :: {port}+.OrionConnResp at {self.simulator.time}')
					callEvent=lambda:call.raiseOrionConnResp();
					self.events.append(Event(self,self.simulator.time,callEvent,self.port+".OrionConnResp"))
				else:
					if DEBUG:
						dprint(f'detmodel <-> stochmodel : {self.name} :: {port}+.OrionConnResp at {self.simulator.time}')
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
		self.min_i=0
		# create Python objects from elementary stochastic components
		self.components=dict()
		#0
		# definition of elementary stochastic components
		
		self.components.clear()
		
		
		self.components.update({ "Orion.SubSystem1_.DelayKeepAliveReceiveTimeout_3" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem1_.DelayKapcsolodik_2" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem1_.DelayZarva_0" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem1_.DelayKeepAliveSendTimeout_1" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem1_.DelayKeepAliveReceiveTimeout_4" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem1_.DelayKapcsolodik_3" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem1_.DelayKeepAliveSendTimeout_0" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem2_.DelayKeepAliveReceiveTimeout_3" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem2_.DelayKapcsolodik_2" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem2_.DelayZarva_0" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem2_.DelayKeepAliveSendTimeout_1" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem2_.DelayKeepAliveReceiveTimeout_4" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem2_.DelayKapcsolodik_3" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem2_.DelayKeepAliveSendTimeout_0" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem3_.DelayKeepAliveReceiveTimeout_3" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem3_.DelayKapcsolodik_2" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem3_.DelayZarva_0" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem3_.DelayKeepAliveSendTimeout_1" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem3_.DelayKeepAliveReceiveTimeout_4" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem3_.DelayKapcsolodik_3" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem3_.DelayKeepAliveSendTimeout_0" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem4_.DelayKeepAliveReceiveTimeout_3" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem4_.DelayKapcsolodik_2" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem4_.DelayZarva_0" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem4_.DelayKeepAliveSendTimeout_1" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem4_.DelayKeepAliveReceiveTimeout_4" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem4_.DelayKapcsolodik_3" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem4_.DelayKeepAliveSendTimeout_0" : DelaySoftwareTimer()})
		
		
		self.components.update({ "Orion.SubSystem1_.OrionSystem.System.masterSlaveChannel" : SwitchStateMachine_Interface_For_Orion()})
		self.components.update({ "Orion.SubSystem1_.OrionSystem.System.slaveMasterChannel" : SwitchStateMachine_Interface_For_Orion()})
		self.components.update({ "Orion.SubSystem2_.OrionSystem.System.masterSlaveChannel" : SwitchStateMachine_Interface_For_Orion()})
		self.components.update({ "Orion.SubSystem2_.OrionSystem.System.slaveMasterChannel" : SwitchStateMachine_Interface_For_Orion()})
		self.components.update({ "Orion.SubSystem3_.OrionSystem.System.masterSlaveChannel" : SwitchStateMachine_Interface_For_Orion()})
		self.components.update({ "Orion.SubSystem3_.OrionSystem.System.slaveMasterChannel" : SwitchStateMachine_Interface_For_Orion()})
		self.components.update({ "Orion.SubSystem4_.OrionSystem.System.masterSlaveChannel" : SwitchStateMachine_Interface_For_Orion()})
		self.components.update({ "Orion.SubSystem4_.OrionSystem.System.slaveMasterChannel" : SwitchStateMachine_Interface_For_Orion()})
		
		
		# register input interfaces of elementary stochastic components
		
		
		
		self.components["Orion.SubSystem1_.DelayKeepAliveReceiveTimeout_3"].configure(
				name  = "Orion.SubSystem1_.DelayKeepAliveReceiveTimeout_3",
				inport=self.detmodel.getOrion().getSubSystem1_().getOrionSystem().getTimoeutKeepAliveReceiveTimeout_3_req(),
				calls = {'TimoeutKeepAliveReceiveTimeout_3' : [self.detmodel.getOrion().getSubSystem1_().getOrionSystem().getTimoeutKeepAliveReceiveTimeout_3(), ]},
				rules = {'TimoeutKeepAliveReceiveTimeout_3' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_30")}},
				simulator=self)
				
		self.components["Orion.SubSystem1_.DelayKapcsolodik_2"].configure(
				name  = "Orion.SubSystem1_.DelayKapcsolodik_2",
				inport=self.detmodel.getOrion().getSubSystem1_().getOrionSystem().getTimeoutKapcsolodik_2_req(),
				calls = {'TimeoutKapcsolodik_2' : [self.detmodel.getOrion().getSubSystem1_().getOrionSystem().getTimeoutKapcsolodik_2(), ]},
				rules = {'TimeoutKapcsolodik_2' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_21")}},
				simulator=self)
				
		self.components["Orion.SubSystem1_.DelayZarva_0"].configure(
				name  = "Orion.SubSystem1_.DelayZarva_0",
				inport=self.detmodel.getOrion().getSubSystem1_().getOrionSystem().getTimeoutZarva_0_req(),
				calls = {'TimeoutZarva_0' : [self.detmodel.getOrion().getSubSystem1_().getOrionSystem().getTimeoutZarva_0(), ]},
				rules = {'TimeoutZarva_0' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayZarva_02")}},
				simulator=self)
				
		self.components["Orion.SubSystem1_.DelayKeepAliveSendTimeout_1"].configure(
				name  = "Orion.SubSystem1_.DelayKeepAliveSendTimeout_1",
				inport=self.detmodel.getOrion().getSubSystem1_().getOrionSystem().getTimeoutKeepAliveSendTimeout_1_req(),
				calls = {'TimeoutKeepAliveSendTimeout_1' : [self.detmodel.getOrion().getSubSystem1_().getOrionSystem().getTimeoutKeepAliveSendTimeout_1(), ]},
				rules = {'TimeoutKeepAliveSendTimeout_1' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_13")}},
				simulator=self)
				
		self.components["Orion.SubSystem1_.DelayKeepAliveReceiveTimeout_4"].configure(
				name  = "Orion.SubSystem1_.DelayKeepAliveReceiveTimeout_4",
				inport=self.detmodel.getOrion().getSubSystem1_().getOrionSystem().getTimeoutKeepAliveReceiveTimeout_4_req(),
				calls = {'TimeoutKeepAliveReceiveTimeout_4' : [self.detmodel.getOrion().getSubSystem1_().getOrionSystem().getTimeoutKeepAliveReceiveTimeout_4(), ]},
				rules = {'TimeoutKeepAliveReceiveTimeout_4' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_44")}},
				simulator=self)
				
		self.components["Orion.SubSystem1_.DelayKapcsolodik_3"].configure(
				name  = "Orion.SubSystem1_.DelayKapcsolodik_3",
				inport=self.detmodel.getOrion().getSubSystem1_().getOrionSystem().getTimeoutKapcsolodik_3_req(),
				calls = {'TimeoutKapcsolodik_3' : [self.detmodel.getOrion().getSubSystem1_().getOrionSystem().getTimeoutKapcsolodik_3(), ]},
				rules = {'TimeoutKapcsolodik_3' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_35")}},
				simulator=self)
				
		self.components["Orion.SubSystem1_.DelayKeepAliveSendTimeout_0"].configure(
				name  = "Orion.SubSystem1_.DelayKeepAliveSendTimeout_0",
				inport=self.detmodel.getOrion().getSubSystem1_().getOrionSystem().getTimeoutKeepAliveSendTimeout_0_req(),
				calls = {'TimeoutKeepAliveSendTimeout_0' : [self.detmodel.getOrion().getSubSystem1_().getOrionSystem().getTimeoutKeepAliveSendTimeout_0(), ]},
				rules = {'TimeoutKeepAliveSendTimeout_0' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_06")}},
				simulator=self)
				
		self.components["Orion.SubSystem2_.DelayKeepAliveReceiveTimeout_3"].configure(
				name  = "Orion.SubSystem2_.DelayKeepAliveReceiveTimeout_3",
				inport=self.detmodel.getOrion().getSubSystem2_().getOrionSystem().getTimoeutKeepAliveReceiveTimeout_3_req(),
				calls = {'TimoeutKeepAliveReceiveTimeout_3' : [self.detmodel.getOrion().getSubSystem2_().getOrionSystem().getTimoeutKeepAliveReceiveTimeout_3(), ]},
				rules = {'TimoeutKeepAliveReceiveTimeout_3' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_37")}},
				simulator=self)
				
		self.components["Orion.SubSystem2_.DelayKapcsolodik_2"].configure(
				name  = "Orion.SubSystem2_.DelayKapcsolodik_2",
				inport=self.detmodel.getOrion().getSubSystem2_().getOrionSystem().getTimeoutKapcsolodik_2_req(),
				calls = {'TimeoutKapcsolodik_2' : [self.detmodel.getOrion().getSubSystem2_().getOrionSystem().getTimeoutKapcsolodik_2(), ]},
				rules = {'TimeoutKapcsolodik_2' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_28")}},
				simulator=self)
				
		self.components["Orion.SubSystem2_.DelayZarva_0"].configure(
				name  = "Orion.SubSystem2_.DelayZarva_0",
				inport=self.detmodel.getOrion().getSubSystem2_().getOrionSystem().getTimeoutZarva_0_req(),
				calls = {'TimeoutZarva_0' : [self.detmodel.getOrion().getSubSystem2_().getOrionSystem().getTimeoutZarva_0(), ]},
				rules = {'TimeoutZarva_0' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayZarva_09")}},
				simulator=self)
				
		self.components["Orion.SubSystem2_.DelayKeepAliveSendTimeout_1"].configure(
				name  = "Orion.SubSystem2_.DelayKeepAliveSendTimeout_1",
				inport=self.detmodel.getOrion().getSubSystem2_().getOrionSystem().getTimeoutKeepAliveSendTimeout_1_req(),
				calls = {'TimeoutKeepAliveSendTimeout_1' : [self.detmodel.getOrion().getSubSystem2_().getOrionSystem().getTimeoutKeepAliveSendTimeout_1(), ]},
				rules = {'TimeoutKeepAliveSendTimeout_1' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_110")}},
				simulator=self)
				
		self.components["Orion.SubSystem2_.DelayKeepAliveReceiveTimeout_4"].configure(
				name  = "Orion.SubSystem2_.DelayKeepAliveReceiveTimeout_4",
				inport=self.detmodel.getOrion().getSubSystem2_().getOrionSystem().getTimeoutKeepAliveReceiveTimeout_4_req(),
				calls = {'TimeoutKeepAliveReceiveTimeout_4' : [self.detmodel.getOrion().getSubSystem2_().getOrionSystem().getTimeoutKeepAliveReceiveTimeout_4(), ]},
				rules = {'TimeoutKeepAliveReceiveTimeout_4' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_411")}},
				simulator=self)
				
		self.components["Orion.SubSystem2_.DelayKapcsolodik_3"].configure(
				name  = "Orion.SubSystem2_.DelayKapcsolodik_3",
				inport=self.detmodel.getOrion().getSubSystem2_().getOrionSystem().getTimeoutKapcsolodik_3_req(),
				calls = {'TimeoutKapcsolodik_3' : [self.detmodel.getOrion().getSubSystem2_().getOrionSystem().getTimeoutKapcsolodik_3(), ]},
				rules = {'TimeoutKapcsolodik_3' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_312")}},
				simulator=self)
				
		self.components["Orion.SubSystem2_.DelayKeepAliveSendTimeout_0"].configure(
				name  = "Orion.SubSystem2_.DelayKeepAliveSendTimeout_0",
				inport=self.detmodel.getOrion().getSubSystem2_().getOrionSystem().getTimeoutKeepAliveSendTimeout_0_req(),
				calls = {'TimeoutKeepAliveSendTimeout_0' : [self.detmodel.getOrion().getSubSystem2_().getOrionSystem().getTimeoutKeepAliveSendTimeout_0(), ]},
				rules = {'TimeoutKeepAliveSendTimeout_0' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_013")}},
				simulator=self)
				
		self.components["Orion.SubSystem3_.DelayKeepAliveReceiveTimeout_3"].configure(
				name  = "Orion.SubSystem3_.DelayKeepAliveReceiveTimeout_3",
				inport=self.detmodel.getOrion().getSubSystem3_().getOrionSystem().getTimoeutKeepAliveReceiveTimeout_3_req(),
				calls = {'TimoeutKeepAliveReceiveTimeout_3' : [self.detmodel.getOrion().getSubSystem3_().getOrionSystem().getTimoeutKeepAliveReceiveTimeout_3(), ]},
				rules = {'TimoeutKeepAliveReceiveTimeout_3' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_314")}},
				simulator=self)
				
		self.components["Orion.SubSystem3_.DelayKapcsolodik_2"].configure(
				name  = "Orion.SubSystem3_.DelayKapcsolodik_2",
				inport=self.detmodel.getOrion().getSubSystem3_().getOrionSystem().getTimeoutKapcsolodik_2_req(),
				calls = {'TimeoutKapcsolodik_2' : [self.detmodel.getOrion().getSubSystem3_().getOrionSystem().getTimeoutKapcsolodik_2(), ]},
				rules = {'TimeoutKapcsolodik_2' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_215")}},
				simulator=self)
				
		self.components["Orion.SubSystem3_.DelayZarva_0"].configure(
				name  = "Orion.SubSystem3_.DelayZarva_0",
				inport=self.detmodel.getOrion().getSubSystem3_().getOrionSystem().getTimeoutZarva_0_req(),
				calls = {'TimeoutZarva_0' : [self.detmodel.getOrion().getSubSystem3_().getOrionSystem().getTimeoutZarva_0(), ]},
				rules = {'TimeoutZarva_0' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayZarva_016")}},
				simulator=self)
				
		self.components["Orion.SubSystem3_.DelayKeepAliveSendTimeout_1"].configure(
				name  = "Orion.SubSystem3_.DelayKeepAliveSendTimeout_1",
				inport=self.detmodel.getOrion().getSubSystem3_().getOrionSystem().getTimeoutKeepAliveSendTimeout_1_req(),
				calls = {'TimeoutKeepAliveSendTimeout_1' : [self.detmodel.getOrion().getSubSystem3_().getOrionSystem().getTimeoutKeepAliveSendTimeout_1(), ]},
				rules = {'TimeoutKeepAliveSendTimeout_1' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_117")}},
				simulator=self)
				
		self.components["Orion.SubSystem3_.DelayKeepAliveReceiveTimeout_4"].configure(
				name  = "Orion.SubSystem3_.DelayKeepAliveReceiveTimeout_4",
				inport=self.detmodel.getOrion().getSubSystem3_().getOrionSystem().getTimeoutKeepAliveReceiveTimeout_4_req(),
				calls = {'TimeoutKeepAliveReceiveTimeout_4' : [self.detmodel.getOrion().getSubSystem3_().getOrionSystem().getTimeoutKeepAliveReceiveTimeout_4(), ]},
				rules = {'TimeoutKeepAliveReceiveTimeout_4' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_418")}},
				simulator=self)
				
		self.components["Orion.SubSystem3_.DelayKapcsolodik_3"].configure(
				name  = "Orion.SubSystem3_.DelayKapcsolodik_3",
				inport=self.detmodel.getOrion().getSubSystem3_().getOrionSystem().getTimeoutKapcsolodik_3_req(),
				calls = {'TimeoutKapcsolodik_3' : [self.detmodel.getOrion().getSubSystem3_().getOrionSystem().getTimeoutKapcsolodik_3(), ]},
				rules = {'TimeoutKapcsolodik_3' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_319")}},
				simulator=self)
				
		self.components["Orion.SubSystem3_.DelayKeepAliveSendTimeout_0"].configure(
				name  = "Orion.SubSystem3_.DelayKeepAliveSendTimeout_0",
				inport=self.detmodel.getOrion().getSubSystem3_().getOrionSystem().getTimeoutKeepAliveSendTimeout_0_req(),
				calls = {'TimeoutKeepAliveSendTimeout_0' : [self.detmodel.getOrion().getSubSystem3_().getOrionSystem().getTimeoutKeepAliveSendTimeout_0(), ]},
				rules = {'TimeoutKeepAliveSendTimeout_0' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_020")}},
				simulator=self)
				
		self.components["Orion.SubSystem4_.DelayKeepAliveReceiveTimeout_3"].configure(
				name  = "Orion.SubSystem4_.DelayKeepAliveReceiveTimeout_3",
				inport=self.detmodel.getOrion().getSubSystem4_().getOrionSystem().getTimoeutKeepAliveReceiveTimeout_3_req(),
				calls = {'TimoeutKeepAliveReceiveTimeout_3' : [self.detmodel.getOrion().getSubSystem4_().getOrionSystem().getTimoeutKeepAliveReceiveTimeout_3(), ]},
				rules = {'TimoeutKeepAliveReceiveTimeout_3' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_321")}},
				simulator=self)
				
		self.components["Orion.SubSystem4_.DelayKapcsolodik_2"].configure(
				name  = "Orion.SubSystem4_.DelayKapcsolodik_2",
				inport=self.detmodel.getOrion().getSubSystem4_().getOrionSystem().getTimeoutKapcsolodik_2_req(),
				calls = {'TimeoutKapcsolodik_2' : [self.detmodel.getOrion().getSubSystem4_().getOrionSystem().getTimeoutKapcsolodik_2(), ]},
				rules = {'TimeoutKapcsolodik_2' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_222")}},
				simulator=self)
				
		self.components["Orion.SubSystem4_.DelayZarva_0"].configure(
				name  = "Orion.SubSystem4_.DelayZarva_0",
				inport=self.detmodel.getOrion().getSubSystem4_().getOrionSystem().getTimeoutZarva_0_req(),
				calls = {'TimeoutZarva_0' : [self.detmodel.getOrion().getSubSystem4_().getOrionSystem().getTimeoutZarva_0(), ]},
				rules = {'TimeoutZarva_0' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayZarva_023")}},
				simulator=self)
				
		self.components["Orion.SubSystem4_.DelayKeepAliveSendTimeout_1"].configure(
				name  = "Orion.SubSystem4_.DelayKeepAliveSendTimeout_1",
				inport=self.detmodel.getOrion().getSubSystem4_().getOrionSystem().getTimeoutKeepAliveSendTimeout_1_req(),
				calls = {'TimeoutKeepAliveSendTimeout_1' : [self.detmodel.getOrion().getSubSystem4_().getOrionSystem().getTimeoutKeepAliveSendTimeout_1(), ]},
				rules = {'TimeoutKeepAliveSendTimeout_1' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_124")}},
				simulator=self)
				
		self.components["Orion.SubSystem4_.DelayKeepAliveReceiveTimeout_4"].configure(
				name  = "Orion.SubSystem4_.DelayKeepAliveReceiveTimeout_4",
				inport=self.detmodel.getOrion().getSubSystem4_().getOrionSystem().getTimeoutKeepAliveReceiveTimeout_4_req(),
				calls = {'TimeoutKeepAliveReceiveTimeout_4' : [self.detmodel.getOrion().getSubSystem4_().getOrionSystem().getTimeoutKeepAliveReceiveTimeout_4(), ]},
				rules = {'TimeoutKeepAliveReceiveTimeout_4' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_425")}},
				simulator=self)
				
		self.components["Orion.SubSystem4_.DelayKapcsolodik_3"].configure(
				name  = "Orion.SubSystem4_.DelayKapcsolodik_3",
				inport=self.detmodel.getOrion().getSubSystem4_().getOrionSystem().getTimeoutKapcsolodik_3_req(),
				calls = {'TimeoutKapcsolodik_3' : [self.detmodel.getOrion().getSubSystem4_().getOrionSystem().getTimeoutKapcsolodik_3(), ]},
				rules = {'TimeoutKapcsolodik_3' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_326")}},
				simulator=self)
				
		self.components["Orion.SubSystem4_.DelayKeepAliveSendTimeout_0"].configure(
				name  = "Orion.SubSystem4_.DelayKeepAliveSendTimeout_0",
				inport=self.detmodel.getOrion().getSubSystem4_().getOrionSystem().getTimeoutKeepAliveSendTimeout_0_req(),
				calls = {'TimeoutKeepAliveSendTimeout_0' : [self.detmodel.getOrion().getSubSystem4_().getOrionSystem().getTimeoutKeepAliveSendTimeout_0(), ]},
				rules = {'TimeoutKeepAliveSendTimeout_0' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_027")}},
				simulator=self)
				
		
		self.components["Orion.SubSystem1_.OrionSystem.System.masterSlaveChannel"].configure(
				name  = "Orion.SubSystem1_.OrionSystem.System.masterSlaveChannel",
				inport=self.detmodel.getOrion().getSubSystem1_().getOrionSystem().getSystem().getMaster().getSend_StateMachine_Port(),
				calls={'Output' : [self.detmodel.getOrion().getSubSystem1_().getOrionSystem().getSystem().getSlave().getStateMachine_Port(), ], 'Lossport' : [None]},
				portarray=["Output", "Lossport"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						torch.tensor(0.9), 
										torch.tensor(0.1)
								])),
					name="MasterSlaveChannel28")
				,
				simulator=self,
				shname="masterSlaveChannel",
				compCall=self.detmodel.getOrion().getSubSystem1_().getOrionSystem().getSystem())
				
		self.components["Orion.SubSystem1_.OrionSystem.System.slaveMasterChannel"].configure(
				name  = "Orion.SubSystem1_.OrionSystem.System.slaveMasterChannel",
				inport=self.detmodel.getOrion().getSubSystem1_().getOrionSystem().getSystem().getSlave().getSend_StateMachine_Port(),
				calls={'Output' : [self.detmodel.getOrion().getSubSystem1_().getOrionSystem().getSystem().getMaster().getStateMachine_Port(), ], 'Lossport' : [None]},
				portarray=["Output", "Lossport"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						torch.tensor(0.9), 
										torch.tensor(0.1)
								])),
					name="SlaveMasterChannel29")
				,
				simulator=self,
				shname="slaveMasterChannel",
				compCall=self.detmodel.getOrion().getSubSystem1_().getOrionSystem().getSystem())
				
		self.components["Orion.SubSystem2_.OrionSystem.System.masterSlaveChannel"].configure(
				name  = "Orion.SubSystem2_.OrionSystem.System.masterSlaveChannel",
				inport=self.detmodel.getOrion().getSubSystem2_().getOrionSystem().getSystem().getMaster().getSend_StateMachine_Port(),
				calls={'Output' : [self.detmodel.getOrion().getSubSystem2_().getOrionSystem().getSystem().getSlave().getStateMachine_Port(), ], 'Lossport' : [None]},
				portarray=["Output", "Lossport"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						torch.tensor(0.9), 
										torch.tensor(0.1)
								])),
					name="MasterSlaveChannel30")
				,
				simulator=self,
				shname="masterSlaveChannel",
				compCall=self.detmodel.getOrion().getSubSystem2_().getOrionSystem().getSystem())
				
		self.components["Orion.SubSystem2_.OrionSystem.System.slaveMasterChannel"].configure(
				name  = "Orion.SubSystem2_.OrionSystem.System.slaveMasterChannel",
				inport=self.detmodel.getOrion().getSubSystem2_().getOrionSystem().getSystem().getSlave().getSend_StateMachine_Port(),
				calls={'Output' : [self.detmodel.getOrion().getSubSystem2_().getOrionSystem().getSystem().getMaster().getStateMachine_Port(), ], 'Lossport' : [None]},
				portarray=["Output", "Lossport"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						torch.tensor(0.9), 
										torch.tensor(0.1)
								])),
					name="SlaveMasterChannel31")
				,
				simulator=self,
				shname="slaveMasterChannel",
				compCall=self.detmodel.getOrion().getSubSystem2_().getOrionSystem().getSystem())
				
		self.components["Orion.SubSystem3_.OrionSystem.System.masterSlaveChannel"].configure(
				name  = "Orion.SubSystem3_.OrionSystem.System.masterSlaveChannel",
				inport=self.detmodel.getOrion().getSubSystem3_().getOrionSystem().getSystem().getMaster().getSend_StateMachine_Port(),
				calls={'Output' : [self.detmodel.getOrion().getSubSystem3_().getOrionSystem().getSystem().getSlave().getStateMachine_Port(), ], 'Lossport' : [None]},
				portarray=["Output", "Lossport"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						torch.tensor(0.9), 
										torch.tensor(0.1)
								])),
					name="MasterSlaveChannel32")
				,
				simulator=self,
				shname="masterSlaveChannel",
				compCall=self.detmodel.getOrion().getSubSystem3_().getOrionSystem().getSystem())
				
		self.components["Orion.SubSystem3_.OrionSystem.System.slaveMasterChannel"].configure(
				name  = "Orion.SubSystem3_.OrionSystem.System.slaveMasterChannel",
				inport=self.detmodel.getOrion().getSubSystem3_().getOrionSystem().getSystem().getSlave().getSend_StateMachine_Port(),
				calls={'Output' : [self.detmodel.getOrion().getSubSystem3_().getOrionSystem().getSystem().getMaster().getStateMachine_Port(), ], 'Lossport' : [None]},
				portarray=["Output", "Lossport"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						torch.tensor(0.9), 
										torch.tensor(0.1)
								])),
					name="SlaveMasterChannel33")
				,
				simulator=self,
				shname="slaveMasterChannel",
				compCall=self.detmodel.getOrion().getSubSystem3_().getOrionSystem().getSystem())
				
		self.components["Orion.SubSystem4_.OrionSystem.System.masterSlaveChannel"].configure(
				name  = "Orion.SubSystem4_.OrionSystem.System.masterSlaveChannel",
				inport=self.detmodel.getOrion().getSubSystem4_().getOrionSystem().getSystem().getMaster().getSend_StateMachine_Port(),
				calls={'Output' : [self.detmodel.getOrion().getSubSystem4_().getOrionSystem().getSystem().getSlave().getStateMachine_Port(), ], 'Lossport' : [None]},
				portarray=["Output", "Lossport"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						torch.tensor(0.9), 
										torch.tensor(0.1)
								])),
					name="MasterSlaveChannel34")
				,
				simulator=self,
				shname="masterSlaveChannel",
				compCall=self.detmodel.getOrion().getSubSystem4_().getOrionSystem().getSystem())
				
		self.components["Orion.SubSystem4_.OrionSystem.System.slaveMasterChannel"].configure(
				name  = "Orion.SubSystem4_.OrionSystem.System.slaveMasterChannel",
				inport=self.detmodel.getOrion().getSubSystem4_().getOrionSystem().getSystem().getSlave().getSend_StateMachine_Port(),
				calls={'Output' : [self.detmodel.getOrion().getSubSystem4_().getOrionSystem().getSystem().getMaster().getStateMachine_Port(), ], 'Lossport' : [None]},
				portarray=["Output", "Lossport"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						torch.tensor(0.9), 
										torch.tensor(0.1)
								])),
					name="SlaveMasterChannel35")
				,
				simulator=self,
				shname="slaveMasterChannel",
				compCall=self.detmodel.getOrion().getSubSystem4_().getOrionSystem().getSystem())
				
		
		
		

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
	
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_30")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_21")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayZarva_02")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_13")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_44")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_35")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_06")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_37")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_28")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayZarva_09")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_110")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_411")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_312")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_013")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_314")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_215")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayZarva_016")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_117")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_418")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_319")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_020")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_321")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_222")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayZarva_023")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_124")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_425")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_326")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_027")]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="MasterSlaveChannel28")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="SlaveMasterChannel29")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="MasterSlaveChannel30")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="SlaveMasterChannel31")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="MasterSlaveChannel32")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="SlaveMasterChannel33")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="MasterSlaveChannel34")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="SlaveMasterChannel35")
	]
	
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
	AspectOrion_SystemConnStatus_ConnFreq=0
	
	if DEBUG:
		print("New simulation run --------------------------------------------------")
		dinit()
	
	# initialize the stochastic event generator
	stochmodel.reset()
	stochmodel.generateEvents()
	
	# schedule the asynchronous component
	detmodel.getOrion().schedule()
	
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
		detmodel.getOrion().schedule()
		

		# evaluate end condition
		#register the result of the analysis to the Pyro
		if DEBUG:
			# register the time only if the event is raised
			if int(detmodel.monitorOfAspectOrion_SystemConnStatus_Conn.freq) != AspectOrion_SystemConnStatus_ConnFreq :
				AspectOrion_SystemConnStatus_ConnFreq=int(detmodel.monitorOfAspectOrion_SystemConnStatus_Conn.freq)
				dprint(f'detmodel -> analysis : "SystemConnStatus.conn at time {stochmodel.time}"')
		
		if detmodel.monitorOfEndConditionOrion_SystemConnStatus_Conn.state != "run":
			# print debug end condition information
			if DEBUG:
				print("End condition is satisfied: ---------------------------------------------")
				print("      EndConditionOrion_SystemConnStatus_Conn : ", detmodel.monitorOfEndConditionOrion_SystemConnStatus_Conn.state)
				dprint('hnote over analysis ')
				dprint('EndConditionOrion_SystemConnStatus_Conn is reached')
				dprint("endnote")
			break
	
	
	#register the result of the analysis to the Pyro
	# register the time only if the event is raised
	if str(detmodel.monitorOfAspectOrion_SystemConnStatus_Conn.state) != "run" :
		pyro.deterministic("SystemConnStatus_conn_mt",torch.tensor(stochmodel.time))
	
	
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
			data_SystemConnStatus_conn_mt =  []
			for i in range(10000):
				SystemConnStatus_conn_mt=simulate()
				data_SystemConnStatus_conn_mt.append(SystemConnStatus_conn_mt)
			t1=time.time()
			# visualize results
			print(f"Analysis is finished in {t1-t0} s")
			print("Results of the analysis: ")
			print("Estimated SystemConnStatus_conn_mt = ",round(stats.mean(data_SystemConnStatus_conn_mt),4))
			print("visualize results...")
			#visualizeMarginal(inference,empirical_marginal_SystemConnStatus_conn_mt,'SystemConnStatus_conn_mt')
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



