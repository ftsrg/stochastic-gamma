/**
 */
package hu.bme.mit.gamma.stochastic.stochastic.impl;

import hu.bme.mit.gamma.expression.model.FunctionDeclaration;
import hu.bme.mit.gamma.stochastic.stochastic.CustomRandomVariable;
import hu.bme.mit.gamma.stochastic.stochastic.StochasticPackage;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Custom Random Variable</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link hu.bme.mit.gamma.stochastic.stochastic.impl.CustomRandomVariableImpl#getInverse_cdf <em>Inverse cdf</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CustomRandomVariableImpl extends ContinouosRandomVariableImpl implements CustomRandomVariable {
	/**
	 * The cached value of the '{@link #getInverse_cdf() <em>Inverse cdf</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInverse_cdf()
	 * @generated
	 * @ordered
	 */
	protected FunctionDeclaration inverse_cdf;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CustomRandomVariableImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return StochasticPackage.Literals.CUSTOM_RANDOM_VARIABLE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FunctionDeclaration getInverse_cdf() {
		if (inverse_cdf != null && inverse_cdf.eIsProxy()) {
			InternalEObject oldInverse_cdf = (InternalEObject)inverse_cdf;
			inverse_cdf = (FunctionDeclaration)eResolveProxy(oldInverse_cdf);
			if (inverse_cdf != oldInverse_cdf) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, StochasticPackage.CUSTOM_RANDOM_VARIABLE__INVERSE_CDF, oldInverse_cdf, inverse_cdf));
			}
		}
		return inverse_cdf;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FunctionDeclaration basicGetInverse_cdf() {
		return inverse_cdf;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInverse_cdf(FunctionDeclaration newInverse_cdf) {
		FunctionDeclaration oldInverse_cdf = inverse_cdf;
		inverse_cdf = newInverse_cdf;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StochasticPackage.CUSTOM_RANDOM_VARIABLE__INVERSE_CDF, oldInverse_cdf, inverse_cdf));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case StochasticPackage.CUSTOM_RANDOM_VARIABLE__INVERSE_CDF:
				if (resolve) return getInverse_cdf();
				return basicGetInverse_cdf();
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
			case StochasticPackage.CUSTOM_RANDOM_VARIABLE__INVERSE_CDF:
				setInverse_cdf((FunctionDeclaration)newValue);
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
			case StochasticPackage.CUSTOM_RANDOM_VARIABLE__INVERSE_CDF:
				setInverse_cdf((FunctionDeclaration)null);
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
			case StochasticPackage.CUSTOM_RANDOM_VARIABLE__INVERSE_CDF:
				return inverse_cdf != null;
		}
		return super.eIsSet(featureID);
	}

} //CustomRandomVariableImpl
