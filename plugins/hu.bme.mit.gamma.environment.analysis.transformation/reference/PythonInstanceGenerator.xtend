package hu.bme.mit.gamma.environment.analysis.transformation.pythongen

import hu.bme.mit.gamma.environment.model.EnvironmentDelay
import hu.bme.mit.gamma.environment.model.EnvironmentSample
import hu.bme.mit.gamma.environment.model.EnvironmentSwitch
import hu.bme.mit.gamma.environment.model.EnvironmentExternSimulation
import hu.bme.mit.gamma.environment.model.EnvironmentEventSource

class PythonInstanceGenerator {
	
	static def generateClass(){
		'''
		class ElementaryComponent():
			def __init__(self,events):
		'''
	}
	
	static dispatch def generate(EnvironmentDelay instance){
		
	}
	
	static dispatch def generate(EnvironmentSample instance){
		
	}
	
	static dispatch def generate(EnvironmentSwitch instance){
		
	}
	
	static dispatch def generate(EnvironmentExternSimulation instance){
		
	}
	
	static dispatch def generate(EnvironmentEventSource instance){
		
	}
	
	def generateCallsOld(EnvironmentConnections connection){
		'''
		{«IF !connection.outCalls.keys.empty»«FOR port : connection.component.outports SEPARATOR ", "»
			"«port.name.toFirstUpper»" : [«IF connection.outCalls.get(port).empty»None«ENDIF»«FOR call : connection.outCalls.get(port) SEPARATOR ", "»
				«IF call.charAt(0)=='_'»«call.replaceFirst("\\_","")» «ELSE»self.detmodel«connection.componentCall»«call»«ENDIF»«ENDFOR»]«ENDFOR»«ENDIF»}
		'''
	}
	
	def generateCallEventsOld(EnvironmentConnections connection){
		'''
		{«IF !connection.outCalls.keys.empty»«FOR port : connection.component.outports SEPARATOR ", "»"«port.name.toFirstUpper»" : {
		«FOR call : connection.outCalls.get(port) SEPARATOR ", "»
		«FOR event : port.interfaceRealization.interface.events.map[e|e.event]SEPARATOR ", "»
			"«event.name.toFirstUpper»" : (lambda:«IF call.charAt(0)=='_'»«call.replaceAll("^\\_","")»«ELSE»self.detmodel«connection.componentCall»«call»«ENDIF».raise«event.name.toFirstUpper»(«TransformationUtility.generateFuncParams(event)»))«ENDFOR»«ENDFOR»}«ENDFOR»«ENDIF»}
		'''
	}
	
	def generateRulesOld(EnvironmentConnections connection){
		'''
		{«FOR port : connection.component.outports SEPARATOR ", "»"«port.name.toFirstUpper»" : {
			«FOR event : port.interfaceRealization.interface.events SEPARATOR ", "» 
				«var rules=connection.component.behaviorRules
						.filter[r|!r.filter.filter[f|f instanceof EventFilter].filter[f|(f as EventFilter).event.event==event.event].empty].toList»
				#«if(rules.isEmpty){rules=connection.component.behaviorRules.filter[r|!r.filter.filter[f|f instanceof PortFilter].
					filter[f|(f as PortFilter).port==port].empty].toList}»
				#«if(rules.isEmpty){rules=connection.component.behaviorRules.filter[r|!r.filter.filter[f|f instanceof ComponentFilter].empty].toList}»
				«IF (!rules.empty)»«var rule=rules.get(0)»"«event.event.name.toFirstUpper»" : «IF rule instanceof SimulationRule»«rule.simulation.simulationClassName»Instance«ELSEIF rule instanceof StochasticRule»
					«IF connection.component instanceof EnvironmentEventSource && rule.stochasticModel instanceof BernoulliRandomVariable»	
					«distGenerator.generateZerotimeStochasticModel(rule.stochasticModel as BernoulliRandomVariable,connection.component.name)»«ELSE»
						«distGenerator.generateStochasticModel(rule.stochasticModel,connection.component.name)»«ENDIF»«ENDIF»
				«ENDIF»
			«ENDFOR»
		}«ENDFOR»}
		'''
	}	
}