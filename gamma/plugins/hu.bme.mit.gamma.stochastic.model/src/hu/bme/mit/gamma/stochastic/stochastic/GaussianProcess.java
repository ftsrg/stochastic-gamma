/**
 */
package hu.bme.mit.gamma.stochastic.stochastic;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Gaussian Process</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link hu.bme.mit.gamma.stochastic.stochastic.GaussianProcess#getKernel <em>Kernel</em>}</li>
 * </ul>
 *
 * @see hu.bme.mit.gamma.stochastic.stochastic.StochasticPackage#getGaussianProcess()
 * @model
 * @generated
 */
public interface GaussianProcess extends StochasticProcess {
	/**
	 * Returns the value of the '<em><b>Kernel</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Kernel</em>' containment reference.
	 * @see #setKernel(Kernel)
	 * @see hu.bme.mit.gamma.stochastic.stochastic.StochasticPackage#getGaussianProcess_Kernel()
	 * @model containment="true" required="true"
	 * @generated
	 */
	Kernel getKernel();

	/**
	 * Sets the value of the '{@link hu.bme.mit.gamma.stochastic.stochastic.GaussianProcess#getKernel <em>Kernel</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Kernel</em>' containment reference.
	 * @see #getKernel()
	 * @generated
	 */
	void setKernel(Kernel value);

} // GaussianProcess
