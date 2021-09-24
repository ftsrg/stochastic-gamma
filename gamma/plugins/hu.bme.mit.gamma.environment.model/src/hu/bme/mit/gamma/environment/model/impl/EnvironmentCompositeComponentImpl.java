/**
 */
package hu.bme.mit.gamma.environment.model.impl;

import hu.bme.mit.gamma.environment.model.EnvironentPackage;
import hu.bme.mit.gamma.environment.model.EnvironmentComponentInstance;
import hu.bme.mit.gamma.environment.model.EnvironmentCompositeComponent;

import hu.bme.mit.gamma.statechart.composite.impl.CascadeCompositeComponentImpl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Environment Composite Component</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link hu.bme.mit.gamma.environment.model.impl.EnvironmentCompositeComponentImpl#getEnvironmentComponents <em>Environment Components</em>}</li>
 * </ul>
 *
 * @generated
 */
public class EnvironmentCompositeComponentImpl extends CascadeCompositeComponentImpl implements EnvironmentCompositeComponent {
	/**
	 * The cached value of the '{@link #getEnvironmentComponents() <em>Environment Components</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEnvironmentComponents()
	 * @generated
	 * @ordered
	 */
	protected EList<EnvironmentComponentInstance> environmentComponents;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EnvironmentCompositeComponentImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EnvironentPackage.Literals.ENVIRONMENT_COMPOSITE_COMPONENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<EnvironmentComponentInstance> getEnvironmentComponents() {
		if (environmentComponents == null) {
			environmentComponents = new EObjectContainmentEList<EnvironmentComponentInstance>(EnvironmentComponentInstance.class, this, EnvironentPackage.ENVIRONMENT_COMPOSITE_COMPONENT__ENVIRONMENT_COMPONENTS);
		}
		return environmentComponents;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case EnvironentPackage.ENVIRONMENT_COMPOSITE_COMPONENT__ENVIRONMENT_COMPONENTS:
				return ((InternalEList<?>)getEnvironmentComponents()).basicRemove(otherEnd, msgs);
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
			case EnvironentPackage.ENVIRONMENT_COMPOSITE_COMPONENT__ENVIRONMENT_COMPONENTS:
				return getEnvironmentComponents();
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
			case EnvironentPackage.ENVIRONMENT_COMPOSITE_COMPONENT__ENVIRONMENT_COMPONENTS:
				getEnvironmentComponents().clear();
				getEnvironmentComponents().addAll((Collection<? extends EnvironmentComponentInstance>)newValue);
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
			case EnvironentPackage.ENVIRONMENT_COMPOSITE_COMPONENT__ENVIRONMENT_COMPONENTS:
				getEnvironmentComponents().clear();
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
			case EnvironentPackage.ENVIRONMENT_COMPOSITE_COMPONENT__ENVIRONMENT_COMPONENTS:
				return environmentComponents != null && !environmentComponents.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //EnvironmentCompositeComponentImpl
