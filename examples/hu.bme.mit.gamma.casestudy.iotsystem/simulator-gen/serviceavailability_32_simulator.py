
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

simTime=1.0
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
	startJVM("""C:\\Program Files\\Java\\jdk-20\\bin\\server\\jvm.dll""", '-ea',"""-Djava.class.path=C:\\Users\\simon\\git\\stochastic-gamma\\examples\\hu.bme.mit.gamma.casestudy.iotsystem\\bin""")
	detmodel = 0
	EntryPoint = JClass('javaenv.ServiceAvailability_32EntryPoint')
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
	def __init__(self,dist,name,N=40):
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


if6 = JClass('hu.bme.mit.gamma.casestudy.iotsystem.interfaces.EventStreamInterface$Listener$Provided')
if6_s = JClass('hu.bme.mit.gamma.casestudy.iotsystem.scheduling.ElementaryComponentSchedulingInterface')

@JImplements([if6,if6_s])
class DelayEventStream():
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
#		implements = ["hu.bme.mit.gamma.casestudy.iotsystem.interfaces.EventStreamInterface$Listener$Provided"]


if7 = JClass('hu.bme.mit.gamma.casestudy.iotsystem.interfaces.DataStreamInterface$Listener$Provided')
if7_s = JClass('hu.bme.mit.gamma.casestudy.iotsystem.scheduling.ElementaryComponentSchedulingInterface')

@JImplements([if7,if7_s])
class SwitchDataStream():

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
	def raiseNewData(self,blurred, car):
		port=self.portarray[self.categorical.calc()]
		eventcalls=self.calls[port]#["NewData"]
		self.event_cntr=self.event_cntr+1
		if DEBUG:
			dprint(f'detmodel -> stochmodel : {self.name} :: {port}+.NewData at {self.simulator.time}')
		for call in eventcalls:
			if call is not None:
				if IESC_SYNC:
					if DEBUG:
						dprint(f'detmodel -> stochmodel : {self.name} :: {port}+.NewData at {self.simulator.time}')
					callEvent=lambda:call.raiseNewData(blurred, car);
					self.events.append(Event(self,self.simulator.time,callEvent,self.port+".NewData"))
				else:
					if DEBUG:
						dprint(f'detmodel <-> stochmodel : {self.name} :: {port}+.NewData at {self.simulator.time}')
					call.raiseNewData(blurred, car)
##	class Java:
#		implements = ["hu.bme.mit.gamma.casestudy.iotsystem.interfaces.DataStreamInterface$Listener$Provided"]

if8 = JClass('hu.bme.mit.gamma.casestudy.iotsystem.interfaces.DataStreamInterface$Listener$Provided')
if8_s = JClass('hu.bme.mit.gamma.casestudy.iotsystem.scheduling.ElementaryComponentSchedulingInterface')

@JImplements([if8,if8_s])
class SampleImageBlur():
	def configure(self,name,inport,calls,rules,simulator,compCall,shname):
		self.name=name
		callitem=list(calls.items())[0]#only one out port
		self.calls=callitem[1]
		self.port=callitem[0]
		self.rules=list(rules.items())[0][1]#only one out port
		self.event_cntr=0
		self.inport=inport
		if inport is not None:
			inport.registerListener(self)
		self.events=[]
		compCall.registerEnvironmentComponent(shname,self)
		self.simulator=simulator
		#iterating through ports
		for port in rules.keys():
			pevents=rules[port].keys()
			#iterating through events
			for pevent in pevents:
				if pevent in rules[port].keys():
					params=rules[port][pevent].keys()
					for param in params:
						rule=rules[port][pevent][param]
						simulator.dists.append(rule)



	def generateEvents(self):
		self.events.clear()
		pass
		#definition of the interface functions

	@JOverride
	def isEventQueueEmpty(self):
		return (len(self.events)==0)

	@JOverride
	def schedule(self):
		for event in self.events:
			event.callEvent()
		self.events.clear(self.name)


	@JOverride
	def raiseNewData(self,blurred, car):
		if "blurred" in self.rules["NewData"].keys():
			blurred = self.rules["NewData"]["blurred"].calc(self.port+"."+"NewData::blurred",self.simulator.time)
		if "car" in self.rules["NewData"].keys():
			car = self.rules["NewData"]["car"].calc(self.port+"."+"NewData::car",self.simulator.time)
		
		#blurred=self.rules["NewData"].calc(self.port+"."+"NewData",self.simulator.time)
		#hu.bme.mit.gamma.expression.model.impl.DecimalTypeDefinitionImpl@381b699c
		self.event_cntr=self.event_cntr+1
		for call in self.calls:
			if IESC_SYNC:
				if DEBUG:
					dprint(f'detmodel -> stochmodel : {self.name} :: {self.port}+.NewData({[blurred, car]}) at {self.simulator.time}')
				callEvent=lambda:call.raiseNewData(blurred, car);
				self.events.append(Event(self,self.simulator.time,callEvent,self.port+".NewData"))
			else:
				if DEBUG:
					dprint(f'detmodel <-> stochmodel : {self.name} :: {self.port}+.NewData({[blurred, car]}) at {self.simulator.time}')
				call.raiseNewData(blurred, car)

##	class Java:
#		implements = ["hu.bme.mit.gamma.casestudy.iotsystem.interfaces.DataStreamInterface$Listener$Provided"]

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
		self.components.update({ "System.Camera1_.softwareTimer" : PeriodicEventSource()})
		self.components.update({ "System.Camera2_.softwareTimer" : PeriodicEventSource()})
		self.components.update({ "System.Camera3_.softwareTimer" : PeriodicEventSource()})
		self.components.update({ "System.Camera4_.softwareTimer" : PeriodicEventSource()})
		self.components.update({ "System.Camera5_.softwareTimer" : PeriodicEventSource()})
		self.components.update({ "System.Camera6_.softwareTimer" : PeriodicEventSource()})
		self.components.update({ "System.Camera7_.softwareTimer" : PeriodicEventSource()})
		self.components.update({ "System.Camera8_.softwareTimer" : PeriodicEventSource()})
		self.components.update({ "System.Camera9_.softwareTimer" : PeriodicEventSource()})
		self.components.update({ "System.Camera10.softwareTimer" : PeriodicEventSource()})
		self.components.update({ "System.Camera11.softwareTimer" : PeriodicEventSource()})
		self.components.update({ "System.Camera12.softwareTimer" : PeriodicEventSource()})
		self.components.update({ "System.Camera13.softwareTimer" : PeriodicEventSource()})
		self.components.update({ "System.Camera14.softwareTimer" : PeriodicEventSource()})
		self.components.update({ "System.Camera15.softwareTimer" : PeriodicEventSource()})
		self.components.update({ "System.Camera16.softwareTimer" : PeriodicEventSource()})
		self.components.update({ "System.Camera17.softwareTimer" : PeriodicEventSource()})
		self.components.update({ "System.Camera18.softwareTimer" : PeriodicEventSource()})
		self.components.update({ "System.Camera19.softwareTimer" : PeriodicEventSource()})
		self.components.update({ "System.Camera20.softwareTimer" : PeriodicEventSource()})
		self.components.update({ "System.Camera21.softwareTimer" : PeriodicEventSource()})
		self.components.update({ "System.Camera22.softwareTimer" : PeriodicEventSource()})
		self.components.update({ "System.Camera23.softwareTimer" : PeriodicEventSource()})
		self.components.update({ "System.Camera24.softwareTimer" : PeriodicEventSource()})
		self.components.update({ "System.Camera25.softwareTimer" : PeriodicEventSource()})
		self.components.update({ "System.Camera26.softwareTimer" : PeriodicEventSource()})
		self.components.update({ "System.Camera27.softwareTimer" : PeriodicEventSource()})
		self.components.update({ "System.Camera28.softwareTimer" : PeriodicEventSource()})
		self.components.update({ "System.Camera29.softwareTimer" : PeriodicEventSource()})
		self.components.update({ "System.Camera30.softwareTimer" : PeriodicEventSource()})
		self.components.update({ "System.Camera31.softwareTimer" : PeriodicEventSource()})
		self.components.update({ "System.Camera32.softwareTimer" : PeriodicEventSource()})
		self.components.update({ "System.Traffic.carArrival" : PeriodicEventSource()})
		
		
		self.components.update({ "System.Traffic.carDelay" : DelayEventStream()})
		
		
		self.components.update({ "System.Camera1_.networkLoss" : SwitchDataStream()})
		self.components.update({ "System.Camera2_.networkLoss" : SwitchDataStream()})
		self.components.update({ "System.Camera3_.networkLoss" : SwitchDataStream()})
		self.components.update({ "System.Camera4_.networkLoss" : SwitchDataStream()})
		self.components.update({ "System.Camera5_.networkLoss" : SwitchDataStream()})
		self.components.update({ "System.Camera6_.networkLoss" : SwitchDataStream()})
		self.components.update({ "System.Camera7_.networkLoss" : SwitchDataStream()})
		self.components.update({ "System.Camera8_.networkLoss" : SwitchDataStream()})
		self.components.update({ "System.Camera9_.networkLoss" : SwitchDataStream()})
		self.components.update({ "System.Camera10.networkLoss" : SwitchDataStream()})
		self.components.update({ "System.Camera11.networkLoss" : SwitchDataStream()})
		self.components.update({ "System.Camera12.networkLoss" : SwitchDataStream()})
		self.components.update({ "System.Camera13.networkLoss" : SwitchDataStream()})
		self.components.update({ "System.Camera14.networkLoss" : SwitchDataStream()})
		self.components.update({ "System.Camera15.networkLoss" : SwitchDataStream()})
		self.components.update({ "System.Camera16.networkLoss" : SwitchDataStream()})
		self.components.update({ "System.Camera17.networkLoss" : SwitchDataStream()})
		self.components.update({ "System.Camera18.networkLoss" : SwitchDataStream()})
		self.components.update({ "System.Camera19.networkLoss" : SwitchDataStream()})
		self.components.update({ "System.Camera20.networkLoss" : SwitchDataStream()})
		self.components.update({ "System.Camera21.networkLoss" : SwitchDataStream()})
		self.components.update({ "System.Camera22.networkLoss" : SwitchDataStream()})
		self.components.update({ "System.Camera23.networkLoss" : SwitchDataStream()})
		self.components.update({ "System.Camera24.networkLoss" : SwitchDataStream()})
		self.components.update({ "System.Camera25.networkLoss" : SwitchDataStream()})
		self.components.update({ "System.Camera26.networkLoss" : SwitchDataStream()})
		self.components.update({ "System.Camera27.networkLoss" : SwitchDataStream()})
		self.components.update({ "System.Camera28.networkLoss" : SwitchDataStream()})
		self.components.update({ "System.Camera29.networkLoss" : SwitchDataStream()})
		self.components.update({ "System.Camera30.networkLoss" : SwitchDataStream()})
		self.components.update({ "System.Camera31.networkLoss" : SwitchDataStream()})
		self.components.update({ "System.Camera32.networkLoss" : SwitchDataStream()})
		
		self.components.update({ "System.Camera1_.Software.CameraSoftware.imageBlur" : SampleImageBlur()})
		self.components.update({ "System.Camera2_.Software.CameraSoftware.imageBlur" : SampleImageBlur()})
		self.components.update({ "System.Camera3_.Software.CameraSoftware.imageBlur" : SampleImageBlur()})
		self.components.update({ "System.Camera4_.Software.CameraSoftware.imageBlur" : SampleImageBlur()})
		self.components.update({ "System.Camera5_.Software.CameraSoftware.imageBlur" : SampleImageBlur()})
		self.components.update({ "System.Camera6_.Software.CameraSoftware.imageBlur" : SampleImageBlur()})
		self.components.update({ "System.Camera7_.Software.CameraSoftware.imageBlur" : SampleImageBlur()})
		self.components.update({ "System.Camera8_.Software.CameraSoftware.imageBlur" : SampleImageBlur()})
		self.components.update({ "System.Camera9_.Software.CameraSoftware.imageBlur" : SampleImageBlur()})
		self.components.update({ "System.Camera10.Software.CameraSoftware.imageBlur" : SampleImageBlur()})
		self.components.update({ "System.Camera11.Software.CameraSoftware.imageBlur" : SampleImageBlur()})
		self.components.update({ "System.Camera12.Software.CameraSoftware.imageBlur" : SampleImageBlur()})
		self.components.update({ "System.Camera13.Software.CameraSoftware.imageBlur" : SampleImageBlur()})
		self.components.update({ "System.Camera14.Software.CameraSoftware.imageBlur" : SampleImageBlur()})
		self.components.update({ "System.Camera15.Software.CameraSoftware.imageBlur" : SampleImageBlur()})
		self.components.update({ "System.Camera16.Software.CameraSoftware.imageBlur" : SampleImageBlur()})
		self.components.update({ "System.Camera17.Software.CameraSoftware.imageBlur" : SampleImageBlur()})
		self.components.update({ "System.Camera18.Software.CameraSoftware.imageBlur" : SampleImageBlur()})
		self.components.update({ "System.Camera19.Software.CameraSoftware.imageBlur" : SampleImageBlur()})
		self.components.update({ "System.Camera20.Software.CameraSoftware.imageBlur" : SampleImageBlur()})
		self.components.update({ "System.Camera21.Software.CameraSoftware.imageBlur" : SampleImageBlur()})
		self.components.update({ "System.Camera22.Software.CameraSoftware.imageBlur" : SampleImageBlur()})
		self.components.update({ "System.Camera23.Software.CameraSoftware.imageBlur" : SampleImageBlur()})
		self.components.update({ "System.Camera24.Software.CameraSoftware.imageBlur" : SampleImageBlur()})
		self.components.update({ "System.Camera25.Software.CameraSoftware.imageBlur" : SampleImageBlur()})
		self.components.update({ "System.Camera26.Software.CameraSoftware.imageBlur" : SampleImageBlur()})
		self.components.update({ "System.Camera27.Software.CameraSoftware.imageBlur" : SampleImageBlur()})
		self.components.update({ "System.Camera28.Software.CameraSoftware.imageBlur" : SampleImageBlur()})
		self.components.update({ "System.Camera29.Software.CameraSoftware.imageBlur" : SampleImageBlur()})
		self.components.update({ "System.Camera30.Software.CameraSoftware.imageBlur" : SampleImageBlur()})
		self.components.update({ "System.Camera31.Software.CameraSoftware.imageBlur" : SampleImageBlur()})
		self.components.update({ "System.Camera32.Software.CameraSoftware.imageBlur" : SampleImageBlur()})
		
		# register input interfaces of elementary stochastic components
		
		self.components["System.Camera1_.softwareTimer"].configure(
				name  = "System.Camera1_.softwareTimer",
				calls = {'Events' : {'NewEvent' : [(lambda:self.detmodel.getSystem().getCamera1_().getSoftware().getSoftwareTimer().raiseNewEvent())]}},
				rules = {'Events' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(0.2),scale=torch.tensor(0.02)),"ContRandomVarriablesoftwareTimer0")}},
				portevents = 	{	"Events" : [ "NewEvent"	]},
				simulator=self)
				
		self.components["System.Camera2_.softwareTimer"].configure(
				name  = "System.Camera2_.softwareTimer",
				calls = {'Events' : {'NewEvent' : [(lambda:self.detmodel.getSystem().getCamera2_().getSoftware().getSoftwareTimer().raiseNewEvent())]}},
				rules = {'Events' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(0.2),scale=torch.tensor(0.02)),"ContRandomVarriablesoftwareTimer1")}},
				portevents = 	{	"Events" : [ "NewEvent"	]},
				simulator=self)
				
		self.components["System.Camera3_.softwareTimer"].configure(
				name  = "System.Camera3_.softwareTimer",
				calls = {'Events' : {'NewEvent' : [(lambda:self.detmodel.getSystem().getCamera3_().getSoftware().getSoftwareTimer().raiseNewEvent())]}},
				rules = {'Events' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(0.2),scale=torch.tensor(0.02)),"ContRandomVarriablesoftwareTimer2")}},
				portevents = 	{	"Events" : [ "NewEvent"	]},
				simulator=self)
				
		self.components["System.Camera4_.softwareTimer"].configure(
				name  = "System.Camera4_.softwareTimer",
				calls = {'Events' : {'NewEvent' : [(lambda:self.detmodel.getSystem().getCamera4_().getSoftware().getSoftwareTimer().raiseNewEvent())]}},
				rules = {'Events' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(0.2),scale=torch.tensor(0.02)),"ContRandomVarriablesoftwareTimer3")}},
				portevents = 	{	"Events" : [ "NewEvent"	]},
				simulator=self)
				
		self.components["System.Camera5_.softwareTimer"].configure(
				name  = "System.Camera5_.softwareTimer",
				calls = {'Events' : {'NewEvent' : [(lambda:self.detmodel.getSystem().getCamera5_().getSoftware().getSoftwareTimer().raiseNewEvent())]}},
				rules = {'Events' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(0.2),scale=torch.tensor(0.02)),"ContRandomVarriablesoftwareTimer4")}},
				portevents = 	{	"Events" : [ "NewEvent"	]},
				simulator=self)
				
		self.components["System.Camera6_.softwareTimer"].configure(
				name  = "System.Camera6_.softwareTimer",
				calls = {'Events' : {'NewEvent' : [(lambda:self.detmodel.getSystem().getCamera6_().getSoftware().getSoftwareTimer().raiseNewEvent())]}},
				rules = {'Events' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(0.2),scale=torch.tensor(0.02)),"ContRandomVarriablesoftwareTimer5")}},
				portevents = 	{	"Events" : [ "NewEvent"	]},
				simulator=self)
				
		self.components["System.Camera7_.softwareTimer"].configure(
				name  = "System.Camera7_.softwareTimer",
				calls = {'Events' : {'NewEvent' : [(lambda:self.detmodel.getSystem().getCamera7_().getSoftware().getSoftwareTimer().raiseNewEvent())]}},
				rules = {'Events' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(0.2),scale=torch.tensor(0.02)),"ContRandomVarriablesoftwareTimer6")}},
				portevents = 	{	"Events" : [ "NewEvent"	]},
				simulator=self)
				
		self.components["System.Camera8_.softwareTimer"].configure(
				name  = "System.Camera8_.softwareTimer",
				calls = {'Events' : {'NewEvent' : [(lambda:self.detmodel.getSystem().getCamera8_().getSoftware().getSoftwareTimer().raiseNewEvent())]}},
				rules = {'Events' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(0.2),scale=torch.tensor(0.02)),"ContRandomVarriablesoftwareTimer7")}},
				portevents = 	{	"Events" : [ "NewEvent"	]},
				simulator=self)
				
		self.components["System.Camera9_.softwareTimer"].configure(
				name  = "System.Camera9_.softwareTimer",
				calls = {'Events' : {'NewEvent' : [(lambda:self.detmodel.getSystem().getCamera9_().getSoftware().getSoftwareTimer().raiseNewEvent())]}},
				rules = {'Events' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(0.2),scale=torch.tensor(0.02)),"ContRandomVarriablesoftwareTimer8")}},
				portevents = 	{	"Events" : [ "NewEvent"	]},
				simulator=self)
				
		self.components["System.Camera10.softwareTimer"].configure(
				name  = "System.Camera10.softwareTimer",
				calls = {'Events' : {'NewEvent' : [(lambda:self.detmodel.getSystem().getCamera10().getSoftware().getSoftwareTimer().raiseNewEvent())]}},
				rules = {'Events' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(0.2),scale=torch.tensor(0.02)),"ContRandomVarriablesoftwareTimer9")}},
				portevents = 	{	"Events" : [ "NewEvent"	]},
				simulator=self)
				
		self.components["System.Camera11.softwareTimer"].configure(
				name  = "System.Camera11.softwareTimer",
				calls = {'Events' : {'NewEvent' : [(lambda:self.detmodel.getSystem().getCamera11().getSoftware().getSoftwareTimer().raiseNewEvent())]}},
				rules = {'Events' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(0.2),scale=torch.tensor(0.02)),"ContRandomVarriablesoftwareTimer10")}},
				portevents = 	{	"Events" : [ "NewEvent"	]},
				simulator=self)
				
		self.components["System.Camera12.softwareTimer"].configure(
				name  = "System.Camera12.softwareTimer",
				calls = {'Events' : {'NewEvent' : [(lambda:self.detmodel.getSystem().getCamera12().getSoftware().getSoftwareTimer().raiseNewEvent())]}},
				rules = {'Events' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(0.2),scale=torch.tensor(0.02)),"ContRandomVarriablesoftwareTimer11")}},
				portevents = 	{	"Events" : [ "NewEvent"	]},
				simulator=self)
				
		self.components["System.Camera13.softwareTimer"].configure(
				name  = "System.Camera13.softwareTimer",
				calls = {'Events' : {'NewEvent' : [(lambda:self.detmodel.getSystem().getCamera13().getSoftware().getSoftwareTimer().raiseNewEvent())]}},
				rules = {'Events' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(0.2),scale=torch.tensor(0.02)),"ContRandomVarriablesoftwareTimer12")}},
				portevents = 	{	"Events" : [ "NewEvent"	]},
				simulator=self)
				
		self.components["System.Camera14.softwareTimer"].configure(
				name  = "System.Camera14.softwareTimer",
				calls = {'Events' : {'NewEvent' : [(lambda:self.detmodel.getSystem().getCamera14().getSoftware().getSoftwareTimer().raiseNewEvent())]}},
				rules = {'Events' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(0.2),scale=torch.tensor(0.02)),"ContRandomVarriablesoftwareTimer13")}},
				portevents = 	{	"Events" : [ "NewEvent"	]},
				simulator=self)
				
		self.components["System.Camera15.softwareTimer"].configure(
				name  = "System.Camera15.softwareTimer",
				calls = {'Events' : {'NewEvent' : [(lambda:self.detmodel.getSystem().getCamera15().getSoftware().getSoftwareTimer().raiseNewEvent())]}},
				rules = {'Events' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(0.2),scale=torch.tensor(0.02)),"ContRandomVarriablesoftwareTimer14")}},
				portevents = 	{	"Events" : [ "NewEvent"	]},
				simulator=self)
				
		self.components["System.Camera16.softwareTimer"].configure(
				name  = "System.Camera16.softwareTimer",
				calls = {'Events' : {'NewEvent' : [(lambda:self.detmodel.getSystem().getCamera16().getSoftware().getSoftwareTimer().raiseNewEvent())]}},
				rules = {'Events' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(0.2),scale=torch.tensor(0.02)),"ContRandomVarriablesoftwareTimer15")}},
				portevents = 	{	"Events" : [ "NewEvent"	]},
				simulator=self)
				
		self.components["System.Camera17.softwareTimer"].configure(
				name  = "System.Camera17.softwareTimer",
				calls = {'Events' : {'NewEvent' : [(lambda:self.detmodel.getSystem().getCamera17().getSoftware().getSoftwareTimer().raiseNewEvent())]}},
				rules = {'Events' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(0.2),scale=torch.tensor(0.02)),"ContRandomVarriablesoftwareTimer16")}},
				portevents = 	{	"Events" : [ "NewEvent"	]},
				simulator=self)
				
		self.components["System.Camera18.softwareTimer"].configure(
				name  = "System.Camera18.softwareTimer",
				calls = {'Events' : {'NewEvent' : [(lambda:self.detmodel.getSystem().getCamera18().getSoftware().getSoftwareTimer().raiseNewEvent())]}},
				rules = {'Events' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(0.2),scale=torch.tensor(0.02)),"ContRandomVarriablesoftwareTimer17")}},
				portevents = 	{	"Events" : [ "NewEvent"	]},
				simulator=self)
				
		self.components["System.Camera19.softwareTimer"].configure(
				name  = "System.Camera19.softwareTimer",
				calls = {'Events' : {'NewEvent' : [(lambda:self.detmodel.getSystem().getCamera19().getSoftware().getSoftwareTimer().raiseNewEvent())]}},
				rules = {'Events' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(0.2),scale=torch.tensor(0.02)),"ContRandomVarriablesoftwareTimer18")}},
				portevents = 	{	"Events" : [ "NewEvent"	]},
				simulator=self)
				
		self.components["System.Camera20.softwareTimer"].configure(
				name  = "System.Camera20.softwareTimer",
				calls = {'Events' : {'NewEvent' : [(lambda:self.detmodel.getSystem().getCamera20().getSoftware().getSoftwareTimer().raiseNewEvent())]}},
				rules = {'Events' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(0.2),scale=torch.tensor(0.02)),"ContRandomVarriablesoftwareTimer19")}},
				portevents = 	{	"Events" : [ "NewEvent"	]},
				simulator=self)
				
		self.components["System.Camera21.softwareTimer"].configure(
				name  = "System.Camera21.softwareTimer",
				calls = {'Events' : {'NewEvent' : [(lambda:self.detmodel.getSystem().getCamera21().getSoftware().getSoftwareTimer().raiseNewEvent())]}},
				rules = {'Events' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(0.2),scale=torch.tensor(0.02)),"ContRandomVarriablesoftwareTimer20")}},
				portevents = 	{	"Events" : [ "NewEvent"	]},
				simulator=self)
				
		self.components["System.Camera22.softwareTimer"].configure(
				name  = "System.Camera22.softwareTimer",
				calls = {'Events' : {'NewEvent' : [(lambda:self.detmodel.getSystem().getCamera22().getSoftware().getSoftwareTimer().raiseNewEvent())]}},
				rules = {'Events' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(0.2),scale=torch.tensor(0.02)),"ContRandomVarriablesoftwareTimer21")}},
				portevents = 	{	"Events" : [ "NewEvent"	]},
				simulator=self)
				
		self.components["System.Camera23.softwareTimer"].configure(
				name  = "System.Camera23.softwareTimer",
				calls = {'Events' : {'NewEvent' : [(lambda:self.detmodel.getSystem().getCamera23().getSoftware().getSoftwareTimer().raiseNewEvent())]}},
				rules = {'Events' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(0.2),scale=torch.tensor(0.02)),"ContRandomVarriablesoftwareTimer22")}},
				portevents = 	{	"Events" : [ "NewEvent"	]},
				simulator=self)
				
		self.components["System.Camera24.softwareTimer"].configure(
				name  = "System.Camera24.softwareTimer",
				calls = {'Events' : {'NewEvent' : [(lambda:self.detmodel.getSystem().getCamera24().getSoftware().getSoftwareTimer().raiseNewEvent())]}},
				rules = {'Events' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(0.2),scale=torch.tensor(0.02)),"ContRandomVarriablesoftwareTimer23")}},
				portevents = 	{	"Events" : [ "NewEvent"	]},
				simulator=self)
				
		self.components["System.Camera25.softwareTimer"].configure(
				name  = "System.Camera25.softwareTimer",
				calls = {'Events' : {'NewEvent' : [(lambda:self.detmodel.getSystem().getCamera25().getSoftware().getSoftwareTimer().raiseNewEvent())]}},
				rules = {'Events' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(0.2),scale=torch.tensor(0.02)),"ContRandomVarriablesoftwareTimer24")}},
				portevents = 	{	"Events" : [ "NewEvent"	]},
				simulator=self)
				
		self.components["System.Camera26.softwareTimer"].configure(
				name  = "System.Camera26.softwareTimer",
				calls = {'Events' : {'NewEvent' : [(lambda:self.detmodel.getSystem().getCamera26().getSoftware().getSoftwareTimer().raiseNewEvent())]}},
				rules = {'Events' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(0.2),scale=torch.tensor(0.02)),"ContRandomVarriablesoftwareTimer25")}},
				portevents = 	{	"Events" : [ "NewEvent"	]},
				simulator=self)
				
		self.components["System.Camera27.softwareTimer"].configure(
				name  = "System.Camera27.softwareTimer",
				calls = {'Events' : {'NewEvent' : [(lambda:self.detmodel.getSystem().getCamera27().getSoftware().getSoftwareTimer().raiseNewEvent())]}},
				rules = {'Events' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(0.2),scale=torch.tensor(0.02)),"ContRandomVarriablesoftwareTimer26")}},
				portevents = 	{	"Events" : [ "NewEvent"	]},
				simulator=self)
				
		self.components["System.Camera28.softwareTimer"].configure(
				name  = "System.Camera28.softwareTimer",
				calls = {'Events' : {'NewEvent' : [(lambda:self.detmodel.getSystem().getCamera28().getSoftware().getSoftwareTimer().raiseNewEvent())]}},
				rules = {'Events' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(0.2),scale=torch.tensor(0.02)),"ContRandomVarriablesoftwareTimer27")}},
				portevents = 	{	"Events" : [ "NewEvent"	]},
				simulator=self)
				
		self.components["System.Camera29.softwareTimer"].configure(
				name  = "System.Camera29.softwareTimer",
				calls = {'Events' : {'NewEvent' : [(lambda:self.detmodel.getSystem().getCamera29().getSoftware().getSoftwareTimer().raiseNewEvent())]}},
				rules = {'Events' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(0.2),scale=torch.tensor(0.02)),"ContRandomVarriablesoftwareTimer28")}},
				portevents = 	{	"Events" : [ "NewEvent"	]},
				simulator=self)
				
		self.components["System.Camera30.softwareTimer"].configure(
				name  = "System.Camera30.softwareTimer",
				calls = {'Events' : {'NewEvent' : [(lambda:self.detmodel.getSystem().getCamera30().getSoftware().getSoftwareTimer().raiseNewEvent())]}},
				rules = {'Events' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(0.2),scale=torch.tensor(0.02)),"ContRandomVarriablesoftwareTimer29")}},
				portevents = 	{	"Events" : [ "NewEvent"	]},
				simulator=self)
				
		self.components["System.Camera31.softwareTimer"].configure(
				name  = "System.Camera31.softwareTimer",
				calls = {'Events' : {'NewEvent' : [(lambda:self.detmodel.getSystem().getCamera31().getSoftware().getSoftwareTimer().raiseNewEvent())]}},
				rules = {'Events' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(0.2),scale=torch.tensor(0.02)),"ContRandomVarriablesoftwareTimer30")}},
				portevents = 	{	"Events" : [ "NewEvent"	]},
				simulator=self)
				
		self.components["System.Camera32.softwareTimer"].configure(
				name  = "System.Camera32.softwareTimer",
				calls = {'Events' : {'NewEvent' : [(lambda:self.detmodel.getSystem().getCamera32().getSoftware().getSoftwareTimer().raiseNewEvent())]}},
				rules = {'Events' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(0.2),scale=torch.tensor(0.02)),"ContRandomVarriablesoftwareTimer31")}},
				portevents = 	{	"Events" : [ "NewEvent"	]},
				simulator=self)
				
		self.components["System.Traffic.carArrival"].configure(
				name  = "System.Traffic.carArrival",
				calls = {'Cars' : {'NewEvent' : [(lambda:self.detmodel.getSystem().getTraffic().getTrafficSct().getCarArrives().raiseNewEvent())]}},
				rules = {'Cars' : {'NewEvent' : RandomVariable(pyro.distributions.Exponential(torch.tensor(2.5)),"ContRandomVarriablecarArrival32")}},
				portevents = 	{	"Cars" : [ "NewEvent"	]},
				simulator=self)
				
		
		
		self.components["System.Traffic.carDelay"].configure(
				name  = "System.Traffic.carDelay",
				inport=self.detmodel.getSystem().getTraffic().getTrafficSct().getCarArrivesOut(),
				calls = {'CarOut' : [self.detmodel.getSystem().getTraffic().getTrafficSct().getCarLeaves(), ]},
				rules = {'CarOut' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(0.3),scale=torch.tensor(0.1)),"ContRandomVarriablecarDelay33")}},
				simulator=self)
				
		
		self.components["System.Camera1_.networkLoss"].configure(
				name  = "System.Camera1_.networkLoss",
				inport=self.detmodel.getSystem().getCamera1_().getSoftware().getImages(),
				calls={'ImageOut' : [self.detmodel.getSystem().getCamera1_().getNetworkQueue().getImageIn(), ], 'LostImages' : [self.detmodel.getSystem().getCamera1_().getNetworkQueue().getImageLoss(), ]},
				portarray=["ImageOut", "LostImages"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						torch.tensor(0.9), 
										torch.tensor(0.1)
								])),
					name="NetworkLoss34")
				,
				simulator=self,
				shname="networkLoss",
				compCall=self.detmodel.getSystem().getCamera1_())
				
		self.components["System.Camera2_.networkLoss"].configure(
				name  = "System.Camera2_.networkLoss",
				inport=self.detmodel.getSystem().getCamera2_().getSoftware().getImages(),
				calls={'ImageOut' : [self.detmodel.getSystem().getCamera2_().getNetworkQueue().getImageIn(), ], 'LostImages' : [self.detmodel.getSystem().getCamera2_().getNetworkQueue().getImageLoss(), ]},
				portarray=["ImageOut", "LostImages"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						torch.tensor(0.9), 
										torch.tensor(0.1)
								])),
					name="NetworkLoss35")
				,
				simulator=self,
				shname="networkLoss",
				compCall=self.detmodel.getSystem().getCamera2_())
				
		self.components["System.Camera3_.networkLoss"].configure(
				name  = "System.Camera3_.networkLoss",
				inport=self.detmodel.getSystem().getCamera3_().getSoftware().getImages(),
				calls={'ImageOut' : [self.detmodel.getSystem().getCamera3_().getNetworkQueue().getImageIn(), ], 'LostImages' : [self.detmodel.getSystem().getCamera3_().getNetworkQueue().getImageLoss(), ]},
				portarray=["ImageOut", "LostImages"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						torch.tensor(0.9), 
										torch.tensor(0.1)
								])),
					name="NetworkLoss36")
				,
				simulator=self,
				shname="networkLoss",
				compCall=self.detmodel.getSystem().getCamera3_())
				
		self.components["System.Camera4_.networkLoss"].configure(
				name  = "System.Camera4_.networkLoss",
				inport=self.detmodel.getSystem().getCamera4_().getSoftware().getImages(),
				calls={'ImageOut' : [self.detmodel.getSystem().getCamera4_().getNetworkQueue().getImageIn(), ], 'LostImages' : [self.detmodel.getSystem().getCamera4_().getNetworkQueue().getImageLoss(), ]},
				portarray=["ImageOut", "LostImages"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						torch.tensor(0.9), 
										torch.tensor(0.1)
								])),
					name="NetworkLoss37")
				,
				simulator=self,
				shname="networkLoss",
				compCall=self.detmodel.getSystem().getCamera4_())
				
		self.components["System.Camera5_.networkLoss"].configure(
				name  = "System.Camera5_.networkLoss",
				inport=self.detmodel.getSystem().getCamera5_().getSoftware().getImages(),
				calls={'ImageOut' : [self.detmodel.getSystem().getCamera5_().getNetworkQueue().getImageIn(), ], 'LostImages' : [self.detmodel.getSystem().getCamera5_().getNetworkQueue().getImageLoss(), ]},
				portarray=["ImageOut", "LostImages"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						torch.tensor(0.9), 
										torch.tensor(0.1)
								])),
					name="NetworkLoss38")
				,
				simulator=self,
				shname="networkLoss",
				compCall=self.detmodel.getSystem().getCamera5_())
				
		self.components["System.Camera6_.networkLoss"].configure(
				name  = "System.Camera6_.networkLoss",
				inport=self.detmodel.getSystem().getCamera6_().getSoftware().getImages(),
				calls={'ImageOut' : [self.detmodel.getSystem().getCamera6_().getNetworkQueue().getImageIn(), ], 'LostImages' : [self.detmodel.getSystem().getCamera6_().getNetworkQueue().getImageLoss(), ]},
				portarray=["ImageOut", "LostImages"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						torch.tensor(0.9), 
										torch.tensor(0.1)
								])),
					name="NetworkLoss39")
				,
				simulator=self,
				shname="networkLoss",
				compCall=self.detmodel.getSystem().getCamera6_())
				
		self.components["System.Camera7_.networkLoss"].configure(
				name  = "System.Camera7_.networkLoss",
				inport=self.detmodel.getSystem().getCamera7_().getSoftware().getImages(),
				calls={'ImageOut' : [self.detmodel.getSystem().getCamera7_().getNetworkQueue().getImageIn(), ], 'LostImages' : [self.detmodel.getSystem().getCamera7_().getNetworkQueue().getImageLoss(), ]},
				portarray=["ImageOut", "LostImages"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						torch.tensor(0.9), 
										torch.tensor(0.1)
								])),
					name="NetworkLoss40")
				,
				simulator=self,
				shname="networkLoss",
				compCall=self.detmodel.getSystem().getCamera7_())
				
		self.components["System.Camera8_.networkLoss"].configure(
				name  = "System.Camera8_.networkLoss",
				inport=self.detmodel.getSystem().getCamera8_().getSoftware().getImages(),
				calls={'ImageOut' : [self.detmodel.getSystem().getCamera8_().getNetworkQueue().getImageIn(), ], 'LostImages' : [self.detmodel.getSystem().getCamera8_().getNetworkQueue().getImageLoss(), ]},
				portarray=["ImageOut", "LostImages"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						torch.tensor(0.9), 
										torch.tensor(0.1)
								])),
					name="NetworkLoss41")
				,
				simulator=self,
				shname="networkLoss",
				compCall=self.detmodel.getSystem().getCamera8_())
				
		self.components["System.Camera9_.networkLoss"].configure(
				name  = "System.Camera9_.networkLoss",
				inport=self.detmodel.getSystem().getCamera9_().getSoftware().getImages(),
				calls={'ImageOut' : [self.detmodel.getSystem().getCamera9_().getNetworkQueue().getImageIn(), ], 'LostImages' : [self.detmodel.getSystem().getCamera9_().getNetworkQueue().getImageLoss(), ]},
				portarray=["ImageOut", "LostImages"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						torch.tensor(0.9), 
										torch.tensor(0.1)
								])),
					name="NetworkLoss42")
				,
				simulator=self,
				shname="networkLoss",
				compCall=self.detmodel.getSystem().getCamera9_())
				
		self.components["System.Camera10.networkLoss"].configure(
				name  = "System.Camera10.networkLoss",
				inport=self.detmodel.getSystem().getCamera10().getSoftware().getImages(),
				calls={'ImageOut' : [self.detmodel.getSystem().getCamera10().getNetworkQueue().getImageIn(), ], 'LostImages' : [self.detmodel.getSystem().getCamera10().getNetworkQueue().getImageLoss(), ]},
				portarray=["ImageOut", "LostImages"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						torch.tensor(0.9), 
										torch.tensor(0.1)
								])),
					name="NetworkLoss43")
				,
				simulator=self,
				shname="networkLoss",
				compCall=self.detmodel.getSystem().getCamera10())
				
		self.components["System.Camera11.networkLoss"].configure(
				name  = "System.Camera11.networkLoss",
				inport=self.detmodel.getSystem().getCamera11().getSoftware().getImages(),
				calls={'ImageOut' : [self.detmodel.getSystem().getCamera11().getNetworkQueue().getImageIn(), ], 'LostImages' : [self.detmodel.getSystem().getCamera11().getNetworkQueue().getImageLoss(), ]},
				portarray=["ImageOut", "LostImages"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						torch.tensor(0.9), 
										torch.tensor(0.1)
								])),
					name="NetworkLoss44")
				,
				simulator=self,
				shname="networkLoss",
				compCall=self.detmodel.getSystem().getCamera11())
				
		self.components["System.Camera12.networkLoss"].configure(
				name  = "System.Camera12.networkLoss",
				inport=self.detmodel.getSystem().getCamera12().getSoftware().getImages(),
				calls={'ImageOut' : [self.detmodel.getSystem().getCamera12().getNetworkQueue().getImageIn(), ], 'LostImages' : [self.detmodel.getSystem().getCamera12().getNetworkQueue().getImageLoss(), ]},
				portarray=["ImageOut", "LostImages"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						torch.tensor(0.9), 
										torch.tensor(0.1)
								])),
					name="NetworkLoss45")
				,
				simulator=self,
				shname="networkLoss",
				compCall=self.detmodel.getSystem().getCamera12())
				
		self.components["System.Camera13.networkLoss"].configure(
				name  = "System.Camera13.networkLoss",
				inport=self.detmodel.getSystem().getCamera13().getSoftware().getImages(),
				calls={'ImageOut' : [self.detmodel.getSystem().getCamera13().getNetworkQueue().getImageIn(), ], 'LostImages' : [self.detmodel.getSystem().getCamera13().getNetworkQueue().getImageLoss(), ]},
				portarray=["ImageOut", "LostImages"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						torch.tensor(0.9), 
										torch.tensor(0.1)
								])),
					name="NetworkLoss46")
				,
				simulator=self,
				shname="networkLoss",
				compCall=self.detmodel.getSystem().getCamera13())
				
		self.components["System.Camera14.networkLoss"].configure(
				name  = "System.Camera14.networkLoss",
				inport=self.detmodel.getSystem().getCamera14().getSoftware().getImages(),
				calls={'ImageOut' : [self.detmodel.getSystem().getCamera14().getNetworkQueue().getImageIn(), ], 'LostImages' : [self.detmodel.getSystem().getCamera14().getNetworkQueue().getImageLoss(), ]},
				portarray=["ImageOut", "LostImages"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						torch.tensor(0.9), 
										torch.tensor(0.1)
								])),
					name="NetworkLoss47")
				,
				simulator=self,
				shname="networkLoss",
				compCall=self.detmodel.getSystem().getCamera14())
				
		self.components["System.Camera15.networkLoss"].configure(
				name  = "System.Camera15.networkLoss",
				inport=self.detmodel.getSystem().getCamera15().getSoftware().getImages(),
				calls={'ImageOut' : [self.detmodel.getSystem().getCamera15().getNetworkQueue().getImageIn(), ], 'LostImages' : [self.detmodel.getSystem().getCamera15().getNetworkQueue().getImageLoss(), ]},
				portarray=["ImageOut", "LostImages"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						torch.tensor(0.9), 
										torch.tensor(0.1)
								])),
					name="NetworkLoss48")
				,
				simulator=self,
				shname="networkLoss",
				compCall=self.detmodel.getSystem().getCamera15())
				
		self.components["System.Camera16.networkLoss"].configure(
				name  = "System.Camera16.networkLoss",
				inport=self.detmodel.getSystem().getCamera16().getSoftware().getImages(),
				calls={'ImageOut' : [self.detmodel.getSystem().getCamera16().getNetworkQueue().getImageIn(), ], 'LostImages' : [self.detmodel.getSystem().getCamera16().getNetworkQueue().getImageLoss(), ]},
				portarray=["ImageOut", "LostImages"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						torch.tensor(0.9), 
										torch.tensor(0.1)
								])),
					name="NetworkLoss49")
				,
				simulator=self,
				shname="networkLoss",
				compCall=self.detmodel.getSystem().getCamera16())
				
		self.components["System.Camera17.networkLoss"].configure(
				name  = "System.Camera17.networkLoss",
				inport=self.detmodel.getSystem().getCamera17().getSoftware().getImages(),
				calls={'ImageOut' : [self.detmodel.getSystem().getCamera17().getNetworkQueue().getImageIn(), ], 'LostImages' : [self.detmodel.getSystem().getCamera17().getNetworkQueue().getImageLoss(), ]},
				portarray=["ImageOut", "LostImages"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						torch.tensor(0.9), 
										torch.tensor(0.1)
								])),
					name="NetworkLoss50")
				,
				simulator=self,
				shname="networkLoss",
				compCall=self.detmodel.getSystem().getCamera17())
				
		self.components["System.Camera18.networkLoss"].configure(
				name  = "System.Camera18.networkLoss",
				inport=self.detmodel.getSystem().getCamera18().getSoftware().getImages(),
				calls={'ImageOut' : [self.detmodel.getSystem().getCamera18().getNetworkQueue().getImageIn(), ], 'LostImages' : [self.detmodel.getSystem().getCamera18().getNetworkQueue().getImageLoss(), ]},
				portarray=["ImageOut", "LostImages"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						torch.tensor(0.9), 
										torch.tensor(0.1)
								])),
					name="NetworkLoss51")
				,
				simulator=self,
				shname="networkLoss",
				compCall=self.detmodel.getSystem().getCamera18())
				
		self.components["System.Camera19.networkLoss"].configure(
				name  = "System.Camera19.networkLoss",
				inport=self.detmodel.getSystem().getCamera19().getSoftware().getImages(),
				calls={'ImageOut' : [self.detmodel.getSystem().getCamera19().getNetworkQueue().getImageIn(), ], 'LostImages' : [self.detmodel.getSystem().getCamera19().getNetworkQueue().getImageLoss(), ]},
				portarray=["ImageOut", "LostImages"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						torch.tensor(0.9), 
										torch.tensor(0.1)
								])),
					name="NetworkLoss52")
				,
				simulator=self,
				shname="networkLoss",
				compCall=self.detmodel.getSystem().getCamera19())
				
		self.components["System.Camera20.networkLoss"].configure(
				name  = "System.Camera20.networkLoss",
				inport=self.detmodel.getSystem().getCamera20().getSoftware().getImages(),
				calls={'ImageOut' : [self.detmodel.getSystem().getCamera20().getNetworkQueue().getImageIn(), ], 'LostImages' : [self.detmodel.getSystem().getCamera20().getNetworkQueue().getImageLoss(), ]},
				portarray=["ImageOut", "LostImages"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						torch.tensor(0.9), 
										torch.tensor(0.1)
								])),
					name="NetworkLoss53")
				,
				simulator=self,
				shname="networkLoss",
				compCall=self.detmodel.getSystem().getCamera20())
				
		self.components["System.Camera21.networkLoss"].configure(
				name  = "System.Camera21.networkLoss",
				inport=self.detmodel.getSystem().getCamera21().getSoftware().getImages(),
				calls={'ImageOut' : [self.detmodel.getSystem().getCamera21().getNetworkQueue().getImageIn(), ], 'LostImages' : [self.detmodel.getSystem().getCamera21().getNetworkQueue().getImageLoss(), ]},
				portarray=["ImageOut", "LostImages"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						torch.tensor(0.9), 
										torch.tensor(0.1)
								])),
					name="NetworkLoss54")
				,
				simulator=self,
				shname="networkLoss",
				compCall=self.detmodel.getSystem().getCamera21())
				
		self.components["System.Camera22.networkLoss"].configure(
				name  = "System.Camera22.networkLoss",
				inport=self.detmodel.getSystem().getCamera22().getSoftware().getImages(),
				calls={'ImageOut' : [self.detmodel.getSystem().getCamera22().getNetworkQueue().getImageIn(), ], 'LostImages' : [self.detmodel.getSystem().getCamera22().getNetworkQueue().getImageLoss(), ]},
				portarray=["ImageOut", "LostImages"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						torch.tensor(0.9), 
										torch.tensor(0.1)
								])),
					name="NetworkLoss55")
				,
				simulator=self,
				shname="networkLoss",
				compCall=self.detmodel.getSystem().getCamera22())
				
		self.components["System.Camera23.networkLoss"].configure(
				name  = "System.Camera23.networkLoss",
				inport=self.detmodel.getSystem().getCamera23().getSoftware().getImages(),
				calls={'ImageOut' : [self.detmodel.getSystem().getCamera23().getNetworkQueue().getImageIn(), ], 'LostImages' : [self.detmodel.getSystem().getCamera23().getNetworkQueue().getImageLoss(), ]},
				portarray=["ImageOut", "LostImages"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						torch.tensor(0.9), 
										torch.tensor(0.1)
								])),
					name="NetworkLoss56")
				,
				simulator=self,
				shname="networkLoss",
				compCall=self.detmodel.getSystem().getCamera23())
				
		self.components["System.Camera24.networkLoss"].configure(
				name  = "System.Camera24.networkLoss",
				inport=self.detmodel.getSystem().getCamera24().getSoftware().getImages(),
				calls={'ImageOut' : [self.detmodel.getSystem().getCamera24().getNetworkQueue().getImageIn(), ], 'LostImages' : [self.detmodel.getSystem().getCamera24().getNetworkQueue().getImageLoss(), ]},
				portarray=["ImageOut", "LostImages"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						torch.tensor(0.9), 
										torch.tensor(0.1)
								])),
					name="NetworkLoss57")
				,
				simulator=self,
				shname="networkLoss",
				compCall=self.detmodel.getSystem().getCamera24())
				
		self.components["System.Camera25.networkLoss"].configure(
				name  = "System.Camera25.networkLoss",
				inport=self.detmodel.getSystem().getCamera25().getSoftware().getImages(),
				calls={'ImageOut' : [self.detmodel.getSystem().getCamera25().getNetworkQueue().getImageIn(), ], 'LostImages' : [self.detmodel.getSystem().getCamera25().getNetworkQueue().getImageLoss(), ]},
				portarray=["ImageOut", "LostImages"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						torch.tensor(0.9), 
										torch.tensor(0.1)
								])),
					name="NetworkLoss58")
				,
				simulator=self,
				shname="networkLoss",
				compCall=self.detmodel.getSystem().getCamera25())
				
		self.components["System.Camera26.networkLoss"].configure(
				name  = "System.Camera26.networkLoss",
				inport=self.detmodel.getSystem().getCamera26().getSoftware().getImages(),
				calls={'ImageOut' : [self.detmodel.getSystem().getCamera26().getNetworkQueue().getImageIn(), ], 'LostImages' : [self.detmodel.getSystem().getCamera26().getNetworkQueue().getImageLoss(), ]},
				portarray=["ImageOut", "LostImages"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						torch.tensor(0.9), 
										torch.tensor(0.1)
								])),
					name="NetworkLoss59")
				,
				simulator=self,
				shname="networkLoss",
				compCall=self.detmodel.getSystem().getCamera26())
				
		self.components["System.Camera27.networkLoss"].configure(
				name  = "System.Camera27.networkLoss",
				inport=self.detmodel.getSystem().getCamera27().getSoftware().getImages(),
				calls={'ImageOut' : [self.detmodel.getSystem().getCamera27().getNetworkQueue().getImageIn(), ], 'LostImages' : [self.detmodel.getSystem().getCamera27().getNetworkQueue().getImageLoss(), ]},
				portarray=["ImageOut", "LostImages"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						torch.tensor(0.9), 
										torch.tensor(0.1)
								])),
					name="NetworkLoss60")
				,
				simulator=self,
				shname="networkLoss",
				compCall=self.detmodel.getSystem().getCamera27())
				
		self.components["System.Camera28.networkLoss"].configure(
				name  = "System.Camera28.networkLoss",
				inport=self.detmodel.getSystem().getCamera28().getSoftware().getImages(),
				calls={'ImageOut' : [self.detmodel.getSystem().getCamera28().getNetworkQueue().getImageIn(), ], 'LostImages' : [self.detmodel.getSystem().getCamera28().getNetworkQueue().getImageLoss(), ]},
				portarray=["ImageOut", "LostImages"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						torch.tensor(0.9), 
										torch.tensor(0.1)
								])),
					name="NetworkLoss61")
				,
				simulator=self,
				shname="networkLoss",
				compCall=self.detmodel.getSystem().getCamera28())
				
		self.components["System.Camera29.networkLoss"].configure(
				name  = "System.Camera29.networkLoss",
				inport=self.detmodel.getSystem().getCamera29().getSoftware().getImages(),
				calls={'ImageOut' : [self.detmodel.getSystem().getCamera29().getNetworkQueue().getImageIn(), ], 'LostImages' : [self.detmodel.getSystem().getCamera29().getNetworkQueue().getImageLoss(), ]},
				portarray=["ImageOut", "LostImages"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						torch.tensor(0.9), 
										torch.tensor(0.1)
								])),
					name="NetworkLoss62")
				,
				simulator=self,
				shname="networkLoss",
				compCall=self.detmodel.getSystem().getCamera29())
				
		self.components["System.Camera30.networkLoss"].configure(
				name  = "System.Camera30.networkLoss",
				inport=self.detmodel.getSystem().getCamera30().getSoftware().getImages(),
				calls={'ImageOut' : [self.detmodel.getSystem().getCamera30().getNetworkQueue().getImageIn(), ], 'LostImages' : [self.detmodel.getSystem().getCamera30().getNetworkQueue().getImageLoss(), ]},
				portarray=["ImageOut", "LostImages"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						torch.tensor(0.9), 
										torch.tensor(0.1)
								])),
					name="NetworkLoss63")
				,
				simulator=self,
				shname="networkLoss",
				compCall=self.detmodel.getSystem().getCamera30())
				
		self.components["System.Camera31.networkLoss"].configure(
				name  = "System.Camera31.networkLoss",
				inport=self.detmodel.getSystem().getCamera31().getSoftware().getImages(),
				calls={'ImageOut' : [self.detmodel.getSystem().getCamera31().getNetworkQueue().getImageIn(), ], 'LostImages' : [self.detmodel.getSystem().getCamera31().getNetworkQueue().getImageLoss(), ]},
				portarray=["ImageOut", "LostImages"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						torch.tensor(0.9), 
										torch.tensor(0.1)
								])),
					name="NetworkLoss64")
				,
				simulator=self,
				shname="networkLoss",
				compCall=self.detmodel.getSystem().getCamera31())
				
		self.components["System.Camera32.networkLoss"].configure(
				name  = "System.Camera32.networkLoss",
				inport=self.detmodel.getSystem().getCamera32().getSoftware().getImages(),
				calls={'ImageOut' : [self.detmodel.getSystem().getCamera32().getNetworkQueue().getImageIn(), ], 'LostImages' : [self.detmodel.getSystem().getCamera32().getNetworkQueue().getImageLoss(), ]},
				portarray=["ImageOut", "LostImages"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						torch.tensor(0.9), 
										torch.tensor(0.1)
								])),
					name="NetworkLoss65")
				,
				simulator=self,
				shname="networkLoss",
				compCall=self.detmodel.getSystem().getCamera32())
				
		
		self.components["System.Camera1_.Software.CameraSoftware.imageBlur"].configure(
				name  = "System.Camera1_.Software.CameraSoftware.imageBlur",
				inport=self.detmodel.getSystem().getCamera1_().getSoftware().getCameraSoftware().getCameraDriver().getImages(),
				calls = {'ImageOut' : [self.detmodel.getSystem().getCamera1_().getSoftware().getCameraSoftware().getCameraControl().getDriverImages(), ]},
				rules = {'ImageOut' : {'NewData' : {'blurred' : RandomVariable(pyro.distributions.Bernoulli(torch.tensor(0.5)),"DiscRandomVarriableimageBlur66")}}},
				simulator=self,
				shname="imageBlur",
				compCall=self.detmodel.getSystem().getCamera1_().getSoftware().getCameraSoftware()
				)
				
		self.components["System.Camera2_.Software.CameraSoftware.imageBlur"].configure(
				name  = "System.Camera2_.Software.CameraSoftware.imageBlur",
				inport=self.detmodel.getSystem().getCamera2_().getSoftware().getCameraSoftware().getCameraDriver().getImages(),
				calls = {'ImageOut' : [self.detmodel.getSystem().getCamera2_().getSoftware().getCameraSoftware().getCameraControl().getDriverImages(), ]},
				rules = {'ImageOut' : {'NewData' : {'blurred' : RandomVariable(pyro.distributions.Bernoulli(torch.tensor(0.5)),"DiscRandomVarriableimageBlur67")}}},
				simulator=self,
				shname="imageBlur",
				compCall=self.detmodel.getSystem().getCamera2_().getSoftware().getCameraSoftware()
				)
				
		self.components["System.Camera3_.Software.CameraSoftware.imageBlur"].configure(
				name  = "System.Camera3_.Software.CameraSoftware.imageBlur",
				inport=self.detmodel.getSystem().getCamera3_().getSoftware().getCameraSoftware().getCameraDriver().getImages(),
				calls = {'ImageOut' : [self.detmodel.getSystem().getCamera3_().getSoftware().getCameraSoftware().getCameraControl().getDriverImages(), ]},
				rules = {'ImageOut' : {'NewData' : {'blurred' : RandomVariable(pyro.distributions.Bernoulli(torch.tensor(0.5)),"DiscRandomVarriableimageBlur68")}}},
				simulator=self,
				shname="imageBlur",
				compCall=self.detmodel.getSystem().getCamera3_().getSoftware().getCameraSoftware()
				)
				
		self.components["System.Camera4_.Software.CameraSoftware.imageBlur"].configure(
				name  = "System.Camera4_.Software.CameraSoftware.imageBlur",
				inport=self.detmodel.getSystem().getCamera4_().getSoftware().getCameraSoftware().getCameraDriver().getImages(),
				calls = {'ImageOut' : [self.detmodel.getSystem().getCamera4_().getSoftware().getCameraSoftware().getCameraControl().getDriverImages(), ]},
				rules = {'ImageOut' : {'NewData' : {'blurred' : RandomVariable(pyro.distributions.Bernoulli(torch.tensor(0.5)),"DiscRandomVarriableimageBlur69")}}},
				simulator=self,
				shname="imageBlur",
				compCall=self.detmodel.getSystem().getCamera4_().getSoftware().getCameraSoftware()
				)
				
		self.components["System.Camera5_.Software.CameraSoftware.imageBlur"].configure(
				name  = "System.Camera5_.Software.CameraSoftware.imageBlur",
				inport=self.detmodel.getSystem().getCamera5_().getSoftware().getCameraSoftware().getCameraDriver().getImages(),
				calls = {'ImageOut' : [self.detmodel.getSystem().getCamera5_().getSoftware().getCameraSoftware().getCameraControl().getDriverImages(), ]},
				rules = {'ImageOut' : {'NewData' : {'blurred' : RandomVariable(pyro.distributions.Bernoulli(torch.tensor(0.5)),"DiscRandomVarriableimageBlur70")}}},
				simulator=self,
				shname="imageBlur",
				compCall=self.detmodel.getSystem().getCamera5_().getSoftware().getCameraSoftware()
				)
				
		self.components["System.Camera6_.Software.CameraSoftware.imageBlur"].configure(
				name  = "System.Camera6_.Software.CameraSoftware.imageBlur",
				inport=self.detmodel.getSystem().getCamera6_().getSoftware().getCameraSoftware().getCameraDriver().getImages(),
				calls = {'ImageOut' : [self.detmodel.getSystem().getCamera6_().getSoftware().getCameraSoftware().getCameraControl().getDriverImages(), ]},
				rules = {'ImageOut' : {'NewData' : {'blurred' : RandomVariable(pyro.distributions.Bernoulli(torch.tensor(0.5)),"DiscRandomVarriableimageBlur71")}}},
				simulator=self,
				shname="imageBlur",
				compCall=self.detmodel.getSystem().getCamera6_().getSoftware().getCameraSoftware()
				)
				
		self.components["System.Camera7_.Software.CameraSoftware.imageBlur"].configure(
				name  = "System.Camera7_.Software.CameraSoftware.imageBlur",
				inport=self.detmodel.getSystem().getCamera7_().getSoftware().getCameraSoftware().getCameraDriver().getImages(),
				calls = {'ImageOut' : [self.detmodel.getSystem().getCamera7_().getSoftware().getCameraSoftware().getCameraControl().getDriverImages(), ]},
				rules = {'ImageOut' : {'NewData' : {'blurred' : RandomVariable(pyro.distributions.Bernoulli(torch.tensor(0.5)),"DiscRandomVarriableimageBlur72")}}},
				simulator=self,
				shname="imageBlur",
				compCall=self.detmodel.getSystem().getCamera7_().getSoftware().getCameraSoftware()
				)
				
		self.components["System.Camera8_.Software.CameraSoftware.imageBlur"].configure(
				name  = "System.Camera8_.Software.CameraSoftware.imageBlur",
				inport=self.detmodel.getSystem().getCamera8_().getSoftware().getCameraSoftware().getCameraDriver().getImages(),
				calls = {'ImageOut' : [self.detmodel.getSystem().getCamera8_().getSoftware().getCameraSoftware().getCameraControl().getDriverImages(), ]},
				rules = {'ImageOut' : {'NewData' : {'blurred' : RandomVariable(pyro.distributions.Bernoulli(torch.tensor(0.5)),"DiscRandomVarriableimageBlur73")}}},
				simulator=self,
				shname="imageBlur",
				compCall=self.detmodel.getSystem().getCamera8_().getSoftware().getCameraSoftware()
				)
				
		self.components["System.Camera9_.Software.CameraSoftware.imageBlur"].configure(
				name  = "System.Camera9_.Software.CameraSoftware.imageBlur",
				inport=self.detmodel.getSystem().getCamera9_().getSoftware().getCameraSoftware().getCameraDriver().getImages(),
				calls = {'ImageOut' : [self.detmodel.getSystem().getCamera9_().getSoftware().getCameraSoftware().getCameraControl().getDriverImages(), ]},
				rules = {'ImageOut' : {'NewData' : {'blurred' : RandomVariable(pyro.distributions.Bernoulli(torch.tensor(0.5)),"DiscRandomVarriableimageBlur74")}}},
				simulator=self,
				shname="imageBlur",
				compCall=self.detmodel.getSystem().getCamera9_().getSoftware().getCameraSoftware()
				)
				
		self.components["System.Camera10.Software.CameraSoftware.imageBlur"].configure(
				name  = "System.Camera10.Software.CameraSoftware.imageBlur",
				inport=self.detmodel.getSystem().getCamera10().getSoftware().getCameraSoftware().getCameraDriver().getImages(),
				calls = {'ImageOut' : [self.detmodel.getSystem().getCamera10().getSoftware().getCameraSoftware().getCameraControl().getDriverImages(), ]},
				rules = {'ImageOut' : {'NewData' : {'blurred' : RandomVariable(pyro.distributions.Bernoulli(torch.tensor(0.5)),"DiscRandomVarriableimageBlur75")}}},
				simulator=self,
				shname="imageBlur",
				compCall=self.detmodel.getSystem().getCamera10().getSoftware().getCameraSoftware()
				)
				
		self.components["System.Camera11.Software.CameraSoftware.imageBlur"].configure(
				name  = "System.Camera11.Software.CameraSoftware.imageBlur",
				inport=self.detmodel.getSystem().getCamera11().getSoftware().getCameraSoftware().getCameraDriver().getImages(),
				calls = {'ImageOut' : [self.detmodel.getSystem().getCamera11().getSoftware().getCameraSoftware().getCameraControl().getDriverImages(), ]},
				rules = {'ImageOut' : {'NewData' : {'blurred' : RandomVariable(pyro.distributions.Bernoulli(torch.tensor(0.5)),"DiscRandomVarriableimageBlur76")}}},
				simulator=self,
				shname="imageBlur",
				compCall=self.detmodel.getSystem().getCamera11().getSoftware().getCameraSoftware()
				)
				
		self.components["System.Camera12.Software.CameraSoftware.imageBlur"].configure(
				name  = "System.Camera12.Software.CameraSoftware.imageBlur",
				inport=self.detmodel.getSystem().getCamera12().getSoftware().getCameraSoftware().getCameraDriver().getImages(),
				calls = {'ImageOut' : [self.detmodel.getSystem().getCamera12().getSoftware().getCameraSoftware().getCameraControl().getDriverImages(), ]},
				rules = {'ImageOut' : {'NewData' : {'blurred' : RandomVariable(pyro.distributions.Bernoulli(torch.tensor(0.5)),"DiscRandomVarriableimageBlur77")}}},
				simulator=self,
				shname="imageBlur",
				compCall=self.detmodel.getSystem().getCamera12().getSoftware().getCameraSoftware()
				)
				
		self.components["System.Camera13.Software.CameraSoftware.imageBlur"].configure(
				name  = "System.Camera13.Software.CameraSoftware.imageBlur",
				inport=self.detmodel.getSystem().getCamera13().getSoftware().getCameraSoftware().getCameraDriver().getImages(),
				calls = {'ImageOut' : [self.detmodel.getSystem().getCamera13().getSoftware().getCameraSoftware().getCameraControl().getDriverImages(), ]},
				rules = {'ImageOut' : {'NewData' : {'blurred' : RandomVariable(pyro.distributions.Bernoulli(torch.tensor(0.5)),"DiscRandomVarriableimageBlur78")}}},
				simulator=self,
				shname="imageBlur",
				compCall=self.detmodel.getSystem().getCamera13().getSoftware().getCameraSoftware()
				)
				
		self.components["System.Camera14.Software.CameraSoftware.imageBlur"].configure(
				name  = "System.Camera14.Software.CameraSoftware.imageBlur",
				inport=self.detmodel.getSystem().getCamera14().getSoftware().getCameraSoftware().getCameraDriver().getImages(),
				calls = {'ImageOut' : [self.detmodel.getSystem().getCamera14().getSoftware().getCameraSoftware().getCameraControl().getDriverImages(), ]},
				rules = {'ImageOut' : {'NewData' : {'blurred' : RandomVariable(pyro.distributions.Bernoulli(torch.tensor(0.5)),"DiscRandomVarriableimageBlur79")}}},
				simulator=self,
				shname="imageBlur",
				compCall=self.detmodel.getSystem().getCamera14().getSoftware().getCameraSoftware()
				)
				
		self.components["System.Camera15.Software.CameraSoftware.imageBlur"].configure(
				name  = "System.Camera15.Software.CameraSoftware.imageBlur",
				inport=self.detmodel.getSystem().getCamera15().getSoftware().getCameraSoftware().getCameraDriver().getImages(),
				calls = {'ImageOut' : [self.detmodel.getSystem().getCamera15().getSoftware().getCameraSoftware().getCameraControl().getDriverImages(), ]},
				rules = {'ImageOut' : {'NewData' : {'blurred' : RandomVariable(pyro.distributions.Bernoulli(torch.tensor(0.5)),"DiscRandomVarriableimageBlur80")}}},
				simulator=self,
				shname="imageBlur",
				compCall=self.detmodel.getSystem().getCamera15().getSoftware().getCameraSoftware()
				)
				
		self.components["System.Camera16.Software.CameraSoftware.imageBlur"].configure(
				name  = "System.Camera16.Software.CameraSoftware.imageBlur",
				inport=self.detmodel.getSystem().getCamera16().getSoftware().getCameraSoftware().getCameraDriver().getImages(),
				calls = {'ImageOut' : [self.detmodel.getSystem().getCamera16().getSoftware().getCameraSoftware().getCameraControl().getDriverImages(), ]},
				rules = {'ImageOut' : {'NewData' : {'blurred' : RandomVariable(pyro.distributions.Bernoulli(torch.tensor(0.5)),"DiscRandomVarriableimageBlur81")}}},
				simulator=self,
				shname="imageBlur",
				compCall=self.detmodel.getSystem().getCamera16().getSoftware().getCameraSoftware()
				)
				
		self.components["System.Camera17.Software.CameraSoftware.imageBlur"].configure(
				name  = "System.Camera17.Software.CameraSoftware.imageBlur",
				inport=self.detmodel.getSystem().getCamera17().getSoftware().getCameraSoftware().getCameraDriver().getImages(),
				calls = {'ImageOut' : [self.detmodel.getSystem().getCamera17().getSoftware().getCameraSoftware().getCameraControl().getDriverImages(), ]},
				rules = {'ImageOut' : {'NewData' : {'blurred' : RandomVariable(pyro.distributions.Bernoulli(torch.tensor(0.5)),"DiscRandomVarriableimageBlur82")}}},
				simulator=self,
				shname="imageBlur",
				compCall=self.detmodel.getSystem().getCamera17().getSoftware().getCameraSoftware()
				)
				
		self.components["System.Camera18.Software.CameraSoftware.imageBlur"].configure(
				name  = "System.Camera18.Software.CameraSoftware.imageBlur",
				inport=self.detmodel.getSystem().getCamera18().getSoftware().getCameraSoftware().getCameraDriver().getImages(),
				calls = {'ImageOut' : [self.detmodel.getSystem().getCamera18().getSoftware().getCameraSoftware().getCameraControl().getDriverImages(), ]},
				rules = {'ImageOut' : {'NewData' : {'blurred' : RandomVariable(pyro.distributions.Bernoulli(torch.tensor(0.5)),"DiscRandomVarriableimageBlur83")}}},
				simulator=self,
				shname="imageBlur",
				compCall=self.detmodel.getSystem().getCamera18().getSoftware().getCameraSoftware()
				)
				
		self.components["System.Camera19.Software.CameraSoftware.imageBlur"].configure(
				name  = "System.Camera19.Software.CameraSoftware.imageBlur",
				inport=self.detmodel.getSystem().getCamera19().getSoftware().getCameraSoftware().getCameraDriver().getImages(),
				calls = {'ImageOut' : [self.detmodel.getSystem().getCamera19().getSoftware().getCameraSoftware().getCameraControl().getDriverImages(), ]},
				rules = {'ImageOut' : {'NewData' : {'blurred' : RandomVariable(pyro.distributions.Bernoulli(torch.tensor(0.5)),"DiscRandomVarriableimageBlur84")}}},
				simulator=self,
				shname="imageBlur",
				compCall=self.detmodel.getSystem().getCamera19().getSoftware().getCameraSoftware()
				)
				
		self.components["System.Camera20.Software.CameraSoftware.imageBlur"].configure(
				name  = "System.Camera20.Software.CameraSoftware.imageBlur",
				inport=self.detmodel.getSystem().getCamera20().getSoftware().getCameraSoftware().getCameraDriver().getImages(),
				calls = {'ImageOut' : [self.detmodel.getSystem().getCamera20().getSoftware().getCameraSoftware().getCameraControl().getDriverImages(), ]},
				rules = {'ImageOut' : {'NewData' : {'blurred' : RandomVariable(pyro.distributions.Bernoulli(torch.tensor(0.5)),"DiscRandomVarriableimageBlur85")}}},
				simulator=self,
				shname="imageBlur",
				compCall=self.detmodel.getSystem().getCamera20().getSoftware().getCameraSoftware()
				)
				
		self.components["System.Camera21.Software.CameraSoftware.imageBlur"].configure(
				name  = "System.Camera21.Software.CameraSoftware.imageBlur",
				inport=self.detmodel.getSystem().getCamera21().getSoftware().getCameraSoftware().getCameraDriver().getImages(),
				calls = {'ImageOut' : [self.detmodel.getSystem().getCamera21().getSoftware().getCameraSoftware().getCameraControl().getDriverImages(), ]},
				rules = {'ImageOut' : {'NewData' : {'blurred' : RandomVariable(pyro.distributions.Bernoulli(torch.tensor(0.5)),"DiscRandomVarriableimageBlur86")}}},
				simulator=self,
				shname="imageBlur",
				compCall=self.detmodel.getSystem().getCamera21().getSoftware().getCameraSoftware()
				)
				
		self.components["System.Camera22.Software.CameraSoftware.imageBlur"].configure(
				name  = "System.Camera22.Software.CameraSoftware.imageBlur",
				inport=self.detmodel.getSystem().getCamera22().getSoftware().getCameraSoftware().getCameraDriver().getImages(),
				calls = {'ImageOut' : [self.detmodel.getSystem().getCamera22().getSoftware().getCameraSoftware().getCameraControl().getDriverImages(), ]},
				rules = {'ImageOut' : {'NewData' : {'blurred' : RandomVariable(pyro.distributions.Bernoulli(torch.tensor(0.5)),"DiscRandomVarriableimageBlur87")}}},
				simulator=self,
				shname="imageBlur",
				compCall=self.detmodel.getSystem().getCamera22().getSoftware().getCameraSoftware()
				)
				
		self.components["System.Camera23.Software.CameraSoftware.imageBlur"].configure(
				name  = "System.Camera23.Software.CameraSoftware.imageBlur",
				inport=self.detmodel.getSystem().getCamera23().getSoftware().getCameraSoftware().getCameraDriver().getImages(),
				calls = {'ImageOut' : [self.detmodel.getSystem().getCamera23().getSoftware().getCameraSoftware().getCameraControl().getDriverImages(), ]},
				rules = {'ImageOut' : {'NewData' : {'blurred' : RandomVariable(pyro.distributions.Bernoulli(torch.tensor(0.5)),"DiscRandomVarriableimageBlur88")}}},
				simulator=self,
				shname="imageBlur",
				compCall=self.detmodel.getSystem().getCamera23().getSoftware().getCameraSoftware()
				)
				
		self.components["System.Camera24.Software.CameraSoftware.imageBlur"].configure(
				name  = "System.Camera24.Software.CameraSoftware.imageBlur",
				inport=self.detmodel.getSystem().getCamera24().getSoftware().getCameraSoftware().getCameraDriver().getImages(),
				calls = {'ImageOut' : [self.detmodel.getSystem().getCamera24().getSoftware().getCameraSoftware().getCameraControl().getDriverImages(), ]},
				rules = {'ImageOut' : {'NewData' : {'blurred' : RandomVariable(pyro.distributions.Bernoulli(torch.tensor(0.5)),"DiscRandomVarriableimageBlur89")}}},
				simulator=self,
				shname="imageBlur",
				compCall=self.detmodel.getSystem().getCamera24().getSoftware().getCameraSoftware()
				)
				
		self.components["System.Camera25.Software.CameraSoftware.imageBlur"].configure(
				name  = "System.Camera25.Software.CameraSoftware.imageBlur",
				inport=self.detmodel.getSystem().getCamera25().getSoftware().getCameraSoftware().getCameraDriver().getImages(),
				calls = {'ImageOut' : [self.detmodel.getSystem().getCamera25().getSoftware().getCameraSoftware().getCameraControl().getDriverImages(), ]},
				rules = {'ImageOut' : {'NewData' : {'blurred' : RandomVariable(pyro.distributions.Bernoulli(torch.tensor(0.5)),"DiscRandomVarriableimageBlur90")}}},
				simulator=self,
				shname="imageBlur",
				compCall=self.detmodel.getSystem().getCamera25().getSoftware().getCameraSoftware()
				)
				
		self.components["System.Camera26.Software.CameraSoftware.imageBlur"].configure(
				name  = "System.Camera26.Software.CameraSoftware.imageBlur",
				inport=self.detmodel.getSystem().getCamera26().getSoftware().getCameraSoftware().getCameraDriver().getImages(),
				calls = {'ImageOut' : [self.detmodel.getSystem().getCamera26().getSoftware().getCameraSoftware().getCameraControl().getDriverImages(), ]},
				rules = {'ImageOut' : {'NewData' : {'blurred' : RandomVariable(pyro.distributions.Bernoulli(torch.tensor(0.5)),"DiscRandomVarriableimageBlur91")}}},
				simulator=self,
				shname="imageBlur",
				compCall=self.detmodel.getSystem().getCamera26().getSoftware().getCameraSoftware()
				)
				
		self.components["System.Camera27.Software.CameraSoftware.imageBlur"].configure(
				name  = "System.Camera27.Software.CameraSoftware.imageBlur",
				inport=self.detmodel.getSystem().getCamera27().getSoftware().getCameraSoftware().getCameraDriver().getImages(),
				calls = {'ImageOut' : [self.detmodel.getSystem().getCamera27().getSoftware().getCameraSoftware().getCameraControl().getDriverImages(), ]},
				rules = {'ImageOut' : {'NewData' : {'blurred' : RandomVariable(pyro.distributions.Bernoulli(torch.tensor(0.5)),"DiscRandomVarriableimageBlur92")}}},
				simulator=self,
				shname="imageBlur",
				compCall=self.detmodel.getSystem().getCamera27().getSoftware().getCameraSoftware()
				)
				
		self.components["System.Camera28.Software.CameraSoftware.imageBlur"].configure(
				name  = "System.Camera28.Software.CameraSoftware.imageBlur",
				inport=self.detmodel.getSystem().getCamera28().getSoftware().getCameraSoftware().getCameraDriver().getImages(),
				calls = {'ImageOut' : [self.detmodel.getSystem().getCamera28().getSoftware().getCameraSoftware().getCameraControl().getDriverImages(), ]},
				rules = {'ImageOut' : {'NewData' : {'blurred' : RandomVariable(pyro.distributions.Bernoulli(torch.tensor(0.5)),"DiscRandomVarriableimageBlur93")}}},
				simulator=self,
				shname="imageBlur",
				compCall=self.detmodel.getSystem().getCamera28().getSoftware().getCameraSoftware()
				)
				
		self.components["System.Camera29.Software.CameraSoftware.imageBlur"].configure(
				name  = "System.Camera29.Software.CameraSoftware.imageBlur",
				inport=self.detmodel.getSystem().getCamera29().getSoftware().getCameraSoftware().getCameraDriver().getImages(),
				calls = {'ImageOut' : [self.detmodel.getSystem().getCamera29().getSoftware().getCameraSoftware().getCameraControl().getDriverImages(), ]},
				rules = {'ImageOut' : {'NewData' : {'blurred' : RandomVariable(pyro.distributions.Bernoulli(torch.tensor(0.5)),"DiscRandomVarriableimageBlur94")}}},
				simulator=self,
				shname="imageBlur",
				compCall=self.detmodel.getSystem().getCamera29().getSoftware().getCameraSoftware()
				)
				
		self.components["System.Camera30.Software.CameraSoftware.imageBlur"].configure(
				name  = "System.Camera30.Software.CameraSoftware.imageBlur",
				inport=self.detmodel.getSystem().getCamera30().getSoftware().getCameraSoftware().getCameraDriver().getImages(),
				calls = {'ImageOut' : [self.detmodel.getSystem().getCamera30().getSoftware().getCameraSoftware().getCameraControl().getDriverImages(), ]},
				rules = {'ImageOut' : {'NewData' : {'blurred' : RandomVariable(pyro.distributions.Bernoulli(torch.tensor(0.5)),"DiscRandomVarriableimageBlur95")}}},
				simulator=self,
				shname="imageBlur",
				compCall=self.detmodel.getSystem().getCamera30().getSoftware().getCameraSoftware()
				)
				
		self.components["System.Camera31.Software.CameraSoftware.imageBlur"].configure(
				name  = "System.Camera31.Software.CameraSoftware.imageBlur",
				inport=self.detmodel.getSystem().getCamera31().getSoftware().getCameraSoftware().getCameraDriver().getImages(),
				calls = {'ImageOut' : [self.detmodel.getSystem().getCamera31().getSoftware().getCameraSoftware().getCameraControl().getDriverImages(), ]},
				rules = {'ImageOut' : {'NewData' : {'blurred' : RandomVariable(pyro.distributions.Bernoulli(torch.tensor(0.5)),"DiscRandomVarriableimageBlur96")}}},
				simulator=self,
				shname="imageBlur",
				compCall=self.detmodel.getSystem().getCamera31().getSoftware().getCameraSoftware()
				)
				
		self.components["System.Camera32.Software.CameraSoftware.imageBlur"].configure(
				name  = "System.Camera32.Software.CameraSoftware.imageBlur",
				inport=self.detmodel.getSystem().getCamera32().getSoftware().getCameraSoftware().getCameraDriver().getImages(),
				calls = {'ImageOut' : [self.detmodel.getSystem().getCamera32().getSoftware().getCameraSoftware().getCameraControl().getDriverImages(), ]},
				rules = {'ImageOut' : {'NewData' : {'blurred' : RandomVariable(pyro.distributions.Bernoulli(torch.tensor(0.5)),"DiscRandomVarriableimageBlur97")}}},
				simulator=self,
				shname="imageBlur",
				compCall=self.detmodel.getSystem().getCamera32().getSoftware().getCameraSoftware()
				)
				
		
		

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
	
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(0.2),scale=torch.tensor(0.02)),"ContRandomVarriablesoftwareTimer0")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(0.2),scale=torch.tensor(0.02)),"ContRandomVarriablesoftwareTimer1")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(0.2),scale=torch.tensor(0.02)),"ContRandomVarriablesoftwareTimer2")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(0.2),scale=torch.tensor(0.02)),"ContRandomVarriablesoftwareTimer3")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(0.2),scale=torch.tensor(0.02)),"ContRandomVarriablesoftwareTimer4")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(0.2),scale=torch.tensor(0.02)),"ContRandomVarriablesoftwareTimer5")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(0.2),scale=torch.tensor(0.02)),"ContRandomVarriablesoftwareTimer6")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(0.2),scale=torch.tensor(0.02)),"ContRandomVarriablesoftwareTimer7")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(0.2),scale=torch.tensor(0.02)),"ContRandomVarriablesoftwareTimer8")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(0.2),scale=torch.tensor(0.02)),"ContRandomVarriablesoftwareTimer9")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(0.2),scale=torch.tensor(0.02)),"ContRandomVarriablesoftwareTimer10")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(0.2),scale=torch.tensor(0.02)),"ContRandomVarriablesoftwareTimer11")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(0.2),scale=torch.tensor(0.02)),"ContRandomVarriablesoftwareTimer12")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(0.2),scale=torch.tensor(0.02)),"ContRandomVarriablesoftwareTimer13")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(0.2),scale=torch.tensor(0.02)),"ContRandomVarriablesoftwareTimer14")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(0.2),scale=torch.tensor(0.02)),"ContRandomVarriablesoftwareTimer15")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(0.2),scale=torch.tensor(0.02)),"ContRandomVarriablesoftwareTimer16")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(0.2),scale=torch.tensor(0.02)),"ContRandomVarriablesoftwareTimer17")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(0.2),scale=torch.tensor(0.02)),"ContRandomVarriablesoftwareTimer18")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(0.2),scale=torch.tensor(0.02)),"ContRandomVarriablesoftwareTimer19")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(0.2),scale=torch.tensor(0.02)),"ContRandomVarriablesoftwareTimer20")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(0.2),scale=torch.tensor(0.02)),"ContRandomVarriablesoftwareTimer21")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(0.2),scale=torch.tensor(0.02)),"ContRandomVarriablesoftwareTimer22")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(0.2),scale=torch.tensor(0.02)),"ContRandomVarriablesoftwareTimer23")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(0.2),scale=torch.tensor(0.02)),"ContRandomVarriablesoftwareTimer24")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(0.2),scale=torch.tensor(0.02)),"ContRandomVarriablesoftwareTimer25")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(0.2),scale=torch.tensor(0.02)),"ContRandomVarriablesoftwareTimer26")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(0.2),scale=torch.tensor(0.02)),"ContRandomVarriablesoftwareTimer27")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(0.2),scale=torch.tensor(0.02)),"ContRandomVarriablesoftwareTimer28")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(0.2),scale=torch.tensor(0.02)),"ContRandomVarriablesoftwareTimer29")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(0.2),scale=torch.tensor(0.02)),"ContRandomVarriablesoftwareTimer30")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(0.2),scale=torch.tensor(0.02)),"ContRandomVarriablesoftwareTimer31")]
	dists.append[RandomVariable(pyro.distributions.Exponential(torch.tensor(2.5)),"ContRandomVarriablecarArrival32")]
	dists.append[RandomVariable(pyro.distributions.Normal(loc=torch.tensor(0.3),scale=torch.tensor(0.1)),"ContRandomVarriablecarDelay33")]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="NetworkLoss34")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="NetworkLoss35")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="NetworkLoss36")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="NetworkLoss37")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="NetworkLoss38")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="NetworkLoss39")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="NetworkLoss40")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="NetworkLoss41")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="NetworkLoss42")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="NetworkLoss43")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="NetworkLoss44")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="NetworkLoss45")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="NetworkLoss46")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="NetworkLoss47")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="NetworkLoss48")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="NetworkLoss49")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="NetworkLoss50")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="NetworkLoss51")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="NetworkLoss52")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="NetworkLoss53")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="NetworkLoss54")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="NetworkLoss55")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="NetworkLoss56")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="NetworkLoss57")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="NetworkLoss58")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="NetworkLoss59")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="NetworkLoss60")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="NetworkLoss61")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="NetworkLoss62")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="NetworkLoss63")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="NetworkLoss64")
	]
	dists.append[RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
			torch.tensor(0.9), 
							torch.tensor(0.1)
					])),
		name="NetworkLoss65")
	]
	dists.append[RandomVariable(pyro.distributions.Bernoulli(torch.tensor(0.5)),"DiscRandomVarriableimageBlur66")]
	dists.append[RandomVariable(pyro.distributions.Bernoulli(torch.tensor(0.5)),"DiscRandomVarriableimageBlur67")]
	dists.append[RandomVariable(pyro.distributions.Bernoulli(torch.tensor(0.5)),"DiscRandomVarriableimageBlur68")]
	dists.append[RandomVariable(pyro.distributions.Bernoulli(torch.tensor(0.5)),"DiscRandomVarriableimageBlur69")]
	dists.append[RandomVariable(pyro.distributions.Bernoulli(torch.tensor(0.5)),"DiscRandomVarriableimageBlur70")]
	dists.append[RandomVariable(pyro.distributions.Bernoulli(torch.tensor(0.5)),"DiscRandomVarriableimageBlur71")]
	dists.append[RandomVariable(pyro.distributions.Bernoulli(torch.tensor(0.5)),"DiscRandomVarriableimageBlur72")]
	dists.append[RandomVariable(pyro.distributions.Bernoulli(torch.tensor(0.5)),"DiscRandomVarriableimageBlur73")]
	dists.append[RandomVariable(pyro.distributions.Bernoulli(torch.tensor(0.5)),"DiscRandomVarriableimageBlur74")]
	dists.append[RandomVariable(pyro.distributions.Bernoulli(torch.tensor(0.5)),"DiscRandomVarriableimageBlur75")]
	dists.append[RandomVariable(pyro.distributions.Bernoulli(torch.tensor(0.5)),"DiscRandomVarriableimageBlur76")]
	dists.append[RandomVariable(pyro.distributions.Bernoulli(torch.tensor(0.5)),"DiscRandomVarriableimageBlur77")]
	dists.append[RandomVariable(pyro.distributions.Bernoulli(torch.tensor(0.5)),"DiscRandomVarriableimageBlur78")]
	dists.append[RandomVariable(pyro.distributions.Bernoulli(torch.tensor(0.5)),"DiscRandomVarriableimageBlur79")]
	dists.append[RandomVariable(pyro.distributions.Bernoulli(torch.tensor(0.5)),"DiscRandomVarriableimageBlur80")]
	dists.append[RandomVariable(pyro.distributions.Bernoulli(torch.tensor(0.5)),"DiscRandomVarriableimageBlur81")]
	dists.append[RandomVariable(pyro.distributions.Bernoulli(torch.tensor(0.5)),"DiscRandomVarriableimageBlur82")]
	dists.append[RandomVariable(pyro.distributions.Bernoulli(torch.tensor(0.5)),"DiscRandomVarriableimageBlur83")]
	dists.append[RandomVariable(pyro.distributions.Bernoulli(torch.tensor(0.5)),"DiscRandomVarriableimageBlur84")]
	dists.append[RandomVariable(pyro.distributions.Bernoulli(torch.tensor(0.5)),"DiscRandomVarriableimageBlur85")]
	dists.append[RandomVariable(pyro.distributions.Bernoulli(torch.tensor(0.5)),"DiscRandomVarriableimageBlur86")]
	dists.append[RandomVariable(pyro.distributions.Bernoulli(torch.tensor(0.5)),"DiscRandomVarriableimageBlur87")]
	dists.append[RandomVariable(pyro.distributions.Bernoulli(torch.tensor(0.5)),"DiscRandomVarriableimageBlur88")]
	dists.append[RandomVariable(pyro.distributions.Bernoulli(torch.tensor(0.5)),"DiscRandomVarriableimageBlur89")]
	dists.append[RandomVariable(pyro.distributions.Bernoulli(torch.tensor(0.5)),"DiscRandomVarriableimageBlur90")]
	dists.append[RandomVariable(pyro.distributions.Bernoulli(torch.tensor(0.5)),"DiscRandomVarriableimageBlur91")]
	dists.append[RandomVariable(pyro.distributions.Bernoulli(torch.tensor(0.5)),"DiscRandomVarriableimageBlur92")]
	dists.append[RandomVariable(pyro.distributions.Bernoulli(torch.tensor(0.5)),"DiscRandomVarriableimageBlur93")]
	dists.append[RandomVariable(pyro.distributions.Bernoulli(torch.tensor(0.5)),"DiscRandomVarriableimageBlur94")]
	dists.append[RandomVariable(pyro.distributions.Bernoulli(torch.tensor(0.5)),"DiscRandomVarriableimageBlur95")]
	dists.append[RandomVariable(pyro.distributions.Bernoulli(torch.tensor(0.5)),"DiscRandomVarriableimageBlur96")]
	dists.append[RandomVariable(pyro.distributions.Bernoulli(torch.tensor(0.5)),"DiscRandomVarriableimageBlur97")]
	
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
	AspectSystem_Failures_NewEventFreq=0
	
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
			if int(detmodel.monitorOfAspectSystem_Failures_NewEvent.freq) != AspectSystem_Failures_NewEventFreq :
				AspectSystem_Failures_NewEventFreq=int(detmodel.monitorOfAspectSystem_Failures_NewEvent.freq)
				dprint(f'detmodel -> analysis : "Failures.newEvent at time {stochmodel.time}"')
		
		if detmodel.monitorOfEndConditionSystem_CarLeave_NewEvent.state != "run":
			# print debug end condition information
			if DEBUG:
				print("End condition is satisfied: ---------------------------------------------")
				print("      EndConditionSystem_CarLeave_NewEvent : ", detmodel.monitorOfEndConditionSystem_CarLeave_NewEvent.state)
				dprint('hnote over analysis ')
				dprint('EndConditionSystem_CarLeave_NewEvent is reached')
				dprint("endnote")
			break
	
	
	#register the result of the analysis to the Pyro
	pyro.deterministic("Failures_newEvent_prob",torch.tensor(state2num(detmodel.monitorOfAspectSystem_Failures_NewEvent.state)))
	
	
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
	return state2num(detmodel.monitorOfAspectSystem_Failures_NewEvent.state)

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
			data_Failures_newEvent_prob =  []
			for i in range(10000):
				Failures_newEvent_prob=simulate()
				data_Failures_newEvent_prob.append(Failures_newEvent_prob)
			t1=time.time()
			# visualize results
			print(f"Analysis is finished in {t1-t0} s")
			print("Results of the analysis: ")
			print("Estimated Failures_newEvent_prob = ",round(stats.mean(data_Failures_newEvent_prob),4))
			print("visualize results...")
			#visualizeMarginal(inference,empirical_marginal_Failures_newEvent_prob,'Failures_newEvent_prob')
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



