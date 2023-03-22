/**
 */
package hu.bme.mit.gamma.fmeda.model.util;

import hu.bme.mit.gamma.fmeda.model.Channel;
import hu.bme.mit.gamma.fmeda.model.ChannelPortReference;
import hu.bme.mit.gamma.fmeda.model.ComponentReference;
import hu.bme.mit.gamma.fmeda.model.DiagnosticsReference;
import hu.bme.mit.gamma.fmeda.model.FMEDAComponent;
import hu.bme.mit.gamma.fmeda.model.FMEDAComponentInstance;
import hu.bme.mit.gamma.fmeda.model.FMEDADiagnostics;
import hu.bme.mit.gamma.fmeda.model.FailureMode;
import hu.bme.mit.gamma.fmeda.model.FailureModeReference;
import hu.bme.mit.gamma.fmeda.model.FailurePropagation;
import hu.bme.mit.gamma.fmeda.model.HardwarePartFailureModeReference;
import hu.bme.mit.gamma.fmeda.model.HardwarePartPortReference;
import hu.bme.mit.gamma.fmeda.model.Interface;
import hu.bme.mit.gamma.fmeda.model.NamedElement;
import hu.bme.mit.gamma.fmeda.model.PartFailureMode;
import hu.bme.mit.gamma.fmeda.model.Port;
import hu.bme.mit.gamma.fmeda.model.PortFailureModeReference;
import hu.bme.mit.gamma.fmeda.model.PortReference;
import hu.bme.mit.gamma.fmeda.model.ThisPartFailureModeReference;
import hu.bme.mit.gamma.fmeda.model.ThisPortFailureModeReference;
import hu.bme.mit.gamma.fmeda.model.ThisPortReference;
import hu.bme.mit.gamma.fmeda.model.fmedaPackage;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see hu.bme.mit.gamma.fmeda.model.fmedaPackage
 * @generated
 */
public class fmedaSwitch<T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static fmedaPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public fmedaSwitch() {
		if (modelPackage == null) {
			modelPackage = fmedaPackage.eINSTANCE;
		}
	}

	/**
	 * Checks whether this is a switch for the given package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param ePackage the package in question.
	 * @return whether this is a switch for the given package.
	 * @generated
	 */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
		return ePackage == modelPackage;
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	@Override
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case fmedaPackage.FMEDA_COMPONENT: {
				FMEDAComponent fmedaComponent = (FMEDAComponent)theEObject;
				T result = caseFMEDAComponent(fmedaComponent);
				if (result == null) result = caseNamedElement(fmedaComponent);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case fmedaPackage.FMEDA_COMPONENT_INSTANCE: {
				FMEDAComponentInstance fmedaComponentInstance = (FMEDAComponentInstance)theEObject;
				T result = caseFMEDAComponentInstance(fmedaComponentInstance);
				if (result == null) result = caseNamedElement(fmedaComponentInstance);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case fmedaPackage.FAILURE_MODE: {
				FailureMode failureMode = (FailureMode)theEObject;
				T result = caseFailureMode(failureMode);
				if (result == null) result = caseNamedElement(failureMode);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case fmedaPackage.HARDWARE_PART_FAILURE_MODE_REFERENCE: {
				HardwarePartFailureModeReference hardwarePartFailureModeReference = (HardwarePartFailureModeReference)theEObject;
				T result = caseHardwarePartFailureModeReference(hardwarePartFailureModeReference);
				if (result == null) result = caseFailureModeReference(hardwarePartFailureModeReference);
				if (result == null) result = caseComponentReference(hardwarePartFailureModeReference);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case fmedaPackage.FAILURE_PROPAGATION: {
				FailurePropagation failurePropagation = (FailurePropagation)theEObject;
				T result = caseFailurePropagation(failurePropagation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case fmedaPackage.PACKAGE: {
				hu.bme.mit.gamma.fmeda.model.Package package_ = (hu.bme.mit.gamma.fmeda.model.Package)theEObject;
				T result = casePackage(package_);
				if (result == null) result = caseNamedElement(package_);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case fmedaPackage.NAMED_ELEMENT: {
				NamedElement namedElement = (NamedElement)theEObject;
				T result = caseNamedElement(namedElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case fmedaPackage.FAILURE_MODE_REFERENCE: {
				FailureModeReference failureModeReference = (FailureModeReference)theEObject;
				T result = caseFailureModeReference(failureModeReference);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case fmedaPackage.THIS_PART_FAILURE_MODE_REFERENCE: {
				ThisPartFailureModeReference thisPartFailureModeReference = (ThisPartFailureModeReference)theEObject;
				T result = caseThisPartFailureModeReference(thisPartFailureModeReference);
				if (result == null) result = caseFailureModeReference(thisPartFailureModeReference);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case fmedaPackage.FMEDA_DIAGNOSTICS: {
				FMEDADiagnostics fmedaDiagnostics = (FMEDADiagnostics)theEObject;
				T result = caseFMEDADiagnostics(fmedaDiagnostics);
				if (result == null) result = caseNamedElement(fmedaDiagnostics);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case fmedaPackage.DIAGNOSTICS_REFERENCE: {
				DiagnosticsReference diagnosticsReference = (DiagnosticsReference)theEObject;
				T result = caseDiagnosticsReference(diagnosticsReference);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case fmedaPackage.PORT_FAILURE_MODE_REFERENCE: {
				PortFailureModeReference portFailureModeReference = (PortFailureModeReference)theEObject;
				T result = casePortFailureModeReference(portFailureModeReference);
				if (result == null) result = caseFailureModeReference(portFailureModeReference);
				if (result == null) result = casePortReference(portFailureModeReference);
				if (result == null) result = caseComponentReference(portFailureModeReference);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case fmedaPackage.INTERFACE: {
				Interface interface_ = (Interface)theEObject;
				T result = caseInterface(interface_);
				if (result == null) result = caseNamedElement(interface_);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case fmedaPackage.THIS_PORT_FAILURE_MODE_REFERENCE: {
				ThisPortFailureModeReference thisPortFailureModeReference = (ThisPortFailureModeReference)theEObject;
				T result = caseThisPortFailureModeReference(thisPortFailureModeReference);
				if (result == null) result = caseFailureModeReference(thisPortFailureModeReference);
				if (result == null) result = casePortReference(thisPortFailureModeReference);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case fmedaPackage.PORT_REFERENCE: {
				PortReference portReference = (PortReference)theEObject;
				T result = casePortReference(portReference);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case fmedaPackage.COMPONENT_REFERENCE: {
				ComponentReference componentReference = (ComponentReference)theEObject;
				T result = caseComponentReference(componentReference);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case fmedaPackage.THIS_PORT_REFERENCE: {
				ThisPortReference thisPortReference = (ThisPortReference)theEObject;
				T result = caseThisPortReference(thisPortReference);
				if (result == null) result = caseChannelPortReference(thisPortReference);
				if (result == null) result = casePortReference(thisPortReference);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case fmedaPackage.HARDWARE_PART_PORT_REFERENCE: {
				HardwarePartPortReference hardwarePartPortReference = (HardwarePartPortReference)theEObject;
				T result = caseHardwarePartPortReference(hardwarePartPortReference);
				if (result == null) result = caseComponentReference(hardwarePartPortReference);
				if (result == null) result = caseChannelPortReference(hardwarePartPortReference);
				if (result == null) result = casePortReference(hardwarePartPortReference);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case fmedaPackage.CHANNEL: {
				Channel channel = (Channel)theEObject;
				T result = caseChannel(channel);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case fmedaPackage.CHANNEL_PORT_REFERENCE: {
				ChannelPortReference channelPortReference = (ChannelPortReference)theEObject;
				T result = caseChannelPortReference(channelPortReference);
				if (result == null) result = casePortReference(channelPortReference);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case fmedaPackage.PORT: {
				Port port = (Port)theEObject;
				T result = casePort(port);
				if (result == null) result = caseNamedElement(port);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case fmedaPackage.PART_FAILURE_MODE: {
				PartFailureMode partFailureMode = (PartFailureMode)theEObject;
				T result = casePartFailureMode(partFailureMode);
				if (result == null) result = caseFailureMode(partFailureMode);
				if (result == null) result = caseNamedElement(partFailureMode);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>FMEDA Component</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>FMEDA Component</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFMEDAComponent(FMEDAComponent object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>FMEDA Component Instance</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>FMEDA Component Instance</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFMEDAComponentInstance(FMEDAComponentInstance object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Failure Mode</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Failure Mode</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFailureMode(FailureMode object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Hardware Part Failure Mode Reference</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Hardware Part Failure Mode Reference</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseHardwarePartFailureModeReference(HardwarePartFailureModeReference object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Failure Propagation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Failure Propagation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFailurePropagation(FailurePropagation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Package</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Package</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePackage(hu.bme.mit.gamma.fmeda.model.Package object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Named Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Named Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNamedElement(NamedElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Failure Mode Reference</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Failure Mode Reference</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFailureModeReference(FailureModeReference object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>This Part Failure Mode Reference</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>This Part Failure Mode Reference</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseThisPartFailureModeReference(ThisPartFailureModeReference object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>FMEDA Diagnostics</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>FMEDA Diagnostics</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFMEDADiagnostics(FMEDADiagnostics object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Diagnostics Reference</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Diagnostics Reference</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDiagnosticsReference(DiagnosticsReference object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Port Failure Mode Reference</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Port Failure Mode Reference</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePortFailureModeReference(PortFailureModeReference object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Interface</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Interface</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseInterface(Interface object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>This Port Failure Mode Reference</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>This Port Failure Mode Reference</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseThisPortFailureModeReference(ThisPortFailureModeReference object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Port Reference</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Port Reference</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePortReference(PortReference object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Component Reference</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Component Reference</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseComponentReference(ComponentReference object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>This Port Reference</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>This Port Reference</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseThisPortReference(ThisPortReference object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Hardware Part Port Reference</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Hardware Part Port Reference</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseHardwarePartPortReference(HardwarePartPortReference object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Channel</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Channel</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseChannel(Channel object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Channel Port Reference</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Channel Port Reference</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseChannelPortReference(ChannelPortReference object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Port</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Port</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePort(Port object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Part Failure Mode</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Part Failure Mode</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePartFailureMode(PartFailureMode object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	@Override
	public T defaultCase(EObject object) {
		return null;
	}

} //fmedaSwitch
