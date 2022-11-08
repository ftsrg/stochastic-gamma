package hu.bme.mit.gamma.analysis.transformation;

import com.google.common.base.Objects;
import hu.bme.mit.gamma.environment.model.ComponentFilter;
import hu.bme.mit.gamma.environment.model.EnvironmentDelay;
import hu.bme.mit.gamma.environment.model.EnvironmentEventSource;
import hu.bme.mit.gamma.environment.model.EnvironmentExternSimulation;
import hu.bme.mit.gamma.environment.model.EnvironmentPeriodicEventSource;
import hu.bme.mit.gamma.environment.model.EnvironmentRule;
import hu.bme.mit.gamma.environment.model.EnvironmentSample;
import hu.bme.mit.gamma.environment.model.EnvironmentSwitch;
import hu.bme.mit.gamma.environment.model.EventFilter;
import hu.bme.mit.gamma.environment.model.Filter;
import hu.bme.mit.gamma.environment.model.PortFilter;
import hu.bme.mit.gamma.environment.model.SimulationRule;
import hu.bme.mit.gamma.environment.model.StochasticRule;
import hu.bme.mit.gamma.expression.util.ExpressionEvaluator;
import hu.bme.mit.gamma.statechart.interface_.Event;
import hu.bme.mit.gamma.statechart.interface_.EventDeclaration;
import hu.bme.mit.gamma.statechart.interface_.Interface;
import hu.bme.mit.gamma.statechart.interface_.Port;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;
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
    PyroDistGenerator _pyroDistGenerator = new PyroDistGenerator(packageName);
    this.distGenerator = _pyroDistGenerator;
  }
  
  public CharSequence generateRules(final EnvironmentConnections connection) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("{");
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
        _builder.append("\" : {");
        _builder.newLineIfNotEmpty();
        {
          EList<EventDeclaration> _events = port.getInterfaceRealization().getInterface().getEvents();
          boolean _hasElements_1 = false;
          for(final EventDeclaration event : _events) {
            if (!_hasElements_1) {
              _hasElements_1 = true;
            } else {
              _builder.appendImmediate(", ", "\t");
            }
            _builder.append("\t");
            final Function1<EnvironmentRule, Boolean> _function = (EnvironmentRule r) -> {
              final Function1<Filter, Boolean> _function_1 = (Filter f) -> {
                return Boolean.valueOf((f instanceof EventFilter));
              };
              final Function1<Filter, Boolean> _function_2 = (Filter f) -> {
                Event _event = ((EventFilter) f).getEvent().getEvent();
                Event _event_1 = event.getEvent();
                return Boolean.valueOf(Objects.equal(_event, _event_1));
              };
              boolean _isEmpty = IterableExtensions.isEmpty(IterableExtensions.<Filter>filter(IterableExtensions.<Filter>filter(r.getFilter(), _function_1), _function_2));
              return Boolean.valueOf((!_isEmpty));
            };
            List<EnvironmentRule> rules = IterableExtensions.<EnvironmentRule>toList(IterableExtensions.<EnvironmentRule>filter(connection.component.getBehaviorRules(), _function));
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            _builder.append("#");
            List<EnvironmentRule> _xifexpression = null;
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
              _xifexpression = rules = IterableExtensions.<EnvironmentRule>toList(IterableExtensions.<EnvironmentRule>filter(connection.component.getBehaviorRules(), _function_1));
            }
            _builder.append(_xifexpression, "\t");
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            _builder.append("#");
            List<EnvironmentRule> _xifexpression_1 = null;
            boolean _isEmpty_1 = rules.isEmpty();
            if (_isEmpty_1) {
              final Function1<EnvironmentRule, Boolean> _function_2 = (EnvironmentRule r) -> {
                final Function1<Filter, Boolean> _function_3 = (Filter f) -> {
                  return Boolean.valueOf((f instanceof ComponentFilter));
                };
                boolean _isEmpty_2 = IterableExtensions.isEmpty(IterableExtensions.<Filter>filter(r.getFilter(), _function_3));
                return Boolean.valueOf((!_isEmpty_2));
              };
              _xifexpression_1 = rules = IterableExtensions.<EnvironmentRule>toList(IterableExtensions.<EnvironmentRule>filter(connection.component.getBehaviorRules(), _function_2));
            }
            _builder.append(_xifexpression_1, "\t");
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            {
              boolean _isEmpty_2 = rules.isEmpty();
              boolean _not = (!_isEmpty_2);
              if (_not) {
                EnvironmentRule rule = rules.get(0);
                _builder.append("\"");
                String _firstUpper_1 = StringExtensions.toFirstUpper(event.getEvent().getName());
                _builder.append(_firstUpper_1, "\t");
                _builder.append("\" : ");
                {
                  if ((rule instanceof SimulationRule)) {
                    String _simulationClassName = ((SimulationRule)rule).getSimulation().getSimulationClassName();
                    _builder.append(_simulationClassName, "\t");
                    _builder.append("Instance");
                  } else {
                    if ((rule instanceof StochasticRule)) {
                      _builder.newLineIfNotEmpty();
                      _builder.append("\t");
                      CharSequence _generateStochasticModel = this.distGenerator.generateStochasticModel(((StochasticRule)rule).getStochasticModel(), connection.component.getName());
                      _builder.append(_generateStochasticModel, "\t");
                    }
                  }
                }
                _builder.newLineIfNotEmpty();
              }
            }
          }
        }
        _builder.append("}");
      }
    }
    _builder.append("}");
    _builder.newLineIfNotEmpty();
    return _builder;
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
  
  public CharSequence generateCalls(final EnvironmentConnections connection) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("{");
    {
      boolean _isEmpty = connection.outCalls.keys().isEmpty();
      boolean _not = (!_isEmpty);
      if (_not) {
        {
          EList<Port> _outports = connection.component.getOutports();
          boolean _hasElements = false;
          for(final Port port : _outports) {
            if (!_hasElements) {
              _hasElements = true;
            } else {
              _builder.appendImmediate(", ", "");
            }
            _builder.newLineIfNotEmpty();
            _builder.append("\"");
            String _firstUpper = StringExtensions.toFirstUpper(port.getName());
            _builder.append(_firstUpper);
            _builder.append("\" : [");
            {
              boolean _isEmpty_1 = connection.outCalls.get(port).isEmpty();
              if (_isEmpty_1) {
                _builder.append("None");
              }
            }
            {
              Collection<String> _get = connection.outCalls.get(port);
              boolean _hasElements_1 = false;
              for(final String call : _get) {
                if (!_hasElements_1) {
                  _hasElements_1 = true;
                } else {
                  _builder.appendImmediate(", ", "");
                }
                _builder.newLineIfNotEmpty();
                {
                  char _charAt = call.charAt(0);
                  boolean _equals = Objects.equal(Character.valueOf(_charAt), "_");
                  if (_equals) {
                    String _replaceAll = call.replaceAll("^_", "");
                    _builder.append(_replaceAll);
                  } else {
                    _builder.append("self.detmodel");
                    _builder.append(connection.componentCall);
                    _builder.append(call);
                  }
                }
              }
            }
            _builder.append("]");
          }
        }
      }
    }
    _builder.append("}");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  public CharSequence generateCallEvents(final EnvironmentConnections connection) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("{");
    {
      boolean _isEmpty = connection.outCalls.keys().isEmpty();
      boolean _not = (!_isEmpty);
      if (_not) {
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
            _builder.append("\" : {");
            _builder.newLineIfNotEmpty();
            {
              Collection<String> _get = connection.outCalls.get(port);
              boolean _hasElements_1 = false;
              for(final String call : _get) {
                if (!_hasElements_1) {
                  _hasElements_1 = true;
                } else {
                  _builder.appendImmediate(", ", "");
                }
                {
                  final Function1<EventDeclaration, Event> _function = (EventDeclaration e) -> {
                    return e.getEvent();
                  };
                  List<Event> _map = ListExtensions.<EventDeclaration, Event>map(port.getInterfaceRealization().getInterface().getEvents(), _function);
                  boolean _hasElements_2 = false;
                  for(final Event event : _map) {
                    if (!_hasElements_2) {
                      _hasElements_2 = true;
                    } else {
                      _builder.appendImmediate(", ", "");
                    }
                    _builder.append("\"");
                    String _firstUpper_1 = StringExtensions.toFirstUpper(event.getName());
                    _builder.append(_firstUpper_1);
                    _builder.append("\" : (lambda:self.detmodel");
                    _builder.append(connection.componentCall);
                    _builder.append(call);
                    _builder.append(".raise");
                    String _firstUpper_2 = StringExtensions.toFirstUpper(event.getName());
                    _builder.append(_firstUpper_2);
                    _builder.append("(");
                    CharSequence _generateFuncParams = TransformationUtility.generateFuncParams(event);
                    _builder.append(_generateFuncParams);
                    _builder.append("))");
                  }
                }
              }
            }
            _builder.append("}");
          }
        }
      }
    }
    _builder.append("}");
    _builder.newLineIfNotEmpty();
    return _builder;
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
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  public CharSequence generatePortevents(final EnvironmentConnections connection) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("{");
    _builder.newLine();
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
        _builder.append("\" : [");
        _builder.newLineIfNotEmpty();
        {
          EList<EventDeclaration> _events = port.getInterfaceRealization().getInterface().getEvents();
          boolean _hasElements_1 = false;
          for(final EventDeclaration event : _events) {
            if (!_hasElements_1) {
              _hasElements_1 = true;
            } else {
              _builder.appendImmediate(", ", "\t");
            }
            _builder.append("\t");
            _builder.append("\"");
            String _firstUpper_1 = StringExtensions.toFirstUpper(event.getEvent().getName());
            _builder.append(_firstUpper_1, "\t");
            _builder.append("\"");
            _builder.newLineIfNotEmpty();
          }
        }
        _builder.append("]");
        _builder.newLine();
      }
    }
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence generateInPort(final EnvironmentConnections connection) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("self.detmodel");
    _builder.append(connection.componentCall);
    String _get = ((String[])Conversions.unwrapArray(connection.inCalls.values(), String.class))[0];
    _builder.append(_get);
    return _builder;
  }
  
  public CharSequence generateSwitchInstances(final List<EnvironmentConnections> connections) {
    StringConcatenation _builder = new StringConcatenation();
    {
      for(final EnvironmentConnections connection : connections) {
        _builder.newLine();
        _builder.append("self.components.update({ \"");
        String _generateEnvCompName = TransformationUtility.generateEnvCompName(connection);
        _builder.append(_generateEnvCompName);
        _builder.append("\" :");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("Switch");
        String _firstUpper = StringExtensions.toFirstUpper(connection.component.getInports().get(0).getInterfaceRealization().getInterface().getName());
        _builder.append(_firstUpper, "\t");
        _builder.append("(");
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
        CharSequence _generateCalls = this.generateCalls(connection);
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
        _builder.append("simulator=self)})");
        _builder.newLine();
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
      _builder.newLine();
      {
        for(final EnvironmentConnections connection : connections) {
          _builder.newLine();
          _builder.append("self.components.update({ \"");
          String _generateEnvCompName = TransformationUtility.generateEnvCompName(connection);
          _builder.append(_generateEnvCompName);
          _builder.append("\" :");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          _builder.append("Delay");
          String _firstUpper = StringExtensions.toFirstUpper(connection.component.getOutports().get(0).getInterfaceRealization().getInterface().getName());
          _builder.append(_firstUpper, "\t");
          _builder.append("(");
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
          CharSequence _generateCalls = this.generateCalls(connection);
          _builder.append(_generateCalls, "\t\t");
          _builder.append(",");
          _builder.newLineIfNotEmpty();
          _builder.append("\t\t");
          _builder.append("rules = ");
          CharSequence _generateRules = this.generateRules(connection);
          _builder.append(_generateRules, "\t\t");
          _builder.append(",");
          _builder.newLineIfNotEmpty();
          _builder.append("\t\t");
          _builder.append("simulator=self)})");
          _builder.newLine();
        }
      }
      _builder.newLine();
      _xblockexpression = _builder;
    }
    return _xblockexpression;
  }
  
  public CharSequence generateSampleInstances(final List<EnvironmentConnections> connections) {
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
      _builder.newLine();
      {
        for(final EnvironmentConnections connection : connections) {
          _builder.newLine();
          _builder.append("self.components.update({ \"");
          String _generateEnvCompName = TransformationUtility.generateEnvCompName(connection);
          _builder.append(_generateEnvCompName);
          _builder.append("\" :");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          _builder.append("Sample");
          String _firstUpper = StringExtensions.toFirstUpper(connection.component.getOutports().get(0).getInterfaceRealization().getInterface().getName());
          _builder.append(_firstUpper, "\t");
          _builder.append("(");
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
          CharSequence _generateCalls = this.generateCalls(connection);
          _builder.append(_generateCalls, "\t\t");
          _builder.append(",");
          _builder.newLineIfNotEmpty();
          _builder.append("\t\t");
          _builder.append("rules = ");
          CharSequence _generateRules = this.generateRules(connection);
          _builder.append(_generateRules, "\t\t");
          _builder.append(",");
          _builder.newLineIfNotEmpty();
          _builder.append("\t\t");
          _builder.append("simulator=self)})");
          _builder.newLine();
        }
      }
      _builder.newLine();
      _xblockexpression = _builder;
    }
    return _xblockexpression;
  }
  
  public CharSequence generatePeriodicEventSourceInstances(final List<EnvironmentConnections> connections) {
    StringConcatenation _builder = new StringConcatenation();
    {
      for(final EnvironmentConnections connection : connections) {
        _builder.newLine();
        _builder.append("self.components.update({ \"");
        String _generateEnvCompName = TransformationUtility.generateEnvCompName(connection);
        _builder.append(_generateEnvCompName);
        _builder.append("\" :");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("PeriodicEventSource(");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("name  = \"");
        String _generateEnvCompName_1 = TransformationUtility.generateEnvCompName(connection);
        _builder.append(_generateEnvCompName_1, "\t\t");
        _builder.append("\",");
        _builder.newLineIfNotEmpty();
        _builder.append("\t\t");
        _builder.append("calls = ");
        CharSequence _generateCallEvents = this.generateCallEvents(connection);
        _builder.append(_generateCallEvents, "\t\t");
        _builder.append(",");
        _builder.newLineIfNotEmpty();
        _builder.append("\t\t");
        _builder.append("rules = ");
        CharSequence _generateRules = this.generateRules(connection);
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
        _builder.append("simulator=self)})");
        _builder.newLine();
      }
    }
    return _builder;
  }
  
  public CharSequence generateEventSourceInstances(final List<EnvironmentConnections> connections) {
    StringConcatenation _builder = new StringConcatenation();
    {
      for(final EnvironmentConnections connection : connections) {
        _builder.newLine();
        _builder.append("self.components.update({ \"");
        String _generateEnvCompName = TransformationUtility.generateEnvCompName(connection);
        _builder.append(_generateEnvCompName);
        _builder.append("\" :");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("EventSource(");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("name  = \"");
        String _generateEnvCompName_1 = TransformationUtility.generateEnvCompName(connection);
        _builder.append(_generateEnvCompName_1, "\t\t");
        _builder.append("\",");
        _builder.newLineIfNotEmpty();
        _builder.append("\t\t");
        _builder.append("calls = ");
        CharSequence _generateCallEvents = this.generateCallEvents(connection);
        _builder.append(_generateCallEvents, "\t\t");
        _builder.append(",");
        _builder.newLineIfNotEmpty();
        _builder.append("\t\t");
        _builder.append("rules = ");
        CharSequence _generateRules = this.generateRules(connection);
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
        _builder.append("simulator=self)})");
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
    final Function1<EnvironmentConnections, Boolean> _function_5 = (EnvironmentConnections c) -> {
      return Boolean.valueOf((c.component instanceof EnvironmentExternSimulation));
    };
    CharSequence _generateEventSourceInstances_1 = this.generateEventSourceInstances(
      IterableExtensions.<EnvironmentConnections>toList(IterableExtensions.<EnvironmentConnections>filter(connections, _function_5)));
    _builder.append(_generateEventSourceInstances_1);
    _builder.newLineIfNotEmpty();
    return _builder;
  }
}
