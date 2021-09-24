/**
 */
package hu.bme.mit.gamma.fmeda.model.impl;

import hu.bme.mit.gamma.fmeda.model.Channel;
import hu.bme.mit.gamma.fmeda.model.DiagnosticsReference;
import hu.bme.mit.gamma.fmeda.model.FMEDAComponent;
import hu.bme.mit.gamma.fmeda.model.FMEDAComponentInstance;
import hu.bme.mit.gamma.fmeda.model.FMEDADiagnostics;
import hu.bme.mit.gamma.fmeda.model.FailureMode;
import hu.bme.mit.gamma.fmeda.model.FailurePropagation;
import hu.bme.mit.gamma.fmeda.model.HardwarePartFailureModeReference;
import hu.bme.mit.gamma.fmeda.model.HardwarePartPortReference;
import hu.bme.mit.gamma.fmeda.model.Interface;
import hu.bme.mit.gamma.fmeda.model.PartFailureMode;
import hu.bme.mit.gamma.fmeda.model.Port;
import hu.bme.mit.gamma.fmeda.model.PortFailureModeReference;
import hu.bme.mit.gamma.fmeda.model.ThisPartFailureModeReference;
import hu.bme.mit.gamma.fmeda.model.ThisPortFailureModeReference;
import hu.bme.mit.gamma.fmeda.model.ThisPortReference;
import hu.bme.mit.gamma.fmeda.model.fmedaFactory;
import hu.bme.mit.gamma.fmeda.model.fmedaPackage;

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
public class fmedaFactoryImpl extends EFactoryImpl implements fmedaFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static fmedaFactory init() {
		try {
			fmedaFactory thefmedaFactory = (fmedaFactory)EPackage.Registry.INSTANCE.getEFactory(fmedaPackage.eNS_URI);
			if (thefmedaFactory != null) {
				return thefmedaFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new fmedaFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public fmedaFactoryImpl() {
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
			case fmedaPackage.FMEDA_COMPONENT: return createFMEDAComponent();
			case fmedaPackage.FMEDA_COMPONENT_INSTANCE: return createFMEDAComponentInstance();
			case fmedaPackage.FAILURE_MODE: return createFailureMode();
			case fmedaPackage.HARDWARE_PART_FAILURE_MODE_REFERENCE: return createHardwarePartFailureModeReference();
			case fmedaPackage.FAILURE_PROPAGATION: return createFailurePropagation();
			case fmedaPackage.PACKAGE: return createPackage();
			case fmedaPackage.THIS_PART_FAILURE_MODE_REFERENCE: return createThisPartFailureModeReference();
			case fmedaPackage.FMEDA_DIAGNOSTICS: return createFMEDADiagnostics();
			case fmedaPackage.DIAGNOSTICS_REFERENCE: return createDiagnosticsReference();
			case fmedaPackage.PORT_FAILURE_MODE_REFERENCE: return createPortFailureModeReference();
			case fmedaPackage.INTERFACE: return createInterface();
			case fmedaPackage.THIS_PORT_FAILURE_MODE_REFERENCE: return createThisPortFailureModeReference();
			case fmedaPackage.THIS_PORT_REFERENCE: return createThisPortReference();
			case fmedaPackage.HARDWARE_PART_PORT_REFERENCE: return createHardwarePartPortReference();
			case fmedaPackage.CHANNEL: return createChannel();
			case fmedaPackage.PORT: return createPort();
			case fmedaPackage.PART_FAILURE_MODE: return createPartFailureMode();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FMEDAComponent createFMEDAComponent() {
		FMEDAComponentImpl fmedaComponent = new FMEDAComponentImpl();
		return fmedaComponent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FMEDAComponentInstance createFMEDAComponentInstance() {
		FMEDAComponentInstanceImpl fmedaComponentInstance = new FMEDAComponentInstanceImpl();
		return fmedaComponentInstance;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FailureMode createFailureMode() {
		FailureModeImpl failureMode = new FailureModeImpl();
		return failureMode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public HardwarePartFailureModeReference createHardwarePartFailureModeReference() {
		HardwarePartFailureModeReferenceImpl hardwarePartFailureModeReference = new HardwarePartFailureModeReferenceImpl();
		return hardwarePartFailureModeReference;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FailurePropagation createFailurePropagation() {
		FailurePropagationImpl failurePropagation = new FailurePropagationImpl();
		return failurePropagation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public hu.bme.mit.gamma.fmeda.model.Package createPackage() {
		PackageImpl package_ = new PackageImpl();
		return package_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ThisPartFailureModeReference createThisPartFailureModeReference() {
		ThisPartFailureModeReferenceImpl thisPartFailureModeReference = new ThisPartFailureModeReferenceImpl();
		return thisPartFailureModeReference;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FMEDADiagnostics createFMEDADiagnostics() {
		FMEDADiagnosticsImpl fmedaDiagnostics = new FMEDADiagnosticsImpl();
		return fmedaDiagnostics;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DiagnosticsReference createDiagnosticsReference() {
		DiagnosticsReferenceImpl diagnosticsReference = new DiagnosticsReferenceImpl();
		return diagnosticsReference;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PortFailureModeReference createPortFailureModeReference() {
		PortFailureModeReferenceImpl portFailureModeReference = new PortFailureModeReferenceImpl();
		return portFailureModeReference;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Interface createInterface() {
		InterfaceImpl interface_ = new InterfaceImpl();
		return interface_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ThisPortFailureModeReference createThisPortFailureModeReference() {
		ThisPortFailureModeReferenceImpl thisPortFailureModeReference = new ThisPortFailureModeReferenceImpl();
		return thisPortFailureModeReference;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ThisPortReference createThisPortReference() {
		ThisPortReferenceImpl thisPortReference = new ThisPortReferenceImpl();
		return thisPortReference;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public HardwarePartPortReference createHardwarePartPortReference() {
		HardwarePartPortReferenceImpl hardwarePartPortReference = new HardwarePartPortReferenceImpl();
		return hardwarePartPortReference;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Channel createChannel() {
		ChannelImpl channel = new ChannelImpl();
		return channel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Port createPort() {
		PortImpl port = new PortImpl();
		return port;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PartFailureMode createPartFailureMode() {
		PartFailureModeImpl partFailureMode = new PartFailureModeImpl();
		return partFailureMode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public fmedaPackage getfmedaPackage() {
		return (fmedaPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static fmedaPackage getPackage() {
		return fmedaPackage.eINSTANCE;
	}

} //fmedaFactoryImpl
