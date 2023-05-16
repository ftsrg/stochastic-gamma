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

class PyroComponentClassGenerator {
	
	static Integer cntr=0
	final String packageName
	final ExpressionEvaluator expEval;
	PyroDistGenerator distGenerator; 
	static Integer if_cntr=0 
	val envUtil=ElementaryEnvironmentComponentUtility.INSTANCE
	
	
	new(String packageName){
		this.packageName=packageName
		expEval=ExpressionEvaluator.INSTANCE;
		distGenerator = new PyroDistGenerator
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
							if time>=0:
								#iterating through port connections
								for call in calls:
									self.simulator.events.append(Event(self,time,call))
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
							while simulationtime < simTime:
								simulationtime=simulationtime+rule.calc(port+"."+pevent,simulationtime)
								#iterating through port connections
								for call in calls:
									self.simulator.events.append(Event(self,simulationtime,call))
		

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
							if pevent in self.rules[port]:
								rule=rules[port][pevent]
								simulator.dists.append(rule)



				def generateEvents(self):
					self.events.clear()
					pass
					#definition of the interface functions

				@JOverride
					return (len(self.events)==0)

				@JOverride
				def schedule(self):
					for event in self.events:
						event.callEvent()
					self.events.clear()

				«FOR event : i.events»
			
				@JOverride
				def raise«event.event.name.toFirstUpper»(self,«TransformationUtility.generateFuncParams(event.event)»):
					«FOR param : event.event.parameterDeclarations»
						if "«param.name.toFirstLower»" in self.rules["«event.event.name.toFirstUpper»"].keys():
							«param.name.toFirstLower» = self.rules["«event.event.name.toFirstUpper»"]["«param.name.toFirstLower»"].calc(self.port+"."+"«event.event.name.toFirstUpper»::«param.name»",self.simulator.time)
					«ENDFOR»
					
					«event.event.parameterDeclarations.get(0).name.toFirstLower»=self.rules["«event.event.name.toFirstUpper»"].calc(self.port+"."+"«event.event.name.toFirstUpper»",self.simulator.time)
					#«event.event.parameterDeclarations.get(0).type»
					self.event_cntr=self.event_cntr+1
					for call in self.calls:
						if IESC_SYNC:
							callEvent=lambda:call.raise«event.event.name.toFirstUpper»(«TransformationUtility.generateFuncParams(event.event)»);
							self.events.append(Event(self,self.simulator.time,callEvent))
						else:
							call.raise«event.event.name.toFirstUpper»(«TransformationUtility.generateFuncParams(event.event)»)
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
					«FOR event : i.events»
					
					@JOverride
					def raise«event.event.name.toFirstUpper»(self,«TransformationUtility.generateFuncParams(event.event)»):
						time=self.rules["«event.event.name.toFirstUpper»"].calc(self.port+"."+"«event.event.name.toFirstUpper»",self.simulator.time)
						self.event_cntr=self.event_cntr+1
						failureTime=abs(time)+self.simulator.time
						for callitem in self.calls:
							callEvent=lambda:callitem.raise«event.event.name.toFirstUpper»(«TransformationUtility.generateFuncParams(event.event)»);
							self.simulator.events.append(Event(self,failureTime,callEvent))
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
					«FOR event : i.events»
				
					@JOverride
					def raise«event.event.name.toFirstUpper»(self,«TransformationUtility.generateFuncParams(event.event)»):
						port=self.portarray[self.categorical.calc()]
						eventcalls=self.calls[port]#["«event.event.name.toFirstUpper»"]
						self.event_cntr=self.event_cntr+1
						for call in eventcalls:
							if call is not None:
								if IESC_SYNC:
									callEvent=lambda:call.raise«event.event.name.toFirstUpper»(«TransformationUtility.generateFuncParams(event.event)»);
									self.events.append(Event(self,self.simulator.time,callEvent))
								else:
									call.raise«event.event.name.toFirstUpper»(«TransformationUtility.generateFuncParams(event.event)»)
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
			def __init__(self,eventSource,eventTime):
				self.eventSource=eventSource
				self.eventTime=eventTime
			def __init__(self,eventSource,eventTime,eventCall):
				self.eventSource=eventSource
				self.eventTime=eventTime
				self.eventCall=eventCall
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