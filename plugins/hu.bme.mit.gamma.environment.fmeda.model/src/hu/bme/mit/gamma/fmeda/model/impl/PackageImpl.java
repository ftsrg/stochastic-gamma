/**
 */
package hu.bme.mit.gamma.fmeda.model.impl;

import hu.bme.mit.gamma.fmeda.model.FMEDAComponent;
import hu.bme.mit.gamma.fmeda.model.FMEDADiagnostics;
import hu.bme.mit.gamma.fmeda.model.Interface;
import hu.bme.mit.gamma.fmeda.model.fmedaPackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Package</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link hu.bme.mit.gamma.fmeda.model.impl.PackageImpl#getComponents <em>Components</em>}</li>
 *   <li>{@link hu.bme.mit.gamma.fmeda.model.impl.PackageImpl#getDiagnostics <em>Diagnostics</em>}</li>
 *   <li>{@link hu.bme.mit.gamma.fmeda.model.impl.PackageImpl#getInterfaces <em>Interfaces</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PackageImpl extends NamedElementImpl implements hu.bme.mit.gamma.fmeda.model.Package {
	/**
	 * The cached value of the '{@link #getComponents() <em>Components</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getComponents()
	 * @generated
	 * @ordered
	 */
	protected EList<FMEDAComponent> components;

	/**
	 * The cached value of the '{@link #getDiagnostics() <em>Diagnostics</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDiagnostics()
	 * @generated
	 * @ordered
	 */
	protected EList<FMEDADiagnostics> diagnostics;

	/**
	 * The cached value of the '{@link #getInterfaces() <em>Interfaces</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInterfaces()
	 * @generated
	 * @ordered
	 */
	protected EList<Interface> interfaces;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PackageImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return fmedaPackage.Literals.PACKAGE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<FMEDAComponent> getComponents() {
		if (components == null) {
			components = new EObjectContainmentEList<FMEDAComponent>(FMEDAComponent.class, this, fmedaPackage.PACKAGE__COMPONENTS);
		}
		return components;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<FMEDADiagnostics> getDiagnostics() {
		if (diagnostics == null) {
			diagnostics = new EObjectContainmentEList<FMEDADiagnostics>(FMEDADiagnostics.class, this, fmedaPackage.PACKAGE__DIAGNOSTICS);
		}
		return diagnostics;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Interface> getInterfaces() {
		if (interfaces == null) {
			interfaces = new EObjectContainmentEList<Interface>(Interface.class, this, fmedaPackage.PACKAGE__INTERFACES);
		}
		return interfaces;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case fmedaPackage.PACKAGE__COMPONENTS:
				return ((InternalEList<?>)getComponents()).basicRemove(otherEnd, msgs);
			case fmedaPackage.PACKAGE__DIAGNOSTICS:
				return ((InternalEList<?>)getDiagnostics()).basicRemove(otherEnd, msgs);
			case fmedaPackage.PACKAGE__INTERFACES:
				return ((InternalEList<?>)getInterfaces()).basicRemove(otherEnd, msgs);
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
			case fmedaPackage.PACKAGE__COMPONENTS:
				return getComponents();
			case fmedaPackage.PACKAGE__DIAGNOSTICS:
				return getDiagnostics();
			case fmedaPackage.PACKAGE__INTERFACES:
				return getInterfaces();
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
			case fmedaPackage.PACKAGE__COMPONENTS:
				getComponents().clear();
				getComponents().addAll((Collection<? extends FMEDAComponent>)newValue);
				return;
			case fmedaPackage.PACKAGE__DIAGNOSTICS:
				getDiagnostics().clear();
				getDiagnostics().addAll((Collection<? extends FMEDADiagnostics>)newValue);
				return;
			case fmedaPackage.PACKAGE__INTERFACES:
				getInterfaces().clear();
				getInterfaces().addAll((Collection<? extends Interface>)newValue);
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
			case fmedaPackage.PACKAGE__COMPONENTS:
				getComponents().clear();
				return;
			case fmedaPackage.PACKAGE__DIAGNOSTICS:
				getDiagnostics().clear();
				return;
			case fmedaPackage.PACKAGE__INTERFACES:
				getInterfaces().clear();
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
			case fmedaPackage.PACKAGE__COMPONENTS:
				return components != null && !components.isEmpty();
			case fmedaPackage.PACKAGE__DIAGNOSTICS:
				return diagnostics != null && !diagnostics.isEmpty();
			case fmedaPackage.PACKAGE__INTERFACES:
				return interfaces != null && !interfaces.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //PackageImpl
