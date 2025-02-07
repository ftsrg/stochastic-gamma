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

import hu.bme.mit.gamma.architecture.transformation.traceability.ArchitectureTrace
import hu.bme.mit.gamma.environment.model.EnvironmentModelFactory
import hu.bme.mit.gamma.statechart.statechart.StatechartModelFactory
import hu.bme.mit.gamma.statechart.interface_.InterfaceModelFactory
import hu.bme.mit.gamma.statechart.composite.CompositeModelFactory
import hu.bme.mit.gamma.expression.model.ExpressionModelFactory
import hu.bme.mit.gamma.util.GammaEcoreUtil
import hu.bme.mit.gamma.statechart.interface_.Interface
import hu.bme.mit.gamma.statechart.interface_.Event
import hu.bme.mit.gamma.architecture.model.ArchitectureComponent
import hu.bme.mit.gamma.statechart.composite.AsynchronousCompositeComponent
import static extension hu.bme.mit.gamma.statechart.derivedfeatures.StatechartModelDerivedFeatures.*
import static extension hu.bme.mit.gamma.architecture.transformation.util.TransformationUtils.*
import static extension hu.bme.mit.gamma.architecture.transformation.RelationTransfomer.*
import hu.bme.mit.gamma.environment.model.EnvironmentAsynchronousCompositeComponent
import hu.bme.mit.gamma.statechart.composite.AsynchronousComponentInstance
import hu.bme.mit.gamma.architecture.transformation.builder.FailureModelGenerator
import hu.bme.mit.gamma.statechart.interface_.Port
import hu.bme.mit.gamma.architecture.model.ArchitecturePort
import hu.bme.mit.gamma.statechart.interface_.InterfaceRealization
import hu.bme.mit.gamma.statechart.interface_.RealizationMode

class ComponentTransformer extends AbstractArchitectureTransformer {

	// val ArchitectureComponent archComp
	// val AsynchronousCompositeComponent gammaComp
	// val EnvironmentAsynchronousCompositeComponent hwComponent
	// val AsynchronousComponentInstance hwComponentInstance
	val extension FailureModelGenerator failureModelGenerator

	new(ArchitectureTrace trace) {
		super(trace)

		this.failureModelGenerator = new FailureModelGenerator(trace)

	}

	def transform(ArchitectureComponent component) {

		val archComp = component

		val gammaComp = stochModelFactory.createEnvironmentAsynchronousCompositeComponent
		gammaComp.name = archComp.gammaName

		val hwComponent = stochModelFactory.createEnvironmentAsynchronousCompositeComponent
		hwComponent.name = component.name + "_HardwareModel"

		val hwComponentInstance = cmpModelFactory.createAsynchronousComponentInstance
		hwComponentInstance.name = "inst_" + component.name + "_HardwareModel"
		hwComponentInstance.type = hwComponent
		hwComponent.packageElement("subsystem_hardware")

		val errorSctTransformer = new HWErrorSCTTransformer(trace)
		val subsysStatechart = errorSctTransformer.transform(component)
		val subsysStatechartInstance = cmpModelFactory.createAsynchronousComponentInstance
		subsysStatechartInstance.type = subsysStatechart
		val funcFailurePorts = <Interface, Port>newHashMap
		subsysStatechartInstance.name = "inst_" + subsysStatechart.name
		gammaComp.components += subsysStatechartInstance
		for (port : subsysStatechart.ports) {
			// interfaceRealization.realizationMode==RealizationMode.REQUIRED
			if (port.name.matches("^HardwareFailure_")) {
				val source = generateSource("SubsystemHWFailure", port.interface)
				val outPort = createPort(port.interface, "Failures", false)
				hwComponent.environmentComponents += source
				hwComponent.ports += outPort
				val sourcePort = source.outports.get(0)

				// hw.source -> hw.port -o)- commInst.port
				channelBuilder.add(hwComponentInstance, outPort, subsysStatechartInstance, port)
				hwComponent.portBindings += createPortBinding(outPort, source, sourcePort)
			} else if (port.name.matches("^FunctionalFailure_")) {
				funcFailurePorts.put(port.interface, port)
			} else {
				val compPort = port.clone
				gammaComp.ports += compPort
				gammaComp.portBindings += createPortBinding(compPort, subsysStatechartInstance, port)
			}

		}

		gammaComp.components += hwComponentInstance
		trace.add(archComp, gammaComp)

		for (subfunc : archComp.subfunctions) {
			val inst = subfunc.transformSubfunction
			gammaComp.components += inst
			instancePorts.put(inst, inst.type.ports.toSet)
		}

		for (flow : archComp.informationFlows) {
			val flowType = flow.flowType
			if (flow.isInPortBinding2) {
				val compPort = flow.source as ArchitecturePort
				val targetInst = flow.flowTargetInst
				val targetPort = getFlowTargetPortLoose(flow)
				val port = createPort(flowType, compPort.name.gammaName + "_" + flow.gammaName, true)
				trace.phyPortMap.put(compPort, port)
				gammaComp.ports += port
				gammaComp.portBindings += createPortBinding(port, targetInst, targetPort)
			} else if (flow.isOutPortBinding2) {
				val compPort = flow.target as ArchitecturePort
				val sourceInst = flow.flowSourceInst
				val sourcePort = getFlowSourcePortLoose(flow)
				val port = createPort(flowType, compPort.name.gammaName + "_" + flow.gammaName, false)
				trace.phyPortMap.put(compPort, port)
				gammaComp.ports += port
				gammaComp.portBindings += createPortBinding(port, sourceInst, sourcePort)
			} else {
				val sourceInst = flow.flowSourceInst
				val targetInst = flow.flowTargetInst
				val sourcePort = getFlowSourcePortLoose(flow)
				val targetPort = getFlowTargetPortLoose(flow)
				channelBuilder.add(sourceInst, sourcePort, targetInst, targetPort)
			}
		}

		// adding failure propagation channels
		for (subfunc : archComp.subfunctions) {
			if (subfunc.allProvidedInterfacePorts.length > 0) {
				val source = generateSource(subfunc.gammaName)
				val funcCompInst = trace.get(subfunc) as AsynchronousComponentInstance
				hwComponent.environmentComponents += source

				for (inPort : subfunc.allProvidedInterfacePorts) {
					val inst = trace.get(subfunc) as AsynchronousComponentInstance
					val _interface = inPort.interfaceRealization.interface

					var Port outPort = null
					if (funcFailurePorts.containsKey(_interface)) {
						// outPort = providedIFPorts.get(_interface)
						// channelBuilder.add(hwComponentInstance, outPort, funcCompInst, inPort)
						outPort = funcFailurePorts.get(_interface)
						channelBuilder.add(subsysStatechartInstance, outPort, funcCompInst, inPort)
					} else {
						outPort = inPort.clone
						outPort.interfaceRealization.realizationMode = RealizationMode.PROVIDED
						outPort.name = subfunc.gammaName + SEP + inPort.name.replaceFirst("In$", "Out")
						// hw.source -> hw.port -o)- subfunc.port
						hwComponent.ports += outPort
						// hwComponent.environmentComponents += source
						channelBuilder.add(hwComponentInstance, outPort, funcCompInst, inPort)
					}

					val sourcePort = source.addPort(inPort.interface,
						inPort.name.replaceFirst(_interface.name + "In$", "Out"))

					hwComponent.portBindings += createPortBinding(outPort, source, sourcePort)
				}

			}

		/* 
		 * val funcCompInst = trace.get(subfunc) as AsynchronousComponentInstance
		 * val funcComp = funcCompInst.type
		 * for (subsubfunc : subfunc.type.subfunctions) {
		 * val source = generateSource(subfunc.gammaName + "___" + subsubfunc.gammaName)
		 * for (archInterface : subsubfunc.type.providedInterfaces) {
		 * 		val gammaInterface = trace.get(archInterface) as Interface
		 * 		val outPort = createPort(gammaInterface, subfunc.gammaName + "_" + subsubfunc.gammaName, false)

		 * 		val inPort = findPort(funcComp, gammaInterface,
		 * 			subsubfunc.gammaName + gammaInterface.name + "In", true)

		 * 		val sourcePort = source.addPort(gammaInterface)
		 * 		// hw.source -> hw.port -o)- subfunc.port
		 * 		hwComponent.ports += outPort
		 * 		channelBuilder.add(hwComponentInstance, outPort, funcCompInst, inPort)
		 * 		hwComponent.portBindings += createPortBinding(outPort, source, sourcePort)
		 * 		hwComponent.environmentComponents += source

		 * 	}
		 * }


		 * for (archInterface : subfunc.type.providedInterfaces) {
		 * 	val gammaInterface = trace.get(archInterface) as Interface
		 * 	val outPort = createPort(gammaInterface, subfunc.gammaName, false)

		 * 	val inPort = findPort(funcComp, gammaInterface, gammaInterface.name + "In", true)

		 * 	val sourcePort = source.addPort(gammaInterface)
		 * 	
		 * 	// hw.source -> hw.port -o)- subfunc.port
		 * 	hwComponent.ports += outPort
		 * 	channelBuilder.add(hwComponentInstance, outPort, funcCompInst, inPort)
		 * 	hwComponent.portBindings += createPortBinding(outPort, source, sourcePort)
		 * 	hwComponent.environmentComponents += source

		 }*/
		// component.packageElement("subsystems")
		}

		gammaComp.channels += channelBuilder.build
		gammaComp.packageElement("electronic_components")
		return gammaComp
	}

}
