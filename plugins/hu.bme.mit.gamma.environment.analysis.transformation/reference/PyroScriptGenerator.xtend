package hu.bme.mit.gamma.analysis.transformation

import hu.bme.mit.gamma.analysis.AnalysisComponent
import hu.bme.mit.gamma.environment.model.EnvironmentAsynchronousCompositeComponentInstance
import hu.bme.mit.gamma.environment.model.EnvironmentDelay
import hu.bme.mit.gamma.environment.model.EnvironmentEventSource
import hu.bme.mit.gamma.environment.model.EnvironmentExternSimulation
import hu.bme.mit.gamma.environment.model.EnvironmentPeriodicEventSource
import hu.bme.mit.gamma.environment.model.EnvironmentSample
import hu.bme.mit.gamma.environment.model.EnvironmentSwitch
import hu.bme.mit.gamma.environment.model.EnvironmentSynchronousCompositeComponentInstance
import hu.bme.mit.gamma.expression.util.ExpressionEvaluator
import hu.bme.mit.gamma.statechart.composite.ComponentInstance
import hu.bme.mit.gamma.stochastic.stochastic.StochasticExpression

class PyroScriptGenerator {
	
	
	def generate(AnalysisComponent analysis_component,String packageName) {
		var component=(analysis_component.analyzedComponent instanceof EnvironmentAsynchronousCompositeComponentInstance) ? 
			(analysis_component.analyzedComponent as EnvironmentAsynchronousCompositeComponentInstance).type :
			(analysis_component.analyzedComponent as EnvironmentSynchronousCompositeComponentInstance).type
		var stack=<ComponentInstance>newArrayList()
		var connections=ElementaryComponentCollector.collect(analysis_component.analyzedComponent,stack)
		var generator=new PythonClassGenerator(packageName)
		var expEval=ExpressionEvaluator.INSTANCE
		
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

#from py4j.java_gateway import JavaGateway, CallbackServerParameters
import matplotlib.pyplot as plt
import matplotlib

#from influxdb import InfluxDBClient

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

def generateComponents(actualTime):	
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




def collectEvents(events,components):
	for component in components:
		events.extend(component.getEvents())
	filter(lambda f: f.eventTime>=0.0,events)
	events.sort(key=lambda f: f.eventTime)
	
	
def generateEvents(events,components):
	for component in components:
		component.generateEvents()


def state2Num(state):
	if state=="run":
		return 0.0
	else:
		return 1.0




def simulate():
	actualTime=[0.0]
	if DEBUG:
		print("new sim ---------------------------------")
	events=list()
	params=sampleParameters()
	i=0
	gateway.entry_point.getEntryPoint().reset(«FOR arg : analysis_component.analyzedComponent.arguments SEPARATOR ","»
		«IF arg instanceof StochasticExpression»
				pyro.sample("param_"+str(i),«generator.generateDitribution((arg as StochasticExpression).randomvariable)»),
				i=i+1
		«ELSE»
				«Double.toString(expEval.evaluateDecimal(arg))»
		«ENDIF»
	«ENDFOR»)
	components=generateComponents(actualTime)
	generateEvents(events,components)
	collectEvents(events,components)
	while len(events)>0 and actualTime[0]<simTime:
		collectEvents(events,components)
		filter(lambda f: f.eventTime>=0.0,events)
		events.sort(key=lambda f: f.eventTime)
		event=events.pop(0)
		actualTime[0]=event.eventTime
		
		if DEBUG:
			print(event.eventSource.name+" at time: "+str(actualTime[0]))
		event.eventCall()
		
		if DEBUG:
			print(event.eventSource.name+" at time: "+str(actualTime))
		event.eventCall()
		
		# run the deterministic statechart behavior
		sctmodel.get«analysis_component.analyzedComponent.name.toFirstUpper»().start()
		
	
	# get the aspects and return from the simulations 
	«TransformationUtility.generateSimulationReturn(analysis_component.aspect)»


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
	


def simulateUntilStop():
	actualTime=[0.0]
	if DEBUG:
		print("new sim ---------------------------------")
	events=list()
	params=sampleParameters()
	i=0
	gateway.entry_point.getEntryPoint().reset(«FOR arg : analysis_component.analyzedComponent.arguments SEPARATOR ","»
		«IF arg instanceof StochasticExpression»
				pyro.sample("param_"+str(i),«generator.generateDitribution((arg as StochasticExpression).randomvariable)»),
				i=i+1
		«ELSE»
				«Double.toString(expEval.evaluateDecimal(arg))»
		«ENDIF»
	«ENDFOR»)
	components=generateComponents(actualTime)
	generateEvents(events,components)
	collectEvents(events,components)
	while len(events)>0 and actualTime[0]<simTime:
		collectEvents(events,components)
		filter(lambda f: f.eventTime>=0.0,events)
		events.sort(key=lambda f: f.eventTime)
		event=events.pop(0)
		actualTime[0]=event.eventTime
		
		if DEBUG:
			print(event.eventSource.name+" at time: "+str(actualTime[0]))
		event.eventCall()
		
		if DEBUG:
			print(event.eventSource.name+" at time: "+str(actualTime))
		event.eventCall()
		for i in range(10):
				sctmodel.get«analysis_component.analyzedComponent.name.toFirstUpper»().runCycle()
				
		if gateway.entry_point.getEntryPoint().getState() != "run":
			break
		
	components.clear()
	return actualTime[0]


def simulateUntilTime():
	actualTime=[0.0]
	events=list()
	gateway.entry_point.getEntryPoint().reset()
	components=generateComponents(actualTime)
	generateEvents(events,components)
	collectEvents(events,components)
	while len(events)>0 and actualTime[0]<simTime:
		collectEvents(events,components)
		filter(lambda f: f.eventTime>=0.0,events)
		events.sort(key=lambda f: f.eventTime)
		event=events.pop(0)
		actualTime[0]=event.eventTime
		
		if DEBUG:
			print(event.eventSource.name+" at time: "+str(actualTime[0]))
		event.eventCall()
		for i in range(10):
			sctmodel.get«analysis_component.analyzedComponent.name.toFirstUpper»().runCycle()
	
	components.clear()
	return gateway.entry_point.getEntryPoint().getFreq()


'''
	}
}