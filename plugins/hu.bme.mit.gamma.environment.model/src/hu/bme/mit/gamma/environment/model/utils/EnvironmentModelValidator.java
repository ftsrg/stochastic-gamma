package hu.bme.mit.gamma.environment.model.utils;

import hu.bme.mit.gamma.statechart.util.ExpressionTypeDeterminator;
import hu.bme.mit.gamma.statechart.util.StatechartModelValidator;
import hu.bme.mit.gamma.statechart.util.StatechartUtil;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;

import hu.bme.mit.gamma.environment.model.ElementaryEnvironmentComponentInstance;
import hu.bme.mit.gamma.expression.model.Expression;
import hu.bme.mit.gamma.expression.model.ExpressionModelPackage;
import hu.bme.mit.gamma.expression.model.ParameterDeclaration;
import hu.bme.mit.gamma.expression.model.Type;
import hu.bme.mit.gamma.statechart.composite.AbstractAsynchronousCompositeComponent;
import hu.bme.mit.gamma.statechart.composite.AbstractSynchronousCompositeComponent;
import hu.bme.mit.gamma.statechart.composite.AsynchronousAdapter;
import hu.bme.mit.gamma.statechart.composite.AsynchronousComponent;
import hu.bme.mit.gamma.statechart.composite.AsynchronousComponentInstance;
import hu.bme.mit.gamma.statechart.composite.AsynchronousCompositeComponent;
import hu.bme.mit.gamma.statechart.composite.BroadcastChannel;
import hu.bme.mit.gamma.statechart.composite.CascadeCompositeComponent;
import hu.bme.mit.gamma.statechart.composite.Channel;
import hu.bme.mit.gamma.statechart.composite.ComponentInstance;
import hu.bme.mit.gamma.statechart.composite.ComponentInstanceReferenceExpression;
import hu.bme.mit.gamma.statechart.composite.CompositeComponent;
import hu.bme.mit.gamma.statechart.composite.CompositeModelPackage;
import hu.bme.mit.gamma.statechart.composite.ControlSpecification;
import hu.bme.mit.gamma.statechart.composite.EventPassing;
import hu.bme.mit.gamma.statechart.composite.InstancePortReference;
import hu.bme.mit.gamma.statechart.composite.MessageQueue;
import hu.bme.mit.gamma.statechart.composite.PortBinding;
import hu.bme.mit.gamma.statechart.composite.ScheduledAsynchronousCompositeComponent;
import hu.bme.mit.gamma.statechart.composite.SimpleChannel;
import hu.bme.mit.gamma.statechart.composite.SynchronousComponentInstance;
import hu.bme.mit.gamma.statechart.derivedfeatures.StatechartModelDerivedFeatures;
import hu.bme.mit.gamma.statechart.interface_.AnyTrigger;
import hu.bme.mit.gamma.statechart.interface_.Clock;
import hu.bme.mit.gamma.statechart.interface_.Component;
import hu.bme.mit.gamma.statechart.interface_.Event;
import hu.bme.mit.gamma.statechart.interface_.EventDirection;
import hu.bme.mit.gamma.statechart.interface_.EventReference;
import hu.bme.mit.gamma.statechart.interface_.EventTrigger;
import hu.bme.mit.gamma.statechart.interface_.Interface;
import hu.bme.mit.gamma.statechart.interface_.InterfaceModelPackage;
import hu.bme.mit.gamma.statechart.interface_.InterfaceRealization;
import hu.bme.mit.gamma.statechart.interface_.Package;
import hu.bme.mit.gamma.statechart.interface_.Port;
import hu.bme.mit.gamma.statechart.interface_.RealizationMode;
import hu.bme.mit.gamma.statechart.interface_.SimpleTrigger;
import hu.bme.mit.gamma.statechart.interface_.Trigger;
import hu.bme.mit.gamma.statechart.statechart.AnyPortEventReference;
import hu.bme.mit.gamma.statechart.statechart.PortEventReference;
import hu.bme.mit.gamma.statechart.statechart.StatechartModelPackage;

public class EnvironmentModelValidator extends StatechartModelValidator {

	public static final EnvironmentModelValidator INSTANCE = new EnvironmentModelValidator();

	protected EnvironmentModelValidator() {
		super.typeDeterminator = ExpressionTypeDeterminator.INSTANCE; // For state reference
		super.expressionUtil = StatechartUtil.INSTANCE; // For getDeclaration
	}

	public Collection<ValidationResultMessage> checkName(Package _package) {
		Collection<ValidationResultMessage> validationResultMessages = new ArrayList<ValidationResultMessage>();
		try {
			if (!_package.getName().toLowerCase().equals(_package.getName())) {
				validationResultMessages.add(new ValidationResultMessage(ValidationResult.INFO,
						"Package names in the generated code will not contain uppercase letters",
						new ReferenceInfo(ExpressionModelPackage.Literals.NAMED_ELEMENT__NAME)));
			}
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
			System.out.println(exception.getCause());
			exception.printStackTrace();
		}
		return validationResultMessages;
	}

	public Collection<ValidationResultMessage> checkCircularDependencies(Component component) {
		Collection<ValidationResultMessage> validationResultMessages = new ArrayList<ValidationResultMessage>();
		try {
			Component referringComponent = StatechartModelDerivedFeatures.getReferringSubcomponent(component);
			if (referringComponent != null) {
				validationResultMessages.add(new ValidationResultMessage(ValidationResult.ERROR,
						"This component is in a dependency circle, referred by component "
								+ referringComponent.getName()
								+ "; composite systems must have an acyclical dependency hierarchy",
						new ReferenceInfo(ExpressionModelPackage.Literals.NAMED_ELEMENT__NAME)));
			}
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
			System.out.println(exception.getCause());
			exception.printStackTrace();
		}
		return validationResultMessages;
	}

	public Collection<ValidationResultMessage> checkMultipleImports(Package gammaPackage) {
		Collection<ValidationResultMessage> validationResultMessages = new ArrayList<ValidationResultMessage>();
		try {
			Set<Package> importedPackages = new HashSet<Package>();
			importedPackages.add(gammaPackage);
			for (Package importedPackage : gammaPackage.getImports()) {
				if (importedPackages.contains(importedPackage)) {
					int index = gammaPackage.getImports().indexOf(importedPackage);
					validationResultMessages.add(new ValidationResultMessage(ValidationResult.WARNING,
							"Package " + importedPackage.getName() + " is already imported",
							new ReferenceInfo(InterfaceModelPackage.Literals.PACKAGE__IMPORTS, index)));
				}
				importedPackages.add(importedPackage);
			}
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
			System.out.println(exception.getCause());
			exception.printStackTrace();
		}
		return validationResultMessages;
	}

	public Collection<ValidationResultMessage> checkParameters(ComponentInstance instance) {
		Collection<ValidationResultMessage> validationResultMessages = new ArrayList<ValidationResultMessage>();
		try {
			Component type = StatechartModelDerivedFeatures.getDerivedType(instance);
			if (instance.getArguments().size() != type.getParameterDeclarations().size()) {
				validationResultMessages
						.add(new ValidationResultMessage(ValidationResult.ERROR, "The number of arguments is wrong",
								new ReferenceInfo(ExpressionModelPackage.Literals.ARGUMENTED_ELEMENT__ARGUMENTS)));
			}
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
			System.out.println(exception.getCause());
			exception.printStackTrace();
		}
		return validationResultMessages;
	}

	public Collection<ValidationResultMessage> checkComponentInstanceArguments(ComponentInstance instance) {
		Collection<ValidationResultMessage> validationResultMessages = new ArrayList<ValidationResultMessage>();
		try {
			try {
				Component type = EnvironmentModelDerivedFeatures.getDerivedType(instance);
				if (type == null) {
					return validationResultMessages;
				}
				List<ParameterDeclaration> parameters = type.getParameterDeclarations();
				for (int i = 0; i < parameters.size(); ++i) {
					ParameterDeclaration parameter = parameters.get(i);
					Expression argument = instance.getArguments().get(i);
					Type declarationType = parameter.getType();
					if (!typeDeterminator.equalsType(declarationType, argument)) {
						validationResultMessages.add(new ValidationResultMessage(ValidationResult.ERROR,
								"The types of the declaration and the right hand side expression are not the same: "
										+ typeDeterminator.print(declarationType) + " and "
										+ typeDeterminator.print(argument),
								new ReferenceInfo(ExpressionModelPackage.Literals.ARGUMENTED_ELEMENT__ARGUMENTS, i)));
					}
				}
			} catch (Exception exception) {
				System.out.println(exception.getCause());
				System.out.println(exception.getMessage());
				exception.printStackTrace();
			}
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
			System.out.println(exception.getCause());
			exception.printStackTrace();
		}
		return validationResultMessages;
	}

	public Collection<ValidationResultMessage> checkComponentInstances(ComponentInstance instance) {
		Collection<ValidationResultMessage> validationResultMessages = new ArrayList<ValidationResultMessage>();
		try {
			Component type = StatechartModelDerivedFeatures.getContainingComponent(instance);
			EObject container = instance.eContainer();
			if (type instanceof AsynchronousAdapter || !(container instanceof CompositeComponent)) {
				// Not checking AsynchronousAdapters or port bindings not contained by
				// CompositeComponents
				return validationResultMessages;
			}
			Collection<Port> unusedPorts = StatechartModelDerivedFeatures.getUnusedPorts(instance);
			if (!unusedPorts.isEmpty()) {
				validationResultMessages.add(new ValidationResultMessage(ValidationResult.WARNING,
						"The following ports are used neither in a system port binding nor a channel: "
								+ unusedPorts.stream().map(it -> it.getName()).collect(Collectors.toSet()),
						new ReferenceInfo(ExpressionModelPackage.Literals.NAMED_ELEMENT__NAME)));
			}
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
			System.out.println(exception.getCause());
			exception.printStackTrace();
		}
		return validationResultMessages;
	}

	public Collection<ValidationResultMessage> checkPortBindingUniqueness(PortBinding portBinding) {
		Collection<ValidationResultMessage> validationResultMessages = new ArrayList<ValidationResultMessage>();
		try {
			Port systemPort = portBinding.getCompositeSystemPort();
			Port instancePort = portBinding.getInstancePortReference().getPort();
			ComponentInstance instance = portBinding.getInstancePortReference().getInstance();
			EObject container = portBinding.eContainer();
			List<PortBinding> portBindings = ecoreUtil.getContentsOfType(container, PortBinding.class);
			if (!StatechartModelDerivedFeatures.getOutputEvents(systemPort).isEmpty() && // Valid for only input ports
					portBindings.stream().filter(it -> it.getCompositeSystemPort() == systemPort).count() > 1) {
				validationResultMessages.add(new ValidationResultMessage(ValidationResult.ERROR,
						"This system port is connected to multiple ports of instances",
						new ReferenceInfo(CompositeModelPackage.Literals.PORT_BINDING__COMPOSITE_SYSTEM_PORT)));
			}
			if (portBindings.stream().filter(it -> it.getInstancePortReference().getPort() == instancePort
					&& it.getInstancePortReference().getInstance() == instance).count() > 1) {
				// Erroneous even for broadcast ports as "outwards" port binding should be
				// trivial (single path)
				validationResultMessages.add(new ValidationResultMessage(ValidationResult.ERROR,
						"Multiple system ports are connected to the port of this instance",
						new ReferenceInfo(CompositeModelPackage.Literals.PORT_BINDING__INSTANCE_PORT_REFERENCE)));
			}
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
			System.out.println(exception.getCause());
			exception.printStackTrace();
		}
		return validationResultMessages;
	}

	public Collection<ValidationResultMessage> checkPortBinding(PortBinding portBinding) {
		Collection<ValidationResultMessage> validationResultMessages = new ArrayList<ValidationResultMessage>();
		try {
			InterfaceRealization compositeInterfaceRealization = portBinding.getCompositeSystemPort()
					.getInterfaceRealization();
			InterfaceRealization instanceInterfaceRealization = portBinding.getInstancePortReference().getPort()
					.getInterfaceRealization();
			RealizationMode systemPortRealizationMode = compositeInterfaceRealization.getRealizationMode();
			RealizationMode instancePortRealizationMode = instanceInterfaceRealization.getRealizationMode();
			Interface systemPortInterface = compositeInterfaceRealization.getInterface();
			Interface instancePortInterface = instanceInterfaceRealization.getInterface();
			if (systemPortRealizationMode != instancePortRealizationMode) {
				validationResultMessages.add(new ValidationResultMessage(ValidationResult.ERROR,
						"Ports can be connected only if their interface types match and this is not realized in this case: "
								+ systemPortRealizationMode.getName() + " -> " + instancePortRealizationMode.getName(),
						new ReferenceInfo(CompositeModelPackage.Literals.PORT_BINDING__INSTANCE_PORT_REFERENCE)));
			}
			if (systemPortInterface != instancePortInterface) {
				validationResultMessages.add(new ValidationResultMessage(ValidationResult.ERROR,
						"Ports can be connected only if their interfaces match and this is not realized in this case: "
								+ systemPortInterface.getName() + " -> " + instancePortInterface.getName(),
						new ReferenceInfo(CompositeModelPackage.Literals.PORT_BINDING__INSTANCE_PORT_REFERENCE)));
			}
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
			System.out.println(exception.getCause());
			exception.printStackTrace();
		}
		return validationResultMessages;
	}

	public Collection<ValidationResultMessage> checkPortBinding(Port port) {
		Collection<ValidationResultMessage> validationResultMessages = new ArrayList<ValidationResultMessage>();
		try {
			Component container = StatechartModelDerivedFeatures.getContainingComponent(port);
			if (port.eContainer() instanceof ElementaryEnvironmentComponentInstance) {
				return validationResultMessages;
			}
			if (container instanceof CompositeComponent) {
				CompositeComponent componentDefinition = (CompositeComponent) container;
				for (PortBinding portDefinition : componentDefinition.getPortBindings()) {
					if (portDefinition.getCompositeSystemPort() == port) {
						return validationResultMessages;
					}
				}
				validationResultMessages.add(new ValidationResultMessage(ValidationResult.WARNING,
						"This system port is not connected to any ports of an instance",
						new ReferenceInfo(ExpressionModelPackage.Literals.NAMED_ELEMENT__NAME)));
			}
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
			System.out.println(exception.getCause());
			exception.printStackTrace();
		}
		return validationResultMessages;
	}

	public Collection<ValidationResultMessage> checkInstancePortReference(InstancePortReference reference) {
		Collection<ValidationResultMessage> validationResultMessages = new ArrayList<ValidationResultMessage>();
		try {
			ComponentInstance instance = reference.getInstance();
			if (instance == null) {
				return validationResultMessages;
			}
			Port port = reference.getPort();
			List<Port> ports = EnvironmentModelDerivedFeatures.getTypePorts(instance);
			if (!ports.contains(port)) {
				validationResultMessages.add(new ValidationResultMessage(ValidationResult.ERROR,
						"The specified port is not on instance " + instance.getName(),
						new ReferenceInfo(CompositeModelPackage.Literals.INSTANCE_PORT_REFERENCE__PORT)));
			}
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
			System.out.println(exception.getCause());
			exception.printStackTrace();
		}
		return validationResultMessages;
	}

	public Collection<ValidationResultMessage> checkPortBindingWithSimpleChannel(SimpleChannel channel) {
		Collection<ValidationResultMessage> validationResultMessages = new ArrayList<ValidationResultMessage>();
		try {
			EObject root = EcoreUtil.getRootContainer(channel);
			Collection<PortBinding> portDefinitions = ecoreUtil.getAllContentsOfType(root, PortBinding.class);
			for (PortBinding portDefinition : portDefinitions) {
				// Broadcast ports can be used in multiple places
				InstancePortReference providedPort = channel.getProvidedPort();
				if (!StatechartModelDerivedFeatures.isBroadcast(providedPort.getPort())
						&& StatechartModelDerivedFeatures.equals(providedPort,
								portDefinition.getInstancePortReference())) {
					validationResultMessages.add(new ValidationResultMessage(ValidationResult.ERROR,
							"A port of an instance can be included either in a channel or a port binding",
							new ReferenceInfo(CompositeModelPackage.Literals.CHANNEL__PROVIDED_PORT)));
				}
				if (StatechartModelDerivedFeatures.equals(channel.getRequiredPort(),
						portDefinition.getInstancePortReference())) {
					validationResultMessages.add(new ValidationResultMessage(ValidationResult.ERROR,
							"A port of an instance can be included either in a channel or a port binding",
							new ReferenceInfo(CompositeModelPackage.Literals.SIMPLE_CHANNEL__REQUIRED_PORT)));
				}
			}
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
			System.out.println(exception.getCause());
			exception.printStackTrace();
		}
		return validationResultMessages;
	}

	public Collection<ValidationResultMessage> checkPortBindingWithBroadcastChannel(BroadcastChannel channel) {
		Collection<ValidationResultMessage> validationResultMessages = new ArrayList<ValidationResultMessage>();
		try {
			EObject root = EcoreUtil.getRootContainer(channel);
			Collection<PortBinding> portDefinitions = ecoreUtil.getAllContentsOfType(root, PortBinding.class);
			for (PortBinding portDefinition : portDefinitions) {
				for (InstancePortReference output : channel.getRequiredPorts()) {
					if (StatechartModelDerivedFeatures.equals(output, portDefinition.getInstancePortReference())) {
						validationResultMessages.add(new ValidationResultMessage(ValidationResult.ERROR,
								"A port of an instance can be included either in a channel or a port binding",
								new ReferenceInfo(CompositeModelPackage.Literals.BROADCAST_CHANNEL__REQUIRED_PORTS)));
					}
				}
			}
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
			System.out.println(exception.getCause());
			exception.printStackTrace();
		}
		return validationResultMessages;
	}

	public Collection<ValidationResultMessage> checkChannelProvidedPorts(Channel channel) {
		Collection<ValidationResultMessage> validationResultMessages = new ArrayList<ValidationResultMessage>();
		try {
			Component parentComponent = (Component) channel.eContainer();
			// Ports inside asynchronous components can be connected to multiple ports
			if (parentComponent instanceof AbstractAsynchronousCompositeComponent) {
				return validationResultMessages;
			}
			// Checking provided instance ports in different channels
			EObject root = EcoreUtil.getRootContainer(channel);
			Collection<InstancePortReference> instancePortReferences = ecoreUtil.getAllContentsOfType(root,
					InstancePortReference.class);
			for (InstancePortReference instancePortReference : instancePortReferences.stream()
					.filter(it -> it != channel.getProvidedPort() && it.eContainer() instanceof Channel)
					.collect(Collectors.toList())) {
				// Broadcast ports are also restricted to be used only in a single channel
				// (restriction on syntax only)
				if (StatechartModelDerivedFeatures.equals(instancePortReference, channel.getProvidedPort())) {
					validationResultMessages.add(new ValidationResultMessage(ValidationResult.ERROR,
							"A port of an instance can be included only in a single channel",
							new ReferenceInfo(CompositeModelPackage.Literals.CHANNEL__PROVIDED_PORT)));
				}
			}
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
			System.out.println(exception.getCause());
			exception.printStackTrace();
		}
		return validationResultMessages;
	}

	public Collection<ValidationResultMessage> checkChannelRequiredPorts(SimpleChannel channel) {
		Collection<ValidationResultMessage> validationResultMessages = new ArrayList<ValidationResultMessage>();
		try {
			Component parentComponent = (Component) channel.eContainer();
			// Ports inside asynchronous components can be connected to multiple ports
			if (parentComponent instanceof AbstractAsynchronousCompositeComponent) {
				return validationResultMessages;
			}
			EObject root = EcoreUtil.getRootContainer(channel);
			Collection<InstancePortReference> instancePortReferences = ecoreUtil.getAllContentsOfType(root,
					InstancePortReference.class);
			// Checking required instance ports in different simple channels
			for (InstancePortReference instancePortReference : instancePortReferences.stream()
					.filter(it -> it != channel.getRequiredPort() && it.eContainer() instanceof Channel)
					.collect(Collectors.toList())) {
				if (StatechartModelDerivedFeatures.equals(instancePortReference, channel.getRequiredPort())) {
					validationResultMessages.add(new ValidationResultMessage(ValidationResult.ERROR,
							"A port of an instance can be included only in a single channel",
							new ReferenceInfo(CompositeModelPackage.Literals.SIMPLE_CHANNEL__REQUIRED_PORT)));
				}
			}
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
			System.out.println(exception.getCause());
			exception.printStackTrace();
		}
		return validationResultMessages;
	}

	public Collection<ValidationResultMessage> checkChannelRequiredPorts(BroadcastChannel channel) {
		Collection<ValidationResultMessage> validationResultMessages = new ArrayList<ValidationResultMessage>();
		try {
			Component parentComponent = (Component) channel.eContainer();
			// Ports inside asynchronous components can be connected to multiple ports
			if (parentComponent instanceof AbstractAsynchronousCompositeComponent) {
				return validationResultMessages;
			}
			EObject root = EcoreUtil.getRootContainer(channel);
			Collection<InstancePortReference> instancePortReferences = ecoreUtil.getAllContentsOfType(root,
					InstancePortReference.class);
			// Checking required instance ports in different broadcast channels
			for (InstancePortReference instancePortReference : instancePortReferences.stream()
					.filter(it -> it.eContainer() != channel && it.eContainer() instanceof Channel)
					.collect(Collectors.toList())) {
				for (InstancePortReference requiredPort : channel.getRequiredPorts()) {
					if (StatechartModelDerivedFeatures.equals(instancePortReference, requiredPort)) {
						int index = channel.getRequiredPorts().indexOf(requiredPort);
						validationResultMessages.add(new ValidationResultMessage(ValidationResult.ERROR,
								"A port of an instance can be included only in a single channel", new ReferenceInfo(
										CompositeModelPackage.Literals.BROADCAST_CHANNEL__REQUIRED_PORTS, index)));
					}
				}
			}
			// Checking required instance ports in the same broadcast channel
			for (InstancePortReference requiredPort : channel.getRequiredPorts()) {
				for (InstancePortReference requiredPort2 : channel.getRequiredPorts().stream()
						.filter(it -> it != requiredPort && it.eContainer() instanceof Channel)
						.collect(Collectors.toList())) {
					if (StatechartModelDerivedFeatures.equals(requiredPort2, requiredPort)) {
						int index = channel.getRequiredPorts().indexOf(requiredPort2);
						validationResultMessages.add(new ValidationResultMessage(ValidationResult.ERROR,
								"A port of an instance can be included only in a single channel", new ReferenceInfo(
										CompositeModelPackage.Literals.BROADCAST_CHANNEL__REQUIRED_PORTS, index)));
					}
				}
			}
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
			System.out.println(exception.getCause());
			exception.printStackTrace();
		}
		return validationResultMessages;
	}

	public Collection<ValidationResultMessage> checkChannelInput(Channel channel) {
		Collection<ValidationResultMessage> validationResultMessages = new ArrayList<ValidationResultMessage>();
		try {
			Port providedPort = channel.getProvidedPort().getPort();
			if (!StatechartModelDerivedFeatures.isProvided(providedPort)) {
				validationResultMessages.add(new ValidationResultMessage(ValidationResult.ERROR,
						"A port providing an interface is needed here",
						new ReferenceInfo(CompositeModelPackage.Literals.CHANNEL__PROVIDED_PORT)));
			}
			List<InstancePortReference> requiredPort = StatechartModelDerivedFeatures.getRequiredPorts(channel);
			if (StatechartModelDerivedFeatures.isInternal(providedPort)
					|| requiredPort.stream().anyMatch(it -> StatechartModelDerivedFeatures.isInternal(it))) {
				validationResultMessages.add(new ValidationResultMessage(ValidationResult.ERROR,
						"A port exposing internal events cannot be connected to other ports",
						new ReferenceInfo(CompositeModelPackage.Literals.CHANNEL__PROVIDED_PORT)));
			}
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
			System.out.println(exception.getCause());
			exception.printStackTrace();
		}
		return validationResultMessages;
	}

	public Collection<ValidationResultMessage> checkSimpleChannelOutput(SimpleChannel channel) {
		Collection<ValidationResultMessage> validationResultMessages = new ArrayList<ValidationResultMessage>();
		try {
			Port requiredPort = channel.getRequiredPort().getPort();
			if (!StatechartModelDerivedFeatures.isRequired(requiredPort)) {
				validationResultMessages.add(new ValidationResultMessage(ValidationResult.ERROR,
						"A port requiring an interface is needed here",
						new ReferenceInfo(CompositeModelPackage.Literals.SIMPLE_CHANNEL__REQUIRED_PORT)));
			}
			// Checking the interfaces
			Port providedPort = channel.getProvidedPort().getPort();
			Interface providedInterface = providedPort.getInterfaceRealization().getInterface();
			Interface requiredInterface = requiredPort.getInterfaceRealization().getInterface();
			if (providedInterface != requiredInterface) {
				validationResultMessages.add(new ValidationResultMessage(ValidationResult.ERROR,
						"Ports connected with a channel must have the same interface and "
								+ "this is not realized in this case: the provided interface: "
								+ providedInterface.getName() + ", the required interface: "
								+ requiredInterface.getName(),
						new ReferenceInfo(CompositeModelPackage.Literals.SIMPLE_CHANNEL__REQUIRED_PORT)));
			}
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
			System.out.println(exception.getCause());
			exception.printStackTrace();
		}
		return validationResultMessages;
	}

	public Collection<ValidationResultMessage> checkBroadcastChannelOutput(BroadcastChannel channel) {
		Collection<ValidationResultMessage> validationResultMessages = new ArrayList<ValidationResultMessage>();
		try {
			if (!StatechartModelDerivedFeatures.isBroadcast(channel.getProvidedPort().getPort())
					&& !(channel.eContainer() instanceof AsynchronousComponent)) {
				// Asynchronous components can have two-way broadcast channels
				validationResultMessages.add(new ValidationResultMessage(ValidationResult.ERROR,
						"A port providing a broadcast interface is needed here",
						new ReferenceInfo(CompositeModelPackage.Literals.CHANNEL__PROVIDED_PORT)));
			}
			for (InstancePortReference output : channel.getRequiredPorts()) {
				if (output.getPort().getInterfaceRealization().getRealizationMode() != RealizationMode.REQUIRED) {
					int index = channel.getRequiredPorts().indexOf(output);
					validationResultMessages.add(new ValidationResultMessage(ValidationResult.ERROR,
							"A port requiring an interface is needed here", new ReferenceInfo(
									CompositeModelPackage.Literals.BROADCAST_CHANNEL__REQUIRED_PORTS, index)));
				}
				Interface requiredInterface = output.getPort().getInterfaceRealization().getInterface();
				Interface providedInterface = channel.getProvidedPort().getPort().getInterfaceRealization()
						.getInterface();
				if (providedInterface != requiredInterface) {
					validationResultMessages.add(new ValidationResultMessage(ValidationResult.ERROR,
							"Ports connected with a broadcast channel must have the same interface and "
									+ "this is not realized in this case: the provided interface: "
									+ providedInterface.getName() + ", the required interface: "
									+ requiredInterface.getName(),
							new ReferenceInfo(CompositeModelPackage.Literals.BROADCAST_CHANNEL__REQUIRED_PORTS)));
				}
			}
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
			System.out.println(exception.getCause());
			exception.printStackTrace();
		}
		return validationResultMessages;
	}

	public Collection<ValidationResultMessage> checkCascadeLoopChannels(Channel channel) {
		Collection<ValidationResultMessage> validationResultMessages = new ArrayList<ValidationResultMessage>();
		try {
			ComponentInstance instance = channel.getProvidedPort().getInstance();
			Component type = StatechartModelDerivedFeatures.getDerivedType(instance);
			List<InstancePortReference> requiredPorts = StatechartModelDerivedFeatures.getRequiredPorts(channel);
			if (type instanceof AbstractSynchronousCompositeComponent
					&& requiredPorts.stream().anyMatch(it -> it.getInstance() == instance)) {
				validationResultMessages.add(new ValidationResultMessage(ValidationResult.WARNING,
						"Verification cannot be executed if different ports of a synchronous component are connected",
						new ReferenceInfo(CompositeModelPackage.Literals.CHANNEL__PROVIDED_PORT)));
			}
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
			System.out.println(exception.getCause());
			exception.printStackTrace();
		}
		return validationResultMessages;
	}

	// Asynchronous adapter

	public Collection<ValidationResultMessage> checkWrapperPortName(Port port) {
		Collection<ValidationResultMessage> validationResultMessages = new ArrayList<ValidationResultMessage>();
		try {
			if (port.eContainer() instanceof AsynchronousAdapter) {
				AsynchronousAdapter adapter = (AsynchronousAdapter) port.eContainer();
				String portName = port.getName();
				if (adapter.getWrappedComponent().getType().getPorts().stream()
						.anyMatch(it -> it.getName().equals(portName))) {
					validationResultMessages.add(new ValidationResultMessage(ValidationResult.ERROR,
							"This port enshadows a port in the wrapped synchronous component",
							new ReferenceInfo(ExpressionModelPackage.Literals.NAMED_ELEMENT__NAME)));
				}
			}
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
			System.out.println(exception.getCause());
			exception.printStackTrace();
		}
		return validationResultMessages;
	}

	public Collection<ValidationResultMessage> checkWrapperClock(Clock clock) {
		Collection<ValidationResultMessage> validationResultMessages = new ArrayList<ValidationResultMessage>();
		try {
			AsynchronousAdapter adapter = ecoreUtil.getContainerOfType(clock, AsynchronousAdapter.class);
			if (!StatechartModelDerivedFeatures.isStoredInMessageQueue(clock, adapter)) {
				validationResultMessages.add(new ValidationResultMessage(ValidationResult.ERROR,
						"Ticks of this clock are not forwarded to any messages queue",
						new ReferenceInfo(ExpressionModelPackage.Literals.NAMED_ELEMENT__NAME)));
			}
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
			System.out.println(exception.getCause());
			exception.printStackTrace();
		}
		return validationResultMessages;
	}

	public Collection<ValidationResultMessage> checkAsynchronousAdapterMultipleEventContainment(
			AsynchronousAdapter wrapper) {
		Collection<ValidationResultMessage> validationResultMessages = new ArrayList<ValidationResultMessage>();
		try {
			Map<Port, Collection<Event>> containedEvents = new HashMap<Port, Collection<Event>>();
			for (MessageQueue queue : wrapper.getMessageQueues()) {
				List<EventReference> eventReferences = StatechartModelDerivedFeatures.getSourceEventReferences(queue);
				for (EventReference eventReference : eventReferences) {
					int index = eventReferences.indexOf(eventReference);
					if (eventReference instanceof PortEventReference) {
						PortEventReference portEventReference = (PortEventReference) eventReference;
						Port containedPort = portEventReference.getPort();
						Event containedEvent = portEventReference.getEvent();
						if (containedEvents.containsKey(containedPort)) {
							Collection<Event> alreadyContainedEvents = containedEvents.get(containedPort);
							if (alreadyContainedEvents.contains(containedEvent)) {
								validationResultMessages.add(new ValidationResultMessage(ValidationResult.ERROR,
										"Event " + containedEvent.getName()
												+ " is already forwarded to a message queue",
										new ReferenceInfo(CompositeModelPackage.Literals.CHANNEL__PROVIDED_PORT, index,
												queue)));
							} else {
								alreadyContainedEvents.add(containedEvent);
							}
						} else {
							Collection<Event> events = new HashSet<Event>();
							events.add(containedEvent);
							containedEvents.put(containedPort, events);
						}
					}
					if (eventReference instanceof AnyPortEventReference) {
						AnyPortEventReference anyPortEventReference = (AnyPortEventReference) eventReference;
						Port containedPort = anyPortEventReference.getPort();
						Collection<Event> events = StatechartModelDerivedFeatures.getInputEvents(containedPort);
						if (containedEvents.containsKey(containedPort)) {
							Collection<Event> alreadyContainedEvents = containedEvents.get(containedPort);
							alreadyContainedEvents.addAll(events);
							Collection<String> alreadyContainedEventNames = alreadyContainedEvents.stream()
									.map(it -> it.getName()).collect(Collectors.toSet());
							validationResultMessages.add(new ValidationResultMessage(ValidationResult.ERROR,
									"Events " + alreadyContainedEventNames
											+ " are already forwarded to a message queue",
									new ReferenceInfo(CompositeModelPackage.Literals.MESSAGE_QUEUE__EVENT_PASSINGS,
											index, queue)));
						} else {
							containedEvents.put(containedPort, events);
						}
					}
				}
			}
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
			System.out.println(exception.getCause());
			exception.printStackTrace();
		}
		return validationResultMessages;
	}

	public Collection<ValidationResultMessage> checkInputPossibility(AsynchronousAdapter wrapper) {
		Collection<ValidationResultMessage> validationResultMessages = new ArrayList<ValidationResultMessage>();
		try {
			if (wrapper.getControlSpecifications().isEmpty() && wrapper.getClocks().isEmpty()) {
				validationResultMessages.add(new ValidationResultMessage(ValidationResult.WARNING,
						"This asynchronous adapter can never be executed",
						new ReferenceInfo(ExpressionModelPackage.Literals.NAMED_ELEMENT__NAME)));
			}
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
			System.out.println(exception.getCause());
			exception.printStackTrace();
		}
		return validationResultMessages;
	}

	public Collection<ValidationResultMessage> checkWrappedPort(AsynchronousAdapter wrapper) {
		Collection<ValidationResultMessage> validationResultMessages = new ArrayList<ValidationResultMessage>();
		try {
			for (Port port : StatechartModelDerivedFeatures.getAllPorts(wrapper)) {
				List<Event> inputEvents = StatechartModelDerivedFeatures.getInputEvents(port);
				for (Event event : inputEvents) {
					Entry<Port, Event> portEvent = new SimpleEntry<Port, Event>(port, event);
					int sourceCount = StatechartModelDerivedFeatures.countAssignedMessageQueues(portEvent, wrapper);
					int targetCount = StatechartModelDerivedFeatures.countTargetingMessageQueues(portEvent, wrapper);
					if (sourceCount != 1) {
						ValidationResult result = null;
						if (sourceCount < 1) {
							if (targetCount < 1) {
								result = ValidationResult.WARNING;
							} else {
								result = ValidationResult.INFO;
							}
						} else {
							result = ValidationResult.ERROR;
						}

						validationResultMessages.add(new ValidationResultMessage(result,
								"Event '" + event.getName() + "' of port '" + port.getName()
										+ "' is not forwarded to a single message queue but to " + sourceCount
										+ ", and " + targetCount + " events are forwarded to it",
								new ReferenceInfo(ExpressionModelPackage.Literals.NAMED_ELEMENT__NAME)));
					}
				}
			}
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
			System.out.println(exception.getCause());
			exception.printStackTrace();
		}
		return validationResultMessages;
	}

	public Collection<ValidationResultMessage> checkControlSpecification(ControlSpecification controlSpecification) {
		Collection<ValidationResultMessage> validationResultMessages = new ArrayList<ValidationResultMessage>();
		try {
			SimpleTrigger trigger = controlSpecification.getTrigger();
			if (trigger instanceof EventTrigger) {
				EventTrigger eventTrigger = (EventTrigger) trigger;
				EventReference eventReference = eventTrigger.getEventReference();
				// Checking out-events
				if (eventReference instanceof PortEventReference) {
					PortEventReference portEventReference = (PortEventReference) eventReference;
					Port containedPort = portEventReference.getPort();
					Event containedEvent = portEventReference.getEvent();
					List<Event> outputEvents = StatechartModelDerivedFeatures.getOutputEvents(containedPort);
					if (outputEvents.stream().filter(it -> StatechartModelDerivedFeatures
							.getContainingEventDeclaration(it).getDirection() != EventDirection.INOUT)
							.anyMatch(it -> it == containedEvent)) {
						validationResultMessages.add(new ValidationResultMessage(ValidationResult.ERROR,
								"Event " + containedEvent.getName()
										+ " is an out event and can not be used in a control specification",
								new ReferenceInfo(CompositeModelPackage.Literals.CONTROL_SPECIFICATION__TRIGGER)));
					}
				}
			}
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
			System.out.println(exception.getCause());
			exception.printStackTrace();
		}
		return validationResultMessages;
	}

	public Collection<ValidationResultMessage> checkMessageQueuePriorities(AsynchronousAdapter wrapper) {
		Collection<ValidationResultMessage> validationResultMessages = new ArrayList<ValidationResultMessage>();
		try {
			List<MessageQueue> messageQueues = wrapper.getMessageQueues();

			if (messageQueues.size() < 1) {
				validationResultMessages.add(new ValidationResultMessage(ValidationResult.ERROR,
						"An asynchronous adapter must have at least one message queue",
						new ReferenceInfo(CompositeModelPackage.Literals.ASYNCHRONOUS_ADAPTER__MESSAGE_QUEUES)));
				return validationResultMessages;
			}

			Set<Integer> priorityValues = new HashSet<Integer>();
			for (int i = 0; i < messageQueues.size(); ++i) {
				MessageQueue queue = messageQueues.get(i);
				int priorityValue = queue.getPriority().intValue();
				if (priorityValues.contains(priorityValue)) {
					validationResultMessages.add(new ValidationResultMessage(ValidationResult.WARNING,
							"Another queue with the same priority is already defined",
							new ReferenceInfo(CompositeModelPackage.Literals.PRIORITIZED_ELEMENT__PRIORITY, queue)));
				} else {
					priorityValues.add(priorityValue);
				}
			}

		} catch (Exception exception) {
			System.out.println(exception.getMessage());
			System.out.println(exception.getCause());
			exception.printStackTrace();
		}
		return validationResultMessages;
	}

	public Collection<ValidationResultMessage> checkMessageQueue(MessageQueue queue) {
		Collection<ValidationResultMessage> validationResultMessages = new ArrayList<ValidationResultMessage>();
		try {

			List<EventReference> eventReferences = StatechartModelDerivedFeatures.getSourceEventReferences(queue);
			for (EventReference eventReference : eventReferences) {
				int index = eventReferences.indexOf(eventReference);
				// Checking out-events
				if (eventReference instanceof PortEventReference) {
					PortEventReference portEventReference = (PortEventReference) eventReference;
					Port containedPort = portEventReference.getPort();
					Event containedEvent = portEventReference.getEvent();
					List<Event> outputEvents = new ArrayList<Event>(
							StatechartModelDerivedFeatures.getOutputEvents(containedPort));
					outputEvents.removeAll(StatechartModelDerivedFeatures.getInputEvents(containedPort));
					if (outputEvents.contains(containedEvent)) {
						validationResultMessages.add(new ValidationResultMessage(ValidationResult.ERROR,
								"Event '" + containedEvent.getName()
										+ "' is an out event and can not be forwarded to a message queue",
								new ReferenceInfo(CompositeModelPackage.Literals.MESSAGE_QUEUE__EVENT_PASSINGS,
										index)));
					}
				}
			}

		} catch (Exception exception) {
			System.out.println(exception.getMessage());
			System.out.println(exception.getCause());
			exception.printStackTrace();
		}
		return validationResultMessages;
	}

	public Collection<ValidationResultMessage> checkAnyPortControls(AsynchronousAdapter adapter) {
		Collection<ValidationResultMessage> validationResultMessages = new ArrayList<ValidationResultMessage>();
		try {
			Map<Port, Collection<Event>> usedEvents = new HashMap<Port, Collection<Event>>();
			for (ControlSpecification controlSpecification : adapter.getControlSpecifications()) {
				Trigger trigger = controlSpecification.getTrigger();
				int index = adapter.getControlSpecifications().indexOf(controlSpecification);
				if (trigger instanceof AnyTrigger) {
					if (adapter.getControlSpecifications().size() > 1) {
						validationResultMessages.add(new ValidationResultMessage(ValidationResult.WARNING,
								"This control specification with any trigger is not disjunct from "
										+ "other control specifications; note that resets have a higher precedence",
								new ReferenceInfo(
										CompositeModelPackage.Literals.ASYNCHRONOUS_ADAPTER__CONTROL_SPECIFICATIONS,
										index, adapter)));
						return validationResultMessages;
					}
				}
				if (trigger instanceof EventTrigger) {
					EventTrigger eventTrigger = (EventTrigger) trigger;
					EventReference eventReference = eventTrigger.getEventReference();
					if (eventReference instanceof AnyPortEventReference) {
						AnyPortEventReference anyPortEventReference = (AnyPortEventReference) eventReference;
						Port port = anyPortEventReference.getPort();
						Collection<Event> portEvents = StatechartModelDerivedFeatures.getInputEvents(port);
						if (usedEvents.containsKey(port)) {
							validationResultMessages.add(new ValidationResultMessage(ValidationResult.WARNING,
									"This control specification with any port trigger is not disjunct from other "
											+ "control specifications with reference to the same port; note that resets have a higher precedence",
									new ReferenceInfo(
											CompositeModelPackage.Literals.ASYNCHRONOUS_ADAPTER__CONTROL_SPECIFICATIONS,
											index, adapter)));
							Collection<Event> containedEvents = usedEvents.get(port);
							containedEvents.addAll(portEvents);
						} else {
							usedEvents.put(port, portEvents);
						}
					} else if (eventReference instanceof PortEventReference) {
						PortEventReference portEventReference = (PortEventReference) eventReference;
						Port port = portEventReference.getPort();
						Event event = portEventReference.getEvent();
						if (usedEvents.containsKey(port)) {
							Collection<Event> containedEvents = usedEvents.get(port);
							if (containedEvents.contains(event)) {
								validationResultMessages.add(new ValidationResultMessage(ValidationResult.WARNING,
										"This control specification with port event trigger has "
												+ "the same effect as a previous control specification",
										new ReferenceInfo(
												CompositeModelPackage.Literals.ASYNCHRONOUS_ADAPTER__CONTROL_SPECIFICATIONS,
												index, adapter)));
							} else {
								containedEvents.add(event);
							}
						} else {
							Collection<Event> events = new HashSet<Event>();
							events.add(event);
							usedEvents.put(port, events);
						}
					}
				}
			}
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
			System.out.println(exception.getCause());
			exception.printStackTrace();
		}
		return validationResultMessages;
	}

	public Collection<ValidationResultMessage> checkMessageQueueAnyEventReferences(
			AnyPortEventReference anyPortEventReference) {
		Collection<ValidationResultMessage> validationResultMessages = new ArrayList<ValidationResultMessage>();
		try {
			if (anyPortEventReference.eContainer() instanceof MessageQueue
					&& StatechartModelDerivedFeatures.isBroadcast(anyPortEventReference.getPort())) {
				validationResultMessages.add(new ValidationResultMessage(ValidationResult.ERROR,
						"There are no events coming in through this port",
						new ReferenceInfo(StatechartModelPackage.Literals.ANY_PORT_EVENT_REFERENCE__PORT)));
			}
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
			System.out.println(exception.getCause());
			exception.printStackTrace();
		}
		return validationResultMessages;
	}

	public Collection<ValidationResultMessage> checkEventPassings(EventPassing eventPassing) {
		Collection<ValidationResultMessage> validationResultMessages = new ArrayList<ValidationResultMessage>();
		try {
			EventReference target = eventPassing.getTarget();
			if (target != null) {
				EventReference source = eventPassing.getSource();
				if (source instanceof AnyPortEventReference) {
					AnyPortEventReference sourceReference=(AnyPortEventReference)source;
					Port sourcePort = sourceReference.getPort();
					if (target instanceof AnyPortEventReference) {
						AnyPortEventReference targetReference =(AnyPortEventReference)target;
						Port targetPort = targetReference.getPort();
						if (!StatechartModelDerivedFeatures.isEventPassingCompatible(sourcePort, targetPort)) {
							validationResultMessages.add(new ValidationResultMessage(ValidationResult.ERROR,
									"In the case of any port event references, the interfaces of the ports must match",
									new ReferenceInfo(CompositeModelPackage.Literals.EVENT_PASSING__TARGET)));
						}
					} else {
						validationResultMessages.add(new ValidationResultMessage(ValidationResult.ERROR,
								"In the case of any port event references, the target must also be an any port event reference",
								new ReferenceInfo(CompositeModelPackage.Literals.EVENT_PASSING__TARGET)));
					}
				} else if (source instanceof PortEventReference) {
					PortEventReference sourceReference=(PortEventReference)source;
					Event sourceEvent = sourceReference.getEvent();
					if (target instanceof PortEventReference) {
						PortEventReference targetReference= (PortEventReference) target;
						Event targetEvent = targetReference.getEvent();
						if (StatechartModelDerivedFeatures.isEventPassingCompatible(sourceEvent, targetEvent)) {
							validationResultMessages.add(new ValidationResultMessage(ValidationResult.ERROR,
									"In the case of port event references, the number and types of parameter declarations must be the same",
									new ReferenceInfo(CompositeModelPackage.Literals.EVENT_PASSING__TARGET)));
						}
					} else {
						validationResultMessages.add(new ValidationResultMessage(ValidationResult.ERROR,
								"In the case of port event references, the target must also be a port event reference",
								new ReferenceInfo(CompositeModelPackage.Literals.EVENT_PASSING__TARGET)));
					}
				}
			}
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
			System.out.println(exception.getCause());
			exception.printStackTrace();
		}
		return validationResultMessages;
	}

	public Collection<ValidationResultMessage> checkExecutionLists(CascadeCompositeComponent cascade) {
		List<SynchronousComponentInstance> components = cascade.getComponents();
		List<ComponentInstanceReferenceExpression> executionList = cascade.getExecutionList();

		return checkExecutionList(components, executionList);
	}

	public Collection<ValidationResultMessage> checkExecutionLists(
			ScheduledAsynchronousCompositeComponent scheduledComponent) {
		List<AsynchronousComponentInstance> components = scheduledComponent.getComponents();
		List<ComponentInstanceReferenceExpression> executionList = scheduledComponent.getExecutionList();

		return checkExecutionList(components, executionList);
	}

	private Collection<ValidationResultMessage> checkExecutionList(List<? extends ComponentInstance> components,
			List<? extends ComponentInstanceReferenceExpression> executionList) {
		Collection<ValidationResultMessage> validationResultMessages = new ArrayList<ValidationResultMessage>();
		try {

			if (executionList.isEmpty()) {
				// Nothing to validate
				return validationResultMessages;
			}
			Collection<ComponentInstance> containedInstances = new HashSet<ComponentInstance>(components);
			for (ComponentInstanceReferenceExpression instanceReference : executionList) {
				ComponentInstance instance = instanceReference.getComponentInstance();
				if (!components.contains(instance)) {
					validationResultMessages.add(new ValidationResultMessage(ValidationResult.ERROR,
							instance.getName() + " is not a contained component", new ReferenceInfo(
									CompositeModelPackage.Literals.SCHEDULABLE_COMPOSITE_COMPONENT__EXECUTION_LIST)));
				}
				if (!StatechartModelDerivedFeatures.isAtomic(instanceReference)) {
					validationResultMessages.add(new ValidationResultMessage(ValidationResult.ERROR,
							instance.getName() + " is not an atomic component", new ReferenceInfo(
									CompositeModelPackage.Literals.SCHEDULABLE_COMPOSITE_COMPONENT__EXECUTION_LIST)));
				}

				containedInstances.remove(instance);
			}
			if (!containedInstances.isEmpty()) {
				validationResultMessages.add(new ValidationResultMessage(ValidationResult.ERROR,
						"The following instances are never executed: "
								+ containedInstances.stream().map(it -> it.getName()).collect(Collectors.toList()),
						new ReferenceInfo(
								CompositeModelPackage.Literals.SCHEDULABLE_COMPOSITE_COMPONENT__EXECUTION_LIST)));
			}

		} catch (Exception exception) {
			System.out.println(exception.getMessage());
			System.out.println(exception.getCause());
			exception.printStackTrace();
		}
		return validationResultMessages;
	}

	public Collection<ValidationResultMessage> checkComponents(
			ScheduledAsynchronousCompositeComponent scheduledComponent) {
		Collection<ValidationResultMessage> validationResultMessages = new ArrayList<ValidationResultMessage>();
		try {

			List<AsynchronousComponentInstance> components = scheduledComponent.getComponents();
			for (AsynchronousComponentInstance component : components) {
				AsynchronousComponent type = component.getType();
				if (type instanceof AsynchronousCompositeComponent) {
					validationResultMessages.add(new ValidationResultMessage(ValidationResult.ERROR,
							"Scheduled asynchronous composite components cannot contain asynchronous components",
							new ReferenceInfo(
									CompositeModelPackage.Literals.ABSTRACT_ASYNCHRONOUS_COMPOSITE_COMPONENT__COMPONENTS,
									ecoreUtil.getIndex(component))));
				}
			}

		} catch (Exception exception) {
			System.out.println(exception.getMessage());
			System.out.println(exception.getCause());
			exception.printStackTrace();
		}
		return validationResultMessages;
	}

	public Collection<ValidationResultMessage> checkComponentInstanceReferences(
			ComponentInstanceReferenceExpression reference) {
		Collection<ValidationResultMessage> validationResultMessages = new ArrayList<ValidationResultMessage>();
		try {

			ComponentInstance instance = reference.getComponentInstance();
			ComponentInstanceReferenceExpression child = reference.getChild();
			if (child != null) {
				ComponentInstance childInstance = child.getComponentInstance();
				if (!StatechartModelDerivedFeatures.contains(instance, childInstance)) {
					validationResultMessages.add(new ValidationResultMessage(ValidationResult.ERROR,
							instance.getName() + " does not contain component instance " + childInstance.getName(),
							new ReferenceInfo(
									CompositeModelPackage.Literals.COMPONENT_INSTANCE_REFERENCE_EXPRESSION__COMPONENT_INSTANCE)));
				}
			}

		} catch (Exception exception) {
			System.out.println(exception.getMessage());
			System.out.println(exception.getCause());
			exception.printStackTrace();
		}
		return validationResultMessages;
	}

}