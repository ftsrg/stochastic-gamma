package hu.bme.mit.gamma.architecture.transformation.builder

import hu.bme.mit.gamma.architecture.model.InformationFlow
import hu.bme.mit.gamma.architecture.model.InterfaceConnector
import hu.bme.mit.gamma.architecture.transformation.traceability.ArchitectureTrace
import hu.bme.mit.gamma.environment.model.EnvironmentModelFactory
import hu.bme.mit.gamma.environment.model.EnvironmentAsynchronousCompositeComponent
import hu.bme.mit.gamma.architecture.transformation.ElementTransformer
import hu.bme.mit.gamma.architecture.transformation.RelationTransfomer

import static extension hu.bme.mit.gamma.architecture.transformation.util.TransformationUtils.*
import static extension hu.bme.mit.gamma.statechart.derivedfeatures.StatechartModelDerivedFeatures.*

import hu.bme.mit.gamma.environment.model.EnvironmentAsynchronousCompositeComponentInstance
import hu.bme.mit.gamma.statechart.interface_.Port
import java.util.Map
import hu.bme.mit.gamma.environment.model.EnvironmentEventSource
import hu.bme.mit.gamma.statechart.statechart.StatechartModelFactory
import hu.bme.mit.gamma.statechart.interface_.Interface
import hu.bme.mit.gamma.statechart.composite.CompositeModelFactory
import hu.bme.mit.gamma.statechart.composite.BroadcastChannel
import hu.bme.mit.gamma.architecture.model.ArchitectureSubcompnent
import hu.bme.mit.gamma.statechart.composite.ComponentInstance
import hu.bme.mit.gamma.statechart.interface_.InterfaceModelFactory
import hu.bme.mit.gamma.architecture.model.Connector
import hu.bme.mit.gamma.architecture.transformation.errors.ArchitectureException
import hu.bme.mit.gamma.environment.stochastic.stochastic.StochasticFactory
import hu.bme.mit.gamma.expression.model.ExpressionModelFactory
import java.math.BigDecimal
import hu.bme.mit.gamma.statechart.composite.AsynchronousCompositeComponent
import hu.bme.mit.gamma.statechart.statechart.AsynchronousStatechartDefinition

class InterfaceComponentBuilder {
	
	val Connector connector
	val ArchitectureTrace trace
	val stochModelFactory = EnvironmentModelFactory.eINSTANCE
	val distModelFactory = StochasticFactory.eINSTANCE
	static val sctModelFactory = StatechartModelFactory.eINSTANCE
	static val cmpModelFactory = CompositeModelFactory.eINSTANCE
	static val ifModelFactory = InterfaceModelFactory.eINSTANCE
	static val expModelFactory = ExpressionModelFactory.eINSTANCE
	val AsynchronousCompositeComponent component 
	//val EnvironmentAsynchronousCompositeComponentInstance instance
	val Map<InformationFlow,Port> inportMap=newHashMap
	val Map<InformationFlow,Port> outportMap=newHashMap
	//val EnvironmentEventSource failureSource
	//val BroadcastChannel failureChannel
	val Map<ArchitectureSubcompnent,ComponentInstance> instanceMap=newHashMap
	
	val extension ElementTransformer elementTransformer
	val extension RelationTransfomer relationTransformer
	var nameCNTR=0
	var portNameCNTR=0
	
	val Port failurePort
	
	new(Connector connector,ArchitectureTrace trace){
		this.component = cmpModelFactory.createAsynchronousCompositeComponent
		//this.instance = stochModelFactory.createEnvironmentAsynchronousCompositeComponentInstance
		this.connector=connector
		this.trace=trace
		this.elementTransformer=new ElementTransformer(trace)
		this.relationTransformer=new RelationTransfomer(trace)
		
		//this.failureSource=stochModelFactory.createEnvironmentEventSource
		
		
		component.name = connector.gammaName+nameCNTR++
		
		failurePort=createPort(elementTransformer.failureInterface,"failureIn",true)
		component.ports+=failurePort
		
		/* 
		failureSource.name = component.name + "_Failures"
		val failureInterface=ifModelFactory.createInterface
		failureInterface.name=component.name + "_FailureModes"
		trace.interfacePackage.interfaces+=failureInterface
		val failurePort=createPort(failureInterface,"failures",false)
		failureSource.outports+=failurePort
		val failureRule= stochModelFactory.createStochasticRule
		val filter= stochModelFactory.createComponentFilter
		val dist= distModelFactory.createExponentialRandomVariable
		val rate=expModelFactory.createDecimalLiteralExpression
		rate.value = new BigDecimal(101.1)
		dist.rate = rate
		failureRule.stochasticModel = dist
		failureRule.filter+=filter
		failureSource.behaviorRules+=failureRule
		component.environmentComponents += failureSource
		this.failureChannel=cmpModelFactory.createBroadcastChannel
		val pref = createInstancePortRef(failureSource,failurePort)
		failureChannel.providedPort=pref
		component.channels+=failureChannel*/
	}
	
	def getComponent(){
		return component
	}
	
	def createInstance(){
		val instance = cmpModelFactory.createAsynchronousComponentInstance
		instance.name = "inst"+component.name.toFirstUpper
		instance.type = component
		return instance
	}
		
	def createInstance(ArchitectureSubcompnent subcompnent){
		val instance = cmpModelFactory.createAsynchronousComponentInstance
		instance.name = "inst"+component.name.toFirstUpper
		instance.type = component
		instanceMap.put(subcompnent,instance)
		return instance
	}

	def getInstance(ArchitectureSubcompnent subcompnent){
		if (!instanceMap.containsKey(subcompnent)){
			throw new ArchitectureException("Subcomponent has no interface component",subcompnent)
		}
		return instanceMap.get(subcompnent)
	}
	
	def addFlow(InformationFlow flow){
		val type = flow.flowType
		var portName=flow.gammaName
		if (portName.blank){
			portName=flow.source.gammaName
		}
		portName=portName+nameCNTR+"_"+portNameCNTR
		val inport = type.createPort(portName,true)
		val outport = type.createPort(portName,false)
		component.ports+=inport
		component.ports+=outport
		inportMap.put(flow,inport)
		outportMap.put(flow,outport)
		val ifInst = cmpModelFactory.createAsynchronousComponentInstance
		ifInst.name=flow.gammaName+type.name+portNameCNTR++
		val ifType= trace.getInterfaceComponent(type)
		ifInst.type = ifType
		component.components+=ifInst
		val inBinding=createPortBinding(inport,ifInst,ifType.requiredPorts.get(0))
		val outBinding=createPortBinding(outport,ifInst,ifType.providedPorts.get(0))
		component.portBindings+=inBinding
		component.portBindings+=outBinding
		nameCNTR++
		
		//add failure propagation channel
		component.portBindings+=createPortBinding(failurePort,ifInst,ifType.failurePort)
		
	}
	
	def getInPort(InformationFlow flow){
		return inportMap.get(flow)
	}
	
	def getOutPort(InformationFlow flow){
		return outportMap.get(flow)
	}
	
	static def createElementaryInterfaceComponents(){
		val comp = sctModelFactory.createAsynchronousStatechartDefinition
		comp.name="Communication"
	}
	
}