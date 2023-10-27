package hu.bme.mit.gamma.environment.analysis.transformation.util;

import com.google.common.base.Objects;
import hu.bme.mit.gamma.environment.analysis.AnalysisAspect;
import hu.bme.mit.gamma.environment.analysis.AnalysisComponent;
import hu.bme.mit.gamma.environment.analysis.AnalysisCondition;
import hu.bme.mit.gamma.environment.analysis.AssumeCondition;
import hu.bme.mit.gamma.environment.analysis.AssumeNotRaised;
import hu.bme.mit.gamma.environment.analysis.AssumeRaised;
import hu.bme.mit.gamma.environment.analysis.EndCondition;
import hu.bme.mit.gamma.environment.analysis.Frequency;
import hu.bme.mit.gamma.environment.analysis.MeanParameter;
import hu.bme.mit.gamma.environment.analysis.MeanTime;
import hu.bme.mit.gamma.environment.analysis.MeanTimeBetweenEvents;
import hu.bme.mit.gamma.environment.analysis.ObserveCondition;
import hu.bme.mit.gamma.environment.analysis.ObserveParameter;
import hu.bme.mit.gamma.environment.analysis.ObserveTime;
import hu.bme.mit.gamma.environment.analysis.Probability;
import hu.bme.mit.gamma.environment.analysis.RecursiveComponentReference;
import hu.bme.mit.gamma.environment.analysis.transformation.pythongen.PyroDistGenerator;
import hu.bme.mit.gamma.environment.model.ElementaryEnvironmentComponentInstance;
import hu.bme.mit.gamma.environment.stochastic.stochastic.StochasticExpression;
import hu.bme.mit.gamma.expression.model.DirectReferenceExpression;
import hu.bme.mit.gamma.expression.model.Expression;
import hu.bme.mit.gamma.expression.model.ParameterDeclaration;
import hu.bme.mit.gamma.expression.util.ExpressionEvaluator;
import hu.bme.mit.gamma.expression.util.TypeSerializer;
import hu.bme.mit.gamma.statechart.interface_.Event;
import java.util.Arrays;
import java.util.List;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.StringExtensions;

@SuppressWarnings("all")
public class TransformationUtility {
  public static CharSequence generateFuncParams(final Event event) {
    StringConcatenation _builder = new StringConcatenation();
    {
      EList<ParameterDeclaration> _parameterDeclarations = event.getParameterDeclarations();
      boolean _hasElements = false;
      for(final ParameterDeclaration parameter : _parameterDeclarations) {
        if (!_hasElements) {
          _hasElements = true;
        } else {
          _builder.appendImmediate(", ", "");
        }
        String _firstLower = StringExtensions.toFirstLower(parameter.getName());
        _builder.append(_firstLower);
      }
    }
    return _builder;
  }

  public static String generateEnvCompName(final EnvironmentConnections connection) {
    return TransformationUtility.generateEnvCompName(connection.componentCall, connection.component);
  }

  public static String generateEnvCompName(final String componentCall, final ElementaryEnvironmentComponentInstance component) {
    String _replaceAll = componentCall.replaceFirst(".get", "").replaceAll(".get", ".");
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("\\(\\)");
    String _replaceAll_1 = _replaceAll.replaceAll(_builder.toString(), "");
    String _plus = (_replaceAll_1 + ".");
    String _name = component.getName();
    return (_plus + _name);
  }

  public static String generateAspectName(final AnalysisAspect aspect) {
    RecursiveComponentReference compRef = aspect.getEvent().getComponent();
    String name = "Aspect";
    if ((aspect instanceof AnalysisCondition)) {
      name = "Condition";
    }
    while ((!(compRef == null))) {
      {
        String _firstUpper = StringExtensions.toFirstUpper(compRef.getComponent().getName());
        String _plus = (name + _firstUpper);
        name = _plus;
        compRef = compRef.getRecursivecomponentreference();
      }
    }
    String _firstUpper = StringExtensions.toFirstUpper(aspect.getEvent().getPort().getName());
    String _plus = ((name + "_") + _firstUpper);
    String _plus_1 = (_plus + "_");
    String _firstUpper_1 = StringExtensions.toFirstUpper(aspect.getEvent().getEvent().getName());
    String _plus_2 = (_plus_1 + _firstUpper_1);
    name = _plus_2;
    return name;
  }

  public static String generateEndConditionName(final EndCondition endCondition) {
    RecursiveComponentReference compRef = endCondition.getEvent().getComponent();
    String name = "EndCondition";
    while ((!Objects.equal(compRef, null))) {
      {
        String _firstUpper = StringExtensions.toFirstUpper(compRef.getComponent().getName());
        String _plus = (name + _firstUpper);
        name = _plus;
        compRef = compRef.getRecursivecomponentreference();
      }
    }
    String _firstUpper = StringExtensions.toFirstUpper(endCondition.getEvent().getPort().getName());
    String _plus = ((name + "_") + _firstUpper);
    String _plus_1 = (_plus + "_");
    String _firstUpper_1 = StringExtensions.toFirstUpper(endCondition.getEvent().getEvent().getName());
    String _plus_2 = (_plus_1 + _firstUpper_1);
    name = _plus_2;
    return name;
  }

  public static CharSequence generateParameters(final Event event) {
    StringConcatenation _builder = new StringConcatenation();
    {
      EList<ParameterDeclaration> _parameterDeclarations = event.getParameterDeclarations();
      boolean _hasElements = false;
      for(final ParameterDeclaration parameter : _parameterDeclarations) {
        if (!_hasElements) {
          _hasElements = true;
        } else {
          _builder.appendImmediate(", ", "");
        }
        String _transformType = TransformationUtility.transformType(TypeSerializer.INSTANCE.serialize(parameter.getType()));
        _builder.append(_transformType);
        _builder.append(" ");
        CharSequence _generateName = TransformationUtility.generateName(parameter);
        _builder.append(_generateName);
      }
    }
    return _builder;
  }

  public static String transformType(final String type) {
    if (type != null) {
      switch (type) {
        case "integer":
          return "long";
        case "string":
          return "String";
        case "real":
          return "double";
        default:
          return type;
      }
    } else {
      return type;
    }
  }

  public static CharSequence generateName(final ParameterDeclaration parameter) {
    StringConcatenation _builder = new StringConcatenation();
    String _firstLower = StringExtensions.toFirstLower(parameter.getName());
    _builder.append(_firstLower);
    return _builder;
  }

  public static CharSequence generatePyroAspectRegistry(final List<AnalysisAspect> aspects) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.newLine();
    _builder.append("#register the result of the analysis to the Pyro");
    _builder.newLine();
    {
      for(final AnalysisAspect aspect : aspects) {
        {
          if ((aspect instanceof MeanTime)) {
            _builder.append("# register the time only if the event is raised");
            _builder.newLine();
            _builder.append("if str(detmodel.monitorOf");
            String _generateAspectName = TransformationUtility.generateAspectName(aspect);
            _builder.append(_generateAspectName);
            _builder.append(".state) != \"run\" :");
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            _builder.append("pyro.deterministic(\"");
            CharSequence _pyroName = TransformationUtility.pyroName(aspect);
            _builder.append(_pyroName, "\t");
            _builder.append("\",torch.tensor(");
            CharSequence _valueCall = TransformationUtility.getValueCall(aspect);
            _builder.append(_valueCall, "\t");
            _builder.append("))");
            _builder.newLineIfNotEmpty();
          } else {
            _builder.append("pyro.deterministic(\"");
            CharSequence _pyroName_1 = TransformationUtility.pyroName(aspect);
            _builder.append(_pyroName_1);
            _builder.append("\",torch.tensor(");
            CharSequence _valueCall_1 = TransformationUtility.getValueCall(aspect);
            _builder.append(_valueCall_1);
            _builder.append("))");
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    return _builder;
  }

  public static CharSequence generateDebugAspectRegistry(final List<AnalysisAspect> aspects) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("#register the result of the analysis to the Pyro");
    _builder.newLine();
    {
      for(final AnalysisAspect aspect : aspects) {
        _builder.append("if DEBUG:");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("# register the time only if the event is raised");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("if int(detmodel.monitorOf");
        String _generateAspectName = TransformationUtility.generateAspectName(aspect);
        _builder.append(_generateAspectName, "\t");
        _builder.append(".freq) != ");
        String _generateAspectName_1 = TransformationUtility.generateAspectName(aspect);
        _builder.append(_generateAspectName_1, "\t");
        _builder.append("Freq :");
        _builder.newLineIfNotEmpty();
        _builder.append("\t\t");
        String _generateAspectName_2 = TransformationUtility.generateAspectName(aspect);
        _builder.append(_generateAspectName_2, "\t\t");
        _builder.append("Freq=int(detmodel.monitorOf");
        String _generateAspectName_3 = TransformationUtility.generateAspectName(aspect);
        _builder.append(_generateAspectName_3, "\t\t");
        _builder.append(".freq)");
        _builder.newLineIfNotEmpty();
        _builder.append("\t\t");
        _builder.append("dprint(f\'detmodel -> analysis : \"");
        String _name = aspect.getEvent().getPort().getName();
        _builder.append(_name, "\t\t");
        _builder.append(".");
        String _name_1 = aspect.getEvent().getEvent().getName();
        _builder.append(_name_1, "\t\t");
        _builder.append(" at time {stochmodel.time}\"\')");
        _builder.newLineIfNotEmpty();
      }
    }
    return _builder;
  }

  public static CharSequence generateDebugAspectVars(final List<AnalysisAspect> aspects) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("# DEBUG variables");
    _builder.newLine();
    {
      for(final AnalysisAspect aspect : aspects) {
        String _generateAspectName = TransformationUtility.generateAspectName(aspect);
        _builder.append(_generateAspectName);
        _builder.append("Freq=0");
        _builder.newLineIfNotEmpty();
      }
    }
    return _builder;
  }

  public static CharSequence generatePyroConditionRegistry(final List<AnalysisCondition> conditions) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.newLine();
    _builder.append("#register the conditions to the Pyro");
    _builder.newLine();
    {
      for(final AnalysisCondition condition : conditions) {
        CharSequence _registerCondition = TransformationUtility.registerCondition(condition);
        _builder.append(_registerCondition);
        _builder.newLineIfNotEmpty();
      }
    }
    return _builder;
  }

  protected static CharSequence _registerCondition(final ObserveCondition condition) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("pyro.sample(\"");
    CharSequence _pyroName = TransformationUtility.pyroName(condition);
    _builder.append(_pyroName);
    _builder.append("\",");
    CharSequence _generateDitribution = PyroDistGenerator.generateDitribution(condition.getRandomvariable());
    _builder.append(_generateDitribution);
    _builder.append(", obs = torch.tensor(");
    CharSequence _valueCall = TransformationUtility.getValueCall(condition);
    _builder.append(_valueCall);
    _builder.append("))");
    return _builder;
  }

  protected static CharSequence _registerCondition(final AssumeRaised condition) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("pyro.sample(\"");
    CharSequence _pyroName = TransformationUtility.pyroName(condition);
    _builder.append(_pyroName);
    _builder.append("\",pyro.distributions.Bernoulli(torch.tensor(0.999)), obs = torch.tensor(");
    CharSequence _valueCall = TransformationUtility.getValueCall(condition);
    _builder.append(_valueCall);
    _builder.append("))");
    return _builder;
  }

  protected static CharSequence _registerCondition(final AssumeNotRaised condition) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("pyro.sample(\"");
    CharSequence _pyroName = TransformationUtility.pyroName(condition);
    _builder.append(_pyroName);
    _builder.append("\",pyro.distributions.Bernoulli(torch.tensor(0.0001)), obs = torch.tensor(");
    CharSequence _valueCall = TransformationUtility.getValueCall(condition);
    _builder.append(_valueCall);
    _builder.append("))");
    return _builder;
  }

  protected static CharSequence _registerCondition(final AnalysisCondition condition) {
    String _string = condition.getClass().toString();
    String _plus = (_string + " type of condition is not supported yet.");
    throw new UnsupportedOperationException(_plus);
  }

  public static CharSequence generateSimulationReturn(final List<AnalysisAspect> aspects) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.newLine();
    _builder.append("#return the result of the simulation");
    _builder.newLine();
    _builder.append("return ");
    {
      boolean _hasElements = false;
      for(final AnalysisAspect aspect : aspects) {
        if (!_hasElements) {
          _hasElements = true;
        } else {
          _builder.appendImmediate(", ", "");
        }
        CharSequence _valueCall = TransformationUtility.getValueCall(aspect);
        _builder.append(_valueCall);
      }
    }
    _builder.newLineIfNotEmpty();
    return _builder;
  }

  protected static CharSequence _getValueCall(final MeanTime aspect) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("stochmodel.time");
    return _builder;
  }

  protected static CharSequence _getValueCall(final Probability aspect) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("state2num(detmodel.monitorOf");
    String _generateAspectName = TransformationUtility.generateAspectName(aspect);
    _builder.append(_generateAspectName);
    _builder.append(".state)");
    return _builder;
  }

  protected static CharSequence _getValueCall(final MeanParameter aspect) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("detmodel.monitorOf");
    String _generateAspectName = TransformationUtility.generateAspectName(aspect);
    _builder.append(_generateAspectName);
    _builder.append(".meanParameter()");
    return _builder;
  }

  protected static CharSequence _getValueCall(final Frequency aspect) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("detmodel.monitorOf");
    String _generateAspectName = TransformationUtility.generateAspectName(aspect);
    _builder.append(_generateAspectName);
    _builder.append(".freq()");
    return _builder;
  }

  protected static CharSequence _getValueCall(final ObserveTime aspect) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("stochmodel.time");
    return _builder;
  }

  protected static CharSequence _getValueCall(final ObserveParameter aspect) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("detmodel.monitorOf");
    String _generateAspectName = TransformationUtility.generateAspectName(aspect);
    _builder.append(_generateAspectName);
    _builder.append(".meanParameter()");
    return _builder;
  }

  protected static CharSequence _getValueCall(final AssumeCondition aspect) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("state2num(detmodel.monitorOf");
    String _generateAspectName = TransformationUtility.generateAspectName(aspect);
    _builder.append(_generateAspectName);
    _builder.append(".state)");
    return _builder;
  }

  public static CharSequence evaluateMixedExpression(final Expression expression) {
    CharSequence _xblockexpression = null;
    {
      ExpressionEvaluator expEval = ExpressionEvaluator.INSTANCE;
      StringConcatenation _builder = new StringConcatenation();
      {
        if ((expression instanceof DirectReferenceExpression)) {
          _builder.append("self.");
          String _name = ((DirectReferenceExpression)expression).getDeclaration().getName();
          _builder.append(_name);
          _builder.append("[0]");
        } else {
          _builder.append("torch.tensor(");
          String _string = Double.toString(expEval.evaluateDecimal(expression));
          _builder.append(_string);
          _builder.append(")");
        }
      }
      _xblockexpression = _builder;
    }
    return _xblockexpression;
  }

  public static CharSequence generateDetmodelParamsInit(final AnalysisComponent component) {
    CharSequence _xblockexpression = null;
    {
      ExpressionEvaluator expEval = ExpressionEvaluator.INSTANCE;
      StringConcatenation _builder = new StringConcatenation();
      {
        EList<Expression> _arguments = component.getAnalyzedComponent().getArguments();
        boolean _hasElements = false;
        for(final Expression arg : _arguments) {
          if (!_hasElements) {
            _hasElements = true;
          } else {
            _builder.appendImmediate(", ", "");
          }
          {
            if ((arg instanceof StochasticExpression)) {
              _builder.append("0.0");
            } else {
              String _string = Double.toString(expEval.evaluate(arg));
              _builder.append(_string);
            }
          }
        }
      }
      _builder.newLineIfNotEmpty();
      _xblockexpression = _builder;
    }
    return _xblockexpression;
  }

  public static CharSequence generateDetmodelParamsReset(final AnalysisComponent component) {
    StringConcatenation _builder = new StringConcatenation();
    {
      EList<ParameterDeclaration> _parameterDeclarations = component.getAnalyzedComponent().getType().getParameterDeclarations();
      boolean _hasElements = false;
      for(final ParameterDeclaration param : _parameterDeclarations) {
        if (!_hasElements) {
          _hasElements = true;
        } else {
          _builder.appendImmediate(", ", "");
        }
        String _transformType = TransformationUtility.transformType(TypeSerializer.INSTANCE.serialize(param.getType()));
        _builder.append(_transformType);
        _builder.append(" ");
        String _name = param.getName();
        _builder.append(_name);
      }
    }
    _builder.newLineIfNotEmpty();
    return _builder;
  }

  public static CharSequence generateDetmodelParamsResetInit(final AnalysisComponent component) {
    StringConcatenation _builder = new StringConcatenation();
    {
      EList<ParameterDeclaration> _parameterDeclarations = component.getAnalyzedComponent().getType().getParameterDeclarations();
      boolean _hasElements = false;
      for(final ParameterDeclaration param : _parameterDeclarations) {
        if (!_hasElements) {
          _hasElements = true;
        } else {
          _builder.appendImmediate(", ", "");
        }
        String _name = param.getName();
        _builder.append(_name);
      }
    }
    _builder.newLineIfNotEmpty();
    return _builder;
  }

  private static int i = 0;

  public static int increment() {
    return TransformationUtility.i = (TransformationUtility.i + 1);
  }

  public static CharSequence generateDetmodelParamsNew(final AnalysisComponent component) {
    CharSequence _xblockexpression = null;
    {
      ExpressionEvaluator expEval = ExpressionEvaluator.INSTANCE;
      TransformationUtility.i = 0;
      StringConcatenation _builder = new StringConcatenation();
      {
        EList<Expression> _arguments = component.getAnalyzedComponent().getArguments();
        boolean _hasElements = false;
        for(final Expression arg : _arguments) {
          if (!_hasElements) {
            _hasElements = true;
          } else {
            _builder.appendImmediate(", ", "");
          }
          {
            if ((arg instanceof StochasticExpression)) {
              _builder.append("self.");
              String _name = component.getAnalyzedComponent().getType().getParameterDeclarations().get(TransformationUtility.i).getName();
              _builder.append(_name);
            } else {
              String _string = Double.toString(expEval.evaluate(arg));
              _builder.append(_string);
            }
          }
          _builder.append("#");
          int _increment = TransformationUtility.increment();
          _builder.append(_increment);
        }
      }
      _builder.newLineIfNotEmpty();
      _xblockexpression = _builder;
    }
    return _xblockexpression;
  }

  protected static CharSequence _pyroName(final Frequency aspect) {
    StringConcatenation _builder = new StringConcatenation();
    String _name = aspect.getEvent().getPort().getName();
    _builder.append(_name);
    _builder.append("_");
    String _name_1 = aspect.getEvent().getEvent().getName();
    _builder.append(_name_1);
    _builder.append("_freq");
    return _builder;
  }

  protected static CharSequence _pyroName(final MeanTime aspect) {
    StringConcatenation _builder = new StringConcatenation();
    String _name = aspect.getEvent().getPort().getName();
    _builder.append(_name);
    _builder.append("_");
    String _name_1 = aspect.getEvent().getEvent().getName();
    _builder.append(_name_1);
    _builder.append("_mt");
    return _builder;
  }

  protected static CharSequence _pyroName(final MeanParameter aspect) {
    StringConcatenation _builder = new StringConcatenation();
    String _name = aspect.getEvent().getPort().getName();
    _builder.append(_name);
    _builder.append("_");
    String _name_1 = aspect.getEvent().getEvent().getName();
    _builder.append(_name_1);
    _builder.append("_mp");
    return _builder;
  }

  protected static CharSequence _pyroName(final MeanTimeBetweenEvents aspect) {
    StringConcatenation _builder = new StringConcatenation();
    String _name = aspect.getEvent().getPort().getName();
    _builder.append(_name);
    _builder.append("_");
    String _name_1 = aspect.getEvent().getEvent().getName();
    _builder.append(_name_1);
    _builder.append("_mtbe");
    return _builder;
  }

  protected static CharSequence _pyroName(final Probability aspect) {
    StringConcatenation _builder = new StringConcatenation();
    String _name = aspect.getEvent().getPort().getName();
    _builder.append(_name);
    _builder.append("_");
    String _name_1 = aspect.getEvent().getEvent().getName();
    _builder.append(_name_1);
    _builder.append("_prob");
    return _builder;
  }

  protected static CharSequence _pyroName(final ObserveParameter aspect) {
    StringConcatenation _builder = new StringConcatenation();
    String _name = aspect.getEvent().getPort().getName();
    _builder.append(_name);
    _builder.append("_");
    String _name_1 = aspect.getEvent().getEvent().getName();
    _builder.append(_name_1);
    _builder.append("_cond_op");
    return _builder;
  }

  protected static CharSequence _pyroName(final ObserveTime aspect) {
    StringConcatenation _builder = new StringConcatenation();
    String _name = aspect.getEvent().getPort().getName();
    _builder.append(_name);
    _builder.append("_");
    String _name_1 = aspect.getEvent().getEvent().getName();
    _builder.append(_name_1);
    _builder.append("_cond_ot");
    return _builder;
  }

  protected static CharSequence _pyroName(final AssumeRaised aspect) {
    StringConcatenation _builder = new StringConcatenation();
    String _name = aspect.getEvent().getPort().getName();
    _builder.append(_name);
    _builder.append("_");
    String _name_1 = aspect.getEvent().getEvent().getName();
    _builder.append(_name_1);
    _builder.append("_cond_ar");
    return _builder;
  }

  protected static CharSequence _pyroName(final AssumeNotRaised aspect) {
    StringConcatenation _builder = new StringConcatenation();
    String _name = aspect.getEvent().getPort().getName();
    _builder.append(_name);
    _builder.append("_");
    String _name_1 = aspect.getEvent().getEvent().getName();
    _builder.append(_name_1);
    _builder.append("_cond_anr");
    return _builder;
  }

  protected static CharSequence _pyroName(final AnalysisAspect aspect) {
    String _genericString = aspect.getClass().toGenericString();
    String _plus = (_genericString + " type of AnalysisAspect is not supported yet. ");
    throw new UnsupportedOperationException(_plus);
  }

  public static CharSequence registerCondition(final AnalysisCondition condition) {
    if (condition instanceof AssumeNotRaised) {
      return _registerCondition((AssumeNotRaised)condition);
    } else if (condition instanceof AssumeRaised) {
      return _registerCondition((AssumeRaised)condition);
    } else if (condition instanceof ObserveCondition) {
      return _registerCondition((ObserveCondition)condition);
    } else if (condition != null) {
      return _registerCondition(condition);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(condition).toString());
    }
  }

  public static CharSequence getValueCall(final AnalysisAspect aspect) {
    if (aspect instanceof ObserveParameter) {
      return _getValueCall((ObserveParameter)aspect);
    } else if (aspect instanceof ObserveTime) {
      return _getValueCall((ObserveTime)aspect);
    } else if (aspect instanceof AssumeCondition) {
      return _getValueCall((AssumeCondition)aspect);
    } else if (aspect instanceof Frequency) {
      return _getValueCall((Frequency)aspect);
    } else if (aspect instanceof MeanParameter) {
      return _getValueCall((MeanParameter)aspect);
    } else if (aspect instanceof MeanTime) {
      return _getValueCall((MeanTime)aspect);
    } else if (aspect instanceof Probability) {
      return _getValueCall((Probability)aspect);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(aspect).toString());
    }
  }

  public static CharSequence pyroName(final AnalysisAspect aspect) {
    if (aspect instanceof AssumeNotRaised) {
      return _pyroName((AssumeNotRaised)aspect);
    } else if (aspect instanceof AssumeRaised) {
      return _pyroName((AssumeRaised)aspect);
    } else if (aspect instanceof ObserveParameter) {
      return _pyroName((ObserveParameter)aspect);
    } else if (aspect instanceof ObserveTime) {
      return _pyroName((ObserveTime)aspect);
    } else if (aspect instanceof Frequency) {
      return _pyroName((Frequency)aspect);
    } else if (aspect instanceof MeanParameter) {
      return _pyroName((MeanParameter)aspect);
    } else if (aspect instanceof MeanTime) {
      return _pyroName((MeanTime)aspect);
    } else if (aspect instanceof MeanTimeBetweenEvents) {
      return _pyroName((MeanTimeBetweenEvents)aspect);
    } else if (aspect instanceof Probability) {
      return _pyroName((Probability)aspect);
    } else if (aspect != null) {
      return _pyroName(aspect);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(aspect).toString());
    }
  }
}
