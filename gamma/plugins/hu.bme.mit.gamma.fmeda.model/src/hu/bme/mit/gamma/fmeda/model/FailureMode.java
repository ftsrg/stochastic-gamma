/**
 */
package hu.bme.mit.gamma.fmeda.model;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Failure Mode</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link hu.bme.mit.gamma.fmeda.model.FailureMode#getDiagnostics <em>Diagnostics</em>}</li>
 * </ul>
 *
 * @see hu.bme.mit.gamma.fmeda.model.fmedaPackage#getFailureMode()
 * @model
 * @generated
 */
public interface FailureMode extends NamedElement {
	/**
	 * Returns the value of the '<em><b>Diagnostics</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Diagnostics</em>' containment reference.
	 * @see #setDiagnostics(DiagnosticsReference)
	 * @see hu.bme.mit.gamma.fmeda.model.fmedaPackage#getFailureMode_Diagnostics()
	 * @model containment="true"
	 * @generated
	 */
	DiagnosticsReference getDiagnostics();

	/**
	 * Sets the value of the '{@link hu.bme.mit.gamma.fmeda.model.FailureMode#getDiagnostics <em>Diagnostics</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Diagnostics</em>' containment reference.
	 * @see #getDiagnostics()
	 * @generated
	 */
	void setDiagnostics(DiagnosticsReference value);

} // FailureMode
