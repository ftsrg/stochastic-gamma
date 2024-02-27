package hu.bme.mit.gamma.environment.ui.wizards;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.core.runtime.*;
import org.eclipse.jface.operation.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.core.resources.*;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import java.io.*;
import org.eclipse.ui.*;
import org.eclipse.ui.ide.IDE;

import com.google.inject.Injector;

import hu.bme.mit.gamma.architecture.transformation.enterprisearchitect.EnterpriseArchitectTransformation;
import hu.bme.mit.gamma.architecture.transformation.enterprisearchitect.GammaAppender;
import hu.bme.mit.gamma.architecture.transformation.enterprisearchitect.SysMLTransformations;
import hu.bme.mit.gamma.architecture.transformation.errors.ArchitectureException;
import hu.bme.mit.gamma.architecture.transformation.errors.GammaTransformationException;
import hu.bme.mit.gamma.architecture.transformation.traceability.ArchitectureTrace;
import hu.bme.mit.gamma.dialog.DialogUtil;
import hu.bme.mit.gamma.statechart.interface_.Package;
import hu.bme.mit.gamma.environment.language.ui.internal.LanguageActivator;
import hu.bme.mit.gamma.environment.language.ui.serializer.EnvironmentLanguageSerializer;
import hu.bme.mit.gamma.ui.taskhandler.TaskHandler.ModelSerializer;
import java.nio.file.Paths;
import java.nio.file.Files;
//import hu.bme.mit.gamma.language.util.serialization.GammaLanguageSerializer;


/**
 * This is a sample new wizard. Its role is to create a new file resource in the
 * provided container. If the container resource (a folder or a project) is
 * selected in the workspace when the wizard is opened, it will accept it as the
 * target container. The wizard creates one file with the extension "gcd". If a
 * sample multi-page editor (also available as a template) is registered for the
 * same extension, it will be able to open it.
 */

public class SysMLImportWizard extends Wizard implements INewWizard {
	private SysMLImportWizardPage page;

	protected Logger logger = Logger.getLogger("GammaLogger");
	private ISelection selection;
	protected String containerName;
	protected String sysGUID;
	protected String funcGUID;

	protected final ModelSerializer serializer = ModelSerializer.INSTANCE;

	final int MAXTRY = 10;
	/**
	 * Constructor for SysMLImportWizard.
	 */
	public SysMLImportWizard() {
		super();
		setNeedsProgressMonitor(true);
	}

	/**
	 * Adding the page to the wizard.
	 */
	@Override
	public void addPages() {
		page = new SysMLImportWizardPage(selection);
		addPage(page);
	}

	/**
	 * This method is called when 'Finish' button is pressed in the wizard. We will
	 * create an operation and run it using wizard as execution context.
	 */
	@Override
	public boolean performFinish() {
		containerName = page.getContainerName();
		sysGUID = page.getSystemPackageGUID();
		funcGUID = page.getFunctionalPackageGUID();

		// final String fileName = page.getFileName();
		IRunnableWithProgress op = monitor -> {
			EnterpriseArchitectTransformation transformation = null;
			try {
				// doFinish(containerName, fileName, monitor);
				monitor.beginTask("Loading SysML models from Enterprise Architect ", 8);
				monitor.worked(1);
				transformation = new EnterpriseArchitectTransformation(funcGUID, sysGUID);
				monitor.worked(1);
				monitor.setTaskName("Transforming SysML structural and interface models to Gamma ");
				monitor.worked(1);
				var eaTrace = transformation.execute2();
				monitor.worked(1);
				ArchitectureTrace gammaTrace = SysMLTransformations.transformArchitecture(eaTrace);
				monitor.worked(1);
				monitor.setTaskName("Transforming SysML statemachines models to Gamma ");
				monitor.worked(1);
				var appender=new GammaAppender(gammaTrace, eaTrace);
				monitor.worked(1);
				appender.execute();
				monitor.worked(1);
				serialize(gammaTrace, monitor);
			} catch (Exception e) {
				if (e instanceof ArchitectureException) {
					var e1 = (ArchitectureException) e;
					if (e1.element != null && transformation != null) {
						// transformation.showElement(e1.element);
					}
				}
				System.out.println(e.getCause());
				e.printStackTrace();
				throw new InvocationTargetException(e);

			} finally {
				monitor.done();
			}
		};

		try {
			getContainer().run(true, false, op);
		} catch (InterruptedException e) {
			return false;
		} catch (InvocationTargetException e) {
			Throwable realException = e.getTargetException();
			if (realException instanceof ArchitectureException) {
				DialogUtil.showErrorWithStackTrace("ArchitectureException occured at ("
						+ ((ArchitectureException) realException).element.getName() + ") " + e.getMessage(),
						realException);
				// MessageDialog.openError(getShell(), "Error", realException.getMessage());
				e.printStackTrace();
				return false;
			} else if (realException instanceof GammaTransformationException) {
				DialogUtil.showErrorWithStackTrace("GammaTransformationException  occured at ("
						+ ((GammaTransformationException) realException).element.getName() + ") " + e.getMessage(),
						realException);
				// MessageDialog.openError(getShell(), "Error", realException.getMessage());
				e.printStackTrace();
				return false;
			} else {
				DialogUtil.showErrorWithStackTrace("Exception occured: " + e.getMessage(), realException);
				// MessageDialog.openError(getShell(), "Error", realException.getMessage());
				e.printStackTrace();
				return false;
			}
		}

		return true;
	}

	/**
	 * The worker method. It will find the container, create the file if missing or
	 * just replace its contents, and open the editor on the newly created file.
	 */

	private void doFinish(String containerName, String fileName, IProgressMonitor monitor) throws CoreException {
		// create a sample file
		monitor.beginTask("Creating " + fileName, 2);
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		IResource resource = root.findMember(IPath.fromOSString(containerName));
		if (!resource.exists() || !(resource instanceof IContainer)) {
			throw new CoreException(Status.error("Container \"" + containerName + "\" does not exist."));
		}
		IContainer container = (IContainer) resource;
		final IFile file = container.getFile(IPath.fromOSString(fileName));

		monitor.worked(1);
		monitor.setTaskName("Opening file for editing...");
		getShell().getDisplay().asyncExec(() -> {
			IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
			try {
				IDE.openEditor(page, file, true);
			} catch (PartInitException e) {
			}
		});
		monitor.worked(1);
	}

	/**
	 * We will accept the selection in the workbench to see if we can initialize
	 * from it.
	 * 
	 * @see IWorkbenchWizard#init(IWorkbench, IStructuredSelection)
	 */
	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		this.selection = selection;
	}
	
	protected void serialize(Set<Package> packages,String appendix,ArchitectureTrace gammaTrace, IProgressMonitor monitor) throws IOException, CoreException{
		int len=packages.size();
		for (int i = 1; i <= MAXTRY; i++) {
			
			try {
				for (Package pkg :packages) {
					logger.log(Level.INFO,
							"Saving " + pkg.getName() + " into " + containerName + gammaTrace.getPackagePath(pkg));
					serialize(pkg, containerName + gammaTrace.getPackagePath(pkg), pkg.getName() + appendix);
				}
				break;
			} catch (RuntimeException e) {
				if (e.getMessage()!=null && e.getMessage().contains("No EObjectDescription could be found in Scope") && (i < MAXTRY)) {
					e.printStackTrace();
				} else {
					throw e;
				}
				sleep(1000);
				refresh();
				build();
				sleep(1000);
			}
		}
		monitor.worked(len);
	}

	protected void serialize(ArchitectureTrace gammaTrace, IProgressMonitor monitor) throws IOException, CoreException {
		build();
		monitor.beginTask("Saving models ", gammaTrace.getPackages().size());
		serialize(gammaTrace.getInterfacePackage(), containerName, gammaTrace.getInterfacePackage().getName() + ".gcd");
		serialize( gammaTrace.getPrimitiveFunctionPackages(),".gcd",gammaTrace, monitor);
		serialize( gammaTrace.getInterfaceComponentPackages(),".gcd",gammaTrace, monitor);
		serialize( gammaTrace.getComponentFunctionPackages(),".gcd",gammaTrace, monitor);
		serialize( gammaTrace.getCommunicationComponentPackages(),".gcd",gammaTrace, monitor);
		serialize( gammaTrace.getSubsystemHardwarePackages(),".sgcd",gammaTrace, monitor);
		serialize( gammaTrace.getSubsystemPackages(),".sgcd",gammaTrace, monitor);
		var syspkgs=gammaTrace.getSystemPackages();

		serialize(gammaTrace.getSystemPackages(),".sgcd",gammaTrace, monitor);

		return;

	}

	private void serialize(EObject rootElem, String parentFolder, String fileName) throws IOException {
		// This is how an injected object can be retrieved
		Injector injector = LanguageActivator.getInstance()
				.getInjector(LanguageActivator.HU_BME_MIT_GAMMA_ENVIRONMENT_LANGUAGE_ENVIRONMENTLANGUAGE);
		EnvironmentLanguageSerializer serializer = injector.getInstance(EnvironmentLanguageSerializer.class);
		serializer.serialize(rootElem, parentFolder, fileName);
	}

	protected void refresh() throws CoreException {
		logger.log(Level.INFO, "Refresh project");
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		IResource resource = root.findMember(IPath.fromOSString(containerName));

		resource.getProject().refreshLocal(IResource.DEPTH_INFINITE, new NullProgressMonitor());
	}

	protected void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	protected void build() throws CoreException {
		logger.log(Level.INFO, "Build project");
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		IResource resource = root.findMember(IPath.fromOSString(containerName));
		resource.getProject().build(IncrementalProjectBuilder.CLEAN_BUILD, new NullProgressMonitor());
		resource.getProject().refreshLocal(IResource.DEPTH_INFINITE, new NullProgressMonitor());
		// resource.getProject().build(IResource.DEPTH_INFINITE, new
		// NullProgressMonitor());
	}

}