/**
 */
package hu.bme.mit.gamma.fmeda.model.impl;

import hu.bme.mit.gamma.fmeda.model.FailureMode;
import hu.bme.mit.gamma.fmeda.model.FailureModeReference;
import hu.bme.mit.gamma.fmeda.model.fmedaPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Failure Mode Reference</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link hu.bme.mit.gamma.fmeda.model.impl.FailureModeReferenceImpl#getFailuremode <em>Failuremode</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class FailureModeReferenceImpl extends MinimalEObjectImpl.Container implements FailureModeReference {
	/**
	 * The cached value of the '{@link #getFailuremode() <em>Failuremode</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFailuremode()
	 * @generated
	 * @ordered
	 */
	protected FailureMode failuremode;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FailureModeReferenceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return fmedaPackage.Literals.FAILURE_MODE_REFERENCE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FailureMode getFailuremode() {
		if (failuremode != null && failuremode.eIsProxy()) {
			InternalEObject oldFailuremode = (InternalEObject)failuremode;
			failuremode = (FailureMode)eResolveProxy(oldFailuremode);
			if (failuremode != oldFailuremode) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, fmedaPackage.FAILURE_MODE_REFERENCE__FAILUREMODE, oldFailuremode, failuremode));
			}
		}
		return failuremode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FailureMode basicGetFailuremode() {
		return failuremode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFailuremode(FailureMode newFailuremode) {
		FailureMode oldFailuremode = failuremode;
		failuremode = newFailuremode;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, fmedaPackage.FAILURE_MODE_REFERENCE__FAILUREMODE, oldFailuremode, failuremode));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case fmedaPackage.FAILURE_MODE_REFERENCE__FAILUREMODE:
				if (resolve) return getFailuremode();
				return basicGetFailuremode();
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
			case fmedaPackage.FAILURE_MODE_REFERENCE__FAILUREMODE:
				setFailuremode((FailureMode)newValue);
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
			case fmedaPackage.FAILURE_MODE_REFERENCE__FAILUREMODE:
				setFailuremode((FailureMode)null);
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
			case fmedaPackage.FAILURE_MODE_REFERENCE__FAILUREMODE:
				return failuremode != null;
		}
		return super.eIsSet(featureID);
	}

} //FailureModeReferenceImpl
