package hu.bme.mit.gamma.codegeneration.java;

import hu.bme.mit.gamma.codegeneration.java.util.ElementaryEnvironmentComponentUtility;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Extension;

@SuppressWarnings("all")
public class EnvironmentTimedObjectInterfaceGenerator {
  @Extension
  protected static final ElementaryEnvironmentComponentUtility envUtil = ElementaryEnvironmentComponentUtility.INSTANCE;

  private final String packageName;

  public EnvironmentTimedObjectInterfaceGenerator(final String packageName) {
    this.packageName = packageName;
  }

  public CharSequence generate() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package ");
    _builder.append(this.packageName);
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("public interface TimedObject {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public long getEarliestTime();");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
}
