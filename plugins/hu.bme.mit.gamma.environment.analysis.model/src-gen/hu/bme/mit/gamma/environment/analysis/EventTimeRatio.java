/**
 */
package hu.bme.mit.gamma.environment.analysis;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Event Time Ratio</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link hu.bme.mit.gamma.environment.analysis.EventTimeRatio#getEvent2 <em>Event2</em>}</li>
 * </ul>
 *
 * @see hu.bme.mit.gamma.environment.analysis.AnalysisPackage#getEventTimeRatio()
 * @model
 * @generated
 */
public interface EventTimeRatio extends AnalysisAspect {
	/**
	 * Returns the value of the '<em><b>Event2</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Event2</em>' containment reference.
	 * @see #setEvent2(ComponentPortEventReference)
	 * @see hu.bme.mit.gamma.environment.analysis.AnalysisPackage#getEventTimeRatio_Event2()
	 * @model containment="true" required="true"
	 * @generated
	 */
	ComponentPortEventReference getEvent2();

	/**
	 * Sets the value of the '{@link hu.bme.mit.gamma.environment.analysis.EventTimeRatio#getEvent2 <em>Event2</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Event2</em>' containment reference.
	 * @see #getEvent2()
	 * @generated
	 */
	void setEvent2(ComponentPortEventReference value);

} // EventTimeRatio
