/**
 */
package hu.bme.mit.gamma.environment.model;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Periodic Simulation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link hu.bme.mit.gamma.environment.model.PeriodicSimulation#getPeriodTime <em>Period Time</em>}</li>
 * </ul>
 *
 * @see hu.bme.mit.gamma.environment.model.EnvironmentModelPackage#getPeriodicSimulation()
 * @model
 * @generated
 */
public interface PeriodicSimulation extends Simulation {
	/**
	 * Returns the value of the '<em><b>Period Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Period Time</em>' attribute.
	 * @see #setPeriodTime(double)
	 * @see hu.bme.mit.gamma.environment.model.EnvironmentModelPackage#getPeriodicSimulation_PeriodTime()
	 * @model required="true"
	 * @generated
	 */
	double getPeriodTime();

	/**
	 * Sets the value of the '{@link hu.bme.mit.gamma.environment.model.PeriodicSimulation#getPeriodTime <em>Period Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Period Time</em>' attribute.
	 * @see #getPeriodTime()
	 * @generated
	 */
	void setPeriodTime(double value);

} // PeriodicSimulation
