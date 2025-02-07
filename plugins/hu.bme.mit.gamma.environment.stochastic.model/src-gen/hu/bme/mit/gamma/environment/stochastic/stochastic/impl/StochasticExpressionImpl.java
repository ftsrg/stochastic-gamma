/**
 */
package hu.bme.mit.gamma.environment.stochastic.stochastic.impl;

import hu.bme.mit.gamma.environment.stochastic.stochastic.RandomVariable;
import hu.bme.mit.gamma.environment.stochastic.stochastic.StochasticExpression;
import hu.bme.mit.gamma.environment.stochastic.stochastic.StochasticPackage;

import hu.bme.mit.gamma.expression.model.impl.ExpressionImpl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Expression</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link hu.bme.mit.gamma.environment.stochastic.stochastic.impl.StochasticExpressionImpl#getRandomvariable <em>Randomvariable</em>}</li>
 * </ul>
 *
 * @generated
 */
public class StochasticExpressionImpl extends ExpressionImpl implements StochasticExpression {
	/**
	 * The cached value of the '{@link #getRandomvariable() <em>Randomvariable</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRandomvariable()
	 * @generated
	 * @ordered
	 */
	protected RandomVariable randomvariable;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StochasticExpressionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return StochasticPackage.Literals.STOCHASTIC_EXPRESSION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RandomVariable getRandomvariable() {
		return randomvariable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRandomvariable(RandomVariable newRandomvariable, NotificationChain msgs) {
		RandomVariable oldRandomvariable = randomvariable;
		randomvariable = newRandomvariable;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, StochasticPackage.STOCHASTIC_EXPRESSION__RANDOMVARIABLE, oldRandomvariable, newRandomvariable);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRandomvariable(RandomVariable newRandomvariable) {
		if (newRandomvariable != randomvariable) {
			NotificationChain msgs = null;
			if (randomvariable != null)
				msgs = ((InternalEObject)randomvariable).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - StochasticPackage.STOCHASTIC_EXPRESSION__RANDOMVARIABLE, null, msgs);
			if (newRandomvariable != null)
				msgs = ((InternalEObject)newRandomvariable).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - StochasticPackage.STOCHASTIC_EXPRESSION__RANDOMVARIABLE, null, msgs);
			msgs = basicSetRandomvariable(newRandomvariable, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StochasticPackage.STOCHASTIC_EXPRESSION__RANDOMVARIABLE, newRandomvariable, newRandomvariable));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case StochasticPackage.STOCHASTIC_EXPRESSION__RANDOMVARIABLE:
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
			case StochasticPackage.STOCHASTIC_EXPRESSION__RANDOMVARIABLE:
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
			case StochasticPackage.STOCHASTIC_EXPRESSION__RANDOMVARIABLE:
				setRandomvariable((RandomVariable)newValue);
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
			case StochasticPackage.STOCHASTIC_EXPRESSION__RANDOMVARIABLE:
				setRandomvariable((RandomVariable)null);
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
			case StochasticPackage.STOCHASTIC_EXPRESSION__RANDOMVARIABLE:
				return randomvariable != null;
		}
		return super.eIsSet(featureID);
	}

} //StochasticExpressionImpl
