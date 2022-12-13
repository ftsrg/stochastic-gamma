package hu.bme.mit.gamma.analysis.transformation;

import hu.bme.mit.gamma.analysis.AnalysisComponent;
import hu.bme.mit.gamma.analysis.transformation.pythonrunner.IStartInfo;
import hu.bme.mit.gamma.analysis.transformation.pythonrunner.PythonInterpreter;
import hu.bme.mit.gamma.analysis.transformation.pythonrunner.StdConsoleProvider;
import hu.bme.mit.gamma.statechart.interface_.Component;
import java.io.File;
import java.io.FileWriter;
import java.net.URI;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;

@SuppressWarnings("all")
public class AnalysisTransformation {
  private Logger logger = Logger.getLogger("GammaLogger");
  
  public void transformandrun(final Resource resource, final URI uri, final String packageName, final List<String> uriList, final String BasePackage) {
    String pyGenURI = ((((BasePackage + File.separator) + "simulator-gen") + File.separator) + "simulator.py");
    String gatewayGenURI = ((((((BasePackage + File.separator) + "gateway-gen") + File.separator) + "javaenv") + File.separator) + "DetModelEntryPoint.java");
    PyroSimulatorGenerator pythonGenerator = new PyroSimulatorGenerator();
    Py4JGatewayGenerator javaGenerator = new Py4JGatewayGenerator();
    EObject _get = resource.getContents().get(0);
    hu.bme.mit.gamma.statechart.interface_.Package pck = ((hu.bme.mit.gamma.statechart.interface_.Package) _get);
    final Function1<Component, Boolean> _function = (Component e) -> {
      return Boolean.valueOf((e instanceof AnalysisComponent));
    };
    Component _get_1 = ((Component[])Conversions.unwrapArray(IterableExtensions.<Component>filter(pck.getComponents(), _function), Component.class))[0];
    AnalysisComponent acomp = ((AnalysisComponent) _get_1);
    System.out.println(pythonGenerator.generate(acomp, packageName));
    System.out.println(javaGenerator.generate(acomp, packageName));
    this.saveCode(pythonGenerator.generate(acomp, packageName), pyGenURI);
    this.saveCode(javaGenerator.generate(acomp, packageName), gatewayGenURI);
    this.logger.log(Level.INFO, "Analysis has been finnished");
  }
  
  public void transform(final Resource resource, final URI uri, final String packageName, final List<String> uriList, final String BasePackage) {
    try {
      String pyGenURI = ((((BasePackage + File.separator) + "simulator-gen") + File.separator) + "simulator.py");
      String gatewayGenURI = ((((((BasePackage + File.separator) + "gateway-gen") + File.separator) + "epasenv") + File.separator) + "AnalyzerGateway.java");
      PyroSimulatorGenerator pythonGenerator = new PyroSimulatorGenerator();
      Py4JGatewayGenerator javaGenerator = new Py4JGatewayGenerator();
      EObject _get = resource.getContents().get(0);
      hu.bme.mit.gamma.statechart.interface_.Package pck = ((hu.bme.mit.gamma.statechart.interface_.Package) _get);
      final Function1<Component, Boolean> _function = (Component e) -> {
        return Boolean.valueOf((e instanceof AnalysisComponent));
      };
      Component _get_1 = ((Component[])Conversions.unwrapArray(IterableExtensions.<Component>filter(pck.getComponents(), _function), Component.class))[0];
      AnalysisComponent acomp = ((AnalysisComponent) _get_1);
      System.out.println(pythonGenerator.generate(acomp, packageName));
      System.out.println(javaGenerator.generate(acomp, packageName));
      this.saveCode(pythonGenerator.generate(acomp, packageName), pyGenURI);
      this.saveCode(javaGenerator.generate(acomp, packageName), gatewayGenURI);
      DynamicCompilation compiler = new DynamicCompilation();
      String gatewayLoc = (((((BasePackage + File.separator) + "gateway-gen") + File.separator) + "epasenv") + File.separator);
      String libLoc = ((BasePackage + File.separator) + "lib");
      compiler.compileAndRun(gatewayGenURI, gatewayLoc, uriList, libLoc);
      this.logger.log(Level.INFO, "Creating Python interpreter...");
      StdConsoleProvider _stdConsoleProvider = new StdConsoleProvider();
      PythonInterpreter interpreter = new PythonInterpreter(new IStartInfo() {
        @Override
        public String getPythonCommand() {
          return "python3.6";
        }
        
        @Override
        public File getWorkingDirectory() {
          return new File("./");
        }
      }, _stdConsoleProvider);
      String pythonscript = pythonGenerator.generate(acomp, packageName).toString();
      this.logger.log(Level.INFO, "Starting Python interpreter");
      interpreter.runCode(pythonscript);
      this.logger.log(Level.INFO, "Analysis has been finnished");
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  /**
   * Creates a Java class from the the given code at the location specified by the given URI.
   */
  protected void saveCode(final CharSequence code, final String uri) {
    try {
      String _substring = uri.substring(0, uri.lastIndexOf(File.separator));
      new File(_substring).mkdirs();
      final FileWriter fw = new FileWriter(uri);
      fw.write(code.toString());
      fw.close();
      return;
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
