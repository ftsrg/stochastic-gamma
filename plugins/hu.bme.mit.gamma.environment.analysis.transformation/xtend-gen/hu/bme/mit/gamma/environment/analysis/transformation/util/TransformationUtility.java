package hu.bme.mit.gamma.environment.analysis.transformation.util;

import com.google.common.base.Objects;
import hu.bme.mit.gamma.environment.analysis.AnalysisAspect;
import hu.bme.mit.gamma.environment.analysis.AnalysisComponent;
import hu.bme.mit.gamma.environment.analysis.AnalysisCondition;
import hu.bme.mit.gamma.environment.analysis.EndCondition;
import hu.bme.mit.gamma.environment.analysis.Frequency;
import hu.bme.mit.gamma.environment.analysis.MeanParameter;
import hu.bme.mit.gamma.environment.analysis.MeanTime;
import hu.bme.mit.gamma.environment.analysis.Probability;
import hu.bme.mit.gamma.environment.analysis.RecursiveComponentReference;
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
    String _replaceAll = componentCall.replaceAll(".get", "");
    String _plus = (_replaceAll + ".");
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
    String _plus = (name + _firstUpper);
    String _firstUpper_1 = StringExtensions.toFirstUpper(aspect.getEvent().getEvent().getName());
    String _plus_1 = (_plus + _firstUpper_1);
    name = _plus_1;
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
    String _plus = (name + _firstUpper);
    String _firstUpper_1 = StringExtensions.toFirstUpper(endCondition.getEvent().getEvent().getName());
    String _plus_1 = (_plus + _firstUpper_1);
    name = _plus_1;
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

  public static CharSequence generateSimulationReturn(final List<AnalysisAspect> aspects) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("#register the result of the analysis to the Pyro");
    _builder.newLine();
    {
      for(final AnalysisAspect aspect : aspects) {
        CharSequence _generateGetValueCalls = TransformationUtility.generateGetValueCalls(aspect);
        _builder.append(_generateGetValueCalls);
        _builder.newLineIfNotEmpty();
        _builder.append("pyro.deterministic(\"");
        String _generateAspectName = TransformationUtility.generateAspectName(aspect);
        _builder.append(_generateAspectName);
        _builder.append("\",torch.tensor(");
        CharSequence _generateGetValueCalls_1 = TransformationUtility.generateGetValueCalls(aspect);
        _builder.append(_generateGetValueCalls_1);
        _builder.append("))");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("#return the result of the simulation");
    _builder.newLine();
    _builder.append("return ");
    {
      for(final AnalysisAspect aspect_1 : aspects) {
        CharSequence _generateGetValueCalls_2 = TransformationUtility.generateGetValueCalls(aspect_1);
        _builder.append(_generateGetValueCalls_2);
      }
    }
    _builder.newLineIfNotEmpty();
    return _builder;
  }

  protected static CharSequence _generateGetValueCalls(final MeanTime aspect) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("stochmodel.time");
    return _builder;
  }

  protected static CharSequence _generateGetValueCalls(final Probability aspect) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("state2num(detmodel.monitorOf");
    String _generateAspectName = TransformationUtility.generateAspectName(aspect);
    _builder.append(_generateAspectName);
    _builder.append(".state)");
    return _builder;
  }

  protected static CharSequence _generateGetValueCalls(final MeanParameter aspect) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("detmodel.monitorOf");
    String _generateAspectName = TransformationUtility.generateAspectName(aspect);
    _builder.append(_generateAspectName);
    _builder.append(".meanParameter()");
    return _builder;
  }

  protected static CharSequence _generateGetValueCalls(final Frequency aspect) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("detmodel.monitorOf");
    String _generateAspectName = TransformationUtility.generateAspectName(aspect);
    _builder.append(_generateAspectName);
    _builder.append(".freq()");
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

  public static CharSequence generateGetValueCalls(final AnalysisAspect aspect) {
    if (aspect instanceof Frequency) {
      return _generateGetValueCalls((Frequency)aspect);
    } else if (aspect instanceof MeanParameter) {
      return _generateGetValueCalls((MeanParameter)aspect);
    } else if (aspect instanceof MeanTime) {
      return _generateGetValueCalls((MeanTime)aspect);
    } else if (aspect instanceof Probability) {
      return _generateGetValueCalls((Probability)aspect);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(aspect).toString());
    }
  }
}
