/**
 */
package hu.bme.mit.gamma.stochastic.stochastic;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Exponential Random Variable</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link hu.bme.mit.gamma.stochastic.stochastic.ExponentialRandomVariable#getRate <em>Rate</em>}</li>
 * </ul>
 *
 * @see hu.bme.mit.gamma.stochastic.stochastic.StochasticPackage#getExponentialRandomVariable()
 * @model
 * @generated
 */
public interface ExponentialRandomVariable extends ContinouosRandomVariable {
	/**
	 * Returns the value of the '<em><b>Rate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Rate</em>' attribute.
	 * @see #setRate(double)
	 * @see hu.bme.mit.gamma.stochastic.stochastic.StochasticPackage#getExponentialRandomVariable_Rate()
	 * @model required="true"
	 * @generated
	 */
	double getRate();

	/**
	 * Sets the value of the '{@link hu.bme.mit.gamma.stochastic.stochastic.ExponentialRandomVariable#getRate <em>Rate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Rate</em>' attribute.
	 * @see #getRate()
	 * @generated
	 */
	void setRate(double value);

} // ExponentialRandomVariable
