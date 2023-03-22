/**
 */
package hu.bme.mit.gamma.environment.analysis.impl;

import hu.bme.mit.gamma.action.model.ActionModelPackage;

import hu.bme.mit.gamma.environment.analysis.AnalysisAspect;
import hu.bme.mit.gamma.environment.analysis.AnalysisComponent;
import hu.bme.mit.gamma.environment.analysis.AnalysisCondition;
import hu.bme.mit.gamma.environment.analysis.AnalysisFactory;
import hu.bme.mit.gamma.environment.analysis.AnalysisPackage;
import hu.bme.mit.gamma.environment.analysis.AssumeCondition;
import hu.bme.mit.gamma.environment.analysis.AssumeNotRaised;
import hu.bme.mit.gamma.environment.analysis.AssumeRaised;
import hu.bme.mit.gamma.environment.analysis.ComponentPortEventReference;
import hu.bme.mit.gamma.environment.analysis.EndCondition;
import hu.bme.mit.gamma.environment.analysis.EventTimeRatio;
import hu.bme.mit.gamma.environment.analysis.Frequency;
import hu.bme.mit.gamma.environment.analysis.GreaterThan;
import hu.bme.mit.gamma.environment.analysis.IsBetween;
import hu.bme.mit.gamma.environment.analysis.LowerThan;
import hu.bme.mit.gamma.environment.analysis.MeanParameter;
import hu.bme.mit.gamma.environment.analysis.MeanTime;
import hu.bme.mit.gamma.environment.analysis.MeanTimeBetweenEvents;
import hu.bme.mit.gamma.environment.analysis.ObserveCondition;
import hu.bme.mit.gamma.environment.analysis.ObserveParameter;
import hu.bme.mit.gamma.environment.analysis.ObserveTime;
import hu.bme.mit.gamma.environment.analysis.ParameterDistribution;
import hu.bme.mit.gamma.environment.analysis.PrioryDistribution;
import hu.bme.mit.gamma.environment.analysis.Probability;
import hu.bme.mit.gamma.environment.analysis.RecursiveComponentReference;
import hu.bme.mit.gamma.environment.analysis.RequirementAspect;
import hu.bme.mit.gamma.environment.analysis.RequirementComponent;
import hu.bme.mit.gamma.environment.analysis.TimeBoundedProbability;
import hu.bme.mit.gamma.environment.analysis.TimedProbability;

import hu.bme.mit.gamma.environment.model.EnvironmentModelPackage;

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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass recursiveComponentReferenceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass meanTimeBetweenEventsEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass eventTimeRatioEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass observeParameterEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass prioryDistributionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass observeConditionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass assumeConditionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass observeTimeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass endConditionEClass = null;

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
	 * @see hu.bme.mit.gamma.environment.analysis.AnalysisPackage#eNS_URI
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
		EnvironmentModelPackage.eINSTANCE.eClass();
		ExpressionModelPackage.eINSTANCE.eClass();
		StochasticPackage.eINSTANCE.eClass();
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
	public EReference getAnalysisComponent_Priorydistribution() {
		return (EReference)analysisComponentEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAnalysisComponent_Endcondition() {
		return (EReference)analysisComponentEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAnalysisComponent_WarmupTime() {
		return (EAttribute)analysisComponentEClass.getEStructuralFeatures().get(7);
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
	public EReference getComponentPortEventReference_Port() {
		return (EReference)componentPortEventReferenceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getComponentPortEventReference_Event() {
		return (EReference)componentPortEventReferenceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getComponentPortEventReference_Component() {
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
	public EReference getMeanParameter_Parameter() {
		return (EReference)meanParameterEClass.getEStructuralFeatures().get(0);
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
	public EReference getParameterDistribution_Parameter() {
		return (EReference)parameterDistributionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRecursiveComponentReference() {
		return recursiveComponentReferenceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRecursiveComponentReference_Component() {
		return (EReference)recursiveComponentReferenceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRecursiveComponentReference_Recursivecomponentreference() {
		return (EReference)recursiveComponentReferenceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMeanTimeBetweenEvents() {
		return meanTimeBetweenEventsEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMeanTimeBetweenEvents_Event2() {
		return (EReference)meanTimeBetweenEventsEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEventTimeRatio() {
		return eventTimeRatioEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEventTimeRatio_Event2() {
		return (EReference)eventTimeRatioEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getObserveParameter() {
		return observeParameterEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getObserveParameter_Parameter() {
		return (EReference)observeParameterEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPrioryDistribution() {
		return prioryDistributionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPrioryDistribution_Randomvariable() {
		return (EReference)prioryDistributionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPrioryDistribution_Parameter() {
		return (EReference)prioryDistributionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getObserveCondition() {
		return observeConditionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getObserveCondition_Value() {
		return (EAttribute)observeConditionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getObserveCondition_Randomvariable() {
		return (EReference)observeConditionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAssumeCondition() {
		return assumeConditionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getObserveTime() {
		return observeTimeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEndCondition() {
		return endConditionEClass;
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
		createEReference(analysisComponentEClass, ANALYSIS_COMPONENT__PRIORYDISTRIBUTION);
		createEReference(analysisComponentEClass, ANALYSIS_COMPONENT__ENDCONDITION);
		createEAttribute(analysisComponentEClass, ANALYSIS_COMPONENT__WARMUP_TIME);

		analysisConditionEClass = createEClass(ANALYSIS_CONDITION);

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
		createEReference(componentPortEventReferenceEClass, COMPONENT_PORT_EVENT_REFERENCE__PORT);
		createEReference(componentPortEventReferenceEClass, COMPONENT_PORT_EVENT_REFERENCE__EVENT);
		createEReference(componentPortEventReferenceEClass, COMPONENT_PORT_EVENT_REFERENCE__COMPONENT);

		frequencyEClass = createEClass(FREQUENCY);

		meanParameterEClass = createEClass(MEAN_PARAMETER);
		createEReference(meanParameterEClass, MEAN_PARAMETER__PARAMETER);

		parameterDistributionEClass = createEClass(PARAMETER_DISTRIBUTION);
		createEReference(parameterDistributionEClass, PARAMETER_DISTRIBUTION__PARAMETER);

		recursiveComponentReferenceEClass = createEClass(RECURSIVE_COMPONENT_REFERENCE);
		createEReference(recursiveComponentReferenceEClass, RECURSIVE_COMPONENT_REFERENCE__COMPONENT);
		createEReference(recursiveComponentReferenceEClass, RECURSIVE_COMPONENT_REFERENCE__RECURSIVECOMPONENTREFERENCE);

		meanTimeBetweenEventsEClass = createEClass(MEAN_TIME_BETWEEN_EVENTS);
		createEReference(meanTimeBetweenEventsEClass, MEAN_TIME_BETWEEN_EVENTS__EVENT2);

		eventTimeRatioEClass = createEClass(EVENT_TIME_RATIO);
		createEReference(eventTimeRatioEClass, EVENT_TIME_RATIO__EVENT2);

		observeParameterEClass = createEClass(OBSERVE_PARAMETER);
		createEReference(observeParameterEClass, OBSERVE_PARAMETER__PARAMETER);

		prioryDistributionEClass = createEClass(PRIORY_DISTRIBUTION);
		createEReference(prioryDistributionEClass, PRIORY_DISTRIBUTION__RANDOMVARIABLE);
		createEReference(prioryDistributionEClass, PRIORY_DISTRIBUTION__PARAMETER);

		observeConditionEClass = createEClass(OBSERVE_CONDITION);
		createEAttribute(observeConditionEClass, OBSERVE_CONDITION__VALUE);
		createEReference(observeConditionEClass, OBSERVE_CONDITION__RANDOMVARIABLE);

		assumeConditionEClass = createEClass(ASSUME_CONDITION);

		observeTimeEClass = createEClass(OBSERVE_TIME);

		endConditionEClass = createEClass(END_CONDITION);
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
		EnvironmentModelPackage theEnvironmentModelPackage = (EnvironmentModelPackage)EPackage.Registry.INSTANCE.getEPackage(EnvironmentModelPackage.eNS_URI);
		ExpressionModelPackage theExpressionModelPackage = (ExpressionModelPackage)EPackage.Registry.INSTANCE.getEPackage(ExpressionModelPackage.eNS_URI);
		StochasticPackage theStochasticPackage = (StochasticPackage)EPackage.Registry.INSTANCE.getEPackage(StochasticPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		analysisComponentEClass.getESuperTypes().add(theInterfaceModelPackage.getComponent());
		analysisConditionEClass.getESuperTypes().add(this.getAnalysisAspect());
		assumeRaisedEClass.getESuperTypes().add(this.getAssumeCondition());
		assumeNotRaisedEClass.getESuperTypes().add(this.getAssumeCondition());
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
		meanTimeBetweenEventsEClass.getESuperTypes().add(this.getAnalysisAspect());
		eventTimeRatioEClass.getESuperTypes().add(this.getAnalysisAspect());
		observeParameterEClass.getESuperTypes().add(this.getObserveCondition());
		observeConditionEClass.getESuperTypes().add(this.getAnalysisCondition());
		assumeConditionEClass.getESuperTypes().add(this.getAnalysisCondition());
		observeTimeEClass.getESuperTypes().add(this.getObserveCondition());
		endConditionEClass.getESuperTypes().add(this.getAnalysisCondition());

		// Initialize classes, features, and operations; add parameters
		initEClass(analysisComponentEClass, AnalysisComponent.class, "AnalysisComponent", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAnalysisComponent_AnalyzedComponent(), theEnvironmentModelPackage.getEnvironmentAsynchronousCompositeComponentInstance(), null, "analyzedComponent", null, 1, 1, AnalysisComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAnalysisComponent_Conditions(), this.getAnalysisCondition(), null, "conditions", null, 0, -1, AnalysisComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAnalysisComponent_Aspect(), this.getAnalysisAspect(), null, "aspect", null, 1, -1, AnalysisComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAnalysisComponent_SimulationTime(), ecorePackage.getEDouble(), "simulationTime", "1.0", 1, 1, AnalysisComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAnalysisComponent_SimulationNumber(), ecorePackage.getEBigInteger(), "simulationNumber", "100", 1, 1, AnalysisComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAnalysisComponent_Priorydistribution(), this.getPrioryDistribution(), null, "priorydistribution", null, 0, -1, AnalysisComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAnalysisComponent_Endcondition(), this.getEndCondition(), null, "endcondition", null, 0, -1, AnalysisComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAnalysisComponent_WarmupTime(), ecorePackage.getEDouble(), "warmupTime", "0.0", 1, 1, AnalysisComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(analysisConditionEClass, AnalysisCondition.class, "AnalysisCondition", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

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
		initEReference(getComponentPortEventReference_Port(), theInterfaceModelPackage.getPort(), null, "port", null, 1, 1, ComponentPortEventReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getComponentPortEventReference_Event(), theInterfaceModelPackage.getEvent(), null, "event", null, 1, 1, ComponentPortEventReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getComponentPortEventReference_Component(), this.getRecursiveComponentReference(), null, "component", null, 1, 1, ComponentPortEventReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(frequencyEClass, Frequency.class, "Frequency", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(meanParameterEClass, MeanParameter.class, "MeanParameter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getMeanParameter_Parameter(), theExpressionModelPackage.getParameterDeclaration(), null, "parameter", null, 1, 1, MeanParameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(parameterDistributionEClass, ParameterDistribution.class, "ParameterDistribution", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getParameterDistribution_Parameter(), theExpressionModelPackage.getParameterDeclaration(), null, "parameter", null, 1, 1, ParameterDistribution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(recursiveComponentReferenceEClass, RecursiveComponentReference.class, "RecursiveComponentReference", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getRecursiveComponentReference_Component(), theEnvironmentModelPackage.getEnvironmentAsynchronousCompositeComponentInstance(), null, "component", null, 1, 1, RecursiveComponentReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getRecursiveComponentReference_Recursivecomponentreference(), this.getRecursiveComponentReference(), null, "recursivecomponentreference", null, 0, 1, RecursiveComponentReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(meanTimeBetweenEventsEClass, MeanTimeBetweenEvents.class, "MeanTimeBetweenEvents", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getMeanTimeBetweenEvents_Event2(), this.getComponentPortEventReference(), null, "event2", null, 1, 1, MeanTimeBetweenEvents.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(eventTimeRatioEClass, EventTimeRatio.class, "EventTimeRatio", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getEventTimeRatio_Event2(), this.getComponentPortEventReference(), null, "event2", null, 1, 1, EventTimeRatio.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(observeParameterEClass, ObserveParameter.class, "ObserveParameter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getObserveParameter_Parameter(), theExpressionModelPackage.getParameterDeclaration(), null, "parameter", null, 1, 1, ObserveParameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(prioryDistributionEClass, PrioryDistribution.class, "PrioryDistribution", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getPrioryDistribution_Randomvariable(), theStochasticPackage.getRandomVariable(), null, "randomvariable", null, 1, 1, PrioryDistribution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPrioryDistribution_Parameter(), theExpressionModelPackage.getParameterDeclaration(), null, "parameter", null, 1, 1, PrioryDistribution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(observeConditionEClass, ObserveCondition.class, "ObserveCondition", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getObserveCondition_Value(), ecorePackage.getEDouble(), "value", null, 1, 1, ObserveCondition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getObserveCondition_Randomvariable(), theStochasticPackage.getStochasticModel(), null, "randomvariable", null, 1, 1, ObserveCondition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(assumeConditionEClass, AssumeCondition.class, "AssumeCondition", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(observeTimeEClass, ObserveTime.class, "ObserveTime", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(endConditionEClass, EndCondition.class, "EndCondition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);
	}

} //AnalysisPackageImpl
