/**
 */
package hu.bme.mit.gamma.environment.analysis;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Aspect</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link hu.bme.mit.gamma.environment.analysis.AnalysisAspect#getEvent <em>Event</em>}</li>
 * </ul>
 *
 * @see hu.bme.mit.gamma.environment.analysis.AnalysisPackage#getAnalysisAspect()
 * @model abstract="true"
 * @generated
 */
public interface AnalysisAspect extends EObject {
	/**
	 * Returns the value of the '<em><b>Event</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Event</em>' containment reference.
	 * @see #setEvent(ComponentPortEventReference)
	 * @see hu.bme.mit.gamma.environment.analysis.AnalysisPackage#getAnalysisAspect_Event()
	 * @model containment="true" required="true"
	 * @generated
	 */
	ComponentPortEventReference getEvent();

	/**
	 * Sets the value of the '{@link hu.bme.mit.gamma.environment.analysis.AnalysisAspect#getEvent <em>Event</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Event</em>' containment reference.
	 * @see #getEvent()
	 * @generated
	 */
	void setEvent(ComponentPortEventReference value);

} // AnalysisAspect
