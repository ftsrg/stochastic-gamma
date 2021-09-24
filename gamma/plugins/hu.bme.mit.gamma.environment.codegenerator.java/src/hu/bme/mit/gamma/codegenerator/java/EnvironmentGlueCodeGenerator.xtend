package hu.bme.mit.gamma.codegenerator.java

import hu.bme.mit.gamma.codegenerator.java.queries.AbstractSynchronousCompositeComponents
import java.io.File
import java.io.FileWriter
import org.eclipse.emf.ecore.resource.ResourceSet

import static extension hu.bme.mit.gamma.codegenerator.java.util.Namings.*
import hu.bme.mit.gamma.statechart.composite.AsynchronousAdapter
import hu.bme.mit.gamma.codegenerator.java.queries.SynchronousComponentWrappers

public class EnvironmentGlueCodeGenerator extends GlueCodeGenerator {
	
	
	def generateWrappedComponentName(AsynchronousAdapter wrapper) {
		return wrapper.wrappedComponent.name.toFirstLower+"Statechart"
	}
	
	EnvironmentCompositeComponentCodeGenerator environmentCompositeComponentCodeGenerator;
	
	var URIs=<String>newArrayList()
	
	new(ResourceSet resourceSet, String basePackageName, String srcGenFolderUri) {
		super(resourceSet, basePackageName, srcGenFolderUri)
		val trace = new Trace(super.engine)
		this.environmentCompositeComponentCodeGenerator=new EnvironmentCompositeComponentCodeGenerator(super.BASE_PACKAGE_NAME, super.YAKINDU_PACKAGE_NAME, trace)
	}
	
		protected override getSynchronousCompositeComponentsRule() {
		if (synchronousCompositeComponentsRule === null) {
			 synchronousCompositeComponentsRule = createRule(AbstractSynchronousCompositeComponents.instance).action [
				val compositeSystemUri = BASE_PACKAGE_URI + File.separator + it.synchronousCompositeComponent.containingPackage.name.toLowerCase
				val code = environmentCompositeComponentCodeGenerator.createEnvironmentCompositeComponentClass(it.synchronousCompositeComponent)
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
	
	
	protected override getAsynchronousAdapterRule() {
		if (synchronousComponentWrapperRule === null) {
			 synchronousComponentWrapperRule = createRule(SynchronousComponentWrappers.instance).action [
				val compositeSystemUri = BASE_PACKAGE_URI + File.separator + it.synchronousComponentWrapper.containingPackage.name.toLowerCase
				val code = it.synchronousComponentWrapper.createAsynchronousAdapterClass
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