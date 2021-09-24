/**
 */
package hu.bme.mit.gamma.analysis;

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
 * @see hu.bme.mit.gamma.analysis.AnalysisFactory
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
	String eNS_PREFIX = "hu.bme.mit.gamma.analysis";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	AnalysisPackage eINSTANCE = hu.bme.mit.gamma.analysis.impl.AnalysisPackageImpl.init();

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.analysis.impl.AnalysisComponentImpl <em>Component</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.analysis.impl.AnalysisComponentImpl
	 * @see hu.bme.mit.gamma.analysis.impl.AnalysisPackageImpl#getAnalysisComponent()
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
	 * The feature id for the '<em><b>Aspect</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANALYSIS_COMPONENT__ASPECT = InterfaceModelPackage.COMPONENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Simulation Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANALYSIS_COMPONENT__SIMULATION_TIME = InterfaceModelPackage.COMPONENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Simulation Number</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANALYSIS_COMPONENT__SIMULATION_NUMBER = InterfaceModelPackage.COMPONENT_FEATURE_COUNT + 4;

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
	 * The meta object id for the '{@link hu.bme.mit.gamma.analysis.impl.AnalysisConditionImpl <em>Condition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.analysis.impl.AnalysisConditionImpl
	 * @see hu.bme.mit.gamma.analysis.impl.AnalysisPackageImpl#getAnalysisCondition()
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
	int ANALYSIS_CONDITION__EVENT = 0;

	/**
	 * The number of structural features of the '<em>Condition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANALYSIS_CONDITION_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Condition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANALYSIS_CONDITION_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.analysis.impl.AssumeRaisedImpl <em>Assume Raised</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.analysis.impl.AssumeRaisedImpl
	 * @see hu.bme.mit.gamma.analysis.impl.AnalysisPackageImpl#getAssumeRaised()
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
	int ASSUME_RAISED__EVENT = ANALYSIS_CONDITION__EVENT;

	/**
	 * The number of structural features of the '<em>Assume Raised</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSUME_RAISED_FEATURE_COUNT = ANALYSIS_CONDITION_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Assume Raised</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSUME_RAISED_OPERATION_COUNT = ANALYSIS_CONDITION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.analysis.impl.AssumeNotRaisedImpl <em>Assume Not Raised</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.analysis.impl.AssumeNotRaisedImpl
	 * @see hu.bme.mit.gamma.analysis.impl.AnalysisPackageImpl#getAssumeNotRaised()
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
	int ASSUME_NOT_RAISED__EVENT = ANALYSIS_CONDITION__EVENT;

	/**
	 * The number of structural features of the '<em>Assume Not Raised</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSUME_NOT_RAISED_FEATURE_COUNT = ANALYSIS_CONDITION_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Assume Not Raised</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSUME_NOT_RAISED_OPERATION_COUNT = ANALYSIS_CONDITION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.analysis.impl.AnalysisAspectImpl <em>Aspect</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.analysis.impl.AnalysisAspectImpl
	 * @see hu.bme.mit.gamma.analysis.impl.AnalysisPackageImpl#getAnalysisAspect()
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
	 * The meta object id for the '{@link hu.bme.mit.gamma.analysis.impl.ProbabilityImpl <em>Probability</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.analysis.impl.ProbabilityImpl
	 * @see hu.bme.mit.gamma.analysis.impl.AnalysisPackageImpl#getProbability()
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
	 * The meta object id for the '{@link hu.bme.mit.gamma.analysis.impl.TimedProbabilityImpl <em>Timed Probability</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.analysis.impl.TimedProbabilityImpl
	 * @see hu.bme.mit.gamma.analysis.impl.AnalysisPackageImpl#getTimedProbability()
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
	 * The meta object id for the '{@link hu.bme.mit.gamma.analysis.impl.MeanTimeImpl <em>Mean Time</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.analysis.impl.MeanTimeImpl
	 * @see hu.bme.mit.gamma.analysis.impl.AnalysisPackageImpl#getMeanTime()
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
	 * The meta object id for the '{@link hu.bme.mit.gamma.analysis.impl.TimeBoundedProbabilityImpl <em>Time Bounded Probability</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.analysis.impl.TimeBoundedProbabilityImpl
	 * @see hu.bme.mit.gamma.analysis.impl.AnalysisPackageImpl#getTimeBoundedProbability()
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
	 * The meta object id for the '{@link hu.bme.mit.gamma.analysis.impl.RequirementComponentImpl <em>Requirement Component</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.analysis.impl.RequirementComponentImpl
	 * @see hu.bme.mit.gamma.analysis.impl.AnalysisPackageImpl#getRequirementComponent()
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
	 * The feature id for the '<em><b>Aspect</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT_COMPONENT__ASPECT = ANALYSIS_COMPONENT__ASPECT;

	/**
	 * The feature id for the '<em><b>Simulation Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT_COMPONENT__SIMULATION_TIME = ANALYSIS_COMPONENT__SIMULATION_TIME;

	/**
	 * The feature id for the '<em><b>Simulation Number</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT_COMPONENT__SIMULATION_NUMBER = ANALYSIS_COMPONENT__SIMULATION_NUMBER;

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
	 * The meta object id for the '{@link hu.bme.mit.gamma.analysis.impl.RequirementAspectImpl <em>Requirement Aspect</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.analysis.impl.RequirementAspectImpl
	 * @see hu.bme.mit.gamma.analysis.impl.AnalysisPackageImpl#getRequirementAspect()
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
	 * The meta object id for the '{@link hu.bme.mit.gamma.analysis.impl.LowerThanImpl <em>Lower Than</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.analysis.impl.LowerThanImpl
	 * @see hu.bme.mit.gamma.analysis.impl.AnalysisPackageImpl#getLowerThan()
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
	 * The meta object id for the '{@link hu.bme.mit.gamma.analysis.impl.GreaterThanImpl <em>Greater Than</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.analysis.impl.GreaterThanImpl
	 * @see hu.bme.mit.gamma.analysis.impl.AnalysisPackageImpl#getGreaterThan()
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
	 * The meta object id for the '{@link hu.bme.mit.gamma.analysis.impl.IsBetweenImpl <em>Is Between</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.analysis.impl.IsBetweenImpl
	 * @see hu.bme.mit.gamma.analysis.impl.AnalysisPackageImpl#getIsBetween()
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
	 * The meta object id for the '{@link hu.bme.mit.gamma.analysis.impl.ComponentPortEventReferenceImpl <em>Component Port Event Reference</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.analysis.impl.ComponentPortEventReferenceImpl
	 * @see hu.bme.mit.gamma.analysis.impl.AnalysisPackageImpl#getComponentPortEventReference()
	 * @generated
	 */
	int COMPONENT_PORT_EVENT_REFERENCE = 14;

	/**
	 * The feature id for the '<em><b>Component</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_PORT_EVENT_REFERENCE__COMPONENT = 0;

	/**
	 * The feature id for the '<em><b>Port</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_PORT_EVENT_REFERENCE__PORT = 1;

	/**
	 * The feature id for the '<em><b>Event</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_PORT_EVENT_REFERENCE__EVENT = 2;

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
	 * The meta object id for the '{@link hu.bme.mit.gamma.analysis.impl.FrequencyImpl <em>Frequency</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.analysis.impl.FrequencyImpl
	 * @see hu.bme.mit.gamma.analysis.impl.AnalysisPackageImpl#getFrequency()
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
	 * The meta object id for the '{@link hu.bme.mit.gamma.analysis.impl.MeanParameterImpl <em>Mean Parameter</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.analysis.impl.MeanParameterImpl
	 * @see hu.bme.mit.gamma.analysis.impl.AnalysisPackageImpl#getMeanParameter()
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
	 * The number of structural features of the '<em>Mean Parameter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEAN_PARAMETER_FEATURE_COUNT = ANALYSIS_ASPECT_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Mean Parameter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEAN_PARAMETER_OPERATION_COUNT = ANALYSIS_ASPECT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.analysis.impl.ParameterDistributionImpl <em>Parameter Distribution</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.analysis.impl.ParameterDistributionImpl
	 * @see hu.bme.mit.gamma.analysis.impl.AnalysisPackageImpl#getParameterDistribution()
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
	 * The number of structural features of the '<em>Parameter Distribution</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER_DISTRIBUTION_FEATURE_COUNT = ANALYSIS_ASPECT_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Parameter Distribution</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER_DISTRIBUTION_OPERATION_COUNT = ANALYSIS_ASPECT_OPERATION_COUNT + 0;


	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.analysis.AnalysisComponent <em>Component</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Component</em>'.
	 * @see hu.bme.mit.gamma.analysis.AnalysisComponent
	 * @generated
	 */
	EClass getAnalysisComponent();

	/**
	 * Returns the meta object for the containment reference '{@link hu.bme.mit.gamma.analysis.AnalysisComponent#getAnalyzedComponent <em>Analyzed Component</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Analyzed Component</em>'.
	 * @see hu.bme.mit.gamma.analysis.AnalysisComponent#getAnalyzedComponent()
	 * @see #getAnalysisComponent()
	 * @generated
	 */
	EReference getAnalysisComponent_AnalyzedComponent();

	/**
	 * Returns the meta object for the containment reference list '{@link hu.bme.mit.gamma.analysis.AnalysisComponent#getConditions <em>Conditions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Conditions</em>'.
	 * @see hu.bme.mit.gamma.analysis.AnalysisComponent#getConditions()
	 * @see #getAnalysisComponent()
	 * @generated
	 */
	EReference getAnalysisComponent_Conditions();

	/**
	 * Returns the meta object for the containment reference '{@link hu.bme.mit.gamma.analysis.AnalysisComponent#getAspect <em>Aspect</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Aspect</em>'.
	 * @see hu.bme.mit.gamma.analysis.AnalysisComponent#getAspect()
	 * @see #getAnalysisComponent()
	 * @generated
	 */
	EReference getAnalysisComponent_Aspect();

	/**
	 * Returns the meta object for the attribute '{@link hu.bme.mit.gamma.analysis.AnalysisComponent#getSimulationTime <em>Simulation Time</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Simulation Time</em>'.
	 * @see hu.bme.mit.gamma.analysis.AnalysisComponent#getSimulationTime()
	 * @see #getAnalysisComponent()
	 * @generated
	 */
	EAttribute getAnalysisComponent_SimulationTime();

	/**
	 * Returns the meta object for the attribute '{@link hu.bme.mit.gamma.analysis.AnalysisComponent#getSimulationNumber <em>Simulation Number</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Simulation Number</em>'.
	 * @see hu.bme.mit.gamma.analysis.AnalysisComponent#getSimulationNumber()
	 * @see #getAnalysisComponent()
	 * @generated
	 */
	EAttribute getAnalysisComponent_SimulationNumber();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.analysis.AnalysisCondition <em>Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Condition</em>'.
	 * @see hu.bme.mit.gamma.analysis.AnalysisCondition
	 * @generated
	 */
	EClass getAnalysisCondition();

	/**
	 * Returns the meta object for the containment reference '{@link hu.bme.mit.gamma.analysis.AnalysisCondition#getEvent <em>Event</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Event</em>'.
	 * @see hu.bme.mit.gamma.analysis.AnalysisCondition#getEvent()
	 * @see #getAnalysisCondition()
	 * @generated
	 */
	EReference getAnalysisCondition_Event();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.analysis.AssumeRaised <em>Assume Raised</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Assume Raised</em>'.
	 * @see hu.bme.mit.gamma.analysis.AssumeRaised
	 * @generated
	 */
	EClass getAssumeRaised();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.analysis.AssumeNotRaised <em>Assume Not Raised</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Assume Not Raised</em>'.
	 * @see hu.bme.mit.gamma.analysis.AssumeNotRaised
	 * @generated
	 */
	EClass getAssumeNotRaised();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.analysis.AnalysisAspect <em>Aspect</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Aspect</em>'.
	 * @see hu.bme.mit.gamma.analysis.AnalysisAspect
	 * @generated
	 */
	EClass getAnalysisAspect();

	/**
	 * Returns the meta object for the containment reference '{@link hu.bme.mit.gamma.analysis.AnalysisAspect#getEvent <em>Event</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Event</em>'.
	 * @see hu.bme.mit.gamma.analysis.AnalysisAspect#getEvent()
	 * @see #getAnalysisAspect()
	 * @generated
	 */
	EReference getAnalysisAspect_Event();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.analysis.Probability <em>Probability</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Probability</em>'.
	 * @see hu.bme.mit.gamma.analysis.Probability
	 * @generated
	 */
	EClass getProbability();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.analysis.TimedProbability <em>Timed Probability</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Timed Probability</em>'.
	 * @see hu.bme.mit.gamma.analysis.TimedProbability
	 * @generated
	 */
	EClass getTimedProbability();

	/**
	 * Returns the meta object for the attribute '{@link hu.bme.mit.gamma.analysis.TimedProbability#getTimeLimit <em>Time Limit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Time Limit</em>'.
	 * @see hu.bme.mit.gamma.analysis.TimedProbability#getTimeLimit()
	 * @see #getTimedProbability()
	 * @generated
	 */
	EAttribute getTimedProbability_TimeLimit();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.analysis.MeanTime <em>Mean Time</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Mean Time</em>'.
	 * @see hu.bme.mit.gamma.analysis.MeanTime
	 * @generated
	 */
	EClass getMeanTime();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.analysis.TimeBoundedProbability <em>Time Bounded Probability</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Time Bounded Probability</em>'.
	 * @see hu.bme.mit.gamma.analysis.TimeBoundedProbability
	 * @generated
	 */
	EClass getTimeBoundedProbability();

	/**
	 * Returns the meta object for the attribute '{@link hu.bme.mit.gamma.analysis.TimeBoundedProbability#getLowerBound <em>Lower Bound</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Lower Bound</em>'.
	 * @see hu.bme.mit.gamma.analysis.TimeBoundedProbability#getLowerBound()
	 * @see #getTimeBoundedProbability()
	 * @generated
	 */
	EAttribute getTimeBoundedProbability_LowerBound();

	/**
	 * Returns the meta object for the attribute '{@link hu.bme.mit.gamma.analysis.TimeBoundedProbability#getUpperBound <em>Upper Bound</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Upper Bound</em>'.
	 * @see hu.bme.mit.gamma.analysis.TimeBoundedProbability#getUpperBound()
	 * @see #getTimeBoundedProbability()
	 * @generated
	 */
	EAttribute getTimeBoundedProbability_UpperBound();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.analysis.RequirementComponent <em>Requirement Component</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Requirement Component</em>'.
	 * @see hu.bme.mit.gamma.analysis.RequirementComponent
	 * @generated
	 */
	EClass getRequirementComponent();

	/**
	 * Returns the meta object for the containment reference list '{@link hu.bme.mit.gamma.analysis.RequirementComponent#getRequirement <em>Requirement</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Requirement</em>'.
	 * @see hu.bme.mit.gamma.analysis.RequirementComponent#getRequirement()
	 * @see #getRequirementComponent()
	 * @generated
	 */
	EReference getRequirementComponent_Requirement();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.analysis.RequirementAspect <em>Requirement Aspect</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Requirement Aspect</em>'.
	 * @see hu.bme.mit.gamma.analysis.RequirementAspect
	 * @generated
	 */
	EClass getRequirementAspect();

	/**
	 * Returns the meta object for the attribute '{@link hu.bme.mit.gamma.analysis.RequirementAspect#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see hu.bme.mit.gamma.analysis.RequirementAspect#getDescription()
	 * @see #getRequirementAspect()
	 * @generated
	 */
	EAttribute getRequirementAspect_Description();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.analysis.LowerThan <em>Lower Than</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Lower Than</em>'.
	 * @see hu.bme.mit.gamma.analysis.LowerThan
	 * @generated
	 */
	EClass getLowerThan();

	/**
	 * Returns the meta object for the attribute '{@link hu.bme.mit.gamma.analysis.LowerThan#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see hu.bme.mit.gamma.analysis.LowerThan#getValue()
	 * @see #getLowerThan()
	 * @generated
	 */
	EAttribute getLowerThan_Value();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.analysis.GreaterThan <em>Greater Than</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Greater Than</em>'.
	 * @see hu.bme.mit.gamma.analysis.GreaterThan
	 * @generated
	 */
	EClass getGreaterThan();

	/**
	 * Returns the meta object for the attribute '{@link hu.bme.mit.gamma.analysis.GreaterThan#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see hu.bme.mit.gamma.analysis.GreaterThan#getValue()
	 * @see #getGreaterThan()
	 * @generated
	 */
	EAttribute getGreaterThan_Value();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.analysis.IsBetween <em>Is Between</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Is Between</em>'.
	 * @see hu.bme.mit.gamma.analysis.IsBetween
	 * @generated
	 */
	EClass getIsBetween();

	/**
	 * Returns the meta object for the attribute '{@link hu.bme.mit.gamma.analysis.IsBetween#getLowerBound <em>Lower Bound</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Lower Bound</em>'.
	 * @see hu.bme.mit.gamma.analysis.IsBetween#getLowerBound()
	 * @see #getIsBetween()
	 * @generated
	 */
	EAttribute getIsBetween_LowerBound();

	/**
	 * Returns the meta object for the attribute '{@link hu.bme.mit.gamma.analysis.IsBetween#getUpperBound <em>Upper Bound</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Upper Bound</em>'.
	 * @see hu.bme.mit.gamma.analysis.IsBetween#getUpperBound()
	 * @see #getIsBetween()
	 * @generated
	 */
	EAttribute getIsBetween_UpperBound();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.analysis.ComponentPortEventReference <em>Component Port Event Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Component Port Event Reference</em>'.
	 * @see hu.bme.mit.gamma.analysis.ComponentPortEventReference
	 * @generated
	 */
	EClass getComponentPortEventReference();

	/**
	 * Returns the meta object for the reference '{@link hu.bme.mit.gamma.analysis.ComponentPortEventReference#getComponent <em>Component</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Component</em>'.
	 * @see hu.bme.mit.gamma.analysis.ComponentPortEventReference#getComponent()
	 * @see #getComponentPortEventReference()
	 * @generated
	 */
	EReference getComponentPortEventReference_Component();

	/**
	 * Returns the meta object for the reference '{@link hu.bme.mit.gamma.analysis.ComponentPortEventReference#getPort <em>Port</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Port</em>'.
	 * @see hu.bme.mit.gamma.analysis.ComponentPortEventReference#getPort()
	 * @see #getComponentPortEventReference()
	 * @generated
	 */
	EReference getComponentPortEventReference_Port();

	/**
	 * Returns the meta object for the reference '{@link hu.bme.mit.gamma.analysis.ComponentPortEventReference#getEvent <em>Event</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Event</em>'.
	 * @see hu.bme.mit.gamma.analysis.ComponentPortEventReference#getEvent()
	 * @see #getComponentPortEventReference()
	 * @generated
	 */
	EReference getComponentPortEventReference_Event();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.analysis.Frequency <em>Frequency</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Frequency</em>'.
	 * @see hu.bme.mit.gamma.analysis.Frequency
	 * @generated
	 */
	EClass getFrequency();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.analysis.MeanParameter <em>Mean Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Mean Parameter</em>'.
	 * @see hu.bme.mit.gamma.analysis.MeanParameter
	 * @generated
	 */
	EClass getMeanParameter();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.analysis.ParameterDistribution <em>Parameter Distribution</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Parameter Distribution</em>'.
	 * @see hu.bme.mit.gamma.analysis.ParameterDistribution
	 * @generated
	 */
	EClass getParameterDistribution();

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
		 * The meta object literal for the '{@link hu.bme.mit.gamma.analysis.impl.AnalysisComponentImpl <em>Component</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.analysis.impl.AnalysisComponentImpl
		 * @see hu.bme.mit.gamma.analysis.impl.AnalysisPackageImpl#getAnalysisComponent()
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
		 * The meta object literal for the '<em><b>Aspect</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ANALYSIS_COMPONENT__ASPECT = eINSTANCE.getAnalysisComponent_Aspect();

		/**
		 * The meta object literal for the '<em><b>Simulation Time</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ANALYSIS_COMPONENT__SIMULATION_TIME = eINSTANCE.getAnalysisComponent_SimulationTime();

		/**
		 * The meta object literal for the '<em><b>Simulation Number</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ANALYSIS_COMPONENT__SIMULATION_NUMBER = eINSTANCE.getAnalysisComponent_SimulationNumber();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.analysis.impl.AnalysisConditionImpl <em>Condition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.analysis.impl.AnalysisConditionImpl
		 * @see hu.bme.mit.gamma.analysis.impl.AnalysisPackageImpl#getAnalysisCondition()
		 * @generated
		 */
		EClass ANALYSIS_CONDITION = eINSTANCE.getAnalysisCondition();

		/**
		 * The meta object literal for the '<em><b>Event</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ANALYSIS_CONDITION__EVENT = eINSTANCE.getAnalysisCondition_Event();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.analysis.impl.AssumeRaisedImpl <em>Assume Raised</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.analysis.impl.AssumeRaisedImpl
		 * @see hu.bme.mit.gamma.analysis.impl.AnalysisPackageImpl#getAssumeRaised()
		 * @generated
		 */
		EClass ASSUME_RAISED = eINSTANCE.getAssumeRaised();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.analysis.impl.AssumeNotRaisedImpl <em>Assume Not Raised</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.analysis.impl.AssumeNotRaisedImpl
		 * @see hu.bme.mit.gamma.analysis.impl.AnalysisPackageImpl#getAssumeNotRaised()
		 * @generated
		 */
		EClass ASSUME_NOT_RAISED = eINSTANCE.getAssumeNotRaised();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.analysis.impl.AnalysisAspectImpl <em>Aspect</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.analysis.impl.AnalysisAspectImpl
		 * @see hu.bme.mit.gamma.analysis.impl.AnalysisPackageImpl#getAnalysisAspect()
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
		 * The meta object literal for the '{@link hu.bme.mit.gamma.analysis.impl.ProbabilityImpl <em>Probability</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.analysis.impl.ProbabilityImpl
		 * @see hu.bme.mit.gamma.analysis.impl.AnalysisPackageImpl#getProbability()
		 * @generated
		 */
		EClass PROBABILITY = eINSTANCE.getProbability();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.analysis.impl.TimedProbabilityImpl <em>Timed Probability</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.analysis.impl.TimedProbabilityImpl
		 * @see hu.bme.mit.gamma.analysis.impl.AnalysisPackageImpl#getTimedProbability()
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
		 * The meta object literal for the '{@link hu.bme.mit.gamma.analysis.impl.MeanTimeImpl <em>Mean Time</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.analysis.impl.MeanTimeImpl
		 * @see hu.bme.mit.gamma.analysis.impl.AnalysisPackageImpl#getMeanTime()
		 * @generated
		 */
		EClass MEAN_TIME = eINSTANCE.getMeanTime();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.analysis.impl.TimeBoundedProbabilityImpl <em>Time Bounded Probability</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.analysis.impl.TimeBoundedProbabilityImpl
		 * @see hu.bme.mit.gamma.analysis.impl.AnalysisPackageImpl#getTimeBoundedProbability()
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
		 * The meta object literal for the '{@link hu.bme.mit.gamma.analysis.impl.RequirementComponentImpl <em>Requirement Component</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.analysis.impl.RequirementComponentImpl
		 * @see hu.bme.mit.gamma.analysis.impl.AnalysisPackageImpl#getRequirementComponent()
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
		 * The meta object literal for the '{@link hu.bme.mit.gamma.analysis.impl.RequirementAspectImpl <em>Requirement Aspect</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.analysis.impl.RequirementAspectImpl
		 * @see hu.bme.mit.gamma.analysis.impl.AnalysisPackageImpl#getRequirementAspect()
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
		 * The meta object literal for the '{@link hu.bme.mit.gamma.analysis.impl.LowerThanImpl <em>Lower Than</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.analysis.impl.LowerThanImpl
		 * @see hu.bme.mit.gamma.analysis.impl.AnalysisPackageImpl#getLowerThan()
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
		 * The meta object literal for the '{@link hu.bme.mit.gamma.analysis.impl.GreaterThanImpl <em>Greater Than</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.analysis.impl.GreaterThanImpl
		 * @see hu.bme.mit.gamma.analysis.impl.AnalysisPackageImpl#getGreaterThan()
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
		 * The meta object literal for the '{@link hu.bme.mit.gamma.analysis.impl.IsBetweenImpl <em>Is Between</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.analysis.impl.IsBetweenImpl
		 * @see hu.bme.mit.gamma.analysis.impl.AnalysisPackageImpl#getIsBetween()
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
		 * The meta object literal for the '{@link hu.bme.mit.gamma.analysis.impl.ComponentPortEventReferenceImpl <em>Component Port Event Reference</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.analysis.impl.ComponentPortEventReferenceImpl
		 * @see hu.bme.mit.gamma.analysis.impl.AnalysisPackageImpl#getComponentPortEventReference()
		 * @generated
		 */
		EClass COMPONENT_PORT_EVENT_REFERENCE = eINSTANCE.getComponentPortEventReference();

		/**
		 * The meta object literal for the '<em><b>Component</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPONENT_PORT_EVENT_REFERENCE__COMPONENT = eINSTANCE.getComponentPortEventReference_Component();

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
		 * The meta object literal for the '{@link hu.bme.mit.gamma.analysis.impl.FrequencyImpl <em>Frequency</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.analysis.impl.FrequencyImpl
		 * @see hu.bme.mit.gamma.analysis.impl.AnalysisPackageImpl#getFrequency()
		 * @generated
		 */
		EClass FREQUENCY = eINSTANCE.getFrequency();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.analysis.impl.MeanParameterImpl <em>Mean Parameter</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.analysis.impl.MeanParameterImpl
		 * @see hu.bme.mit.gamma.analysis.impl.AnalysisPackageImpl#getMeanParameter()
		 * @generated
		 */
		EClass MEAN_PARAMETER = eINSTANCE.getMeanParameter();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.analysis.impl.ParameterDistributionImpl <em>Parameter Distribution</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.analysis.impl.ParameterDistributionImpl
		 * @see hu.bme.mit.gamma.analysis.impl.AnalysisPackageImpl#getParameterDistribution()
		 * @generated
		 */
		EClass PARAMETER_DISTRIBUTION = eINSTANCE.getParameterDistribution();

	}

} //AnalysisPackage
