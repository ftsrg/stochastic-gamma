package hu.bme.mit.gamma.analysis.transformation;

import hu.bme.mit.gamma.environment.model.EnvironmentRule;
import hu.bme.mit.gamma.environment.model.SimulationRule;
import hu.bme.mit.gamma.expression.model.ParameterDeclaration;
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
public class SimulatorTemplateClassGenerator {
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
  
  public CharSequence generateExternSimulationTemplateClasses(final String packageName, final List<EnvironmentConnections> connections) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.newLine();
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
            _builder.append(packageName, "\t\t");
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
}
