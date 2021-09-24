/**
 */
package hu.bme.mit.gamma.environment.model;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Environment Composite Component Instance</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link hu.bme.mit.gamma.environment.model.EnvironmentCompositeComponentInstance#getType <em>Type</em>}</li>
 * </ul>
 *
 * @see hu.bme.mit.gamma.environment.model.EnvironentPackage#getEnvironmentCompositeComponentInstance()
 * @model
 * @generated
 */
public interface EnvironmentCompositeComponentInstance extends EnvironmentComponentInstance {
	/**
	 * Returns the value of the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' reference.
	 * @see #setType(EnvironmentCompositeComponent)
	 * @see hu.bme.mit.gamma.environment.model.EnvironentPackage#getEnvironmentCompositeComponentInstance_Type()
	 * @model required="true"
	 * @generated
	 */
	EnvironmentCompositeComponent getType();

	/**
	 * Sets the value of the '{@link hu.bme.mit.gamma.environment.model.EnvironmentCompositeComponentInstance#getType <em>Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' reference.
	 * @see #getType()
	 * @generated
	 */
	void setType(EnvironmentCompositeComponent value);

} // EnvironmentCompositeComponentInstance
