/**
 */
package hu.bme.mit.gamma.environment.model.impl;

import hu.bme.mit.gamma.environment.model.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class EnvironmentModelFactoryImpl extends EFactoryImpl implements EnvironmentModelFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static EnvironmentModelFactory init() {
		try {
			EnvironmentModelFactory theEnvironmentModelFactory = (EnvironmentModelFactory)EPackage.Registry.INSTANCE.getEFactory(EnvironmentModelPackage.eNS_URI);
			if (theEnvironmentModelFactory != null) {
				return theEnvironmentModelFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new EnvironmentModelFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EnvironmentModelFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case EnvironmentModelPackage.ENVIRONMENT_EVENT_SOURCE: return createEnvironmentEventSource();
			case EnvironmentModelPackage.ENVIRONMENT_CASCADE_COMPOSITE_COMPONENT: return createEnvironmentCascadeCompositeComponent();
			case EnvironmentModelPackage.ENVIRONMENT_CASCADE_COMPOSITE_COMPONENT_INSTANCE: return createEnvironmentCascadeCompositeComponentInstance();
			case EnvironmentModelPackage.ENVIRONMENT_DELAY: return createEnvironmentDelay();
			case EnvironmentModelPackage.ENVIRONMENT_SWITCH: return createEnvironmentSwitch();
			case EnvironmentModelPackage.EVENT_FILTER: return createEventFilter();
			case EnvironmentModelPackage.STOCHASTIC_RULE: return createStochasticRule();
			case EnvironmentModelPackage.PORT_FILTER: return createPortFilter();
			case EnvironmentModelPackage.COMPONENT_FILTER: return createComponentFilter();
			case EnvironmentModelPackage.ENVIRONMENT_SAMPLE: return createEnvironmentSample();
			case EnvironmentModelPackage.ENVIRONMENT_EXTERN_SIMULATION: return createEnvironmentExternSimulation();
			case EnvironmentModelPackage.SIMULATION_RULE: return createSimulationRule();
			case EnvironmentModelPackage.SIMULATION: return createSimulation();
			case EnvironmentModelPackage.ENVIRONMENT_PERIODIC_EVENT_SOURCE: return createEnvironmentPeriodicEventSource();
			case EnvironmentModelPackage.PERIODIC_SIMULATION: return createPeriodicSimulation();
			case EnvironmentModelPackage.ENVIRONMENT_SYNCHRONOUS_COMPOSITE_COMPONENT: return createEnvironmentSynchronousCompositeComponent();
			case EnvironmentModelPackage.ENVIRONMENT_ASYNCHRONOUS_COMPOSITE_COMPONENT: return createEnvironmentAsynchronousCompositeComponent();
			case EnvironmentModelPackage.ENVIRONMENT_SYNCHRONOUS_COMPOSITE_COMPONENT_INSTANCE: return createEnvironmentSynchronousCompositeComponentInstance();
			case EnvironmentModelPackage.ENVIRONMENT_ASYNCHRONOUS_COMPOSITE_COMPONENT_INSTANCE: return createEnvironmentAsynchronousCompositeComponentInstance();
			case EnvironmentModelPackage.PARAMETER_FILTER: return createParameterFilter();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EnvironmentEventSource createEnvironmentEventSource() {
		EnvironmentEventSourceImpl environmentEventSource = new EnvironmentEventSourceImpl();
		return environmentEventSource;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EnvironmentCascadeCompositeComponent createEnvironmentCascadeCompositeComponent() {
		EnvironmentCascadeCompositeComponentImpl environmentCascadeCompositeComponent = new EnvironmentCascadeCompositeComponentImpl();
		return environmentCascadeCompositeComponent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EnvironmentCascadeCompositeComponentInstance createEnvironmentCascadeCompositeComponentInstance() {
		EnvironmentCascadeCompositeComponentInstanceImpl environmentCascadeCompositeComponentInstance = new EnvironmentCascadeCompositeComponentInstanceImpl();
		return environmentCascadeCompositeComponentInstance;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EnvironmentDelay createEnvironmentDelay() {
		EnvironmentDelayImpl environmentDelay = new EnvironmentDelayImpl();
		return environmentDelay;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EnvironmentSwitch createEnvironmentSwitch() {
		EnvironmentSwitchImpl environmentSwitch = new EnvironmentSwitchImpl();
		return environmentSwitch;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EventFilter createEventFilter() {
		EventFilterImpl eventFilter = new EventFilterImpl();
		return eventFilter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StochasticRule createStochasticRule() {
		StochasticRuleImpl stochasticRule = new StochasticRuleImpl();
		return stochasticRule;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PortFilter createPortFilter() {
		PortFilterImpl portFilter = new PortFilterImpl();
		return portFilter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComponentFilter createComponentFilter() {
		ComponentFilterImpl componentFilter = new ComponentFilterImpl();
		return componentFilter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EnvironmentSample createEnvironmentSample() {
		EnvironmentSampleImpl environmentSample = new EnvironmentSampleImpl();
		return environmentSample;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EnvironmentExternSimulation createEnvironmentExternSimulation() {
		EnvironmentExternSimulationImpl environmentExternSimulation = new EnvironmentExternSimulationImpl();
		return environmentExternSimulation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SimulationRule createSimulationRule() {
		SimulationRuleImpl simulationRule = new SimulationRuleImpl();
		return simulationRule;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Simulation createSimulation() {
		SimulationImpl simulation = new SimulationImpl();
		return simulation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EnvironmentPeriodicEventSource createEnvironmentPeriodicEventSource() {
		EnvironmentPeriodicEventSourceImpl environmentPeriodicEventSource = new EnvironmentPeriodicEventSourceImpl();
		return environmentPeriodicEventSource;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PeriodicSimulation createPeriodicSimulation() {
		PeriodicSimulationImpl periodicSimulation = new PeriodicSimulationImpl();
		return periodicSimulation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EnvironmentSynchronousCompositeComponent createEnvironmentSynchronousCompositeComponent() {
		EnvironmentSynchronousCompositeComponentImpl environmentSynchronousCompositeComponent = new EnvironmentSynchronousCompositeComponentImpl();
		return environmentSynchronousCompositeComponent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EnvironmentAsynchronousCompositeComponent createEnvironmentAsynchronousCompositeComponent() {
		EnvironmentAsynchronousCompositeComponentImpl environmentAsynchronousCompositeComponent = new EnvironmentAsynchronousCompositeComponentImpl();
		return environmentAsynchronousCompositeComponent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EnvironmentSynchronousCompositeComponentInstance createEnvironmentSynchronousCompositeComponentInstance() {
		EnvironmentSynchronousCompositeComponentInstanceImpl environmentSynchronousCompositeComponentInstance = new EnvironmentSynchronousCompositeComponentInstanceImpl();
		return environmentSynchronousCompositeComponentInstance;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EnvironmentAsynchronousCompositeComponentInstance createEnvironmentAsynchronousCompositeComponentInstance() {
		EnvironmentAsynchronousCompositeComponentInstanceImpl environmentAsynchronousCompositeComponentInstance = new EnvironmentAsynchronousCompositeComponentInstanceImpl();
		return environmentAsynchronousCompositeComponentInstance;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ParameterFilter createParameterFilter() {
		ParameterFilterImpl parameterFilter = new ParameterFilterImpl();
		return parameterFilter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EnvironmentModelPackage getEnvironmentModelPackage() {
		return (EnvironmentModelPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static EnvironmentModelPackage getPackage() {
		return EnvironmentModelPackage.eINSTANCE;
	}

} //EnvironmentModelFactoryImpl
