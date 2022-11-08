/**
 */
package hu.bme.mit.gamma.analysis.impl;

import hu.bme.mit.gamma.analysis.AnalysisPackage;
import hu.bme.mit.gamma.analysis.ObserveCondition;

import hu.bme.mit.gamma.stochastic.stochastic.StochasticModel;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Observe Condition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link hu.bme.mit.gamma.analysis.impl.ObserveConditionImpl#getValue <em>Value</em>}</li>
 *   <li>{@link hu.bme.mit.gamma.analysis.impl.ObserveConditionImpl#getRandomvariable <em>Randomvariable</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class ObserveConditionImpl extends AnalysisConditionImpl implements ObserveCondition {
	/**
	 * The default value of the '{@link #getValue() <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValue()
	 * @generated
	 * @ordered
	 */
	protected static final double VALUE_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getValue() <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValue()
	 * @generated
	 * @ordered
	 */
	protected double value = VALUE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getRandomvariable() <em>Randomvariable</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRandomvariable()
	 * @generated
	 * @ordered
	 */
	protected StochasticModel randomvariable;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ObserveConditionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AnalysisPackage.Literals.OBSERVE_CONDITION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getValue() {
		return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setValue(double newValue) {
		double oldValue = value;
		value = newValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AnalysisPackage.OBSERVE_CONDITION__VALUE, oldValue, value));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StochasticModel getRandomvariable() {
		return randomvariable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRandomvariable(StochasticModel newRandomvariable, NotificationChain msgs) {
		StochasticModel oldRandomvariable = randomvariable;
		randomvariable = newRandomvariable;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, AnalysisPackage.OBSERVE_CONDITION__RANDOMVARIABLE, oldRandomvariable, newRandomvariable);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRandomvariable(StochasticModel newRandomvariable) {
		if (newRandomvariable != randomvariable) {
			NotificationChain msgs = null;
			if (randomvariable != null)
				msgs = ((InternalEObject)randomvariable).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - AnalysisPackage.OBSERVE_CONDITION__RANDOMVARIABLE, null, msgs);
			if (newRandomvariable != null)
				msgs = ((InternalEObject)newRandomvariable).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - AnalysisPackage.OBSERVE_CONDITION__RANDOMVARIABLE, null, msgs);
			msgs = basicSetRandomvariable(newRandomvariable, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AnalysisPackage.OBSERVE_CONDITION__RANDOMVARIABLE, newRandomvariable, newRandomvariable));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case AnalysisPackage.OBSERVE_CONDITION__RANDOMVARIABLE:
				return basicSetRandomvariable(null, msgs);
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
			case AnalysisPackage.OBSERVE_CONDITION__VALUE:
				return getValue();
			case AnalysisPackage.OBSERVE_CONDITION__RANDOMVARIABLE:
				return getRandomvariable();
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
			case AnalysisPackage.OBSERVE_CONDITION__VALUE:
				setValue((Double)newValue);
				return;
			case AnalysisPackage.OBSERVE_CONDITION__RANDOMVARIABLE:
				setRandomvariable((StochasticModel)newValue);
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
			case AnalysisPackage.OBSERVE_CONDITION__VALUE:
				setValue(VALUE_EDEFAULT);
				return;
			case AnalysisPackage.OBSERVE_CONDITION__RANDOMVARIABLE:
				setRandomvariable((StochasticModel)null);
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
			case AnalysisPackage.OBSERVE_CONDITION__VALUE:
				return value != VALUE_EDEFAULT;
			case AnalysisPackage.OBSERVE_CONDITION__RANDOMVARIABLE:
				return randomvariable != null;
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
		result.append(" (value: ");
		result.append(value);
		result.append(')');
		return result.toString();
	}

} //ObserveConditionImpl
