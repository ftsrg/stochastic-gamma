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
import hu.bme.mit.gamma.architecture.transformation.traceability.AllocationTrace
import hu.bme.mit.gamma.architecture.model.ArchitectureFunction
import hu.bme.mit.gamma.architecture.model.PhysicalInterface
import hu.bme.mit.gamma.architecture.model.ArchitectureInterface
import static extension hu.bme.mit.gamma.statechart.derivedfeatures.StatechartModelDerivedFeatures.*
import static extension hu.bme.mit.gamma.architecture.transformation.util.TransformationUtils.*
import hu.bme.mit.gamma.util.GammaEcoreUtil
import hu.bme.mit.gamma.architecture.model.ArchitecturePort

class InterfaceComponentBuilder {

	val Connector connector
	val ArchitectureTrace trace
	val AllocationTrace allocationTrace
	val stochModelFactory = EnvironmentModelFactory.eINSTANCE
	val distModelFactory = StochasticFactory.eINSTANCE
	static val sctModelFactory = StatechartModelFactory.eINSTANCE
	static val cmpModelFactory = CompositeModelFactory.eINSTANCE
	static val ifModelFactory = InterfaceModelFactory.eINSTANCE
	static val expModelFactory = ExpressionModelFactory.eINSTANCE
	val AsynchronousCompositeComponent component
	// val EnvironmentAsynchronousCompositeComponentInstance instance
	val Map<InformationFlow, Port> inportMap = newHashMap
	val Map<InformationFlow, Port> outportMap = newHashMap
	// val EnvironmentEventSource failureSource
	// val BroadcastChannel failureChannel
	val Map<ArchitectureSubcompnent, ComponentInstance> instanceMap = newHashMap

	val extension ElementTransformer elementTransformer
	val extension RelationTransfomer relationTransformer

	protected extension val GammaEcoreUtil gammaEcoreUtil = GammaEcoreUtil.INSTANCE

	var nameCNTR = 0
	var portNameCNTR = 0

	val Port failurePort
	var failurePortAdded = false
	val PhysicalInterface connType

	val ArchitecturePort phyPort

	val failurePortMap = <ArchitectureInterface, Port>newHashMap
	public val failurePorts = <Port>newHashSet

	new(Connector connector, ArchitectureTrace trace, AllocationTrace allocationTrace) {
		this.component = cmpModelFactory.createAsynchronousCompositeComponent
		// this.instance = stochModelFactory.createEnvironmentAsynchronousCompositeComponentInstance
		this.connector = connector
		this.trace = trace
		this.allocationTrace = allocationTrace
		this.elementTransformer = new ElementTransformer(trace)
		this.relationTransformer = new RelationTransfomer(trace)

		// this.failureSource=stochModelFactory.createEnvironmentEventSource
		component.name = connector.gammaName + nameCNTR++

		failurePort = createPort(elementTransformer.failureInterface, "failureIn", true)

		connType = connector.connType
		if (connType !== null) {
			for (funcIF : connType.providedInterfaces.filter[archIF|! (archIF instanceof PhysicalInterface)]) {
				val port = createPort(trace.get(funcIF) as Interface, "", true)
				failurePortMap.put(funcIF, port)
				failurePorts.add(port)
				component.ports += port
			}
		}

		if (connector.source instanceof ArchitecturePort) {
			phyPort = connector.source as ArchitecturePort
		} else if (connector.target instanceof ArchitecturePort) {
			phyPort = connector.target as ArchitecturePort
		} else {
			phyPort = null
		}

		trace.add(component, connector, this)
	}

	def getComponent() {
		return component
	}

	def createInstance() {
		val instance = cmpModelFactory.createAsynchronousComponentInstance
		instance.name = "inst" + component.name.toFirstUpper
		instance.type = component
		return instance
	}
	
	def hasFailurePort(){
		return failurePortAdded
	}

	def createInstance(ArchitectureSubcompnent subcompnent) {
		val instance = cmpModelFactory.createAsynchronousComponentInstance
		instance.name = "inst" + component.name.toFirstUpper
		instance.type = component
		instanceMap.put(subcompnent, instance)
		return instance
	}

	def getInstance(ArchitectureSubcompnent subcompnent) {
		if (!instanceMap.containsKey(subcompnent)) {
			throw new ArchitectureException("Subcomponent has no interface component", subcompnent)
		}
		return instanceMap.get(subcompnent)
	}

	def addFlow(InformationFlow flow) {
		val type = flow.flowType
		var portName = flow.gammaName
		if (portName.blank) {
			portName = flow.source.gammaName
		}
		portName = portName + nameCNTR + "_" + portNameCNTR
		val inport = type.createPort(portName, true)
		val outport = type.createPort(portName, false)
		component.ports += inport
		component.ports += outport
		inportMap.put(flow, inport)
		outportMap.put(flow, outport)
		val ifInst = cmpModelFactory.createAsynchronousComponentInstance
		ifInst.name = flow.gammaName + type.name + portNameCNTR++
		var ifType = allocationTrace.getInterfacingComponent(flow, phyPort) // trace.getInterfaceComponent(type)
		ifInst.type = ifType
		component.components += ifInst
		val inBinding = createPortBinding(inport, ifInst, ifType.requiredPorts.get(0))
		val outBinding = createPortBinding(outport, ifInst, ifType.providedPorts.get(0))
		component.portBindings += inBinding
		component.portBindings += outBinding
		nameCNTR++

		// add failure propagation channel
		if (trace.isAutogenInterfaceComponent(ifType)) {
			if (! failurePortAdded) {
				component.ports += failurePort
				failurePortAdded = true
			}
			component.portBindings += createPortBinding(failurePort, ifInst, ifType.failurePort)
		} else {
			val ifFunc = trace.get(ifType) as ArchitectureFunction
			for (archIF : ifFunc.providedInterfaces) {
				val instPort = findPort(ifType, trace.get(archIF) as Interface, true)
				if (failurePortMap.containsKey(archIF)) {
					component.portBindings += createPortBinding(failurePortMap.get(archIF), ifInst, instPort)
				} else {
					val port = instPort.clone
					port.name = ifInst.name + "__" + port.name
					component.ports += port
					component.portBindings += createPortBinding(port, ifInst, instPort)
					failurePorts.add(port)
				}
			}

		}

	}

	def getInPort(InformationFlow flow) {
		return inportMap.get(flow)
	}

	def getOutPort(InformationFlow flow) {
		return outportMap.get(flow)
	}

	static def createElementaryInterfaceComponents() {
		val comp = sctModelFactory.createAsynchronousStatechartDefinition
		comp.name = "Communication"
	}

}
