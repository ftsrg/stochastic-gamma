/**
 */
package hu.bme.mit.gamma.analysis;

import hu.bme.mit.gamma.expression.model.ParameterDeclaration;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Parameter Distribution</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link hu.bme.mit.gamma.analysis.ParameterDistribution#getParameter <em>Parameter</em>}</li>
 * </ul>
 *
 * @see hu.bme.mit.gamma.analysis.AnalysisPackage#getParameterDistribution()
 * @model
 * @generated
 */
public interface ParameterDistribution extends AnalysisAspect {
	/**
	 * Returns the value of the '<em><b>Parameter</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parameter</em>' reference.
	 * @see #setParameter(ParameterDeclaration)
	 * @see hu.bme.mit.gamma.analysis.AnalysisPackage#getParameterDistribution_Parameter()
	 * @model required="true"
	 * @generated
	 */
	ParameterDeclaration getParameter();

	/**
	 * Sets the value of the '{@link hu.bme.mit.gamma.analysis.ParameterDistribution#getParameter <em>Parameter</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parameter</em>' reference.
	 * @see #getParameter()
	 * @generated
	 */
	void setParameter(ParameterDeclaration value);

} // ParameterDistribution
