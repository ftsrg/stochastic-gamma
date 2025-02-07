/********************************************************************************
 * Copyright (c) 2018-2024 Contributors to the Gamma project
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * SPDX-License-Identifier: EPL-1.0
 ********************************************************************************/
package hu.bme.mit.gamma.environment.ui.wizards;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.widgets.MessageBox;
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
import org.eclipse.jface.dialogs.PopupDialog;
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

import hu.bme.mit.gamma.architecture.model.ArchitectureElement;
import hu.bme.mit.gamma.architecture.transformation.enterprisearchitect.EnterpriseArchitectTransformation;
import hu.bme.mit.gamma.architecture.transformation.enterprisearchitect.GammaAppender;
import hu.bme.mit.gamma.architecture.transformation.enterprisearchitect.SysMLTransformations;
import hu.bme.mit.gamma.architecture.transformation.enterprisearchitect.TableAppender;
import hu.bme.mit.gamma.architecture.transformation.enterprisearchitect.traceability.ElementTrace;
import hu.bme.mit.gamma.architecture.transformation.errors.ArchitectureException;
import hu.bme.mit.gamma.architecture.transformation.errors.GammaTransformationException;
import hu.bme.mit.gamma.architecture.transformation.traceability.ArchitectureTrace;
import hu.bme.mit.gamma.dialog.DialogUtil;
import hu.bme.mit.gamma.statechart.interface_.Package;
import hu.bme.mit.gamma.environment.language.ui.internal.LanguageActivator;
import hu.bme.mit.gamma.environment.language.ui.serializer.EnvironmentLanguageSerializer;
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
	protected String swGUID;
	protected String hwGUID;

//	protected final ModelSerializer serializer = ModelSerializer.INSTANCE;

	final int MAXTRY = 15;

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
	ElementTrace eaTrace = null;
	ArchitectureTrace gammaTrace = null;

	@Override
	public boolean performFinish() {
		containerName = page.getContainerName();
		sysGUID = page.getSystemPackageGUID();
		funcGUID = page.getFunctionalPackageGUID();
		swGUID = page.getSoftwarePackageGUID();
		hwGUID = page.getHardwarePackageGUID();
		// final String fileName = page.getFileName();
		IRunnableWithProgress op = monitor -> {
			EnterpriseArchitectTransformation transformation = null;
			try {
				// doFinish(containerName, fileName, monitor);
				monitor.beginTask("Loading SysML models from Enterprise Architect ", 10);
				monitor.worked(1);
				// transformation = new EnterpriseArchitectTransformation(funcGUID, sysGUID);
				transformation = new EnterpriseArchitectTransformation(funcGUID, hwGUID, swGUID, sysGUID);
				eaTrace = transformation.getTrace();
				monitor.worked(1);
				monitor.setTaskName("Transforming SysML structural and interface models to Gamma ");
				monitor.worked(1);
				transformation.execute3();
				monitor.worked(1);
				gammaTrace = SysMLTransformations.transformArchitecture(eaTrace);
				monitor.worked(1);
				monitor.setTaskName("Transforming Excel tables into Gamma Statecharts");
				var tabAppender = new TableAppender(gammaTrace, eaTrace);
				monitor.worked(1);
				tabAppender.execute();
				monitor.worked(1);
				monitor.setTaskName("Transforming SysML Statemachines models to Gamma Statecharts");
				monitor.worked(1);
				var sctAppender = new GammaAppender(gammaTrace, eaTrace);
				monitor.worked(1);
				sctAppender.execute();
				monitor.worked(1);
				serialize(gammaTrace, monitor);
			} catch (Exception e) {
				if (e instanceof ArchitectureException) {
					var e1 = (ArchitectureException) e;
					if (e1.element != null && transformation != null) {
						transformation.showElement(e1.element);
					}
				}
				if (e instanceof GammaTransformationException) {
					var e1 = (GammaTransformationException) e;
					if (e1.element != null && transformation != null && gammaTrace != null) {
						if (gammaTrace.contains(e1.element)) {
							var element = gammaTrace.get(e1.element);
							transformation.showElement(element);
						}
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
			if (realException instanceof GammaTransformationException) {
				var element = ((GammaTransformationException) realException).element;
				if (element != null) {
					if (element instanceof ArchitectureElement) {
						DialogUtil
								.showErrorWithStackTrace(
										"GammaTransformationException  occured at " + element.getName()
												+ " with EA ID: " + eaTrace.get((ArchitectureElement) element),
										realException);

					} else {
						DialogUtil
								.showErrorWithStackTrace(
										"GammaTransformationException  occured at " + element.getName()
												+ " with EA ID: " + eaTrace.get(gammaTrace.get(element)),
										realException);
					}
				} else {
					DialogUtil.showErrorWithStackTrace("GammaTransformationException occured at unknown element",
							realException);
				}
				// MessageDialog.openError(getShell(), "Error", realException.getMessage());
				e.printStackTrace();
				return false;
			} else if (realException instanceof ArchitectureException) {
				var element = ((ArchitectureException) realException).element;
				if (element != null) {
					DialogUtil.showErrorWithStackTrace("ArchitectureException  occured at " + element.getName()
							+ " with EA ID: " + eaTrace.get(element), realException);
					// DialogUtil.showInfo("select * from t_objects where object_id=" +
					// Long.toString(eaTrace.get(element)));
				} else {
					DialogUtil.showErrorWithStackTrace("ArchitectureException occured at unknown element",
							realException);
				}
				// MessageDialog.openError(getShell(), "Error", realException.getMessage());
				e.printStackTrace();
				return false;
			} else {
				DialogUtil.showErrorWithStackTrace("Exception occured: " + realException.getClass().toGenericString(),
						realException);
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

	protected void serialize(Set<Package> packages, String appendix, ArchitectureTrace gammaTrace,
			IProgressMonitor monitor) throws IOException, CoreException {
		int len = packages.size();
		for (int i = 1; i <= MAXTRY; i++) {

			try {
				for (Package pkg : packages) {
					logger.log(Level.INFO,
							"Saving " + pkg.getName() + " into " + containerName + gammaTrace.getPackagePath(pkg));
					serialize(pkg, containerName + gammaTrace.getPackagePath(pkg), pkg.getName() + appendix);
					serializedPackages.add(pkg);
				}
				break;
			} catch (RuntimeException e) {
				if (e.getMessage() != null && e.getMessage().contains("No EObjectDescription could be found in Scope")
						&& (i < MAXTRY)) {
					e.printStackTrace();
				} else {
					throw e;
				}
				// sleep(1000); // refresh(); // build();
				sleep(1000);
			}
		}
		monitor.worked(len);
	}

	protected void serialize2(ArchitectureTrace gammaTrace, IProgressMonitor monitor)
			throws IOException, CoreException {
		build();
		monitor.beginTask("Saving models ", gammaTrace.getPackages().size());
		serialize(gammaTrace.getInterfacePackage(), containerName, gammaTrace.getInterfacePackage().getName() + ".gcd");
		serialize(gammaTrace.getPrimitiveFunctionPackages(), ".gcd", gammaTrace, monitor);
		serialize(gammaTrace.getInterfaceComponentPackages(), ".gcd", gammaTrace, monitor);
		serialize(gammaTrace.getComponentFunctionPackages(), ".sgcd", gammaTrace, monitor);
		serialize(gammaTrace.getCommunicationComponentPackages(), ".gcd", gammaTrace, monitor);
		serialize(gammaTrace.getSubsystemHardwarePackages(), ".sgcd", gammaTrace, monitor);
		serialize(gammaTrace.getHWErrorStatechartPackages(), ".gcd", gammaTrace, monitor);
		serialize(gammaTrace.getElectronicComponentPackages(), ".sgcd", gammaTrace, monitor);
		serialize(gammaTrace.getSubsystemPackages(), ".sgcd", gammaTrace, monitor);
		var syspkgs = gammaTrace.getSystemPackages();

		serialize(gammaTrace.getSystemPackages(), ".sgcd", gammaTrace, monitor);

		return;

	}

	protected Set<Package> serializedPackages;

	protected boolean canSerialize(Package pkg) {
		for (Package impPkg : pkg.getImports()) {
			if (!serializedPackages.contains(impPkg)) {
				return false;
			}
		}
		return true;
	}

	protected void serialize(ArchitectureTrace gammaTrace, IProgressMonitor monitor) throws IOException, CoreException {
		monitor.beginTask("Saving models ", gammaTrace.getPackages().size());
		build();
		serializedPackages = new HashSet<Package>();
		serialize(gammaTrace.getInterfacePackage(), containerName, gammaTrace.getInterfacePackage().getName() + ".gcd");
		serialize(gammaTrace.getInterfaceComponentPackages(), ".gcd", gammaTrace, monitor);
		serialize(gammaTrace.getPrimitiveFunctionPackages(), ".gcd", gammaTrace, monitor);
		serialize(gammaTrace.getCommunicationComponentPackages(), ".gcd", gammaTrace, monitor);
		serialize(gammaTrace.getHWErrorStatechartPackages(), ".gcd", gammaTrace, monitor);
		serialize(gammaTrace.getSubsystemHardwarePackages(), ".sgcd", gammaTrace, monitor);
		serialize(gammaTrace.getComponentFunctionPackages(), ".sgcd", gammaTrace, monitor);
		serialize(gammaTrace.getElectronicComponentPackages(), ".sgcd", gammaTrace, monitor);
		serialize(gammaTrace.getSubsystemPackages(), ".sgcd", gammaTrace, monitor);
		monitor.worked(1);
		serializedPackages.add(gammaTrace.getInterfacePackage());
		boolean shallRun = true;
		while (shallRun) {
			shallRun = false;
			for (Package pkg : gammaTrace.getPackages()) {
				if ((!serializedPackages.contains(pkg)) & canSerialize(pkg)) {
					String extension = ".sgcd";
					if (pkg.getComponents().isEmpty()) {
						extension = ".gcd";
					} else if (gammaTrace.getPackagePath(pkg).contains("primitive")
							|| gammaTrace.getPackagePath(pkg).contains("interface")
							|| gammaTrace.getPackagePath(pkg).contains("communication")) {
						extension = ".gcd";
					}
					extension = ".sgcd";
					for (int i = 1; i <= MAXTRY; i++) {
						try {
							serialize(pkg, containerName + gammaTrace.getPackagePath(pkg), pkg.getName() + extension);
							serializedPackages.add(pkg);
							break;
						} catch (RuntimeException e) {
							if (e.getMessage().contains("No EObjectDescription could be found in Scope")
									&& e.getMessage() != null && (i < MAXTRY)) {
								e.printStackTrace();
							} else {
								throw e;
							}
							// sleep(1000); // refresh(); // build();
							sleep(1000);
						}
					}
					monitor.worked(1);
					shallRun = true;
				}
			}
		}
		refresh();
		build();
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