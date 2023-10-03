package hu.bme.mit.gamma.environment.analysis.transformation.pythongen;

import com.google.common.base.Objects;
import hu.bme.mit.gamma.environment.analysis.transformation.util.EnvironmentConnections;
import hu.bme.mit.gamma.environment.analysis.transformation.util.TransformationUtility;
import hu.bme.mit.gamma.environment.model.ComponentFilter;
import hu.bme.mit.gamma.environment.model.EnvironmentDelay;
import hu.bme.mit.gamma.environment.model.EnvironmentEventSource;
import hu.bme.mit.gamma.environment.model.EnvironmentPeriodicEventSource;
import hu.bme.mit.gamma.environment.model.EnvironmentRule;
import hu.bme.mit.gamma.environment.model.EnvironmentSample;
import hu.bme.mit.gamma.environment.model.EnvironmentSwitch;
import hu.bme.mit.gamma.environment.model.EventFilter;
import hu.bme.mit.gamma.environment.model.Filter;
import hu.bme.mit.gamma.environment.model.ParameterFilter;
import hu.bme.mit.gamma.environment.model.PortFilter;
import hu.bme.mit.gamma.environment.model.SimulationRule;
import hu.bme.mit.gamma.environment.model.StochasticRule;
import hu.bme.mit.gamma.expression.model.ParameterDeclaration;
import hu.bme.mit.gamma.expression.util.ExpressionEvaluator;
import hu.bme.mit.gamma.statechart.derivedfeatures.StatechartModelDerivedFeatures;
import hu.bme.mit.gamma.statechart.interface_.Event;
import hu.bme.mit.gamma.statechart.interface_.Interface;
import hu.bme.mit.gamma.statechart.interface_.Port;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.StringExtensions;

@SuppressWarnings("all")
public class PyroComponentInstanceGenerator {
  private static Integer cntr = Integer.valueOf(0);

  private final String packageName;

  private final ExpressionEvaluator expEval;

  private PyroDistGenerator distGenerator;

  public PyroComponentInstanceGenerator(final String packageName) {
    this.packageName = packageName;
    this.expEval = ExpressionEvaluator.INSTANCE;
    PyroDistGenerator _pyroDistGenerator = new PyroDistGenerator();
    this.distGenerator = _pyroDistGenerator;
  }

  protected CharSequence _generateRule(final StochasticRule rule, final String name) {
    return this.distGenerator.generateStochasticModel(rule.getStochasticModel(), name);
  }

  protected CharSequence _generateRule(final SimulationRule rule, final String name) {
    throw new UnsupportedOperationException("Simulation rule is not supported yet in sample.");
  }

  protected CharSequence _generateRule(final EnvironmentRule rule, final String name) {
    throw new UnsupportedOperationException("Simulation rule is not supported yet in sample.");
  }

  public String generateRules(final EnvironmentConnections connection) {
    StringBuilder builder = new StringBuilder();
    EnvironmentRule rule = null;
    builder.append("{");
    EList<Port> _outports = connection.component.getOutports();
    for (final Port port : _outports) {
      {
        builder.append("\'").append(StringExtensions.toFirstUpper(port.getName())).append("\' : {");
        List<Event> _outputEvents = StatechartModelDerivedFeatures.getOutputEvents(port);
        for (final Event event : _outputEvents) {
          {
            final Function1<EnvironmentRule, Boolean> _function = (EnvironmentRule r) -> {
              final Function1<Filter, Boolean> _function_1 = (Filter f) -> {
                return Boolean.valueOf((f instanceof EventFilter));
              };
              final Function1<Filter, Boolean> _function_2 = (Filter f) -> {
                return Boolean.valueOf((Objects.equal(((EventFilter) f).getEvent().getEvent(), event) && 
                  Objects.equal(((EventFilter) f).getEvent().getPort(), port)));
              };
              boolean _isEmpty = IterableExtensions.isEmpty(IterableExtensions.<Filter>filter(IterableExtensions.<Filter>filter(r.getFilter(), _function_1), _function_2));
              return Boolean.valueOf((!_isEmpty));
            };
            List<EnvironmentRule> rules = IterableExtensions.<EnvironmentRule>toList(IterableExtensions.<EnvironmentRule>filter(connection.component.getBehaviorRules(), _function));
            boolean _isEmpty = rules.isEmpty();
            if (_isEmpty) {
              final Function1<EnvironmentRule, Boolean> _function_1 = (EnvironmentRule r) -> {
                final Function1<Filter, Boolean> _function_2 = (Filter f) -> {
                  return Boolean.valueOf((f instanceof PortFilter));
                };
                final Function1<Filter, Boolean> _function_3 = (Filter f) -> {
                  Port _port = ((PortFilter) f).getPort();
                  return Boolean.valueOf(Objects.equal(_port, port));
                };
                boolean _isEmpty_1 = IterableExtensions.isEmpty(IterableExtensions.<Filter>filter(IterableExtensions.<Filter>filter(r.getFilter(), _function_2), _function_3));
                return Boolean.valueOf((!_isEmpty_1));
              };
              rules = IterableExtensions.<EnvironmentRule>toList(IterableExtensions.<EnvironmentRule>filter(connection.component.getBehaviorRules(), _function_1));
            }
            boolean _isEmpty_1 = rules.isEmpty();
            if (_isEmpty_1) {
              final Function1<EnvironmentRule, Boolean> _function_2 = (EnvironmentRule r) -> {
                final Function1<Filter, Boolean> _function_3 = (Filter f) -> {
                  return Boolean.valueOf((f instanceof ComponentFilter));
                };
                boolean _isEmpty_2 = IterableExtensions.isEmpty(IterableExtensions.<Filter>filter(r.getFilter(), _function_3));
                return Boolean.valueOf((!_isEmpty_2));
              };
              rules = IterableExtensions.<EnvironmentRule>toList(IterableExtensions.<EnvironmentRule>filter(connection.component.getBehaviorRules(), _function_2));
            }
            boolean _isEmpty_2 = rules.isEmpty();
            boolean _not = (!_isEmpty_2);
            if (_not) {
              rule = rules.get(0);
              builder.append("\'").append(StringExtensions.toFirstUpper(event.getName())).append("\' : ");
              builder.append(this.generateRule(rule, connection.component.getName())).append(",");
            }
          }
        }
        builder.append("},");
      }
    }
    builder.append("}");
    return builder.toString().replaceAll(",,", ",").replaceAll(",}", "}").replaceAll(", }", "}");
  }

  public String generateSampleRules(final EnvironmentConnections connection) {
    StringBuilder builder = new StringBuilder();
    builder.append("{");
    EList<Port> _outports = connection.component.getOutports();
    for (final Port port : _outports) {
      {
        builder.append("\'").append(StringExtensions.toFirstUpper(port.getName())).append("\' : {");
        List<Event> _outputEvents = StatechartModelDerivedFeatures.getOutputEvents(port);
        for (final Event event : _outputEvents) {
          {
            builder.append("\'").append(StringExtensions.toFirstUpper(event.getName())).append("\' : {");
            EList<ParameterDeclaration> _parameterDeclarations = event.getParameterDeclarations();
            for (final ParameterDeclaration param : _parameterDeclarations) {
              {
                final Function1<EnvironmentRule, Boolean> _function = (EnvironmentRule r) -> {
                  final Function1<Filter, Boolean> _function_1 = (Filter f) -> {
                    return Boolean.valueOf((f instanceof EventFilter));
                  };
                  final Function1<Filter, Boolean> _function_2 = (Filter f) -> {
                    return Boolean.valueOf(((Objects.equal(((ParameterFilter) f).getEvent().getEvent(), event) && 
                      Objects.equal(((ParameterFilter) f).getEvent().getPort(), port)) && 
                      Objects.equal(((ParameterFilter) f).getParameter(), param)));
                  };
                  boolean _isEmpty = IterableExtensions.isEmpty(IterableExtensions.<Filter>filter(IterableExtensions.<Filter>filter(r.getFilter(), _function_1), _function_2));
                  return Boolean.valueOf((!_isEmpty));
                };
                List<EnvironmentRule> rules = IterableExtensions.<EnvironmentRule>toList(IterableExtensions.<EnvironmentRule>filter(connection.component.getBehaviorRules(), _function));
                boolean _isEmpty = rules.isEmpty();
                boolean _not = (!_isEmpty);
                if (_not) {
                  EnvironmentRule rule = rules.get(0);
                  builder.append("\'").append(StringExtensions.toFirstLower(param.getName())).append("\'");
                  builder.append(" : ");
                  builder.append(this.generateRule(rule, connection.component.getName()));
                  builder.append(",");
                }
              }
            }
            builder.append("},");
          }
        }
        builder.append("},");
      }
    }
    builder.append("}");
    return builder.toString().replaceAll(",,", ",").replaceAll(",}", "}").replaceAll(", }", "}");
  }

  public CharSequence generateSimulationInstances(final List<EnvironmentConnections> connections) {
    StringConcatenation _builder = new StringConcatenation();
    final Function1<EnvironmentConnections, EList<EnvironmentRule>> _function = (EnvironmentConnections c) -> {
      return c.component.getBehaviorRules();
    };
    final Function1<EnvironmentRule, Boolean> _function_1 = (EnvironmentRule r) -> {
      return Boolean.valueOf((r instanceof SimulationRule));
    };
    final Function1<EnvironmentRule, SimulationRule> _function_2 = (EnvironmentRule r) -> {
      return ((SimulationRule) r);
    };
    Iterable<SimulationRule> simrules = IterableExtensions.<EnvironmentRule, SimulationRule>map(IterableExtensions.<EnvironmentRule>filter(IterableExtensions.<EnvironmentConnections, EnvironmentRule>flatMap(connections, _function), _function_1), _function_2);
    _builder.newLineIfNotEmpty();
    {
      for(final SimulationRule simrule : simrules) {
        String _simulationClassName = simrule.getSimulation().getSimulationClassName();
        _builder.append(_simulationClassName);
        _builder.append("Instance = ");
        String _simulationClassName_1 = simrule.getSimulation().getSimulationClassName();
        _builder.append(_simulationClassName_1);
        _builder.append("()");
        _builder.newLineIfNotEmpty();
      }
    }
    return _builder;
  }

  public String generateCalls(final EnvironmentConnections connection) {
    StringBuilder builder = new StringBuilder();
    builder.append("{");
    boolean _isEmpty = connection.outCalls.keys().isEmpty();
    boolean _not = (!_isEmpty);
    if (_not) {
      EList<Port> _outports = connection.component.getOutports();
      for (final Port port : _outports) {
        {
          builder.append("\'").append(StringExtensions.toFirstUpper(port.getName())).append("\' : [");
          boolean _isEmpty_1 = connection.outCalls.get(port).isEmpty();
          if (_isEmpty_1) {
            builder.append("None");
          } else {
            Collection<String> _get = connection.outCalls.get(port);
            for (final String call : _get) {
              {
                int _compareTo = Character.valueOf(call.charAt(0)).compareTo(Character.valueOf('_'));
                boolean _equals = (_compareTo == 0);
                if (_equals) {
                  builder.append(call.replaceFirst("\\_", ""));
                } else {
                  builder.append("self.detmodel").append(connection.componentCall).append(call);
                }
                builder.append(", ");
              }
            }
          }
          builder.append("], ");
        }
      }
    }
    builder.append("}");
    return builder.toString().replaceAll(",,", ",").replaceAll(",}", "}").replaceAll(", }", "}");
  }

  public String generateCallEvents(final EnvironmentConnections connection) {
    StringBuilder builder = new StringBuilder();
    builder.append("{");
    boolean _isEmpty = connection.outCalls.keys().isEmpty();
    boolean _not = (!_isEmpty);
    if (_not) {
      EList<Port> _outports = connection.component.getOutports();
      for (final Port port : _outports) {
        {
          builder.append("\'").append(StringExtensions.toFirstUpper(port.getName())).append("\' : {");
          boolean _isEmpty_1 = connection.outCalls.get(port).isEmpty();
          if (_isEmpty_1) {
            builder.append("None");
          } else {
            List<Event> _outputEvents = StatechartModelDerivedFeatures.getOutputEvents(port);
            for (final Event event : _outputEvents) {
              {
                builder.append("\'").append(StringExtensions.toFirstUpper(event.getName())).append("\' : [");
                Collection<String> _get = connection.outCalls.get(port);
                for (final String call : _get) {
                  {
                    builder.append("(lambda:");
                    int _compareTo = Character.valueOf(call.charAt(0)).compareTo(Character.valueOf('_'));
                    boolean _equals = (_compareTo == 0);
                    if (_equals) {
                      builder.append(call.replaceFirst("\\_", ""));
                    } else {
                      builder.append("self.detmodel").append(connection.componentCall).append(call);
                    }
                    builder.append(".raise").append(StringExtensions.toFirstUpper(event.getName())).append("(");
                    builder.append(TransformationUtility.generateFuncParams(event)).append(")),");
                  }
                }
                builder.append("],");
              }
            }
            builder.append(",");
          }
          builder.append("}, ");
        }
      }
    }
    builder.append("}");
    return builder.toString().replaceAll(",,", ",").replaceAll(",}", "}").replaceAll(", }", "}").replaceAll(",]", "]").replaceAll(", ]", "]");
  }

  public CharSequence generatePortArray(final EnvironmentConnections connection) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("[");
    {
      EList<Port> _outports = connection.component.getOutports();
      boolean _hasElements = false;
      for(final Port port : _outports) {
        if (!_hasElements) {
          _hasElements = true;
        } else {
          _builder.appendImmediate(", ", "");
        }
        _builder.append("\"");
        String _firstUpper = StringExtensions.toFirstUpper(port.getName());
        _builder.append(_firstUpper);
        _builder.append("\"");
      }
    }
    _builder.append("]");
    return _builder;
  }

  public CharSequence generatePortevents(final EnvironmentConnections connection) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("\t");
    _builder.append("{");
    {
      EList<Port> _outports = connection.component.getOutports();
      boolean _hasElements = false;
      for(final Port port : _outports) {
        if (!_hasElements) {
          _hasElements = true;
        } else {
          _builder.appendImmediate(", ", "\t");
        }
        _builder.append("\t\"");
        String _firstUpper = StringExtensions.toFirstUpper(port.getName());
        _builder.append(_firstUpper, "\t");
        _builder.append("\" : [");
        {
          List<Event> _outputEvents = StatechartModelDerivedFeatures.getOutputEvents(port);
          boolean _hasElements_1 = false;
          for(final Event event : _outputEvents) {
            if (!_hasElements_1) {
              _hasElements_1 = true;
            } else {
              _builder.appendImmediate(", ", "\t");
            }
            _builder.append(" \"");
            String _firstUpper_1 = StringExtensions.toFirstUpper(event.getName());
            _builder.append(_firstUpper_1, "\t");
            _builder.append("\"\t");
          }
        }
        _builder.append("]");
      }
    }
    _builder.append("}");
    return _builder;
  }

  public CharSequence generateInPort(final EnvironmentConnections connection) {
    StringConcatenation _builder = new StringConcatenation();
    {
      boolean _isEmpty = connection.inCalls.values().isEmpty();
      boolean _not = (!_isEmpty);
      if (_not) {
        _builder.append("self.detmodel");
        _builder.append(connection.componentCall);
        String _get = ((String[])Conversions.unwrapArray(connection.inCalls.values(), String.class))[0];
        _builder.append(_get);
      } else {
        _builder.append("None");
      }
    }
    return _builder;
  }

  public CharSequence generateSwitchInstances(final List<EnvironmentConnections> connections) {
    StringConcatenation _builder = new StringConcatenation();
    {
      for(final EnvironmentConnections connection : connections) {
        _builder.append("self.components.update({ \"");
        String _generateEnvCompName = TransformationUtility.generateEnvCompName(connection);
        _builder.append(_generateEnvCompName);
        _builder.append("\" : Switch");
        String _firstUpper = StringExtensions.toFirstUpper(connection.component.getInports().get(0).getInterfaceRealization().getInterface().getName());
        _builder.append(_firstUpper);
        _builder.append("()})");
        _builder.newLineIfNotEmpty();
      }
    }
    return _builder;
  }

  public CharSequence generateDelayInstances(final List<EnvironmentConnections> connections) {
    CharSequence _xblockexpression = null;
    {
      final Function1<EnvironmentConnections, Boolean> _function = (EnvironmentConnections c) -> {
        return Boolean.valueOf((c.component instanceof EnvironmentDelay));
      };
      final Function1<EnvironmentConnections, Interface> _function_1 = (EnvironmentConnections c) -> {
        return c.component.getOutports().get(0).getInterfaceRealization().getInterface();
      };
      Set<Interface> interfaces = IterableExtensions.<Interface>toSet(IterableExtensions.<EnvironmentConnections, Interface>map(IterableExtensions.<EnvironmentConnections>filter(connections, _function), _function_1));
      StringConcatenation _builder = new StringConcatenation();
      {
        for(final EnvironmentConnections connection : connections) {
          _builder.append("self.components.update({ \"");
          String _generateEnvCompName = TransformationUtility.generateEnvCompName(connection);
          _builder.append(_generateEnvCompName);
          _builder.append("\" : Delay");
          String _firstUpper = StringExtensions.toFirstUpper(connection.component.getOutports().get(0).getInterfaceRealization().getInterface().getName());
          _builder.append(_firstUpper);
          _builder.append("()})");
          _builder.newLineIfNotEmpty();
        }
      }
      _builder.newLine();
      _xblockexpression = _builder;
    }
    return _xblockexpression;
  }

  public CharSequence generateSampleInstances(final List<EnvironmentConnections> connections) {
    StringConcatenation _builder = new StringConcatenation();
    {
      for(final EnvironmentConnections connection : connections) {
        _builder.append("self.components.update({ \"");
        String _generateEnvCompName = TransformationUtility.generateEnvCompName(connection);
        _builder.append(_generateEnvCompName);
        _builder.append("\" : Sample");
        String _firstUpper = StringExtensions.toFirstUpper(connection.component.getName());
        _builder.append(_firstUpper);
        _builder.append("()})");
        _builder.newLineIfNotEmpty();
      }
    }
    return _builder;
  }

  public CharSequence generatePeriodicEventSourceInstances(final List<EnvironmentConnections> connections) {
    StringConcatenation _builder = new StringConcatenation();
    {
      for(final EnvironmentConnections connection : connections) {
        _builder.append("self.components.update({ \"");
        String _generateEnvCompName = TransformationUtility.generateEnvCompName(connection);
        _builder.append(_generateEnvCompName);
        _builder.append("\" : PeriodicEventSource()})");
        _builder.newLineIfNotEmpty();
      }
    }
    return _builder;
  }

  public CharSequence generateEventSourceInstances(final List<EnvironmentConnections> connections) {
    StringConcatenation _builder = new StringConcatenation();
    {
      for(final EnvironmentConnections connection : connections) {
        _builder.append("self.components.update({ \"");
        String _generateEnvCompName = TransformationUtility.generateEnvCompName(connection);
        _builder.append(_generateEnvCompName);
        _builder.append("\" : EventSource()})");
        _builder.newLineIfNotEmpty();
      }
    }
    return _builder;
  }

  public CharSequence generateSwitchInstancesConfigurations(final List<EnvironmentConnections> connections) {
    StringConcatenation _builder = new StringConcatenation();
    {
      for(final EnvironmentConnections connection : connections) {
        _builder.append("self.components[\"");
        String _generateEnvCompName = TransformationUtility.generateEnvCompName(connection);
        _builder.append(_generateEnvCompName);
        _builder.append("\"].configure(");
        _builder.newLineIfNotEmpty();
        _builder.append("\t\t");
        _builder.append("name  = \"");
        String _generateEnvCompName_1 = TransformationUtility.generateEnvCompName(connection);
        _builder.append(_generateEnvCompName_1, "\t\t");
        _builder.append("\",");
        _builder.newLineIfNotEmpty();
        _builder.append("\t\t");
        _builder.append("inport=");
        CharSequence _generateInPort = this.generateInPort(connection);
        _builder.append(_generateInPort, "\t\t");
        _builder.append(",");
        _builder.newLineIfNotEmpty();
        _builder.append("\t\t");
        _builder.append("calls=");
        String _generateCalls = this.generateCalls(connection);
        _builder.append(_generateCalls, "\t\t");
        _builder.append(",");
        _builder.newLineIfNotEmpty();
        _builder.append("\t\t");
        _builder.append("portarray=");
        CharSequence _generatePortArray = this.generatePortArray(connection);
        _builder.append(_generatePortArray, "\t\t");
        _builder.append(",");
        _builder.newLineIfNotEmpty();
        _builder.append("\t\t");
        _builder.append("categorical=");
        CharSequence _generateCategorical = this.distGenerator.generateCategorical(connection);
        _builder.append(_generateCategorical, "\t\t");
        _builder.append(",");
        _builder.newLineIfNotEmpty();
        _builder.append("\t\t");
        _builder.append("simulator=self,");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("shname=\"");
        String _name = connection.component.getName();
        _builder.append(_name, "\t\t");
        _builder.append("\",");
        _builder.newLineIfNotEmpty();
        _builder.append("\t\t");
        _builder.append("compCall=self.detmodel");
        _builder.append(connection.componentCall, "\t\t");
        _builder.append(")");
        _builder.newLineIfNotEmpty();
        _builder.append("\t\t");
        _builder.newLine();
      }
    }
    return _builder;
  }

  public CharSequence generateDelayInstancesConfigurations(final List<EnvironmentConnections> connections) {
    StringConcatenation _builder = new StringConcatenation();
    {
      for(final EnvironmentConnections connection : connections) {
        _builder.append("self.components[\"");
        String _generateEnvCompName = TransformationUtility.generateEnvCompName(connection);
        _builder.append(_generateEnvCompName);
        _builder.append("\"].configure(");
        _builder.newLineIfNotEmpty();
        _builder.append("\t\t");
        _builder.append("name  = \"");
        String _generateEnvCompName_1 = TransformationUtility.generateEnvCompName(connection);
        _builder.append(_generateEnvCompName_1, "\t\t");
        _builder.append("\",");
        _builder.newLineIfNotEmpty();
        _builder.append("\t\t");
        _builder.append("inport=");
        CharSequence _generateInPort = this.generateInPort(connection);
        _builder.append(_generateInPort, "\t\t");
        _builder.append(",");
        _builder.newLineIfNotEmpty();
        _builder.append("\t\t");
        _builder.append("calls = ");
        String _generateCalls = this.generateCalls(connection);
        _builder.append(_generateCalls, "\t\t");
        _builder.append(",");
        _builder.newLineIfNotEmpty();
        _builder.append("\t\t");
        _builder.append("rules = ");
        String _generateRules = this.generateRules(connection);
        _builder.append(_generateRules, "\t\t");
        _builder.append(",");
        _builder.newLineIfNotEmpty();
        _builder.append("\t\t");
        _builder.append("simulator=self)");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.newLine();
      }
    }
    return _builder;
  }

  public CharSequence generateSampleInstancesConfigurations(final List<EnvironmentConnections> connections) {
    StringConcatenation _builder = new StringConcatenation();
    {
      for(final EnvironmentConnections connection : connections) {
        _builder.append("self.components[\"");
        String _generateEnvCompName = TransformationUtility.generateEnvCompName(connection);
        _builder.append(_generateEnvCompName);
        _builder.append("\"].configure(");
        _builder.newLineIfNotEmpty();
        _builder.append("\t\t");
        _builder.append("name  = \"");
        String _generateEnvCompName_1 = TransformationUtility.generateEnvCompName(connection);
        _builder.append(_generateEnvCompName_1, "\t\t");
        _builder.append("\",");
        _builder.newLineIfNotEmpty();
        _builder.append("\t\t");
        _builder.append("inport=");
        CharSequence _generateInPort = this.generateInPort(connection);
        _builder.append(_generateInPort, "\t\t");
        _builder.append(",");
        _builder.newLineIfNotEmpty();
        _builder.append("\t\t");
        _builder.append("calls = ");
        String _generateCalls = this.generateCalls(connection);
        _builder.append(_generateCalls, "\t\t");
        _builder.append(",");
        _builder.newLineIfNotEmpty();
        _builder.append("\t\t");
        _builder.append("rules = ");
        String _generateSampleRules = this.generateSampleRules(connection);
        _builder.append(_generateSampleRules, "\t\t");
        _builder.append(",");
        _builder.newLineIfNotEmpty();
        _builder.append("\t\t");
        _builder.append("simulator=self,");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("shname=\"");
        String _name = connection.component.getName();
        _builder.append(_name, "\t\t");
        _builder.append("\",");
        _builder.newLineIfNotEmpty();
        _builder.append("\t\t");
        _builder.append("compCall=self.detmodel");
        _builder.append(connection.componentCall, "\t\t");
        _builder.newLineIfNotEmpty();
        _builder.append("\t\t");
        _builder.append(")");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.newLine();
      }
    }
    return _builder;
  }

  public CharSequence generatePeriodicEventSourceInstancesConfigurations(final List<EnvironmentConnections> connections) {
    StringConcatenation _builder = new StringConcatenation();
    {
      for(final EnvironmentConnections connection : connections) {
        _builder.append("self.components[\"");
        String _generateEnvCompName = TransformationUtility.generateEnvCompName(connection);
        _builder.append(_generateEnvCompName);
        _builder.append("\"].configure(");
        _builder.newLineIfNotEmpty();
        _builder.append("\t\t");
        _builder.append("name  = \"");
        String _generateEnvCompName_1 = TransformationUtility.generateEnvCompName(connection);
        _builder.append(_generateEnvCompName_1, "\t\t");
        _builder.append("\",");
        _builder.newLineIfNotEmpty();
        _builder.append("\t\t");
        _builder.append("calls = ");
        String _generateCallEvents = this.generateCallEvents(connection);
        _builder.append(_generateCallEvents, "\t\t");
        _builder.append(",");
        _builder.newLineIfNotEmpty();
        _builder.append("\t\t");
        _builder.append("rules = ");
        String _generateRules = this.generateRules(connection);
        _builder.append(_generateRules, "\t\t");
        _builder.append(",");
        _builder.newLineIfNotEmpty();
        _builder.append("\t\t");
        _builder.append("portevents = ");
        CharSequence _generatePortevents = this.generatePortevents(connection);
        _builder.append(_generatePortevents, "\t\t");
        _builder.append(",");
        _builder.newLineIfNotEmpty();
        _builder.append("\t\t");
        _builder.append("simulator=self)");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.newLine();
      }
    }
    return _builder;
  }

  public CharSequence generateEventSourceInstancesConfigurations(final List<EnvironmentConnections> connections) {
    StringConcatenation _builder = new StringConcatenation();
    {
      for(final EnvironmentConnections connection : connections) {
        _builder.newLine();
        _builder.append("self.components[\"");
        String _generateEnvCompName = TransformationUtility.generateEnvCompName(connection);
        _builder.append(_generateEnvCompName);
        _builder.append("\"].configure(");
        _builder.newLineIfNotEmpty();
        _builder.append("\t\t");
        _builder.append("name  = \"");
        String _generateEnvCompName_1 = TransformationUtility.generateEnvCompName(connection);
        _builder.append(_generateEnvCompName_1, "\t\t");
        _builder.append("\",");
        _builder.newLineIfNotEmpty();
        _builder.append("\t\t");
        _builder.append("calls = ");
        String _generateCallEvents = this.generateCallEvents(connection);
        _builder.append(_generateCallEvents, "\t\t");
        _builder.append(",");
        _builder.newLineIfNotEmpty();
        _builder.append("\t\t");
        _builder.append("rules = ");
        String _generateRules = this.generateRules(connection);
        _builder.append(_generateRules, "\t\t");
        _builder.append(",");
        _builder.newLineIfNotEmpty();
        _builder.append("\t\t");
        _builder.append("portevents = ");
        CharSequence _generatePortevents = this.generatePortevents(connection);
        _builder.append(_generatePortevents, "\t\t");
        _builder.append(",");
        _builder.newLineIfNotEmpty();
        _builder.append("\t\t");
        _builder.append("simulator=self)");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.newLine();
      }
    }
    return _builder;
  }

  public CharSequence gennerateInstances(final List<EnvironmentConnections> connections) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.newLine();
    _builder.append("self.components.clear()");
    _builder.newLine();
    final Function1<EnvironmentConnections, Boolean> _function = (EnvironmentConnections c) -> {
      return Boolean.valueOf((c.component instanceof EnvironmentPeriodicEventSource));
    };
    CharSequence _generatePeriodicEventSourceInstances = this.generatePeriodicEventSourceInstances(
      IterableExtensions.<EnvironmentConnections>toList(IterableExtensions.<EnvironmentConnections>filter(connections, _function)));
    _builder.append(_generatePeriodicEventSourceInstances);
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    final Function1<EnvironmentConnections, Boolean> _function_1 = (EnvironmentConnections c) -> {
      return Boolean.valueOf((c.component instanceof EnvironmentEventSource));
    };
    CharSequence _generateEventSourceInstances = this.generateEventSourceInstances(
      IterableExtensions.<EnvironmentConnections>toList(IterableExtensions.<EnvironmentConnections>filter(connections, _function_1)));
    _builder.append(_generateEventSourceInstances);
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    final Function1<EnvironmentConnections, Boolean> _function_2 = (EnvironmentConnections c) -> {
      return Boolean.valueOf((c.component instanceof EnvironmentDelay));
    };
    CharSequence _generateDelayInstances = this.generateDelayInstances(
      IterableExtensions.<EnvironmentConnections>toList(IterableExtensions.<EnvironmentConnections>filter(connections, _function_2)));
    _builder.append(_generateDelayInstances);
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    final Function1<EnvironmentConnections, Boolean> _function_3 = (EnvironmentConnections c) -> {
      return Boolean.valueOf((c.component instanceof EnvironmentSwitch));
    };
    CharSequence _generateSwitchInstances = this.generateSwitchInstances(
      IterableExtensions.<EnvironmentConnections>toList(IterableExtensions.<EnvironmentConnections>filter(connections, _function_3)));
    _builder.append(_generateSwitchInstances);
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    final Function1<EnvironmentConnections, Boolean> _function_4 = (EnvironmentConnections c) -> {
      return Boolean.valueOf((c.component instanceof EnvironmentSample));
    };
    CharSequence _generateSampleInstances = this.generateSampleInstances(
      IterableExtensions.<EnvironmentConnections>toList(IterableExtensions.<EnvironmentConnections>filter(connections, _function_4)));
    _builder.append(_generateSampleInstances);
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    return _builder;
  }

  public CharSequence generateConfigurations(final List<EnvironmentConnections> connections) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.newLine();
    final Function1<EnvironmentConnections, Boolean> _function = (EnvironmentConnections c) -> {
      return Boolean.valueOf((c.component instanceof EnvironmentPeriodicEventSource));
    };
    CharSequence _generatePeriodicEventSourceInstancesConfigurations = this.generatePeriodicEventSourceInstancesConfigurations(
      IterableExtensions.<EnvironmentConnections>toList(IterableExtensions.<EnvironmentConnections>filter(connections, _function)));
    _builder.append(_generatePeriodicEventSourceInstancesConfigurations);
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    final Function1<EnvironmentConnections, Boolean> _function_1 = (EnvironmentConnections c) -> {
      return Boolean.valueOf((c.component instanceof EnvironmentEventSource));
    };
    CharSequence _generateEventSourceInstancesConfigurations = this.generateEventSourceInstancesConfigurations(
      IterableExtensions.<EnvironmentConnections>toList(IterableExtensions.<EnvironmentConnections>filter(connections, _function_1)));
    _builder.append(_generateEventSourceInstancesConfigurations);
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    final Function1<EnvironmentConnections, Boolean> _function_2 = (EnvironmentConnections c) -> {
      return Boolean.valueOf((c.component instanceof EnvironmentDelay));
    };
    CharSequence _generateDelayInstancesConfigurations = this.generateDelayInstancesConfigurations(
      IterableExtensions.<EnvironmentConnections>toList(IterableExtensions.<EnvironmentConnections>filter(connections, _function_2)));
    _builder.append(_generateDelayInstancesConfigurations);
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    final Function1<EnvironmentConnections, Boolean> _function_3 = (EnvironmentConnections c) -> {
      return Boolean.valueOf((c.component instanceof EnvironmentSwitch));
    };
    CharSequence _generateSwitchInstancesConfigurations = this.generateSwitchInstancesConfigurations(
      IterableExtensions.<EnvironmentConnections>toList(IterableExtensions.<EnvironmentConnections>filter(connections, _function_3)));
    _builder.append(_generateSwitchInstancesConfigurations);
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    final Function1<EnvironmentConnections, Boolean> _function_4 = (EnvironmentConnections c) -> {
      return Boolean.valueOf((c.component instanceof EnvironmentSample));
    };
    CharSequence _generateSampleInstancesConfigurations = this.generateSampleInstancesConfigurations(
      IterableExtensions.<EnvironmentConnections>toList(IterableExtensions.<EnvironmentConnections>filter(connections, _function_4)));
    _builder.append(_generateSampleInstancesConfigurations);
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.newLine();
    return _builder;
  }

  public CharSequence generateRule(final EnvironmentRule rule, final String name) {
    if (rule instanceof SimulationRule) {
      return _generateRule((SimulationRule)rule, name);
    } else if (rule instanceof StochasticRule) {
      return _generateRule((StochasticRule)rule, name);
    } else if (rule != null) {
      return _generateRule(rule, name);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(rule, name).toString());
    }
  }
}
