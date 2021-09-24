/**
 */
package hu.bme.mit.gamma.fmeda.model;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see hu.bme.mit.gamma.fmeda.model.fmedaPackage
 * @generated
 */
public interface fmedaFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	fmedaFactory eINSTANCE = hu.bme.mit.gamma.fmeda.model.impl.fmedaFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>FMEDA Component</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>FMEDA Component</em>'.
	 * @generated
	 */
	FMEDAComponent createFMEDAComponent();

	/**
	 * Returns a new object of class '<em>FMEDA Component Instance</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>FMEDA Component Instance</em>'.
	 * @generated
	 */
	FMEDAComponentInstance createFMEDAComponentInstance();

	/**
	 * Returns a new object of class '<em>Failure Mode</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Failure Mode</em>'.
	 * @generated
	 */
	FailureMode createFailureMode();

	/**
	 * Returns a new object of class '<em>Hardware Part Failure Mode Reference</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Hardware Part Failure Mode Reference</em>'.
	 * @generated
	 */
	HardwarePartFailureModeReference createHardwarePartFailureModeReference();

	/**
	 * Returns a new object of class '<em>Failure Propagation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Failure Propagation</em>'.
	 * @generated
	 */
	FailurePropagation createFailurePropagation();

	/**
	 * Returns a new object of class '<em>Package</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Package</em>'.
	 * @generated
	 */
	Package createPackage();

	/**
	 * Returns a new object of class '<em>This Part Failure Mode Reference</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>This Part Failure Mode Reference</em>'.
	 * @generated
	 */
	ThisPartFailureModeReference createThisPartFailureModeReference();

	/**
	 * Returns a new object of class '<em>FMEDA Diagnostics</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>FMEDA Diagnostics</em>'.
	 * @generated
	 */
	FMEDADiagnostics createFMEDADiagnostics();

	/**
	 * Returns a new object of class '<em>Diagnostics Reference</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Diagnostics Reference</em>'.
	 * @generated
	 */
	DiagnosticsReference createDiagnosticsReference();

	/**
	 * Returns a new object of class '<em>Port Failure Mode Reference</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Port Failure Mode Reference</em>'.
	 * @generated
	 */
	PortFailureModeReference createPortFailureModeReference();

	/**
	 * Returns a new object of class '<em>Interface</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Interface</em>'.
	 * @generated
	 */
	Interface createInterface();

	/**
	 * Returns a new object of class '<em>This Port Failure Mode Reference</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>This Port Failure Mode Reference</em>'.
	 * @generated
	 */
	ThisPortFailureModeReference createThisPortFailureModeReference();

	/**
	 * Returns a new object of class '<em>This Port Reference</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>This Port Reference</em>'.
	 * @generated
	 */
	ThisPortReference createThisPortReference();

	/**
	 * Returns a new object of class '<em>Hardware Part Port Reference</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Hardware Part Port Reference</em>'.
	 * @generated
	 */
	HardwarePartPortReference createHardwarePartPortReference();

	/**
	 * Returns a new object of class '<em>Channel</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Channel</em>'.
	 * @generated
	 */
	Channel createChannel();

	/**
	 * Returns a new object of class '<em>Port</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Port</em>'.
	 * @generated
	 */
	Port createPort();

	/**
	 * Returns a new object of class '<em>Part Failure Mode</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Part Failure Mode</em>'.
	 * @generated
	 */
	PartFailureMode createPartFailureMode();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	fmedaPackage getfmedaPackage();

} //fmedaFactory
