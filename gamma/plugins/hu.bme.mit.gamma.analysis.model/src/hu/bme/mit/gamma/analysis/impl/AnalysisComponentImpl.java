/**
 */
package hu.bme.mit.gamma.analysis.impl;

import hu.bme.mit.gamma.analysis.AnalysisAspect;
import hu.bme.mit.gamma.analysis.AnalysisComponent;
import hu.bme.mit.gamma.analysis.AnalysisCondition;
import hu.bme.mit.gamma.analysis.AnalysisPackage;

import hu.bme.mit.gamma.environment.model.EnvironmentCompositeComponentInstance;

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
	protected EnvironmentCompositeComponentInstance analyzedComponent;

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
	 * The cached value of the '{@link #getAspect() <em>Aspect</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAspect()
	 * @generated
	 * @ordered
	 */
	protected AnalysisAspect aspect;

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
	public EnvironmentCompositeComponentInstance getAnalyzedComponent() {
		return analyzedComponent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetAnalyzedComponent(EnvironmentCompositeComponentInstance newAnalyzedComponent, NotificationChain msgs) {
		EnvironmentCompositeComponentInstance oldAnalyzedComponent = analyzedComponent;
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
	public void setAnalyzedComponent(EnvironmentCompositeComponentInstance newAnalyzedComponent) {
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
	public AnalysisAspect getAspect() {
		return aspect;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetAspect(AnalysisAspect newAspect, NotificationChain msgs) {
		AnalysisAspect oldAspect = aspect;
		aspect = newAspect;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, AnalysisPackage.ANALYSIS_COMPONENT__ASPECT, oldAspect, newAspect);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAspect(AnalysisAspect newAspect) {
		if (newAspect != aspect) {
			NotificationChain msgs = null;
			if (aspect != null)
				msgs = ((InternalEObject)aspect).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - AnalysisPackage.ANALYSIS_COMPONENT__ASPECT, null, msgs);
			if (newAspect != null)
				msgs = ((InternalEObject)newAspect).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - AnalysisPackage.ANALYSIS_COMPONENT__ASPECT, null, msgs);
			msgs = basicSetAspect(newAspect, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AnalysisPackage.ANALYSIS_COMPONENT__ASPECT, newAspect, newAspect));
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
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case AnalysisPackage.ANALYSIS_COMPONENT__ANALYZED_COMPONENT:
				return basicSetAnalyzedComponent(null, msgs);
			case AnalysisPackage.ANALYSIS_COMPONENT__CONDITIONS:
				return ((InternalEList<?>)getConditions()).basicRemove(otherEnd, msgs);
			case AnalysisPackage.ANALYSIS_COMPONENT__ASPECT:
				return basicSetAspect(null, msgs);
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
				setAnalyzedComponent((EnvironmentCompositeComponentInstance)newValue);
				return;
			case AnalysisPackage.ANALYSIS_COMPONENT__CONDITIONS:
				getConditions().clear();
				getConditions().addAll((Collection<? extends AnalysisCondition>)newValue);
				return;
			case AnalysisPackage.ANALYSIS_COMPONENT__ASPECT:
				setAspect((AnalysisAspect)newValue);
				return;
			case AnalysisPackage.ANALYSIS_COMPONENT__SIMULATION_TIME:
				setSimulationTime((Double)newValue);
				return;
			case AnalysisPackage.ANALYSIS_COMPONENT__SIMULATION_NUMBER:
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
			case AnalysisPackage.ANALYSIS_COMPONENT__ANALYZED_COMPONENT:
				setAnalyzedComponent((EnvironmentCompositeComponentInstance)null);
				return;
			case AnalysisPackage.ANALYSIS_COMPONENT__CONDITIONS:
				getConditions().clear();
				return;
			case AnalysisPackage.ANALYSIS_COMPONENT__ASPECT:
				setAspect((AnalysisAspect)null);
				return;
			case AnalysisPackage.ANALYSIS_COMPONENT__SIMULATION_TIME:
				setSimulationTime(SIMULATION_TIME_EDEFAULT);
				return;
			case AnalysisPackage.ANALYSIS_COMPONENT__SIMULATION_NUMBER:
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
			case AnalysisPackage.ANALYSIS_COMPONENT__ANALYZED_COMPONENT:
				return analyzedComponent != null;
			case AnalysisPackage.ANALYSIS_COMPONENT__CONDITIONS:
				return conditions != null && !conditions.isEmpty();
			case AnalysisPackage.ANALYSIS_COMPONENT__ASPECT:
				return aspect != null;
			case AnalysisPackage.ANALYSIS_COMPONENT__SIMULATION_TIME:
				return simulationTime != SIMULATION_TIME_EDEFAULT;
			case AnalysisPackage.ANALYSIS_COMPONENT__SIMULATION_NUMBER:
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
		result.append(" (simulationTime: ");
		result.append(simulationTime);
		result.append(", simulationNumber: ");
		result.append(simulationNumber);
		result.append(')');
		return result.toString();
	}

} //AnalysisComponentImpl
