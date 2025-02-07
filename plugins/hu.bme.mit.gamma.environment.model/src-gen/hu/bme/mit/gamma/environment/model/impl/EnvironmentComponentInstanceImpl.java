/**
 */
package hu.bme.mit.gamma.environment.model.impl;

import hu.bme.mit.gamma.environment.model.EnvironmentComponentInstance;
import hu.bme.mit.gamma.environment.model.EnvironmentModelPackage;

import hu.bme.mit.gamma.statechart.composite.impl.ComponentInstanceImpl;

import org.eclipse.emf.ecore.EClass;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Environment Component Instance</b></em>'.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public abstract class EnvironmentComponentInstanceImpl extends ComponentInstanceImpl implements EnvironmentComponentInstance {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EnvironmentComponentInstanceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EnvironmentModelPackage.Literals.ENVIRONMENT_COMPONENT_INSTANCE;
	}

} //EnvironmentComponentInstanceImpl
