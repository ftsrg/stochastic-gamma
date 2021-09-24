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
public class EnvironentFactoryImpl extends EFactoryImpl implements EnvironentFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static EnvironentFactory init() {
		try {
			EnvironentFactory theEnvironentFactory = (EnvironentFactory)EPackage.Registry.INSTANCE.getEFactory(EnvironentPackage.eNS_URI);
			if (theEnvironentFactory != null) {
				return theEnvironentFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new EnvironentFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EnvironentFactoryImpl() {
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
			case EnvironentPackage.ENVIRONMENT_EVENT_SOURCE: return createEnvironmentEventSource();
			case EnvironentPackage.ENVIRONMENT_COMPOSITE_COMPONENT: return createEnvironmentCompositeComponent();
			case EnvironentPackage.ENVIRONMENT_COMPOSITE_COMPONENT_INSTANCE: return createEnvironmentCompositeComponentInstance();
			case EnvironentPackage.ENVIRONMENT_DELAY: return createEnvironmentDelay();
			case EnvironentPackage.ENVIRONMENT_SWITCH: return createEnvironmentSwitch();
			case EnvironentPackage.EVENT_FILTER: return createEventFilter();
			case EnvironentPackage.STOCHASTIC_RULE: return createStochasticRule();
			case EnvironentPackage.PORT_FILTER: return createPortFilter();
			case EnvironentPackage.COMPONENT_FILTER: return createComponentFilter();
			case EnvironentPackage.ENVIRONMENT_SAMPLE: return createEnvironmentSample();
			case EnvironentPackage.ENVIRONMENT_EXTERN_SIMULATION: return createEnvironmentExternSimulation();
			case EnvironentPackage.SIMULATION_RULE: return createSimulationRule();
			case EnvironentPackage.SIMULATION: return createSimulation();
			case EnvironentPackage.ENVIRONMENT_PERIODIC_EVENT_SOURCE: return createEnvironmentPeriodicEventSource();
			case EnvironentPackage.PERIODIC_SIMULATION: return createPeriodicSimulation();
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
	public EnvironmentCompositeComponent createEnvironmentCompositeComponent() {
		EnvironmentCompositeComponentImpl environmentCompositeComponent = new EnvironmentCompositeComponentImpl();
		return environmentCompositeComponent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EnvironmentCompositeComponentInstance createEnvironmentCompositeComponentInstance() {
		EnvironmentCompositeComponentInstanceImpl environmentCompositeComponentInstance = new EnvironmentCompositeComponentInstanceImpl();
		return environmentCompositeComponentInstance;
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
	public EnvironentPackage getEnvironentPackage() {
		return (EnvironentPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static EnvironentPackage getPackage() {
		return EnvironentPackage.eINSTANCE;
	}

} //EnvironentFactoryImpl
