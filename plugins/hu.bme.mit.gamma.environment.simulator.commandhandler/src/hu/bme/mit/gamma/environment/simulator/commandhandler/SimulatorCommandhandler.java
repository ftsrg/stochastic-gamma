
package hu.bme.mit.gamma.environment.simulator.commandhandler;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;

import hu.bme.mit.gamma.dialog.DialogUtil;
import hu.bme.mit.gamma.environment.analysis.AnalysisComponent;
import hu.bme.mit.gamma.environment.simulator.transformation.SimulatorTransformer;
import hu.bme.mit.gamma.statechart.interface_.Component;
import hu.bme.mit.gamma.statechart.interface_.Package;

public class SimulatorCommandhandler extends AbstractHandler  {
	
	protected Logger logger = Logger.getLogger("GammaLogger");
	
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {	
	    var t0 = System.currentTimeMillis();
		var sel = HandlerUtil.getActiveMenuSelection(event);
		logger.log(Level.INFO,"Generate simulator...--------------------------------------------");

		if (sel instanceof IStructuredSelection) {
			if (((IStructuredSelection) sel).size() == 1) {
				if (((IStructuredSelection) sel).getFirstElement() instanceof IFile) {
					var file = (IFile) ((IStructuredSelection) sel).getFirstElement();
					var path = ((IFile) file).getFileExtension();
					var compositeSystemURI = URI.createPlatformResourceURI(file.getFullPath().toString(), true);
					var resSet = new ResourceSetImpl();
					var basePackageURI = file.getProject().getLocation().toString();
					var resource = resSet.getResource(compositeSystemURI, true);
					var analysisSystem = (Package) resource.getContents().get(0);
					AnalysisComponent analysisComponent=null;
					for (Component comp : analysisSystem.getComponents()) {
						if (comp instanceof AnalysisComponent) {
							analysisComponent=(AnalysisComponent) comp;
						}
					}
					if (analysisComponent==null) {
						DialogUtil.showError("No AnalysisComponent has been found in the selected .sgcl file.");
					}
					var transformer=new SimulatorTransformer();
					transformer.transform(analysisComponent,basePackageURI);
				}
				
			}
		}

		var t1 = System.currentTimeMillis();
		logger.log(Level.INFO,"Simulator has been generated--------------------------------------------------------------");
		logger.log(Level.INFO,"Elapsed time: ");
		logger.log(Level.INFO,Double.toString((t1 - t0) / 1000.0));
		logger.log(Level.INFO," s");
		DialogUtil.showInfo("Simulator is generated successfully!");
		return null;
	}
	

	
}
