package hu.bme.mit.gamma.architecture.transformation.builder

import hu.bme.mit.gamma.statechart.interface_.Interface
import hu.bme.mit.gamma.environment.model.EnvironmentModelFactory
import hu.bme.mit.gamma.statechart.statechart.StatechartModelFactory
import hu.bme.mit.gamma.statechart.composite.CompositeModelFactory

import static extension hu.bme.mit.gamma.architecture.transformation.util.TransformationUtils.*
import static extension hu.bme.mit.gamma.statechart.derivedfeatures.StatechartModelDerivedFeatures.*
import hu.bme.mit.gamma.statechart.interface_.InterfaceModelFactory
import hu.bme.mit.gamma.architecture.transformation.ElementTransformer
import hu.bme.mit.gamma.architecture.transformation.RelationTransfomer
import hu.bme.mit.gamma.architecture.transformation.traceability.ArchitectureTrace
import hu.bme.mit.gamma.environment.stochastic.stochastic.StochasticFactory
import hu.bme.mit.gamma.expression.model.ExpressionModelFactory

import java.math.BigDecimal
import hu.bme.mit.gamma.statechart.interface_.Event
import hu.bme.mit.gamma.statechart.interface_.EventDirection

class FailureModelGenerator {
	
	//val component=
	static val stochModelFactory = EnvironmentModelFactory.eINSTANCE
	static val sctModelFactory = StatechartModelFactory.eINSTANCE
	static val cmpModelFactory = CompositeModelFactory.eINSTANCE
	static val ifModelFactory = InterfaceModelFactory.eINSTANCE
	static val distModelFactory = StochasticFactory.eINSTANCE
	static val expModelFactory = ExpressionModelFactory.eINSTANCE
	
	val ArchitectureTrace trace
	
	
	
	static int nameCNTR=0
	
	static val defaultRate="100.0"
	
	val extension ElementTransformer elementTransformer
	val extension RelationTransfomer relationTransformer
	
	new(ArchitectureTrace trace){
		//this.component = stochModelFactory.createEnvironmentAsynchronousCompositeComponent
		//this.instance = stochModelFactory.createEnvironmentAsynchronousCompositeComponentInstance
		
		this.trace=trace
		this.elementTransformer=new ElementTransformer(trace)
		this.relationTransformer=new RelationTransfomer(trace)
			
		
	}
	
	
	
	def generateSource(String name,Interface _interface){
		
		val failureSource=stochModelFactory.createEnvironmentEventSource
		failureSource.name = name + "_Failures"+nameCNTR++
		val failurePort=ElementTransformer._createPort(_interface,"failures",false)
		failureSource.outports+=failurePort
		val failureRule= stochModelFactory.createStochasticRule
		val filter= stochModelFactory.createComponentFilter
		val dist= distModelFactory.createExponentialRandomVariable
		val rate = expModelFactory.createDecimalLiteralExpression
		rate.value = new BigDecimal(defaultRate)
		dist.rate = rate
		failureRule.stochasticModel = dist
		failureRule.filter+=filter
		failureSource.behaviorRules+=failureRule

		return failureSource
	}
	
}