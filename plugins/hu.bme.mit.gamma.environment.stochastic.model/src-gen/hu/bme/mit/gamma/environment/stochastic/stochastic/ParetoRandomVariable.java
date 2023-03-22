/**
 */
package hu.bme.mit.gamma.environment.stochastic.stochastic;

import hu.bme.mit.gamma.expression.model.Expression;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Pareto Random Variable</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link hu.bme.mit.gamma.environment.stochastic.stochastic.ParetoRandomVariable#getScale <em>Scale</em>}</li>
 *   <li>{@link hu.bme.mit.gamma.environment.stochastic.stochastic.ParetoRandomVariable#getAlpha <em>Alpha</em>}</li>
 * </ul>
 *
 * @see hu.bme.mit.gamma.environment.stochastic.stochastic.StochasticPackage#getParetoRandomVariable()
 * @model
 * @generated
 */
public interface ParetoRandomVariable extends ContinouosRandomVariable {
	/**
	 * Returns the value of the '<em><b>Scale</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Scale</em>' containment reference.
	 * @see #setScale(Expression)
	 * @see hu.bme.mit.gamma.environment.stochastic.stochastic.StochasticPackage#getParetoRandomVariable_Scale()
	 * @model containment="true" required="true"
	 * @generated
	 */
	Expression getScale();

	/**
	 * Sets the value of the '{@link hu.bme.mit.gamma.environment.stochastic.stochastic.ParetoRandomVariable#getScale <em>Scale</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Scale</em>' containment reference.
	 * @see #getScale()
	 * @generated
	 */
	void setScale(Expression value);

	/**
	 * Returns the value of the '<em><b>Alpha</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Alpha</em>' containment reference.
	 * @see #setAlpha(Expression)
	 * @see hu.bme.mit.gamma.environment.stochastic.stochastic.StochasticPackage#getParetoRandomVariable_Alpha()
	 * @model containment="true" required="true"
	 * @generated
	 */
	Expression getAlpha();

	/**
	 * Sets the value of the '{@link hu.bme.mit.gamma.environment.stochastic.stochastic.ParetoRandomVariable#getAlpha <em>Alpha</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Alpha</em>' containment reference.
	 * @see #getAlpha()
	 * @generated
	 */
	void setAlpha(Expression value);

} // ParetoRandomVariable
