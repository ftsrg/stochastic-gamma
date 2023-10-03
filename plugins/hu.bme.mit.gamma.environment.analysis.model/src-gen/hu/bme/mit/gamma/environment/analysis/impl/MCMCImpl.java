/**
 */
package hu.bme.mit.gamma.environment.analysis.impl;

import hu.bme.mit.gamma.environment.analysis.AnalysisPackage;
import hu.bme.mit.gamma.environment.analysis.MCMC;
import hu.bme.mit.gamma.environment.analysis.MCMCKernel;

import hu.bme.mit.gamma.expression.model.IntegerLiteralExpression;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>MCMC</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link hu.bme.mit.gamma.environment.analysis.impl.MCMCImpl#getKernel <em>Kernel</em>}</li>
 *   <li>{@link hu.bme.mit.gamma.environment.analysis.impl.MCMCImpl#getWarmupStepNum <em>Warmup Step Num</em>}</li>
 * </ul>
 *
 * @generated
 */
public class MCMCImpl extends SimulationAnalysisMethodImpl implements MCMC {
	/**
	 * The cached value of the '{@link #getKernel() <em>Kernel</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKernel()
	 * @generated
	 * @ordered
	 */
	protected MCMCKernel kernel;

	/**
	 * The cached value of the '{@link #getWarmupStepNum() <em>Warmup Step Num</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWarmupStepNum()
	 * @generated
	 * @ordered
	 */
	protected IntegerLiteralExpression warmupStepNum;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MCMCImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AnalysisPackage.Literals.MCMC;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MCMCKernel getKernel() {
		return kernel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetKernel(MCMCKernel newKernel, NotificationChain msgs) {
		MCMCKernel oldKernel = kernel;
		kernel = newKernel;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, AnalysisPackage.MCMC__KERNEL, oldKernel, newKernel);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setKernel(MCMCKernel newKernel) {
		if (newKernel != kernel) {
			NotificationChain msgs = null;
			if (kernel != null)
				msgs = ((InternalEObject)kernel).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - AnalysisPackage.MCMC__KERNEL, null, msgs);
			if (newKernel != null)
				msgs = ((InternalEObject)newKernel).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - AnalysisPackage.MCMC__KERNEL, null, msgs);
			msgs = basicSetKernel(newKernel, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AnalysisPackage.MCMC__KERNEL, newKernel, newKernel));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IntegerLiteralExpression getWarmupStepNum() {
		return warmupStepNum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetWarmupStepNum(IntegerLiteralExpression newWarmupStepNum, NotificationChain msgs) {
		IntegerLiteralExpression oldWarmupStepNum = warmupStepNum;
		warmupStepNum = newWarmupStepNum;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, AnalysisPackage.MCMC__WARMUP_STEP_NUM, oldWarmupStepNum, newWarmupStepNum);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWarmupStepNum(IntegerLiteralExpression newWarmupStepNum) {
		if (newWarmupStepNum != warmupStepNum) {
			NotificationChain msgs = null;
			if (warmupStepNum != null)
				msgs = ((InternalEObject)warmupStepNum).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - AnalysisPackage.MCMC__WARMUP_STEP_NUM, null, msgs);
			if (newWarmupStepNum != null)
				msgs = ((InternalEObject)newWarmupStepNum).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - AnalysisPackage.MCMC__WARMUP_STEP_NUM, null, msgs);
			msgs = basicSetWarmupStepNum(newWarmupStepNum, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AnalysisPackage.MCMC__WARMUP_STEP_NUM, newWarmupStepNum, newWarmupStepNum));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case AnalysisPackage.MCMC__KERNEL:
				return basicSetKernel(null, msgs);
			case AnalysisPackage.MCMC__WARMUP_STEP_NUM:
				return basicSetWarmupStepNum(null, msgs);
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
			case AnalysisPackage.MCMC__KERNEL:
				return getKernel();
			case AnalysisPackage.MCMC__WARMUP_STEP_NUM:
				return getWarmupStepNum();
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
			case AnalysisPackage.MCMC__KERNEL:
				setKernel((MCMCKernel)newValue);
				return;
			case AnalysisPackage.MCMC__WARMUP_STEP_NUM:
				setWarmupStepNum((IntegerLiteralExpression)newValue);
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
			case AnalysisPackage.MCMC__KERNEL:
				setKernel((MCMCKernel)null);
				return;
			case AnalysisPackage.MCMC__WARMUP_STEP_NUM:
				setWarmupStepNum((IntegerLiteralExpression)null);
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
			case AnalysisPackage.MCMC__KERNEL:
				return kernel != null;
			case AnalysisPackage.MCMC__WARMUP_STEP_NUM:
				return warmupStepNum != null;
		}
		return super.eIsSet(featureID);
	}

} //MCMCImpl
