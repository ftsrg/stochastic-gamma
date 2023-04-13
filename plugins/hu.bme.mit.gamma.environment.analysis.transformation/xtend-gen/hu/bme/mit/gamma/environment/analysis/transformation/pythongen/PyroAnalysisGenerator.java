package hu.bme.mit.gamma.environment.analysis.transformation.pythongen;

import hu.bme.mit.gamma.environment.analysis.AnalysisAspect;
import hu.bme.mit.gamma.environment.analysis.AnalysisComponent;
import hu.bme.mit.gamma.environment.analysis.AnalysisMethod;
import hu.bme.mit.gamma.environment.analysis.HMC;
import hu.bme.mit.gamma.environment.analysis.ImportanceSampling;
import hu.bme.mit.gamma.environment.analysis.MCMC;
import hu.bme.mit.gamma.environment.analysis.MCMCKernel;
import hu.bme.mit.gamma.environment.analysis.NUTS;
import hu.bme.mit.gamma.environment.analysis.transformation.util.TransformationUtility;
import java.math.BigInteger;
import java.util.Arrays;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtend2.lib.StringConcatenation;

@SuppressWarnings("all")
public class PyroAnalysisGenerator {
  public CharSequence generateMarginalVisualisation() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("def visualizeMarginal(inference, marginal, name):");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("sample_num=20000");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("bin_num=100");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("marginal_samples = torch.stack([torch.abs(marginal()) for _ in range(sample_num)])");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("fig, a = plt.subplots()");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("a.set_title( \"Empirical marginal \"+name+\" (ESS:\"+str(round(inference.get_ESS().item(),2))+\", avg:\"+str(round(marginal.mean.item(),2))+\", stddev:\"+str(round(marginal.variance.sqrt().item(),2))+\")\" )");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("a.hist(marginal_samples.numpy(), color=\'b\',bins=bin_num, density=1, label=\"Marginal of \"+name)");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("plt.ylabel(\"Estimated density\")");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("plt.xlabel(\"Value of \"+name)");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("plt.show()");
    _builder.newLine();
    return _builder;
  }

  public CharSequence generateMain(final AnalysisComponent analysisComp) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("if __name__ == \"__main__\":");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("try:");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("# dummy simulations for debugging");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("if DEBUG:");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("for i in range(10):");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("print(simulate())");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("else:");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("# run importance sampling");
    _builder.newLine();
    _builder.append("\t\t\t");
    CharSequence _generateInference = this.generateInference(analysisComp.getAnalysismethod());
    _builder.append(_generateInference, "\t\t\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t\t");
    _builder.append("print(\"run inference algorithm...\")");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("inference.run()");
    _builder.newLine();
    {
      EList<AnalysisAspect> _aspect = analysisComp.getAspect();
      for(final AnalysisAspect aspect : _aspect) {
        _builder.append("\t\t\t");
        CharSequence _marginalName = this.marginalName(aspect);
        _builder.append(_marginalName, "\t\t\t");
        _builder.append(" = pyro.infer.EmpiricalMarginal(inference, \"");
        CharSequence _pyroName = TransformationUtility.pyroName(aspect);
        _builder.append(_pyroName, "\t\t\t");
        _builder.append("\")");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("\t\t\t");
    _builder.append("# visualize results");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("print(\"Results of the analysis: \")");
    _builder.newLine();
    {
      EList<AnalysisAspect> _aspect_1 = analysisComp.getAspect();
      for(final AnalysisAspect aspect_1 : _aspect_1) {
        _builder.append("\t\t\t");
        _builder.append("print(\"Estimated ");
        CharSequence _pyroName_1 = TransformationUtility.pyroName(aspect_1);
        _builder.append(_pyroName_1, "\t\t\t");
        _builder.append(" = \",round(");
        CharSequence _marginalName_1 = this.marginalName(aspect_1);
        _builder.append(_marginalName_1, "\t\t\t");
        _builder.append(".mean.item(),2))");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("\t\t\t");
    _builder.append("print(\"visualize results...\")");
    _builder.newLine();
    {
      EList<AnalysisAspect> _aspect_2 = analysisComp.getAspect();
      for(final AnalysisAspect aspect_2 : _aspect_2) {
        _builder.append("\t\t\t");
        _builder.append("visualizeMarginal(inference,");
        CharSequence _marginalName_2 = this.marginalName(aspect_2);
        _builder.append(_marginalName_2, "\t\t\t");
        _builder.append(",\'");
        CharSequence _pyroName_2 = TransformationUtility.pyroName(aspect_2);
        _builder.append(_pyroName_2, "\t\t\t");
        _builder.append("\')");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("\t");
    _builder.append("except java.lang.RuntimeException as ex:");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("print(\"Caught Java runtime exception : \", str(ex))");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("print(ex.stacktrace())");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("except jpype.JException as ex:");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("print(\"Caught Jpype exception : \", str(ex))");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("print(ex.stacktrace())");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("except Exception as err:");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("print(\"Caught Python exception : \", err)");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("traceback.print_exc()");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("finally:");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("print(\"shutting down JVM...\")");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("shutdownJVM()");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("print (\"analysis is finished successfully\")");
    _builder.newLine();
    return _builder;
  }

  public CharSequence marginalName(final AnalysisAspect aspect) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("empirical_marginal_");
    CharSequence _pyroName = TransformationUtility.pyroName(aspect);
    _builder.append(_pyroName);
    return _builder;
  }

  protected CharSequence _generateInference(final ImportanceSampling analysisMethod) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("inference=pyro.infer.Importance(model=simulate, num_samples=");
    BigInteger _simulationNumber = analysisMethod.getSimulationNumber();
    _builder.append(_simulationNumber);
    _builder.append(")");
    return _builder;
  }

  protected CharSequence _generateInference(final MCMC analysisMethod) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("inference=pyro.infer.MCMC(kernel=");
    CharSequence _generateMCMCKernel = this.generateMCMCKernel(analysisMethod.getKernel());
    _builder.append(_generateMCMCKernel);
    _builder.append(", num_samples = ");
    BigInteger _simulationNumber = analysisMethod.getSimulationNumber();
    _builder.append(_simulationNumber);
    _builder.append(", warmup_steps = ");
    BigInteger _warmupStepNum = analysisMethod.getWarmupStepNum();
    _builder.append(_warmupStepNum);
    _builder.append(")");
    return _builder;
  }

  protected CharSequence _generateInference(final AnalysisMethod analysisMethod) {
    throw new UnsupportedOperationException("Only importance sampling and MCMC supported yet.");
  }

  protected CharSequence _generateMCMCKernel(final NUTS kernel) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("pyro.infer.NUTS(model=simulate)");
    return _builder;
  }

  protected CharSequence _generateMCMCKernel(final HMC kernel) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("pyro.infer.HMC(model=simulate)");
    return _builder;
  }

  public CharSequence generateInference(final AnalysisMethod analysisMethod) {
    if (analysisMethod instanceof ImportanceSampling) {
      return _generateInference((ImportanceSampling)analysisMethod);
    } else if (analysisMethod instanceof MCMC) {
      return _generateInference((MCMC)analysisMethod);
    } else if (analysisMethod != null) {
      return _generateInference(analysisMethod);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(analysisMethod).toString());
    }
  }

  public CharSequence generateMCMCKernel(final MCMCKernel kernel) {
    if (kernel instanceof HMC) {
      return _generateMCMCKernel((HMC)kernel);
    } else if (kernel instanceof NUTS) {
      return _generateMCMCKernel((NUTS)kernel);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(kernel).toString());
    }
  }
}
