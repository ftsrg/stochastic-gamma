/**
 */
package hu.bme.mit.gamma.environment.model;

import hu.bme.mit.gamma.statechart.composite.CompositeModelPackage;

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
 * @see hu.bme.mit.gamma.environment.model.EnvironentFactory
 * @model kind="package"
 * @generated
 */
public interface EnvironentPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "model";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "www.mit.bme.hu/gamma/environment/Model";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "hu.bme.mit.gamma.environment";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	EnvironentPackage eINSTANCE = hu.bme.mit.gamma.environment.model.impl.EnvironentPackageImpl.init();

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.environment.model.impl.EnvironmentComponentInstanceImpl <em>Environment Component Instance</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.environment.model.impl.EnvironmentComponentInstanceImpl
	 * @see hu.bme.mit.gamma.environment.model.impl.EnvironentPackageImpl#getEnvironmentComponentInstance()
	 * @generated
	 */
	int ENVIRONMENT_COMPONENT_INSTANCE = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENVIRONMENT_COMPONENT_INSTANCE__NAME = CompositeModelPackage.COMPONENT_INSTANCE__NAME;

	/**
	 * The feature id for the '<em><b>Arguments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENVIRONMENT_COMPONENT_INSTANCE__ARGUMENTS = CompositeModelPackage.COMPONENT_INSTANCE__ARGUMENTS;

	/**
	 * The number of structural features of the '<em>Environment Component Instance</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENVIRONMENT_COMPONENT_INSTANCE_FEATURE_COUNT = CompositeModelPackage.COMPONENT_INSTANCE_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Environment Component Instance</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENVIRONMENT_COMPONENT_INSTANCE_OPERATION_COUNT = CompositeModelPackage.COMPONENT_INSTANCE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.environment.model.impl.ElementaryEnvironmentComponentInstanceImpl <em>Elementary Environment Component Instance</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.environment.model.impl.ElementaryEnvironmentComponentInstanceImpl
	 * @see hu.bme.mit.gamma.environment.model.impl.EnvironentPackageImpl#getElementaryEnvironmentComponentInstance()
	 * @generated
	 */
	int ELEMENTARY_ENVIRONMENT_COMPONENT_INSTANCE = 6;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEMENTARY_ENVIRONMENT_COMPONENT_INSTANCE__NAME = ENVIRONMENT_COMPONENT_INSTANCE__NAME;

	/**
	 * The feature id for the '<em><b>Arguments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEMENTARY_ENVIRONMENT_COMPONENT_INSTANCE__ARGUMENTS = ENVIRONMENT_COMPONENT_INSTANCE__ARGUMENTS;

	/**
	 * The feature id for the '<em><b>Outports</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEMENTARY_ENVIRONMENT_COMPONENT_INSTANCE__OUTPORTS = ENVIRONMENT_COMPONENT_INSTANCE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Inports</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEMENTARY_ENVIRONMENT_COMPONENT_INSTANCE__INPORTS = ENVIRONMENT_COMPONENT_INSTANCE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Behavior Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEMENTARY_ENVIRONMENT_COMPONENT_INSTANCE__BEHAVIOR_RULES = ENVIRONMENT_COMPONENT_INSTANCE_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Elementary Environment Component Instance</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEMENTARY_ENVIRONMENT_COMPONENT_INSTANCE_FEATURE_COUNT = ENVIRONMENT_COMPONENT_INSTANCE_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Elementary Environment Component Instance</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEMENTARY_ENVIRONMENT_COMPONENT_INSTANCE_OPERATION_COUNT = ENVIRONMENT_COMPONENT_INSTANCE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.environment.model.impl.EnvironmentEventSourceImpl <em>Environment Event Source</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.environment.model.impl.EnvironmentEventSourceImpl
	 * @see hu.bme.mit.gamma.environment.model.impl.EnvironentPackageImpl#getEnvironmentEventSource()
	 * @generated
	 */
	int ENVIRONMENT_EVENT_SOURCE = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENVIRONMENT_EVENT_SOURCE__NAME = ELEMENTARY_ENVIRONMENT_COMPONENT_INSTANCE__NAME;

	/**
	 * The feature id for the '<em><b>Arguments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENVIRONMENT_EVENT_SOURCE__ARGUMENTS = ELEMENTARY_ENVIRONMENT_COMPONENT_INSTANCE__ARGUMENTS;

	/**
	 * The feature id for the '<em><b>Outports</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENVIRONMENT_EVENT_SOURCE__OUTPORTS = ELEMENTARY_ENVIRONMENT_COMPONENT_INSTANCE__OUTPORTS;

	/**
	 * The feature id for the '<em><b>Inports</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENVIRONMENT_EVENT_SOURCE__INPORTS = ELEMENTARY_ENVIRONMENT_COMPONENT_INSTANCE__INPORTS;

	/**
	 * The feature id for the '<em><b>Behavior Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENVIRONMENT_EVENT_SOURCE__BEHAVIOR_RULES = ELEMENTARY_ENVIRONMENT_COMPONENT_INSTANCE__BEHAVIOR_RULES;

	/**
	 * The number of structural features of the '<em>Environment Event Source</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENVIRONMENT_EVENT_SOURCE_FEATURE_COUNT = ELEMENTARY_ENVIRONMENT_COMPONENT_INSTANCE_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Environment Event Source</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENVIRONMENT_EVENT_SOURCE_OPERATION_COUNT = ELEMENTARY_ENVIRONMENT_COMPONENT_INSTANCE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.environment.model.impl.EnvironmentCompositeComponentImpl <em>Environment Composite Component</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.environment.model.impl.EnvironmentCompositeComponentImpl
	 * @see hu.bme.mit.gamma.environment.model.impl.EnvironentPackageImpl#getEnvironmentCompositeComponent()
	 * @generated
	 */
	int ENVIRONMENT_COMPOSITE_COMPONENT = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENVIRONMENT_COMPOSITE_COMPONENT__NAME = CompositeModelPackage.CASCADE_COMPOSITE_COMPONENT__NAME;

	/**
	 * The feature id for the '<em><b>Parameter Declarations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENVIRONMENT_COMPOSITE_COMPONENT__PARAMETER_DECLARATIONS = CompositeModelPackage.CASCADE_COMPOSITE_COMPONENT__PARAMETER_DECLARATIONS;

	/**
	 * The feature id for the '<em><b>Ports</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENVIRONMENT_COMPOSITE_COMPONENT__PORTS = CompositeModelPackage.CASCADE_COMPOSITE_COMPONENT__PORTS;

	/**
	 * The feature id for the '<em><b>Function Declarations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENVIRONMENT_COMPOSITE_COMPONENT__FUNCTION_DECLARATIONS = CompositeModelPackage.CASCADE_COMPOSITE_COMPONENT__FUNCTION_DECLARATIONS;

	/**
	 * The feature id for the '<em><b>Port Bindings</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENVIRONMENT_COMPOSITE_COMPONENT__PORT_BINDINGS = CompositeModelPackage.CASCADE_COMPOSITE_COMPONENT__PORT_BINDINGS;

	/**
	 * The feature id for the '<em><b>Channels</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENVIRONMENT_COMPOSITE_COMPONENT__CHANNELS = CompositeModelPackage.CASCADE_COMPOSITE_COMPONENT__CHANNELS;

	/**
	 * The feature id for the '<em><b>Components</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENVIRONMENT_COMPOSITE_COMPONENT__COMPONENTS = CompositeModelPackage.CASCADE_COMPOSITE_COMPONENT__COMPONENTS;

	/**
	 * The feature id for the '<em><b>Execution List</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENVIRONMENT_COMPOSITE_COMPONENT__EXECUTION_LIST = CompositeModelPackage.CASCADE_COMPOSITE_COMPONENT__EXECUTION_LIST;

	/**
	 * The feature id for the '<em><b>Environment Components</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENVIRONMENT_COMPOSITE_COMPONENT__ENVIRONMENT_COMPONENTS = CompositeModelPackage.CASCADE_COMPOSITE_COMPONENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Environment Composite Component</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENVIRONMENT_COMPOSITE_COMPONENT_FEATURE_COUNT = CompositeModelPackage.CASCADE_COMPOSITE_COMPONENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Environment Composite Component</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENVIRONMENT_COMPOSITE_COMPONENT_OPERATION_COUNT = CompositeModelPackage.CASCADE_COMPOSITE_COMPONENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.environment.model.impl.EnvironmentCompositeComponentInstanceImpl <em>Environment Composite Component Instance</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.environment.model.impl.EnvironmentCompositeComponentInstanceImpl
	 * @see hu.bme.mit.gamma.environment.model.impl.EnvironentPackageImpl#getEnvironmentCompositeComponentInstance()
	 * @generated
	 */
	int ENVIRONMENT_COMPOSITE_COMPONENT_INSTANCE = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENVIRONMENT_COMPOSITE_COMPONENT_INSTANCE__NAME = ENVIRONMENT_COMPONENT_INSTANCE__NAME;

	/**
	 * The feature id for the '<em><b>Arguments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENVIRONMENT_COMPOSITE_COMPONENT_INSTANCE__ARGUMENTS = ENVIRONMENT_COMPONENT_INSTANCE__ARGUMENTS;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENVIRONMENT_COMPOSITE_COMPONENT_INSTANCE__TYPE = ENVIRONMENT_COMPONENT_INSTANCE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Environment Composite Component Instance</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENVIRONMENT_COMPOSITE_COMPONENT_INSTANCE_FEATURE_COUNT = ENVIRONMENT_COMPONENT_INSTANCE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Environment Composite Component Instance</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENVIRONMENT_COMPOSITE_COMPONENT_INSTANCE_OPERATION_COUNT = ENVIRONMENT_COMPONENT_INSTANCE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.environment.model.impl.EnvironmentDelayImpl <em>Environment Delay</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.environment.model.impl.EnvironmentDelayImpl
	 * @see hu.bme.mit.gamma.environment.model.impl.EnvironentPackageImpl#getEnvironmentDelay()
	 * @generated
	 */
	int ENVIRONMENT_DELAY = 4;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENVIRONMENT_DELAY__NAME = ELEMENTARY_ENVIRONMENT_COMPONENT_INSTANCE__NAME;

	/**
	 * The feature id for the '<em><b>Arguments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENVIRONMENT_DELAY__ARGUMENTS = ELEMENTARY_ENVIRONMENT_COMPONENT_INSTANCE__ARGUMENTS;

	/**
	 * The feature id for the '<em><b>Outports</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENVIRONMENT_DELAY__OUTPORTS = ELEMENTARY_ENVIRONMENT_COMPONENT_INSTANCE__OUTPORTS;

	/**
	 * The feature id for the '<em><b>Inports</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENVIRONMENT_DELAY__INPORTS = ELEMENTARY_ENVIRONMENT_COMPONENT_INSTANCE__INPORTS;

	/**
	 * The feature id for the '<em><b>Behavior Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENVIRONMENT_DELAY__BEHAVIOR_RULES = ELEMENTARY_ENVIRONMENT_COMPONENT_INSTANCE__BEHAVIOR_RULES;

	/**
	 * The number of structural features of the '<em>Environment Delay</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENVIRONMENT_DELAY_FEATURE_COUNT = ELEMENTARY_ENVIRONMENT_COMPONENT_INSTANCE_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Environment Delay</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENVIRONMENT_DELAY_OPERATION_COUNT = ELEMENTARY_ENVIRONMENT_COMPONENT_INSTANCE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.environment.model.impl.EnvironmentSwitchImpl <em>Environment Switch</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.environment.model.impl.EnvironmentSwitchImpl
	 * @see hu.bme.mit.gamma.environment.model.impl.EnvironentPackageImpl#getEnvironmentSwitch()
	 * @generated
	 */
	int ENVIRONMENT_SWITCH = 5;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENVIRONMENT_SWITCH__NAME = ELEMENTARY_ENVIRONMENT_COMPONENT_INSTANCE__NAME;

	/**
	 * The feature id for the '<em><b>Arguments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENVIRONMENT_SWITCH__ARGUMENTS = ELEMENTARY_ENVIRONMENT_COMPONENT_INSTANCE__ARGUMENTS;

	/**
	 * The feature id for the '<em><b>Outports</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENVIRONMENT_SWITCH__OUTPORTS = ELEMENTARY_ENVIRONMENT_COMPONENT_INSTANCE__OUTPORTS;

	/**
	 * The feature id for the '<em><b>Inports</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENVIRONMENT_SWITCH__INPORTS = ELEMENTARY_ENVIRONMENT_COMPONENT_INSTANCE__INPORTS;

	/**
	 * The feature id for the '<em><b>Behavior Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENVIRONMENT_SWITCH__BEHAVIOR_RULES = ELEMENTARY_ENVIRONMENT_COMPONENT_INSTANCE__BEHAVIOR_RULES;

	/**
	 * The number of structural features of the '<em>Environment Switch</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENVIRONMENT_SWITCH_FEATURE_COUNT = ELEMENTARY_ENVIRONMENT_COMPONENT_INSTANCE_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Environment Switch</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENVIRONMENT_SWITCH_OPERATION_COUNT = ELEMENTARY_ENVIRONMENT_COMPONENT_INSTANCE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.environment.model.impl.FilterImpl <em>Filter</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.environment.model.impl.FilterImpl
	 * @see hu.bme.mit.gamma.environment.model.impl.EnvironentPackageImpl#getFilter()
	 * @generated
	 */
	int FILTER = 15;

	/**
	 * The number of structural features of the '<em>Filter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTER_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Filter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTER_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.environment.model.impl.EventFilterImpl <em>Event Filter</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.environment.model.impl.EventFilterImpl
	 * @see hu.bme.mit.gamma.environment.model.impl.EnvironentPackageImpl#getEventFilter()
	 * @generated
	 */
	int EVENT_FILTER = 7;

	/**
	 * The feature id for the '<em><b>Event</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_FILTER__EVENT = FILTER_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Event Filter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_FILTER_FEATURE_COUNT = FILTER_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Event Filter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_FILTER_OPERATION_COUNT = FILTER_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.environment.model.impl.EnvironmentRuleImpl <em>Environment Rule</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.environment.model.impl.EnvironmentRuleImpl
	 * @see hu.bme.mit.gamma.environment.model.impl.EnvironentPackageImpl#getEnvironmentRule()
	 * @generated
	 */
	int ENVIRONMENT_RULE = 11;

	/**
	 * The feature id for the '<em><b>Filter</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENVIRONMENT_RULE__FILTER = 0;

	/**
	 * The number of structural features of the '<em>Environment Rule</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENVIRONMENT_RULE_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Environment Rule</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENVIRONMENT_RULE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.environment.model.impl.StochasticRuleImpl <em>Stochastic Rule</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.environment.model.impl.StochasticRuleImpl
	 * @see hu.bme.mit.gamma.environment.model.impl.EnvironentPackageImpl#getStochasticRule()
	 * @generated
	 */
	int STOCHASTIC_RULE = 8;

	/**
	 * The feature id for the '<em><b>Filter</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STOCHASTIC_RULE__FILTER = ENVIRONMENT_RULE__FILTER;

	/**
	 * The feature id for the '<em><b>Stochastic Model</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STOCHASTIC_RULE__STOCHASTIC_MODEL = ENVIRONMENT_RULE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Stochastic Rule</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STOCHASTIC_RULE_FEATURE_COUNT = ENVIRONMENT_RULE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Stochastic Rule</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STOCHASTIC_RULE_OPERATION_COUNT = ENVIRONMENT_RULE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.environment.model.impl.PortFilterImpl <em>Port Filter</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.environment.model.impl.PortFilterImpl
	 * @see hu.bme.mit.gamma.environment.model.impl.EnvironentPackageImpl#getPortFilter()
	 * @generated
	 */
	int PORT_FILTER = 9;

	/**
	 * The feature id for the '<em><b>Port</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_FILTER__PORT = FILTER_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Port Filter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_FILTER_FEATURE_COUNT = FILTER_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Port Filter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_FILTER_OPERATION_COUNT = FILTER_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.environment.model.impl.ComponentFilterImpl <em>Component Filter</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.environment.model.impl.ComponentFilterImpl
	 * @see hu.bme.mit.gamma.environment.model.impl.EnvironentPackageImpl#getComponentFilter()
	 * @generated
	 */
	int COMPONENT_FILTER = 10;

	/**
	 * The number of structural features of the '<em>Component Filter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_FILTER_FEATURE_COUNT = FILTER_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Component Filter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_FILTER_OPERATION_COUNT = FILTER_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.environment.model.impl.EnvironmentSampleImpl <em>Environment Sample</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.environment.model.impl.EnvironmentSampleImpl
	 * @see hu.bme.mit.gamma.environment.model.impl.EnvironentPackageImpl#getEnvironmentSample()
	 * @generated
	 */
	int ENVIRONMENT_SAMPLE = 12;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENVIRONMENT_SAMPLE__NAME = ELEMENTARY_ENVIRONMENT_COMPONENT_INSTANCE__NAME;

	/**
	 * The feature id for the '<em><b>Arguments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENVIRONMENT_SAMPLE__ARGUMENTS = ELEMENTARY_ENVIRONMENT_COMPONENT_INSTANCE__ARGUMENTS;

	/**
	 * The feature id for the '<em><b>Outports</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENVIRONMENT_SAMPLE__OUTPORTS = ELEMENTARY_ENVIRONMENT_COMPONENT_INSTANCE__OUTPORTS;

	/**
	 * The feature id for the '<em><b>Inports</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENVIRONMENT_SAMPLE__INPORTS = ELEMENTARY_ENVIRONMENT_COMPONENT_INSTANCE__INPORTS;

	/**
	 * The feature id for the '<em><b>Behavior Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENVIRONMENT_SAMPLE__BEHAVIOR_RULES = ELEMENTARY_ENVIRONMENT_COMPONENT_INSTANCE__BEHAVIOR_RULES;

	/**
	 * The number of structural features of the '<em>Environment Sample</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENVIRONMENT_SAMPLE_FEATURE_COUNT = ELEMENTARY_ENVIRONMENT_COMPONENT_INSTANCE_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Environment Sample</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENVIRONMENT_SAMPLE_OPERATION_COUNT = ELEMENTARY_ENVIRONMENT_COMPONENT_INSTANCE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.environment.model.impl.EnvironmentExternSimulationImpl <em>Environment Extern Simulation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.environment.model.impl.EnvironmentExternSimulationImpl
	 * @see hu.bme.mit.gamma.environment.model.impl.EnvironentPackageImpl#getEnvironmentExternSimulation()
	 * @generated
	 */
	int ENVIRONMENT_EXTERN_SIMULATION = 13;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENVIRONMENT_EXTERN_SIMULATION__NAME = ELEMENTARY_ENVIRONMENT_COMPONENT_INSTANCE__NAME;

	/**
	 * The feature id for the '<em><b>Arguments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENVIRONMENT_EXTERN_SIMULATION__ARGUMENTS = ELEMENTARY_ENVIRONMENT_COMPONENT_INSTANCE__ARGUMENTS;

	/**
	 * The feature id for the '<em><b>Outports</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENVIRONMENT_EXTERN_SIMULATION__OUTPORTS = ELEMENTARY_ENVIRONMENT_COMPONENT_INSTANCE__OUTPORTS;

	/**
	 * The feature id for the '<em><b>Inports</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENVIRONMENT_EXTERN_SIMULATION__INPORTS = ELEMENTARY_ENVIRONMENT_COMPONENT_INSTANCE__INPORTS;

	/**
	 * The feature id for the '<em><b>Behavior Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENVIRONMENT_EXTERN_SIMULATION__BEHAVIOR_RULES = ELEMENTARY_ENVIRONMENT_COMPONENT_INSTANCE__BEHAVIOR_RULES;

	/**
	 * The number of structural features of the '<em>Environment Extern Simulation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENVIRONMENT_EXTERN_SIMULATION_FEATURE_COUNT = ELEMENTARY_ENVIRONMENT_COMPONENT_INSTANCE_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Environment Extern Simulation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENVIRONMENT_EXTERN_SIMULATION_OPERATION_COUNT = ELEMENTARY_ENVIRONMENT_COMPONENT_INSTANCE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.environment.model.impl.SimulationRuleImpl <em>Simulation Rule</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.environment.model.impl.SimulationRuleImpl
	 * @see hu.bme.mit.gamma.environment.model.impl.EnvironentPackageImpl#getSimulationRule()
	 * @generated
	 */
	int SIMULATION_RULE = 14;

	/**
	 * The feature id for the '<em><b>Filter</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMULATION_RULE__FILTER = ENVIRONMENT_RULE__FILTER;

	/**
	 * The feature id for the '<em><b>Simulation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMULATION_RULE__SIMULATION = ENVIRONMENT_RULE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Simulation Rule</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMULATION_RULE_FEATURE_COUNT = ENVIRONMENT_RULE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Simulation Rule</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMULATION_RULE_OPERATION_COUNT = ENVIRONMENT_RULE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.environment.model.impl.SimulationImpl <em>Simulation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.environment.model.impl.SimulationImpl
	 * @see hu.bme.mit.gamma.environment.model.impl.EnvironentPackageImpl#getSimulation()
	 * @generated
	 */
	int SIMULATION = 16;

	/**
	 * The feature id for the '<em><b>Simulation Class Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMULATION__SIMULATION_CLASS_NAME = 0;

	/**
	 * The number of structural features of the '<em>Simulation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMULATION_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Simulation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMULATION_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.environment.model.impl.EnvironmentPeriodicEventSourceImpl <em>Environment Periodic Event Source</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.environment.model.impl.EnvironmentPeriodicEventSourceImpl
	 * @see hu.bme.mit.gamma.environment.model.impl.EnvironentPackageImpl#getEnvironmentPeriodicEventSource()
	 * @generated
	 */
	int ENVIRONMENT_PERIODIC_EVENT_SOURCE = 17;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENVIRONMENT_PERIODIC_EVENT_SOURCE__NAME = ELEMENTARY_ENVIRONMENT_COMPONENT_INSTANCE__NAME;

	/**
	 * The feature id for the '<em><b>Arguments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENVIRONMENT_PERIODIC_EVENT_SOURCE__ARGUMENTS = ELEMENTARY_ENVIRONMENT_COMPONENT_INSTANCE__ARGUMENTS;

	/**
	 * The feature id for the '<em><b>Outports</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENVIRONMENT_PERIODIC_EVENT_SOURCE__OUTPORTS = ELEMENTARY_ENVIRONMENT_COMPONENT_INSTANCE__OUTPORTS;

	/**
	 * The feature id for the '<em><b>Inports</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENVIRONMENT_PERIODIC_EVENT_SOURCE__INPORTS = ELEMENTARY_ENVIRONMENT_COMPONENT_INSTANCE__INPORTS;

	/**
	 * The feature id for the '<em><b>Behavior Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENVIRONMENT_PERIODIC_EVENT_SOURCE__BEHAVIOR_RULES = ELEMENTARY_ENVIRONMENT_COMPONENT_INSTANCE__BEHAVIOR_RULES;

	/**
	 * The number of structural features of the '<em>Environment Periodic Event Source</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENVIRONMENT_PERIODIC_EVENT_SOURCE_FEATURE_COUNT = ELEMENTARY_ENVIRONMENT_COMPONENT_INSTANCE_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Environment Periodic Event Source</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENVIRONMENT_PERIODIC_EVENT_SOURCE_OPERATION_COUNT = ELEMENTARY_ENVIRONMENT_COMPONENT_INSTANCE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.environment.model.impl.PeriodicSimulationImpl <em>Periodic Simulation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.environment.model.impl.PeriodicSimulationImpl
	 * @see hu.bme.mit.gamma.environment.model.impl.EnvironentPackageImpl#getPeriodicSimulation()
	 * @generated
	 */
	int PERIODIC_SIMULATION = 18;

	/**
	 * The feature id for the '<em><b>Simulation Class Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERIODIC_SIMULATION__SIMULATION_CLASS_NAME = SIMULATION__SIMULATION_CLASS_NAME;

	/**
	 * The feature id for the '<em><b>Period Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERIODIC_SIMULATION__PERIOD_TIME = SIMULATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Periodic Simulation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERIODIC_SIMULATION_FEATURE_COUNT = SIMULATION_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Periodic Simulation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERIODIC_SIMULATION_OPERATION_COUNT = SIMULATION_OPERATION_COUNT + 0;


	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.environment.model.EnvironmentEventSource <em>Environment Event Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Environment Event Source</em>'.
	 * @see hu.bme.mit.gamma.environment.model.EnvironmentEventSource
	 * @generated
	 */
	EClass getEnvironmentEventSource();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.environment.model.EnvironmentCompositeComponent <em>Environment Composite Component</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Environment Composite Component</em>'.
	 * @see hu.bme.mit.gamma.environment.model.EnvironmentCompositeComponent
	 * @generated
	 */
	EClass getEnvironmentCompositeComponent();

	/**
	 * Returns the meta object for the containment reference list '{@link hu.bme.mit.gamma.environment.model.EnvironmentCompositeComponent#getEnvironmentComponents <em>Environment Components</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Environment Components</em>'.
	 * @see hu.bme.mit.gamma.environment.model.EnvironmentCompositeComponent#getEnvironmentComponents()
	 * @see #getEnvironmentCompositeComponent()
	 * @generated
	 */
	EReference getEnvironmentCompositeComponent_EnvironmentComponents();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.environment.model.EnvironmentComponentInstance <em>Environment Component Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Environment Component Instance</em>'.
	 * @see hu.bme.mit.gamma.environment.model.EnvironmentComponentInstance
	 * @generated
	 */
	EClass getEnvironmentComponentInstance();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.environment.model.EnvironmentCompositeComponentInstance <em>Environment Composite Component Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Environment Composite Component Instance</em>'.
	 * @see hu.bme.mit.gamma.environment.model.EnvironmentCompositeComponentInstance
	 * @generated
	 */
	EClass getEnvironmentCompositeComponentInstance();

	/**
	 * Returns the meta object for the reference '{@link hu.bme.mit.gamma.environment.model.EnvironmentCompositeComponentInstance#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Type</em>'.
	 * @see hu.bme.mit.gamma.environment.model.EnvironmentCompositeComponentInstance#getType()
	 * @see #getEnvironmentCompositeComponentInstance()
	 * @generated
	 */
	EReference getEnvironmentCompositeComponentInstance_Type();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.environment.model.EnvironmentDelay <em>Environment Delay</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Environment Delay</em>'.
	 * @see hu.bme.mit.gamma.environment.model.EnvironmentDelay
	 * @generated
	 */
	EClass getEnvironmentDelay();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.environment.model.EnvironmentSwitch <em>Environment Switch</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Environment Switch</em>'.
	 * @see hu.bme.mit.gamma.environment.model.EnvironmentSwitch
	 * @generated
	 */
	EClass getEnvironmentSwitch();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.environment.model.ElementaryEnvironmentComponentInstance <em>Elementary Environment Component Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Elementary Environment Component Instance</em>'.
	 * @see hu.bme.mit.gamma.environment.model.ElementaryEnvironmentComponentInstance
	 * @generated
	 */
	EClass getElementaryEnvironmentComponentInstance();

	/**
	 * Returns the meta object for the containment reference list '{@link hu.bme.mit.gamma.environment.model.ElementaryEnvironmentComponentInstance#getOutports <em>Outports</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Outports</em>'.
	 * @see hu.bme.mit.gamma.environment.model.ElementaryEnvironmentComponentInstance#getOutports()
	 * @see #getElementaryEnvironmentComponentInstance()
	 * @generated
	 */
	EReference getElementaryEnvironmentComponentInstance_Outports();

	/**
	 * Returns the meta object for the containment reference list '{@link hu.bme.mit.gamma.environment.model.ElementaryEnvironmentComponentInstance#getInports <em>Inports</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Inports</em>'.
	 * @see hu.bme.mit.gamma.environment.model.ElementaryEnvironmentComponentInstance#getInports()
	 * @see #getElementaryEnvironmentComponentInstance()
	 * @generated
	 */
	EReference getElementaryEnvironmentComponentInstance_Inports();

	/**
	 * Returns the meta object for the containment reference list '{@link hu.bme.mit.gamma.environment.model.ElementaryEnvironmentComponentInstance#getBehaviorRules <em>Behavior Rules</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Behavior Rules</em>'.
	 * @see hu.bme.mit.gamma.environment.model.ElementaryEnvironmentComponentInstance#getBehaviorRules()
	 * @see #getElementaryEnvironmentComponentInstance()
	 * @generated
	 */
	EReference getElementaryEnvironmentComponentInstance_BehaviorRules();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.environment.model.EventFilter <em>Event Filter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Event Filter</em>'.
	 * @see hu.bme.mit.gamma.environment.model.EventFilter
	 * @generated
	 */
	EClass getEventFilter();

	/**
	 * Returns the meta object for the containment reference '{@link hu.bme.mit.gamma.environment.model.EventFilter#getEvent <em>Event</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Event</em>'.
	 * @see hu.bme.mit.gamma.environment.model.EventFilter#getEvent()
	 * @see #getEventFilter()
	 * @generated
	 */
	EReference getEventFilter_Event();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.environment.model.StochasticRule <em>Stochastic Rule</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Stochastic Rule</em>'.
	 * @see hu.bme.mit.gamma.environment.model.StochasticRule
	 * @generated
	 */
	EClass getStochasticRule();

	/**
	 * Returns the meta object for the containment reference '{@link hu.bme.mit.gamma.environment.model.StochasticRule#getStochasticModel <em>Stochastic Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Stochastic Model</em>'.
	 * @see hu.bme.mit.gamma.environment.model.StochasticRule#getStochasticModel()
	 * @see #getStochasticRule()
	 * @generated
	 */
	EReference getStochasticRule_StochasticModel();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.environment.model.PortFilter <em>Port Filter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Port Filter</em>'.
	 * @see hu.bme.mit.gamma.environment.model.PortFilter
	 * @generated
	 */
	EClass getPortFilter();

	/**
	 * Returns the meta object for the reference '{@link hu.bme.mit.gamma.environment.model.PortFilter#getPort <em>Port</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Port</em>'.
	 * @see hu.bme.mit.gamma.environment.model.PortFilter#getPort()
	 * @see #getPortFilter()
	 * @generated
	 */
	EReference getPortFilter_Port();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.environment.model.ComponentFilter <em>Component Filter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Component Filter</em>'.
	 * @see hu.bme.mit.gamma.environment.model.ComponentFilter
	 * @generated
	 */
	EClass getComponentFilter();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.environment.model.EnvironmentRule <em>Environment Rule</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Environment Rule</em>'.
	 * @see hu.bme.mit.gamma.environment.model.EnvironmentRule
	 * @generated
	 */
	EClass getEnvironmentRule();

	/**
	 * Returns the meta object for the containment reference list '{@link hu.bme.mit.gamma.environment.model.EnvironmentRule#getFilter <em>Filter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Filter</em>'.
	 * @see hu.bme.mit.gamma.environment.model.EnvironmentRule#getFilter()
	 * @see #getEnvironmentRule()
	 * @generated
	 */
	EReference getEnvironmentRule_Filter();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.environment.model.EnvironmentSample <em>Environment Sample</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Environment Sample</em>'.
	 * @see hu.bme.mit.gamma.environment.model.EnvironmentSample
	 * @generated
	 */
	EClass getEnvironmentSample();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.environment.model.EnvironmentExternSimulation <em>Environment Extern Simulation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Environment Extern Simulation</em>'.
	 * @see hu.bme.mit.gamma.environment.model.EnvironmentExternSimulation
	 * @generated
	 */
	EClass getEnvironmentExternSimulation();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.environment.model.SimulationRule <em>Simulation Rule</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Simulation Rule</em>'.
	 * @see hu.bme.mit.gamma.environment.model.SimulationRule
	 * @generated
	 */
	EClass getSimulationRule();

	/**
	 * Returns the meta object for the containment reference '{@link hu.bme.mit.gamma.environment.model.SimulationRule#getSimulation <em>Simulation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Simulation</em>'.
	 * @see hu.bme.mit.gamma.environment.model.SimulationRule#getSimulation()
	 * @see #getSimulationRule()
	 * @generated
	 */
	EReference getSimulationRule_Simulation();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.environment.model.Filter <em>Filter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Filter</em>'.
	 * @see hu.bme.mit.gamma.environment.model.Filter
	 * @generated
	 */
	EClass getFilter();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.environment.model.Simulation <em>Simulation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Simulation</em>'.
	 * @see hu.bme.mit.gamma.environment.model.Simulation
	 * @generated
	 */
	EClass getSimulation();

	/**
	 * Returns the meta object for the attribute '{@link hu.bme.mit.gamma.environment.model.Simulation#getSimulationClassName <em>Simulation Class Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Simulation Class Name</em>'.
	 * @see hu.bme.mit.gamma.environment.model.Simulation#getSimulationClassName()
	 * @see #getSimulation()
	 * @generated
	 */
	EAttribute getSimulation_SimulationClassName();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.environment.model.EnvironmentPeriodicEventSource <em>Environment Periodic Event Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Environment Periodic Event Source</em>'.
	 * @see hu.bme.mit.gamma.environment.model.EnvironmentPeriodicEventSource
	 * @generated
	 */
	EClass getEnvironmentPeriodicEventSource();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.environment.model.PeriodicSimulation <em>Periodic Simulation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Periodic Simulation</em>'.
	 * @see hu.bme.mit.gamma.environment.model.PeriodicSimulation
	 * @generated
	 */
	EClass getPeriodicSimulation();

	/**
	 * Returns the meta object for the attribute '{@link hu.bme.mit.gamma.environment.model.PeriodicSimulation#getPeriodTime <em>Period Time</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Period Time</em>'.
	 * @see hu.bme.mit.gamma.environment.model.PeriodicSimulation#getPeriodTime()
	 * @see #getPeriodicSimulation()
	 * @generated
	 */
	EAttribute getPeriodicSimulation_PeriodTime();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	EnvironentFactory getEnvironentFactory();

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
		 * The meta object literal for the '{@link hu.bme.mit.gamma.environment.model.impl.EnvironmentEventSourceImpl <em>Environment Event Source</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.environment.model.impl.EnvironmentEventSourceImpl
		 * @see hu.bme.mit.gamma.environment.model.impl.EnvironentPackageImpl#getEnvironmentEventSource()
		 * @generated
		 */
		EClass ENVIRONMENT_EVENT_SOURCE = eINSTANCE.getEnvironmentEventSource();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.environment.model.impl.EnvironmentCompositeComponentImpl <em>Environment Composite Component</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.environment.model.impl.EnvironmentCompositeComponentImpl
		 * @see hu.bme.mit.gamma.environment.model.impl.EnvironentPackageImpl#getEnvironmentCompositeComponent()
		 * @generated
		 */
		EClass ENVIRONMENT_COMPOSITE_COMPONENT = eINSTANCE.getEnvironmentCompositeComponent();

		/**
		 * The meta object literal for the '<em><b>Environment Components</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ENVIRONMENT_COMPOSITE_COMPONENT__ENVIRONMENT_COMPONENTS = eINSTANCE.getEnvironmentCompositeComponent_EnvironmentComponents();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.environment.model.impl.EnvironmentComponentInstanceImpl <em>Environment Component Instance</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.environment.model.impl.EnvironmentComponentInstanceImpl
		 * @see hu.bme.mit.gamma.environment.model.impl.EnvironentPackageImpl#getEnvironmentComponentInstance()
		 * @generated
		 */
		EClass ENVIRONMENT_COMPONENT_INSTANCE = eINSTANCE.getEnvironmentComponentInstance();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.environment.model.impl.EnvironmentCompositeComponentInstanceImpl <em>Environment Composite Component Instance</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.environment.model.impl.EnvironmentCompositeComponentInstanceImpl
		 * @see hu.bme.mit.gamma.environment.model.impl.EnvironentPackageImpl#getEnvironmentCompositeComponentInstance()
		 * @generated
		 */
		EClass ENVIRONMENT_COMPOSITE_COMPONENT_INSTANCE = eINSTANCE.getEnvironmentCompositeComponentInstance();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ENVIRONMENT_COMPOSITE_COMPONENT_INSTANCE__TYPE = eINSTANCE.getEnvironmentCompositeComponentInstance_Type();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.environment.model.impl.EnvironmentDelayImpl <em>Environment Delay</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.environment.model.impl.EnvironmentDelayImpl
		 * @see hu.bme.mit.gamma.environment.model.impl.EnvironentPackageImpl#getEnvironmentDelay()
		 * @generated
		 */
		EClass ENVIRONMENT_DELAY = eINSTANCE.getEnvironmentDelay();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.environment.model.impl.EnvironmentSwitchImpl <em>Environment Switch</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.environment.model.impl.EnvironmentSwitchImpl
		 * @see hu.bme.mit.gamma.environment.model.impl.EnvironentPackageImpl#getEnvironmentSwitch()
		 * @generated
		 */
		EClass ENVIRONMENT_SWITCH = eINSTANCE.getEnvironmentSwitch();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.environment.model.impl.ElementaryEnvironmentComponentInstanceImpl <em>Elementary Environment Component Instance</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.environment.model.impl.ElementaryEnvironmentComponentInstanceImpl
		 * @see hu.bme.mit.gamma.environment.model.impl.EnvironentPackageImpl#getElementaryEnvironmentComponentInstance()
		 * @generated
		 */
		EClass ELEMENTARY_ENVIRONMENT_COMPONENT_INSTANCE = eINSTANCE.getElementaryEnvironmentComponentInstance();

		/**
		 * The meta object literal for the '<em><b>Outports</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ELEMENTARY_ENVIRONMENT_COMPONENT_INSTANCE__OUTPORTS = eINSTANCE.getElementaryEnvironmentComponentInstance_Outports();

		/**
		 * The meta object literal for the '<em><b>Inports</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ELEMENTARY_ENVIRONMENT_COMPONENT_INSTANCE__INPORTS = eINSTANCE.getElementaryEnvironmentComponentInstance_Inports();

		/**
		 * The meta object literal for the '<em><b>Behavior Rules</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ELEMENTARY_ENVIRONMENT_COMPONENT_INSTANCE__BEHAVIOR_RULES = eINSTANCE.getElementaryEnvironmentComponentInstance_BehaviorRules();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.environment.model.impl.EventFilterImpl <em>Event Filter</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.environment.model.impl.EventFilterImpl
		 * @see hu.bme.mit.gamma.environment.model.impl.EnvironentPackageImpl#getEventFilter()
		 * @generated
		 */
		EClass EVENT_FILTER = eINSTANCE.getEventFilter();

		/**
		 * The meta object literal for the '<em><b>Event</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EVENT_FILTER__EVENT = eINSTANCE.getEventFilter_Event();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.environment.model.impl.StochasticRuleImpl <em>Stochastic Rule</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.environment.model.impl.StochasticRuleImpl
		 * @see hu.bme.mit.gamma.environment.model.impl.EnvironentPackageImpl#getStochasticRule()
		 * @generated
		 */
		EClass STOCHASTIC_RULE = eINSTANCE.getStochasticRule();

		/**
		 * The meta object literal for the '<em><b>Stochastic Model</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STOCHASTIC_RULE__STOCHASTIC_MODEL = eINSTANCE.getStochasticRule_StochasticModel();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.environment.model.impl.PortFilterImpl <em>Port Filter</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.environment.model.impl.PortFilterImpl
		 * @see hu.bme.mit.gamma.environment.model.impl.EnvironentPackageImpl#getPortFilter()
		 * @generated
		 */
		EClass PORT_FILTER = eINSTANCE.getPortFilter();

		/**
		 * The meta object literal for the '<em><b>Port</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PORT_FILTER__PORT = eINSTANCE.getPortFilter_Port();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.environment.model.impl.ComponentFilterImpl <em>Component Filter</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.environment.model.impl.ComponentFilterImpl
		 * @see hu.bme.mit.gamma.environment.model.impl.EnvironentPackageImpl#getComponentFilter()
		 * @generated
		 */
		EClass COMPONENT_FILTER = eINSTANCE.getComponentFilter();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.environment.model.impl.EnvironmentRuleImpl <em>Environment Rule</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.environment.model.impl.EnvironmentRuleImpl
		 * @see hu.bme.mit.gamma.environment.model.impl.EnvironentPackageImpl#getEnvironmentRule()
		 * @generated
		 */
		EClass ENVIRONMENT_RULE = eINSTANCE.getEnvironmentRule();

		/**
		 * The meta object literal for the '<em><b>Filter</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ENVIRONMENT_RULE__FILTER = eINSTANCE.getEnvironmentRule_Filter();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.environment.model.impl.EnvironmentSampleImpl <em>Environment Sample</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.environment.model.impl.EnvironmentSampleImpl
		 * @see hu.bme.mit.gamma.environment.model.impl.EnvironentPackageImpl#getEnvironmentSample()
		 * @generated
		 */
		EClass ENVIRONMENT_SAMPLE = eINSTANCE.getEnvironmentSample();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.environment.model.impl.EnvironmentExternSimulationImpl <em>Environment Extern Simulation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.environment.model.impl.EnvironmentExternSimulationImpl
		 * @see hu.bme.mit.gamma.environment.model.impl.EnvironentPackageImpl#getEnvironmentExternSimulation()
		 * @generated
		 */
		EClass ENVIRONMENT_EXTERN_SIMULATION = eINSTANCE.getEnvironmentExternSimulation();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.environment.model.impl.SimulationRuleImpl <em>Simulation Rule</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.environment.model.impl.SimulationRuleImpl
		 * @see hu.bme.mit.gamma.environment.model.impl.EnvironentPackageImpl#getSimulationRule()
		 * @generated
		 */
		EClass SIMULATION_RULE = eINSTANCE.getSimulationRule();

		/**
		 * The meta object literal for the '<em><b>Simulation</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SIMULATION_RULE__SIMULATION = eINSTANCE.getSimulationRule_Simulation();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.environment.model.impl.FilterImpl <em>Filter</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.environment.model.impl.FilterImpl
		 * @see hu.bme.mit.gamma.environment.model.impl.EnvironentPackageImpl#getFilter()
		 * @generated
		 */
		EClass FILTER = eINSTANCE.getFilter();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.environment.model.impl.SimulationImpl <em>Simulation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.environment.model.impl.SimulationImpl
		 * @see hu.bme.mit.gamma.environment.model.impl.EnvironentPackageImpl#getSimulation()
		 * @generated
		 */
		EClass SIMULATION = eINSTANCE.getSimulation();

		/**
		 * The meta object literal for the '<em><b>Simulation Class Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SIMULATION__SIMULATION_CLASS_NAME = eINSTANCE.getSimulation_SimulationClassName();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.environment.model.impl.EnvironmentPeriodicEventSourceImpl <em>Environment Periodic Event Source</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.environment.model.impl.EnvironmentPeriodicEventSourceImpl
		 * @see hu.bme.mit.gamma.environment.model.impl.EnvironentPackageImpl#getEnvironmentPeriodicEventSource()
		 * @generated
		 */
		EClass ENVIRONMENT_PERIODIC_EVENT_SOURCE = eINSTANCE.getEnvironmentPeriodicEventSource();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.environment.model.impl.PeriodicSimulationImpl <em>Periodic Simulation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.environment.model.impl.PeriodicSimulationImpl
		 * @see hu.bme.mit.gamma.environment.model.impl.EnvironentPackageImpl#getPeriodicSimulation()
		 * @generated
		 */
		EClass PERIODIC_SIMULATION = eINSTANCE.getPeriodicSimulation();

		/**
		 * The meta object literal for the '<em><b>Period Time</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PERIODIC_SIMULATION__PERIOD_TIME = eINSTANCE.getPeriodicSimulation_PeriodTime();

	}

} //EnvironentPackage
