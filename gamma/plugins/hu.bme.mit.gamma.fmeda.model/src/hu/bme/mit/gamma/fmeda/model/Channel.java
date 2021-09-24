/**
 */
package hu.bme.mit.gamma.fmeda.model;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Channel</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link hu.bme.mit.gamma.fmeda.model.Channel#getFrom <em>From</em>}</li>
 *   <li>{@link hu.bme.mit.gamma.fmeda.model.Channel#getTo <em>To</em>}</li>
 * </ul>
 *
 * @see hu.bme.mit.gamma.fmeda.model.fmedaPackage#getChannel()
 * @model
 * @generated
 */
public interface Channel extends EObject {
	/**
	 * Returns the value of the '<em><b>From</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>From</em>' containment reference.
	 * @see #setFrom(ChannelPortReference)
	 * @see hu.bme.mit.gamma.fmeda.model.fmedaPackage#getChannel_From()
	 * @model containment="true" required="true"
	 * @generated
	 */
	ChannelPortReference getFrom();

	/**
	 * Sets the value of the '{@link hu.bme.mit.gamma.fmeda.model.Channel#getFrom <em>From</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>From</em>' containment reference.
	 * @see #getFrom()
	 * @generated
	 */
	void setFrom(ChannelPortReference value);

	/**
	 * Returns the value of the '<em><b>To</b></em>' containment reference list.
	 * The list contents are of type {@link hu.bme.mit.gamma.fmeda.model.ChannelPortReference}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>To</em>' containment reference list.
	 * @see hu.bme.mit.gamma.fmeda.model.fmedaPackage#getChannel_To()
	 * @model containment="true" required="true"
	 * @generated
	 */
	EList<ChannelPortReference> getTo();

} // Channel
