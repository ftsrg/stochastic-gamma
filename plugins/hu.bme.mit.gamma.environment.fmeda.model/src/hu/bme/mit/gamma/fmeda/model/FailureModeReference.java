/**
 */
package hu.bme.mit.gamma.fmeda.model;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Failure Mode Reference</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link hu.bme.mit.gamma.fmeda.model.FailureModeReference#getFailuremode <em>Failuremode</em>}</li>
 * </ul>
 *
 * @see hu.bme.mit.gamma.fmeda.model.fmedaPackage#getFailureModeReference()
 * @model abstract="true"
 * @generated
 */
public interface FailureModeReference extends EObject {
	/**
	 * Returns the value of the '<em><b>Failuremode</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Failuremode</em>' reference.
	 * @see #setFailuremode(FailureMode)
	 * @see hu.bme.mit.gamma.fmeda.model.fmedaPackage#getFailureModeReference_Failuremode()
	 * @model required="true"
	 * @generated
	 */
	FailureMode getFailuremode();

	/**
	 * Sets the value of the '{@link hu.bme.mit.gamma.fmeda.model.FailureModeReference#getFailuremode <em>Failuremode</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Failuremode</em>' reference.
	 * @see #getFailuremode()
	 * @generated
	 */
	void setFailuremode(FailureMode value);

} // FailureModeReference
