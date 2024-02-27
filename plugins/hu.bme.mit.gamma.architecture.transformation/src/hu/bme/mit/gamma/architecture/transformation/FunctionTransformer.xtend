package hu.bme.mit.gamma.architecture.transformation

import hu.bme.mit.gamma.architecture.model.ArchitectureFunction
import hu.bme.mit.gamma.statechart.interface_.Package
import hu.bme.mit.gamma.architecture.model.ArchitectureSubfunction
import java.util.Map
import hu.bme.mit.gamma.environment.model.EnvironmentModelFactory

import static extension hu.bme.mit.gamma.architecture.transformation.util.TransformationUtils.*
import static extension hu.bme.mit.gamma.architecture.transformation.RelationTransfomer.*
import hu.bme.mit.gamma.architecture.transformation.traceability.ArchitectureTrace
import hu.bme.mit.gamma.architecture.model.InformationFlow
import hu.bme.mit.gamma.architecture.model.ArchitecturePort
import hu.bme.mit.gamma.statechart.composite.AsynchronousComponent
import hu.bme.mit.gamma.statechart.interface_.Interface
import hu.bme.mit.gamma.statechart.interface_.Port
import hu.bme.mit.gamma.architecture.transformation.errors.ArchitectureException
import hu.bme.mit.gamma.architecture.transformation.builder.Channelbuilder
import hu.bme.mit.gamma.statechart.composite.ComponentInstance
import hu.bme.mit.gamma.environment.model.EnvironmentAsynchronousCompositeComponent
import hu.bme.mit.gamma.architecture.model.ArchitectureElement
import hu.bme.mit.gamma.architecture.transformation.builder.PrimitiveFunctionBuilder
import hu.bme.mit.gamma.statechart.interface_.RealizationMode
import hu.bme.mit.gamma.statechart.composite.AsynchronousComponentInstance
import java.util.Set
import hu.bme.mit.gamma.statechart.statechart.StatechartModelFactory
import hu.bme.mit.gamma.statechart.composite.CompositeModelFactory
import hu.bme.mit.gamma.statechart.composite.AsynchronousCompositeComponent

class FunctionTransformer {

	static def transformComponentFunction(ArchitectureFunction componentFunction, ArchitectureTrace trace) {
		val transformer = new FunctionTransformer(componentFunction, trace)
		val component = transformer.execute
	// return component
	}

	val ArchitectureFunction function
	val ArchitectureTrace trace
	val Map<ArchitectureSubfunction, PrimitiveFunctionBuilder> builderMap = newHashMap
	// val stochModelFactory = EnvironmentModelFactory.eINSTANCE
	val cmpModelFactory = CompositeModelFactory.eINSTANCE
	val channelBuilder = new Channelbuilder
	val AsynchronousCompositeComponent component
	val instancePorts = <AsynchronousComponentInstance, Set<Port>>newHashMap

	val extension ElementTransformer elementTransformer
	val extension RelationTransfomer relationTransformer

	new(ArchitectureFunction function, ArchitectureTrace trace) {
		// this.component = stochModelFactory.createEnvironmentAsynchronousCompositeComponent
		this.component = cmpModelFactory.createAsynchronousCompositeComponent
		this.component.name = function.gammaName
		this.function = function
		this.trace = trace
		this.elementTransformer = new ElementTransformer(trace)
		this.relationTransformer = new RelationTransfomer(trace)
	}

	def execute() {

		// val gammaFunction = stochModelFactory.createEnvironmentAsynchronousCompositeComponent
		val gammaFunction = cmpModelFactory.createAsynchronousCompositeComponent
		gammaFunction.name = function.gammaName

		val subfuncBuilders = <PrimitiveFunctionBuilder>newArrayList

		for (subfunc : function.subfunctions) {
			val builder = PrimitiveFunctionBuilder.createBuilder(subfunc, trace)
			subfuncBuilders.add(builder)
			builderMap.put(subfunc, builder)
			val inst = subfunc.transformSubfunction
			component.components += inst
			instancePorts.put(inst, inst.type.ports.toSet)
		}

		for (archPort : function.ports) {
			val port = archPort.transformPort
			component.ports += port
		}

		for (flow : function.informationFlows) {
			if (flow.isInPortBinding) {
				val port = trace.get(flow.source) as Port
				val targetInst = flow.flowTargetInst
				val targetPort = getFlowTargetPort(flow)
				component.portBindings += createPortBinding(port, targetInst, targetPort)
			} else if (flow.isOutPortBinding) {
				val port = trace.get(flow.target) as Port
				val sourceInst = flow.flowSourceInst
				val sourcePort = getFlowSourcePort(flow)
				component.portBindings += createPortBinding(port, sourceInst, sourcePort)
			} else {
				val sourceInst = flow.flowSourceInst
				val targetInst = flow.flowTargetInst
				val sourcePort = getFlowSourcePort(flow)
				val targetPort = getFlowTargetPort(flow)
				channelBuilder.add(sourceInst, sourcePort, targetInst, targetPort)
			}

		}
		
		// adding failure ports and binding
		// failurePort -> func.failurePort
		for (subfunc : function.subfunctions){
			for (archInterface : subfunc.type.providedInterfaces){
				val gammaInterface=trace.get(archInterface) as Interface;
				val inst=trace.get(subfunc) as AsynchronousComponentInstance
				val port=createPort(gammaInterface,inst.name,true)
				component.ports+=port
				val instPort=findInputPort(inst,gammaInterface)
				component.portBindings+=createPortBinding(port,inst,instPort)
			}
		}

		component.channels += channelBuilder.build
		/* 
		 * for (builder : subfuncBuilders){
		 * 	builder.build
		 * }
		 */
		trace.add(function, component)
		component.packageElement("component_functions")
		return component
	}

	def getFlowSourcePort(InformationFlow flow) {

		var type = flow.flowType

		if (flow.source instanceof ArchitecturePort) {
			val port = flow.source as ArchitecturePort
			val name = port.gammaName
			if (port.eContainer instanceof ArchitectureSubfunction) {
				val subfunc = port.eContainer as ArchitectureSubfunction
				val inst = trace.get(subfunc) as AsynchronousComponentInstance
				val sPort = findOutputPort(inst, type, name)
				return sPort
			} else {
				throw new ArchitectureException("Flow source cannot recognized",
					flow.source.eContainer as ArchitectureElement)
			}
		} else if (flow.source instanceof ArchitectureSubfunction) {
			val name = flow.gammaName
			val inst = trace.get(flow.source) as AsynchronousComponentInstance
			var sPort = findOutputPort(inst, type, name)
			return sPort
		} else {
			throw new ArchitectureException("Flow source cannot recognized", flow.source)
		}
	}

	def getFlowTargetPort(InformationFlow flow) {

		var type = flow.flowType

		if (flow.target instanceof ArchitecturePort) {
			val port = flow.target as ArchitecturePort
			if (port.eContainer instanceof ArchitectureSubfunction) {
				val subfunc = port.eContainer as ArchitectureSubfunction
				val inst = trace.get(subfunc) as AsynchronousComponentInstance
				val sPort = findInputPort(inst, type)
				return sPort
			} else {
				throw new ArchitectureException("Flow target cannot recognized",
					flow.target.eContainer as ArchitectureElement)
			}
		} else if (flow.target instanceof ArchitectureSubfunction) {
			val inst = trace.get(flow.target) as AsynchronousComponentInstance
			var sPort = findInputPort(inst, type)
			return sPort
		} else {
			throw new ArchitectureException("Flow target cannot recognized", flow.target)
		}
	}

	def findInputPort(AsynchronousComponentInstance instance, Interface _interface) {
		val ports = instancePorts.get(instance)
		val matches = ports.filter [ port |
			port.interfaceRealization.interface == _interface &&
				port.interfaceRealization.realizationMode == RealizationMode.REQUIRED
		]
		if (matches.empty) {
			throw new ArchitectureException("Subfunction port cannot be found in function", trace.get(instance))
		}
		val port = matches.get(0)
		ports.remove(port)
		return port
	}

	def findOutputPort(AsynchronousComponentInstance instance, Interface _interface, String name) {
		val ports = instance.type.ports
		var Iterable<Port> matches
		if (name.isBlank) {
			matches = ports.filter [ port |
				port.interfaceRealization.interface == _interface &&
					port.interfaceRealization.realizationMode == RealizationMode.PROVIDED
			]
		} else {
			matches = ports.filter [ port |
				port.interfaceRealization.interface == _interface &&
					port.interfaceRealization.realizationMode == RealizationMode.PROVIDED && name.contains(port.name)
			]
			if (matches.empty) {
				matches = ports.filter [ port |
					port.interfaceRealization.interface == _interface &&
						port.interfaceRealization.realizationMode == RealizationMode.PROVIDED
				]
			}
		}
		if (matches.empty) {
			throw new ArchitectureException("Subfunction port cannot be found in function", trace.get(instance))
		}
		val port = matches.get(0)
		return port
	}

	def findPort(AsynchronousComponent component, Interface _interface, String name, boolean conj) {
		val relMode = (conj) ? RealizationMode.REQUIRED : RealizationMode.PROVIDED
		val relPorts = component.ports.filter [ port |
			port.interfaceRealization.interface == _interface && port.interfaceRealization.realizationMode == relMode
		].toList

		val exactMatches = relPorts.filter[port|port.name == name].toList
		if (!exactMatches.isEmpty) {
			return exactMatches.get(0)
		}
		val anonymMatches = relPorts.filter[port|port.name == ""].toList
		if (anonymMatches.length == 1) {
			return anonymMatches.get(0)
		}

	}

	def findPort(AsynchronousComponent component, ArchitecturePort archPort) {
		return findPort(component, trace.get(archPort) as Interface, archPort.gammaName, archPort.conjugated)
	}

}
