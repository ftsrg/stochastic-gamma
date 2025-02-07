/**
 */
package hu.bme.mit.gamma.environment.stochastic.stochastic;

import hu.bme.mit.gamma.expression.model.Expression;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Uniform Random Variable</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link hu.bme.mit.gamma.environment.stochastic.stochastic.UniformRandomVariable#getLowerBound <em>Lower Bound</em>}</li>
 *   <li>{@link hu.bme.mit.gamma.environment.stochastic.stochastic.UniformRandomVariable#getUpperBound <em>Upper Bound</em>}</li>
 * </ul>
 *
 * @see hu.bme.mit.gamma.environment.stochastic.stochastic.StochasticPackage#getUniformRandomVariable()
 * @model
 * @generated
 */
public interface UniformRandomVariable extends ContinouosRandomVariable {
	/**
	 * Returns the value of the '<em><b>Lower Bound</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Lower Bound</em>' containment reference.
	 * @see #setLowerBound(Expression)
	 * @see hu.bme.mit.gamma.environment.stochastic.stochastic.StochasticPackage#getUniformRandomVariable_LowerBound()
	 * @model containment="true" required="true"
	 * @generated
	 */
	Expression getLowerBound();

	/**
	 * Sets the value of the '{@link hu.bme.mit.gamma.environment.stochastic.stochastic.UniformRandomVariable#getLowerBound <em>Lower Bound</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Lower Bound</em>' containment reference.
	 * @see #getLowerBound()
	 * @generated
	 */
	void setLowerBound(Expression value);

	/**
	 * Returns the value of the '<em><b>Upper Bound</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Upper Bound</em>' containment reference.
	 * @see #setUpperBound(Expression)
	 * @see hu.bme.mit.gamma.environment.stochastic.stochastic.StochasticPackage#getUniformRandomVariable_UpperBound()
	 * @model containment="true" required="true"
	 * @generated
	 */
	Expression getUpperBound();

	/**
	 * Sets the value of the '{@link hu.bme.mit.gamma.environment.stochastic.stochastic.UniformRandomVariable#getUpperBound <em>Upper Bound</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Upper Bound</em>' containment reference.
	 * @see #getUpperBound()
	 * @generated
	 */
	void setUpperBound(Expression value);

} // UniformRandomVariable
