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
	sample_num=10000
	bin_num=100
	marginal_samples = torch.stack([torch.abs(marginal()) for _ in range(sample_num)])
	fig, a = plt.subplots()
	a.set_title( "Empirical marginal "+name+" (ESS:"+str(round(inference.get_ESS().item(),2))+", avg:"+str(round(marginal.mean.item(),2))+", stddev:"+str(round(marginal.variance.sqrt().item(),2))+")" )
	a.hist(marginal_samples.numpy(), color='b',bins=bin_num, density=1, label="Marginal of "+name)
	a.set_ylabel("Estimated density")
	a.set_xlabel("Value of "+name)

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


if3 = JClass('hu.bme.mit.gamma.stochastic.casestudy.orion.interfaces.SoftwareTimerInterface$Listener$Provided')
if3_s = JClass('hu.bme.mit.gamma.stochastic.casestudy.orion.scheduling.ElementaryComponentSchedulingInterface')

@JImplements([if3,if3_s])
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
		for callitem in self.calls:
			callEvent=lambda:callitem.raiseNewEvent();
			self.simulator.events.append(Event(self,failureTime,callEvent,self.port+".NewEvent"))
##	class Java:
#		implements = ["hu.bme.mit.gamma.stochastic.casestudy.orion.interfaces.SoftwareTimerInterface$Listener$Provided"]


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
					self.events.append(Event(self,self.simulator.time,callEvent,port+".OrionDisconn"))
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
					self.events.append(Event(self,self.simulator.time,callEvent,port+".OrionDisconnCause"))
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
					self.events.append(Event(self,self.simulator.time,callEvent,port+".OrionConnReq"))
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
					self.events.append(Event(self,self.simulator.time,callEvent,port+".OrionAppData"))
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
					self.events.append(Event(self,self.simulator.time,callEvent,port+".OrionKeepAlive"))
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
					self.events.append(Event(self,self.simulator.time,callEvent,port+".OrionConnConf"))
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
					self.events.append(Event(self,self.simulator.time,callEvent,port+".OrionConnResp"))
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
		#0
		# definition of elementary stochastic components
		
		self.components.clear()
		
		
		self.components.update({ "Orion.DelayKeepAliveReceiveTimeout_3" : DelaySoftwareTimer()})
		self.components.update({ "Orion.DelayKapcsolodik_2" : DelaySoftwareTimer()})
		self.components.update({ "Orion.DelayZarva_0" : DelaySoftwareTimer()})
		self.components.update({ "Orion.DelayKeepAliveSendTimeout_1" : DelaySoftwareTimer()})
		self.components.update({ "Orion.DelayKeepAliveReceiveTimeout_4" : DelaySoftwareTimer()})
		self.components.update({ "Orion.DelayKapcsolodik_3" : DelaySoftwareTimer()})
		self.components.update({ "Orion.DelayKeepAliveSendTimeout_0" : DelaySoftwareTimer()})
		
		
		self.components.update({ "Orion.OrionSystem.Master.masterSlaveChannel" : SwitchStateMachine_Interface_For_Orion()})
		self.components.update({ "Orion.OrionSystem.Master.slaveMasterChannel" : SwitchStateMachine_Interface_For_Orion()})
		
		
		# register input interfaces of elementary stochastic components
		
		
		
		self.components["Orion.DelayKeepAliveReceiveTimeout_3"].configure(
				name  = "Orion.DelayKeepAliveReceiveTimeout_3",
				inport=self.detmodel.getOrion().getOrionSystem().getTimoeutKeepAliveReceiveTimeout_3_req(),
				calls = {'TimoeutKeepAliveReceiveTimeout_3' : [self.detmodel.getOrion().getOrionSystem().getTimoeutKeepAliveReceiveTimeout_3(), ]},
				rules = {'TimoeutKeepAliveReceiveTimeout_3' : {'NewEvent' : ContinuousRandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_320")}},
				simulator=self)
				
		self.components["Orion.DelayKapcsolodik_2"].configure(
				name  = "Orion.DelayKapcsolodik_2",
				inport=self.detmodel.getOrion().getOrionSystem().getTimeoutKapcsolodik_2_req(),
				calls = {'TimeoutKapcsolodik_2' : [self.detmodel.getOrion().getOrionSystem().getTimeoutKapcsolodik_2(), ]},
				rules = {'TimeoutKapcsolodik_2' : {'NewEvent' : ContinuousRandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_221")}},
				simulator=self)
				
		self.components["Orion.DelayZarva_0"].configure(
				name  = "Orion.DelayZarva_0",
				inport=self.detmodel.getOrion().getOrionSystem().getTimeoutZarva_0_req(),
				calls = {'TimeoutZarva_0' : [self.detmodel.getOrion().getOrionSystem().getTimeoutZarva_0(), ]},
				rules = {'TimeoutZarva_0' : {'NewEvent' : ContinuousRandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayZarva_022")}},
				simulator=self)
				
		self.components["Orion.DelayKeepAliveSendTimeout_1"].configure(
				name  = "Orion.DelayKeepAliveSendTimeout_1",
				inport=self.detmodel.getOrion().getOrionSystem().getTimeoutKeepAliveSendTimeout_1_req(),
				calls = {'TimeoutKeepAliveSendTimeout_1' : [self.detmodel.getOrion().getOrionSystem().getTimeoutKeepAliveSendTimeout_1(), ]},
				rules = {'TimeoutKeepAliveSendTimeout_1' : {'NewEvent' : ContinuousRandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_123")}},
				simulator=self)
				
		self.components["Orion.DelayKeepAliveReceiveTimeout_4"].configure(
				name  = "Orion.DelayKeepAliveReceiveTimeout_4",
				inport=self.detmodel.getOrion().getOrionSystem().getTimeoutKeepAliveReceiveTimeout_4_req(),
				calls = {'TimeoutKeepAliveReceiveTimeout_4' : [self.detmodel.getOrion().getOrionSystem().getTimeoutKeepAliveReceiveTimeout_4(), ]},
				rules = {'TimeoutKeepAliveReceiveTimeout_4' : {'NewEvent' : ContinuousRandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveReceiveTimeout_424")}},
				simulator=self)
				
		self.components["Orion.DelayKapcsolodik_3"].configure(
				name  = "Orion.DelayKapcsolodik_3",
				inport=self.detmodel.getOrion().getOrionSystem().getTimeoutKapcsolodik_3_req(),
				calls = {'TimeoutKapcsolodik_3' : [self.detmodel.getOrion().getOrionSystem().getTimeoutKapcsolodik_3(), ]},
				rules = {'TimeoutKapcsolodik_3' : {'NewEvent' : ContinuousRandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKapcsolodik_325")}},
				simulator=self)
				
		self.components["Orion.DelayKeepAliveSendTimeout_0"].configure(
				name  = "Orion.DelayKeepAliveSendTimeout_0",
				inport=self.detmodel.getOrion().getOrionSystem().getTimeoutKeepAliveSendTimeout_0_req(),
				calls = {'TimeoutKeepAliveSendTimeout_0' : [self.detmodel.getOrion().getOrionSystem().getTimeoutKeepAliveSendTimeout_0(), ]},
				rules = {'TimeoutKeepAliveSendTimeout_0' : {'NewEvent' : ContinuousRandomVariable(pyro.distributions.Normal(loc=torch.tensor(5.0),scale=torch.tensor(0.5)),"ContRandomVarriableDelayKeepAliveSendTimeout_026")}},
				simulator=self)
				
		
		self.components["Orion.OrionSystem.Master.masterSlaveChannel"].configure(
				name  = "Orion.OrionSystem.Master.masterSlaveChannel",
				inport=self.detmodel.getOrion().getOrionSystem().getMaster().getMaster().getSend_StateMachine_Port(),
				calls={'Output' : [self.detmodel.getOrion().getOrionSystem().getMaster().getSlave().getStateMachine_Port(), ], 'Lossport' : [None]},
				portarray=["Output", "Lossport"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						0.9, 
										0.1
								])),
					name="MasterSlaveChannel27")
				,
				simulator=self,
				shname="masterSlaveChannel",
				compCall=self.detmodel.getOrion().getOrionSystem().getMaster())
				
		self.components["Orion.OrionSystem.Master.slaveMasterChannel"].configure(
				name  = "Orion.OrionSystem.Master.slaveMasterChannel",
				inport=self.detmodel.getOrion().getOrionSystem().getMaster().getSlave().getSend_StateMachine_Port(),
				calls={'Output' : [self.detmodel.getOrion().getOrionSystem().getMaster().getMaster().getStateMachine_Port(), ], 'Lossport' : [None]},
				portarray=["Output", "Lossport"],
				categorical=RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([
						0.9, 
										0.1
								])),
					name="SlaveMasterChannel28")
				,
				simulator=self,
				shname="slaveMasterChannel",
				compCall=self.detmodel.getOrion().getOrionSystem().getMaster())
				
		
		
		

	def reset(self):
		self.time=0
		self.events.clear()
		for dist in self.dists:
			dist.reset()
		self.detmodel.reset()
		"""self.detmodel.reset()"""

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
		print("New simulation run --------------------------------------------------")
	
	# initialize the stochastic event generator
	stochmodel.reset()
	stochmodel.generateEvents()
	
	if DEBUG:
		print("Initial events: ---------------------------------------------")
		for event in stochmodel.events:
			print("      ESC name: ", event.eventSource.name + "   Event name: " + event.name +'   Time: ' + str(event.eventTime))
		print("Simulation events: ---------------------------------------------")
	
	# run the simulator until there are stochastic events available and simulation time is not reached
	while len(stochmodel.events) > 0 and stochmodel.time < simTime:
		
		# get the event with the earliest clock
		event = stochmodel.popEvent()
		
		# insert the event into the deterministic evaluator
		stochmodel.time = event.eventTime
		
		
		if stochmodel.time > simTime :
			if DEBUG:
				print("End condition is satisfied: ---------------------------------------------")
				print("       Out of time")
			break
		
		# print debug event information
		if DEBUG:
			print("      ESC name: ", event.eventSource.name + "   Event name: " + event.name + '   Time: ' + str(event.eventTime))
			
		# raise the event
		event.eventCall()
		
		# schedule the deterministic evaluator
		detmodel.getOrion().schedule()
		
		# evaluate end condition
		
		if detmodel.monitorOfEndConditionOrion_SystemConnStatus_Conn.state != "run":
			# print debug end condition information
			if DEBUG:
				print("End condition is satisfied: ---------------------------------------------")
				print("      EndConditionOrion_SystemConnStatus_Conn : ", detmodel.monitorOfEndConditionOrion_SystemConnStatus_Conn.state)
			break
	
	
	#register the result of the analysis to the Pyro
	# register the time only if the event is raised
	if str(detmodel.monitorOfAspectOrion_SystemConnStatus_Conn.state) != "run" :
		pyro.deterministic("SystemConnStatus_conn_mt",torch.tensor(stochmodel.time))
	
	
	#register the conditions to the Pyro
	
	if DEBUG:
		print("Simulation is finished! ---------------------------------------------")
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
			print("Estimated SystemConnStatus_conn_mt = ",round(empirical_marginal_SystemConnStatus_conn_mt.mean.item(),4))
			print("visualize results...")
			visualizeMarginal(inference,empirical_marginal_SystemConnStatus_conn_mt,'SystemConnStatus_conn_mt')
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


