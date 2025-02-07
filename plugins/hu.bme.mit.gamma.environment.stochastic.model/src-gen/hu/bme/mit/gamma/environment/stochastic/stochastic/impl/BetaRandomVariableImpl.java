/**
 */
package hu.bme.mit.gamma.environment.stochastic.stochastic.impl;

import hu.bme.mit.gamma.environment.stochastic.stochastic.BetaRandomVariable;
import hu.bme.mit.gamma.environment.stochastic.stochastic.StochasticPackage;

import hu.bme.mit.gamma.expression.model.Expression;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Beta Random Variable</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link hu.bme.mit.gamma.environment.stochastic.stochastic.impl.BetaRandomVariableImpl#getApha <em>Apha</em>}</li>
 *   <li>{@link hu.bme.mit.gamma.environment.stochastic.stochastic.impl.BetaRandomVariableImpl#getBeta <em>Beta</em>}</li>
 * </ul>
 *
 * @generated
 */
public class BetaRandomVariableImpl extends ContinouosRandomVariableImpl implements BetaRandomVariable {
	/**
	 * The cached value of the '{@link #getApha() <em>Apha</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getApha()
	 * @generated
	 * @ordered
	 */
	protected Expression apha;

	/**
	 * The cached value of the '{@link #getBeta() <em>Beta</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBeta()
	 * @generated
	 * @ordered
	 */
	protected Expression beta;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BetaRandomVariableImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return StochasticPackage.Literals.BETA_RANDOM_VARIABLE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression getApha() {
		return apha;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetApha(Expression newApha, NotificationChain msgs) {
		Expression oldApha = apha;
		apha = newApha;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, StochasticPackage.BETA_RANDOM_VARIABLE__APHA, oldApha, newApha);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setApha(Expression newApha) {
		if (newApha != apha) {
			NotificationChain msgs = null;
			if (apha != null)
				msgs = ((InternalEObject)apha).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - StochasticPackage.BETA_RANDOM_VARIABLE__APHA, null, msgs);
			if (newApha != null)
				msgs = ((InternalEObject)newApha).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - StochasticPackage.BETA_RANDOM_VARIABLE__APHA, null, msgs);
			msgs = basicSetApha(newApha, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StochasticPackage.BETA_RANDOM_VARIABLE__APHA, newApha, newApha));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression getBeta() {
		return beta;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetBeta(Expression newBeta, NotificationChain msgs) {
		Expression oldBeta = beta;
		beta = newBeta;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, StochasticPackage.BETA_RANDOM_VARIABLE__BETA, oldBeta, newBeta);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBeta(Expression newBeta) {
		if (newBeta != beta) {
			NotificationChain msgs = null;
			if (beta != null)
				msgs = ((InternalEObject)beta).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - StochasticPackage.BETA_RANDOM_VARIABLE__BETA, null, msgs);
			if (newBeta != null)
				msgs = ((InternalEObject)newBeta).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - StochasticPackage.BETA_RANDOM_VARIABLE__BETA, null, msgs);
			msgs = basicSetBeta(newBeta, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StochasticPackage.BETA_RANDOM_VARIABLE__BETA, newBeta, newBeta));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case StochasticPackage.BETA_RANDOM_VARIABLE__APHA:
				return basicSetApha(null, msgs);
			case StochasticPackage.BETA_RANDOM_VARIABLE__BETA:
				return basicSetBeta(null, msgs);
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
			case StochasticPackage.BETA_RANDOM_VARIABLE__APHA:
				return getApha();
			case StochasticPackage.BETA_RANDOM_VARIABLE__BETA:
				return getBeta();
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
			case StochasticPackage.BETA_RANDOM_VARIABLE__APHA:
				setApha((Expression)newValue);
				return;
			case StochasticPackage.BETA_RANDOM_VARIABLE__BETA:
				setBeta((Expression)newValue);
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
			case StochasticPackage.BETA_RANDOM_VARIABLE__APHA:
				setApha((Expression)null);
				return;
			case StochasticPackage.BETA_RANDOM_VARIABLE__BETA:
				setBeta((Expression)null);
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
			case StochasticPackage.BETA_RANDOM_VARIABLE__APHA:
				return apha != null;
			case StochasticPackage.BETA_RANDOM_VARIABLE__BETA:
				return beta != null;
		}
		return super.eIsSet(featureID);
	}

} //BetaRandomVariableImpl
