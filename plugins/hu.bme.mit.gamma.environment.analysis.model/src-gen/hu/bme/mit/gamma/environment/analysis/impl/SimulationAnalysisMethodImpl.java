/**
 */
package hu.bme.mit.gamma.environment.analysis.impl;

import hu.bme.mit.gamma.environment.analysis.AnalysisPackage;
import hu.bme.mit.gamma.environment.analysis.EndCondition;
import hu.bme.mit.gamma.environment.analysis.SimulationAnalysisMethod;

import hu.bme.mit.gamma.expression.model.Expression;
import hu.bme.mit.gamma.expression.model.IntegerLiteralExpression;
import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Simulation Analysis Method</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link hu.bme.mit.gamma.environment.analysis.impl.SimulationAnalysisMethodImpl#getEndcondition <em>Endcondition</em>}</li>
 *   <li>{@link hu.bme.mit.gamma.environment.analysis.impl.SimulationAnalysisMethodImpl#getSimulationNumber <em>Simulation Number</em>}</li>
 *   <li>{@link hu.bme.mit.gamma.environment.analysis.impl.SimulationAnalysisMethodImpl#getWarmupTime <em>Warmup Time</em>}</li>
 *   <li>{@link hu.bme.mit.gamma.environment.analysis.impl.SimulationAnalysisMethodImpl#getSimulationTime <em>Simulation Time</em>}</li>
 *   <li>{@link hu.bme.mit.gamma.environment.analysis.impl.SimulationAnalysisMethodImpl#getSamplingBatchSize <em>Sampling Batch Size</em>}</li>
 *   <li>{@link hu.bme.mit.gamma.environment.analysis.impl.SimulationAnalysisMethodImpl#getJointSampling <em>Joint Sampling</em>}</li>
 *   <li>{@link hu.bme.mit.gamma.environment.analysis.impl.SimulationAnalysisMethodImpl#isDebug <em>Debug</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class SimulationAnalysisMethodImpl extends AnalysisMethodImpl implements SimulationAnalysisMethod {
	/**
	 * The cached value of the '{@link #getEndcondition() <em>Endcondition</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEndcondition()
	 * @generated
	 * @ordered
	 */
	protected EList<EndCondition> endcondition;

	/**
	 * The cached value of the '{@link #getSimulationNumber() <em>Simulation Number</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSimulationNumber()
	 * @generated
	 * @ordered
	 */
	protected IntegerLiteralExpression simulationNumber;

	/**
	 * The cached value of the '{@link #getWarmupTime() <em>Warmup Time</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWarmupTime()
	 * @generated
	 * @ordered
	 */
	protected Expression warmupTime;

	/**
	 * The cached value of the '{@link #getSimulationTime() <em>Simulation Time</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSimulationTime()
	 * @generated
	 * @ordered
	 */
	protected Expression simulationTime;

	/**
	 * The cached value of the '{@link #getSamplingBatchSize() <em>Sampling Batch Size</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSamplingBatchSize()
	 * @generated
	 * @ordered
	 */
	protected IntegerLiteralExpression samplingBatchSize;

	/**
	 * The default value of the '{@link #getJointSampling() <em>Joint Sampling</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getJointSampling()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean JOINT_SAMPLING_EDEFAULT = Boolean.FALSE;

	/**
	 * The cached value of the '{@link #getJointSampling() <em>Joint Sampling</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getJointSampling()
	 * @generated
	 * @ordered
	 */
	protected Boolean jointSampling = JOINT_SAMPLING_EDEFAULT;

	/**
	 * The default value of the '{@link #isDebug() <em>Debug</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDebug()
	 * @generated
	 * @ordered
	 */
	protected static final boolean DEBUG_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isDebug() <em>Debug</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDebug()
	 * @generated
	 * @ordered
	 */
	protected boolean debug = DEBUG_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SimulationAnalysisMethodImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AnalysisPackage.Literals.SIMULATION_ANALYSIS_METHOD;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<EndCondition> getEndcondition() {
		if (endcondition == null) {
			endcondition = new EObjectContainmentEList<EndCondition>(EndCondition.class, this, AnalysisPackage.SIMULATION_ANALYSIS_METHOD__ENDCONDITION);
		}
		return endcondition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression getWarmupTime() {
		return warmupTime;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetWarmupTime(Expression newWarmupTime, NotificationChain msgs) {
		Expression oldWarmupTime = warmupTime;
		warmupTime = newWarmupTime;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, AnalysisPackage.SIMULATION_ANALYSIS_METHOD__WARMUP_TIME, oldWarmupTime, newWarmupTime);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWarmupTime(Expression newWarmupTime) {
		if (newWarmupTime != warmupTime) {
			NotificationChain msgs = null;
			if (warmupTime != null)
				msgs = ((InternalEObject)warmupTime).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - AnalysisPackage.SIMULATION_ANALYSIS_METHOD__WARMUP_TIME, null, msgs);
			if (newWarmupTime != null)
				msgs = ((InternalEObject)newWarmupTime).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - AnalysisPackage.SIMULATION_ANALYSIS_METHOD__WARMUP_TIME, null, msgs);
			msgs = basicSetWarmupTime(newWarmupTime, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AnalysisPackage.SIMULATION_ANALYSIS_METHOD__WARMUP_TIME, newWarmupTime, newWarmupTime));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression getSimulationTime() {
		return simulationTime;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSimulationTime(Expression newSimulationTime, NotificationChain msgs) {
		Expression oldSimulationTime = simulationTime;
		simulationTime = newSimulationTime;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, AnalysisPackage.SIMULATION_ANALYSIS_METHOD__SIMULATION_TIME, oldSimulationTime, newSimulationTime);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSimulationTime(Expression newSimulationTime) {
		if (newSimulationTime != simulationTime) {
			NotificationChain msgs = null;
			if (simulationTime != null)
				msgs = ((InternalEObject)simulationTime).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - AnalysisPackage.SIMULATION_ANALYSIS_METHOD__SIMULATION_TIME, null, msgs);
			if (newSimulationTime != null)
				msgs = ((InternalEObject)newSimulationTime).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - AnalysisPackage.SIMULATION_ANALYSIS_METHOD__SIMULATION_TIME, null, msgs);
			msgs = basicSetSimulationTime(newSimulationTime, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AnalysisPackage.SIMULATION_ANALYSIS_METHOD__SIMULATION_TIME, newSimulationTime, newSimulationTime));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IntegerLiteralExpression getSamplingBatchSize() {
		return samplingBatchSize;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSamplingBatchSize(IntegerLiteralExpression newSamplingBatchSize, NotificationChain msgs) {
		IntegerLiteralExpression oldSamplingBatchSize = samplingBatchSize;
		samplingBatchSize = newSamplingBatchSize;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, AnalysisPackage.SIMULATION_ANALYSIS_METHOD__SAMPLING_BATCH_SIZE, oldSamplingBatchSize, newSamplingBatchSize);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSamplingBatchSize(IntegerLiteralExpression newSamplingBatchSize) {
		if (newSamplingBatchSize != samplingBatchSize) {
			NotificationChain msgs = null;
			if (samplingBatchSize != null)
				msgs = ((InternalEObject)samplingBatchSize).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - AnalysisPackage.SIMULATION_ANALYSIS_METHOD__SAMPLING_BATCH_SIZE, null, msgs);
			if (newSamplingBatchSize != null)
				msgs = ((InternalEObject)newSamplingBatchSize).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - AnalysisPackage.SIMULATION_ANALYSIS_METHOD__SAMPLING_BATCH_SIZE, null, msgs);
			msgs = basicSetSamplingBatchSize(newSamplingBatchSize, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AnalysisPackage.SIMULATION_ANALYSIS_METHOD__SAMPLING_BATCH_SIZE, newSamplingBatchSize, newSamplingBatchSize));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getJointSampling() {
		return jointSampling;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setJointSampling(Boolean newJointSampling) {
		Boolean oldJointSampling = jointSampling;
		jointSampling = newJointSampling;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AnalysisPackage.SIMULATION_ANALYSIS_METHOD__JOINT_SAMPLING, oldJointSampling, jointSampling));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isDebug() {
		return debug;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDebug(boolean newDebug) {
		boolean oldDebug = debug;
		debug = newDebug;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AnalysisPackage.SIMULATION_ANALYSIS_METHOD__DEBUG, oldDebug, debug));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IntegerLiteralExpression getSimulationNumber() {
		return simulationNumber;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSimulationNumber(IntegerLiteralExpression newSimulationNumber, NotificationChain msgs) {
		IntegerLiteralExpression oldSimulationNumber = simulationNumber;
		simulationNumber = newSimulationNumber;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, AnalysisPackage.SIMULATION_ANALYSIS_METHOD__SIMULATION_NUMBER, oldSimulationNumber, newSimulationNumber);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSimulationNumber(IntegerLiteralExpression newSimulationNumber) {
		if (newSimulationNumber != simulationNumber) {
			NotificationChain msgs = null;
			if (simulationNumber != null)
				msgs = ((InternalEObject)simulationNumber).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - AnalysisPackage.SIMULATION_ANALYSIS_METHOD__SIMULATION_NUMBER, null, msgs);
			if (newSimulationNumber != null)
				msgs = ((InternalEObject)newSimulationNumber).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - AnalysisPackage.SIMULATION_ANALYSIS_METHOD__SIMULATION_NUMBER, null, msgs);
			msgs = basicSetSimulationNumber(newSimulationNumber, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AnalysisPackage.SIMULATION_ANALYSIS_METHOD__SIMULATION_NUMBER, newSimulationNumber, newSimulationNumber));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case AnalysisPackage.SIMULATION_ANALYSIS_METHOD__ENDCONDITION:
				return ((InternalEList<?>)getEndcondition()).basicRemove(otherEnd, msgs);
			case AnalysisPackage.SIMULATION_ANALYSIS_METHOD__SIMULATION_NUMBER:
				return basicSetSimulationNumber(null, msgs);
			case AnalysisPackage.SIMULATION_ANALYSIS_METHOD__WARMUP_TIME:
				return basicSetWarmupTime(null, msgs);
			case AnalysisPackage.SIMULATION_ANALYSIS_METHOD__SIMULATION_TIME:
				return basicSetSimulationTime(null, msgs);
			case AnalysisPackage.SIMULATION_ANALYSIS_METHOD__SAMPLING_BATCH_SIZE:
				return basicSetSamplingBatchSize(null, msgs);
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
			case AnalysisPackage.SIMULATION_ANALYSIS_METHOD__ENDCONDITION:
				return getEndcondition();
			case AnalysisPackage.SIMULATION_ANALYSIS_METHOD__SIMULATION_NUMBER:
				return getSimulationNumber();
			case AnalysisPackage.SIMULATION_ANALYSIS_METHOD__WARMUP_TIME:
				return getWarmupTime();
			case AnalysisPackage.SIMULATION_ANALYSIS_METHOD__SIMULATION_TIME:
				return getSimulationTime();
			case AnalysisPackage.SIMULATION_ANALYSIS_METHOD__SAMPLING_BATCH_SIZE:
				return getSamplingBatchSize();
			case AnalysisPackage.SIMULATION_ANALYSIS_METHOD__JOINT_SAMPLING:
				return getJointSampling();
			case AnalysisPackage.SIMULATION_ANALYSIS_METHOD__DEBUG:
				return isDebug();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case AnalysisPackage.SIMULATION_ANALYSIS_METHOD__ENDCONDITION:
				getEndcondition().clear();
				getEndcondition().addAll((Collection<? extends EndCondition>)newValue);
				return;
			case AnalysisPackage.SIMULATION_ANALYSIS_METHOD__SIMULATION_NUMBER:
				setSimulationNumber((IntegerLiteralExpression)newValue);
				return;
			case AnalysisPackage.SIMULATION_ANALYSIS_METHOD__WARMUP_TIME:
				setWarmupTime((Expression)newValue);
				return;
			case AnalysisPackage.SIMULATION_ANALYSIS_METHOD__SIMULATION_TIME:
				setSimulationTime((Expression)newValue);
				return;
			case AnalysisPackage.SIMULATION_ANALYSIS_METHOD__SAMPLING_BATCH_SIZE:
				setSamplingBatchSize((IntegerLiteralExpression)newValue);
				return;
			case AnalysisPackage.SIMULATION_ANALYSIS_METHOD__JOINT_SAMPLING:
				setJointSampling((Boolean)newValue);
				return;
			case AnalysisPackage.SIMULATION_ANALYSIS_METHOD__DEBUG:
				setDebug((Boolean)newValue);
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
			case AnalysisPackage.SIMULATION_ANALYSIS_METHOD__ENDCONDITION:
				getEndcondition().clear();
				return;
			case AnalysisPackage.SIMULATION_ANALYSIS_METHOD__SIMULATION_NUMBER:
				setSimulationNumber((IntegerLiteralExpression)null);
				return;
			case AnalysisPackage.SIMULATION_ANALYSIS_METHOD__WARMUP_TIME:
				setWarmupTime((Expression)null);
				return;
			case AnalysisPackage.SIMULATION_ANALYSIS_METHOD__SIMULATION_TIME:
				setSimulationTime((Expression)null);
				return;
			case AnalysisPackage.SIMULATION_ANALYSIS_METHOD__SAMPLING_BATCH_SIZE:
				setSamplingBatchSize((IntegerLiteralExpression)null);
				return;
			case AnalysisPackage.SIMULATION_ANALYSIS_METHOD__JOINT_SAMPLING:
				setJointSampling(JOINT_SAMPLING_EDEFAULT);
				return;
			case AnalysisPackage.SIMULATION_ANALYSIS_METHOD__DEBUG:
				setDebug(DEBUG_EDEFAULT);
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
			case AnalysisPackage.SIMULATION_ANALYSIS_METHOD__ENDCONDITION:
				return endcondition != null && !endcondition.isEmpty();
			case AnalysisPackage.SIMULATION_ANALYSIS_METHOD__SIMULATION_NUMBER:
				return simulationNumber != null;
			case AnalysisPackage.SIMULATION_ANALYSIS_METHOD__WARMUP_TIME:
				return warmupTime != null;
			case AnalysisPackage.SIMULATION_ANALYSIS_METHOD__SIMULATION_TIME:
				return simulationTime != null;
			case AnalysisPackage.SIMULATION_ANALYSIS_METHOD__SAMPLING_BATCH_SIZE:
				return samplingBatchSize != null;
			case AnalysisPackage.SIMULATION_ANALYSIS_METHOD__JOINT_SAMPLING:
				return JOINT_SAMPLING_EDEFAULT == null ? jointSampling != null : !JOINT_SAMPLING_EDEFAULT.equals(jointSampling);
			case AnalysisPackage.SIMULATION_ANALYSIS_METHOD__DEBUG:
				return debug != DEBUG_EDEFAULT;
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
		result.append(" (jointSampling: ");
		result.append(jointSampling);
		result.append(", debug: ");
		result.append(debug);
		result.append(')');
		return result.toString();
	}

} //SimulationAnalysisMethodImpl
