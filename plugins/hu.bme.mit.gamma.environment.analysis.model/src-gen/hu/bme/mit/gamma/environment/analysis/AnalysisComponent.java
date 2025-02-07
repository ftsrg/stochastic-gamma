/**
 */
package hu.bme.mit.gamma.environment.analysis;

import hu.bme.mit.gamma.environment.model.EnvironmentAsynchronousCompositeComponentInstance;

import hu.bme.mit.gamma.statechart.interface_.Component;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Component</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link hu.bme.mit.gamma.environment.analysis.AnalysisComponent#getAnalyzedComponent <em>Analyzed Component</em>}</li>
 *   <li>{@link hu.bme.mit.gamma.environment.analysis.AnalysisComponent#getConditions <em>Conditions</em>}</li>
 *   <li>{@link hu.bme.mit.gamma.environment.analysis.AnalysisComponent#getAspect <em>Aspect</em>}</li>
 *   <li>{@link hu.bme.mit.gamma.environment.analysis.AnalysisComponent#getPriorydistribution <em>Priorydistribution</em>}</li>
 *   <li>{@link hu.bme.mit.gamma.environment.analysis.AnalysisComponent#getAnalysismethod <em>Analysismethod</em>}</li>
 * </ul>
 *
 * @see hu.bme.mit.gamma.environment.analysis.AnalysisPackage#getAnalysisComponent()
 * @model
 * @generated
 */
public interface AnalysisComponent extends Component {
	/**
	 * Returns the value of the '<em><b>Analyzed Component</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Analyzed Component</em>' containment reference.
	 * @see #setAnalyzedComponent(EnvironmentAsynchronousCompositeComponentInstance)
	 * @see hu.bme.mit.gamma.environment.analysis.AnalysisPackage#getAnalysisComponent_AnalyzedComponent()
	 * @model containment="true" required="true"
	 * @generated
	 */
	EnvironmentAsynchronousCompositeComponentInstance getAnalyzedComponent();

	/**
	 * Sets the value of the '{@link hu.bme.mit.gamma.environment.analysis.AnalysisComponent#getAnalyzedComponent <em>Analyzed Component</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Analyzed Component</em>' containment reference.
	 * @see #getAnalyzedComponent()
	 * @generated
	 */
	void setAnalyzedComponent(EnvironmentAsynchronousCompositeComponentInstance value);

	/**
	 * Returns the value of the '<em><b>Conditions</b></em>' containment reference list.
	 * The list contents are of type {@link hu.bme.mit.gamma.environment.analysis.AnalysisCondition}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Conditions</em>' containment reference list.
	 * @see hu.bme.mit.gamma.environment.analysis.AnalysisPackage#getAnalysisComponent_Conditions()
	 * @model containment="true"
	 * @generated
	 */
	EList<AnalysisCondition> getConditions();

	/**
	 * Returns the value of the '<em><b>Aspect</b></em>' containment reference list.
	 * The list contents are of type {@link hu.bme.mit.gamma.environment.analysis.AnalysisAspect}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Aspect</em>' containment reference list.
	 * @see hu.bme.mit.gamma.environment.analysis.AnalysisPackage#getAnalysisComponent_Aspect()
	 * @model containment="true" required="true"
	 * @generated
	 */
	EList<AnalysisAspect> getAspect();

	/**
	 * Returns the value of the '<em><b>Priorydistribution</b></em>' containment reference list.
	 * The list contents are of type {@link hu.bme.mit.gamma.environment.analysis.PrioryDistribution}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Priorydistribution</em>' containment reference list.
	 * @see hu.bme.mit.gamma.environment.analysis.AnalysisPackage#getAnalysisComponent_Priorydistribution()
	 * @model containment="true"
	 * @generated
	 */
	EList<PrioryDistribution> getPriorydistribution();

	/**
	 * Returns the value of the '<em><b>Analysismethod</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Analysismethod</em>' containment reference.
	 * @see #setAnalysismethod(AnalysisMethod)
	 * @see hu.bme.mit.gamma.environment.analysis.AnalysisPackage#getAnalysisComponent_Analysismethod()
	 * @model containment="true" required="true"
	 * @generated
	 */
	AnalysisMethod getAnalysismethod();

	/**
	 * Sets the value of the '{@link hu.bme.mit.gamma.environment.analysis.AnalysisComponent#getAnalysismethod <em>Analysismethod</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Analysismethod</em>' containment reference.
	 * @see #getAnalysismethod()
	 * @generated
	 */
	void setAnalysismethod(AnalysisMethod value);

} // AnalysisComponent
