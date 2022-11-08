package hu.bme.mit.gamma.analysis.transformation

import hu.bme.mit.gamma.expression.util.ExpressionEvaluator
import java.util.List
import hu.bme.mit.gamma.statechart.interface_.Interface
import hu.bme.mit.gamma.environment.model.EventFilter
import hu.bme.mit.gamma.environment.model.PortFilter
import hu.bme.mit.gamma.environment.model.ComponentFilter
import hu.bme.mit.gamma.environment.model.SimulationRule
import hu.bme.mit.gamma.environment.model.StochasticRule
import hu.bme.mit.gamma.statechart.interface_.Event
import hu.bme.mit.gamma.environment.model.EnvironmentDelay
import hu.bme.mit.gamma.environment.model.EnvironmentExternSimulation
import hu.bme.mit.gamma.environment.model.EnvironmentSample
import hu.bme.mit.gamma.environment.model.EnvironmentSwitch
import hu.bme.mit.gamma.environment.model.EnvironmentEventSource
import hu.bme.mit.gamma.environment.model.EnvironmentPeriodicEventSource

class PyroComponentInstanceGenerator {
	
	static Integer cntr=0
	final String packageName
	final ExpressionEvaluator expEval;
	PyroDistGenerator distGenerator; 
	
	
	new(String packageName){
		this.packageName=packageName
		expEval=ExpressionEvaluator.INSTANCE;
		distGenerator=new PyroDistGenerator(packageName)
	}
	
		
	def generateRules(EnvironmentConnections connection){
		'''
		{«FOR port : connection.component.outports SEPARATOR ", "»"«port.name.toFirstUpper»" : {
			«FOR event : port.interfaceRealization.interface.events SEPARATOR ", "» 
				«var rules=connection.component.behaviorRules
						.filter[r|!r.filter.filter[f|f instanceof EventFilter].filter[f|(f as EventFilter).event.event==event.event].empty].toList»
				#«if(rules.isEmpty){rules=connection.component.behaviorRules.filter[r|!r.filter.filter[f|f instanceof PortFilter].
					filter[f|(f as PortFilter).port==port].empty].toList}»
				#«if(rules.isEmpty){rules=connection.component.behaviorRules.filter[r|!r.filter.filter[f|f instanceof ComponentFilter].empty].toList}»
				«IF (!rules.empty)»«var rule=rules.get(0)»"«event.event.name.toFirstUpper»" : «IF rule instanceof SimulationRule»«rule.simulation.simulationClassName»Instance«ELSEIF rule instanceof StochasticRule»
						«distGenerator.generateStochasticModel(rule.stochasticModel,connection.component.name)»«ENDIF»
				«ENDIF»
			«ENDFOR»
		}«ENDFOR»}
		'''
	}
	
	def generateSimulationInstances(List<EnvironmentConnections> connections){
		'''
		«var simrules=connections.flatMap[c|c.component.behaviorRules]
			.filter[r|r instanceof SimulationRule]
				.map[r|r as SimulationRule]»
		«FOR simrule : simrules»
			«simrule.simulation.simulationClassName»Instance = «simrule.simulation.simulationClassName»()
		«ENDFOR»
		'''
	}
	
	def generateCalls(EnvironmentConnections connection){
		'''
		{«IF !connection.outCalls.keys.empty»«FOR port : connection.component.outports SEPARATOR ", "»
			"«port.name.toFirstUpper»" : [«IF connection.outCalls.get(port).empty»None«ENDIF»«FOR call : connection.outCalls.get(port) SEPARATOR ", "»
				«IF call.charAt(0)=='_'»«call.replaceAll("^_","")»«ELSE»self.detmodel«connection.componentCall»«call»«ENDIF»«ENDFOR»]«ENDFOR»«ENDIF»}
		'''
	}
	
	def generateCallEvents(EnvironmentConnections connection){
		'''
		{«IF !connection.outCalls.keys.empty»«FOR port : connection.component.outports SEPARATOR ", "»"«port.name.toFirstUpper»" : {
		«FOR call : connection.outCalls.get(port) SEPARATOR ", "»
		«FOR event : port.interfaceRealization.interface.events.map[e|e.event]SEPARATOR ", "»
			"«event.name.toFirstUpper»" : (lambda:self.detmodel«connection.componentCall»«call».raise«event.name.toFirstUpper»(«TransformationUtility.generateFuncParams(event)»))«ENDFOR»«ENDFOR»}«ENDFOR»«ENDIF»}
		'''
	}
	
	def generatePortArray(EnvironmentConnections connection){
		'''
		[«FOR port : connection.component.outports SEPARATOR ', '»"«port.name.toFirstUpper»"«ENDFOR»]
		'''
	}
	
	def generatePortevents(EnvironmentConnections connection){
		'''
		{
		«FOR port : connection.component.outports SEPARATOR ", "»
			"«port.name.toFirstUpper»" : [
				«FOR event : port.interfaceRealization.interface.events SEPARATOR ", "»
					"«event.event.name.toFirstUpper»"
				«ENDFOR»
			]
		«ENDFOR»
		}
		'''
	}
	
	def generateInPort(EnvironmentConnections connection){
		'''self.detmodel«connection.componentCall»«connection.inCalls.values.get(0)»'''
	}
	
	
		def generateSwitchInstances(List<EnvironmentConnections> connections){
		'''
		«FOR connection : connections»
		«»
		self.components.update({ "«TransformationUtility.generateEnvCompName(connection)»" :
			Switch«connection.component.inports.get(0).interfaceRealization.interface.name.toFirstUpper»(
				name  = "«TransformationUtility.generateEnvCompName(connection)»",
				inport=«generateInPort(connection)»,
				calls=«generateCalls(connection)»,
				portarray=«generatePortArray(connection)»,
				categorical=«distGenerator.generateCategorical(connection)»,
				simulator=self)})
		«ENDFOR»
		'''
	}
	
	
	def generateDelayInstances(List<EnvironmentConnections> connections){
		

		
		var interfaces=connections.filter[c|c.component instanceof EnvironmentDelay]
									.map[c|c.component.outports.get(0).interfaceRealization.interface].toSet
		'''
		
		«FOR connection : connections»
		«»
		self.components.update({ "«TransformationUtility.generateEnvCompName(connection)»" :
			Delay«connection.component.outports.get(0).interfaceRealization.interface.name.toFirstUpper»(
				name  = "«TransformationUtility.generateEnvCompName(connection)»",
				inport=«generateInPort(connection)»,
				calls = «generateCalls(connection)»,
				rules = «generateRules(connection)»,
				simulator=self)})
		«ENDFOR»
		
		'''
		
	}
	
	def generateSampleInstances(List<EnvironmentConnections> connections){
		

		
		var interfaces=connections.filter[c|c.component instanceof EnvironmentDelay]
									.map[c|c.component.outports.get(0).interfaceRealization.interface].toSet
		'''
		
		«FOR connection : connections»
		«»
		self.components.update({ "«TransformationUtility.generateEnvCompName(connection)»" :
			Sample«connection.component.outports.get(0).interfaceRealization.interface.name.toFirstUpper»(
				name  = "«TransformationUtility.generateEnvCompName(connection)»",
				inport=«generateInPort(connection)»,
				calls = «generateCalls(connection)»,
				rules = «generateRules(connection)»,
				simulator=self)})
		«ENDFOR»
		
		'''
		
	}
	
	
	def generatePeriodicEventSourceInstances(List<EnvironmentConnections> connections){
		'''
		«FOR connection : connections»
		«»
		self.components.update({ "«TransformationUtility.generateEnvCompName(connection)»" :
			PeriodicEventSource(
				name  = "«TransformationUtility.generateEnvCompName(connection)»",
				calls = «generateCallEvents(connection)»,
				rules = «generateRules(connection)»,
				portevents = «generatePortevents(connection)»,
				simulator=self)})
		«ENDFOR»
		'''
	}
	
	
	def generateEventSourceInstances(List<EnvironmentConnections> connections){
		'''
		«FOR connection : connections»
		«»
		self.components.update({ "«TransformationUtility.generateEnvCompName(connection)»" :
			EventSource(
				name  = "«TransformationUtility.generateEnvCompName(connection)»",
				calls = «generateCallEvents(connection)»,
				rules = «generateRules(connection)»,
				portevents = «generatePortevents(connection)»,
				simulator=self)})
		«ENDFOR»
		'''
	}
	
	def gennerateInstances(List<EnvironmentConnections> connections)
'''

self.components.clear()
«generatePeriodicEventSourceInstances(
	connections.filter[c|c.component instanceof EnvironmentPeriodicEventSource].toList
)»

«generateEventSourceInstances(
	connections.filter[c|c.component instanceof EnvironmentEventSource].toList
)»

«generateDelayInstances(
	connections.filter[c|c.component instanceof EnvironmentDelay].toList
)»

«generateSwitchInstances(
	connections.filter[c|c.component instanceof EnvironmentSwitch].toList
)»

«generateSampleInstances(
	connections.filter[c|c.component instanceof EnvironmentSample].toList
)»

«generateEventSourceInstances(
	connections.filter[c|c.component instanceof EnvironmentExternSimulation].toList
)»
'''
	
}