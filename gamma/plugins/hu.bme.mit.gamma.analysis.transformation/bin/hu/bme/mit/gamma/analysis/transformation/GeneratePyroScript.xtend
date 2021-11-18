package hu.bme.mit.gamma.analysis.transformation

import hu.bme.mit.gamma.environment.model.EnvironmentCompositeComponent
import hu.bme.mit.gamma.environment.model.EnvironmentComponentInstance
import hu.bme.mit.gamma.environment.model.EnvironmentCompositeComponentInstance
import java.util.Map
import java.util.HashMap
import com.google.common.collect.HashMultimap
import java.util.Random
import hu.bme.mit.gamma.analysis.AnalysisComponent
import hu.bme.mit.gamma.analysis.MeanTime
import hu.bme.mit.gamma.analysis.Frequency
import hu.bme.mit.gamma.environment.model.EnvironmentDelay
import hu.bme.mit.gamma.environment.model.EnvironmentSwitch
import hu.bme.mit.gamma.environment.model.EnvironmentSample
import hu.bme.mit.gamma.environment.model.EnvironmentPeriodicEventSource
import hu.bme.mit.gamma.environment.model.EnvironmentExternSimulation
import hu.bme.mit.gamma.environment.model.EnvironmentEventSource

class GeneratePyroScript {
	
	
	def generate(AnalysisComponent analysis_component,String packageName) {
		var component=analysis_component.analyzedComponent.type
		var stack=<EnvironmentCompositeComponentInstance>newArrayList()
		var connections=ElementaryComponentCollector.collect(analysis_component.analyzedComponent,stack)
		var generator=new ClassGenerator(packageName)
		
		/*var names=new HashMap<RandomEventSourceInstance,String>()
		var counter=0
		var gen=new RandomEventSourceCollector
		var traces=gen.collect(component,<EnvironmentCompositeComponentInstance>newArrayList)
		var source2trace = HashMultimap.<RandomEventSourceInstance,CallTrace>create()
		var sources=traces.map[e|e.sourceInstance]
		for (trace:traces){
			source2trace.put(trace.sourceInstance,trace)
		}
		for (source:sources){
			names.put(source,source2trace.get(source).get(0).name+"_"+(counter).toString)
			counter+=1
		}
		
		var delaynames=new HashMap<RandomDelayInstance,String>()
		var delaycounter=0
		var delaytraces=gen.collectDelays(component,<EnvironmentCompositeComponentInstance>newArrayList)
		var delay2delaytrace = new HashMap<RandomDelayInstance,DelayCallTrace>()
		var delays=delaytraces.map[e|e.sourceInstance]
		for (trace:delaytraces){
			delay2delaytrace.put(trace.sourceInstance,trace)
		}
		for (delay:delays){
			delaynames.put(delay,delay2delaytrace.get(delay).source.name+"_DS_"+(delaycounter).toString)
			delaycounter+=1
		}*/
		'''

import pyro
import torch
from pyro.infer import SVI, Trace_ELBO
from pyro.optim import Adam
import pyro.distributions as dist
import torch.distributions.constraints as constraints
from pyro.distributions.distribution import Distribution

import math
from math import exp
import numpy as np

from py4j.java_gateway import JavaGateway, CallbackServerParameters
import matplotlib.pyplot as plt
import matplotlib

from influxdb import InfluxDBClient

import pyro.contrib.gp as gp

import time
import os
import datetime
import traceback

print("python script is called")

print("creating java Py4J gateway")

os.system("""javac $(find . -name "*.java") -cp /usr/share/java/py4j0.10.8.1.jar""")
os.system("""cd ./bin && java -cp /usr/share/java/py4j0.10.8.1.jar:lib/*:. javaenv.AnalyzerGateway &""")
time.sleep(3)


print("creating python Py4J gateway")

gateway = JavaGateway(callback_server_parameters=CallbackServerParameters())
sctmodel = gateway.entry_point.getEntryPoint()#.getDetModel()


simTime=«Double.toString(analysis_component.simulationTime)»
simNumber=«analysis_component.simulationNumber.toString»

actualTime=0.0


lifetime = 0.001

print("connected to gateway")

DEBUG=False

events=list()


«generator.generateDatasetClass»


# stochastic model classes

«generator.generateContinuousRandomVariableClass»


«generator.generateDiscreteRandomVariableClass»


«generator.generateRandomVariableClass»


«generator.generateGaussProcessClass»


«generator.generateEventClass»


«generator.generateFittedExponentialRandomVariableClass»


«generator.generateFittedNormalRandomVariableClass»


# environment component classes

«generator.generatePeriodicEventSourceClass»


«generator.generateEventSourceClass»


«generator.generateDelayClasses(
	connections.filter[c|c.component instanceof EnvironmentDelay].toList
)»

«generator.generateSwitchClasses(
	connections.filter[c|c.component instanceof EnvironmentSwitch].toList
)»

«generator.generateSampleClasses(
	connections.filter[c|c.component instanceof EnvironmentSample].toList
)»



# environment instances

def generateComponents():	
	components=list()
	
	«generator.generatePeriodicEventSourceInstances(
		connections.filter[c|c.component instanceof EnvironmentPeriodicEventSource].toList
	)»
	
	
	«generator.generateEventSourceInstances(
		connections.filter[c|c.component instanceof EnvironmentEventSource].toList
	)»
	
	
	«generator.generateDelayInstances(
		connections.filter[c|c.component instanceof EnvironmentDelay].toList
	)»
	
	«generator.generateSwitchInstances(
		connections.filter[c|c.component instanceof EnvironmentSwitch].toList
	)»
	
	«generator.generateSampleInstances(
		connections.filter[c|c.component instanceof EnvironmentSample].toList
	)»
	
	«generator.generateEventSourceInstances(
		connections.filter[c|c.component instanceof EnvironmentExternSimulation].toList
	)»
	return components

#test component generation
if DEBUG:
	components=list()
	try:
		components=generateComponents()
	except Exception as err:
		print("Exception occured during testing the simulation: ")
		print(err)
		traceback.print_exc()
		print("shuting down the Py4J gateway")
		gateway.shutdown()
		print("exit")


def collectEvents(events,components):
	for component in components:
		events.extend(component.getEvents())
	filter(lambda f: f.eventTime>=0.0,events)
	events.sort(key=lambda f: f.eventTime)
	
	
def generateEvents(events,components):
	for component in components:
		component.generateEvents()
	


def simulateUntilStop():
	components=generateComponents()
	events=list()
	actualTime=0
	gateway.entry_point.getEntryPoint().reset()
	generateEvents(events,components)
	collectEvents(events,components)
	while len(events)>0 and actualTime<simTime:
		collectEvents(events,components)
		filter(lambda f: f.eventTime>=0.0,events)
		events.sort(key=lambda f: f.eventTime)
		event=events.pop(0)
		actualTime=event.eventTime
		
		if DEBUG:
			print(event.eventSource.name+" at time: "+str(actualTime))
		event.eventCall()
		for i in range(10):
				sctmodel.get«analysis_component.analyzedComponent.name.toFirstUpper»().runCycle()
				
		if gateway.entry_point.getEntryPoint().getState() != "run":
			break
		
	components.clear()
	return actualTime


def simulateUntilTime():
	components=generateComponents()
	events=list()
	actualTime=0
	gateway.entry_point.getEntryPoint().reset()
	generateEvents(events,components)
	collectEvents(events,components)
	while len(events)>0 and actualTime<simTime:
		collectEvents(events,components)
		filter(lambda f: f.eventTime>=0.0,events)
		events.sort(key=lambda f: f.eventTime)
		event=events.pop(0)
		actualTime=event.eventTime
		
		if DEBUG:
			print(event.eventSource.name+" at time: "+str(actualTime))
		event.eventCall()
		for i in range(10):
				sctmodel.get«analysis_component.analyzedComponent.name.toFirstUpper»().runCycle()
	
	components.clear()
	return gateway.entry_point.getEntryPoint().getFreq()


def simulate():
	«IF analysis_component.aspect instanceof MeanTime»
		return simulateUntilStop()
	«ELSEIF analysis_component.aspect instanceof Frequency»
		return simulateUntilTime()
	«ENDIF»

print("testing the simulator")

if DEBUG:
	try:
		for i in range(1):
			print(simulate())
		
		print("start simulator")
		
		prior_data=list()
		for i in range(simNumber):
			if i % 5 == 0:
				print("Simulation step: ", i)
			prior_data.append(simulate())
		
		print("visualize histogram")
		fig, a = plt.subplots()
		a.set_title("simulation results")
		a.hist(prior_data)
		plt.show()
		
		print("simulation has been finished")
		
		
	except Exception as err:
		print("Exception occured during testing the simulation: ")
		print(err)
		traceback.print_exc()
	finally:
		print("shuting down the Py4J gateway")
		gateway.shutdown()
		print("exit")
	
	print("simulation has been finished")
	print("shuting down the Py4J gateway")
	gateway.shutdown()
	print("exit")

'''
	}
}