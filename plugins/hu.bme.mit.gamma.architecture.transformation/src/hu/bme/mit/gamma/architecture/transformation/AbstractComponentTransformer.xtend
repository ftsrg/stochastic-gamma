package hu.bme.mit.gamma.architecture.transformation

import hu.bme.mit.gamma.architecture.transformation.traceability.ArchitectureTrace
import hu.bme.mit.gamma.environment.model.EnvironmentAsynchronousCompositeComponent
import hu.bme.mit.gamma.statechart.composite.AsynchronousComponentInstance

abstract class AbstractComponentTransformer extends AbstractArchitectureTransformer {

	protected val EnvironmentAsynchronousCompositeComponent hwComponent
	protected val AsynchronousComponentInstance hwComponentInstance

	static var hwNameCNTR = 0
	new(ArchitectureTrace trace) {
		super(trace)
		hwComponent = stochModelFactory.createEnvironmentAsynchronousCompositeComponent
		hwComponent.name = "HardwareModel" + (hwNameCNTR)
		hwComponentInstance = cmpModelFactory.createAsynchronousComponentInstance
		hwComponentInstance.name = "HardwareModel" + (hwNameCNTR++)
		hwComponentInstance.type = hwComponent
		hwComponent.packageElement("subsystem_hardware")
	}

}
