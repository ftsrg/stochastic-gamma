/**
 */
package hu.bme.mit.gamma.environment.analysis;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Greater Than</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link hu.bme.mit.gamma.environment.analysis.GreaterThan#getValue <em>Value</em>}</li>
 * </ul>
 *
 * @see hu.bme.mit.gamma.environment.analysis.AnalysisPackage#getGreaterThan()
 * @model
 * @generated
 */
public interface GreaterThan extends RequirementAspect {
	/**
	 * Returns the value of the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Value</em>' attribute.
	 * @see #setValue(double)
	 * @see hu.bme.mit.gamma.environment.analysis.AnalysisPackage#getGreaterThan_Value()
	 * @model required="true"
	 * @generated
	 */
	double getValue();

	/**
	 * Sets the value of the '{@link hu.bme.mit.gamma.environment.analysis.GreaterThan#getValue <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' attribute.
	 * @see #getValue()
	 * @generated
	 */
	void setValue(double value);

} // GreaterThan
