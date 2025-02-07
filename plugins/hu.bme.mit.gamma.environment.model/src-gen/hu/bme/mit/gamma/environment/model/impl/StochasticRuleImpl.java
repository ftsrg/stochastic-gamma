/**
 */
package hu.bme.mit.gamma.environment.model.impl;

import hu.bme.mit.gamma.environment.model.EnvironmentModelPackage;
import hu.bme.mit.gamma.environment.model.StochasticRule;

import hu.bme.mit.gamma.environment.stochastic.stochastic.StochasticModel;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Stochastic Rule</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link hu.bme.mit.gamma.environment.model.impl.StochasticRuleImpl#getStochasticModel <em>Stochastic Model</em>}</li>
 * </ul>
 *
 * @generated
 */
public class StochasticRuleImpl extends EnvironmentRuleImpl implements StochasticRule {
	/**
	 * The cached value of the '{@link #getStochasticModel() <em>Stochastic Model</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStochasticModel()
	 * @generated
	 * @ordered
	 */
	protected StochasticModel stochasticModel;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StochasticRuleImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EnvironmentModelPackage.Literals.STOCHASTIC_RULE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StochasticModel getStochasticModel() {
		return stochasticModel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetStochasticModel(StochasticModel newStochasticModel, NotificationChain msgs) {
		StochasticModel oldStochasticModel = stochasticModel;
		stochasticModel = newStochasticModel;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, EnvironmentModelPackage.STOCHASTIC_RULE__STOCHASTIC_MODEL, oldStochasticModel, newStochasticModel);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStochasticModel(StochasticModel newStochasticModel) {
		if (newStochasticModel != stochasticModel) {
			NotificationChain msgs = null;
			if (stochasticModel != null)
				msgs = ((InternalEObject)stochasticModel).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - EnvironmentModelPackage.STOCHASTIC_RULE__STOCHASTIC_MODEL, null, msgs);
			if (newStochasticModel != null)
				msgs = ((InternalEObject)newStochasticModel).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - EnvironmentModelPackage.STOCHASTIC_RULE__STOCHASTIC_MODEL, null, msgs);
			msgs = basicSetStochasticModel(newStochasticModel, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EnvironmentModelPackage.STOCHASTIC_RULE__STOCHASTIC_MODEL, newStochasticModel, newStochasticModel));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case EnvironmentModelPackage.STOCHASTIC_RULE__STOCHASTIC_MODEL:
				return basicSetStochasticModel(null, msgs);
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
			case EnvironmentModelPackage.STOCHASTIC_RULE__STOCHASTIC_MODEL:
				return getStochasticModel();
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
			case EnvironmentModelPackage.STOCHASTIC_RULE__STOCHASTIC_MODEL:
				setStochasticModel((StochasticModel)newValue);
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
			case EnvironmentModelPackage.STOCHASTIC_RULE__STOCHASTIC_MODEL:
				setStochasticModel((StochasticModel)null);
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
			case EnvironmentModelPackage.STOCHASTIC_RULE__STOCHASTIC_MODEL:
				return stochasticModel != null;
		}
		return super.eIsSet(featureID);
	}

} //StochasticRuleImpl
