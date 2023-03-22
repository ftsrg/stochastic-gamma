/**
 */
package hu.bme.mit.gamma.fmeda.model.impl;

import hu.bme.mit.gamma.fmeda.model.DiagnosticsReference;
import hu.bme.mit.gamma.fmeda.model.FMEDADiagnostics;
import hu.bme.mit.gamma.fmeda.model.fmedaPackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Diagnostics Reference</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link hu.bme.mit.gamma.fmeda.model.impl.DiagnosticsReferenceImpl#getCoverage <em>Coverage</em>}</li>
 *   <li>{@link hu.bme.mit.gamma.fmeda.model.impl.DiagnosticsReferenceImpl#getFmedadiagnostics <em>Fmedadiagnostics</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DiagnosticsReferenceImpl extends MinimalEObjectImpl.Container implements DiagnosticsReference {
	/**
	 * The default value of the '{@link #getCoverage() <em>Coverage</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCoverage()
	 * @generated
	 * @ordered
	 */
	protected static final double COVERAGE_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getCoverage() <em>Coverage</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCoverage()
	 * @generated
	 * @ordered
	 */
	protected double coverage = COVERAGE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getFmedadiagnostics() <em>Fmedadiagnostics</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFmedadiagnostics()
	 * @generated
	 * @ordered
	 */
	protected EList<FMEDADiagnostics> fmedadiagnostics;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DiagnosticsReferenceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return fmedaPackage.Literals.DIAGNOSTICS_REFERENCE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getCoverage() {
		return coverage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCoverage(double newCoverage) {
		double oldCoverage = coverage;
		coverage = newCoverage;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, fmedaPackage.DIAGNOSTICS_REFERENCE__COVERAGE, oldCoverage, coverage));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<FMEDADiagnostics> getFmedadiagnostics() {
		if (fmedadiagnostics == null) {
			fmedadiagnostics = new EObjectResolvingEList<FMEDADiagnostics>(FMEDADiagnostics.class, this, fmedaPackage.DIAGNOSTICS_REFERENCE__FMEDADIAGNOSTICS);
		}
		return fmedadiagnostics;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case fmedaPackage.DIAGNOSTICS_REFERENCE__COVERAGE:
				return getCoverage();
			case fmedaPackage.DIAGNOSTICS_REFERENCE__FMEDADIAGNOSTICS:
				return getFmedadiagnostics();
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
			case fmedaPackage.DIAGNOSTICS_REFERENCE__COVERAGE:
				setCoverage((Double)newValue);
				return;
			case fmedaPackage.DIAGNOSTICS_REFERENCE__FMEDADIAGNOSTICS:
				getFmedadiagnostics().clear();
				getFmedadiagnostics().addAll((Collection<? extends FMEDADiagnostics>)newValue);
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
			case fmedaPackage.DIAGNOSTICS_REFERENCE__COVERAGE:
				setCoverage(COVERAGE_EDEFAULT);
				return;
			case fmedaPackage.DIAGNOSTICS_REFERENCE__FMEDADIAGNOSTICS:
				getFmedadiagnostics().clear();
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
			case fmedaPackage.DIAGNOSTICS_REFERENCE__COVERAGE:
				return coverage != COVERAGE_EDEFAULT;
			case fmedaPackage.DIAGNOSTICS_REFERENCE__FMEDADIAGNOSTICS:
				return fmedadiagnostics != null && !fmedadiagnostics.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (coverage: ");
		result.append(coverage);
		result.append(')');
		return result.toString();
	}

} //DiagnosticsReferenceImpl
