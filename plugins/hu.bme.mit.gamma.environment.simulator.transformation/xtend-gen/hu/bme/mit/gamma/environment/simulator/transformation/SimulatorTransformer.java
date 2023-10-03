package hu.bme.mit.gamma.environment.simulator.transformation;

import hu.bme.mit.gamma.environment.analysis.AnalysisComponent;
import hu.bme.mit.gamma.environment.model.utils.EnvironmentModelDerivedFeatures;
import hu.bme.mit.gamma.environment.simulator.transformation.configgen.ConfigGenerator;
import hu.bme.mit.gamma.environment.simulator.transformation.configgen.StatechartConfigGenerator;
import hu.bme.mit.gamma.environment.simulator.transformation.plantumlgen.PlantUMLTransformer;
import hu.bme.mit.gamma.environment.simulator.transformation.pythongen.ServerGenerator;
import hu.bme.mit.gamma.statechart.composite.ComponentInstance;
import hu.bme.mit.gamma.statechart.derivedfeatures.StatechartModelDerivedFeatures;
import hu.bme.mit.gamma.statechart.interface_.Component;
import hu.bme.mit.gamma.util.FileUtil;
import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sourceforge.plantuml.util.DiagramData;
import org.eclipse.xtext.xbase.lib.Conversions;

@SuppressWarnings("all")
public class SimulatorTransformer {
  protected Logger logger = Logger.getLogger("GammaLogger");

  public void transform(final AnalysisComponent analysisComponent, final String basePackageURI) {
    HashSet<Component> components = new HashSet<Component>();
    FileUtil futil = FileUtil.INSTANCE;
    HashSet<Component> transformedComponents = new HashSet<Component>();
    PlantUMLTransformer generatorPlantUML = new PlantUMLTransformer();
    ServerGenerator generatorPython = new ServerGenerator();
    ConfigGenerator generatorConfig = new ConfigGenerator();
    StatechartConfigGenerator generatorSctConfig = new StatechartConfigGenerator();
    components.add(analysisComponent.getAnalyzedComponent().getType());
    while ((!components.isEmpty())) {
      {
        final HashSet<Component> _converted_components = (HashSet<Component>)components;
        final Component component = ((Component[])Conversions.unwrapArray(_converted_components, Component.class))[0];
        components.remove(component);
        transformedComponents.add(component);
        List<ComponentInstance> _allInstances = StatechartModelDerivedFeatures.getAllInstances(component);
        for (final ComponentInstance instance : _allInstances) {
          {
            final Component type = EnvironmentModelDerivedFeatures.getDerivedType(instance);
            boolean _contains = transformedComponents.contains(type);
            boolean _not = (!_contains);
            if (_not) {
              components.add(type);
            }
          }
        }
        String _name = component.getName();
        String _plus = ("Transforming component to SVG: " + _name);
        this.logger.log(Level.INFO, _plus);
        String source = generatorPlantUML.getComponentPlantUmlCode(component);
        DiagramData diagramData = new DiagramData(source);
        String svg = diagramData.getSvg(0);
        String _name_1 = component.getName();
        String _plus_1 = ((((basePackageURI + File.separator) + "simulator-gen") + File.separator) + _name_1);
        String _plus_2 = (_plus_1 + ".svg");
        futil.saveString(_plus_2, svg);
      }
    }
    String server = generatorPython.generate(analysisComponent);
    futil.saveString(((((basePackageURI + File.separator) + "simulator-gen") + File.separator) + "webserver.py"), server);
    String config = generatorConfig.generate(analysisComponent);
    futil.saveString(((((basePackageURI + File.separator) + "simulator-gen") + File.separator) + "config.yml"), config);
    String sct_config = generatorSctConfig.generate(analysisComponent);
    futil.saveString(((((basePackageURI + File.separator) + "simulator-gen") + File.separator) + "config_sct.yml"), sct_config);
  }
}
