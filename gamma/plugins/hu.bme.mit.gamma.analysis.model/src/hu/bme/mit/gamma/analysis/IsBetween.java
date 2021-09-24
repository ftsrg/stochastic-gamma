/**
 */
package hu.bme.mit.gamma.analysis;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Is Between</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link hu.bme.mit.gamma.analysis.IsBetween#getLowerBound <em>Lower Bound</em>}</li>
 *   <li>{@link hu.bme.mit.gamma.analysis.IsBetween#getUpperBound <em>Upper Bound</em>}</li>
 * </ul>
 *
 * @see hu.bme.mit.gamma.analysis.AnalysisPackage#getIsBetween()
 * @model
 * @generated
 */
public interface IsBetween extends RequirementAspect {
	/**
	 * Returns the value of the '<em><b>Lower Bound</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Lower Bound</em>' attribute.
	 * @see #setLowerBound(double)
	 * @see hu.bme.mit.gamma.analysis.AnalysisPackage#getIsBetween_LowerBound()
	 * @model required="true"
	 * @generated
	 */
	double getLowerBound();

	/**
	 * Sets the value of the '{@link hu.bme.mit.gamma.analysis.IsBetween#getLowerBound <em>Lower Bound</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Lower Bound</em>' attribute.
	 * @see #getLowerBound()
	 * @generated
	 */
	void setLowerBound(double value);

	/**
	 * Returns the value of the '<em><b>Upper Bound</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Upper Bound</em>' attribute.
	 * @see #setUpperBound(double)
	 * @see hu.bme.mit.gamma.analysis.AnalysisPackage#getIsBetween_UpperBound()
	 * @model required="true"
	 * @generated
	 */
	double getUpperBound();

	/**
	 * Sets the value of the '{@link hu.bme.mit.gamma.analysis.IsBetween#getUpperBound <em>Upper Bound</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Upper Bound</em>' attribute.
	 * @see #getUpperBound()
	 * @generated
	 */
	void setUpperBound(double value);

} // IsBetween
