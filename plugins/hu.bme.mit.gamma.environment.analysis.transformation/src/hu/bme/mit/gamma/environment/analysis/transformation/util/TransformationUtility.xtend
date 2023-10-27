package hu.bme.mit.gamma.environment.analysis.transformation.util

import hu.bme.mit.gamma.environment.analysis.AnalysisAspect
import hu.bme.mit.gamma.statechart.interface_.Event
import hu.bme.mit.gamma.expression.model.Type
import hu.bme.mit.gamma.expression.model.IntegerTypeDefinition
import hu.bme.mit.gamma.expression.util.TypeSerializer
import hu.bme.mit.gamma.expression.model.ParameterDeclaration
import hu.bme.mit.gamma.environment.analysis.EndCondition
import java.util.List
import hu.bme.mit.gamma.environment.analysis.MeanTime
import hu.bme.mit.gamma.environment.analysis.MeanParameter
import hu.bme.mit.gamma.environment.analysis.Probability
import hu.bme.mit.gamma.environment.analysis.Frequency
import hu.bme.mit.gamma.environment.model.ElementaryEnvironmentComponentInstance
import hu.bme.mit.gamma.expression.model.Expression
import hu.bme.mit.gamma.environment.stochastic.stochastic.StochasticExpression
import hu.bme.mit.gamma.expression.model.DirectReferenceExpression
import hu.bme.mit.gamma.expression.util.ExpressionEvaluator
import hu.bme.mit.gamma.environment.analysis.AnalysisComponent
import hu.bme.mit.gamma.environment.analysis.AnalysisCondition
import hu.bme.mit.gamma.environment.analysis.MeanTimeBetweenEvents
import hu.bme.mit.gamma.environment.analysis.ObserveParameter
import hu.bme.mit.gamma.environment.analysis.ObserveTime
import hu.bme.mit.gamma.environment.analysis.AssumeRaised
import hu.bme.mit.gamma.environment.analysis.AssumeNotRaised


import static extension hu.bme.mit.gamma.environment.analysis.transformation.pythongen.PyroDistGenerator.*
import hu.bme.mit.gamma.environment.analysis.ObserveCondition
import hu.bme.mit.gamma.environment.analysis.AssumeCondition

class TransformationUtility {
	
	
	static def generateFuncParams(Event event) '''«FOR parameter : event.parameterDeclarations SEPARATOR ", "»«parameter.name.toFirstLower»«ENDFOR»'''
	
	
	static def String generateEnvCompName(EnvironmentConnections connection){
		return generateEnvCompName(connection.componentCall,connection.component)
	}
	
	static def String generateEnvCompName(String componentCall,ElementaryEnvironmentComponentInstance component){
		return componentCall.replaceFirst(".get","").replaceAll(".get",".").replaceAll('''\(\)''',"")+"."+component.name
	}
	 
	
	static def String generateAspectName(AnalysisAspect aspect){
		var compRef=aspect.event.component
		var name="Aspect"
		if (aspect instanceof AnalysisCondition){
			name="Condition"
		}
		while (!(compRef===null)) {
			name=name+compRef.component.name.toFirstUpper
			compRef=compRef.recursivecomponentreference
		}
		name=name+"_"+aspect.event.port.name.toFirstUpper+"_"+aspect.event.event.name.toFirstUpper
		return name
	}
	
	static def String generateEndConditionName(EndCondition endCondition){
		var compRef=endCondition.event.component
		var name="EndCondition"
		while (!(compRef==null)) {
			name=name+compRef.component.name.toFirstUpper
			compRef=compRef.recursivecomponentreference
		}
		name=name+"_"+endCondition.event.port.name.toFirstUpper+"_"+endCondition.event.event.name.toFirstUpper
		return name
	}
	
	
	static def generateParameters(Event event) '''«FOR parameter : event.parameterDeclarations SEPARATOR ", "»«TypeSerializer.INSTANCE.serialize(parameter.type).transformType» «parameter.generateName»«ENDFOR»'''

	
	static def transformType(String type) {
		switch (type) {
			case "integer": 
				return "long"
			case "string": 
				return "String"
			case "real": 
				return "double"
			default:
				return type
		}
	}
	
	static def generateName(ParameterDeclaration parameter) '''«parameter.name.toFirstLower»'''
	
	
	static def generatePyroAspectRegistry(List<AnalysisAspect> aspects){
		'''
		
		#register the result of the analysis to the Pyro
		«FOR aspect : aspects»
			«IF aspect instanceof MeanTime»
				# register the time only if the event is raised
				if str(detmodel.monitorOf«TransformationUtility.generateAspectName(aspect)».state) != "run" :
					pyro.deterministic("«aspect.pyroName»",torch.tensor(«aspect.valueCall»))
			«ELSE»
				pyro.deterministic("«aspect.pyroName»",torch.tensor(«aspect.valueCall»))
			«ENDIF»
		«ENDFOR»
		'''
	}
	
	
	static def generateDebugAspectRegistry(List<AnalysisAspect> aspects){
''' 
		#register the result of the analysis to the Pyro
		«FOR aspect : aspects»
			if DEBUG:
				# register the time only if the event is raised
				if int(detmodel.monitorOf«TransformationUtility.generateAspectName(aspect)».freq) != «TransformationUtility.generateAspectName(aspect)»Freq :
					«TransformationUtility.generateAspectName(aspect)»Freq=int(detmodel.monitorOf«TransformationUtility.generateAspectName(aspect)».freq)
					dprint(f'detmodel -> analysis : "«aspect.event.port.name».«aspect.event.event.name» at time {stochmodel.time}"')
		«ENDFOR»
		'''
	}
	static def generateDebugAspectVars(List<AnalysisAspect> aspects){
'''	
		# DEBUG variables
		«FOR aspect : aspects»
		«TransformationUtility.generateAspectName(aspect)»Freq=0
		«ENDFOR»
		'''
	}
	
	
	
	static def generatePyroConditionRegistry(List<AnalysisCondition> conditions){
		'''
		
		#register the conditions to the Pyro
		«FOR condition : conditions»
			«condition.registerCondition»
		«ENDFOR»
		'''
	}
	
	static dispatch def registerCondition(ObserveCondition condition){
		'''pyro.sample("«condition.pyroName»",«condition.randomvariable.generateDitribution», obs = torch.tensor(«condition.valueCall»))'''
	}
	
	static dispatch def registerCondition(AssumeRaised condition){
		'''pyro.sample("«condition.pyroName»",pyro.distributions.Bernoulli(torch.tensor(0.999)), obs = torch.tensor(«condition.valueCall»))'''
	}
	
	static dispatch def registerCondition(AssumeNotRaised condition){
		'''pyro.sample("«condition.pyroName»",pyro.distributions.Bernoulli(torch.tensor(0.0001)), obs = torch.tensor(«condition.valueCall»))'''
	}
	
	static dispatch def registerCondition(AnalysisCondition condition){
		throw new UnsupportedOperationException(condition.class.toString + " type of condition is not supported yet.")
	}
	
	static def generateSimulationReturn(List<AnalysisAspect> aspects){
		'''
	
	#return the result of the simulation
	return «FOR aspect : aspects SEPARATOR ", "»«aspect.valueCall»«ENDFOR»
		'''
	}
	
	static dispatch def getValueCall(MeanTime aspect)
		'''stochmodel.time'''
	
	static dispatch def getValueCall(Probability aspect)
		'''state2num(detmodel.monitorOf«TransformationUtility.generateAspectName(aspect)».state)'''
	
	static dispatch def getValueCall(MeanParameter aspect)
		'''detmodel.monitorOf«TransformationUtility.generateAspectName(aspect)».meanParameter()'''
	
	static dispatch def getValueCall(Frequency aspect)
		'''detmodel.monitorOf«TransformationUtility.generateAspectName(aspect)».freq()''' 
	
	
	static dispatch def getValueCall(ObserveTime aspect)
		'''stochmodel.time'''
	
	static dispatch def getValueCall(ObserveParameter aspect)
		'''detmodel.monitorOf«TransformationUtility.generateAspectName(aspect)».meanParameter()'''
		
	
	static dispatch def getValueCall(AssumeCondition aspect)
		'''state2num(detmodel.monitorOf«TransformationUtility.generateAspectName(aspect)».state)'''
	
	
	
	//Mixing Double expressions and Stochastic Expressions
	static def evaluateMixedExpression(Expression expression){
		var expEval=ExpressionEvaluator.INSTANCE;
	'''«IF expression instanceof DirectReferenceExpression»self.«expression.declaration.name»[0]«ELSE»torch.tensor(«Double.toString(expEval.evaluateDecimal(expression))»)«ENDIF»'''
	
	}
	
	static def generateDetmodelParamsInit(AnalysisComponent component){
		var expEval=ExpressionEvaluator.INSTANCE
		'''
		«FOR arg : component.analyzedComponent.arguments SEPARATOR ', '»«IF arg instanceof StochasticExpression»0.0«ELSE»«Double.toString(expEval.evaluate(arg))»«ENDIF»«ENDFOR»
		'''
	}
	static def generateDetmodelParamsReset(AnalysisComponent component){
		'''
		«FOR param : component.analyzedComponent.type.parameterDeclarations SEPARATOR ', '»«TypeSerializer.INSTANCE.serialize(param.type).transformType» «param.name»«ENDFOR»
		'''
	}
	static def generateDetmodelParamsResetInit(AnalysisComponent component){
		'''
		«FOR param : component.analyzedComponent.type.parameterDeclarations SEPARATOR ', '»«param.name»«ENDFOR»
		'''
	}
	
	static var i=0
	
	static def increment(){
		i=i+1;
	}
	
	static def generateDetmodelParamsNew(AnalysisComponent component){
		var expEval=ExpressionEvaluator.INSTANCE
		i=0
		'''
		«FOR arg : component.analyzedComponent.arguments SEPARATOR ', '»«IF arg instanceof StochasticExpression»self.«component.analyzedComponent.type.parameterDeclarations.get(i).name»«ELSE»«Double.toString(expEval.evaluate(arg))»«ENDIF»#«increment»«ENDFOR»
		'''
	}
	
	
	dispatch static def pyroName(Frequency aspect){
		'''«aspect.event.port.name»_«aspect.event.event.name»_freq'''
	}
	
	dispatch static def pyroName(MeanTime aspect){
		'''«aspect.event.port.name»_«aspect.event.event.name»_mt'''
	}
	
	dispatch static def pyroName(MeanParameter aspect){
		'''«aspect.event.port.name»_«aspect.event.event.name»_mp'''
	}
	
	dispatch static def pyroName(MeanTimeBetweenEvents aspect){
		'''«aspect.event.port.name»_«aspect.event.event.name»_mtbe'''
	}
	
	dispatch static def pyroName(Probability aspect){
		'''«aspect.event.port.name»_«aspect.event.event.name»_prob'''
	}
	
	dispatch static def pyroName(ObserveParameter aspect){
		'''«aspect.event.port.name»_«aspect.event.event.name»_cond_op'''
	}
	
	dispatch static def pyroName(ObserveTime aspect){
		'''«aspect.event.port.name»_«aspect.event.event.name»_cond_ot'''
	}
	
	dispatch static def pyroName(AssumeRaised aspect){
		'''«aspect.event.port.name»_«aspect.event.event.name»_cond_ar'''
	}
	
	dispatch static def pyroName(AssumeNotRaised aspect){
		'''«aspect.event.port.name»_«aspect.event.event.name»_cond_anr'''
	}
	
	
	dispatch static def pyroName(AnalysisAspect aspect){
		throw new UnsupportedOperationException(aspect.class.toGenericString + " type of AnalysisAspect is not supported yet. ")
	}
}