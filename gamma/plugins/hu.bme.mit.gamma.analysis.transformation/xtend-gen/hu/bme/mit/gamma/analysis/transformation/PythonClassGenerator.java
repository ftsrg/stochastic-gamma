package hu.bme.mit.gamma.analysis.transformation;

import com.google.common.base.Objects;
import hu.bme.mit.gamma.environment.model.ComponentFilter;
import hu.bme.mit.gamma.environment.model.EnvironmentDelay;
import hu.bme.mit.gamma.environment.model.EnvironmentRule;
import hu.bme.mit.gamma.environment.model.EnvironmentSample;
import hu.bme.mit.gamma.environment.model.EnvironmentSwitch;
import hu.bme.mit.gamma.environment.model.EventFilter;
import hu.bme.mit.gamma.environment.model.Filter;
import hu.bme.mit.gamma.environment.model.PortFilter;
import hu.bme.mit.gamma.environment.model.SimulationRule;
import hu.bme.mit.gamma.environment.model.StochasticRule;
import hu.bme.mit.gamma.expression.model.ParameterDeclaration;
import hu.bme.mit.gamma.expression.model.Type;
import hu.bme.mit.gamma.expression.util.ExpressionEvaluator;
import hu.bme.mit.gamma.statechart.interface_.Event;
import hu.bme.mit.gamma.statechart.interface_.EventDeclaration;
import hu.bme.mit.gamma.statechart.interface_.Interface;
import hu.bme.mit.gamma.statechart.interface_.Port;
import hu.bme.mit.gamma.stochastic.stochastic.BernoulliRandomVariable;
import hu.bme.mit.gamma.stochastic.stochastic.BrownianKernel;
import hu.bme.mit.gamma.stochastic.stochastic.CategoricalProbabaility;
import hu.bme.mit.gamma.stochastic.stochastic.ContinouosRandomVariable;
import hu.bme.mit.gamma.stochastic.stochastic.DataSource;
import hu.bme.mit.gamma.stochastic.stochastic.DiracProcess;
import hu.bme.mit.gamma.stochastic.stochastic.DiscreteRandomVariable;
import hu.bme.mit.gamma.stochastic.stochastic.ExponentialRandomVariable;
import hu.bme.mit.gamma.stochastic.stochastic.FittedExponentialRandomVariable;
import hu.bme.mit.gamma.stochastic.stochastic.FittedGaussianProcess;
import hu.bme.mit.gamma.stochastic.stochastic.FittedNormalRandomVariable;
import hu.bme.mit.gamma.stochastic.stochastic.GammaRandomVariable;
import hu.bme.mit.gamma.stochastic.stochastic.InfluxDB;
import hu.bme.mit.gamma.stochastic.stochastic.Kernel;
import hu.bme.mit.gamma.stochastic.stochastic.LinearKernel;
import hu.bme.mit.gamma.stochastic.stochastic.NormalRandomVariable;
import hu.bme.mit.gamma.stochastic.stochastic.PeriodicKernel;
import hu.bme.mit.gamma.stochastic.stochastic.PythonSimulation;
import hu.bme.mit.gamma.stochastic.stochastic.RBFKernel;
import hu.bme.mit.gamma.stochastic.stochastic.StochasticModel;
import hu.bme.mit.gamma.stochastic.stochastic.SumKernel;
import hu.bme.mit.gamma.stochastic.stochastic.WeibullRandomVariable;
import java.util.Arrays;
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
public class PythonClassGenerator {
  private static Integer cntr = Integer.valueOf(0);
  
  private final String packageName;
  
  private static Integer distcntr = Integer.valueOf(0);
  
  private final ExpressionEvaluator expEval;
  
  public PythonClassGenerator(final String packageName) {
    this.packageName = packageName;
    this.expEval = ExpressionEvaluator.INSTANCE;
  }
  
  public CharSequence generateRules(final EnvironmentConnections connection) {
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
        _builder.append("\" : {");
        _builder.newLineIfNotEmpty();
        {
          EList<EventDeclaration> _events = port.getInterfaceRealization().getInterface().getEvents();
          boolean _hasElements_1 = false;
          for(final EventDeclaration event : _events) {
            if (!_hasElements_1) {
              _hasElements_1 = true;
            } else {
              _builder.appendImmediate(", ", "");
            }
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
            _builder.append(_xifexpression);
            _builder.newLineIfNotEmpty();
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
            _builder.append(_xifexpression_1);
            _builder.newLineIfNotEmpty();
            {
              boolean _isEmpty_2 = rules.isEmpty();
              boolean _not = (!_isEmpty_2);
              if (_not) {
                EnvironmentRule rule = rules.get(0);
                _builder.newLineIfNotEmpty();
                _builder.append("\"");
                String _firstUpper_1 = StringExtensions.toFirstUpper(event.getEvent().getName());
                _builder.append(_firstUpper_1);
                _builder.append("\" : ");
                _builder.newLineIfNotEmpty();
                {
                  if ((rule instanceof SimulationRule)) {
                    String _simulationClassName = ((SimulationRule)rule).getSimulation().getSimulationClassName();
                    _builder.append(_simulationClassName);
                    _builder.append("Instance");
                    _builder.newLineIfNotEmpty();
                  } else {
                    if ((rule instanceof StochasticRule)) {
                      CharSequence _generateStochasticModel = this.generateStochasticModel(
                        ((StochasticRule)rule).getStochasticModel(), connection.component.getName());
                      _builder.append(_generateStochasticModel);
                      _builder.newLineIfNotEmpty();
                    }
                  }
                }
              }
            }
          }
        }
        _builder.append("}");
        _builder.newLine();
      }
    }
    _builder.append("}");
    _builder.newLine();
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
    _builder.newLine();
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
            _builder.append("\" : [");
            _builder.newLineIfNotEmpty();
            {
              Collection<String> _get = connection.outCalls.get(((Port[])Conversions.unwrapArray(connection.outCalls.keys(), Port.class))[0]);
              boolean _hasElements_1 = false;
              for(final String call : _get) {
                if (!_hasElements_1) {
                  _hasElements_1 = true;
                } else {
                  _builder.appendImmediate(", ", "");
                }
                {
                  char _charAt = call.charAt(0);
                  boolean _equals = Objects.equal(Character.valueOf(_charAt), "_");
                  if (_equals) {
                    String _replaceAll = connection.componentCall.replaceAll(".", "");
                    _builder.append(_replaceAll);
                    _builder.append(call);
                    _builder.newLineIfNotEmpty();
                  } else {
                    _builder.append("sctmodel");
                    _builder.append(connection.componentCall);
                    _builder.append(call);
                    _builder.newLineIfNotEmpty();
                  }
                }
              }
            }
            _builder.append("]");
            _builder.newLine();
          }
        }
      }
    }
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence generateCallEvents(final EnvironmentConnections connection) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("{");
    _builder.newLine();
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
                    _builder.append("\" : (lambda:sctmodel");
                    _builder.append(connection.componentCall);
                    _builder.append(call);
                    _builder.append(".raise");
                    String _firstUpper_2 = StringExtensions.toFirstUpper(event.getName());
                    _builder.append(_firstUpper_2);
                    _builder.append("(");
                    CharSequence _generateFuncParams = this.generateFuncParams(event);
                    _builder.append(_generateFuncParams);
                    _builder.append("))");
                    _builder.newLineIfNotEmpty();
                  }
                }
              }
            }
            _builder.append("}");
            _builder.newLine();
          }
        }
      }
    }
    _builder.append("}");
    _builder.newLine();
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
  
  public CharSequence generateInterfaceSubClass(final Interface i) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("class Java:");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("implements = [\"");
    _builder.append(this.packageName, "\t\t");
    _builder.append(".interfaces.");
    String _firstUpper = StringExtensions.toFirstUpper(i.getName());
    _builder.append(_firstUpper, "\t\t");
    _builder.append("Interface$Listener$Provided\"]");
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
    _builder.append("sctmodel");
    _builder.append(connection.componentCall);
    String _get = ((String[])Conversions.unwrapArray(connection.inCalls.values(), String.class))[0];
    _builder.append(_get);
    return _builder;
  }
  
  public CharSequence generateFuncParams(final Event event) {
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
  
  public CharSequence generateEventSourceClass() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class EventSource():");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def __init__(self,name,calls,rules,portevents,detmodel):");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("self.calls=calls");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("self.rules=rules");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("self.portevents=portevents");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("self.detmodel=detmodel");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def generateEvents(self):");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("self.events=[]");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("ports=list(self.calls.keys())");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("#iterating through ports");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("for port in ports:");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("pevents=self.portevents[port]");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("#iterating through events");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("for pevent in pevents:");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("rule=self.rules[port][pevent]");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("call=self.calls[port][pevent]");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("time=rule.calc(port+\".\"+pevent,0.0)");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("if time>=0:");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.append("#iterating through port connections");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.append("self.events.append(Event(self,time,call))");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def getEvents(self):");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("eventcopy=self.events.copy()");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("self.events.clear()");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return eventcopy");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence generatePeriodicEventSourceClass() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class PeriodicEventSource():");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def __init__(self,name,calls,rules,portevents,detmodel):");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("self.name=name");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("self.calls=calls");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("self.rules=rules");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("self.portevents=portevents");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("self.detmodel=detmodel");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def generateEvents(self):");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("self.events=[]");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("ports=list(self.calls.keys())");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("#iterating through self.ports");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("for port in ports:");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("pevents=self.portevents[port]");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("#iterating through events");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("for pevent in pevents:");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("call=self.calls[port][pevent]");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("rule=self.rules[port][pevent]");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("simulationtime=0.0");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("while simulationtime < simTime:");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.append("simulationtime=simulationtime+rule.calc(port+\".\"+pevent,simulationtime)");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.append("#iterating through port connections");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.append("self.events.append(Event(self,simulationtime,call))");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def getEvents(self):");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("eventcopy=self.events.copy()");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("self.events.clear()");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return eventcopy");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence generateSampleClasses(final List<EnvironmentConnections> connections) {
    StringConcatenation _builder = new StringConcatenation();
    final Function1<EnvironmentConnections, Boolean> _function = (EnvironmentConnections c) -> {
      return Boolean.valueOf((c.component instanceof EnvironmentSample));
    };
    final Function1<EnvironmentConnections, Interface> _function_1 = (EnvironmentConnections c) -> {
      return c.component.getOutports().get(0).getInterfaceRealization().getInterface();
    };
    Set<Interface> interfaces = IterableExtensions.<Interface>toSet(IterableExtensions.<EnvironmentConnections, Interface>map(IterableExtensions.<EnvironmentConnections>filter(connections, _function), _function_1));
    _builder.newLineIfNotEmpty();
    {
      for(final Interface i : interfaces) {
        _builder.append("class Sample");
        String _firstUpper = StringExtensions.toFirstUpper(i.getName());
        _builder.append(_firstUpper);
        _builder.append("():");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("def __init__(self,name,inport,calls,rules,detmodel,actual_time):");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("self.name=name");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("callitem=calls.popitem()#only one out port");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("self.calls=callitem[1]");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("self.port=callitem[0]");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("self.rules=rules.popitem()[1]#only one out port");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("self.detmodel=detmodel");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("self.event_cntr=0");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("self.events=[]");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("inport.registerListener(self)");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("self.actual_time=actual_time");
        _builder.newLine();
        _builder.append("\t\t\t");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("def getEvents(self):");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("eventcopy=self.events.copy()");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("self.events.clear()");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("return eventcopy");
        _builder.newLine();
        _builder.newLine();
        _builder.append("\t");
        _builder.append("def generateEvents(self):");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("self.events=[]");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("#definition of the interface functions");
        _builder.newLine();
        {
          EList<EventDeclaration> _events = i.getEvents();
          for(final EventDeclaration event : _events) {
            _builder.append("\t");
            _builder.append("def raise");
            String _firstUpper_1 = StringExtensions.toFirstUpper(event.getEvent().getName());
            _builder.append(_firstUpper_1, "\t");
            _builder.append("(self,");
            CharSequence _generateFuncParams = this.generateFuncParams(event.getEvent());
            _builder.append(_generateFuncParams, "\t");
            _builder.append("):");
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            _builder.append("\t");
            String _firstLower = StringExtensions.toFirstLower(event.getEvent().getParameterDeclarations().get(0).getName());
            _builder.append(_firstLower, "\t\t");
            _builder.append("=self.rules[\"");
            String _firstUpper_2 = StringExtensions.toFirstUpper(event.getEvent().getName());
            _builder.append(_firstUpper_2, "\t\t");
            _builder.append("\"].calc(self.port+\".\"+\"");
            String _firstUpper_3 = StringExtensions.toFirstUpper(event.getEvent().getName());
            _builder.append(_firstUpper_3, "\t\t");
            _builder.append("\",self.actual_time[0])");
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            _builder.append("\t");
            _builder.append("#");
            Type _type = event.getEvent().getParameterDeclarations().get(0).getType();
            _builder.append(_type, "\t\t");
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            _builder.append("\t");
            _builder.append("self.event_cntr=self.event_cntr+1");
            _builder.newLine();
            _builder.append("\t");
            _builder.append("\t");
            _builder.append("for call in self.calls:");
            _builder.newLine();
            _builder.append("\t");
            _builder.append("\t\t");
            _builder.append("callEvent=lambda:call.raise");
            String _firstUpper_4 = StringExtensions.toFirstUpper(event.getEvent().getName());
            _builder.append(_firstUpper_4, "\t\t\t");
            _builder.append("(");
            CharSequence _generateFuncParams_1 = this.generateFuncParams(event.getEvent());
            _builder.append(_generateFuncParams_1, "\t\t\t");
            _builder.append(");");
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            _builder.append("\t\t");
            _builder.append("self.events.append(Event(self,self.actual_time[0],callEvent))");
            _builder.newLine();
          }
        }
        _builder.newLine();
        CharSequence _generateInterfaceSubClass = this.generateInterfaceSubClass(i);
        _builder.append(_generateInterfaceSubClass);
        _builder.newLineIfNotEmpty();
      }
    }
    return _builder;
  }
  
  public String generateDelayClasses(final List<EnvironmentConnections> connections) {
    final Function1<EnvironmentConnections, Boolean> _function = (EnvironmentConnections c) -> {
      return Boolean.valueOf((c.component instanceof EnvironmentDelay));
    };
    final Function1<EnvironmentConnections, Interface> _function_1 = (EnvironmentConnections c) -> {
      return c.component.getOutports().get(0).getInterfaceRealization().getInterface();
    };
    Set<Interface> interfaces = IterableExtensions.<Interface>toSet(IterableExtensions.<EnvironmentConnections, Interface>map(IterableExtensions.<EnvironmentConnections>filter(connections, _function), _function_1));
    StringBuilder classes = new StringBuilder();
    for (final Interface i : interfaces) {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("class Delay");
      String _firstUpper = StringExtensions.toFirstUpper(i.getName());
      _builder.append(_firstUpper);
      _builder.append("():");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("def __init__(self,name,inport,calls,rules,detmodel,actual_time):");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("self.name=name");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("callitem=calls.popitem()#only one out port");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("self.calls=callitem[1]");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("self.port=callitem[0]");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("self.rules=rules.popitem()[1]#only one out port");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("self.detmodel=detmodel");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("self.event_cntr=0");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("self.events=[]");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("inport.registerListener(self)");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("self.actual_time=actual_time");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("def generateEvents(self):");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("self.events=[]");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("def getEvents(self):");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("eventcopy=self.events.copy()");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("self.events.clear()");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("return eventcopy");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("#definition of the interface functions");
      _builder.newLine();
      {
        EList<EventDeclaration> _events = i.getEvents();
        for(final EventDeclaration event : _events) {
          _builder.append("\t");
          _builder.append("def raise");
          String _firstUpper_1 = StringExtensions.toFirstUpper(event.getEvent().getName());
          _builder.append(_firstUpper_1, "\t");
          _builder.append("(self,");
          CharSequence _generateFuncParams = this.generateFuncParams(event.getEvent());
          _builder.append(_generateFuncParams, "\t");
          _builder.append("):");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          _builder.append("\t");
          _builder.append("time=self.rules[\"");
          String _firstUpper_2 = StringExtensions.toFirstUpper(event.getEvent().getName());
          _builder.append(_firstUpper_2, "\t\t");
          _builder.append("\"].calc(self.port+\".\"+\"");
          String _firstUpper_3 = StringExtensions.toFirstUpper(event.getEvent().getName());
          _builder.append(_firstUpper_3, "\t\t");
          _builder.append("\",self.actual_time[0])");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          _builder.append("\t");
          _builder.append("self.event_cntr=self.event_cntr+1");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("\t");
          _builder.append("failureTime=time+self.actual_time[0]");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("\t");
          _builder.append("for callitem in self.calls:");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("\t\t");
          _builder.append("callEvent=lambda:callitem.raise");
          String _firstUpper_4 = StringExtensions.toFirstUpper(event.getEvent().getName());
          _builder.append(_firstUpper_4, "\t\t\t");
          _builder.append("(");
          CharSequence _generateFuncParams_1 = this.generateFuncParams(event.getEvent());
          _builder.append(_generateFuncParams_1, "\t\t\t");
          _builder.append(");");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          _builder.append("\t\t");
          _builder.append("self.events.append(Event(self,failureTime,callEvent))");
          _builder.newLine();
        }
      }
      CharSequence _generateInterfaceSubClass = this.generateInterfaceSubClass(i);
      _builder.append(_generateInterfaceSubClass);
      _builder.newLineIfNotEmpty();
      _builder.newLine();
      classes.append(_builder);
    }
    return classes.toString();
  }
  
  public String generateSwitchClasses(final List<EnvironmentConnections> connections) {
    final Function1<EnvironmentConnections, Boolean> _function = (EnvironmentConnections c) -> {
      return Boolean.valueOf((c.component instanceof EnvironmentSwitch));
    };
    final Function1<EnvironmentConnections, Interface> _function_1 = (EnvironmentConnections c) -> {
      return c.component.getOutports().get(0).getInterfaceRealization().getInterface();
    };
    Set<Interface> interfaces = IterableExtensions.<Interface>toSet(IterableExtensions.<EnvironmentConnections, Interface>map(IterableExtensions.<EnvironmentConnections>filter(connections, _function), _function_1));
    StringBuilder classes = new StringBuilder();
    for (final Interface i : interfaces) {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("class Switch");
      String _firstUpper = StringExtensions.toFirstUpper(i.getName());
      _builder.append(_firstUpper);
      _builder.append("():");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("def __init__(self,name,inport,calls,portarray,categorical,detmodel,actual_time):");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("self.name=name");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("self.calls=calls");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("self.portarray=portarray");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("self.categorical=categorical");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("self.detmodel=detmodel");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("self.event_cntr=0");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("self.events=[]");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("inport.registerListener(self)");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("self.actual_time=actual_time\t\t\t");
      _builder.newLine();
      _builder.append("\t\t\t\t\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("def generateEvents(self):");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("self.events=[]");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("def getEvents(self):");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("eventcopy=self.events.copy()");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("self.events.clear()");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("return eventcopy");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("#definition of the interface functions");
      _builder.newLine();
      {
        EList<EventDeclaration> _events = i.getEvents();
        for(final EventDeclaration event : _events) {
          _builder.append("\t");
          _builder.append("def raise");
          String _firstUpper_1 = StringExtensions.toFirstUpper(event.getEvent().getName());
          _builder.append(_firstUpper_1, "\t");
          _builder.append("(self,");
          CharSequence _generateFuncParams = this.generateFuncParams(event.getEvent());
          _builder.append(_generateFuncParams, "\t");
          _builder.append("):");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          _builder.append("\t");
          _builder.append("port=self.portarray[self.categorical.calc()]");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("\t");
          _builder.append("eventcalls=self.calls[port]#[\"");
          String _firstUpper_2 = StringExtensions.toFirstUpper(event.getEvent().getName());
          _builder.append(_firstUpper_2, "\t\t");
          _builder.append("\"]");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          _builder.append("\t");
          _builder.append("self.event_cntr=self.event_cntr+1");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("\t");
          _builder.append("for call in eventcalls:");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("\t\t");
          _builder.append("callEvent=lambda:call.raise");
          String _firstUpper_3 = StringExtensions.toFirstUpper(event.getEvent().getName());
          _builder.append(_firstUpper_3, "\t\t\t");
          _builder.append("(");
          CharSequence _generateFuncParams_1 = this.generateFuncParams(event.getEvent());
          _builder.append(_generateFuncParams_1, "\t\t\t");
          _builder.append(");");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          _builder.append("\t\t");
          _builder.append("self.events.append(Event(self,self.actual_time[0],callEvent))");
          _builder.newLine();
        }
      }
      CharSequence _generateInterfaceSubClass = this.generateInterfaceSubClass(i);
      _builder.append(_generateInterfaceSubClass);
      _builder.newLineIfNotEmpty();
      classes.append(_builder);
    }
    return classes.toString();
  }
  
  public CharSequence generateExternSimulationTemplateClasses(final List<EnvironmentConnections> connections) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("#these are template classes for external simulation");
    _builder.newLine();
    _builder.append("#behavior is not specified only the interfaces");
    _builder.newLine();
    _builder.append("#TODO: add simulation behavior");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("#TODO: move the class into another file, this file might regenerate regularly on build");
    _builder.newLine();
    _builder.append("#TODO: remove \'ExternSimulationTemplateClass\' from class name");
    _builder.newLine();
    {
      for(final EnvironmentConnections connection : connections) {
        EnvironmentRule _get = connection.component.getBehaviorRules().get(0);
        String classname = ((SimulationRule) _get).getSimulation().getSimulationClassName();
        _builder.newLineIfNotEmpty();
        final Function1<Port, Interface> _function = (Port p) -> {
          return p.getInterfaceRealization().getInterface();
        };
        Set<Interface> interfaces = IterableExtensions.<Interface>toSet(ListExtensions.<Port, Interface>map(connection.component.getInports(), _function));
        _builder.newLineIfNotEmpty();
        _builder.append("class ");
        _builder.append(classname);
        _builder.append("ExternSimulationTemplateClass():");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("#interface ");
        _builder.newLine();
        {
          for(final Interface i : interfaces) {
            _builder.append("\t");
            _builder.append("class InPort");
            String _firstUpper = StringExtensions.toFirstUpper(i.getName());
            _builder.append(_firstUpper, "\t");
            _builder.append("():");
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            _builder.append("\t");
            _builder.append("def __init__(self,portname,portcall):");
            _builder.newLine();
            _builder.append("\t");
            _builder.append("\t\t");
            _builder.append("portcall.registerListener(self)");
            _builder.newLine();
            _builder.append("\t");
            _builder.append("\t\t");
            _builder.append("self.portcall=portcall");
            _builder.newLine();
            _builder.append("\t");
            _builder.append("\t\t");
            _builder.append("self.name=portname");
            _builder.newLine();
            _builder.append("\t");
            _builder.append("\t\t");
            _builder.append("self.events=dict()");
            _builder.newLine();
            _builder.append("\t");
            _builder.newLine();
            _builder.append("\t");
            _builder.append("def getEvents(self,globalevents):");
            _builder.newLine();
            _builder.append("\t");
            _builder.append("\t");
            _builder.append("globalevents.update(self.events)");
            _builder.newLine();
            _builder.append("\t");
            _builder.append("\t");
            _builder.append("self.events.clear()");
            _builder.newLine();
            _builder.append("\t");
            _builder.append("\t");
            _builder.newLine();
            _builder.append("\t");
            _builder.append("#definition of the interface functions");
            _builder.newLine();
            {
              EList<EventDeclaration> _events = i.getEvents();
              for(final EventDeclaration event : _events) {
                _builder.append("\t");
                _builder.append("def raise");
                String _firstUpper_1 = StringExtensions.toFirstUpper(event.getEvent().getName());
                _builder.append(_firstUpper_1, "\t");
                _builder.append("(self,");
                CharSequence _generateFuncParams = this.generateFuncParams(event.getEvent());
                _builder.append(_generateFuncParams, "\t");
                _builder.append("):");
                _builder.newLineIfNotEmpty();
                _builder.append("\t");
                _builder.append("\t");
                _builder.append("self.events[portname+\"->");
                String _firstUpper_2 = StringExtensions.toFirstUpper(event.getEvent().getName());
                _builder.append(_firstUpper_2, "\t\t");
                _builder.append("\",(");
                CharSequence _generateFuncParams_1 = this.generateFuncParams(event.getEvent());
                _builder.append(_generateFuncParams_1, "\t\t");
                _builder.append(")]");
                _builder.newLineIfNotEmpty();
              }
            }
            _builder.append("\t");
            _builder.newLine();
            _builder.append("\t");
            _builder.append("class Java:");
            _builder.newLine();
            _builder.append("\t");
            _builder.append("\t");
            _builder.append("implements = [\"");
            _builder.append(this.packageName, "\t\t");
            _builder.append(".interfaces.");
            String _firstUpper_3 = StringExtensions.toFirstUpper(i.getName());
            _builder.append(_firstUpper_3, "\t\t");
            _builder.append("Interface$Listener$Provided\"]");
            _builder.newLineIfNotEmpty();
          }
        }
        _builder.append("\t\t");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("#functions calling output event");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("#Do not modify these functions!");
        _builder.newLine();
        {
          EList<Port> _outports = connection.component.getOutports();
          for(final Port port : _outports) {
            {
              final Function1<EventDeclaration, Event> _function_1 = (EventDeclaration e) -> {
                return e.getEvent();
              };
              List<Event> _map = ListExtensions.<EventDeclaration, Event>map(port.getInterfaceRealization().getInterface().getEvents(), _function_1);
              for(final Event event_1 : _map) {
                _builder.append("\t");
                _builder.append("def call");
                String _firstUpper_4 = StringExtensions.toFirstUpper(port.getName());
                _builder.append(_firstUpper_4, "\t");
                String _firstUpper_5 = StringExtensions.toFirstUpper(event_1.getName());
                _builder.append(_firstUpper_5, "\t");
                _builder.append("(");
                CharSequence _generateFuncParams_2 = this.generateFuncParams(event_1);
                _builder.append(_generateFuncParams_2, "\t");
                _builder.append("):");
                _builder.newLineIfNotEmpty();
                {
                  Collection<String> _get_1 = connection.outCalls.get(port);
                  for(final String outCall : _get_1) {
                    _builder.append("\t");
                    _builder.append("\t");
                    _builder.append("callEvent=lambda:self.detmodel");
                    _builder.append(connection.componentCall, "\t\t");
                    _builder.append(outCall, "\t\t");
                    _builder.append(".raise");
                    String _firstUpper_6 = StringExtensions.toFirstUpper(event_1.getName());
                    _builder.append(_firstUpper_6, "\t\t");
                    _builder.append("(");
                    CharSequence _generateFuncParams_3 = this.generateFuncParams(event_1);
                    _builder.append(_generateFuncParams_3, "\t\t");
                    _builder.append(")");
                    _builder.newLineIfNotEmpty();
                    _builder.append("\t");
                    _builder.append("\t");
                    _builder.append("self.events.append(Event(self,actualTime,callEvent))");
                    _builder.newLine();
                  }
                }
              }
            }
          }
        }
        _builder.append("\t\t");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("def generateEvents(self):");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("self.events=[]");
        _builder.newLine();
        _builder.append("\t");
        _builder.newLine();
        _builder.append("\t");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("def getEvents(self):");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("eventcopy=self.events.copy()");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("self.events.clear()");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("return eventcopy");
        _builder.newLine();
        _builder.append("\t");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("def __init__(self,detmodel):");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("self.detmodel=detmodel");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("#init input ports");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("self.inports=[");
        _builder.newLine();
        {
          EList<Port> _inports = connection.component.getInports();
          boolean _hasElements = false;
          for(final Port inport : _inports) {
            if (!_hasElements) {
              _hasElements = true;
            } else {
              _builder.appendImmediate(", ", "\t\t");
            }
            {
              int _size = connection.inCalls.get(inport).size();
              boolean _greaterThan = (_size > 0);
              if (_greaterThan) {
                _builder.append("\t\t");
                _builder.append("InPort");
                String _firstUpper_7 = StringExtensions.toFirstUpper(inport.getInterfaceRealization().getInterface().getName());
                _builder.append(_firstUpper_7, "\t\t");
                _builder.append("(");
                _builder.newLineIfNotEmpty();
                _builder.append("\t\t");
                _builder.append("\t");
                _builder.append("portname=\"");
                String _firstUpper_8 = StringExtensions.toFirstUpper(inport.getName());
                _builder.append(_firstUpper_8, "\t\t\t");
                _builder.append("\",");
                _builder.newLineIfNotEmpty();
                _builder.append("\t\t");
                _builder.append("\t");
                _builder.append("portcall=detmodel");
                _builder.append(connection.componentCall, "\t\t\t");
                String _get_2 = ((String[])Conversions.unwrapArray(connection.inCalls.get(inport), String.class))[0];
                _builder.append(_get_2, "\t\t\t");
                _builder.newLineIfNotEmpty();
                _builder.append("\t\t");
                _builder.append(")");
                _builder.newLine();
              }
            }
          }
        }
        _builder.append("\t\t");
        _builder.append("]");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("self.inevents=dict()");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("self.outevents=list()");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("#TODO: init simulation");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("#...");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("pass");
        _builder.newLine();
        _builder.append("\t\t\t\t\t");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("def collectInEvents(self):");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("self.inevents.clear()");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("for port in inports:");
        _builder.newLine();
        _builder.append("\t\t\t");
        _builder.append("port.getEvents(self.inevents)");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("def updateInputs(self):");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("#collect the incoming events");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("self.collectInEvents()");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("#input check template");
        _builder.newLine();
        {
          EList<Port> _inports_1 = connection.component.getInports();
          for(final Port port_1 : _inports_1) {
            _builder.append("\t\t");
            _builder.append("#check ");
            String _firstUpper_9 = StringExtensions.toFirstUpper(port_1.getName());
            _builder.append(_firstUpper_9, "\t\t");
            _builder.newLineIfNotEmpty();
            {
              final Function1<EventDeclaration, Event> _function_2 = (EventDeclaration e) -> {
                return e.getEvent();
              };
              List<Event> _map_1 = ListExtensions.<EventDeclaration, Event>map(port_1.getInterfaceRealization().getInterface().getEvents(), _function_2);
              for(final Event event_2 : _map_1) {
                _builder.append("\t\t");
                _builder.append("if \"");
                String _firstUpper_10 = StringExtensions.toFirstUpper(port_1.getName());
                _builder.append(_firstUpper_10, "\t\t");
                _builder.append("_");
                String _firstUpper_11 = StringExtensions.toFirstUpper(event_2.getName());
                _builder.append(_firstUpper_11, "\t\t");
                _builder.append("\" in self.inevents:");
                _builder.newLineIfNotEmpty();
                _builder.append("\t\t");
                _builder.append("\t");
                _builder.append("parameters=self.inevents[\"");
                String _firstUpper_12 = StringExtensions.toFirstUpper(port_1.getName());
                _builder.append(_firstUpper_12, "\t\t\t");
                _builder.append("_");
                String _firstUpper_13 = StringExtensions.toFirstUpper(event_2.getName());
                _builder.append(_firstUpper_13, "\t\t\t");
                _builder.append("\"]");
                _builder.newLineIfNotEmpty();
                _builder.append("\t\t");
                _builder.append("\t");
                _builder.append("#TODO: handle the event");
                _builder.newLine();
                _builder.append("\t\t");
                _builder.append("\t");
                _builder.append("#...");
                _builder.newLine();
                _builder.append("\t\t");
                _builder.append("\t");
                _builder.append("pass");
                _builder.newLine();
              }
            }
          }
        }
        _builder.append("\t\t");
        _builder.newLine();
        _builder.append("\t\t\t\t\t");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("def updateSimulation(self):");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("#TODO: update the simulation");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("#...");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("pass");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("def release(self):");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("#TODO: release all allocated resources");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("#...");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("pass");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.newLine();
        _builder.append("\t");
        _builder.newLine();
      }
    }
    return _builder;
  }
  
  public CharSequence generateCategorical(final EnvironmentConnections connection) {
    StringConcatenation _builder = new StringConcatenation();
    final Function1<EnvironmentRule, StochasticRule> _function = (EnvironmentRule r) -> {
      return ((StochasticRule) r);
    };
    List<StochasticRule> rules = ListExtensions.<EnvironmentRule, StochasticRule>map(connection.component.getBehaviorRules(), _function);
    _builder.newLineIfNotEmpty();
    _builder.append("RandomVariable(");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("dist=pyro.distributions.Categorical(");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("torch.tensor([");
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
        final Function1<StochasticRule, String> _function_2 = (StochasticRule r) -> {
          StochasticModel _stochasticModel = r.getStochasticModel();
          return Double.toString(this.expEval.evaluateDecimal(((CategoricalProbabaility) _stochasticModel).getProbability()));
        };
        String _get = ((String[])Conversions.unwrapArray(IterableExtensions.<StochasticRule, String>map(IterableExtensions.<StochasticRule>filter(rules, _function_1), _function_2), String.class))[0];
        _builder.append(_get, "\t\t");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("\t\t");
    _builder.append("])),");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("name=\"");
    String _firstUpper = StringExtensions.toFirstUpper(connection.component.getName());
    _builder.append(_firstUpper, "\t");
    String _string = (PythonClassGenerator.distcntr++).toString();
    _builder.append(_string, "\t");
    _builder.append("\")");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  public CharSequence generateSwitchInstances(final List<EnvironmentConnections> connections) {
    StringConcatenation _builder = new StringConcatenation();
    {
      for(final EnvironmentConnections connection : connections) {
        _builder.append("components.append(");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("Switch");
        String _firstUpper = StringExtensions.toFirstUpper(connection.component.getInports().get(0).getInterfaceRealization().getInterface().getName());
        _builder.append(_firstUpper, "\t");
        _builder.append("(");
        _builder.newLineIfNotEmpty();
        _builder.append("\t\t");
        _builder.append("name  = \"");
        String _generateEnvCompName = TransformationUtility.generateEnvCompName(connection);
        _builder.append(_generateEnvCompName, "\t\t");
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
        CharSequence _generateCategorical = this.generateCategorical(connection);
        _builder.append(_generateCategorical, "\t\t");
        _builder.append(",");
        _builder.newLineIfNotEmpty();
        _builder.append("\t\t");
        _builder.append("detmodel=sctmodel,");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("actual_time = actualTime");
        _builder.newLine();
        _builder.append("\t");
        _builder.append(")");
        _builder.newLine();
        _builder.append(")");
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
          _builder.append("components.append(Delay");
          String _firstUpper = StringExtensions.toFirstUpper(connection.component.getOutports().get(0).getInterfaceRealization().getInterface().getName());
          _builder.append(_firstUpper);
          _builder.append("(");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          _builder.append("name  = \"");
          String _generateEnvCompName = TransformationUtility.generateEnvCompName(connection);
          _builder.append(_generateEnvCompName, "\t");
          _builder.append("\",");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          _builder.append("inport=");
          CharSequence _generateInPort = this.generateInPort(connection);
          _builder.append(_generateInPort, "\t");
          _builder.append(",");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          _builder.append("calls = ");
          CharSequence _generateCalls = this.generateCalls(connection);
          _builder.append(_generateCalls, "\t");
          _builder.append(",");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          _builder.append("rules = ");
          CharSequence _generateRules = this.generateRules(connection);
          _builder.append(_generateRules, "\t");
          _builder.append(",");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          _builder.append("detmodel = sctmodel,");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("actual_time = actualTime");
          _builder.newLine();
          _builder.append("))");
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
          _builder.append("components.append(Sample");
          String _firstUpper = StringExtensions.toFirstUpper(connection.component.getOutports().get(0).getInterfaceRealization().getInterface().getName());
          _builder.append(_firstUpper);
          _builder.append("(");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          _builder.append("name  = \"");
          String _generateEnvCompName = TransformationUtility.generateEnvCompName(connection);
          _builder.append(_generateEnvCompName, "\t");
          _builder.append("\",");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          _builder.append("inport=");
          CharSequence _generateInPort = this.generateInPort(connection);
          _builder.append(_generateInPort, "\t");
          _builder.append(",");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          _builder.append("calls = ");
          CharSequence _generateCalls = this.generateCalls(connection);
          _builder.append(_generateCalls, "\t");
          _builder.append(",");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          _builder.append("rules = ");
          CharSequence _generateRules = this.generateRules(connection);
          _builder.append(_generateRules, "\t");
          _builder.append(",");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          _builder.append("detmodel = sctmodel,");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("actual_time = actualTime");
          _builder.newLine();
          _builder.append("))");
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
        _builder.append("components.append(PeriodicEventSource(");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("name  = \"");
        String _generateEnvCompName = TransformationUtility.generateEnvCompName(connection);
        _builder.append(_generateEnvCompName, "\t");
        _builder.append("\",");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("calls = ");
        CharSequence _generateCallEvents = this.generateCallEvents(connection);
        _builder.append(_generateCallEvents, "\t");
        _builder.append(",");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("rules = ");
        CharSequence _generateRules = this.generateRules(connection);
        _builder.append(_generateRules, "\t");
        _builder.append(",");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("portevents = ");
        CharSequence _generatePortevents = this.generatePortevents(connection);
        _builder.append(_generatePortevents, "\t");
        _builder.append(",");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("detmodel = sctmodel");
        _builder.newLine();
        _builder.append("))");
        _builder.newLine();
      }
    }
    return _builder;
  }
  
  public CharSequence generateEventSourceInstances(final List<EnvironmentConnections> connections) {
    StringConcatenation _builder = new StringConcatenation();
    {
      for(final EnvironmentConnections connection : connections) {
        _builder.append("components.append(EventSource(");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("name  = \"");
        String _generateEnvCompName = TransformationUtility.generateEnvCompName(connection);
        _builder.append(_generateEnvCompName, "\t");
        _builder.append("\",");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("calls = ");
        CharSequence _generateCallEvents = this.generateCallEvents(connection);
        _builder.append(_generateCallEvents, "\t");
        _builder.append(",");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("rules = ");
        CharSequence _generateRules = this.generateRules(connection);
        _builder.append(_generateRules, "\t");
        _builder.append(",");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("portevents = ");
        CharSequence _generatePortevents = this.generatePortevents(connection);
        _builder.append(_generatePortevents, "\t");
        _builder.append(",");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("detmodel = sctmodel");
        _builder.newLine();
        _builder.append("))");
        _builder.newLine();
      }
    }
    return _builder;
  }
  
  public CharSequence generateEventClass() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class Event():");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def __init__(self,eventSource,eventTime):");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("self.eventSource=eventSource");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("self.eventTime=eventTime");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def __init__(self,eventSource,eventTime,eventCall):");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("self.eventSource=eventSource");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("self.eventTime=eventTime");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("self.eventCall=eventCall");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence generateRandomVariableClass() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class RandomVariable():");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def __init__(self,dist,name,N=100000):");
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
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence generateRandomVariableClass_old() {
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
    return _builder;
  }
  
  public CharSequence generateContinuousRandomVariableClass() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class ContinuousRandomVariable():");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def __init__(self,dist,name,N=100000):");
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
    _builder.append("\t");
    _builder.append("source=");
    CharSequence _generateDatasetInstance = this.generateDatasetInstance(variable.getSource());
    _builder.append(_generateDatasetInstance, "\t");
    _builder.append(",");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("name=\"FittedExpVariable");
    _builder.append(name, "\t");
    String _string = (PythonClassGenerator.distcntr++).toString();
    _builder.append(_string, "\t");
    _builder.append("\",");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("lr=");
    String _string_1 = Double.valueOf(variable.getLr()).toString();
    _builder.append(_string_1, "\t");
    _builder.newLineIfNotEmpty();
    _builder.append(")");
    _builder.newLine();
    return _builder;
  }
  
  protected CharSequence _generateStochasticModel(final FittedNormalRandomVariable variable, final String name) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("FittedNormalRandomVariable(");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("source=");
    CharSequence _generateDatasetInstance = this.generateDatasetInstance(variable.getSource());
    _builder.append(_generateDatasetInstance, "\t");
    _builder.append(",");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("name=\"FittedNormVariable");
    _builder.append(name, "\t");
    String _string = (PythonClassGenerator.distcntr++).toString();
    _builder.append(_string, "\t");
    _builder.append("\",");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("lr=");
    String _string_1 = Double.valueOf(variable.getLr()).toString();
    _builder.append(_string_1, "\t");
    _builder.newLineIfNotEmpty();
    _builder.append(")");
    _builder.newLine();
    return _builder;
  }
  
  protected CharSequence _generateStochasticModel(final ContinouosRandomVariable variable, final String name) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("ContinuousRandomVariable(");
    CharSequence _generateDitribution = this.generateDitribution(variable);
    _builder.append(_generateDitribution);
    _builder.append(",\"ContRandomVarriable");
    _builder.append(name);
    String _string = (PythonClassGenerator.distcntr++).toString();
    _builder.append(_string);
    _builder.append("\")");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  protected CharSequence _generateStochasticModel(final DiscreteRandomVariable variable, final String name) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("ContinuousRandomVariable(");
    CharSequence _generateDitribution = this.generateDitribution(variable);
    _builder.append(_generateDitribution);
    _builder.append(",\"DiscRandomVarriable");
    _builder.append(name);
    String _string = (PythonClassGenerator.distcntr++).toString();
    _builder.append(_string);
    _builder.append("\")");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  protected CharSequence _generateStochasticModel(final FittedGaussianProcess variable, final String name) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("GaussProcess(");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("dataset=");
    CharSequence _generateDatasetInstance = this.generateDatasetInstance(variable.getSource());
    _builder.append(_generateDatasetInstance, "\t");
    _builder.append(",");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("kernel=");
    String _generateKernel = PythonClassGenerator.generateKernel(variable.getKernel());
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
    String _string_1 = (PythonClassGenerator.distcntr++).toString();
    _builder.append(_string_1, "\t");
    _builder.append("\"");
    _builder.newLineIfNotEmpty();
    _builder.append(")");
    _builder.newLine();
    return _builder;
  }
  
  protected CharSequence _generateDitribution(final NormalRandomVariable dist) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("pyro.distributions.Normal(");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("torch.tensor(");
    String _string = Double.toString(this.expEval.evaluateDecimal(dist.getMean()));
    _builder.append(_string, "\t\t");
    _builder.append("),");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("torch.tensor(");
    String _string_1 = Double.toString(this.expEval.evaluateDecimal(dist.getScale()));
    _builder.append(_string_1, "\t\t");
    _builder.append(")");
    _builder.newLineIfNotEmpty();
    _builder.append(")");
    _builder.newLine();
    return _builder;
  }
  
  protected CharSequence _generateDitribution(final WeibullRandomVariable dist) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("pyro.distributions.Weibull(");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("concentration=torch.tensor(");
    String _string = Double.toString(this.expEval.evaluateDecimal(dist.getShape()));
    _builder.append(_string, "\t\t");
    _builder.append("),");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("shape=torch.tensor(");
    String _string_1 = Double.toString(this.expEval.evaluateDecimal(dist.getScale()));
    _builder.append(_string_1, "\t\t");
    _builder.append(")");
    _builder.newLineIfNotEmpty();
    _builder.append(")");
    _builder.newLine();
    return _builder;
  }
  
  protected CharSequence _generateDitribution(final GammaRandomVariable dist) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("pyro.distributions.Gamma(");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("concentration=torch.tensor(");
    String _string = Double.toString(this.expEval.evaluateDecimal(dist.getShape()));
    _builder.append(_string, "\t\t");
    _builder.append("),");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("rate=torch.tensor(");
    String _string_1 = Double.toString(this.expEval.evaluateDecimal(dist.getScale()));
    _builder.append(_string_1, "\t\t");
    _builder.append(")");
    _builder.newLineIfNotEmpty();
    _builder.append(")");
    _builder.newLine();
    return _builder;
  }
  
  protected CharSequence _generateDitribution(final ExponentialRandomVariable dist) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("pyro.distributions.Exponential(torch.tensor(");
    String _string = Double.toString(this.expEval.evaluateDecimal(dist.getRate()));
    _builder.append(_string);
    _builder.append("))");
    return _builder;
  }
  
  protected CharSequence _generateDitribution(final BernoulliRandomVariable dist) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("pyro.distributions.Bernoulli(torch.tensor(");
    String _string = Double.toString(this.expEval.evaluateDecimal(dist.getProbability()));
    _builder.append(_string);
    _builder.append("))");
    return _builder;
  }
  
  protected CharSequence _generateStochasticProcess(final FittedGaussianProcess gp) {
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
    CharSequence _generateDatasetInstance = this.generateDatasetInstance(gp.getSource());
    _builder.append(_generateDatasetInstance, "\t");
    _builder.append(",");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("kernel=");
    String _generateKernel = PythonClassGenerator.generateKernel(gp.getKernel());
    _builder.append(_generateKernel, "\t");
    _builder.newLineIfNotEmpty();
    _builder.append("),dist=None,dirac=None");
    _builder.newLine();
    return _builder;
  }
  
  protected CharSequence _generateDitribution(final DiracProcess dirac) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("dirac=");
    CharSequence _generateDatasetInstance = this.generateDatasetInstance(dirac.getSource());
    _builder.append(_generateDatasetInstance);
    _builder.append(",dist=None,mlgp=None");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  protected CharSequence _generateDatasetInstance(final InfluxDB dataset) {
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
  
  protected CharSequence _generateDatasetInstance(final PythonSimulation dataset) {
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
    _builder.append("\t\t");
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
    Object _generateKernel = PythonClassGenerator.generateKernel(kernel.getKernels().get(0));
    _builder.append(_generateKernel);
    _builder.append(",");
    Object _generateKernel_1 = PythonClassGenerator.generateKernel(kernel.getKernels().get(1));
    _builder.append(_generateKernel_1);
    _builder.append(")");
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
  
  public CharSequence generateDitribution(final StochasticModel dist) {
    if (dist instanceof BernoulliRandomVariable) {
      return _generateDitribution((BernoulliRandomVariable)dist);
    } else if (dist instanceof ExponentialRandomVariable) {
      return _generateDitribution((ExponentialRandomVariable)dist);
    } else if (dist instanceof GammaRandomVariable) {
      return _generateDitribution((GammaRandomVariable)dist);
    } else if (dist instanceof NormalRandomVariable) {
      return _generateDitribution((NormalRandomVariable)dist);
    } else if (dist instanceof WeibullRandomVariable) {
      return _generateDitribution((WeibullRandomVariable)dist);
    } else if (dist instanceof DiracProcess) {
      return _generateDitribution((DiracProcess)dist);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(dist).toString());
    }
  }
  
  public CharSequence generateStochasticProcess(final FittedGaussianProcess gp) {
    return _generateStochasticProcess(gp);
  }
  
  public CharSequence generateDatasetInstance(final DataSource dataset) {
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
