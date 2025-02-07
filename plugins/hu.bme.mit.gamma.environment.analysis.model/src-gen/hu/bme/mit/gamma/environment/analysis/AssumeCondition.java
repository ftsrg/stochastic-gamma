/**
 */
package hu.bme.mit.gamma.environment.analysis;

import hu.bme.mit.gamma.expression.model.DecimalLiteralExpression;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Assume Condition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link hu.bme.mit.gamma.environment.analysis.AssumeCondition#getProbability <em>Probability</em>}</li>
 * </ul>
 *
 * @see hu.bme.mit.gamma.environment.analysis.AnalysisPackage#getAssumeCondition()
 * @model abstract="true"
 * @generated
 */
public interface AssumeCondition extends AnalysisCondition {

	/**
	 * Returns the value of the '<em><b>Probability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Probability</em>' containment reference.
	 * @see #setProbability(DecimalLiteralExpression)
	 * @see hu.bme.mit.gamma.environment.analysis.AnalysisPackage#getAssumeCondition_Probability()
	 * @model containment="true"
	 * @generated
	 */
	DecimalLiteralExpression getProbability();

	/**
	 * Sets the value of the '{@link hu.bme.mit.gamma.environment.analysis.AssumeCondition#getProbability <em>Probability</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Probability</em>' containment reference.
	 * @see #getProbability()
	 * @generated
	 */
	void setProbability(DecimalLiteralExpression value);
} // AssumeCondition
