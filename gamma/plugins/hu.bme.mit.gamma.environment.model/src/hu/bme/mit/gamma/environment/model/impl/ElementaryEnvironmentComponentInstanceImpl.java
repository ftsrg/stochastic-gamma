/**
 */
package hu.bme.mit.gamma.environment.model.impl;

import hu.bme.mit.gamma.environment.model.ElementaryEnvironmentComponentInstance;
import hu.bme.mit.gamma.environment.model.EnvironentPackage;
import hu.bme.mit.gamma.environment.model.EnvironmentRule;

import hu.bme.mit.gamma.statechart.interface_.Port;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Elementary Environment Component Instance</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link hu.bme.mit.gamma.environment.model.impl.ElementaryEnvironmentComponentInstanceImpl#getOutports <em>Outports</em>}</li>
 *   <li>{@link hu.bme.mit.gamma.environment.model.impl.ElementaryEnvironmentComponentInstanceImpl#getInports <em>Inports</em>}</li>
 *   <li>{@link hu.bme.mit.gamma.environment.model.impl.ElementaryEnvironmentComponentInstanceImpl#getBehaviorRules <em>Behavior Rules</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class ElementaryEnvironmentComponentInstanceImpl extends EnvironmentComponentInstanceImpl implements ElementaryEnvironmentComponentInstance {
	/**
	 * The cached value of the '{@link #getOutports() <em>Outports</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOutports()
	 * @generated
	 * @ordered
	 */
	protected EList<Port> outports;

	/**
	 * The cached value of the '{@link #getInports() <em>Inports</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInports()
	 * @generated
	 * @ordered
	 */
	protected EList<Port> inports;

	/**
	 * The cached value of the '{@link #getBehaviorRules() <em>Behavior Rules</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBehaviorRules()
	 * @generated
	 * @ordered
	 */
	protected EList<EnvironmentRule> behaviorRules;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ElementaryEnvironmentComponentInstanceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EnvironentPackage.Literals.ELEMENTARY_ENVIRONMENT_COMPONENT_INSTANCE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Port> getOutports() {
		if (outports == null) {
			outports = new EObjectContainmentEList<Port>(Port.class, this, EnvironentPackage.ELEMENTARY_ENVIRONMENT_COMPONENT_INSTANCE__OUTPORTS);
		}
		return outports;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Port> getInports() {
		if (inports == null) {
			inports = new EObjectContainmentEList<Port>(Port.class, this, EnvironentPackage.ELEMENTARY_ENVIRONMENT_COMPONENT_INSTANCE__INPORTS);
		}
		return inports;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<EnvironmentRule> getBehaviorRules() {
		if (behaviorRules == null) {
			behaviorRules = new EObjectContainmentEList<EnvironmentRule>(EnvironmentRule.class, this, EnvironentPackage.ELEMENTARY_ENVIRONMENT_COMPONENT_INSTANCE__BEHAVIOR_RULES);
		}
		return behaviorRules;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case EnvironentPackage.ELEMENTARY_ENVIRONMENT_COMPONENT_INSTANCE__OUTPORTS:
				return ((InternalEList<?>)getOutports()).basicRemove(otherEnd, msgs);
			case EnvironentPackage.ELEMENTARY_ENVIRONMENT_COMPONENT_INSTANCE__INPORTS:
				return ((InternalEList<?>)getInports()).basicRemove(otherEnd, msgs);
			case EnvironentPackage.ELEMENTARY_ENVIRONMENT_COMPONENT_INSTANCE__BEHAVIOR_RULES:
				return ((InternalEList<?>)getBehaviorRules()).basicRemove(otherEnd, msgs);
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
			case EnvironentPackage.ELEMENTARY_ENVIRONMENT_COMPONENT_INSTANCE__OUTPORTS:
				return getOutports();
			case EnvironentPackage.ELEMENTARY_ENVIRONMENT_COMPONENT_INSTANCE__INPORTS:
				return getInports();
			case EnvironentPackage.ELEMENTARY_ENVIRONMENT_COMPONENT_INSTANCE__BEHAVIOR_RULES:
				return getBehaviorRules();
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
			case EnvironentPackage.ELEMENTARY_ENVIRONMENT_COMPONENT_INSTANCE__OUTPORTS:
				getOutports().clear();
				getOutports().addAll((Collection<? extends Port>)newValue);
				return;
			case EnvironentPackage.ELEMENTARY_ENVIRONMENT_COMPONENT_INSTANCE__INPORTS:
				getInports().clear();
				getInports().addAll((Collection<? extends Port>)newValue);
				return;
			case EnvironentPackage.ELEMENTARY_ENVIRONMENT_COMPONENT_INSTANCE__BEHAVIOR_RULES:
				getBehaviorRules().clear();
				getBehaviorRules().addAll((Collection<? extends EnvironmentRule>)newValue);
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
			case EnvironentPackage.ELEMENTARY_ENVIRONMENT_COMPONENT_INSTANCE__OUTPORTS:
				getOutports().clear();
				return;
			case EnvironentPackage.ELEMENTARY_ENVIRONMENT_COMPONENT_INSTANCE__INPORTS:
				getInports().clear();
				return;
			case EnvironentPackage.ELEMENTARY_ENVIRONMENT_COMPONENT_INSTANCE__BEHAVIOR_RULES:
				getBehaviorRules().clear();
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
			case EnvironentPackage.ELEMENTARY_ENVIRONMENT_COMPONENT_INSTANCE__OUTPORTS:
				return outports != null && !outports.isEmpty();
			case EnvironentPackage.ELEMENTARY_ENVIRONMENT_COMPONENT_INSTANCE__INPORTS:
				return inports != null && !inports.isEmpty();
			case EnvironentPackage.ELEMENTARY_ENVIRONMENT_COMPONENT_INSTANCE__BEHAVIOR_RULES:
				return behaviorRules != null && !behaviorRules.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ElementaryEnvironmentComponentInstanceImpl
