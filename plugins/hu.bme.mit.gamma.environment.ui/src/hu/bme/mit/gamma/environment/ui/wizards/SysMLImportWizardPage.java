package hu.bme.mit.gamma.environment.ui.wizards;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Preferences;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.ContainerSelectionDialog;
import org.osgi.service.prefs.BackingStoreException;

/**
 * The "New" wizard page allows setting the container for the new file as well
 * as the file name. The page will only accept file name without the extension
 * OR with the extension that matches the expected one (gcd).
 */

public class SysMLImportWizardPage extends WizardPage {

	private Text containerText;
	private Text functionalPackageGUID;
	private Text hardwarePackageGUID;
	private Text softwarePackageGUID;
	private Text systemPackageGUID;
	private IEclipsePreferences preferences;

	//private Text fileText;

	private ISelection selection;

	/**
	 * Constructor for SampleNewWizardPage.
	 *
	 * @param pageName
	 */
	public SysMLImportWizardPage(ISelection selection) {
		super(" SysMLImportWizard");
		setTitle("SysML Import Wizard");
		setDescription("This wizard imports SysML models and transforms them into Gamma.");
		this.selection = selection;
	}

	@Override
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);

	    //FillLayout layout = new FillLayout();

	   // layout.type = SWT.VERTICAL;
		GridLayout layout = new GridLayout();
		container.setLayout(layout);
		layout.numColumns = 3;
		layout.verticalSpacing = 9;
		Label label = new Label(container, SWT.NULL);
		label.setText("&Container:");
		

		containerText = new Text(container, SWT.BORDER | SWT.SINGLE);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		containerText.setLayoutData(gd);
		containerText.addModifyListener(e -> dialogChanged());
		
		
		
		Button button = new Button(container, SWT.PUSH);
		button.setText("Browse...");
		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				handleBrowse();
			}
		});

		label = new Label(container, SWT.NULL);
		label.setText("&Functional Package GUID:");
		functionalPackageGUID = new Text(container, SWT.BORDER | SWT.SINGLE);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		functionalPackageGUID.setLayoutData(gd);
		functionalPackageGUID.addModifyListener(e -> funcChanged());
		label = new Label(container, SWT.NULL);

		label = new Label(container, SWT.NULL);
		label.setText("&Hardware Package GUID:");
		hardwarePackageGUID = new Text(container, SWT.BORDER | SWT.SINGLE);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		hardwarePackageGUID.setLayoutData(gd);
		hardwarePackageGUID.addModifyListener(e -> hwChanged());
		label = new Label(container, SWT.NULL);

		label = new Label(container, SWT.NULL);
		label.setText("&Software Package GUID:");
		softwarePackageGUID = new Text(container, SWT.BORDER | SWT.SINGLE);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		softwarePackageGUID.setLayoutData(gd);
		softwarePackageGUID.addModifyListener(e -> swChanged());
		label = new Label(container, SWT.NULL);
		
		label = new Label(container, SWT.NULL);
		label.setText("&System Package GUID:");
		systemPackageGUID = new Text(container, SWT.BORDER | SWT.SINGLE);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		systemPackageGUID.setLayoutData(gd);
		systemPackageGUID.addModifyListener(e -> sysChanged());
		
		initialize();
		dialogChanged();
		setControl(container);
	}

	/**
	 * Tests if the current workbench selection is a suitable container to use.
	 */

	private void initialize() {
		preferences = InstanceScope.INSTANCE.getNode("hu.bme.mit.gamma.environment");
		String funcPkgGUID=preferences.get("functionalPackageGUID","");
		String sysPkgGUID=preferences.get("systemPackageGUID","");
		String hwPkgGUID=preferences.get("hardwarePackageGUID","");
		String swPkgGUID=preferences.get("softwarePackageGUID","");
		if (funcPkgGUID!=null&&sysPkgGUID!=null) {
			functionalPackageGUID.setText(funcPkgGUID);
			systemPackageGUID.setText(sysPkgGUID);
			hardwarePackageGUID.setText(hwPkgGUID);
			softwarePackageGUID.setText(swPkgGUID);
		}
		if (selection != null && selection.isEmpty() == false
				&& selection instanceof IStructuredSelection) {
			IStructuredSelection ssel = (IStructuredSelection) selection;
			if (ssel.size() > 1)
				return;
			Object obj = ssel.getFirstElement();
			if (obj instanceof IResource) {
				IContainer container;
				if (obj instanceof IContainer)
					container = (IContainer) obj;
				else
					container = ((IResource) obj).getParent();
				containerText.setText(container.getFullPath().toString());
			}
		}
		
		//fileText.setText("new_file.gcd");
	}

	/**
	 * Uses the standard container selection dialog to choose the new value for
	 * the container field.
	 */

	private void handleBrowse() {
		ContainerSelectionDialog dialog = new ContainerSelectionDialog(
				getShell(), ResourcesPlugin.getWorkspace().getRoot(), false,
				"Select new file container");
		if (dialog.open() == ContainerSelectionDialog.OK) {
			Object[] result = dialog.getResult();
			if (result.length == 1) {
				containerText.setText(result[0].toString());
			}
		}
	}

	/**
	 * Ensures that both text fields are set.
	 */

	private void dialogChanged() {
		IResource container = ResourcesPlugin.getWorkspace().getRoot()
				.findMember(IPath.fromOSString(getContainerName()));
		String fileName = getFileName();

		if (getContainerName().length() == 0) {
			updateStatus("File container must be specified");
			return;
		}
		if (container == null
				|| (container.getType() & (IResource.PROJECT | IResource.FOLDER)) == 0) {
			updateStatus("File container must exist");
			return;
		}
		if (!container.isAccessible()) {
			updateStatus("Project must be writable");
			return;
		}/*
		if (fileName.length() == 0) {
			updateStatus("File name must be specified");
			return;
		}
		if (fileName.replace('\\', '/').indexOf('/', 1) > 0) {
			updateStatus("File name must be valid");
			return;
		}*/
		int dotLoc = fileName.lastIndexOf('.');
		if (dotLoc != -1) {
			String ext = fileName.substring(dotLoc + 1);
			if (ext.equalsIgnoreCase("gcd") == false) {
				updateStatus("File extension must be \"gcd\"");
				return;
			}
		}
		updateStatus(null);
	}

	private void funcChanged() {
		preferences.put("functionalPackageGUID", getFunctionalPackageGUID());
		try {
			preferences.flush();
		} catch (BackingStoreException e) {
			e.printStackTrace();
		}
	}

	private void hwChanged() {
		preferences.put("hardwarePackageGUID", getHardwarePackageGUID());
		try {
			preferences.flush();
		} catch (BackingStoreException e) {
			e.printStackTrace();
		}
	}

	private void swChanged() {
		preferences.put("softwarePackageGUID", getSoftwarePackageGUID());
		try {
			preferences.flush();
		} catch (BackingStoreException e) {
			e.printStackTrace();
		}
	}

	private void sysChanged() {
		preferences.put("systemPackageGUID", getSystemPackageGUID());
		try {
			preferences.flush();
		} catch (BackingStoreException e) {
			e.printStackTrace();
		}
	}

	private void updateStatus(String message) {
		setErrorMessage(message);
		setPageComplete(message == null);
	}

	public String getContainerName() {
		return containerText.getText();
	}

	public String getFunctionalPackageGUID() {
		return functionalPackageGUID.getText();
	}

	public String getHardwarePackageGUID() {
		return hardwarePackageGUID.getText();
	}

	public String getSoftwarePackageGUID() {
		return softwarePackageGUID.getText();
	}

	public String getSystemPackageGUID() {
		return systemPackageGUID.getText();
	}

	public String getFileName() {
		return "";//fileText.getText();
	}
}