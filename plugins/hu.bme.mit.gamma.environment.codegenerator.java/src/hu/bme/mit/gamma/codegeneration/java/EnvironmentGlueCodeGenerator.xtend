package hu.bme.mit.gamma.codegeneration.java

import hu.bme.mit.gamma.codegeneration.java.queries.AbstractSynchronousCompositeComponents
import java.io.File
import org.eclipse.emf.ecore.resource.ResourceSet

import static extension hu.bme.mit.gamma.codegeneration.java.util.Namings.*
import hu.bme.mit.gamma.statechart.composite.AsynchronousAdapter
import hu.bme.mit.gamma.codegeneration.java.queries.SynchronousComponentWrappers
import hu.bme.mit.gamma.codegeneration.java.queries.AsynchronousCompositeComponents
import hu.bme.mit.gamma.codegeneration.java.GlueCodeGenerator
import hu.bme.mit.gamma.codegeneration.java.Trace
import hu.bme.mit.gamma.codegeneration.java.EnvironmentAsynchronousAdapterCodeGenerator
import hu.bme.mit.gamma.environment.xsts.codegeneration.java.StatechartToJavaCodeGenerator
import hu.bme.mit.gamma.codegeneration.java.queries.SimpleYakinduComponents
import hu.bme.mit.gamma.statechart.statechart.StatechartDefinition
import hu.bme.mit.gamma.lowlevel.xsts.transformation.LowlevelToXstsTransformer
import hu.bme.mit.gamma.statechart.lowlevel.transformation.GammaToLowlevelTransformer
import hu.bme.mit.gamma.lowlevel.xsts.transformation.TransitionMerging
import hu.bme.mit.gamma.environment.xsts.codegeneration.java.CommonizedVariableActionSerializer
import java.util.AbstractMap.SimpleEntry
import hu.bme.mit.gamma.xsts.model.XSTS
import hu.bme.mit.gamma.lowlevel.xsts.transformation.traceability.L2STrace;
import hu.bme.mit.gamma.codegeneration.java.queries.SimpleGammaComponents
import hu.bme.mit.gamma.codegeneration.java.util.ElementaryEnvironmentComponentUtility
import hu.bme.mit.gamma.codegeneration.java.util.EnvironmentVirtualTimerServiceCodeGenerator
import java.util.HashMap
import hu.bme.mit.gamma.statechart.statechart.TimeoutDeclaration
import hu.bme.mit.gamma.expression.model.VariableDeclaration

public class EnvironmentGlueCodeGenerator extends GlueCodeGenerator {
	
	
	def generateWrappedComponentName(AsynchronousAdapter wrapper) {
		return wrapper.wrappedComponent.name.toFirstLower+"Statechart"
	}
	
	EnvironmentSynchronousCompositeComponentCodeGenerator environmentSynchronousCompositeComponentCodeGenerator;
	EnvironmentAsynchronousCompositeComponentCodeGenerator environmentAsynchronousCompositeComponentCodeGenerator;
	EnvironmentAsynchronousAdapterCodeGenerator environmentAsynchronousAdapterCodeGenerator;
	EnvironmentVirtualTimerServiceCodeGenerator environmentVirtualTimerServiceCodeGenerator;
	EnvironmentTimedObjectInterfaceGenerator environmentTimedObjectInterfaceGenerator;
	EnvironmentSchedulingInterfaceGenerator environmentSchedulingInterfaceGenerator;
	
	var URIs=<String>newArrayList()
	
	new(ResourceSet resourceSet, String basePackageName, String srcGenFolderUri) {
		super(resourceSet, basePackageName, srcGenFolderUri)
		val trace = new Trace(super.engine)
		this.environmentSchedulingInterfaceGenerator= new EnvironmentSchedulingInterfaceGenerator(super.BASE_PACKAGE_NAME)
		this.environmentSynchronousCompositeComponentCodeGenerator=new EnvironmentSynchronousCompositeComponentCodeGenerator(super.BASE_PACKAGE_NAME, super.YAKINDU_PACKAGE_NAME, trace)
		this.environmentAsynchronousCompositeComponentCodeGenerator=new EnvironmentAsynchronousCompositeComponentCodeGenerator(this.BASE_PACKAGE_NAME, trace)
		this.environmentAsynchronousAdapterCodeGenerator=new EnvironmentAsynchronousAdapterCodeGenerator(this.BASE_PACKAGE_NAME, trace)
		this.environmentVirtualTimerServiceCodeGenerator=new EnvironmentVirtualTimerServiceCodeGenerator(this.BASE_PACKAGE_NAME)
		this.environmentTimedObjectInterfaceGenerator=new EnvironmentTimedObjectInterfaceGenerator(this.BASE_PACKAGE_NAME)
		val envSchGen = new EnvironmentSchedulingInterfaceGenerator(this.BASE_PACKAGE_NAME)
		val envTimedObjGen=new EnvironmentTimedObjectInterfaceGenerator(this.BASE_PACKAGE_NAME)
		val envUtil = ElementaryEnvironmentComponentUtility.INSTANCE
		println("Java Scheduling IF URI: "+BASE_PACKAGE_URI + File.separator + envUtil.schedulingInterfacePackage + File.separator + envUtil.schedulingInterfaceName + ".java")
		//envSchGen.generate.saveCode(BASE_PACKAGE_URI + File.separator + envUtil.schedulingInterfacePackage + File.separator + envUtil.schedulingInterfaceName + ".java")
		//envTimedObjGen.generate.saveCode(BASE_PACKAGE_URI + File.separator + envUtil.schedulingInterfacePackage + "TimedObject.java")
	}
	
	override execute() {
		checkUniqueInterfaceNames
		generateEventClass
		generateTimerClasses
		getTypeDeclarationRule.fireAllCurrent
		getPortInterfaceRule.fireAllCurrent
		generateReflectiveInterfaceRule
		getSimpleComponentReflectionRule.fireAllCurrent
		getSimpleComponentDeclarationRule.fireAllCurrent
		getSynchronousCompositeComponentsRule.fireAllCurrent
		if (hasSynchronousWrapper) {
			generateLinkedBlockingMultiQueueClasses
		}
		getAsynchronousAdapterRule.fireAllCurrent
		if (hasAsynchronousComposite) {
			getChannelsRule.fireAllCurrent
		}
		getAsynchronousCompositeComponentsRule.fireAllCurrent
	}	
	
	protected override getSynchronousCompositeComponentsRule() {
		if (synchronousCompositeComponentsRule === null) {
			 synchronousCompositeComponentsRule = createRule(AbstractSynchronousCompositeComponents.instance).action [
				val compositeSystemUri = BASE_PACKAGE_URI + File.separator + it.synchronousCompositeComponent.containingPackage.name.toLowerCase
				val code = environmentSynchronousCompositeComponentCodeGenerator.createEnvironmentCompositeComponentClass(it.synchronousCompositeComponent)
				code.saveCode(compositeSystemUri + File.separator + it.synchronousCompositeComponent.generateComponentClassName + ".java")
				// Generating the interface that is able to return the Ports
				val interfaceCode = it.synchronousCompositeComponent.generateComponentInterface
				interfaceCode.saveCode(compositeSystemUri + File.separator + it.synchronousCompositeComponent.generatePortOwnerInterfaceName + ".java")
				// Generating the reflective class
				val reflectiveCode = it.synchronousCompositeComponent.generateReflectiveClass
				reflectiveCode.saveCode(compositeSystemUri + File.separator + it.synchronousCompositeComponent.reflectiveClassName + ".java")
			].build		
		}
		return synchronousCompositeComponentsRule
	}
	
	protected def generateTimeoutMap(StatechartDefinition statechart, hu.bme.mit.gamma.statechart.lowlevel.transformation.Trace trace) {
		var timeoutMap=new HashMap<TimeoutDeclaration,VariableDeclaration>;
		for (timeout : statechart.timeoutDeclarations){
			timeoutMap.put(timeout,trace.get(timeout))
		}
		return timeoutMap
	}
	
	
	/**
	* Creates a Java class for each component transformed from Gamma statechart components given in the component model.
	*/
	protected override getSimpleComponentDeclarationRule() {
			
			 simpleComponentsRule = createRule(SimpleGammaComponents.instance).action [
				val componentUri = BASE_PACKAGE_URI + File.separator  + it.statechartDefinition.containingPackage.name.toLowerCase
				//val code = (it.statechartDefinition as StatechartDefinition).createSimpleComponentClass
				//code.saveCode(componentUri + File.separator + it.statechartDefinition.generateComponentClassName + ".java")
				//// Generating the interface for returning the Ports
				//val interfaceCode = it.statechartDefinition.generateComponentInterface
				//interfaceCode.saveCode(componentUri + File.separator + it.statechartDefinition.generatePortOwnerInterfaceName + ".java")
				
				val javaActionSerializer = new CommonizedVariableActionSerializer()
				val gammaStatechart=it.statechartDefinition as StatechartDefinition
				val transformer = new GammaToLowlevelTransformer();
				// Transforming only a single statechart
				val lowlevelPackage = transformer.transformAndWrap(gammaStatechart) as hu.bme.mit.gamma.statechart.lowlevel.model.Package;
				val traces=transformer.traces
				val timeoutMap=generateTimeoutMap(gammaStatechart,traces)
				val lowlevelTransformer = new LowlevelToXstsTransformer(lowlevelPackage, false, TransitionMerging.HIERARCHICAL /* Flat does not work now */);
				val resultModels = lowlevelTransformer.execute as SimpleEntry<XSTS, L2STrace>
				val xSts = resultModels.getKey();
				val codeGenerator = new StatechartToJavaCodeGenerator(super.BASE_FOLDER_URI, super.BASE_PACKAGE_NAME, gammaStatechart, xSts, javaActionSerializer,timeoutMap);
				
				codeGenerator.execute();
				
			].build			
		
		return simpleComponentsRule
	}
	
	protected override getAsynchronousCompositeComponentsRule() {
		if (asynchronousCompositeComponentsRule === null) {
			 asynchronousCompositeComponentsRule = createRule(AsynchronousCompositeComponents.instance).action [
				val compositeSystemUri = BASE_PACKAGE_URI + File.separator + it.asynchronousCompositeComponent.containingPackage.name.toLowerCase
				// Main components
				val code = environmentAsynchronousCompositeComponentCodeGenerator.createEnvironmentAsynchronousCompositeComponentClass(it.asynchronousCompositeComponent,0,0)
				code.saveCode(compositeSystemUri + File.separator + it.asynchronousCompositeComponent.generateComponentClassName + ".java")
				val interfaceCode = it.asynchronousCompositeComponent.generateComponentInterface
				interfaceCode.saveCode(compositeSystemUri + File.separator + it.asynchronousCompositeComponent.generatePortOwnerInterfaceName + ".java")
			].build		
		}
		return asynchronousCompositeComponentsRule
	}
	
	
	protected override getAsynchronousAdapterRule() {
		if (synchronousComponentWrapperRule === null) {
			 synchronousComponentWrapperRule = createRule(SynchronousComponentWrappers.instance).action [
				val compositeSystemUri = BASE_PACKAGE_URI + File.separator + it.synchronousComponentWrapper.containingPackage.name.toLowerCase
				val code = environmentAsynchronousAdapterCodeGenerator.createAsynchronousAdapterClass(it.synchronousComponentWrapper)
				code.saveCode(compositeSystemUri + File.separator + it.synchronousComponentWrapper.generateComponentClassName + ".java")
				val interfaceCode = it.synchronousComponentWrapper.generateComponentInterface
				interfaceCode.saveCode(compositeSystemUri + File.separator + it.synchronousComponentWrapper.generatePortOwnerInterfaceName + ".java")
				// Generating the reflective class
				val reflectiveCode = it.synchronousComponentWrapper.generateReflectiveClass
				reflectiveCode.saveCode(compositeSystemUri + File.separator + it.synchronousComponentWrapper.reflectiveClassName + ".java")
			].build		
		}
		return synchronousComponentWrapperRule
	}
	
		/**
	 * Creates and saves the message class that is responsible for informing the statecharts about the event that has to be raised (with the given value).
	 */
	protected override generateTimerClasses() {
		val timedObjectClassCode = environmentTimedObjectInterfaceGenerator.generate
		timedObjectClassCode.saveCode(BASE_PACKAGE_URI + File.separator + "TimedObject.java")
		val schedulingInterfaceClassCode = environmentSchedulingInterfaceGenerator.generate
		schedulingInterfaceClassCode.saveCode(BASE_PACKAGE_URI + File.separator +"scheduling"+File.separator+ "ElementaryComponentSchedulingInterface.java")
		val virtualTimerClassCode = environmentVirtualTimerServiceCodeGenerator.createVirtualTimerClassCode
		virtualTimerClassCode.saveCode(BASE_PACKAGE_URI + File.separator + environmentVirtualTimerServiceCodeGenerator.className + ".java")
		val timerInterfaceCode = createITimerInterfaceCode
		timerInterfaceCode.saveCode(BASE_PACKAGE_URI + File.separator + timerInterfaceGenerator.yakinduInterfaceName + ".java")
		val timerCallbackInterface = createITimerCallbackInterfaceCode
		timerCallbackInterface.saveCode(BASE_PACKAGE_URI + File.separator + timerCallbackInterfaceGenerator.interfaceName + ".java")
		val timerServiceClass = createTimerServiceClassCode
		timerServiceClass.saveCode(BASE_PACKAGE_URI + File.separator + timerServiceCodeGenerator.yakinduClassName + ".java")
		val gammaTimerInterface = createGammaTimerInterfaceCode
		gammaTimerInterface.saveCode(BASE_PACKAGE_URI + File.separator + timerInterfaceGenerator.gammaInterfaceName + ".java")
		val gammaTimerClass = createGammaTimerClassCode
		gammaTimerClass.saveCode(BASE_PACKAGE_URI + File.separator + timerServiceCodeGenerator.gammaClassName + ".java")
		val unifiedTimerInterface = createUnifiedTimerInterfaceCode
		unifiedTimerInterface.saveCode(BASE_PACKAGE_URI + File.separator + timerInterfaceGenerator.unifiedInterfaceName + ".java")
		val unifiedTimerClass = createUnifiedTimerClassCode
		unifiedTimerClass.saveCode(BASE_PACKAGE_URI + File.separator + timerServiceCodeGenerator.unifiedClassName + ".java")
	}
	
	public def getBasePackageURI(){
		return BASE_PACKAGE_URI
	}
	
	protected override saveCode(CharSequence code, String uri) {
		URIs.add(uri)
		super.saveCode(code, uri)
	}
	
	public def getURIs(){
		return URIs
	}
	
	
}