package hu.bme.mit.gamma.environment.analysis.transformation.pythongen

import hu.bme.mit.gamma.environment.analysis.AnalysisComponent
import hu.bme.mit.gamma.environment.model.EnvironmentAsynchronousCompositeComponentInstance
import hu.bme.mit.gamma.environment.model.AbstractEnvironmentCompositeComponentInstance
import hu.bme.mit.gamma.environment.model.EnvironmentSynchronousCompositeComponentInstance
import hu.bme.mit.gamma.expression.util.ExpressionEvaluator
import hu.bme.mit.gamma.environment.stochastic.stochastic.StochasticExpression
import hu.bme.mit.gamma.statechart.composite.ComponentInstance
import hu.bme.mit.gamma.environment.analysis.transformation.util.TransformationUtility
import hu.bme.mit.gamma.environment.analysis.transformation.util.ElementaryComponentCollector

import static extension hu.bme.mit.gamma.environment.analysis.transformation.util.TransformationUtility.*
import static extension hu.bme.mit.gamma.environment.analysis.transformation.pythongen.PyroDistGenerator.*

class  PyroStochasticClassGenerator {
	
	static def generate(AnalysisComponent analysis_component,String packageName){
		var component=(analysis_component.analyzedComponent instanceof EnvironmentAsynchronousCompositeComponentInstance) ? 
			(analysis_component.analyzedComponent as EnvironmentAsynchronousCompositeComponentInstance).type :
			(analysis_component.analyzedComponent as EnvironmentSynchronousCompositeComponentInstance).type
		var stack=<ComponentInstance>newArrayList()
		var connections=ElementaryComponentCollector.collect(analysis_component.analyzedComponent,stack)
		var expEval=ExpressionEvaluator.INSTANCE
		var componentGenerator=new PyroComponentInstanceGenerator(packageName)
		var distGenerator=new PyroDistGenerator
		var param_cntr=new Integer(0)
'''  
class StochasticEventGenerator():


	def __init__(self,detmodel):
		self.detmodel=detmodel
		self.time=0.0
		self.events=[]
		self.dists=[]
		self.min_i=0
		# create Python objects from elementary stochastic components
		self.components=dict()
		«FOR arg : analysis_component.analyzedComponent.arguments SEPARATOR ","»
			«IF arg instanceof StochasticExpression»
				self.«analysis_component.analyzedComponent.type.parameterDeclarations.get(param_cntr).name»=torch.tensor([0.000001])
				#«param_cntr++»
			«ENDIF»
		«ENDFOR»
		#«param_cntr=0»
		# definition of elementary stochastic components
		«componentGenerator.gennerateInstances(connections)»
		# register input interfaces of elementary stochastic components
		«componentGenerator.generateConfigurations(connections)»

	def reset(self):
		self.time=0
		self.events.clear()
		for dist in self.dists:
			dist.reset()
		«FOR arg : analysis_component.analyzedComponent.arguments SEPARATOR ","»
			«IF arg instanceof StochasticExpression»
				self.«analysis_component.analyzedComponent.type.parameterDeclarations.get(param_cntr).name»[0]=pyro.sample("param_«(param_cntr).toString»",«(arg.randomvariable).generateDitribution»).detach()
			«ENDIF»
			#«param_cntr++»
		«ENDFOR»
		self.detmodel.reset()
		"""self.detmodel.reset(«TransformationUtility.generateDetmodelParamsNew(analysis_component)»)"""

	def generateEvents(self):
		for component in list(self.components.values()):
			component.generateEvents()

	# shall be called after the getEarliestTime() function
	def popEvent(self):
		event=self.events[self.min_i]
		self.events.remove(event)
		return event

	def getEarliestTime(self):
		mintime=1000000000000000.0
		min_i=0
		for i in range (len(self.events)):
			if self.events[i].eventTime<mintime:
				min_i=i
				mintime=self.events[min_i].eventTime
		self.min_i=min_i
		return mintime-self.time

'''
	}
}