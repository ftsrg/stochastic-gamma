/**
 */
package hu.bme.mit.gamma.fmeda.model;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Diagnostics Reference</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link hu.bme.mit.gamma.fmeda.model.DiagnosticsReference#getCoverage <em>Coverage</em>}</li>
 *   <li>{@link hu.bme.mit.gamma.fmeda.model.DiagnosticsReference#getFmedadiagnostics <em>Fmedadiagnostics</em>}</li>
 * </ul>
 *
 * @see hu.bme.mit.gamma.fmeda.model.fmedaPackage#getDiagnosticsReference()
 * @model
 * @generated
 */
public interface DiagnosticsReference extends EObject {
	/**
	 * Returns the value of the '<em><b>Coverage</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Coverage</em>' attribute.
	 * @see #setCoverage(double)
	 * @see hu.bme.mit.gamma.fmeda.model.fmedaPackage#getDiagnosticsReference_Coverage()
	 * @model unique="false" required="true"
	 * @generated
	 */
	double getCoverage();

	/**
	 * Sets the value of the '{@link hu.bme.mit.gamma.fmeda.model.DiagnosticsReference#getCoverage <em>Coverage</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Coverage</em>' attribute.
	 * @see #getCoverage()
	 * @generated
	 */
	void setCoverage(double value);

	/**
	 * Returns the value of the '<em><b>Fmedadiagnostics</b></em>' reference list.
	 * The list contents are of type {@link hu.bme.mit.gamma.fmeda.model.FMEDADiagnostics}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Fmedadiagnostics</em>' reference list.
	 * @see hu.bme.mit.gamma.fmeda.model.fmedaPackage#getDiagnosticsReference_Fmedadiagnostics()
	 * @model
	 * @generated
	 */
	EList<FMEDADiagnostics> getFmedadiagnostics();

} // DiagnosticsReference
