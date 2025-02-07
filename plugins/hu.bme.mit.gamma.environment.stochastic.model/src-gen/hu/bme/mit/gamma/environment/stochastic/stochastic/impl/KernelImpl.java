/**
 */
package hu.bme.mit.gamma.environment.stochastic.stochastic.impl;

import hu.bme.mit.gamma.environment.stochastic.stochastic.Kernel;
import hu.bme.mit.gamma.environment.stochastic.stochastic.StochasticPackage;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Kernel</b></em>'.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public abstract class KernelImpl extends MinimalEObjectImpl.Container implements Kernel {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected KernelImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return StochasticPackage.Literals.KERNEL;
	}

} //KernelImpl
