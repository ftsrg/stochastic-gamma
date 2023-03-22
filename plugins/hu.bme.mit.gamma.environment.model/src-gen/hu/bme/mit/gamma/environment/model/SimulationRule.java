/**
 */
package hu.bme.mit.gamma.environment.model;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Simulation Rule</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link hu.bme.mit.gamma.environment.model.SimulationRule#getSimulation <em>Simulation</em>}</li>
 * </ul>
 *
 * @see hu.bme.mit.gamma.environment.model.EnvironmentModelPackage#getSimulationRule()
 * @model
 * @generated
 */
public interface SimulationRule extends EnvironmentRule {
	/**
	 * Returns the value of the '<em><b>Simulation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Simulation</em>' containment reference.
	 * @see #setSimulation(Simulation)
	 * @see hu.bme.mit.gamma.environment.model.EnvironmentModelPackage#getSimulationRule_Simulation()
	 * @model containment="true" required="true"
	 * @generated
	 */
	Simulation getSimulation();

	/**
	 * Sets the value of the '{@link hu.bme.mit.gamma.environment.model.SimulationRule#getSimulation <em>Simulation</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Simulation</em>' containment reference.
	 * @see #getSimulation()
	 * @generated
	 */
	void setSimulation(Simulation value);

} // SimulationRule
