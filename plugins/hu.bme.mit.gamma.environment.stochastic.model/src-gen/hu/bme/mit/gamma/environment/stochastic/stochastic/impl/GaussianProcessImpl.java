/**
 */
package hu.bme.mit.gamma.environment.stochastic.stochastic.impl;

import hu.bme.mit.gamma.environment.stochastic.stochastic.GaussianProcess;
import hu.bme.mit.gamma.environment.stochastic.stochastic.Kernel;
import hu.bme.mit.gamma.environment.stochastic.stochastic.StochasticPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Gaussian Process</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link hu.bme.mit.gamma.environment.stochastic.stochastic.impl.GaussianProcessImpl#getKernel <em>Kernel</em>}</li>
 * </ul>
 *
 * @generated
 */
public class GaussianProcessImpl extends StochasticProcessImpl implements GaussianProcess {
	/**
	 * The cached value of the '{@link #getKernel() <em>Kernel</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKernel()
	 * @generated
	 * @ordered
	 */
	protected Kernel kernel;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GaussianProcessImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return StochasticPackage.Literals.GAUSSIAN_PROCESS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Kernel getKernel() {
		return kernel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetKernel(Kernel newKernel, NotificationChain msgs) {
		Kernel oldKernel = kernel;
		kernel = newKernel;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, StochasticPackage.GAUSSIAN_PROCESS__KERNEL, oldKernel, newKernel);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setKernel(Kernel newKernel) {
		if (newKernel != kernel) {
			NotificationChain msgs = null;
			if (kernel != null)
				msgs = ((InternalEObject)kernel).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - StochasticPackage.GAUSSIAN_PROCESS__KERNEL, null, msgs);
			if (newKernel != null)
				msgs = ((InternalEObject)newKernel).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - StochasticPackage.GAUSSIAN_PROCESS__KERNEL, null, msgs);
			msgs = basicSetKernel(newKernel, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StochasticPackage.GAUSSIAN_PROCESS__KERNEL, newKernel, newKernel));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case StochasticPackage.GAUSSIAN_PROCESS__KERNEL:
				return basicSetKernel(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case StochasticPackage.GAUSSIAN_PROCESS__KERNEL:
				return getKernel();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case StochasticPackage.GAUSSIAN_PROCESS__KERNEL:
				setKernel((Kernel)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case StochasticPackage.GAUSSIAN_PROCESS__KERNEL:
				setKernel((Kernel)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case StochasticPackage.GAUSSIAN_PROCESS__KERNEL:
				return kernel != null;
		}
		return super.eIsSet(featureID);
	}

} //GaussianProcessImpl
