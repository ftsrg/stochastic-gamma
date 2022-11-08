/**
 */
package hu.bme.mit.gamma.environment.model;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Abstract Environment Composite Component</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link hu.bme.mit.gamma.environment.model.AbstractEnvironmentCompositeComponent#getEnvironmentComponents <em>Environment Components</em>}</li>
 * </ul>
 *
 * @see hu.bme.mit.gamma.environment.model.EnvironentPackage#getAbstractEnvironmentCompositeComponent()
 * @model abstract="true"
 * @generated
 */
public interface AbstractEnvironmentCompositeComponent extends EnvironmentComponent {
	/**
	 * Returns the value of the '<em><b>Environment Components</b></em>' containment reference list.
	 * The list contents are of type {@link hu.bme.mit.gamma.environment.model.EnvironmentComponentInstance}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Environment Components</em>' containment reference list.
	 * @see hu.bme.mit.gamma.environment.model.EnvironentPackage#getAbstractEnvironmentCompositeComponent_EnvironmentComponents()
	 * @model containment="true"
	 * @generated
	 */
	EList<EnvironmentComponentInstance> getEnvironmentComponents();

} // AbstractEnvironmentCompositeComponent
