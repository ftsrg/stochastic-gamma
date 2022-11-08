/**
 */
package hu.bme.mit.gamma.analysis.impl;

import hu.bme.mit.gamma.analysis.AnalysisAspect;
import hu.bme.mit.gamma.analysis.AnalysisComponent;
import hu.bme.mit.gamma.analysis.AnalysisCondition;
import hu.bme.mit.gamma.analysis.AnalysisPackage;
import hu.bme.mit.gamma.analysis.EndCondition;
import hu.bme.mit.gamma.analysis.PrioryDistribution;
import hu.bme.mit.gamma.environment.model.EnvironmentAsynchronousCompositeComponentInstance;
import hu.bme.mit.gamma.statechart.interface_.impl.ComponentImpl;

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
 * An implementation of the model object '<em><b>Component</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link hu.bme.mit.gamma.analysis.impl.AnalysisComponentImpl#getAnalyzedComponent <em>Analyzed Component</em>}</li>
 *   <li>{@link hu.bme.mit.gamma.analysis.impl.AnalysisComponentImpl#getConditions <em>Conditions</em>}</li>
 *   <li>{@link hu.bme.mit.gamma.analysis.impl.AnalysisComponentImpl#getAspect <em>Aspect</em>}</li>
 *   <li>{@link hu.bme.mit.gamma.analysis.impl.AnalysisComponentImpl#getSimulationTime <em>Simulation Time</em>}</li>
 *   <li>{@link hu.bme.mit.gamma.analysis.impl.AnalysisComponentImpl#getSimulationNumber <em>Simulation Number</em>}</li>
 *   <li>{@link hu.bme.mit.gamma.analysis.impl.AnalysisComponentImpl#getPriorydistribution <em>Priorydistribution</em>}</li>
 *   <li>{@link hu.bme.mit.gamma.analysis.impl.AnalysisComponentImpl#getEndcondition <em>Endcondition</em>}</li>
 *   <li>{@link hu.bme.mit.gamma.analysis.impl.AnalysisComponentImpl#getWarmupTime <em>Warmup Time</em>}</li>
 * </ul>
 *
 * @generated
 */
public class AnalysisComponentImpl extends ComponentImpl implements AnalysisComponent {
	/**
	 * The cached value of the '{@link #getAnalyzedComponent() <em>Analyzed Component</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAnalyzedComponent()
	 * @generated
	 * @ordered
	 */
	protected EnvironmentAsynchronousCompositeComponentInstance analyzedComponent;

	/**
	 * The cached value of the '{@link #getConditions() <em>Conditions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConditions()
	 * @generated
	 * @ordered
	 */
	protected EList<AnalysisCondition> conditions;

	/**
	 * The cached value of the '{@link #getAspect() <em>Aspect</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAspect()
	 * @generated
	 * @ordered
	 */
	protected EList<AnalysisAspect> aspect;

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
	 * The cached value of the '{@link #getPriorydistribution() <em>Priorydistribution</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPriorydistribution()
	 * @generated
	 * @ordered
	 */
	protected EList<PrioryDistribution> priorydistribution;

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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AnalysisComponentImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AnalysisPackage.Literals.ANALYSIS_COMPONENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EnvironmentAsynchronousCompositeComponentInstance getAnalyzedComponent() {
		return analyzedComponent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetAnalyzedComponent(EnvironmentAsynchronousCompositeComponentInstance newAnalyzedComponent, NotificationChain msgs) {
		EnvironmentAsynchronousCompositeComponentInstance oldAnalyzedComponent = analyzedComponent;
		analyzedComponent = newAnalyzedComponent;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, AnalysisPackage.ANALYSIS_COMPONENT__ANALYZED_COMPONENT, oldAnalyzedComponent, newAnalyzedComponent);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAnalyzedComponent(EnvironmentAsynchronousCompositeComponentInstance newAnalyzedComponent) {
		if (newAnalyzedComponent != analyzedComponent) {
			NotificationChain msgs = null;
			if (analyzedComponent != null)
				msgs = ((InternalEObject)analyzedComponent).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - AnalysisPackage.ANALYSIS_COMPONENT__ANALYZED_COMPONENT, null, msgs);
			if (newAnalyzedComponent != null)
				msgs = ((InternalEObject)newAnalyzedComponent).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - AnalysisPackage.ANALYSIS_COMPONENT__ANALYZED_COMPONENT, null, msgs);
			msgs = basicSetAnalyzedComponent(newAnalyzedComponent, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AnalysisPackage.ANALYSIS_COMPONENT__ANALYZED_COMPONENT, newAnalyzedComponent, newAnalyzedComponent));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<AnalysisCondition> getConditions() {
		if (conditions == null) {
			conditions = new EObjectContainmentEList<AnalysisCondition>(AnalysisCondition.class, this, AnalysisPackage.ANALYSIS_COMPONENT__CONDITIONS);
		}
		return conditions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<AnalysisAspect> getAspect() {
		if (aspect == null) {
			aspect = new EObjectContainmentEList<AnalysisAspect>(AnalysisAspect.class, this, AnalysisPackage.ANALYSIS_COMPONENT__ASPECT);
		}
		return aspect;
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
			eNotify(new ENotificationImpl(this, Notification.SET, AnalysisPackage.ANALYSIS_COMPONENT__SIMULATION_TIME, oldSimulationTime, simulationTime));
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
			eNotify(new ENotificationImpl(this, Notification.SET, AnalysisPackage.ANALYSIS_COMPONENT__SIMULATION_NUMBER, oldSimulationNumber, simulationNumber));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<PrioryDistribution> getPriorydistribution() {
		if (priorydistribution == null) {
			priorydistribution = new EObjectContainmentEList<PrioryDistribution>(PrioryDistribution.class, this, AnalysisPackage.ANALYSIS_COMPONENT__PRIORYDISTRIBUTION);
		}
		return priorydistribution;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<EndCondition> getEndcondition() {
		if (endcondition == null) {
			endcondition = new EObjectContainmentEList<EndCondition>(EndCondition.class, this, AnalysisPackage.ANALYSIS_COMPONENT__ENDCONDITION);
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
			eNotify(new ENotificationImpl(this, Notification.SET, AnalysisPackage.ANALYSIS_COMPONENT__WARMUP_TIME, oldWarmupTime, warmupTime));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case AnalysisPackage.ANALYSIS_COMPONENT__ANALYZED_COMPONENT:
				return basicSetAnalyzedComponent(null, msgs);
			case AnalysisPackage.ANALYSIS_COMPONENT__CONDITIONS:
				return ((InternalEList<?>)getConditions()).basicRemove(otherEnd, msgs);
			case AnalysisPackage.ANALYSIS_COMPONENT__ASPECT:
				return ((InternalEList<?>)getAspect()).basicRemove(otherEnd, msgs);
			case AnalysisPackage.ANALYSIS_COMPONENT__PRIORYDISTRIBUTION:
				return ((InternalEList<?>)getPriorydistribution()).basicRemove(otherEnd, msgs);
			case AnalysisPackage.ANALYSIS_COMPONENT__ENDCONDITION:
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
			case AnalysisPackage.ANALYSIS_COMPONENT__ANALYZED_COMPONENT:
				return getAnalyzedComponent();
			case AnalysisPackage.ANALYSIS_COMPONENT__CONDITIONS:
				return getConditions();
			case AnalysisPackage.ANALYSIS_COMPONENT__ASPECT:
				return getAspect();
			case AnalysisPackage.ANALYSIS_COMPONENT__SIMULATION_TIME:
				return getSimulationTime();
			case AnalysisPackage.ANALYSIS_COMPONENT__SIMULATION_NUMBER:
				return getSimulationNumber();
			case AnalysisPackage.ANALYSIS_COMPONENT__PRIORYDISTRIBUTION:
				return getPriorydistribution();
			case AnalysisPackage.ANALYSIS_COMPONENT__ENDCONDITION:
				return getEndcondition();
			case AnalysisPackage.ANALYSIS_COMPONENT__WARMUP_TIME:
				return getWarmupTime();
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
			case AnalysisPackage.ANALYSIS_COMPONENT__ANALYZED_COMPONENT:
				setAnalyzedComponent((EnvironmentAsynchronousCompositeComponentInstance)newValue);
				return;
			case AnalysisPackage.ANALYSIS_COMPONENT__CONDITIONS:
				getConditions().clear();
				getConditions().addAll((Collection<? extends AnalysisCondition>)newValue);
				return;
			case AnalysisPackage.ANALYSIS_COMPONENT__ASPECT:
				getAspect().clear();
				getAspect().addAll((Collection<? extends AnalysisAspect>)newValue);
				return;
			case AnalysisPackage.ANALYSIS_COMPONENT__SIMULATION_TIME:
				setSimulationTime((Double)newValue);
				return;
			case AnalysisPackage.ANALYSIS_COMPONENT__SIMULATION_NUMBER:
				setSimulationNumber((BigInteger)newValue);
				return;
			case AnalysisPackage.ANALYSIS_COMPONENT__PRIORYDISTRIBUTION:
				getPriorydistribution().clear();
				getPriorydistribution().addAll((Collection<? extends PrioryDistribution>)newValue);
				return;
			case AnalysisPackage.ANALYSIS_COMPONENT__ENDCONDITION:
				getEndcondition().clear();
				getEndcondition().addAll((Collection<? extends EndCondition>)newValue);
				return;
			case AnalysisPackage.ANALYSIS_COMPONENT__WARMUP_TIME:
				setWarmupTime((Double)newValue);
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
			case AnalysisPackage.ANALYSIS_COMPONENT__ANALYZED_COMPONENT:
				setAnalyzedComponent((EnvironmentAsynchronousCompositeComponentInstance)null);
				return;
			case AnalysisPackage.ANALYSIS_COMPONENT__CONDITIONS:
				getConditions().clear();
				return;
			case AnalysisPackage.ANALYSIS_COMPONENT__ASPECT:
				getAspect().clear();
				return;
			case AnalysisPackage.ANALYSIS_COMPONENT__SIMULATION_TIME:
				setSimulationTime(SIMULATION_TIME_EDEFAULT);
				return;
			case AnalysisPackage.ANALYSIS_COMPONENT__SIMULATION_NUMBER:
				setSimulationNumber(SIMULATION_NUMBER_EDEFAULT);
				return;
			case AnalysisPackage.ANALYSIS_COMPONENT__PRIORYDISTRIBUTION:
				getPriorydistribution().clear();
				return;
			case AnalysisPackage.ANALYSIS_COMPONENT__ENDCONDITION:
				getEndcondition().clear();
				return;
			case AnalysisPackage.ANALYSIS_COMPONENT__WARMUP_TIME:
				setWarmupTime(WARMUP_TIME_EDEFAULT);
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
			case AnalysisPackage.ANALYSIS_COMPONENT__ANALYZED_COMPONENT:
				return analyzedComponent != null;
			case AnalysisPackage.ANALYSIS_COMPONENT__CONDITIONS:
				return conditions != null && !conditions.isEmpty();
			case AnalysisPackage.ANALYSIS_COMPONENT__ASPECT:
				return aspect != null && !aspect.isEmpty();
			case AnalysisPackage.ANALYSIS_COMPONENT__SIMULATION_TIME:
				return simulationTime != SIMULATION_TIME_EDEFAULT;
			case AnalysisPackage.ANALYSIS_COMPONENT__SIMULATION_NUMBER:
				return SIMULATION_NUMBER_EDEFAULT == null ? simulationNumber != null : !SIMULATION_NUMBER_EDEFAULT.equals(simulationNumber);
			case AnalysisPackage.ANALYSIS_COMPONENT__PRIORYDISTRIBUTION:
				return priorydistribution != null && !priorydistribution.isEmpty();
			case AnalysisPackage.ANALYSIS_COMPONENT__ENDCONDITION:
				return endcondition != null && !endcondition.isEmpty();
			case AnalysisPackage.ANALYSIS_COMPONENT__WARMUP_TIME:
				return warmupTime != WARMUP_TIME_EDEFAULT;
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
		result.append(" (simulationTime: ");
		result.append(simulationTime);
		result.append(", simulationNumber: ");
		result.append(simulationNumber);
		result.append(", warmupTime: ");
		result.append(warmupTime);
		result.append(')');
		return result.toString();
	}

} //AnalysisComponentImpl
