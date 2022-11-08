/**
 */
package hu.bme.mit.gamma.analysis;

import hu.bme.mit.gamma.stochastic.stochastic.StochasticModel;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Observe Condition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link hu.bme.mit.gamma.analysis.ObserveCondition#getValue <em>Value</em>}</li>
 *   <li>{@link hu.bme.mit.gamma.analysis.ObserveCondition#getRandomvariable <em>Randomvariable</em>}</li>
 * </ul>
 *
 * @see hu.bme.mit.gamma.analysis.AnalysisPackage#getObserveCondition()
 * @model abstract="true"
 * @generated
 */
public interface ObserveCondition extends AnalysisCondition {
	/**
	 * Returns the value of the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Value</em>' attribute.
	 * @see #setValue(double)
	 * @see hu.bme.mit.gamma.analysis.AnalysisPackage#getObserveCondition_Value()
	 * @model required="true"
	 * @generated
	 */
	double getValue();

	/**
	 * Sets the value of the '{@link hu.bme.mit.gamma.analysis.ObserveCondition#getValue <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' attribute.
	 * @see #getValue()
	 * @generated
	 */
	void setValue(double value);

	/**
	 * Returns the value of the '<em><b>Randomvariable</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Randomvariable</em>' containment reference.
	 * @see #setRandomvariable(StochasticModel)
	 * @see hu.bme.mit.gamma.analysis.AnalysisPackage#getObserveCondition_Randomvariable()
	 * @model containment="true" required="true"
	 * @generated
	 */
	StochasticModel getRandomvariable();

	/**
	 * Sets the value of the '{@link hu.bme.mit.gamma.analysis.ObserveCondition#getRandomvariable <em>Randomvariable</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Randomvariable</em>' containment reference.
	 * @see #getRandomvariable()
	 * @generated
	 */
	void setRandomvariable(StochasticModel value);

} // ObserveCondition
