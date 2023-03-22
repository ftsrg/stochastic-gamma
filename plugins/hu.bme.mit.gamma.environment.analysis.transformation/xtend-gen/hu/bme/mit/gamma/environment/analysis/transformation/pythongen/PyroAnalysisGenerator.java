package hu.bme.mit.gamma.environment.analysis.transformation.pythongen;

import org.eclipse.xtend2.lib.StringConcatenation;

@SuppressWarnings("all")
public class PyroAnalysisGenerator {
  private final PyroAnalysisGenerator INSTANCE = new PyroAnalysisGenerator();

  public CharSequence generateMain() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("def __main__(args):");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    return _builder;
  }
}
