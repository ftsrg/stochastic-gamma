
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

simTime=3.0
simNumber=50

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
	EntryPoint = JClass('javaenv.ConditionalServiceAvailabilityEntryPoint')
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
		#with RandomVariable.plate:
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


if0 = JClass('hu.bme.mit.gamma.casestudy.iotsystem.interfaces.EventStreamInterface$Listener$Provided')
if0_s = JClass('hu.bme.mit.gamma.casestudy.iotsystem.scheduling.ElementaryComponentSchedulingInterface')

@JImplements([if0,if0_s])
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


if1 = JClass('hu.bme.mit.gamma.casestudy.iotsystem.interfaces.DataStreamInterface$Listener$Provided')
if1_s = JClass('hu.bme.mit.gamma.casestudy.iotsystem.scheduling.ElementaryComponentSchedulingInterface')

@JImplements([if1,if1_s])
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

if2 = JClass('hu.bme.mit.gamma.casestudy.iotsystem.interfaces.DataStreamInterface$Listener$Provided')
if2_s = JClass('hu.bme.mit.gamma.casestudy.iotsystem.scheduling.ElementaryComponentSchedulingInterface')

@JImplements([if2,if2_s])
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
		#hu.bme.mit.gamma.expression.model.impl.DecimalTypeDefinitionImpl@71e4af7b
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
		self.failureProb=torch.tensor([0.000001])
		#0
		#0
		# definition of elementary stochastic components
		
		self.components.clear()
		self.components.update({ "System.Camera1.softwareTimer" : PeriodicEventSource()})
		self.components.update({ "System.Camera2.softwareTimer" : PeriodicEventSource()})
		self.components.update({ "System.Traffic.carArrival" : PeriodicEventSource()})
		
		
		self.components.update({ "System.Traffic.carDelay" : DelayEventStream()})
		
		
		self.components.update({ "System.Camera1.networkLoss" : SwitchDataStream()})
		self.components.update({ "System.Camera2.networkLoss" : SwitchDataStream()})
		
		self.components.update({ "System.Camera1.Software.CameraSoftware.imageBlur" : SampleImageBlur()})
		self.components.update({ "System.Camera2.Software.CameraSoftware.imageBlur" : SampleImageBlur()})
		
		# register input interfaces of elementary stochastic components
		
		self.components["System.Camera1.softwareTimer"].configure(
				name  = "System.Camera1.softwareTimer",
				calls = {'Events' : {'NewEvent' : [(lambda:self.detmodel.getSystem().getCamera1().getSoftware().getSoftwareTimer().raiseNewEvent())]}},
				rules = {'Events' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(0.2),scale=torch.tensor(0.02)),"ContRandomVarriablesoftwareTimer0")}},
				portevents = 	{	"Events" : [ "NewEvent"	]},
				simulator=self)
				
		self.components["System.Camera2.softwareTimer"].configure(
				name  = "System.Camera2.softwareTimer",
				calls = {'Events' : {'NewEvent' : [(lambda:self.detmodel.getSystem().getCamera2().getSoftware().getSoftwareTimer().raiseNewEvent())]}},
				rules = {'Events' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(0.2),scale=torch.tensor(0.02)),"ContRandomVarriablesoftwareTimer1")}},
				portevents = 	{	"Events" : [ "NewEvent"	]},
				simulator=self)
				
		self.components["System.Traffic.carArrival"].configure(
				name  = "System.Traffic.carArrival",
				calls = {'Cars' : {'NewEvent' : [(lambda:self.detmodel.getSystem().getTraffic().getTrafficSct().getCarArrives().raiseNewEvent())]}},
				rules = {'Cars' : {'NewEvent' : RandomVariable(pyro.distributions.Exponential(torch.tensor(2.5)),"ContRandomVarriablecarArrival2")}},
				portevents = 	{	"Cars" : [ "NewEvent"	]},
				simulator=self)
				
		
		
		self.components["System.Traffic.carDelay"].configure(
				name  = "System.Traffic.carDelay",
				inport=self.detmodel.getSystem().getTraffic().getTrafficSct().getCarArrivesOut(),
				calls = {'CarOut' : [self.detmodel.getSystem().getTraffic().getTrafficSct().getCarLeaves(), ]},
				rules = {'CarOut' : {'NewEvent' : RandomVariable(pyro.distributions.Normal(loc=torch.tensor(0.3),scale=torch.tensor(0.1)),"ContRandomVarriablecarDelay3")}},
				simulator=self)
				
		
		self.components["System.Camera1.networkLoss"].configure(
				name  = "System.Camera1.networkLoss",
				inport=self.detmodel.getSystem().getCamera1().getSoftware().getImages(),
				calls={'ImageOut' : [self.detmodel.getSystem().getCamera1().getNetworkQueue().getImageIn(), ], 'LostImages' : [self.detmodel.getSystem().getCamera1().getNetworkQueue().getImageLoss(), ]},
				portarray=["ImageOut", "LostImages"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						torch.tensor(0.9), 
										torch.tensor(0.1)
								])),
					name="NetworkLoss4")
				,
				simulator=self,
				shname="networkLoss",
				compCall=self.detmodel.getSystem().getCamera1())
				
		self.components["System.Camera2.networkLoss"].configure(
				name  = "System.Camera2.networkLoss",
				inport=self.detmodel.getSystem().getCamera2().getSoftware().getImages(),
				calls={'ImageOut' : [self.detmodel.getSystem().getCamera2().getNetworkQueue().getImageIn(), ], 'LostImages' : [self.detmodel.getSystem().getCamera2().getNetworkQueue().getImageLoss(), ]},
				portarray=["ImageOut", "LostImages"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						torch.tensor(0.9), 
										torch.tensor(0.1)
								])),
					name="NetworkLoss5")
				,
				simulator=self,
				shname="networkLoss",
				compCall=self.detmodel.getSystem().getCamera2())
				
		
		self.components["System.Camera1.Software.CameraSoftware.imageBlur"].configure(
				name  = "System.Camera1.Software.CameraSoftware.imageBlur",
				inport=self.detmodel.getSystem().getCamera1().getSoftware().getCameraSoftware().getCameraDriver().getImages(),
				calls = {'ImageOut' : [self.detmodel.getSystem().getCamera1().getSoftware().getCameraSoftware().getCameraControl().getDriverImages(), ]},
				rules = {'ImageOut' : {'NewData' : {'blurred' : RandomVariable(pyro.distributions.Bernoulli(self.failureProb[0]),"DiscRandomVarriableimageBlur6")}}},
				simulator=self,
				shname="imageBlur",
				compCall=self.detmodel.getSystem().getCamera1().getSoftware().getCameraSoftware()
				)
				
		self.components["System.Camera2.Software.CameraSoftware.imageBlur"].configure(
				name  = "System.Camera2.Software.CameraSoftware.imageBlur",
				inport=self.detmodel.getSystem().getCamera2().getSoftware().getCameraSoftware().getCameraDriver().getImages(),
				calls = {'ImageOut' : [self.detmodel.getSystem().getCamera2().getSoftware().getCameraSoftware().getCameraControl().getDriverImages(), ]},
				rules = {'ImageOut' : {'NewData' : {'blurred' : RandomVariable(pyro.distributions.Bernoulli(self.failureProb[0]),"DiscRandomVarriableimageBlur7")}}},
				simulator=self,
				shname="imageBlur",
				compCall=self.detmodel.getSystem().getCamera2().getSoftware().getCameraSoftware()
				)
				
		
		

	def reset(self):
		self.time=0
		self.events.clear()
		self.failureProb[0]=pyro.sample("param_0",pyro.distributions.Uniform(low=torch.tensor(0.0),high=torch.tensor(1.0))).detach()
		#0
		for i in pyro.plate("initial_samples",len(self.dists)):
			self.dists[i].reset()
			#self.dists[i].event_cntr=-1
			#self.dists[i].samples=pyro.sample(self.dists[i].name+"_sample_"+str(0),self.dists[i].dist.expand([40]))
		self.detmodel.reset()
		"""self.detmodel.reset(self.failureProb#1
		)"""

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
	
	
	for dist in dists:
		dist.reset()


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
	dist = pyro.distributions.Bernoulli(torch.tensor(0.90))
	result=state2num(detmodel.monitorOfAspectSystem_Failures_NewEvent.state)
	pyro.sample("analysis result", dist, obs=torch.tensor(result))
	
	#return the result of the simulation
	return state2num(detmodel.monitorOfAspectSystem_Failures_NewEvent.state)

if __name__ == "__main__":
	try:
		# dummy simulations for debugging
		if DEBUG:
			for i in range(10):
				print(simulate())
		else:
			
			# run inference algorithm
			inference=pyro.infer.Importance(model=simulate, num_samples=1000)
			print("run inference algorithm...")
			t0=time.time()
			inference.run()
			empirical_marginal_Failures_newEvent_prob = pyro.infer.EmpiricalMarginal(inference, "Failures_newEvent_prob")
			empirical_marginal_param_0 = pyro.infer.EmpiricalMarginal(inference, "param_0")
			t1=time.time()
			# visualize results
			print(f"Analysis is finished in {t1-t0} s")
			print("Results of the analysis: ")
			print("Estimated Failures_newEvent_prob = ",round(empirical_marginal_Failures_newEvent_prob.mean.item(),4))
			print("Estimated param_0 = ",round(empirical_marginal_param_0.mean.item(),4))
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



