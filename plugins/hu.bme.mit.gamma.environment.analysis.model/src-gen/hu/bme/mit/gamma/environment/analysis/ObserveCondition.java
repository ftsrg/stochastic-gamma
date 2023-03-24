/**
 */
package hu.bme.mit.gamma.environment.analysis;

import hu.bme.mit.gamma.environment.stochastic.stochastic.StochasticModel;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Observe Condition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link hu.bme.mit.gamma.environment.analysis.ObserveCondition#getRandomvariable <em>Randomvariable</em>}</li>
 * </ul>
 *
 * @see hu.bme.mit.gamma.environment.analysis.AnalysisPackage#getObserveCondition()
 * @model abstract="true"
 * @generated
 */
public interface ObserveCondition extends AnalysisCondition {
	/**
	 * Returns the value of the '<em><b>Randomvariable</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Randomvariable</em>' containment reference.
	 * @see #setRandomvariable(StochasticModel)
	 * @see hu.bme.mit.gamma.environment.analysis.AnalysisPackage#getObserveCondition_Randomvariable()
	 * @model containment="true" required="true"
	 * @generated
	 */
	StochasticModel getRandomvariable();

	/**
	 * Sets the value of the '{@link hu.bme.mit.gamma.environment.analysis.ObserveCondition#getRandomvariable <em>Randomvariable</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Randomvariable</em>' containment reference.
	 * @see #getRandomvariable()
	 * @generated
	 */
	void setRandomvariable(StochasticModel value);

} // ObserveCondition
