/**
 */
package hu.bme.mit.gamma.environment.stochastic.stochastic.impl;

import hu.bme.mit.gamma.environment.stochastic.stochastic.DataSource;
import hu.bme.mit.gamma.environment.stochastic.stochastic.FittedGaussianProcess;
import hu.bme.mit.gamma.environment.stochastic.stochastic.FittedModel;
import hu.bme.mit.gamma.environment.stochastic.stochastic.StochasticPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Fitted Gaussian Process</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link hu.bme.mit.gamma.environment.stochastic.stochastic.impl.FittedGaussianProcessImpl#getSource <em>Source</em>}</li>
 *   <li>{@link hu.bme.mit.gamma.environment.stochastic.stochastic.impl.FittedGaussianProcessImpl#getLr <em>Lr</em>}</li>
 * </ul>
 *
 * @generated
 */
public class FittedGaussianProcessImpl extends GaussianProcessImpl implements FittedGaussianProcess {
	/**
	 * The cached value of the '{@link #getSource() <em>Source</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSource()
	 * @generated
	 * @ordered
	 */
	protected DataSource source;

	/**
	 * The default value of the '{@link #getLr() <em>Lr</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLr()
	 * @generated
	 * @ordered
	 */
	protected static final double LR_EDEFAULT = 0.05;

	/**
	 * The cached value of the '{@link #getLr() <em>Lr</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLr()
	 * @generated
	 * @ordered
	 */
	protected double lr = LR_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FittedGaussianProcessImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return StochasticPackage.Literals.FITTED_GAUSSIAN_PROCESS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataSource getSource() {
		return source;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSource(DataSource newSource, NotificationChain msgs) {
		DataSource oldSource = source;
		source = newSource;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, StochasticPackage.FITTED_GAUSSIAN_PROCESS__SOURCE, oldSource, newSource);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSource(DataSource newSource) {
		if (newSource != source) {
			NotificationChain msgs = null;
			if (source != null)
				msgs = ((InternalEObject)source).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - StochasticPackage.FITTED_GAUSSIAN_PROCESS__SOURCE, null, msgs);
			if (newSource != null)
				msgs = ((InternalEObject)newSource).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - StochasticPackage.FITTED_GAUSSIAN_PROCESS__SOURCE, null, msgs);
			msgs = basicSetSource(newSource, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StochasticPackage.FITTED_GAUSSIAN_PROCESS__SOURCE, newSource, newSource));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getLr() {
		return lr;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLr(double newLr) {
		double oldLr = lr;
		lr = newLr;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StochasticPackage.FITTED_GAUSSIAN_PROCESS__LR, oldLr, lr));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case StochasticPackage.FITTED_GAUSSIAN_PROCESS__SOURCE:
				return basicSetSource(null, msgs);
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
			case StochasticPackage.FITTED_GAUSSIAN_PROCESS__SOURCE:
				return getSource();
			case StochasticPackage.FITTED_GAUSSIAN_PROCESS__LR:
				return getLr();
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
			case StochasticPackage.FITTED_GAUSSIAN_PROCESS__SOURCE:
				setSource((DataSource)newValue);
				return;
			case StochasticPackage.FITTED_GAUSSIAN_PROCESS__LR:
				setLr((Double)newValue);
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
			case StochasticPackage.FITTED_GAUSSIAN_PROCESS__SOURCE:
				setSource((DataSource)null);
				return;
			case StochasticPackage.FITTED_GAUSSIAN_PROCESS__LR:
				setLr(LR_EDEFAULT);
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
			case StochasticPackage.FITTED_GAUSSIAN_PROCESS__SOURCE:
				return source != null;
			case StochasticPackage.FITTED_GAUSSIAN_PROCESS__LR:
				return lr != LR_EDEFAULT;
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
		if (baseClass == FittedModel.class) {
			switch (derivedFeatureID) {
				case StochasticPackage.FITTED_GAUSSIAN_PROCESS__SOURCE: return StochasticPackage.FITTED_MODEL__SOURCE;
				case StochasticPackage.FITTED_GAUSSIAN_PROCESS__LR: return StochasticPackage.FITTED_MODEL__LR;
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
		if (baseClass == FittedModel.class) {
			switch (baseFeatureID) {
				case StochasticPackage.FITTED_MODEL__SOURCE: return StochasticPackage.FITTED_GAUSSIAN_PROCESS__SOURCE;
				case StochasticPackage.FITTED_MODEL__LR: return StochasticPackage.FITTED_GAUSSIAN_PROCESS__LR;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
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
		result.append(" (lr: ");
		result.append(lr);
		result.append(')');
		return result.toString();
	}

} //FittedGaussianProcessImpl
