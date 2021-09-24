/**
 */
package hu.bme.mit.gamma.fmeda.model;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Package</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link hu.bme.mit.gamma.fmeda.model.Package#getComponents <em>Components</em>}</li>
 *   <li>{@link hu.bme.mit.gamma.fmeda.model.Package#getDiagnostics <em>Diagnostics</em>}</li>
 *   <li>{@link hu.bme.mit.gamma.fmeda.model.Package#getInterfaces <em>Interfaces</em>}</li>
 * </ul>
 *
 * @see hu.bme.mit.gamma.fmeda.model.fmedaPackage#getPackage()
 * @model
 * @generated
 */
public interface Package extends NamedElement {
	/**
	 * Returns the value of the '<em><b>Components</b></em>' containment reference list.
	 * The list contents are of type {@link hu.bme.mit.gamma.fmeda.model.FMEDAComponent}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Components</em>' containment reference list.
	 * @see hu.bme.mit.gamma.fmeda.model.fmedaPackage#getPackage_Components()
	 * @model containment="true"
	 * @generated
	 */
	EList<FMEDAComponent> getComponents();

	/**
	 * Returns the value of the '<em><b>Diagnostics</b></em>' containment reference list.
	 * The list contents are of type {@link hu.bme.mit.gamma.fmeda.model.FMEDADiagnostics}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Diagnostics</em>' containment reference list.
	 * @see hu.bme.mit.gamma.fmeda.model.fmedaPackage#getPackage_Diagnostics()
	 * @model containment="true"
	 * @generated
	 */
	EList<FMEDADiagnostics> getDiagnostics();

	/**
	 * Returns the value of the '<em><b>Interfaces</b></em>' containment reference list.
	 * The list contents are of type {@link hu.bme.mit.gamma.fmeda.model.Interface}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Interfaces</em>' containment reference list.
	 * @see hu.bme.mit.gamma.fmeda.model.fmedaPackage#getPackage_Interfaces()
	 * @model containment="true"
	 * @generated
	 */
	EList<Interface> getInterfaces();

} // Package
