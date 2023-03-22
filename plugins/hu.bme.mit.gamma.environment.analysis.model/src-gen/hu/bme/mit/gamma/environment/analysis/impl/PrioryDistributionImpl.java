/**
 */
package hu.bme.mit.gamma.environment.analysis.impl;

import hu.bme.mit.gamma.environment.analysis.AnalysisPackage;
import hu.bme.mit.gamma.environment.analysis.PrioryDistribution;

import hu.bme.mit.gamma.environment.stochastic.stochastic.RandomVariable;

import hu.bme.mit.gamma.expression.model.ParameterDeclaration;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Priory Distribution</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link hu.bme.mit.gamma.environment.analysis.impl.PrioryDistributionImpl#getRandomvariable <em>Randomvariable</em>}</li>
 *   <li>{@link hu.bme.mit.gamma.environment.analysis.impl.PrioryDistributionImpl#getParameter <em>Parameter</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PrioryDistributionImpl extends MinimalEObjectImpl.Container implements PrioryDistribution {
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
	 * The cached value of the '{@link #getParameter() <em>Parameter</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParameter()
	 * @generated
	 * @ordered
	 */
	protected ParameterDeclaration parameter;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PrioryDistributionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AnalysisPackage.Literals.PRIORY_DISTRIBUTION;
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, AnalysisPackage.PRIORY_DISTRIBUTION__RANDOMVARIABLE, oldRandomvariable, newRandomvariable);
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
				msgs = ((InternalEObject)randomvariable).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - AnalysisPackage.PRIORY_DISTRIBUTION__RANDOMVARIABLE, null, msgs);
			if (newRandomvariable != null)
				msgs = ((InternalEObject)newRandomvariable).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - AnalysisPackage.PRIORY_DISTRIBUTION__RANDOMVARIABLE, null, msgs);
			msgs = basicSetRandomvariable(newRandomvariable, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AnalysisPackage.PRIORY_DISTRIBUTION__RANDOMVARIABLE, newRandomvariable, newRandomvariable));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ParameterDeclaration getParameter() {
		if (parameter != null && parameter.eIsProxy()) {
			InternalEObject oldParameter = (InternalEObject)parameter;
			parameter = (ParameterDeclaration)eResolveProxy(oldParameter);
			if (parameter != oldParameter) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, AnalysisPackage.PRIORY_DISTRIBUTION__PARAMETER, oldParameter, parameter));
			}
		}
		return parameter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ParameterDeclaration basicGetParameter() {
		return parameter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParameter(ParameterDeclaration newParameter) {
		ParameterDeclaration oldParameter = parameter;
		parameter = newParameter;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AnalysisPackage.PRIORY_DISTRIBUTION__PARAMETER, oldParameter, parameter));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case AnalysisPackage.PRIORY_DISTRIBUTION__RANDOMVARIABLE:
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
			case AnalysisPackage.PRIORY_DISTRIBUTION__RANDOMVARIABLE:
				return getRandomvariable();
			case AnalysisPackage.PRIORY_DISTRIBUTION__PARAMETER:
				if (resolve) return getParameter();
				return basicGetParameter();
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
			case AnalysisPackage.PRIORY_DISTRIBUTION__RANDOMVARIABLE:
				setRandomvariable((RandomVariable)newValue);
				return;
			case AnalysisPackage.PRIORY_DISTRIBUTION__PARAMETER:
				setParameter((ParameterDeclaration)newValue);
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
			case AnalysisPackage.PRIORY_DISTRIBUTION__RANDOMVARIABLE:
				setRandomvariable((RandomVariable)null);
				return;
			case AnalysisPackage.PRIORY_DISTRIBUTION__PARAMETER:
				setParameter((ParameterDeclaration)null);
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
			case AnalysisPackage.PRIORY_DISTRIBUTION__RANDOMVARIABLE:
				return randomvariable != null;
			case AnalysisPackage.PRIORY_DISTRIBUTION__PARAMETER:
				return parameter != null;
		}
		return super.eIsSet(featureID);
	}

} //PrioryDistributionImpl
