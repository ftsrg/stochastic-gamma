/**
 */
package hu.bme.mit.gamma.environment.model;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Environment Synchronous Composite Component Instance</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link hu.bme.mit.gamma.environment.model.EnvironmentSynchronousCompositeComponentInstance#getType <em>Type</em>}</li>
 * </ul>
 *
 * @see hu.bme.mit.gamma.environment.model.EnvironmentModelPackage#getEnvironmentSynchronousCompositeComponentInstance()
 * @model
 * @generated
 */
public interface EnvironmentSynchronousCompositeComponentInstance extends AbstractEnvironmentCompositeComponentInstance {
	/**
	 * Returns the value of the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' reference.
	 * @see #setType(EnvironmentSynchronousCompositeComponent)
	 * @see hu.bme.mit.gamma.environment.model.EnvironmentModelPackage#getEnvironmentSynchronousCompositeComponentInstance_Type()
	 * @model required="true"
	 * @generated
	 */
	EnvironmentSynchronousCompositeComponent getType();

	/**
	 * Sets the value of the '{@link hu.bme.mit.gamma.environment.model.EnvironmentSynchronousCompositeComponentInstance#getType <em>Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' reference.
	 * @see #getType()
	 * @generated
	 */
	void setType(EnvironmentSynchronousCompositeComponent value);

} // EnvironmentSynchronousCompositeComponentInstance
