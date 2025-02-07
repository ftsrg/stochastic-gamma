
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
	EntryPoint = JClass('javaenv.Orion_Performance_32EntryPoint')
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


if55 = JClass('hu.bme.mit.gamma.stochastic.casestudy.orion.interfaces.SoftwareTimerInterface$Listener$Provided')
if55_s = JClass('hu.bme.mit.gamma.stochastic.casestudy.orion.scheduling.ElementaryComponentSchedulingInterface')

@JImplements([if55,if55_s])
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


if56 = JClass('hu.bme.mit.gamma.stochastic.casestudy.orion.interfaces.StateMachine_Interface_For_OrionInterface$Listener$Provided')
if56_s = JClass('hu.bme.mit.gamma.stochastic.casestudy.orion.scheduling.ElementaryComponentSchedulingInterface')

@JImplements([if56,if56_s])
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
		
		
		self.components.update({ "Orion.SubSystem1.DelayKeepAliveReceiveTimeout_3" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem1.DelayKapcsolodik_2" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem1.DelayZarva_0" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem1.DelayKeepAliveSendTimeout_1" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem1.DelayKeepAliveReceiveTimeout_4" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem1.DelayKapcsolodik_3" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem1.DelayKeepAliveSendTimeout_0" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem2.DelayKeepAliveReceiveTimeout_3" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem2.DelayKapcsolodik_2" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem2.DelayZarva_0" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem2.DelayKeepAliveSendTimeout_1" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem2.DelayKeepAliveReceiveTimeout_4" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem2.DelayKapcsolodik_3" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem2.DelayKeepAliveSendTimeout_0" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem3.DelayKeepAliveReceiveTimeout_3" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem3.DelayKapcsolodik_2" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem3.DelayZarva_0" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem3.DelayKeepAliveSendTimeout_1" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem3.DelayKeepAliveReceiveTimeout_4" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem3.DelayKapcsolodik_3" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem3.DelayKeepAliveSendTimeout_0" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem4.DelayKeepAliveReceiveTimeout_3" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem4.DelayKapcsolodik_2" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem4.DelayZarva_0" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem4.DelayKeepAliveSendTimeout_1" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem4.DelayKeepAliveReceiveTimeout_4" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem4.DelayKapcsolodik_3" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem4.DelayKeepAliveSendTimeout_0" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem5.DelayKeepAliveReceiveTimeout_3" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem5.DelayKapcsolodik_2" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem5.DelayZarva_0" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem5.DelayKeepAliveSendTimeout_1" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem5.DelayKeepAliveReceiveTimeout_4" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem5.DelayKapcsolodik_3" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem5.DelayKeepAliveSendTimeout_0" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem6.DelayKeepAliveReceiveTimeout_3" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem6.DelayKapcsolodik_2" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem6.DelayZarva_0" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem6.DelayKeepAliveSendTimeout_1" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem6.DelayKeepAliveReceiveTimeout_4" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem6.DelayKapcsolodik_3" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem6.DelayKeepAliveSendTimeout_0" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem7.DelayKeepAliveReceiveTimeout_3" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem7.DelayKapcsolodik_2" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem7.DelayZarva_0" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem7.DelayKeepAliveSendTimeout_1" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem7.DelayKeepAliveReceiveTimeout_4" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem7.DelayKapcsolodik_3" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem7.DelayKeepAliveSendTimeout_0" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem8.DelayKeepAliveReceiveTimeout_3" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem8.DelayKapcsolodik_2" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem8.DelayZarva_0" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem8.DelayKeepAliveSendTimeout_1" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem8.DelayKeepAliveReceiveTimeout_4" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem8.DelayKapcsolodik_3" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem8.DelayKeepAliveSendTimeout_0" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem9.DelayKeepAliveReceiveTimeout_3" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem9.DelayKapcsolodik_2" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem9.DelayZarva_0" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem9.DelayKeepAliveSendTimeout_1" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem9.DelayKeepAliveReceiveTimeout_4" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem9.DelayKapcsolodik_3" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem9.DelayKeepAliveSendTimeout_0" : DelaySoftwareTimer()})
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
		self.components.update({ "Orion.SubSystem17.DelayKeepAliveReceiveTimeout_3" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem17.DelayKapcsolodik_2" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem17.DelayZarva_0" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem17.DelayKeepAliveSendTimeout_1" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem17.DelayKeepAliveReceiveTimeout_4" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem17.DelayKapcsolodik_3" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem17.DelayKeepAliveSendTimeout_0" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem18.DelayKeepAliveReceiveTimeout_3" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem18.DelayKapcsolodik_2" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem18.DelayZarva_0" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem18.DelayKeepAliveSendTimeout_1" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem18.DelayKeepAliveReceiveTimeout_4" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem18.DelayKapcsolodik_3" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem18.DelayKeepAliveSendTimeout_0" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem19.DelayKeepAliveReceiveTimeout_3" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem19.DelayKapcsolodik_2" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem19.DelayZarva_0" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem19.DelayKeepAliveSendTimeout_1" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem19.DelayKeepAliveReceiveTimeout_4" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem19.DelayKapcsolodik_3" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem19.DelayKeepAliveSendTimeout_0" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem20.DelayKeepAliveReceiveTimeout_3" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem20.DelayKapcsolodik_2" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem20.DelayZarva_0" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem20.DelayKeepAliveSendTimeout_1" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem20.DelayKeepAliveReceiveTimeout_4" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem20.DelayKapcsolodik_3" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem20.DelayKeepAliveSendTimeout_0" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem21.DelayKeepAliveReceiveTimeout_3" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem21.DelayKapcsolodik_2" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem21.DelayZarva_0" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem21.DelayKeepAliveSendTimeout_1" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem21.DelayKeepAliveReceiveTimeout_4" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem21.DelayKapcsolodik_3" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem21.DelayKeepAliveSendTimeout_0" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem22.DelayKeepAliveReceiveTimeout_3" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem22.DelayKapcsolodik_2" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem22.DelayZarva_0" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem22.DelayKeepAliveSendTimeout_1" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem22.DelayKeepAliveReceiveTimeout_4" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem22.DelayKapcsolodik_3" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem22.DelayKeepAliveSendTimeout_0" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem23.DelayKeepAliveReceiveTimeout_3" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem23.DelayKapcsolodik_2" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem23.DelayZarva_0" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem23.DelayKeepAliveSendTimeout_1" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem23.DelayKeepAliveReceiveTimeout_4" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem23.DelayKapcsolodik_3" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem23.DelayKeepAliveSendTimeout_0" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem24.DelayKeepAliveReceiveTimeout_3" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem24.DelayKapcsolodik_2" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem24.DelayZarva_0" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem24.DelayKeepAliveSendTimeout_1" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem24.DelayKeepAliveReceiveTimeout_4" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem24.DelayKapcsolodik_3" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem24.DelayKeepAliveSendTimeout_0" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem25.DelayKeepAliveReceiveTimeout_3" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem25.DelayKapcsolodik_2" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem25.DelayZarva_0" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem25.DelayKeepAliveSendTimeout_1" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem25.DelayKeepAliveReceiveTimeout_4" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem25.DelayKapcsolodik_3" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem25.DelayKeepAliveSendTimeout_0" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem26.DelayKeepAliveReceiveTimeout_3" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem26.DelayKapcsolodik_2" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem26.DelayZarva_0" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem26.DelayKeepAliveSendTimeout_1" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem26.DelayKeepAliveReceiveTimeout_4" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem26.DelayKapcsolodik_3" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem26.DelayKeepAliveSendTimeout_0" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem27.DelayKeepAliveReceiveTimeout_3" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem27.DelayKapcsolodik_2" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem27.DelayZarva_0" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem27.DelayKeepAliveSendTimeout_1" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem27.DelayKeepAliveReceiveTimeout_4" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem27.DelayKapcsolodik_3" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem27.DelayKeepAliveSendTimeout_0" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem28.DelayKeepAliveReceiveTimeout_3" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem28.DelayKapcsolodik_2" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem28.DelayZarva_0" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem28.DelayKeepAliveSendTimeout_1" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem28.DelayKeepAliveReceiveTimeout_4" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem28.DelayKapcsolodik_3" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem28.DelayKeepAliveSendTimeout_0" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem29.DelayKeepAliveReceiveTimeout_3" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem29.DelayKapcsolodik_2" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem29.DelayZarva_0" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem29.DelayKeepAliveSendTimeout_1" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem29.DelayKeepAliveReceiveTimeout_4" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem29.DelayKapcsolodik_3" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem29.DelayKeepAliveSendTimeout_0" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem30.DelayKeepAliveReceiveTimeout_3" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem30.DelayKapcsolodik_2" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem30.DelayZarva_0" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem30.DelayKeepAliveSendTimeout_1" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem30.DelayKeepAliveReceiveTimeout_4" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem30.DelayKapcsolodik_3" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem30.DelayKeepAliveSendTimeout_0" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem31.DelayKeepAliveReceiveTimeout_3" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem31.DelayKapcsolodik_2" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem31.DelayZarva_0" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem31.DelayKeepAliveSendTimeout_1" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem31.DelayKeepAliveReceiveTimeout_4" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem31.DelayKapcsolodik_3" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem31.DelayKeepAliveSendTimeout_0" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem32.DelayKeepAliveReceiveTimeout_3" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem32.DelayKapcsolodik_2" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem32.DelayZarva_0" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem32.DelayKeepAliveSendTimeout_1" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem32.DelayKeepAliveReceiveTimeout_4" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem32.DelayKapcsolodik_3" : DelaySoftwareTimer()})
		self.components.update({ "Orion.SubSystem32.DelayKeepAliveSendTimeout_0" : DelaySoftwareTimer()})
		
		
		self.components.update({ "Orion.SubSystem1.OrionSystem.System.masterSlaveChannel" : SwitchStateMachine_Interface_For_Orion()})
		self.components.update({ "Orion.SubSystem1.OrionSystem.System.slaveMasterChannel" : SwitchStateMachine_Interface_For_Orion()})
		self.components.update({ "Orion.SubSystem2.OrionSystem.System.masterSlaveChannel" : SwitchStateMachine_Interface_For_Orion()})
		self.components.update({ "Orion.SubSystem2.OrionSystem.System.slaveMasterChannel" : SwitchStateMachine_Interface_For_Orion()})
		self.components.update({ "Orion.SubSystem3.OrionSystem.System.masterSlaveChannel" : SwitchStateMachine_Interface_For_Orion()})
		self.components.update({ "Orion.SubSystem3.OrionSystem.System.slaveMasterChannel" : SwitchStateMachine_Interface_For_Orion()})
		self.components.update({ "Orion.SubSystem4.OrionSystem.System.masterSlaveChannel" : SwitchStateMachine_Interface_For_Orion()})
		self.components.update({ "Orion.SubSystem4.OrionSystem.System.slaveMasterChannel" : SwitchStateMachine_Interface_For_Orion()})
		self.components.update({ "Orion.SubSystem5.OrionSystem.System.masterSlaveChannel" : SwitchStateMachine_Interface_For_Orion()})
		self.components.update({ "Orion.SubSystem5.OrionSystem.System.slaveMasterChannel" : SwitchStateMachine_Interface_For_Orion()})
		self.components.update({ "Orion.SubSystem6.OrionSystem.System.masterSlaveChannel" : SwitchStateMachine_Interface_For_Orion()})
		self.components.update({ "Orion.SubSystem6.OrionSystem.System.slaveMasterChannel" : SwitchStateMachine_Interface_For_Orion()})
		self.components.update({ "Orion.SubSystem7.OrionSystem.System.masterSlaveChannel" : SwitchStateMachine_Interface_For_Orion()})
		self.components.update({ "Orion.SubSystem7.OrionSystem.System.slaveMasterChannel" : SwitchStateMachine_Interface_For_Orion()})
		self.components.update({ "Orion.SubSystem8.OrionSystem.System.masterSlaveChannel" : SwitchStateMachine_Interface_For_Orion()})
		self.components.update({ "Orion.SubSystem8.OrionSystem.System.slaveMasterChannel" : SwitchStateMachine_Interface_For_Orion()})
		self.components.update({ "Orion.SubSystem9.OrionSystem.System.masterSlaveChannel" : SwitchStateMachine_Interface_For_Orion()})
		self.components.update({ "Orion.SubSystem9.OrionSystem.System.slaveMasterChannel" : SwitchStateMachine_Interface_For_Orion()})
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
		self.components.update({ "Orion.SubSystem17.OrionSystem.System.masterSlaveChannel" : SwitchStateMachine_Interface_For_Orion()})
		self.components.update({ "Orion.SubSystem17.OrionSystem.System.slaveMasterChannel" : SwitchStateMachine_Interface_For_Orion()})
		self.components.update({ "Orion.SubSystem18.OrionSystem.System.masterSlaveChannel" : SwitchStateMachine_Interface_For_Orion()})
		self.components.update({ "Orion.SubSystem18.OrionSystem.System.slaveMasterChannel" : SwitchStateMachine_Interface_For_Orion()})
		self.components.update({ "Orion.SubSystem19.OrionSystem.System.masterSlaveChannel" : SwitchStateMachine_Interface_For_Orion()})
		self.components.update({ "Orion.SubSystem19.OrionSystem.System.slaveMasterChannel" : SwitchStateMachine_Interface_For_Orion()})
		self.components.update({ "Orion.SubSystem20.OrionSystem.System.masterSlaveChannel" : SwitchStateMachine_Interface_For_Orion()})
		self.components.update({ "Orion.SubSystem20.OrionSystem.System.slaveMasterChannel" : SwitchStateMachine_Interface_For_Orion()})
		self.components.update({ "Orion.SubSystem21.OrionSystem.System.masterSlaveChannel" : SwitchStateMachine_Interface_For_Orion()})
		self.components.update({ "Orion.SubSystem21.OrionSystem.System.slaveMasterChannel" : SwitchStateMachine_Interface_For_Orion()})
		self.components.update({ "Orion.SubSystem22.OrionSystem.System.masterSlaveChannel" : SwitchStateMachine_Interface_For_Orion()})
		self.components.update({ "Orion.SubSystem22.OrionSystem.System.slaveMasterChannel" : SwitchStateMachine_Interface_For_Orion()})
		self.components.update({ "Orion.SubSystem23.OrionSystem.System.masterSlaveChannel" : SwitchStateMachine_Interface_For_Orion()})
		self.components.update({ "Orion.SubSystem23.OrionSystem.System.slaveMasterChannel" : SwitchStateMachine_Interface_For_Orion()})
		self.components.update({ "Orion.SubSystem24.OrionSystem.System.masterSlaveChannel" : SwitchStateMachine_Interface_For_Orion()})
		self.components.update({ "Orion.SubSystem24.OrionSystem.System.slaveMasterChannel" : SwitchStateMachine_Interface_For_Orion()})
		self.components.update({ "Orion.SubSystem25.OrionSystem.System.masterSlaveChannel" : SwitchStateMachine_Interface_For_Orion()})
		self.components.update({ "Orion.SubSystem25.OrionSystem.System.slaveMasterChannel" : SwitchStateMachine_Interface_For_Orion()})
		self.components.update({ "Orion.SubSystem26.OrionSystem.System.masterSlaveChannel" : SwitchStateMachine_Interface_For_Orion()})
		self.components.update({ "Orion.SubSystem26.OrionSystem.System.slaveMasterChannel" : SwitchStateMachine_Interface_For_Orion()})
		self.components.update({ "Orion.SubSystem27.OrionSystem.System.masterSlaveChannel" : SwitchStateMachine_Interface_For_Orion()})
		self.components.update({ "Orion.SubSystem27.OrionSystem.System.slaveMasterChannel" : SwitchStateMachine_Interface_For_Orion()})
		self.components.update({ "Orion.SubSystem28.OrionSystem.System.masterSlaveChannel" : SwitchStateMachine_Interface_For_Orion()})
		self.components.update({ "Orion.SubSystem28.OrionSystem.System.slaveMasterChannel" : SwitchStateMachine_Interface_For_Orion()})
		self.components.update({ "Orion.SubSystem29.OrionSystem.System.masterSlaveChannel" : SwitchStateMachine_Interface_For_Orion()})
		self.components.update({ "Orion.SubSystem29.OrionSystem.System.slaveMasterChannel" : SwitchStateMachine_Interface_For_Orion()})
		self.components.update({ "Orion.SubSystem30.OrionSystem.System.masterSlaveChannel" : SwitchStateMachine_Interface_For_Orion()})
		self.components.update({ "Orion.SubSystem30.OrionSystem.System.slaveMasterChannel" : SwitchStateMachine_Interface_For_Orion()})
		self.components.update({ "Orion.SubSystem31.OrionSystem.System.masterSlaveChannel" : SwitchStateMachine_Interface_For_Orion()})
		self.components.update({ "Orion.SubSystem31.OrionSystem.System.slaveMasterChannel" : SwitchStateMachine_Interface_For_Orion()})
		self.components.update({ "Orion.SubSystem32.OrionSystem.System.masterSlaveChannel" : SwitchStateMachine_Interface_For_Orion()})
		self.components.update({ "Orion.SubSystem32.OrionSystem.System.slaveMasterChannel" : SwitchStateMachine_Interface_For_Orion()})
		
		
		# register input interfaces of elementary stochastic components
		
		
		
		self.components["Orion.SubSystem1.DelayKeepAliveReceiveTimeout_3"].configure(
				name  = "Orion.SubSystem1.DelayKeepAliveReceiveTimeout_3",
				inport=self.detmodel.getOrion().getSubSystem1().getOrionSystem().getTimoeutKeepAliveReceiveTimeout_3_req(),
				calls = {'TimoeutKeepAliveReceiveTimeout_3' : [self.detmodel.getOrion().getSubSystem1().getOrionSystem().getTimoeutKeepAliveReceiveTimeout_3(), ]},
				rules = {'TimoeutKeepAliveReceiveTimeout_3' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_30")}},
				simulator=self)
				
		self.components["Orion.SubSystem1.DelayKapcsolodik_2"].configure(
				name  = "Orion.SubSystem1.DelayKapcsolodik_2",
				inport=self.detmodel.getOrion().getSubSystem1().getOrionSystem().getTimeoutKapcsolodik_2_req(),
				calls = {'TimeoutKapcsolodik_2' : [self.detmodel.getOrion().getSubSystem1().getOrionSystem().getTimeoutKapcsolodik_2(), ]},
				rules = {'TimeoutKapcsolodik_2' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_21")}},
				simulator=self)
				
		self.components["Orion.SubSystem1.DelayZarva_0"].configure(
				name  = "Orion.SubSystem1.DelayZarva_0",
				inport=self.detmodel.getOrion().getSubSystem1().getOrionSystem().getTimeoutZarva_0_req(),
				calls = {'TimeoutZarva_0' : [self.detmodel.getOrion().getSubSystem1().getOrionSystem().getTimeoutZarva_0(), ]},
				rules = {'TimeoutZarva_0' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayZarva_02")}},
				simulator=self)
				
		self.components["Orion.SubSystem1.DelayKeepAliveSendTimeout_1"].configure(
				name  = "Orion.SubSystem1.DelayKeepAliveSendTimeout_1",
				inport=self.detmodel.getOrion().getSubSystem1().getOrionSystem().getTimeoutKeepAliveSendTimeout_1_req(),
				calls = {'TimeoutKeepAliveSendTimeout_1' : [self.detmodel.getOrion().getSubSystem1().getOrionSystem().getTimeoutKeepAliveSendTimeout_1(), ]},
				rules = {'TimeoutKeepAliveSendTimeout_1' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_13")}},
				simulator=self)
				
		self.components["Orion.SubSystem1.DelayKeepAliveReceiveTimeout_4"].configure(
				name  = "Orion.SubSystem1.DelayKeepAliveReceiveTimeout_4",
				inport=self.detmodel.getOrion().getSubSystem1().getOrionSystem().getTimeoutKeepAliveReceiveTimeout_4_req(),
				calls = {'TimeoutKeepAliveReceiveTimeout_4' : [self.detmodel.getOrion().getSubSystem1().getOrionSystem().getTimeoutKeepAliveReceiveTimeout_4(), ]},
				rules = {'TimeoutKeepAliveReceiveTimeout_4' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_44")}},
				simulator=self)
				
		self.components["Orion.SubSystem1.DelayKapcsolodik_3"].configure(
				name  = "Orion.SubSystem1.DelayKapcsolodik_3",
				inport=self.detmodel.getOrion().getSubSystem1().getOrionSystem().getTimeoutKapcsolodik_3_req(),
				calls = {'TimeoutKapcsolodik_3' : [self.detmodel.getOrion().getSubSystem1().getOrionSystem().getTimeoutKapcsolodik_3(), ]},
				rules = {'TimeoutKapcsolodik_3' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_35")}},
				simulator=self)
				
		self.components["Orion.SubSystem1.DelayKeepAliveSendTimeout_0"].configure(
				name  = "Orion.SubSystem1.DelayKeepAliveSendTimeout_0",
				inport=self.detmodel.getOrion().getSubSystem1().getOrionSystem().getTimeoutKeepAliveSendTimeout_0_req(),
				calls = {'TimeoutKeepAliveSendTimeout_0' : [self.detmodel.getOrion().getSubSystem1().getOrionSystem().getTimeoutKeepAliveSendTimeout_0(), ]},
				rules = {'TimeoutKeepAliveSendTimeout_0' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_06")}},
				simulator=self)
				
		self.components["Orion.SubSystem2.DelayKeepAliveReceiveTimeout_3"].configure(
				name  = "Orion.SubSystem2.DelayKeepAliveReceiveTimeout_3",
				inport=self.detmodel.getOrion().getSubSystem2().getOrionSystem().getTimoeutKeepAliveReceiveTimeout_3_req(),
				calls = {'TimoeutKeepAliveReceiveTimeout_3' : [self.detmodel.getOrion().getSubSystem2().getOrionSystem().getTimoeutKeepAliveReceiveTimeout_3(), ]},
				rules = {'TimoeutKeepAliveReceiveTimeout_3' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_37")}},
				simulator=self)
				
		self.components["Orion.SubSystem2.DelayKapcsolodik_2"].configure(
				name  = "Orion.SubSystem2.DelayKapcsolodik_2",
				inport=self.detmodel.getOrion().getSubSystem2().getOrionSystem().getTimeoutKapcsolodik_2_req(),
				calls = {'TimeoutKapcsolodik_2' : [self.detmodel.getOrion().getSubSystem2().getOrionSystem().getTimeoutKapcsolodik_2(), ]},
				rules = {'TimeoutKapcsolodik_2' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_28")}},
				simulator=self)
				
		self.components["Orion.SubSystem2.DelayZarva_0"].configure(
				name  = "Orion.SubSystem2.DelayZarva_0",
				inport=self.detmodel.getOrion().getSubSystem2().getOrionSystem().getTimeoutZarva_0_req(),
				calls = {'TimeoutZarva_0' : [self.detmodel.getOrion().getSubSystem2().getOrionSystem().getTimeoutZarva_0(), ]},
				rules = {'TimeoutZarva_0' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayZarva_09")}},
				simulator=self)
				
		self.components["Orion.SubSystem2.DelayKeepAliveSendTimeout_1"].configure(
				name  = "Orion.SubSystem2.DelayKeepAliveSendTimeout_1",
				inport=self.detmodel.getOrion().getSubSystem2().getOrionSystem().getTimeoutKeepAliveSendTimeout_1_req(),
				calls = {'TimeoutKeepAliveSendTimeout_1' : [self.detmodel.getOrion().getSubSystem2().getOrionSystem().getTimeoutKeepAliveSendTimeout_1(), ]},
				rules = {'TimeoutKeepAliveSendTimeout_1' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_110")}},
				simulator=self)
				
		self.components["Orion.SubSystem2.DelayKeepAliveReceiveTimeout_4"].configure(
				name  = "Orion.SubSystem2.DelayKeepAliveReceiveTimeout_4",
				inport=self.detmodel.getOrion().getSubSystem2().getOrionSystem().getTimeoutKeepAliveReceiveTimeout_4_req(),
				calls = {'TimeoutKeepAliveReceiveTimeout_4' : [self.detmodel.getOrion().getSubSystem2().getOrionSystem().getTimeoutKeepAliveReceiveTimeout_4(), ]},
				rules = {'TimeoutKeepAliveReceiveTimeout_4' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_411")}},
				simulator=self)
				
		self.components["Orion.SubSystem2.DelayKapcsolodik_3"].configure(
				name  = "Orion.SubSystem2.DelayKapcsolodik_3",
				inport=self.detmodel.getOrion().getSubSystem2().getOrionSystem().getTimeoutKapcsolodik_3_req(),
				calls = {'TimeoutKapcsolodik_3' : [self.detmodel.getOrion().getSubSystem2().getOrionSystem().getTimeoutKapcsolodik_3(), ]},
				rules = {'TimeoutKapcsolodik_3' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_312")}},
				simulator=self)
				
		self.components["Orion.SubSystem2.DelayKeepAliveSendTimeout_0"].configure(
				name  = "Orion.SubSystem2.DelayKeepAliveSendTimeout_0",
				inport=self.detmodel.getOrion().getSubSystem2().getOrionSystem().getTimeoutKeepAliveSendTimeout_0_req(),
				calls = {'TimeoutKeepAliveSendTimeout_0' : [self.detmodel.getOrion().getSubSystem2().getOrionSystem().getTimeoutKeepAliveSendTimeout_0(), ]},
				rules = {'TimeoutKeepAliveSendTimeout_0' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_013")}},
				simulator=self)
				
		self.components["Orion.SubSystem3.DelayKeepAliveReceiveTimeout_3"].configure(
				name  = "Orion.SubSystem3.DelayKeepAliveReceiveTimeout_3",
				inport=self.detmodel.getOrion().getSubSystem3().getOrionSystem().getTimoeutKeepAliveReceiveTimeout_3_req(),
				calls = {'TimoeutKeepAliveReceiveTimeout_3' : [self.detmodel.getOrion().getSubSystem3().getOrionSystem().getTimoeutKeepAliveReceiveTimeout_3(), ]},
				rules = {'TimoeutKeepAliveReceiveTimeout_3' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_314")}},
				simulator=self)
				
		self.components["Orion.SubSystem3.DelayKapcsolodik_2"].configure(
				name  = "Orion.SubSystem3.DelayKapcsolodik_2",
				inport=self.detmodel.getOrion().getSubSystem3().getOrionSystem().getTimeoutKapcsolodik_2_req(),
				calls = {'TimeoutKapcsolodik_2' : [self.detmodel.getOrion().getSubSystem3().getOrionSystem().getTimeoutKapcsolodik_2(), ]},
				rules = {'TimeoutKapcsolodik_2' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_215")}},
				simulator=self)
				
		self.components["Orion.SubSystem3.DelayZarva_0"].configure(
				name  = "Orion.SubSystem3.DelayZarva_0",
				inport=self.detmodel.getOrion().getSubSystem3().getOrionSystem().getTimeoutZarva_0_req(),
				calls = {'TimeoutZarva_0' : [self.detmodel.getOrion().getSubSystem3().getOrionSystem().getTimeoutZarva_0(), ]},
				rules = {'TimeoutZarva_0' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayZarva_016")}},
				simulator=self)
				
		self.components["Orion.SubSystem3.DelayKeepAliveSendTimeout_1"].configure(
				name  = "Orion.SubSystem3.DelayKeepAliveSendTimeout_1",
				inport=self.detmodel.getOrion().getSubSystem3().getOrionSystem().getTimeoutKeepAliveSendTimeout_1_req(),
				calls = {'TimeoutKeepAliveSendTimeout_1' : [self.detmodel.getOrion().getSubSystem3().getOrionSystem().getTimeoutKeepAliveSendTimeout_1(), ]},
				rules = {'TimeoutKeepAliveSendTimeout_1' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_117")}},
				simulator=self)
				
		self.components["Orion.SubSystem3.DelayKeepAliveReceiveTimeout_4"].configure(
				name  = "Orion.SubSystem3.DelayKeepAliveReceiveTimeout_4",
				inport=self.detmodel.getOrion().getSubSystem3().getOrionSystem().getTimeoutKeepAliveReceiveTimeout_4_req(),
				calls = {'TimeoutKeepAliveReceiveTimeout_4' : [self.detmodel.getOrion().getSubSystem3().getOrionSystem().getTimeoutKeepAliveReceiveTimeout_4(), ]},
				rules = {'TimeoutKeepAliveReceiveTimeout_4' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_418")}},
				simulator=self)
				
		self.components["Orion.SubSystem3.DelayKapcsolodik_3"].configure(
				name  = "Orion.SubSystem3.DelayKapcsolodik_3",
				inport=self.detmodel.getOrion().getSubSystem3().getOrionSystem().getTimeoutKapcsolodik_3_req(),
				calls = {'TimeoutKapcsolodik_3' : [self.detmodel.getOrion().getSubSystem3().getOrionSystem().getTimeoutKapcsolodik_3(), ]},
				rules = {'TimeoutKapcsolodik_3' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_319")}},
				simulator=self)
				
		self.components["Orion.SubSystem3.DelayKeepAliveSendTimeout_0"].configure(
				name  = "Orion.SubSystem3.DelayKeepAliveSendTimeout_0",
				inport=self.detmodel.getOrion().getSubSystem3().getOrionSystem().getTimeoutKeepAliveSendTimeout_0_req(),
				calls = {'TimeoutKeepAliveSendTimeout_0' : [self.detmodel.getOrion().getSubSystem3().getOrionSystem().getTimeoutKeepAliveSendTimeout_0(), ]},
				rules = {'TimeoutKeepAliveSendTimeout_0' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_020")}},
				simulator=self)
				
		self.components["Orion.SubSystem4.DelayKeepAliveReceiveTimeout_3"].configure(
				name  = "Orion.SubSystem4.DelayKeepAliveReceiveTimeout_3",
				inport=self.detmodel.getOrion().getSubSystem4().getOrionSystem().getTimoeutKeepAliveReceiveTimeout_3_req(),
				calls = {'TimoeutKeepAliveReceiveTimeout_3' : [self.detmodel.getOrion().getSubSystem4().getOrionSystem().getTimoeutKeepAliveReceiveTimeout_3(), ]},
				rules = {'TimoeutKeepAliveReceiveTimeout_3' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_321")}},
				simulator=self)
				
		self.components["Orion.SubSystem4.DelayKapcsolodik_2"].configure(
				name  = "Orion.SubSystem4.DelayKapcsolodik_2",
				inport=self.detmodel.getOrion().getSubSystem4().getOrionSystem().getTimeoutKapcsolodik_2_req(),
				calls = {'TimeoutKapcsolodik_2' : [self.detmodel.getOrion().getSubSystem4().getOrionSystem().getTimeoutKapcsolodik_2(), ]},
				rules = {'TimeoutKapcsolodik_2' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_222")}},
				simulator=self)
				
		self.components["Orion.SubSystem4.DelayZarva_0"].configure(
				name  = "Orion.SubSystem4.DelayZarva_0",
				inport=self.detmodel.getOrion().getSubSystem4().getOrionSystem().getTimeoutZarva_0_req(),
				calls = {'TimeoutZarva_0' : [self.detmodel.getOrion().getSubSystem4().getOrionSystem().getTimeoutZarva_0(), ]},
				rules = {'TimeoutZarva_0' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayZarva_023")}},
				simulator=self)
				
		self.components["Orion.SubSystem4.DelayKeepAliveSendTimeout_1"].configure(
				name  = "Orion.SubSystem4.DelayKeepAliveSendTimeout_1",
				inport=self.detmodel.getOrion().getSubSystem4().getOrionSystem().getTimeoutKeepAliveSendTimeout_1_req(),
				calls = {'TimeoutKeepAliveSendTimeout_1' : [self.detmodel.getOrion().getSubSystem4().getOrionSystem().getTimeoutKeepAliveSendTimeout_1(), ]},
				rules = {'TimeoutKeepAliveSendTimeout_1' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_124")}},
				simulator=self)
				
		self.components["Orion.SubSystem4.DelayKeepAliveReceiveTimeout_4"].configure(
				name  = "Orion.SubSystem4.DelayKeepAliveReceiveTimeout_4",
				inport=self.detmodel.getOrion().getSubSystem4().getOrionSystem().getTimeoutKeepAliveReceiveTimeout_4_req(),
				calls = {'TimeoutKeepAliveReceiveTimeout_4' : [self.detmodel.getOrion().getSubSystem4().getOrionSystem().getTimeoutKeepAliveReceiveTimeout_4(), ]},
				rules = {'TimeoutKeepAliveReceiveTimeout_4' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_425")}},
				simulator=self)
				
		self.components["Orion.SubSystem4.DelayKapcsolodik_3"].configure(
				name  = "Orion.SubSystem4.DelayKapcsolodik_3",
				inport=self.detmodel.getOrion().getSubSystem4().getOrionSystem().getTimeoutKapcsolodik_3_req(),
				calls = {'TimeoutKapcsolodik_3' : [self.detmodel.getOrion().getSubSystem4().getOrionSystem().getTimeoutKapcsolodik_3(), ]},
				rules = {'TimeoutKapcsolodik_3' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_326")}},
				simulator=self)
				
		self.components["Orion.SubSystem4.DelayKeepAliveSendTimeout_0"].configure(
				name  = "Orion.SubSystem4.DelayKeepAliveSendTimeout_0",
				inport=self.detmodel.getOrion().getSubSystem4().getOrionSystem().getTimeoutKeepAliveSendTimeout_0_req(),
				calls = {'TimeoutKeepAliveSendTimeout_0' : [self.detmodel.getOrion().getSubSystem4().getOrionSystem().getTimeoutKeepAliveSendTimeout_0(), ]},
				rules = {'TimeoutKeepAliveSendTimeout_0' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_027")}},
				simulator=self)
				
		self.components["Orion.SubSystem5.DelayKeepAliveReceiveTimeout_3"].configure(
				name  = "Orion.SubSystem5.DelayKeepAliveReceiveTimeout_3",
				inport=self.detmodel.getOrion().getSubSystem5().getOrionSystem().getTimoeutKeepAliveReceiveTimeout_3_req(),
				calls = {'TimoeutKeepAliveReceiveTimeout_3' : [self.detmodel.getOrion().getSubSystem5().getOrionSystem().getTimoeutKeepAliveReceiveTimeout_3(), ]},
				rules = {'TimoeutKeepAliveReceiveTimeout_3' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_328")}},
				simulator=self)
				
		self.components["Orion.SubSystem5.DelayKapcsolodik_2"].configure(
				name  = "Orion.SubSystem5.DelayKapcsolodik_2",
				inport=self.detmodel.getOrion().getSubSystem5().getOrionSystem().getTimeoutKapcsolodik_2_req(),
				calls = {'TimeoutKapcsolodik_2' : [self.detmodel.getOrion().getSubSystem5().getOrionSystem().getTimeoutKapcsolodik_2(), ]},
				rules = {'TimeoutKapcsolodik_2' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_229")}},
				simulator=self)
				
		self.components["Orion.SubSystem5.DelayZarva_0"].configure(
				name  = "Orion.SubSystem5.DelayZarva_0",
				inport=self.detmodel.getOrion().getSubSystem5().getOrionSystem().getTimeoutZarva_0_req(),
				calls = {'TimeoutZarva_0' : [self.detmodel.getOrion().getSubSystem5().getOrionSystem().getTimeoutZarva_0(), ]},
				rules = {'TimeoutZarva_0' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayZarva_030")}},
				simulator=self)
				
		self.components["Orion.SubSystem5.DelayKeepAliveSendTimeout_1"].configure(
				name  = "Orion.SubSystem5.DelayKeepAliveSendTimeout_1",
				inport=self.detmodel.getOrion().getSubSystem5().getOrionSystem().getTimeoutKeepAliveSendTimeout_1_req(),
				calls = {'TimeoutKeepAliveSendTimeout_1' : [self.detmodel.getOrion().getSubSystem5().getOrionSystem().getTimeoutKeepAliveSendTimeout_1(), ]},
				rules = {'TimeoutKeepAliveSendTimeout_1' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_131")}},
				simulator=self)
				
		self.components["Orion.SubSystem5.DelayKeepAliveReceiveTimeout_4"].configure(
				name  = "Orion.SubSystem5.DelayKeepAliveReceiveTimeout_4",
				inport=self.detmodel.getOrion().getSubSystem5().getOrionSystem().getTimeoutKeepAliveReceiveTimeout_4_req(),
				calls = {'TimeoutKeepAliveReceiveTimeout_4' : [self.detmodel.getOrion().getSubSystem5().getOrionSystem().getTimeoutKeepAliveReceiveTimeout_4(), ]},
				rules = {'TimeoutKeepAliveReceiveTimeout_4' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_432")}},
				simulator=self)
				
		self.components["Orion.SubSystem5.DelayKapcsolodik_3"].configure(
				name  = "Orion.SubSystem5.DelayKapcsolodik_3",
				inport=self.detmodel.getOrion().getSubSystem5().getOrionSystem().getTimeoutKapcsolodik_3_req(),
				calls = {'TimeoutKapcsolodik_3' : [self.detmodel.getOrion().getSubSystem5().getOrionSystem().getTimeoutKapcsolodik_3(), ]},
				rules = {'TimeoutKapcsolodik_3' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_333")}},
				simulator=self)
				
		self.components["Orion.SubSystem5.DelayKeepAliveSendTimeout_0"].configure(
				name  = "Orion.SubSystem5.DelayKeepAliveSendTimeout_0",
				inport=self.detmodel.getOrion().getSubSystem5().getOrionSystem().getTimeoutKeepAliveSendTimeout_0_req(),
				calls = {'TimeoutKeepAliveSendTimeout_0' : [self.detmodel.getOrion().getSubSystem5().getOrionSystem().getTimeoutKeepAliveSendTimeout_0(), ]},
				rules = {'TimeoutKeepAliveSendTimeout_0' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_034")}},
				simulator=self)
				
		self.components["Orion.SubSystem6.DelayKeepAliveReceiveTimeout_3"].configure(
				name  = "Orion.SubSystem6.DelayKeepAliveReceiveTimeout_3",
				inport=self.detmodel.getOrion().getSubSystem6().getOrionSystem().getTimoeutKeepAliveReceiveTimeout_3_req(),
				calls = {'TimoeutKeepAliveReceiveTimeout_3' : [self.detmodel.getOrion().getSubSystem6().getOrionSystem().getTimoeutKeepAliveReceiveTimeout_3(), ]},
				rules = {'TimoeutKeepAliveReceiveTimeout_3' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_335")}},
				simulator=self)
				
		self.components["Orion.SubSystem6.DelayKapcsolodik_2"].configure(
				name  = "Orion.SubSystem6.DelayKapcsolodik_2",
				inport=self.detmodel.getOrion().getSubSystem6().getOrionSystem().getTimeoutKapcsolodik_2_req(),
				calls = {'TimeoutKapcsolodik_2' : [self.detmodel.getOrion().getSubSystem6().getOrionSystem().getTimeoutKapcsolodik_2(), ]},
				rules = {'TimeoutKapcsolodik_2' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_236")}},
				simulator=self)
				
		self.components["Orion.SubSystem6.DelayZarva_0"].configure(
				name  = "Orion.SubSystem6.DelayZarva_0",
				inport=self.detmodel.getOrion().getSubSystem6().getOrionSystem().getTimeoutZarva_0_req(),
				calls = {'TimeoutZarva_0' : [self.detmodel.getOrion().getSubSystem6().getOrionSystem().getTimeoutZarva_0(), ]},
				rules = {'TimeoutZarva_0' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayZarva_037")}},
				simulator=self)
				
		self.components["Orion.SubSystem6.DelayKeepAliveSendTimeout_1"].configure(
				name  = "Orion.SubSystem6.DelayKeepAliveSendTimeout_1",
				inport=self.detmodel.getOrion().getSubSystem6().getOrionSystem().getTimeoutKeepAliveSendTimeout_1_req(),
				calls = {'TimeoutKeepAliveSendTimeout_1' : [self.detmodel.getOrion().getSubSystem6().getOrionSystem().getTimeoutKeepAliveSendTimeout_1(), ]},
				rules = {'TimeoutKeepAliveSendTimeout_1' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_138")}},
				simulator=self)
				
		self.components["Orion.SubSystem6.DelayKeepAliveReceiveTimeout_4"].configure(
				name  = "Orion.SubSystem6.DelayKeepAliveReceiveTimeout_4",
				inport=self.detmodel.getOrion().getSubSystem6().getOrionSystem().getTimeoutKeepAliveReceiveTimeout_4_req(),
				calls = {'TimeoutKeepAliveReceiveTimeout_4' : [self.detmodel.getOrion().getSubSystem6().getOrionSystem().getTimeoutKeepAliveReceiveTimeout_4(), ]},
				rules = {'TimeoutKeepAliveReceiveTimeout_4' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_439")}},
				simulator=self)
				
		self.components["Orion.SubSystem6.DelayKapcsolodik_3"].configure(
				name  = "Orion.SubSystem6.DelayKapcsolodik_3",
				inport=self.detmodel.getOrion().getSubSystem6().getOrionSystem().getTimeoutKapcsolodik_3_req(),
				calls = {'TimeoutKapcsolodik_3' : [self.detmodel.getOrion().getSubSystem6().getOrionSystem().getTimeoutKapcsolodik_3(), ]},
				rules = {'TimeoutKapcsolodik_3' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_340")}},
				simulator=self)
				
		self.components["Orion.SubSystem6.DelayKeepAliveSendTimeout_0"].configure(
				name  = "Orion.SubSystem6.DelayKeepAliveSendTimeout_0",
				inport=self.detmodel.getOrion().getSubSystem6().getOrionSystem().getTimeoutKeepAliveSendTimeout_0_req(),
				calls = {'TimeoutKeepAliveSendTimeout_0' : [self.detmodel.getOrion().getSubSystem6().getOrionSystem().getTimeoutKeepAliveSendTimeout_0(), ]},
				rules = {'TimeoutKeepAliveSendTimeout_0' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_041")}},
				simulator=self)
				
		self.components["Orion.SubSystem7.DelayKeepAliveReceiveTimeout_3"].configure(
				name  = "Orion.SubSystem7.DelayKeepAliveReceiveTimeout_3",
				inport=self.detmodel.getOrion().getSubSystem7().getOrionSystem().getTimoeutKeepAliveReceiveTimeout_3_req(),
				calls = {'TimoeutKeepAliveReceiveTimeout_3' : [self.detmodel.getOrion().getSubSystem7().getOrionSystem().getTimoeutKeepAliveReceiveTimeout_3(), ]},
				rules = {'TimoeutKeepAliveReceiveTimeout_3' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_342")}},
				simulator=self)
				
		self.components["Orion.SubSystem7.DelayKapcsolodik_2"].configure(
				name  = "Orion.SubSystem7.DelayKapcsolodik_2",
				inport=self.detmodel.getOrion().getSubSystem7().getOrionSystem().getTimeoutKapcsolodik_2_req(),
				calls = {'TimeoutKapcsolodik_2' : [self.detmodel.getOrion().getSubSystem7().getOrionSystem().getTimeoutKapcsolodik_2(), ]},
				rules = {'TimeoutKapcsolodik_2' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_243")}},
				simulator=self)
				
		self.components["Orion.SubSystem7.DelayZarva_0"].configure(
				name  = "Orion.SubSystem7.DelayZarva_0",
				inport=self.detmodel.getOrion().getSubSystem7().getOrionSystem().getTimeoutZarva_0_req(),
				calls = {'TimeoutZarva_0' : [self.detmodel.getOrion().getSubSystem7().getOrionSystem().getTimeoutZarva_0(), ]},
				rules = {'TimeoutZarva_0' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayZarva_044")}},
				simulator=self)
				
		self.components["Orion.SubSystem7.DelayKeepAliveSendTimeout_1"].configure(
				name  = "Orion.SubSystem7.DelayKeepAliveSendTimeout_1",
				inport=self.detmodel.getOrion().getSubSystem7().getOrionSystem().getTimeoutKeepAliveSendTimeout_1_req(),
				calls = {'TimeoutKeepAliveSendTimeout_1' : [self.detmodel.getOrion().getSubSystem7().getOrionSystem().getTimeoutKeepAliveSendTimeout_1(), ]},
				rules = {'TimeoutKeepAliveSendTimeout_1' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_145")}},
				simulator=self)
				
		self.components["Orion.SubSystem7.DelayKeepAliveReceiveTimeout_4"].configure(
				name  = "Orion.SubSystem7.DelayKeepAliveReceiveTimeout_4",
				inport=self.detmodel.getOrion().getSubSystem7().getOrionSystem().getTimeoutKeepAliveReceiveTimeout_4_req(),
				calls = {'TimeoutKeepAliveReceiveTimeout_4' : [self.detmodel.getOrion().getSubSystem7().getOrionSystem().getTimeoutKeepAliveReceiveTimeout_4(), ]},
				rules = {'TimeoutKeepAliveReceiveTimeout_4' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_446")}},
				simulator=self)
				
		self.components["Orion.SubSystem7.DelayKapcsolodik_3"].configure(
				name  = "Orion.SubSystem7.DelayKapcsolodik_3",
				inport=self.detmodel.getOrion().getSubSystem7().getOrionSystem().getTimeoutKapcsolodik_3_req(),
				calls = {'TimeoutKapcsolodik_3' : [self.detmodel.getOrion().getSubSystem7().getOrionSystem().getTimeoutKapcsolodik_3(), ]},
				rules = {'TimeoutKapcsolodik_3' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_347")}},
				simulator=self)
				
		self.components["Orion.SubSystem7.DelayKeepAliveSendTimeout_0"].configure(
				name  = "Orion.SubSystem7.DelayKeepAliveSendTimeout_0",
				inport=self.detmodel.getOrion().getSubSystem7().getOrionSystem().getTimeoutKeepAliveSendTimeout_0_req(),
				calls = {'TimeoutKeepAliveSendTimeout_0' : [self.detmodel.getOrion().getSubSystem7().getOrionSystem().getTimeoutKeepAliveSendTimeout_0(), ]},
				rules = {'TimeoutKeepAliveSendTimeout_0' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_048")}},
				simulator=self)
				
		self.components["Orion.SubSystem8.DelayKeepAliveReceiveTimeout_3"].configure(
				name  = "Orion.SubSystem8.DelayKeepAliveReceiveTimeout_3",
				inport=self.detmodel.getOrion().getSubSystem8().getOrionSystem().getTimoeutKeepAliveReceiveTimeout_3_req(),
				calls = {'TimoeutKeepAliveReceiveTimeout_3' : [self.detmodel.getOrion().getSubSystem8().getOrionSystem().getTimoeutKeepAliveReceiveTimeout_3(), ]},
				rules = {'TimoeutKeepAliveReceiveTimeout_3' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_349")}},
				simulator=self)
				
		self.components["Orion.SubSystem8.DelayKapcsolodik_2"].configure(
				name  = "Orion.SubSystem8.DelayKapcsolodik_2",
				inport=self.detmodel.getOrion().getSubSystem8().getOrionSystem().getTimeoutKapcsolodik_2_req(),
				calls = {'TimeoutKapcsolodik_2' : [self.detmodel.getOrion().getSubSystem8().getOrionSystem().getTimeoutKapcsolodik_2(), ]},
				rules = {'TimeoutKapcsolodik_2' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_250")}},
				simulator=self)
				
		self.components["Orion.SubSystem8.DelayZarva_0"].configure(
				name  = "Orion.SubSystem8.DelayZarva_0",
				inport=self.detmodel.getOrion().getSubSystem8().getOrionSystem().getTimeoutZarva_0_req(),
				calls = {'TimeoutZarva_0' : [self.detmodel.getOrion().getSubSystem8().getOrionSystem().getTimeoutZarva_0(), ]},
				rules = {'TimeoutZarva_0' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayZarva_051")}},
				simulator=self)
				
		self.components["Orion.SubSystem8.DelayKeepAliveSendTimeout_1"].configure(
				name  = "Orion.SubSystem8.DelayKeepAliveSendTimeout_1",
				inport=self.detmodel.getOrion().getSubSystem8().getOrionSystem().getTimeoutKeepAliveSendTimeout_1_req(),
				calls = {'TimeoutKeepAliveSendTimeout_1' : [self.detmodel.getOrion().getSubSystem8().getOrionSystem().getTimeoutKeepAliveSendTimeout_1(), ]},
				rules = {'TimeoutKeepAliveSendTimeout_1' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_152")}},
				simulator=self)
				
		self.components["Orion.SubSystem8.DelayKeepAliveReceiveTimeout_4"].configure(
				name  = "Orion.SubSystem8.DelayKeepAliveReceiveTimeout_4",
				inport=self.detmodel.getOrion().getSubSystem8().getOrionSystem().getTimeoutKeepAliveReceiveTimeout_4_req(),
				calls = {'TimeoutKeepAliveReceiveTimeout_4' : [self.detmodel.getOrion().getSubSystem8().getOrionSystem().getTimeoutKeepAliveReceiveTimeout_4(), ]},
				rules = {'TimeoutKeepAliveReceiveTimeout_4' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_453")}},
				simulator=self)
				
		self.components["Orion.SubSystem8.DelayKapcsolodik_3"].configure(
				name  = "Orion.SubSystem8.DelayKapcsolodik_3",
				inport=self.detmodel.getOrion().getSubSystem8().getOrionSystem().getTimeoutKapcsolodik_3_req(),
				calls = {'TimeoutKapcsolodik_3' : [self.detmodel.getOrion().getSubSystem8().getOrionSystem().getTimeoutKapcsolodik_3(), ]},
				rules = {'TimeoutKapcsolodik_3' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_354")}},
				simulator=self)
				
		self.components["Orion.SubSystem8.DelayKeepAliveSendTimeout_0"].configure(
				name  = "Orion.SubSystem8.DelayKeepAliveSendTimeout_0",
				inport=self.detmodel.getOrion().getSubSystem8().getOrionSystem().getTimeoutKeepAliveSendTimeout_0_req(),
				calls = {'TimeoutKeepAliveSendTimeout_0' : [self.detmodel.getOrion().getSubSystem8().getOrionSystem().getTimeoutKeepAliveSendTimeout_0(), ]},
				rules = {'TimeoutKeepAliveSendTimeout_0' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_055")}},
				simulator=self)
				
		self.components["Orion.SubSystem9.DelayKeepAliveReceiveTimeout_3"].configure(
				name  = "Orion.SubSystem9.DelayKeepAliveReceiveTimeout_3",
				inport=self.detmodel.getOrion().getSubSystem9().getOrionSystem().getTimoeutKeepAliveReceiveTimeout_3_req(),
				calls = {'TimoeutKeepAliveReceiveTimeout_3' : [self.detmodel.getOrion().getSubSystem9().getOrionSystem().getTimoeutKeepAliveReceiveTimeout_3(), ]},
				rules = {'TimoeutKeepAliveReceiveTimeout_3' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_356")}},
				simulator=self)
				
		self.components["Orion.SubSystem9.DelayKapcsolodik_2"].configure(
				name  = "Orion.SubSystem9.DelayKapcsolodik_2",
				inport=self.detmodel.getOrion().getSubSystem9().getOrionSystem().getTimeoutKapcsolodik_2_req(),
				calls = {'TimeoutKapcsolodik_2' : [self.detmodel.getOrion().getSubSystem9().getOrionSystem().getTimeoutKapcsolodik_2(), ]},
				rules = {'TimeoutKapcsolodik_2' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_257")}},
				simulator=self)
				
		self.components["Orion.SubSystem9.DelayZarva_0"].configure(
				name  = "Orion.SubSystem9.DelayZarva_0",
				inport=self.detmodel.getOrion().getSubSystem9().getOrionSystem().getTimeoutZarva_0_req(),
				calls = {'TimeoutZarva_0' : [self.detmodel.getOrion().getSubSystem9().getOrionSystem().getTimeoutZarva_0(), ]},
				rules = {'TimeoutZarva_0' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayZarva_058")}},
				simulator=self)
				
		self.components["Orion.SubSystem9.DelayKeepAliveSendTimeout_1"].configure(
				name  = "Orion.SubSystem9.DelayKeepAliveSendTimeout_1",
				inport=self.detmodel.getOrion().getSubSystem9().getOrionSystem().getTimeoutKeepAliveSendTimeout_1_req(),
				calls = {'TimeoutKeepAliveSendTimeout_1' : [self.detmodel.getOrion().getSubSystem9().getOrionSystem().getTimeoutKeepAliveSendTimeout_1(), ]},
				rules = {'TimeoutKeepAliveSendTimeout_1' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_159")}},
				simulator=self)
				
		self.components["Orion.SubSystem9.DelayKeepAliveReceiveTimeout_4"].configure(
				name  = "Orion.SubSystem9.DelayKeepAliveReceiveTimeout_4",
				inport=self.detmodel.getOrion().getSubSystem9().getOrionSystem().getTimeoutKeepAliveReceiveTimeout_4_req(),
				calls = {'TimeoutKeepAliveReceiveTimeout_4' : [self.detmodel.getOrion().getSubSystem9().getOrionSystem().getTimeoutKeepAliveReceiveTimeout_4(), ]},
				rules = {'TimeoutKeepAliveReceiveTimeout_4' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_460")}},
				simulator=self)
				
		self.components["Orion.SubSystem9.DelayKapcsolodik_3"].configure(
				name  = "Orion.SubSystem9.DelayKapcsolodik_3",
				inport=self.detmodel.getOrion().getSubSystem9().getOrionSystem().getTimeoutKapcsolodik_3_req(),
				calls = {'TimeoutKapcsolodik_3' : [self.detmodel.getOrion().getSubSystem9().getOrionSystem().getTimeoutKapcsolodik_3(), ]},
				rules = {'TimeoutKapcsolodik_3' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_361")}},
				simulator=self)
				
		self.components["Orion.SubSystem9.DelayKeepAliveSendTimeout_0"].configure(
				name  = "Orion.SubSystem9.DelayKeepAliveSendTimeout_0",
				inport=self.detmodel.getOrion().getSubSystem9().getOrionSystem().getTimeoutKeepAliveSendTimeout_0_req(),
				calls = {'TimeoutKeepAliveSendTimeout_0' : [self.detmodel.getOrion().getSubSystem9().getOrionSystem().getTimeoutKeepAliveSendTimeout_0(), ]},
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
				
		self.components["Orion.SubSystem17.DelayKeepAliveReceiveTimeout_3"].configure(
				name  = "Orion.SubSystem17.DelayKeepAliveReceiveTimeout_3",
				inport=self.detmodel.getOrion().getSubSystem17().getOrionSystem().getTimoeutKeepAliveReceiveTimeout_3_req(),
				calls = {'TimoeutKeepAliveReceiveTimeout_3' : [self.detmodel.getOrion().getSubSystem17().getOrionSystem().getTimoeutKeepAliveReceiveTimeout_3(), ]},
				rules = {'TimoeutKeepAliveReceiveTimeout_3' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_3112")}},
				simulator=self)
				
		self.components["Orion.SubSystem17.DelayKapcsolodik_2"].configure(
				name  = "Orion.SubSystem17.DelayKapcsolodik_2",
				inport=self.detmodel.getOrion().getSubSystem17().getOrionSystem().getTimeoutKapcsolodik_2_req(),
				calls = {'TimeoutKapcsolodik_2' : [self.detmodel.getOrion().getSubSystem17().getOrionSystem().getTimeoutKapcsolodik_2(), ]},
				rules = {'TimeoutKapcsolodik_2' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_2113")}},
				simulator=self)
				
		self.components["Orion.SubSystem17.DelayZarva_0"].configure(
				name  = "Orion.SubSystem17.DelayZarva_0",
				inport=self.detmodel.getOrion().getSubSystem17().getOrionSystem().getTimeoutZarva_0_req(),
				calls = {'TimeoutZarva_0' : [self.detmodel.getOrion().getSubSystem17().getOrionSystem().getTimeoutZarva_0(), ]},
				rules = {'TimeoutZarva_0' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayZarva_0114")}},
				simulator=self)
				
		self.components["Orion.SubSystem17.DelayKeepAliveSendTimeout_1"].configure(
				name  = "Orion.SubSystem17.DelayKeepAliveSendTimeout_1",
				inport=self.detmodel.getOrion().getSubSystem17().getOrionSystem().getTimeoutKeepAliveSendTimeout_1_req(),
				calls = {'TimeoutKeepAliveSendTimeout_1' : [self.detmodel.getOrion().getSubSystem17().getOrionSystem().getTimeoutKeepAliveSendTimeout_1(), ]},
				rules = {'TimeoutKeepAliveSendTimeout_1' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_1115")}},
				simulator=self)
				
		self.components["Orion.SubSystem17.DelayKeepAliveReceiveTimeout_4"].configure(
				name  = "Orion.SubSystem17.DelayKeepAliveReceiveTimeout_4",
				inport=self.detmodel.getOrion().getSubSystem17().getOrionSystem().getTimeoutKeepAliveReceiveTimeout_4_req(),
				calls = {'TimeoutKeepAliveReceiveTimeout_4' : [self.detmodel.getOrion().getSubSystem17().getOrionSystem().getTimeoutKeepAliveReceiveTimeout_4(), ]},
				rules = {'TimeoutKeepAliveReceiveTimeout_4' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_4116")}},
				simulator=self)
				
		self.components["Orion.SubSystem17.DelayKapcsolodik_3"].configure(
				name  = "Orion.SubSystem17.DelayKapcsolodik_3",
				inport=self.detmodel.getOrion().getSubSystem17().getOrionSystem().getTimeoutKapcsolodik_3_req(),
				calls = {'TimeoutKapcsolodik_3' : [self.detmodel.getOrion().getSubSystem17().getOrionSystem().getTimeoutKapcsolodik_3(), ]},
				rules = {'TimeoutKapcsolodik_3' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_3117")}},
				simulator=self)
				
		self.components["Orion.SubSystem17.DelayKeepAliveSendTimeout_0"].configure(
				name  = "Orion.SubSystem17.DelayKeepAliveSendTimeout_0",
				inport=self.detmodel.getOrion().getSubSystem17().getOrionSystem().getTimeoutKeepAliveSendTimeout_0_req(),
				calls = {'TimeoutKeepAliveSendTimeout_0' : [self.detmodel.getOrion().getSubSystem17().getOrionSystem().getTimeoutKeepAliveSendTimeout_0(), ]},
				rules = {'TimeoutKeepAliveSendTimeout_0' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_0118")}},
				simulator=self)
				
		self.components["Orion.SubSystem18.DelayKeepAliveReceiveTimeout_3"].configure(
				name  = "Orion.SubSystem18.DelayKeepAliveReceiveTimeout_3",
				inport=self.detmodel.getOrion().getSubSystem18().getOrionSystem().getTimoeutKeepAliveReceiveTimeout_3_req(),
				calls = {'TimoeutKeepAliveReceiveTimeout_3' : [self.detmodel.getOrion().getSubSystem18().getOrionSystem().getTimoeutKeepAliveReceiveTimeout_3(), ]},
				rules = {'TimoeutKeepAliveReceiveTimeout_3' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_3119")}},
				simulator=self)
				
		self.components["Orion.SubSystem18.DelayKapcsolodik_2"].configure(
				name  = "Orion.SubSystem18.DelayKapcsolodik_2",
				inport=self.detmodel.getOrion().getSubSystem18().getOrionSystem().getTimeoutKapcsolodik_2_req(),
				calls = {'TimeoutKapcsolodik_2' : [self.detmodel.getOrion().getSubSystem18().getOrionSystem().getTimeoutKapcsolodik_2(), ]},
				rules = {'TimeoutKapcsolodik_2' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_2120")}},
				simulator=self)
				
		self.components["Orion.SubSystem18.DelayZarva_0"].configure(
				name  = "Orion.SubSystem18.DelayZarva_0",
				inport=self.detmodel.getOrion().getSubSystem18().getOrionSystem().getTimeoutZarva_0_req(),
				calls = {'TimeoutZarva_0' : [self.detmodel.getOrion().getSubSystem18().getOrionSystem().getTimeoutZarva_0(), ]},
				rules = {'TimeoutZarva_0' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayZarva_0121")}},
				simulator=self)
				
		self.components["Orion.SubSystem18.DelayKeepAliveSendTimeout_1"].configure(
				name  = "Orion.SubSystem18.DelayKeepAliveSendTimeout_1",
				inport=self.detmodel.getOrion().getSubSystem18().getOrionSystem().getTimeoutKeepAliveSendTimeout_1_req(),
				calls = {'TimeoutKeepAliveSendTimeout_1' : [self.detmodel.getOrion().getSubSystem18().getOrionSystem().getTimeoutKeepAliveSendTimeout_1(), ]},
				rules = {'TimeoutKeepAliveSendTimeout_1' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_1122")}},
				simulator=self)
				
		self.components["Orion.SubSystem18.DelayKeepAliveReceiveTimeout_4"].configure(
				name  = "Orion.SubSystem18.DelayKeepAliveReceiveTimeout_4",
				inport=self.detmodel.getOrion().getSubSystem18().getOrionSystem().getTimeoutKeepAliveReceiveTimeout_4_req(),
				calls = {'TimeoutKeepAliveReceiveTimeout_4' : [self.detmodel.getOrion().getSubSystem18().getOrionSystem().getTimeoutKeepAliveReceiveTimeout_4(), ]},
				rules = {'TimeoutKeepAliveReceiveTimeout_4' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_4123")}},
				simulator=self)
				
		self.components["Orion.SubSystem18.DelayKapcsolodik_3"].configure(
				name  = "Orion.SubSystem18.DelayKapcsolodik_3",
				inport=self.detmodel.getOrion().getSubSystem18().getOrionSystem().getTimeoutKapcsolodik_3_req(),
				calls = {'TimeoutKapcsolodik_3' : [self.detmodel.getOrion().getSubSystem18().getOrionSystem().getTimeoutKapcsolodik_3(), ]},
				rules = {'TimeoutKapcsolodik_3' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_3124")}},
				simulator=self)
				
		self.components["Orion.SubSystem18.DelayKeepAliveSendTimeout_0"].configure(
				name  = "Orion.SubSystem18.DelayKeepAliveSendTimeout_0",
				inport=self.detmodel.getOrion().getSubSystem18().getOrionSystem().getTimeoutKeepAliveSendTimeout_0_req(),
				calls = {'TimeoutKeepAliveSendTimeout_0' : [self.detmodel.getOrion().getSubSystem18().getOrionSystem().getTimeoutKeepAliveSendTimeout_0(), ]},
				rules = {'TimeoutKeepAliveSendTimeout_0' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_0125")}},
				simulator=self)
				
		self.components["Orion.SubSystem19.DelayKeepAliveReceiveTimeout_3"].configure(
				name  = "Orion.SubSystem19.DelayKeepAliveReceiveTimeout_3",
				inport=self.detmodel.getOrion().getSubSystem19().getOrionSystem().getTimoeutKeepAliveReceiveTimeout_3_req(),
				calls = {'TimoeutKeepAliveReceiveTimeout_3' : [self.detmodel.getOrion().getSubSystem19().getOrionSystem().getTimoeutKeepAliveReceiveTimeout_3(), ]},
				rules = {'TimoeutKeepAliveReceiveTimeout_3' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_3126")}},
				simulator=self)
				
		self.components["Orion.SubSystem19.DelayKapcsolodik_2"].configure(
				name  = "Orion.SubSystem19.DelayKapcsolodik_2",
				inport=self.detmodel.getOrion().getSubSystem19().getOrionSystem().getTimeoutKapcsolodik_2_req(),
				calls = {'TimeoutKapcsolodik_2' : [self.detmodel.getOrion().getSubSystem19().getOrionSystem().getTimeoutKapcsolodik_2(), ]},
				rules = {'TimeoutKapcsolodik_2' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_2127")}},
				simulator=self)
				
		self.components["Orion.SubSystem19.DelayZarva_0"].configure(
				name  = "Orion.SubSystem19.DelayZarva_0",
				inport=self.detmodel.getOrion().getSubSystem19().getOrionSystem().getTimeoutZarva_0_req(),
				calls = {'TimeoutZarva_0' : [self.detmodel.getOrion().getSubSystem19().getOrionSystem().getTimeoutZarva_0(), ]},
				rules = {'TimeoutZarva_0' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayZarva_0128")}},
				simulator=self)
				
		self.components["Orion.SubSystem19.DelayKeepAliveSendTimeout_1"].configure(
				name  = "Orion.SubSystem19.DelayKeepAliveSendTimeout_1",
				inport=self.detmodel.getOrion().getSubSystem19().getOrionSystem().getTimeoutKeepAliveSendTimeout_1_req(),
				calls = {'TimeoutKeepAliveSendTimeout_1' : [self.detmodel.getOrion().getSubSystem19().getOrionSystem().getTimeoutKeepAliveSendTimeout_1(), ]},
				rules = {'TimeoutKeepAliveSendTimeout_1' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_1129")}},
				simulator=self)
				
		self.components["Orion.SubSystem19.DelayKeepAliveReceiveTimeout_4"].configure(
				name  = "Orion.SubSystem19.DelayKeepAliveReceiveTimeout_4",
				inport=self.detmodel.getOrion().getSubSystem19().getOrionSystem().getTimeoutKeepAliveReceiveTimeout_4_req(),
				calls = {'TimeoutKeepAliveReceiveTimeout_4' : [self.detmodel.getOrion().getSubSystem19().getOrionSystem().getTimeoutKeepAliveReceiveTimeout_4(), ]},
				rules = {'TimeoutKeepAliveReceiveTimeout_4' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_4130")}},
				simulator=self)
				
		self.components["Orion.SubSystem19.DelayKapcsolodik_3"].configure(
				name  = "Orion.SubSystem19.DelayKapcsolodik_3",
				inport=self.detmodel.getOrion().getSubSystem19().getOrionSystem().getTimeoutKapcsolodik_3_req(),
				calls = {'TimeoutKapcsolodik_3' : [self.detmodel.getOrion().getSubSystem19().getOrionSystem().getTimeoutKapcsolodik_3(), ]},
				rules = {'TimeoutKapcsolodik_3' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_3131")}},
				simulator=self)
				
		self.components["Orion.SubSystem19.DelayKeepAliveSendTimeout_0"].configure(
				name  = "Orion.SubSystem19.DelayKeepAliveSendTimeout_0",
				inport=self.detmodel.getOrion().getSubSystem19().getOrionSystem().getTimeoutKeepAliveSendTimeout_0_req(),
				calls = {'TimeoutKeepAliveSendTimeout_0' : [self.detmodel.getOrion().getSubSystem19().getOrionSystem().getTimeoutKeepAliveSendTimeout_0(), ]},
				rules = {'TimeoutKeepAliveSendTimeout_0' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_0132")}},
				simulator=self)
				
		self.components["Orion.SubSystem20.DelayKeepAliveReceiveTimeout_3"].configure(
				name  = "Orion.SubSystem20.DelayKeepAliveReceiveTimeout_3",
				inport=self.detmodel.getOrion().getSubSystem20().getOrionSystem().getTimoeutKeepAliveReceiveTimeout_3_req(),
				calls = {'TimoeutKeepAliveReceiveTimeout_3' : [self.detmodel.getOrion().getSubSystem20().getOrionSystem().getTimoeutKeepAliveReceiveTimeout_3(), ]},
				rules = {'TimoeutKeepAliveReceiveTimeout_3' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_3133")}},
				simulator=self)
				
		self.components["Orion.SubSystem20.DelayKapcsolodik_2"].configure(
				name  = "Orion.SubSystem20.DelayKapcsolodik_2",
				inport=self.detmodel.getOrion().getSubSystem20().getOrionSystem().getTimeoutKapcsolodik_2_req(),
				calls = {'TimeoutKapcsolodik_2' : [self.detmodel.getOrion().getSubSystem20().getOrionSystem().getTimeoutKapcsolodik_2(), ]},
				rules = {'TimeoutKapcsolodik_2' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_2134")}},
				simulator=self)
				
		self.components["Orion.SubSystem20.DelayZarva_0"].configure(
				name  = "Orion.SubSystem20.DelayZarva_0",
				inport=self.detmodel.getOrion().getSubSystem20().getOrionSystem().getTimeoutZarva_0_req(),
				calls = {'TimeoutZarva_0' : [self.detmodel.getOrion().getSubSystem20().getOrionSystem().getTimeoutZarva_0(), ]},
				rules = {'TimeoutZarva_0' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayZarva_0135")}},
				simulator=self)
				
		self.components["Orion.SubSystem20.DelayKeepAliveSendTimeout_1"].configure(
				name  = "Orion.SubSystem20.DelayKeepAliveSendTimeout_1",
				inport=self.detmodel.getOrion().getSubSystem20().getOrionSystem().getTimeoutKeepAliveSendTimeout_1_req(),
				calls = {'TimeoutKeepAliveSendTimeout_1' : [self.detmodel.getOrion().getSubSystem20().getOrionSystem().getTimeoutKeepAliveSendTimeout_1(), ]},
				rules = {'TimeoutKeepAliveSendTimeout_1' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_1136")}},
				simulator=self)
				
		self.components["Orion.SubSystem20.DelayKeepAliveReceiveTimeout_4"].configure(
				name  = "Orion.SubSystem20.DelayKeepAliveReceiveTimeout_4",
				inport=self.detmodel.getOrion().getSubSystem20().getOrionSystem().getTimeoutKeepAliveReceiveTimeout_4_req(),
				calls = {'TimeoutKeepAliveReceiveTimeout_4' : [self.detmodel.getOrion().getSubSystem20().getOrionSystem().getTimeoutKeepAliveReceiveTimeout_4(), ]},
				rules = {'TimeoutKeepAliveReceiveTimeout_4' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_4137")}},
				simulator=self)
				
		self.components["Orion.SubSystem20.DelayKapcsolodik_3"].configure(
				name  = "Orion.SubSystem20.DelayKapcsolodik_3",
				inport=self.detmodel.getOrion().getSubSystem20().getOrionSystem().getTimeoutKapcsolodik_3_req(),
				calls = {'TimeoutKapcsolodik_3' : [self.detmodel.getOrion().getSubSystem20().getOrionSystem().getTimeoutKapcsolodik_3(), ]},
				rules = {'TimeoutKapcsolodik_3' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_3138")}},
				simulator=self)
				
		self.components["Orion.SubSystem20.DelayKeepAliveSendTimeout_0"].configure(
				name  = "Orion.SubSystem20.DelayKeepAliveSendTimeout_0",
				inport=self.detmodel.getOrion().getSubSystem20().getOrionSystem().getTimeoutKeepAliveSendTimeout_0_req(),
				calls = {'TimeoutKeepAliveSendTimeout_0' : [self.detmodel.getOrion().getSubSystem20().getOrionSystem().getTimeoutKeepAliveSendTimeout_0(), ]},
				rules = {'TimeoutKeepAliveSendTimeout_0' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_0139")}},
				simulator=self)
				
		self.components["Orion.SubSystem21.DelayKeepAliveReceiveTimeout_3"].configure(
				name  = "Orion.SubSystem21.DelayKeepAliveReceiveTimeout_3",
				inport=self.detmodel.getOrion().getSubSystem21().getOrionSystem().getTimoeutKeepAliveReceiveTimeout_3_req(),
				calls = {'TimoeutKeepAliveReceiveTimeout_3' : [self.detmodel.getOrion().getSubSystem21().getOrionSystem().getTimoeutKeepAliveReceiveTimeout_3(), ]},
				rules = {'TimoeutKeepAliveReceiveTimeout_3' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_3140")}},
				simulator=self)
				
		self.components["Orion.SubSystem21.DelayKapcsolodik_2"].configure(
				name  = "Orion.SubSystem21.DelayKapcsolodik_2",
				inport=self.detmodel.getOrion().getSubSystem21().getOrionSystem().getTimeoutKapcsolodik_2_req(),
				calls = {'TimeoutKapcsolodik_2' : [self.detmodel.getOrion().getSubSystem21().getOrionSystem().getTimeoutKapcsolodik_2(), ]},
				rules = {'TimeoutKapcsolodik_2' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_2141")}},
				simulator=self)
				
		self.components["Orion.SubSystem21.DelayZarva_0"].configure(
				name  = "Orion.SubSystem21.DelayZarva_0",
				inport=self.detmodel.getOrion().getSubSystem21().getOrionSystem().getTimeoutZarva_0_req(),
				calls = {'TimeoutZarva_0' : [self.detmodel.getOrion().getSubSystem21().getOrionSystem().getTimeoutZarva_0(), ]},
				rules = {'TimeoutZarva_0' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayZarva_0142")}},
				simulator=self)
				
		self.components["Orion.SubSystem21.DelayKeepAliveSendTimeout_1"].configure(
				name  = "Orion.SubSystem21.DelayKeepAliveSendTimeout_1",
				inport=self.detmodel.getOrion().getSubSystem21().getOrionSystem().getTimeoutKeepAliveSendTimeout_1_req(),
				calls = {'TimeoutKeepAliveSendTimeout_1' : [self.detmodel.getOrion().getSubSystem21().getOrionSystem().getTimeoutKeepAliveSendTimeout_1(), ]},
				rules = {'TimeoutKeepAliveSendTimeout_1' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_1143")}},
				simulator=self)
				
		self.components["Orion.SubSystem21.DelayKeepAliveReceiveTimeout_4"].configure(
				name  = "Orion.SubSystem21.DelayKeepAliveReceiveTimeout_4",
				inport=self.detmodel.getOrion().getSubSystem21().getOrionSystem().getTimeoutKeepAliveReceiveTimeout_4_req(),
				calls = {'TimeoutKeepAliveReceiveTimeout_4' : [self.detmodel.getOrion().getSubSystem21().getOrionSystem().getTimeoutKeepAliveReceiveTimeout_4(), ]},
				rules = {'TimeoutKeepAliveReceiveTimeout_4' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_4144")}},
				simulator=self)
				
		self.components["Orion.SubSystem21.DelayKapcsolodik_3"].configure(
				name  = "Orion.SubSystem21.DelayKapcsolodik_3",
				inport=self.detmodel.getOrion().getSubSystem21().getOrionSystem().getTimeoutKapcsolodik_3_req(),
				calls = {'TimeoutKapcsolodik_3' : [self.detmodel.getOrion().getSubSystem21().getOrionSystem().getTimeoutKapcsolodik_3(), ]},
				rules = {'TimeoutKapcsolodik_3' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_3145")}},
				simulator=self)
				
		self.components["Orion.SubSystem21.DelayKeepAliveSendTimeout_0"].configure(
				name  = "Orion.SubSystem21.DelayKeepAliveSendTimeout_0",
				inport=self.detmodel.getOrion().getSubSystem21().getOrionSystem().getTimeoutKeepAliveSendTimeout_0_req(),
				calls = {'TimeoutKeepAliveSendTimeout_0' : [self.detmodel.getOrion().getSubSystem21().getOrionSystem().getTimeoutKeepAliveSendTimeout_0(), ]},
				rules = {'TimeoutKeepAliveSendTimeout_0' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_0146")}},
				simulator=self)
				
		self.components["Orion.SubSystem22.DelayKeepAliveReceiveTimeout_3"].configure(
				name  = "Orion.SubSystem22.DelayKeepAliveReceiveTimeout_3",
				inport=self.detmodel.getOrion().getSubSystem22().getOrionSystem().getTimoeutKeepAliveReceiveTimeout_3_req(),
				calls = {'TimoeutKeepAliveReceiveTimeout_3' : [self.detmodel.getOrion().getSubSystem22().getOrionSystem().getTimoeutKeepAliveReceiveTimeout_3(), ]},
				rules = {'TimoeutKeepAliveReceiveTimeout_3' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_3147")}},
				simulator=self)
				
		self.components["Orion.SubSystem22.DelayKapcsolodik_2"].configure(
				name  = "Orion.SubSystem22.DelayKapcsolodik_2",
				inport=self.detmodel.getOrion().getSubSystem22().getOrionSystem().getTimeoutKapcsolodik_2_req(),
				calls = {'TimeoutKapcsolodik_2' : [self.detmodel.getOrion().getSubSystem22().getOrionSystem().getTimeoutKapcsolodik_2(), ]},
				rules = {'TimeoutKapcsolodik_2' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_2148")}},
				simulator=self)
				
		self.components["Orion.SubSystem22.DelayZarva_0"].configure(
				name  = "Orion.SubSystem22.DelayZarva_0",
				inport=self.detmodel.getOrion().getSubSystem22().getOrionSystem().getTimeoutZarva_0_req(),
				calls = {'TimeoutZarva_0' : [self.detmodel.getOrion().getSubSystem22().getOrionSystem().getTimeoutZarva_0(), ]},
				rules = {'TimeoutZarva_0' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayZarva_0149")}},
				simulator=self)
				
		self.components["Orion.SubSystem22.DelayKeepAliveSendTimeout_1"].configure(
				name  = "Orion.SubSystem22.DelayKeepAliveSendTimeout_1",
				inport=self.detmodel.getOrion().getSubSystem22().getOrionSystem().getTimeoutKeepAliveSendTimeout_1_req(),
				calls = {'TimeoutKeepAliveSendTimeout_1' : [self.detmodel.getOrion().getSubSystem22().getOrionSystem().getTimeoutKeepAliveSendTimeout_1(), ]},
				rules = {'TimeoutKeepAliveSendTimeout_1' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_1150")}},
				simulator=self)
				
		self.components["Orion.SubSystem22.DelayKeepAliveReceiveTimeout_4"].configure(
				name  = "Orion.SubSystem22.DelayKeepAliveReceiveTimeout_4",
				inport=self.detmodel.getOrion().getSubSystem22().getOrionSystem().getTimeoutKeepAliveReceiveTimeout_4_req(),
				calls = {'TimeoutKeepAliveReceiveTimeout_4' : [self.detmodel.getOrion().getSubSystem22().getOrionSystem().getTimeoutKeepAliveReceiveTimeout_4(), ]},
				rules = {'TimeoutKeepAliveReceiveTimeout_4' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_4151")}},
				simulator=self)
				
		self.components["Orion.SubSystem22.DelayKapcsolodik_3"].configure(
				name  = "Orion.SubSystem22.DelayKapcsolodik_3",
				inport=self.detmodel.getOrion().getSubSystem22().getOrionSystem().getTimeoutKapcsolodik_3_req(),
				calls = {'TimeoutKapcsolodik_3' : [self.detmodel.getOrion().getSubSystem22().getOrionSystem().getTimeoutKapcsolodik_3(), ]},
				rules = {'TimeoutKapcsolodik_3' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_3152")}},
				simulator=self)
				
		self.components["Orion.SubSystem22.DelayKeepAliveSendTimeout_0"].configure(
				name  = "Orion.SubSystem22.DelayKeepAliveSendTimeout_0",
				inport=self.detmodel.getOrion().getSubSystem22().getOrionSystem().getTimeoutKeepAliveSendTimeout_0_req(),
				calls = {'TimeoutKeepAliveSendTimeout_0' : [self.detmodel.getOrion().getSubSystem22().getOrionSystem().getTimeoutKeepAliveSendTimeout_0(), ]},
				rules = {'TimeoutKeepAliveSendTimeout_0' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_0153")}},
				simulator=self)
				
		self.components["Orion.SubSystem23.DelayKeepAliveReceiveTimeout_3"].configure(
				name  = "Orion.SubSystem23.DelayKeepAliveReceiveTimeout_3",
				inport=self.detmodel.getOrion().getSubSystem23().getOrionSystem().getTimoeutKeepAliveReceiveTimeout_3_req(),
				calls = {'TimoeutKeepAliveReceiveTimeout_3' : [self.detmodel.getOrion().getSubSystem23().getOrionSystem().getTimoeutKeepAliveReceiveTimeout_3(), ]},
				rules = {'TimoeutKeepAliveReceiveTimeout_3' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_3154")}},
				simulator=self)
				
		self.components["Orion.SubSystem23.DelayKapcsolodik_2"].configure(
				name  = "Orion.SubSystem23.DelayKapcsolodik_2",
				inport=self.detmodel.getOrion().getSubSystem23().getOrionSystem().getTimeoutKapcsolodik_2_req(),
				calls = {'TimeoutKapcsolodik_2' : [self.detmodel.getOrion().getSubSystem23().getOrionSystem().getTimeoutKapcsolodik_2(), ]},
				rules = {'TimeoutKapcsolodik_2' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_2155")}},
				simulator=self)
				
		self.components["Orion.SubSystem23.DelayZarva_0"].configure(
				name  = "Orion.SubSystem23.DelayZarva_0",
				inport=self.detmodel.getOrion().getSubSystem23().getOrionSystem().getTimeoutZarva_0_req(),
				calls = {'TimeoutZarva_0' : [self.detmodel.getOrion().getSubSystem23().getOrionSystem().getTimeoutZarva_0(), ]},
				rules = {'TimeoutZarva_0' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayZarva_0156")}},
				simulator=self)
				
		self.components["Orion.SubSystem23.DelayKeepAliveSendTimeout_1"].configure(
				name  = "Orion.SubSystem23.DelayKeepAliveSendTimeout_1",
				inport=self.detmodel.getOrion().getSubSystem23().getOrionSystem().getTimeoutKeepAliveSendTimeout_1_req(),
				calls = {'TimeoutKeepAliveSendTimeout_1' : [self.detmodel.getOrion().getSubSystem23().getOrionSystem().getTimeoutKeepAliveSendTimeout_1(), ]},
				rules = {'TimeoutKeepAliveSendTimeout_1' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_1157")}},
				simulator=self)
				
		self.components["Orion.SubSystem23.DelayKeepAliveReceiveTimeout_4"].configure(
				name  = "Orion.SubSystem23.DelayKeepAliveReceiveTimeout_4",
				inport=self.detmodel.getOrion().getSubSystem23().getOrionSystem().getTimeoutKeepAliveReceiveTimeout_4_req(),
				calls = {'TimeoutKeepAliveReceiveTimeout_4' : [self.detmodel.getOrion().getSubSystem23().getOrionSystem().getTimeoutKeepAliveReceiveTimeout_4(), ]},
				rules = {'TimeoutKeepAliveReceiveTimeout_4' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_4158")}},
				simulator=self)
				
		self.components["Orion.SubSystem23.DelayKapcsolodik_3"].configure(
				name  = "Orion.SubSystem23.DelayKapcsolodik_3",
				inport=self.detmodel.getOrion().getSubSystem23().getOrionSystem().getTimeoutKapcsolodik_3_req(),
				calls = {'TimeoutKapcsolodik_3' : [self.detmodel.getOrion().getSubSystem23().getOrionSystem().getTimeoutKapcsolodik_3(), ]},
				rules = {'TimeoutKapcsolodik_3' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_3159")}},
				simulator=self)
				
		self.components["Orion.SubSystem23.DelayKeepAliveSendTimeout_0"].configure(
				name  = "Orion.SubSystem23.DelayKeepAliveSendTimeout_0",
				inport=self.detmodel.getOrion().getSubSystem23().getOrionSystem().getTimeoutKeepAliveSendTimeout_0_req(),
				calls = {'TimeoutKeepAliveSendTimeout_0' : [self.detmodel.getOrion().getSubSystem23().getOrionSystem().getTimeoutKeepAliveSendTimeout_0(), ]},
				rules = {'TimeoutKeepAliveSendTimeout_0' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_0160")}},
				simulator=self)
				
		self.components["Orion.SubSystem24.DelayKeepAliveReceiveTimeout_3"].configure(
				name  = "Orion.SubSystem24.DelayKeepAliveReceiveTimeout_3",
				inport=self.detmodel.getOrion().getSubSystem24().getOrionSystem().getTimoeutKeepAliveReceiveTimeout_3_req(),
				calls = {'TimoeutKeepAliveReceiveTimeout_3' : [self.detmodel.getOrion().getSubSystem24().getOrionSystem().getTimoeutKeepAliveReceiveTimeout_3(), ]},
				rules = {'TimoeutKeepAliveReceiveTimeout_3' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_3161")}},
				simulator=self)
				
		self.components["Orion.SubSystem24.DelayKapcsolodik_2"].configure(
				name  = "Orion.SubSystem24.DelayKapcsolodik_2",
				inport=self.detmodel.getOrion().getSubSystem24().getOrionSystem().getTimeoutKapcsolodik_2_req(),
				calls = {'TimeoutKapcsolodik_2' : [self.detmodel.getOrion().getSubSystem24().getOrionSystem().getTimeoutKapcsolodik_2(), ]},
				rules = {'TimeoutKapcsolodik_2' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_2162")}},
				simulator=self)
				
		self.components["Orion.SubSystem24.DelayZarva_0"].configure(
				name  = "Orion.SubSystem24.DelayZarva_0",
				inport=self.detmodel.getOrion().getSubSystem24().getOrionSystem().getTimeoutZarva_0_req(),
				calls = {'TimeoutZarva_0' : [self.detmodel.getOrion().getSubSystem24().getOrionSystem().getTimeoutZarva_0(), ]},
				rules = {'TimeoutZarva_0' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayZarva_0163")}},
				simulator=self)
				
		self.components["Orion.SubSystem24.DelayKeepAliveSendTimeout_1"].configure(
				name  = "Orion.SubSystem24.DelayKeepAliveSendTimeout_1",
				inport=self.detmodel.getOrion().getSubSystem24().getOrionSystem().getTimeoutKeepAliveSendTimeout_1_req(),
				calls = {'TimeoutKeepAliveSendTimeout_1' : [self.detmodel.getOrion().getSubSystem24().getOrionSystem().getTimeoutKeepAliveSendTimeout_1(), ]},
				rules = {'TimeoutKeepAliveSendTimeout_1' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_1164")}},
				simulator=self)
				
		self.components["Orion.SubSystem24.DelayKeepAliveReceiveTimeout_4"].configure(
				name  = "Orion.SubSystem24.DelayKeepAliveReceiveTimeout_4",
				inport=self.detmodel.getOrion().getSubSystem24().getOrionSystem().getTimeoutKeepAliveReceiveTimeout_4_req(),
				calls = {'TimeoutKeepAliveReceiveTimeout_4' : [self.detmodel.getOrion().getSubSystem24().getOrionSystem().getTimeoutKeepAliveReceiveTimeout_4(), ]},
				rules = {'TimeoutKeepAliveReceiveTimeout_4' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_4165")}},
				simulator=self)
				
		self.components["Orion.SubSystem24.DelayKapcsolodik_3"].configure(
				name  = "Orion.SubSystem24.DelayKapcsolodik_3",
				inport=self.detmodel.getOrion().getSubSystem24().getOrionSystem().getTimeoutKapcsolodik_3_req(),
				calls = {'TimeoutKapcsolodik_3' : [self.detmodel.getOrion().getSubSystem24().getOrionSystem().getTimeoutKapcsolodik_3(), ]},
				rules = {'TimeoutKapcsolodik_3' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_3166")}},
				simulator=self)
				
		self.components["Orion.SubSystem24.DelayKeepAliveSendTimeout_0"].configure(
				name  = "Orion.SubSystem24.DelayKeepAliveSendTimeout_0",
				inport=self.detmodel.getOrion().getSubSystem24().getOrionSystem().getTimeoutKeepAliveSendTimeout_0_req(),
				calls = {'TimeoutKeepAliveSendTimeout_0' : [self.detmodel.getOrion().getSubSystem24().getOrionSystem().getTimeoutKeepAliveSendTimeout_0(), ]},
				rules = {'TimeoutKeepAliveSendTimeout_0' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_0167")}},
				simulator=self)
				
		self.components["Orion.SubSystem25.DelayKeepAliveReceiveTimeout_3"].configure(
				name  = "Orion.SubSystem25.DelayKeepAliveReceiveTimeout_3",
				inport=self.detmodel.getOrion().getSubSystem25().getOrionSystem().getTimoeutKeepAliveReceiveTimeout_3_req(),
				calls = {'TimoeutKeepAliveReceiveTimeout_3' : [self.detmodel.getOrion().getSubSystem25().getOrionSystem().getTimoeutKeepAliveReceiveTimeout_3(), ]},
				rules = {'TimoeutKeepAliveReceiveTimeout_3' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_3168")}},
				simulator=self)
				
		self.components["Orion.SubSystem25.DelayKapcsolodik_2"].configure(
				name  = "Orion.SubSystem25.DelayKapcsolodik_2",
				inport=self.detmodel.getOrion().getSubSystem25().getOrionSystem().getTimeoutKapcsolodik_2_req(),
				calls = {'TimeoutKapcsolodik_2' : [self.detmodel.getOrion().getSubSystem25().getOrionSystem().getTimeoutKapcsolodik_2(), ]},
				rules = {'TimeoutKapcsolodik_2' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_2169")}},
				simulator=self)
				
		self.components["Orion.SubSystem25.DelayZarva_0"].configure(
				name  = "Orion.SubSystem25.DelayZarva_0",
				inport=self.detmodel.getOrion().getSubSystem25().getOrionSystem().getTimeoutZarva_0_req(),
				calls = {'TimeoutZarva_0' : [self.detmodel.getOrion().getSubSystem25().getOrionSystem().getTimeoutZarva_0(), ]},
				rules = {'TimeoutZarva_0' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayZarva_0170")}},
				simulator=self)
				
		self.components["Orion.SubSystem25.DelayKeepAliveSendTimeout_1"].configure(
				name  = "Orion.SubSystem25.DelayKeepAliveSendTimeout_1",
				inport=self.detmodel.getOrion().getSubSystem25().getOrionSystem().getTimeoutKeepAliveSendTimeout_1_req(),
				calls = {'TimeoutKeepAliveSendTimeout_1' : [self.detmodel.getOrion().getSubSystem25().getOrionSystem().getTimeoutKeepAliveSendTimeout_1(), ]},
				rules = {'TimeoutKeepAliveSendTimeout_1' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_1171")}},
				simulator=self)
				
		self.components["Orion.SubSystem25.DelayKeepAliveReceiveTimeout_4"].configure(
				name  = "Orion.SubSystem25.DelayKeepAliveReceiveTimeout_4",
				inport=self.detmodel.getOrion().getSubSystem25().getOrionSystem().getTimeoutKeepAliveReceiveTimeout_4_req(),
				calls = {'TimeoutKeepAliveReceiveTimeout_4' : [self.detmodel.getOrion().getSubSystem25().getOrionSystem().getTimeoutKeepAliveReceiveTimeout_4(), ]},
				rules = {'TimeoutKeepAliveReceiveTimeout_4' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_4172")}},
				simulator=self)
				
		self.components["Orion.SubSystem25.DelayKapcsolodik_3"].configure(
				name  = "Orion.SubSystem25.DelayKapcsolodik_3",
				inport=self.detmodel.getOrion().getSubSystem25().getOrionSystem().getTimeoutKapcsolodik_3_req(),
				calls = {'TimeoutKapcsolodik_3' : [self.detmodel.getOrion().getSubSystem25().getOrionSystem().getTimeoutKapcsolodik_3(), ]},
				rules = {'TimeoutKapcsolodik_3' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_3173")}},
				simulator=self)
				
		self.components["Orion.SubSystem25.DelayKeepAliveSendTimeout_0"].configure(
				name  = "Orion.SubSystem25.DelayKeepAliveSendTimeout_0",
				inport=self.detmodel.getOrion().getSubSystem25().getOrionSystem().getTimeoutKeepAliveSendTimeout_0_req(),
				calls = {'TimeoutKeepAliveSendTimeout_0' : [self.detmodel.getOrion().getSubSystem25().getOrionSystem().getTimeoutKeepAliveSendTimeout_0(), ]},
				rules = {'TimeoutKeepAliveSendTimeout_0' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_0174")}},
				simulator=self)
				
		self.components["Orion.SubSystem26.DelayKeepAliveReceiveTimeout_3"].configure(
				name  = "Orion.SubSystem26.DelayKeepAliveReceiveTimeout_3",
				inport=self.detmodel.getOrion().getSubSystem26().getOrionSystem().getTimoeutKeepAliveReceiveTimeout_3_req(),
				calls = {'TimoeutKeepAliveReceiveTimeout_3' : [self.detmodel.getOrion().getSubSystem26().getOrionSystem().getTimoeutKeepAliveReceiveTimeout_3(), ]},
				rules = {'TimoeutKeepAliveReceiveTimeout_3' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_3175")}},
				simulator=self)
				
		self.components["Orion.SubSystem26.DelayKapcsolodik_2"].configure(
				name  = "Orion.SubSystem26.DelayKapcsolodik_2",
				inport=self.detmodel.getOrion().getSubSystem26().getOrionSystem().getTimeoutKapcsolodik_2_req(),
				calls = {'TimeoutKapcsolodik_2' : [self.detmodel.getOrion().getSubSystem26().getOrionSystem().getTimeoutKapcsolodik_2(), ]},
				rules = {'TimeoutKapcsolodik_2' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_2176")}},
				simulator=self)
				
		self.components["Orion.SubSystem26.DelayZarva_0"].configure(
				name  = "Orion.SubSystem26.DelayZarva_0",
				inport=self.detmodel.getOrion().getSubSystem26().getOrionSystem().getTimeoutZarva_0_req(),
				calls = {'TimeoutZarva_0' : [self.detmodel.getOrion().getSubSystem26().getOrionSystem().getTimeoutZarva_0(), ]},
				rules = {'TimeoutZarva_0' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayZarva_0177")}},
				simulator=self)
				
		self.components["Orion.SubSystem26.DelayKeepAliveSendTimeout_1"].configure(
				name  = "Orion.SubSystem26.DelayKeepAliveSendTimeout_1",
				inport=self.detmodel.getOrion().getSubSystem26().getOrionSystem().getTimeoutKeepAliveSendTimeout_1_req(),
				calls = {'TimeoutKeepAliveSendTimeout_1' : [self.detmodel.getOrion().getSubSystem26().getOrionSystem().getTimeoutKeepAliveSendTimeout_1(), ]},
				rules = {'TimeoutKeepAliveSendTimeout_1' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_1178")}},
				simulator=self)
				
		self.components["Orion.SubSystem26.DelayKeepAliveReceiveTimeout_4"].configure(
				name  = "Orion.SubSystem26.DelayKeepAliveReceiveTimeout_4",
				inport=self.detmodel.getOrion().getSubSystem26().getOrionSystem().getTimeoutKeepAliveReceiveTimeout_4_req(),
				calls = {'TimeoutKeepAliveReceiveTimeout_4' : [self.detmodel.getOrion().getSubSystem26().getOrionSystem().getTimeoutKeepAliveReceiveTimeout_4(), ]},
				rules = {'TimeoutKeepAliveReceiveTimeout_4' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_4179")}},
				simulator=self)
				
		self.components["Orion.SubSystem26.DelayKapcsolodik_3"].configure(
				name  = "Orion.SubSystem26.DelayKapcsolodik_3",
				inport=self.detmodel.getOrion().getSubSystem26().getOrionSystem().getTimeoutKapcsolodik_3_req(),
				calls = {'TimeoutKapcsolodik_3' : [self.detmodel.getOrion().getSubSystem26().getOrionSystem().getTimeoutKapcsolodik_3(), ]},
				rules = {'TimeoutKapcsolodik_3' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_3180")}},
				simulator=self)
				
		self.components["Orion.SubSystem26.DelayKeepAliveSendTimeout_0"].configure(
				name  = "Orion.SubSystem26.DelayKeepAliveSendTimeout_0",
				inport=self.detmodel.getOrion().getSubSystem26().getOrionSystem().getTimeoutKeepAliveSendTimeout_0_req(),
				calls = {'TimeoutKeepAliveSendTimeout_0' : [self.detmodel.getOrion().getSubSystem26().getOrionSystem().getTimeoutKeepAliveSendTimeout_0(), ]},
				rules = {'TimeoutKeepAliveSendTimeout_0' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_0181")}},
				simulator=self)
				
		self.components["Orion.SubSystem27.DelayKeepAliveReceiveTimeout_3"].configure(
				name  = "Orion.SubSystem27.DelayKeepAliveReceiveTimeout_3",
				inport=self.detmodel.getOrion().getSubSystem27().getOrionSystem().getTimoeutKeepAliveReceiveTimeout_3_req(),
				calls = {'TimoeutKeepAliveReceiveTimeout_3' : [self.detmodel.getOrion().getSubSystem27().getOrionSystem().getTimoeutKeepAliveReceiveTimeout_3(), ]},
				rules = {'TimoeutKeepAliveReceiveTimeout_3' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_3182")}},
				simulator=self)
				
		self.components["Orion.SubSystem27.DelayKapcsolodik_2"].configure(
				name  = "Orion.SubSystem27.DelayKapcsolodik_2",
				inport=self.detmodel.getOrion().getSubSystem27().getOrionSystem().getTimeoutKapcsolodik_2_req(),
				calls = {'TimeoutKapcsolodik_2' : [self.detmodel.getOrion().getSubSystem27().getOrionSystem().getTimeoutKapcsolodik_2(), ]},
				rules = {'TimeoutKapcsolodik_2' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_2183")}},
				simulator=self)
				
		self.components["Orion.SubSystem27.DelayZarva_0"].configure(
				name  = "Orion.SubSystem27.DelayZarva_0",
				inport=self.detmodel.getOrion().getSubSystem27().getOrionSystem().getTimeoutZarva_0_req(),
				calls = {'TimeoutZarva_0' : [self.detmodel.getOrion().getSubSystem27().getOrionSystem().getTimeoutZarva_0(), ]},
				rules = {'TimeoutZarva_0' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayZarva_0184")}},
				simulator=self)
				
		self.components["Orion.SubSystem27.DelayKeepAliveSendTimeout_1"].configure(
				name  = "Orion.SubSystem27.DelayKeepAliveSendTimeout_1",
				inport=self.detmodel.getOrion().getSubSystem27().getOrionSystem().getTimeoutKeepAliveSendTimeout_1_req(),
				calls = {'TimeoutKeepAliveSendTimeout_1' : [self.detmodel.getOrion().getSubSystem27().getOrionSystem().getTimeoutKeepAliveSendTimeout_1(), ]},
				rules = {'TimeoutKeepAliveSendTimeout_1' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_1185")}},
				simulator=self)
				
		self.components["Orion.SubSystem27.DelayKeepAliveReceiveTimeout_4"].configure(
				name  = "Orion.SubSystem27.DelayKeepAliveReceiveTimeout_4",
				inport=self.detmodel.getOrion().getSubSystem27().getOrionSystem().getTimeoutKeepAliveReceiveTimeout_4_req(),
				calls = {'TimeoutKeepAliveReceiveTimeout_4' : [self.detmodel.getOrion().getSubSystem27().getOrionSystem().getTimeoutKeepAliveReceiveTimeout_4(), ]},
				rules = {'TimeoutKeepAliveReceiveTimeout_4' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_4186")}},
				simulator=self)
				
		self.components["Orion.SubSystem27.DelayKapcsolodik_3"].configure(
				name  = "Orion.SubSystem27.DelayKapcsolodik_3",
				inport=self.detmodel.getOrion().getSubSystem27().getOrionSystem().getTimeoutKapcsolodik_3_req(),
				calls = {'TimeoutKapcsolodik_3' : [self.detmodel.getOrion().getSubSystem27().getOrionSystem().getTimeoutKapcsolodik_3(), ]},
				rules = {'TimeoutKapcsolodik_3' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_3187")}},
				simulator=self)
				
		self.components["Orion.SubSystem27.DelayKeepAliveSendTimeout_0"].configure(
				name  = "Orion.SubSystem27.DelayKeepAliveSendTimeout_0",
				inport=self.detmodel.getOrion().getSubSystem27().getOrionSystem().getTimeoutKeepAliveSendTimeout_0_req(),
				calls = {'TimeoutKeepAliveSendTimeout_0' : [self.detmodel.getOrion().getSubSystem27().getOrionSystem().getTimeoutKeepAliveSendTimeout_0(), ]},
				rules = {'TimeoutKeepAliveSendTimeout_0' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_0188")}},
				simulator=self)
				
		self.components["Orion.SubSystem28.DelayKeepAliveReceiveTimeout_3"].configure(
				name  = "Orion.SubSystem28.DelayKeepAliveReceiveTimeout_3",
				inport=self.detmodel.getOrion().getSubSystem28().getOrionSystem().getTimoeutKeepAliveReceiveTimeout_3_req(),
				calls = {'TimoeutKeepAliveReceiveTimeout_3' : [self.detmodel.getOrion().getSubSystem28().getOrionSystem().getTimoeutKeepAliveReceiveTimeout_3(), ]},
				rules = {'TimoeutKeepAliveReceiveTimeout_3' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_3189")}},
				simulator=self)
				
		self.components["Orion.SubSystem28.DelayKapcsolodik_2"].configure(
				name  = "Orion.SubSystem28.DelayKapcsolodik_2",
				inport=self.detmodel.getOrion().getSubSystem28().getOrionSystem().getTimeoutKapcsolodik_2_req(),
				calls = {'TimeoutKapcsolodik_2' : [self.detmodel.getOrion().getSubSystem28().getOrionSystem().getTimeoutKapcsolodik_2(), ]},
				rules = {'TimeoutKapcsolodik_2' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_2190")}},
				simulator=self)
				
		self.components["Orion.SubSystem28.DelayZarva_0"].configure(
				name  = "Orion.SubSystem28.DelayZarva_0",
				inport=self.detmodel.getOrion().getSubSystem28().getOrionSystem().getTimeoutZarva_0_req(),
				calls = {'TimeoutZarva_0' : [self.detmodel.getOrion().getSubSystem28().getOrionSystem().getTimeoutZarva_0(), ]},
				rules = {'TimeoutZarva_0' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayZarva_0191")}},
				simulator=self)
				
		self.components["Orion.SubSystem28.DelayKeepAliveSendTimeout_1"].configure(
				name  = "Orion.SubSystem28.DelayKeepAliveSendTimeout_1",
				inport=self.detmodel.getOrion().getSubSystem28().getOrionSystem().getTimeoutKeepAliveSendTimeout_1_req(),
				calls = {'TimeoutKeepAliveSendTimeout_1' : [self.detmodel.getOrion().getSubSystem28().getOrionSystem().getTimeoutKeepAliveSendTimeout_1(), ]},
				rules = {'TimeoutKeepAliveSendTimeout_1' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_1192")}},
				simulator=self)
				
		self.components["Orion.SubSystem28.DelayKeepAliveReceiveTimeout_4"].configure(
				name  = "Orion.SubSystem28.DelayKeepAliveReceiveTimeout_4",
				inport=self.detmodel.getOrion().getSubSystem28().getOrionSystem().getTimeoutKeepAliveReceiveTimeout_4_req(),
				calls = {'TimeoutKeepAliveReceiveTimeout_4' : [self.detmodel.getOrion().getSubSystem28().getOrionSystem().getTimeoutKeepAliveReceiveTimeout_4(), ]},
				rules = {'TimeoutKeepAliveReceiveTimeout_4' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_4193")}},
				simulator=self)
				
		self.components["Orion.SubSystem28.DelayKapcsolodik_3"].configure(
				name  = "Orion.SubSystem28.DelayKapcsolodik_3",
				inport=self.detmodel.getOrion().getSubSystem28().getOrionSystem().getTimeoutKapcsolodik_3_req(),
				calls = {'TimeoutKapcsolodik_3' : [self.detmodel.getOrion().getSubSystem28().getOrionSystem().getTimeoutKapcsolodik_3(), ]},
				rules = {'TimeoutKapcsolodik_3' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_3194")}},
				simulator=self)
				
		self.components["Orion.SubSystem28.DelayKeepAliveSendTimeout_0"].configure(
				name  = "Orion.SubSystem28.DelayKeepAliveSendTimeout_0",
				inport=self.detmodel.getOrion().getSubSystem28().getOrionSystem().getTimeoutKeepAliveSendTimeout_0_req(),
				calls = {'TimeoutKeepAliveSendTimeout_0' : [self.detmodel.getOrion().getSubSystem28().getOrionSystem().getTimeoutKeepAliveSendTimeout_0(), ]},
				rules = {'TimeoutKeepAliveSendTimeout_0' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_0195")}},
				simulator=self)
				
		self.components["Orion.SubSystem29.DelayKeepAliveReceiveTimeout_3"].configure(
				name  = "Orion.SubSystem29.DelayKeepAliveReceiveTimeout_3",
				inport=self.detmodel.getOrion().getSubSystem29().getOrionSystem().getTimoeutKeepAliveReceiveTimeout_3_req(),
				calls = {'TimoeutKeepAliveReceiveTimeout_3' : [self.detmodel.getOrion().getSubSystem29().getOrionSystem().getTimoeutKeepAliveReceiveTimeout_3(), ]},
				rules = {'TimoeutKeepAliveReceiveTimeout_3' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_3196")}},
				simulator=self)
				
		self.components["Orion.SubSystem29.DelayKapcsolodik_2"].configure(
				name  = "Orion.SubSystem29.DelayKapcsolodik_2",
				inport=self.detmodel.getOrion().getSubSystem29().getOrionSystem().getTimeoutKapcsolodik_2_req(),
				calls = {'TimeoutKapcsolodik_2' : [self.detmodel.getOrion().getSubSystem29().getOrionSystem().getTimeoutKapcsolodik_2(), ]},
				rules = {'TimeoutKapcsolodik_2' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_2197")}},
				simulator=self)
				
		self.components["Orion.SubSystem29.DelayZarva_0"].configure(
				name  = "Orion.SubSystem29.DelayZarva_0",
				inport=self.detmodel.getOrion().getSubSystem29().getOrionSystem().getTimeoutZarva_0_req(),
				calls = {'TimeoutZarva_0' : [self.detmodel.getOrion().getSubSystem29().getOrionSystem().getTimeoutZarva_0(), ]},
				rules = {'TimeoutZarva_0' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayZarva_0198")}},
				simulator=self)
				
		self.components["Orion.SubSystem29.DelayKeepAliveSendTimeout_1"].configure(
				name  = "Orion.SubSystem29.DelayKeepAliveSendTimeout_1",
				inport=self.detmodel.getOrion().getSubSystem29().getOrionSystem().getTimeoutKeepAliveSendTimeout_1_req(),
				calls = {'TimeoutKeepAliveSendTimeout_1' : [self.detmodel.getOrion().getSubSystem29().getOrionSystem().getTimeoutKeepAliveSendTimeout_1(), ]},
				rules = {'TimeoutKeepAliveSendTimeout_1' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_1199")}},
				simulator=self)
				
		self.components["Orion.SubSystem29.DelayKeepAliveReceiveTimeout_4"].configure(
				name  = "Orion.SubSystem29.DelayKeepAliveReceiveTimeout_4",
				inport=self.detmodel.getOrion().getSubSystem29().getOrionSystem().getTimeoutKeepAliveReceiveTimeout_4_req(),
				calls = {'TimeoutKeepAliveReceiveTimeout_4' : [self.detmodel.getOrion().getSubSystem29().getOrionSystem().getTimeoutKeepAliveReceiveTimeout_4(), ]},
				rules = {'TimeoutKeepAliveReceiveTimeout_4' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_4200")}},
				simulator=self)
				
		self.components["Orion.SubSystem29.DelayKapcsolodik_3"].configure(
				name  = "Orion.SubSystem29.DelayKapcsolodik_3",
				inport=self.detmodel.getOrion().getSubSystem29().getOrionSystem().getTimeoutKapcsolodik_3_req(),
				calls = {'TimeoutKapcsolodik_3' : [self.detmodel.getOrion().getSubSystem29().getOrionSystem().getTimeoutKapcsolodik_3(), ]},
				rules = {'TimeoutKapcsolodik_3' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_3201")}},
				simulator=self)
				
		self.components["Orion.SubSystem29.DelayKeepAliveSendTimeout_0"].configure(
				name  = "Orion.SubSystem29.DelayKeepAliveSendTimeout_0",
				inport=self.detmodel.getOrion().getSubSystem29().getOrionSystem().getTimeoutKeepAliveSendTimeout_0_req(),
				calls = {'TimeoutKeepAliveSendTimeout_0' : [self.detmodel.getOrion().getSubSystem29().getOrionSystem().getTimeoutKeepAliveSendTimeout_0(), ]},
				rules = {'TimeoutKeepAliveSendTimeout_0' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_0202")}},
				simulator=self)
				
		self.components["Orion.SubSystem30.DelayKeepAliveReceiveTimeout_3"].configure(
				name  = "Orion.SubSystem30.DelayKeepAliveReceiveTimeout_3",
				inport=self.detmodel.getOrion().getSubSystem30().getOrionSystem().getTimoeutKeepAliveReceiveTimeout_3_req(),
				calls = {'TimoeutKeepAliveReceiveTimeout_3' : [self.detmodel.getOrion().getSubSystem30().getOrionSystem().getTimoeutKeepAliveReceiveTimeout_3(), ]},
				rules = {'TimoeutKeepAliveReceiveTimeout_3' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_3203")}},
				simulator=self)
				
		self.components["Orion.SubSystem30.DelayKapcsolodik_2"].configure(
				name  = "Orion.SubSystem30.DelayKapcsolodik_2",
				inport=self.detmodel.getOrion().getSubSystem30().getOrionSystem().getTimeoutKapcsolodik_2_req(),
				calls = {'TimeoutKapcsolodik_2' : [self.detmodel.getOrion().getSubSystem30().getOrionSystem().getTimeoutKapcsolodik_2(), ]},
				rules = {'TimeoutKapcsolodik_2' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_2204")}},
				simulator=self)
				
		self.components["Orion.SubSystem30.DelayZarva_0"].configure(
				name  = "Orion.SubSystem30.DelayZarva_0",
				inport=self.detmodel.getOrion().getSubSystem30().getOrionSystem().getTimeoutZarva_0_req(),
				calls = {'TimeoutZarva_0' : [self.detmodel.getOrion().getSubSystem30().getOrionSystem().getTimeoutZarva_0(), ]},
				rules = {'TimeoutZarva_0' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayZarva_0205")}},
				simulator=self)
				
		self.components["Orion.SubSystem30.DelayKeepAliveSendTimeout_1"].configure(
				name  = "Orion.SubSystem30.DelayKeepAliveSendTimeout_1",
				inport=self.detmodel.getOrion().getSubSystem30().getOrionSystem().getTimeoutKeepAliveSendTimeout_1_req(),
				calls = {'TimeoutKeepAliveSendTimeout_1' : [self.detmodel.getOrion().getSubSystem30().getOrionSystem().getTimeoutKeepAliveSendTimeout_1(), ]},
				rules = {'TimeoutKeepAliveSendTimeout_1' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_1206")}},
				simulator=self)
				
		self.components["Orion.SubSystem30.DelayKeepAliveReceiveTimeout_4"].configure(
				name  = "Orion.SubSystem30.DelayKeepAliveReceiveTimeout_4",
				inport=self.detmodel.getOrion().getSubSystem30().getOrionSystem().getTimeoutKeepAliveReceiveTimeout_4_req(),
				calls = {'TimeoutKeepAliveReceiveTimeout_4' : [self.detmodel.getOrion().getSubSystem30().getOrionSystem().getTimeoutKeepAliveReceiveTimeout_4(), ]},
				rules = {'TimeoutKeepAliveReceiveTimeout_4' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_4207")}},
				simulator=self)
				
		self.components["Orion.SubSystem30.DelayKapcsolodik_3"].configure(
				name  = "Orion.SubSystem30.DelayKapcsolodik_3",
				inport=self.detmodel.getOrion().getSubSystem30().getOrionSystem().getTimeoutKapcsolodik_3_req(),
				calls = {'TimeoutKapcsolodik_3' : [self.detmodel.getOrion().getSubSystem30().getOrionSystem().getTimeoutKapcsolodik_3(), ]},
				rules = {'TimeoutKapcsolodik_3' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_3208")}},
				simulator=self)
				
		self.components["Orion.SubSystem30.DelayKeepAliveSendTimeout_0"].configure(
				name  = "Orion.SubSystem30.DelayKeepAliveSendTimeout_0",
				inport=self.detmodel.getOrion().getSubSystem30().getOrionSystem().getTimeoutKeepAliveSendTimeout_0_req(),
				calls = {'TimeoutKeepAliveSendTimeout_0' : [self.detmodel.getOrion().getSubSystem30().getOrionSystem().getTimeoutKeepAliveSendTimeout_0(), ]},
				rules = {'TimeoutKeepAliveSendTimeout_0' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_0209")}},
				simulator=self)
				
		self.components["Orion.SubSystem31.DelayKeepAliveReceiveTimeout_3"].configure(
				name  = "Orion.SubSystem31.DelayKeepAliveReceiveTimeout_3",
				inport=self.detmodel.getOrion().getSubSystem31().getOrionSystem().getTimoeutKeepAliveReceiveTimeout_3_req(),
				calls = {'TimoeutKeepAliveReceiveTimeout_3' : [self.detmodel.getOrion().getSubSystem31().getOrionSystem().getTimoeutKeepAliveReceiveTimeout_3(), ]},
				rules = {'TimoeutKeepAliveReceiveTimeout_3' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_3210")}},
				simulator=self)
				
		self.components["Orion.SubSystem31.DelayKapcsolodik_2"].configure(
				name  = "Orion.SubSystem31.DelayKapcsolodik_2",
				inport=self.detmodel.getOrion().getSubSystem31().getOrionSystem().getTimeoutKapcsolodik_2_req(),
				calls = {'TimeoutKapcsolodik_2' : [self.detmodel.getOrion().getSubSystem31().getOrionSystem().getTimeoutKapcsolodik_2(), ]},
				rules = {'TimeoutKapcsolodik_2' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_2211")}},
				simulator=self)
				
		self.components["Orion.SubSystem31.DelayZarva_0"].configure(
				name  = "Orion.SubSystem31.DelayZarva_0",
				inport=self.detmodel.getOrion().getSubSystem31().getOrionSystem().getTimeoutZarva_0_req(),
				calls = {'TimeoutZarva_0' : [self.detmodel.getOrion().getSubSystem31().getOrionSystem().getTimeoutZarva_0(), ]},
				rules = {'TimeoutZarva_0' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayZarva_0212")}},
				simulator=self)
				
		self.components["Orion.SubSystem31.DelayKeepAliveSendTimeout_1"].configure(
				name  = "Orion.SubSystem31.DelayKeepAliveSendTimeout_1",
				inport=self.detmodel.getOrion().getSubSystem31().getOrionSystem().getTimeoutKeepAliveSendTimeout_1_req(),
				calls = {'TimeoutKeepAliveSendTimeout_1' : [self.detmodel.getOrion().getSubSystem31().getOrionSystem().getTimeoutKeepAliveSendTimeout_1(), ]},
				rules = {'TimeoutKeepAliveSendTimeout_1' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_1213")}},
				simulator=self)
				
		self.components["Orion.SubSystem31.DelayKeepAliveReceiveTimeout_4"].configure(
				name  = "Orion.SubSystem31.DelayKeepAliveReceiveTimeout_4",
				inport=self.detmodel.getOrion().getSubSystem31().getOrionSystem().getTimeoutKeepAliveReceiveTimeout_4_req(),
				calls = {'TimeoutKeepAliveReceiveTimeout_4' : [self.detmodel.getOrion().getSubSystem31().getOrionSystem().getTimeoutKeepAliveReceiveTimeout_4(), ]},
				rules = {'TimeoutKeepAliveReceiveTimeout_4' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_4214")}},
				simulator=self)
				
		self.components["Orion.SubSystem31.DelayKapcsolodik_3"].configure(
				name  = "Orion.SubSystem31.DelayKapcsolodik_3",
				inport=self.detmodel.getOrion().getSubSystem31().getOrionSystem().getTimeoutKapcsolodik_3_req(),
				calls = {'TimeoutKapcsolodik_3' : [self.detmodel.getOrion().getSubSystem31().getOrionSystem().getTimeoutKapcsolodik_3(), ]},
				rules = {'TimeoutKapcsolodik_3' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_3215")}},
				simulator=self)
				
		self.components["Orion.SubSystem31.DelayKeepAliveSendTimeout_0"].configure(
				name  = "Orion.SubSystem31.DelayKeepAliveSendTimeout_0",
				inport=self.detmodel.getOrion().getSubSystem31().getOrionSystem().getTimeoutKeepAliveSendTimeout_0_req(),
				calls = {'TimeoutKeepAliveSendTimeout_0' : [self.detmodel.getOrion().getSubSystem31().getOrionSystem().getTimeoutKeepAliveSendTimeout_0(), ]},
				rules = {'TimeoutKeepAliveSendTimeout_0' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_0216")}},
				simulator=self)
				
		self.components["Orion.SubSystem32.DelayKeepAliveReceiveTimeout_3"].configure(
				name  = "Orion.SubSystem32.DelayKeepAliveReceiveTimeout_3",
				inport=self.detmodel.getOrion().getSubSystem32().getOrionSystem().getTimoeutKeepAliveReceiveTimeout_3_req(),
				calls = {'TimoeutKeepAliveReceiveTimeout_3' : [self.detmodel.getOrion().getSubSystem32().getOrionSystem().getTimoeutKeepAliveReceiveTimeout_3(), ]},
				rules = {'TimoeutKeepAliveReceiveTimeout_3' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_3217")}},
				simulator=self)
				
		self.components["Orion.SubSystem32.DelayKapcsolodik_2"].configure(
				name  = "Orion.SubSystem32.DelayKapcsolodik_2",
				inport=self.detmodel.getOrion().getSubSystem32().getOrionSystem().getTimeoutKapcsolodik_2_req(),
				calls = {'TimeoutKapcsolodik_2' : [self.detmodel.getOrion().getSubSystem32().getOrionSystem().getTimeoutKapcsolodik_2(), ]},
				rules = {'TimeoutKapcsolodik_2' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_2218")}},
				simulator=self)
				
		self.components["Orion.SubSystem32.DelayZarva_0"].configure(
				name  = "Orion.SubSystem32.DelayZarva_0",
				inport=self.detmodel.getOrion().getSubSystem32().getOrionSystem().getTimeoutZarva_0_req(),
				calls = {'TimeoutZarva_0' : [self.detmodel.getOrion().getSubSystem32().getOrionSystem().getTimeoutZarva_0(), ]},
				rules = {'TimeoutZarva_0' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayZarva_0219")}},
				simulator=self)
				
		self.components["Orion.SubSystem32.DelayKeepAliveSendTimeout_1"].configure(
				name  = "Orion.SubSystem32.DelayKeepAliveSendTimeout_1",
				inport=self.detmodel.getOrion().getSubSystem32().getOrionSystem().getTimeoutKeepAliveSendTimeout_1_req(),
				calls = {'TimeoutKeepAliveSendTimeout_1' : [self.detmodel.getOrion().getSubSystem32().getOrionSystem().getTimeoutKeepAliveSendTimeout_1(), ]},
				rules = {'TimeoutKeepAliveSendTimeout_1' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_1220")}},
				simulator=self)
				
		self.components["Orion.SubSystem32.DelayKeepAliveReceiveTimeout_4"].configure(
				name  = "Orion.SubSystem32.DelayKeepAliveReceiveTimeout_4",
				inport=self.detmodel.getOrion().getSubSystem32().getOrionSystem().getTimeoutKeepAliveReceiveTimeout_4_req(),
				calls = {'TimeoutKeepAliveReceiveTimeout_4' : [self.detmodel.getOrion().getSubSystem32().getOrionSystem().getTimeoutKeepAliveReceiveTimeout_4(), ]},
				rules = {'TimeoutKeepAliveReceiveTimeout_4' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_4221")}},
				simulator=self)
				
		self.components["Orion.SubSystem32.DelayKapcsolodik_3"].configure(
				name  = "Orion.SubSystem32.DelayKapcsolodik_3",
				inport=self.detmodel.getOrion().getSubSystem32().getOrionSystem().getTimeoutKapcsolodik_3_req(),
				calls = {'TimeoutKapcsolodik_3' : [self.detmodel.getOrion().getSubSystem32().getOrionSystem().getTimeoutKapcsolodik_3(), ]},
				rules = {'TimeoutKapcsolodik_3' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_3222")}},
				simulator=self)
				
		self.components["Orion.SubSystem32.DelayKeepAliveSendTimeout_0"].configure(
				name  = "Orion.SubSystem32.DelayKeepAliveSendTimeout_0",
				inport=self.detmodel.getOrion().getSubSystem32().getOrionSystem().getTimeoutKeepAliveSendTimeout_0_req(),
				calls = {'TimeoutKeepAliveSendTimeout_0' : [self.detmodel.getOrion().getSubSystem32().getOrionSystem().getTimeoutKeepAliveSendTimeout_0(), ]},
				rules = {'TimeoutKeepAliveSendTimeout_0' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_0223")}},
				simulator=self)
				
		
		self.components["Orion.SubSystem1.OrionSystem.System.masterSlaveChannel"].configure(
				name  = "Orion.SubSystem1.OrionSystem.System.masterSlaveChannel",
				inport=self.detmodel.getOrion().getSubSystem1().getOrionSystem().getSystem().getMaster().getSend_StateMachine_Port(),
				calls={'Output' : [self.detmodel.getOrion().getSubSystem1().getOrionSystem().getSystem().getSlave().getStateMachine_Port(), ], 'Lossport' : [None]},
				portarray=["Output", "Lossport"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						torch.tensor(0.9), 
										torch.tensor(0.1)
								])),
					name="MasterSlaveChannel224")
				,
				simulator=self,
				shname="masterSlaveChannel",
				compCall=self.detmodel.getOrion().getSubSystem1().getOrionSystem().getSystem())
				
		self.components["Orion.SubSystem1.OrionSystem.System.slaveMasterChannel"].configure(
				name  = "Orion.SubSystem1.OrionSystem.System.slaveMasterChannel",
				inport=self.detmodel.getOrion().getSubSystem1().getOrionSystem().getSystem().getSlave().getSend_StateMachine_Port(),
				calls={'Output' : [self.detmodel.getOrion().getSubSystem1().getOrionSystem().getSystem().getMaster().getStateMachine_Port(), ], 'Lossport' : [None]},
				portarray=["Output", "Lossport"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						torch.tensor(0.9), 
										torch.tensor(0.1)
								])),
					name="SlaveMasterChannel225")
				,
				simulator=self,
				shname="slaveMasterChannel",
				compCall=self.detmodel.getOrion().getSubSystem1().getOrionSystem().getSystem())
				
		self.components["Orion.SubSystem2.OrionSystem.System.masterSlaveChannel"].configure(
				name  = "Orion.SubSystem2.OrionSystem.System.masterSlaveChannel",
				inport=self.detmodel.getOrion().getSubSystem2().getOrionSystem().getSystem().getMaster().getSend_StateMachine_Port(),
				calls={'Output' : [self.detmodel.getOrion().getSubSystem2().getOrionSystem().getSystem().getSlave().getStateMachine_Port(), ], 'Lossport' : [None]},
				portarray=["Output", "Lossport"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						torch.tensor(0.9), 
										torch.tensor(0.1)
								])),
					name="MasterSlaveChannel226")
				,
				simulator=self,
				shname="masterSlaveChannel",
				compCall=self.detmodel.getOrion().getSubSystem2().getOrionSystem().getSystem())
				
		self.components["Orion.SubSystem2.OrionSystem.System.slaveMasterChannel"].configure(
				name  = "Orion.SubSystem2.OrionSystem.System.slaveMasterChannel",
				inport=self.detmodel.getOrion().getSubSystem2().getOrionSystem().getSystem().getSlave().getSend_StateMachine_Port(),
				calls={'Output' : [self.detmodel.getOrion().getSubSystem2().getOrionSystem().getSystem().getMaster().getStateMachine_Port(), ], 'Lossport' : [None]},
				portarray=["Output", "Lossport"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						torch.tensor(0.9), 
										torch.tensor(0.1)
								])),
					name="SlaveMasterChannel227")
				,
				simulator=self,
				shname="slaveMasterChannel",
				compCall=self.detmodel.getOrion().getSubSystem2().getOrionSystem().getSystem())
				
		self.components["Orion.SubSystem3.OrionSystem.System.masterSlaveChannel"].configure(
				name  = "Orion.SubSystem3.OrionSystem.System.masterSlaveChannel",
				inport=self.detmodel.getOrion().getSubSystem3().getOrionSystem().getSystem().getMaster().getSend_StateMachine_Port(),
				calls={'Output' : [self.detmodel.getOrion().getSubSystem3().getOrionSystem().getSystem().getSlave().getStateMachine_Port(), ], 'Lossport' : [None]},
				portarray=["Output", "Lossport"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						torch.tensor(0.9), 
										torch.tensor(0.1)
								])),
					name="MasterSlaveChannel228")
				,
				simulator=self,
				shname="masterSlaveChannel",
				compCall=self.detmodel.getOrion().getSubSystem3().getOrionSystem().getSystem())
				
		self.components["Orion.SubSystem3.OrionSystem.System.slaveMasterChannel"].configure(
				name  = "Orion.SubSystem3.OrionSystem.System.slaveMasterChannel",
				inport=self.detmodel.getOrion().getSubSystem3().getOrionSystem().getSystem().getSlave().getSend_StateMachine_Port(),
				calls={'Output' : [self.detmodel.getOrion().getSubSystem3().getOrionSystem().getSystem().getMaster().getStateMachine_Port(), ], 'Lossport' : [None]},
				portarray=["Output", "Lossport"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						torch.tensor(0.9), 
										torch.tensor(0.1)
								])),
					name="SlaveMasterChannel229")
				,
				simulator=self,
				shname="slaveMasterChannel",
				compCall=self.detmodel.getOrion().getSubSystem3().getOrionSystem().getSystem())
				
		self.components["Orion.SubSystem4.OrionSystem.System.masterSlaveChannel"].configure(
				name  = "Orion.SubSystem4.OrionSystem.System.masterSlaveChannel",
				inport=self.detmodel.getOrion().getSubSystem4().getOrionSystem().getSystem().getMaster().getSend_StateMachine_Port(),
				calls={'Output' : [self.detmodel.getOrion().getSubSystem4().getOrionSystem().getSystem().getSlave().getStateMachine_Port(), ], 'Lossport' : [None]},
				portarray=["Output", "Lossport"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						torch.tensor(0.9), 
										torch.tensor(0.1)
								])),
					name="MasterSlaveChannel230")
				,
				simulator=self,
				shname="masterSlaveChannel",
				compCall=self.detmodel.getOrion().getSubSystem4().getOrionSystem().getSystem())
				
		self.components["Orion.SubSystem4.OrionSystem.System.slaveMasterChannel"].configure(
				name  = "Orion.SubSystem4.OrionSystem.System.slaveMasterChannel",
				inport=self.detmodel.getOrion().getSubSystem4().getOrionSystem().getSystem().getSlave().getSend_StateMachine_Port(),
				calls={'Output' : [self.detmodel.getOrion().getSubSystem4().getOrionSystem().getSystem().getMaster().getStateMachine_Port(), ], 'Lossport' : [None]},
				portarray=["Output", "Lossport"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						torch.tensor(0.9), 
										torch.tensor(0.1)
								])),
					name="SlaveMasterChannel231")
				,
				simulator=self,
				shname="slaveMasterChannel",
				compCall=self.detmodel.getOrion().getSubSystem4().getOrionSystem().getSystem())
				
		self.components["Orion.SubSystem5.OrionSystem.System.masterSlaveChannel"].configure(
				name  = "Orion.SubSystem5.OrionSystem.System.masterSlaveChannel",
				inport=self.detmodel.getOrion().getSubSystem5().getOrionSystem().getSystem().getMaster().getSend_StateMachine_Port(),
				calls={'Output' : [self.detmodel.getOrion().getSubSystem5().getOrionSystem().getSystem().getSlave().getStateMachine_Port(), ], 'Lossport' : [None]},
				portarray=["Output", "Lossport"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						torch.tensor(0.9), 
										torch.tensor(0.1)
								])),
					name="MasterSlaveChannel232")
				,
				simulator=self,
				shname="masterSlaveChannel",
				compCall=self.detmodel.getOrion().getSubSystem5().getOrionSystem().getSystem())
				
		self.components["Orion.SubSystem5.OrionSystem.System.slaveMasterChannel"].configure(
				name  = "Orion.SubSystem5.OrionSystem.System.slaveMasterChannel",
				inport=self.detmodel.getOrion().getSubSystem5().getOrionSystem().getSystem().getSlave().getSend_StateMachine_Port(),
				calls={'Output' : [self.detmodel.getOrion().getSubSystem5().getOrionSystem().getSystem().getMaster().getStateMachine_Port(), ], 'Lossport' : [None]},
				portarray=["Output", "Lossport"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						torch.tensor(0.9), 
										torch.tensor(0.1)
								])),
					name="SlaveMasterChannel233")
				,
				simulator=self,
				shname="slaveMasterChannel",
				compCall=self.detmodel.getOrion().getSubSystem5().getOrionSystem().getSystem())
				
		self.components["Orion.SubSystem6.OrionSystem.System.masterSlaveChannel"].configure(
				name  = "Orion.SubSystem6.OrionSystem.System.masterSlaveChannel",
				inport=self.detmodel.getOrion().getSubSystem6().getOrionSystem().getSystem().getMaster().getSend_StateMachine_Port(),
				calls={'Output' : [self.detmodel.getOrion().getSubSystem6().getOrionSystem().getSystem().getSlave().getStateMachine_Port(), ], 'Lossport' : [None]},
				portarray=["Output", "Lossport"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						torch.tensor(0.9), 
										torch.tensor(0.1)
								])),
					name="MasterSlaveChannel234")
				,
				simulator=self,
				shname="masterSlaveChannel",
				compCall=self.detmodel.getOrion().getSubSystem6().getOrionSystem().getSystem())
				
		self.components["Orion.SubSystem6.OrionSystem.System.slaveMasterChannel"].configure(
				name  = "Orion.SubSystem6.OrionSystem.System.slaveMasterChannel",
				inport=self.detmodel.getOrion().getSubSystem6().getOrionSystem().getSystem().getSlave().getSend_StateMachine_Port(),
				calls={'Output' : [self.detmodel.getOrion().getSubSystem6().getOrionSystem().getSystem().getMaster().getStateMachine_Port(), ], 'Lossport' : [None]},
				portarray=["Output", "Lossport"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						torch.tensor(0.9), 
										torch.tensor(0.1)
								])),
					name="SlaveMasterChannel235")
				,
				simulator=self,
				shname="slaveMasterChannel",
				compCall=self.detmodel.getOrion().getSubSystem6().getOrionSystem().getSystem())
				
		self.components["Orion.SubSystem7.OrionSystem.System.masterSlaveChannel"].configure(
				name  = "Orion.SubSystem7.OrionSystem.System.masterSlaveChannel",
				inport=self.detmodel.getOrion().getSubSystem7().getOrionSystem().getSystem().getMaster().getSend_StateMachine_Port(),
				calls={'Output' : [self.detmodel.getOrion().getSubSystem7().getOrionSystem().getSystem().getSlave().getStateMachine_Port(), ], 'Lossport' : [None]},
				portarray=["Output", "Lossport"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						torch.tensor(0.9), 
										torch.tensor(0.1)
								])),
					name="MasterSlaveChannel236")
				,
				simulator=self,
				shname="masterSlaveChannel",
				compCall=self.detmodel.getOrion().getSubSystem7().getOrionSystem().getSystem())
				
		self.components["Orion.SubSystem7.OrionSystem.System.slaveMasterChannel"].configure(
				name  = "Orion.SubSystem7.OrionSystem.System.slaveMasterChannel",
				inport=self.detmodel.getOrion().getSubSystem7().getOrionSystem().getSystem().getSlave().getSend_StateMachine_Port(),
				calls={'Output' : [self.detmodel.getOrion().getSubSystem7().getOrionSystem().getSystem().getMaster().getStateMachine_Port(), ], 'Lossport' : [None]},
				portarray=["Output", "Lossport"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						torch.tensor(0.9), 
										torch.tensor(0.1)
								])),
					name="SlaveMasterChannel237")
				,
				simulator=self,
				shname="slaveMasterChannel",
				compCall=self.detmodel.getOrion().getSubSystem7().getOrionSystem().getSystem())
				
		self.components["Orion.SubSystem8.OrionSystem.System.masterSlaveChannel"].configure(
				name  = "Orion.SubSystem8.OrionSystem.System.masterSlaveChannel",
				inport=self.detmodel.getOrion().getSubSystem8().getOrionSystem().getSystem().getMaster().getSend_StateMachine_Port(),
				calls={'Output' : [self.detmodel.getOrion().getSubSystem8().getOrionSystem().getSystem().getSlave().getStateMachine_Port(), ], 'Lossport' : [None]},
				portarray=["Output", "Lossport"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						torch.tensor(0.9), 
										torch.tensor(0.1)
								])),
					name="MasterSlaveChannel238")
				,
				simulator=self,
				shname="masterSlaveChannel",
				compCall=self.detmodel.getOrion().getSubSystem8().getOrionSystem().getSystem())
				
		self.components["Orion.SubSystem8.OrionSystem.System.slaveMasterChannel"].configure(
				name  = "Orion.SubSystem8.OrionSystem.System.slaveMasterChannel",
				inport=self.detmodel.getOrion().getSubSystem8().getOrionSystem().getSystem().getSlave().getSend_StateMachine_Port(),
				calls={'Output' : [self.detmodel.getOrion().getSubSystem8().getOrionSystem().getSystem().getMaster().getStateMachine_Port(), ], 'Lossport' : [None]},
				portarray=["Output", "Lossport"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						torch.tensor(0.9), 
										torch.tensor(0.1)
								])),
					name="SlaveMasterChannel239")
				,
				simulator=self,
				shname="slaveMasterChannel",
				compCall=self.detmodel.getOrion().getSubSystem8().getOrionSystem().getSystem())
				
		self.components["Orion.SubSystem9.OrionSystem.System.masterSlaveChannel"].configure(
				name  = "Orion.SubSystem9.OrionSystem.System.masterSlaveChannel",
				inport=self.detmodel.getOrion().getSubSystem9().getOrionSystem().getSystem().getMaster().getSend_StateMachine_Port(),
				calls={'Output' : [self.detmodel.getOrion().getSubSystem9().getOrionSystem().getSystem().getSlave().getStateMachine_Port(), ], 'Lossport' : [None]},
				portarray=["Output", "Lossport"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						torch.tensor(0.9), 
										torch.tensor(0.1)
								])),
					name="MasterSlaveChannel240")
				,
				simulator=self,
				shname="masterSlaveChannel",
				compCall=self.detmodel.getOrion().getSubSystem9().getOrionSystem().getSystem())
				
		self.components["Orion.SubSystem9.OrionSystem.System.slaveMasterChannel"].configure(
				name  = "Orion.SubSystem9.OrionSystem.System.slaveMasterChannel",
				inport=self.detmodel.getOrion().getSubSystem9().getOrionSystem().getSystem().getSlave().getSend_StateMachine_Port(),
				calls={'Output' : [self.detmodel.getOrion().getSubSystem9().getOrionSystem().getSystem().getMaster().getStateMachine_Port(), ], 'Lossport' : [None]},
				portarray=["Output", "Lossport"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						torch.tensor(0.9), 
										torch.tensor(0.1)
								])),
					name="SlaveMasterChannel241")
				,
				simulator=self,
				shname="slaveMasterChannel",
				compCall=self.detmodel.getOrion().getSubSystem9().getOrionSystem().getSystem())
				
		self.components["Orion.SubSystem10.OrionSystem.System.masterSlaveChannel"].configure(
				name  = "Orion.SubSystem10.OrionSystem.System.masterSlaveChannel",
				inport=self.detmodel.getOrion().getSubSystem10().getOrionSystem().getSystem().getMaster().getSend_StateMachine_Port(),
				calls={'Output' : [self.detmodel.getOrion().getSubSystem10().getOrionSystem().getSystem().getSlave().getStateMachine_Port(), ], 'Lossport' : [None]},
				portarray=["Output", "Lossport"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						torch.tensor(0.9), 
										torch.tensor(0.1)
								])),
					name="MasterSlaveChannel242")
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
					name="SlaveMasterChannel243")
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
					name="MasterSlaveChannel244")
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
					name="SlaveMasterChannel245")
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
					name="MasterSlaveChannel246")
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
					name="SlaveMasterChannel247")
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
					name="MasterSlaveChannel248")
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
					name="SlaveMasterChannel249")
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
					name="MasterSlaveChannel250")
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
					name="SlaveMasterChannel251")
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
					name="MasterSlaveChannel252")
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
					name="SlaveMasterChannel253")
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
					name="MasterSlaveChannel254")
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
					name="SlaveMasterChannel255")
				,
				simulator=self,
				shname="slaveMasterChannel",
				compCall=self.detmodel.getOrion().getSubSystem16().getOrionSystem().getSystem())
				
		self.components["Orion.SubSystem17.OrionSystem.System.masterSlaveChannel"].configure(
				name  = "Orion.SubSystem17.OrionSystem.System.masterSlaveChannel",
				inport=self.detmodel.getOrion().getSubSystem17().getOrionSystem().getSystem().getMaster().getSend_StateMachine_Port(),
				calls={'Output' : [self.detmodel.getOrion().getSubSystem17().getOrionSystem().getSystem().getSlave().getStateMachine_Port(), ], 'Lossport' : [None]},
				portarray=["Output", "Lossport"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						torch.tensor(0.9), 
										torch.tensor(0.1)
								])),
					name="MasterSlaveChannel256")
				,
				simulator=self,
				shname="masterSlaveChannel",
				compCall=self.detmodel.getOrion().getSubSystem17().getOrionSystem().getSystem())
				
		self.components["Orion.SubSystem17.OrionSystem.System.slaveMasterChannel"].configure(
				name  = "Orion.SubSystem17.OrionSystem.System.slaveMasterChannel",
				inport=self.detmodel.getOrion().getSubSystem17().getOrionSystem().getSystem().getSlave().getSend_StateMachine_Port(),
				calls={'Output' : [self.detmodel.getOrion().getSubSystem17().getOrionSystem().getSystem().getMaster().getStateMachine_Port(), ], 'Lossport' : [None]},
				portarray=["Output", "Lossport"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						torch.tensor(0.9), 
										torch.tensor(0.1)
								])),
					name="SlaveMasterChannel257")
				,
				simulator=self,
				shname="slaveMasterChannel",
				compCall=self.detmodel.getOrion().getSubSystem17().getOrionSystem().getSystem())
				
		self.components["Orion.SubSystem18.OrionSystem.System.masterSlaveChannel"].configure(
				name  = "Orion.SubSystem18.OrionSystem.System.masterSlaveChannel",
				inport=self.detmodel.getOrion().getSubSystem18().getOrionSystem().getSystem().getMaster().getSend_StateMachine_Port(),
				calls={'Output' : [self.detmodel.getOrion().getSubSystem18().getOrionSystem().getSystem().getSlave().getStateMachine_Port(), ], 'Lossport' : [None]},
				portarray=["Output", "Lossport"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						torch.tensor(0.9), 
										torch.tensor(0.1)
								])),
					name="MasterSlaveChannel258")
				,
				simulator=self,
				shname="masterSlaveChannel",
				compCall=self.detmodel.getOrion().getSubSystem18().getOrionSystem().getSystem())
				
		self.components["Orion.SubSystem18.OrionSystem.System.slaveMasterChannel"].configure(
				name  = "Orion.SubSystem18.OrionSystem.System.slaveMasterChannel",
				inport=self.detmodel.getOrion().getSubSystem18().getOrionSystem().getSystem().getSlave().getSend_StateMachine_Port(),
				calls={'Output' : [self.detmodel.getOrion().getSubSystem18().getOrionSystem().getSystem().getMaster().getStateMachine_Port(), ], 'Lossport' : [None]},
				portarray=["Output", "Lossport"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						torch.tensor(0.9), 
										torch.tensor(0.1)
								])),
					name="SlaveMasterChannel259")
				,
				simulator=self,
				shname="slaveMasterChannel",
				compCall=self.detmodel.getOrion().getSubSystem18().getOrionSystem().getSystem())
				
		self.components["Orion.SubSystem19.OrionSystem.System.masterSlaveChannel"].configure(
				name  = "Orion.SubSystem19.OrionSystem.System.masterSlaveChannel",
				inport=self.detmodel.getOrion().getSubSystem19().getOrionSystem().getSystem().getMaster().getSend_StateMachine_Port(),
				calls={'Output' : [self.detmodel.getOrion().getSubSystem19().getOrionSystem().getSystem().getSlave().getStateMachine_Port(), ], 'Lossport' : [None]},
				portarray=["Output", "Lossport"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						torch.tensor(0.9), 
										torch.tensor(0.1)
								])),
					name="MasterSlaveChannel260")
				,
				simulator=self,
				shname="masterSlaveChannel",
				compCall=self.detmodel.getOrion().getSubSystem19().getOrionSystem().getSystem())
				
		self.components["Orion.SubSystem19.OrionSystem.System.slaveMasterChannel"].configure(
				name  = "Orion.SubSystem19.OrionSystem.System.slaveMasterChannel",
				inport=self.detmodel.getOrion().getSubSystem19().getOrionSystem().getSystem().getSlave().getSend_StateMachine_Port(),
				calls={'Output' : [self.detmodel.getOrion().getSubSystem19().getOrionSystem().getSystem().getMaster().getStateMachine_Port(), ], 'Lossport' : [None]},
				portarray=["Output", "Lossport"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						torch.tensor(0.9), 
										torch.tensor(0.1)
								])),
					name="SlaveMasterChannel261")
				,
				simulator=self,
				shname="slaveMasterChannel",
				compCall=self.detmodel.getOrion().getSubSystem19().getOrionSystem().getSystem())
				
		self.components["Orion.SubSystem20.OrionSystem.System.masterSlaveChannel"].configure(
				name  = "Orion.SubSystem20.OrionSystem.System.masterSlaveChannel",
				inport=self.detmodel.getOrion().getSubSystem20().getOrionSystem().getSystem().getMaster().getSend_StateMachine_Port(),
				calls={'Output' : [self.detmodel.getOrion().getSubSystem20().getOrionSystem().getSystem().getSlave().getStateMachine_Port(), ], 'Lossport' : [None]},
				portarray=["Output", "Lossport"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						torch.tensor(0.9), 
										torch.tensor(0.1)
								])),
					name="MasterSlaveChannel262")
				,
				simulator=self,
				shname="masterSlaveChannel",
				compCall=self.detmodel.getOrion().getSubSystem20().getOrionSystem().getSystem())
				
		self.components["Orion.SubSystem20.OrionSystem.System.slaveMasterChannel"].configure(
				name  = "Orion.SubSystem20.OrionSystem.System.slaveMasterChannel",
				inport=self.detmodel.getOrion().getSubSystem20().getOrionSystem().getSystem().getSlave().getSend_StateMachine_Port(),
				calls={'Output' : [self.detmodel.getOrion().getSubSystem20().getOrionSystem().getSystem().getMaster().getStateMachine_Port(), ], 'Lossport' : [None]},
				portarray=["Output", "Lossport"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						torch.tensor(0.9), 
										torch.tensor(0.1)
								])),
					name="SlaveMasterChannel263")
				,
				simulator=self,
				shname="slaveMasterChannel",
				compCall=self.detmodel.getOrion().getSubSystem20().getOrionSystem().getSystem())
				
		self.components["Orion.SubSystem21.OrionSystem.System.masterSlaveChannel"].configure(
				name  = "Orion.SubSystem21.OrionSystem.System.masterSlaveChannel",
				inport=self.detmodel.getOrion().getSubSystem21().getOrionSystem().getSystem().getMaster().getSend_StateMachine_Port(),
				calls={'Output' : [self.detmodel.getOrion().getSubSystem21().getOrionSystem().getSystem().getSlave().getStateMachine_Port(), ], 'Lossport' : [None]},
				portarray=["Output", "Lossport"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						torch.tensor(0.9), 
										torch.tensor(0.1)
								])),
					name="MasterSlaveChannel264")
				,
				simulator=self,
				shname="masterSlaveChannel",
				compCall=self.detmodel.getOrion().getSubSystem21().getOrionSystem().getSystem())
				
		self.components["Orion.SubSystem21.OrionSystem.System.slaveMasterChannel"].configure(
				name  = "Orion.SubSystem21.OrionSystem.System.slaveMasterChannel",
				inport=self.detmodel.getOrion().getSubSystem21().getOrionSystem().getSystem().getSlave().getSend_StateMachine_Port(),
				calls={'Output' : [self.detmodel.getOrion().getSubSystem21().getOrionSystem().getSystem().getMaster().getStateMachine_Port(), ], 'Lossport' : [None]},
				portarray=["Output", "Lossport"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						torch.tensor(0.9), 
										torch.tensor(0.1)
								])),
					name="SlaveMasterChannel265")
				,
				simulator=self,
				shname="slaveMasterChannel",
				compCall=self.detmodel.getOrion().getSubSystem21().getOrionSystem().getSystem())
				
		self.components["Orion.SubSystem22.OrionSystem.System.masterSlaveChannel"].configure(
				name  = "Orion.SubSystem22.OrionSystem.System.masterSlaveChannel",
				inport=self.detmodel.getOrion().getSubSystem22().getOrionSystem().getSystem().getMaster().getSend_StateMachine_Port(),
				calls={'Output' : [self.detmodel.getOrion().getSubSystem22().getOrionSystem().getSystem().getSlave().getStateMachine_Port(), ], 'Lossport' : [None]},
				portarray=["Output", "Lossport"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						torch.tensor(0.9), 
										torch.tensor(0.1)
								])),
					name="MasterSlaveChannel266")
				,
				simulator=self,
				shname="masterSlaveChannel",
				compCall=self.detmodel.getOrion().getSubSystem22().getOrionSystem().getSystem())
				
		self.components["Orion.SubSystem22.OrionSystem.System.slaveMasterChannel"].configure(
				name  = "Orion.SubSystem22.OrionSystem.System.slaveMasterChannel",
				inport=self.detmodel.getOrion().getSubSystem22().getOrionSystem().getSystem().getSlave().getSend_StateMachine_Port(),
				calls={'Output' : [self.detmodel.getOrion().getSubSystem22().getOrionSystem().getSystem().getMaster().getStateMachine_Port(), ], 'Lossport' : [None]},
				portarray=["Output", "Lossport"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						torch.tensor(0.9), 
										torch.tensor(0.1)
								])),
					name="SlaveMasterChannel267")
				,
				simulator=self,
				shname="slaveMasterChannel",
				compCall=self.detmodel.getOrion().getSubSystem22().getOrionSystem().getSystem())
				
		self.components["Orion.SubSystem23.OrionSystem.System.masterSlaveChannel"].configure(
				name  = "Orion.SubSystem23.OrionSystem.System.masterSlaveChannel",
				inport=self.detmodel.getOrion().getSubSystem23().getOrionSystem().getSystem().getMaster().getSend_StateMachine_Port(),
				calls={'Output' : [self.detmodel.getOrion().getSubSystem23().getOrionSystem().getSystem().getSlave().getStateMachine_Port(), ], 'Lossport' : [None]},
				portarray=["Output", "Lossport"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						torch.tensor(0.9), 
										torch.tensor(0.1)
								])),
					name="MasterSlaveChannel268")
				,
				simulator=self,
				shname="masterSlaveChannel",
				compCall=self.detmodel.getOrion().getSubSystem23().getOrionSystem().getSystem())
				
		self.components["Orion.SubSystem23.OrionSystem.System.slaveMasterChannel"].configure(
				name  = "Orion.SubSystem23.OrionSystem.System.slaveMasterChannel",
				inport=self.detmodel.getOrion().getSubSystem23().getOrionSystem().getSystem().getSlave().getSend_StateMachine_Port(),
				calls={'Output' : [self.detmodel.getOrion().getSubSystem23().getOrionSystem().getSystem().getMaster().getStateMachine_Port(), ], 'Lossport' : [None]},
				portarray=["Output", "Lossport"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						torch.tensor(0.9), 
										torch.tensor(0.1)
								])),
					name="SlaveMasterChannel269")
				,
				simulator=self,
				shname="slaveMasterChannel",
				compCall=self.detmodel.getOrion().getSubSystem23().getOrionSystem().getSystem())
				
		self.components["Orion.SubSystem24.OrionSystem.System.masterSlaveChannel"].configure(
				name  = "Orion.SubSystem24.OrionSystem.System.masterSlaveChannel",
				inport=self.detmodel.getOrion().getSubSystem24().getOrionSystem().getSystem().getMaster().getSend_StateMachine_Port(),
				calls={'Output' : [self.detmodel.getOrion().getSubSystem24().getOrionSystem().getSystem().getSlave().getStateMachine_Port(), ], 'Lossport' : [None]},
				portarray=["Output", "Lossport"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						torch.tensor(0.9), 
										torch.tensor(0.1)
								])),
					name="MasterSlaveChannel270")
				,
				simulator=self,
				shname="masterSlaveChannel",
				compCall=self.detmodel.getOrion().getSubSystem24().getOrionSystem().getSystem())
				
		self.components["Orion.SubSystem24.OrionSystem.System.slaveMasterChannel"].configure(
				name  = "Orion.SubSystem24.OrionSystem.System.slaveMasterChannel",
				inport=self.detmodel.getOrion().getSubSystem24().getOrionSystem().getSystem().getSlave().getSend_StateMachine_Port(),
				calls={'Output' : [self.detmodel.getOrion().getSubSystem24().getOrionSystem().getSystem().getMaster().getStateMachine_Port(), ], 'Lossport' : [None]},
				portarray=["Output", "Lossport"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						torch.tensor(0.9), 
										torch.tensor(0.1)
								])),
					name="SlaveMasterChannel271")
				,
				simulator=self,
				shname="slaveMasterChannel",
				compCall=self.detmodel.getOrion().getSubSystem24().getOrionSystem().getSystem())
				
		self.components["Orion.SubSystem25.OrionSystem.System.masterSlaveChannel"].configure(
				name  = "Orion.SubSystem25.OrionSystem.System.masterSlaveChannel",
				inport=self.detmodel.getOrion().getSubSystem25().getOrionSystem().getSystem().getMaster().getSend_StateMachine_Port(),
				calls={'Output' : [self.detmodel.getOrion().getSubSystem25().getOrionSystem().getSystem().getSlave().getStateMachine_Port(), ], 'Lossport' : [None]},
				portarray=["Output", "Lossport"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						torch.tensor(0.9), 
										torch.tensor(0.1)
								])),
					name="MasterSlaveChannel272")
				,
				simulator=self,
				shname="masterSlaveChannel",
				compCall=self.detmodel.getOrion().getSubSystem25().getOrionSystem().getSystem())
				
		self.components["Orion.SubSystem25.OrionSystem.System.slaveMasterChannel"].configure(
				name  = "Orion.SubSystem25.OrionSystem.System.slaveMasterChannel",
				inport=self.detmodel.getOrion().getSubSystem25().getOrionSystem().getSystem().getSlave().getSend_StateMachine_Port(),
				calls={'Output' : [self.detmodel.getOrion().getSubSystem25().getOrionSystem().getSystem().getMaster().getStateMachine_Port(), ], 'Lossport' : [None]},
				portarray=["Output", "Lossport"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						torch.tensor(0.9), 
										torch.tensor(0.1)
								])),
					name="SlaveMasterChannel273")
				,
				simulator=self,
				shname="slaveMasterChannel",
				compCall=self.detmodel.getOrion().getSubSystem25().getOrionSystem().getSystem())
				
		self.components["Orion.SubSystem26.OrionSystem.System.masterSlaveChannel"].configure(
				name  = "Orion.SubSystem26.OrionSystem.System.masterSlaveChannel",
				inport=self.detmodel.getOrion().getSubSystem26().getOrionSystem().getSystem().getMaster().getSend_StateMachine_Port(),
				calls={'Output' : [self.detmodel.getOrion().getSubSystem26().getOrionSystem().getSystem().getSlave().getStateMachine_Port(), ], 'Lossport' : [None]},
				portarray=["Output", "Lossport"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						torch.tensor(0.9), 
										torch.tensor(0.1)
								])),
					name="MasterSlaveChannel274")
				,
				simulator=self,
				shname="masterSlaveChannel",
				compCall=self.detmodel.getOrion().getSubSystem26().getOrionSystem().getSystem())
				
		self.components["Orion.SubSystem26.OrionSystem.System.slaveMasterChannel"].configure(
				name  = "Orion.SubSystem26.OrionSystem.System.slaveMasterChannel",
				inport=self.detmodel.getOrion().getSubSystem26().getOrionSystem().getSystem().getSlave().getSend_StateMachine_Port(),
				calls={'Output' : [self.detmodel.getOrion().getSubSystem26().getOrionSystem().getSystem().getMaster().getStateMachine_Port(), ], 'Lossport' : [None]},
				portarray=["Output", "Lossport"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						torch.tensor(0.9), 
										torch.tensor(0.1)
								])),
					name="SlaveMasterChannel275")
				,
				simulator=self,
				shname="slaveMasterChannel",
				compCall=self.detmodel.getOrion().getSubSystem26().getOrionSystem().getSystem())
				
		self.components["Orion.SubSystem27.OrionSystem.System.masterSlaveChannel"].configure(
				name  = "Orion.SubSystem27.OrionSystem.System.masterSlaveChannel",
				inport=self.detmodel.getOrion().getSubSystem27().getOrionSystem().getSystem().getMaster().getSend_StateMachine_Port(),
				calls={'Output' : [self.detmodel.getOrion().getSubSystem27().getOrionSystem().getSystem().getSlave().getStateMachine_Port(), ], 'Lossport' : [None]},
				portarray=["Output", "Lossport"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						torch.tensor(0.9), 
										torch.tensor(0.1)
								])),
					name="MasterSlaveChannel276")
				,
				simulator=self,
				shname="masterSlaveChannel",
				compCall=self.detmodel.getOrion().getSubSystem27().getOrionSystem().getSystem())
				
		self.components["Orion.SubSystem27.OrionSystem.System.slaveMasterChannel"].configure(
				name  = "Orion.SubSystem27.OrionSystem.System.slaveMasterChannel",
				inport=self.detmodel.getOrion().getSubSystem27().getOrionSystem().getSystem().getSlave().getSend_StateMachine_Port(),
				calls={'Output' : [self.detmodel.getOrion().getSubSystem27().getOrionSystem().getSystem().getMaster().getStateMachine_Port(), ], 'Lossport' : [None]},
				portarray=["Output", "Lossport"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						torch.tensor(0.9), 
										torch.tensor(0.1)
								])),
					name="SlaveMasterChannel277")
				,
				simulator=self,
				shname="slaveMasterChannel",
				compCall=self.detmodel.getOrion().getSubSystem27().getOrionSystem().getSystem())
				
		self.components["Orion.SubSystem28.OrionSystem.System.masterSlaveChannel"].configure(
				name  = "Orion.SubSystem28.OrionSystem.System.masterSlaveChannel",
				inport=self.detmodel.getOrion().getSubSystem28().getOrionSystem().getSystem().getMaster().getSend_StateMachine_Port(),
				calls={'Output' : [self.detmodel.getOrion().getSubSystem28().getOrionSystem().getSystem().getSlave().getStateMachine_Port(), ], 'Lossport' : [None]},
				portarray=["Output", "Lossport"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						torch.tensor(0.9), 
										torch.tensor(0.1)
								])),
					name="MasterSlaveChannel278")
				,
				simulator=self,
				shname="masterSlaveChannel",
				compCall=self.detmodel.getOrion().getSubSystem28().getOrionSystem().getSystem())
				
		self.components["Orion.SubSystem28.OrionSystem.System.slaveMasterChannel"].configure(
				name  = "Orion.SubSystem28.OrionSystem.System.slaveMasterChannel",
				inport=self.detmodel.getOrion().getSubSystem28().getOrionSystem().getSystem().getSlave().getSend_StateMachine_Port(),
				calls={'Output' : [self.detmodel.getOrion().getSubSystem28().getOrionSystem().getSystem().getMaster().getStateMachine_Port(), ], 'Lossport' : [None]},
				portarray=["Output", "Lossport"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						torch.tensor(0.9), 
										torch.tensor(0.1)
								])),
					name="SlaveMasterChannel279")
				,
				simulator=self,
				shname="slaveMasterChannel",
				compCall=self.detmodel.getOrion().getSubSystem28().getOrionSystem().getSystem())
				
		self.components["Orion.SubSystem29.OrionSystem.System.masterSlaveChannel"].configure(
				name  = "Orion.SubSystem29.OrionSystem.System.masterSlaveChannel",
				inport=self.detmodel.getOrion().getSubSystem29().getOrionSystem().getSystem().getMaster().getSend_StateMachine_Port(),
				calls={'Output' : [self.detmodel.getOrion().getSubSystem29().getOrionSystem().getSystem().getSlave().getStateMachine_Port(), ], 'Lossport' : [None]},
				portarray=["Output", "Lossport"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						torch.tensor(0.9), 
										torch.tensor(0.1)
								])),
					name="MasterSlaveChannel280")
				,
				simulator=self,
				shname="masterSlaveChannel",
				compCall=self.detmodel.getOrion().getSubSystem29().getOrionSystem().getSystem())
				
		self.components["Orion.SubSystem29.OrionSystem.System.slaveMasterChannel"].configure(
				name  = "Orion.SubSystem29.OrionSystem.System.slaveMasterChannel",
				inport=self.detmodel.getOrion().getSubSystem29().getOrionSystem().getSystem().getSlave().getSend_StateMachine_Port(),
				calls={'Output' : [self.detmodel.getOrion().getSubSystem29().getOrionSystem().getSystem().getMaster().getStateMachine_Port(), ], 'Lossport' : [None]},
				portarray=["Output", "Lossport"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						torch.tensor(0.9), 
										torch.tensor(0.1)
								])),
					name="SlaveMasterChannel281")
				,
				simulator=self,
				shname="slaveMasterChannel",
				compCall=self.detmodel.getOrion().getSubSystem29().getOrionSystem().getSystem())
				
		self.components["Orion.SubSystem30.OrionSystem.System.masterSlaveChannel"].configure(
				name  = "Orion.SubSystem30.OrionSystem.System.masterSlaveChannel",
				inport=self.detmodel.getOrion().getSubSystem30().getOrionSystem().getSystem().getMaster().getSend_StateMachine_Port(),
				calls={'Output' : [self.detmodel.getOrion().getSubSystem30().getOrionSystem().getSystem().getSlave().getStateMachine_Port(), ], 'Lossport' : [None]},
				portarray=["Output", "Lossport"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						torch.tensor(0.9), 
										torch.tensor(0.1)
								])),
					name="MasterSlaveChannel282")
				,
				simulator=self,
				shname="masterSlaveChannel",
				compCall=self.detmodel.getOrion().getSubSystem30().getOrionSystem().getSystem())
				
		self.components["Orion.SubSystem30.OrionSystem.System.slaveMasterChannel"].configure(
				name  = "Orion.SubSystem30.OrionSystem.System.slaveMasterChannel",
				inport=self.detmodel.getOrion().getSubSystem30().getOrionSystem().getSystem().getSlave().getSend_StateMachine_Port(),
				calls={'Output' : [self.detmodel.getOrion().getSubSystem30().getOrionSystem().getSystem().getMaster().getStateMachine_Port(), ], 'Lossport' : [None]},
				portarray=["Output", "Lossport"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						torch.tensor(0.9), 
										torch.tensor(0.1)
								])),
					name="SlaveMasterChannel283")
				,
				simulator=self,
				shname="slaveMasterChannel",
				compCall=self.detmodel.getOrion().getSubSystem30().getOrionSystem().getSystem())
				
		self.components["Orion.SubSystem31.OrionSystem.System.masterSlaveChannel"].configure(
				name  = "Orion.SubSystem31.OrionSystem.System.masterSlaveChannel",
				inport=self.detmodel.getOrion().getSubSystem31().getOrionSystem().getSystem().getMaster().getSend_StateMachine_Port(),
				calls={'Output' : [self.detmodel.getOrion().getSubSystem31().getOrionSystem().getSystem().getSlave().getStateMachine_Port(), ], 'Lossport' : [None]},
				portarray=["Output", "Lossport"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						torch.tensor(0.9), 
										torch.tensor(0.1)
								])),
					name="MasterSlaveChannel284")
				,
				simulator=self,
				shname="masterSlaveChannel",
				compCall=self.detmodel.getOrion().getSubSystem31().getOrionSystem().getSystem())
				
		self.components["Orion.SubSystem31.OrionSystem.System.slaveMasterChannel"].configure(
				name  = "Orion.SubSystem31.OrionSystem.System.slaveMasterChannel",
				inport=self.detmodel.getOrion().getSubSystem31().getOrionSystem().getSystem().getSlave().getSend_StateMachine_Port(),
				calls={'Output' : [self.detmodel.getOrion().getSubSystem31().getOrionSystem().getSystem().getMaster().getStateMachine_Port(), ], 'Lossport' : [None]},
				portarray=["Output", "Lossport"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						torch.tensor(0.9), 
										torch.tensor(0.1)
								])),
					name="SlaveMasterChannel285")
				,
				simulator=self,
				shname="slaveMasterChannel",
				compCall=self.detmodel.getOrion().getSubSystem31().getOrionSystem().getSystem())
				
		self.components["Orion.SubSystem32.OrionSystem.System.masterSlaveChannel"].configure(
				name  = "Orion.SubSystem32.OrionSystem.System.masterSlaveChannel",
				inport=self.detmodel.getOrion().getSubSystem32().getOrionSystem().getSystem().getMaster().getSend_StateMachine_Port(),
				calls={'Output' : [self.detmodel.getOrion().getSubSystem32().getOrionSystem().getSystem().getSlave().getStateMachine_Port(), ], 'Lossport' : [None]},
				portarray=["Output", "Lossport"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						torch.tensor(0.9), 
										torch.tensor(0.1)
								])),
					name="MasterSlaveChannel286")
				,
				simulator=self,
				shname="masterSlaveChannel",
				compCall=self.detmodel.getOrion().getSubSystem32().getOrionSystem().getSystem())
				
		self.components["Orion.SubSystem32.OrionSystem.System.slaveMasterChannel"].configure(
				name  = "Orion.SubSystem32.OrionSystem.System.slaveMasterChannel",
				inport=self.detmodel.getOrion().getSubSystem32().getOrionSystem().getSystem().getSlave().getSend_StateMachine_Port(),
				calls={'Output' : [self.detmodel.getOrion().getSubSystem32().getOrionSystem().getSystem().getMaster().getStateMachine_Port(), ], 'Lossport' : [None]},
				portarray=["Output", "Lossport"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						torch.tensor(0.9), 
										torch.tensor(0.1)
								])),
					name="SlaveMasterChannel287")
				,
				simulator=self,
				shname="slaveMasterChannel",
				compCall=self.detmodel.getOrion().getSubSystem32().getOrionSystem().getSystem())
				
		
		
		

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
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_3112")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_2113")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayZarva_0114")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_1115")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_4116")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_3117")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_0118")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_3119")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_2120")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayZarva_0121")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_1122")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_4123")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_3124")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_0125")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_3126")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_2127")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayZarva_0128")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_1129")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_4130")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_3131")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_0132")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_3133")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_2134")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayZarva_0135")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_1136")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_4137")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_3138")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_0139")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_3140")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_2141")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayZarva_0142")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_1143")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_4144")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_3145")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_0146")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_3147")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_2148")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayZarva_0149")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_1150")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_4151")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_3152")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_0153")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_3154")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_2155")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayZarva_0156")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_1157")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_4158")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_3159")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_0160")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_3161")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_2162")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayZarva_0163")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_1164")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_4165")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_3166")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_0167")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_3168")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_2169")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayZarva_0170")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_1171")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_4172")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_3173")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_0174")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_3175")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_2176")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayZarva_0177")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_1178")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_4179")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_3180")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_0181")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_3182")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_2183")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayZarva_0184")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_1185")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_4186")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_3187")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_0188")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_3189")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_2190")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayZarva_0191")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_1192")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_4193")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_3194")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_0195")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_3196")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_2197")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayZarva_0198")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_1199")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_4200")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_3201")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_0202")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_3203")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_2204")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayZarva_0205")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_1206")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_4207")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_3208")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_0209")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_3210")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_2211")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayZarva_0212")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_1213")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_4214")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_3215")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_0216")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_3217")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_2218")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayZarva_0219")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_1220")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_4221")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_3222")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_0223")]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="MasterSlaveChannel224")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="SlaveMasterChannel225")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="MasterSlaveChannel226")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="SlaveMasterChannel227")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="MasterSlaveChannel228")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="SlaveMasterChannel229")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="MasterSlaveChannel230")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="SlaveMasterChannel231")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="MasterSlaveChannel232")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="SlaveMasterChannel233")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="MasterSlaveChannel234")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="SlaveMasterChannel235")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="MasterSlaveChannel236")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="SlaveMasterChannel237")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="MasterSlaveChannel238")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="SlaveMasterChannel239")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="MasterSlaveChannel240")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="SlaveMasterChannel241")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="MasterSlaveChannel242")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="SlaveMasterChannel243")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="MasterSlaveChannel244")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="SlaveMasterChannel245")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="MasterSlaveChannel246")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="SlaveMasterChannel247")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="MasterSlaveChannel248")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="SlaveMasterChannel249")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="MasterSlaveChannel250")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="SlaveMasterChannel251")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="MasterSlaveChannel252")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="SlaveMasterChannel253")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="MasterSlaveChannel254")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="SlaveMasterChannel255")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="MasterSlaveChannel256")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="SlaveMasterChannel257")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="MasterSlaveChannel258")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="SlaveMasterChannel259")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="MasterSlaveChannel260")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="SlaveMasterChannel261")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="MasterSlaveChannel262")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="SlaveMasterChannel263")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="MasterSlaveChannel264")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="SlaveMasterChannel265")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="MasterSlaveChannel266")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="SlaveMasterChannel267")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="MasterSlaveChannel268")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="SlaveMasterChannel269")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="MasterSlaveChannel270")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="SlaveMasterChannel271")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="MasterSlaveChannel272")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="SlaveMasterChannel273")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="MasterSlaveChannel274")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="SlaveMasterChannel275")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="MasterSlaveChannel276")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="SlaveMasterChannel277")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="MasterSlaveChannel278")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="SlaveMasterChannel279")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="MasterSlaveChannel280")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="SlaveMasterChannel281")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="MasterSlaveChannel282")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="SlaveMasterChannel283")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="MasterSlaveChannel284")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="SlaveMasterChannel285")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="MasterSlaveChannel286")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="SlaveMasterChannel287")
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



