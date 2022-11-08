/**
 */
package hu.bme.mit.gamma.stochastic.stochastic;

import hu.bme.mit.gamma.expression.model.Expression;

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
	 * Returns the value of the '<em><b>Mean</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Mean</em>' containment reference.
	 * @see #setMean(Expression)
	 * @see hu.bme.mit.gamma.stochastic.stochastic.StochasticPackage#getNormalRandomVariable_Mean()
	 * @model containment="true" required="true"
	 * @generated
	 */
	Expression getMean();

	/**
	 * Sets the value of the '{@link hu.bme.mit.gamma.stochastic.stochastic.NormalRandomVariable#getMean <em>Mean</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Mean</em>' containment reference.
	 * @see #getMean()
	 * @generated
	 */
	void setMean(Expression value);

	/**
	 * Returns the value of the '<em><b>Scale</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Scale</em>' containment reference.
	 * @see #setScale(Expression)
	 * @see hu.bme.mit.gamma.stochastic.stochastic.StochasticPackage#getNormalRandomVariable_Scale()
	 * @model containment="true" required="true"
	 * @generated
	 */
	Expression getScale();

	/**
	 * Sets the value of the '{@link hu.bme.mit.gamma.stochastic.stochastic.NormalRandomVariable#getScale <em>Scale</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Scale</em>' containment reference.
	 * @see #getScale()
	 * @generated
	 */
	void setScale(Expression value);

} // NormalRandomVariable
