/**
 */
package hu.bme.mit.gamma.analysis;

import hu.bme.mit.gamma.statechart.interface_.Event;
import hu.bme.mit.gamma.statechart.interface_.Port;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Component Port Event Reference</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link hu.bme.mit.gamma.analysis.ComponentPortEventReference#getPort <em>Port</em>}</li>
 *   <li>{@link hu.bme.mit.gamma.analysis.ComponentPortEventReference#getEvent <em>Event</em>}</li>
 *   <li>{@link hu.bme.mit.gamma.analysis.ComponentPortEventReference#getComponent <em>Component</em>}</li>
 * </ul>
 *
 * @see hu.bme.mit.gamma.analysis.AnalysisPackage#getComponentPortEventReference()
 * @model
 * @generated
 */
public interface ComponentPortEventReference extends EObject {
	/**
	 * Returns the value of the '<em><b>Port</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Port</em>' reference.
	 * @see #setPort(Port)
	 * @see hu.bme.mit.gamma.analysis.AnalysisPackage#getComponentPortEventReference_Port()
	 * @model required="true"
	 * @generated
	 */
	Port getPort();

	/**
	 * Sets the value of the '{@link hu.bme.mit.gamma.analysis.ComponentPortEventReference#getPort <em>Port</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Port</em>' reference.
	 * @see #getPort()
	 * @generated
	 */
	void setPort(Port value);

	/**
	 * Returns the value of the '<em><b>Event</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Event</em>' reference.
	 * @see #setEvent(Event)
	 * @see hu.bme.mit.gamma.analysis.AnalysisPackage#getComponentPortEventReference_Event()
	 * @model required="true"
	 * @generated
	 */
	Event getEvent();

	/**
	 * Sets the value of the '{@link hu.bme.mit.gamma.analysis.ComponentPortEventReference#getEvent <em>Event</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Event</em>' reference.
	 * @see #getEvent()
	 * @generated
	 */
	void setEvent(Event value);

	/**
	 * Returns the value of the '<em><b>Component</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Component</em>' containment reference.
	 * @see #setComponent(RecursiveComponentReference)
	 * @see hu.bme.mit.gamma.analysis.AnalysisPackage#getComponentPortEventReference_Component()
	 * @model containment="true" required="true"
	 * @generated
	 */
	RecursiveComponentReference getComponent();

	/**
	 * Sets the value of the '{@link hu.bme.mit.gamma.analysis.ComponentPortEventReference#getComponent <em>Component</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Component</em>' containment reference.
	 * @see #getComponent()
	 * @generated
	 */
	void setComponent(RecursiveComponentReference value);

} // ComponentPortEventReference
