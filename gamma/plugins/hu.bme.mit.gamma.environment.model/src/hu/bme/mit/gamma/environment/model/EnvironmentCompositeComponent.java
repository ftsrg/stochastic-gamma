/**
 */
package hu.bme.mit.gamma.environment.model;

import hu.bme.mit.gamma.statechart.composite.CascadeCompositeComponent;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Environment Composite Component</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link hu.bme.mit.gamma.environment.model.EnvironmentCompositeComponent#getEnvironmentComponents <em>Environment Components</em>}</li>
 * </ul>
 *
 * @see hu.bme.mit.gamma.environment.model.EnvironentPackage#getEnvironmentCompositeComponent()
 * @model
 * @generated
 */
public interface EnvironmentCompositeComponent extends CascadeCompositeComponent {
	/**
	 * Returns the value of the '<em><b>Environment Components</b></em>' containment reference list.
	 * The list contents are of type {@link hu.bme.mit.gamma.environment.model.EnvironmentComponentInstance}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Environment Components</em>' containment reference list.
	 * @see hu.bme.mit.gamma.environment.model.EnvironentPackage#getEnvironmentCompositeComponent_EnvironmentComponents()
	 * @model containment="true"
	 * @generated
	 */
	EList<EnvironmentComponentInstance> getEnvironmentComponents();

} // EnvironmentCompositeComponent
