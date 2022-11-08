package hu.bme.mit.gamma.analysis.transformation;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import hu.bme.mit.gamma.environment.model.ElementaryEnvironmentComponentInstance;
import hu.bme.mit.gamma.statechart.composite.ComponentInstance;
import hu.bme.mit.gamma.statechart.composite.InstancePortReference;
import hu.bme.mit.gamma.statechart.interface_.Port;
import java.util.List;
import org.eclipse.xtext.xbase.lib.StringExtensions;

@SuppressWarnings("all")
public class EnvironmentConnections {
  public static class Builder {
    private static Multimap<Port, String> inCalls;
    
    private static Multimap<Port, String> outCalls;
    
    private static String componentCall;
    
    private static ElementaryEnvironmentComponentInstance component;
    
    public static void addInCall(final Port port, final InstancePortReference listenerPort) {
      ComponentInstance _instance = listenerPort.getInstance();
      boolean _not = (!(_instance instanceof ElementaryEnvironmentComponentInstance));
      if (_not) {
        String _firstUpper = StringExtensions.toFirstUpper(listenerPort.getInstance().getName());
        String _plus = (".get" + _firstUpper);
        String _plus_1 = (_plus + "().get");
        String _firstUpper_1 = StringExtensions.toFirstUpper(listenerPort.getPort().getName());
        String _plus_2 = (_plus_1 + _firstUpper_1);
        String _plus_3 = (_plus_2 + "()");
        EnvironmentConnections.Builder.inCalls.put(port, _plus_3);
      }
    }
    
    public static void addInCall(final Port port, final Port listenerPort) {
      String _firstUpper = StringExtensions.toFirstUpper(listenerPort.getName());
      String _plus = (".get" + _firstUpper);
      String _plus_1 = (_plus + "()");
      EnvironmentConnections.Builder.inCalls.put(port, _plus_1);
    }
    
    public static void addOutCall(final Port port, final InstancePortReference listenerPort) {
      ComponentInstance _instance = listenerPort.getInstance();
      boolean _not = (!(_instance instanceof ElementaryEnvironmentComponentInstance));
      if (_not) {
        String _firstUpper = StringExtensions.toFirstUpper(listenerPort.getInstance().getName());
        String _plus = (".get" + _firstUpper);
        String _plus_1 = (_plus + "().get");
        String _firstUpper_1 = StringExtensions.toFirstUpper(listenerPort.getPort().getName());
        String _plus_2 = (_plus_1 + _firstUpper_1);
        String _plus_3 = (_plus_2 + "()");
        EnvironmentConnections.Builder.outCalls.put(port, _plus_3);
      } else {
        ComponentInstance _instance_1 = listenerPort.getInstance();
        String _generateEnvCompName = TransformationUtility.generateEnvCompName(EnvironmentConnections.Builder.componentCall, ((ElementaryEnvironmentComponentInstance) _instance_1));
        String _plus_4 = ("_self.components[" + _generateEnvCompName);
        String _plus_5 = (_plus_4 + "]");
        EnvironmentConnections.Builder.outCalls.put(port, _plus_5);
      }
    }
    
    public static void addOutCall(final Port port, final Port listenerPort) {
      String _firstUpper = StringExtensions.toFirstUpper(listenerPort.getName());
      String _plus = (".get" + _firstUpper);
      String _plus_1 = (_plus + "()");
      EnvironmentConnections.Builder.outCalls.put(port, _plus_1);
    }
    
    public static void init(final ElementaryEnvironmentComponentInstance _component, final List<ComponentInstance> callStack) {
      EnvironmentConnections.Builder.componentCall = "";
      for (final ComponentInstance comp : callStack) {
        String _firstUpper = StringExtensions.toFirstUpper(comp.getName());
        String _plus = ((EnvironmentConnections.Builder.componentCall + ".get") + _firstUpper);
        String _plus_1 = (_plus + "()");
        EnvironmentConnections.Builder.componentCall = _plus_1;
      }
      EnvironmentConnections.Builder.component = _component;
      EnvironmentConnections.Builder.inCalls = HashMultimap.<Port, String>create();
      EnvironmentConnections.Builder.outCalls = HashMultimap.<Port, String>create();
    }
    
    public static EnvironmentConnections build() {
      final EnvironmentConnections conn = new EnvironmentConnections(
        EnvironmentConnections.Builder.inCalls, 
        EnvironmentConnections.Builder.outCalls, 
        EnvironmentConnections.Builder.componentCall, 
        EnvironmentConnections.Builder.component);
      EnvironmentConnections.Builder.inCalls = HashMultimap.<Port, String>create();
      EnvironmentConnections.Builder.outCalls = HashMultimap.<Port, String>create();
      return conn;
    }
  }
  
  public final Multimap<Port, String> inCalls;
  
  public final Multimap<Port, String> outCalls;
  
  public final String componentCall;
  
  public final ElementaryEnvironmentComponentInstance component;
  
  private EnvironmentConnections(final Multimap<Port, String> _inCalls, final Multimap<Port, String> _outCalls, final String _componentCall, final ElementaryEnvironmentComponentInstance _component) {
    this.inCalls = _inCalls;
    this.outCalls = _outCalls;
    this.componentCall = _componentCall;
    this.component = _component;
  }
}
