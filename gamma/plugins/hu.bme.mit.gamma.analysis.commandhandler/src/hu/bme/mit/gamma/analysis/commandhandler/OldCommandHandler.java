package hu.bme.mit.gamma.analysis.commandhandler;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;

import hu.bme.mit.gamma.analysis.transformation.AnalysisTransformation;
import hu.bme.mit.gamma.analysis.transformation.GeneratePyroScript;
import hu.bme.mit.gamma.codegenerator.java.GlueCodeGenerator;
import hu.bme.mit.gamma.dialog.DialogUtil;
import hu.bme.mit.gamma.genmodel.model.AnalysisModelTransformation;
import hu.bme.mit.gamma.genmodel.model.CodeGeneration;
import hu.bme.mit.gamma.genmodel.model.EventPriorityTransformation;
import hu.bme.mit.gamma.genmodel.model.GenModel;
import hu.bme.mit.gamma.genmodel.model.StatechartCompilation;
import hu.bme.mit.gamma.genmodel.model.Task;
import hu.bme.mit.gamma.genmodel.model.TestGeneration;
import hu.bme.mit.gamma.genmodel.model.YakinduCompilation;
import hu.bme.mit.gamma.statechart.lowlevel.model.Package;
import hu.bme.mit.gamma.statechart.lowlevel.model.StatechartDefinition;
import hu.bme.mit.gamma.statechart.lowlevel.model.Component;
import hu.bme.mit.gamma.ui.GammaApi;
import hu.bme.mit.gamma.yakindu.transformation.traceability.Y2GTrace;


public class OldCommandHandler<InterfaceCompilationHandler> extends AbstractHandler{
	
	protected Logger logger = Logger.getLogger("GammaLogger");
	private final String folderName = "src-gen";	

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		logger.log(Level.INFO ,"Transformation to Python");
		List<String> sources=null;
		ISelection sel = HandlerUtil.getActiveMenuSelection(event);
		try {
			if (sel instanceof IStructuredSelection) {
				IStructuredSelection selection = (IStructuredSelection) sel;
				if (selection.size() == 1) {
					if (selection.getFirstElement() instanceof IFile) {
						IFile file = (IFile) selection.getFirstElement();
						ResourceSet resSet = new ResourceSetImpl();
						logger.log(Level.INFO, "Resource set for Java code generation created: " + resSet);
						URI compositeSystemURI = URI.createPlatformResourceURI(file.getFullPath().toString(), true);
						// Loading the composite system to the resource set
						Resource resource = loadResource(resSet, compositeSystemURI);
						Package compositeSystem = (Package) resource.getContents().get(0);
						// Checking whether all the statecharts have unique names
						checkStatechartNameUniqueness(file.getProject(), new HashSet<String>());
						// Getting the simple statechart names
						Collection<String> simpleStatechartFileNames = getSimpleStatechartFileNames(compositeSystem);
						// Very important step: recursively attaining the Yakindu-Gamma traces from the project folders based on the imported Statecharts of the composite system
						List<URI> uriList = new ArrayList<URI>();
						obtainTraceURIs(file.getProject(), simpleStatechartFileNames, uriList);
						if (simpleStatechartFileNames.size() != uriList.size()) {
							logger.log(Level.INFO, "Some trace model is not found: " +
									simpleStatechartFileNames + System.lineSeparator() + uriList + System.lineSeparator() +
									"Wrapper is not generated for the Gamma statecharts without trace.");
						}
						for (URI uri : uriList) {
							loadResource(resSet, uri);
						}
						
						
						// Setting the URI so the composite system code will be generated into a separate package
						String parentFolder = file.getProject().getLocation() + "/" + folderName;
						// Decoding so spaces do not stir trouble
						parentFolder = URI.decode(parentFolder);
						logger.log(Level.INFO, "Resource set content for Java code generation: " + resSet);
						String packageName = file.getProject().getName().toLowerCase();
						GlueCodeGenerator generator = new GlueCodeGenerator(resSet, packageName, parentFolder);
						generator.execute();
						
						//sources=generator.getURIs(); //TODO do something or do not use
						
						generator.dispose();
						
						
						logger.log(Level.INFO, "The Java code generation has been finished.");
						
					}
				}
			}
		} catch (Exception exception) {
			exception.printStackTrace();
			logger.log(Level.SEVERE, exception.getMessage());
			DialogUtil.showErrorWithStackTrace(exception.getMessage(), exception);
		}
		
		try {
			
			ISelection sel2 = HandlerUtil.getActiveMenuSelection(event);
			if (sel instanceof IStructuredSelection) {
				IStructuredSelection selection = (IStructuredSelection) sel2;
				if (selection.size() == 1) {
					if (selection.getFirstElement() instanceof IFile) {
						IFile firstElement = (IFile) selection.getFirstElement();
						IContainer parentFolder = firstElement.getParent();
						ResourceSet resSet = new ResourceSetImpl();
						URI compositeSystemURI = URI.createPlatformResourceURI(firstElement.getFullPath().toString(), true);
						Resource resource = resSet.getResource(compositeSystemURI, true);
						AnalysisTransformation analysis=new AnalysisTransformation();
 
						java.net.URI loc=firstElement.getLocationURI();

						String packageName = firstElement.getProject().getName().toLowerCase();
						analysis.transform(resource,loc,packageName,sources,"");						
					} 
					logger.log(Level.INFO, "The Python code generation has been finished.");
				}
			}
		} catch (Exception e) {
				e.printStackTrace();
				logger.log(Level.SEVERE, "The Python code generation encountered an exception.");
				logger.log(Level.SEVERE, e.getCause().toString());
				logger.log(Level.SEVERE, e.getStackTrace().toString());
				logger.log(Level.SEVERE, e.getMessage());
		}
		
		
		System.out.println("Analysis has been finnished");
		return null;
		 
	}

	

	/**
	 * Checks whether all statecharts have unique names.
	 */
	private void checkStatechartNameUniqueness(IContainer container, Set<String> fileNames) throws CoreException {
		for (IResource iResource : container.members()) {
			if (iResource instanceof IFile) {
				IFile file = (IFile) iResource;
				String fileName = file.getName();
				// It is faster to check only Gamma statecharts: gsm and gcd file extensions
				if (fileName.endsWith(".gsm") || fileName.endsWith(".gcd") ) {
					if (fileNames.contains(fileName)) {
						throw new IllegalArgumentException("Multiple statechart files with the same name: " + fileName
							+ ". Please use different names for the statecharts!");
					}
					fileNames.add(fileName);
				}
			}
			else if (iResource instanceof IContainer) {
				checkStatechartNameUniqueness((IContainer) iResource, fileNames);
			}
		}
	}
	
	/**
	 * Returns the names of the imported statecharts recursively starting from the given project.
	 */
	private Collection<String> getSimpleStatechartFileNames(Package gammaPackage) {
		Set<String> simpleStatechartNameList = new HashSet<String>();
		for (Package importedPackage : gammaPackage.getImports()) {
			if (hasOnlyStatecharts(importedPackage)) {
				// Adding the name of the file of the statechart to the list
				String packageFileName = getPackageFileName(importedPackage);
				simpleStatechartNameList.add(packageFileName);
			}
			else {				
				// Recursively doing this with referred composite systems
				final Collection<String> names = getSimpleStatechartFileNames(importedPackage);
				simpleStatechartNameList.addAll(names);
			}
		}
		return simpleStatechartNameList;
	}
	
	private boolean hasOnlyStatecharts(Package gammaPackage) {
		// Only statecharts (theoretically single statechart) are contained
		Collection<Component> components = gammaPackage.getComponents();
		return !components.isEmpty() && components.stream().allMatch(it -> it instanceof StatechartDefinition);
	}
	
	private String getPackageFileName(Package _package) {
		URI uri = _package.eResource().getURI();
		String platformString = uri.toPlatformString(true);
		// /hu.bme.mit.gamma.tutorial.extra/model/Monitor/Monitor.gcd
		String packageFileName = platformString.substring(platformString.lastIndexOf("/") + 1);
		// Monitor.gcd -> ["Monitor", "gcd"]
		String[] splittedPackageFileName = packageFileName.split("\\.");
		return splittedPackageFileName[0];
	}
	
	/**
	 * Puts the URIs of the Yakindu-Gamma trace files into the URIList if the trace file has a name contained in importList.
	 */
	private void obtainTraceURIs(IContainer container, Collection<String> importList, List<URI> URIList) throws CoreException {
		for (IResource iResource : container.members()) {
			if (iResource instanceof IFile) {
				IFile file = (IFile) iResource;
				String[] fileName = file.getName().split("\\.");
				// Starts with index 1, because the traces are hidden files so their names start with a '.'
				if (fileName.length >= 3 && importList.contains(fileName[1]) && fileName[2].equals("y2g")) {
					URIList.add(URI.createPlatformResourceURI(file.getFullPath().toString(), true));
				}
			}
			else if (iResource instanceof IContainer) {
				obtainTraceURIs((IContainer) iResource, importList, URIList);
			}
		}
	}

	private Resource loadResource(ResourceSet resSet, URI uri) throws IllegalArgumentException {
		Resource resource = resSet.getResource(uri, true);
		EObject object = resource.getContents().get(0);
		if (!(object instanceof Package || object instanceof Y2GTrace)) {
			throw new IllegalArgumentException("There can be only Packages and Traces in the selection: " + resource.getContents().get(0));
		}
		return resource;
	}
}
