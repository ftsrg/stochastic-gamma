/**
 */
package hu.bme.mit.gamma.environment.model;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Simulation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link hu.bme.mit.gamma.environment.model.Simulation#getSimulationClassName <em>Simulation Class Name</em>}</li>
 * </ul>
 *
 * @see hu.bme.mit.gamma.environment.model.EnvironentPackage#getSimulation()
 * @model
 * @generated
 */
public interface Simulation extends EObject {
	/**
	 * Returns the value of the '<em><b>Simulation Class Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Simulation Class Name</em>' attribute.
	 * @see #setSimulationClassName(String)
	 * @see hu.bme.mit.gamma.environment.model.EnvironentPackage#getSimulation_SimulationClassName()
	 * @model required="true"
	 * @generated
	 */
	String getSimulationClassName();

	/**
	 * Sets the value of the '{@link hu.bme.mit.gamma.environment.model.Simulation#getSimulationClassName <em>Simulation Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Simulation Class Name</em>' attribute.
	 * @see #getSimulationClassName()
	 * @generated
	 */
	void setSimulationClassName(String value);

} // Simulation
