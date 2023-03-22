/**
 */
package hu.bme.mit.gamma.environment.analysis;

import hu.bme.mit.gamma.expression.model.ParameterDeclaration;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Observe Parameter</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link hu.bme.mit.gamma.environment.analysis.ObserveParameter#getParameter <em>Parameter</em>}</li>
 * </ul>
 *
 * @see hu.bme.mit.gamma.environment.analysis.AnalysisPackage#getObserveParameter()
 * @model
 * @generated
 */
public interface ObserveParameter extends ObserveCondition {
	/**
	 * Returns the value of the '<em><b>Parameter</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parameter</em>' reference.
	 * @see #setParameter(ParameterDeclaration)
	 * @see hu.bme.mit.gamma.environment.analysis.AnalysisPackage#getObserveParameter_Parameter()
	 * @model required="true"
	 * @generated
	 */
	ParameterDeclaration getParameter();

	/**
	 * Sets the value of the '{@link hu.bme.mit.gamma.environment.analysis.ObserveParameter#getParameter <em>Parameter</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parameter</em>' reference.
	 * @see #getParameter()
	 * @generated
	 */
	void setParameter(ParameterDeclaration value);

} // ObserveParameter
