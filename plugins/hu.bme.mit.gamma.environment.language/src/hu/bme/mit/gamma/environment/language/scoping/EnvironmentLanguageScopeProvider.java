package hu.bme.mit.gamma.environment.language.scoping;

import static com.google.common.base.Preconditions.checkState;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.scoping.IScope;
import org.eclipse.xtext.scoping.Scopes;

import hu.bme.mit.gamma.environment.analysis.AnalysisComponent;
import hu.bme.mit.gamma.environment.analysis.AnalysisMethod;
import hu.bme.mit.gamma.environment.analysis.AnalysisPackage;
import hu.bme.mit.gamma.environment.analysis.ComponentPortEventReference;
import hu.bme.mit.gamma.environment.analysis.MeanParameter;
import hu.bme.mit.gamma.environment.analysis.ObserveParameter;
import hu.bme.mit.gamma.environment.analysis.ParameterDistribution;
import hu.bme.mit.gamma.environment.analysis.PrioryDistribution;
import hu.bme.mit.gamma.environment.analysis.RecursiveComponentReference;
import hu.bme.mit.gamma.environment.model.AbstractEnvironmentCompositeComponent;
import hu.bme.mit.gamma.environment.model.AbstractEnvironmentCompositeComponentInstance;
import hu.bme.mit.gamma.environment.model.ElementaryEnvironmentComponentInstance;
import hu.bme.mit.gamma.environment.model.EnvironmentModelPackage;
import hu.bme.mit.gamma.environment.model.EnvironmentSample;
import hu.bme.mit.gamma.environment.model.EnvironmentAsynchronousCompositeComponent;
import hu.bme.mit.gamma.environment.model.EnvironmentAsynchronousCompositeComponentInstance;
import hu.bme.mit.gamma.environment.model.EnvironmentCascadeCompositeComponent;
import hu.bme.mit.gamma.environment.model.EnvironmentCascadeCompositeComponentInstance;
import hu.bme.mit.gamma.environment.model.EnvironmentComponentInstance;
import hu.bme.mit.gamma.environment.model.EnvironmentSynchronousCompositeComponent;
import hu.bme.mit.gamma.environment.model.EnvironmentSynchronousCompositeComponentInstance;
import hu.bme.mit.gamma.environment.model.ParameterFilter;
import hu.bme.mit.gamma.environment.model.PortFilter;
import hu.bme.mit.gamma.environment.model.StochasticRule;
import hu.bme.mit.gamma.expression.model.Declaration;
import hu.bme.mit.gamma.expression.model.EnumerationLiteralDefinition;
import hu.bme.mit.gamma.expression.model.EnumerationLiteralExpression;
import hu.bme.mit.gamma.expression.model.ExpressionModelPackage;
import hu.bme.mit.gamma.statechart.composite.AbstractSynchronousCompositeComponent;
import hu.bme.mit.gamma.statechart.composite.AsynchronousAdapter;
import hu.bme.mit.gamma.statechart.composite.AsynchronousComponent;
import hu.bme.mit.gamma.statechart.composite.AsynchronousComponentInstance;
import hu.bme.mit.gamma.statechart.composite.AsynchronousCompositeComponent;
import hu.bme.mit.gamma.statechart.composite.ComponentInstance;
import hu.bme.mit.gamma.statechart.composite.CompositeComponent;
import hu.bme.mit.gamma.statechart.composite.CompositeModelPackage;
import hu.bme.mit.gamma.statechart.composite.ControlSpecification;
import hu.bme.mit.gamma.statechart.composite.InstancePortReference;
import hu.bme.mit.gamma.statechart.composite.MessageQueue;
import hu.bme.mit.gamma.statechart.composite.SynchronousComponent;
import hu.bme.mit.gamma.statechart.composite.SynchronousComponentInstance;
import hu.bme.mit.gamma.statechart.contract.AdaptiveContractAnnotation;
import hu.bme.mit.gamma.statechart.contract.ContractModelPackage;
import hu.bme.mit.gamma.statechart.contract.StateContractAnnotation;
import hu.bme.mit.gamma.statechart.derivedfeatures.StatechartModelDerivedFeatures;
import hu.bme.mit.gamma.statechart.interface_.Component;
import hu.bme.mit.gamma.statechart.interface_.Event;
import hu.bme.mit.gamma.statechart.interface_.EventDeclaration;
import hu.bme.mit.gamma.statechart.interface_.EventParameterReferenceExpression;
import hu.bme.mit.gamma.statechart.interface_.Interface;
import hu.bme.mit.gamma.statechart.interface_.InterfaceModelPackage;
import hu.bme.mit.gamma.statechart.interface_.InterfaceRealization;
import hu.bme.mit.gamma.statechart.interface_.Package;
import hu.bme.mit.gamma.statechart.interface_.Port;
import hu.bme.mit.gamma.statechart.language.scoping.StatechartLanguageScopeProvider;
import hu.bme.mit.gamma.statechart.phase.InstanceVariableReference;
import hu.bme.mit.gamma.statechart.phase.MissionPhaseStateAnnotation;
import hu.bme.mit.gamma.statechart.phase.PhaseModelPackage;
import hu.bme.mit.gamma.statechart.statechart.AnyPortEventReference;
import hu.bme.mit.gamma.statechart.statechart.PortEventReference;
import hu.bme.mit.gamma.statechart.statechart.RaiseEventAction;
import hu.bme.mit.gamma.statechart.statechart.StateNode;
import hu.bme.mit.gamma.statechart.statechart.StatechartDefinition;
import hu.bme.mit.gamma.statechart.statechart.StatechartModelPackage;
import hu.bme.mit.gamma.statechart.statechart.Transition;


public class EnvironmentLanguageScopeProvider extends StatechartLanguageScopeProvider{

	
	
	@Override
	public IScope getScope(final EObject context, final EReference reference) {
		
		

		// Statechart

		try {
			// Adaptive
			if (context instanceof AdaptiveContractAnnotation &&
					reference == ContractModelPackage.Literals.ADAPTIVE_CONTRACT_ANNOTATION__MONITORED_COMPONENT) {
				Package parentPackage = StatechartModelDerivedFeatures.getContainingPackage(context);
				StatechartDefinition parentStatechart = StatechartModelDerivedFeatures.getContainingStatechart(context);
				Set<Component> allComponents = StatechartModelDerivedFeatures.getAllComponents(parentPackage);
				allComponents.remove(parentStatechart);
				return Scopes.scopeFor(allComponents);
			}
			if (context instanceof StateContractAnnotation &&
					reference == ContractModelPackage.Literals.STATE_CONTRACT_ANNOTATION__CONTRACT_STATECHART) {
				Package parentPackage = StatechartModelDerivedFeatures.getContainingPackage(context);
				StatechartDefinition parentStatechart = StatechartModelDerivedFeatures.getContainingStatechart(context);
				Set<StatechartDefinition> allComponents = StatechartModelDerivedFeatures.getAllStatechartComponents(parentPackage);
				allComponents.remove(parentStatechart);
				return Scopes.scopeFor(allComponents);
			}
			// Phase
			if (context instanceof InstanceVariableReference &&
					reference == PhaseModelPackage.Literals.INSTANCE_VARIABLE_REFERENCE__VARIABLE) {
				MissionPhaseStateAnnotation container = ecoreUtil.getContainerOfType(context, MissionPhaseStateAnnotation.class);
				ComponentInstance instance = container.getComponent();
				Component type = StatechartModelDerivedFeatures.getDerivedType(instance);
				if (type instanceof StatechartDefinition) {
					StatechartDefinition statechart = (StatechartDefinition) type;
					return Scopes.scopeFor(statechart.getVariableDeclarations());
				}
			}
			// Transitions
			if (context instanceof Transition && (reference == StatechartModelPackage.Literals.TRANSITION__SOURCE_STATE
					|| reference == StatechartModelPackage.Literals.TRANSITION__TARGET_STATE)) {
				final Collection<StateNode> candidates = stateNodesForTransition((Transition) context);
				return Scopes.scopeFor(candidates);
			}
			if (context instanceof PortEventReference && reference == StatechartModelPackage.Literals.PORT_EVENT_REFERENCE__EVENT) {
				Port port = ((PortEventReference) context).getPort();
				Interface _interface = port.getInterfaceRealization().getInterface();
				// Not only in events are returned as less-aware users tend to write out events on triggers
				return Scopes.scopeFor(StatechartModelDerivedFeatures.getAllEvents(_interface));
			}
			
			if (context instanceof RecursiveComponentReference) {

				if (reference == AnalysisPackage.Literals.RECURSIVE_COMPONENT_REFERENCE__COMPONENT) {
					AbstractEnvironmentCompositeComponentInstance envComponent;
					if (context.eContainer().eContainer().eContainer() instanceof AnalysisComponent) {
						AnalysisComponent component = (AnalysisComponent) context.eContainer().eContainer().eContainer();
						envComponent = (AbstractEnvironmentCompositeComponentInstance) component.getAnalyzedComponent();					
						Collection<AbstractEnvironmentCompositeComponentInstance> components = new HashSet<>();
						components.add(envComponent);
						return Scopes.scopeFor(components);
					}else if(context.eContainer() instanceof RecursiveComponentReference) {
						EnvironmentComponentInstance component = (EnvironmentComponentInstance) 
								((RecursiveComponentReference)context.eContainer()).getComponent();
						Collection<ComponentInstance> components = new HashSet<>();
						if (component instanceof SynchronousComponentInstance) {
							components.addAll((
										(AbstractSynchronousCompositeComponent)((SynchronousComponentInstance)component).getType()
									).getComponents());
						}
						if (component instanceof AsynchronousComponentInstance) {
							components.addAll((
										(AsynchronousCompositeComponent)((AsynchronousComponentInstance)component).getType()
									).getComponents());
						}
						if (component instanceof EnvironmentSynchronousCompositeComponentInstance) {
							components.addAll((
										(EnvironmentSynchronousCompositeComponent)((EnvironmentSynchronousCompositeComponentInstance)component).getType()
									).getEnvironmentComponents());
						}
						if (component instanceof EnvironmentAsynchronousCompositeComponentInstance) {
							components.addAll((
										(EnvironmentAsynchronousCompositeComponent)((EnvironmentAsynchronousCompositeComponentInstance)component).getType()
									).getEnvironmentComponents());
						}
						if (component instanceof EnvironmentCascadeCompositeComponentInstance) {
							components.addAll((
										(EnvironmentCascadeCompositeComponent)((EnvironmentCascadeCompositeComponentInstance)component).getType()
									).getEnvironmentComponents());
						}
						return Scopes.scopeFor(components);
					}

				}
			}
			
			//Environment component specific code
			if (context instanceof ObserveParameter && reference == AnalysisPackage.Literals.OBSERVE_PARAMETER__PARAMETER) {
				ObserveParameter obs = (ObserveParameter) context;
				return Scopes.scopeFor(obs.getEvent().getEvent().getParameterDeclarations());
			}
			if (context instanceof MeanParameter && reference == AnalysisPackage.Literals.MEAN_PARAMETER__PARAMETER) {
				MeanParameter mp = (MeanParameter) context;
				return Scopes.scopeFor(mp.getEvent().getEvent().getParameterDeclarations());
			}
			if (context instanceof ParameterDistribution && reference == AnalysisPackage.Literals.PARAMETER_DISTRIBUTION__PARAMETER) {
				ParameterDistribution mp = (ParameterDistribution) context;
				return Scopes.scopeFor(mp.getEvent().getEvent().getParameterDeclarations());
			}
			if (context instanceof ParameterFilter && reference == EnvironmentModelPackage.Literals.PARAMETER_FILTER__PARAMETER) {
				if (context.eContainer().eContainer() instanceof EnvironmentAsynchronousCompositeComponent) {
					EnvironmentAsynchronousCompositeComponent comp = (EnvironmentAsynchronousCompositeComponent) context.eContainer().eContainer();
					return Scopes.scopeFor(comp.getParameterDeclarations());
				}
				if (context.eContainer().eContainer() instanceof EnvironmentCascadeCompositeComponent) {
					EnvironmentCascadeCompositeComponent comp = (EnvironmentCascadeCompositeComponent) context.eContainer().eContainer();
					return Scopes.scopeFor(comp.getParameterDeclarations());
				}
				if (context.eContainer().eContainer() instanceof EnvironmentSynchronousCompositeComponent) {
					EnvironmentSynchronousCompositeComponent comp = (EnvironmentSynchronousCompositeComponent) context.eContainer().eContainer();
					return Scopes.scopeFor(comp.getParameterDeclarations());
				}
				if (context.eContainer().eContainer() instanceof EnvironmentSample) {
					ParameterFilter filter = (ParameterFilter) context;
					EnvironmentSample comp = (EnvironmentSample) context.eContainer().eContainer();
					return Scopes.scopeFor(filter.getEvent().getEvent().getParameterDeclarations());
				}
			}
			if (context instanceof PrioryDistribution && reference == AnalysisPackage.Literals.PRIORY_DISTRIBUTION__PARAMETER) {
				PrioryDistribution dist =(PrioryDistribution) context;
				AnalysisComponent comp = (AnalysisComponent) dist.eContainer();
				if (comp instanceof EnvironmentCascadeCompositeComponentInstance) {
					return Scopes.scopeFor(((EnvironmentCascadeCompositeComponentInstance)comp)
							.getType().getParameterDeclarations());
				}
				if (comp instanceof EnvironmentAsynchronousCompositeComponentInstance) {
					return Scopes.scopeFor(((EnvironmentAsynchronousCompositeComponentInstance)comp)
							.getType().getParameterDeclarations());
				}
				if (comp instanceof EnvironmentSynchronousCompositeComponentInstance) {
					return Scopes.scopeFor(((EnvironmentSynchronousCompositeComponentInstance)comp)
							.getType().getParameterDeclarations());
				};
			}
			if (context instanceof ComponentPortEventReference ) {
				ComponentPortEventReference ref=(ComponentPortEventReference) context;
				AnalysisComponent component;
				if (context.eContainer().eContainer() instanceof AnalysisComponent) {
					component = (AnalysisComponent) context.eContainer().eContainer();
				}
				if (context.eContainer().eContainer() instanceof AnalysisMethod) {
					component = (AnalysisComponent) context.eContainer().eContainer().eContainer();
				}
				AbstractEnvironmentCompositeComponentInstance envComponent = getRecursiveInstance(ref.getComponent());
				if(reference==AnalysisPackage.Literals.COMPONENT_PORT_EVENT_REFERENCE__PORT) {
					Collection<Port> ports = new HashSet<>();
					if (envComponent instanceof EnvironmentCascadeCompositeComponentInstance) {
						return Scopes.scopeFor(((EnvironmentCascadeCompositeComponentInstance)envComponent)
								.getType().getPorts());
					}
					if (envComponent instanceof EnvironmentAsynchronousCompositeComponentInstance) {
						return Scopes.scopeFor(((EnvironmentAsynchronousCompositeComponentInstance)envComponent)
								.getType().getPorts());
					}
					if (envComponent instanceof EnvironmentSynchronousCompositeComponentInstance) {
						return Scopes.scopeFor(((EnvironmentSynchronousCompositeComponentInstance)envComponent)
								.getType().getPorts());
					}
				}
				if(reference==AnalysisPackage.Literals.COMPONENT_PORT_EVENT_REFERENCE__EVENT) {
					return Scopes.scopeFor(eventsFromDeclarations(ref.getPort().getInterfaceRealization().getInterface().getEvents()));
				}
			}
			
			if (context instanceof PortFilter && reference == EnvironmentModelPackage.Literals.PORT_FILTER__PORT) {
				ElementaryEnvironmentComponentInstance comp=(ElementaryEnvironmentComponentInstance) context.eContainer().eContainer();
				return Scopes.scopeFor(comp.getOutports());
			}
			
			if (reference == StatechartModelPackage.Literals.PORT_EVENT_REFERENCE__EVENT) {
				// If the branch above does not work
				StatechartDefinition statechart = StatechartModelDerivedFeatures.getContainingStatechart(context);
				Collection<Event> events = new HashSet<Event>();
				statechart.getPorts()
					.forEach(it -> events.addAll(StatechartModelDerivedFeatures.getAllEvents(it.getInterfaceRealization().getInterface())));
				// Not only in events are returned as less-aware users tend to write out events on triggers
				return Scopes.scopeFor(events);
			}
			if (context instanceof RaiseEventAction
					&& reference == StatechartModelPackage.Literals.RAISE_EVENT_ACTION__EVENT) {
				RaiseEventAction raiseEventAction = (RaiseEventAction) context;
				Port port = raiseEventAction.getPort();
				Interface _interface = port.getInterfaceRealization().getInterface();
				// Not only in events are returned as less-aware users tend to write in events on actions
				return Scopes.scopeFor(StatechartModelDerivedFeatures.getAllEvents(_interface));
			}
			if (context instanceof EnumerationLiteralExpression && 
					reference == ExpressionModelPackage.Literals.ENUMERATION_LITERAL_EXPRESSION__REFERENCE) {
				Package root = (Package) EcoreUtil2.getRootContainer(context, true);
				Collection<EnumerationLiteralDefinition> enumLiterals = EcoreUtil2.getAllContentsOfType(root, EnumerationLiteralDefinition.class);
				for (Package imported : root.getImports()) {
					enumLiterals.addAll(EcoreUtil2.getAllContentsOfType(imported, EnumerationLiteralDefinition.class));
				}
				return Scopes.scopeFor(enumLiterals);
			}
			/* Without such scoping rules, the following exception is thrown:
			 * Caused By: org.eclipse.xtext.conversion.ValueConverterException: ID 'Test.testIn.testInValue'
			 * contains invalid characters: '.' (0x2e) */
			// Valueof
			if (context instanceof EventParameterReferenceExpression
					&& reference == InterfaceModelPackage.Literals.EVENT_PARAMETER_REFERENCE_EXPRESSION__PORT) {
				Component component = StatechartModelDerivedFeatures.getContainingComponent(context);				
				return Scopes.scopeFor(component.getPorts());
			}
			if (context instanceof EventParameterReferenceExpression
					&& reference == InterfaceModelPackage.Literals.EVENT_PARAMETER_REFERENCE_EXPRESSION__EVENT) {
				EventParameterReferenceExpression expression = (EventParameterReferenceExpression) context;
				checkState(expression.getPort() != null);
				Port port = expression.getPort();
				return Scopes.scopeFor(StatechartModelDerivedFeatures.getInputEvents(port));
			}
			if (context instanceof EventParameterReferenceExpression
					&& reference == InterfaceModelPackage.Literals.EVENT_PARAMETER_REFERENCE_EXPRESSION__PARAMETER) {
				EventParameterReferenceExpression expression = (EventParameterReferenceExpression) context;
				checkState(expression.getPort() != null);
				Event event = expression.getEvent();
				return Scopes.scopeFor(event.getParameterDeclarations());
			}
			/*if (context instanceof ParameterFilter
					&& reference == EnvironmentModelPackage.Literals.PARAMETER_FILTER__PARAMETER) {
				ParameterFilter expression = (ParameterFilter) context;
				checkState(expression.getPort() != null);
				Event event = expression.getEvent();
				return Scopes.scopeFor(event.getParameterDeclarations());
			}*/


			// Composite system

			// Ports
			if (context instanceof InterfaceRealization && reference == InterfaceModelPackage.Literals.INTERFACE_REALIZATION__INTERFACE) {
				Package gammaPackage;
				//Environment Component specific
				if (context.eContainer().eContainer().eContainer() instanceof AbstractEnvironmentCompositeComponent) {
					gammaPackage = (Package) context.eContainer().eContainer().eContainer().eContainer();
				}else {
					gammaPackage = (Package) context.eContainer().eContainer().eContainer();
				}
				if (!gammaPackage.getImports().isEmpty()) {
					Set<Interface> interfaces = new HashSet<Interface>();
					gammaPackage.getImports().stream().map(it -> it.getInterfaces()).forEach(it -> interfaces.addAll(it));
					return Scopes.scopeFor(interfaces);
				}
			}
			if (context instanceof InstancePortReference && reference == CompositeModelPackage.Literals.INSTANCE_PORT_REFERENCE__PORT) {
				InstancePortReference portInstance = (InstancePortReference) context;
				ComponentInstance instance = portInstance.getInstance();
				Component type=null;
				List<Port> ports = new ArrayList<Port>();
				if(instance instanceof SynchronousComponentInstance) {
					type = ((SynchronousComponentInstance) instance).getType();
					ports = new ArrayList<Port>(type.getPorts());
				}
				if(instance instanceof AsynchronousComponentInstance) {
					type = ((AsynchronousComponentInstance) instance).getType();
					ports = new ArrayList<Port>(type.getPorts());
				}
				//Environment component specific code
				if(instance instanceof EnvironmentSynchronousCompositeComponentInstance) {
					type = ((EnvironmentSynchronousCompositeComponentInstance) instance).getType();
					ports = new ArrayList<Port>(type.getPorts());
				}
				if(instance instanceof EnvironmentAsynchronousCompositeComponentInstance) {
					type = ((EnvironmentSynchronousCompositeComponentInstance) instance).getType();
					ports = new ArrayList<Port>(type.getPorts());
				}
				if(instance instanceof EnvironmentCascadeCompositeComponentInstance) {
					type = ((EnvironmentSynchronousCompositeComponentInstance) instance).getType();
					ports = new ArrayList<Port>(type.getPorts());
				}
				if(instance instanceof ElementaryEnvironmentComponentInstance) {
					List<Port> inport = ((ElementaryEnvironmentComponentInstance) instance).getInports();
					List<Port> outport = ((ElementaryEnvironmentComponentInstance) instance).getOutports();
					ports=new ArrayList<Port>();
					ports.addAll(inport);
					ports.addAll(outport);
				}
				// In case of wrappers, we added the ports of the wrapped component as well
				if (type instanceof AsynchronousAdapter) {
					AsynchronousAdapter wrapper = (AsynchronousAdapter) type;
					ports.addAll(wrapper.getWrappedComponent().getType().getPorts());
				}				
				return Scopes.scopeFor(ports);
			}
			if (context instanceof CompositeComponent && reference == CompositeModelPackage.Literals.INSTANCE_PORT_REFERENCE__PORT) {
				// If the branch above does not handle it
				CompositeComponent component = (CompositeComponent) context;
				List<? extends ComponentInstance> components = StatechartModelDerivedFeatures.getDerivedComponents(component);
				Collection<Port> ports = new HashSet<Port>();
				components.stream().map(it -> StatechartModelDerivedFeatures.getDerivedType(it))
								.map(it ->StatechartModelDerivedFeatures.getAllPorts(it))
								.forEach(it -> ports.addAll(it));
				return Scopes.scopeFor(ports); 
			}
			// Types
			if (context instanceof SynchronousComponentInstance && reference == CompositeModelPackage.Literals.SYNCHRONOUS_COMPONENT_INSTANCE__TYPE) {
				Package _package = StatechartModelDerivedFeatures.getContainingPackage(context);
				Set<SynchronousComponent> components = StatechartModelDerivedFeatures.getAllSynchronousComponents(_package);
				components.remove(context.eContainer());
				return Scopes.scopeFor(components);
			}
			if (context instanceof AsynchronousComponentInstance && reference == CompositeModelPackage.Literals.ASYNCHRONOUS_COMPONENT_INSTANCE__TYPE) {
				Package _package = StatechartModelDerivedFeatures.getContainingPackage(context);
				Set<AsynchronousComponent> components = StatechartModelDerivedFeatures.getAllAsynchronousComponents(_package);
				components.remove(context.eContainer());
				return Scopes.scopeFor(components);
			}		
			// Asynchronous adapter-specific rules
			if (context instanceof PortEventReference && reference == StatechartModelPackage.Literals.PORT_EVENT_REFERENCE__PORT ||
				context instanceof AnyPortEventReference && reference == StatechartModelPackage.Literals.ANY_PORT_EVENT_REFERENCE__PORT) {
				if (context.eContainer() instanceof StochasticRule) {
					ElementaryEnvironmentComponentInstance comp=(ElementaryEnvironmentComponentInstance) context.eContainer().eContainer();
					return Scopes.scopeFor(comp.getOutports());
				}else {
					AsynchronousAdapter wrapper = ecoreUtil.getContainerOfType(context, AsynchronousAdapter.class);
					if (wrapper != null) {
						// Derived feature "allPorts" does not work all the time
						Set<Port> ports = new HashSet<Port>();
						ports.addAll(wrapper.getPorts());
						ports.addAll(wrapper.getWrappedComponent().getType().getPorts());
						return Scopes.scopeFor(ports);
					}
				}
			}
			if ((context instanceof MessageQueue || context instanceof ControlSpecification) &&
					(reference == StatechartModelPackage.Literals.PORT_EVENT_REFERENCE__PORT ||
					reference == StatechartModelPackage.Literals.ANY_PORT_EVENT_REFERENCE__PORT)) {
				AsynchronousAdapter wrapper = ecoreUtil.getContainerOfType(context, AsynchronousAdapter.class);
				return Scopes.scopeFor(StatechartModelDerivedFeatures.getAllPorts(wrapper));
			}
			if ((context instanceof MessageQueue || context instanceof ControlSpecification) &&
					reference == StatechartModelPackage.Literals.PORT_EVENT_REFERENCE__EVENT) {
				AsynchronousAdapter wrapper = ecoreUtil.getContainerOfType(context, AsynchronousAdapter.class);
				Collection<Event> events = new HashSet<Event>();
				StatechartModelDerivedFeatures.getAllPorts(wrapper).stream()
					.forEach(it -> events.addAll(StatechartModelDerivedFeatures.getInputEvents(it)));
				return Scopes.scopeFor(events);
			}
			/*
			if (reference == ExpressionModelPackage.Literals.TYPE_REFERENCE__REFERENCE) {
				Package gammaPackage = (Package) EcoreUtil2.getRootContainer(context, true);
				List<TypeDeclaration> typeDeclarations = collectTypeDeclarations(gammaPackage);
				return Scopes.scopeFor(typeDeclarations);
			}*/
			if (/*context instanceof EventTrigger && */reference == ExpressionModelPackage.Literals.DIRECT_REFERENCE_EXPRESSION__DECLARATION) {
				Package gammaPackage = (Package) EcoreUtil2.getRootContainer(context, true);
				Collection<Declaration> normalDeclarations = EcoreUtil2.getAllContentsOfType(gammaPackage, Declaration.class);
				return Scopes.scopeFor(normalDeclarations);
			}
			
			//Environment component specific rules
			if (context instanceof EnvironmentSynchronousCompositeComponentInstance && (
					reference == EnvironmentModelPackage.Literals.ENVIRONMENT_SYNCHRONOUS_COMPOSITE_COMPONENT_INSTANCE__TYPE  )) {
				List<Component> components = collectEnvironmentalComponents((Package) context.eContainer().eContainer());
				components.remove(context.eContainer());
				return Scopes.scopeFor(components);
			}	
			if (context instanceof EnvironmentCascadeCompositeComponentInstance && (
					reference == EnvironmentModelPackage.Literals.ENVIRONMENT_CASCADE_COMPOSITE_COMPONENT_INSTANCE__TYPE  )) {
				List<Component> components = collectEnvironmentalComponents((Package) context.eContainer().eContainer());
				components.remove(context.eContainer());
				return Scopes.scopeFor(components);
			}	
			if (context instanceof EnvironmentAsynchronousCompositeComponentInstance && (
					reference == EnvironmentModelPackage.Literals.ENVIRONMENT_ASYNCHRONOUS_COMPOSITE_COMPONENT_INSTANCE__TYPE  )) {
				List<Component> components = collectEnvironmentalComponents((Package) context.eContainer().eContainer());
				components.remove(context.eContainer());
				return Scopes.scopeFor(components);
			}	
			
		} catch (NullPointerException e) {
			// Nullptr exception is thrown if the scope turns out to be empty
			// This can be due to modeling error of the user, e.g., there no in events on the specified ports
			return super.getScope(context, reference);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return super.getScope(context, reference);
	}
	
	
	

	private List<Component> collectEnvironmentalComponents(Package parentPackage) {
		List<Component> types = new ArrayList<Component>();
		for (Package importedPackage : parentPackage.getImports()) {
			for (Component importedComponent : importedPackage.getComponents()) {
				if (importedComponent instanceof AbstractEnvironmentCompositeComponent) {
					types.add(importedComponent);
				}
			}
		}
		for (Component siblingComponent : parentPackage.getComponents()) {
			if (siblingComponent instanceof AbstractEnvironmentCompositeComponent ) {
				types.add(siblingComponent);
			}
		}
		return types;
	}

	private List<Event> eventsFromDeclarations (List<EventDeclaration> eventDecs){
		List<Event> events=new ArrayList<>();
		for (EventDeclaration eventDec : eventDecs) {
			events.add(eventDec.getEvent());
		}
		return events;
	}
	
	
	/** The parent interfaces are taken into considerations as well. */ 
	private Collection<Event> getAllEvents(Interface anInterface) {
  		if (anInterface == null) {
  			return Collections.emptySet();
  		}
  		Set<Event> eventSet = new HashSet<Event>();
  		for (Interface parentInterface : anInterface.getParents()) {
  			eventSet.addAll(getAllEvents(parentInterface));
  		}
  		for (Event event : anInterface.getEvents().stream().map(it -> it.getEvent()).collect(Collectors.toSet())) {
  			eventSet.add(event);
  		}
  		return eventSet;
  	}
	
	private AbstractEnvironmentCompositeComponentInstance getRecursiveInstance(RecursiveComponentReference ref) {
		RecursiveComponentReference refi=ref;
		while (refi.getRecursivecomponentreference() != null) {
			refi=refi.getRecursivecomponentreference();
		}
		return refi.getComponent();
	}
	
	
	
	
}
