/**
 */
package hu.bme.mit.gamma.environment.analysis.impl;

import hu.bme.mit.gamma.environment.analysis.AnalysisPackage;
import hu.bme.mit.gamma.environment.analysis.EndCondition;
import hu.bme.mit.gamma.environment.analysis.SimulationAnalysisMethod;

import java.math.BigInteger;

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
 *   <li>{@link hu.bme.mit.gamma.environment.analysis.impl.SimulationAnalysisMethodImpl#getWarmupTime <em>Warmup Time</em>}</li>
 *   <li>{@link hu.bme.mit.gamma.environment.analysis.impl.SimulationAnalysisMethodImpl#getSimulationTime <em>Simulation Time</em>}</li>
 *   <li>{@link hu.bme.mit.gamma.environment.analysis.impl.SimulationAnalysisMethodImpl#getSimulationNumber <em>Simulation Number</em>}</li>
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
	 * The default value of the '{@link #getWarmupTime() <em>Warmup Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWarmupTime()
	 * @generated
	 * @ordered
	 */
	protected static final double WARMUP_TIME_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getWarmupTime() <em>Warmup Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWarmupTime()
	 * @generated
	 * @ordered
	 */
	protected double warmupTime = WARMUP_TIME_EDEFAULT;

	/**
	 * The default value of the '{@link #getSimulationTime() <em>Simulation Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSimulationTime()
	 * @generated
	 * @ordered
	 */
	protected static final double SIMULATION_TIME_EDEFAULT = 1.0;

	/**
	 * The cached value of the '{@link #getSimulationTime() <em>Simulation Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSimulationTime()
	 * @generated
	 * @ordered
	 */
	protected double simulationTime = SIMULATION_TIME_EDEFAULT;

	/**
	 * The default value of the '{@link #getSimulationNumber() <em>Simulation Number</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSimulationNumber()
	 * @generated
	 * @ordered
	 */
	protected static final BigInteger SIMULATION_NUMBER_EDEFAULT = new BigInteger("100");

	/**
	 * The cached value of the '{@link #getSimulationNumber() <em>Simulation Number</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSimulationNumber()
	 * @generated
	 * @ordered
	 */
	protected BigInteger simulationNumber = SIMULATION_NUMBER_EDEFAULT;

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
	public double getWarmupTime() {
		return warmupTime;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWarmupTime(double newWarmupTime) {
		double oldWarmupTime = warmupTime;
		warmupTime = newWarmupTime;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AnalysisPackage.SIMULATION_ANALYSIS_METHOD__WARMUP_TIME, oldWarmupTime, warmupTime));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getSimulationTime() {
		return simulationTime;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSimulationTime(double newSimulationTime) {
		double oldSimulationTime = simulationTime;
		simulationTime = newSimulationTime;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AnalysisPackage.SIMULATION_ANALYSIS_METHOD__SIMULATION_TIME, oldSimulationTime, simulationTime));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger getSimulationNumber() {
		return simulationNumber;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSimulationNumber(BigInteger newSimulationNumber) {
		BigInteger oldSimulationNumber = simulationNumber;
		simulationNumber = newSimulationNumber;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AnalysisPackage.SIMULATION_ANALYSIS_METHOD__SIMULATION_NUMBER, oldSimulationNumber, simulationNumber));
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
			case AnalysisPackage.SIMULATION_ANALYSIS_METHOD__WARMUP_TIME:
				return getWarmupTime();
			case AnalysisPackage.SIMULATION_ANALYSIS_METHOD__SIMULATION_TIME:
				return getSimulationTime();
			case AnalysisPackage.SIMULATION_ANALYSIS_METHOD__SIMULATION_NUMBER:
				return getSimulationNumber();
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
			case AnalysisPackage.SIMULATION_ANALYSIS_METHOD__WARMUP_TIME:
				setWarmupTime((Double)newValue);
				return;
			case AnalysisPackage.SIMULATION_ANALYSIS_METHOD__SIMULATION_TIME:
				setSimulationTime((Double)newValue);
				return;
			case AnalysisPackage.SIMULATION_ANALYSIS_METHOD__SIMULATION_NUMBER:
				setSimulationNumber((BigInteger)newValue);
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
			case AnalysisPackage.SIMULATION_ANALYSIS_METHOD__WARMUP_TIME:
				setWarmupTime(WARMUP_TIME_EDEFAULT);
				return;
			case AnalysisPackage.SIMULATION_ANALYSIS_METHOD__SIMULATION_TIME:
				setSimulationTime(SIMULATION_TIME_EDEFAULT);
				return;
			case AnalysisPackage.SIMULATION_ANALYSIS_METHOD__SIMULATION_NUMBER:
				setSimulationNumber(SIMULATION_NUMBER_EDEFAULT);
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
			case AnalysisPackage.SIMULATION_ANALYSIS_METHOD__WARMUP_TIME:
				return warmupTime != WARMUP_TIME_EDEFAULT;
			case AnalysisPackage.SIMULATION_ANALYSIS_METHOD__SIMULATION_TIME:
				return simulationTime != SIMULATION_TIME_EDEFAULT;
			case AnalysisPackage.SIMULATION_ANALYSIS_METHOD__SIMULATION_NUMBER:
				return SIMULATION_NUMBER_EDEFAULT == null ? simulationNumber != null : !SIMULATION_NUMBER_EDEFAULT.equals(simulationNumber);
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
		result.append(" (warmupTime: ");
		result.append(warmupTime);
		result.append(", simulationTime: ");
		result.append(simulationTime);
		result.append(", simulationNumber: ");
		result.append(simulationNumber);
		result.append(')');
		return result.toString();
	}

} //SimulationAnalysisMethodImpl
