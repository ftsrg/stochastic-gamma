/**
 */
package hu.bme.mit.gamma.environment.model;

import hu.bme.mit.gamma.statechart.statechart.PortEventReference;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Event Filter</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link hu.bme.mit.gamma.environment.model.EventFilter#getEvent <em>Event</em>}</li>
 * </ul>
 *
 * @see hu.bme.mit.gamma.environment.model.EnvironmentModelPackage#getEventFilter()
 * @model
 * @generated
 */
public interface EventFilter extends Filter {
	/**
	 * Returns the value of the '<em><b>Event</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Event</em>' containment reference.
	 * @see #setEvent(PortEventReference)
	 * @see hu.bme.mit.gamma.environment.model.EnvironmentModelPackage#getEventFilter_Event()
	 * @model containment="true" required="true"
	 * @generated
	 */
	PortEventReference getEvent();

	/**
	 * Sets the value of the '{@link hu.bme.mit.gamma.environment.model.EventFilter#getEvent <em>Event</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Event</em>' containment reference.
	 * @see #getEvent()
	 * @generated
	 */
	void setEvent(PortEventReference value);

} // EventFilter
