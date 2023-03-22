/**
 */
package hu.bme.mit.gamma.fmeda.model;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Interface</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link hu.bme.mit.gamma.fmeda.model.Interface#getFailuremodes <em>Failuremodes</em>}</li>
 * </ul>
 *
 * @see hu.bme.mit.gamma.fmeda.model.fmedaPackage#getInterface()
 * @model
 * @generated
 */
public interface Interface extends NamedElement {
	/**
	 * Returns the value of the '<em><b>Failuremodes</b></em>' containment reference list.
	 * The list contents are of type {@link hu.bme.mit.gamma.fmeda.model.FailureMode}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Failuremodes</em>' containment reference list.
	 * @see hu.bme.mit.gamma.fmeda.model.fmedaPackage#getInterface_Failuremodes()
	 * @model containment="true" required="true"
	 * @generated
	 */
	EList<FailureMode> getFailuremodes();

} // Interface
