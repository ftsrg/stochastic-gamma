/**
 */
package hu.bme.mit.gamma.fmeda.model;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>FMEDA Component Instance</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link hu.bme.mit.gamma.fmeda.model.FMEDAComponentInstance#getType <em>Type</em>}</li>
 * </ul>
 *
 * @see hu.bme.mit.gamma.fmeda.model.fmedaPackage#getFMEDAComponentInstance()
 * @model
 * @generated
 */
public interface FMEDAComponentInstance extends NamedElement {
	/**
	 * Returns the value of the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' reference.
	 * @see #setType(FMEDAComponent)
	 * @see hu.bme.mit.gamma.fmeda.model.fmedaPackage#getFMEDAComponentInstance_Type()
	 * @model required="true"
	 * @generated
	 */
	FMEDAComponent getType();

	/**
	 * Sets the value of the '{@link hu.bme.mit.gamma.fmeda.model.FMEDAComponentInstance#getType <em>Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' reference.
	 * @see #getType()
	 * @generated
	 */
	void setType(FMEDAComponent value);

} // FMEDAComponentInstance
