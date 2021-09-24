/**
 */
package hu.bme.mit.gamma.stochastic.stochastic;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Sum Kernel</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link hu.bme.mit.gamma.stochastic.stochastic.SumKernel#getKernels <em>Kernels</em>}</li>
 * </ul>
 *
 * @see hu.bme.mit.gamma.stochastic.stochastic.StochasticPackage#getSumKernel()
 * @model
 * @generated
 */
public interface SumKernel extends Kernel {
	/**
	 * Returns the value of the '<em><b>Kernels</b></em>' containment reference list.
	 * The list contents are of type {@link hu.bme.mit.gamma.stochastic.stochastic.Kernel}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Kernels</em>' containment reference list.
	 * @see hu.bme.mit.gamma.stochastic.stochastic.StochasticPackage#getSumKernel_Kernels()
	 * @model containment="true" lower="2" upper="2"
	 * @generated
	 */
	EList<Kernel> getKernels();

} // SumKernel
