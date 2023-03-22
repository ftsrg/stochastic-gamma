/**
 */
package hu.bme.mit.gamma.fmeda.model.impl;

import hu.bme.mit.gamma.fmeda.model.ComponentReference;
import hu.bme.mit.gamma.fmeda.model.FMEDAComponentInstance;
import hu.bme.mit.gamma.fmeda.model.Port;
import hu.bme.mit.gamma.fmeda.model.PortFailureModeReference;
import hu.bme.mit.gamma.fmeda.model.PortReference;
import hu.bme.mit.gamma.fmeda.model.fmedaPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Port Failure Mode Reference</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link hu.bme.mit.gamma.fmeda.model.impl.PortFailureModeReferenceImpl#getPort <em>Port</em>}</li>
 *   <li>{@link hu.bme.mit.gamma.fmeda.model.impl.PortFailureModeReferenceImpl#getPart <em>Part</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PortFailureModeReferenceImpl extends FailureModeReferenceImpl implements PortFailureModeReference {
	/**
	 * The cached value of the '{@link #getPort() <em>Port</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPort()
	 * @generated
	 * @ordered
	 */
	protected Port port;

	/**
	 * The cached value of the '{@link #getPart() <em>Part</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPart()
	 * @generated
	 * @ordered
	 */
	protected FMEDAComponentInstance part;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PortFailureModeReferenceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return fmedaPackage.Literals.PORT_FAILURE_MODE_REFERENCE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Port getPort() {
		if (port != null && port.eIsProxy()) {
			InternalEObject oldPort = (InternalEObject)port;
			port = (Port)eResolveProxy(oldPort);
			if (port != oldPort) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, fmedaPackage.PORT_FAILURE_MODE_REFERENCE__PORT, oldPort, port));
			}
		}
		return port;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Port basicGetPort() {
		return port;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPort(Port newPort) {
		Port oldPort = port;
		port = newPort;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, fmedaPackage.PORT_FAILURE_MODE_REFERENCE__PORT, oldPort, port));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FMEDAComponentInstance getPart() {
		if (part != null && part.eIsProxy()) {
			InternalEObject oldPart = (InternalEObject)part;
			part = (FMEDAComponentInstance)eResolveProxy(oldPart);
			if (part != oldPart) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, fmedaPackage.PORT_FAILURE_MODE_REFERENCE__PART, oldPart, part));
			}
		}
		return part;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FMEDAComponentInstance basicGetPart() {
		return part;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPart(FMEDAComponentInstance newPart) {
		FMEDAComponentInstance oldPart = part;
		part = newPart;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, fmedaPackage.PORT_FAILURE_MODE_REFERENCE__PART, oldPart, part));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case fmedaPackage.PORT_FAILURE_MODE_REFERENCE__PORT:
				if (resolve) return getPort();
				return basicGetPort();
			case fmedaPackage.PORT_FAILURE_MODE_REFERENCE__PART:
				if (resolve) return getPart();
				return basicGetPart();
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
			case fmedaPackage.PORT_FAILURE_MODE_REFERENCE__PORT:
				setPort((Port)newValue);
				return;
			case fmedaPackage.PORT_FAILURE_MODE_REFERENCE__PART:
				setPart((FMEDAComponentInstance)newValue);
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
			case fmedaPackage.PORT_FAILURE_MODE_REFERENCE__PORT:
				setPort((Port)null);
				return;
			case fmedaPackage.PORT_FAILURE_MODE_REFERENCE__PART:
				setPart((FMEDAComponentInstance)null);
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
			case fmedaPackage.PORT_FAILURE_MODE_REFERENCE__PORT:
				return port != null;
			case fmedaPackage.PORT_FAILURE_MODE_REFERENCE__PART:
				return part != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == PortReference.class) {
			switch (derivedFeatureID) {
				case fmedaPackage.PORT_FAILURE_MODE_REFERENCE__PORT: return fmedaPackage.PORT_REFERENCE__PORT;
				default: return -1;
			}
		}
		if (baseClass == ComponentReference.class) {
			switch (derivedFeatureID) {
				case fmedaPackage.PORT_FAILURE_MODE_REFERENCE__PART: return fmedaPackage.COMPONENT_REFERENCE__PART;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == PortReference.class) {
			switch (baseFeatureID) {
				case fmedaPackage.PORT_REFERENCE__PORT: return fmedaPackage.PORT_FAILURE_MODE_REFERENCE__PORT;
				default: return -1;
			}
		}
		if (baseClass == ComponentReference.class) {
			switch (baseFeatureID) {
				case fmedaPackage.COMPONENT_REFERENCE__PART: return fmedaPackage.PORT_FAILURE_MODE_REFERENCE__PART;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

} //PortFailureModeReferenceImpl
