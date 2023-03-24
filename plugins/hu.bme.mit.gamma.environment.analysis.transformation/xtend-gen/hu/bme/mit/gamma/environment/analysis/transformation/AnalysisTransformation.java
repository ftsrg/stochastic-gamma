package hu.bme.mit.gamma.environment.analysis.transformation;

import hu.bme.mit.gamma.environment.analysis.AnalysisComponent;
import hu.bme.mit.gamma.environment.analysis.transformation.javagen.JavaEntryClassGenerator;
import hu.bme.mit.gamma.environment.analysis.transformation.pythongen.PyroSimulatorGenerator;
import hu.bme.mit.gamma.statechart.interface_.Component;
import hu.bme.mit.gamma.util.FileUtil;
import java.io.File;
import java.net.URI;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;

@SuppressWarnings("all")
public class AnalysisTransformation {
  private Logger logger = Logger.getLogger("GammaLogger");

  private final FileUtil futil = FileUtil.INSTANCE;

  public void transformandrun(final Resource resource, final URI uri, final String packageName, final List<String> uriList, final String basePackage) {
    String pyGenURI = ((((basePackage + File.separator) + "simulator-gen") + File.separator) + "simulator.py");
    String gatewayGenURI = ((((((basePackage + File.separator) + "gateway-gen") + File.separator) + "javaenv") + File.separator) + "DetModelEntryPoint.java");
    PyroSimulatorGenerator pythonGenerator = new PyroSimulatorGenerator();
    JavaEntryClassGenerator javaGenerator = new JavaEntryClassGenerator();
    EObject _get = resource.getContents().get(0);
    hu.bme.mit.gamma.statechart.interface_.Package pck = ((hu.bme.mit.gamma.statechart.interface_.Package) _get);
    final Function1<Component, Boolean> _function = (Component e) -> {
      return Boolean.valueOf((e instanceof AnalysisComponent));
    };
    Component _get_1 = ((Component[])Conversions.unwrapArray(IterableExtensions.<Component>filter(pck.getComponents(), _function), Component.class))[0];
    AnalysisComponent acomp = ((AnalysisComponent) _get_1);
    this.logger.log(Level.INFO, ("Python Simulator URI: " + pyGenURI));
    this.logger.log(Level.INFO, ("Java Simulator URI: " + gatewayGenURI));
    this.futil.saveString(pyGenURI, pythonGenerator.generate(acomp, packageName, basePackage).toString());
    this.futil.saveString(gatewayGenURI, javaGenerator.generate(acomp, packageName).toString());
    this.logger.log(Level.INFO, "Analysis has been finnished");
  }
}
