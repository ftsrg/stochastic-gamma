/**
 */
package hu.bme.mit.gamma.analysis.impl;

import hu.bme.mit.gamma.action.model.ActionModelPackage;

import hu.bme.mit.gamma.analysis.AnalysisAspect;
import hu.bme.mit.gamma.analysis.AnalysisComponent;
import hu.bme.mit.gamma.analysis.AnalysisCondition;
import hu.bme.mit.gamma.analysis.AnalysisFactory;
import hu.bme.mit.gamma.analysis.AnalysisPackage;
import hu.bme.mit.gamma.analysis.AssumeNotRaised;
import hu.bme.mit.gamma.analysis.AssumeRaised;
import hu.bme.mit.gamma.analysis.ComponentPortEventReference;
import hu.bme.mit.gamma.analysis.Frequency;
import hu.bme.mit.gamma.analysis.GreaterThan;
import hu.bme.mit.gamma.analysis.IsBetween;
import hu.bme.mit.gamma.analysis.LowerThan;
import hu.bme.mit.gamma.analysis.MeanParameter;
import hu.bme.mit.gamma.analysis.MeanTime;
import hu.bme.mit.gamma.analysis.ParameterDistribution;
import hu.bme.mit.gamma.analysis.Probability;
import hu.bme.mit.gamma.analysis.RequirementAspect;
import hu.bme.mit.gamma.analysis.RequirementComponent;
import hu.bme.mit.gamma.analysis.TimeBoundedProbability;
import hu.bme.mit.gamma.analysis.TimedProbability;

import hu.bme.mit.gamma.environment.model.EnvironentPackage;

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
public class AnalysisPackageImpl extends EPackageImpl implements AnalysisPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass analysisComponentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass analysisConditionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass assumeRaisedEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass assumeNotRaisedEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass analysisAspectEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass probabilityEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass timedProbabilityEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass meanTimeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass timeBoundedProbabilityEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass requirementComponentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass requirementAspectEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass lowerThanEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass greaterThanEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass isBetweenEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass componentPortEventReferenceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass frequencyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass meanParameterEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass parameterDistributionEClass = null;

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
	 * @see hu.bme.mit.gamma.analysis.AnalysisPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private AnalysisPackageImpl() {
		super(eNS_URI, AnalysisFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link AnalysisPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static AnalysisPackage init() {
		if (isInited) return (AnalysisPackage)EPackage.Registry.INSTANCE.getEPackage(AnalysisPackage.eNS_URI);

		// Obtain or create and register package
		Object registeredAnalysisPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
		AnalysisPackageImpl theAnalysisPackage = registeredAnalysisPackage instanceof AnalysisPackageImpl ? (AnalysisPackageImpl)registeredAnalysisPackage : new AnalysisPackageImpl();

		isInited = true;

		// Initialize simple dependencies
		ActionModelPackage.eINSTANCE.eClass();
		EnvironentPackage.eINSTANCE.eClass();
		ExpressionModelPackage.eINSTANCE.eClass();
		hu.bme.mit.gamma.stochastic.stochastic.StochasticPackage.eINSTANCE.eClass();
		InterfaceModelPackage.eINSTANCE.eClass();
		CompositeModelPackage.eINSTANCE.eClass();
		StatechartModelPackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theAnalysisPackage.createPackageContents();

		// Initialize created meta-data
		theAnalysisPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theAnalysisPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(AnalysisPackage.eNS_URI, theAnalysisPackage);
		return theAnalysisPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAnalysisComponent() {
		return analysisComponentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAnalysisComponent_AnalyzedComponent() {
		return (EReference)analysisComponentEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAnalysisComponent_Conditions() {
		return (EReference)analysisComponentEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAnalysisComponent_Aspect() {
		return (EReference)analysisComponentEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAnalysisComponent_SimulationTime() {
		return (EAttribute)analysisComponentEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAnalysisComponent_SimulationNumber() {
		return (EAttribute)analysisComponentEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAnalysisCondition() {
		return analysisConditionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAnalysisCondition_Event() {
		return (EReference)analysisConditionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAssumeRaised() {
		return assumeRaisedEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAssumeNotRaised() {
		return assumeNotRaisedEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAnalysisAspect() {
		return analysisAspectEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAnalysisAspect_Event() {
		return (EReference)analysisAspectEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getProbability() {
		return probabilityEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTimedProbability() {
		return timedProbabilityEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTimedProbability_TimeLimit() {
		return (EAttribute)timedProbabilityEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMeanTime() {
		return meanTimeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTimeBoundedProbability() {
		return timeBoundedProbabilityEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTimeBoundedProbability_LowerBound() {
		return (EAttribute)timeBoundedProbabilityEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTimeBoundedProbability_UpperBound() {
		return (EAttribute)timeBoundedProbabilityEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRequirementComponent() {
		return requirementComponentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRequirementComponent_Requirement() {
		return (EReference)requirementComponentEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRequirementAspect() {
		return requirementAspectEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRequirementAspect_Description() {
		return (EAttribute)requirementAspectEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLowerThan() {
		return lowerThanEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLowerThan_Value() {
		return (EAttribute)lowerThanEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGreaterThan() {
		return greaterThanEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGreaterThan_Value() {
		return (EAttribute)greaterThanEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIsBetween() {
		return isBetweenEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIsBetween_LowerBound() {
		return (EAttribute)isBetweenEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIsBetween_UpperBound() {
		return (EAttribute)isBetweenEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getComponentPortEventReference() {
		return componentPortEventReferenceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getComponentPortEventReference_Component() {
		return (EReference)componentPortEventReferenceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getComponentPortEventReference_Port() {
		return (EReference)componentPortEventReferenceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getComponentPortEventReference_Event() {
		return (EReference)componentPortEventReferenceEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFrequency() {
		return frequencyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMeanParameter() {
		return meanParameterEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getParameterDistribution() {
		return parameterDistributionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AnalysisFactory getAnalysisFactory() {
		return (AnalysisFactory)getEFactoryInstance();
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
		analysisComponentEClass = createEClass(ANALYSIS_COMPONENT);
		createEReference(analysisComponentEClass, ANALYSIS_COMPONENT__ANALYZED_COMPONENT);
		createEReference(analysisComponentEClass, ANALYSIS_COMPONENT__CONDITIONS);
		createEReference(analysisComponentEClass, ANALYSIS_COMPONENT__ASPECT);
		createEAttribute(analysisComponentEClass, ANALYSIS_COMPONENT__SIMULATION_TIME);
		createEAttribute(analysisComponentEClass, ANALYSIS_COMPONENT__SIMULATION_NUMBER);

		analysisConditionEClass = createEClass(ANALYSIS_CONDITION);
		createEReference(analysisConditionEClass, ANALYSIS_CONDITION__EVENT);

		assumeRaisedEClass = createEClass(ASSUME_RAISED);

		assumeNotRaisedEClass = createEClass(ASSUME_NOT_RAISED);

		analysisAspectEClass = createEClass(ANALYSIS_ASPECT);
		createEReference(analysisAspectEClass, ANALYSIS_ASPECT__EVENT);

		probabilityEClass = createEClass(PROBABILITY);

		timedProbabilityEClass = createEClass(TIMED_PROBABILITY);
		createEAttribute(timedProbabilityEClass, TIMED_PROBABILITY__TIME_LIMIT);

		meanTimeEClass = createEClass(MEAN_TIME);

		timeBoundedProbabilityEClass = createEClass(TIME_BOUNDED_PROBABILITY);
		createEAttribute(timeBoundedProbabilityEClass, TIME_BOUNDED_PROBABILITY__LOWER_BOUND);
		createEAttribute(timeBoundedProbabilityEClass, TIME_BOUNDED_PROBABILITY__UPPER_BOUND);

		requirementComponentEClass = createEClass(REQUIREMENT_COMPONENT);
		createEReference(requirementComponentEClass, REQUIREMENT_COMPONENT__REQUIREMENT);

		requirementAspectEClass = createEClass(REQUIREMENT_ASPECT);
		createEAttribute(requirementAspectEClass, REQUIREMENT_ASPECT__DESCRIPTION);

		lowerThanEClass = createEClass(LOWER_THAN);
		createEAttribute(lowerThanEClass, LOWER_THAN__VALUE);

		greaterThanEClass = createEClass(GREATER_THAN);
		createEAttribute(greaterThanEClass, GREATER_THAN__VALUE);

		isBetweenEClass = createEClass(IS_BETWEEN);
		createEAttribute(isBetweenEClass, IS_BETWEEN__LOWER_BOUND);
		createEAttribute(isBetweenEClass, IS_BETWEEN__UPPER_BOUND);

		componentPortEventReferenceEClass = createEClass(COMPONENT_PORT_EVENT_REFERENCE);
		createEReference(componentPortEventReferenceEClass, COMPONENT_PORT_EVENT_REFERENCE__COMPONENT);
		createEReference(componentPortEventReferenceEClass, COMPONENT_PORT_EVENT_REFERENCE__PORT);
		createEReference(componentPortEventReferenceEClass, COMPONENT_PORT_EVENT_REFERENCE__EVENT);

		frequencyEClass = createEClass(FREQUENCY);

		meanParameterEClass = createEClass(MEAN_PARAMETER);

		parameterDistributionEClass = createEClass(PARAMETER_DISTRIBUTION);
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
		InterfaceModelPackage theInterfaceModelPackage = (InterfaceModelPackage)EPackage.Registry.INSTANCE.getEPackage(InterfaceModelPackage.eNS_URI);
		EnvironentPackage theEnvironentPackage = (EnvironentPackage)EPackage.Registry.INSTANCE.getEPackage(EnvironentPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		analysisComponentEClass.getESuperTypes().add(theInterfaceModelPackage.getComponent());
		assumeRaisedEClass.getESuperTypes().add(this.getAnalysisCondition());
		assumeNotRaisedEClass.getESuperTypes().add(this.getAnalysisCondition());
		probabilityEClass.getESuperTypes().add(this.getAnalysisAspect());
		timedProbabilityEClass.getESuperTypes().add(this.getAnalysisAspect());
		meanTimeEClass.getESuperTypes().add(this.getAnalysisAspect());
		timeBoundedProbabilityEClass.getESuperTypes().add(this.getAnalysisAspect());
		requirementComponentEClass.getESuperTypes().add(this.getAnalysisComponent());
		lowerThanEClass.getESuperTypes().add(this.getRequirementAspect());
		greaterThanEClass.getESuperTypes().add(this.getRequirementAspect());
		isBetweenEClass.getESuperTypes().add(this.getRequirementAspect());
		frequencyEClass.getESuperTypes().add(this.getAnalysisAspect());
		meanParameterEClass.getESuperTypes().add(this.getAnalysisAspect());
		parameterDistributionEClass.getESuperTypes().add(this.getAnalysisAspect());

		// Initialize classes, features, and operations; add parameters
		initEClass(analysisComponentEClass, AnalysisComponent.class, "AnalysisComponent", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAnalysisComponent_AnalyzedComponent(), theEnvironentPackage.getEnvironmentCompositeComponentInstance(), null, "analyzedComponent", null, 1, 1, AnalysisComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAnalysisComponent_Conditions(), this.getAnalysisCondition(), null, "conditions", null, 0, -1, AnalysisComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAnalysisComponent_Aspect(), this.getAnalysisAspect(), null, "aspect", null, 1, 1, AnalysisComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAnalysisComponent_SimulationTime(), ecorePackage.getEDouble(), "simulationTime", "1.0", 1, 1, AnalysisComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAnalysisComponent_SimulationNumber(), ecorePackage.getEBigInteger(), "simulationNumber", "100", 1, 1, AnalysisComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(analysisConditionEClass, AnalysisCondition.class, "AnalysisCondition", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAnalysisCondition_Event(), this.getComponentPortEventReference(), null, "event", null, 1, 1, AnalysisCondition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(assumeRaisedEClass, AssumeRaised.class, "AssumeRaised", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(assumeNotRaisedEClass, AssumeNotRaised.class, "AssumeNotRaised", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(analysisAspectEClass, AnalysisAspect.class, "AnalysisAspect", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAnalysisAspect_Event(), this.getComponentPortEventReference(), null, "event", null, 1, 1, AnalysisAspect.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(probabilityEClass, Probability.class, "Probability", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(timedProbabilityEClass, TimedProbability.class, "TimedProbability", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTimedProbability_TimeLimit(), ecorePackage.getEDouble(), "timeLimit", null, 1, 1, TimedProbability.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(meanTimeEClass, MeanTime.class, "MeanTime", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(timeBoundedProbabilityEClass, TimeBoundedProbability.class, "TimeBoundedProbability", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTimeBoundedProbability_LowerBound(), ecorePackage.getEDouble(), "lowerBound", null, 1, 1, TimeBoundedProbability.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTimeBoundedProbability_UpperBound(), ecorePackage.getEDouble(), "upperBound", null, 1, 1, TimeBoundedProbability.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(requirementComponentEClass, RequirementComponent.class, "RequirementComponent", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getRequirementComponent_Requirement(), this.getRequirementAspect(), null, "requirement", null, 0, -1, RequirementComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(requirementAspectEClass, RequirementAspect.class, "RequirementAspect", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getRequirementAspect_Description(), ecorePackage.getEString(), "description", null, 0, 1, RequirementAspect.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(lowerThanEClass, LowerThan.class, "LowerThan", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getLowerThan_Value(), ecorePackage.getEDouble(), "value", null, 1, 1, LowerThan.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(greaterThanEClass, GreaterThan.class, "GreaterThan", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getGreaterThan_Value(), ecorePackage.getEDouble(), "value", null, 1, 1, GreaterThan.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(isBetweenEClass, IsBetween.class, "IsBetween", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getIsBetween_LowerBound(), ecorePackage.getEDouble(), "lowerBound", null, 1, 1, IsBetween.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIsBetween_UpperBound(), ecorePackage.getEDouble(), "upperBound", null, 1, 1, IsBetween.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(componentPortEventReferenceEClass, ComponentPortEventReference.class, "ComponentPortEventReference", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getComponentPortEventReference_Component(), theEnvironentPackage.getEnvironmentCompositeComponentInstance(), null, "component", null, 1, 1, ComponentPortEventReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getComponentPortEventReference_Port(), theInterfaceModelPackage.getPort(), null, "port", null, 1, 1, ComponentPortEventReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getComponentPortEventReference_Event(), theInterfaceModelPackage.getEvent(), null, "event", null, 1, 1, ComponentPortEventReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(frequencyEClass, Frequency.class, "Frequency", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(meanParameterEClass, MeanParameter.class, "MeanParameter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(parameterDistributionEClass, ParameterDistribution.class, "ParameterDistribution", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);
	}

} //AnalysisPackageImpl