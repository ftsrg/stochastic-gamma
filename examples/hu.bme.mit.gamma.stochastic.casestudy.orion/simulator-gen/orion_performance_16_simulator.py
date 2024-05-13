
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
	EntryPoint = JClass('javaenv.Orion_Performance_16EntryPoint')
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


if51 = JClass('hu.bme.mit.gamma.stochastic.casestudy.orion.interfaces.SoftwareTimerInterface$Listener$Provided')
if51_s = JClass('hu.bme.mit.gamma.stochastic.casestudy.orion.scheduling.ElementaryComponentSchedulingInterface')

@JImplements([if51,if51_s])
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


if52 = JClass('hu.bme.mit.gamma.stochastic.casestudy.orion.interfaces.StateMachine_Interface_For_OrionInterface$Listener$Provided')
if52_s = JClass('hu.bme.mit.gamma.stochastic.casestudy.orion.scheduling.ElementaryComponentSchedulingInterface')

@JImplements([if52,if52_s])
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
		self.components.update({ "Orion.SubSystem5_.DelayKeepAliveReceiveTimeout_3" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem5_.DelayKapcsolodik_2" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem5_.DelayZarva_0" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem5_.DelayKeepAliveSendTimeout_1" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem5_.DelayKeepAliveReceiveTimeout_4" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem5_.DelayKapcsolodik_3" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem5_.DelayKeepAliveSendTimeout_0" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem6_.DelayKeepAliveReceiveTimeout_3" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem6_.DelayKapcsolodik_2" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem6_.DelayZarva_0" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem6_.DelayKeepAliveSendTimeout_1" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem6_.DelayKeepAliveReceiveTimeout_4" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem6_.DelayKapcsolodik_3" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem6_.DelayKeepAliveSendTimeout_0" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem7_.DelayKeepAliveReceiveTimeout_3" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem7_.DelayKapcsolodik_2" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem7_.DelayZarva_0" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem7_.DelayKeepAliveSendTimeout_1" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem7_.DelayKeepAliveReceiveTimeout_4" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem7_.DelayKapcsolodik_3" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem7_.DelayKeepAliveSendTimeout_0" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem8_.DelayKeepAliveReceiveTimeout_3" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem8_.DelayKapcsolodik_2" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem8_.DelayZarva_0" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem8_.DelayKeepAliveSendTimeout_1" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem8_.DelayKeepAliveReceiveTimeout_4" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem8_.DelayKapcsolodik_3" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem8_.DelayKeepAliveSendTimeout_0" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem9_.DelayKeepAliveReceiveTimeout_3" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem9_.DelayKapcsolodik_2" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem9_.DelayZarva_0" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem9_.DelayKeepAliveSendTimeout_1" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem9_.DelayKeepAliveReceiveTimeout_4" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem9_.DelayKapcsolodik_3" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem9_.DelayKeepAliveSendTimeout_0" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem10.DelayKeepAliveReceiveTimeout_3" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem10.DelayKapcsolodik_2" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem10.DelayZarva_0" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem10.DelayKeepAliveSendTimeout_1" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem10.DelayKeepAliveReceiveTimeout_4" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem10.DelayKapcsolodik_3" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem10.DelayKeepAliveSendTimeout_0" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem11.DelayKeepAliveReceiveTimeout_3" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem11.DelayKapcsolodik_2" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem11.DelayZarva_0" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem11.DelayKeepAliveSendTimeout_1" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem11.DelayKeepAliveReceiveTimeout_4" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem11.DelayKapcsolodik_3" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem11.DelayKeepAliveSendTimeout_0" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem12.DelayKeepAliveReceiveTimeout_3" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem12.DelayKapcsolodik_2" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem12.DelayZarva_0" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem12.DelayKeepAliveSendTimeout_1" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem12.DelayKeepAliveReceiveTimeout_4" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem12.DelayKapcsolodik_3" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem12.DelayKeepAliveSendTimeout_0" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem13.DelayKeepAliveReceiveTimeout_3" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem13.DelayKapcsolodik_2" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem13.DelayZarva_0" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem13.DelayKeepAliveSendTimeout_1" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem13.DelayKeepAliveReceiveTimeout_4" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem13.DelayKapcsolodik_3" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem13.DelayKeepAliveSendTimeout_0" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem14.DelayKeepAliveReceiveTimeout_3" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem14.DelayKapcsolodik_2" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem14.DelayZarva_0" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem14.DelayKeepAliveSendTimeout_1" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem14.DelayKeepAliveReceiveTimeout_4" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem14.DelayKapcsolodik_3" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem14.DelayKeepAliveSendTimeout_0" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem15.DelayKeepAliveReceiveTimeout_3" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem15.DelayKapcsolodik_2" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem15.DelayZarva_0" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem15.DelayKeepAliveSendTimeout_1" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem15.DelayKeepAliveReceiveTimeout_4" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem15.DelayKapcsolodik_3" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem15.DelayKeepAliveSendTimeout_0" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem16.DelayKeepAliveReceiveTimeout_3" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem16.DelayKapcsolodik_2" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem16.DelayZarva_0" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem16.DelayKeepAliveSendTimeout_1" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem16.DelayKeepAliveReceiveTimeout_4" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem16.DelayKapcsolodik_3" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem16.DelayKeepAliveSendTimeout_0" : DelaySoftwareTimer()})
		
		
		self.components.update({ "Orion.SubSystem1_.OrionSystem.System.masterSlaveChannel" : SwitchStateMachine_Interface_For_Orion()})
		self.components.update({ "Orion.SubSystem1_.OrionSystem.System.slaveMasterChannel" : SwitchStateMachine_Interface_For_Orion()})
		self.components.update({ "Orion.SubSystem2_.OrionSystem.System.masterSlaveChannel" : SwitchStateMachine_Interface_For_Orion()})
		self.components.update({ "Orion.SubSystem2_.OrionSystem.System.slaveMasterChannel" : SwitchStateMachine_Interface_For_Orion()})
		self.components.update({ "Orion.SubSystem3_.OrionSystem.System.masterSlaveChannel" : SwitchStateMachine_Interface_For_Orion()})
		self.components.update({ "Orion.SubSystem3_.OrionSystem.System.slaveMasterChannel" : SwitchStateMachine_Interface_For_Orion()})
		self.components.update({ "Orion.SubSystem4_.OrionSystem.System.masterSlaveChannel" : SwitchStateMachine_Interface_For_Orion()})
		self.components.update({ "Orion.SubSystem4_.OrionSystem.System.slaveMasterChannel" : SwitchStateMachine_Interface_For_Orion()})
		self.components.update({ "Orion.SubSystem5_.OrionSystem.System.masterSlaveChannel" : SwitchStateMachine_Interface_For_Orion()})
		self.components.update({ "Orion.SubSystem5_.OrionSystem.System.slaveMasterChannel" : SwitchStateMachine_Interface_For_Orion()})
		self.components.update({ "Orion.SubSystem6_.OrionSystem.System.masterSlaveChannel" : SwitchStateMachine_Interface_For_Orion()})
		self.components.update({ "Orion.SubSystem6_.OrionSystem.System.slaveMasterChannel" : SwitchStateMachine_Interface_For_Orion()})
		self.components.update({ "Orion.SubSystem7_.OrionSystem.System.masterSlaveChannel" : SwitchStateMachine_Interface_For_Orion()})
		self.components.update({ "Orion.SubSystem7_.OrionSystem.System.slaveMasterChannel" : SwitchStateMachine_Interface_For_Orion()})
		self.components.update({ "Orion.SubSystem8_.OrionSystem.System.masterSlaveChannel" : SwitchStateMachine_Interface_For_Orion()})
		self.components.update({ "Orion.SubSystem8_.OrionSystem.System.slaveMasterChannel" : SwitchStateMachine_Interface_For_Orion()})
		self.components.update({ "Orion.SubSystem9_.OrionSystem.System.masterSlaveChannel" : SwitchStateMachine_Interface_For_Orion()})
		self.components.update({ "Orion.SubSystem9_.OrionSystem.System.slaveMasterChannel" : SwitchStateMachine_Interface_For_Orion()})
		self.components.update({ "Orion.SubSystem10.OrionSystem.System.masterSlaveChannel" : SwitchStateMachine_Interface_For_Orion()})
		self.components.update({ "Orion.SubSystem10.OrionSystem.System.slaveMasterChannel" : SwitchStateMachine_Interface_For_Orion()})
		self.components.update({ "Orion.SubSystem11.OrionSystem.System.masterSlaveChannel" : SwitchStateMachine_Interface_For_Orion()})
		self.components.update({ "Orion.SubSystem11.OrionSystem.System.slaveMasterChannel" : SwitchStateMachine_Interface_For_Orion()})
		self.components.update({ "Orion.SubSystem12.OrionSystem.System.masterSlaveChannel" : SwitchStateMachine_Interface_For_Orion()})
		self.components.update({ "Orion.SubSystem12.OrionSystem.System.slaveMasterChannel" : SwitchStateMachine_Interface_For_Orion()})
		self.components.update({ "Orion.SubSystem13.OrionSystem.System.masterSlaveChannel" : SwitchStateMachine_Interface_For_Orion()})
		self.components.update({ "Orion.SubSystem13.OrionSystem.System.slaveMasterChannel" : SwitchStateMachine_Interface_For_Orion()})
		self.components.update({ "Orion.SubSystem14.OrionSystem.System.masterSlaveChannel" : SwitchStateMachine_Interface_For_Orion()})
		self.components.update({ "Orion.SubSystem14.OrionSystem.System.slaveMasterChannel" : SwitchStateMachine_Interface_For_Orion()})
		self.components.update({ "Orion.SubSystem15.OrionSystem.System.masterSlaveChannel" : SwitchStateMachine_Interface_For_Orion()})
		self.components.update({ "Orion.SubSystem15.OrionSystem.System.slaveMasterChannel" : SwitchStateMachine_Interface_For_Orion()})
		self.components.update({ "Orion.SubSystem16.OrionSystem.System.masterSlaveChannel" : SwitchStateMachine_Interface_For_Orion()})
		self.components.update({ "Orion.SubSystem16.OrionSystem.System.slaveMasterChannel" : SwitchStateMachine_Interface_For_Orion()})
		
		
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
				
		self.components["Orion.SubSystem5_.DelayKeepAliveReceiveTimeout_3"].configure(
				name  = "Orion.SubSystem5_.DelayKeepAliveReceiveTimeout_3",
				inport=self.detmodel.getOrion().getSubSystem5_().getOrionSystem().getTimoeutKeepAliveReceiveTimeout_3_req(),
				calls = {'TimoeutKeepAliveReceiveTimeout_3' : [self.detmodel.getOrion().getSubSystem5_().getOrionSystem().getTimoeutKeepAliveReceiveTimeout_3(), ]},
				rules = {'TimoeutKeepAliveReceiveTimeout_3' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_328")}},
				simulator=self)
				
		self.components["Orion.SubSystem5_.DelayKapcsolodik_2"].configure(
				name  = "Orion.SubSystem5_.DelayKapcsolodik_2",
				inport=self.detmodel.getOrion().getSubSystem5_().getOrionSystem().getTimeoutKapcsolodik_2_req(),
				calls = {'TimeoutKapcsolodik_2' : [self.detmodel.getOrion().getSubSystem5_().getOrionSystem().getTimeoutKapcsolodik_2(), ]},
				rules = {'TimeoutKapcsolodik_2' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_229")}},
				simulator=self)
				
		self.components["Orion.SubSystem5_.DelayZarva_0"].configure(
				name  = "Orion.SubSystem5_.DelayZarva_0",
				inport=self.detmodel.getOrion().getSubSystem5_().getOrionSystem().getTimeoutZarva_0_req(),
				calls = {'TimeoutZarva_0' : [self.detmodel.getOrion().getSubSystem5_().getOrionSystem().getTimeoutZarva_0(), ]},
				rules = {'TimeoutZarva_0' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayZarva_030")}},
				simulator=self)
				
		self.components["Orion.SubSystem5_.DelayKeepAliveSendTimeout_1"].configure(
				name  = "Orion.SubSystem5_.DelayKeepAliveSendTimeout_1",
				inport=self.detmodel.getOrion().getSubSystem5_().getOrionSystem().getTimeoutKeepAliveSendTimeout_1_req(),
				calls = {'TimeoutKeepAliveSendTimeout_1' : [self.detmodel.getOrion().getSubSystem5_().getOrionSystem().getTimeoutKeepAliveSendTimeout_1(), ]},
				rules = {'TimeoutKeepAliveSendTimeout_1' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_131")}},
				simulator=self)
				
		self.components["Orion.SubSystem5_.DelayKeepAliveReceiveTimeout_4"].configure(
				name  = "Orion.SubSystem5_.DelayKeepAliveReceiveTimeout_4",
				inport=self.detmodel.getOrion().getSubSystem5_().getOrionSystem().getTimeoutKeepAliveReceiveTimeout_4_req(),
				calls = {'TimeoutKeepAliveReceiveTimeout_4' : [self.detmodel.getOrion().getSubSystem5_().getOrionSystem().getTimeoutKeepAliveReceiveTimeout_4(), ]},
				rules = {'TimeoutKeepAliveReceiveTimeout_4' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_432")}},
				simulator=self)
				
		self.components["Orion.SubSystem5_.DelayKapcsolodik_3"].configure(
				name  = "Orion.SubSystem5_.DelayKapcsolodik_3",
				inport=self.detmodel.getOrion().getSubSystem5_().getOrionSystem().getTimeoutKapcsolodik_3_req(),
				calls = {'TimeoutKapcsolodik_3' : [self.detmodel.getOrion().getSubSystem5_().getOrionSystem().getTimeoutKapcsolodik_3(), ]},
				rules = {'TimeoutKapcsolodik_3' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_333")}},
				simulator=self)
				
		self.components["Orion.SubSystem5_.DelayKeepAliveSendTimeout_0"].configure(
				name  = "Orion.SubSystem5_.DelayKeepAliveSendTimeout_0",
				inport=self.detmodel.getOrion().getSubSystem5_().getOrionSystem().getTimeoutKeepAliveSendTimeout_0_req(),
				calls = {'TimeoutKeepAliveSendTimeout_0' : [self.detmodel.getOrion().getSubSystem5_().getOrionSystem().getTimeoutKeepAliveSendTimeout_0(), ]},
				rules = {'TimeoutKeepAliveSendTimeout_0' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_034")}},
				simulator=self)
				
		self.components["Orion.SubSystem6_.DelayKeepAliveReceiveTimeout_3"].configure(
				name  = "Orion.SubSystem6_.DelayKeepAliveReceiveTimeout_3",
				inport=self.detmodel.getOrion().getSubSystem6_().getOrionSystem().getTimoeutKeepAliveReceiveTimeout_3_req(),
				calls = {'TimoeutKeepAliveReceiveTimeout_3' : [self.detmodel.getOrion().getSubSystem6_().getOrionSystem().getTimoeutKeepAliveReceiveTimeout_3(), ]},
				rules = {'TimoeutKeepAliveReceiveTimeout_3' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_335")}},
				simulator=self)
				
		self.components["Orion.SubSystem6_.DelayKapcsolodik_2"].configure(
				name  = "Orion.SubSystem6_.DelayKapcsolodik_2",
				inport=self.detmodel.getOrion().getSubSystem6_().getOrionSystem().getTimeoutKapcsolodik_2_req(),
				calls = {'TimeoutKapcsolodik_2' : [self.detmodel.getOrion().getSubSystem6_().getOrionSystem().getTimeoutKapcsolodik_2(), ]},
				rules = {'TimeoutKapcsolodik_2' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_236")}},
				simulator=self)
				
		self.components["Orion.SubSystem6_.DelayZarva_0"].configure(
				name  = "Orion.SubSystem6_.DelayZarva_0",
				inport=self.detmodel.getOrion().getSubSystem6_().getOrionSystem().getTimeoutZarva_0_req(),
				calls = {'TimeoutZarva_0' : [self.detmodel.getOrion().getSubSystem6_().getOrionSystem().getTimeoutZarva_0(), ]},
				rules = {'TimeoutZarva_0' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayZarva_037")}},
				simulator=self)
				
		self.components["Orion.SubSystem6_.DelayKeepAliveSendTimeout_1"].configure(
				name  = "Orion.SubSystem6_.DelayKeepAliveSendTimeout_1",
				inport=self.detmodel.getOrion().getSubSystem6_().getOrionSystem().getTimeoutKeepAliveSendTimeout_1_req(),
				calls = {'TimeoutKeepAliveSendTimeout_1' : [self.detmodel.getOrion().getSubSystem6_().getOrionSystem().getTimeoutKeepAliveSendTimeout_1(), ]},
				rules = {'TimeoutKeepAliveSendTimeout_1' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_138")}},
				simulator=self)
				
		self.components["Orion.SubSystem6_.DelayKeepAliveReceiveTimeout_4"].configure(
				name  = "Orion.SubSystem6_.DelayKeepAliveReceiveTimeout_4",
				inport=self.detmodel.getOrion().getSubSystem6_().getOrionSystem().getTimeoutKeepAliveReceiveTimeout_4_req(),
				calls = {'TimeoutKeepAliveReceiveTimeout_4' : [self.detmodel.getOrion().getSubSystem6_().getOrionSystem().getTimeoutKeepAliveReceiveTimeout_4(), ]},
				rules = {'TimeoutKeepAliveReceiveTimeout_4' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_439")}},
				simulator=self)
				
		self.components["Orion.SubSystem6_.DelayKapcsolodik_3"].configure(
				name  = "Orion.SubSystem6_.DelayKapcsolodik_3",
				inport=self.detmodel.getOrion().getSubSystem6_().getOrionSystem().getTimeoutKapcsolodik_3_req(),
				calls = {'TimeoutKapcsolodik_3' : [self.detmodel.getOrion().getSubSystem6_().getOrionSystem().getTimeoutKapcsolodik_3(), ]},
				rules = {'TimeoutKapcsolodik_3' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_340")}},
				simulator=self)
				
		self.components["Orion.SubSystem6_.DelayKeepAliveSendTimeout_0"].configure(
				name  = "Orion.SubSystem6_.DelayKeepAliveSendTimeout_0",
				inport=self.detmodel.getOrion().getSubSystem6_().getOrionSystem().getTimeoutKeepAliveSendTimeout_0_req(),
				calls = {'TimeoutKeepAliveSendTimeout_0' : [self.detmodel.getOrion().getSubSystem6_().getOrionSystem().getTimeoutKeepAliveSendTimeout_0(), ]},
				rules = {'TimeoutKeepAliveSendTimeout_0' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_041")}},
				simulator=self)
				
		self.components["Orion.SubSystem7_.DelayKeepAliveReceiveTimeout_3"].configure(
				name  = "Orion.SubSystem7_.DelayKeepAliveReceiveTimeout_3",
				inport=self.detmodel.getOrion().getSubSystem7_().getOrionSystem().getTimoeutKeepAliveReceiveTimeout_3_req(),
				calls = {'TimoeutKeepAliveReceiveTimeout_3' : [self.detmodel.getOrion().getSubSystem7_().getOrionSystem().getTimoeutKeepAliveReceiveTimeout_3(), ]},
				rules = {'TimoeutKeepAliveReceiveTimeout_3' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_342")}},
				simulator=self)
				
		self.components["Orion.SubSystem7_.DelayKapcsolodik_2"].configure(
				name  = "Orion.SubSystem7_.DelayKapcsolodik_2",
				inport=self.detmodel.getOrion().getSubSystem7_().getOrionSystem().getTimeoutKapcsolodik_2_req(),
				calls = {'TimeoutKapcsolodik_2' : [self.detmodel.getOrion().getSubSystem7_().getOrionSystem().getTimeoutKapcsolodik_2(), ]},
				rules = {'TimeoutKapcsolodik_2' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_243")}},
				simulator=self)
				
		self.components["Orion.SubSystem7_.DelayZarva_0"].configure(
				name  = "Orion.SubSystem7_.DelayZarva_0",
				inport=self.detmodel.getOrion().getSubSystem7_().getOrionSystem().getTimeoutZarva_0_req(),
				calls = {'TimeoutZarva_0' : [self.detmodel.getOrion().getSubSystem7_().getOrionSystem().getTimeoutZarva_0(), ]},
				rules = {'TimeoutZarva_0' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayZarva_044")}},
				simulator=self)
				
		self.components["Orion.SubSystem7_.DelayKeepAliveSendTimeout_1"].configure(
				name  = "Orion.SubSystem7_.DelayKeepAliveSendTimeout_1",
				inport=self.detmodel.getOrion().getSubSystem7_().getOrionSystem().getTimeoutKeepAliveSendTimeout_1_req(),
				calls = {'TimeoutKeepAliveSendTimeout_1' : [self.detmodel.getOrion().getSubSystem7_().getOrionSystem().getTimeoutKeepAliveSendTimeout_1(), ]},
				rules = {'TimeoutKeepAliveSendTimeout_1' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_145")}},
				simulator=self)
				
		self.components["Orion.SubSystem7_.DelayKeepAliveReceiveTimeout_4"].configure(
				name  = "Orion.SubSystem7_.DelayKeepAliveReceiveTimeout_4",
				inport=self.detmodel.getOrion().getSubSystem7_().getOrionSystem().getTimeoutKeepAliveReceiveTimeout_4_req(),
				calls = {'TimeoutKeepAliveReceiveTimeout_4' : [self.detmodel.getOrion().getSubSystem7_().getOrionSystem().getTimeoutKeepAliveReceiveTimeout_4(), ]},
				rules = {'TimeoutKeepAliveReceiveTimeout_4' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_446")}},
				simulator=self)
				
		self.components["Orion.SubSystem7_.DelayKapcsolodik_3"].configure(
				name  = "Orion.SubSystem7_.DelayKapcsolodik_3",
				inport=self.detmodel.getOrion().getSubSystem7_().getOrionSystem().getTimeoutKapcsolodik_3_req(),
				calls = {'TimeoutKapcsolodik_3' : [self.detmodel.getOrion().getSubSystem7_().getOrionSystem().getTimeoutKapcsolodik_3(), ]},
				rules = {'TimeoutKapcsolodik_3' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_347")}},
				simulator=self)
				
		self.components["Orion.SubSystem7_.DelayKeepAliveSendTimeout_0"].configure(
				name  = "Orion.SubSystem7_.DelayKeepAliveSendTimeout_0",
				inport=self.detmodel.getOrion().getSubSystem7_().getOrionSystem().getTimeoutKeepAliveSendTimeout_0_req(),
				calls = {'TimeoutKeepAliveSendTimeout_0' : [self.detmodel.getOrion().getSubSystem7_().getOrionSystem().getTimeoutKeepAliveSendTimeout_0(), ]},
				rules = {'TimeoutKeepAliveSendTimeout_0' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_048")}},
				simulator=self)
				
		self.components["Orion.SubSystem8_.DelayKeepAliveReceiveTimeout_3"].configure(
				name  = "Orion.SubSystem8_.DelayKeepAliveReceiveTimeout_3",
				inport=self.detmodel.getOrion().getSubSystem8_().getOrionSystem().getTimoeutKeepAliveReceiveTimeout_3_req(),
				calls = {'TimoeutKeepAliveReceiveTimeout_3' : [self.detmodel.getOrion().getSubSystem8_().getOrionSystem().getTimoeutKeepAliveReceiveTimeout_3(), ]},
				rules = {'TimoeutKeepAliveReceiveTimeout_3' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_349")}},
				simulator=self)
				
		self.components["Orion.SubSystem8_.DelayKapcsolodik_2"].configure(
				name  = "Orion.SubSystem8_.DelayKapcsolodik_2",
				inport=self.detmodel.getOrion().getSubSystem8_().getOrionSystem().getTimeoutKapcsolodik_2_req(),
				calls = {'TimeoutKapcsolodik_2' : [self.detmodel.getOrion().getSubSystem8_().getOrionSystem().getTimeoutKapcsolodik_2(), ]},
				rules = {'TimeoutKapcsolodik_2' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_250")}},
				simulator=self)
				
		self.components["Orion.SubSystem8_.DelayZarva_0"].configure(
				name  = "Orion.SubSystem8_.DelayZarva_0",
				inport=self.detmodel.getOrion().getSubSystem8_().getOrionSystem().getTimeoutZarva_0_req(),
				calls = {'TimeoutZarva_0' : [self.detmodel.getOrion().getSubSystem8_().getOrionSystem().getTimeoutZarva_0(), ]},
				rules = {'TimeoutZarva_0' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayZarva_051")}},
				simulator=self)
				
		self.components["Orion.SubSystem8_.DelayKeepAliveSendTimeout_1"].configure(
				name  = "Orion.SubSystem8_.DelayKeepAliveSendTimeout_1",
				inport=self.detmodel.getOrion().getSubSystem8_().getOrionSystem().getTimeoutKeepAliveSendTimeout_1_req(),
				calls = {'TimeoutKeepAliveSendTimeout_1' : [self.detmodel.getOrion().getSubSystem8_().getOrionSystem().getTimeoutKeepAliveSendTimeout_1(), ]},
				rules = {'TimeoutKeepAliveSendTimeout_1' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_152")}},
				simulator=self)
				
		self.components["Orion.SubSystem8_.DelayKeepAliveReceiveTimeout_4"].configure(
				name  = "Orion.SubSystem8_.DelayKeepAliveReceiveTimeout_4",
				inport=self.detmodel.getOrion().getSubSystem8_().getOrionSystem().getTimeoutKeepAliveReceiveTimeout_4_req(),
				calls = {'TimeoutKeepAliveReceiveTimeout_4' : [self.detmodel.getOrion().getSubSystem8_().getOrionSystem().getTimeoutKeepAliveReceiveTimeout_4(), ]},
				rules = {'TimeoutKeepAliveReceiveTimeout_4' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_453")}},
				simulator=self)
				
		self.components["Orion.SubSystem8_.DelayKapcsolodik_3"].configure(
				name  = "Orion.SubSystem8_.DelayKapcsolodik_3",
				inport=self.detmodel.getOrion().getSubSystem8_().getOrionSystem().getTimeoutKapcsolodik_3_req(),
				calls = {'TimeoutKapcsolodik_3' : [self.detmodel.getOrion().getSubSystem8_().getOrionSystem().getTimeoutKapcsolodik_3(), ]},
				rules = {'TimeoutKapcsolodik_3' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_354")}},
				simulator=self)
				
		self.components["Orion.SubSystem8_.DelayKeepAliveSendTimeout_0"].configure(
				name  = "Orion.SubSystem8_.DelayKeepAliveSendTimeout_0",
				inport=self.detmodel.getOrion().getSubSystem8_().getOrionSystem().getTimeoutKeepAliveSendTimeout_0_req(),
				calls = {'TimeoutKeepAliveSendTimeout_0' : [self.detmodel.getOrion().getSubSystem8_().getOrionSystem().getTimeoutKeepAliveSendTimeout_0(), ]},
				rules = {'TimeoutKeepAliveSendTimeout_0' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_055")}},
				simulator=self)
				
		self.components["Orion.SubSystem9_.DelayKeepAliveReceiveTimeout_3"].configure(
				name  = "Orion.SubSystem9_.DelayKeepAliveReceiveTimeout_3",
				inport=self.detmodel.getOrion().getSubSystem9_().getOrionSystem().getTimoeutKeepAliveReceiveTimeout_3_req(),
				calls = {'TimoeutKeepAliveReceiveTimeout_3' : [self.detmodel.getOrion().getSubSystem9_().getOrionSystem().getTimoeutKeepAliveReceiveTimeout_3(), ]},
				rules = {'TimoeutKeepAliveReceiveTimeout_3' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_356")}},
				simulator=self)
				
		self.components["Orion.SubSystem9_.DelayKapcsolodik_2"].configure(
				name  = "Orion.SubSystem9_.DelayKapcsolodik_2",
				inport=self.detmodel.getOrion().getSubSystem9_().getOrionSystem().getTimeoutKapcsolodik_2_req(),
				calls = {'TimeoutKapcsolodik_2' : [self.detmodel.getOrion().getSubSystem9_().getOrionSystem().getTimeoutKapcsolodik_2(), ]},
				rules = {'TimeoutKapcsolodik_2' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_257")}},
				simulator=self)
				
		self.components["Orion.SubSystem9_.DelayZarva_0"].configure(
				name  = "Orion.SubSystem9_.DelayZarva_0",
				inport=self.detmodel.getOrion().getSubSystem9_().getOrionSystem().getTimeoutZarva_0_req(),
				calls = {'TimeoutZarva_0' : [self.detmodel.getOrion().getSubSystem9_().getOrionSystem().getTimeoutZarva_0(), ]},
				rules = {'TimeoutZarva_0' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayZarva_058")}},
				simulator=self)
				
		self.components["Orion.SubSystem9_.DelayKeepAliveSendTimeout_1"].configure(
				name  = "Orion.SubSystem9_.DelayKeepAliveSendTimeout_1",
				inport=self.detmodel.getOrion().getSubSystem9_().getOrionSystem().getTimeoutKeepAliveSendTimeout_1_req(),
				calls = {'TimeoutKeepAliveSendTimeout_1' : [self.detmodel.getOrion().getSubSystem9_().getOrionSystem().getTimeoutKeepAliveSendTimeout_1(), ]},
				rules = {'TimeoutKeepAliveSendTimeout_1' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_159")}},
				simulator=self)
				
		self.components["Orion.SubSystem9_.DelayKeepAliveReceiveTimeout_4"].configure(
				name  = "Orion.SubSystem9_.DelayKeepAliveReceiveTimeout_4",
				inport=self.detmodel.getOrion().getSubSystem9_().getOrionSystem().getTimeoutKeepAliveReceiveTimeout_4_req(),
				calls = {'TimeoutKeepAliveReceiveTimeout_4' : [self.detmodel.getOrion().getSubSystem9_().getOrionSystem().getTimeoutKeepAliveReceiveTimeout_4(), ]},
				rules = {'TimeoutKeepAliveReceiveTimeout_4' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_460")}},
				simulator=self)
				
		self.components["Orion.SubSystem9_.DelayKapcsolodik_3"].configure(
				name  = "Orion.SubSystem9_.DelayKapcsolodik_3",
				inport=self.detmodel.getOrion().getSubSystem9_().getOrionSystem().getTimeoutKapcsolodik_3_req(),
				calls = {'TimeoutKapcsolodik_3' : [self.detmodel.getOrion().getSubSystem9_().getOrionSystem().getTimeoutKapcsolodik_3(), ]},
				rules = {'TimeoutKapcsolodik_3' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_361")}},
				simulator=self)
				
		self.components["Orion.SubSystem9_.DelayKeepAliveSendTimeout_0"].configure(
				name  = "Orion.SubSystem9_.DelayKeepAliveSendTimeout_0",
				inport=self.detmodel.getOrion().getSubSystem9_().getOrionSystem().getTimeoutKeepAliveSendTimeout_0_req(),
				calls = {'TimeoutKeepAliveSendTimeout_0' : [self.detmodel.getOrion().getSubSystem9_().getOrionSystem().getTimeoutKeepAliveSendTimeout_0(), ]},
				rules = {'TimeoutKeepAliveSendTimeout_0' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_062")}},
				simulator=self)
				
		self.components["Orion.SubSystem10.DelayKeepAliveReceiveTimeout_3"].configure(
				name  = "Orion.SubSystem10.DelayKeepAliveReceiveTimeout_3",
				inport=self.detmodel.getOrion().getSubSystem10().getOrionSystem().getTimoeutKeepAliveReceiveTimeout_3_req(),
				calls = {'TimoeutKeepAliveReceiveTimeout_3' : [self.detmodel.getOrion().getSubSystem10().getOrionSystem().getTimoeutKeepAliveReceiveTimeout_3(), ]},
				rules = {'TimoeutKeepAliveReceiveTimeout_3' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_363")}},
				simulator=self)
				
		self.components["Orion.SubSystem10.DelayKapcsolodik_2"].configure(
				name  = "Orion.SubSystem10.DelayKapcsolodik_2",
				inport=self.detmodel.getOrion().getSubSystem10().getOrionSystem().getTimeoutKapcsolodik_2_req(),
				calls = {'TimeoutKapcsolodik_2' : [self.detmodel.getOrion().getSubSystem10().getOrionSystem().getTimeoutKapcsolodik_2(), ]},
				rules = {'TimeoutKapcsolodik_2' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_264")}},
				simulator=self)
				
		self.components["Orion.SubSystem10.DelayZarva_0"].configure(
				name  = "Orion.SubSystem10.DelayZarva_0",
				inport=self.detmodel.getOrion().getSubSystem10().getOrionSystem().getTimeoutZarva_0_req(),
				calls = {'TimeoutZarva_0' : [self.detmodel.getOrion().getSubSystem10().getOrionSystem().getTimeoutZarva_0(), ]},
				rules = {'TimeoutZarva_0' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayZarva_065")}},
				simulator=self)
				
		self.components["Orion.SubSystem10.DelayKeepAliveSendTimeout_1"].configure(
				name  = "Orion.SubSystem10.DelayKeepAliveSendTimeout_1",
				inport=self.detmodel.getOrion().getSubSystem10().getOrionSystem().getTimeoutKeepAliveSendTimeout_1_req(),
				calls = {'TimeoutKeepAliveSendTimeout_1' : [self.detmodel.getOrion().getSubSystem10().getOrionSystem().getTimeoutKeepAliveSendTimeout_1(), ]},
				rules = {'TimeoutKeepAliveSendTimeout_1' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_166")}},
				simulator=self)
				
		self.components["Orion.SubSystem10.DelayKeepAliveReceiveTimeout_4"].configure(
				name  = "Orion.SubSystem10.DelayKeepAliveReceiveTimeout_4",
				inport=self.detmodel.getOrion().getSubSystem10().getOrionSystem().getTimeoutKeepAliveReceiveTimeout_4_req(),
				calls = {'TimeoutKeepAliveReceiveTimeout_4' : [self.detmodel.getOrion().getSubSystem10().getOrionSystem().getTimeoutKeepAliveReceiveTimeout_4(), ]},
				rules = {'TimeoutKeepAliveReceiveTimeout_4' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_467")}},
				simulator=self)
				
		self.components["Orion.SubSystem10.DelayKapcsolodik_3"].configure(
				name  = "Orion.SubSystem10.DelayKapcsolodik_3",
				inport=self.detmodel.getOrion().getSubSystem10().getOrionSystem().getTimeoutKapcsolodik_3_req(),
				calls = {'TimeoutKapcsolodik_3' : [self.detmodel.getOrion().getSubSystem10().getOrionSystem().getTimeoutKapcsolodik_3(), ]},
				rules = {'TimeoutKapcsolodik_3' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_368")}},
				simulator=self)
				
		self.components["Orion.SubSystem10.DelayKeepAliveSendTimeout_0"].configure(
				name  = "Orion.SubSystem10.DelayKeepAliveSendTimeout_0",
				inport=self.detmodel.getOrion().getSubSystem10().getOrionSystem().getTimeoutKeepAliveSendTimeout_0_req(),
				calls = {'TimeoutKeepAliveSendTimeout_0' : [self.detmodel.getOrion().getSubSystem10().getOrionSystem().getTimeoutKeepAliveSendTimeout_0(), ]},
				rules = {'TimeoutKeepAliveSendTimeout_0' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_069")}},
				simulator=self)
				
		self.components["Orion.SubSystem11.DelayKeepAliveReceiveTimeout_3"].configure(
				name  = "Orion.SubSystem11.DelayKeepAliveReceiveTimeout_3",
				inport=self.detmodel.getOrion().getSubSystem11().getOrionSystem().getTimoeutKeepAliveReceiveTimeout_3_req(),
				calls = {'TimoeutKeepAliveReceiveTimeout_3' : [self.detmodel.getOrion().getSubSystem11().getOrionSystem().getTimoeutKeepAliveReceiveTimeout_3(), ]},
				rules = {'TimoeutKeepAliveReceiveTimeout_3' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_370")}},
				simulator=self)
				
		self.components["Orion.SubSystem11.DelayKapcsolodik_2"].configure(
				name  = "Orion.SubSystem11.DelayKapcsolodik_2",
				inport=self.detmodel.getOrion().getSubSystem11().getOrionSystem().getTimeoutKapcsolodik_2_req(),
				calls = {'TimeoutKapcsolodik_2' : [self.detmodel.getOrion().getSubSystem11().getOrionSystem().getTimeoutKapcsolodik_2(), ]},
				rules = {'TimeoutKapcsolodik_2' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_271")}},
				simulator=self)
				
		self.components["Orion.SubSystem11.DelayZarva_0"].configure(
				name  = "Orion.SubSystem11.DelayZarva_0",
				inport=self.detmodel.getOrion().getSubSystem11().getOrionSystem().getTimeoutZarva_0_req(),
				calls = {'TimeoutZarva_0' : [self.detmodel.getOrion().getSubSystem11().getOrionSystem().getTimeoutZarva_0(), ]},
				rules = {'TimeoutZarva_0' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayZarva_072")}},
				simulator=self)
				
		self.components["Orion.SubSystem11.DelayKeepAliveSendTimeout_1"].configure(
				name  = "Orion.SubSystem11.DelayKeepAliveSendTimeout_1",
				inport=self.detmodel.getOrion().getSubSystem11().getOrionSystem().getTimeoutKeepAliveSendTimeout_1_req(),
				calls = {'TimeoutKeepAliveSendTimeout_1' : [self.detmodel.getOrion().getSubSystem11().getOrionSystem().getTimeoutKeepAliveSendTimeout_1(), ]},
				rules = {'TimeoutKeepAliveSendTimeout_1' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_173")}},
				simulator=self)
				
		self.components["Orion.SubSystem11.DelayKeepAliveReceiveTimeout_4"].configure(
				name  = "Orion.SubSystem11.DelayKeepAliveReceiveTimeout_4",
				inport=self.detmodel.getOrion().getSubSystem11().getOrionSystem().getTimeoutKeepAliveReceiveTimeout_4_req(),
				calls = {'TimeoutKeepAliveReceiveTimeout_4' : [self.detmodel.getOrion().getSubSystem11().getOrionSystem().getTimeoutKeepAliveReceiveTimeout_4(), ]},
				rules = {'TimeoutKeepAliveReceiveTimeout_4' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_474")}},
				simulator=self)
				
		self.components["Orion.SubSystem11.DelayKapcsolodik_3"].configure(
				name  = "Orion.SubSystem11.DelayKapcsolodik_3",
				inport=self.detmodel.getOrion().getSubSystem11().getOrionSystem().getTimeoutKapcsolodik_3_req(),
				calls = {'TimeoutKapcsolodik_3' : [self.detmodel.getOrion().getSubSystem11().getOrionSystem().getTimeoutKapcsolodik_3(), ]},
				rules = {'TimeoutKapcsolodik_3' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_375")}},
				simulator=self)
				
		self.components["Orion.SubSystem11.DelayKeepAliveSendTimeout_0"].configure(
				name  = "Orion.SubSystem11.DelayKeepAliveSendTimeout_0",
				inport=self.detmodel.getOrion().getSubSystem11().getOrionSystem().getTimeoutKeepAliveSendTimeout_0_req(),
				calls = {'TimeoutKeepAliveSendTimeout_0' : [self.detmodel.getOrion().getSubSystem11().getOrionSystem().getTimeoutKeepAliveSendTimeout_0(), ]},
				rules = {'TimeoutKeepAliveSendTimeout_0' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_076")}},
				simulator=self)
				
		self.components["Orion.SubSystem12.DelayKeepAliveReceiveTimeout_3"].configure(
				name  = "Orion.SubSystem12.DelayKeepAliveReceiveTimeout_3",
				inport=self.detmodel.getOrion().getSubSystem12().getOrionSystem().getTimoeutKeepAliveReceiveTimeout_3_req(),
				calls = {'TimoeutKeepAliveReceiveTimeout_3' : [self.detmodel.getOrion().getSubSystem12().getOrionSystem().getTimoeutKeepAliveReceiveTimeout_3(), ]},
				rules = {'TimoeutKeepAliveReceiveTimeout_3' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_377")}},
				simulator=self)
				
		self.components["Orion.SubSystem12.DelayKapcsolodik_2"].configure(
				name  = "Orion.SubSystem12.DelayKapcsolodik_2",
				inport=self.detmodel.getOrion().getSubSystem12().getOrionSystem().getTimeoutKapcsolodik_2_req(),
				calls = {'TimeoutKapcsolodik_2' : [self.detmodel.getOrion().getSubSystem12().getOrionSystem().getTimeoutKapcsolodik_2(), ]},
				rules = {'TimeoutKapcsolodik_2' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_278")}},
				simulator=self)
				
		self.components["Orion.SubSystem12.DelayZarva_0"].configure(
				name  = "Orion.SubSystem12.DelayZarva_0",
				inport=self.detmodel.getOrion().getSubSystem12().getOrionSystem().getTimeoutZarva_0_req(),
				calls = {'TimeoutZarva_0' : [self.detmodel.getOrion().getSubSystem12().getOrionSystem().getTimeoutZarva_0(), ]},
				rules = {'TimeoutZarva_0' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayZarva_079")}},
				simulator=self)
				
		self.components["Orion.SubSystem12.DelayKeepAliveSendTimeout_1"].configure(
				name  = "Orion.SubSystem12.DelayKeepAliveSendTimeout_1",
				inport=self.detmodel.getOrion().getSubSystem12().getOrionSystem().getTimeoutKeepAliveSendTimeout_1_req(),
				calls = {'TimeoutKeepAliveSendTimeout_1' : [self.detmodel.getOrion().getSubSystem12().getOrionSystem().getTimeoutKeepAliveSendTimeout_1(), ]},
				rules = {'TimeoutKeepAliveSendTimeout_1' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_180")}},
				simulator=self)
				
		self.components["Orion.SubSystem12.DelayKeepAliveReceiveTimeout_4"].configure(
				name  = "Orion.SubSystem12.DelayKeepAliveReceiveTimeout_4",
				inport=self.detmodel.getOrion().getSubSystem12().getOrionSystem().getTimeoutKeepAliveReceiveTimeout_4_req(),
				calls = {'TimeoutKeepAliveReceiveTimeout_4' : [self.detmodel.getOrion().getSubSystem12().getOrionSystem().getTimeoutKeepAliveReceiveTimeout_4(), ]},
				rules = {'TimeoutKeepAliveReceiveTimeout_4' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_481")}},
				simulator=self)
				
		self.components["Orion.SubSystem12.DelayKapcsolodik_3"].configure(
				name  = "Orion.SubSystem12.DelayKapcsolodik_3",
				inport=self.detmodel.getOrion().getSubSystem12().getOrionSystem().getTimeoutKapcsolodik_3_req(),
				calls = {'TimeoutKapcsolodik_3' : [self.detmodel.getOrion().getSubSystem12().getOrionSystem().getTimeoutKapcsolodik_3(), ]},
				rules = {'TimeoutKapcsolodik_3' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_382")}},
				simulator=self)
				
		self.components["Orion.SubSystem12.DelayKeepAliveSendTimeout_0"].configure(
				name  = "Orion.SubSystem12.DelayKeepAliveSendTimeout_0",
				inport=self.detmodel.getOrion().getSubSystem12().getOrionSystem().getTimeoutKeepAliveSendTimeout_0_req(),
				calls = {'TimeoutKeepAliveSendTimeout_0' : [self.detmodel.getOrion().getSubSystem12().getOrionSystem().getTimeoutKeepAliveSendTimeout_0(), ]},
				rules = {'TimeoutKeepAliveSendTimeout_0' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_083")}},
				simulator=self)
				
		self.components["Orion.SubSystem13.DelayKeepAliveReceiveTimeout_3"].configure(
				name  = "Orion.SubSystem13.DelayKeepAliveReceiveTimeout_3",
				inport=self.detmodel.getOrion().getSubSystem13().getOrionSystem().getTimoeutKeepAliveReceiveTimeout_3_req(),
				calls = {'TimoeutKeepAliveReceiveTimeout_3' : [self.detmodel.getOrion().getSubSystem13().getOrionSystem().getTimoeutKeepAliveReceiveTimeout_3(), ]},
				rules = {'TimoeutKeepAliveReceiveTimeout_3' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_384")}},
				simulator=self)
				
		self.components["Orion.SubSystem13.DelayKapcsolodik_2"].configure(
				name  = "Orion.SubSystem13.DelayKapcsolodik_2",
				inport=self.detmodel.getOrion().getSubSystem13().getOrionSystem().getTimeoutKapcsolodik_2_req(),
				calls = {'TimeoutKapcsolodik_2' : [self.detmodel.getOrion().getSubSystem13().getOrionSystem().getTimeoutKapcsolodik_2(), ]},
				rules = {'TimeoutKapcsolodik_2' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_285")}},
				simulator=self)
				
		self.components["Orion.SubSystem13.DelayZarva_0"].configure(
				name  = "Orion.SubSystem13.DelayZarva_0",
				inport=self.detmodel.getOrion().getSubSystem13().getOrionSystem().getTimeoutZarva_0_req(),
				calls = {'TimeoutZarva_0' : [self.detmodel.getOrion().getSubSystem13().getOrionSystem().getTimeoutZarva_0(), ]},
				rules = {'TimeoutZarva_0' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayZarva_086")}},
				simulator=self)
				
		self.components["Orion.SubSystem13.DelayKeepAliveSendTimeout_1"].configure(
				name  = "Orion.SubSystem13.DelayKeepAliveSendTimeout_1",
				inport=self.detmodel.getOrion().getSubSystem13().getOrionSystem().getTimeoutKeepAliveSendTimeout_1_req(),
				calls = {'TimeoutKeepAliveSendTimeout_1' : [self.detmodel.getOrion().getSubSystem13().getOrionSystem().getTimeoutKeepAliveSendTimeout_1(), ]},
				rules = {'TimeoutKeepAliveSendTimeout_1' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_187")}},
				simulator=self)
				
		self.components["Orion.SubSystem13.DelayKeepAliveReceiveTimeout_4"].configure(
				name  = "Orion.SubSystem13.DelayKeepAliveReceiveTimeout_4",
				inport=self.detmodel.getOrion().getSubSystem13().getOrionSystem().getTimeoutKeepAliveReceiveTimeout_4_req(),
				calls = {'TimeoutKeepAliveReceiveTimeout_4' : [self.detmodel.getOrion().getSubSystem13().getOrionSystem().getTimeoutKeepAliveReceiveTimeout_4(), ]},
				rules = {'TimeoutKeepAliveReceiveTimeout_4' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_488")}},
				simulator=self)
				
		self.components["Orion.SubSystem13.DelayKapcsolodik_3"].configure(
				name  = "Orion.SubSystem13.DelayKapcsolodik_3",
				inport=self.detmodel.getOrion().getSubSystem13().getOrionSystem().getTimeoutKapcsolodik_3_req(),
				calls = {'TimeoutKapcsolodik_3' : [self.detmodel.getOrion().getSubSystem13().getOrionSystem().getTimeoutKapcsolodik_3(), ]},
				rules = {'TimeoutKapcsolodik_3' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_389")}},
				simulator=self)
				
		self.components["Orion.SubSystem13.DelayKeepAliveSendTimeout_0"].configure(
				name  = "Orion.SubSystem13.DelayKeepAliveSendTimeout_0",
				inport=self.detmodel.getOrion().getSubSystem13().getOrionSystem().getTimeoutKeepAliveSendTimeout_0_req(),
				calls = {'TimeoutKeepAliveSendTimeout_0' : [self.detmodel.getOrion().getSubSystem13().getOrionSystem().getTimeoutKeepAliveSendTimeout_0(), ]},
				rules = {'TimeoutKeepAliveSendTimeout_0' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_090")}},
				simulator=self)
				
		self.components["Orion.SubSystem14.DelayKeepAliveReceiveTimeout_3"].configure(
				name  = "Orion.SubSystem14.DelayKeepAliveReceiveTimeout_3",
				inport=self.detmodel.getOrion().getSubSystem14().getOrionSystem().getTimoeutKeepAliveReceiveTimeout_3_req(),
				calls = {'TimoeutKeepAliveReceiveTimeout_3' : [self.detmodel.getOrion().getSubSystem14().getOrionSystem().getTimoeutKeepAliveReceiveTimeout_3(), ]},
				rules = {'TimoeutKeepAliveReceiveTimeout_3' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_391")}},
				simulator=self)
				
		self.components["Orion.SubSystem14.DelayKapcsolodik_2"].configure(
				name  = "Orion.SubSystem14.DelayKapcsolodik_2",
				inport=self.detmodel.getOrion().getSubSystem14().getOrionSystem().getTimeoutKapcsolodik_2_req(),
				calls = {'TimeoutKapcsolodik_2' : [self.detmodel.getOrion().getSubSystem14().getOrionSystem().getTimeoutKapcsolodik_2(), ]},
				rules = {'TimeoutKapcsolodik_2' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_292")}},
				simulator=self)
				
		self.components["Orion.SubSystem14.DelayZarva_0"].configure(
				name  = "Orion.SubSystem14.DelayZarva_0",
				inport=self.detmodel.getOrion().getSubSystem14().getOrionSystem().getTimeoutZarva_0_req(),
				calls = {'TimeoutZarva_0' : [self.detmodel.getOrion().getSubSystem14().getOrionSystem().getTimeoutZarva_0(), ]},
				rules = {'TimeoutZarva_0' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayZarva_093")}},
				simulator=self)
				
		self.components["Orion.SubSystem14.DelayKeepAliveSendTimeout_1"].configure(
				name  = "Orion.SubSystem14.DelayKeepAliveSendTimeout_1",
				inport=self.detmodel.getOrion().getSubSystem14().getOrionSystem().getTimeoutKeepAliveSendTimeout_1_req(),
				calls = {'TimeoutKeepAliveSendTimeout_1' : [self.detmodel.getOrion().getSubSystem14().getOrionSystem().getTimeoutKeepAliveSendTimeout_1(), ]},
				rules = {'TimeoutKeepAliveSendTimeout_1' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_194")}},
				simulator=self)
				
		self.components["Orion.SubSystem14.DelayKeepAliveReceiveTimeout_4"].configure(
				name  = "Orion.SubSystem14.DelayKeepAliveReceiveTimeout_4",
				inport=self.detmodel.getOrion().getSubSystem14().getOrionSystem().getTimeoutKeepAliveReceiveTimeout_4_req(),
				calls = {'TimeoutKeepAliveReceiveTimeout_4' : [self.detmodel.getOrion().getSubSystem14().getOrionSystem().getTimeoutKeepAliveReceiveTimeout_4(), ]},
				rules = {'TimeoutKeepAliveReceiveTimeout_4' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_495")}},
				simulator=self)
				
		self.components["Orion.SubSystem14.DelayKapcsolodik_3"].configure(
				name  = "Orion.SubSystem14.DelayKapcsolodik_3",
				inport=self.detmodel.getOrion().getSubSystem14().getOrionSystem().getTimeoutKapcsolodik_3_req(),
				calls = {'TimeoutKapcsolodik_3' : [self.detmodel.getOrion().getSubSystem14().getOrionSystem().getTimeoutKapcsolodik_3(), ]},
				rules = {'TimeoutKapcsolodik_3' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_396")}},
				simulator=self)
				
		self.components["Orion.SubSystem14.DelayKeepAliveSendTimeout_0"].configure(
				name  = "Orion.SubSystem14.DelayKeepAliveSendTimeout_0",
				inport=self.detmodel.getOrion().getSubSystem14().getOrionSystem().getTimeoutKeepAliveSendTimeout_0_req(),
				calls = {'TimeoutKeepAliveSendTimeout_0' : [self.detmodel.getOrion().getSubSystem14().getOrionSystem().getTimeoutKeepAliveSendTimeout_0(), ]},
				rules = {'TimeoutKeepAliveSendTimeout_0' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_097")}},
				simulator=self)
				
		self.components["Orion.SubSystem15.DelayKeepAliveReceiveTimeout_3"].configure(
				name  = "Orion.SubSystem15.DelayKeepAliveReceiveTimeout_3",
				inport=self.detmodel.getOrion().getSubSystem15().getOrionSystem().getTimoeutKeepAliveReceiveTimeout_3_req(),
				calls = {'TimoeutKeepAliveReceiveTimeout_3' : [self.detmodel.getOrion().getSubSystem15().getOrionSystem().getTimoeutKeepAliveReceiveTimeout_3(), ]},
				rules = {'TimoeutKeepAliveReceiveTimeout_3' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_398")}},
				simulator=self)
				
		self.components["Orion.SubSystem15.DelayKapcsolodik_2"].configure(
				name  = "Orion.SubSystem15.DelayKapcsolodik_2",
				inport=self.detmodel.getOrion().getSubSystem15().getOrionSystem().getTimeoutKapcsolodik_2_req(),
				calls = {'TimeoutKapcsolodik_2' : [self.detmodel.getOrion().getSubSystem15().getOrionSystem().getTimeoutKapcsolodik_2(), ]},
				rules = {'TimeoutKapcsolodik_2' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_299")}},
				simulator=self)
				
		self.components["Orion.SubSystem15.DelayZarva_0"].configure(
				name  = "Orion.SubSystem15.DelayZarva_0",
				inport=self.detmodel.getOrion().getSubSystem15().getOrionSystem().getTimeoutZarva_0_req(),
				calls = {'TimeoutZarva_0' : [self.detmodel.getOrion().getSubSystem15().getOrionSystem().getTimeoutZarva_0(), ]},
				rules = {'TimeoutZarva_0' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayZarva_0100")}},
				simulator=self)
				
		self.components["Orion.SubSystem15.DelayKeepAliveSendTimeout_1"].configure(
				name  = "Orion.SubSystem15.DelayKeepAliveSendTimeout_1",
				inport=self.detmodel.getOrion().getSubSystem15().getOrionSystem().getTimeoutKeepAliveSendTimeout_1_req(),
				calls = {'TimeoutKeepAliveSendTimeout_1' : [self.detmodel.getOrion().getSubSystem15().getOrionSystem().getTimeoutKeepAliveSendTimeout_1(), ]},
				rules = {'TimeoutKeepAliveSendTimeout_1' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_1101")}},
				simulator=self)
				
		self.components["Orion.SubSystem15.DelayKeepAliveReceiveTimeout_4"].configure(
				name  = "Orion.SubSystem15.DelayKeepAliveReceiveTimeout_4",
				inport=self.detmodel.getOrion().getSubSystem15().getOrionSystem().getTimeoutKeepAliveReceiveTimeout_4_req(),
				calls = {'TimeoutKeepAliveReceiveTimeout_4' : [self.detmodel.getOrion().getSubSystem15().getOrionSystem().getTimeoutKeepAliveReceiveTimeout_4(), ]},
				rules = {'TimeoutKeepAliveReceiveTimeout_4' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_4102")}},
				simulator=self)
				
		self.components["Orion.SubSystem15.DelayKapcsolodik_3"].configure(
				name  = "Orion.SubSystem15.DelayKapcsolodik_3",
				inport=self.detmodel.getOrion().getSubSystem15().getOrionSystem().getTimeoutKapcsolodik_3_req(),
				calls = {'TimeoutKapcsolodik_3' : [self.detmodel.getOrion().getSubSystem15().getOrionSystem().getTimeoutKapcsolodik_3(), ]},
				rules = {'TimeoutKapcsolodik_3' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_3103")}},
				simulator=self)
				
		self.components["Orion.SubSystem15.DelayKeepAliveSendTimeout_0"].configure(
				name  = "Orion.SubSystem15.DelayKeepAliveSendTimeout_0",
				inport=self.detmodel.getOrion().getSubSystem15().getOrionSystem().getTimeoutKeepAliveSendTimeout_0_req(),
				calls = {'TimeoutKeepAliveSendTimeout_0' : [self.detmodel.getOrion().getSubSystem15().getOrionSystem().getTimeoutKeepAliveSendTimeout_0(), ]},
				rules = {'TimeoutKeepAliveSendTimeout_0' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_0104")}},
				simulator=self)
				
		self.components["Orion.SubSystem16.DelayKeepAliveReceiveTimeout_3"].configure(
				name  = "Orion.SubSystem16.DelayKeepAliveReceiveTimeout_3",
				inport=self.detmodel.getOrion().getSubSystem16().getOrionSystem().getTimoeutKeepAliveReceiveTimeout_3_req(),
				calls = {'TimoeutKeepAliveReceiveTimeout_3' : [self.detmodel.getOrion().getSubSystem16().getOrionSystem().getTimoeutKeepAliveReceiveTimeout_3(), ]},
				rules = {'TimoeutKeepAliveReceiveTimeout_3' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_3105")}},
				simulator=self)
				
		self.components["Orion.SubSystem16.DelayKapcsolodik_2"].configure(
				name  = "Orion.SubSystem16.DelayKapcsolodik_2",
				inport=self.detmodel.getOrion().getSubSystem16().getOrionSystem().getTimeoutKapcsolodik_2_req(),
				calls = {'TimeoutKapcsolodik_2' : [self.detmodel.getOrion().getSubSystem16().getOrionSystem().getTimeoutKapcsolodik_2(), ]},
				rules = {'TimeoutKapcsolodik_2' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_2106")}},
				simulator=self)
				
		self.components["Orion.SubSystem16.DelayZarva_0"].configure(
				name  = "Orion.SubSystem16.DelayZarva_0",
				inport=self.detmodel.getOrion().getSubSystem16().getOrionSystem().getTimeoutZarva_0_req(),
				calls = {'TimeoutZarva_0' : [self.detmodel.getOrion().getSubSystem16().getOrionSystem().getTimeoutZarva_0(), ]},
				rules = {'TimeoutZarva_0' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayZarva_0107")}},
				simulator=self)
				
		self.components["Orion.SubSystem16.DelayKeepAliveSendTimeout_1"].configure(
				name  = "Orion.SubSystem16.DelayKeepAliveSendTimeout_1",
				inport=self.detmodel.getOrion().getSubSystem16().getOrionSystem().getTimeoutKeepAliveSendTimeout_1_req(),
				calls = {'TimeoutKeepAliveSendTimeout_1' : [self.detmodel.getOrion().getSubSystem16().getOrionSystem().getTimeoutKeepAliveSendTimeout_1(), ]},
				rules = {'TimeoutKeepAliveSendTimeout_1' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_1108")}},
				simulator=self)
				
		self.components["Orion.SubSystem16.DelayKeepAliveReceiveTimeout_4"].configure(
				name  = "Orion.SubSystem16.DelayKeepAliveReceiveTimeout_4",
				inport=self.detmodel.getOrion().getSubSystem16().getOrionSystem().getTimeoutKeepAliveReceiveTimeout_4_req(),
				calls = {'TimeoutKeepAliveReceiveTimeout_4' : [self.detmodel.getOrion().getSubSystem16().getOrionSystem().getTimeoutKeepAliveReceiveTimeout_4(), ]},
				rules = {'TimeoutKeepAliveReceiveTimeout_4' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_4109")}},
				simulator=self)
				
		self.components["Orion.SubSystem16.DelayKapcsolodik_3"].configure(
				name  = "Orion.SubSystem16.DelayKapcsolodik_3",
				inport=self.detmodel.getOrion().getSubSystem16().getOrionSystem().getTimeoutKapcsolodik_3_req(),
				calls = {'TimeoutKapcsolodik_3' : [self.detmodel.getOrion().getSubSystem16().getOrionSystem().getTimeoutKapcsolodik_3(), ]},
				rules = {'TimeoutKapcsolodik_3' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_3110")}},
				simulator=self)
				
		self.components["Orion.SubSystem16.DelayKeepAliveSendTimeout_0"].configure(
				name  = "Orion.SubSystem16.DelayKeepAliveSendTimeout_0",
				inport=self.detmodel.getOrion().getSubSystem16().getOrionSystem().getTimeoutKeepAliveSendTimeout_0_req(),
				calls = {'TimeoutKeepAliveSendTimeout_0' : [self.detmodel.getOrion().getSubSystem16().getOrionSystem().getTimeoutKeepAliveSendTimeout_0(), ]},
				rules = {'TimeoutKeepAliveSendTimeout_0' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_0111")}},
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
					name="MasterSlaveChannel112")
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
					name="SlaveMasterChannel113")
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
					name="MasterSlaveChannel114")
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
					name="SlaveMasterChannel115")
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
					name="MasterSlaveChannel116")
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
					name="SlaveMasterChannel117")
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
					name="MasterSlaveChannel118")
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
					name="SlaveMasterChannel119")
				,
				simulator=self,
				shname="slaveMasterChannel",
				compCall=self.detmodel.getOrion().getSubSystem4_().getOrionSystem().getSystem())
				
		self.components["Orion.SubSystem5_.OrionSystem.System.masterSlaveChannel"].configure(
				name  = "Orion.SubSystem5_.OrionSystem.System.masterSlaveChannel",
				inport=self.detmodel.getOrion().getSubSystem5_().getOrionSystem().getSystem().getMaster().getSend_StateMachine_Port(),
				calls={'Output' : [self.detmodel.getOrion().getSubSystem5_().getOrionSystem().getSystem().getSlave().getStateMachine_Port(), ], 'Lossport' : [None]},
				portarray=["Output", "Lossport"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						torch.tensor(0.9), 
										torch.tensor(0.1)
								])),
					name="MasterSlaveChannel120")
				,
				simulator=self,
				shname="masterSlaveChannel",
				compCall=self.detmodel.getOrion().getSubSystem5_().getOrionSystem().getSystem())
				
		self.components["Orion.SubSystem5_.OrionSystem.System.slaveMasterChannel"].configure(
				name  = "Orion.SubSystem5_.OrionSystem.System.slaveMasterChannel",
				inport=self.detmodel.getOrion().getSubSystem5_().getOrionSystem().getSystem().getSlave().getSend_StateMachine_Port(),
				calls={'Output' : [self.detmodel.getOrion().getSubSystem5_().getOrionSystem().getSystem().getMaster().getStateMachine_Port(), ], 'Lossport' : [None]},
				portarray=["Output", "Lossport"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						torch.tensor(0.9), 
										torch.tensor(0.1)
								])),
					name="SlaveMasterChannel121")
				,
				simulator=self,
				shname="slaveMasterChannel",
				compCall=self.detmodel.getOrion().getSubSystem5_().getOrionSystem().getSystem())
				
		self.components["Orion.SubSystem6_.OrionSystem.System.masterSlaveChannel"].configure(
				name  = "Orion.SubSystem6_.OrionSystem.System.masterSlaveChannel",
				inport=self.detmodel.getOrion().getSubSystem6_().getOrionSystem().getSystem().getMaster().getSend_StateMachine_Port(),
				calls={'Output' : [self.detmodel.getOrion().getSubSystem6_().getOrionSystem().getSystem().getSlave().getStateMachine_Port(), ], 'Lossport' : [None]},
				portarray=["Output", "Lossport"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						torch.tensor(0.9), 
										torch.tensor(0.1)
								])),
					name="MasterSlaveChannel122")
				,
				simulator=self,
				shname="masterSlaveChannel",
				compCall=self.detmodel.getOrion().getSubSystem6_().getOrionSystem().getSystem())
				
		self.components["Orion.SubSystem6_.OrionSystem.System.slaveMasterChannel"].configure(
				name  = "Orion.SubSystem6_.OrionSystem.System.slaveMasterChannel",
				inport=self.detmodel.getOrion().getSubSystem6_().getOrionSystem().getSystem().getSlave().getSend_StateMachine_Port(),
				calls={'Output' : [self.detmodel.getOrion().getSubSystem6_().getOrionSystem().getSystem().getMaster().getStateMachine_Port(), ], 'Lossport' : [None]},
				portarray=["Output", "Lossport"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						torch.tensor(0.9), 
										torch.tensor(0.1)
								])),
					name="SlaveMasterChannel123")
				,
				simulator=self,
				shname="slaveMasterChannel",
				compCall=self.detmodel.getOrion().getSubSystem6_().getOrionSystem().getSystem())
				
		self.components["Orion.SubSystem7_.OrionSystem.System.masterSlaveChannel"].configure(
				name  = "Orion.SubSystem7_.OrionSystem.System.masterSlaveChannel",
				inport=self.detmodel.getOrion().getSubSystem7_().getOrionSystem().getSystem().getMaster().getSend_StateMachine_Port(),
				calls={'Output' : [self.detmodel.getOrion().getSubSystem7_().getOrionSystem().getSystem().getSlave().getStateMachine_Port(), ], 'Lossport' : [None]},
				portarray=["Output", "Lossport"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						torch.tensor(0.9), 
										torch.tensor(0.1)
								])),
					name="MasterSlaveChannel124")
				,
				simulator=self,
				shname="masterSlaveChannel",
				compCall=self.detmodel.getOrion().getSubSystem7_().getOrionSystem().getSystem())
				
		self.components["Orion.SubSystem7_.OrionSystem.System.slaveMasterChannel"].configure(
				name  = "Orion.SubSystem7_.OrionSystem.System.slaveMasterChannel",
				inport=self.detmodel.getOrion().getSubSystem7_().getOrionSystem().getSystem().getSlave().getSend_StateMachine_Port(),
				calls={'Output' : [self.detmodel.getOrion().getSubSystem7_().getOrionSystem().getSystem().getMaster().getStateMachine_Port(), ], 'Lossport' : [None]},
				portarray=["Output", "Lossport"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						torch.tensor(0.9), 
										torch.tensor(0.1)
								])),
					name="SlaveMasterChannel125")
				,
				simulator=self,
				shname="slaveMasterChannel",
				compCall=self.detmodel.getOrion().getSubSystem7_().getOrionSystem().getSystem())
				
		self.components["Orion.SubSystem8_.OrionSystem.System.masterSlaveChannel"].configure(
				name  = "Orion.SubSystem8_.OrionSystem.System.masterSlaveChannel",
				inport=self.detmodel.getOrion().getSubSystem8_().getOrionSystem().getSystem().getMaster().getSend_StateMachine_Port(),
				calls={'Output' : [self.detmodel.getOrion().getSubSystem8_().getOrionSystem().getSystem().getSlave().getStateMachine_Port(), ], 'Lossport' : [None]},
				portarray=["Output", "Lossport"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						torch.tensor(0.9), 
										torch.tensor(0.1)
								])),
					name="MasterSlaveChannel126")
				,
				simulator=self,
				shname="masterSlaveChannel",
				compCall=self.detmodel.getOrion().getSubSystem8_().getOrionSystem().getSystem())
				
		self.components["Orion.SubSystem8_.OrionSystem.System.slaveMasterChannel"].configure(
				name  = "Orion.SubSystem8_.OrionSystem.System.slaveMasterChannel",
				inport=self.detmodel.getOrion().getSubSystem8_().getOrionSystem().getSystem().getSlave().getSend_StateMachine_Port(),
				calls={'Output' : [self.detmodel.getOrion().getSubSystem8_().getOrionSystem().getSystem().getMaster().getStateMachine_Port(), ], 'Lossport' : [None]},
				portarray=["Output", "Lossport"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						torch.tensor(0.9), 
										torch.tensor(0.1)
								])),
					name="SlaveMasterChannel127")
				,
				simulator=self,
				shname="slaveMasterChannel",
				compCall=self.detmodel.getOrion().getSubSystem8_().getOrionSystem().getSystem())
				
		self.components["Orion.SubSystem9_.OrionSystem.System.masterSlaveChannel"].configure(
				name  = "Orion.SubSystem9_.OrionSystem.System.masterSlaveChannel",
				inport=self.detmodel.getOrion().getSubSystem9_().getOrionSystem().getSystem().getMaster().getSend_StateMachine_Port(),
				calls={'Output' : [self.detmodel.getOrion().getSubSystem9_().getOrionSystem().getSystem().getSlave().getStateMachine_Port(), ], 'Lossport' : [None]},
				portarray=["Output", "Lossport"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						torch.tensor(0.9), 
										torch.tensor(0.1)
								])),
					name="MasterSlaveChannel128")
				,
				simulator=self,
				shname="masterSlaveChannel",
				compCall=self.detmodel.getOrion().getSubSystem9_().getOrionSystem().getSystem())
				
		self.components["Orion.SubSystem9_.OrionSystem.System.slaveMasterChannel"].configure(
				name  = "Orion.SubSystem9_.OrionSystem.System.slaveMasterChannel",
				inport=self.detmodel.getOrion().getSubSystem9_().getOrionSystem().getSystem().getSlave().getSend_StateMachine_Port(),
				calls={'Output' : [self.detmodel.getOrion().getSubSystem9_().getOrionSystem().getSystem().getMaster().getStateMachine_Port(), ], 'Lossport' : [None]},
				portarray=["Output", "Lossport"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						torch.tensor(0.9), 
										torch.tensor(0.1)
								])),
					name="SlaveMasterChannel129")
				,
				simulator=self,
				shname="slaveMasterChannel",
				compCall=self.detmodel.getOrion().getSubSystem9_().getOrionSystem().getSystem())
				
		self.components["Orion.SubSystem10.OrionSystem.System.masterSlaveChannel"].configure(
				name  = "Orion.SubSystem10.OrionSystem.System.masterSlaveChannel",
				inport=self.detmodel.getOrion().getSubSystem10().getOrionSystem().getSystem().getMaster().getSend_StateMachine_Port(),
				calls={'Output' : [self.detmodel.getOrion().getSubSystem10().getOrionSystem().getSystem().getSlave().getStateMachine_Port(), ], 'Lossport' : [None]},
				portarray=["Output", "Lossport"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						torch.tensor(0.9), 
										torch.tensor(0.1)
								])),
					name="MasterSlaveChannel130")
				,
				simulator=self,
				shname="masterSlaveChannel",
				compCall=self.detmodel.getOrion().getSubSystem10().getOrionSystem().getSystem())
				
		self.components["Orion.SubSystem10.OrionSystem.System.slaveMasterChannel"].configure(
				name  = "Orion.SubSystem10.OrionSystem.System.slaveMasterChannel",
				inport=self.detmodel.getOrion().getSubSystem10().getOrionSystem().getSystem().getSlave().getSend_StateMachine_Port(),
				calls={'Output' : [self.detmodel.getOrion().getSubSystem10().getOrionSystem().getSystem().getMaster().getStateMachine_Port(), ], 'Lossport' : [None]},
				portarray=["Output", "Lossport"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						torch.tensor(0.9), 
										torch.tensor(0.1)
								])),
					name="SlaveMasterChannel131")
				,
				simulator=self,
				shname="slaveMasterChannel",
				compCall=self.detmodel.getOrion().getSubSystem10().getOrionSystem().getSystem())
				
		self.components["Orion.SubSystem11.OrionSystem.System.masterSlaveChannel"].configure(
				name  = "Orion.SubSystem11.OrionSystem.System.masterSlaveChannel",
				inport=self.detmodel.getOrion().getSubSystem11().getOrionSystem().getSystem().getMaster().getSend_StateMachine_Port(),
				calls={'Output' : [self.detmodel.getOrion().getSubSystem11().getOrionSystem().getSystem().getSlave().getStateMachine_Port(), ], 'Lossport' : [None]},
				portarray=["Output", "Lossport"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						torch.tensor(0.9), 
										torch.tensor(0.1)
								])),
					name="MasterSlaveChannel132")
				,
				simulator=self,
				shname="masterSlaveChannel",
				compCall=self.detmodel.getOrion().getSubSystem11().getOrionSystem().getSystem())
				
		self.components["Orion.SubSystem11.OrionSystem.System.slaveMasterChannel"].configure(
				name  = "Orion.SubSystem11.OrionSystem.System.slaveMasterChannel",
				inport=self.detmodel.getOrion().getSubSystem11().getOrionSystem().getSystem().getSlave().getSend_StateMachine_Port(),
				calls={'Output' : [self.detmodel.getOrion().getSubSystem11().getOrionSystem().getSystem().getMaster().getStateMachine_Port(), ], 'Lossport' : [None]},
				portarray=["Output", "Lossport"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						torch.tensor(0.9), 
										torch.tensor(0.1)
								])),
					name="SlaveMasterChannel133")
				,
				simulator=self,
				shname="slaveMasterChannel",
				compCall=self.detmodel.getOrion().getSubSystem11().getOrionSystem().getSystem())
				
		self.components["Orion.SubSystem12.OrionSystem.System.masterSlaveChannel"].configure(
				name  = "Orion.SubSystem12.OrionSystem.System.masterSlaveChannel",
				inport=self.detmodel.getOrion().getSubSystem12().getOrionSystem().getSystem().getMaster().getSend_StateMachine_Port(),
				calls={'Output' : [self.detmodel.getOrion().getSubSystem12().getOrionSystem().getSystem().getSlave().getStateMachine_Port(), ], 'Lossport' : [None]},
				portarray=["Output", "Lossport"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						torch.tensor(0.9), 
										torch.tensor(0.1)
								])),
					name="MasterSlaveChannel134")
				,
				simulator=self,
				shname="masterSlaveChannel",
				compCall=self.detmodel.getOrion().getSubSystem12().getOrionSystem().getSystem())
				
		self.components["Orion.SubSystem12.OrionSystem.System.slaveMasterChannel"].configure(
				name  = "Orion.SubSystem12.OrionSystem.System.slaveMasterChannel",
				inport=self.detmodel.getOrion().getSubSystem12().getOrionSystem().getSystem().getSlave().getSend_StateMachine_Port(),
				calls={'Output' : [self.detmodel.getOrion().getSubSystem12().getOrionSystem().getSystem().getMaster().getStateMachine_Port(), ], 'Lossport' : [None]},
				portarray=["Output", "Lossport"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						torch.tensor(0.9), 
										torch.tensor(0.1)
								])),
					name="SlaveMasterChannel135")
				,
				simulator=self,
				shname="slaveMasterChannel",
				compCall=self.detmodel.getOrion().getSubSystem12().getOrionSystem().getSystem())
				
		self.components["Orion.SubSystem13.OrionSystem.System.masterSlaveChannel"].configure(
				name  = "Orion.SubSystem13.OrionSystem.System.masterSlaveChannel",
				inport=self.detmodel.getOrion().getSubSystem13().getOrionSystem().getSystem().getMaster().getSend_StateMachine_Port(),
				calls={'Output' : [self.detmodel.getOrion().getSubSystem13().getOrionSystem().getSystem().getSlave().getStateMachine_Port(), ], 'Lossport' : [None]},
				portarray=["Output", "Lossport"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						torch.tensor(0.9), 
										torch.tensor(0.1)
								])),
					name="MasterSlaveChannel136")
				,
				simulator=self,
				shname="masterSlaveChannel",
				compCall=self.detmodel.getOrion().getSubSystem13().getOrionSystem().getSystem())
				
		self.components["Orion.SubSystem13.OrionSystem.System.slaveMasterChannel"].configure(
				name  = "Orion.SubSystem13.OrionSystem.System.slaveMasterChannel",
				inport=self.detmodel.getOrion().getSubSystem13().getOrionSystem().getSystem().getSlave().getSend_StateMachine_Port(),
				calls={'Output' : [self.detmodel.getOrion().getSubSystem13().getOrionSystem().getSystem().getMaster().getStateMachine_Port(), ], 'Lossport' : [None]},
				portarray=["Output", "Lossport"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						torch.tensor(0.9), 
										torch.tensor(0.1)
								])),
					name="SlaveMasterChannel137")
				,
				simulator=self,
				shname="slaveMasterChannel",
				compCall=self.detmodel.getOrion().getSubSystem13().getOrionSystem().getSystem())
				
		self.components["Orion.SubSystem14.OrionSystem.System.masterSlaveChannel"].configure(
				name  = "Orion.SubSystem14.OrionSystem.System.masterSlaveChannel",
				inport=self.detmodel.getOrion().getSubSystem14().getOrionSystem().getSystem().getMaster().getSend_StateMachine_Port(),
				calls={'Output' : [self.detmodel.getOrion().getSubSystem14().getOrionSystem().getSystem().getSlave().getStateMachine_Port(), ], 'Lossport' : [None]},
				portarray=["Output", "Lossport"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						torch.tensor(0.9), 
										torch.tensor(0.1)
								])),
					name="MasterSlaveChannel138")
				,
				simulator=self,
				shname="masterSlaveChannel",
				compCall=self.detmodel.getOrion().getSubSystem14().getOrionSystem().getSystem())
				
		self.components["Orion.SubSystem14.OrionSystem.System.slaveMasterChannel"].configure(
				name  = "Orion.SubSystem14.OrionSystem.System.slaveMasterChannel",
				inport=self.detmodel.getOrion().getSubSystem14().getOrionSystem().getSystem().getSlave().getSend_StateMachine_Port(),
				calls={'Output' : [self.detmodel.getOrion().getSubSystem14().getOrionSystem().getSystem().getMaster().getStateMachine_Port(), ], 'Lossport' : [None]},
				portarray=["Output", "Lossport"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						torch.tensor(0.9), 
										torch.tensor(0.1)
								])),
					name="SlaveMasterChannel139")
				,
				simulator=self,
				shname="slaveMasterChannel",
				compCall=self.detmodel.getOrion().getSubSystem14().getOrionSystem().getSystem())
				
		self.components["Orion.SubSystem15.OrionSystem.System.masterSlaveChannel"].configure(
				name  = "Orion.SubSystem15.OrionSystem.System.masterSlaveChannel",
				inport=self.detmodel.getOrion().getSubSystem15().getOrionSystem().getSystem().getMaster().getSend_StateMachine_Port(),
				calls={'Output' : [self.detmodel.getOrion().getSubSystem15().getOrionSystem().getSystem().getSlave().getStateMachine_Port(), ], 'Lossport' : [None]},
				portarray=["Output", "Lossport"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						torch.tensor(0.9), 
										torch.tensor(0.1)
								])),
					name="MasterSlaveChannel140")
				,
				simulator=self,
				shname="masterSlaveChannel",
				compCall=self.detmodel.getOrion().getSubSystem15().getOrionSystem().getSystem())
				
		self.components["Orion.SubSystem15.OrionSystem.System.slaveMasterChannel"].configure(
				name  = "Orion.SubSystem15.OrionSystem.System.slaveMasterChannel",
				inport=self.detmodel.getOrion().getSubSystem15().getOrionSystem().getSystem().getSlave().getSend_StateMachine_Port(),
				calls={'Output' : [self.detmodel.getOrion().getSubSystem15().getOrionSystem().getSystem().getMaster().getStateMachine_Port(), ], 'Lossport' : [None]},
				portarray=["Output", "Lossport"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						torch.tensor(0.9), 
										torch.tensor(0.1)
								])),
					name="SlaveMasterChannel141")
				,
				simulator=self,
				shname="slaveMasterChannel",
				compCall=self.detmodel.getOrion().getSubSystem15().getOrionSystem().getSystem())
				
		self.components["Orion.SubSystem16.OrionSystem.System.masterSlaveChannel"].configure(
				name  = "Orion.SubSystem16.OrionSystem.System.masterSlaveChannel",
				inport=self.detmodel.getOrion().getSubSystem16().getOrionSystem().getSystem().getMaster().getSend_StateMachine_Port(),
				calls={'Output' : [self.detmodel.getOrion().getSubSystem16().getOrionSystem().getSystem().getSlave().getStateMachine_Port(), ], 'Lossport' : [None]},
				portarray=["Output", "Lossport"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						torch.tensor(0.9), 
										torch.tensor(0.1)
								])),
					name="MasterSlaveChannel142")
				,
				simulator=self,
				shname="masterSlaveChannel",
				compCall=self.detmodel.getOrion().getSubSystem16().getOrionSystem().getSystem())
				
		self.components["Orion.SubSystem16.OrionSystem.System.slaveMasterChannel"].configure(
				name  = "Orion.SubSystem16.OrionSystem.System.slaveMasterChannel",
				inport=self.detmodel.getOrion().getSubSystem16().getOrionSystem().getSystem().getSlave().getSend_StateMachine_Port(),
				calls={'Output' : [self.detmodel.getOrion().getSubSystem16().getOrionSystem().getSystem().getMaster().getStateMachine_Port(), ], 'Lossport' : [None]},
				portarray=["Output", "Lossport"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						torch.tensor(0.9), 
										torch.tensor(0.1)
								])),
					name="SlaveMasterChannel143")
				,
				simulator=self,
				shname="slaveMasterChannel",
				compCall=self.detmodel.getOrion().getSubSystem16().getOrionSystem().getSystem())
				
		
		
		

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
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_328")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_229")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayZarva_030")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_131")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_432")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_333")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_034")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_335")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_236")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayZarva_037")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_138")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_439")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_340")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_041")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_342")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_243")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayZarva_044")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_145")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_446")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_347")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_048")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_349")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_250")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayZarva_051")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_152")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_453")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_354")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_055")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_356")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_257")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayZarva_058")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_159")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_460")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_361")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_062")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_363")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_264")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayZarva_065")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_166")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_467")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_368")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_069")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_370")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_271")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayZarva_072")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_173")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_474")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_375")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_076")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_377")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_278")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayZarva_079")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_180")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_481")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_382")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_083")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_384")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_285")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayZarva_086")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_187")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_488")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_389")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_090")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_391")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_292")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayZarva_093")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_194")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_495")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_396")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_097")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_398")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_299")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayZarva_0100")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_1101")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_4102")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_3103")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_0104")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_3105")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_2106")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayZarva_0107")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_1108")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_4109")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_3110")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_0111")]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="MasterSlaveChannel112")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="SlaveMasterChannel113")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="MasterSlaveChannel114")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="SlaveMasterChannel115")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="MasterSlaveChannel116")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="SlaveMasterChannel117")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="MasterSlaveChannel118")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="SlaveMasterChannel119")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="MasterSlaveChannel120")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="SlaveMasterChannel121")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="MasterSlaveChannel122")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="SlaveMasterChannel123")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="MasterSlaveChannel124")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="SlaveMasterChannel125")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="MasterSlaveChannel126")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="SlaveMasterChannel127")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="MasterSlaveChannel128")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="SlaveMasterChannel129")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="MasterSlaveChannel130")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="SlaveMasterChannel131")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="MasterSlaveChannel132")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="SlaveMasterChannel133")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="MasterSlaveChannel134")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="SlaveMasterChannel135")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="MasterSlaveChannel136")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="SlaveMasterChannel137")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="MasterSlaveChannel138")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="SlaveMasterChannel139")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="MasterSlaveChannel140")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="SlaveMasterChannel141")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="MasterSlaveChannel142")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="SlaveMasterChannel143")
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



