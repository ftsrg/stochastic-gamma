package hu.bme.mit.gamma.analysis.transformation;

import com.google.common.base.Objects;
import hu.bme.mit.gamma.environment.model.ElementaryEnvironmentComponentInstance;
import hu.bme.mit.gamma.environment.model.EnvironmentComponentInstance;
import hu.bme.mit.gamma.environment.model.EnvironmentCompositeComponent;
import hu.bme.mit.gamma.environment.model.EnvironmentCompositeComponentInstance;
import hu.bme.mit.gamma.statechart.composite.BroadcastChannel;
import hu.bme.mit.gamma.statechart.composite.Channel;
import hu.bme.mit.gamma.statechart.composite.ComponentInstance;
import hu.bme.mit.gamma.statechart.composite.InstancePortReference;
import hu.bme.mit.gamma.statechart.composite.PortBinding;
import hu.bme.mit.gamma.statechart.composite.SimpleChannel;
import hu.bme.mit.gamma.statechart.interface_.RealizationMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;

@SuppressWarnings("all")
public class ElementaryComponentCollector {
  protected static List<EnvironmentConnections> _collect(final ElementaryEnvironmentComponentInstance component, final List<EnvironmentCompositeComponentInstance> callStack) {
    ArrayList<EnvironmentConnections> conn = CollectionLiterals.<EnvironmentConnections>newArrayList();
    EObject _eContainer = component.eContainer();
    EnvironmentCompositeComponent supercomp = ((EnvironmentCompositeComponent) _eContainer);
    EnvironmentConnections.Builder.init(component, callStack);
    EList<Channel> _channels = supercomp.getChannels();
    for (final Channel chan : _channels) {
      {
        ComponentInstance _instance = chan.getProvidedPort().getInstance();
        boolean _equals = Objects.equal(_instance, component);
        if (_equals) {
          if ((chan instanceof SimpleChannel)) {
            EnvironmentConnections.Builder.addOutCall(((SimpleChannel)chan).getProvidedPort().getPort(), ((SimpleChannel)chan).getRequiredPort());
          }
          if ((chan instanceof BroadcastChannel)) {
            EList<InstancePortReference> _requiredPorts = ((BroadcastChannel)chan).getRequiredPorts();
            for (final InstancePortReference req : _requiredPorts) {
              EnvironmentConnections.Builder.addOutCall(((BroadcastChannel)chan).getProvidedPort().getPort(), req);
            }
          }
        }
        if ((chan instanceof SimpleChannel)) {
          ComponentInstance _instance_1 = ((SimpleChannel)chan).getRequiredPort().getInstance();
          boolean _equals_1 = Objects.equal(_instance_1, component);
          if (_equals_1) {
            EnvironmentConnections.Builder.addInCall(((SimpleChannel)chan).getRequiredPort().getPort(), ((SimpleChannel)chan).getProvidedPort());
          }
        }
        if ((chan instanceof BroadcastChannel)) {
          final Function1<InstancePortReference, Boolean> _function = (InstancePortReference e) -> {
            ComponentInstance _instance_2 = e.getInstance();
            return Boolean.valueOf(Objects.equal(_instance_2, component));
          };
          final Consumer<InstancePortReference> _function_1 = (InstancePortReference e) -> {
            EnvironmentConnections.Builder.addOutCall(e.getPort(), ((BroadcastChannel)chan).getProvidedPort());
          };
          IterableExtensions.<InstancePortReference>filter(((BroadcastChannel)chan).getRequiredPorts(), _function).forEach(_function_1);
        }
      }
    }
    EList<PortBinding> _portBindings = supercomp.getPortBindings();
    for (final PortBinding bind : _portBindings) {
      ComponentInstance _instance = bind.getInstancePortReference().getInstance();
      boolean _equals = Objects.equal(_instance, component);
      if (_equals) {
        RealizationMode _realizationMode = bind.getCompositeSystemPort().getInterfaceRealization().getRealizationMode();
        boolean _equals_1 = Objects.equal(_realizationMode, RealizationMode.PROVIDED);
        if (_equals_1) {
          EnvironmentConnections.Builder.addOutCall(bind.getInstancePortReference().getPort(), bind.getCompositeSystemPort());
        }
        RealizationMode _realizationMode_1 = bind.getCompositeSystemPort().getInterfaceRealization().getRealizationMode();
        boolean _equals_2 = Objects.equal(_realizationMode_1, RealizationMode.REQUIRED);
        if (_equals_2) {
          EnvironmentConnections.Builder.addInCall(bind.getInstancePortReference().getPort(), bind.getCompositeSystemPort());
        }
      }
    }
    conn.add(EnvironmentConnections.Builder.build());
    return conn;
  }
  
  protected static List<EnvironmentConnections> _collect(final EnvironmentCompositeComponentInstance component, final List<EnvironmentCompositeComponentInstance> callStack) {
    ArrayList<EnvironmentConnections> connections = CollectionLiterals.<EnvironmentConnections>newArrayList();
    ArrayList<EnvironmentCompositeComponentInstance> localStack = CollectionLiterals.<EnvironmentCompositeComponentInstance>newArrayList();
    localStack.addAll(callStack);
    localStack.add(component);
    EList<EnvironmentComponentInstance> _environmentComponents = component.getType().getEnvironmentComponents();
    for (final EnvironmentComponentInstance subcomp : _environmentComponents) {
      connections.addAll(ElementaryComponentCollector.collect(subcomp, localStack));
    }
    return connections;
  }
  
  public static List<EnvironmentConnections> collect(final EnvironmentComponentInstance component, final List<EnvironmentCompositeComponentInstance> callStack) {
    if (component instanceof ElementaryEnvironmentComponentInstance) {
      return _collect((ElementaryEnvironmentComponentInstance)component, callStack);
    } else if (component instanceof EnvironmentCompositeComponentInstance) {
      return _collect((EnvironmentCompositeComponentInstance)component, callStack);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(component, callStack).toString());
    }
  }
}
