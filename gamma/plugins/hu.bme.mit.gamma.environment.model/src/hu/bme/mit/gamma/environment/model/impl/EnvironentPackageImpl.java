/**
 */
package hu.bme.mit.gamma.environment.model.impl;

import hu.bme.mit.gamma.action.model.ActionModelPackage;

import hu.bme.mit.gamma.environment.model.ComponentFilter;
import hu.bme.mit.gamma.environment.model.ElementaryEnvironmentComponentInstance;
import hu.bme.mit.gamma.environment.model.EnvironentFactory;
import hu.bme.mit.gamma.environment.model.EnvironentPackage;
import hu.bme.mit.gamma.environment.model.EnvironmentComponentInstance;
import hu.bme.mit.gamma.environment.model.EnvironmentCompositeComponent;
import hu.bme.mit.gamma.environment.model.EnvironmentCompositeComponentInstance;
import hu.bme.mit.gamma.environment.model.EnvironmentDelay;
import hu.bme.mit.gamma.environment.model.EnvironmentEventSource;
import hu.bme.mit.gamma.environment.model.EnvironmentExternSimulation;
import hu.bme.mit.gamma.environment.model.EnvironmentPeriodicEventSource;
import hu.bme.mit.gamma.environment.model.EnvironmentRule;
import hu.bme.mit.gamma.environment.model.EnvironmentSample;
import hu.bme.mit.gamma.environment.model.EnvironmentSwitch;
import hu.bme.mit.gamma.environment.model.EventFilter;
import hu.bme.mit.gamma.environment.model.Filter;
import hu.bme.mit.gamma.environment.model.PeriodicSimulation;
import hu.bme.mit.gamma.environment.model.PortFilter;
import hu.bme.mit.gamma.environment.model.Simulation;
import hu.bme.mit.gamma.environment.model.SimulationRule;
import hu.bme.mit.gamma.environment.model.StochasticRule;

import hu.bme.mit.gamma.expression.model.ExpressionModelPackage;

import hu.bme.mit.gamma.statechart.composite.CompositeModelPackage;

import hu.bme.mit.gamma.statechart.interface_.InterfaceModelPackage;

import hu.bme.mit.gamma.statechart.statechart.StatechartModelPackage;

import hu.bme.mit.gamma.stochastic.stochastic.StochasticPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class EnvironentPackageImpl extends EPackageImpl implements EnvironentPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass environmentEventSourceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass environmentCompositeComponentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass environmentComponentInstanceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass environmentCompositeComponentInstanceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass environmentDelayEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass environmentSwitchEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass elementaryEnvironmentComponentInstanceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass eventFilterEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stochasticRuleEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass portFilterEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass componentFilterEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass environmentRuleEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass environmentSampleEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass environmentExternSimulationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass simulationRuleEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass filterEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass simulationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass environmentPeriodicEventSourceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass periodicSimulationEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see hu.bme.mit.gamma.environment.model.EnvironentPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private EnvironentPackageImpl() {
		super(eNS_URI, EnvironentFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 *
	 * <p>This method is used to initialize {@link EnvironentPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static EnvironentPackage init() {
		if (isInited) return (EnvironentPackage)EPackage.Registry.INSTANCE.getEPackage(EnvironentPackage.eNS_URI);

		// Obtain or create and register package
		Object registeredEnvironentPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
		EnvironentPackageImpl theEnvironentPackage = registeredEnvironentPackage instanceof EnvironentPackageImpl ? (EnvironentPackageImpl)registeredEnvironentPackage : new EnvironentPackageImpl();

		isInited = true;

		// Initialize simple dependencies
		ExpressionModelPackage.eINSTANCE.eClass();
		CompositeModelPackage.eINSTANCE.eClass();
		InterfaceModelPackage.eINSTANCE.eClass();
		StochasticPackage.eINSTANCE.eClass();
		StatechartModelPackage.eINSTANCE.eClass();
		ActionModelPackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theEnvironentPackage.createPackageContents();

		// Initialize created meta-data
		theEnvironentPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theEnvironentPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(EnvironentPackage.eNS_URI, theEnvironentPackage);
		return theEnvironentPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEnvironmentEventSource() {
		return environmentEventSourceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEnvironmentCompositeComponent() {
		return environmentCompositeComponentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEnvironmentCompositeComponent_EnvironmentComponents() {
		return (EReference)environmentCompositeComponentEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEnvironmentComponentInstance() {
		return environmentComponentInstanceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEnvironmentCompositeComponentInstance() {
		return environmentCompositeComponentInstanceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEnvironmentCompositeComponentInstance_Type() {
		return (EReference)environmentCompositeComponentInstanceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEnvironmentDelay() {
		return environmentDelayEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEnvironmentSwitch() {
		return environmentSwitchEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getElementaryEnvironmentComponentInstance() {
		return elementaryEnvironmentComponentInstanceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getElementaryEnvironmentComponentInstance_Outports() {
		return (EReference)elementaryEnvironmentComponentInstanceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getElementaryEnvironmentComponentInstance_Inports() {
		return (EReference)elementaryEnvironmentComponentInstanceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getElementaryEnvironmentComponentInstance_BehaviorRules() {
		return (EReference)elementaryEnvironmentComponentInstanceEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEventFilter() {
		return eventFilterEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEventFilter_Event() {
		return (EReference)eventFilterEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStochasticRule() {
		return stochasticRuleEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStochasticRule_StochasticModel() {
		return (EReference)stochasticRuleEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPortFilter() {
		return portFilterEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPortFilter_Port() {
		return (EReference)portFilterEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getComponentFilter() {
		return componentFilterEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEnvironmentRule() {
		return environmentRuleEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEnvironmentRule_Filter() {
		return (EReference)environmentRuleEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEnvironmentSample() {
		return environmentSampleEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEnvironmentExternSimulation() {
		return environmentExternSimulationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSimulationRule() {
		return simulationRuleEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSimulationRule_Simulation() {
		return (EReference)simulationRuleEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFilter() {
		return filterEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSimulation() {
		return simulationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSimulation_SimulationClassName() {
		return (EAttribute)simulationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEnvironmentPeriodicEventSource() {
		return environmentPeriodicEventSourceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPeriodicSimulation() {
		return periodicSimulationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPeriodicSimulation_PeriodTime() {
		return (EAttribute)periodicSimulationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EnvironentFactory getEnvironentFactory() {
		return (EnvironentFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		environmentEventSourceEClass = createEClass(ENVIRONMENT_EVENT_SOURCE);

		environmentCompositeComponentEClass = createEClass(ENVIRONMENT_COMPOSITE_COMPONENT);
		createEReference(environmentCompositeComponentEClass, ENVIRONMENT_COMPOSITE_COMPONENT__ENVIRONMENT_COMPONENTS);

		environmentComponentInstanceEClass = createEClass(ENVIRONMENT_COMPONENT_INSTANCE);

		environmentCompositeComponentInstanceEClass = createEClass(ENVIRONMENT_COMPOSITE_COMPONENT_INSTANCE);
		createEReference(environmentCompositeComponentInstanceEClass, ENVIRONMENT_COMPOSITE_COMPONENT_INSTANCE__TYPE);

		environmentDelayEClass = createEClass(ENVIRONMENT_DELAY);

		environmentSwitchEClass = createEClass(ENVIRONMENT_SWITCH);

		elementaryEnvironmentComponentInstanceEClass = createEClass(ELEMENTARY_ENVIRONMENT_COMPONENT_INSTANCE);
		createEReference(elementaryEnvironmentComponentInstanceEClass, ELEMENTARY_ENVIRONMENT_COMPONENT_INSTANCE__OUTPORTS);
		createEReference(elementaryEnvironmentComponentInstanceEClass, ELEMENTARY_ENVIRONMENT_COMPONENT_INSTANCE__INPORTS);
		createEReference(elementaryEnvironmentComponentInstanceEClass, ELEMENTARY_ENVIRONMENT_COMPONENT_INSTANCE__BEHAVIOR_RULES);

		eventFilterEClass = createEClass(EVENT_FILTER);
		createEReference(eventFilterEClass, EVENT_FILTER__EVENT);

		stochasticRuleEClass = createEClass(STOCHASTIC_RULE);
		createEReference(stochasticRuleEClass, STOCHASTIC_RULE__STOCHASTIC_MODEL);

		portFilterEClass = createEClass(PORT_FILTER);
		createEReference(portFilterEClass, PORT_FILTER__PORT);

		componentFilterEClass = createEClass(COMPONENT_FILTER);

		environmentRuleEClass = createEClass(ENVIRONMENT_RULE);
		createEReference(environmentRuleEClass, ENVIRONMENT_RULE__FILTER);

		environmentSampleEClass = createEClass(ENVIRONMENT_SAMPLE);

		environmentExternSimulationEClass = createEClass(ENVIRONMENT_EXTERN_SIMULATION);

		simulationRuleEClass = createEClass(SIMULATION_RULE);
		createEReference(simulationRuleEClass, SIMULATION_RULE__SIMULATION);

		filterEClass = createEClass(FILTER);

		simulationEClass = createEClass(SIMULATION);
		createEAttribute(simulationEClass, SIMULATION__SIMULATION_CLASS_NAME);

		environmentPeriodicEventSourceEClass = createEClass(ENVIRONMENT_PERIODIC_EVENT_SOURCE);

		periodicSimulationEClass = createEClass(PERIODIC_SIMULATION);
		createEAttribute(periodicSimulationEClass, PERIODIC_SIMULATION__PERIOD_TIME);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		CompositeModelPackage theCompositeModelPackage = (CompositeModelPackage)EPackage.Registry.INSTANCE.getEPackage(CompositeModelPackage.eNS_URI);
		InterfaceModelPackage theInterfaceModelPackage = (InterfaceModelPackage)EPackage.Registry.INSTANCE.getEPackage(InterfaceModelPackage.eNS_URI);
		StatechartModelPackage theStatechartModelPackage = (StatechartModelPackage)EPackage.Registry.INSTANCE.getEPackage(StatechartModelPackage.eNS_URI);
		StochasticPackage theStochasticPackage = (StochasticPackage)EPackage.Registry.INSTANCE.getEPackage(StochasticPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		environmentEventSourceEClass.getESuperTypes().add(this.getElementaryEnvironmentComponentInstance());
		environmentCompositeComponentEClass.getESuperTypes().add(theCompositeModelPackage.getCascadeCompositeComponent());
		environmentComponentInstanceEClass.getESuperTypes().add(theCompositeModelPackage.getComponentInstance());
		environmentCompositeComponentInstanceEClass.getESuperTypes().add(this.getEnvironmentComponentInstance());
		environmentDelayEClass.getESuperTypes().add(this.getElementaryEnvironmentComponentInstance());
		environmentSwitchEClass.getESuperTypes().add(this.getElementaryEnvironmentComponentInstance());
		elementaryEnvironmentComponentInstanceEClass.getESuperTypes().add(this.getEnvironmentComponentInstance());
		eventFilterEClass.getESuperTypes().add(this.getFilter());
		stochasticRuleEClass.getESuperTypes().add(this.getEnvironmentRule());
		portFilterEClass.getESuperTypes().add(this.getFilter());
		componentFilterEClass.getESuperTypes().add(this.getFilter());
		environmentSampleEClass.getESuperTypes().add(this.getElementaryEnvironmentComponentInstance());
		environmentExternSimulationEClass.getESuperTypes().add(this.getElementaryEnvironmentComponentInstance());
		simulationRuleEClass.getESuperTypes().add(this.getEnvironmentRule());
		environmentPeriodicEventSourceEClass.getESuperTypes().add(this.getElementaryEnvironmentComponentInstance());
		periodicSimulationEClass.getESuperTypes().add(this.getSimulation());

		// Initialize classes, features, and operations; add parameters
		initEClass(environmentEventSourceEClass, EnvironmentEventSource.class, "EnvironmentEventSource", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(environmentCompositeComponentEClass, EnvironmentCompositeComponent.class, "EnvironmentCompositeComponent", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getEnvironmentCompositeComponent_EnvironmentComponents(), this.getEnvironmentComponentInstance(), null, "environmentComponents", null, 0, -1, EnvironmentCompositeComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(environmentComponentInstanceEClass, EnvironmentComponentInstance.class, "EnvironmentComponentInstance", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(environmentCompositeComponentInstanceEClass, EnvironmentCompositeComponentInstance.class, "EnvironmentCompositeComponentInstance", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getEnvironmentCompositeComponentInstance_Type(), this.getEnvironmentCompositeComponent(), null, "type", null, 1, 1, EnvironmentCompositeComponentInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(environmentDelayEClass, EnvironmentDelay.class, "EnvironmentDelay", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(environmentSwitchEClass, EnvironmentSwitch.class, "EnvironmentSwitch", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(elementaryEnvironmentComponentInstanceEClass, ElementaryEnvironmentComponentInstance.class, "ElementaryEnvironmentComponentInstance", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getElementaryEnvironmentComponentInstance_Outports(), theInterfaceModelPackage.getPort(), null, "outports", null, 0, -1, ElementaryEnvironmentComponentInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getElementaryEnvironmentComponentInstance_Inports(), theInterfaceModelPackage.getPort(), null, "inports", null, 0, -1, ElementaryEnvironmentComponentInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getElementaryEnvironmentComponentInstance_BehaviorRules(), this.getEnvironmentRule(), null, "behaviorRules", null, 0, -1, ElementaryEnvironmentComponentInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(eventFilterEClass, EventFilter.class, "EventFilter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getEventFilter_Event(), theStatechartModelPackage.getPortEventReference(), null, "event", null, 1, 1, EventFilter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(stochasticRuleEClass, StochasticRule.class, "StochasticRule", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getStochasticRule_StochasticModel(), theStochasticPackage.getStochasticModel(), null, "stochasticModel", null, 1, 1, StochasticRule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(portFilterEClass, PortFilter.class, "PortFilter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getPortFilter_Port(), theInterfaceModelPackage.getPort(), null, "port", null, 1, 1, PortFilter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(componentFilterEClass, ComponentFilter.class, "ComponentFilter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(environmentRuleEClass, EnvironmentRule.class, "EnvironmentRule", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getEnvironmentRule_Filter(), this.getFilter(), null, "filter", null, 0, -1, EnvironmentRule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(environmentSampleEClass, EnvironmentSample.class, "EnvironmentSample", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(environmentExternSimulationEClass, EnvironmentExternSimulation.class, "EnvironmentExternSimulation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(simulationRuleEClass, SimulationRule.class, "SimulationRule", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSimulationRule_Simulation(), this.getSimulation(), null, "simulation", null, 1, 1, SimulationRule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(filterEClass, Filter.class, "Filter", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(simulationEClass, Simulation.class, "Simulation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSimulation_SimulationClassName(), ecorePackage.getEString(), "simulationClassName", null, 1, 1, Simulation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(environmentPeriodicEventSourceEClass, EnvironmentPeriodicEventSource.class, "EnvironmentPeriodicEventSource", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(periodicSimulationEClass, PeriodicSimulation.class, "PeriodicSimulation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPeriodicSimulation_PeriodTime(), ecorePackage.getEDouble(), "periodTime", null, 1, 1, PeriodicSimulation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

} //EnvironentPackageImpl
