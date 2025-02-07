/**
 */
package hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.impl;

import hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.ElementaryTrace;
import hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.ReducerTracePackage;

import hu.bme.mit.gamma.environment.model.ElementaryEnvironmentComponentInstance;

import hu.bme.mit.gamma.statechart.composite.ComponentInstance;
import hu.bme.mit.gamma.statechart.statechart.StatechartDefinition;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Elementary Trace</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.impl.ElementaryTraceImpl#getSource <em>Source</em>}</li>
 *   <li>{@link hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.impl.ElementaryTraceImpl#getTypeTarget <em>Type Target</em>}</li>
 *   <li>{@link hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.impl.ElementaryTraceImpl#getInstanceTarget <em>Instance Target</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ElementaryTraceImpl extends AbstractTraceImpl implements ElementaryTrace {
	/**
	 * The cached value of the '{@link #getSource() <em>Source</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSource()
	 * @generated
	 * @ordered
	 */
	protected ElementaryEnvironmentComponentInstance source;

	/**
	 * The cached value of the '{@link #getTypeTarget() <em>Type Target</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTypeTarget()
	 * @generated
	 * @ordered
	 */
	protected StatechartDefinition typeTarget;

	/**
	 * The cached value of the '{@link #getInstanceTarget() <em>Instance Target</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInstanceTarget()
	 * @generated
	 * @ordered
	 */
	protected ComponentInstance instanceTarget;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ElementaryTraceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ReducerTracePackage.Literals.ELEMENTARY_TRACE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ElementaryEnvironmentComponentInstance getSource() {
		if (source != null && source.eIsProxy()) {
			InternalEObject oldSource = (InternalEObject)source;
			source = (ElementaryEnvironmentComponentInstance)eResolveProxy(oldSource);
			if (source != oldSource) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ReducerTracePackage.ELEMENTARY_TRACE__SOURCE, oldSource, source));
			}
		}
		return source;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ElementaryEnvironmentComponentInstance basicGetSource() {
		return source;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSource(ElementaryEnvironmentComponentInstance newSource) {
		ElementaryEnvironmentComponentInstance oldSource = source;
		source = newSource;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ReducerTracePackage.ELEMENTARY_TRACE__SOURCE, oldSource, source));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StatechartDefinition getTypeTarget() {
		if (typeTarget != null && typeTarget.eIsProxy()) {
			InternalEObject oldTypeTarget = (InternalEObject)typeTarget;
			typeTarget = (StatechartDefinition)eResolveProxy(oldTypeTarget);
			if (typeTarget != oldTypeTarget) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ReducerTracePackage.ELEMENTARY_TRACE__TYPE_TARGET, oldTypeTarget, typeTarget));
			}
		}
		return typeTarget;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StatechartDefinition basicGetTypeTarget() {
		return typeTarget;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTypeTarget(StatechartDefinition newTypeTarget) {
		StatechartDefinition oldTypeTarget = typeTarget;
		typeTarget = newTypeTarget;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ReducerTracePackage.ELEMENTARY_TRACE__TYPE_TARGET, oldTypeTarget, typeTarget));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComponentInstance getInstanceTarget() {
		if (instanceTarget != null && instanceTarget.eIsProxy()) {
			InternalEObject oldInstanceTarget = (InternalEObject)instanceTarget;
			instanceTarget = (ComponentInstance)eResolveProxy(oldInstanceTarget);
			if (instanceTarget != oldInstanceTarget) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ReducerTracePackage.ELEMENTARY_TRACE__INSTANCE_TARGET, oldInstanceTarget, instanceTarget));
			}
		}
		return instanceTarget;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComponentInstance basicGetInstanceTarget() {
		return instanceTarget;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInstanceTarget(ComponentInstance newInstanceTarget) {
		ComponentInstance oldInstanceTarget = instanceTarget;
		instanceTarget = newInstanceTarget;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ReducerTracePackage.ELEMENTARY_TRACE__INSTANCE_TARGET, oldInstanceTarget, instanceTarget));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ReducerTracePackage.ELEMENTARY_TRACE__SOURCE:
				if (resolve) return getSource();
				return basicGetSource();
			case ReducerTracePackage.ELEMENTARY_TRACE__TYPE_TARGET:
				if (resolve) return getTypeTarget();
				return basicGetTypeTarget();
			case ReducerTracePackage.ELEMENTARY_TRACE__INSTANCE_TARGET:
				if (resolve) return getInstanceTarget();
				return basicGetInstanceTarget();
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
			case ReducerTracePackage.ELEMENTARY_TRACE__SOURCE:
				setSource((ElementaryEnvironmentComponentInstance)newValue);
				return;
			case ReducerTracePackage.ELEMENTARY_TRACE__TYPE_TARGET:
				setTypeTarget((StatechartDefinition)newValue);
				return;
			case ReducerTracePackage.ELEMENTARY_TRACE__INSTANCE_TARGET:
				setInstanceTarget((ComponentInstance)newValue);
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
			case ReducerTracePackage.ELEMENTARY_TRACE__SOURCE:
				setSource((ElementaryEnvironmentComponentInstance)null);
				return;
			case ReducerTracePackage.ELEMENTARY_TRACE__TYPE_TARGET:
				setTypeTarget((StatechartDefinition)null);
				return;
			case ReducerTracePackage.ELEMENTARY_TRACE__INSTANCE_TARGET:
				setInstanceTarget((ComponentInstance)null);
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
			case ReducerTracePackage.ELEMENTARY_TRACE__SOURCE:
				return source != null;
			case ReducerTracePackage.ELEMENTARY_TRACE__TYPE_TARGET:
				return typeTarget != null;
			case ReducerTracePackage.ELEMENTARY_TRACE__INSTANCE_TARGET:
				return instanceTarget != null;
		}
		return super.eIsSet(featureID);
	}

} //ElementaryTraceImpl
