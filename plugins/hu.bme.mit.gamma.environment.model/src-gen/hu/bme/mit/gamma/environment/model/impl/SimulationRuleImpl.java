/**
 */
package hu.bme.mit.gamma.environment.model.impl;

import hu.bme.mit.gamma.environment.model.EnvironmentModelPackage;
import hu.bme.mit.gamma.environment.model.Simulation;
import hu.bme.mit.gamma.environment.model.SimulationRule;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Simulation Rule</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link hu.bme.mit.gamma.environment.model.impl.SimulationRuleImpl#getSimulation <em>Simulation</em>}</li>
 * </ul>
 *
 * @generated
 */
public class SimulationRuleImpl extends EnvironmentRuleImpl implements SimulationRule {
	/**
	 * The cached value of the '{@link #getSimulation() <em>Simulation</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSimulation()
	 * @generated
	 * @ordered
	 */
	protected Simulation simulation;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SimulationRuleImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EnvironmentModelPackage.Literals.SIMULATION_RULE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Simulation getSimulation() {
		return simulation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSimulation(Simulation newSimulation, NotificationChain msgs) {
		Simulation oldSimulation = simulation;
		simulation = newSimulation;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, EnvironmentModelPackage.SIMULATION_RULE__SIMULATION, oldSimulation, newSimulation);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSimulation(Simulation newSimulation) {
		if (newSimulation != simulation) {
			NotificationChain msgs = null;
			if (simulation != null)
				msgs = ((InternalEObject)simulation).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - EnvironmentModelPackage.SIMULATION_RULE__SIMULATION, null, msgs);
			if (newSimulation != null)
				msgs = ((InternalEObject)newSimulation).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - EnvironmentModelPackage.SIMULATION_RULE__SIMULATION, null, msgs);
			msgs = basicSetSimulation(newSimulation, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EnvironmentModelPackage.SIMULATION_RULE__SIMULATION, newSimulation, newSimulation));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case EnvironmentModelPackage.SIMULATION_RULE__SIMULATION:
				return basicSetSimulation(null, msgs);
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
			case EnvironmentModelPackage.SIMULATION_RULE__SIMULATION:
				return getSimulation();
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
			case EnvironmentModelPackage.SIMULATION_RULE__SIMULATION:
				setSimulation((Simulation)newValue);
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
			case EnvironmentModelPackage.SIMULATION_RULE__SIMULATION:
				setSimulation((Simulation)null);
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
			case EnvironmentModelPackage.SIMULATION_RULE__SIMULATION:
				return simulation != null;
		}
		return super.eIsSet(featureID);
	}

} //SimulationRuleImpl
