/**
 */
package hu.bme.mit.gamma.environment.model.impl;

import hu.bme.mit.gamma.environment.model.EnvironentPackage;
import hu.bme.mit.gamma.environment.model.EnvironmentCascadeCompositeComponent;
import hu.bme.mit.gamma.environment.model.EnvironmentCascadeCompositeComponentInstance;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Environment Cascade Composite Component Instance</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link hu.bme.mit.gamma.environment.model.impl.EnvironmentCascadeCompositeComponentInstanceImpl#getType <em>Type</em>}</li>
 * </ul>
 *
 * @generated
 */
public class EnvironmentCascadeCompositeComponentInstanceImpl extends AbstractEnvironmentCompositeComponentInstanceImpl implements EnvironmentCascadeCompositeComponentInstance {
	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected EnvironmentCascadeCompositeComponent type;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EnvironmentCascadeCompositeComponentInstanceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EnvironentPackage.Literals.ENVIRONMENT_CASCADE_COMPOSITE_COMPONENT_INSTANCE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EnvironmentCascadeCompositeComponent getType() {
		if (type != null && type.eIsProxy()) {
			InternalEObject oldType = (InternalEObject)type;
			type = (EnvironmentCascadeCompositeComponent)eResolveProxy(oldType);
			if (type != oldType) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, EnvironentPackage.ENVIRONMENT_CASCADE_COMPOSITE_COMPONENT_INSTANCE__TYPE, oldType, type));
			}
		}
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EnvironmentCascadeCompositeComponent basicGetType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setType(EnvironmentCascadeCompositeComponent newType) {
		EnvironmentCascadeCompositeComponent oldType = type;
		type = newType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EnvironentPackage.ENVIRONMENT_CASCADE_COMPOSITE_COMPONENT_INSTANCE__TYPE, oldType, type));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case EnvironentPackage.ENVIRONMENT_CASCADE_COMPOSITE_COMPONENT_INSTANCE__TYPE:
				if (resolve) return getType();
				return basicGetType();
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
			case EnvironentPackage.ENVIRONMENT_CASCADE_COMPOSITE_COMPONENT_INSTANCE__TYPE:
				setType((EnvironmentCascadeCompositeComponent)newValue);
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
			case EnvironentPackage.ENVIRONMENT_CASCADE_COMPOSITE_COMPONENT_INSTANCE__TYPE:
				setType((EnvironmentCascadeCompositeComponent)null);
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
			case EnvironentPackage.ENVIRONMENT_CASCADE_COMPOSITE_COMPONENT_INSTANCE__TYPE:
				return type != null;
		}
		return super.eIsSet(featureID);
	}

} //EnvironmentCascadeCompositeComponentInstanceImpl
