/**
 */
package hu.bme.mit.gamma.environment.stochastic.stochastic;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Python Simulation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link hu.bme.mit.gamma.environment.stochastic.stochastic.PythonSimulation#getScript <em>Script</em>}</li>
 * </ul>
 *
 * @see hu.bme.mit.gamma.environment.stochastic.stochastic.StochasticPackage#getPythonSimulation()
 * @model
 * @generated
 */
public interface PythonSimulation extends DataSource {
	/**
	 * Returns the value of the '<em><b>Script</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Script</em>' attribute.
	 * @see #setScript(String)
	 * @see hu.bme.mit.gamma.environment.stochastic.stochastic.StochasticPackage#getPythonSimulation_Script()
	 * @model required="true"
	 * @generated
	 */
	String getScript();

	/**
	 * Sets the value of the '{@link hu.bme.mit.gamma.environment.stochastic.stochastic.PythonSimulation#getScript <em>Script</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Script</em>' attribute.
	 * @see #getScript()
	 * @generated
	 */
	void setScript(String value);

} // PythonSimulation
