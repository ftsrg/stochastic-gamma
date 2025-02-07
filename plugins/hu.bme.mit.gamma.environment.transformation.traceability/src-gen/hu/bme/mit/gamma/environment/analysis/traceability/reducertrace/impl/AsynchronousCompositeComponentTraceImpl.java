/**
 */
package hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.impl;

import hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.AsynchronousCompositeComponentTrace;
import hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.ReducerTracePackage;

import hu.bme.mit.gamma.environment.model.EnvironmentAsynchronousCompositeComponent;

import hu.bme.mit.gamma.statechart.composite.AsynchronousCompositeComponent;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Asynchronous Composite Component Trace</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.impl.AsynchronousCompositeComponentTraceImpl#getSource <em>Source</em>}</li>
 *   <li>{@link hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.impl.AsynchronousCompositeComponentTraceImpl#getTarget <em>Target</em>}</li>
 * </ul>
 *
 * @generated
 */
public class AsynchronousCompositeComponentTraceImpl extends AbstractTraceImpl implements AsynchronousCompositeComponentTrace {
	/**
	 * The cached value of the '{@link #getSource() <em>Source</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSource()
	 * @generated
	 * @ordered
	 */
	protected EnvironmentAsynchronousCompositeComponent source;

	/**
	 * The cached value of the '{@link #getTarget() <em>Target</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTarget()
	 * @generated
	 * @ordered
	 */
	protected AsynchronousCompositeComponent target;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AsynchronousCompositeComponentTraceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ReducerTracePackage.Literals.ASYNCHRONOUS_COMPOSITE_COMPONENT_TRACE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EnvironmentAsynchronousCompositeComponent getSource() {
		if (source != null && source.eIsProxy()) {
			InternalEObject oldSource = (InternalEObject)source;
			source = (EnvironmentAsynchronousCompositeComponent)eResolveProxy(oldSource);
			if (source != oldSource) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ReducerTracePackage.ASYNCHRONOUS_COMPOSITE_COMPONENT_TRACE__SOURCE, oldSource, source));
			}
		}
		return source;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EnvironmentAsynchronousCompositeComponent basicGetSource() {
		return source;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSource(EnvironmentAsynchronousCompositeComponent newSource) {
		EnvironmentAsynchronousCompositeComponent oldSource = source;
		source = newSource;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ReducerTracePackage.ASYNCHRONOUS_COMPOSITE_COMPONENT_TRACE__SOURCE, oldSource, source));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AsynchronousCompositeComponent getTarget() {
		if (target != null && target.eIsProxy()) {
			InternalEObject oldTarget = (InternalEObject)target;
			target = (AsynchronousCompositeComponent)eResolveProxy(oldTarget);
			if (target != oldTarget) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ReducerTracePackage.ASYNCHRONOUS_COMPOSITE_COMPONENT_TRACE__TARGET, oldTarget, target));
			}
		}
		return target;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AsynchronousCompositeComponent basicGetTarget() {
		return target;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTarget(AsynchronousCompositeComponent newTarget) {
		AsynchronousCompositeComponent oldTarget = target;
		target = newTarget;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ReducerTracePackage.ASYNCHRONOUS_COMPOSITE_COMPONENT_TRACE__TARGET, oldTarget, target));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ReducerTracePackage.ASYNCHRONOUS_COMPOSITE_COMPONENT_TRACE__SOURCE:
				if (resolve) return getSource();
				return basicGetSource();
			case ReducerTracePackage.ASYNCHRONOUS_COMPOSITE_COMPONENT_TRACE__TARGET:
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
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ReducerTracePackage.ASYNCHRONOUS_COMPOSITE_COMPONENT_TRACE__SOURCE:
				setSource((EnvironmentAsynchronousCompositeComponent)newValue);
				return;
			case ReducerTracePackage.ASYNCHRONOUS_COMPOSITE_COMPONENT_TRACE__TARGET:
				setTarget((AsynchronousCompositeComponent)newValue);
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
			case ReducerTracePackage.ASYNCHRONOUS_COMPOSITE_COMPONENT_TRACE__SOURCE:
				setSource((EnvironmentAsynchronousCompositeComponent)null);
				return;
			case ReducerTracePackage.ASYNCHRONOUS_COMPOSITE_COMPONENT_TRACE__TARGET:
				setTarget((AsynchronousCompositeComponent)null);
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
			case ReducerTracePackage.ASYNCHRONOUS_COMPOSITE_COMPONENT_TRACE__SOURCE:
				return source != null;
			case ReducerTracePackage.ASYNCHRONOUS_COMPOSITE_COMPONENT_TRACE__TARGET:
				return target != null;
		}
		return super.eIsSet(featureID);
	}

} //AsynchronousCompositeComponentTraceImpl
