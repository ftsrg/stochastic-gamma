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
import hu.bme.mit.gamma.statechart.composite.AsynchronousComponentInstance
import hu.bme.mit.gamma.statechart.composite.ComponentInstance
import hu.bme.mit.gamma.architecture.model.ArchitectureSubcompnent
import hu.bme.mit.gamma.architecture.model.ArchitectureSubfunction
import hu.bme.mit.gamma.architecture.model.StructuralElement
import hu.bme.mit.gamma.architecture.transformation.errors.GammaTransformationException
import hu.bme.mit.gamma.architecture.transformation.AbstractArchitectureTransformer

class SingletonComponentBuilder  extends AbstractArchitectureTransformer{

	val ArchitectureSubcompnent subcomponent
	
	val EnvironmentAsynchronousCompositeComponent component
	val AsynchronousComponentInstance instance
	val Map<InformationFlow, Port> inportMap = newHashMap
	val Map<InformationFlow, Port> outportMap = newHashMap
	val commInstances = <AsynchronousComponentInstance>newLinkedList
	val EnvironmentEventSource failureSource

	val extension FailureModelGenerator failureModelGenerator
	var nameCNTR = 0
	var isBuilt = false

	static var hwNameCNTR = 0

	val EnvironmentAsynchronousCompositeComponent hwComponent
	val AsynchronousComponentInstance hwComponentInstance

	new(ArchitectureSubcompnent subcompnent, ArchitectureTrace trace){
		
		super(trace)
		
		this.component = stochModelFactory.createEnvironmentAsynchronousCompositeComponent
		this.instance = cmpModelFactory.createAsynchronousComponentInstance

		this.subcomponent = subcompnent

		this.failureModelGenerator = new FailureModelGenerator(trace)

		this.failureSource = stochModelFactory.createEnvironmentEventSource

		component.name = subcompnent.gammaName
		instance.name = "inst_" + component.name.toFirstUpper
		instance.type = component

		hwComponent = stochModelFactory.createEnvironmentAsynchronousCompositeComponent
		hwComponent.name = component.name + "_HardwareModel" + (hwNameCNTR)
		hwComponentInstance = cmpModelFactory.createAsynchronousComponentInstance
		hwComponentInstance.name = instance.name + "_HardwareModel" + (hwNameCNTR++)
		hwComponentInstance.type = hwComponent
		hwComponent.packageElement("subsystem_hardware")
		addInstance(hwComponentInstance)

		// this.failureChannel=cmpModelFactory.createBroadcastChannel
		/* 
		 * failureSource.name = component.name + "_Failures"
		 * val failurePort=createPort(null,"failures",false)
		 * failureSource.outports+=failurePort
		 * component.environmentComponents += failureSource
		 * val pref = createInstancePortRef(failureSource,failurePort)
		 * failureChannel.providedPort=pref
		 * component.channels+=failureChannel
		 */
		trace.add(subcompnent, instance)

	}

	/* 
	 * new(ArchitectureSubfunction subfunction,ArchitectureTrace trace){
	 * 	
	 * 	this.component = stochModelFactory.createEnvironmentAsynchronousCompositeComponent
	 * 	this.instance = cmpModelFactory.createAsynchronousComponentInstance
	 * 	
	 * 	this.element=subfunction
	 * 	this.trace=trace
	 * 	
	 * 	this.elementTransformer=new ElementTransformer(trace)
	 * 	this.relationTransformer=new RelationTransfomer(trace)
	 * 	
	 * 	this.failureSource=stochModelFactory.createEnvironmentEventSource
	 * 	
	 * 	
	 * 	component.name = subfunction.gammaName
	 * 	instance.name = "inst"+component.name.toFirstUpper
	 * 	instance.type = component
	 * 	
	 * 	//failureSource.name = component.name + "_Failures"
	 * 	//val failurePort=createPort(null,"failures",false)
	 * 	//failureSource.outports+=failurePort
	 * 	//component.environmentComponents += failureSource
	 * 	//this.failureChannel=cmpModelFactory.createBroadcastChannel
	 * 	//val pref = createInstancePortRef(failureSource,failurePort)
	 * 	//failureChannel.providedPort=pref
	 * 	//component.channels+=failureChannel
	 * 	
	 * 	trace.add(subfunction,instance)
	 * 	
	 * }
	 */
	def getInPort(InformationFlow flow) {
		val type = trace.get(flow.type) as Interface
		val inport = type.createPort(flow.inPortName + nameCNTR, true)
		component.ports += inport
		inportMap.put(flow, inport)
		nameCNTR++
		return inport
	}

	def getOutPort(InformationFlow flow) {
		val type = trace.get(flow.type) as Interface
		val outport = type.createPort(flow.outPortName + nameCNTR, false)
		component.ports += outport
		outportMap.put(flow, outport)
		nameCNTR++
		return outport
	}

	def getComponent() {
		return component
	}

	def getInstance() {
		return instance
	}

	def addPort(Port port) {
		component.ports += port
	}

	def addInstance(AsynchronousComponentInstance instance) {
		component.components += instance
	// add failure propagation channels?
	}

	def addCommInstance(AsynchronousComponentInstance instance) {
		commInstances += instance
		component.components += instance
	// add failure propagation channels?
	}

	def addChannel(ComponentInstance sourceInst, Port sourcePort, ComponentInstance targetInst, Port targetPort) {
		if (! component.components.contains(sourceInst) || sourceInst === null) {
			throw new GammaTransformationException("Source component instance is not in the component", sourceInst)
		}
		if (! component.components.contains(targetInst) || targetInst === null) {
			throw new GammaTransformationException("Target component instance is not in the component", targetInst)
		}
		if (! (targetInst as AsynchronousComponentInstance).type.ports.contains(targetPort) || targetPort === null) {
			throw new GammaTransformationException("Target port is not in the component", targetInst)
		}
		if (! (sourceInst as AsynchronousComponentInstance).type.ports.contains(sourcePort) || sourcePort === null) {
			throw new GammaTransformationException("Source port is not in the component", sourceInst)
		}
		channelBuilder.add(sourceInst, sourcePort, targetInst, targetPort)
	}

	def addBinding(Port port, ComponentInstance inst, Port instPort) {
		if (! component.components.contains(inst)) {
			throw new GammaTransformationException("Component instance is not in the component", inst)
		}
		if (! component.ports.contains(port)) {
			throw new GammaTransformationException("Composite port is not in the component", port)
		}
		if (! (inst as AsynchronousComponentInstance).type.ports.contains(instPort)) {
			throw new GammaTransformationException("Instance port is not in the component", instPort)
		}
		val bind = createPortBinding(port, inst, instPort)
		component.portBindings += bind
	}

	def build() {
		if (!isBuilt) {

			// creating failure propagation channels
			for (subfunc : subcomponent.subfunctions) {
				val funcCompInst = trace.get(subfunc) as AsynchronousComponentInstance
				val funcComp = funcCompInst.type
				for (subsubfunc : subfunc.type.subfunctions) {
					for (archInterface : subsubfunc.type.providedInterfaces) {
						val gammaInterface = trace.get(archInterface) as Interface
						val source = generateSource(subfunc.gammaName + "_" + subsubfunc.gammaName + "_" +
							gammaInterface.name, gammaInterface)
						val outPort = createPort(gammaInterface, subfunc.gammaName + "_" + subsubfunc.gammaName, false)

						val inPort = findPort(funcComp, gammaInterface,
							subsubfunc.gammaName + gammaInterface.name + "In", true)

						val sourcePort = source.outports.get(0)
						// hw.source -> hw.port -o)- subfunc.port
						hwComponent.ports += outPort
						addChannel(hwComponentInstance, outPort, funcCompInst, inPort)
						hwComponent.portBindings += createPortBinding(outPort, source, sourcePort)
						hwComponent.environmentComponents += source

					}
				}

			// component.packageElement("subsystems")
			}

			// communication failures
			for (commInst : commInstances) {
				val source = generateSource(commInst.name + "_Failures", elementTransformer.failureInterface)
				val outPort = createPort(elementTransformer.failureInterface, commInst.name+"Failures", false)

				val inPort = commInst.type.failurePort
				hwComponent.ports += outPort

				val sourcePort = source.outports.get(0)
				// hw.source -> hw.port -o)- commInst.port
				addChannel(hwComponentInstance, outPort, commInst, inPort)
				hwComponent.portBindings += createPortBinding(outPort, source, sourcePort)
				hwComponent.environmentComponents += source

			}

			component.channels += (channelBuilder.build)
			isBuilt = true
		} else {
			throw new GammaTransformationException("Singleton Component Builder is already built")
		}
	}

}
