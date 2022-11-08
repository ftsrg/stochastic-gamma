/**
 */
package hu.bme.mit.gamma.analysis;

import hu.bme.mit.gamma.environment.model.EnvironmentAsynchronousCompositeComponentInstance;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Recursive Component Reference</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link hu.bme.mit.gamma.analysis.RecursiveComponentReference#getComponent <em>Component</em>}</li>
 *   <li>{@link hu.bme.mit.gamma.analysis.RecursiveComponentReference#getRecursivecomponentreference <em>Recursivecomponentreference</em>}</li>
 * </ul>
 *
 * @see hu.bme.mit.gamma.analysis.AnalysisPackage#getRecursiveComponentReference()
 * @model
 * @generated
 */
public interface RecursiveComponentReference extends EObject {
	/**
	 * Returns the value of the '<em><b>Component</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Component</em>' reference.
	 * @see #setComponent(EnvironmentAsynchronousCompositeComponentInstance)
	 * @see hu.bme.mit.gamma.analysis.AnalysisPackage#getRecursiveComponentReference_Component()
	 * @model required="true"
	 * @generated
	 */
	EnvironmentAsynchronousCompositeComponentInstance getComponent();

	/**
	 * Sets the value of the '{@link hu.bme.mit.gamma.analysis.RecursiveComponentReference#getComponent <em>Component</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Component</em>' reference.
	 * @see #getComponent()
	 * @generated
	 */
	void setComponent(EnvironmentAsynchronousCompositeComponentInstance value);

	/**
	 * Returns the value of the '<em><b>Recursivecomponentreference</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Recursivecomponentreference</em>' containment reference.
	 * @see #setRecursivecomponentreference(RecursiveComponentReference)
	 * @see hu.bme.mit.gamma.analysis.AnalysisPackage#getRecursiveComponentReference_Recursivecomponentreference()
	 * @model containment="true"
	 * @generated
	 */
	RecursiveComponentReference getRecursivecomponentreference();

	/**
	 * Sets the value of the '{@link hu.bme.mit.gamma.analysis.RecursiveComponentReference#getRecursivecomponentreference <em>Recursivecomponentreference</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Recursivecomponentreference</em>' containment reference.
	 * @see #getRecursivecomponentreference()
	 * @generated
	 */
	void setRecursivecomponentreference(RecursiveComponentReference value);

} // RecursiveComponentReference
