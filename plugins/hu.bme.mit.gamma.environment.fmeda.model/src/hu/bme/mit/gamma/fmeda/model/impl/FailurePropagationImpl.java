/**
 */
package hu.bme.mit.gamma.fmeda.model.impl;

import hu.bme.mit.gamma.fmeda.model.FailureModeReference;
import hu.bme.mit.gamma.fmeda.model.FailurePropagation;
import hu.bme.mit.gamma.fmeda.model.fmedaPackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Failure Propagation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link hu.bme.mit.gamma.fmeda.model.impl.FailurePropagationImpl#getCause <em>Cause</em>}</li>
 *   <li>{@link hu.bme.mit.gamma.fmeda.model.impl.FailurePropagationImpl#getEffect <em>Effect</em>}</li>
 * </ul>
 *
 * @generated
 */
public class FailurePropagationImpl extends MinimalEObjectImpl.Container implements FailurePropagation {
	/**
	 * The cached value of the '{@link #getCause() <em>Cause</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCause()
	 * @generated
	 * @ordered
	 */
	protected FailureModeReference cause;

	/**
	 * The cached value of the '{@link #getEffect() <em>Effect</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEffect()
	 * @generated
	 * @ordered
	 */
	protected EList<FailureModeReference> effect;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FailurePropagationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return fmedaPackage.Literals.FAILURE_PROPAGATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FailureModeReference getCause() {
		return cause;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCause(FailureModeReference newCause, NotificationChain msgs) {
		FailureModeReference oldCause = cause;
		cause = newCause;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, fmedaPackage.FAILURE_PROPAGATION__CAUSE, oldCause, newCause);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCause(FailureModeReference newCause) {
		if (newCause != cause) {
			NotificationChain msgs = null;
			if (cause != null)
				msgs = ((InternalEObject)cause).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - fmedaPackage.FAILURE_PROPAGATION__CAUSE, null, msgs);
			if (newCause != null)
				msgs = ((InternalEObject)newCause).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - fmedaPackage.FAILURE_PROPAGATION__CAUSE, null, msgs);
			msgs = basicSetCause(newCause, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, fmedaPackage.FAILURE_PROPAGATION__CAUSE, newCause, newCause));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<FailureModeReference> getEffect() {
		if (effect == null) {
			effect = new EObjectContainmentEList<FailureModeReference>(FailureModeReference.class, this, fmedaPackage.FAILURE_PROPAGATION__EFFECT);
		}
		return effect;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case fmedaPackage.FAILURE_PROPAGATION__CAUSE:
				return basicSetCause(null, msgs);
			case fmedaPackage.FAILURE_PROPAGATION__EFFECT:
				return ((InternalEList<?>)getEffect()).basicRemove(otherEnd, msgs);
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
			case fmedaPackage.FAILURE_PROPAGATION__CAUSE:
				return getCause();
			case fmedaPackage.FAILURE_PROPAGATION__EFFECT:
				return getEffect();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case fmedaPackage.FAILURE_PROPAGATION__CAUSE:
				setCause((FailureModeReference)newValue);
				return;
			case fmedaPackage.FAILURE_PROPAGATION__EFFECT:
				getEffect().clear();
				getEffect().addAll((Collection<? extends FailureModeReference>)newValue);
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
			case fmedaPackage.FAILURE_PROPAGATION__CAUSE:
				setCause((FailureModeReference)null);
				return;
			case fmedaPackage.FAILURE_PROPAGATION__EFFECT:
				getEffect().clear();
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
			case fmedaPackage.FAILURE_PROPAGATION__CAUSE:
				return cause != null;
			case fmedaPackage.FAILURE_PROPAGATION__EFFECT:
				return effect != null && !effect.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //FailurePropagationImpl
