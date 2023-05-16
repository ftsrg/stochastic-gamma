package hu.bme.mit.gamma.logictable.transformation.commandhandler.importwizards;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IImportWizard;
import org.eclipse.ui.IWorkbench;

public class LogicTableImportWizard extends Wizard implements IImportWizard {

	LogicTableImportWizardPage mainPage;
	InterfaceImportWizardPage interfacePage;

	public LogicTableImportWizard() {
		super();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#performFinish()
	 */
	public boolean performFinish() {
		IFile file = null;//mainPage.createNewFile();
        if (file == null)
            return false;
        return true;
	}
	 
	/* (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchWizard#init(org.eclipse.ui.IWorkbench, org.eclipse.jface.viewers.IStructuredSelection)
	 */
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		setWindowTitle("Logic Table Import Wizard"); //NON-NLS-1
		setNeedsProgressMonitor(true);
		mainPage = new LogicTableImportWizardPage("Import Logic Table Excel File",selection); //NON-NLS-1
		interfacePage = new InterfaceImportWizardPage("Select Gamma Interface File",selection); //NON-NLS-1
	}
	
	/* (non-Javadoc)
     * @see org.eclipse.jface.wizard.IWizard#addPages()
     */
    public void addPages() {
        super.addPages(); 
        addPage(mainPage);        
        addPage(interfacePage);        
    }

}
