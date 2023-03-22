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

class TransformationUtility {
	
	
	static def generateFuncParams(Event event) '''«FOR parameter : event.parameterDeclarations SEPARATOR ", "»«parameter.name.toFirstLower»«ENDFOR»'''
	
	
	static def String generateEnvCompName(EnvironmentConnections connection){
		return generateEnvCompName(connection.componentCall,connection.component)
	}
	
	static def String generateEnvCompName(String componentCall,ElementaryEnvironmentComponentInstance component){
		return componentCall.replaceAll(".get","")+"."+component.name
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
		name=name+aspect.event.port.name.toFirstUpper+aspect.event.event.name.toFirstUpper
		return name
	}
	
	static def String generateEndConditionName(EndCondition endCondition){
		var compRef=endCondition.event.component
		var name="EndCondition"
		while (!(compRef==null)) {
			name=name+compRef.component.name.toFirstUpper
			compRef=compRef.recursivecomponentreference
		}
		name=name+endCondition.event.port.name.toFirstUpper+endCondition.event.event.name.toFirstUpper
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
	
	static def generateSimulationReturn(List<AnalysisAspect> aspects)
	'''
	#register the result of the analysis to the Pyro
	«FOR aspect : aspects»«generateGetValueCalls(aspect)»
	pyro.deterministic("«aspect.generateAspectName»",torch.tensor(«generateGetValueCalls(aspect)»))
	«ENDFOR»
	#return the result of the simulation
	return «FOR aspect : aspects»«generateGetValueCalls(aspect)»«ENDFOR»
	'''
	
	static dispatch def generateGetValueCalls(MeanTime aspect)
	'''stochmodel.time'''
	
	static dispatch def generateGetValueCalls(Probability aspect)
	'''state2num(detmodel.monitorOf«TransformationUtility.generateAspectName(aspect)».state)'''
	
	static dispatch def generateGetValueCalls(MeanParameter aspect)
	'''detmodel.monitorOf«TransformationUtility.generateAspectName(aspect)».meanParameter()'''
	
	static dispatch def generateGetValueCalls(Frequency aspect)
	'''detmodel.monitorOf«TransformationUtility.generateAspectName(aspect)».freq()''' 
	
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
}