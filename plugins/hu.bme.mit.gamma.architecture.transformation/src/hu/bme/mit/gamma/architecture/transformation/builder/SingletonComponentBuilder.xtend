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
import hu.bme.mit.gamma.statechart.composite.AsynchronousComponentInstance
import hu.bme.mit.gamma.statechart.composite.ComponentInstance
import hu.bme.mit.gamma.architecture.model.ArchitectureSubcompnent
import hu.bme.mit.gamma.architecture.model.ArchitectureSubfunction
import hu.bme.mit.gamma.architecture.model.StructuralElement

class SingletonComponentBuilder {
	
	
	val StructuralElement element
	val ArchitectureTrace trace
	val stochModelFactory = EnvironmentModelFactory.eINSTANCE
	static val sctModelFactory = StatechartModelFactory.eINSTANCE
	static val cmpModelFactory = CompositeModelFactory.eINSTANCE
	val EnvironmentAsynchronousCompositeComponent component 
	val EnvironmentAsynchronousCompositeComponentInstance instance
	val Map<InforationFlow,Port> inportMap=newHashMap
	val Map<InforationFlow,Port> outportMap=newHashMap
	val EnvironmentEventSource failureSource
	val BroadcastChannel failureChannel
	
	val channelBuilder = new Channelbuilder
	
	val extension ElementTransformer elementTransformer
	val extension RelationTransfomer relationTransformer
	
	new(ArchitectureSubcompnent subcompnent,ArchitectureTrace trace){
		
		this.component = stochModelFactory.createEnvironmentAsynchronousCompositeComponent
		this.instance = stochModelFactory.createEnvironmentAsynchronousCompositeComponentInstance
		
		this.element=subcompnent
		this.trace=trace
		
		this.elementTransformer=new ElementTransformer(trace)
		this.relationTransformer=new RelationTransfomer(trace)
		
		this.failureSource=stochModelFactory.createEnvironmentEventSource
		
		
		component.name = subcompnent.gammaName
		instance.name = "inst"+component.name.toFirstUpper
		instance.type = component
		
		failureSource.name = component.name + "_Failures"
		val failurePort=createPort(null,"failures",false)
		failureSource.outports+=failurePort
		component.environmentComponents += failureSource
		this.failureChannel=cmpModelFactory.createBroadcastChannel
		val pref = createInstancePortRef(failureSource,failurePort)
		failureChannel.providedPort=pref
		component.channels+=failureChannel
		
		trace.add(subcompnent,instance)
		
	}
	
	new(ArchitectureSubfunction subfunction,ArchitectureTrace trace){
		
		this.component = stochModelFactory.createEnvironmentAsynchronousCompositeComponent
		this.instance = stochModelFactory.createEnvironmentAsynchronousCompositeComponentInstance
		
		this.element=subfunction
		this.trace=trace
		
		this.elementTransformer=new ElementTransformer(trace)
		this.relationTransformer=new RelationTransfomer(trace)
		
		this.failureSource=stochModelFactory.createEnvironmentEventSource
		
		
		component.name = subfunction.gammaName
		instance.name = "inst"+component.name.toFirstUpper
		instance.type = component
		
		failureSource.name = component.name + "_Failures"
		val failurePort=createPort(null,"failures",false)
		failureSource.outports+=failurePort
		component.environmentComponents += failureSource
		this.failureChannel=cmpModelFactory.createBroadcastChannel
		val pref = createInstancePortRef(failureSource,failurePort)
		failureChannel.providedPort=pref
		component.channels+=failureChannel
		
		trace.add(subfunction,instance)
		
	}

	
	def getInPort(InforationFlow flow){
		val type = trace.get(flow.type) as Interface
		val inport = type.createPort(flow.inPortName,true)
		component.ports+=inport
		inportMap.put(flow,inport)
		return inport
	}
	
	def getOutPort(InforationFlow flow){
		val type = trace.get(flow.type) as Interface
		val outport = type.createPort(flow.outPortName,false)
		component.ports+=outport
		outportMap.put(flow,outport)
		return outport
	}
	
	def getComponent(){
		return component
	}
	
	def getInstance(){
		return instance
	}
	
	def addPort(Port port){
		component.ports+=port
	}
	
	def addInstance(ComponentInstance instance){
		component.instances+=instance
		// add failure propagation channels?
	}
	
	def addChannel(ComponentInstance sourceInst,Port sourcePort,ComponentInstance targetInst,Port targetPort){
		channelBuilder.add(sourceInst,sourcePort,targetInst,targetPort)
	}

	def addBinding(Port port, ComponentInstance inst, Port instPort){
		val bind=createPortBinding(port, inst, instPort)
		component.portBindings+=bind
	}
	
	
	def build(){
		component.channels+=(channelBuilder.build)
	}
	
	
}