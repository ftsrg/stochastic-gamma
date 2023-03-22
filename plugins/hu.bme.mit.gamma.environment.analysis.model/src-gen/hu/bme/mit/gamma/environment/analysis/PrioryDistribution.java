/**
 */
package hu.bme.mit.gamma.environment.analysis;

import hu.bme.mit.gamma.environment.stochastic.stochastic.RandomVariable;

import hu.bme.mit.gamma.expression.model.ParameterDeclaration;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Priory Distribution</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link hu.bme.mit.gamma.environment.analysis.PrioryDistribution#getRandomvariable <em>Randomvariable</em>}</li>
 *   <li>{@link hu.bme.mit.gamma.environment.analysis.PrioryDistribution#getParameter <em>Parameter</em>}</li>
 * </ul>
 *
 * @see hu.bme.mit.gamma.environment.analysis.AnalysisPackage#getPrioryDistribution()
 * @model
 * @generated
 */
public interface PrioryDistribution extends EObject {
	/**
	 * Returns the value of the '<em><b>Randomvariable</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Randomvariable</em>' containment reference.
	 * @see #setRandomvariable(RandomVariable)
	 * @see hu.bme.mit.gamma.environment.analysis.AnalysisPackage#getPrioryDistribution_Randomvariable()
	 * @model containment="true" required="true"
	 * @generated
	 */
	RandomVariable getRandomvariable();

	/**
	 * Sets the value of the '{@link hu.bme.mit.gamma.environment.analysis.PrioryDistribution#getRandomvariable <em>Randomvariable</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Randomvariable</em>' containment reference.
	 * @see #getRandomvariable()
	 * @generated
	 */
	void setRandomvariable(RandomVariable value);

	/**
	 * Returns the value of the '<em><b>Parameter</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parameter</em>' reference.
	 * @see #setParameter(ParameterDeclaration)
	 * @see hu.bme.mit.gamma.environment.analysis.AnalysisPackage#getPrioryDistribution_Parameter()
	 * @model required="true"
	 * @generated
	 */
	ParameterDeclaration getParameter();

	/**
	 * Sets the value of the '{@link hu.bme.mit.gamma.environment.analysis.PrioryDistribution#getParameter <em>Parameter</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parameter</em>' reference.
	 * @see #getParameter()
	 * @generated
	 */
	void setParameter(ParameterDeclaration value);

} // PrioryDistribution
