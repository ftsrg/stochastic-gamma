/**
 */
package hu.bme.mit.gamma.environment.stochastic.stochastic.impl;

import hu.bme.mit.gamma.environment.stochastic.stochastic.PoissonProcess;
import hu.bme.mit.gamma.environment.stochastic.stochastic.StochasticPackage;

import org.eclipse.emf.ecore.EClass;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Poisson Process</b></em>'.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public abstract class PoissonProcessImpl extends StochasticProcessImpl implements PoissonProcess {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PoissonProcessImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return StochasticPackage.Literals.POISSON_PROCESS;
	}

} //PoissonProcessImpl
