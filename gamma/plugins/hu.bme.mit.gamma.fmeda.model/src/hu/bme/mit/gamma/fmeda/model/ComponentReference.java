/**
 */
package hu.bme.mit.gamma.fmeda.model;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Component Reference</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link hu.bme.mit.gamma.fmeda.model.ComponentReference#getPart <em>Part</em>}</li>
 * </ul>
 *
 * @see hu.bme.mit.gamma.fmeda.model.fmedaPackage#getComponentReference()
 * @model abstract="true"
 * @generated
 */
public interface ComponentReference extends EObject {
	/**
	 * Returns the value of the '<em><b>Part</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Part</em>' reference.
	 * @see #setPart(FMEDAComponentInstance)
	 * @see hu.bme.mit.gamma.fmeda.model.fmedaPackage#getComponentReference_Part()
	 * @model required="true"
	 * @generated
	 */
	FMEDAComponentInstance getPart();

	/**
	 * Sets the value of the '{@link hu.bme.mit.gamma.fmeda.model.ComponentReference#getPart <em>Part</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Part</em>' reference.
	 * @see #getPart()
	 * @generated
	 */
	void setPart(FMEDAComponentInstance value);

} // ComponentReference
