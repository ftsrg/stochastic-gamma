/**
 */
package hu.bme.mit.gamma.environment.stochastic.stochastic;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Dirac Process</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link hu.bme.mit.gamma.environment.stochastic.stochastic.DiracProcess#getSource <em>Source</em>}</li>
 * </ul>
 *
 * @see hu.bme.mit.gamma.environment.stochastic.stochastic.StochasticPackage#getDiracProcess()
 * @model
 * @generated
 */
public interface DiracProcess extends StochasticProcess {
	/**
	 * Returns the value of the '<em><b>Source</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Source</em>' containment reference.
	 * @see #setSource(DataSource)
	 * @see hu.bme.mit.gamma.environment.stochastic.stochastic.StochasticPackage#getDiracProcess_Source()
	 * @model containment="true" required="true"
	 * @generated
	 */
	DataSource getSource();

	/**
	 * Sets the value of the '{@link hu.bme.mit.gamma.environment.stochastic.stochastic.DiracProcess#getSource <em>Source</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source</em>' containment reference.
	 * @see #getSource()
	 * @generated
	 */
	void setSource(DataSource value);

} // DiracProcess
