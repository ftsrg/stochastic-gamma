/**
 */
package hu.bme.mit.gamma.fmeda.model;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Failure Propagation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link hu.bme.mit.gamma.fmeda.model.FailurePropagation#getCause <em>Cause</em>}</li>
 *   <li>{@link hu.bme.mit.gamma.fmeda.model.FailurePropagation#getEffect <em>Effect</em>}</li>
 * </ul>
 *
 * @see hu.bme.mit.gamma.fmeda.model.fmedaPackage#getFailurePropagation()
 * @model
 * @generated
 */
public interface FailurePropagation extends EObject {
	/**
	 * Returns the value of the '<em><b>Cause</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Cause</em>' containment reference.
	 * @see #setCause(FailureModeReference)
	 * @see hu.bme.mit.gamma.fmeda.model.fmedaPackage#getFailurePropagation_Cause()
	 * @model containment="true" required="true"
	 * @generated
	 */
	FailureModeReference getCause();

	/**
	 * Sets the value of the '{@link hu.bme.mit.gamma.fmeda.model.FailurePropagation#getCause <em>Cause</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cause</em>' containment reference.
	 * @see #getCause()
	 * @generated
	 */
	void setCause(FailureModeReference value);

	/**
	 * Returns the value of the '<em><b>Effect</b></em>' containment reference list.
	 * The list contents are of type {@link hu.bme.mit.gamma.fmeda.model.FailureModeReference}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Effect</em>' containment reference list.
	 * @see hu.bme.mit.gamma.fmeda.model.fmedaPackage#getFailurePropagation_Effect()
	 * @model containment="true" required="true"
	 * @generated
	 */
	EList<FailureModeReference> getEffect();

} // FailurePropagation
