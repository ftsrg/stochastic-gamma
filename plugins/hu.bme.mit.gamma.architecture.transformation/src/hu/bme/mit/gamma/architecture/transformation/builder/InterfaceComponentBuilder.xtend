package hu.bme.mit.gamma.architecture.transformation.builder

import hu.bme.mit.gamma.architecture.model.InforationFlow
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

class InterfaceComponentBuilder {
	
	val InterfaceConnector connector
	val ArchitectureTrace trace
	val stochModelFactory = EnvironmentModelFactory.eINSTANCE
	static val sctModelFactory = StatechartModelFactory.eINSTANCE
	static val cmpModelFactory = CompositeModelFactory.eINSTANCE
	val EnvironmentAsynchronousCompositeComponent component 
	//val EnvironmentAsynchronousCompositeComponentInstance instance
	val Map<InforationFlow,Port> inportMap=newHashMap
	val Map<InforationFlow,Port> outportMap=newHashMap
	val EnvironmentEventSource failureSource
	val BroadcastChannel failureChannel
	val Map<ArchitectureSubcompnent,ComponentInstance> instanceMap=newHashMap
	
	val extension ElementTransformer elementTransformer
	val extension RelationTransfomer relationTransformer
	
	new(InterfaceConnector connector,ArchitectureTrace trace){
		this.component = stochModelFactory.createEnvironmentAsynchronousCompositeComponent
		//this.instance = stochModelFactory.createEnvironmentAsynchronousCompositeComponentInstance
		this.connector=connector
		this.trace=trace
		this.elementTransformer=new ElementTransformer(trace)
		this.relationTransformer=new RelationTransfomer(trace)
		
		this.failureSource=stochModelFactory.createEnvironmentEventSource
		
		
		component.name = connector.gammaName
		
		failureSource.name = component.name + "_Failures"
		val failurePort=createPort(null,"failures",false)
		failureSource.outports+=failurePort
		component.environmentComponents += failureSource
		this.failureChannel=cmpModelFactory.createBroadcastChannel
		val pref = createInstancePortRef(failureSource,failurePort)
		failureChannel.providedPort=pref
		component.channels+=failureChannel
	}
	
	def getComponent(){
		return component
	}
	
	def createInstance(){
		val instance = stochModelFactory.createEnvironmentAsynchronousCompositeComponentInstance
		instance.name = "inst"+component.name.toFirstUpper
		instance.type = component
		return instance
	}
		
	def createInstance(ArchitectureSubcompnent subcompnent){
		val instance = stochModelFactory.createEnvironmentAsynchronousCompositeComponentInstance
		instance.name = "inst"+component.name.toFirstUpper
		instance.type = component
		instanceMap.put(subcompnent,instance)
		return instance
	}

	def getInstance(ArchitectureSubcompnent subcompnent){
		return instanceMap.get(subcompnent)
	}
	
	def addFlow(InforationFlow flow){
		val type = trace.get(flow.type) as Interface
		val inport = type.createPort(flow.inPortName,true)
		val outport = type.createPort(flow.outPortName,false)
		component.ports+=inport
		component.ports+=outport
		inportMap.put(flow,inport)
		outportMap.put(flow,outport)
		val ifInst = cmpModelFactory.createAsynchronousComponentInstance
		ifInst.name=flow.gammaName
		val ifType= trace.getInterfaceComponent(type)
		ifInst.type = ifType
		component.components+=ifInst
		val inBinding=createPortBinding(inport,ifInst,ifType.requiredPorts.get(0))
		val outBinding=createPortBinding(outport,ifInst,ifType.providedPorts.get(0))
		component.portBindings+=inBinding
		component.portBindings+=outBinding
		
	}
	
	def getInPort(InforationFlow flow){
		return inportMap.get(flow)
	}
	
	def getOutPort(InforationFlow flow){
		return outportMap.get(flow)
	}
	
	static def createElementaryInterfaceComponents(){
		val comp = sctModelFactory.createAsynchronousStatechartDefinition
		comp.name="Communication"
	}
	
}