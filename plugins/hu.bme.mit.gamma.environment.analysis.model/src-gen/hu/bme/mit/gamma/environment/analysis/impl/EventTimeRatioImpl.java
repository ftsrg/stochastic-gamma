/**
 */
package hu.bme.mit.gamma.environment.analysis.impl;

import hu.bme.mit.gamma.environment.analysis.AnalysisPackage;
import hu.bme.mit.gamma.environment.analysis.ComponentPortEventReference;
import hu.bme.mit.gamma.environment.analysis.EventTimeRatio;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Event Time Ratio</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link hu.bme.mit.gamma.environment.analysis.impl.EventTimeRatioImpl#getEvent2 <em>Event2</em>}</li>
 * </ul>
 *
 * @generated
 */
public class EventTimeRatioImpl extends AnalysisAspectImpl implements EventTimeRatio {
	/**
	 * The cached value of the '{@link #getEvent2() <em>Event2</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEvent2()
	 * @generated
	 * @ordered
	 */
	protected ComponentPortEventReference event2;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EventTimeRatioImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AnalysisPackage.Literals.EVENT_TIME_RATIO;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComponentPortEventReference getEvent2() {
		return event2;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetEvent2(ComponentPortEventReference newEvent2, NotificationChain msgs) {
		ComponentPortEventReference oldEvent2 = event2;
		event2 = newEvent2;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, AnalysisPackage.EVENT_TIME_RATIO__EVENT2, oldEvent2, newEvent2);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEvent2(ComponentPortEventReference newEvent2) {
		if (newEvent2 != event2) {
			NotificationChain msgs = null;
			if (event2 != null)
				msgs = ((InternalEObject)event2).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - AnalysisPackage.EVENT_TIME_RATIO__EVENT2, null, msgs);
			if (newEvent2 != null)
				msgs = ((InternalEObject)newEvent2).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - AnalysisPackage.EVENT_TIME_RATIO__EVENT2, null, msgs);
			msgs = basicSetEvent2(newEvent2, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AnalysisPackage.EVENT_TIME_RATIO__EVENT2, newEvent2, newEvent2));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case AnalysisPackage.EVENT_TIME_RATIO__EVENT2:
				return basicSetEvent2(null, msgs);
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
			case AnalysisPackage.EVENT_TIME_RATIO__EVENT2:
				return getEvent2();
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
			case AnalysisPackage.EVENT_TIME_RATIO__EVENT2:
				setEvent2((ComponentPortEventReference)newValue);
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
			case AnalysisPackage.EVENT_TIME_RATIO__EVENT2:
				setEvent2((ComponentPortEventReference)null);
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
			case AnalysisPackage.EVENT_TIME_RATIO__EVENT2:
				return event2 != null;
		}
		return super.eIsSet(featureID);
	}

} //EventTimeRatioImpl
