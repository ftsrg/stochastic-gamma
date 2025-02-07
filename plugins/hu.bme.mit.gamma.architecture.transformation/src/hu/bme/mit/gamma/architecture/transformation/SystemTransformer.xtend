/********************************************************************************
 * Copyright (c) 2018-2024 Contributors to the Gamma project
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * SPDX-License-Identifier: EPL-1.0
 ********************************************************************************/
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
import hu.bme.mit.gamma.architecture.model.ElectronicComponent
import hu.bme.mit.gamma.statechart.interface_.InterfaceRealization
import hu.bme.mit.gamma.util.GammaEcoreUtil
import hu.bme.mit.gamma.statechart.interface_.Port
import java.util.Set
import hu.bme.mit.gamma.architecture.model.ArchitectureInterface
import hu.bme.mit.gamma.statechart.composite.AsynchronousCompositeComponent
import hu.bme.mit.gamma.architecture.model.PhysicalInterface

class SystemTransformer extends AbstractArchitectureTransformer {

	static def transformSystem(System system, ArchitectureTrace trace, AllocationTrace allocationTrace) {
		val transformer = new SystemTransformer(system, trace, allocationTrace)
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
	
	val notconnectedPhyPorts=<ArchitectureSubcompnent,Set<ArchitecturePort>>newHashMap

	val extension ElementTransformer elementTransformer
	val extension RelationTransfomer relationTransformer

	protected extension val GammaEcoreUtil gammaEcoreUtil = GammaEcoreUtil.INSTANCE

	var portCNTR = 0

	new(System system, ArchitectureTrace trace, AllocationTrace allocationTrace) {
		super(trace)
		this.system = system
		this.trace = trace
		this.allocationTrace = allocationTrace
		this.systemComponent = stochModelFactory.createEnvironmentAsynchronousCompositeComponent
		this.elementTransformer = new ElementTransformer(trace)
		this.relationTransformer = new RelationTransfomer(trace)
		
		trace.add(system, systemComponent)
		
		for (subcomp:system.subcompnents){
			notconnectedPhyPorts.put(subcomp,subcomp.type.ports.toSet)
		}
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

	/* 
	 * 	def createPort(InformationFlow flow, boolean conj) {
	 * 		val port = ifModelFactory.createPort
	 * 		var name = flow.gammaName
	 * 		var ifrel = ifModelFactory.createInterfaceRealization
	 * 		val _interface = trace.get(flow.type) as Interface
	 * 		if (name == "") {
	 * 			name = _interface.name
	 * 		}
	 * 		if (conj) {
	 * 			ifrel.realizationMode = RealizationMode.REQUIRED
	 * 			name = name + "In"
	 * 		} else {
	 * 			ifrel.realizationMode = RealizationMode.PROVIDED
	 * 			name = name + "Out"
	 * 		}
	 * 		ifrel.interface = _interface
	 * 		port.name = name
	 * 		port.interfaceRealization = ifrel
	 * 		return port
	 * 	}
	 */
	def transformConnector(Connector connector) {
		if (connector.isFunctionalConnector) {
		} else if (connector.isSubsystemConnector) {
			val sourcePhy = getPhyEndpoint(connector.source)
			val targetPhy = getPhyEndpoint(connector.target)
			
			//connect physical interface channels
			val connType=connector.connType
			if (connType!==null){
				if (!connType.providedInterfaces.filter[i|i instanceof PhysicalInterface].empty){
					// connecting physical interfaces
					val sourceInst = trace.get(connector.source.getPhyEndSubcomponent() as ArchitectureSubcompnent) as AsynchronousComponentInstance
					val targetInst = trace.get(connector.target.getPhyEndSubcomponent() as ArchitectureSubcompnent) as AsynchronousComponentInstance
					val sourceComp = sourceInst.type as AsynchronousCompositeComponent
					val targetComp = targetInst.type as AsynchronousCompositeComponent
					val connIF=trace.get(connType) as Interface
					val sourceProvPort=sourceComp.findPort(connIF,connector.source.name,false)
					val sourceReqPort=sourceComp.findPort(connIF,connector.source.name,true)
					val targetProvPort=targetComp.findPort(connIF,connector.target.name,false)
					val targetReqPort=targetComp.findPort(connIF,connector.target.name,true)
					channelBuilder.add(sourceInst,sourceProvPort,targetInst,targetReqPort)
					channelBuilder.add(targetInst,targetProvPort,sourceInst,sourceReqPort)
				}
			}			
			val builder = new InterfaceComponentBuilder(connector, trace, allocationTrace)
			ifcompMap.put(connector, builder)
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
		} else if (connector.isElectronicConnector) {
			val sourceComp = connector.source.endpointComp
			val sourceInst = trace.get(sourceComp) as AsynchronousComponentInstance
			val targetComp = connector.target.endpointComp
			val targetInst = trace.get(targetComp) as AsynchronousComponentInstance
			val sourcePort = connector.sourcePort
			val targetPort = connector.targetPort
			for (match : findConnections(sourcePort, targetPort)) {
				val port1 = match.get(0)
				val port2 = match.get(1)
				if (port1.interfaceRealization.realizationMode == RealizationMode.PROVIDED) {
					sysChannelBuilder.add(sourceInst, port1, targetInst, port2)
				} else {
					sysChannelBuilder.add(targetInst, port2, sourceInst, port1)
				}
			}
		} else if (connector.isElectronicInputConnector) {
			val targetComp = connector.target.endpointComp
			val targetInst = trace.get(targetComp) as AsynchronousComponentInstance
			val targetPort = connector.targetPort
			for (port2 : trace.getPhyPorts(targetPort)) {
				val port1 = port2.clone
				systemComponent.ports += port1
				trace.phyPortMap.put(connector.source as ArchitecturePort, port1)
				port1.name = connector.source.gammaName + "_" +
					port2.name.replaceFirst("^" + targetPort.name.gammaName+"_", "")
				systemComponent.portBindings += createPortBinding(port1, targetInst, port2)
			}
		} else if (connector.isElectronicOutputConnector) {
			val sourceComp = connector.source.endpointComp
			val sourceInst = trace.get(sourceComp) as AsynchronousComponentInstance
			val sourcePort = connector.sourcePort
			for (port1 : trace.getPhyPorts(sourcePort)) {
				val port2 = port1.clone
				systemComponent.ports += port2
				trace.phyPortMap.put(connector.target as ArchitecturePort, port2)
				port2.name = connector.target.gammaName + "_" +
					port1.name.replaceFirst("^" + sourcePort.name.gammaName+"_", "")
				systemComponent.portBindings += createPortBinding(port2, sourceInst, port1)
			}
		} else {
			throw new ArchitectureException('''Connector is incorrectly connected in system «connector.source.name» -> «connector.target.name» ''',
				connector.source)
		}
	}

	def transformFlow(InformationFlow flow) {
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
			var InterfaceComponentBuilder ifComponentBuilder = null
			if (allocationTrace.isAllocatedTo(flow)){
				ifComponentBuilder=trace.getInterfaceComponentBuilder(allocationTrace.getConnector(flow))
			}else{
				ifComponentBuilder = ifCompTable.get(getPhyComponent(sourceSubsystem), getPhyComponent(targetSubsystem))
			}
			if (ifComponentBuilder===null){
				throw new ArchitectureException ("Communication component is not found, maybe a connector is missing",flow.source)
			}
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
			sysChannelBuilder.add(sourceComponentBuilder.instance, outSubsysCompPort, targetComponentBuilder.instance,
				inSubsysCompPort)
			targetComponentBuilder.addBinding(inSubsysCompPort, targetIFCompInst, inIFCompPort)
			targetComponentBuilder.addChannel(targetIFCompInst, outIFCompPort, targetInst, targetPort)

		} else if (flow.isSystemInputFlow) {

			val sourcePort = flow.source as ArchitecturePort
			
			val type=flow.flowType

			val targetInst = flow.flowTargetInst
			val targetSubsystem = flow.targetSubsystem
			val targetPort = getFlowTargetPort(flow)
			val targetComponentBuilder = subsysMap.get(targetSubsystem)
			val ifComponentBuilder = ifCompTable.get(flow.source, getPhyComponent(targetSubsystem))
			if (ifComponentBuilder===null){
				throw new ArchitectureException ("Communication component is not found, maybe a connector is missing",flow.source)
			}
			ifComponentBuilder.addFlow(flow)
			val targetIFCompInst = ifComponentBuilder.getInstance(getPhyComponent(targetSubsystem))
			val inIFCompPort = ifComponentBuilder.getInPort(flow)
			val outIFCompPort = ifComponentBuilder.getOutPort(flow)
			val inSubsysCompPort = targetComponentBuilder.getInPort(flow)
			val subsysInstance = targetComponentBuilder.instance

			val portName = flow.source.gammaName + "_" + flow.gammaName
			val portNameFull = flow.source.gammaName + "_" + flow.gammaName+type.name+"In"
			var Port sysPort = null
			val sysPortIt = trace.phyPortMap.get(sourcePort).filter[p|p.name == portNameFull].iterator
			if (sysPortIt.hasNext){
				sysPort=sysPortIt.next
			}else{
				sysPort = createPort(type, portName, true)
				trace.phyPortMap.put(sourcePort, sysPort)
				systemComponent.ports += sysPort
			}
			


			// add connections
			// sys.port -> subsys.port -> ifComp -o)- func
			targetComponentBuilder.addBinding(inSubsysCompPort, targetIFCompInst, inIFCompPort)
			targetComponentBuilder.addChannel(targetIFCompInst, outIFCompPort, targetInst, targetPort)
			systemComponent.portBindings += createPortBinding(sysPort, subsysInstance, inSubsysCompPort)

		} else if (flow.isSystemOutputFlow) {

			val targetPort = flow.target as ArchitecturePort
			
			val type=flow.flowType

			val sourceInst = flow.flowSourceInst
			val sourceSubsystem = flow.sourceSubsystem
			val sourcePort = getFlowSourcePort(flow)
			val sourceComponentBuilder = subsysMap.get(sourceSubsystem)
			val ifComponentBuilder = ifCompTable.get(getPhyComponent(sourceSubsystem), targetPort)
			if (ifComponentBuilder===null){
				throw new ArchitectureException ("Communication component is not found, maybe a connector is missing",flow.target)
			}
			ifComponentBuilder.addFlow(flow)
			val sourceIFCompInst = ifComponentBuilder.getInstance(getPhyComponent(sourceSubsystem))
			val inIFCompPort = ifComponentBuilder.getInPort(flow)
			val outIFCompPort = ifComponentBuilder.getOutPort(flow)
			val outSubsysCompPort = sourceComponentBuilder.getOutPort(flow)
			val subsysInstance = sourceComponentBuilder.instance

			val sysPort = createPort(type,
				targetPort.name.gammaName + "_" + flow.gammaName, false)
			systemComponent.ports += sysPort

			trace.phyPortMap.put(targetPort, sysPort)

			// add connections
			// func -o)- ifComp -> port -> sys.port
			sourceComponentBuilder.addChannel(sourceInst, sourcePort, sourceIFCompInst, inIFCompPort)
			sourceComponentBuilder.addBinding(outSubsysCompPort, sourceIFCompInst, outIFCompPort)
			systemComponent.portBindings += createPortBinding(sysPort, subsysInstance, outSubsysCompPort)

		} else if (flow.isFromHWFlow) {

			val type = flow.flowType
			val name = flow.gammaName

			val targetInst = flow.flowTargetInst
			val targetSubsystem = flow.targetSubsystem
			val targetPort = getFlowTargetPort(flow)
			val targetComponentBuilder = subsysMap.get(targetSubsystem)

			val sourcePort = findConnection(flow.source as ArchitecturePort, type, name, RealizationMode.PROVIDED)
			val sourceInst = trace.get(
				flow.source.eContainer as ArchitectureSubcompnent) as AsynchronousComponentInstance

			val inSubsysCompPort = createPort(type, sourceInst.name + SEP + flow.source.gammaName +"_"+ flow.gammaName, true)
			targetComponentBuilder.addPort(inSubsysCompPort)

			// add connections
			// hwComp.port -o)- subsys.port -> func 
			sysChannelBuilder.add(sourceInst, sourcePort, targetComponentBuilder.instance, inSubsysCompPort)
			targetComponentBuilder.addBinding(inSubsysCompPort, targetInst, targetPort)

		} else if (flow.isToHWFlow) {

			val type = flow.flowType
			val name = flow.gammaName

			val sourceInst = flow.flowSourceInst
			val sourceSubsystem = flow.sourceSubsystem
			val sourcePort = getFlowSourcePort(flow)
			val sourceComponentBuilder = subsysMap.get(sourceSubsystem)

			val targetPort = findConnection(flow.target as ArchitecturePort, type, name, RealizationMode.REQUIRED)
			val targetInst = trace.get(
				flow.target.eContainer as ArchitectureSubcompnent) as AsynchronousComponentInstance

			val outSubsysCompPort = createPort(flow.flowType, sourceInst.name +SEP + flow.target.gammaName +"_"+ flow.gammaName, false)
			sourceComponentBuilder.addPort(outSubsysCompPort)

			// add connections
			// func -> subsys.port -o)- hwComp.port
			sourceComponentBuilder.addBinding(outSubsysCompPort, sourceInst, sourcePort)
			sysChannelBuilder.add(sourceComponentBuilder.instance, outSubsysCompPort, targetInst, targetPort)

		} else {
			throw new ArchitectureException("The information flow is incorrectly connected", flow.source)
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
			if (subcomponent.subfunctions.isEmpty) {
				systemComponent.components += subcomponent.transformSubcomponent
			} else {
				if (!swAllocationMap.containsKey(subcomponent)) {
					val builder = new SingletonComponentBuilder(subcomponent, trace)
					systemComponent.components += builder.instance as AsynchronousComponentInstance
					subsysMap.put(subcomponent, builder)
				}
			}
		}
		for (swcomponent : swAllocationMap.keySet) {
			subsysMap.put(swcomponent, subsysMap.get(swAllocationMap.get(swcomponent)))
		}

		// transforming the component functions into Gamma Components
		for (subsystem : system.subcompnents.filter[c|c.isSubsystem]) {
			val subsysBuilder = subsysMap.get(getPhyComponent(subsystem))
			for (subfunction : subsystem.subfunctions) {
				// val function = subfunction.type as ArchitectureFunction
				// val functionTransformer = new FunctionTransformer(function, trace)
				// functionTransformer.transform()
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
			connector.transformConnector
		}

		// transforming the information flows into ports and channels
		val flows = system.informationFlows
		for (flow : flows) {
			flow.transformFlow

		}

		for (ifcompBuilder : ifCompTable.values.toSet) {
			val ifcomp = ifcompBuilder.component
			ifcomp.packageElement("communication")
		}

		for (builder : subsysMap.values.toSet) {
			builder.build
			val subsysComponent = builder.component
			subsysComponent.packageElement("subsystems")
		}

		systemComponent.channels += sysChannelBuilder.build
		systemComponent.packageElement("systems")

	}


	def getPhyEndpoint(ArchitectureElement element) {
		if (element.isComponentPort) {
			return element
		} else if (element.isSubcomponentPort) {
			return element.eContainer as ArchitectureSubcompnent
		} else if (element.isSubfunctionPort) {
			return getPhyComponent(
				(element.eContainer as ArchitectureSubfunction).eContainer as ArchitectureSubcompnent)
		} else if (element instanceof ArchitectureSubfunction) {
			return getPhyComponent(element.eContainer as ArchitectureSubcompnent)
		} else if (element instanceof ArchitectureSubcompnent) {
			return getPhyComponent(element as ArchitectureSubcompnent)
		} else {
			throw new ArchitectureException("Endpoint is not found", element)
		}
	}
	
	def getPhyEndSubcomponent(ArchitectureElement element) {
		if (element.isComponentPort) {
			throw new ArchitectureException("Endpoint cannot be component port", element)
		} else if (element.isSubcomponentPort) {
			return element.eContainer as ArchitectureSubcompnent
		} else if (element.isSubfunctionPort) {
			return getPhyComponent(
				(element.eContainer as ArchitectureSubfunction).eContainer as ArchitectureSubcompnent)
		} else if (element instanceof ArchitectureSubfunction) {
			return getPhyComponent(element.eContainer as ArchitectureSubcompnent)
		} else if (element instanceof ArchitectureSubcompnent) {
			return getPhyComponent(element as ArchitectureSubcompnent)
		} else {
			throw new ArchitectureException("Endpoint is not found", element)
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
	
	def ArchitectureSubcompnent getEndpointComp(ArchitectureElement element){
		if (element instanceof ArchitectureSubcompnent) {
			return element
		}else if (element instanceof ArchitecturePort && element.eContainer instanceof ArchitectureSubcompnent){
			return element.eContainer as ArchitectureSubcompnent
		} else {
			throw new ArchitectureException("Electronic commenction endpoint component cannot be recognized",element)
		}		
	}
	
	def ArchitecturePort getTargetPort(Connector connector){
		if (connector.target instanceof ArchitecturePort) {
			val instPort = connector.target as ArchitecturePort
			val typePort=findPort(instPort .eContainer as ArchitectureSubcompnent,instPort.type,instPort.name)
			notconnectedPhyPorts.get(instPort.eContainer).remove(typePort)
			return typePort
		} else if (connector.target instanceof ArchitectureSubcompnent){
			if (connector.source instanceof ArchitecturePort){
				val portType=(connector.source as ArchitecturePort).type
				val typePort=findPort(connector.target as ArchitectureSubcompnent,portType)
				notconnectedPhyPorts.get(connector.target).remove(typePort)
				return typePort
			}else if (connector.source instanceof ArchitectureSubcompnent) {
				val typePort=findPort(connector.target as ArchitectureSubcompnent)
				notconnectedPhyPorts.get(connector.target).remove(typePort)
				return typePort
			}else{
				throw new ArchitectureException('''At least one connector endpoint must be a subcomponent port or a subcomponent ''',connector.source)
			}
		} else {
			throw new ArchitectureException("Electronic commenction endpoint port cannot be recognized",connector.target)
		}		
		
	}
	
	def ArchitecturePort getSourcePort(Connector connector){
		if (connector.source instanceof ArchitecturePort) {
			val instPort = connector.source as ArchitecturePort
			val typePort=findPort(instPort.eContainer as ArchitectureSubcompnent,instPort.type,instPort.name)
			notconnectedPhyPorts.get(instPort.eContainer).remove(typePort)
			return typePort
		} else if (connector.source instanceof ArchitectureSubcompnent){
			if (connector.target instanceof ArchitecturePort){
				val portType=(connector.target as ArchitecturePort).type
				val typePort=findPort(connector.source as ArchitectureSubcompnent,portType)
				notconnectedPhyPorts.get(connector.source).remove(typePort)
				return typePort
			}else if (connector.target instanceof ArchitectureSubcompnent) {
				val typePort=findPort(connector.source as ArchitectureSubcompnent)
				notconnectedPhyPorts.get(connector.source).remove(typePort)
				return typePort
			}else{
				throw new ArchitectureException('''At least one connector endpoint must be a subcomponent port or a subcomponent ''',connector.target)
			}
		} else {
			throw new ArchitectureException("Electronic commenction endpoint port cannot be recognized",connector.source)
		}		
		
	}
	

	
	def ArchitecturePort findPort(ArchitectureSubcompnent subcompnent){
		val portIt=notconnectedPhyPorts.get(subcompnent).iterator
		if (!portIt.hasNext){
			throw new ArchitectureException('''Subcomponent port cannot be found ''',subcompnent)
		}
		val port =portIt.next
		return port
	}

	def ArchitecturePort findPort(ArchitectureSubcompnent subcompnent,ArchitectureInterface _interface){
		val portIt=notconnectedPhyPorts.get(subcompnent).filter[p|p.type===_interface].iterator
		if (!portIt.hasNext){
			throw new ArchitectureException('''Subcomponent port cannot be found with interface type: '«_interface.name»' ''',subcompnent)
		}
		val port =portIt.next
		return port
	}

	def ArchitecturePort findPort(ArchitectureSubcompnent subcompnent,ArchitectureInterface _interface,String name){
		val portIt=notconnectedPhyPorts.get(subcompnent).filter[p|p.type===_interface].filter[p|p.name==name].iterator
		if (!portIt.hasNext){
			throw new ArchitectureException('''Subcomponent port cannot be found '«name»:«_interface.name»' ''',subcompnent)
		}
		val port =portIt.next
		return port
	}

}
