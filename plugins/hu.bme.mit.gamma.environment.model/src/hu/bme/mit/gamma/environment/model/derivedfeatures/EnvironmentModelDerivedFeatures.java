package hu.bme.mit.gamma.environment.model.derivedfeatures;

import java.util.ArrayList;
import java.util.List;import org.eclipse.emf.ecore.EObject;

import hu.bme.mit.gamma.environment.model.ElementaryEnvironmentComponentInstance;
import hu.bme.mit.gamma.environment.model.EnvironmentAsynchronousCompositeComponent;
import hu.bme.mit.gamma.environment.model.EnvironmentAsynchronousCompositeComponentInstance;
import hu.bme.mit.gamma.environment.model.EnvironmentCascadeCompositeComponent;
import hu.bme.mit.gamma.environment.model.EnvironmentCascadeCompositeComponentInstance;
import hu.bme.mit.gamma.environment.model.EnvironmentComponentInstance;
import hu.bme.mit.gamma.environment.model.EnvironmentSample;
import hu.bme.mit.gamma.environment.model.EnvironmentSwitch;
import hu.bme.mit.gamma.environment.model.EnvironmentSynchronousCompositeComponent;
import hu.bme.mit.gamma.environment.model.EnvironmentSynchronousCompositeComponentInstance;
import hu.bme.mit.gamma.statechart.composite.AbstractAsynchronousCompositeComponent;
import hu.bme.mit.gamma.statechart.composite.AbstractSynchronousCompositeComponent;
import hu.bme.mit.gamma.statechart.composite.AsynchronousAdapter;
import hu.bme.mit.gamma.statechart.composite.AsynchronousComponentInstance;
import hu.bme.mit.gamma.statechart.composite.CascadeCompositeComponent;
import hu.bme.mit.gamma.statechart.composite.ComponentInstance;
import hu.bme.mit.gamma.statechart.composite.ComponentInstanceReferenceExpression;
import hu.bme.mit.gamma.statechart.composite.InstancePortReference;
import hu.bme.mit.gamma.statechart.composite.ScheduledAsynchronousCompositeComponent;
import hu.bme.mit.gamma.statechart.composite.SynchronousComponentInstance;
import hu.bme.mit.gamma.statechart.derivedfeatures.StatechartModelDerivedFeatures;
import hu.bme.mit.gamma.statechart.interface_.Component;
import hu.bme.mit.gamma.statechart.interface_.EventDeclaration;
import hu.bme.mit.gamma.statechart.interface_.EventDirection;
import hu.bme.mit.gamma.statechart.interface_.Interface;
import hu.bme.mit.gamma.statechart.interface_.InterfaceRealization;
import hu.bme.mit.gamma.statechart.interface_.Port;
import hu.bme.mit.gamma.statechart.interface_.RealizationMode;

public class EnvironmentModelDerivedFeatures extends StatechartModelDerivedFeatures {
	public static Component getDerivedType(ComponentInstance instance) {
		if (instance instanceof SynchronousComponentInstance) {
			SynchronousComponentInstance synchronousInstance = (SynchronousComponentInstance) instance;
			return synchronousInstance.getType();
		}
		if (instance instanceof AsynchronousComponentInstance) {
			AsynchronousComponentInstance asynchronousInstance = (AsynchronousComponentInstance) instance;
			return asynchronousInstance.getType();
		}
		if (instance instanceof EnvironmentAsynchronousCompositeComponentInstance) {
			EnvironmentAsynchronousCompositeComponentInstance asynchronousInstance = (EnvironmentAsynchronousCompositeComponentInstance) instance;
			return asynchronousInstance.getType();
		}
		if (instance instanceof EnvironmentSynchronousCompositeComponentInstance) {
			EnvironmentSynchronousCompositeComponentInstance synchronousInstance = (EnvironmentSynchronousCompositeComponentInstance) instance;
			return synchronousInstance.getType();
		}
		if (instance instanceof EnvironmentCascadeCompositeComponentInstance) {
			EnvironmentCascadeCompositeComponentInstance cascadeInstance = (EnvironmentCascadeCompositeComponentInstance) instance;
			return cascadeInstance.getType();
		}
		if (instance instanceof ElementaryEnvironmentComponentInstance) {
			ElementaryEnvironmentComponentInstance envInstace = (ElementaryEnvironmentComponentInstance) instance;
			return null;
		}
		throw new IllegalArgumentException("Not known type: " + instance);
	}
	public static List<Port> getTypePorts(ComponentInstance instance) {
		if (instance instanceof SynchronousComponentInstance) {
			SynchronousComponentInstance synchronousInstance = (SynchronousComponentInstance) instance;
			return synchronousInstance.getType().getPorts();
		}
		if (instance instanceof AsynchronousComponentInstance) {
			AsynchronousComponentInstance asynchronousInstance = (AsynchronousComponentInstance) instance;
			if (asynchronousInstance.getType() instanceof AsynchronousAdapter) {
				AsynchronousAdapter adapter = (AsynchronousAdapter) asynchronousInstance.getType();
				List<Port> ports=new ArrayList<>();
				ports.addAll(adapter.getPorts());
				ports.addAll(adapter.getWrappedComponent().getType().getPorts());
				return ports;
			}else {
				return asynchronousInstance.getType().getPorts();
			}
		}
		if (instance instanceof EnvironmentAsynchronousCompositeComponentInstance) {
			EnvironmentAsynchronousCompositeComponentInstance asynchronousInstance = (EnvironmentAsynchronousCompositeComponentInstance) instance;
			return asynchronousInstance.getType().getPorts();
		}
		if (instance instanceof EnvironmentSynchronousCompositeComponentInstance) {
			EnvironmentSynchronousCompositeComponentInstance synchronousInstance = (EnvironmentSynchronousCompositeComponentInstance) instance;
			return synchronousInstance.getType().getPorts();
		}
		if (instance instanceof EnvironmentCascadeCompositeComponentInstance) {
			EnvironmentCascadeCompositeComponentInstance cascadeInstance = (EnvironmentCascadeCompositeComponentInstance) instance;
			return cascadeInstance.getType().getPorts();
		}
		if (instance instanceof ElementaryEnvironmentComponentInstance) {
			List<Port> ports=new ArrayList<>();
			ElementaryEnvironmentComponentInstance envInstace = (ElementaryEnvironmentComponentInstance) instance;
			ports.addAll(envInstace.getInports());
			ports.addAll(envInstace.getOutports());
			return ports;
		}
		throw new IllegalArgumentException("Not known type: " + instance);
	}
	

	public static Component getContainingComponent(EObject object) {
		if (object == null) {
			throw new IllegalArgumentException("Not contained by a component: " + object);
		}
		if (object instanceof Component) {
			return (Component) object;
		}
		return getContainingComponent(object.eContainer());
	}
	
	public static boolean isBroadcast(Port port) {
		return isBroadcast(port.getInterfaceRealization());
	}
	
	public static boolean isBroadcastMatcher(Port port) {
		return isBroadcastMatcher(port.getInterfaceRealization());
	}
	
	public static boolean isBroadcastOrBroadcastMatcher(Port port) {
		return isBroadcast(port) || isBroadcastMatcher(port);
	}
	
	public static boolean isProvided(InstancePortReference port) {
		return isProvided(port.getPort());
	}
	
	public static boolean isProvided(Port port) {
		return isProvided(port.getInterfaceRealization());
	}
	
	public static boolean isRequired(InstancePortReference port) {
		return isRequired(port.getPort());
	}
	
	public static boolean isRequired(Port port) {
		return isRequired(port.getInterfaceRealization());
	}
	
	
	public static List<ComponentInstance> filterInstances(List<EnvironmentComponentInstance> components){
		List<ComponentInstance> fcomponents=new ArrayList<>();
		for (ComponentInstance component : components) {
			if (component instanceof EnvironmentSwitch || component instanceof EnvironmentSample) {
				fcomponents.add(component);
			}
		}
		return fcomponents;
	}
	
	public static List<? extends ComponentInstance> getScheduledInstances(Component component) {
		if (component instanceof EnvironmentSynchronousCompositeComponent synchronousComponent) {
			return getScheduledInstances(synchronousComponent);
		} else if (component instanceof EnvironmentCascadeCompositeComponent synchronousComponent) {
			return getScheduledInstances(synchronousComponent);
		} else if (component instanceof EnvironmentAsynchronousCompositeComponent asynchronousComponent) {
			return getScheduledInstances(asynchronousComponent);
		}
		if (component instanceof EnvironmentSynchronousCompositeComponent synchronousComponent) {
			return getScheduledInstances(synchronousComponent);
		} else if (component instanceof AbstractAsynchronousCompositeComponent asynchronousComponent) {
			return getScheduledInstances(asynchronousComponent);
		}
		else if (component instanceof AsynchronousAdapter asynchronusAdapter) {
			return List.of(asynchronusAdapter.getWrappedComponent());
		}
		throw new IllegalArgumentException("Not known component: " + component);
	}
	
	public static List<ComponentInstance> getScheduledInstances(
			EnvironmentSynchronousCompositeComponent component) {
		List<ComponentInstance> components=new ArrayList<>();
		components.addAll(component.getComponents());
		components.addAll(component.getEnvironmentComponents());
		return components;
	}
	
	
	public static List<ComponentInstance> getScheduledInstances(
			EnvironmentCascadeCompositeComponent component) {
		
		List<ComponentInstanceReferenceExpression> executionList = component.getExecutionList();
		if (!executionList.isEmpty()) {
			List<ComponentInstance> instances = new ArrayList<ComponentInstance>();
			for (ComponentInstanceReferenceExpression instanceReference : executionList) {
				ComponentInstance componentInstance =
					(ComponentInstance) instanceReference.getComponentInstance();
				instances.add(componentInstance);
			}
			return instances;
		}
		List<ComponentInstance> components=new ArrayList<>();
		components.addAll(component.getComponents());
		components.addAll(filterInstances(component.getEnvironmentComponents()));
		return components;
	}
	
	public static List<ComponentInstance> getScheduledInstances(
			EnvironmentAsynchronousCompositeComponent component) {
		List<ComponentInstance> components=new ArrayList<>();
		components.addAll(component.getComponents());
		components.addAll(filterInstances(component.getEnvironmentComponents()));
		return components;
	}
	
	public static List<SynchronousComponentInstance> getScheduledInstances(
			AbstractSynchronousCompositeComponent component) {
		if (component instanceof CascadeCompositeComponent) {
			CascadeCompositeComponent cascade = (CascadeCompositeComponent) component;
			List<ComponentInstanceReferenceExpression> executionList = cascade.getExecutionList();
			if (!executionList.isEmpty()) {
				List<SynchronousComponentInstance> instances =
						new ArrayList<SynchronousComponentInstance>();
				for (ComponentInstanceReferenceExpression instanceReference : executionList) {
					SynchronousComponentInstance componentInstance =
						(SynchronousComponentInstance) instanceReference.getComponentInstance();
					instances.add(componentInstance);
				}
				return instances;
			}
		}
		return component.getComponents();
	}
	
	public static List<AsynchronousComponentInstance> getScheduledInstances(
			AbstractAsynchronousCompositeComponent component) {
		if (component instanceof ScheduledAsynchronousCompositeComponent) {
			ScheduledAsynchronousCompositeComponent scheduledComponent =
					(ScheduledAsynchronousCompositeComponent) component;
			List<ComponentInstanceReferenceExpression> executionList = scheduledComponent.getExecutionList();
			if (!executionList.isEmpty()) {
				List<AsynchronousComponentInstance> instances =
						new ArrayList<AsynchronousComponentInstance>();
				for (ComponentInstanceReferenceExpression instanceReference : executionList) {
					AsynchronousComponentInstance componentInstance =
						(AsynchronousComponentInstance) instanceReference.getComponentInstance();
					instances.add(componentInstance);
				}
				return instances;
			}
		}
		return component.getComponents();
	}
	
}
