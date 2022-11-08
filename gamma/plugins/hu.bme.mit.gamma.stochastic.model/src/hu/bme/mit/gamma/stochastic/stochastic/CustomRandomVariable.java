/**
 */
package hu.bme.mit.gamma.stochastic.stochastic;

import hu.bme.mit.gamma.expression.model.FunctionDeclaration;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Custom Random Variable</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link hu.bme.mit.gamma.stochastic.stochastic.CustomRandomVariable#getInverse_cdf <em>Inverse cdf</em>}</li>
 * </ul>
 *
 * @see hu.bme.mit.gamma.stochastic.stochastic.StochasticPackage#getCustomRandomVariable()
 * @model
 * @generated
 */
public interface CustomRandomVariable extends ContinouosRandomVariable {
	/**
	 * Returns the value of the '<em><b>Inverse cdf</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Inverse cdf</em>' reference.
	 * @see #setInverse_cdf(FunctionDeclaration)
	 * @see hu.bme.mit.gamma.stochastic.stochastic.StochasticPackage#getCustomRandomVariable_Inverse_cdf()
	 * @model required="true"
	 * @generated
	 */
	FunctionDeclaration getInverse_cdf();

	/**
	 * Sets the value of the '{@link hu.bme.mit.gamma.stochastic.stochastic.CustomRandomVariable#getInverse_cdf <em>Inverse cdf</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Inverse cdf</em>' reference.
	 * @see #getInverse_cdf()
	 * @generated
	 */
	void setInverse_cdf(FunctionDeclaration value);

} // CustomRandomVariable
