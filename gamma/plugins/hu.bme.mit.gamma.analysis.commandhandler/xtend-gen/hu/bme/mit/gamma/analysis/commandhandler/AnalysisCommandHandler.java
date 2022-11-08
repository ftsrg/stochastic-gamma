package hu.bme.mit.gamma.analysis.commandhandler;

import hu.bme.mit.gamma.analysis.transformation.AnalysisTransformation;
import hu.bme.mit.gamma.codegenerator.java.EnvironmentGlueCodeGenerator;
import hu.bme.mit.gamma.codegenerator.java.commandhandler.CommandHandler;
import hu.bme.mit.gamma.dialog.DialogUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.InputOutput;

@SuppressWarnings("all")
public class AnalysisCommandHandler extends CommandHandler {
  private Object sources;
  
  private ArrayList<String> uris = CollectionLiterals.<String>newArrayList();
  
  private String BasePackageURI = "";
  
  @Override
  public Object execute(final ExecutionEvent event) throws ExecutionException {
    long t0 = System.currentTimeMillis();
    ISelection sel = HandlerUtil.getActiveMenuSelection(event);
    InputOutput.<String>println("Run analysis...--------------------------------------------");
    try {
      if ((sel instanceof IStructuredSelection)) {
        IStructuredSelection selection = ((IStructuredSelection) sel);
        int _size = selection.size();
        boolean _equals = (_size == 1);
        if (_equals) {
          Object _firstElement = selection.getFirstElement();
          if ((_firstElement instanceof IFile)) {
            Object _firstElement_1 = selection.getFirstElement();
            IFile file = ((IFile) _firstElement_1);
            ResourceSetImpl resSet = new ResourceSetImpl();
            this.logger.log(Level.INFO, ("Resource set for Java code generation created: " + resSet));
            InputOutput.<String>println(("Resource set for Java code generation created: " + resSet));
            URI compositeSystemURI = URI.createPlatformResourceURI(file.getFullPath().toString(), true);
            Resource resource = this.loadResource(resSet, compositeSystemURI);
            EObject _get = resource.getContents().get(0);
            hu.bme.mit.gamma.statechart.interface_.Package compositeSystem = ((hu.bme.mit.gamma.statechart.interface_.Package) _get);
            IProject _project = file.getProject();
            HashSet<String> _hashSet = new HashSet<String>();
            this.checkStatechartNameUniqueness(_project, _hashSet);
            Collection<String> simpleStatechartFileNames = this.getSimpleStatechartFileNames(compositeSystem);
            ArrayList<URI> uriList = new ArrayList<URI>();
            this.obtainTraceURIs(file.getProject(), simpleStatechartFileNames, uriList);
            int _size_1 = simpleStatechartFileNames.size();
            int _size_2 = uriList.size();
            boolean _notEquals = (_size_1 != _size_2);
            if (_notEquals) {
              String _lineSeparator = System.lineSeparator();
              String _plus = (("Some trace model is not found: " + simpleStatechartFileNames) + _lineSeparator);
              String _plus_1 = (_plus + uriList);
              String _lineSeparator_1 = System.lineSeparator();
              String _plus_2 = (_plus_1 + _lineSeparator_1);
              String _plus_3 = (_plus_2 + 
                "Wrapper is not generated for the Gamma statecharts without trace.");
              this.logger.log(Level.INFO, _plus_3);
              String _lineSeparator_2 = System.lineSeparator();
              String _plus_4 = (("Some trace model is not found: " + simpleStatechartFileNames) + _lineSeparator_2);
              String _plus_5 = (_plus_4 + uriList);
              String _lineSeparator_3 = System.lineSeparator();
              String _plus_6 = (_plus_5 + _lineSeparator_3);
              String _plus_7 = (_plus_6 + 
                "Wrapper is not generated for the Gamma statecharts without trace.");
              InputOutput.<String>println(_plus_7);
            }
            for (final URI uri : uriList) {
              this.loadResource(resSet, uri);
            }
            IPath _location = file.getProject().getLocation();
            String _plus_8 = (_location + "/");
            String parentFolder = (_plus_8 + this.folderName);
            parentFolder = URI.decode(parentFolder);
            this.logger.log(Level.INFO, ("Resource set content for Java code generation: " + resSet));
            InputOutput.<String>println(("Resource set content for Java code generation: " + resSet));
            String packageName = file.getProject().getName().toLowerCase();
            EnvironmentGlueCodeGenerator generator = new EnvironmentGlueCodeGenerator(resSet, packageName, parentFolder);
            generator.execute();
            this.uris = generator.getURIs();
            this.BasePackageURI = file.getProject().getLocation().toString();
            generator.dispose();
            this.logger.log(Level.INFO, "The Java code generation has been finished.");
            InputOutput.<String>println("The Java code generation has been finished.------------------------------------");
          }
        }
      }
    } catch (final Throwable _t) {
      if (_t instanceof Exception) {
        final Exception exception = (Exception)_t;
        exception.printStackTrace();
        this.logger.log(Level.SEVERE, exception.getMessage());
        InputOutput.<String>println(exception.getMessage());
        DialogUtil.showErrorWithStackTrace(exception.getMessage(), exception);
      } else {
        throw Exceptions.sneakyThrow(_t);
      }
    }
    try {
      if ((sel instanceof IStructuredSelection)) {
        final IStructuredSelection selection = ((IStructuredSelection) sel);
        int _size = selection.size();
        boolean _equals = (_size == 1);
        if (_equals) {
          Object _firstElement = selection.getFirstElement();
          if ((_firstElement instanceof IFile)) {
            Object _firstElement_1 = selection.getFirstElement();
            final IFile firstElement = ((IFile) _firstElement_1);
            final ResourceSetImpl resSet = new ResourceSetImpl();
            final URI compositeSystemURI = URI.createPlatformResourceURI(firstElement.getFullPath().toString(), true);
            final Resource resource = resSet.getResource(compositeSystemURI, true);
            final AnalysisTransformation analysis = new AnalysisTransformation();
            final java.net.URI loc = firstElement.getLocationURI();
            final String packageName = firstElement.getProject().getName().toLowerCase();
            analysis.transformandrun(resource, loc, packageName, this.uris, this.BasePackageURI);
          }
          this.logger.log(Level.INFO, "The Python code generation has been finished.----------------");
          InputOutput.<String>println("The Python code generation has been finished.");
        }
      }
    } catch (final Throwable _t) {
      if (_t instanceof Exception) {
        final Exception e = (Exception)_t;
        e.printStackTrace();
        this.logger.log(Level.SEVERE, "The Python code generation encountered an exception.-----------");
        InputOutput.<String>println("The Python code generation encountered an exception.");
        this.logger.log(Level.SEVERE, e.getCause().toString());
        InputOutput.<String>println(e.getCause().toString());
        this.logger.log(Level.SEVERE, ((List<StackTraceElement>)Conversions.doWrapArray(e.getStackTrace())).toString());
        this.logger.log(Level.SEVERE, e.getMessage());
      } else {
        throw Exceptions.sneakyThrow(_t);
      }
    }
    long t1 = System.currentTimeMillis();
    InputOutput.<String>println("Analysis has been finnished--------------------------------------------------------------");
    InputOutput.<String>print("Elapsed time: ");
    InputOutput.<Double>println(Double.valueOf(((t1 - t0) / 1000.0)));
    return null;
  }
}
