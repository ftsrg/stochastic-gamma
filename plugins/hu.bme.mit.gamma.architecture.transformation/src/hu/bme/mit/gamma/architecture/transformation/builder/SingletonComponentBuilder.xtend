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
import hu.bme.mit.gamma.statechart.composite.AsynchronousComponentInstance
import hu.bme.mit.gamma.statechart.composite.ComponentInstance
import hu.bme.mit.gamma.architecture.model.ArchitectureSubcompnent
import hu.bme.mit.gamma.architecture.model.ArchitectureSubfunction
import hu.bme.mit.gamma.architecture.model.StructuralElement
import hu.bme.mit.gamma.architecture.transformation.errors.GammaTransformationException
import hu.bme.mit.gamma.architecture.transformation.AbstractArchitectureTransformer
import hu.bme.mit.gamma.statechart.interface_.RealizationMode
import hu.bme.mit.gamma.statechart.statechart.AsynchronousStatechartDefinition

class SingletonComponentBuilder extends AbstractArchitectureTransformer {

	val ArchitectureSubcompnent subcomponent

	val EnvironmentAsynchronousCompositeComponent component
	val AsynchronousComponentInstance instance
	val Map<InformationFlow, Port> inportMap = newHashMap
	val Map<InformationFlow, Port> outportMap = newHashMap
	val commInstances = <AsynchronousComponentInstance>newLinkedList
	val EnvironmentEventSource failureSource

	val extension FailureModelGenerator failureModelGenerator
	var nameCNTR = 0
	var instNameCNTR = 0
	var isBuilt = false

	static var hwNameCNTR = 0

	val EnvironmentAsynchronousCompositeComponent hwComponent
	val AsynchronousComponentInstance hwComponentInstance

	val AsynchronousStatechartDefinition subsysStatechart
	val AsynchronousComponentInstance subsysStatechartInstance
	val funcFailurePorts = <Interface, Port>newHashMap

	new(ArchitectureSubcompnent subcompnent, ArchitectureTrace trace) {

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
		hwComponent.name = component.name + "_HardwareModel" // + (hwNameCNTR)
		hwComponentInstance = cmpModelFactory.createAsynchronousComponentInstance
		hwComponentInstance.name = instance.name + "_HardwareModel" // + (hwNameCNTR++)
		hwComponentInstance.type = hwComponent
		hwComponent.packageElement("subsystem_hardware")
		addInstance(hwComponentInstance)

		subsysStatechart = trace.getHWErrorSct(subcompnent.type) as AsynchronousStatechartDefinition
		subsysStatechartInstance = cmpModelFactory.createAsynchronousComponentInstance
		subsysStatechartInstance.name = "inst_" + subsysStatechart.name
		subsysStatechartInstance.type = subsysStatechart
		addInstance(subsysStatechartInstance)
		for (port : subsysStatechart.ports) {
			// interfaceRealization.realizationMode==RealizationMode.REQUIRED
			if (port.name.matches("^HardwareFailure_.*")) {
				val source = generateSource("SubsystemHWFailure", port.interface)
				val outPort = createPort(port.interface, "Failures", false)
				hwComponent.environmentComponents += source
				hwComponent.ports += outPort
				val sourcePort = source.outports.get(0)

				// hw.source -> hw.port -o)- commInst.port
				addChannel(hwComponentInstance, outPort, subsysStatechartInstance, port)
				hwComponent.portBindings += createPortBinding(outPort, source, sourcePort)
			} else if (port.name.matches("^FunctionalFailure_.*")) {
				funcFailurePorts.put(port.interface, port)
			} else {
				val compPort = port.clone
				component.ports += compPort
				component.portBindings += createPortBinding(compPort, subsysStatechartInstance, port)
			}

		}

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
		val type = flow.flowType
		val inport = type.createPort(flow.name + "_" + nameCNTR, true)
		component.ports += inport
		inportMap.put(flow, inport)
		nameCNTR++
		return inport
	}

	def getOutPort(InformationFlow flow) {
		val type = flow.flowType
		val outport = type.createPort(flow.name + "_" + nameCNTR, false)
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
	// instance.name = instance.name // +"_"+instNameCNTR++
	// add failure propagation channels?
	}

	def addCommInstance(AsynchronousComponentInstance instance) {
		commInstances += instance
		component.components += instance
		instance.name = instance.name + "_" + instNameCNTR++
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
			/*val providedIFPorts = <Interface, Port>newHashMap
			 * val gsource = generatePSource("Global_Event_Source")
			 * if (subcomponent.type.providedInterfaces.length > 0) {
			 * 	hwComponent.environmentComponents.add(gsource)
			 * 	for (_ainterface : subcomponent.type.providedInterfaces) {
			 * 		val _interface=trace.get(_ainterface) as Interface
			 * 		val port = createPort(_interface, "", false)
			 * 		hwComponent.ports += port
			 * 		val s_port = port.clone
			 * 		gsource.outports += s_port
			 * 		providedIFPorts.put(_interface, port)
			 * 		hwComponent.portBindings += createPortBinding(port, gsource, s_port)
			 * 	}
			 }*/
			var cntr2 = 0
			for (subfunc : subcomponent.allSubfunctions) {
				if (subfunc.allProvidedInterfacePorts.length > 0) {
					val funcCompInst = trace.get(subfunc) as AsynchronousComponentInstance
					val source = generateSource(subfunc.gammaName)
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
							outPort.name = subfunc.gammaName + "___" + outPort.name.replaceFirst("In$", "Out")

							val sourcePort = source.addPort(inPort.interface,
								inPort.name.replaceFirst(_interface.name + "In$", ""))

							// hw.source -> hw.port -o)- subfunc.port
							hwComponent.ports += outPort
							// hwComponent.environmentComponents += source
							hwComponent.portBindings += createPortBinding(outPort, source, sourcePort)
							channelBuilder.add(hwComponentInstance, outPort, funcCompInst, inPort)
						// source.outports+=sourcePort
						}

					}

				}

			}

			// communication failures
			var cntr = 0
			for (commInst : commInstances) {
				var EnvironmentEventSource  source = null
				val ifBuilder = trace.getInterfaceComponentBuilder(commInst.type)
				if (ifBuilder.hasFailurePort){
					source=generateSource(commInst.name, elementTransformer.failureInterface)
				}else{
					source=generateSource(commInst.name)
				} 
				hwComponent.environmentComponents += source
				for (inPort : ifBuilder.failurePorts) {
					val _interface = inPort.interfaceRealization.interface

					var Port outPort = null
					if (funcFailurePorts.containsKey(_interface)) {
						outPort = funcFailurePorts.get(_interface)
						channelBuilder.add(subsysStatechartInstance, outPort, commInst, inPort)
					} else {
						outPort = inPort.clone
						outPort.interfaceRealization.realizationMode = RealizationMode.PROVIDED
						outPort.name = commInst.name + "___" + outPort.name.replaceFirst("In$", "Out")

						val sourcePort = source.addPort(inPort.interface,
							inPort.name.replaceFirst(_interface.name + "In$", ""))

						// hw.source -> hw.port -o)- subfunc.port
						hwComponent.ports += outPort
						// hwComponent.environmentComponents += source
						hwComponent.portBindings += createPortBinding(outPort, source, sourcePort)
						channelBuilder.add(hwComponentInstance, outPort, commInst, inPort)
					// source.outports+=sourcePort
					}

				}
				
				
				if (ifBuilder.hasFailurePort){
					//val source = generateSource(commInst.name + cntr, elementTransformer.failureInterface)
					val outPort = createPort(elementTransformer.failureInterface, commInst.name + "Failures" + cntr++,
						false)
					
					
					val inPort = commInst.type.failurePort
					hwComponent.ports += outPort
	
					val sourcePort = source.outports.get(0)
					// hw.source -> hw.port |--o)--| commInst.port
					addChannel(hwComponentInstance, outPort, commInst, inPort)
					hwComponent.portBindings += createPortBinding(outPort, source, sourcePort)
				}

			}

			component.channels += (channelBuilder.build)
			isBuilt = true
		} else {
			throw new GammaTransformationException("Singleton Component Builder is already built")
		}

	}

}
