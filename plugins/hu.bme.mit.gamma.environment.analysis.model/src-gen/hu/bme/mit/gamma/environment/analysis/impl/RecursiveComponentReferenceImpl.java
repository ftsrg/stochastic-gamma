/**
 */
package hu.bme.mit.gamma.environment.analysis.impl;

import hu.bme.mit.gamma.environment.analysis.AnalysisPackage;
import hu.bme.mit.gamma.environment.analysis.RecursiveComponentReference;

import hu.bme.mit.gamma.environment.model.EnvironmentAsynchronousCompositeComponentInstance;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Recursive Component Reference</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link hu.bme.mit.gamma.environment.analysis.impl.RecursiveComponentReferenceImpl#getComponent <em>Component</em>}</li>
 *   <li>{@link hu.bme.mit.gamma.environment.analysis.impl.RecursiveComponentReferenceImpl#getRecursivecomponentreference <em>Recursivecomponentreference</em>}</li>
 * </ul>
 *
 * @generated
 */
public class RecursiveComponentReferenceImpl extends MinimalEObjectImpl.Container implements RecursiveComponentReference {
	/**
	 * The cached value of the '{@link #getComponent() <em>Component</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getComponent()
	 * @generated
	 * @ordered
	 */
	protected EnvironmentAsynchronousCompositeComponentInstance component;

	/**
	 * The cached value of the '{@link #getRecursivecomponentreference() <em>Recursivecomponentreference</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRecursivecomponentreference()
	 * @generated
	 * @ordered
	 */
	protected RecursiveComponentReference recursivecomponentreference;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RecursiveComponentReferenceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AnalysisPackage.Literals.RECURSIVE_COMPONENT_REFERENCE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EnvironmentAsynchronousCompositeComponentInstance getComponent() {
		if (component != null && component.eIsProxy()) {
			InternalEObject oldComponent = (InternalEObject)component;
			component = (EnvironmentAsynchronousCompositeComponentInstance)eResolveProxy(oldComponent);
			if (component != oldComponent) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, AnalysisPackage.RECURSIVE_COMPONENT_REFERENCE__COMPONENT, oldComponent, component));
			}
		}
		return component;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EnvironmentAsynchronousCompositeComponentInstance basicGetComponent() {
		return component;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setComponent(EnvironmentAsynchronousCompositeComponentInstance newComponent) {
		EnvironmentAsynchronousCompositeComponentInstance oldComponent = component;
		component = newComponent;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AnalysisPackage.RECURSIVE_COMPONENT_REFERENCE__COMPONENT, oldComponent, component));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RecursiveComponentReference getRecursivecomponentreference() {
		return recursivecomponentreference;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRecursivecomponentreference(RecursiveComponentReference newRecursivecomponentreference, NotificationChain msgs) {
		RecursiveComponentReference oldRecursivecomponentreference = recursivecomponentreference;
		recursivecomponentreference = newRecursivecomponentreference;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, AnalysisPackage.RECURSIVE_COMPONENT_REFERENCE__RECURSIVECOMPONENTREFERENCE, oldRecursivecomponentreference, newRecursivecomponentreference);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRecursivecomponentreference(RecursiveComponentReference newRecursivecomponentreference) {
		if (newRecursivecomponentreference != recursivecomponentreference) {
			NotificationChain msgs = null;
			if (recursivecomponentreference != null)
				msgs = ((InternalEObject)recursivecomponentreference).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - AnalysisPackage.RECURSIVE_COMPONENT_REFERENCE__RECURSIVECOMPONENTREFERENCE, null, msgs);
			if (newRecursivecomponentreference != null)
				msgs = ((InternalEObject)newRecursivecomponentreference).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - AnalysisPackage.RECURSIVE_COMPONENT_REFERENCE__RECURSIVECOMPONENTREFERENCE, null, msgs);
			msgs = basicSetRecursivecomponentreference(newRecursivecomponentreference, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AnalysisPackage.RECURSIVE_COMPONENT_REFERENCE__RECURSIVECOMPONENTREFERENCE, newRecursivecomponentreference, newRecursivecomponentreference));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case AnalysisPackage.RECURSIVE_COMPONENT_REFERENCE__RECURSIVECOMPONENTREFERENCE:
				return basicSetRecursivecomponentreference(null, msgs);
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
			case AnalysisPackage.RECURSIVE_COMPONENT_REFERENCE__COMPONENT:
				if (resolve) return getComponent();
				return basicGetComponent();
			case AnalysisPackage.RECURSIVE_COMPONENT_REFERENCE__RECURSIVECOMPONENTREFERENCE:
				return getRecursivecomponentreference();
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
			case AnalysisPackage.RECURSIVE_COMPONENT_REFERENCE__COMPONENT:
				setComponent((EnvironmentAsynchronousCompositeComponentInstance)newValue);
				return;
			case AnalysisPackage.RECURSIVE_COMPONENT_REFERENCE__RECURSIVECOMPONENTREFERENCE:
				setRecursivecomponentreference((RecursiveComponentReference)newValue);
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
			case AnalysisPackage.RECURSIVE_COMPONENT_REFERENCE__COMPONENT:
				setComponent((EnvironmentAsynchronousCompositeComponentInstance)null);
				return;
			case AnalysisPackage.RECURSIVE_COMPONENT_REFERENCE__RECURSIVECOMPONENTREFERENCE:
				setRecursivecomponentreference((RecursiveComponentReference)null);
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
			case AnalysisPackage.RECURSIVE_COMPONENT_REFERENCE__COMPONENT:
				return component != null;
			case AnalysisPackage.RECURSIVE_COMPONENT_REFERENCE__RECURSIVECOMPONENTREFERENCE:
				return recursivecomponentreference != null;
		}
		return super.eIsSet(featureID);
	}

} //RecursiveComponentReferenceImpl
