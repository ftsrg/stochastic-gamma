/**
 */
package hu.bme.mit.gamma.environment.model;

import hu.bme.mit.gamma.expression.model.ParameterDeclaration;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Parameter Filter</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link hu.bme.mit.gamma.environment.model.ParameterFilter#getParameter <em>Parameter</em>}</li>
 * </ul>
 *
 * @see hu.bme.mit.gamma.environment.model.EnvironmentModelPackage#getParameterFilter()
 * @model
 * @generated
 */
public interface ParameterFilter extends EventFilter {
	/**
	 * Returns the value of the '<em><b>Parameter</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parameter</em>' reference.
	 * @see #setParameter(ParameterDeclaration)
	 * @see hu.bme.mit.gamma.environment.model.EnvironmentModelPackage#getParameterFilter_Parameter()
	 * @model required="true"
	 * @generated
	 */
	ParameterDeclaration getParameter();

	/**
	 * Sets the value of the '{@link hu.bme.mit.gamma.environment.model.ParameterFilter#getParameter <em>Parameter</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parameter</em>' reference.
	 * @see #getParameter()
	 * @generated
	 */
	void setParameter(ParameterDeclaration value);

} // ParameterFilter
