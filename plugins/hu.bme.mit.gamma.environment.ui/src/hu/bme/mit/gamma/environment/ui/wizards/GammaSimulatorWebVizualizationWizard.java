package hu.bme.mit.gamma.environment.ui.wizards;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.core.runtime.*;
import org.eclipse.jface.operation.*;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.net.URL;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.core.resources.*;
import org.eclipse.core.runtime.CoreException;
import hu.bme.mit.gamma.util.FileUtil;
import java.io.*;
import org.eclipse.ui.*;
import org.eclipse.ui.ide.IDE;
import org.osgi.framework.Bundle;
import org.eclipse.ui.wizards.newresource.BasicNewProjectResourceWizard;

/**
 * This is a sample new wizard. Its role is to create a new file 
 * resource in the provided container. If the container resource
 * (a folder or a project) is selected in the workspace 
 * when the wizard is opened, it will accept it as the target
 * container. The wizard creates one file with the extension
 * "sgcd". If a sample multi-page editor (also available
 * as a template) is registered for the same extension, it will
 * be able to open it.
 */

public class GammaSimulatorWebVizualizationWizard extends Wizard implements INewWizard {
	private GammaSimulatorWebVizualizationWizardPage page;
	private ISelection selection;
	public final String configFileName="viz.config.yml";

	/**
	 * Constructor for GammaSimulatorWebVizualizationWizard.
	 */
	public GammaSimulatorWebVizualizationWizard() {
		super();
		setNeedsProgressMonitor(true);
	}
	
	/**
	 * Adding the page to the wizard.
	 */
	@Override
	public void addPages() {
		page = new GammaSimulatorWebVizualizationWizardPage(selection);
		addPage(page);
	}

	/**
	 * This method is called when 'Finish' button is pressed in
	 * the wizard. We will create an operation and run it
	 * using wizard as execution context.
	 */
	@Override
	public boolean performFinish() {
		final String containerName = page.getContainerName();
		final String fileName = page.getFileName();
		IRunnableWithProgress op = monitor -> {
			try {
				doFinish(containerName, fileName, monitor);
			} catch (CoreException e) {
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
			MessageDialog.openError(getShell(), "Error", realException.getMessage());
			return false;
		}
		return true;
	}
	
	/**
	 * The worker method. It will find the container, create the
	 * file if missing or just replace its contents, and open
	 * the editor on the newly created file.
	 */

	private void doFinish(
		String containerName,
		String fileName,
		IProgressMonitor monitor)
		throws CoreException {
		// create a sample file
		monitor.beginTask("Creating " + fileName, 2);
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		IResource resource = root.findMember(new Path(containerName));
		if (!resource.exists() || !(resource instanceof IContainer)) {
			throw new CoreException(Status.error("Container \"" + containerName + "\" does not exist."));
		}
		IContainer container = (IContainer) resource;
		final IFile file1 = container.getFile(new Path(fileName));
		final IFile file2 = container.getFile(new Path(configFileName));
		try {
			InputStream stream1 = openContentStream1();
			if (file1.exists()) {
				file1.setContents(stream1, true, true, monitor);
			} else {
				file1.create(stream1, true, monitor);
			}
			stream1.close();
			InputStream stream2 = openContentStream2();
			if (file2.exists()) {
				file2.setContents(stream2, true, true, monitor);
			} else {
				file2.create(stream2, true, monitor);
			}
			stream2.close();
		} catch (IOException e) {
		}
		monitor.worked(1);
	}
	
	/**
	 * We will initialize file contents with a sample text.
	 */

	private InputStream openContentStream1() {
		String contents = "print('Error occured during simulator file generation')";
		try {
			Bundle bundle = Platform.getBundle("hu.bme.mit.gamma.environment.ui");
			URL url = FileLocator.find(bundle, new Path("/resources/webserver.py"), null);
			url = FileLocator.toFileURL(url);
			FileUtil futil = FileUtil.INSTANCE;
			File file = URIUtil.toFile(URIUtil.toURI(url));
			contents = futil.loadString(file);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ByteArrayInputStream(contents.getBytes());
	}

	private InputStream openContentStream2() {
		String contents = "";
		return new ByteArrayInputStream(contents.getBytes());
	}

	/**
	 * We will accept the selection in the workbench to see if
	 * we can initialize from it.
	 * @see IWorkbenchWizard#init(IWorkbench, IStructuredSelection)
	 */
	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		this.selection = selection;
	}
}