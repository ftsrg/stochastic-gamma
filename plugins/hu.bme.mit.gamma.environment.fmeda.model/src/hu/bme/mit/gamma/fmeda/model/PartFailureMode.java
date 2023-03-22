/**
 */
package hu.bme.mit.gamma.fmeda.model;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Part Failure Mode</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link hu.bme.mit.gamma.fmeda.model.PartFailureMode#getFailureRate <em>Failure Rate</em>}</li>
 * </ul>
 *
 * @see hu.bme.mit.gamma.fmeda.model.fmedaPackage#getPartFailureMode()
 * @model
 * @generated
 */
public interface PartFailureMode extends FailureMode {
	/**
	 * Returns the value of the '<em><b>Failure Rate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Failure Rate</em>' attribute.
	 * @see #setFailureRate(double)
	 * @see hu.bme.mit.gamma.fmeda.model.fmedaPackage#getPartFailureMode_FailureRate()
	 * @model required="true"
	 * @generated
	 */
	double getFailureRate();

	/**
	 * Sets the value of the '{@link hu.bme.mit.gamma.fmeda.model.PartFailureMode#getFailureRate <em>Failure Rate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Failure Rate</em>' attribute.
	 * @see #getFailureRate()
	 * @generated
	 */
	void setFailureRate(double value);

} // PartFailureMode
