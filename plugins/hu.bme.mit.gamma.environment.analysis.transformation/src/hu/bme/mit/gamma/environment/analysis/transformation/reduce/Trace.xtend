package hu.bme.mit.gamma.environment.analysis.transformation.reduce

import hu.bme.mit.gamma.environment.model.EnvironmentAsynchronousCompositeComponent
import hu.bme.mit.gamma.statechart.composite.AsynchronousCompositeComponent
import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine
import org.eclipse.viatra.query.runtime.emf.EMFScope
import hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.ReducerTraceFactory
import hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.ReducerTrace

import static com.google.common.base.Preconditions.checkArgument
import static com.google.common.base.Preconditions.checkState
import hu.bme.mit.gamma.environment.model.EnvironmentSynchronousCompositeComponent
import hu.bme.mit.gamma.statechart.composite.SynchronousCompositeComponent
import hu.bme.mit.gamma.statechart.composite.CascadeCompositeComponent
import hu.bme.mit.gamma.environment.model.EnvironmentCascadeCompositeComponent
import hu.bme.mit.gamma.statechart.composite.SynchronousComponentInstance
import hu.bme.mit.gamma.environment.model.EnvironmentCascadeCompositeComponentInstance
import hu.bme.mit.gamma.environment.model.EnvironmentSynchronousCompositeComponentInstance
import hu.bme.mit.gamma.environment.model.EnvironmentAsynchronousCompositeComponentInstance
import hu.bme.mit.gamma.statechart.composite.AsynchronousComponentInstance
import hu.bme.mit.gamma.statechart.composite.SimpleChannel
import hu.bme.mit.gamma.statechart.composite.BroadcastChannel
import hu.bme.mit.gamma.statechart.composite.PortBinding
import hu.bme.mit.gamma.environment.model.ElementaryEnvironmentComponentInstance
import hu.bme.mit.gamma.statechart.composite.ComponentInstance
import hu.bme.mit.gamma.statechart.statechart.StatechartDefinition
import hu.bme.mit.gamma.statechart.interface_.Package import hu.bme.mit.gamma.statechart.composite.AsynchronousComponent
import hu.bme.mit.gamma.environment.analysis.transformation.reduce.patterns.EnvironmentAsynchronousCompositeCopmponentTracePattern
import hu.bme.mit.gamma.environment.analysis.transformation.reduce.patterns.EnvironmentSynchronousCompositeCopmponentTracePattern
import hu.bme.mit.gamma.environment.analysis.transformation.reduce.patterns.EnvironmentCascadeCompositeComponentTracePattern

class Trace {
 
	// Trace model factory
	protected final extension ReducerTraceFactory traceabilityFactory = ReducerTraceFactory.eINSTANCE
	// Trace model
	protected final ReducerTrace trace;
	// Tracing engine
	protected final ViatraQueryEngine tracingEngine

	new(Package sourcePackage, Package targetPackage) {
		this.trace = createReducerTrace => [
			it.source = source 
			it.target = target
		]
		this.tracingEngine = ViatraQueryEngine.on(new EMFScope(trace))
	}

	def getSource() {
		return trace.source
	}

	def getTarget() { 
		return trace.target
	}

	// Source and target package
	def put(Package source, Package target) {
		checkArgument(source !== null)
		checkArgument(target !== null)
		trace.traces += createPackageTrace => [
			it.source = source
			it.target = target
		]
	}

	def put(EnvironmentAsynchronousCompositeComponent source, AsynchronousCompositeComponent target) {
		checkArgument(source !== null)
		checkArgument(target !== null)
		trace.traces += createAsynchronousCompositeComponentTrace => [
			it.source = source
			it.target = target
		]
	}

	def put(EnvironmentSynchronousCompositeComponent source, SynchronousCompositeComponent target) {
		checkArgument(source !== null)
		checkArgument(target !== null)
		trace.traces += createSynchronousCompositeComponentTrace => [
			it.source = source
			it.target = target
		]
	}

	def put(EnvironmentCascadeCompositeComponent source, CascadeCompositeComponent target) {
		checkArgument(source !== null)
		checkArgument(target !== null)
		trace.traces += createCascadeCompositeComponentTrace => [
			it.source = source
			it.target = target
		]
	}

	def put(EnvironmentAsynchronousCompositeComponentInstance source, AsynchronousComponentInstance target) {
		checkArgument(source !== null)
		checkArgument(target !== null)
		trace.traces += createAsynchronousCompositeInstanceTrace => [
			it.source = source
			it.target = target
		]
	}

	def put(EnvironmentSynchronousCompositeComponentInstance source, SynchronousComponentInstance target) {
		checkArgument(source !== null)
		checkArgument(target !== null)
		trace.traces += createSynchronousCompositeInstanceTrace => [
			it.source = source
			it.target = target
		]
	}

	def put(EnvironmentCascadeCompositeComponentInstance source, SynchronousComponentInstance target) {
		checkArgument(source !== null)
		checkArgument(target !== null)
		trace.traces += createCascadeCompositeInstanceTrace => [
			it.source = source
			it.target = target
		]
	}

	def put(ElementaryEnvironmentComponentInstance source, ComponentInstance instanceTarget,
		StatechartDefinition typeTarget) {
		checkArgument(source !== null)
		checkArgument(target !== null)
		trace.traces += createElementaryTrace => [
			it.source = source
			it.instanceTarget = instanceTarget
			it.typeTarget = typeTarget
		]
	}

	def put(PortBinding source, PortBinding target) {
		checkArgument(source !== null)
		checkArgument(target !== null)
		trace.traces += createBindingTrace => [
			it.source = source
			it.target = target
		]
	}

	def put(SimpleChannel source, SimpleChannel target) {
		checkArgument(source !== null)
		checkArgument(target !== null)
		trace.traces += createSimpleChannelTrace => [
			it.source = source
			it.target = target
		]
	}

	def put(BroadcastChannel source, BroadcastChannel target) {
		checkArgument(source !== null)
		checkArgument(target !== null)
		trace.traces += createBroadcastChannelTrace => [
			it.source = source
			it.target = target
		]
	}
	
	def getTargetAsynchronousComponent(EnvironmentAsynchronousCompositeComponent source) {
		checkArgument(source !== null)
		val matches = EnvironmentAsynchronousCompositeCopmponentTracePattern.Matcher.on(tracingEngine).getAllValuesOftarget(source)
		checkState(matches.size == 1, matches.size)
		return matches.head
	}
	
	def getTargetSynchronousComponent(EnvironmentSynchronousCompositeComponent source) {
		checkArgument(source !== null)
		val matches = EnvironmentSynchronousCompositeCopmponentTracePattern.Matcher.on(tracingEngine).getAllValuesOftarget(source)
		checkState(matches.size == 1, matches.size)
		return matches.head
	}
	
	def getTargetCascadeComponent(EnvironmentCascadeCompositeComponent source) {
		checkArgument(source !== null)
		val matches = EnvironmentCascadeCompositeComponentTracePattern.Matcher.on(tracingEngine).getAllValuesOftarget(source)
		checkState(matches.size == 1, matches.size)
		return matches.head
	}

}
