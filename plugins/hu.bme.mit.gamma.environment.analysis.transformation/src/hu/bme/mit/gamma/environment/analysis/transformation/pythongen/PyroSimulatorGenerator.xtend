package hu.bme.mit.gamma.environment.analysis.transformation.pythongen

import hu.bme.mit.gamma.environment.analysis.AnalysisComponent
import hu.bme.mit.gamma.environment.model.EnvironmentAsynchronousCompositeComponentInstance
import hu.bme.mit.gamma.environment.model.EnvironmentSynchronousCompositeComponentInstance
import hu.bme.mit.gamma.expression.util.ExpressionEvaluator
import hu.bme.mit.gamma.statechart.composite.ComponentInstance
import org.eclipse.core.resources.ResourcesPlugin
import java.io.File
import hu.bme.mit.gamma.environment.analysis.transformation.util.TransformationUtility
import hu.bme.mit.gamma.environment.analysis.transformation.util.ElementaryComponentCollector
import hu.bme.mit.gamma.environment.analysis.SimulationAnalysisMethod


import static extension hu.bme.mit.gamma.environment.analysis.transformation.util.TransformationUtility.*

import static extension hu.bme.mit.gamma.environment.analysis.transformation.pythongen.PyroAnalysisGenerator.*

class PyroSimulatorGenerator {
		
	
	def generate(AnalysisComponent analysis_component,String packageName,String basePackage) {
		var component=(analysis_component.analyzedComponent instanceof EnvironmentAsynchronousCompositeComponentInstance) ? 
			(analysis_component.analyzedComponent as EnvironmentAsynchronousCompositeComponentInstance).type :
			(analysis_component.analyzedComponent as EnvironmentSynchronousCompositeComponentInstance).type
		var stack=<ComponentInstance>newArrayList()
		var connections=ElementaryComponentCollector.collect(analysis_component.analyzedComponent,stack)
		var distGenerator=new PyroDistGenerator
		var compClassGenerator=new PyroComponentClassGenerator(packageName)
		var expEval=ExpressionEvaluator.INSTANCE 
		var workspace = ResourcesPlugin.getWorkspace()
		var projectName = ResourcesPlugin.workspace.root.getProjects()
		var workspaceDirectory = workspace.root.rawLocationURI.toString.replaceAll("^file:/","").replace('/',File.separatorChar)
		val strSep="\\"+File.separator
		//var jarSources = (workspaceDirectory+File.separator+packageName+File.separator+"bin").replaceAll("\\\\","\\\\\\\\")
		var jarSources = (basePackage+File.separator+"bin").replaceAll("\\\\","\\\\\\\\").replaceAll("/","\\\\\\\\")
		var analysismethod = analysis_component.analysismethod as SimulationAnalysisMethod
		var jvmHome=(System.getProperty("java.home")+File.separator+"bin"+File.separator+"server"+File.separator+"jvm.dll").replaceAll("\\\\","\\\\\\\\")
		var analysisGen=new PyroAnalysisGenerator
		
'''

«generateImports»

DEBUG=False
BUILD=False
IESC_SYNC=False

simTime=«Double.toString(analysismethod.simulationTime)»
simNumber=«analysismethod.simulationNumber.toString»

«analysisGen.generateMarginalVisualisation()»

print('initiating Python-Java connection')

def create_detmodel():
	if BUILD:
		commands = ["""javac $(find «workspaceDirectory.replaceAll("\\\\","\\\\\\\\")» -name "*.java")"""]
		for command in commands:
			if os.system(command) == 0:
				continue
			else:
				print( "ERROR")
				break
	startJVM("""«jvmHome»""", '-ea',"""-Djava.class.path=«jarSources»""")
	detmodel = 0
	DetModelEntryPoint = JClass('javaenv.DetModelEntryPoint')
	detmodel = DetModelEntryPoint()
	print('Python-Java connection established')
	return detmodel

detmodel=create_detmodel()



# python classes of random variables and distributions
«distGenerator.generateClasses()»

#
«compClassGenerator.generateClasses(connections)»

#
«PyroStochasticClassGenerator.generate(analysis_component,packageName)»


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

«analysis_component.generateSimulator»

«analysisGen.generateMain(analysis_component)»



'''
		}
		
	def generateImports(){
		'''
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
		'''
	}
	
	def generateSimulator(AnalysisComponent analysis_component){
		var analysismethod = analysis_component.analysismethod as SimulationAnalysisMethod
		'''
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
				
				# print debug information
				if DEBUG:
					print(event.eventSource.name + ' at time: ' + str(stochmodel.time))
					
				# raise the event
				event.eventCall()
				
				# schedule the deterministic evaluator
				detmodel.get«analysis_component.analyzedComponent.name.toFirstUpper»().schedule()
				
				# evaluate end condition
				
				«FOR endCondition : analysismethod.endcondition»
					if detmodel.monitorOf«generateEndConditionName(endCondition)».state != "run":
						break
				«ENDFOR»
			
			«generatePyroAspectRegistry(analysis_component.aspect)»
			
			«generatePyroConditionRegistry(analysis_component.conditions)»
			
			# get the aspects and return from the simulations 
			«generateSimulationReturn(analysis_component.aspect)»
		'''
	}
		
}