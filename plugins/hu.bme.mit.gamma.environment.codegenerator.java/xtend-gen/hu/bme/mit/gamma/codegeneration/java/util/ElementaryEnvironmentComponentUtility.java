package hu.bme.mit.gamma.codegeneration.java.util;

import hu.bme.mit.gamma.environment.model.ElementaryEnvironmentComponentInstance;
import hu.bme.mit.gamma.environment.model.EnvironmentSample;
import hu.bme.mit.gamma.environment.model.EnvironmentSwitch;
import org.eclipse.xtend2.lib.StringConcatenation;

@SuppressWarnings("all")
public class ElementaryEnvironmentComponentUtility {
  public static final ElementaryEnvironmentComponentUtility INSTANCE = new ElementaryEnvironmentComponentUtility();

  public String getSchedulingInterfaceName() {
    return "ElementaryComponentSchedulingInterface";
  }

  public CharSequence getSchedulingInterfacePackage() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("scheduling");
    return _builder;
  }

  public CharSequence getScheduingInterfaceImport(final String basePackageName) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import ");
    _builder.append(basePackageName);
    _builder.append(".");
    CharSequence _schedulingInterfacePackage = this.getSchedulingInterfacePackage();
    _builder.append(_schedulingInterfacePackage);
    _builder.append(".");
    String _schedulingInterfaceName = this.getSchedulingInterfaceName();
    _builder.append(_schedulingInterfaceName);
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    _builder.append("import java.util.Map;");
    _builder.newLine();
    _builder.append("import java.util.ArrayList;");
    _builder.newLine();
    _builder.append("import java.util.HashMap;");
    _builder.newLine();
    return _builder;
  }

  public CharSequence getListDefinition() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("protected List<");
    String _schedulingInterfaceName = this.getSchedulingInterfaceName();
    _builder.append(_schedulingInterfaceName);
    _builder.append("> envComponents = new ArrayList<");
    String _schedulingInterfaceName_1 = this.getSchedulingInterfaceName();
    _builder.append(_schedulingInterfaceName_1);
    _builder.append(">();");
    _builder.newLineIfNotEmpty();
    _builder.append("protected Map<String,");
    String _schedulingInterfaceName_2 = this.getSchedulingInterfaceName();
    _builder.append(_schedulingInterfaceName_2);
    _builder.append("> envMap = new HashMap<String,");
    String _schedulingInterfaceName_3 = this.getSchedulingInterfaceName();
    _builder.append(_schedulingInterfaceName_3);
    _builder.append(">();");
    _builder.newLineIfNotEmpty();
    return _builder;
  }

  public CharSequence getRegisterFunc() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("public void registerEnvironmentComponent(String name,");
    String _schedulingInterfaceName = this.getSchedulingInterfaceName();
    _builder.append(_schedulingInterfaceName);
    _builder.append(" component){");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("envComponents.add(component);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("envMap.put(name,component);");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }

  public String getScheduleCall(final ElementaryEnvironmentComponentInstance component) {
    if (((component instanceof EnvironmentSwitch) || (component instanceof EnvironmentSample))) {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("envMap.get(\"");
      String _name = component.getName();
      _builder.append(_name);
      _builder.append("\").schedule();");
      return _builder.toString();
    } else {
      return "";
    }
  }

  public String getIsEmptyCall(final ElementaryEnvironmentComponentInstance component) {
    if (((component instanceof EnvironmentSwitch) || (component instanceof EnvironmentSample))) {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("envMap.get(\"");
      String _name = component.getName();
      _builder.append(_name);
      _builder.append("\").isEventQueueEmpty()");
      return _builder.toString();
    } else {
      return "true";
    }
  }
}
