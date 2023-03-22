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

class PyroSimulatorGenerator {
		
	
	def generate(AnalysisComponent analysis_component,String packageName) {
		var component=(analysis_component.analyzedComponent instanceof EnvironmentAsynchronousCompositeComponentInstance) ? 
			(analysis_component.analyzedComponent as EnvironmentAsynchronousCompositeComponentInstance).type :
			(analysis_component.analyzedComponent as EnvironmentSynchronousCompositeComponentInstance).type
		var stack=<ComponentInstance>newArrayList()
		var connections=ElementaryComponentCollector.collect(analysis_component.analyzedComponent,stack)
		var distGenerator=new PyroDistGenerator(packageName)
		var compClassGenerator=new PyroComponentClassGenerator(packageName)
		var expEval=ExpressionEvaluator.INSTANCE 
		var workspace = ResourcesPlugin.getWorkspace()
		var workspaceDirectory = workspace.root.rawLocationURI.toString.replaceAll("^file:/","").replace('/',File.separatorChar)
		val strSep="\\"+File.separator
		var jarSources = (workspaceDirectory+File.separator+packageName+File.separator+"bin").replaceAll("\\\\","\\\\\\\\")
		
		var jvmHome=(System.getProperty("java.home")+File.separator+"bin"+File.separator+"server"+File.separator+"jvm.dll").replaceAll("\\\\","\\\\\\\\")
		
'''
import pyro
import torch
import pyro.distributions as dist

import math
from math import exp
import numpy as np

import matplotlib.pyplot as plt
import matplotlib

#from influxdb import InfluxDBClient

import pyro.contrib.gp as gp

import time
import os
import datetime
import traceback
from jpype import JImplements, JOverride
from jpype import *
import jpype

DEBUG=False
BUILD=False
IESC_SYNC=False

simTime=«Double.toString(analysis_component.simulationTime)»
simNumber=«analysis_component.simulationNumber.toString»



#time.sleep(3)

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
	return detmodel

detmodel=create_detmodel()


print('Python-Java connection established')

# python classes of random variables and distributions
«distGenerator.generateClasses()»

#
«compClassGenerator.generateClasses(connections)»

#
«PyroStochasticClassGenerator.generate(analysis_component,packageName)»

stochmodel=0
try:
	stochmodel = StochasticEventGenerator(detmodel)
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
	if DEBUG:
		print("new sim ---------------------------------")
	global stochmodel, detmodel
	stochmodel.reset()
	stochmodel.generateEvents()
	while len(stochmodel.events) > 0 and stochmodel.time < simTime:
		event = stochmodel.popEvent()
		stochmodel.time = event.eventTime
		if DEBUG:
			print(event.eventSource.name + ' at time: ' + str(stochmodel.time))
		event.eventCall()
		detmodel.get«analysis_component.analyzedComponent.name.toFirstUpper»().schedule()
		# evaluate end condition
				
		«FOR endCondition : analysis_component.endcondition»
			if detmodel.monitorOf«TransformationUtility.generateEndConditionName(endCondition)».state != "run":
				break
		«ENDFOR»

	# get the aspects and return from the simulations 
	«TransformationUtility.generateSimulationReturn(analysis_component.aspect)»
	



if DEBUG:
	print("testing the simulator")
	try:
		for i in range(10):
			print(simulate())
	except Exception as err:
		print("Exception occured during testing the simulation: ")
		print(err)
		traceback.print_exc()
	except java.lang.RuntimeException as ex:
		print("Caught runtime exception : ", str(ex))
		print(ex.stacktrace())
	except jpype.JException as ex:
		print("Caught base exception : ", str(ex))
		print(ex.stacktrace())
	except Exception as ex:
		print("Caught python exception :", str(ex))
	except Exception as err:
		print("Exception occured during testing the simulation: ")
		print(err)
		traceback.print_exc()
	finally:
		shutdownJVM()
'''
		}
}