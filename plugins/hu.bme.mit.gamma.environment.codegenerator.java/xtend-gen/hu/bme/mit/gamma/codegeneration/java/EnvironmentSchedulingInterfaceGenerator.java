package hu.bme.mit.gamma.codegeneration.java;

import hu.bme.mit.gamma.codegeneration.java.util.ElementaryEnvironmentComponentUtility;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Extension;

@SuppressWarnings("all")
public class EnvironmentSchedulingInterfaceGenerator {
  @Extension
  protected static final ElementaryEnvironmentComponentUtility envUtil = ElementaryEnvironmentComponentUtility.INSTANCE;

  private final String packageName;

  public EnvironmentSchedulingInterfaceGenerator(final String packageName) {
    this.packageName = packageName;
  }

  public CharSequence generate() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package ");
    _builder.append(this.packageName);
    _builder.append(".");
    CharSequence _schedulingInterfacePackage = EnvironmentSchedulingInterfaceGenerator.envUtil.getSchedulingInterfacePackage();
    _builder.append(_schedulingInterfacePackage);
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("public interface ");
    String _schedulingInterfaceName = EnvironmentSchedulingInterfaceGenerator.envUtil.getSchedulingInterfaceName();
    _builder.append(_schedulingInterfaceName);
    _builder.append(" {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("public void schedule();");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public boolean isEventQueueEmpty();");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    return _builder;
  }
}
