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

class FunctionTransformer extends AbstractArchitectureTransformer{

	/*static def transformComponentFunction(ArchitectureFunction componentFunction, ArchitectureTrace trace) {
		val transformer = new FunctionTransformer(componentFunction, trace)
		val component = transformer.execute
	// return component
	}*/

	//val ArchitectureFunction function
	//val AsynchronousCompositeComponent component


	new(ArchitectureTrace trace) {
		super(trace)
	}

	def transform(ArchitectureFunction function) {
		val component = stochModelFactory.createEnvironmentAsynchronousCompositeComponent
		//this.component = cmpModelFactory.createAsynchronousCompositeComponent
		component.name = function.gammaName
		
		
		if (function.subfunctions.empty){
			throw new ArchitectureException("Function shall contain at least one subfunction",function)
		}
		if (function.ports.empty){
			throw new ArchitectureException("Function shall contain at least one port",function)
		}

		

		for (archPort : function.ports){
			component.ports+=archPort.transformPort
		}

		for (subfunc : function.subfunctions) {
			val inst = subfunc.transformSubfunction
			component.components += inst
			instancePorts.put(inst, inst.type.ports.toSet)
		}



		for (flow : function.informationFlows) {
			if (flow.isInPortBinding) {
				val port = trace.get(flow.source) as Port
				val targetInst = flow.flowTargetInst
				val targetPort = getFlowTargetPortLoose(flow)
				component.portBindings += createPortBinding(port, targetInst, targetPort)
			} else if (flow.isOutPortBinding) {
				val port = trace.get(flow.target) as Port
				val sourceInst = flow.flowSourceInst
				val sourcePort = getFlowSourcePortLoose(flow)
				component.portBindings += createPortBinding(port, sourceInst, sourcePort)
			} else {
				val sourceInst = flow.flowSourceInst
				val targetInst = flow.flowTargetInst
				val sourcePort = getFlowSourcePortLoose(flow)
				val targetPort = getFlowTargetPortLoose(flow)
				channelBuilder.add(sourceInst, sourcePort, targetInst, targetPort)
			}
		}

		
		
		// adding failure ports and binding
		// failurePort -> func.failurePort
		for (subfunc : function.subfunctions){
			for (instPort : subfunc.allProvidedInterfacePorts){
				val inst=trace.get(subfunc) as AsynchronousComponentInstance
				val port=instPort.clone
				port.name=subfunc.gammaName+SEP+port.name
				component.ports+=port
				component.portBindings+=createPortBinding(port,inst,instPort)
			}
			/* 
			for (archInterface : subfunc.type.providedInterfaces){
				val gammaInterface=trace.get(archInterface) as Interface;
				val inst=trace.get(subfunc) as AsynchronousComponentInstance
				val port=createPort(gammaInterface,inst.name,true)
				component.ports+=port
				val instPort=findInputPortLoose(inst,gammaInterface)
				component.portBindings+=createPortBinding(port,inst,instPort)
			}*/
		}

		component.channels += channelBuilder.build

		trace.add(function, component)
		component.packageElement("component_functions")
		return component
	}




}
