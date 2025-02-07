/**
 */
package hu.bme.mit.gamma.environment.analysis;

import hu.bme.mit.gamma.statechart.interface_.InterfaceModelPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see hu.bme.mit.gamma.environment.analysis.AnalysisFactory
 * @model kind="package"
 * @generated
 */
public interface AnalysisPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "analysis";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "www.mit.bme.hu/analysis/Model";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "hu.bme.mit.gamma.environment.analysis";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	AnalysisPackage eINSTANCE = hu.bme.mit.gamma.environment.analysis.impl.AnalysisPackageImpl.init();

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.environment.analysis.impl.AnalysisComponentImpl <em>Component</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.environment.analysis.impl.AnalysisComponentImpl
	 * @see hu.bme.mit.gamma.environment.analysis.impl.AnalysisPackageImpl#getAnalysisComponent()
	 * @generated
	 */
	int ANALYSIS_COMPONENT = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANALYSIS_COMPONENT__NAME = InterfaceModelPackage.COMPONENT__NAME;

	/**
	 * The feature id for the '<em><b>Parameter Declarations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANALYSIS_COMPONENT__PARAMETER_DECLARATIONS = InterfaceModelPackage.COMPONENT__PARAMETER_DECLARATIONS;

	/**
	 * The feature id for the '<em><b>Ports</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANALYSIS_COMPONENT__PORTS = InterfaceModelPackage.COMPONENT__PORTS;

	/**
	 * The feature id for the '<em><b>Function Declarations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANALYSIS_COMPONENT__FUNCTION_DECLARATIONS = InterfaceModelPackage.COMPONENT__FUNCTION_DECLARATIONS;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANALYSIS_COMPONENT__ANNOTATIONS = InterfaceModelPackage.COMPONENT__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Analyzed Component</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANALYSIS_COMPONENT__ANALYZED_COMPONENT = InterfaceModelPackage.COMPONENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Conditions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANALYSIS_COMPONENT__CONDITIONS = InterfaceModelPackage.COMPONENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Aspect</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANALYSIS_COMPONENT__ASPECT = InterfaceModelPackage.COMPONENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Priorydistribution</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANALYSIS_COMPONENT__PRIORYDISTRIBUTION = InterfaceModelPackage.COMPONENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Analysismethod</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANALYSIS_COMPONENT__ANALYSISMETHOD = InterfaceModelPackage.COMPONENT_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Component</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANALYSIS_COMPONENT_FEATURE_COUNT = InterfaceModelPackage.COMPONENT_FEATURE_COUNT + 5;

	/**
	 * The number of operations of the '<em>Component</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANALYSIS_COMPONENT_OPERATION_COUNT = InterfaceModelPackage.COMPONENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.environment.analysis.impl.AnalysisAspectImpl <em>Aspect</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.environment.analysis.impl.AnalysisAspectImpl
	 * @see hu.bme.mit.gamma.environment.analysis.impl.AnalysisPackageImpl#getAnalysisAspect()
	 * @generated
	 */
	int ANALYSIS_ASPECT = 4;

	/**
	 * The feature id for the '<em><b>Event</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANALYSIS_ASPECT__EVENT = 0;

	/**
	 * The number of structural features of the '<em>Aspect</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANALYSIS_ASPECT_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Aspect</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANALYSIS_ASPECT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.environment.analysis.impl.AnalysisConditionImpl <em>Condition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.environment.analysis.impl.AnalysisConditionImpl
	 * @see hu.bme.mit.gamma.environment.analysis.impl.AnalysisPackageImpl#getAnalysisCondition()
	 * @generated
	 */
	int ANALYSIS_CONDITION = 1;

	/**
	 * The feature id for the '<em><b>Event</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANALYSIS_CONDITION__EVENT = ANALYSIS_ASPECT__EVENT;

	/**
	 * The number of structural features of the '<em>Condition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANALYSIS_CONDITION_FEATURE_COUNT = ANALYSIS_ASPECT_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Condition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANALYSIS_CONDITION_OPERATION_COUNT = ANALYSIS_ASPECT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.environment.analysis.impl.AssumeConditionImpl <em>Assume Condition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.environment.analysis.impl.AssumeConditionImpl
	 * @see hu.bme.mit.gamma.environment.analysis.impl.AnalysisPackageImpl#getAssumeCondition()
	 * @generated
	 */
	int ASSUME_CONDITION = 24;

	/**
	 * The feature id for the '<em><b>Event</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSUME_CONDITION__EVENT = ANALYSIS_CONDITION__EVENT;

	/**
	 * The feature id for the '<em><b>Probability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSUME_CONDITION__PROBABILITY = ANALYSIS_CONDITION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Assume Condition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSUME_CONDITION_FEATURE_COUNT = ANALYSIS_CONDITION_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Assume Condition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSUME_CONDITION_OPERATION_COUNT = ANALYSIS_CONDITION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.environment.analysis.impl.AssumeRaisedImpl <em>Assume Raised</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.environment.analysis.impl.AssumeRaisedImpl
	 * @see hu.bme.mit.gamma.environment.analysis.impl.AnalysisPackageImpl#getAssumeRaised()
	 * @generated
	 */
	int ASSUME_RAISED = 2;

	/**
	 * The feature id for the '<em><b>Event</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSUME_RAISED__EVENT = ASSUME_CONDITION__EVENT;

	/**
	 * The feature id for the '<em><b>Probability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSUME_RAISED__PROBABILITY = ASSUME_CONDITION__PROBABILITY;

	/**
	 * The number of structural features of the '<em>Assume Raised</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSUME_RAISED_FEATURE_COUNT = ASSUME_CONDITION_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Assume Raised</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSUME_RAISED_OPERATION_COUNT = ASSUME_CONDITION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.environment.analysis.impl.AssumeNotRaisedImpl <em>Assume Not Raised</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.environment.analysis.impl.AssumeNotRaisedImpl
	 * @see hu.bme.mit.gamma.environment.analysis.impl.AnalysisPackageImpl#getAssumeNotRaised()
	 * @generated
	 */
	int ASSUME_NOT_RAISED = 3;

	/**
	 * The feature id for the '<em><b>Event</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSUME_NOT_RAISED__EVENT = ASSUME_CONDITION__EVENT;

	/**
	 * The feature id for the '<em><b>Probability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSUME_NOT_RAISED__PROBABILITY = ASSUME_CONDITION__PROBABILITY;

	/**
	 * The number of structural features of the '<em>Assume Not Raised</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSUME_NOT_RAISED_FEATURE_COUNT = ASSUME_CONDITION_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Assume Not Raised</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSUME_NOT_RAISED_OPERATION_COUNT = ASSUME_CONDITION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.environment.analysis.impl.ProbabilityImpl <em>Probability</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.environment.analysis.impl.ProbabilityImpl
	 * @see hu.bme.mit.gamma.environment.analysis.impl.AnalysisPackageImpl#getProbability()
	 * @generated
	 */
	int PROBABILITY = 5;

	/**
	 * The feature id for the '<em><b>Event</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROBABILITY__EVENT = ANALYSIS_ASPECT__EVENT;

	/**
	 * The number of structural features of the '<em>Probability</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROBABILITY_FEATURE_COUNT = ANALYSIS_ASPECT_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Probability</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROBABILITY_OPERATION_COUNT = ANALYSIS_ASPECT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.environment.analysis.impl.TimedProbabilityImpl <em>Timed Probability</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.environment.analysis.impl.TimedProbabilityImpl
	 * @see hu.bme.mit.gamma.environment.analysis.impl.AnalysisPackageImpl#getTimedProbability()
	 * @generated
	 */
	int TIMED_PROBABILITY = 6;

	/**
	 * The feature id for the '<em><b>Event</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIMED_PROBABILITY__EVENT = ANALYSIS_ASPECT__EVENT;

	/**
	 * The feature id for the '<em><b>Time Limit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIMED_PROBABILITY__TIME_LIMIT = ANALYSIS_ASPECT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Timed Probability</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIMED_PROBABILITY_FEATURE_COUNT = ANALYSIS_ASPECT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Timed Probability</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIMED_PROBABILITY_OPERATION_COUNT = ANALYSIS_ASPECT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.environment.analysis.impl.MeanTimeImpl <em>Mean Time</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.environment.analysis.impl.MeanTimeImpl
	 * @see hu.bme.mit.gamma.environment.analysis.impl.AnalysisPackageImpl#getMeanTime()
	 * @generated
	 */
	int MEAN_TIME = 7;

	/**
	 * The feature id for the '<em><b>Event</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEAN_TIME__EVENT = ANALYSIS_ASPECT__EVENT;

	/**
	 * The number of structural features of the '<em>Mean Time</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEAN_TIME_FEATURE_COUNT = ANALYSIS_ASPECT_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Mean Time</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEAN_TIME_OPERATION_COUNT = ANALYSIS_ASPECT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.environment.analysis.impl.TimeBoundedProbabilityImpl <em>Time Bounded Probability</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.environment.analysis.impl.TimeBoundedProbabilityImpl
	 * @see hu.bme.mit.gamma.environment.analysis.impl.AnalysisPackageImpl#getTimeBoundedProbability()
	 * @generated
	 */
	int TIME_BOUNDED_PROBABILITY = 8;

	/**
	 * The feature id for the '<em><b>Event</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME_BOUNDED_PROBABILITY__EVENT = ANALYSIS_ASPECT__EVENT;

	/**
	 * The feature id for the '<em><b>Lower Bound</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME_BOUNDED_PROBABILITY__LOWER_BOUND = ANALYSIS_ASPECT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Upper Bound</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME_BOUNDED_PROBABILITY__UPPER_BOUND = ANALYSIS_ASPECT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Time Bounded Probability</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME_BOUNDED_PROBABILITY_FEATURE_COUNT = ANALYSIS_ASPECT_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Time Bounded Probability</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME_BOUNDED_PROBABILITY_OPERATION_COUNT = ANALYSIS_ASPECT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.environment.analysis.impl.RequirementComponentImpl <em>Requirement Component</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.environment.analysis.impl.RequirementComponentImpl
	 * @see hu.bme.mit.gamma.environment.analysis.impl.AnalysisPackageImpl#getRequirementComponent()
	 * @generated
	 */
	int REQUIREMENT_COMPONENT = 9;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT_COMPONENT__NAME = ANALYSIS_COMPONENT__NAME;

	/**
	 * The feature id for the '<em><b>Parameter Declarations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT_COMPONENT__PARAMETER_DECLARATIONS = ANALYSIS_COMPONENT__PARAMETER_DECLARATIONS;

	/**
	 * The feature id for the '<em><b>Ports</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT_COMPONENT__PORTS = ANALYSIS_COMPONENT__PORTS;

	/**
	 * The feature id for the '<em><b>Function Declarations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT_COMPONENT__FUNCTION_DECLARATIONS = ANALYSIS_COMPONENT__FUNCTION_DECLARATIONS;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT_COMPONENT__ANNOTATIONS = ANALYSIS_COMPONENT__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Analyzed Component</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT_COMPONENT__ANALYZED_COMPONENT = ANALYSIS_COMPONENT__ANALYZED_COMPONENT;

	/**
	 * The feature id for the '<em><b>Conditions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT_COMPONENT__CONDITIONS = ANALYSIS_COMPONENT__CONDITIONS;

	/**
	 * The feature id for the '<em><b>Aspect</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT_COMPONENT__ASPECT = ANALYSIS_COMPONENT__ASPECT;

	/**
	 * The feature id for the '<em><b>Priorydistribution</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT_COMPONENT__PRIORYDISTRIBUTION = ANALYSIS_COMPONENT__PRIORYDISTRIBUTION;

	/**
	 * The feature id for the '<em><b>Analysismethod</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT_COMPONENT__ANALYSISMETHOD = ANALYSIS_COMPONENT__ANALYSISMETHOD;

	/**
	 * The feature id for the '<em><b>Requirement</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT_COMPONENT__REQUIREMENT = ANALYSIS_COMPONENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Requirement Component</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT_COMPONENT_FEATURE_COUNT = ANALYSIS_COMPONENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Requirement Component</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT_COMPONENT_OPERATION_COUNT = ANALYSIS_COMPONENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.environment.analysis.impl.RequirementAspectImpl <em>Requirement Aspect</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.environment.analysis.impl.RequirementAspectImpl
	 * @see hu.bme.mit.gamma.environment.analysis.impl.AnalysisPackageImpl#getRequirementAspect()
	 * @generated
	 */
	int REQUIREMENT_ASPECT = 10;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT_ASPECT__DESCRIPTION = 0;

	/**
	 * The number of structural features of the '<em>Requirement Aspect</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT_ASPECT_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Requirement Aspect</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT_ASPECT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.environment.analysis.impl.LowerThanImpl <em>Lower Than</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.environment.analysis.impl.LowerThanImpl
	 * @see hu.bme.mit.gamma.environment.analysis.impl.AnalysisPackageImpl#getLowerThan()
	 * @generated
	 */
	int LOWER_THAN = 11;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOWER_THAN__DESCRIPTION = REQUIREMENT_ASPECT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOWER_THAN__VALUE = REQUIREMENT_ASPECT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Lower Than</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOWER_THAN_FEATURE_COUNT = REQUIREMENT_ASPECT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Lower Than</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOWER_THAN_OPERATION_COUNT = REQUIREMENT_ASPECT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.environment.analysis.impl.GreaterThanImpl <em>Greater Than</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.environment.analysis.impl.GreaterThanImpl
	 * @see hu.bme.mit.gamma.environment.analysis.impl.AnalysisPackageImpl#getGreaterThan()
	 * @generated
	 */
	int GREATER_THAN = 12;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GREATER_THAN__DESCRIPTION = REQUIREMENT_ASPECT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GREATER_THAN__VALUE = REQUIREMENT_ASPECT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Greater Than</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GREATER_THAN_FEATURE_COUNT = REQUIREMENT_ASPECT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Greater Than</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GREATER_THAN_OPERATION_COUNT = REQUIREMENT_ASPECT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.environment.analysis.impl.IsBetweenImpl <em>Is Between</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.environment.analysis.impl.IsBetweenImpl
	 * @see hu.bme.mit.gamma.environment.analysis.impl.AnalysisPackageImpl#getIsBetween()
	 * @generated
	 */
	int IS_BETWEEN = 13;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IS_BETWEEN__DESCRIPTION = REQUIREMENT_ASPECT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Lower Bound</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IS_BETWEEN__LOWER_BOUND = REQUIREMENT_ASPECT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Upper Bound</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IS_BETWEEN__UPPER_BOUND = REQUIREMENT_ASPECT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Is Between</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IS_BETWEEN_FEATURE_COUNT = REQUIREMENT_ASPECT_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Is Between</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IS_BETWEEN_OPERATION_COUNT = REQUIREMENT_ASPECT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.environment.analysis.impl.ComponentPortEventReferenceImpl <em>Component Port Event Reference</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.environment.analysis.impl.ComponentPortEventReferenceImpl
	 * @see hu.bme.mit.gamma.environment.analysis.impl.AnalysisPackageImpl#getComponentPortEventReference()
	 * @generated
	 */
	int COMPONENT_PORT_EVENT_REFERENCE = 14;

	/**
	 * The feature id for the '<em><b>Port</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_PORT_EVENT_REFERENCE__PORT = 0;

	/**
	 * The feature id for the '<em><b>Event</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_PORT_EVENT_REFERENCE__EVENT = 1;

	/**
	 * The feature id for the '<em><b>Component</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_PORT_EVENT_REFERENCE__COMPONENT = 2;

	/**
	 * The number of structural features of the '<em>Component Port Event Reference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_PORT_EVENT_REFERENCE_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Component Port Event Reference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_PORT_EVENT_REFERENCE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.environment.analysis.impl.FrequencyImpl <em>Frequency</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.environment.analysis.impl.FrequencyImpl
	 * @see hu.bme.mit.gamma.environment.analysis.impl.AnalysisPackageImpl#getFrequency()
	 * @generated
	 */
	int FREQUENCY = 15;

	/**
	 * The feature id for the '<em><b>Event</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FREQUENCY__EVENT = ANALYSIS_ASPECT__EVENT;

	/**
	 * The number of structural features of the '<em>Frequency</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FREQUENCY_FEATURE_COUNT = ANALYSIS_ASPECT_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Frequency</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FREQUENCY_OPERATION_COUNT = ANALYSIS_ASPECT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.environment.analysis.impl.MeanParameterImpl <em>Mean Parameter</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.environment.analysis.impl.MeanParameterImpl
	 * @see hu.bme.mit.gamma.environment.analysis.impl.AnalysisPackageImpl#getMeanParameter()
	 * @generated
	 */
	int MEAN_PARAMETER = 16;

	/**
	 * The feature id for the '<em><b>Event</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEAN_PARAMETER__EVENT = ANALYSIS_ASPECT__EVENT;

	/**
	 * The feature id for the '<em><b>Parameter</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEAN_PARAMETER__PARAMETER = ANALYSIS_ASPECT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Mean Parameter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEAN_PARAMETER_FEATURE_COUNT = ANALYSIS_ASPECT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Mean Parameter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEAN_PARAMETER_OPERATION_COUNT = ANALYSIS_ASPECT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.environment.analysis.impl.ParameterDistributionImpl <em>Parameter Distribution</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.environment.analysis.impl.ParameterDistributionImpl
	 * @see hu.bme.mit.gamma.environment.analysis.impl.AnalysisPackageImpl#getParameterDistribution()
	 * @generated
	 */
	int PARAMETER_DISTRIBUTION = 17;

	/**
	 * The feature id for the '<em><b>Event</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER_DISTRIBUTION__EVENT = ANALYSIS_ASPECT__EVENT;

	/**
	 * The feature id for the '<em><b>Parameter</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER_DISTRIBUTION__PARAMETER = ANALYSIS_ASPECT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Parameter Distribution</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER_DISTRIBUTION_FEATURE_COUNT = ANALYSIS_ASPECT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Parameter Distribution</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER_DISTRIBUTION_OPERATION_COUNT = ANALYSIS_ASPECT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.environment.analysis.impl.RecursiveComponentReferenceImpl <em>Recursive Component Reference</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.environment.analysis.impl.RecursiveComponentReferenceImpl
	 * @see hu.bme.mit.gamma.environment.analysis.impl.AnalysisPackageImpl#getRecursiveComponentReference()
	 * @generated
	 */
	int RECURSIVE_COMPONENT_REFERENCE = 18;

	/**
	 * The feature id for the '<em><b>Component</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECURSIVE_COMPONENT_REFERENCE__COMPONENT = 0;

	/**
	 * The feature id for the '<em><b>Recursivecomponentreference</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECURSIVE_COMPONENT_REFERENCE__RECURSIVECOMPONENTREFERENCE = 1;

	/**
	 * The number of structural features of the '<em>Recursive Component Reference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECURSIVE_COMPONENT_REFERENCE_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Recursive Component Reference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECURSIVE_COMPONENT_REFERENCE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.environment.analysis.impl.MeanTimeBetweenEventsImpl <em>Mean Time Between Events</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.environment.analysis.impl.MeanTimeBetweenEventsImpl
	 * @see hu.bme.mit.gamma.environment.analysis.impl.AnalysisPackageImpl#getMeanTimeBetweenEvents()
	 * @generated
	 */
	int MEAN_TIME_BETWEEN_EVENTS = 19;

	/**
	 * The feature id for the '<em><b>Event</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEAN_TIME_BETWEEN_EVENTS__EVENT = ANALYSIS_ASPECT__EVENT;

	/**
	 * The feature id for the '<em><b>Event2</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEAN_TIME_BETWEEN_EVENTS__EVENT2 = ANALYSIS_ASPECT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Mean Time Between Events</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEAN_TIME_BETWEEN_EVENTS_FEATURE_COUNT = ANALYSIS_ASPECT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Mean Time Between Events</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEAN_TIME_BETWEEN_EVENTS_OPERATION_COUNT = ANALYSIS_ASPECT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.environment.analysis.impl.EventTimeRatioImpl <em>Event Time Ratio</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.environment.analysis.impl.EventTimeRatioImpl
	 * @see hu.bme.mit.gamma.environment.analysis.impl.AnalysisPackageImpl#getEventTimeRatio()
	 * @generated
	 */
	int EVENT_TIME_RATIO = 20;

	/**
	 * The feature id for the '<em><b>Event</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_TIME_RATIO__EVENT = ANALYSIS_ASPECT__EVENT;

	/**
	 * The feature id for the '<em><b>Event2</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_TIME_RATIO__EVENT2 = ANALYSIS_ASPECT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Event Time Ratio</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_TIME_RATIO_FEATURE_COUNT = ANALYSIS_ASPECT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Event Time Ratio</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_TIME_RATIO_OPERATION_COUNT = ANALYSIS_ASPECT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.environment.analysis.impl.ObserveConditionImpl <em>Observe Condition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.environment.analysis.impl.ObserveConditionImpl
	 * @see hu.bme.mit.gamma.environment.analysis.impl.AnalysisPackageImpl#getObserveCondition()
	 * @generated
	 */
	int OBSERVE_CONDITION = 23;

	/**
	 * The feature id for the '<em><b>Event</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBSERVE_CONDITION__EVENT = ANALYSIS_CONDITION__EVENT;

	/**
	 * The feature id for the '<em><b>Randomvariable</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBSERVE_CONDITION__RANDOMVARIABLE = ANALYSIS_CONDITION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Observe Condition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBSERVE_CONDITION_FEATURE_COUNT = ANALYSIS_CONDITION_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Observe Condition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBSERVE_CONDITION_OPERATION_COUNT = ANALYSIS_CONDITION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.environment.analysis.impl.ObserveParameterImpl <em>Observe Parameter</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.environment.analysis.impl.ObserveParameterImpl
	 * @see hu.bme.mit.gamma.environment.analysis.impl.AnalysisPackageImpl#getObserveParameter()
	 * @generated
	 */
	int OBSERVE_PARAMETER = 21;

	/**
	 * The feature id for the '<em><b>Event</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBSERVE_PARAMETER__EVENT = OBSERVE_CONDITION__EVENT;

	/**
	 * The feature id for the '<em><b>Randomvariable</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBSERVE_PARAMETER__RANDOMVARIABLE = OBSERVE_CONDITION__RANDOMVARIABLE;

	/**
	 * The feature id for the '<em><b>Parameter</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBSERVE_PARAMETER__PARAMETER = OBSERVE_CONDITION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Observe Parameter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBSERVE_PARAMETER_FEATURE_COUNT = OBSERVE_CONDITION_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Observe Parameter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBSERVE_PARAMETER_OPERATION_COUNT = OBSERVE_CONDITION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.environment.analysis.impl.PrioryDistributionImpl <em>Priory Distribution</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.environment.analysis.impl.PrioryDistributionImpl
	 * @see hu.bme.mit.gamma.environment.analysis.impl.AnalysisPackageImpl#getPrioryDistribution()
	 * @generated
	 */
	int PRIORY_DISTRIBUTION = 22;

	/**
	 * The feature id for the '<em><b>Randomvariable</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIORY_DISTRIBUTION__RANDOMVARIABLE = 0;

	/**
	 * The feature id for the '<em><b>Parameter</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIORY_DISTRIBUTION__PARAMETER = 1;

	/**
	 * The number of structural features of the '<em>Priory Distribution</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIORY_DISTRIBUTION_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Priory Distribution</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIORY_DISTRIBUTION_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.environment.analysis.impl.ObserveTimeImpl <em>Observe Time</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.environment.analysis.impl.ObserveTimeImpl
	 * @see hu.bme.mit.gamma.environment.analysis.impl.AnalysisPackageImpl#getObserveTime()
	 * @generated
	 */
	int OBSERVE_TIME = 25;

	/**
	 * The feature id for the '<em><b>Event</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBSERVE_TIME__EVENT = OBSERVE_CONDITION__EVENT;

	/**
	 * The feature id for the '<em><b>Randomvariable</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBSERVE_TIME__RANDOMVARIABLE = OBSERVE_CONDITION__RANDOMVARIABLE;

	/**
	 * The number of structural features of the '<em>Observe Time</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBSERVE_TIME_FEATURE_COUNT = OBSERVE_CONDITION_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Observe Time</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBSERVE_TIME_OPERATION_COUNT = OBSERVE_CONDITION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.environment.analysis.impl.EndConditionImpl <em>End Condition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.environment.analysis.impl.EndConditionImpl
	 * @see hu.bme.mit.gamma.environment.analysis.impl.AnalysisPackageImpl#getEndCondition()
	 * @generated
	 */
	int END_CONDITION = 26;

	/**
	 * The feature id for the '<em><b>Event</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END_CONDITION__EVENT = ANALYSIS_CONDITION__EVENT;

	/**
	 * The number of structural features of the '<em>End Condition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END_CONDITION_FEATURE_COUNT = ANALYSIS_CONDITION_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>End Condition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END_CONDITION_OPERATION_COUNT = ANALYSIS_CONDITION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.environment.analysis.impl.AnalysisMethodImpl <em>Method</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.environment.analysis.impl.AnalysisMethodImpl
	 * @see hu.bme.mit.gamma.environment.analysis.impl.AnalysisPackageImpl#getAnalysisMethod()
	 * @generated
	 */
	int ANALYSIS_METHOD = 27;

	/**
	 * The number of structural features of the '<em>Method</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANALYSIS_METHOD_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Method</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANALYSIS_METHOD_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.environment.analysis.impl.ExactAnalysisMethodImpl <em>Exact Analysis Method</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.environment.analysis.impl.ExactAnalysisMethodImpl
	 * @see hu.bme.mit.gamma.environment.analysis.impl.AnalysisPackageImpl#getExactAnalysisMethod()
	 * @generated
	 */
	int EXACT_ANALYSIS_METHOD = 28;

	/**
	 * The number of structural features of the '<em>Exact Analysis Method</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXACT_ANALYSIS_METHOD_FEATURE_COUNT = ANALYSIS_METHOD_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Exact Analysis Method</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXACT_ANALYSIS_METHOD_OPERATION_COUNT = ANALYSIS_METHOD_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.environment.analysis.impl.SimulationAnalysisMethodImpl <em>Simulation Analysis Method</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.environment.analysis.impl.SimulationAnalysisMethodImpl
	 * @see hu.bme.mit.gamma.environment.analysis.impl.AnalysisPackageImpl#getSimulationAnalysisMethod()
	 * @generated
	 */
	int SIMULATION_ANALYSIS_METHOD = 29;

	/**
	 * The feature id for the '<em><b>Endcondition</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMULATION_ANALYSIS_METHOD__ENDCONDITION = ANALYSIS_METHOD_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Simulation Number</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMULATION_ANALYSIS_METHOD__SIMULATION_NUMBER = ANALYSIS_METHOD_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Warmup Time</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMULATION_ANALYSIS_METHOD__WARMUP_TIME = ANALYSIS_METHOD_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Simulation Time</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMULATION_ANALYSIS_METHOD__SIMULATION_TIME = ANALYSIS_METHOD_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Sampling Batch Size</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMULATION_ANALYSIS_METHOD__SAMPLING_BATCH_SIZE = ANALYSIS_METHOD_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Joint Sampling</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMULATION_ANALYSIS_METHOD__JOINT_SAMPLING = ANALYSIS_METHOD_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Debug</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMULATION_ANALYSIS_METHOD__DEBUG = ANALYSIS_METHOD_FEATURE_COUNT + 6;

	/**
	 * The number of structural features of the '<em>Simulation Analysis Method</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMULATION_ANALYSIS_METHOD_FEATURE_COUNT = ANALYSIS_METHOD_FEATURE_COUNT + 7;

	/**
	 * The number of operations of the '<em>Simulation Analysis Method</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMULATION_ANALYSIS_METHOD_OPERATION_COUNT = ANALYSIS_METHOD_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.environment.analysis.impl.ImportanceSamplingImpl <em>Importance Sampling</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.environment.analysis.impl.ImportanceSamplingImpl
	 * @see hu.bme.mit.gamma.environment.analysis.impl.AnalysisPackageImpl#getImportanceSampling()
	 * @generated
	 */
	int IMPORTANCE_SAMPLING = 30;

	/**
	 * The feature id for the '<em><b>Endcondition</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORTANCE_SAMPLING__ENDCONDITION = SIMULATION_ANALYSIS_METHOD__ENDCONDITION;

	/**
	 * The feature id for the '<em><b>Simulation Number</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORTANCE_SAMPLING__SIMULATION_NUMBER = SIMULATION_ANALYSIS_METHOD__SIMULATION_NUMBER;

	/**
	 * The feature id for the '<em><b>Warmup Time</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORTANCE_SAMPLING__WARMUP_TIME = SIMULATION_ANALYSIS_METHOD__WARMUP_TIME;

	/**
	 * The feature id for the '<em><b>Simulation Time</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORTANCE_SAMPLING__SIMULATION_TIME = SIMULATION_ANALYSIS_METHOD__SIMULATION_TIME;

	/**
	 * The feature id for the '<em><b>Sampling Batch Size</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORTANCE_SAMPLING__SAMPLING_BATCH_SIZE = SIMULATION_ANALYSIS_METHOD__SAMPLING_BATCH_SIZE;

	/**
	 * The feature id for the '<em><b>Joint Sampling</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORTANCE_SAMPLING__JOINT_SAMPLING = SIMULATION_ANALYSIS_METHOD__JOINT_SAMPLING;

	/**
	 * The feature id for the '<em><b>Debug</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORTANCE_SAMPLING__DEBUG = SIMULATION_ANALYSIS_METHOD__DEBUG;

	/**
	 * The number of structural features of the '<em>Importance Sampling</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORTANCE_SAMPLING_FEATURE_COUNT = SIMULATION_ANALYSIS_METHOD_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Importance Sampling</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORTANCE_SAMPLING_OPERATION_COUNT = SIMULATION_ANALYSIS_METHOD_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.environment.analysis.impl.MCMCImpl <em>MCMC</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.environment.analysis.impl.MCMCImpl
	 * @see hu.bme.mit.gamma.environment.analysis.impl.AnalysisPackageImpl#getMCMC()
	 * @generated
	 */
	int MCMC = 31;

	/**
	 * The feature id for the '<em><b>Endcondition</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MCMC__ENDCONDITION = SIMULATION_ANALYSIS_METHOD__ENDCONDITION;

	/**
	 * The feature id for the '<em><b>Simulation Number</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MCMC__SIMULATION_NUMBER = SIMULATION_ANALYSIS_METHOD__SIMULATION_NUMBER;

	/**
	 * The feature id for the '<em><b>Warmup Time</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MCMC__WARMUP_TIME = SIMULATION_ANALYSIS_METHOD__WARMUP_TIME;

	/**
	 * The feature id for the '<em><b>Simulation Time</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MCMC__SIMULATION_TIME = SIMULATION_ANALYSIS_METHOD__SIMULATION_TIME;

	/**
	 * The feature id for the '<em><b>Sampling Batch Size</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MCMC__SAMPLING_BATCH_SIZE = SIMULATION_ANALYSIS_METHOD__SAMPLING_BATCH_SIZE;

	/**
	 * The feature id for the '<em><b>Joint Sampling</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MCMC__JOINT_SAMPLING = SIMULATION_ANALYSIS_METHOD__JOINT_SAMPLING;

	/**
	 * The feature id for the '<em><b>Debug</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MCMC__DEBUG = SIMULATION_ANALYSIS_METHOD__DEBUG;

	/**
	 * The feature id for the '<em><b>Kernel</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MCMC__KERNEL = SIMULATION_ANALYSIS_METHOD_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Warmup Step Num</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MCMC__WARMUP_STEP_NUM = SIMULATION_ANALYSIS_METHOD_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>MCMC</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MCMC_FEATURE_COUNT = SIMULATION_ANALYSIS_METHOD_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>MCMC</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MCMC_OPERATION_COUNT = SIMULATION_ANALYSIS_METHOD_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.environment.analysis.impl.SVIImpl <em>SVI</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.environment.analysis.impl.SVIImpl
	 * @see hu.bme.mit.gamma.environment.analysis.impl.AnalysisPackageImpl#getSVI()
	 * @generated
	 */
	int SVI = 32;

	/**
	 * The feature id for the '<em><b>Endcondition</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SVI__ENDCONDITION = SIMULATION_ANALYSIS_METHOD__ENDCONDITION;

	/**
	 * The feature id for the '<em><b>Simulation Number</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SVI__SIMULATION_NUMBER = SIMULATION_ANALYSIS_METHOD__SIMULATION_NUMBER;

	/**
	 * The feature id for the '<em><b>Warmup Time</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SVI__WARMUP_TIME = SIMULATION_ANALYSIS_METHOD__WARMUP_TIME;

	/**
	 * The feature id for the '<em><b>Simulation Time</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SVI__SIMULATION_TIME = SIMULATION_ANALYSIS_METHOD__SIMULATION_TIME;

	/**
	 * The feature id for the '<em><b>Sampling Batch Size</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SVI__SAMPLING_BATCH_SIZE = SIMULATION_ANALYSIS_METHOD__SAMPLING_BATCH_SIZE;

	/**
	 * The feature id for the '<em><b>Joint Sampling</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SVI__JOINT_SAMPLING = SIMULATION_ANALYSIS_METHOD__JOINT_SAMPLING;

	/**
	 * The feature id for the '<em><b>Debug</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SVI__DEBUG = SIMULATION_ANALYSIS_METHOD__DEBUG;

	/**
	 * The number of structural features of the '<em>SVI</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SVI_FEATURE_COUNT = SIMULATION_ANALYSIS_METHOD_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>SVI</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SVI_OPERATION_COUNT = SIMULATION_ANALYSIS_METHOD_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.environment.analysis.impl.MCMCKernelImpl <em>MCMC Kernel</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.environment.analysis.impl.MCMCKernelImpl
	 * @see hu.bme.mit.gamma.environment.analysis.impl.AnalysisPackageImpl#getMCMCKernel()
	 * @generated
	 */
	int MCMC_KERNEL = 33;

	/**
	 * The number of structural features of the '<em>MCMC Kernel</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MCMC_KERNEL_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>MCMC Kernel</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MCMC_KERNEL_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.environment.analysis.impl.NUTSImpl <em>NUTS</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.environment.analysis.impl.NUTSImpl
	 * @see hu.bme.mit.gamma.environment.analysis.impl.AnalysisPackageImpl#getNUTS()
	 * @generated
	 */
	int NUTS = 34;

	/**
	 * The number of structural features of the '<em>NUTS</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUTS_FEATURE_COUNT = MCMC_KERNEL_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>NUTS</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUTS_OPERATION_COUNT = MCMC_KERNEL_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.environment.analysis.impl.HMCImpl <em>HMC</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.environment.analysis.impl.HMCImpl
	 * @see hu.bme.mit.gamma.environment.analysis.impl.AnalysisPackageImpl#getHMC()
	 * @generated
	 */
	int HMC = 35;

	/**
	 * The number of structural features of the '<em>HMC</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HMC_FEATURE_COUNT = MCMC_KERNEL_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>HMC</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HMC_OPERATION_COUNT = MCMC_KERNEL_OPERATION_COUNT + 0;


	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.environment.analysis.impl.SimulationImpl <em>Simulation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.environment.analysis.impl.SimulationImpl
	 * @see hu.bme.mit.gamma.environment.analysis.impl.AnalysisPackageImpl#getSimulation()
	 * @generated
	 */
	int SIMULATION = 36;

	/**
	 * The feature id for the '<em><b>Endcondition</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMULATION__ENDCONDITION = SIMULATION_ANALYSIS_METHOD__ENDCONDITION;

	/**
	 * The feature id for the '<em><b>Simulation Number</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMULATION__SIMULATION_NUMBER = SIMULATION_ANALYSIS_METHOD__SIMULATION_NUMBER;

	/**
	 * The feature id for the '<em><b>Warmup Time</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMULATION__WARMUP_TIME = SIMULATION_ANALYSIS_METHOD__WARMUP_TIME;

	/**
	 * The feature id for the '<em><b>Simulation Time</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMULATION__SIMULATION_TIME = SIMULATION_ANALYSIS_METHOD__SIMULATION_TIME;

	/**
	 * The feature id for the '<em><b>Sampling Batch Size</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMULATION__SAMPLING_BATCH_SIZE = SIMULATION_ANALYSIS_METHOD__SAMPLING_BATCH_SIZE;

	/**
	 * The feature id for the '<em><b>Joint Sampling</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMULATION__JOINT_SAMPLING = SIMULATION_ANALYSIS_METHOD__JOINT_SAMPLING;

	/**
	 * The feature id for the '<em><b>Debug</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMULATION__DEBUG = SIMULATION_ANALYSIS_METHOD__DEBUG;

	/**
	 * The number of structural features of the '<em>Simulation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMULATION_FEATURE_COUNT = SIMULATION_ANALYSIS_METHOD_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Simulation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMULATION_OPERATION_COUNT = SIMULATION_ANALYSIS_METHOD_OPERATION_COUNT + 0;


	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.environment.analysis.AnalysisComponent <em>Component</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Component</em>'.
	 * @see hu.bme.mit.gamma.environment.analysis.AnalysisComponent
	 * @generated
	 */
	EClass getAnalysisComponent();

	/**
	 * Returns the meta object for the containment reference '{@link hu.bme.mit.gamma.environment.analysis.AnalysisComponent#getAnalyzedComponent <em>Analyzed Component</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Analyzed Component</em>'.
	 * @see hu.bme.mit.gamma.environment.analysis.AnalysisComponent#getAnalyzedComponent()
	 * @see #getAnalysisComponent()
	 * @generated
	 */
	EReference getAnalysisComponent_AnalyzedComponent();

	/**
	 * Returns the meta object for the containment reference list '{@link hu.bme.mit.gamma.environment.analysis.AnalysisComponent#getConditions <em>Conditions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Conditions</em>'.
	 * @see hu.bme.mit.gamma.environment.analysis.AnalysisComponent#getConditions()
	 * @see #getAnalysisComponent()
	 * @generated
	 */
	EReference getAnalysisComponent_Conditions();

	/**
	 * Returns the meta object for the containment reference list '{@link hu.bme.mit.gamma.environment.analysis.AnalysisComponent#getAspect <em>Aspect</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Aspect</em>'.
	 * @see hu.bme.mit.gamma.environment.analysis.AnalysisComponent#getAspect()
	 * @see #getAnalysisComponent()
	 * @generated
	 */
	EReference getAnalysisComponent_Aspect();

	/**
	 * Returns the meta object for the containment reference list '{@link hu.bme.mit.gamma.environment.analysis.AnalysisComponent#getPriorydistribution <em>Priorydistribution</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Priorydistribution</em>'.
	 * @see hu.bme.mit.gamma.environment.analysis.AnalysisComponent#getPriorydistribution()
	 * @see #getAnalysisComponent()
	 * @generated
	 */
	EReference getAnalysisComponent_Priorydistribution();

	/**
	 * Returns the meta object for the containment reference '{@link hu.bme.mit.gamma.environment.analysis.AnalysisComponent#getAnalysismethod <em>Analysismethod</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Analysismethod</em>'.
	 * @see hu.bme.mit.gamma.environment.analysis.AnalysisComponent#getAnalysismethod()
	 * @see #getAnalysisComponent()
	 * @generated
	 */
	EReference getAnalysisComponent_Analysismethod();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.environment.analysis.AnalysisCondition <em>Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Condition</em>'.
	 * @see hu.bme.mit.gamma.environment.analysis.AnalysisCondition
	 * @generated
	 */
	EClass getAnalysisCondition();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.environment.analysis.AssumeRaised <em>Assume Raised</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Assume Raised</em>'.
	 * @see hu.bme.mit.gamma.environment.analysis.AssumeRaised
	 * @generated
	 */
	EClass getAssumeRaised();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.environment.analysis.AssumeNotRaised <em>Assume Not Raised</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Assume Not Raised</em>'.
	 * @see hu.bme.mit.gamma.environment.analysis.AssumeNotRaised
	 * @generated
	 */
	EClass getAssumeNotRaised();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.environment.analysis.AnalysisAspect <em>Aspect</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Aspect</em>'.
	 * @see hu.bme.mit.gamma.environment.analysis.AnalysisAspect
	 * @generated
	 */
	EClass getAnalysisAspect();

	/**
	 * Returns the meta object for the containment reference '{@link hu.bme.mit.gamma.environment.analysis.AnalysisAspect#getEvent <em>Event</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Event</em>'.
	 * @see hu.bme.mit.gamma.environment.analysis.AnalysisAspect#getEvent()
	 * @see #getAnalysisAspect()
	 * @generated
	 */
	EReference getAnalysisAspect_Event();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.environment.analysis.Probability <em>Probability</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Probability</em>'.
	 * @see hu.bme.mit.gamma.environment.analysis.Probability
	 * @generated
	 */
	EClass getProbability();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.environment.analysis.TimedProbability <em>Timed Probability</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Timed Probability</em>'.
	 * @see hu.bme.mit.gamma.environment.analysis.TimedProbability
	 * @generated
	 */
	EClass getTimedProbability();

	/**
	 * Returns the meta object for the attribute '{@link hu.bme.mit.gamma.environment.analysis.TimedProbability#getTimeLimit <em>Time Limit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Time Limit</em>'.
	 * @see hu.bme.mit.gamma.environment.analysis.TimedProbability#getTimeLimit()
	 * @see #getTimedProbability()
	 * @generated
	 */
	EAttribute getTimedProbability_TimeLimit();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.environment.analysis.MeanTime <em>Mean Time</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Mean Time</em>'.
	 * @see hu.bme.mit.gamma.environment.analysis.MeanTime
	 * @generated
	 */
	EClass getMeanTime();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.environment.analysis.TimeBoundedProbability <em>Time Bounded Probability</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Time Bounded Probability</em>'.
	 * @see hu.bme.mit.gamma.environment.analysis.TimeBoundedProbability
	 * @generated
	 */
	EClass getTimeBoundedProbability();

	/**
	 * Returns the meta object for the attribute '{@link hu.bme.mit.gamma.environment.analysis.TimeBoundedProbability#getLowerBound <em>Lower Bound</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Lower Bound</em>'.
	 * @see hu.bme.mit.gamma.environment.analysis.TimeBoundedProbability#getLowerBound()
	 * @see #getTimeBoundedProbability()
	 * @generated
	 */
	EAttribute getTimeBoundedProbability_LowerBound();

	/**
	 * Returns the meta object for the attribute '{@link hu.bme.mit.gamma.environment.analysis.TimeBoundedProbability#getUpperBound <em>Upper Bound</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Upper Bound</em>'.
	 * @see hu.bme.mit.gamma.environment.analysis.TimeBoundedProbability#getUpperBound()
	 * @see #getTimeBoundedProbability()
	 * @generated
	 */
	EAttribute getTimeBoundedProbability_UpperBound();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.environment.analysis.RequirementComponent <em>Requirement Component</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Requirement Component</em>'.
	 * @see hu.bme.mit.gamma.environment.analysis.RequirementComponent
	 * @generated
	 */
	EClass getRequirementComponent();

	/**
	 * Returns the meta object for the containment reference list '{@link hu.bme.mit.gamma.environment.analysis.RequirementComponent#getRequirement <em>Requirement</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Requirement</em>'.
	 * @see hu.bme.mit.gamma.environment.analysis.RequirementComponent#getRequirement()
	 * @see #getRequirementComponent()
	 * @generated
	 */
	EReference getRequirementComponent_Requirement();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.environment.analysis.RequirementAspect <em>Requirement Aspect</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Requirement Aspect</em>'.
	 * @see hu.bme.mit.gamma.environment.analysis.RequirementAspect
	 * @generated
	 */
	EClass getRequirementAspect();

	/**
	 * Returns the meta object for the attribute '{@link hu.bme.mit.gamma.environment.analysis.RequirementAspect#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see hu.bme.mit.gamma.environment.analysis.RequirementAspect#getDescription()
	 * @see #getRequirementAspect()
	 * @generated
	 */
	EAttribute getRequirementAspect_Description();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.environment.analysis.LowerThan <em>Lower Than</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Lower Than</em>'.
	 * @see hu.bme.mit.gamma.environment.analysis.LowerThan
	 * @generated
	 */
	EClass getLowerThan();

	/**
	 * Returns the meta object for the attribute '{@link hu.bme.mit.gamma.environment.analysis.LowerThan#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see hu.bme.mit.gamma.environment.analysis.LowerThan#getValue()
	 * @see #getLowerThan()
	 * @generated
	 */
	EAttribute getLowerThan_Value();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.environment.analysis.GreaterThan <em>Greater Than</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Greater Than</em>'.
	 * @see hu.bme.mit.gamma.environment.analysis.GreaterThan
	 * @generated
	 */
	EClass getGreaterThan();

	/**
	 * Returns the meta object for the attribute '{@link hu.bme.mit.gamma.environment.analysis.GreaterThan#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see hu.bme.mit.gamma.environment.analysis.GreaterThan#getValue()
	 * @see #getGreaterThan()
	 * @generated
	 */
	EAttribute getGreaterThan_Value();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.environment.analysis.IsBetween <em>Is Between</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Is Between</em>'.
	 * @see hu.bme.mit.gamma.environment.analysis.IsBetween
	 * @generated
	 */
	EClass getIsBetween();

	/**
	 * Returns the meta object for the attribute '{@link hu.bme.mit.gamma.environment.analysis.IsBetween#getLowerBound <em>Lower Bound</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Lower Bound</em>'.
	 * @see hu.bme.mit.gamma.environment.analysis.IsBetween#getLowerBound()
	 * @see #getIsBetween()
	 * @generated
	 */
	EAttribute getIsBetween_LowerBound();

	/**
	 * Returns the meta object for the attribute '{@link hu.bme.mit.gamma.environment.analysis.IsBetween#getUpperBound <em>Upper Bound</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Upper Bound</em>'.
	 * @see hu.bme.mit.gamma.environment.analysis.IsBetween#getUpperBound()
	 * @see #getIsBetween()
	 * @generated
	 */
	EAttribute getIsBetween_UpperBound();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.environment.analysis.ComponentPortEventReference <em>Component Port Event Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Component Port Event Reference</em>'.
	 * @see hu.bme.mit.gamma.environment.analysis.ComponentPortEventReference
	 * @generated
	 */
	EClass getComponentPortEventReference();

	/**
	 * Returns the meta object for the reference '{@link hu.bme.mit.gamma.environment.analysis.ComponentPortEventReference#getPort <em>Port</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Port</em>'.
	 * @see hu.bme.mit.gamma.environment.analysis.ComponentPortEventReference#getPort()
	 * @see #getComponentPortEventReference()
	 * @generated
	 */
	EReference getComponentPortEventReference_Port();

	/**
	 * Returns the meta object for the reference '{@link hu.bme.mit.gamma.environment.analysis.ComponentPortEventReference#getEvent <em>Event</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Event</em>'.
	 * @see hu.bme.mit.gamma.environment.analysis.ComponentPortEventReference#getEvent()
	 * @see #getComponentPortEventReference()
	 * @generated
	 */
	EReference getComponentPortEventReference_Event();

	/**
	 * Returns the meta object for the containment reference '{@link hu.bme.mit.gamma.environment.analysis.ComponentPortEventReference#getComponent <em>Component</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Component</em>'.
	 * @see hu.bme.mit.gamma.environment.analysis.ComponentPortEventReference#getComponent()
	 * @see #getComponentPortEventReference()
	 * @generated
	 */
	EReference getComponentPortEventReference_Component();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.environment.analysis.Frequency <em>Frequency</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Frequency</em>'.
	 * @see hu.bme.mit.gamma.environment.analysis.Frequency
	 * @generated
	 */
	EClass getFrequency();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.environment.analysis.MeanParameter <em>Mean Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Mean Parameter</em>'.
	 * @see hu.bme.mit.gamma.environment.analysis.MeanParameter
	 * @generated
	 */
	EClass getMeanParameter();

	/**
	 * Returns the meta object for the reference '{@link hu.bme.mit.gamma.environment.analysis.MeanParameter#getParameter <em>Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Parameter</em>'.
	 * @see hu.bme.mit.gamma.environment.analysis.MeanParameter#getParameter()
	 * @see #getMeanParameter()
	 * @generated
	 */
	EReference getMeanParameter_Parameter();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.environment.analysis.ParameterDistribution <em>Parameter Distribution</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Parameter Distribution</em>'.
	 * @see hu.bme.mit.gamma.environment.analysis.ParameterDistribution
	 * @generated
	 */
	EClass getParameterDistribution();

	/**
	 * Returns the meta object for the reference '{@link hu.bme.mit.gamma.environment.analysis.ParameterDistribution#getParameter <em>Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Parameter</em>'.
	 * @see hu.bme.mit.gamma.environment.analysis.ParameterDistribution#getParameter()
	 * @see #getParameterDistribution()
	 * @generated
	 */
	EReference getParameterDistribution_Parameter();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.environment.analysis.RecursiveComponentReference <em>Recursive Component Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Recursive Component Reference</em>'.
	 * @see hu.bme.mit.gamma.environment.analysis.RecursiveComponentReference
	 * @generated
	 */
	EClass getRecursiveComponentReference();

	/**
	 * Returns the meta object for the reference '{@link hu.bme.mit.gamma.environment.analysis.RecursiveComponentReference#getComponent <em>Component</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Component</em>'.
	 * @see hu.bme.mit.gamma.environment.analysis.RecursiveComponentReference#getComponent()
	 * @see #getRecursiveComponentReference()
	 * @generated
	 */
	EReference getRecursiveComponentReference_Component();

	/**
	 * Returns the meta object for the containment reference '{@link hu.bme.mit.gamma.environment.analysis.RecursiveComponentReference#getRecursivecomponentreference <em>Recursivecomponentreference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Recursivecomponentreference</em>'.
	 * @see hu.bme.mit.gamma.environment.analysis.RecursiveComponentReference#getRecursivecomponentreference()
	 * @see #getRecursiveComponentReference()
	 * @generated
	 */
	EReference getRecursiveComponentReference_Recursivecomponentreference();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.environment.analysis.MeanTimeBetweenEvents <em>Mean Time Between Events</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Mean Time Between Events</em>'.
	 * @see hu.bme.mit.gamma.environment.analysis.MeanTimeBetweenEvents
	 * @generated
	 */
	EClass getMeanTimeBetweenEvents();

	/**
	 * Returns the meta object for the containment reference '{@link hu.bme.mit.gamma.environment.analysis.MeanTimeBetweenEvents#getEvent2 <em>Event2</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Event2</em>'.
	 * @see hu.bme.mit.gamma.environment.analysis.MeanTimeBetweenEvents#getEvent2()
	 * @see #getMeanTimeBetweenEvents()
	 * @generated
	 */
	EReference getMeanTimeBetweenEvents_Event2();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.environment.analysis.EventTimeRatio <em>Event Time Ratio</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Event Time Ratio</em>'.
	 * @see hu.bme.mit.gamma.environment.analysis.EventTimeRatio
	 * @generated
	 */
	EClass getEventTimeRatio();

	/**
	 * Returns the meta object for the containment reference '{@link hu.bme.mit.gamma.environment.analysis.EventTimeRatio#getEvent2 <em>Event2</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Event2</em>'.
	 * @see hu.bme.mit.gamma.environment.analysis.EventTimeRatio#getEvent2()
	 * @see #getEventTimeRatio()
	 * @generated
	 */
	EReference getEventTimeRatio_Event2();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.environment.analysis.ObserveParameter <em>Observe Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Observe Parameter</em>'.
	 * @see hu.bme.mit.gamma.environment.analysis.ObserveParameter
	 * @generated
	 */
	EClass getObserveParameter();

	/**
	 * Returns the meta object for the reference '{@link hu.bme.mit.gamma.environment.analysis.ObserveParameter#getParameter <em>Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Parameter</em>'.
	 * @see hu.bme.mit.gamma.environment.analysis.ObserveParameter#getParameter()
	 * @see #getObserveParameter()
	 * @generated
	 */
	EReference getObserveParameter_Parameter();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.environment.analysis.PrioryDistribution <em>Priory Distribution</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Priory Distribution</em>'.
	 * @see hu.bme.mit.gamma.environment.analysis.PrioryDistribution
	 * @generated
	 */
	EClass getPrioryDistribution();

	/**
	 * Returns the meta object for the containment reference '{@link hu.bme.mit.gamma.environment.analysis.PrioryDistribution#getRandomvariable <em>Randomvariable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Randomvariable</em>'.
	 * @see hu.bme.mit.gamma.environment.analysis.PrioryDistribution#getRandomvariable()
	 * @see #getPrioryDistribution()
	 * @generated
	 */
	EReference getPrioryDistribution_Randomvariable();

	/**
	 * Returns the meta object for the reference '{@link hu.bme.mit.gamma.environment.analysis.PrioryDistribution#getParameter <em>Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Parameter</em>'.
	 * @see hu.bme.mit.gamma.environment.analysis.PrioryDistribution#getParameter()
	 * @see #getPrioryDistribution()
	 * @generated
	 */
	EReference getPrioryDistribution_Parameter();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.environment.analysis.ObserveCondition <em>Observe Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Observe Condition</em>'.
	 * @see hu.bme.mit.gamma.environment.analysis.ObserveCondition
	 * @generated
	 */
	EClass getObserveCondition();

	/**
	 * Returns the meta object for the containment reference '{@link hu.bme.mit.gamma.environment.analysis.ObserveCondition#getRandomvariable <em>Randomvariable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Randomvariable</em>'.
	 * @see hu.bme.mit.gamma.environment.analysis.ObserveCondition#getRandomvariable()
	 * @see #getObserveCondition()
	 * @generated
	 */
	EReference getObserveCondition_Randomvariable();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.environment.analysis.AssumeCondition <em>Assume Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Assume Condition</em>'.
	 * @see hu.bme.mit.gamma.environment.analysis.AssumeCondition
	 * @generated
	 */
	EClass getAssumeCondition();

	/**
	 * Returns the meta object for the containment reference '{@link hu.bme.mit.gamma.environment.analysis.AssumeCondition#getProbability <em>Probability</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Probability</em>'.
	 * @see hu.bme.mit.gamma.environment.analysis.AssumeCondition#getProbability()
	 * @see #getAssumeCondition()
	 * @generated
	 */
	EReference getAssumeCondition_Probability();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.environment.analysis.ObserveTime <em>Observe Time</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Observe Time</em>'.
	 * @see hu.bme.mit.gamma.environment.analysis.ObserveTime
	 * @generated
	 */
	EClass getObserveTime();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.environment.analysis.EndCondition <em>End Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>End Condition</em>'.
	 * @see hu.bme.mit.gamma.environment.analysis.EndCondition
	 * @generated
	 */
	EClass getEndCondition();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.environment.analysis.AnalysisMethod <em>Method</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Method</em>'.
	 * @see hu.bme.mit.gamma.environment.analysis.AnalysisMethod
	 * @generated
	 */
	EClass getAnalysisMethod();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.environment.analysis.ExactAnalysisMethod <em>Exact Analysis Method</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Exact Analysis Method</em>'.
	 * @see hu.bme.mit.gamma.environment.analysis.ExactAnalysisMethod
	 * @generated
	 */
	EClass getExactAnalysisMethod();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.environment.analysis.SimulationAnalysisMethod <em>Simulation Analysis Method</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Simulation Analysis Method</em>'.
	 * @see hu.bme.mit.gamma.environment.analysis.SimulationAnalysisMethod
	 * @generated
	 */
	EClass getSimulationAnalysisMethod();

	/**
	 * Returns the meta object for the containment reference list '{@link hu.bme.mit.gamma.environment.analysis.SimulationAnalysisMethod#getEndcondition <em>Endcondition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Endcondition</em>'.
	 * @see hu.bme.mit.gamma.environment.analysis.SimulationAnalysisMethod#getEndcondition()
	 * @see #getSimulationAnalysisMethod()
	 * @generated
	 */
	EReference getSimulationAnalysisMethod_Endcondition();

	/**
	 * Returns the meta object for the containment reference '{@link hu.bme.mit.gamma.environment.analysis.SimulationAnalysisMethod#getWarmupTime <em>Warmup Time</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Warmup Time</em>'.
	 * @see hu.bme.mit.gamma.environment.analysis.SimulationAnalysisMethod#getWarmupTime()
	 * @see #getSimulationAnalysisMethod()
	 * @generated
	 */
	EReference getSimulationAnalysisMethod_WarmupTime();

	/**
	 * Returns the meta object for the containment reference '{@link hu.bme.mit.gamma.environment.analysis.SimulationAnalysisMethod#getSimulationTime <em>Simulation Time</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Simulation Time</em>'.
	 * @see hu.bme.mit.gamma.environment.analysis.SimulationAnalysisMethod#getSimulationTime()
	 * @see #getSimulationAnalysisMethod()
	 * @generated
	 */
	EReference getSimulationAnalysisMethod_SimulationTime();

	/**
	 * Returns the meta object for the containment reference '{@link hu.bme.mit.gamma.environment.analysis.SimulationAnalysisMethod#getSamplingBatchSize <em>Sampling Batch Size</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Sampling Batch Size</em>'.
	 * @see hu.bme.mit.gamma.environment.analysis.SimulationAnalysisMethod#getSamplingBatchSize()
	 * @see #getSimulationAnalysisMethod()
	 * @generated
	 */
	EReference getSimulationAnalysisMethod_SamplingBatchSize();

	/**
	 * Returns the meta object for the attribute '{@link hu.bme.mit.gamma.environment.analysis.SimulationAnalysisMethod#getJointSampling <em>Joint Sampling</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Joint Sampling</em>'.
	 * @see hu.bme.mit.gamma.environment.analysis.SimulationAnalysisMethod#getJointSampling()
	 * @see #getSimulationAnalysisMethod()
	 * @generated
	 */
	EAttribute getSimulationAnalysisMethod_JointSampling();

	/**
	 * Returns the meta object for the attribute '{@link hu.bme.mit.gamma.environment.analysis.SimulationAnalysisMethod#isDebug <em>Debug</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Debug</em>'.
	 * @see hu.bme.mit.gamma.environment.analysis.SimulationAnalysisMethod#isDebug()
	 * @see #getSimulationAnalysisMethod()
	 * @generated
	 */
	EAttribute getSimulationAnalysisMethod_Debug();

	/**
	 * Returns the meta object for the containment reference '{@link hu.bme.mit.gamma.environment.analysis.SimulationAnalysisMethod#getSimulationNumber <em>Simulation Number</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Simulation Number</em>'.
	 * @see hu.bme.mit.gamma.environment.analysis.SimulationAnalysisMethod#getSimulationNumber()
	 * @see #getSimulationAnalysisMethod()
	 * @generated
	 */
	EReference getSimulationAnalysisMethod_SimulationNumber();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.environment.analysis.ImportanceSampling <em>Importance Sampling</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Importance Sampling</em>'.
	 * @see hu.bme.mit.gamma.environment.analysis.ImportanceSampling
	 * @generated
	 */
	EClass getImportanceSampling();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.environment.analysis.MCMC <em>MCMC</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>MCMC</em>'.
	 * @see hu.bme.mit.gamma.environment.analysis.MCMC
	 * @generated
	 */
	EClass getMCMC();

	/**
	 * Returns the meta object for the containment reference '{@link hu.bme.mit.gamma.environment.analysis.MCMC#getKernel <em>Kernel</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Kernel</em>'.
	 * @see hu.bme.mit.gamma.environment.analysis.MCMC#getKernel()
	 * @see #getMCMC()
	 * @generated
	 */
	EReference getMCMC_Kernel();

	/**
	 * Returns the meta object for the containment reference '{@link hu.bme.mit.gamma.environment.analysis.MCMC#getWarmupStepNum <em>Warmup Step Num</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Warmup Step Num</em>'.
	 * @see hu.bme.mit.gamma.environment.analysis.MCMC#getWarmupStepNum()
	 * @see #getMCMC()
	 * @generated
	 */
	EReference getMCMC_WarmupStepNum();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.environment.analysis.SVI <em>SVI</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>SVI</em>'.
	 * @see hu.bme.mit.gamma.environment.analysis.SVI
	 * @generated
	 */
	EClass getSVI();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.environment.analysis.MCMCKernel <em>MCMC Kernel</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>MCMC Kernel</em>'.
	 * @see hu.bme.mit.gamma.environment.analysis.MCMCKernel
	 * @generated
	 */
	EClass getMCMCKernel();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.environment.analysis.NUTS <em>NUTS</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>NUTS</em>'.
	 * @see hu.bme.mit.gamma.environment.analysis.NUTS
	 * @generated
	 */
	EClass getNUTS();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.environment.analysis.HMC <em>HMC</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>HMC</em>'.
	 * @see hu.bme.mit.gamma.environment.analysis.HMC
	 * @generated
	 */
	EClass getHMC();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.environment.analysis.Simulation <em>Simulation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Simulation</em>'.
	 * @see hu.bme.mit.gamma.environment.analysis.Simulation
	 * @generated
	 */
	EClass getSimulation();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	AnalysisFactory getAnalysisFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.environment.analysis.impl.AnalysisComponentImpl <em>Component</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.environment.analysis.impl.AnalysisComponentImpl
		 * @see hu.bme.mit.gamma.environment.analysis.impl.AnalysisPackageImpl#getAnalysisComponent()
		 * @generated
		 */
		EClass ANALYSIS_COMPONENT = eINSTANCE.getAnalysisComponent();

		/**
		 * The meta object literal for the '<em><b>Analyzed Component</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ANALYSIS_COMPONENT__ANALYZED_COMPONENT = eINSTANCE.getAnalysisComponent_AnalyzedComponent();

		/**
		 * The meta object literal for the '<em><b>Conditions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ANALYSIS_COMPONENT__CONDITIONS = eINSTANCE.getAnalysisComponent_Conditions();

		/**
		 * The meta object literal for the '<em><b>Aspect</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ANALYSIS_COMPONENT__ASPECT = eINSTANCE.getAnalysisComponent_Aspect();

		/**
		 * The meta object literal for the '<em><b>Priorydistribution</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ANALYSIS_COMPONENT__PRIORYDISTRIBUTION = eINSTANCE.getAnalysisComponent_Priorydistribution();

		/**
		 * The meta object literal for the '<em><b>Analysismethod</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ANALYSIS_COMPONENT__ANALYSISMETHOD = eINSTANCE.getAnalysisComponent_Analysismethod();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.environment.analysis.impl.AnalysisConditionImpl <em>Condition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.environment.analysis.impl.AnalysisConditionImpl
		 * @see hu.bme.mit.gamma.environment.analysis.impl.AnalysisPackageImpl#getAnalysisCondition()
		 * @generated
		 */
		EClass ANALYSIS_CONDITION = eINSTANCE.getAnalysisCondition();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.environment.analysis.impl.AssumeRaisedImpl <em>Assume Raised</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.environment.analysis.impl.AssumeRaisedImpl
		 * @see hu.bme.mit.gamma.environment.analysis.impl.AnalysisPackageImpl#getAssumeRaised()
		 * @generated
		 */
		EClass ASSUME_RAISED = eINSTANCE.getAssumeRaised();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.environment.analysis.impl.AssumeNotRaisedImpl <em>Assume Not Raised</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.environment.analysis.impl.AssumeNotRaisedImpl
		 * @see hu.bme.mit.gamma.environment.analysis.impl.AnalysisPackageImpl#getAssumeNotRaised()
		 * @generated
		 */
		EClass ASSUME_NOT_RAISED = eINSTANCE.getAssumeNotRaised();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.environment.analysis.impl.AnalysisAspectImpl <em>Aspect</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.environment.analysis.impl.AnalysisAspectImpl
		 * @see hu.bme.mit.gamma.environment.analysis.impl.AnalysisPackageImpl#getAnalysisAspect()
		 * @generated
		 */
		EClass ANALYSIS_ASPECT = eINSTANCE.getAnalysisAspect();

		/**
		 * The meta object literal for the '<em><b>Event</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ANALYSIS_ASPECT__EVENT = eINSTANCE.getAnalysisAspect_Event();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.environment.analysis.impl.ProbabilityImpl <em>Probability</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.environment.analysis.impl.ProbabilityImpl
		 * @see hu.bme.mit.gamma.environment.analysis.impl.AnalysisPackageImpl#getProbability()
		 * @generated
		 */
		EClass PROBABILITY = eINSTANCE.getProbability();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.environment.analysis.impl.TimedProbabilityImpl <em>Timed Probability</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.environment.analysis.impl.TimedProbabilityImpl
		 * @see hu.bme.mit.gamma.environment.analysis.impl.AnalysisPackageImpl#getTimedProbability()
		 * @generated
		 */
		EClass TIMED_PROBABILITY = eINSTANCE.getTimedProbability();

		/**
		 * The meta object literal for the '<em><b>Time Limit</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TIMED_PROBABILITY__TIME_LIMIT = eINSTANCE.getTimedProbability_TimeLimit();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.environment.analysis.impl.MeanTimeImpl <em>Mean Time</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.environment.analysis.impl.MeanTimeImpl
		 * @see hu.bme.mit.gamma.environment.analysis.impl.AnalysisPackageImpl#getMeanTime()
		 * @generated
		 */
		EClass MEAN_TIME = eINSTANCE.getMeanTime();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.environment.analysis.impl.TimeBoundedProbabilityImpl <em>Time Bounded Probability</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.environment.analysis.impl.TimeBoundedProbabilityImpl
		 * @see hu.bme.mit.gamma.environment.analysis.impl.AnalysisPackageImpl#getTimeBoundedProbability()
		 * @generated
		 */
		EClass TIME_BOUNDED_PROBABILITY = eINSTANCE.getTimeBoundedProbability();

		/**
		 * The meta object literal for the '<em><b>Lower Bound</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TIME_BOUNDED_PROBABILITY__LOWER_BOUND = eINSTANCE.getTimeBoundedProbability_LowerBound();

		/**
		 * The meta object literal for the '<em><b>Upper Bound</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TIME_BOUNDED_PROBABILITY__UPPER_BOUND = eINSTANCE.getTimeBoundedProbability_UpperBound();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.environment.analysis.impl.RequirementComponentImpl <em>Requirement Component</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.environment.analysis.impl.RequirementComponentImpl
		 * @see hu.bme.mit.gamma.environment.analysis.impl.AnalysisPackageImpl#getRequirementComponent()
		 * @generated
		 */
		EClass REQUIREMENT_COMPONENT = eINSTANCE.getRequirementComponent();

		/**
		 * The meta object literal for the '<em><b>Requirement</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REQUIREMENT_COMPONENT__REQUIREMENT = eINSTANCE.getRequirementComponent_Requirement();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.environment.analysis.impl.RequirementAspectImpl <em>Requirement Aspect</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.environment.analysis.impl.RequirementAspectImpl
		 * @see hu.bme.mit.gamma.environment.analysis.impl.AnalysisPackageImpl#getRequirementAspect()
		 * @generated
		 */
		EClass REQUIREMENT_ASPECT = eINSTANCE.getRequirementAspect();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REQUIREMENT_ASPECT__DESCRIPTION = eINSTANCE.getRequirementAspect_Description();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.environment.analysis.impl.LowerThanImpl <em>Lower Than</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.environment.analysis.impl.LowerThanImpl
		 * @see hu.bme.mit.gamma.environment.analysis.impl.AnalysisPackageImpl#getLowerThan()
		 * @generated
		 */
		EClass LOWER_THAN = eINSTANCE.getLowerThan();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LOWER_THAN__VALUE = eINSTANCE.getLowerThan_Value();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.environment.analysis.impl.GreaterThanImpl <em>Greater Than</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.environment.analysis.impl.GreaterThanImpl
		 * @see hu.bme.mit.gamma.environment.analysis.impl.AnalysisPackageImpl#getGreaterThan()
		 * @generated
		 */
		EClass GREATER_THAN = eINSTANCE.getGreaterThan();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GREATER_THAN__VALUE = eINSTANCE.getGreaterThan_Value();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.environment.analysis.impl.IsBetweenImpl <em>Is Between</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.environment.analysis.impl.IsBetweenImpl
		 * @see hu.bme.mit.gamma.environment.analysis.impl.AnalysisPackageImpl#getIsBetween()
		 * @generated
		 */
		EClass IS_BETWEEN = eINSTANCE.getIsBetween();

		/**
		 * The meta object literal for the '<em><b>Lower Bound</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IS_BETWEEN__LOWER_BOUND = eINSTANCE.getIsBetween_LowerBound();

		/**
		 * The meta object literal for the '<em><b>Upper Bound</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IS_BETWEEN__UPPER_BOUND = eINSTANCE.getIsBetween_UpperBound();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.environment.analysis.impl.ComponentPortEventReferenceImpl <em>Component Port Event Reference</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.environment.analysis.impl.ComponentPortEventReferenceImpl
		 * @see hu.bme.mit.gamma.environment.analysis.impl.AnalysisPackageImpl#getComponentPortEventReference()
		 * @generated
		 */
		EClass COMPONENT_PORT_EVENT_REFERENCE = eINSTANCE.getComponentPortEventReference();

		/**
		 * The meta object literal for the '<em><b>Port</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPONENT_PORT_EVENT_REFERENCE__PORT = eINSTANCE.getComponentPortEventReference_Port();

		/**
		 * The meta object literal for the '<em><b>Event</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPONENT_PORT_EVENT_REFERENCE__EVENT = eINSTANCE.getComponentPortEventReference_Event();

		/**
		 * The meta object literal for the '<em><b>Component</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPONENT_PORT_EVENT_REFERENCE__COMPONENT = eINSTANCE.getComponentPortEventReference_Component();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.environment.analysis.impl.FrequencyImpl <em>Frequency</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.environment.analysis.impl.FrequencyImpl
		 * @see hu.bme.mit.gamma.environment.analysis.impl.AnalysisPackageImpl#getFrequency()
		 * @generated
		 */
		EClass FREQUENCY = eINSTANCE.getFrequency();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.environment.analysis.impl.MeanParameterImpl <em>Mean Parameter</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.environment.analysis.impl.MeanParameterImpl
		 * @see hu.bme.mit.gamma.environment.analysis.impl.AnalysisPackageImpl#getMeanParameter()
		 * @generated
		 */
		EClass MEAN_PARAMETER = eINSTANCE.getMeanParameter();

		/**
		 * The meta object literal for the '<em><b>Parameter</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MEAN_PARAMETER__PARAMETER = eINSTANCE.getMeanParameter_Parameter();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.environment.analysis.impl.ParameterDistributionImpl <em>Parameter Distribution</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.environment.analysis.impl.ParameterDistributionImpl
		 * @see hu.bme.mit.gamma.environment.analysis.impl.AnalysisPackageImpl#getParameterDistribution()
		 * @generated
		 */
		EClass PARAMETER_DISTRIBUTION = eINSTANCE.getParameterDistribution();

		/**
		 * The meta object literal for the '<em><b>Parameter</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PARAMETER_DISTRIBUTION__PARAMETER = eINSTANCE.getParameterDistribution_Parameter();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.environment.analysis.impl.RecursiveComponentReferenceImpl <em>Recursive Component Reference</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.environment.analysis.impl.RecursiveComponentReferenceImpl
		 * @see hu.bme.mit.gamma.environment.analysis.impl.AnalysisPackageImpl#getRecursiveComponentReference()
		 * @generated
		 */
		EClass RECURSIVE_COMPONENT_REFERENCE = eINSTANCE.getRecursiveComponentReference();

		/**
		 * The meta object literal for the '<em><b>Component</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RECURSIVE_COMPONENT_REFERENCE__COMPONENT = eINSTANCE.getRecursiveComponentReference_Component();

		/**
		 * The meta object literal for the '<em><b>Recursivecomponentreference</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RECURSIVE_COMPONENT_REFERENCE__RECURSIVECOMPONENTREFERENCE = eINSTANCE.getRecursiveComponentReference_Recursivecomponentreference();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.environment.analysis.impl.MeanTimeBetweenEventsImpl <em>Mean Time Between Events</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.environment.analysis.impl.MeanTimeBetweenEventsImpl
		 * @see hu.bme.mit.gamma.environment.analysis.impl.AnalysisPackageImpl#getMeanTimeBetweenEvents()
		 * @generated
		 */
		EClass MEAN_TIME_BETWEEN_EVENTS = eINSTANCE.getMeanTimeBetweenEvents();

		/**
		 * The meta object literal for the '<em><b>Event2</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MEAN_TIME_BETWEEN_EVENTS__EVENT2 = eINSTANCE.getMeanTimeBetweenEvents_Event2();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.environment.analysis.impl.EventTimeRatioImpl <em>Event Time Ratio</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.environment.analysis.impl.EventTimeRatioImpl
		 * @see hu.bme.mit.gamma.environment.analysis.impl.AnalysisPackageImpl#getEventTimeRatio()
		 * @generated
		 */
		EClass EVENT_TIME_RATIO = eINSTANCE.getEventTimeRatio();

		/**
		 * The meta object literal for the '<em><b>Event2</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EVENT_TIME_RATIO__EVENT2 = eINSTANCE.getEventTimeRatio_Event2();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.environment.analysis.impl.ObserveParameterImpl <em>Observe Parameter</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.environment.analysis.impl.ObserveParameterImpl
		 * @see hu.bme.mit.gamma.environment.analysis.impl.AnalysisPackageImpl#getObserveParameter()
		 * @generated
		 */
		EClass OBSERVE_PARAMETER = eINSTANCE.getObserveParameter();

		/**
		 * The meta object literal for the '<em><b>Parameter</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OBSERVE_PARAMETER__PARAMETER = eINSTANCE.getObserveParameter_Parameter();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.environment.analysis.impl.PrioryDistributionImpl <em>Priory Distribution</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.environment.analysis.impl.PrioryDistributionImpl
		 * @see hu.bme.mit.gamma.environment.analysis.impl.AnalysisPackageImpl#getPrioryDistribution()
		 * @generated
		 */
		EClass PRIORY_DISTRIBUTION = eINSTANCE.getPrioryDistribution();

		/**
		 * The meta object literal for the '<em><b>Randomvariable</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PRIORY_DISTRIBUTION__RANDOMVARIABLE = eINSTANCE.getPrioryDistribution_Randomvariable();

		/**
		 * The meta object literal for the '<em><b>Parameter</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PRIORY_DISTRIBUTION__PARAMETER = eINSTANCE.getPrioryDistribution_Parameter();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.environment.analysis.impl.ObserveConditionImpl <em>Observe Condition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.environment.analysis.impl.ObserveConditionImpl
		 * @see hu.bme.mit.gamma.environment.analysis.impl.AnalysisPackageImpl#getObserveCondition()
		 * @generated
		 */
		EClass OBSERVE_CONDITION = eINSTANCE.getObserveCondition();

		/**
		 * The meta object literal for the '<em><b>Randomvariable</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OBSERVE_CONDITION__RANDOMVARIABLE = eINSTANCE.getObserveCondition_Randomvariable();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.environment.analysis.impl.AssumeConditionImpl <em>Assume Condition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.environment.analysis.impl.AssumeConditionImpl
		 * @see hu.bme.mit.gamma.environment.analysis.impl.AnalysisPackageImpl#getAssumeCondition()
		 * @generated
		 */
		EClass ASSUME_CONDITION = eINSTANCE.getAssumeCondition();

		/**
		 * The meta object literal for the '<em><b>Probability</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ASSUME_CONDITION__PROBABILITY = eINSTANCE.getAssumeCondition_Probability();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.environment.analysis.impl.ObserveTimeImpl <em>Observe Time</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.environment.analysis.impl.ObserveTimeImpl
		 * @see hu.bme.mit.gamma.environment.analysis.impl.AnalysisPackageImpl#getObserveTime()
		 * @generated
		 */
		EClass OBSERVE_TIME = eINSTANCE.getObserveTime();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.environment.analysis.impl.EndConditionImpl <em>End Condition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.environment.analysis.impl.EndConditionImpl
		 * @see hu.bme.mit.gamma.environment.analysis.impl.AnalysisPackageImpl#getEndCondition()
		 * @generated
		 */
		EClass END_CONDITION = eINSTANCE.getEndCondition();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.environment.analysis.impl.AnalysisMethodImpl <em>Method</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.environment.analysis.impl.AnalysisMethodImpl
		 * @see hu.bme.mit.gamma.environment.analysis.impl.AnalysisPackageImpl#getAnalysisMethod()
		 * @generated
		 */
		EClass ANALYSIS_METHOD = eINSTANCE.getAnalysisMethod();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.environment.analysis.impl.ExactAnalysisMethodImpl <em>Exact Analysis Method</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.environment.analysis.impl.ExactAnalysisMethodImpl
		 * @see hu.bme.mit.gamma.environment.analysis.impl.AnalysisPackageImpl#getExactAnalysisMethod()
		 * @generated
		 */
		EClass EXACT_ANALYSIS_METHOD = eINSTANCE.getExactAnalysisMethod();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.environment.analysis.impl.SimulationAnalysisMethodImpl <em>Simulation Analysis Method</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.environment.analysis.impl.SimulationAnalysisMethodImpl
		 * @see hu.bme.mit.gamma.environment.analysis.impl.AnalysisPackageImpl#getSimulationAnalysisMethod()
		 * @generated
		 */
		EClass SIMULATION_ANALYSIS_METHOD = eINSTANCE.getSimulationAnalysisMethod();

		/**
		 * The meta object literal for the '<em><b>Endcondition</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SIMULATION_ANALYSIS_METHOD__ENDCONDITION = eINSTANCE.getSimulationAnalysisMethod_Endcondition();

		/**
		 * The meta object literal for the '<em><b>Warmup Time</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SIMULATION_ANALYSIS_METHOD__WARMUP_TIME = eINSTANCE.getSimulationAnalysisMethod_WarmupTime();

		/**
		 * The meta object literal for the '<em><b>Simulation Time</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SIMULATION_ANALYSIS_METHOD__SIMULATION_TIME = eINSTANCE.getSimulationAnalysisMethod_SimulationTime();

		/**
		 * The meta object literal for the '<em><b>Sampling Batch Size</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SIMULATION_ANALYSIS_METHOD__SAMPLING_BATCH_SIZE = eINSTANCE.getSimulationAnalysisMethod_SamplingBatchSize();

		/**
		 * The meta object literal for the '<em><b>Joint Sampling</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SIMULATION_ANALYSIS_METHOD__JOINT_SAMPLING = eINSTANCE.getSimulationAnalysisMethod_JointSampling();

		/**
		 * The meta object literal for the '<em><b>Debug</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SIMULATION_ANALYSIS_METHOD__DEBUG = eINSTANCE.getSimulationAnalysisMethod_Debug();

		/**
		 * The meta object literal for the '<em><b>Simulation Number</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SIMULATION_ANALYSIS_METHOD__SIMULATION_NUMBER = eINSTANCE.getSimulationAnalysisMethod_SimulationNumber();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.environment.analysis.impl.ImportanceSamplingImpl <em>Importance Sampling</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.environment.analysis.impl.ImportanceSamplingImpl
		 * @see hu.bme.mit.gamma.environment.analysis.impl.AnalysisPackageImpl#getImportanceSampling()
		 * @generated
		 */
		EClass IMPORTANCE_SAMPLING = eINSTANCE.getImportanceSampling();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.environment.analysis.impl.MCMCImpl <em>MCMC</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.environment.analysis.impl.MCMCImpl
		 * @see hu.bme.mit.gamma.environment.analysis.impl.AnalysisPackageImpl#getMCMC()
		 * @generated
		 */
		EClass MCMC = eINSTANCE.getMCMC();

		/**
		 * The meta object literal for the '<em><b>Kernel</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MCMC__KERNEL = eINSTANCE.getMCMC_Kernel();

		/**
		 * The meta object literal for the '<em><b>Warmup Step Num</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MCMC__WARMUP_STEP_NUM = eINSTANCE.getMCMC_WarmupStepNum();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.environment.analysis.impl.SVIImpl <em>SVI</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.environment.analysis.impl.SVIImpl
		 * @see hu.bme.mit.gamma.environment.analysis.impl.AnalysisPackageImpl#getSVI()
		 * @generated
		 */
		EClass SVI = eINSTANCE.getSVI();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.environment.analysis.impl.MCMCKernelImpl <em>MCMC Kernel</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.environment.analysis.impl.MCMCKernelImpl
		 * @see hu.bme.mit.gamma.environment.analysis.impl.AnalysisPackageImpl#getMCMCKernel()
		 * @generated
		 */
		EClass MCMC_KERNEL = eINSTANCE.getMCMCKernel();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.environment.analysis.impl.NUTSImpl <em>NUTS</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.environment.analysis.impl.NUTSImpl
		 * @see hu.bme.mit.gamma.environment.analysis.impl.AnalysisPackageImpl#getNUTS()
		 * @generated
		 */
		EClass NUTS = eINSTANCE.getNUTS();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.environment.analysis.impl.HMCImpl <em>HMC</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.environment.analysis.impl.HMCImpl
		 * @see hu.bme.mit.gamma.environment.analysis.impl.AnalysisPackageImpl#getHMC()
		 * @generated
		 */
		EClass HMC = eINSTANCE.getHMC();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.environment.analysis.impl.SimulationImpl <em>Simulation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.environment.analysis.impl.SimulationImpl
		 * @see hu.bme.mit.gamma.environment.analysis.impl.AnalysisPackageImpl#getSimulation()
		 * @generated
		 */
		EClass SIMULATION = eINSTANCE.getSimulation();

	}

} //AnalysisPackage
