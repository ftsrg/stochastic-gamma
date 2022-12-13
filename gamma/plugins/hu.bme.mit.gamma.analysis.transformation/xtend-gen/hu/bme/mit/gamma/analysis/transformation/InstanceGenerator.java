package hu.bme.mit.gamma.analysis.transformation;

import hu.bme.mit.gamma.environment.model.ElementaryEnvironmentComponentInstance;
import hu.bme.mit.gamma.environment.model.EnvironmentDelay;
import hu.bme.mit.gamma.environment.model.EnvironmentEventSource;
import hu.bme.mit.gamma.environment.model.EnvironmentExternSimulation;
import hu.bme.mit.gamma.environment.model.EnvironmentSample;
import hu.bme.mit.gamma.environment.model.EnvironmentSwitch;
import java.util.Arrays;
import org.eclipse.xtend2.lib.StringConcatenation;

@SuppressWarnings("all")
public class InstanceGenerator {
  public static CharSequence generateClass() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class ElementaryComponent():");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def __init__(self,events):");
    _builder.newLine();
    return _builder;
  }
  
  protected static Object _generate(final EnvironmentDelay instance) {
    return null;
  }
  
  protected static Object _generate(final EnvironmentSample instance) {
    return null;
  }
  
  protected static Object _generate(final EnvironmentSwitch instance) {
    return null;
  }
  
  protected static Object _generate(final EnvironmentExternSimulation instance) {
    return null;
  }
  
  protected static Object _generate(final EnvironmentEventSource instance) {
    return null;
  }
  
  public static Object generate(final ElementaryEnvironmentComponentInstance instance) {
    if (instance instanceof EnvironmentDelay) {
      return _generate((EnvironmentDelay)instance);
    } else if (instance instanceof EnvironmentEventSource) {
      return _generate((EnvironmentEventSource)instance);
    } else if (instance instanceof EnvironmentExternSimulation) {
      return _generate((EnvironmentExternSimulation)instance);
    } else if (instance instanceof EnvironmentSample) {
      return _generate((EnvironmentSample)instance);
    } else if (instance instanceof EnvironmentSwitch) {
      return _generate((EnvironmentSwitch)instance);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(instance).toString());
    }
  }
}
