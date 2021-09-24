/**
 */
package hu.bme.mit.gamma.analysis;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Condition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link hu.bme.mit.gamma.analysis.AnalysisCondition#getEvent <em>Event</em>}</li>
 * </ul>
 *
 * @see hu.bme.mit.gamma.analysis.AnalysisPackage#getAnalysisCondition()
 * @model abstract="true"
 * @generated
 */
public interface AnalysisCondition extends EObject {
	/**
	 * Returns the value of the '<em><b>Event</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Event</em>' containment reference.
	 * @see #setEvent(ComponentPortEventReference)
	 * @see hu.bme.mit.gamma.analysis.AnalysisPackage#getAnalysisCondition_Event()
	 * @model containment="true" required="true"
	 * @generated
	 */
	ComponentPortEventReference getEvent();

	/**
	 * Sets the value of the '{@link hu.bme.mit.gamma.analysis.AnalysisCondition#getEvent <em>Event</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Event</em>' containment reference.
	 * @see #getEvent()
	 * @generated
	 */
	void setEvent(ComponentPortEventReference value);

} // AnalysisCondition
