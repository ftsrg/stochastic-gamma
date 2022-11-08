package hu.bme.mit.gamma.analysis.transformation

import hu.bme.mit.gamma.expression.util.ExpressionEvaluator

import hu.bme.mit.gamma.environment.model.EnvironmentDelay
import hu.bme.mit.gamma.environment.model.EnvironmentSample
import hu.bme.mit.gamma.environment.model.EnvironmentSwitch
import hu.bme.mit.gamma.environment.model.EnvironmentExternSimulation
import hu.bme.mit.gamma.environment.model.EnvironmentEventSource
import hu.bme.mit.gamma.stochastic.stochastic.BrownianKernel
import hu.bme.mit.gamma.stochastic.stochastic.PeriodicKernel
import hu.bme.mit.gamma.stochastic.stochastic.LinearKernel
import hu.bme.mit.gamma.stochastic.stochastic.RBFKernel
import hu.bme.mit.gamma.stochastic.stochastic.SumKernel
import hu.bme.mit.gamma.stochastic.stochastic.PythonSimulation
import hu.bme.mit.gamma.stochastic.stochastic.InfluxDB
import hu.bme.mit.gamma.stochastic.stochastic.DiracProcess
import hu.bme.mit.gamma.stochastic.stochastic.FittedGaussianProcess
import hu.bme.mit.gamma.stochastic.stochastic.ExponentialRandomVariable
import hu.bme.mit.gamma.stochastic.stochastic.NormalRandomVariable
import java.util.List
import hu.bme.mit.gamma.stochastic.stochastic.RandomVariable
import hu.bme.mit.gamma.environment.model.StochasticRule
import hu.bme.mit.gamma.environment.model.SimulationRule
import hu.bme.mit.gamma.stochastic.stochastic.BernoulliRandomVariable
import hu.bme.mit.gamma.stochastic.stochastic.ContinouosRandomVariable
import hu.bme.mit.gamma.stochastic.stochastic.DiscreteRandomVariable
import hu.bme.mit.gamma.statechart.interface_.Port
import hu.bme.mit.gamma.stochastic.stochastic.CategoricalProbabaility
import hu.bme.mit.gamma.statechart.interface_.Interface
import hu.bme.mit.gamma.statechart.interface_.Event
import hu.bme.mit.gamma.environment.model.EventFilter
import hu.bme.mit.gamma.environment.model.PortFilter
import hu.bme.mit.gamma.environment.model.ComponentFilter
import hu.bme.mit.gamma.stochastic.stochastic.WeibullRandomVariable
import hu.bme.mit.gamma.stochastic.stochastic.GammaRandomVariable
import hu.bme.mit.gamma.stochastic.stochastic.FittedExponentialRandomVariable
import hu.bme.mit.gamma.stochastic.stochastic.FittedNormalRandomVariable
import hu.bme.mit.gamma.expression.util.ExpressionEvaluator

class PyroComponentClassGenerator {
	
	static Integer cntr=0
	final String packageName
	final ExpressionEvaluator expEval;
	PyroDistGenerator distGenerator; 
	static Integer if_cntr=0
	
	
	new(String packageName){
		this.packageName=packageName
		expEval=ExpressionEvaluator.INSTANCE;
		distGenerator = new PyroDistGenerator(packageName)
	}
	
	def generateInterface(Interface i)
	'''
	if«if_cntr» = \
	    JClass('«packageName».interfaces.«i.name.toFirstUpper»Interface$Listener$Provided'
	           )
	
	
	@JImplements(if«if_cntr++»)
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
			def __init__(self,name,calls,rules,portevents,simulator):
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
						rule=self.rules[port][pevent]
						self.simulator.dists.append(rule)


			def generateEvents(self):
				ports=list(self.calls.keys())
				#iterating through ports
				for port in ports:
					pevents=self.portevents[port]
					#iterating through events
					for pevent in pevents:
						rule=self.rules[port][pevent]
						call=self.calls[port][pevent]
						time=rule.calc(port+"."+pevent,0.0)
						if time>=0:
							#iterating through port connections
							self.simulator.events.append(Event(self,time,call))

		'''
	}
	
	def generatePeriodicEventSourceClass(){
		'''
		class PeriodicEventSource():
			def __init__(self,name,calls,rules,portevents,simulator):
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
						rule=self.rules[port][pevent]
						self.simulator.dists.append(rule)


			def generateEvents(self):
				ports=list(self.calls.keys())
				#iterating through self.ports
				for port in ports:
					pevents=self.portevents[port]
					#iterating through events
					for pevent in pevents:
						call=self.calls[port][pevent]
						rule=self.rules[port][pevent]
						simulationtime=0.0
						while simulationtime < simTime:
							simulationtime=simulationtime+rule.calc(port+"."+pevent,simulationtime)
							#iterating through port connections
							self.simulator.events.append(Event(self,simulationtime,call))

		'''
	}
	
	def generateSampleClasses(List<EnvironmentConnections> connections){
		'''
		«var interfaces=connections.filter[c|c.component instanceof EnvironmentSample]
									.map[c|c.component.outports.get(0).interfaceRealization.interface].toSet»
		«FOR i : interfaces»
			«generateInterface(i)»
			class Sample«i.name.toFirstUpper»():
				def __init__(self,name,inport,calls,rules,simulator):
					self.name=name
					callitem=list(calls.items())[0]#only one out port
					self.calls=callitem[1]
					self.port=callitem[0]
					self.rules=list(rules.items())[0][1]#only one out port
					self.event_cntr=0
					inport.registerListener(self)
					self.simulator=simulator
					#iterating through ports
					for port in rules.keys():
						pevents=rules[port].keys()
						#iterating through events
						for pevent in pevents:
							rule=rules[port][pevent]
							simulator.dists.append(rule)


				def generateEvents(self):
					pass
					#definition of the interface functions

				
				«FOR event : i.events»
			
				@JOverride
				def raise«event.event.name.toFirstUpper»(self,«TransformationUtility.generateFuncParams(event.event)»):
					«event.event.parameterDeclarations.get(0).name.toFirstLower»=self.rules["«event.event.name.toFirstUpper»"].calc(self.port+"."+"«event.event.name.toFirstUpper»",self.simulator.time)
					#«event.event.parameterDeclarations.get(0).type»
					self.event_cntr=self.event_cntr+1
					for call in self.calls:
						if IESC_SYNC:
							callEvent=lambda:call.raise«event.event.name.toFirstUpper»(«TransformationUtility.generateFuncParams(event.event)»);
							self.simulator.events.append(Event(self,self.simulator.time,callEvent))
						else:
							callEvent=call.raise«event.event.name.toFirstUpper»(«TransformationUtility.generateFuncParams(event.event)»)
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
					def __init__(self,name,inport,calls,rules,simulator):
						self.name=name
						callitem=list(calls.items())[0]#only one out port
						self.calls=callitem[1]
						self.port=callitem[0]
						self.rules=list(rules.items())[0][1]#only one out port
						self.event_cntr=0
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
					def __init__(self,name,inport,calls,portarray,categorical,simulator):
						self.name=name
						self.calls=calls
						self.portarray=portarray
						self.categorical=categorical
						self.event_cntr=0
						inport.registerListener(self)
						self.simulator=simulator
						self.simulator.dists.append(categorical)
									
					def generateEvents(self):
						pass



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
									self.simulator.events.append(Event(self,self.simulator.time,callEvent))
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