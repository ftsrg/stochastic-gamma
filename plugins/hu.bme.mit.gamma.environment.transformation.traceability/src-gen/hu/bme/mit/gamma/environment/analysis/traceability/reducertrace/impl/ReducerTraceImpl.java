/**
 */
package hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.impl;

import hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.AbstractTrace;
import hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.ReducerTrace;
import hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.ReducerTracePackage;

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
 * An implementation of the model object '<em><b>Reducer Trace</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.impl.ReducerTraceImpl#getTraces <em>Traces</em>}</li>
 *   <li>{@link hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.impl.ReducerTraceImpl#getSource <em>Source</em>}</li>
 *   <li>{@link hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.impl.ReducerTraceImpl#getTarget <em>Target</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ReducerTraceImpl extends MinimalEObjectImpl.Container implements ReducerTrace {
	/**
	 * The cached value of the '{@link #getTraces() <em>Traces</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTraces()
	 * @generated
	 * @ordered
	 */
	protected EList<AbstractTrace> traces;

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
	 * The cached value of the '{@link #getTarget() <em>Target</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTarget()
	 * @generated
	 * @ordered
	 */
	protected hu.bme.mit.gamma.statechart.interface_.Package target;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ReducerTraceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ReducerTracePackage.Literals.REDUCER_TRACE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<AbstractTrace> getTraces() {
		if (traces == null) {
			traces = new EObjectContainmentEList<AbstractTrace>(AbstractTrace.class, this, ReducerTracePackage.REDUCER_TRACE__TRACES);
		}
		return traces;
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ReducerTracePackage.REDUCER_TRACE__SOURCE, oldSource, source));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ReducerTracePackage.REDUCER_TRACE__SOURCE, oldSource, source));
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ReducerTracePackage.REDUCER_TRACE__TARGET, oldTarget, target));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ReducerTracePackage.REDUCER_TRACE__TARGET, oldTarget, target));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ReducerTracePackage.REDUCER_TRACE__TRACES:
				return ((InternalEList<?>)getTraces()).basicRemove(otherEnd, msgs);
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
			case ReducerTracePackage.REDUCER_TRACE__TRACES:
				return getTraces();
			case ReducerTracePackage.REDUCER_TRACE__SOURCE:
				if (resolve) return getSource();
				return basicGetSource();
			case ReducerTracePackage.REDUCER_TRACE__TARGET:
				if (resolve) return getTarget();
				return basicGetTarget();
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
			case ReducerTracePackage.REDUCER_TRACE__TRACES:
				getTraces().clear();
				getTraces().addAll((Collection<? extends AbstractTrace>)newValue);
				return;
			case ReducerTracePackage.REDUCER_TRACE__SOURCE:
				setSource((hu.bme.mit.gamma.statechart.interface_.Package)newValue);
				return;
			case ReducerTracePackage.REDUCER_TRACE__TARGET:
				setTarget((hu.bme.mit.gamma.statechart.interface_.Package)newValue);
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
			case ReducerTracePackage.REDUCER_TRACE__TRACES:
				getTraces().clear();
				return;
			case ReducerTracePackage.REDUCER_TRACE__SOURCE:
				setSource((hu.bme.mit.gamma.statechart.interface_.Package)null);
				return;
			case ReducerTracePackage.REDUCER_TRACE__TARGET:
				setTarget((hu.bme.mit.gamma.statechart.interface_.Package)null);
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
			case ReducerTracePackage.REDUCER_TRACE__TRACES:
				return traces != null && !traces.isEmpty();
			case ReducerTracePackage.REDUCER_TRACE__SOURCE:
				return source != null;
			case ReducerTracePackage.REDUCER_TRACE__TARGET:
				return target != null;
		}
		return super.eIsSet(featureID);
	}

} //ReducerTraceImpl
