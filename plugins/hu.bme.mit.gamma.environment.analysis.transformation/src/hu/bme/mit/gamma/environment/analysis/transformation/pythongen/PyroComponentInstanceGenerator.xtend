package hu.bme.mit.gamma.environment.analysis.transformation.pythongen

import hu.bme.mit.gamma.environment.analysis.transformation.util.TransformationUtility
import hu.bme.mit.gamma.environment.model.ComponentFilter
import hu.bme.mit.gamma.environment.model.EnvironmentDelay
import hu.bme.mit.gamma.environment.model.EnvironmentEventSource
import hu.bme.mit.gamma.environment.model.EnvironmentExternSimulation
import hu.bme.mit.gamma.environment.model.EnvironmentPeriodicEventSource
import hu.bme.mit.gamma.environment.model.EnvironmentSample
import hu.bme.mit.gamma.environment.model.EnvironmentSwitch
import hu.bme.mit.gamma.environment.model.EventFilter
import hu.bme.mit.gamma.environment.model.ParameterFilter
import hu.bme.mit.gamma.environment.model.PortFilter
import hu.bme.mit.gamma.environment.model.SimulationRule
import hu.bme.mit.gamma.environment.model.StochasticRule
import hu.bme.mit.gamma.expression.util.ExpressionEvaluator
import java.util.List
import hu.bme.mit.gamma.environment.analysis.transformation.util.EnvironmentConnections
import hu.bme.mit.gamma.environment.stochastic.stochastic.BernoulliRandomVariable
import hu.bme.mit.gamma.environment.model.EnvironmentRule
import hu.bme.mit.gamma.statechart.interface_.Port
import hu.bme.mit.gamma.statechart.interface_.Event

import static extension hu.bme.mit.gamma.environment.model.utils.EnvironmentModelDerivedFeatures.*

class PyroComponentInstanceGenerator {
	
	static Integer cntr=0
	final String packageName
	final ExpressionEvaluator expEval;
	PyroDistGenerator distGenerator; 
	
	
	new(String packageName){
		this.packageName=packageName
		expEval=ExpressionEvaluator.INSTANCE;
		distGenerator=new PyroDistGenerator
	}
	 
	dispatch def generateRule(StochasticRule rule, String name){
		return distGenerator.generateStochasticModel(rule.stochasticModel,name)
	}
	 
	dispatch def generateRule(SimulationRule rule, String name){
		throw new UnsupportedOperationException("Simulation rule is not supported yet in sample.")
	}
	
	dispatch def generateRule(EnvironmentRule rule, String name){
		throw new UnsupportedOperationException("Simulation rule is not supported yet in sample.")
	}
	
	def generateRules(EnvironmentConnections connection){
		var builder=new StringBuilder()
		var EnvironmentRule rule
		builder.append("{")
		for (port : connection.component.outports) {
			builder.append("'").append(port.name.toFirstUpper).append("' : {")
			for (event : port.outputEvents){
					var rules=connection.component.behaviorRules
						.filter[
							r|!r.filter.filter[f|f instanceof EventFilter]
							.filter[f|
								(f as EventFilter).event.event==event && 
								(f as EventFilter).event.port==port 						 
							].empty
						].toList
					if (rules.empty) {
						rules=connection.component.behaviorRules
							.filter[
								r|!r.filter.filter[f|f instanceof PortFilter]
								.filter[f|
									(f as PortFilter).port==port 						 
								].empty
							].toList	
					} 
					if (rules.empty) {
						rules=connection.component.behaviorRules
							.filter[
								r|!r.filter.filter[f|f instanceof ComponentFilter].empty
							].toList	
					} 
					if (!rules.empty){
						rule=rules.get(0)
						builder.append("'").append(event.name.toFirstUpper).append("' : ")
						builder.append(generateRule(rule,connection.component.name)).append(",")
					} 
			}
			builder.append("},")
		}
		builder.append("}")
		return builder.toString.replaceAll(",," , ",").replaceAll(",}" , "}").replaceAll(", }" , "}")
	}
	
	
	def generateSampleRules(EnvironmentConnections connection){
		var builder=new StringBuilder()
		builder.append("{")
		for (port : connection.component.outports) {
			builder.append("'").append(port.name.toFirstUpper).append("' : {")
			for (event : port.outputEvents){
				builder.append("'").append(event.name.toFirstUpper).append("' : {")
				for (param : event.parameterDeclarations){
					var rules=connection.component.behaviorRules
						.filter[
							r|!r.filter.filter[f|f instanceof EventFilter]
							.filter[f|
								(f as ParameterFilter).event.event==event && 
								(f as ParameterFilter).event.port==port && 
								(f as ParameterFilter).parameter==param							 
							].empty
						].toList
					if (!rules.empty){
						var rule=rules.get(0)
						builder.append("'").append(param.name.toFirstLower).append("'")
						builder.append(" : ")
						builder.append(generateRule(rule,connection.component.name))
						builder.append(",")
					}
				}
				builder.append("},")
			}
			builder.append("},")
		}
		builder.append("}")
		return builder.toString.replaceAll(",," , ",").replaceAll(",}" , "}").replaceAll(", }" , "}")
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
		var builder=new StringBuilder()
		builder.append("{")
		if (!connection.outCalls.keys.empty){
			for(Port port : connection.component.outports){
				builder.append("'").append(port.name.toFirstUpper).append("' : [")
				if (connection.outCalls.get(port).empty){
					builder.append("None")
				} else {
					for (String call : connection.outCalls.get(port)){
						if(call.charAt(0).compareTo('_')==0){
							builder.append(call.replaceFirst("\\_",""))
						} else {
							builder.append("self.detmodel").append(connection.componentCall).append(call)
						}
						builder.append(", ")
					}
				}
				builder.append("], ")
			}
		}
		builder.append("}")
		return builder.toString.replaceAll(",," , ",").replaceAll(",}" , "}").replaceAll(", }" , "}")
	}
	
	def generateCallEvents(EnvironmentConnections connection){
		var builder=new StringBuilder()
		builder.append("{")
		if (!connection.outCalls.keys.empty){
			for(Port port : connection.component.outports){
				builder.append("'").append(port.name.toFirstUpper).append("' : {")
				if (connection.outCalls.get(port).empty){
					builder.append("None")
				} else {
					for (Event event : port.outputEvents){
						builder.append("'").append(event.name.toFirstUpper).append("' : [")
						for (String call : connection.outCalls.get(port)){
							builder.append("(lambda:")
							if(call.charAt(0).compareTo('_')==0){
								builder.append(call.replaceFirst("\\_",""))
							} else {
								builder.append("self.detmodel").append(connection.componentCall).append(call)
							}
							builder.append(".raise").append(event.name.toFirstUpper).append("(")
							builder.append(TransformationUtility.generateFuncParams(event)).append(")),")
						}
						builder.append("],")
					}
					builder.append(",")
				}
				builder.append("}, ")
			}
		}
		builder.append("}")
		return builder.toString.replaceAll(",,",",").replaceAll(",}","}").replaceAll(", }","}").replaceAll(",]","]").replaceAll(", ]","]")
	}
	
	
	def generatePortArray(EnvironmentConnections connection){
		'''[«FOR port : connection.component.outports SEPARATOR ', '»"«port.name.toFirstUpper»"«ENDFOR»]'''
	}
	
	def generatePortevents(EnvironmentConnections connection){
		'''	{«FOR port : connection.component.outports SEPARATOR ", "»	"«port.name.toFirstUpper»" : [«FOR event : port.outputEvents SEPARATOR ", "» "«event.name.toFirstUpper»"	«ENDFOR»]«ENDFOR»}'''
	}
	
	def generateInPort(EnvironmentConnections connection){
		'''«IF !connection.inCalls.values.empty»self.detmodel«connection.componentCall»«connection.inCalls.values.get(0)»«ELSE»None«ENDIF»'''
	}
	

	def generateSwitchInstances(List<EnvironmentConnections> connections){
		'''
		«FOR connection : connections»
		self.components.update({ "«TransformationUtility.generateEnvCompName(connection)»" : Switch«connection.component.inports.get(0).interfaceRealization.interface.name.toFirstUpper»()})
		«ENDFOR»
		'''
	}
	
	
	def generateDelayInstances(List<EnvironmentConnections> connections){
		

		
		var interfaces=connections.filter[c|c.component instanceof EnvironmentDelay]
									.map[c|c.component.outports.get(0).interfaceRealization.interface].toSet
		'''
		«FOR connection : connections»
		self.components.update({ "«TransformationUtility.generateEnvCompName(connection)»" : Delay«connection.component.outports.get(0).interfaceRealization.interface.name.toFirstUpper»()})
		«ENDFOR»
		
		'''
		
	}
	
	def generateSampleInstances(List<EnvironmentConnections> connections){
		

		'''
		«FOR connection : connections»
		self.components.update({ "«TransformationUtility.generateEnvCompName(connection)»" : Sample«connection.component.name.toFirstUpper»()})
		«ENDFOR»
		'''
		
	}
	
	
	def generatePeriodicEventSourceInstances(List<EnvironmentConnections> connections){
		'''
		«FOR connection : connections»
		self.components.update({ "«TransformationUtility.generateEnvCompName(connection)»" : PeriodicEventSource()})
		«ENDFOR»
		'''
	}
	
	
	def generateEventSourceInstances(List<EnvironmentConnections> connections){
		'''
		«FOR connection : connections»
		self.components.update({ "«TransformationUtility.generateEnvCompName(connection)»" : EventSource()})
		«ENDFOR»
		'''
	}


	def generateSwitchInstancesConfigurations(List<EnvironmentConnections> connections){
		'''
		«FOR connection : connections»
		self.components["«TransformationUtility.generateEnvCompName(connection)»"].configure(
				name  = "«TransformationUtility.generateEnvCompName(connection)»",
				inport=«generateInPort(connection)»,
				calls=«generateCalls(connection)»,
				portarray=«generatePortArray(connection)»,
				categorical=«distGenerator.generateCategorical(connection)»,
				simulator=self,
				shname="«connection.component.name»",
				compCall=self.detmodel«connection.componentCall»)
				
		«ENDFOR»
		'''
	}
	
	
	def generateDelayInstancesConfigurations(List<EnvironmentConnections> connections){
		
		'''
		«FOR connection : connections»
		self.components["«TransformationUtility.generateEnvCompName(connection)»"].configure(
				name  = "«TransformationUtility.generateEnvCompName(connection)»",
				inport=«generateInPort(connection)»,
				calls = «generateCalls(connection)»,
				rules = «generateRules(connection)»,
				simulator=self)
				
		«ENDFOR»
		'''
		
	}
	
	def generateSampleInstancesConfigurations(List<EnvironmentConnections> connections){
		

	
		'''
		«FOR connection : connections»
		self.components["«TransformationUtility.generateEnvCompName(connection)»"].configure(
				name  = "«TransformationUtility.generateEnvCompName(connection)»",
				inport=«generateInPort(connection)»,
				calls = «generateCalls(connection)»,
				rules = «generateSampleRules(connection)»,
				simulator=self,
				shname="«connection.component.name»",
				compCall=self.detmodel«connection.componentCall»
				)
				
		«ENDFOR»
		'''
		
	}
	
	
	def generatePeriodicEventSourceInstancesConfigurations(List<EnvironmentConnections> connections){
		'''
		«FOR connection : connections»
		self.components["«TransformationUtility.generateEnvCompName(connection)»"].configure(
				name  = "«TransformationUtility.generateEnvCompName(connection)»",
				calls = «generateCallEvents(connection)»,
				rules = «generateRules(connection)»,
				portevents = «generatePortevents(connection)»,
				simulator=self)
				
		«ENDFOR»
		'''
	}
	
	
	def generateEventSourceInstancesConfigurations(List<EnvironmentConnections> connections){
		'''
		«FOR connection : connections»
		«»
		self.components["«TransformationUtility.generateEnvCompName(connection)»"].configure(
				name  = "«TransformationUtility.generateEnvCompName(connection)»",
				calls = «generateCallEvents(connection)»,
				rules = «generateRules(connection)»,
				portevents = «generatePortevents(connection)»,
				simulator=self)
				
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

'''
	
	
	def generateConfigurations(List<EnvironmentConnections> connections)
'''

«generatePeriodicEventSourceInstancesConfigurations(
	connections.filter[c|c.component instanceof EnvironmentPeriodicEventSource].toList
)»

«generateEventSourceInstancesConfigurations(
	connections.filter[c|c.component instanceof EnvironmentEventSource].toList
)»

«generateDelayInstancesConfigurations(
	connections.filter[c|c.component instanceof EnvironmentDelay].toList
)»

«generateSwitchInstancesConfigurations(
	connections.filter[c|c.component instanceof EnvironmentSwitch].toList
)»

«generateSampleInstancesConfigurations(
	connections.filter[c|c.component instanceof EnvironmentSample].toList
)»


'''
	
}