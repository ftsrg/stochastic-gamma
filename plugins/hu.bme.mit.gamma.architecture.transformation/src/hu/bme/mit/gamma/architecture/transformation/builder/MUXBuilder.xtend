package hu.bme.mit.gamma.architecture.transformation.builder

import hu.bme.mit.gamma.architecture.model.ArchitectureSubfunction
import hu.bme.mit.gamma.statechart.interface_.Package
import hu.bme.mit.gamma.statechart.statechart.StatechartModelFactory
import hu.bme.mit.gamma.statechart.statechart.AsynchronousStatechartDefinition
import hu.bme.mit.gamma.statechart.interface_.Interface
import hu.bme.mit.gamma.architecture.transformation.traceability.ArchitectureTrace
import hu.bme.mit.gamma.environment.model.EnvironmentAsynchronousCompositeComponent
import hu.bme.mit.gamma.environment.model.EnvironmentAsynchronousCompositeComponentInstance

import static extension hu.bme.mit.gamma.architecture.transformation.util.TransformationUtils.*
import static extension hu.bme.mit.gamma.statechart.derivedfeatures.StatechartModelDerivedFeatures.*

class MUXBuilder extends PrimitiveFunctionBuilder{
	
	var inputNum=0
	var Interface type=null
	val AsynchronousStatechartDefinition component
	
	var boolean isFirst=true

	
	new(ArchitectureSubfunction subfunction, ArchitectureTrace trace) {
		super(subfunction, trace)
		val modelFactory=StatechartModelFactory.eINSTANCE
		component=modelFactory.createAsynchronousStatechartDefinition
	}
	
	override getInPort() {
		if (isFirst){
		}
	}
	
	override getOutPort() {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}
	
	override getPackage() {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}
	
	override build() {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}
	
}