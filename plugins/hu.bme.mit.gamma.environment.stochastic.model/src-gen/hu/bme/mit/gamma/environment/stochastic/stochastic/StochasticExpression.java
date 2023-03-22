/**
 */
package hu.bme.mit.gamma.environment.stochastic.stochastic;

import hu.bme.mit.gamma.expression.model.Expression;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link hu.bme.mit.gamma.environment.stochastic.stochastic.StochasticExpression#getRandomvariable <em>Randomvariable</em>}</li>
 * </ul>
 *
 * @see hu.bme.mit.gamma.environment.stochastic.stochastic.StochasticPackage#getStochasticExpression()
 * @model
 * @generated
 */
public interface StochasticExpression extends Expression {
	/**
	 * Returns the value of the '<em><b>Randomvariable</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Randomvariable</em>' containment reference.
	 * @see #setRandomvariable(RandomVariable)
	 * @see hu.bme.mit.gamma.environment.stochastic.stochastic.StochasticPackage#getStochasticExpression_Randomvariable()
	 * @model containment="true" required="true"
	 * @generated
	 */
	RandomVariable getRandomvariable();

	/**
	 * Sets the value of the '{@link hu.bme.mit.gamma.environment.stochastic.stochastic.StochasticExpression#getRandomvariable <em>Randomvariable</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Randomvariable</em>' containment reference.
	 * @see #getRandomvariable()
	 * @generated
	 */
	void setRandomvariable(RandomVariable value);

} // StochasticExpression
