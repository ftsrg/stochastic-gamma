package hu.bme.mit.gamma.environment.analysis.transformation.pythongen;

import hu.bme.mit.gamma.environment.analysis.SimulationAnalysisMethod;
import hu.bme.mit.gamma.environment.analysis.transformation.util.EnvironmentConnections;
import hu.bme.mit.gamma.environment.analysis.transformation.util.TransformationUtility;
import hu.bme.mit.gamma.environment.model.EnvironmentRule;
import hu.bme.mit.gamma.environment.model.Filter;
import hu.bme.mit.gamma.environment.model.PortFilter;
import hu.bme.mit.gamma.environment.model.StochasticRule;
import hu.bme.mit.gamma.environment.stochastic.stochastic.BernoulliRandomVariable;
import hu.bme.mit.gamma.environment.stochastic.stochastic.BetaRandomVariable;
import hu.bme.mit.gamma.environment.stochastic.stochastic.BrownianKernel;
import hu.bme.mit.gamma.environment.stochastic.stochastic.CategoricalProbabaility;
import hu.bme.mit.gamma.environment.stochastic.stochastic.ContinouosRandomVariable;
import hu.bme.mit.gamma.environment.stochastic.stochastic.DataSource;
import hu.bme.mit.gamma.environment.stochastic.stochastic.DiracProcess;
import hu.bme.mit.gamma.environment.stochastic.stochastic.DiscreteRandomVariable;
import hu.bme.mit.gamma.environment.stochastic.stochastic.ExponentialRandomVariable;
import hu.bme.mit.gamma.environment.stochastic.stochastic.FittedExponentialRandomVariable;
import hu.bme.mit.gamma.environment.stochastic.stochastic.FittedGaussianProcess;
import hu.bme.mit.gamma.environment.stochastic.stochastic.FittedNormalRandomVariable;
import hu.bme.mit.gamma.environment.stochastic.stochastic.GammaRandomVariable;
import hu.bme.mit.gamma.environment.stochastic.stochastic.InfluxDB;
import hu.bme.mit.gamma.environment.stochastic.stochastic.Kernel;
import hu.bme.mit.gamma.environment.stochastic.stochastic.LinearKernel;
import hu.bme.mit.gamma.environment.stochastic.stochastic.LogNormalRandomVariable;
import hu.bme.mit.gamma.environment.stochastic.stochastic.NormalRandomVariable;
import hu.bme.mit.gamma.environment.stochastic.stochastic.ParetoRandomVariable;
import hu.bme.mit.gamma.environment.stochastic.stochastic.PeriodicKernel;
import hu.bme.mit.gamma.environment.stochastic.stochastic.PythonSimulation;
import hu.bme.mit.gamma.environment.stochastic.stochastic.RBFKernel;
import hu.bme.mit.gamma.environment.stochastic.stochastic.RandomVariable;
import hu.bme.mit.gamma.environment.stochastic.stochastic.StochasticModel;
import hu.bme.mit.gamma.environment.stochastic.stochastic.SumKernel;
import hu.bme.mit.gamma.environment.stochastic.stochastic.UniformRandomVariable;
import hu.bme.mit.gamma.environment.stochastic.stochastic.WeibullRandomVariable;
import hu.bme.mit.gamma.expression.model.IntegerLiteralExpression;
import hu.bme.mit.gamma.expression.util.ExpressionEvaluator;
import hu.bme.mit.gamma.statechart.interface_.Port;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;
import org.eclipse.xtext.xbase.lib.StringExtensions;

@SuppressWarnings("all")
public class PyroDistGenerator {
  private Integer distcntr = Integer.valueOf(0);

  private final ExpressionEvaluator expEval = ExpressionEvaluator.INSTANCE;

  public static final LinkedList<String> random_vars = CollectionLiterals.<String>newLinkedList();

  public CharSequence generateRandomVariableClass(final IntegerLiteralExpression N) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class RandomVariable():");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("plate=pyro.plate(\"random_variable\")");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def __init__(self,dist,name,N=");
    {
      if ((N == null)) {
        _builder.append("1");
      } else {
        BigInteger _value = N.getValue();
        _builder.append(_value, "\t");
      }
    }
    _builder.append("):");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("self.dist=dist");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("self.name=name");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("self.event_cntr=N-1");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("self.meta_cntr=-1");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("self.N=N");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def sampleb(self):");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return pyro.sample(self.name+\"_sample_\"+str(self.meta_cntr),self.dist.expand([self.N]))");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def calc(self,event=0,time=0):");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("self.event_cntr=self.event_cntr+1");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("if self.N>0:");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("if self.event_cntr==self.N:");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("self.event_cntr=0");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("self.meta_cntr=self.meta_cntr+1");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("with RandomVariable.plate:");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.append("self.samples=self.sampleb()");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("return self.samples[self.event_cntr].item()");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("else:");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("with RandomVariable.plate:");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("return pyro.sample(self.name+\"_sample_\"+str(self.event_cntr),self.dist).item()");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def reset(self):");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("self.event_cntr=-1 # self.N-1");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("self.meta_cntr=0#-1");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("with RandomVariable.plate:");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("if self.N>0:");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("self.samples=self.sampleb()");
    _builder.newLine();
    return _builder;
  }

  public CharSequence generateJointRandomVariableClass(final IntegerLiteralExpression N) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class JointDistribution(TorchDistribution):");
    _builder.newLine();
    _builder.newLine();
    _builder.append("    ");
    _builder.append("arg_constraints = {}  # nothing can be constrained");
    _builder.newLine();
    _builder.newLine();
    _builder.append("    ");
    _builder.append("def __init__(self, dists, validate_args=None):");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("for dist in dists:");
    _builder.newLine();
    _builder.append("            ");
    _builder.append("if dist.event_shape != dists[0].event_shape:");
    _builder.newLine();
    _builder.append("                ");
    _builder.append("raise ValueError(\"components event_shape disagree: {} vs {}\".format(");
    _builder.newLine();
    _builder.append("                    ");
    _builder.append("dist.event_shape, dists[0].event_shape))");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("batch_shape = broadcast_shape(dists)");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("self.dists = dists");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("self.dnum = len(dists)");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("super().__init__(batch_shape, dists[0].event_shape, validate_args)");
    _builder.newLine();
    _builder.newLine();
    _builder.append("    ");
    _builder.append("@property");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("def has_rsample(self):");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("return True");
    _builder.newLine();
    _builder.newLine();
    _builder.append("    ");
    _builder.append("def expand(self, batch_shape):");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("new_dists=[]");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("for dist in self.dists:");
    _builder.newLine();
    _builder.append("            ");
    _builder.append("new_dists.append(dist.expand(batch_shape))");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("return JointDistribution(new_dists)");
    _builder.newLine();
    _builder.newLine();
    _builder.append("    ");
    _builder.append("def sample(self, sample_shape=torch.Size()):");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("samples=[]");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("for dist in self.dists:");
    _builder.newLine();
    _builder.append("            ");
    _builder.append("samples.append(dist.sample(sample_shape))");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("return torch.stack(samples)");
    _builder.newLine();
    _builder.newLine();
    _builder.append("    ");
    _builder.append("def rsample(self, sample_shape=torch.Size()):");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("samples=[]");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("for dist in self.dists:");
    _builder.newLine();
    _builder.append("            ");
    _builder.append("samples.append(dist.sample(sample_shape))");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("return torch.stack(samples)");
    _builder.newLine();
    _builder.newLine();
    _builder.append("    ");
    _builder.append("def log_prob(self, value):");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("log_prob=torch.tensor(0.0)");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("for i in range(len(self.dists)):");
    _builder.newLine();
    _builder.append("            ");
    _builder.append("log_prob=log_prob+self.dists[i].log_prob(value[i]).sum()");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("return log_prob");
    _builder.newLine();
    _builder.newLine();
    _builder.append("    ");
    _builder.append("def mean(self):");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("means=[]");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("for dist in self.dists:");
    _builder.newLine();
    _builder.append("            ");
    _builder.append("means.append(dist.mean)");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("return torch.stack(means)");
    _builder.newLine();
    _builder.newLine();
    _builder.append("    ");
    _builder.append("def variance(self):");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("variances=[]");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("for dist in self.dists:");
    _builder.newLine();
    _builder.append("            ");
    _builder.append("variances.append(dist.variance)");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("return torch.stack(variances)");
    _builder.newLine();
    _builder.newLine();
    _builder.newLine();
    _builder.append("class RandomVariable():");
    _builder.newLine();
    _builder.newLine();
    _builder.append("    ");
    _builder.append("plate=pyro.plate(\"random_variable\")");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("dists=[]");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("samples=[]");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("dist=None");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("insts=[]");
    _builder.newLine();
    _builder.newLine();
    _builder.append("    ");
    _builder.append("def ginit():");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("RandomVariable.dist=JointDistribution(RandomVariable.dists)");
    _builder.newLine();
    _builder.newLine();
    _builder.append("    ");
    _builder.append("def greset():");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("RandomVariable.cntr=-1");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("RandomVariable.gsample()");
    _builder.newLine();
    _builder.newLine();
    _builder.append("    ");
    _builder.append("def gsample():");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("RandomVariable.cntr=RandomVariable.cntr+1");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("RandomVariable.samples=pyro.sample(\"samples_\"+str(RandomVariable.cntr),RandomVariable.dist)");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("for dist in RandomVariable.insts:");
    _builder.newLine();
    _builder.append("            ");
    _builder.append("dist.event_cntr=-1");
    _builder.newLine();
    _builder.newLine();
    _builder.append("    ");
    _builder.append("def __init__(self,dist,name,N=");
    {
      if ((N == null)) {
        _builder.append("1");
      } else {
        BigInteger _value = N.getValue();
        _builder.append(_value, "    ");
      }
    }
    _builder.append("):");
    _builder.newLineIfNotEmpty();
    _builder.append("        ");
    _builder.append("self.dist=dist");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("self.name=name");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("self.event_cntr=-1");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("self.N=N");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("self.i=len(RandomVariable.dists)");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("RandomVariable.dists.append(dist.expand([N]))");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("RandomVariable.insts.append(self)");
    _builder.newLine();
    _builder.append("    ");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("def calc(self,event=0,time=0):");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("self.event_cntr=self.event_cntr+1");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("if self.event_cntr==self.N:");
    _builder.newLine();
    _builder.append("            ");
    _builder.append("RandomVariable.gsample()");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("return RandomVariable.samples[self.i][int(self.event_cntr)].item()");
    _builder.newLine();
    return _builder;
  }

  public CharSequence generateSimpleRandomVariableClass() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class RandomVariable():");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def __init__(self,dist,name):");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("self.dist=dist");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("self.name=name");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("self.event_cntr=0");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def calc(self,event=0,time=0):");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("self.event_cntr=self.event_cntr+1");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return pyro.sample(self.name+\"_sample_\"+str(self.event_cntr),self.dist).item()");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def reset(self):");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("self.event_cntr=0");
    _builder.newLine();
    return _builder;
  }

  public CharSequence generateContinuousRandomVariableClass() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class ContinuousRandomVariable():");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def __init__(self,dist,name,N=30):");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("self.dist=dist");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("self.name=name");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("self.event_cntr=N-1");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("self.meta_cntr=-1");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("self.N=N");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def calc(self,event=0,time=0):");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("self.event_cntr=self.event_cntr+1");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("if self.N>0:");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("if self.event_cntr==self.N:");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("self.event_cntr=0");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("self.meta_cntr=self.meta_cntr+1");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("self.samples=pyro.sample(self.name+\"_sample_\"+str(self.meta_cntr),self.dist.expand([self.N]))");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("return self.samples[self.event_cntr].item()");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("else:");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("return pyro.sample(self.name+\"_sample_\"+str(self.event_cntr),self.dist).item()");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def reset(self):");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("self.event_cntr=self.N-1");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("self.meta_cntr=-1");
    _builder.newLine();
    return _builder;
  }

  public CharSequence generateZerotimeRandomVariableClass() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class ZerotimeRandomVariable():");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def __init__(self,dist,name,N=1):");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("self.dist=dist");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("self.name=name");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("self.event_cntr=N-1");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("self.meta_cntr=-1");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("self.N=N");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def calc(self,event=0,time=0):");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("self.event_cntr=self.event_cntr+1");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return pyro.sample(self.name+\"_sample_\"+str(self.event_cntr),self.dist).item()-1.0");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def reset(self):");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("self.event_cntr=self.N-1");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("self.meta_cntr=-1");
    _builder.newLine();
    return _builder;
  }

  public CharSequence generateContinuousRandomVariableClass_old() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class ContinuousRandomVariable():");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def __init__(self,dist,name):");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("self.dist=dist");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("self.name=name");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("self.event_cntr=0");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def calc(self,event=0,time=0):");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("self.event_cntr=self.event_cntr+1");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return pyro.sample(self.name+\"_sample_\"+str(self.event_cntr),self.dist).item()");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def reset(self):");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("self.event_cntr=0");
    _builder.newLine();
    return _builder;
  }

  public CharSequence generateDRandomVariableClass() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class ContinuousRandomVariable():");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def __init__(self,dist,name,N=1):");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("self.dist=dist");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("self.name=name");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("self.event_cntr=N-1");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("self.meta_cntr=-1");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("self.N=N");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def calc(self,event=0,time=0):");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("self.event_cntr=self.event_cntr+1");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("if self.N>0:");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("if self.event_cntr==self.N:");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("self.event_cntr=0");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("self.meta_cntr=self.meta_cntr+1");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("self.samples=pyro.sample(self.name+\"_sample_\"+str(self.meta_cntr),self.dist.expand([self.N]))");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("return self.samples[self.event_cntr].item()");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("else:");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("return pyro.sample(self.name+\"_sample_\"+str(self.event_cntr),self.dist).item()");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def reset(self):");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("self.event_cntr=self.N-1");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("self.meta_cntr=-1");
    _builder.newLine();
    return _builder;
  }

  public CharSequence generateDiscreteRandomVariableClass() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class DiscreteRandomVariable():");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def __init__(self,dist,name):");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("self.dist=dist");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("self.name=name");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("self.event_cntr=0");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def calc(self,event=0,time=0):");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("self.event_cntr=self.event_cntr+1");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return pyro.sample(self.name+\"_sample_\"+str(self.event_cntr),self.dist).item()-1.0");
    _builder.newLine();
    return _builder;
  }

  public CharSequence generateGaussProcessClass() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class GaussProcess():");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def __init__(self, dataset, kernel, lr, name):");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("self.name=name");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("self.event_cntr=0");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("points=dataset.points");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("x = []");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("t = []");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("y = []");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("i = 0");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("t0 = 0");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("for p in points:");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("if i == 0:");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("t0 = datetime.datetime.strptime(p.pop(\"time\"), \'%Y-%m-%dT%H:%M:%S.%fZ\')");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("t.append(t0)");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("else:");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("t.append(datetime.datetime.strptime(p.pop(\"time\"), \'%Y-%m-%dT%H:%M:%S.%fZ\'))");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("t[i] = t[i] - t0");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("x.append(t[i].total_seconds())");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("yi = list(p.values())");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("if len(yi) == 1:");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("y.append(yi[0])");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("else:");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("y.append(yi)");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("i = i + 1");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("x = torch.tensor(x)");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("y = torch.tensor(y)");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("X = x");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("# initialize the inducing inputs");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("Xu = torch.arange(1.) / 6.0");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("#kernel = gp.kernels.Sum(gp.kernels.Periodic(input_dim=1), gp.kernels.Brownian(input_dim=1))");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("# we increase the jitter for better numerical stability");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("sgpr = gp.models.SparseGPRegression(X=X, y=y, kernel=kernel, Xu=Xu, jitter=1.0e-5)");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("# the way we setup inference is similar to above");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("optimizer = torch.optim.Adam(sgpr.parameters(), lr=lr)");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("loss_fn = pyro.infer.Trace_ELBO().differentiable_loss");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("losses = []");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("num_steps = 2000");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("for i in range(num_steps):");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("optimizer.zero_grad()");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("loss = loss_fn(sgpr.model, sgpr.guide)");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("if i % 20 == 0:");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("print(\"Step: \", i, \" Loss: \", loss)");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("loss.backward()");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("optimizer.step()");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("losses.append(loss.item())");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("self.gp=sgpr");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def calc(self,event,time):");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("self.event_cntr=self.event_cntr+1");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("mu,sig=self.gp.forward(torch.tensor([time]), full_cov=False, noiseless=False)");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return pyro.sample(self.name+\"_sample_GP_\"+str(self.event_cntr),pyro.distributions.Normal(mu,sig)).item()");
    _builder.newLine();
    return _builder;
  }

  public CharSequence generateFittedExponentialRandomVariableClass() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class FittedExponentialRandomVariable():");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def model(self,data):");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("rate = pyro.param(\"alpha_q\", torch.tensor(1.0),");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("constraint=constraints.positive)");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("for i in range(len(data)):");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("# observe datapoint i using the bernoulli likelihood");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("pyro.sample(\"obs_{}\".format(i), dist.Exponential(rate), obs=(torch.tensor(data[i])))");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def guide(self,data):");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("pass");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def __init__(self,name,source,lr):");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("self.name=name");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("self.event_cntr=0");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("#preprocess data");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("data=[float(p[\"data\"]) for p in list(source.points)]");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("md=min(data)");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("data=[d-md for d in data]");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("# setup the optimizer");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("adam_params = {\"lr\": lr, \"betas\": (0.99, 0.999)}");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("optimizer = Adam(adam_params)");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("n_steps=2000");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("# setup the inference algorithm");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("svi = SVI(self.model, self.guide, optimizer, loss=Trace_ELBO())");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("losses=list()");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("ploss=100000000.0");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("# do gradient steps");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("for step in range(n_steps):");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("loss=svi.step(data)");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("losses.append(loss)");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("if step % 10 == 0:");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("print(\"loss=\",loss)");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("if ploss-loss<0.001:");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("break");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("ploss=loss");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("# grab the learned variational parameters");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("m = pyro.param(\"alpha_q\").item()");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("print(m)");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("xx = list(np.arange(0, 4/m, 0.1/m))");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("yy = []");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("d=dist.Exponential(m)");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("self.dist=d");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("for x in xx:");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("y=float(exp(dist.Exponential(m).log_prob(torch.tensor(x))))");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("yy.append(y)");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("fig1, a1 = plt.subplots()");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("fig2, a2 = plt.subplots()");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("a1.plot(losses)");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("a1.set_ylabel(\'loss\')");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("a1.set_xlabel(\'step\')");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("a1.set_title(\"model fitting process\")");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("a2.hist(data,bins=10)");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("a2.plot(xx,yy)");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("a2.set_ylabel(\'frequency\')");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("a2.set_xlabel(\'delay (s)\')");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("a2.set_title(\"model fitting results\")");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def calc(self,event=0,time=0):");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("self.event_cntr=self.event_cntr+1");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return pyro.sample(self.name+\"_sample_\"+str(self.event_cntr),self.dist).item()");
    _builder.newLine();
    return _builder;
  }

  public CharSequence generateFittedNormalRandomVariableClass() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class FittedNormalRandomVariable():");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def model(self,data):");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("alpha_q = pyro.param(\"alpha_q\", torch.tensor(1.0),");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("constraint=constraints.positive)");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("beta_q = pyro.param(\"beta_q\", torch.tensor(1.0),");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("constraint=constraints.positive)");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("for i in range(len(data)):");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("# observe datapoint i using the bernoulli likelihood");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("pyro.sample(\"obs_{}\".format(i), dist.Normal(alpha_q,beta_q), obs=torch.tensor(data[i]))");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def guide(self,data):");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("pass");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def __init__(self,name,source,lr):");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("self.name=name");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("self.event_cntr=0");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("data=[float(p[\"data\"]) for p in list(source.points)]");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("# setup the optimizer");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("adam_params = {\"lr\": lr, \"betas\": (0.99, 0.999)}");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("optimizer = Adam(adam_params)");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("n_steps=2000");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("# setup the inference algorithm");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("svi = SVI(self.model, self.guide, optimizer, loss=Trace_ELBO())");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("losses=list()");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("ploss=100000000.0");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("# do gradient steps");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("for step in range(n_steps):");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("loss=svi.step(data)");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("losses.append(loss)");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("if step % 10 == 0:");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("print(\"loss=\",loss)");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("if ploss-loss<0.001:");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("break");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("ploss=loss");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("# grab the learned variational parameters");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("m = pyro.param(\"alpha_q\").item()");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("s = pyro.param(\"beta_q\").item()");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("print(m)");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("print(s)");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("xx = list(np.arange(m-2*s, m+2*s, s/10))");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("yy = []");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("d=dist.Normal(loc=torch.tensor(m),scale=torch.tensor(s))");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("self.dist=d");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("for x in xx:");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("y=float(exp(dist.Normal(loc=torch.tensor(m),scale=torch.tensor(s)).log_prob(torch.tensor(x))))");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("yy.append(y)");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("fig1, a1 = plt.subplots()");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("fig2, a2 = plt.subplots()");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("a1.plot(losses)");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("a1.set_ylabel(\'loss\')");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("a1.set_xlabel(\'step\')");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("a1.set_title(\"model fitting process\")");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("a2.hist(data,bins=10)");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("a2.plot(xx,yy)");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("a2.set_ylabel(\'frequency\')");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("a2.set_xlabel(\'delay (s)\')");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("a2.set_title(\"model fitting results\")");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def calc(self,event=0,time=0):");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("self.event_cntr=self.event_cntr+1");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return pyro.sample(self.name+\"_sample_\"+str(self.event_cntr),self.dist).item()");
    _builder.newLine();
    return _builder;
  }

  protected CharSequence _generateStochasticModel(final FittedExponentialRandomVariable variable, final String name) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("FittedExponentialRandomVariable(");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("source=");
    CharSequence _generateDatasetInstance = PyroDistGenerator.generateDatasetInstance(variable.getSource());
    _builder.append(_generateDatasetInstance, "\t\t\t");
    _builder.append(",");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t\t");
    _builder.append("name=\"FittedExpVariable");
    _builder.append(name, "\t\t\t");
    String _string = (this.distcntr++).toString();
    _builder.append(_string, "\t\t\t");
    _builder.append("\",");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t\t");
    _builder.append("lr=");
    String _string_1 = Double.valueOf(variable.getLr()).toString();
    _builder.append(_string_1, "\t\t\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append(")");
    return _builder;
  }

  protected CharSequence _generateStochasticModel(final FittedNormalRandomVariable variable, final String name) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("FittedNormalRandomVariable(");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("source=");
    CharSequence _generateDatasetInstance = PyroDistGenerator.generateDatasetInstance(variable.getSource());
    _builder.append(_generateDatasetInstance, "\t\t\t");
    _builder.append(",");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t\t");
    _builder.append("name=\"FittedNormVariable");
    _builder.append(name, "\t\t\t");
    String _string = (this.distcntr++).toString();
    _builder.append(_string, "\t\t\t");
    _builder.append("\",");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t\t");
    _builder.append("lr=");
    String _string_1 = Double.valueOf(variable.getLr()).toString();
    _builder.append(_string_1, "\t\t\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append(")");
    return _builder;
  }

  protected String _generateZerotimeStochasticModel(final DiscreteRandomVariable variable, final String name) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("ZerotimeRandomVariable(");
    CharSequence _generateDitribution = PyroDistGenerator.generateDitribution(variable);
    _builder.append(_generateDitribution);
    _builder.append(",\"ZerotimeRandomVarriable");
    _builder.append(name);
    String _string = (this.distcntr++).toString();
    _builder.append(_string);
    _builder.append("\")");
    final String str1 = _builder.toString();
    PyroDistGenerator.random_vars.add(str1);
    return str1;
  }

  protected CharSequence _generateStochasticModel(final ContinouosRandomVariable variable, final String name) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("RandomVariable(");
    CharSequence _generateDitribution = PyroDistGenerator.generateDitribution(variable);
    _builder.append(_generateDitribution);
    _builder.append(",\"ContRandomVarriable");
    _builder.append(name);
    String _string = (this.distcntr++).toString();
    _builder.append(_string);
    _builder.append("\")");
    final String str1 = _builder.toString();
    PyroDistGenerator.random_vars.add(str1);
    return str1;
  }

  protected CharSequence _generateStochasticModel(final DiscreteRandomVariable variable, final String name) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("RandomVariable(");
    CharSequence _generateDitribution = PyroDistGenerator.generateDitribution(variable);
    _builder.append(_generateDitribution);
    _builder.append(",\"DiscRandomVarriable");
    _builder.append(name);
    String _string = (this.distcntr++).toString();
    _builder.append(_string);
    _builder.append("\")");
    final String str1 = _builder.toString();
    PyroDistGenerator.random_vars.add(str1);
    return str1;
  }

  protected CharSequence _generateStochasticModel(final FittedGaussianProcess variable, final String name) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("GaussProcess(");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("dataset=");
    CharSequence _generateDatasetInstance = PyroDistGenerator.generateDatasetInstance(variable.getSource());
    _builder.append(_generateDatasetInstance, "\t");
    _builder.append(",");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("kernel=");
    String _generateKernel = PyroDistGenerator.generateKernel(variable.getKernel());
    _builder.append(_generateKernel, "\t");
    _builder.append(",");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("lr=");
    String _string = Double.toString(variable.getLr());
    _builder.append(_string, "\t");
    _builder.append(",");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("name=\"FittedGaussianProcess");
    _builder.append(name, "\t");
    String _string_1 = (this.distcntr++).toString();
    _builder.append(_string_1, "\t");
    _builder.append("\"");
    _builder.newLineIfNotEmpty();
    _builder.append(")");
    _builder.newLine();
    return _builder;
  }

  protected static CharSequence _generateDitribution(final NormalRandomVariable dist) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("pyro.distributions.Normal(loc=");
    CharSequence _evaluateMixedExpression = TransformationUtility.evaluateMixedExpression(dist.getMean());
    _builder.append(_evaluateMixedExpression);
    _builder.append(",scale=");
    CharSequence _evaluateMixedExpression_1 = TransformationUtility.evaluateMixedExpression(dist.getScale());
    _builder.append(_evaluateMixedExpression_1);
    _builder.append(")");
    return _builder;
  }

  protected static CharSequence _generateDitribution(final WeibullRandomVariable dist) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("pyro.distributions.Weibull(concentration=");
    CharSequence _evaluateMixedExpression = TransformationUtility.evaluateMixedExpression(dist.getShape());
    _builder.append(_evaluateMixedExpression);
    _builder.append(",shape=");
    CharSequence _evaluateMixedExpression_1 = TransformationUtility.evaluateMixedExpression(dist.getScale());
    _builder.append(_evaluateMixedExpression_1);
    _builder.append(")");
    return _builder;
  }

  protected static CharSequence _generateDitribution(final GammaRandomVariable dist) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("pyro.distributions.Gamma(concentration=");
    CharSequence _evaluateMixedExpression = TransformationUtility.evaluateMixedExpression(dist.getShape());
    _builder.append(_evaluateMixedExpression);
    _builder.append(",rate=");
    CharSequence _evaluateMixedExpression_1 = TransformationUtility.evaluateMixedExpression(dist.getScale());
    _builder.append(_evaluateMixedExpression_1);
    _builder.append(")");
    return _builder;
  }

  protected static CharSequence _generateDitribution(final UniformRandomVariable dist) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("pyro.distributions.Uniform(low=");
    CharSequence _evaluateMixedExpression = TransformationUtility.evaluateMixedExpression(dist.getLowerBound());
    _builder.append(_evaluateMixedExpression);
    _builder.append(",high=");
    CharSequence _evaluateMixedExpression_1 = TransformationUtility.evaluateMixedExpression(dist.getUpperBound());
    _builder.append(_evaluateMixedExpression_1);
    _builder.append(")");
    return _builder;
  }

  protected static CharSequence _generateDitribution(final LogNormalRandomVariable dist) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("pyro.distributions.LogNormal(loc=");
    CharSequence _evaluateMixedExpression = TransformationUtility.evaluateMixedExpression(dist.getMean());
    _builder.append(_evaluateMixedExpression);
    _builder.append(",scale=");
    CharSequence _evaluateMixedExpression_1 = TransformationUtility.evaluateMixedExpression(dist.getScale());
    _builder.append(_evaluateMixedExpression_1);
    _builder.append(")");
    return _builder;
  }

  protected static CharSequence _generateDitribution(final ParetoRandomVariable dist) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("pyro.distributions.Pareto(alpha=");
    CharSequence _evaluateMixedExpression = TransformationUtility.evaluateMixedExpression(dist.getAlpha());
    _builder.append(_evaluateMixedExpression);
    _builder.append(",scale=");
    CharSequence _evaluateMixedExpression_1 = TransformationUtility.evaluateMixedExpression(dist.getScale());
    _builder.append(_evaluateMixedExpression_1);
    _builder.append(")");
    return _builder;
  }

  protected static CharSequence _generateDitribution(final BetaRandomVariable dist) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("pyro.distributions.Beta(concentration1=");
    CharSequence _evaluateMixedExpression = TransformationUtility.evaluateMixedExpression(dist.getApha());
    _builder.append(_evaluateMixedExpression);
    _builder.append(",concentration0=");
    CharSequence _evaluateMixedExpression_1 = TransformationUtility.evaluateMixedExpression(dist.getBeta());
    _builder.append(_evaluateMixedExpression_1);
    _builder.append(")");
    return _builder;
  }

  protected static CharSequence _generateDitribution(final ExponentialRandomVariable dist) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("pyro.distributions.Exponential(");
    CharSequence _evaluateMixedExpression = TransformationUtility.evaluateMixedExpression(dist.getRate());
    _builder.append(_evaluateMixedExpression);
    _builder.append(")");
    return _builder;
  }

  protected static CharSequence _generateDitribution(final BernoulliRandomVariable dist) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("pyro.distributions.Bernoulli(");
    CharSequence _evaluateMixedExpression = TransformationUtility.evaluateMixedExpression(dist.getProbability());
    _builder.append(_evaluateMixedExpression);
    _builder.append(")");
    return _builder;
  }

  protected static CharSequence _generateDitribution(final RandomVariable dist) {
    throw new UnsupportedOperationException("Stochastic Processes are not supported yet.");
  }

  protected static CharSequence _generateStochasticProcess(final FittedGaussianProcess gp) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("MLGaussProcess(");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("lr=float(");
    String _string = Double.valueOf(gp.getLr()).toString();
    _builder.append(_string, "\t");
    _builder.append("),");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("dataset=");
    CharSequence _generateDatasetInstance = PyroDistGenerator.generateDatasetInstance(gp.getSource());
    _builder.append(_generateDatasetInstance, "\t");
    _builder.append(",");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("kernel=");
    String _generateKernel = PyroDistGenerator.generateKernel(gp.getKernel());
    _builder.append(_generateKernel, "\t");
    _builder.newLineIfNotEmpty();
    _builder.append("),dist=None,dirac=None");
    _builder.newLine();
    return _builder;
  }

  protected static CharSequence _generateDitribution(final DiracProcess dirac) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("dirac=");
    CharSequence _generateDatasetInstance = PyroDistGenerator.generateDatasetInstance(dirac.getSource());
    _builder.append(_generateDatasetInstance);
    _builder.append(",dist=None,mlgp=None");
    _builder.newLineIfNotEmpty();
    return _builder;
  }

  protected static CharSequence _generateDatasetInstance(final InfluxDB dataset) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("Dataset(");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("dbname=\"");
    String _dbname = dataset.getDbname();
    _builder.append(_dbname, "\t");
    _builder.append("\",");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("ip=\"");
    String _ip = dataset.getIp();
    _builder.append(_ip, "\t");
    _builder.append("\",");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("port=");
    String _port = dataset.getPort();
    _builder.append(_port, "\t");
    _builder.append(",");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("query=\"\"\"");
    String _query = dataset.getQuery();
    _builder.append(_query, "\t");
    _builder.append("\"\"\" ,");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("script=None");
    _builder.newLine();
    _builder.append(")");
    _builder.newLine();
    return _builder;
  }

  public CharSequence generateDatasetClass() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class Dataset():");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def __init__(self,dbname,ip,port,query=None,script=None):");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("if query is not None:");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("client = InfluxDBClient(ip, int(port), database=dbname)");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("result = client.query(query)");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("points = result.get_points()");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("self.points=points");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("elif script is not None:");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("exec(script)");
    _builder.newLine();
    return _builder;
  }

  protected static CharSequence _generateDatasetInstance(final PythonSimulation dataset) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("Dataset(");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("dbname=None,");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("query=None,");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("ip=None,");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("port=None, ");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("script=r\"\"\"");
    _builder.newLine();
    String _script = dataset.getScript();
    _builder.append(_script);
    _builder.newLineIfNotEmpty();
    _builder.append("\"\"\"");
    _builder.newLine();
    _builder.append(")");
    return _builder;
  }

  protected static String _generateKernel(final BrownianKernel kernel) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("gp.kernels.Brownian(input_dim=1)");
    return _builder.toString();
  }

  protected static String _generateKernel(final PeriodicKernel kernel) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("gp.kernels.Periodic(input_dim=1)");
    return _builder.toString();
  }

  protected static String _generateKernel(final LinearKernel kernel) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("gp.kernels.Linear(input_dim=1) ");
    return _builder.toString();
  }

  protected static String _generateKernel(final RBFKernel kernel) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("gp.kernels.RBF(input_dim=1)");
    return _builder.toString();
  }

  protected static String _generateKernel(final SumKernel kernel) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("gp.kernels.Sum(");
    Object _generateKernel = PyroDistGenerator.generateKernel(kernel.getKernels().get(0));
    _builder.append(_generateKernel);
    _builder.append(",");
    Object _generateKernel_1 = PyroDistGenerator.generateKernel(kernel.getKernels().get(1));
    _builder.append(_generateKernel_1);
    _builder.append(")");
    return _builder.toString();
  }

  public String generateCategorical(final EnvironmentConnections connection) {
    StringConcatenation _builder = new StringConcatenation();
    final Function1<EnvironmentRule, StochasticRule> _function = (EnvironmentRule r) -> {
      return ((StochasticRule) r);
    };
    List<StochasticRule> rules = ListExtensions.<EnvironmentRule, StochasticRule>map(connection.component.getBehaviorRules(), _function);
    _builder.newLineIfNotEmpty();
    _builder.append("RandomVariable(dist=pyro.distributions.Categorical(torch.tensor([");
    _builder.newLine();
    {
      EList<Port> _outports = connection.component.getOutports();
      boolean _hasElements = false;
      for(final Port port : _outports) {
        if (!_hasElements) {
          _hasElements = true;
        } else {
          _builder.appendImmediate(", ", "\t\t");
        }
        _builder.append("\t\t");
        final Function1<StochasticRule, Boolean> _function_1 = (StochasticRule r) -> {
          final Function1<Filter, Port> _function_2 = (Filter f) -> {
            return ((PortFilter) f).getPort();
          };
          return Boolean.valueOf(ListExtensions.<Filter, Port>map(r.getFilter(), _function_2).contains(port));
        };
        final Function1<StochasticRule, CharSequence> _function_2 = (StochasticRule r) -> {
          StochasticModel _stochasticModel = r.getStochasticModel();
          return TransformationUtility.evaluateMixedExpression(((CategoricalProbabaility) _stochasticModel).getProbability());
        };
        CharSequence _get = ((CharSequence[])Conversions.unwrapArray(IterableExtensions.<StochasticRule, CharSequence>map(IterableExtensions.<StochasticRule>filter(rules, _function_1), _function_2), CharSequence.class))[0];
        _builder.append(_get, "\t\t");
        _builder.newLineIfNotEmpty();
        _builder.append("\t\t\t\t");
      }
    }
    _builder.append("])),");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("name=\"");
    String _firstUpper = StringExtensions.toFirstUpper(connection.component.getName());
    _builder.append(_firstUpper, "\t");
    String _string = (this.distcntr++).toString();
    _builder.append(_string, "\t");
    _builder.append("\")");
    _builder.newLineIfNotEmpty();
    final String str1 = _builder.toString();
    PyroDistGenerator.random_vars.add(str1);
    return str1;
  }

  public String generateClasses(final SimulationAnalysisMethod analysisMethod) {
    StringConcatenation _builder = new StringConcatenation();
    CharSequence _generateDatasetClass = this.generateDatasetClass();
    _builder.append(_generateDatasetClass);
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.newLine();
    _builder.append("# stochastic model classes");
    _builder.newLine();
    _builder.newLine();
    CharSequence _generateDiscreteRandomVariableClass = this.generateDiscreteRandomVariableClass();
    _builder.append(_generateDiscreteRandomVariableClass);
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    {
      Boolean _jointSampling = analysisMethod.getJointSampling();
      if ((_jointSampling).booleanValue()) {
        CharSequence _generateJointRandomVariableClass = this.generateJointRandomVariableClass(analysisMethod.getSamplingBatchSize());
        _builder.append(_generateJointRandomVariableClass);
        _builder.newLineIfNotEmpty();
      } else {
        CharSequence _generateRandomVariableClass = this.generateRandomVariableClass(analysisMethod.getSamplingBatchSize());
        _builder.append(_generateRandomVariableClass);
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.newLine();
    return _builder.toString();
  }

  public CharSequence generateStochasticModel(final StochasticModel variable, final String name) {
    if (variable instanceof FittedExponentialRandomVariable) {
      return _generateStochasticModel((FittedExponentialRandomVariable)variable, name);
    } else if (variable instanceof FittedNormalRandomVariable) {
      return _generateStochasticModel((FittedNormalRandomVariable)variable, name);
    } else if (variable instanceof FittedGaussianProcess) {
      return _generateStochasticModel((FittedGaussianProcess)variable, name);
    } else if (variable instanceof ContinouosRandomVariable) {
      return _generateStochasticModel((ContinouosRandomVariable)variable, name);
    } else if (variable instanceof DiscreteRandomVariable) {
      return _generateStochasticModel((DiscreteRandomVariable)variable, name);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(variable, name).toString());
    }
  }

  public String generateZerotimeStochasticModel(final DiscreteRandomVariable variable, final String name) {
    return _generateZerotimeStochasticModel(variable, name);
  }

  public static CharSequence generateDitribution(final StochasticModel dist) {
    if (dist instanceof BernoulliRandomVariable) {
      return _generateDitribution((BernoulliRandomVariable)dist);
    } else if (dist instanceof BetaRandomVariable) {
      return _generateDitribution((BetaRandomVariable)dist);
    } else if (dist instanceof ExponentialRandomVariable) {
      return _generateDitribution((ExponentialRandomVariable)dist);
    } else if (dist instanceof GammaRandomVariable) {
      return _generateDitribution((GammaRandomVariable)dist);
    } else if (dist instanceof LogNormalRandomVariable) {
      return _generateDitribution((LogNormalRandomVariable)dist);
    } else if (dist instanceof NormalRandomVariable) {
      return _generateDitribution((NormalRandomVariable)dist);
    } else if (dist instanceof ParetoRandomVariable) {
      return _generateDitribution((ParetoRandomVariable)dist);
    } else if (dist instanceof UniformRandomVariable) {
      return _generateDitribution((UniformRandomVariable)dist);
    } else if (dist instanceof WeibullRandomVariable) {
      return _generateDitribution((WeibullRandomVariable)dist);
    } else if (dist instanceof DiracProcess) {
      return _generateDitribution((DiracProcess)dist);
    } else if (dist instanceof RandomVariable) {
      return _generateDitribution((RandomVariable)dist);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(dist).toString());
    }
  }

  public static CharSequence generateStochasticProcess(final FittedGaussianProcess gp) {
    return _generateStochasticProcess(gp);
  }

  public static CharSequence generateDatasetInstance(final DataSource dataset) {
    if (dataset instanceof InfluxDB) {
      return _generateDatasetInstance((InfluxDB)dataset);
    } else if (dataset instanceof PythonSimulation) {
      return _generateDatasetInstance((PythonSimulation)dataset);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(dataset).toString());
    }
  }

  public static String generateKernel(final Kernel kernel) {
    if (kernel instanceof BrownianKernel) {
      return _generateKernel((BrownianKernel)kernel);
    } else if (kernel instanceof LinearKernel) {
      return _generateKernel((LinearKernel)kernel);
    } else if (kernel instanceof PeriodicKernel) {
      return _generateKernel((PeriodicKernel)kernel);
    } else if (kernel instanceof RBFKernel) {
      return _generateKernel((RBFKernel)kernel);
    } else if (kernel instanceof SumKernel) {
      return _generateKernel((SumKernel)kernel);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(kernel).toString());
    }
  }
}
