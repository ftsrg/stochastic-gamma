/**
 */
package hu.bme.mit.gamma.environment.stochastic.stochastic;

import hu.bme.mit.gamma.expression.model.Expression;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Gamma Random Variable</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link hu.bme.mit.gamma.environment.stochastic.stochastic.GammaRandomVariable#getScale <em>Scale</em>}</li>
 *   <li>{@link hu.bme.mit.gamma.environment.stochastic.stochastic.GammaRandomVariable#getShape <em>Shape</em>}</li>
 * </ul>
 *
 * @see hu.bme.mit.gamma.environment.stochastic.stochastic.StochasticPackage#getGammaRandomVariable()
 * @model
 * @generated
 */
public interface GammaRandomVariable extends ContinouosRandomVariable {
	/**
	 * Returns the value of the '<em><b>Scale</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Scale</em>' containment reference.
	 * @see #setScale(Expression)
	 * @see hu.bme.mit.gamma.environment.stochastic.stochastic.StochasticPackage#getGammaRandomVariable_Scale()
	 * @model containment="true" required="true"
	 * @generated
	 */
	Expression getScale();

	/**
	 * Sets the value of the '{@link hu.bme.mit.gamma.environment.stochastic.stochastic.GammaRandomVariable#getScale <em>Scale</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Scale</em>' containment reference.
	 * @see #getScale()
	 * @generated
	 */
	void setScale(Expression value);

	/**
	 * Returns the value of the '<em><b>Shape</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Shape</em>' containment reference.
	 * @see #setShape(Expression)
	 * @see hu.bme.mit.gamma.environment.stochastic.stochastic.StochasticPackage#getGammaRandomVariable_Shape()
	 * @model containment="true" required="true"
	 * @generated
	 */
	Expression getShape();

	/**
	 * Sets the value of the '{@link hu.bme.mit.gamma.environment.stochastic.stochastic.GammaRandomVariable#getShape <em>Shape</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Shape</em>' containment reference.
	 * @see #getShape()
	 * @generated
	 */
	void setShape(Expression value);

} // GammaRandomVariable
