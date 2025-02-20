/********************************************************************************
 * Copyright (c) 2018-2024 Contributors to the Gamma project
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * SPDX-License-Identifier: EPL-1.0
 ********************************************************************************/
package hu.bme.mit.gamma.environment.analysis.transformation.pythongen

import hu.bme.mit.gamma.environment.analysis.transformation.util.TransformationUtility
import hu.bme.mit.gamma.environment.model.EnvironmentDelay
import hu.bme.mit.gamma.environment.model.EnvironmentSample
import hu.bme.mit.gamma.environment.model.EnvironmentSwitch
import hu.bme.mit.gamma.expression.util.ExpressionEvaluator
import hu.bme.mit.gamma.statechart.interface_.Interface
import java.util.List
import hu.bme.mit.gamma.environment.analysis.transformation.util.EnvironmentConnections
import hu.bme.mit.gamma.codegeneration.java.util.ElementaryEnvironmentComponentUtility
import hu.bme.mit.gamma.environment.model.utils.EnvironmentModelValidator

import static extension hu.bme.mit.gamma.environment.model.utils.EnvironmentModelDerivedFeatures.*
import hu.bme.mit.gamma.statechart.interface_.EventDirection
import hu.bme.mit.gamma.environment.analysis.AnalysisMethod
import hu.bme.mit.gamma.environment.analysis.SimulationAnalysisMethod
import hu.bme.mit.gamma.environment.analysis.AnalysisComponent

class PyroComponentClassGenerator {
	
	static Integer cntr=0
	final String packageName
	final ExpressionEvaluator expEval;
	PyroDistGenerator distGenerator; 
	static Integer if_cntr=0 
	val envUtil=ElementaryEnvironmentComponentUtility.INSTANCE
	val SimulationAnalysisMethod analysisMethod
	val boolean debug
	
	new(String packageName,AnalysisComponent analysisComponent){
		this.packageName=packageName
		expEval=ExpressionEvaluator.INSTANCE;
		distGenerator = new PyroDistGenerator
		this.analysisMethod=analysisComponent.analysismethod as SimulationAnalysisMethod
		this.debug=analysisMethod.debug
	}
	
	def generateInterface(Interface i)
	'''
		if«if_cntr» = JClass('«packageName».interfaces.«i.name.toFirstUpper»Interface$Listener$Provided')
		if«if_cntr»_s = JClass('«packageName».«envUtil.schedulingInterfacePackage».«envUtil.schedulingInterfaceName»')

		@JImplements([if«if_cntr»,if«if_cntr++»_s])
	'''
	
	def generateInterface()
	'''
		if«if_cntr»_s = JClass('«packageName».«envUtil.schedulingInterfacePackage».«envUtil.schedulingInterfaceName»')

		@JImplements(if«if_cntr++»_s)
	'''
	
	def generateInterfaceSubClass(Interface i){
		'''
		#	class Java:
		#		implements = ["«packageName».interfaces.«i.name.toFirstUpper»Interface$Listener$Provided"]
		'''
	}
	

	def generateEventSourceClass(){
		'''
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
		'''
	}
	
	def generatePeriodicEventSourceClass(){
		'''
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
		

		'''
	}
	
	def generateSampleClasses(List<EnvironmentConnections> connections){
		'''
		«var samples=connections.filter[c|c.component instanceof EnvironmentSample].map[c|c.component].toSet»
		«FOR sample : samples»
			«var i=sample.outports.get(0).interfaceRealization.interface»
			«generateInterface(i)»
			class Sample«sample.name.toFirstUpper»():
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

				«FOR event : sample.outports.get(0).outputEvents»
			
				@JOverride
				def raise«event.name.toFirstUpper»(self,«TransformationUtility.generateFuncParams(event)»):
					«FOR param : event.parameterDeclarations»
						if "«param.name.toFirstLower»" in self.rules["«event.name.toFirstUpper»"].keys():
							«param.name.toFirstLower» = self.rules["«event.name.toFirstUpper»"]["«param.name.toFirstLower»"].calc(self.port+"."+"«event.name.toFirstUpper»::«param.name»",self.simulator.time)
					«ENDFOR»
					
					#«event.parameterDeclarations.get(0).name.toFirstLower»=self.rules["«event.name.toFirstUpper»"].calc(self.port+"."+"«event.name.toFirstUpper»",self.simulator.time)
					#«event.parameterDeclarations.get(0).type»
					self.event_cntr=self.event_cntr+1
					for call in self.calls:
						if IESC_SYNC:
							«IF debug»
								dprint(f'detmodel -> stochmodel : {self.name} :: {self.port}+.«event.name.toFirstUpper»({[«TransformationUtility.generateFuncParams(event)»]}) at {self.simulator.time}')
							«ENDIF»
							callEvent=lambda:call.raise«event.name.toFirstUpper»(«TransformationUtility.generateFuncParams(event)»);
							self.events.append(Event(self,self.simulator.time,callEvent,self.port+".«event.name.toFirstUpper»"))
						else:
							«IF debug»
								dprint(f'detmodel <-> stochmodel : {self.name} :: {self.port}+.«event.name.toFirstUpper»({[«TransformationUtility.generateFuncParams(event)»]}) at {self.simulator.time}')
							«ENDIF»
							call.raise«event.name.toFirstUpper»(«TransformationUtility.generateFuncParams(event)»)
				«ENDFOR»
			
			#«generateInterfaceSubClass(i)»
		«ENDFOR»									
		'''
	}
	
	def generateDelayClasses(List<EnvironmentConnections> connections){
		var interfaces=connections.filter[c|c.component instanceof EnvironmentDelay]
									.map[c|c.component.outports.get(0).interfaceRealization.interface].toSet
		var classes=new StringBuilder()
		for (i : interfaces){
			classes.append(
				'''
				«generateInterface(i)»
				class Delay«i.name.toFirstUpper»():
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
					«FOR event : i.allEvents.filter[e | e.direction==EventDirection.OUT]»
					
					@JOverride
					def raise«event.name.toFirstUpper»(self,«TransformationUtility.generateFuncParams(event)»):
						time=self.rules["«event.name.toFirstUpper»"].calc(self.port+"."+"«event.name.toFirstUpper»",self.simulator.time)
						self.event_cntr=self.event_cntr+1
						failureTime=abs(time)+self.simulator.time
						«IF debug»
							dprint(f'detmodel -> stochmodel : {self.name} :: {self.port}.«event.name.toFirstUpper» at {self.simulator.time}')
						«ENDIF»
						for callitem in self.calls:
							callEvent=lambda:callitem.raise«event.name.toFirstUpper»(«TransformationUtility.generateFuncParams(event)»);
							self.simulator.events.append(Event(self,failureTime,callEvent,self.port+".«event.name.toFirstUpper»"))
					«ENDFOR»
				#«generateInterfaceSubClass(i)»
				
				'''
			)
		}
		return classes.toString
	}
	
	def generateSwitchClasses(List<EnvironmentConnections> connections){
		var interfaces=connections.filter[c|c.component instanceof EnvironmentSwitch]
									.map[c|c.component.outports.get(0).interfaceRealization.interface].toSet
		var classes=new StringBuilder()
		for (i : interfaces){
			classes.append(
				'''
				«generateInterface(i)»
				class Switch«i.name.toFirstUpper»():

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
					«FOR event : i.allEvents.filter[e | e.direction==EventDirection.OUT]»
				
					@JOverride
					def raise«event.name.toFirstUpper»(self,«TransformationUtility.generateFuncParams(event)»):
						port=self.portarray[int(self.categorical.calc())]
						eventcalls=self.calls[port]#["«event.name.toFirstUpper»"]
						self.event_cntr=self.event_cntr+1
						«IF debug»
							dprint(f'detmodel -> stochmodel : {self.name} :: {port}+.«event.name.toFirstUpper» at {self.simulator.time}')
						«ENDIF»
						for call in eventcalls:
							if call is not None:
								if IESC_SYNC:
									«IF debug»
										dprint(f'detmodel -> stochmodel : {self.name} :: {port}+.«event.name.toFirstUpper» at {self.simulator.time}')
									«ENDIF»
									callEvent=lambda:call.raise«event.name.toFirstUpper»(«TransformationUtility.generateFuncParams(event)»);
									self.events.append(Event(self,self.simulator.time,callEvent,self.port+".«event.name.toFirstUpper»"))
								else:
									«IF debug»
										dprint(f'detmodel <-> stochmodel : {self.name} :: {port}+.«event.name.toFirstUpper» at {self.simulator.time}')
									«ENDIF»
									call.raise«event.name.toFirstUpper»(«TransformationUtility.generateFuncParams(event)»)
					«ENDFOR»
				#«generateInterfaceSubClass(i)»
				'''
			)
		}
		return classes.toString
	}
	

	
	def generateEventClass(){
		'''
		class Event():
			def __init__(self,eventSource,eventTime,eventCall,name="anonymous"):
				self.eventSource=eventSource
				self.eventTime=eventTime
				self.eventCall=eventCall
				self.name=name
		'''
	}
	
	def generateClasses(List<EnvironmentConnections> connections)
		'''
		# environment component classes
		
		
		«generateEventClass»
		
		
		«generatePeriodicEventSourceClass()»
		
		
		«generateEventSourceClass()»
		
		
		«generateDelayClasses(
			connections.filter[c|c.component instanceof EnvironmentDelay].toList
		)»
		
		«generateSwitchClasses(
			connections.filter[c|c.component instanceof EnvironmentSwitch].toList
		)»
		
		«generateSampleClasses(
			connections.filter[c|c.component instanceof EnvironmentSample].toList
		)»
		'''
	
}