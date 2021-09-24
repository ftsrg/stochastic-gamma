/**
 */
package hu.bme.mit.gamma.stochastic.stochastic;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Normal Random Variable</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link hu.bme.mit.gamma.stochastic.stochastic.NormalRandomVariable#getMean <em>Mean</em>}</li>
 *   <li>{@link hu.bme.mit.gamma.stochastic.stochastic.NormalRandomVariable#getScale <em>Scale</em>}</li>
 * </ul>
 *
 * @see hu.bme.mit.gamma.stochastic.stochastic.StochasticPackage#getNormalRandomVariable()
 * @model
 * @generated
 */
public interface NormalRandomVariable extends ContinouosRandomVariable {
	/**
	 * Returns the value of the '<em><b>Mean</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Mean</em>' attribute.
	 * @see #setMean(double)
	 * @see hu.bme.mit.gamma.stochastic.stochastic.StochasticPackage#getNormalRandomVariable_Mean()
	 * @model required="true"
	 * @generated
	 */
	double getMean();

	/**
	 * Sets the value of the '{@link hu.bme.mit.gamma.stochastic.stochastic.NormalRandomVariable#getMean <em>Mean</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Mean</em>' attribute.
	 * @see #getMean()
	 * @generated
	 */
	void setMean(double value);

	/**
	 * Returns the value of the '<em><b>Scale</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Scale</em>' attribute.
	 * @see #setScale(double)
	 * @see hu.bme.mit.gamma.stochastic.stochastic.StochasticPackage#getNormalRandomVariable_Scale()
	 * @model required="true"
	 * @generated
	 */
	double getScale();

	/**
	 * Sets the value of the '{@link hu.bme.mit.gamma.stochastic.stochastic.NormalRandomVariable#getScale <em>Scale</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Scale</em>' attribute.
	 * @see #getScale()
	 * @generated
	 */
	void setScale(double value);

} // NormalRandomVariable
