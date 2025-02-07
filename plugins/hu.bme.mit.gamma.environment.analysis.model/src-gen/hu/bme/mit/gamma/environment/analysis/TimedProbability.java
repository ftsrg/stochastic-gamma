/**
 */
package hu.bme.mit.gamma.environment.analysis;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Timed Probability</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link hu.bme.mit.gamma.environment.analysis.TimedProbability#getTimeLimit <em>Time Limit</em>}</li>
 * </ul>
 *
 * @see hu.bme.mit.gamma.environment.analysis.AnalysisPackage#getTimedProbability()
 * @model
 * @generated
 */
public interface TimedProbability extends AnalysisAspect {
	/**
	 * Returns the value of the '<em><b>Time Limit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Time Limit</em>' attribute.
	 * @see #setTimeLimit(double)
	 * @see hu.bme.mit.gamma.environment.analysis.AnalysisPackage#getTimedProbability_TimeLimit()
	 * @model required="true"
	 * @generated
	 */
	double getTimeLimit();

	/**
	 * Sets the value of the '{@link hu.bme.mit.gamma.environment.analysis.TimedProbability#getTimeLimit <em>Time Limit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Time Limit</em>' attribute.
	 * @see #getTimeLimit()
	 * @generated
	 */
	void setTimeLimit(double value);

} // TimedProbability
