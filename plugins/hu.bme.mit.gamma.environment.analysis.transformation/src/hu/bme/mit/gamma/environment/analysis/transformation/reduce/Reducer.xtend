package hu.bme.mit.gamma.environment.analysis.transformation.reduce

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine
import org.eclipse.viatra.query.runtime.emf.EMFScope
import org.eclipse.viatra.transformation.runtime.emf.modelmanipulation.IModelManipulations
import org.eclipse.viatra.transformation.runtime.emf.modelmanipulation.SimpleModelManipulations
import org.eclipse.viatra.transformation.runtime.emf.rules.batch.BatchTransformationRuleFactory
import org.eclipse.viatra.transformation.runtime.emf.rules.batch.BatchTransformationRule
import org.eclipse.viatra.transformation.runtime.emf.transformation.batch.BatchTransformation
import org.eclipse.viatra.transformation.runtime.emf.transformation.batch.BatchTransformationStatements
import org.eclipse.emf.ecore.resource.Resource
import hu.bme.mit.gamma.environment.model.EnvironmentAsynchronousCompositeComponent
import hu.bme.mit.gamma.statechart.composite.AsynchronousCompositeComponent
import hu.bme.mit.gamma.statechart.interface_.InterfaceModelFactory
import hu.bme.mit.gamma.statechart.statechart.StatechartModelFactory
import hu.bme.mit.gamma.environment.model.EnvironmentModelFactory
import hu.bme.mit.gamma.statechart.composite.CompositeModelFactory
import hu.bme.mit.gamma.statechart.interface_.Package

import static com.google.common.base.Preconditions.checkArgument
import static com.google.common.base.Preconditions.checkState
import hu.bme.mit.gamma.environment.analysis.transformation.reduce.patterns.EnvironmentAsynchronousCompositeComponentPattern
import hu.bme.mit.gamma.environment.analysis.transformation.reduce.patterns.EnvironmentSynchronousCompositeComponentPattern
import hu.bme.mit.gamma.environment.analysis.transformation.reduce.patterns.EnvironmentCascadeCompositeComponentPattern
import hu.bme.mit.gamma.environment.analysis.transformation.reduce.patterns.PackagePattern

import hu.bme.mit.gamma.environment.analysis.transformation.reduce.Trace
import hu.bme.mit.gamma.environment.analysis.transformation.reduce.patterns.EnvironmentSynchronousCompositeComponentInstancePattern
import hu.bme.mit.gamma.environment.analysis.transformation.reduce.patterns.EnvironmentAsynchronousCompositeComponentInstancePattern
import hu.bme.mit.gamma.environment.analysis.transformation.reduce.patterns.EnvironmentCascadeCompositeComponentInstancePattern
import hu.bme.mit.gamma.environment.model.EnvironmentSynchronousCompositeComponent
import hu.bme.mit.gamma.environment.model.EnvironmentCascadeCompositeComponent
import hu.bme.mit.gamma.environment.analysis.transformation.reduce.patterns.ElementaryEnvironmentComponentInstancePattern

class Reducer {

    /* Transformation-related extensions */
    extension BatchTransformation transformation
    extension BatchTransformationStatements statements
    
    /* Transformation rule-related extensions */
    extension BatchTransformationRuleFactory = new BatchTransformationRuleFactory
    extension IModelManipulations manipulation
	protected final extension InterfaceModelFactory = InterfaceModelFactory.eINSTANCE
	protected final extension StatechartModelFactory = StatechartModelFactory.eINSTANCE
	protected final extension CompositeModelFactory = CompositeModelFactory.eINSTANCE
	protected final extension EnvironmentModelFactory = EnvironmentModelFactory.eINSTANCE 
    
	protected final Package _package
	protected Trace trace
    protected ViatraQueryEngine engine
    protected Resource resource
    protected BatchTransformationRule<EnvironmentAsynchronousCompositeComponentPattern.Match,EnvironmentAsynchronousCompositeComponentPattern.Matcher> asynchronousCompositeComponentRule
    protected BatchTransformationRule<EnvironmentSynchronousCompositeComponentPattern.Match,EnvironmentSynchronousCompositeComponentPattern.Matcher> synchronousCompositeComponentRule
    protected BatchTransformationRule<EnvironmentCascadeCompositeComponentPattern.Match,EnvironmentCascadeCompositeComponentPattern.Matcher> cascadeCompositeComponentRule
    protected BatchTransformationRule<EnvironmentAsynchronousCompositeComponentInstancePattern.Match,EnvironmentAsynchronousCompositeComponentInstancePattern.Matcher> asynchronousCompositeComponentInstanceRule
    protected BatchTransformationRule<EnvironmentSynchronousCompositeComponentInstancePattern.Match,EnvironmentSynchronousCompositeComponentInstancePattern.Matcher> synchronousCompositeComponentInstanceRule
    protected BatchTransformationRule<EnvironmentCascadeCompositeComponentInstancePattern.Match,EnvironmentCascadeCompositeComponentInstancePattern.Matcher> cascadeCompositeComponentInstanceRule
    protected BatchTransformationRule<ElementaryEnvironmentComponentInstancePattern.Match,ElementaryEnvironmentComponentInstancePattern.Matcher> elementaryEnvironmentComponentInstanceRule
    protected BatchTransformationRule<PackagePattern.Match,PackagePattern.Matcher> packagePatternRule

	new(Package _package) {
		this._package = _package
		val resource = _package.eResource
		// Create EMF scope and EMF IncQuery engine based on the resource
		val scope = new EMFScope(resource)
		this.engine = ViatraQueryEngine.on(scope);
		this.trace = null
		// Create VIATRA Batch transformation
		transformation = BatchTransformation.forEngine(engine).build
		// Initialize batch transformation statements
		statements = transformation.transformationStatements
	}

    public def execute() {
//      Fire the defined rules here
		getAsynchronousCompositeComponentRule().fireAllCurrent
		getSynchronousCompositeComponentRule().fireAllCurrent
		getCascadeCompositeComponentRule().fireAllCurrent
    }

    private def createTransformation() {
        //Create VIATRA model manipulations
        this.manipulation = new SimpleModelManipulations(engine)
        //Create VIATRA Batch transformation
        transformation = BatchTransformation.forEngine(engine)
        .build
        //Initialize batch transformation statements
        statements = transformation.transformationStatements
    }
    
    
	private def getPackagePatternRule() {
		if (packagePatternRule === null) {
			packagePatternRule = createRule(PackagePattern.instance).action [
				checkState(trace === null)
				val source = it.package
				val target = createPackage => [
					it.name = source.name
					it.constantDeclarations+=source.constantDeclarations
					it.basicConstraintDefinitions+=source.basicConstraintDefinitions
					it.interfaces+=source.interfaces
					it.imports+=source.imports
					it.parameterDeclarations+=source.parameterDeclarations
				]
				this.trace = new Trace(source, target)
				trace.put(source, target)
			].build
		}
		return packagePatternRule
	}
	

	private def getAsynchronousCompositeComponentRule() {
		if (asynchronousCompositeComponentRule == null){
			asynchronousCompositeComponentRule = createRule(EnvironmentAsynchronousCompositeComponentPattern.instance).action[
				checkState(trace === null)
				val source=it.component
				val target= createAsynchronousCompositeComponent => [
					it.name=source.name
					it.annotations.addAll(source.annotations)
					it.ports.addAll(source.ports)
					it.parameterDeclarations.addAll(source.parameterDeclarations)
					it.functionDeclarations.addAll(source.functionDeclarations)
				]
				val sourcePackage=source.eContainer as Package
				val targetPackage = createPackage => [
					it.name = sourcePackage.name
				]
				targetPackage.components+=target
				trace.put(sourcePackage,targetPackage)
				trace.put(source,target)
			].build
		}
	}

	private def getSynchronousCompositeComponentRule() {
		if (synchronousCompositeComponentRule == null){
			synchronousCompositeComponentRule = createRule(EnvironmentSynchronousCompositeComponentPattern.instance).action[
				checkState(trace === null)
				val source=it.component
				val target= createSynchronousCompositeComponent => [
					it.name=source.name
					it.annotations.addAll(source.annotations)
					it.ports.addAll(source.ports)
					it.parameterDeclarations.addAll(source.parameterDeclarations)
					it.functionDeclarations.addAll(source.functionDeclarations)
				]
				val sourcePackage=source.eContainer as Package
				val targetPackage = createPackage => [
					it.name = sourcePackage.name
				]
				targetPackage.components+=target
				trace.put(sourcePackage,targetPackage)
				trace.put(source,target)
			].build
		}
	}

	private def getCascadeCompositeComponentRule() {
		if (cascadeCompositeComponentRule == null){
			cascadeCompositeComponentRule = createRule(EnvironmentCascadeCompositeComponentPattern.instance).action[
				checkState(trace === null)
				val source=it.component
				val target= createCascadeCompositeComponent => [
					it.name=source.name
					it.annotations.addAll(source.annotations)
					it.ports.addAll(source.ports)
					it.parameterDeclarations.addAll(source.parameterDeclarations)
					it.functionDeclarations.addAll(source.functionDeclarations)
				]
				val sourcePackage=source.eContainer as Package
				val targetPackage = createPackage => [
					it.name = sourcePackage.name
				]
				targetPackage.components+=target
				trace.put(sourcePackage,targetPackage)
				trace.put(source,target)
			].build
		}
	}
	
	
	private def getAsynchronousCompositeComponentInstanceRule() {
		if (asynchronousCompositeComponentInstanceRule == null){
			asynchronousCompositeComponentInstanceRule = createRule(EnvironmentAsynchronousCompositeComponentInstancePattern.instance).action[
				checkState(trace === null)
				val source=it.component
				val sourceType = source.type
				val sourceComponent = source.eContainer as EnvironmentAsynchronousCompositeComponent
				val targetType = trace.getTargetAsynchronousComponent(sourceType)
				val targetContainer = trace.getTargetAsynchronousComponent(sourceComponent)
				val target= createAsynchronousComponentInstance => [
					it.name=source.name
					it.type=targetType
					it.arguments+=source.arguments
				]
				targetContainer.components+=target
				trace.put(source,target)
			].build
		}
	}
	
	private def getSynchronousCompositeComponentInstanceRule() {
		if (synchronousCompositeComponentInstanceRule == null){
			synchronousCompositeComponentInstanceRule = createRule(EnvironmentSynchronousCompositeComponentInstancePattern.instance).action[
				checkState(trace === null)
				val source=it.component
				val sourceType = source.type
				val sourceComponent = source.eContainer as EnvironmentSynchronousCompositeComponent
				val targetType = trace.getTargetSynchronousComponent(sourceType)
				val targetContainer = trace.getTargetSynchronousComponent(sourceComponent)
				val target= createSynchronousComponentInstance => [
					it.name=source.name
					it.type=targetType
					it.arguments+=source.arguments
				]
				targetContainer.components+=target
				trace.put(source,target)
			].build
		}
	}
	
	private def getCascadeCompositeComponentInstanceRule() {
		if (cascadeCompositeComponentInstanceRule == null){
			cascadeCompositeComponentInstanceRule = createRule(EnvironmentCascadeCompositeComponentInstancePattern.instance).action[
				checkState(trace === null)
				val source=it.component
				val sourceType = source.type
				val sourceComponent = source.eContainer as EnvironmentCascadeCompositeComponent
				val targetType = trace.getTargetCascadeComponent(sourceType)
				val targetContainer = trace.getTargetCascadeComponent(sourceComponent)
				val target= createSynchronousComponentInstance => [
					it.name=source.name
					it.type=targetType
					it.arguments+=source.arguments
				]
				targetContainer.components+=target
				trace.put(source,target)
			].build
		}
	}
	
	private def getElementaryEnvironmentComponentInstanceRule() {
		if (elementaryEnvironmentComponentInstanceRule == null){
			elementaryEnvironmentComponentInstanceRule = createRule(ElementaryEnvironmentComponentInstancePattern.instance).action[
				checkState(trace === null)
				val source=it.instance
				if (source.eContainer instanceof EnvironmentAsynchronousCompositeComponent){
					val sourceComponent = source.eContainer as EnvironmentAsynchronousCompositeComponent
					val targetContainer = trace.getTargetAsynchronousComponent(sourceComponent)
					val targetType = createAsynchronousStatechartDefinition => [
						it.name=source.name+"Type"
						it.ports+=source.inports
						it.ports+=source.outports
						val state1=createState => [
							it.name="state1"
						]
						val init1=createInitialState => [
							it.name="init1"
						]
						it.regions+= createRegion => [
							it.name="mainRegion"
							it.stateNodes+=init1
							it.stateNodes+=state1
						]
						it.transitions+=createTransition => [
							it.sourceState=init1
							it.targetState=state1
						]
					]
					val sourcePackage=source.eContainer as Package
					val targetPackage = createPackage => [
						it.name = sourcePackage.name
					]
					targetPackage.components+=targetType
					trace.put(sourcePackage,targetPackage)
					val targetInstance = createAsynchronousComponentInstance => [
						it.name=source.name
						it.type=targetType
					]
					targetContainer.components+=targetInstance
					trace.put(source,targetInstance,targetType)
				}
				
				if (source.eContainer instanceof EnvironmentSynchronousCompositeComponent){
					val sourceComponent = source.eContainer as EnvironmentSynchronousCompositeComponent
					val targetContainer = trace.getTargetSynchronousComponent(sourceComponent)
					val targetType = createSynchronousStatechartDefinition => [
						it.name=source.name+"Type"
						it.ports+=source.inports
						it.ports+=source.outports
						val state1=createState => [
							it.name="state1"
						]
						val init1=createInitialState => [
							it.name="init1"
						]
						it.regions+= createRegion => [
							it.name="mainRegion"
							it.stateNodes+=init1
							it.stateNodes+=state1
						]
						it.transitions+=createTransition => [
							it.sourceState=init1
							it.targetState=state1
						]
					]
					val sourcePackage=source.eContainer as Package
					val targetPackage = createPackage => [
						it.name = sourcePackage.name
					]
					targetPackage.components+=targetType
					trace.put(sourcePackage,targetPackage)
					val targetInstance = createSynchronousComponentInstance => [
						it.name=source.name
						it.type=targetType
					]
					targetContainer.components+=targetInstance
					trace.put(source,targetInstance,targetType)
				}
				
				if (source.eContainer instanceof EnvironmentCascadeCompositeComponent){
					val sourceComponent = source.eContainer as EnvironmentCascadeCompositeComponent
					val targetContainer = trace.getTargetCascadeComponent(sourceComponent)
					val targetType = createSynchronousStatechartDefinition => [
						it.name=source.name+"Type"
						it.ports+=source.inports
						it.ports+=source.outports
						val state1=createState => [
							it.name="state1"
						]
						val init1=createInitialState => [
							it.name="init1"
						]
						it.regions+= createRegion => [
							it.name="mainRegion"
							it.stateNodes+=init1
							it.stateNodes+=state1
						]
						it.transitions+=createTransition => [
							it.sourceState=init1
							it.targetState=state1
						]
					]
					val sourcePackage=source.eContainer as Package
					val targetPackage = createPackage => [
						it.name = sourcePackage.name
					]
					targetPackage.components+=targetType
					trace.put(sourcePackage,targetPackage)
					val targetInstance = createSynchronousComponentInstance => [
						it.name=source.name
						it.type=targetType
					]
					targetContainer.components+=targetInstance
					trace.put(source,targetInstance,targetType)
				}
			].build
		}
	}
	

    def dispose() {
        if (transformation != null) {
            transformation.ruleEngine.dispose
        }
        transformation = null
        return
    }
}
