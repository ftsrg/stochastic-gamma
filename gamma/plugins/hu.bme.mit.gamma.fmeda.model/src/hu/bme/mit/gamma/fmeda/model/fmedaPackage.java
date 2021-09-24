/**
 */
package hu.bme.mit.gamma.fmeda.model;

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
 * @see hu.bme.mit.gamma.fmeda.model.fmedaFactory
 * @model kind="package"
 * @generated
 */
public interface fmedaPackage extends EPackage {
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
	String eNS_URI = "www.mit.bme.hu/gamma/fmeda/Model";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "hu.bme.mit.gamma.fmeda";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	fmedaPackage eINSTANCE = hu.bme.mit.gamma.fmeda.model.impl.fmedaPackageImpl.init();

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.fmeda.model.impl.NamedElementImpl <em>Named Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.fmeda.model.impl.NamedElementImpl
	 * @see hu.bme.mit.gamma.fmeda.model.impl.fmedaPackageImpl#getNamedElement()
	 * @generated
	 */
	int NAMED_ELEMENT = 6;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_ELEMENT__NAME = 0;

	/**
	 * The number of structural features of the '<em>Named Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_ELEMENT_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Named Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_ELEMENT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.fmeda.model.impl.FMEDAComponentImpl <em>FMEDA Component</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.fmeda.model.impl.FMEDAComponentImpl
	 * @see hu.bme.mit.gamma.fmeda.model.impl.fmedaPackageImpl#getFMEDAComponent()
	 * @generated
	 */
	int FMEDA_COMPONENT = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FMEDA_COMPONENT__NAME = NAMED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Failuremodes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FMEDA_COMPONENT__FAILUREMODES = NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Subcomponents</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FMEDA_COMPONENT__SUBCOMPONENTS = NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Failurepropagations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FMEDA_COMPONENT__FAILUREPROPAGATIONS = NAMED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Channels</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FMEDA_COMPONENT__CHANNELS = NAMED_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Ports</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FMEDA_COMPONENT__PORTS = NAMED_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>FMEDA Component</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FMEDA_COMPONENT_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The number of operations of the '<em>FMEDA Component</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FMEDA_COMPONENT_OPERATION_COUNT = NAMED_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.fmeda.model.impl.FMEDAComponentInstanceImpl <em>FMEDA Component Instance</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.fmeda.model.impl.FMEDAComponentInstanceImpl
	 * @see hu.bme.mit.gamma.fmeda.model.impl.fmedaPackageImpl#getFMEDAComponentInstance()
	 * @generated
	 */
	int FMEDA_COMPONENT_INSTANCE = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FMEDA_COMPONENT_INSTANCE__NAME = NAMED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FMEDA_COMPONENT_INSTANCE__TYPE = NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>FMEDA Component Instance</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FMEDA_COMPONENT_INSTANCE_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>FMEDA Component Instance</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FMEDA_COMPONENT_INSTANCE_OPERATION_COUNT = NAMED_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.fmeda.model.impl.FailureModeImpl <em>Failure Mode</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.fmeda.model.impl.FailureModeImpl
	 * @see hu.bme.mit.gamma.fmeda.model.impl.fmedaPackageImpl#getFailureMode()
	 * @generated
	 */
	int FAILURE_MODE = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FAILURE_MODE__NAME = NAMED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Diagnostics</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FAILURE_MODE__DIAGNOSTICS = NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Failure Mode</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FAILURE_MODE_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Failure Mode</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FAILURE_MODE_OPERATION_COUNT = NAMED_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.fmeda.model.impl.FailureModeReferenceImpl <em>Failure Mode Reference</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.fmeda.model.impl.FailureModeReferenceImpl
	 * @see hu.bme.mit.gamma.fmeda.model.impl.fmedaPackageImpl#getFailureModeReference()
	 * @generated
	 */
	int FAILURE_MODE_REFERENCE = 7;

	/**
	 * The feature id for the '<em><b>Failuremode</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FAILURE_MODE_REFERENCE__FAILUREMODE = 0;

	/**
	 * The number of structural features of the '<em>Failure Mode Reference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FAILURE_MODE_REFERENCE_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Failure Mode Reference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FAILURE_MODE_REFERENCE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.fmeda.model.impl.HardwarePartFailureModeReferenceImpl <em>Hardware Part Failure Mode Reference</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.fmeda.model.impl.HardwarePartFailureModeReferenceImpl
	 * @see hu.bme.mit.gamma.fmeda.model.impl.fmedaPackageImpl#getHardwarePartFailureModeReference()
	 * @generated
	 */
	int HARDWARE_PART_FAILURE_MODE_REFERENCE = 3;

	/**
	 * The feature id for the '<em><b>Failuremode</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HARDWARE_PART_FAILURE_MODE_REFERENCE__FAILUREMODE = FAILURE_MODE_REFERENCE__FAILUREMODE;

	/**
	 * The feature id for the '<em><b>Part</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HARDWARE_PART_FAILURE_MODE_REFERENCE__PART = FAILURE_MODE_REFERENCE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Hardware Part Failure Mode Reference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HARDWARE_PART_FAILURE_MODE_REFERENCE_FEATURE_COUNT = FAILURE_MODE_REFERENCE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Hardware Part Failure Mode Reference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HARDWARE_PART_FAILURE_MODE_REFERENCE_OPERATION_COUNT = FAILURE_MODE_REFERENCE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.fmeda.model.impl.FailurePropagationImpl <em>Failure Propagation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.fmeda.model.impl.FailurePropagationImpl
	 * @see hu.bme.mit.gamma.fmeda.model.impl.fmedaPackageImpl#getFailurePropagation()
	 * @generated
	 */
	int FAILURE_PROPAGATION = 4;

	/**
	 * The feature id for the '<em><b>Cause</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FAILURE_PROPAGATION__CAUSE = 0;

	/**
	 * The feature id for the '<em><b>Effect</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FAILURE_PROPAGATION__EFFECT = 1;

	/**
	 * The number of structural features of the '<em>Failure Propagation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FAILURE_PROPAGATION_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Failure Propagation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FAILURE_PROPAGATION_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.fmeda.model.impl.PackageImpl <em>Package</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.fmeda.model.impl.PackageImpl
	 * @see hu.bme.mit.gamma.fmeda.model.impl.fmedaPackageImpl#getPackage()
	 * @generated
	 */
	int PACKAGE = 5;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE__NAME = NAMED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Components</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE__COMPONENTS = NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Diagnostics</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE__DIAGNOSTICS = NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Interfaces</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE__INTERFACES = NAMED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Package</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Package</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_OPERATION_COUNT = NAMED_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.fmeda.model.impl.ThisPartFailureModeReferenceImpl <em>This Part Failure Mode Reference</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.fmeda.model.impl.ThisPartFailureModeReferenceImpl
	 * @see hu.bme.mit.gamma.fmeda.model.impl.fmedaPackageImpl#getThisPartFailureModeReference()
	 * @generated
	 */
	int THIS_PART_FAILURE_MODE_REFERENCE = 8;

	/**
	 * The feature id for the '<em><b>Failuremode</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int THIS_PART_FAILURE_MODE_REFERENCE__FAILUREMODE = FAILURE_MODE_REFERENCE__FAILUREMODE;

	/**
	 * The number of structural features of the '<em>This Part Failure Mode Reference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int THIS_PART_FAILURE_MODE_REFERENCE_FEATURE_COUNT = FAILURE_MODE_REFERENCE_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>This Part Failure Mode Reference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int THIS_PART_FAILURE_MODE_REFERENCE_OPERATION_COUNT = FAILURE_MODE_REFERENCE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.fmeda.model.impl.FMEDADiagnosticsImpl <em>FMEDA Diagnostics</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.fmeda.model.impl.FMEDADiagnosticsImpl
	 * @see hu.bme.mit.gamma.fmeda.model.impl.fmedaPackageImpl#getFMEDADiagnostics()
	 * @generated
	 */
	int FMEDA_DIAGNOSTICS = 9;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FMEDA_DIAGNOSTICS__NAME = NAMED_ELEMENT__NAME;

	/**
	 * The number of structural features of the '<em>FMEDA Diagnostics</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FMEDA_DIAGNOSTICS_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>FMEDA Diagnostics</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FMEDA_DIAGNOSTICS_OPERATION_COUNT = NAMED_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.fmeda.model.impl.DiagnosticsReferenceImpl <em>Diagnostics Reference</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.fmeda.model.impl.DiagnosticsReferenceImpl
	 * @see hu.bme.mit.gamma.fmeda.model.impl.fmedaPackageImpl#getDiagnosticsReference()
	 * @generated
	 */
	int DIAGNOSTICS_REFERENCE = 10;

	/**
	 * The feature id for the '<em><b>Coverage</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGNOSTICS_REFERENCE__COVERAGE = 0;

	/**
	 * The feature id for the '<em><b>Fmedadiagnostics</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGNOSTICS_REFERENCE__FMEDADIAGNOSTICS = 1;

	/**
	 * The number of structural features of the '<em>Diagnostics Reference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGNOSTICS_REFERENCE_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Diagnostics Reference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGNOSTICS_REFERENCE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.fmeda.model.impl.PortFailureModeReferenceImpl <em>Port Failure Mode Reference</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.fmeda.model.impl.PortFailureModeReferenceImpl
	 * @see hu.bme.mit.gamma.fmeda.model.impl.fmedaPackageImpl#getPortFailureModeReference()
	 * @generated
	 */
	int PORT_FAILURE_MODE_REFERENCE = 11;

	/**
	 * The feature id for the '<em><b>Failuremode</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_FAILURE_MODE_REFERENCE__FAILUREMODE = FAILURE_MODE_REFERENCE__FAILUREMODE;

	/**
	 * The feature id for the '<em><b>Port</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_FAILURE_MODE_REFERENCE__PORT = FAILURE_MODE_REFERENCE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Part</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_FAILURE_MODE_REFERENCE__PART = FAILURE_MODE_REFERENCE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Port Failure Mode Reference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_FAILURE_MODE_REFERENCE_FEATURE_COUNT = FAILURE_MODE_REFERENCE_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Port Failure Mode Reference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_FAILURE_MODE_REFERENCE_OPERATION_COUNT = FAILURE_MODE_REFERENCE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.fmeda.model.impl.InterfaceImpl <em>Interface</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.fmeda.model.impl.InterfaceImpl
	 * @see hu.bme.mit.gamma.fmeda.model.impl.fmedaPackageImpl#getInterface()
	 * @generated
	 */
	int INTERFACE = 12;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERFACE__NAME = NAMED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Failuremodes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERFACE__FAILUREMODES = NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Interface</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERFACE_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Interface</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERFACE_OPERATION_COUNT = NAMED_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.fmeda.model.impl.ThisPortFailureModeReferenceImpl <em>This Port Failure Mode Reference</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.fmeda.model.impl.ThisPortFailureModeReferenceImpl
	 * @see hu.bme.mit.gamma.fmeda.model.impl.fmedaPackageImpl#getThisPortFailureModeReference()
	 * @generated
	 */
	int THIS_PORT_FAILURE_MODE_REFERENCE = 13;

	/**
	 * The feature id for the '<em><b>Failuremode</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int THIS_PORT_FAILURE_MODE_REFERENCE__FAILUREMODE = FAILURE_MODE_REFERENCE__FAILUREMODE;

	/**
	 * The feature id for the '<em><b>Port</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int THIS_PORT_FAILURE_MODE_REFERENCE__PORT = FAILURE_MODE_REFERENCE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>This Port Failure Mode Reference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int THIS_PORT_FAILURE_MODE_REFERENCE_FEATURE_COUNT = FAILURE_MODE_REFERENCE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>This Port Failure Mode Reference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int THIS_PORT_FAILURE_MODE_REFERENCE_OPERATION_COUNT = FAILURE_MODE_REFERENCE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.fmeda.model.impl.PortReferenceImpl <em>Port Reference</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.fmeda.model.impl.PortReferenceImpl
	 * @see hu.bme.mit.gamma.fmeda.model.impl.fmedaPackageImpl#getPortReference()
	 * @generated
	 */
	int PORT_REFERENCE = 14;

	/**
	 * The feature id for the '<em><b>Port</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_REFERENCE__PORT = 0;

	/**
	 * The number of structural features of the '<em>Port Reference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_REFERENCE_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Port Reference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_REFERENCE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.fmeda.model.impl.ComponentReferenceImpl <em>Component Reference</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.fmeda.model.impl.ComponentReferenceImpl
	 * @see hu.bme.mit.gamma.fmeda.model.impl.fmedaPackageImpl#getComponentReference()
	 * @generated
	 */
	int COMPONENT_REFERENCE = 15;

	/**
	 * The feature id for the '<em><b>Part</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_REFERENCE__PART = 0;

	/**
	 * The number of structural features of the '<em>Component Reference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_REFERENCE_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Component Reference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_REFERENCE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.fmeda.model.impl.ChannelPortReferenceImpl <em>Channel Port Reference</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.fmeda.model.impl.ChannelPortReferenceImpl
	 * @see hu.bme.mit.gamma.fmeda.model.impl.fmedaPackageImpl#getChannelPortReference()
	 * @generated
	 */
	int CHANNEL_PORT_REFERENCE = 19;

	/**
	 * The feature id for the '<em><b>Port</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHANNEL_PORT_REFERENCE__PORT = PORT_REFERENCE__PORT;

	/**
	 * The number of structural features of the '<em>Channel Port Reference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHANNEL_PORT_REFERENCE_FEATURE_COUNT = PORT_REFERENCE_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Channel Port Reference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHANNEL_PORT_REFERENCE_OPERATION_COUNT = PORT_REFERENCE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.fmeda.model.impl.ThisPortReferenceImpl <em>This Port Reference</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.fmeda.model.impl.ThisPortReferenceImpl
	 * @see hu.bme.mit.gamma.fmeda.model.impl.fmedaPackageImpl#getThisPortReference()
	 * @generated
	 */
	int THIS_PORT_REFERENCE = 16;

	/**
	 * The feature id for the '<em><b>Port</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int THIS_PORT_REFERENCE__PORT = CHANNEL_PORT_REFERENCE__PORT;

	/**
	 * The number of structural features of the '<em>This Port Reference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int THIS_PORT_REFERENCE_FEATURE_COUNT = CHANNEL_PORT_REFERENCE_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>This Port Reference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int THIS_PORT_REFERENCE_OPERATION_COUNT = CHANNEL_PORT_REFERENCE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.fmeda.model.impl.HardwarePartPortReferenceImpl <em>Hardware Part Port Reference</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.fmeda.model.impl.HardwarePartPortReferenceImpl
	 * @see hu.bme.mit.gamma.fmeda.model.impl.fmedaPackageImpl#getHardwarePartPortReference()
	 * @generated
	 */
	int HARDWARE_PART_PORT_REFERENCE = 17;

	/**
	 * The feature id for the '<em><b>Part</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HARDWARE_PART_PORT_REFERENCE__PART = COMPONENT_REFERENCE__PART;

	/**
	 * The feature id for the '<em><b>Port</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HARDWARE_PART_PORT_REFERENCE__PORT = COMPONENT_REFERENCE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Hardware Part Port Reference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HARDWARE_PART_PORT_REFERENCE_FEATURE_COUNT = COMPONENT_REFERENCE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Hardware Part Port Reference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HARDWARE_PART_PORT_REFERENCE_OPERATION_COUNT = COMPONENT_REFERENCE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.fmeda.model.impl.ChannelImpl <em>Channel</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.fmeda.model.impl.ChannelImpl
	 * @see hu.bme.mit.gamma.fmeda.model.impl.fmedaPackageImpl#getChannel()
	 * @generated
	 */
	int CHANNEL = 18;

	/**
	 * The feature id for the '<em><b>From</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHANNEL__FROM = 0;

	/**
	 * The feature id for the '<em><b>To</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHANNEL__TO = 1;

	/**
	 * The number of structural features of the '<em>Channel</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHANNEL_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Channel</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHANNEL_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.fmeda.model.impl.PortImpl <em>Port</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.fmeda.model.impl.PortImpl
	 * @see hu.bme.mit.gamma.fmeda.model.impl.fmedaPackageImpl#getPort()
	 * @generated
	 */
	int PORT = 20;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT__NAME = NAMED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT__TYPE = NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Port</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Port</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_OPERATION_COUNT = NAMED_ELEMENT_OPERATION_COUNT + 0;


	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.fmeda.model.impl.PartFailureModeImpl <em>Part Failure Mode</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.fmeda.model.impl.PartFailureModeImpl
	 * @see hu.bme.mit.gamma.fmeda.model.impl.fmedaPackageImpl#getPartFailureMode()
	 * @generated
	 */
	int PART_FAILURE_MODE = 21;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PART_FAILURE_MODE__NAME = FAILURE_MODE__NAME;

	/**
	 * The feature id for the '<em><b>Diagnostics</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PART_FAILURE_MODE__DIAGNOSTICS = FAILURE_MODE__DIAGNOSTICS;

	/**
	 * The feature id for the '<em><b>Failure Rate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PART_FAILURE_MODE__FAILURE_RATE = FAILURE_MODE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Part Failure Mode</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PART_FAILURE_MODE_FEATURE_COUNT = FAILURE_MODE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Part Failure Mode</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PART_FAILURE_MODE_OPERATION_COUNT = FAILURE_MODE_OPERATION_COUNT + 0;


	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.fmeda.model.FMEDAComponent <em>FMEDA Component</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>FMEDA Component</em>'.
	 * @see hu.bme.mit.gamma.fmeda.model.FMEDAComponent
	 * @generated
	 */
	EClass getFMEDAComponent();

	/**
	 * Returns the meta object for the containment reference list '{@link hu.bme.mit.gamma.fmeda.model.FMEDAComponent#getFailuremodes <em>Failuremodes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Failuremodes</em>'.
	 * @see hu.bme.mit.gamma.fmeda.model.FMEDAComponent#getFailuremodes()
	 * @see #getFMEDAComponent()
	 * @generated
	 */
	EReference getFMEDAComponent_Failuremodes();

	/**
	 * Returns the meta object for the containment reference list '{@link hu.bme.mit.gamma.fmeda.model.FMEDAComponent#getSubcomponents <em>Subcomponents</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Subcomponents</em>'.
	 * @see hu.bme.mit.gamma.fmeda.model.FMEDAComponent#getSubcomponents()
	 * @see #getFMEDAComponent()
	 * @generated
	 */
	EReference getFMEDAComponent_Subcomponents();

	/**
	 * Returns the meta object for the containment reference list '{@link hu.bme.mit.gamma.fmeda.model.FMEDAComponent#getFailurepropagations <em>Failurepropagations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Failurepropagations</em>'.
	 * @see hu.bme.mit.gamma.fmeda.model.FMEDAComponent#getFailurepropagations()
	 * @see #getFMEDAComponent()
	 * @generated
	 */
	EReference getFMEDAComponent_Failurepropagations();

	/**
	 * Returns the meta object for the containment reference list '{@link hu.bme.mit.gamma.fmeda.model.FMEDAComponent#getChannels <em>Channels</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Channels</em>'.
	 * @see hu.bme.mit.gamma.fmeda.model.FMEDAComponent#getChannels()
	 * @see #getFMEDAComponent()
	 * @generated
	 */
	EReference getFMEDAComponent_Channels();

	/**
	 * Returns the meta object for the containment reference list '{@link hu.bme.mit.gamma.fmeda.model.FMEDAComponent#getPorts <em>Ports</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Ports</em>'.
	 * @see hu.bme.mit.gamma.fmeda.model.FMEDAComponent#getPorts()
	 * @see #getFMEDAComponent()
	 * @generated
	 */
	EReference getFMEDAComponent_Ports();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.fmeda.model.FMEDAComponentInstance <em>FMEDA Component Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>FMEDA Component Instance</em>'.
	 * @see hu.bme.mit.gamma.fmeda.model.FMEDAComponentInstance
	 * @generated
	 */
	EClass getFMEDAComponentInstance();

	/**
	 * Returns the meta object for the reference '{@link hu.bme.mit.gamma.fmeda.model.FMEDAComponentInstance#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Type</em>'.
	 * @see hu.bme.mit.gamma.fmeda.model.FMEDAComponentInstance#getType()
	 * @see #getFMEDAComponentInstance()
	 * @generated
	 */
	EReference getFMEDAComponentInstance_Type();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.fmeda.model.FailureMode <em>Failure Mode</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Failure Mode</em>'.
	 * @see hu.bme.mit.gamma.fmeda.model.FailureMode
	 * @generated
	 */
	EClass getFailureMode();

	/**
	 * Returns the meta object for the containment reference '{@link hu.bme.mit.gamma.fmeda.model.FailureMode#getDiagnostics <em>Diagnostics</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Diagnostics</em>'.
	 * @see hu.bme.mit.gamma.fmeda.model.FailureMode#getDiagnostics()
	 * @see #getFailureMode()
	 * @generated
	 */
	EReference getFailureMode_Diagnostics();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.fmeda.model.HardwarePartFailureModeReference <em>Hardware Part Failure Mode Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Hardware Part Failure Mode Reference</em>'.
	 * @see hu.bme.mit.gamma.fmeda.model.HardwarePartFailureModeReference
	 * @generated
	 */
	EClass getHardwarePartFailureModeReference();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.fmeda.model.FailurePropagation <em>Failure Propagation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Failure Propagation</em>'.
	 * @see hu.bme.mit.gamma.fmeda.model.FailurePropagation
	 * @generated
	 */
	EClass getFailurePropagation();

	/**
	 * Returns the meta object for the containment reference '{@link hu.bme.mit.gamma.fmeda.model.FailurePropagation#getCause <em>Cause</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Cause</em>'.
	 * @see hu.bme.mit.gamma.fmeda.model.FailurePropagation#getCause()
	 * @see #getFailurePropagation()
	 * @generated
	 */
	EReference getFailurePropagation_Cause();

	/**
	 * Returns the meta object for the containment reference list '{@link hu.bme.mit.gamma.fmeda.model.FailurePropagation#getEffect <em>Effect</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Effect</em>'.
	 * @see hu.bme.mit.gamma.fmeda.model.FailurePropagation#getEffect()
	 * @see #getFailurePropagation()
	 * @generated
	 */
	EReference getFailurePropagation_Effect();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.fmeda.model.Package <em>Package</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Package</em>'.
	 * @see hu.bme.mit.gamma.fmeda.model.Package
	 * @generated
	 */
	EClass getPackage();

	/**
	 * Returns the meta object for the containment reference list '{@link hu.bme.mit.gamma.fmeda.model.Package#getComponents <em>Components</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Components</em>'.
	 * @see hu.bme.mit.gamma.fmeda.model.Package#getComponents()
	 * @see #getPackage()
	 * @generated
	 */
	EReference getPackage_Components();

	/**
	 * Returns the meta object for the containment reference list '{@link hu.bme.mit.gamma.fmeda.model.Package#getDiagnostics <em>Diagnostics</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Diagnostics</em>'.
	 * @see hu.bme.mit.gamma.fmeda.model.Package#getDiagnostics()
	 * @see #getPackage()
	 * @generated
	 */
	EReference getPackage_Diagnostics();

	/**
	 * Returns the meta object for the containment reference list '{@link hu.bme.mit.gamma.fmeda.model.Package#getInterfaces <em>Interfaces</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Interfaces</em>'.
	 * @see hu.bme.mit.gamma.fmeda.model.Package#getInterfaces()
	 * @see #getPackage()
	 * @generated
	 */
	EReference getPackage_Interfaces();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.fmeda.model.NamedElement <em>Named Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Named Element</em>'.
	 * @see hu.bme.mit.gamma.fmeda.model.NamedElement
	 * @generated
	 */
	EClass getNamedElement();

	/**
	 * Returns the meta object for the attribute '{@link hu.bme.mit.gamma.fmeda.model.NamedElement#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see hu.bme.mit.gamma.fmeda.model.NamedElement#getName()
	 * @see #getNamedElement()
	 * @generated
	 */
	EAttribute getNamedElement_Name();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.fmeda.model.FailureModeReference <em>Failure Mode Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Failure Mode Reference</em>'.
	 * @see hu.bme.mit.gamma.fmeda.model.FailureModeReference
	 * @generated
	 */
	EClass getFailureModeReference();

	/**
	 * Returns the meta object for the reference '{@link hu.bme.mit.gamma.fmeda.model.FailureModeReference#getFailuremode <em>Failuremode</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Failuremode</em>'.
	 * @see hu.bme.mit.gamma.fmeda.model.FailureModeReference#getFailuremode()
	 * @see #getFailureModeReference()
	 * @generated
	 */
	EReference getFailureModeReference_Failuremode();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.fmeda.model.ThisPartFailureModeReference <em>This Part Failure Mode Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>This Part Failure Mode Reference</em>'.
	 * @see hu.bme.mit.gamma.fmeda.model.ThisPartFailureModeReference
	 * @generated
	 */
	EClass getThisPartFailureModeReference();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.fmeda.model.FMEDADiagnostics <em>FMEDA Diagnostics</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>FMEDA Diagnostics</em>'.
	 * @see hu.bme.mit.gamma.fmeda.model.FMEDADiagnostics
	 * @generated
	 */
	EClass getFMEDADiagnostics();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.fmeda.model.DiagnosticsReference <em>Diagnostics Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Diagnostics Reference</em>'.
	 * @see hu.bme.mit.gamma.fmeda.model.DiagnosticsReference
	 * @generated
	 */
	EClass getDiagnosticsReference();

	/**
	 * Returns the meta object for the attribute '{@link hu.bme.mit.gamma.fmeda.model.DiagnosticsReference#getCoverage <em>Coverage</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Coverage</em>'.
	 * @see hu.bme.mit.gamma.fmeda.model.DiagnosticsReference#getCoverage()
	 * @see #getDiagnosticsReference()
	 * @generated
	 */
	EAttribute getDiagnosticsReference_Coverage();

	/**
	 * Returns the meta object for the reference list '{@link hu.bme.mit.gamma.fmeda.model.DiagnosticsReference#getFmedadiagnostics <em>Fmedadiagnostics</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Fmedadiagnostics</em>'.
	 * @see hu.bme.mit.gamma.fmeda.model.DiagnosticsReference#getFmedadiagnostics()
	 * @see #getDiagnosticsReference()
	 * @generated
	 */
	EReference getDiagnosticsReference_Fmedadiagnostics();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.fmeda.model.PortFailureModeReference <em>Port Failure Mode Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Port Failure Mode Reference</em>'.
	 * @see hu.bme.mit.gamma.fmeda.model.PortFailureModeReference
	 * @generated
	 */
	EClass getPortFailureModeReference();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.fmeda.model.Interface <em>Interface</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Interface</em>'.
	 * @see hu.bme.mit.gamma.fmeda.model.Interface
	 * @generated
	 */
	EClass getInterface();

	/**
	 * Returns the meta object for the containment reference list '{@link hu.bme.mit.gamma.fmeda.model.Interface#getFailuremodes <em>Failuremodes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Failuremodes</em>'.
	 * @see hu.bme.mit.gamma.fmeda.model.Interface#getFailuremodes()
	 * @see #getInterface()
	 * @generated
	 */
	EReference getInterface_Failuremodes();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.fmeda.model.ThisPortFailureModeReference <em>This Port Failure Mode Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>This Port Failure Mode Reference</em>'.
	 * @see hu.bme.mit.gamma.fmeda.model.ThisPortFailureModeReference
	 * @generated
	 */
	EClass getThisPortFailureModeReference();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.fmeda.model.PortReference <em>Port Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Port Reference</em>'.
	 * @see hu.bme.mit.gamma.fmeda.model.PortReference
	 * @generated
	 */
	EClass getPortReference();

	/**
	 * Returns the meta object for the reference '{@link hu.bme.mit.gamma.fmeda.model.PortReference#getPort <em>Port</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Port</em>'.
	 * @see hu.bme.mit.gamma.fmeda.model.PortReference#getPort()
	 * @see #getPortReference()
	 * @generated
	 */
	EReference getPortReference_Port();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.fmeda.model.ComponentReference <em>Component Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Component Reference</em>'.
	 * @see hu.bme.mit.gamma.fmeda.model.ComponentReference
	 * @generated
	 */
	EClass getComponentReference();

	/**
	 * Returns the meta object for the reference '{@link hu.bme.mit.gamma.fmeda.model.ComponentReference#getPart <em>Part</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Part</em>'.
	 * @see hu.bme.mit.gamma.fmeda.model.ComponentReference#getPart()
	 * @see #getComponentReference()
	 * @generated
	 */
	EReference getComponentReference_Part();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.fmeda.model.ThisPortReference <em>This Port Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>This Port Reference</em>'.
	 * @see hu.bme.mit.gamma.fmeda.model.ThisPortReference
	 * @generated
	 */
	EClass getThisPortReference();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.fmeda.model.HardwarePartPortReference <em>Hardware Part Port Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Hardware Part Port Reference</em>'.
	 * @see hu.bme.mit.gamma.fmeda.model.HardwarePartPortReference
	 * @generated
	 */
	EClass getHardwarePartPortReference();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.fmeda.model.Channel <em>Channel</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Channel</em>'.
	 * @see hu.bme.mit.gamma.fmeda.model.Channel
	 * @generated
	 */
	EClass getChannel();

	/**
	 * Returns the meta object for the containment reference '{@link hu.bme.mit.gamma.fmeda.model.Channel#getFrom <em>From</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>From</em>'.
	 * @see hu.bme.mit.gamma.fmeda.model.Channel#getFrom()
	 * @see #getChannel()
	 * @generated
	 */
	EReference getChannel_From();

	/**
	 * Returns the meta object for the containment reference list '{@link hu.bme.mit.gamma.fmeda.model.Channel#getTo <em>To</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>To</em>'.
	 * @see hu.bme.mit.gamma.fmeda.model.Channel#getTo()
	 * @see #getChannel()
	 * @generated
	 */
	EReference getChannel_To();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.fmeda.model.ChannelPortReference <em>Channel Port Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Channel Port Reference</em>'.
	 * @see hu.bme.mit.gamma.fmeda.model.ChannelPortReference
	 * @generated
	 */
	EClass getChannelPortReference();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.fmeda.model.Port <em>Port</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Port</em>'.
	 * @see hu.bme.mit.gamma.fmeda.model.Port
	 * @generated
	 */
	EClass getPort();

	/**
	 * Returns the meta object for the reference '{@link hu.bme.mit.gamma.fmeda.model.Port#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Type</em>'.
	 * @see hu.bme.mit.gamma.fmeda.model.Port#getType()
	 * @see #getPort()
	 * @generated
	 */
	EReference getPort_Type();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.fmeda.model.PartFailureMode <em>Part Failure Mode</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Part Failure Mode</em>'.
	 * @see hu.bme.mit.gamma.fmeda.model.PartFailureMode
	 * @generated
	 */
	EClass getPartFailureMode();

	/**
	 * Returns the meta object for the attribute '{@link hu.bme.mit.gamma.fmeda.model.PartFailureMode#getFailureRate <em>Failure Rate</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Failure Rate</em>'.
	 * @see hu.bme.mit.gamma.fmeda.model.PartFailureMode#getFailureRate()
	 * @see #getPartFailureMode()
	 * @generated
	 */
	EAttribute getPartFailureMode_FailureRate();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	fmedaFactory getfmedaFactory();

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
		 * The meta object literal for the '{@link hu.bme.mit.gamma.fmeda.model.impl.FMEDAComponentImpl <em>FMEDA Component</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.fmeda.model.impl.FMEDAComponentImpl
		 * @see hu.bme.mit.gamma.fmeda.model.impl.fmedaPackageImpl#getFMEDAComponent()
		 * @generated
		 */
		EClass FMEDA_COMPONENT = eINSTANCE.getFMEDAComponent();

		/**
		 * The meta object literal for the '<em><b>Failuremodes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FMEDA_COMPONENT__FAILUREMODES = eINSTANCE.getFMEDAComponent_Failuremodes();

		/**
		 * The meta object literal for the '<em><b>Subcomponents</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FMEDA_COMPONENT__SUBCOMPONENTS = eINSTANCE.getFMEDAComponent_Subcomponents();

		/**
		 * The meta object literal for the '<em><b>Failurepropagations</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FMEDA_COMPONENT__FAILUREPROPAGATIONS = eINSTANCE.getFMEDAComponent_Failurepropagations();

		/**
		 * The meta object literal for the '<em><b>Channels</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FMEDA_COMPONENT__CHANNELS = eINSTANCE.getFMEDAComponent_Channels();

		/**
		 * The meta object literal for the '<em><b>Ports</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FMEDA_COMPONENT__PORTS = eINSTANCE.getFMEDAComponent_Ports();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.fmeda.model.impl.FMEDAComponentInstanceImpl <em>FMEDA Component Instance</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.fmeda.model.impl.FMEDAComponentInstanceImpl
		 * @see hu.bme.mit.gamma.fmeda.model.impl.fmedaPackageImpl#getFMEDAComponentInstance()
		 * @generated
		 */
		EClass FMEDA_COMPONENT_INSTANCE = eINSTANCE.getFMEDAComponentInstance();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FMEDA_COMPONENT_INSTANCE__TYPE = eINSTANCE.getFMEDAComponentInstance_Type();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.fmeda.model.impl.FailureModeImpl <em>Failure Mode</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.fmeda.model.impl.FailureModeImpl
		 * @see hu.bme.mit.gamma.fmeda.model.impl.fmedaPackageImpl#getFailureMode()
		 * @generated
		 */
		EClass FAILURE_MODE = eINSTANCE.getFailureMode();

		/**
		 * The meta object literal for the '<em><b>Diagnostics</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FAILURE_MODE__DIAGNOSTICS = eINSTANCE.getFailureMode_Diagnostics();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.fmeda.model.impl.HardwarePartFailureModeReferenceImpl <em>Hardware Part Failure Mode Reference</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.fmeda.model.impl.HardwarePartFailureModeReferenceImpl
		 * @see hu.bme.mit.gamma.fmeda.model.impl.fmedaPackageImpl#getHardwarePartFailureModeReference()
		 * @generated
		 */
		EClass HARDWARE_PART_FAILURE_MODE_REFERENCE = eINSTANCE.getHardwarePartFailureModeReference();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.fmeda.model.impl.FailurePropagationImpl <em>Failure Propagation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.fmeda.model.impl.FailurePropagationImpl
		 * @see hu.bme.mit.gamma.fmeda.model.impl.fmedaPackageImpl#getFailurePropagation()
		 * @generated
		 */
		EClass FAILURE_PROPAGATION = eINSTANCE.getFailurePropagation();

		/**
		 * The meta object literal for the '<em><b>Cause</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FAILURE_PROPAGATION__CAUSE = eINSTANCE.getFailurePropagation_Cause();

		/**
		 * The meta object literal for the '<em><b>Effect</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FAILURE_PROPAGATION__EFFECT = eINSTANCE.getFailurePropagation_Effect();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.fmeda.model.impl.PackageImpl <em>Package</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.fmeda.model.impl.PackageImpl
		 * @see hu.bme.mit.gamma.fmeda.model.impl.fmedaPackageImpl#getPackage()
		 * @generated
		 */
		EClass PACKAGE = eINSTANCE.getPackage();

		/**
		 * The meta object literal for the '<em><b>Components</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PACKAGE__COMPONENTS = eINSTANCE.getPackage_Components();

		/**
		 * The meta object literal for the '<em><b>Diagnostics</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PACKAGE__DIAGNOSTICS = eINSTANCE.getPackage_Diagnostics();

		/**
		 * The meta object literal for the '<em><b>Interfaces</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PACKAGE__INTERFACES = eINSTANCE.getPackage_Interfaces();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.fmeda.model.impl.NamedElementImpl <em>Named Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.fmeda.model.impl.NamedElementImpl
		 * @see hu.bme.mit.gamma.fmeda.model.impl.fmedaPackageImpl#getNamedElement()
		 * @generated
		 */
		EClass NAMED_ELEMENT = eINSTANCE.getNamedElement();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NAMED_ELEMENT__NAME = eINSTANCE.getNamedElement_Name();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.fmeda.model.impl.FailureModeReferenceImpl <em>Failure Mode Reference</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.fmeda.model.impl.FailureModeReferenceImpl
		 * @see hu.bme.mit.gamma.fmeda.model.impl.fmedaPackageImpl#getFailureModeReference()
		 * @generated
		 */
		EClass FAILURE_MODE_REFERENCE = eINSTANCE.getFailureModeReference();

		/**
		 * The meta object literal for the '<em><b>Failuremode</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FAILURE_MODE_REFERENCE__FAILUREMODE = eINSTANCE.getFailureModeReference_Failuremode();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.fmeda.model.impl.ThisPartFailureModeReferenceImpl <em>This Part Failure Mode Reference</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.fmeda.model.impl.ThisPartFailureModeReferenceImpl
		 * @see hu.bme.mit.gamma.fmeda.model.impl.fmedaPackageImpl#getThisPartFailureModeReference()
		 * @generated
		 */
		EClass THIS_PART_FAILURE_MODE_REFERENCE = eINSTANCE.getThisPartFailureModeReference();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.fmeda.model.impl.FMEDADiagnosticsImpl <em>FMEDA Diagnostics</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.fmeda.model.impl.FMEDADiagnosticsImpl
		 * @see hu.bme.mit.gamma.fmeda.model.impl.fmedaPackageImpl#getFMEDADiagnostics()
		 * @generated
		 */
		EClass FMEDA_DIAGNOSTICS = eINSTANCE.getFMEDADiagnostics();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.fmeda.model.impl.DiagnosticsReferenceImpl <em>Diagnostics Reference</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.fmeda.model.impl.DiagnosticsReferenceImpl
		 * @see hu.bme.mit.gamma.fmeda.model.impl.fmedaPackageImpl#getDiagnosticsReference()
		 * @generated
		 */
		EClass DIAGNOSTICS_REFERENCE = eINSTANCE.getDiagnosticsReference();

		/**
		 * The meta object literal for the '<em><b>Coverage</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DIAGNOSTICS_REFERENCE__COVERAGE = eINSTANCE.getDiagnosticsReference_Coverage();

		/**
		 * The meta object literal for the '<em><b>Fmedadiagnostics</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DIAGNOSTICS_REFERENCE__FMEDADIAGNOSTICS = eINSTANCE.getDiagnosticsReference_Fmedadiagnostics();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.fmeda.model.impl.PortFailureModeReferenceImpl <em>Port Failure Mode Reference</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.fmeda.model.impl.PortFailureModeReferenceImpl
		 * @see hu.bme.mit.gamma.fmeda.model.impl.fmedaPackageImpl#getPortFailureModeReference()
		 * @generated
		 */
		EClass PORT_FAILURE_MODE_REFERENCE = eINSTANCE.getPortFailureModeReference();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.fmeda.model.impl.InterfaceImpl <em>Interface</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.fmeda.model.impl.InterfaceImpl
		 * @see hu.bme.mit.gamma.fmeda.model.impl.fmedaPackageImpl#getInterface()
		 * @generated
		 */
		EClass INTERFACE = eINSTANCE.getInterface();

		/**
		 * The meta object literal for the '<em><b>Failuremodes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INTERFACE__FAILUREMODES = eINSTANCE.getInterface_Failuremodes();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.fmeda.model.impl.ThisPortFailureModeReferenceImpl <em>This Port Failure Mode Reference</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.fmeda.model.impl.ThisPortFailureModeReferenceImpl
		 * @see hu.bme.mit.gamma.fmeda.model.impl.fmedaPackageImpl#getThisPortFailureModeReference()
		 * @generated
		 */
		EClass THIS_PORT_FAILURE_MODE_REFERENCE = eINSTANCE.getThisPortFailureModeReference();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.fmeda.model.impl.PortReferenceImpl <em>Port Reference</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.fmeda.model.impl.PortReferenceImpl
		 * @see hu.bme.mit.gamma.fmeda.model.impl.fmedaPackageImpl#getPortReference()
		 * @generated
		 */
		EClass PORT_REFERENCE = eINSTANCE.getPortReference();

		/**
		 * The meta object literal for the '<em><b>Port</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PORT_REFERENCE__PORT = eINSTANCE.getPortReference_Port();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.fmeda.model.impl.ComponentReferenceImpl <em>Component Reference</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.fmeda.model.impl.ComponentReferenceImpl
		 * @see hu.bme.mit.gamma.fmeda.model.impl.fmedaPackageImpl#getComponentReference()
		 * @generated
		 */
		EClass COMPONENT_REFERENCE = eINSTANCE.getComponentReference();

		/**
		 * The meta object literal for the '<em><b>Part</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPONENT_REFERENCE__PART = eINSTANCE.getComponentReference_Part();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.fmeda.model.impl.ThisPortReferenceImpl <em>This Port Reference</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.fmeda.model.impl.ThisPortReferenceImpl
		 * @see hu.bme.mit.gamma.fmeda.model.impl.fmedaPackageImpl#getThisPortReference()
		 * @generated
		 */
		EClass THIS_PORT_REFERENCE = eINSTANCE.getThisPortReference();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.fmeda.model.impl.HardwarePartPortReferenceImpl <em>Hardware Part Port Reference</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.fmeda.model.impl.HardwarePartPortReferenceImpl
		 * @see hu.bme.mit.gamma.fmeda.model.impl.fmedaPackageImpl#getHardwarePartPortReference()
		 * @generated
		 */
		EClass HARDWARE_PART_PORT_REFERENCE = eINSTANCE.getHardwarePartPortReference();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.fmeda.model.impl.ChannelImpl <em>Channel</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.fmeda.model.impl.ChannelImpl
		 * @see hu.bme.mit.gamma.fmeda.model.impl.fmedaPackageImpl#getChannel()
		 * @generated
		 */
		EClass CHANNEL = eINSTANCE.getChannel();

		/**
		 * The meta object literal for the '<em><b>From</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CHANNEL__FROM = eINSTANCE.getChannel_From();

		/**
		 * The meta object literal for the '<em><b>To</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CHANNEL__TO = eINSTANCE.getChannel_To();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.fmeda.model.impl.ChannelPortReferenceImpl <em>Channel Port Reference</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.fmeda.model.impl.ChannelPortReferenceImpl
		 * @see hu.bme.mit.gamma.fmeda.model.impl.fmedaPackageImpl#getChannelPortReference()
		 * @generated
		 */
		EClass CHANNEL_PORT_REFERENCE = eINSTANCE.getChannelPortReference();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.fmeda.model.impl.PortImpl <em>Port</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.fmeda.model.impl.PortImpl
		 * @see hu.bme.mit.gamma.fmeda.model.impl.fmedaPackageImpl#getPort()
		 * @generated
		 */
		EClass PORT = eINSTANCE.getPort();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PORT__TYPE = eINSTANCE.getPort_Type();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.fmeda.model.impl.PartFailureModeImpl <em>Part Failure Mode</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.fmeda.model.impl.PartFailureModeImpl
		 * @see hu.bme.mit.gamma.fmeda.model.impl.fmedaPackageImpl#getPartFailureMode()
		 * @generated
		 */
		EClass PART_FAILURE_MODE = eINSTANCE.getPartFailureMode();

		/**
		 * The meta object literal for the '<em><b>Failure Rate</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PART_FAILURE_MODE__FAILURE_RATE = eINSTANCE.getPartFailureMode_FailureRate();

	}

} //fmedaPackage
