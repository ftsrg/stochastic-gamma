package hu.bme.mit.gamma.logictable.transformation.commandhandler;

import java.io.File;
import java.util.ArrayList;
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
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import hu.bme.mit.gamma.dialog.DialogUtil;
import hu.bme.mit.gamma.logictable.transformation.TableTransformation;
import hu.bme.mit.gamma.statechart.interface_.Interface;
import hu.bme.mit.gamma.statechart.interface_.Package;
import hu.bme.mit.gamma.util.FileUtil;

public class TableImportCommandHandler extends AbstractHandler {

	protected Logger logger = Logger.getLogger("GammaLogger");
	
	protected Set<String> interfacePaths=new HashSet<>();

	
	Set<Interface> processContainer(IContainer container) throws CoreException
	{
	   IResource [] members = container.members();
	   Set<Interface> interfaces=new HashSet<>();
	   for (IResource file : members)
	    {
	       if (file instanceof IContainer)
	         interfaces.addAll(processContainer((IContainer)file));
	       else if (file instanceof IFile) {
	    	   if (file.getFileExtension() != null) { 
		    		if (file.getFileExtension() .equals("gcd")) {
						URI uri = URI.createPlatformResourceURI(file.getFullPath().toString(), true);
						ResourceSet resSet = new ResourceSetImpl();
						Resource resource = resSet.getResource(uri, true);
						EObject object = resource.getContents().get(0);
						if (object instanceof Package) {
							Package p=(Package) object;
							if (!p.getInterfaces().isEmpty()) {
								interfacePaths.add(p.getName());
								interfaces.addAll(p.getInterfaces());
								logger.log(Level.INFO,"Interface gcd file found: "+file.getFullPath().toString());
								logger.log(Level.INFO,"Interface package name: "+p.getName().toString());
							}
						}
		    	   }
	    	   }
	       }
	    }
	   return interfaces;
	}
	
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		var sel = HandlerUtil.getActiveMenuSelection(event);
		try {
			IStructuredSelection selection = (IStructuredSelection) sel;
			if (selection.size() == 1) {
				if (selection.getFirstElement() instanceof IFile) {
					IFile file = (IFile) selection.getFirstElement();
					if ( file.getFileExtension() .equals("xlsx") ||  file.getFileExtension() .equals("xlsx") ||  file.getFileExtension() .equals("xlsx")) {
						var fname=file.getName().replace("."+file.getFileExtension(), "").toLowerCase();
						IProject project=file.getProject();
						interfacePaths.clear();
						Set<Interface> interfaces=processContainer(project);
						TableTransformation transformation=new TableTransformation();
						var futil=FileUtil.INSTANCE;
						var outURI=file.getProject().getLocation().toString()+File.separator+"model-gen"+File.separator+fname+".gcd";
						var sct_data = transformation.generate(file.getLocation().toFile().toString(), interfaces, fname, interfacePaths);
						futil.saveString(outURI, sct_data);
					}else {
						DialogUtil.showError("Only Microsoft Excel (xls,xlsm,xlsx) files can be parsed.");
					}
				}
			}
		} catch (Exception exception) {
			exception.printStackTrace();
			logger.log(Level.SEVERE, exception.getMessage());
			DialogUtil.showErrorWithStackTrace(exception.getMessage(), exception);
		}
		return null;
	}

}
