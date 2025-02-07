/**
 */
package hu.bme.mit.gamma.environment.model;

import hu.bme.mit.gamma.statechart.interface_.Port;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Port Filter</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link hu.bme.mit.gamma.environment.model.PortFilter#getPort <em>Port</em>}</li>
 * </ul>
 *
 * @see hu.bme.mit.gamma.environment.model.EnvironmentModelPackage#getPortFilter()
 * @model
 * @generated
 */
public interface PortFilter extends Filter {
	/**
	 * Returns the value of the '<em><b>Port</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Port</em>' reference.
	 * @see #setPort(Port)
	 * @see hu.bme.mit.gamma.environment.model.EnvironmentModelPackage#getPortFilter_Port()
	 * @model required="true"
	 * @generated
	 */
	Port getPort();

	/**
	 * Sets the value of the '{@link hu.bme.mit.gamma.environment.model.PortFilter#getPort <em>Port</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Port</em>' reference.
	 * @see #getPort()
	 * @generated
	 */
	void setPort(Port value);

} // PortFilter
