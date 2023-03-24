package hu.bme.mit.gamma.environment.analysis.commandhandler;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.ICoreRunnable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.e4.ui.di.UISynchronize;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

import hu.bme.mit.gamma.codegeneration.java.EnvironmentGlueCodeGenerator;
import hu.bme.mit.gamma.codegeneration.java.commandhandler.CommandHandler;
import hu.bme.mit.gamma.dialog.DialogUtil;
import hu.bme.mit.gamma.environment.analysis.AnalysisComponent;
import hu.bme.mit.gamma.environment.analysis.transformation.AnalysisTransformation;
import hu.bme.mit.gamma.statechart.interface_.Component;
import hu.bme.mit.gamma.statechart.interface_.Package;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;

public class AnalysisTransformationHandler extends CommandHandler {
	@Inject
	UISynchronize sync;

	protected Logger logger = Logger.getLogger("GammaLogger");
	List<String> uris;
	String BasePackageURI;


	
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException{
		var t0 = System.currentTimeMillis();
		var sel = HandlerUtil.getActiveMenuSelection(event);
		logger.log(Level.INFO,"Run analysis...--------------------------------------------");

		if (sel instanceof IStructuredSelection) {
			if (((IStructuredSelection) sel).size() == 1) {
				if (((IStructuredSelection) sel).getFirstElement() instanceof IFile) {
					IFile file = (IFile) ((IStructuredSelection) sel).getFirstElement();
					start(file);
				}
			}
		}

		var t1 = System.currentTimeMillis();
		logger.log(Level.INFO,"Analysis has been finnished--------------------------------------------------------------");
		logger.log(Level.INFO,"Elapsed time: ");
		logger.log(Level.INFO,Double.toString((t1 - t0) / 1000.0));
		logger.log(Level.INFO," s");
		return null;
	}
	
	public void start(IFile file) {

		try {
			var resSet = new ResourceSetImpl();
			logger.log(Level.INFO, "Resource set for Java code generation created: " + resSet);
			var compositeSystemURI = URI.createPlatformResourceURI(file.getFullPath().toString(), true);
			// Loading the composite system to the resource set
			var resource = loadResource(resSet, compositeSystemURI);
			var analysisSystem = (Package) resource.getContents().get(0);
			AnalysisComponent analysisComponent=null;
			for (Component comp : analysisSystem.getComponents()) {
				if (comp instanceof AnalysisComponent) {
					analysisComponent=(AnalysisComponent) comp;
				}
			}
			if (analysisComponent==null) {
				DialogUtil.showError("No AnalysisComponent has been found in the selected .sgcl file.");
				return;
			}
			var compositeSystem = (Package) analysisComponent.getAnalyzedComponent().getType().eContainer();
			// Checking whether all the statecharts have unique names
			checkStatechartNameUniqueness(file.getProject(), new HashSet<String>());
			// Getting the simple statechart names
			var simpleStatechartFileNames = getSimpleStatechartFileNames(compositeSystem);
			// Very important step: recursively attaining the Yakindu-Gamma traces from the
			// project folders based on the imported Statecharts of the composite system
			var uriList = new ArrayList<URI>();
			obtainTraceURIs(file.getProject(), simpleStatechartFileNames, uriList);
			if (simpleStatechartFileNames.size() != uriList.size()) {
				logger.log(Level.INFO,
						"Some trace model is not found: " + simpleStatechartFileNames + System.lineSeparator()
								+ uriList + System.lineSeparator()
								+ "Wrapper is not generated for the Gamma statecharts without trace.");
			}
			for (URI uri : uriList) {
				loadResource(resSet, uri);
			}
			// Setting the URI so the composite system code will be generated into a
			// separate package
			String parentFolder = file.getProject().getLocation() + "/" + folderName;
			// Decoding so spaces do not stir trouble
			parentFolder = URI.decode(parentFolder);
			logger.log(Level.INFO, "Resource set content for Java code generation: " + resSet);
			String packageName = file.getProject().getName().toLowerCase();
			EnvironmentGlueCodeGenerator generator = new EnvironmentGlueCodeGenerator(resSet, packageName, parentFolder);
			generator.execute();
			uris = generator.getURIs();
			BasePackageURI = file.getProject().getLocation().toString();
			logger.log(Level.INFO, "Base package URI: "+BasePackageURI);
			generator.dispose();
			logger.log(Level.INFO, "The Java code generation has been finished.");
		} catch (IllegalArgumentException e) {
			logger.log(Level.SEVERE, e.getMessage());
			e.printStackTrace();
			DialogUtil.showErrorWithStackTrace(e.getMessage(), e);
			return;	
		} catch (Exception exception) {
			exception.printStackTrace();
			logger.log(Level.SEVERE, exception.getMessage());
			DialogUtil.showErrorWithStackTrace(exception.getMessage(), exception);
			return;	
		}
		
		try {
			ResourceSetImpl resSet = new ResourceSetImpl();
			URI compositeSystemURI = URI.createPlatformResourceURI(file.getFullPath().toString(), true);
			Resource resource = resSet.getResource(compositeSystemURI, true);
			AnalysisTransformation analysis = new AnalysisTransformation();
			java.net.URI loc = file.getLocationURI();
			String packageName = file.getProject().getName().toLowerCase();
			analysis.transformandrun(resource, loc, packageName, uris, BasePackageURI);

			logger.log(Level.INFO, "The Python code generation has been finished.----------------");

		} catch (Exception e) {
			DialogUtil.showErrorWithStackTrace(e.getMessage(), e);
			e.printStackTrace();
			logger.log(Level.SEVERE, "The Python code generation encountered an exception.-----------");
			logger.log(Level.SEVERE, e.getCause().toString());
			logger.log(Level.SEVERE, e.getStackTrace().toString());
			logger.log(Level.SEVERE, e.getMessage());
			return;
		}
		DialogUtil.showInfo("Analysis Transformation is succesfull!");
		/*
		Job job = Job.create("Transform Stochastic Gamma Models to probabilistic program", (ICoreRunnable) monitor -> {
			sync.asyncExec(() -> {
				
			});
		});*/

		// Start the Job
		//job.schedule();
	}

}
