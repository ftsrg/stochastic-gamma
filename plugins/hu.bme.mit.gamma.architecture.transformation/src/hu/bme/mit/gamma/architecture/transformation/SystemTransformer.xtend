package hu.bme.mit.gamma.architecture.transformation

import hu.bme.mit.gamma.architecture.transformation.traceability.AllocationTrace
import hu.bme.mit.gamma.architecture.transformation.traceability.ArchitectureTrace
import hu.bme.mit.gamma.architecture.model.ArchitectureSubfunction

import static extension hu.bme.mit.gamma.architecture.transformation.util.TransformationUtils.*
import static extension hu.bme.mit.gamma.architecture.transformation.RelationTransfomer.*
import hu.bme.mit.gamma.architecture.model.ArchitectureSubcompnent
import hu.bme.mit.gamma.architecture.model.InformationFlow
import hu.bme.mit.gamma.architecture.model.InterfaceConnector
import hu.bme.mit.gamma.environment.model.EnvironmentAsynchronousCompositeComponent
import hu.bme.mit.gamma.environment.model.EnvironmentModelFactory
import hu.bme.mit.gamma.environment.model.impl.EnvironmentModelFactoryImpl
import hu.bme.mit.gamma.architecture.model.ArchitecturePort
import hu.bme.mit.gamma.statechart.statechart.StatechartModelFactory
import hu.bme.mit.gamma.statechart.interface_.InterfaceModelFactory
import hu.bme.mit.gamma.architecture.model.ArchitectureElement
import hu.bme.mit.gamma.architecture.model.System
import hu.bme.mit.gamma.statechart.interface_.Interface
import hu.bme.mit.gamma.statechart.interface_.RealizationMode
import hu.bme.mit.gamma.architecture.model.ArchitectureFunction
import hu.bme.mit.gamma.architecture.transformation.errors.ArchitectureException
import hu.bme.mit.gamma.statechart.composite.AsynchronousComponent
import hu.bme.mit.gamma.architecture.transformation.builder.Channelbuilder
import hu.bme.mit.gamma.architecture.transformation.builder.SingletonComponentBuilder
import hu.bme.mit.gamma.architecture.transformation.builder.InterfaceComponentBuilder
import com.google.common.collect.Table
import com.google.common.collect.HashBasedTable
import hu.bme.mit.gamma.statechart.composite.AsynchronousComponentInstance
import hu.bme.mit.gamma.architecture.model.Connector
import hu.bme.mit.gamma.architecture.model.ArchitectureComponent

class SystemTransformer {

	static def transformSystem(System system, ArchitectureTrace trace) {
		val transformer = new SystemTransformer(system, trace)
		val systemComponent = transformer.execute
		return systemComponent
	}

	val System system

	val AllocationTrace allocationTrace
	val ArchitectureTrace trace
	val stochModelFactory = EnvironmentModelFactory.eINSTANCE
	val sctModelFactory = StatechartModelFactory.eINSTANCE
	val ifModelFactory = InterfaceModelFactory.eINSTANCE
	val EnvironmentAsynchronousCompositeComponent systemComponent
	val sysChannelBuilder = new Channelbuilder
	val subsysMap = <ArchitectureSubcompnent, SingletonComponentBuilder>newHashMap
	val ifcompMap = <Connector, InterfaceComponentBuilder>newHashMap
	val Table<ArchitectureElement, ArchitectureElement, InterfaceComponentBuilder> ifCompTable = HashBasedTable.create
	val swAllocationMap = <ArchitectureSubcompnent, ArchitectureSubcompnent>newHashMap
	val functionAllocationMap = <ArchitectureSubfunction, ArchitectureSubcompnent>newHashMap

	val extension ElementTransformer elementTransformer
	val extension RelationTransfomer relationTransformer
	
	var portCNTR=0

	new(System system, ArchitectureTrace trace) {
		this.system = system
		this.trace = trace
		this.allocationTrace = new AllocationTrace(system)
		this.systemComponent = stochModelFactory.createEnvironmentAsynchronousCompositeComponent
		this.elementTransformer = new ElementTransformer(trace)
		this.relationTransformer = new RelationTransfomer(trace)
	}

	def getPhyComponent(ArchitectureSubfunction subfunction) {
		// return trace.get(allocationTrace.get(subfunction))
		return getPhyComponent(subfunction.eContainer as ArchitectureSubcompnent)
	}

	def getPhyComponent(ArchitectureSubcompnent subcompnent) {
		if (swAllocationMap.containsKey(subcompnent)) {
			return swAllocationMap.get(subcompnent)
		} else {
			return subcompnent
		}
	/* 
	 * if (subcompnent.isSoftware) {
	 * 	return trace.get(allocationTrace.get(subcompnent))
	 * } else {
	 * 	return trace.get(subcompnent)
	 * }
	 */
	}

	def transform(InformationFlow flow) {
		if (flow.target === flow.source) {
		} else {
		}
	}

	def createPort(InformationFlow flow, boolean conj) {
		val port = ifModelFactory.createPort
		var name = flow.gammaName
		var ifrel = ifModelFactory.createInterfaceRealization
		val _interface = trace.get(flow.type) as Interface
		if (name == "") {
			name = _interface.name
		}
		if (conj) {
			ifrel.realizationMode = RealizationMode.REQUIRED
			name = name + "In"
		} else {
			ifrel.realizationMode = RealizationMode.PROVIDED
			name = name + "Out"
		}
		ifrel.interface = _interface
		port.name = name
		port.interfaceRealization = ifrel
		return port
	}

	def transform(InterfaceConnector connector) {
		var ifComponent = stochModelFactory.createEnvironmentAsynchronousCompositeComponent
		ifComponent.name = connector.gammaName
		if (connector.source instanceof ArchitecturePort) {
		}
		if (connector.source instanceof ArchitectureSubcompnent) {
		}
		if (connector.target instanceof ArchitecturePort) {
		}
		if (connector.target instanceof ArchitectureSubcompnent) {
		}
	}

	def execute() {

		systemComponent.name = system.gammaName

		// processing the allocations
		for (allocation : system.allocations) {
			swAllocationMap.put(allocation.source as ArchitectureSubcompnent,
				allocation.target as ArchitectureSubcompnent)
		}

		// create builder for the subsystems
		for (subcomponent : system.subcompnents) {
			if (!swAllocationMap.containsKey(subcomponent)) {
				val builder = new SingletonComponentBuilder(subcomponent, trace)
				subsysMap.put(subcomponent, builder)
			}
		}
		for (swcomponent : swAllocationMap.keySet) {
			subsysMap.put(swcomponent, subsysMap.get(swAllocationMap.get(swcomponent)))
		}

		// transforming the component functions into Gamma Components
		for (subcomponent : system.subcompnents) {
			val subsysBuilder = subsysMap.get(getPhyComponent(subcomponent))
			for (subfunction : subcomponent.subfunctions) {
				val function = subfunction.type as ArchitectureFunction
				val functionTransformer = new FunctionTransformer(function, trace)
				functionTransformer.execute
				val func = subfunction.transformSubfunction
				subsysBuilder.addInstance(func)
			}
		}

		// transforming the system ports
		for (archPort : system.ports) {
			// val port = archPort.transformPort
			// systemComponent.ports+=port
		}

		// transforming the interface connectors into Gamma Components
		for (connector : system.connectors) {
			if (! connector.isFunctionalConnector) {
				val builder = new InterfaceComponentBuilder(connector, trace)
				ifcompMap.put(connector, builder)
				val sourcePhy=getPhyEndpoint(connector.source)
				val targetPhy=getPhyEndpoint(connector.target)
				ifCompTable.put(sourcePhy, targetPhy, builder)
				ifCompTable.put(targetPhy, sourcePhy, builder)
				if (! connector.source.isComponentPort) {
					val sourceSubsystem = connector.sourceSubsystem
					val sourceSubsystemBuilder = subsysMap.get(sourceSubsystem)
					val sourceIFCompInst = builder.createInstance(sourceSubsystem)
					sourceSubsystemBuilder.addCommInstance(sourceIFCompInst)
				}
				if (! connector.target.isComponentPort) {
					val targetSubsystem = connector.targetSubsystem
					val targetSubsystemBuilder = subsysMap.get(targetSubsystem)
					val targetIFCompInst = builder.createInstance(targetSubsystem)
					targetSubsystemBuilder.addCommInstance(targetIFCompInst)
				}
			}
		}

		// transforming the information flows into ports and channels
		val flows=system.informationFlows
		for (flow : flows) {

			if (flow.isSubsystemFlow) {

				val sourceInst = flow.flowSourceInst
				val targetInst = flow.flowTargetInst
				val sourcePort = getFlowSourcePort(flow)
				val targetPort = getFlowTargetPort(flow)
				val builder = subsysMap.get(flow.sourceSubsystem)
				builder.addChannel(sourceInst, sourcePort, targetInst, targetPort)

			} else if (flow.isSystemFlow) {

				val sourceInst = flow.flowSourceInst
				val targetInst = flow.flowTargetInst
				val sourceSubsystem = flow.sourceSubsystem
				val targetSubsystem = flow.targetSubsystem
				val sourcePort = getFlowSourcePort(flow)
				val targetPort = getFlowTargetPort(flow)
				val sourceComponentBuilder = subsysMap.get(sourceSubsystem)
				val targetComponentBuilder = subsysMap.get(targetSubsystem)
				val ifComponentBuilder = ifCompTable.get(getPhyComponent(sourceSubsystem),
					getPhyComponent(targetSubsystem))
				ifComponentBuilder.addFlow(flow)
				val sourceIFCompInst = ifComponentBuilder.getInstance(getPhyComponent(sourceSubsystem))
				val targetIFCompInst = ifComponentBuilder.getInstance(getPhyComponent(targetSubsystem))
				val inIFCompPort = ifComponentBuilder.getInPort(flow)
				val outIFCompPort = ifComponentBuilder.getOutPort(flow)
				val inSubsysCompPort = targetComponentBuilder.getInPort(flow)
				val outSubsysCompPort = sourceComponentBuilder.getOutPort(flow)

				// add connections
				// func -o)- ifComp -> port -o)- port -> ifComp -o)- func
				sourceComponentBuilder.addChannel(sourceInst, sourcePort, sourceIFCompInst, inIFCompPort)
				sourceComponentBuilder.addBinding(outSubsysCompPort, sourceIFCompInst, outIFCompPort)
				sysChannelBuilder.add(sourceComponentBuilder.instance, outSubsysCompPort,
					targetComponentBuilder.instance, inSubsysCompPort)
				targetComponentBuilder.addBinding(inSubsysCompPort, targetIFCompInst, inIFCompPort)
				targetComponentBuilder.addChannel(targetIFCompInst, outIFCompPort, targetInst, targetPort)

			} else if (flow.isSystemInputFlow) {

				val sourcePort = flow.source as ArchitecturePort

				val targetInst = flow.flowTargetInst
				val targetSubsystem = flow.targetSubsystem
				val targetPort = getFlowTargetPort(flow)
				val targetComponentBuilder = subsysMap.get(targetSubsystem)
				val ifComponentBuilder = ifCompTable.get(flow.source, getPhyComponent(targetSubsystem))
				ifComponentBuilder.addFlow(flow)
				val targetIFCompInst = ifComponentBuilder.getInstance(getPhyComponent(targetSubsystem))
				val inIFCompPort = ifComponentBuilder.getInPort(flow)
				val outIFCompPort = ifComponentBuilder.getOutPort(flow)
				val inSubsysCompPort = targetComponentBuilder.getInPort(flow)
				val subsysInstance=targetComponentBuilder.instance

				val sysPort = createPort(trace.get(flow.type) as Interface, flow.gammaName + "_" + sourcePort.gammaName+"_"+portCNTR++,
					true)

				// add connections
				// sys.port -> subsys.port -> ifComp -o)- func
				targetComponentBuilder.addBinding(inSubsysCompPort, targetIFCompInst, inIFCompPort)
				targetComponentBuilder.addChannel(targetIFCompInst, outIFCompPort, targetInst, targetPort)
				systemComponent.portBindings+=createPortBinding(sysPort,subsysInstance,inSubsysCompPort)
				systemComponent.ports += sysPort

			} else if (flow.isSystemOutputFlow) {

				val targetPort = flow.target as ArchitecturePort

				val sourceInst = flow.flowSourceInst
				val sourceSubsystem = flow.sourceSubsystem
				val sourcePort = getFlowSourcePort(flow)
				val sourceComponentBuilder = subsysMap.get(sourceSubsystem)
				val ifComponentBuilder = ifCompTable.get(getPhyComponent(sourceSubsystem), targetPort)
				ifComponentBuilder.addFlow(flow)
				val sourceIFCompInst = ifComponentBuilder.getInstance(getPhyComponent(sourceSubsystem))
				val inIFCompPort = ifComponentBuilder.getInPort(flow)
				val outIFCompPort = ifComponentBuilder.getOutPort(flow)
				val outSubsysCompPort = sourceComponentBuilder.getOutPort(flow)
				val subsysInstance=sourceComponentBuilder.instance

				val sysPort = createPort(trace.get(flow.type) as Interface, flow.gammaName + "_" + targetPort.gammaName+"_"+portCNTR++,
					false)

				// add connections
				// func -o)- ifComp -> port -> sys.port
				sourceComponentBuilder.addChannel(sourceInst, sourcePort, sourceIFCompInst, inIFCompPort)
				sourceComponentBuilder.addBinding(outSubsysCompPort, sourceIFCompInst, outIFCompPort)
				systemComponent.portBindings+=createPortBinding(sysPort,subsysInstance,outSubsysCompPort)
				systemComponent.ports += sysPort

			} else {
				throw new ArchitectureException("The information flow is incorrectly connected", flow.source)
			}

		}
		
		for (ifcompBuilder : ifCompTable.values.toSet){
			val ifcomp=ifcompBuilder.component
			ifcomp.packageElement("communication")
		}
		

		for (builder : subsysMap.values.toSet) {
			builder.build
			systemComponent.components += builder.instance as AsynchronousComponentInstance
			val subsysComponent = builder.component
			subsysComponent.packageElement("subsystems")
		}
		
		systemComponent.channels += sysChannelBuilder.build
		systemComponent.packageElement("systems")

	}

	def getFlowSourcePort(InformationFlow flow) {

		if (flow.source instanceof ArchitecturePort) {
			val port = flow.source as ArchitecturePort
			if (port.eContainer instanceof ArchitectureSubfunction) {
				val subfunc = port.eContainer as ArchitectureSubfunction
				val gammaComp = trace.get(subfunc) as AsynchronousComponent
				val sPort = findPort(gammaComp, port)
				if (sPort === null) {
					throw new ArchitectureException("Subfunction out port cannot be found in function: "+port.name+" : "+port.type.name, port)
				}
				return sPort
			}
		}
		if (flow.source instanceof ArchitectureSubfunction) {
			val type = trace.get(flow.type) as Interface
			val name = flow.outPortName
			val comp = trace.get(flow.source) as AsynchronousComponentInstance
			var sPort = findPort(comp.type, type, name, false)
			if (sPort === null) {
				throw new ArchitectureException("Subfunction out port cannot be found in function: "+name+" : "+type.name, flow.source)
			}
			return sPort
		}
	}

	def getFlowTargetPort(InformationFlow flow) {
		if (flow.target instanceof ArchitecturePort) {
			val port = flow.target as ArchitecturePort
			if (port.eContainer instanceof ArchitectureSubfunction) {
				val subfunc = port.eContainer as ArchitectureSubfunction
				val gammaComp = trace.get(subfunc) as AsynchronousComponent
				val sPort = findPort(gammaComp, port)
				if (sPort === null) {
					throw new ArchitectureException("Subfunction in port cannot be found in function: "+port.name+" : "+port.type.name, port)
				}
				return sPort
			}
		}
		if (flow.target instanceof ArchitectureSubfunction) {
			val type = trace.get(flow.type) as Interface
			val name = flow.inPortName
			val comp = trace.get(flow.target) as AsynchronousComponentInstance
			var sPort = findPort(comp.type, type, name, true)
			if (sPort === null) {
				throw new ArchitectureException("Subfunction in port cannot be found in function: "+name+" : "+type.name, flow.target)
			}
			return sPort
		}
	}

	def getPhyEndpoint(ArchitectureElement element) {
		if (element.isComponentPort) {
			return element
		} else if (element.isSubfunctionPort) {
			return getPhyComponent(
				(element.eContainer as ArchitectureSubfunction).eContainer as ArchitectureSubcompnent)
		} else if (element instanceof ArchitectureSubfunction) {
			return getPhyComponent(element.eContainer as ArchitectureSubcompnent)
		} else  if (element instanceof ArchitectureSubcompnent) {
			return getPhyComponent(element as ArchitectureSubcompnent)
		} else {
			throw new ArchitectureException("Endpoint is not found",element)
		}
	}
	
	def isSubsystemFlow(InformationFlow flow) {
		val source = flow.source
		val target = flow.target
		var ArchitectureSubcompnent sourceComp
		var ArchitectureSubcompnent targetComp
		if (source.isSubfunctionPort) {
			sourceComp = getPhyComponent(source.eContainer.eContainer as ArchitectureSubcompnent)
		} else if (source instanceof ArchitectureSubfunction) {
			sourceComp = getPhyComponent(source.eContainer as ArchitectureSubcompnent)
		} else {
			return false
		}
		if (target.isSubfunctionPort) {
			targetComp = getPhyComponent(target.eContainer.eContainer as ArchitectureSubcompnent)
		} else if (target instanceof ArchitectureSubfunction) {
			targetComp = getPhyComponent(target.eContainer as ArchitectureSubcompnent)
		} else {
			return false
		}
		return (sourceComp === targetComp)
	}
	
	def isSystemFlow(InformationFlow flow) {
		val source = flow.source
		val target = flow.target
		var ArchitectureSubcompnent sourceComp
		var ArchitectureSubcompnent targetComp
		if (source.isSubfunctionPort) {
			sourceComp = getPhyComponent(source.eContainer.eContainer as ArchitectureSubcompnent)
		} else if (source instanceof ArchitectureSubfunction) {
			sourceComp = getPhyComponent(source.eContainer as ArchitectureSubcompnent)
		} else {
			return false
		}
		if (target.isSubfunctionPort) {
			targetComp = getPhyComponent(target.eContainer.eContainer as ArchitectureSubcompnent)
		} else if (target instanceof ArchitectureSubfunction) {
			targetComp = getPhyComponent(target.eContainer as ArchitectureSubcompnent)
		} else {
			return false
		}
		return !(sourceComp === targetComp)
	}
	
}
