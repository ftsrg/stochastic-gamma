/**
 */
package hu.bme.mit.gamma.analysis;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Requirement Component</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link hu.bme.mit.gamma.analysis.RequirementComponent#getRequirement <em>Requirement</em>}</li>
 * </ul>
 *
 * @see hu.bme.mit.gamma.analysis.AnalysisPackage#getRequirementComponent()
 * @model
 * @generated
 */
public interface RequirementComponent extends AnalysisComponent {
	/**
	 * Returns the value of the '<em><b>Requirement</b></em>' containment reference list.
	 * The list contents are of type {@link hu.bme.mit.gamma.analysis.RequirementAspect}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Requirement</em>' containment reference list.
	 * @see hu.bme.mit.gamma.analysis.AnalysisPackage#getRequirementComponent_Requirement()
	 * @model containment="true"
	 * @generated
	 */
	EList<RequirementAspect> getRequirement();

} // RequirementComponent
