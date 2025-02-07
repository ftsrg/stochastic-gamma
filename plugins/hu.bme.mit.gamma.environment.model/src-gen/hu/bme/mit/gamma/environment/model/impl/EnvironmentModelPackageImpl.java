/**
 */
package hu.bme.mit.gamma.environment.model.impl;

import hu.bme.mit.gamma.action.model.ActionModelPackage;

import hu.bme.mit.gamma.environment.model.AbstractEnvironmentCompositeComponent;
import hu.bme.mit.gamma.environment.model.AbstractEnvironmentCompositeComponentInstance;
import hu.bme.mit.gamma.environment.model.ComponentFilter;
import hu.bme.mit.gamma.environment.model.ElementaryEnvironmentComponentInstance;
import hu.bme.mit.gamma.environment.model.EnvironmentAsynchronousCompositeComponent;
import hu.bme.mit.gamma.environment.model.EnvironmentAsynchronousCompositeComponentInstance;
import hu.bme.mit.gamma.environment.model.EnvironmentCascadeCompositeComponent;
import hu.bme.mit.gamma.environment.model.EnvironmentCascadeCompositeComponentInstance;
import hu.bme.mit.gamma.environment.model.EnvironmentComponent;
import hu.bme.mit.gamma.environment.model.EnvironmentComponentInstance;
import hu.bme.mit.gamma.environment.model.EnvironmentDelay;
import hu.bme.mit.gamma.environment.model.EnvironmentEventSource;
import hu.bme.mit.gamma.environment.model.EnvironmentExternSimulation;
import hu.bme.mit.gamma.environment.model.EnvironmentModelFactory;
import hu.bme.mit.gamma.environment.model.EnvironmentModelPackage;
import hu.bme.mit.gamma.environment.model.EnvironmentPeriodicEventSource;
import hu.bme.mit.gamma.environment.model.EnvironmentRule;
import hu.bme.mit.gamma.environment.model.EnvironmentSample;
import hu.bme.mit.gamma.environment.model.EnvironmentSwitch;
import hu.bme.mit.gamma.environment.model.EnvironmentSynchronousCompositeComponent;
import hu.bme.mit.gamma.environment.model.EnvironmentSynchronousCompositeComponentInstance;
import hu.bme.mit.gamma.environment.model.EventFilter;
import hu.bme.mit.gamma.environment.model.Filter;
import hu.bme.mit.gamma.environment.model.ParameterFilter;
import hu.bme.mit.gamma.environment.model.PeriodicSimulation;
import hu.bme.mit.gamma.environment.model.PortFilter;
import hu.bme.mit.gamma.environment.model.Simulation;
import hu.bme.mit.gamma.environment.model.SimulationRule;
import hu.bme.mit.gamma.environment.model.StochasticRule;

import hu.bme.mit.gamma.environment.stochastic.stochastic.StochasticPackage;

import hu.bme.mit.gamma.expression.model.ExpressionModelPackage;

import hu.bme.mit.gamma.statechart.composite.CompositeModelPackage;

import hu.bme.mit.gamma.statechart.interface_.InterfaceModelPackage;

import hu.bme.mit.gamma.statechart.statechart.StatechartModelPackage;

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
public class EnvironmentModelPackageImpl extends EPackageImpl implements EnvironmentModelPackage {
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
	private EClass environmentCascadeCompositeComponentEClass = null;

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
	private EClass environmentCascadeCompositeComponentInstanceEClass = null;

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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass environmentSynchronousCompositeComponentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass abstractEnvironmentCompositeComponentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass environmentComponentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass environmentAsynchronousCompositeComponentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass abstractEnvironmentCompositeComponentInstanceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass environmentSynchronousCompositeComponentInstanceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass environmentAsynchronousCompositeComponentInstanceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass parameterFilterEClass = null;

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
	 * @see hu.bme.mit.gamma.environment.model.EnvironmentModelPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private EnvironmentModelPackageImpl() {
		super(eNS_URI, EnvironmentModelFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link EnvironmentModelPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static EnvironmentModelPackage init() {
		if (isInited) return (EnvironmentModelPackage)EPackage.Registry.INSTANCE.getEPackage(EnvironmentModelPackage.eNS_URI);

		// Obtain or create and register package
		Object registeredEnvironmentModelPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
		EnvironmentModelPackageImpl theEnvironmentModelPackage = registeredEnvironmentModelPackage instanceof EnvironmentModelPackageImpl ? (EnvironmentModelPackageImpl)registeredEnvironmentModelPackage : new EnvironmentModelPackageImpl();

		isInited = true;

		// Initialize simple dependencies
		ExpressionModelPackage.eINSTANCE.eClass();
		CompositeModelPackage.eINSTANCE.eClass();
		InterfaceModelPackage.eINSTANCE.eClass();
		StochasticPackage.eINSTANCE.eClass();
		StatechartModelPackage.eINSTANCE.eClass();
		ActionModelPackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theEnvironmentModelPackage.createPackageContents();

		// Initialize created meta-data
		theEnvironmentModelPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theEnvironmentModelPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(EnvironmentModelPackage.eNS_URI, theEnvironmentModelPackage);
		return theEnvironmentModelPackage;
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
	public EClass getEnvironmentCascadeCompositeComponent() {
		return environmentCascadeCompositeComponentEClass;
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
	public EClass getEnvironmentCascadeCompositeComponentInstance() {
		return environmentCascadeCompositeComponentInstanceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEnvironmentCascadeCompositeComponentInstance_Type() {
		return (EReference)environmentCascadeCompositeComponentInstanceEClass.getEStructuralFeatures().get(0);
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
	public EClass getEnvironmentSynchronousCompositeComponent() {
		return environmentSynchronousCompositeComponentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAbstractEnvironmentCompositeComponent() {
		return abstractEnvironmentCompositeComponentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAbstractEnvironmentCompositeComponent_EnvironmentComponents() {
		return (EReference)abstractEnvironmentCompositeComponentEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEnvironmentComponent() {
		return environmentComponentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEnvironmentAsynchronousCompositeComponent() {
		return environmentAsynchronousCompositeComponentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAbstractEnvironmentCompositeComponentInstance() {
		return abstractEnvironmentCompositeComponentInstanceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEnvironmentSynchronousCompositeComponentInstance() {
		return environmentSynchronousCompositeComponentInstanceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEnvironmentSynchronousCompositeComponentInstance_Type() {
		return (EReference)environmentSynchronousCompositeComponentInstanceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEnvironmentAsynchronousCompositeComponentInstance() {
		return environmentAsynchronousCompositeComponentInstanceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEnvironmentAsynchronousCompositeComponentInstance_Type() {
		return (EReference)environmentAsynchronousCompositeComponentInstanceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getParameterFilter() {
		return parameterFilterEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getParameterFilter_Parameter() {
		return (EReference)parameterFilterEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EnvironmentModelFactory getEnvironmentModelFactory() {
		return (EnvironmentModelFactory)getEFactoryInstance();
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

		environmentCascadeCompositeComponentEClass = createEClass(ENVIRONMENT_CASCADE_COMPOSITE_COMPONENT);

		environmentComponentInstanceEClass = createEClass(ENVIRONMENT_COMPONENT_INSTANCE);

		environmentCascadeCompositeComponentInstanceEClass = createEClass(ENVIRONMENT_CASCADE_COMPOSITE_COMPONENT_INSTANCE);
		createEReference(environmentCascadeCompositeComponentInstanceEClass, ENVIRONMENT_CASCADE_COMPOSITE_COMPONENT_INSTANCE__TYPE);

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

		environmentSynchronousCompositeComponentEClass = createEClass(ENVIRONMENT_SYNCHRONOUS_COMPOSITE_COMPONENT);

		abstractEnvironmentCompositeComponentEClass = createEClass(ABSTRACT_ENVIRONMENT_COMPOSITE_COMPONENT);
		createEReference(abstractEnvironmentCompositeComponentEClass, ABSTRACT_ENVIRONMENT_COMPOSITE_COMPONENT__ENVIRONMENT_COMPONENTS);

		environmentComponentEClass = createEClass(ENVIRONMENT_COMPONENT);

		environmentAsynchronousCompositeComponentEClass = createEClass(ENVIRONMENT_ASYNCHRONOUS_COMPOSITE_COMPONENT);

		abstractEnvironmentCompositeComponentInstanceEClass = createEClass(ABSTRACT_ENVIRONMENT_COMPOSITE_COMPONENT_INSTANCE);

		environmentSynchronousCompositeComponentInstanceEClass = createEClass(ENVIRONMENT_SYNCHRONOUS_COMPOSITE_COMPONENT_INSTANCE);
		createEReference(environmentSynchronousCompositeComponentInstanceEClass, ENVIRONMENT_SYNCHRONOUS_COMPOSITE_COMPONENT_INSTANCE__TYPE);

		environmentAsynchronousCompositeComponentInstanceEClass = createEClass(ENVIRONMENT_ASYNCHRONOUS_COMPOSITE_COMPONENT_INSTANCE);
		createEReference(environmentAsynchronousCompositeComponentInstanceEClass, ENVIRONMENT_ASYNCHRONOUS_COMPOSITE_COMPONENT_INSTANCE__TYPE);

		parameterFilterEClass = createEClass(PARAMETER_FILTER);
		createEReference(parameterFilterEClass, PARAMETER_FILTER__PARAMETER);
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
		ExpressionModelPackage theExpressionModelPackage = (ExpressionModelPackage)EPackage.Registry.INSTANCE.getEPackage(ExpressionModelPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		environmentEventSourceEClass.getESuperTypes().add(this.getElementaryEnvironmentComponentInstance());
		environmentCascadeCompositeComponentEClass.getESuperTypes().add(theCompositeModelPackage.getCascadeCompositeComponent());
		environmentCascadeCompositeComponentEClass.getESuperTypes().add(this.getAbstractEnvironmentCompositeComponent());
		environmentComponentInstanceEClass.getESuperTypes().add(theCompositeModelPackage.getComponentInstance());
		environmentCascadeCompositeComponentInstanceEClass.getESuperTypes().add(this.getAbstractEnvironmentCompositeComponentInstance());
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
		environmentSynchronousCompositeComponentEClass.getESuperTypes().add(this.getAbstractEnvironmentCompositeComponent());
		environmentSynchronousCompositeComponentEClass.getESuperTypes().add(theCompositeModelPackage.getSynchronousCompositeComponent());
		abstractEnvironmentCompositeComponentEClass.getESuperTypes().add(this.getEnvironmentComponent());
		environmentAsynchronousCompositeComponentEClass.getESuperTypes().add(this.getAbstractEnvironmentCompositeComponent());
		environmentAsynchronousCompositeComponentEClass.getESuperTypes().add(theCompositeModelPackage.getAsynchronousCompositeComponent());
		abstractEnvironmentCompositeComponentInstanceEClass.getESuperTypes().add(this.getEnvironmentComponentInstance());
		environmentSynchronousCompositeComponentInstanceEClass.getESuperTypes().add(this.getAbstractEnvironmentCompositeComponentInstance());
		environmentAsynchronousCompositeComponentInstanceEClass.getESuperTypes().add(this.getAbstractEnvironmentCompositeComponentInstance());
		parameterFilterEClass.getESuperTypes().add(this.getEventFilter());

		// Initialize classes, features, and operations; add parameters
		initEClass(environmentEventSourceEClass, EnvironmentEventSource.class, "EnvironmentEventSource", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(environmentCascadeCompositeComponentEClass, EnvironmentCascadeCompositeComponent.class, "EnvironmentCascadeCompositeComponent", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(environmentComponentInstanceEClass, EnvironmentComponentInstance.class, "EnvironmentComponentInstance", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(environmentCascadeCompositeComponentInstanceEClass, EnvironmentCascadeCompositeComponentInstance.class, "EnvironmentCascadeCompositeComponentInstance", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getEnvironmentCascadeCompositeComponentInstance_Type(), this.getEnvironmentCascadeCompositeComponent(), null, "type", null, 1, 1, EnvironmentCascadeCompositeComponentInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

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

		initEClass(environmentSynchronousCompositeComponentEClass, EnvironmentSynchronousCompositeComponent.class, "EnvironmentSynchronousCompositeComponent", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(abstractEnvironmentCompositeComponentEClass, AbstractEnvironmentCompositeComponent.class, "AbstractEnvironmentCompositeComponent", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAbstractEnvironmentCompositeComponent_EnvironmentComponents(), this.getEnvironmentComponentInstance(), null, "environmentComponents", null, 0, -1, AbstractEnvironmentCompositeComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(environmentComponentEClass, EnvironmentComponent.class, "EnvironmentComponent", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(environmentAsynchronousCompositeComponentEClass, EnvironmentAsynchronousCompositeComponent.class, "EnvironmentAsynchronousCompositeComponent", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(abstractEnvironmentCompositeComponentInstanceEClass, AbstractEnvironmentCompositeComponentInstance.class, "AbstractEnvironmentCompositeComponentInstance", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(environmentSynchronousCompositeComponentInstanceEClass, EnvironmentSynchronousCompositeComponentInstance.class, "EnvironmentSynchronousCompositeComponentInstance", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getEnvironmentSynchronousCompositeComponentInstance_Type(), this.getEnvironmentSynchronousCompositeComponent(), null, "type", null, 1, 1, EnvironmentSynchronousCompositeComponentInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(environmentAsynchronousCompositeComponentInstanceEClass, EnvironmentAsynchronousCompositeComponentInstance.class, "EnvironmentAsynchronousCompositeComponentInstance", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getEnvironmentAsynchronousCompositeComponentInstance_Type(), this.getEnvironmentAsynchronousCompositeComponent(), null, "type", null, 1, 1, EnvironmentAsynchronousCompositeComponentInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(parameterFilterEClass, ParameterFilter.class, "ParameterFilter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getParameterFilter_Parameter(), theExpressionModelPackage.getParameterDeclaration(), null, "parameter", null, 1, 1, ParameterFilter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

} //EnvironmentModelPackageImpl
