/**
 */
package hu.bme.mit.gamma.environment.stochastic.stochastic;

import hu.bme.mit.gamma.expression.model.Expression;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Bernoulli Random Variable</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link hu.bme.mit.gamma.environment.stochastic.stochastic.BernoulliRandomVariable#getProbability <em>Probability</em>}</li>
 * </ul>
 *
 * @see hu.bme.mit.gamma.environment.stochastic.stochastic.StochasticPackage#getBernoulliRandomVariable()
 * @model
 * @generated
 */
public interface BernoulliRandomVariable extends DiscreteRandomVariable {
	/**
	 * Returns the value of the '<em><b>Probability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Probability</em>' containment reference.
	 * @see #setProbability(Expression)
	 * @see hu.bme.mit.gamma.environment.stochastic.stochastic.StochasticPackage#getBernoulliRandomVariable_Probability()
	 * @model containment="true" required="true"
	 * @generated
	 */
	Expression getProbability();

	/**
	 * Sets the value of the '{@link hu.bme.mit.gamma.environment.stochastic.stochastic.BernoulliRandomVariable#getProbability <em>Probability</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Probability</em>' containment reference.
	 * @see #getProbability()
	 * @generated
	 */
	void setProbability(Expression value);

} // BernoulliRandomVariable
