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
					val _interface=inPort.interfaceRealization.interface
					val outPort = inPort.clone
					outPort.interfaceRealization.realizationMode = RealizationMode.PROVIDED
					outPort.name = subfunc.gammaName+SEP+inPort.name.replaceFirst(_interface.name+"In$", "")

					val sourcePort = source.addPort(inPort.interface,inPort.name.replaceFirst(_interface.name+"In$", "Out"))

					// hw.source -> hw.port -o)- subfunc.port
					hwComponent.ports += outPort
					// hwComponent.environmentComponents += source
					channelBuilder.add(hwComponentInstance, outPort, funcCompInst, inPort)
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
