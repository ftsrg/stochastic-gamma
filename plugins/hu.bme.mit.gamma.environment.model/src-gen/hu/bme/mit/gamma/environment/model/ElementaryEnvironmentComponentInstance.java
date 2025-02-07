/**
 */
package hu.bme.mit.gamma.environment.model;

import hu.bme.mit.gamma.statechart.interface_.Port;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Elementary Environment Component Instance</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link hu.bme.mit.gamma.environment.model.ElementaryEnvironmentComponentInstance#getOutports <em>Outports</em>}</li>
 *   <li>{@link hu.bme.mit.gamma.environment.model.ElementaryEnvironmentComponentInstance#getInports <em>Inports</em>}</li>
 *   <li>{@link hu.bme.mit.gamma.environment.model.ElementaryEnvironmentComponentInstance#getBehaviorRules <em>Behavior Rules</em>}</li>
 * </ul>
 *
 * @see hu.bme.mit.gamma.environment.model.EnvironmentModelPackage#getElementaryEnvironmentComponentInstance()
 * @model abstract="true"
 * @generated
 */
public interface ElementaryEnvironmentComponentInstance extends EnvironmentComponentInstance {
	/**
	 * Returns the value of the '<em><b>Outports</b></em>' containment reference list.
	 * The list contents are of type {@link hu.bme.mit.gamma.statechart.interface_.Port}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Outports</em>' containment reference list.
	 * @see hu.bme.mit.gamma.environment.model.EnvironmentModelPackage#getElementaryEnvironmentComponentInstance_Outports()
	 * @model containment="true"
	 * @generated
	 */
	EList<Port> getOutports();

	/**
	 * Returns the value of the '<em><b>Inports</b></em>' containment reference list.
	 * The list contents are of type {@link hu.bme.mit.gamma.statechart.interface_.Port}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Inports</em>' containment reference list.
	 * @see hu.bme.mit.gamma.environment.model.EnvironmentModelPackage#getElementaryEnvironmentComponentInstance_Inports()
	 * @model containment="true"
	 * @generated
	 */
	EList<Port> getInports();

	/**
	 * Returns the value of the '<em><b>Behavior Rules</b></em>' containment reference list.
	 * The list contents are of type {@link hu.bme.mit.gamma.environment.model.EnvironmentRule}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Behavior Rules</em>' containment reference list.
	 * @see hu.bme.mit.gamma.environment.model.EnvironmentModelPackage#getElementaryEnvironmentComponentInstance_BehaviorRules()
	 * @model containment="true"
	 * @generated
	 */
	EList<EnvironmentRule> getBehaviorRules();

} // ElementaryEnvironmentComponentInstance
