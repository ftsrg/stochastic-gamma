package hu.bme.mit.gamma.architecture.transformation

import hu.bme.mit.gamma.architecture.transformation.traceability.ArchitectureTrace
import hu.bme.mit.gamma.statechart.statechart.SchedulingOrder
import hu.bme.mit.gamma.statechart.statechart.TransitionPriority
import hu.bme.mit.gamma.statechart.interface_.Interface

import static extension hu.bme.mit.gamma.architecture.transformation.util.TransformationUtils.*
import hu.bme.mit.gamma.architecture.model.ElectricalInterface
import hu.bme.mit.gamma.statechart.interface_.InterfaceRealization
import hu.bme.mit.gamma.statechart.interface_.RealizationMode
import hu.bme.mit.gamma.architecture.model.PhysicalInterface
import hu.bme.mit.gamma.architecture.model.ArchitectureComponent

class HWErrorSCTTransformer extends AbstractArchitectureTransformer{
	
	new(ArchitectureTrace trace) {
		super(trace)
	}
	
	def transform(ArchitectureComponent component){
		val sct = sctModelFactory.createAsynchronousStatechartDefinition
		sct.name = component.gammaName+"_HWErrorStatechart"
		for (archPort : component.ports) {
			val gammaIf = trace.get(archPort.type) as Interface
			sct.ports += createPort(gammaIf, archPort.name.gammaName, false)
			sct.ports += createPort(gammaIf, archPort.name.gammaName, true)
		}
		sct.schedulingOrder = SchedulingOrder.TOP_DOWN
		sct.transitionPriority = TransitionPriority.VALUE_BASED
		sct.annotations += sctModelFactory.createRunUponExternalEventOrInternalTimeoutAnnotation

		for (failureInterface : component.providedInterfaces) {
			val gammaIf = trace.get(failureInterface) as Interface
			if (failureInterface instanceof PhysicalInterface){
				sct.ports += createPort(gammaIf, "HardwareFailure_", true)
			}else{
				sct.ports += createPort(gammaIf, "FunctionalFailure_", false)
			}
		}

		trace.addHWErrorSct(component, sct)
		packageElement(sct, "hw_error_sct")
		return sct
	}
	
}