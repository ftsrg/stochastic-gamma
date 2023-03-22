/**
 */
package hu.bme.mit.gamma.environment.model;

import hu.bme.mit.gamma.environment.stochastic.stochastic.StochasticModel;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Stochastic Rule</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link hu.bme.mit.gamma.environment.model.StochasticRule#getStochasticModel <em>Stochastic Model</em>}</li>
 * </ul>
 *
 * @see hu.bme.mit.gamma.environment.model.EnvironmentModelPackage#getStochasticRule()
 * @model
 * @generated
 */
public interface StochasticRule extends EnvironmentRule {
	/**
	 * Returns the value of the '<em><b>Stochastic Model</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Stochastic Model</em>' containment reference.
	 * @see #setStochasticModel(StochasticModel)
	 * @see hu.bme.mit.gamma.environment.model.EnvironmentModelPackage#getStochasticRule_StochasticModel()
	 * @model containment="true" required="true"
	 * @generated
	 */
	StochasticModel getStochasticModel();

	/**
	 * Sets the value of the '{@link hu.bme.mit.gamma.environment.model.StochasticRule#getStochasticModel <em>Stochastic Model</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Stochastic Model</em>' containment reference.
	 * @see #getStochasticModel()
	 * @generated
	 */
	void setStochasticModel(StochasticModel value);

} // StochasticRule
