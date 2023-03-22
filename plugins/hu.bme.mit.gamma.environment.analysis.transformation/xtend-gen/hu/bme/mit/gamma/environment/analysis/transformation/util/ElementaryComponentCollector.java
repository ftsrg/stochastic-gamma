package hu.bme.mit.gamma.environment.analysis.transformation.util;

import com.google.common.base.Objects;
import hu.bme.mit.gamma.environment.model.ElementaryEnvironmentComponentInstance;
import hu.bme.mit.gamma.environment.model.EnvironmentAsynchronousCompositeComponent;
import hu.bme.mit.gamma.environment.model.EnvironmentAsynchronousCompositeComponentInstance;
import hu.bme.mit.gamma.environment.model.EnvironmentCascadeCompositeComponent;
import hu.bme.mit.gamma.environment.model.EnvironmentCascadeCompositeComponentInstance;
import hu.bme.mit.gamma.environment.model.EnvironmentComponentInstance;
import hu.bme.mit.gamma.environment.model.EnvironmentSynchronousCompositeComponent;
import hu.bme.mit.gamma.environment.model.EnvironmentSynchronousCompositeComponentInstance;
import hu.bme.mit.gamma.statechart.composite.AsynchronousAdapter;
import hu.bme.mit.gamma.statechart.composite.AsynchronousComponent;
import hu.bme.mit.gamma.statechart.composite.AsynchronousComponentInstance;
import hu.bme.mit.gamma.statechart.composite.BroadcastChannel;
import hu.bme.mit.gamma.statechart.composite.Channel;
import hu.bme.mit.gamma.statechart.composite.ComponentInstance;
import hu.bme.mit.gamma.statechart.composite.InstancePortReference;
import hu.bme.mit.gamma.statechart.composite.PortBinding;
import hu.bme.mit.gamma.statechart.composite.SimpleChannel;
import hu.bme.mit.gamma.statechart.composite.SynchronousComponent;
import hu.bme.mit.gamma.statechart.composite.SynchronousComponentInstance;
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
  protected static List<EnvironmentConnections> _collect(final ElementaryEnvironmentComponentInstance component, final List<ComponentInstance> callStack) {
    ArrayList<EnvironmentConnections> conn = CollectionLiterals.<EnvironmentConnections>newArrayList();
    EObject _eContainer = component.eContainer();
    if ((_eContainer instanceof EnvironmentAsynchronousCompositeComponent)) {
      EObject _eContainer_1 = component.eContainer();
      EnvironmentAsynchronousCompositeComponent supercomp = ((EnvironmentAsynchronousCompositeComponent) _eContainer_1);
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
            EList<InstancePortReference> _requiredPorts_1 = ((BroadcastChannel)chan).getRequiredPorts();
            for (final InstancePortReference req_1 : _requiredPorts_1) {
              ComponentInstance _instance_2 = req_1.getInstance();
              boolean _equals_2 = Objects.equal(_instance_2, component);
              if (_equals_2) {
                EnvironmentConnections.Builder.addInCall(req_1.getPort(), ((BroadcastChannel)chan).getProvidedPort().getPort());
              }
            }
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
    EObject _eContainer_2 = component.eContainer();
    if ((_eContainer_2 instanceof EnvironmentSynchronousCompositeComponent)) {
      EObject _eContainer_3 = component.eContainer();
      EnvironmentSynchronousCompositeComponent supercomp_1 = ((EnvironmentSynchronousCompositeComponent) _eContainer_3);
      EnvironmentConnections.Builder.init(component, callStack);
      EList<Channel> _channels_1 = supercomp_1.getChannels();
      for (final Channel chan_1 : _channels_1) {
        {
          ComponentInstance _instance_1 = chan_1.getProvidedPort().getInstance();
          boolean _equals_3 = Objects.equal(_instance_1, component);
          if (_equals_3) {
            if ((chan_1 instanceof SimpleChannel)) {
              EnvironmentConnections.Builder.addOutCall(((SimpleChannel)chan_1).getProvidedPort().getPort(), ((SimpleChannel)chan_1).getRequiredPort());
            }
            if ((chan_1 instanceof BroadcastChannel)) {
              EList<InstancePortReference> _requiredPorts = ((BroadcastChannel)chan_1).getRequiredPorts();
              for (final InstancePortReference req : _requiredPorts) {
                EnvironmentConnections.Builder.addOutCall(((BroadcastChannel)chan_1).getProvidedPort().getPort(), req);
              }
            }
          }
          if ((chan_1 instanceof SimpleChannel)) {
            ComponentInstance _instance_2 = ((SimpleChannel)chan_1).getRequiredPort().getInstance();
            boolean _equals_4 = Objects.equal(_instance_2, component);
            if (_equals_4) {
              EnvironmentConnections.Builder.addInCall(((SimpleChannel)chan_1).getRequiredPort().getPort(), ((SimpleChannel)chan_1).getProvidedPort());
            }
          }
          if ((chan_1 instanceof BroadcastChannel)) {
            final Function1<InstancePortReference, Boolean> _function = (InstancePortReference e) -> {
              ComponentInstance _instance_3 = e.getInstance();
              return Boolean.valueOf(Objects.equal(_instance_3, component));
            };
            final Consumer<InstancePortReference> _function_1 = (InstancePortReference e) -> {
              EnvironmentConnections.Builder.addInCall(e.getPort(), ((BroadcastChannel)chan_1).getProvidedPort());
            };
            IterableExtensions.<InstancePortReference>filter(((BroadcastChannel)chan_1).getRequiredPorts(), _function).forEach(_function_1);
          }
        }
      }
      EList<PortBinding> _portBindings_1 = supercomp_1.getPortBindings();
      for (final PortBinding bind_1 : _portBindings_1) {
        ComponentInstance _instance_1 = bind_1.getInstancePortReference().getInstance();
        boolean _equals_3 = Objects.equal(_instance_1, component);
        if (_equals_3) {
          RealizationMode _realizationMode_2 = bind_1.getCompositeSystemPort().getInterfaceRealization().getRealizationMode();
          boolean _equals_4 = Objects.equal(_realizationMode_2, RealizationMode.PROVIDED);
          if (_equals_4) {
            EnvironmentConnections.Builder.addOutCall(bind_1.getInstancePortReference().getPort(), bind_1.getCompositeSystemPort());
          }
          RealizationMode _realizationMode_3 = bind_1.getCompositeSystemPort().getInterfaceRealization().getRealizationMode();
          boolean _equals_5 = Objects.equal(_realizationMode_3, RealizationMode.REQUIRED);
          if (_equals_5) {
            EnvironmentConnections.Builder.addInCall(bind_1.getInstancePortReference().getPort(), bind_1.getCompositeSystemPort());
          }
        }
      }
      conn.add(EnvironmentConnections.Builder.build());
      return conn;
    }
    EObject _eContainer_4 = component.eContainer();
    if ((_eContainer_4 instanceof EnvironmentCascadeCompositeComponent)) {
      EObject _eContainer_5 = component.eContainer();
      EnvironmentCascadeCompositeComponent supercomp_2 = ((EnvironmentCascadeCompositeComponent) _eContainer_5);
      EnvironmentConnections.Builder.init(component, callStack);
      EList<Channel> _channels_2 = supercomp_2.getChannels();
      for (final Channel chan_2 : _channels_2) {
        {
          ComponentInstance _instance_2 = chan_2.getProvidedPort().getInstance();
          boolean _equals_6 = Objects.equal(_instance_2, component);
          if (_equals_6) {
            if ((chan_2 instanceof SimpleChannel)) {
              EnvironmentConnections.Builder.addOutCall(((SimpleChannel)chan_2).getProvidedPort().getPort(), ((SimpleChannel)chan_2).getRequiredPort());
            }
            if ((chan_2 instanceof BroadcastChannel)) {
              EList<InstancePortReference> _requiredPorts = ((BroadcastChannel)chan_2).getRequiredPorts();
              for (final InstancePortReference req : _requiredPorts) {
                EnvironmentConnections.Builder.addOutCall(((BroadcastChannel)chan_2).getProvidedPort().getPort(), req);
              }
            }
          }
          if ((chan_2 instanceof SimpleChannel)) {
            ComponentInstance _instance_3 = ((SimpleChannel)chan_2).getRequiredPort().getInstance();
            boolean _equals_7 = Objects.equal(_instance_3, component);
            if (_equals_7) {
              EnvironmentConnections.Builder.addInCall(((SimpleChannel)chan_2).getRequiredPort().getPort(), ((SimpleChannel)chan_2).getProvidedPort());
            }
          }
          if ((chan_2 instanceof BroadcastChannel)) {
            final Function1<InstancePortReference, Boolean> _function = (InstancePortReference e) -> {
              ComponentInstance _instance_4 = e.getInstance();
              return Boolean.valueOf(Objects.equal(_instance_4, component));
            };
            final Consumer<InstancePortReference> _function_1 = (InstancePortReference e) -> {
              EnvironmentConnections.Builder.addInCall(e.getPort(), ((BroadcastChannel)chan_2).getProvidedPort());
            };
            IterableExtensions.<InstancePortReference>filter(((BroadcastChannel)chan_2).getRequiredPorts(), _function).forEach(_function_1);
          }
        }
      }
      EList<PortBinding> _portBindings_2 = supercomp_2.getPortBindings();
      for (final PortBinding bind_2 : _portBindings_2) {
        ComponentInstance _instance_2 = bind_2.getInstancePortReference().getInstance();
        boolean _equals_6 = Objects.equal(_instance_2, component);
        if (_equals_6) {
          RealizationMode _realizationMode_4 = bind_2.getCompositeSystemPort().getInterfaceRealization().getRealizationMode();
          boolean _equals_7 = Objects.equal(_realizationMode_4, RealizationMode.PROVIDED);
          if (_equals_7) {
            EnvironmentConnections.Builder.addOutCall(bind_2.getInstancePortReference().getPort(), bind_2.getCompositeSystemPort());
          }
          RealizationMode _realizationMode_5 = bind_2.getCompositeSystemPort().getInterfaceRealization().getRealizationMode();
          boolean _equals_8 = Objects.equal(_realizationMode_5, RealizationMode.REQUIRED);
          if (_equals_8) {
            EnvironmentConnections.Builder.addInCall(bind_2.getInstancePortReference().getPort(), bind_2.getCompositeSystemPort());
          }
        }
      }
      conn.add(EnvironmentConnections.Builder.build());
      return conn;
    }
    return null;
  }

  protected static List<EnvironmentConnections> _collect(final EnvironmentAsynchronousCompositeComponentInstance component, final List<ComponentInstance> callStack) {
    ArrayList<EnvironmentConnections> connections = CollectionLiterals.<EnvironmentConnections>newArrayList();
    ArrayList<ComponentInstance> localStack = CollectionLiterals.<ComponentInstance>newArrayList();
    localStack.addAll(callStack);
    localStack.add(component);
    EList<EnvironmentComponentInstance> _environmentComponents = component.getType().getEnvironmentComponents();
    for (final EnvironmentComponentInstance subcomp : _environmentComponents) {
      connections.addAll(ElementaryComponentCollector.collect(subcomp, localStack));
    }
    EList<AsynchronousComponentInstance> _components = component.getType().getComponents();
    for (final AsynchronousComponentInstance subcomp_1 : _components) {
      connections.addAll(ElementaryComponentCollector.collect(subcomp_1, localStack));
    }
    return connections;
  }

  protected static List<EnvironmentConnections> _collect(final EnvironmentSynchronousCompositeComponentInstance component, final List<ComponentInstance> callStack) {
    ArrayList<EnvironmentConnections> connections = CollectionLiterals.<EnvironmentConnections>newArrayList();
    ArrayList<ComponentInstance> localStack = CollectionLiterals.<ComponentInstance>newArrayList();
    localStack.addAll(callStack);
    localStack.add(component);
    EList<EnvironmentComponentInstance> _environmentComponents = component.getType().getEnvironmentComponents();
    for (final EnvironmentComponentInstance subcomp : _environmentComponents) {
      connections.addAll(ElementaryComponentCollector.collect(subcomp, localStack));
    }
    EList<SynchronousComponentInstance> _components = component.getType().getComponents();
    for (final SynchronousComponentInstance subcomp_1 : _components) {
      connections.addAll(ElementaryComponentCollector.collect(subcomp_1, localStack));
    }
    return connections;
  }

  protected static List<EnvironmentConnections> _collect(final EnvironmentCascadeCompositeComponentInstance component, final List<ComponentInstance> callStack) {
    ArrayList<EnvironmentConnections> connections = CollectionLiterals.<EnvironmentConnections>newArrayList();
    ArrayList<ComponentInstance> localStack = CollectionLiterals.<ComponentInstance>newArrayList();
    localStack.addAll(callStack);
    localStack.add(component);
    EList<EnvironmentComponentInstance> _environmentComponents = component.getType().getEnvironmentComponents();
    for (final EnvironmentComponentInstance subcomp : _environmentComponents) {
      connections.addAll(ElementaryComponentCollector.collect(subcomp, localStack));
    }
    EList<SynchronousComponentInstance> _components = component.getType().getComponents();
    for (final SynchronousComponentInstance subcomp_1 : _components) {
      connections.addAll(ElementaryComponentCollector.collect(subcomp_1, localStack));
    }
    return connections;
  }

  protected static List<EnvironmentConnections> _collect(final AsynchronousComponentInstance component, final List<ComponentInstance> callStack) {
    ArrayList<EnvironmentConnections> connections = CollectionLiterals.<EnvironmentConnections>newArrayList();
    ArrayList<ComponentInstance> localStack = CollectionLiterals.<ComponentInstance>newArrayList();
    localStack.addAll(callStack);
    localStack.add(component);
    AsynchronousComponent _type = component.getType();
    if ((_type instanceof EnvironmentAsynchronousCompositeComponent)) {
      AsynchronousComponent _type_1 = component.getType();
      EnvironmentAsynchronousCompositeComponent comptype = ((EnvironmentAsynchronousCompositeComponent) _type_1);
      EList<EnvironmentComponentInstance> _environmentComponents = comptype.getEnvironmentComponents();
      for (final EnvironmentComponentInstance subcomp : _environmentComponents) {
        connections.addAll(ElementaryComponentCollector.collect(subcomp, localStack));
      }
      EList<AsynchronousComponentInstance> _components = comptype.getComponents();
      for (final AsynchronousComponentInstance subcomp_1 : _components) {
        connections.addAll(ElementaryComponentCollector.collect(subcomp_1, localStack));
      }
      return connections;
    }
    AsynchronousComponent _type_2 = component.getType();
    if ((_type_2 instanceof AsynchronousAdapter)) {
      AsynchronousComponent _type_3 = component.getType();
      SynchronousComponentInstance inst = ((AsynchronousAdapter) _type_3).getWrappedComponent();
      connections.addAll(ElementaryComponentCollector.collect(inst, localStack));
    }
    return connections;
  }

  protected static List<EnvironmentConnections> _collect(final SynchronousComponentInstance component, final List<ComponentInstance> callStack) {
    ArrayList<EnvironmentConnections> connections = CollectionLiterals.<EnvironmentConnections>newArrayList();
    ArrayList<ComponentInstance> localStack = CollectionLiterals.<ComponentInstance>newArrayList();
    localStack.addAll(callStack);
    localStack.add(component);
    SynchronousComponent _type = component.getType();
    if ((_type instanceof EnvironmentSynchronousCompositeComponent)) {
      SynchronousComponent _type_1 = component.getType();
      EnvironmentSynchronousCompositeComponent comptype = ((EnvironmentSynchronousCompositeComponent) _type_1);
      EList<EnvironmentComponentInstance> _environmentComponents = comptype.getEnvironmentComponents();
      for (final EnvironmentComponentInstance subcomp : _environmentComponents) {
        connections.addAll(ElementaryComponentCollector.collect(subcomp, localStack));
      }
      EList<SynchronousComponentInstance> _components = comptype.getComponents();
      for (final SynchronousComponentInstance subcomp_1 : _components) {
        connections.addAll(ElementaryComponentCollector.collect(subcomp_1, localStack));
      }
      return connections;
    } else {
      SynchronousComponent _type_2 = component.getType();
      if ((_type_2 instanceof EnvironmentCascadeCompositeComponent)) {
        SynchronousComponent _type_3 = component.getType();
        EnvironmentCascadeCompositeComponent comptype_1 = ((EnvironmentCascadeCompositeComponent) _type_3);
        EList<EnvironmentComponentInstance> _environmentComponents_1 = comptype_1.getEnvironmentComponents();
        for (final EnvironmentComponentInstance subcomp_2 : _environmentComponents_1) {
          connections.addAll(ElementaryComponentCollector.collect(subcomp_2, localStack));
        }
        EList<SynchronousComponentInstance> _components_1 = comptype_1.getComponents();
        for (final SynchronousComponentInstance subcomp_3 : _components_1) {
          connections.addAll(ElementaryComponentCollector.collect(subcomp_3, localStack));
        }
        return connections;
      } else {
        return connections;
      }
    }
  }

  public static List<EnvironmentConnections> collect(final ComponentInstance component, final List<ComponentInstance> callStack) {
    if (component instanceof EnvironmentAsynchronousCompositeComponentInstance) {
      return _collect((EnvironmentAsynchronousCompositeComponentInstance)component, callStack);
    } else if (component instanceof EnvironmentCascadeCompositeComponentInstance) {
      return _collect((EnvironmentCascadeCompositeComponentInstance)component, callStack);
    } else if (component instanceof EnvironmentSynchronousCompositeComponentInstance) {
      return _collect((EnvironmentSynchronousCompositeComponentInstance)component, callStack);
    } else if (component instanceof ElementaryEnvironmentComponentInstance) {
      return _collect((ElementaryEnvironmentComponentInstance)component, callStack);
    } else if (component instanceof AsynchronousComponentInstance) {
      return _collect((AsynchronousComponentInstance)component, callStack);
    } else if (component instanceof SynchronousComponentInstance) {
      return _collect((SynchronousComponentInstance)component, callStack);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(component, callStack).toString());
    }
  }
}
