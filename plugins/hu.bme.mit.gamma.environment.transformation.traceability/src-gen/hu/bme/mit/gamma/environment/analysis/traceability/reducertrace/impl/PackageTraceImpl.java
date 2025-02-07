/**
 */
package hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.impl;

import hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.PackageTrace;
import hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.ReducerTracePackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Package Trace</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.impl.PackageTraceImpl#getTarget <em>Target</em>}</li>
 *   <li>{@link hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.impl.PackageTraceImpl#getSource <em>Source</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PackageTraceImpl extends AbstractTraceImpl implements PackageTrace {
	/**
	 * The cached value of the '{@link #getTarget() <em>Target</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTarget()
	 * @generated
	 * @ordered
	 */
	protected hu.bme.mit.gamma.statechart.interface_.Package target;

	/**
	 * The cached value of the '{@link #getSource() <em>Source</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSource()
	 * @generated
	 * @ordered
	 */
	protected hu.bme.mit.gamma.statechart.interface_.Package source;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PackageTraceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ReducerTracePackage.Literals.PACKAGE_TRACE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public hu.bme.mit.gamma.statechart.interface_.Package getTarget() {
		if (target != null && target.eIsProxy()) {
			InternalEObject oldTarget = (InternalEObject)target;
			target = (hu.bme.mit.gamma.statechart.interface_.Package)eResolveProxy(oldTarget);
			if (target != oldTarget) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ReducerTracePackage.PACKAGE_TRACE__TARGET, oldTarget, target));
			}
		}
		return target;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public hu.bme.mit.gamma.statechart.interface_.Package basicGetTarget() {
		return target;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTarget(hu.bme.mit.gamma.statechart.interface_.Package newTarget) {
		hu.bme.mit.gamma.statechart.interface_.Package oldTarget = target;
		target = newTarget;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ReducerTracePackage.PACKAGE_TRACE__TARGET, oldTarget, target));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public hu.bme.mit.gamma.statechart.interface_.Package getSource() {
		if (source != null && source.eIsProxy()) {
			InternalEObject oldSource = (InternalEObject)source;
			source = (hu.bme.mit.gamma.statechart.interface_.Package)eResolveProxy(oldSource);
			if (source != oldSource) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ReducerTracePackage.PACKAGE_TRACE__SOURCE, oldSource, source));
			}
		}
		return source;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public hu.bme.mit.gamma.statechart.interface_.Package basicGetSource() {
		return source;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSource(hu.bme.mit.gamma.statechart.interface_.Package newSource) {
		hu.bme.mit.gamma.statechart.interface_.Package oldSource = source;
		source = newSource;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ReducerTracePackage.PACKAGE_TRACE__SOURCE, oldSource, source));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ReducerTracePackage.PACKAGE_TRACE__TARGET:
				if (resolve) return getTarget();
				return basicGetTarget();
			case ReducerTracePackage.PACKAGE_TRACE__SOURCE:
				if (resolve) return getSource();
				return basicGetSource();
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
			case ReducerTracePackage.PACKAGE_TRACE__TARGET:
				setTarget((hu.bme.mit.gamma.statechart.interface_.Package)newValue);
				return;
			case ReducerTracePackage.PACKAGE_TRACE__SOURCE:
				setSource((hu.bme.mit.gamma.statechart.interface_.Package)newValue);
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
			case ReducerTracePackage.PACKAGE_TRACE__TARGET:
				setTarget((hu.bme.mit.gamma.statechart.interface_.Package)null);
				return;
			case ReducerTracePackage.PACKAGE_TRACE__SOURCE:
				setSource((hu.bme.mit.gamma.statechart.interface_.Package)null);
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
			case ReducerTracePackage.PACKAGE_TRACE__TARGET:
				return target != null;
			case ReducerTracePackage.PACKAGE_TRACE__SOURCE:
				return source != null;
		}
		return super.eIsSet(featureID);
	}

} //PackageTraceImpl
