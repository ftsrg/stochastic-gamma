/**
 */
package hu.bme.mit.gamma.fmeda.model.impl;

import hu.bme.mit.gamma.fmeda.model.DiagnosticsReference;
import hu.bme.mit.gamma.fmeda.model.FailureMode;
import hu.bme.mit.gamma.fmeda.model.fmedaPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Failure Mode</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link hu.bme.mit.gamma.fmeda.model.impl.FailureModeImpl#getDiagnostics <em>Diagnostics</em>}</li>
 * </ul>
 *
 * @generated
 */
public class FailureModeImpl extends NamedElementImpl implements FailureMode {
	/**
	 * The cached value of the '{@link #getDiagnostics() <em>Diagnostics</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDiagnostics()
	 * @generated
	 * @ordered
	 */
	protected DiagnosticsReference diagnostics;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FailureModeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return fmedaPackage.Literals.FAILURE_MODE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DiagnosticsReference getDiagnostics() {
		return diagnostics;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDiagnostics(DiagnosticsReference newDiagnostics, NotificationChain msgs) {
		DiagnosticsReference oldDiagnostics = diagnostics;
		diagnostics = newDiagnostics;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, fmedaPackage.FAILURE_MODE__DIAGNOSTICS, oldDiagnostics, newDiagnostics);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDiagnostics(DiagnosticsReference newDiagnostics) {
		if (newDiagnostics != diagnostics) {
			NotificationChain msgs = null;
			if (diagnostics != null)
				msgs = ((InternalEObject)diagnostics).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - fmedaPackage.FAILURE_MODE__DIAGNOSTICS, null, msgs);
			if (newDiagnostics != null)
				msgs = ((InternalEObject)newDiagnostics).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - fmedaPackage.FAILURE_MODE__DIAGNOSTICS, null, msgs);
			msgs = basicSetDiagnostics(newDiagnostics, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, fmedaPackage.FAILURE_MODE__DIAGNOSTICS, newDiagnostics, newDiagnostics));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case fmedaPackage.FAILURE_MODE__DIAGNOSTICS:
				return basicSetDiagnostics(null, msgs);
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
			case fmedaPackage.FAILURE_MODE__DIAGNOSTICS:
				return getDiagnostics();
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
			case fmedaPackage.FAILURE_MODE__DIAGNOSTICS:
				setDiagnostics((DiagnosticsReference)newValue);
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
			case fmedaPackage.FAILURE_MODE__DIAGNOSTICS:
				setDiagnostics((DiagnosticsReference)null);
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
			case fmedaPackage.FAILURE_MODE__DIAGNOSTICS:
				return diagnostics != null;
		}
		return super.eIsSet(featureID);
	}

} //FailureModeImpl
