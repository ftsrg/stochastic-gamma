/**
 */
package hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.impl;

import hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.BroadcastChannelTrace;
import hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.ReducerTracePackage;

import hu.bme.mit.gamma.statechart.composite.BroadcastChannel;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Broadcast Channel Trace</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.impl.BroadcastChannelTraceImpl#getTarget <em>Target</em>}</li>
 *   <li>{@link hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.impl.BroadcastChannelTraceImpl#getSource <em>Source</em>}</li>
 * </ul>
 *
 * @generated
 */
public class BroadcastChannelTraceImpl extends AbstractTraceImpl implements BroadcastChannelTrace {
	/**
	 * The cached value of the '{@link #getTarget() <em>Target</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTarget()
	 * @generated
	 * @ordered
	 */
	protected BroadcastChannel target;

	/**
	 * The cached value of the '{@link #getSource() <em>Source</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSource()
	 * @generated
	 * @ordered
	 */
	protected BroadcastChannel source;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BroadcastChannelTraceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ReducerTracePackage.Literals.BROADCAST_CHANNEL_TRACE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BroadcastChannel getTarget() {
		if (target != null && target.eIsProxy()) {
			InternalEObject oldTarget = (InternalEObject)target;
			target = (BroadcastChannel)eResolveProxy(oldTarget);
			if (target != oldTarget) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ReducerTracePackage.BROADCAST_CHANNEL_TRACE__TARGET, oldTarget, target));
			}
		}
		return target;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BroadcastChannel basicGetTarget() {
		return target;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTarget(BroadcastChannel newTarget) {
		BroadcastChannel oldTarget = target;
		target = newTarget;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ReducerTracePackage.BROADCAST_CHANNEL_TRACE__TARGET, oldTarget, target));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BroadcastChannel getSource() {
		if (source != null && source.eIsProxy()) {
			InternalEObject oldSource = (InternalEObject)source;
			source = (BroadcastChannel)eResolveProxy(oldSource);
			if (source != oldSource) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ReducerTracePackage.BROADCAST_CHANNEL_TRACE__SOURCE, oldSource, source));
			}
		}
		return source;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BroadcastChannel basicGetSource() {
		return source;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSource(BroadcastChannel newSource) {
		BroadcastChannel oldSource = source;
		source = newSource;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ReducerTracePackage.BROADCAST_CHANNEL_TRACE__SOURCE, oldSource, source));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ReducerTracePackage.BROADCAST_CHANNEL_TRACE__TARGET:
				if (resolve) return getTarget();
				return basicGetTarget();
			case ReducerTracePackage.BROADCAST_CHANNEL_TRACE__SOURCE:
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
			case ReducerTracePackage.BROADCAST_CHANNEL_TRACE__TARGET:
				setTarget((BroadcastChannel)newValue);
				return;
			case ReducerTracePackage.BROADCAST_CHANNEL_TRACE__SOURCE:
				setSource((BroadcastChannel)newValue);
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
			case ReducerTracePackage.BROADCAST_CHANNEL_TRACE__TARGET:
				setTarget((BroadcastChannel)null);
				return;
			case ReducerTracePackage.BROADCAST_CHANNEL_TRACE__SOURCE:
				setSource((BroadcastChannel)null);
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
			case ReducerTracePackage.BROADCAST_CHANNEL_TRACE__TARGET:
				return target != null;
			case ReducerTracePackage.BROADCAST_CHANNEL_TRACE__SOURCE:
				return source != null;
		}
		return super.eIsSet(featureID);
	}

} //BroadcastChannelTraceImpl
