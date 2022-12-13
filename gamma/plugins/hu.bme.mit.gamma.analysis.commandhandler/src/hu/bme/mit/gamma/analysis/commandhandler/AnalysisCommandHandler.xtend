package hu.bme.mit.gamma.analysis.commandhandler

import hu.bme.mit.gamma.analysis.transformation.AnalysisTransformation
import hu.bme.mit.gamma.codegeneration.java.EnvironmentGlueCodeGenerator
import hu.bme.mit.gamma.codegeneration.java.commandhandler.CommandHandler
import hu.bme.mit.gamma.dialog.DialogUtil
import hu.bme.mit.gamma.statechart.interface_.Package
import java.util.ArrayList
import java.util.HashSet
import java.util.logging.Level
import org.eclipse.core.commands.ExecutionEvent
import org.eclipse.core.commands.ExecutionException
import org.eclipse.core.resources.IFile
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl
import org.eclipse.jface.viewers.IStructuredSelection
import org.eclipse.ui.handlers.HandlerUtil

class AnalysisCommandHandler extends CommandHandler {
	
	Object sources
	
	var uris=<String>newArrayList
	
	var BasePackageURI=""
	
	override Object execute(ExecutionEvent event) throws ExecutionException{
		var t0=System.currentTimeMillis()
		var sel = HandlerUtil.getActiveMenuSelection(event);
		println("Run analysis...--------------------------------------------")
		try {
			if (sel instanceof IStructuredSelection) {
				var selection = sel as IStructuredSelection
				if (selection.size() == 1) {
					if (selection.getFirstElement() instanceof IFile) {
						var file = selection.getFirstElement() as IFile
						var resSet = new ResourceSetImpl();
						logger.log(Level.INFO, "Resource set for Java code generation created: " + resSet);
						println( "Resource set for Java code generation created: " + resSet);
						var compositeSystemURI = URI.createPlatformResourceURI(file.getFullPath().toString(), true);
						// Loading the composite system to the resource set
						var resource = loadResource(resSet, compositeSystemURI);
						var compositeSystem = resource.getContents().get(0) as Package
						// Checking whether all the statecharts have unique names
						checkStatechartNameUniqueness(file.getProject(), new HashSet<String>());
						// Getting the simple statechart names
						var simpleStatechartFileNames = getSimpleStatechartFileNames(compositeSystem);
						// Very important step: recursively attaining the Yakindu-Gamma traces from the project folders based on the imported Statecharts of the composite system
						var uriList = new ArrayList<URI>();
						obtainTraceURIs(file.getProject(), simpleStatechartFileNames, uriList);
						if (simpleStatechartFileNames.size() != uriList.size()) {
							logger.log(Level.INFO, "Some trace model is not found: " +
									simpleStatechartFileNames + System.lineSeparator() + uriList + System.lineSeparator() +
									"Wrapper is not generated for the Gamma statecharts without trace.");
							println( "Some trace model is not found: " +
									simpleStatechartFileNames + System.lineSeparator() + uriList + System.lineSeparator() +
									"Wrapper is not generated for the Gamma statecharts without trace.");
						}
						for (URI uri : uriList) {
							loadResource(resSet, uri);
						}
						// Setting the URI so the composite system code will be generated into a separate package
						var parentFolder = file.getProject().getLocation() + "/" + folderName;
						// Decoding so spaces do not stir trouble
						parentFolder = URI.decode(parentFolder);
						logger.log(Level.INFO, "Resource set content for Java code generation: " + resSet);
						println( "Resource set content for Java code generation: " + resSet);
						var packageName = file.getProject().getName().toLowerCase();
						var generator = new EnvironmentGlueCodeGenerator(resSet, packageName, parentFolder);
						generator.execute();
						uris=generator.getURIs
						BasePackageURI=file.getProject().getLocation().toString
						generator.dispose();
						logger.log(Level.INFO, "The Java code generation has been finished.");
						println("The Java code generation has been finished.------------------------------------");
					}
				}
			}
		} catch (Exception exception) {
			exception.printStackTrace();
			logger.log(Level.SEVERE, exception.getMessage());
			println(exception.getMessage());
			DialogUtil.showErrorWithStackTrace(exception.getMessage(), exception);
		}
		try {
			if (sel instanceof IStructuredSelection) {
				val selection = sel as IStructuredSelection
				if (selection.size() == 1) {
					if (selection.getFirstElement() instanceof IFile) {
						val firstElement = selection.getFirstElement() as IFile
						val resSet = new ResourceSetImpl();
						val compositeSystemURI = URI.createPlatformResourceURI(firstElement.getFullPath().toString(), true);
						val resource = resSet.getResource(compositeSystemURI, true);
						val analysis=new AnalysisTransformation();
 
						val loc=firstElement.getLocationURI();
						
						val packageName = firstElement.getProject().getName().toLowerCase();
	
						
						analysis.transformandrun(resource,loc,packageName,uris,BasePackageURI);						
					} 
					logger.log(Level.INFO, "The Python code generation has been finished.----------------");
					println( "The Python code generation has been finished.");
				}
			}
		} catch (Exception e) {
				e.printStackTrace();
				logger.log(Level.SEVERE, "The Python code generation encountered an exception.-----------");
				println("The Python code generation encountered an exception.");
				logger.log(Level.SEVERE, e.getCause().toString());
				println(e.getCause().toString());
				logger.log(Level.SEVERE, e.getStackTrace().toString());
				logger.log(Level.SEVERE, e.getMessage());
		}
		
		
		var t1=System.currentTimeMillis()
		println("Analysis has been finnished--------------------------------------------------------------");
		print("Elapsed time: ")
		println((t1-t0)/1000.0)
		return null;
	}
	
}