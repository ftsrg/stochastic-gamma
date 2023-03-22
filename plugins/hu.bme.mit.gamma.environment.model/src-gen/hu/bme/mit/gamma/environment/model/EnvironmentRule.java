/**
 */
package hu.bme.mit.gamma.environment.model;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Environment Rule</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link hu.bme.mit.gamma.environment.model.EnvironmentRule#getFilter <em>Filter</em>}</li>
 * </ul>
 *
 * @see hu.bme.mit.gamma.environment.model.EnvironmentModelPackage#getEnvironmentRule()
 * @model abstract="true"
 * @generated
 */
public interface EnvironmentRule extends EObject {
	/**
	 * Returns the value of the '<em><b>Filter</b></em>' containment reference list.
	 * The list contents are of type {@link hu.bme.mit.gamma.environment.model.Filter}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Filter</em>' containment reference list.
	 * @see hu.bme.mit.gamma.environment.model.EnvironmentModelPackage#getEnvironmentRule_Filter()
	 * @model containment="true"
	 * @generated
	 */
	EList<Filter> getFilter();

} // EnvironmentRule
