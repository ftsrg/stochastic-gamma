/**
 */
package hu.bme.mit.gamma.analysis;

import hu.bme.mit.gamma.environment.model.EnvironmentCompositeComponentInstance;

import hu.bme.mit.gamma.statechart.interface_.Component;

import java.math.BigInteger;
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
 *   <li>{@link hu.bme.mit.gamma.analysis.AnalysisComponent#getAnalyzedComponent <em>Analyzed Component</em>}</li>
 *   <li>{@link hu.bme.mit.gamma.analysis.AnalysisComponent#getConditions <em>Conditions</em>}</li>
 *   <li>{@link hu.bme.mit.gamma.analysis.AnalysisComponent#getAspect <em>Aspect</em>}</li>
 *   <li>{@link hu.bme.mit.gamma.analysis.AnalysisComponent#getSimulationTime <em>Simulation Time</em>}</li>
 *   <li>{@link hu.bme.mit.gamma.analysis.AnalysisComponent#getSimulationNumber <em>Simulation Number</em>}</li>
 * </ul>
 *
 * @see hu.bme.mit.gamma.analysis.AnalysisPackage#getAnalysisComponent()
 * @model
 * @generated
 */
public interface AnalysisComponent extends Component {
	/**
	 * Returns the value of the '<em><b>Analyzed Component</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Analyzed Component</em>' containment reference.
	 * @see #setAnalyzedComponent(EnvironmentCompositeComponentInstance)
	 * @see hu.bme.mit.gamma.analysis.AnalysisPackage#getAnalysisComponent_AnalyzedComponent()
	 * @model containment="true" required="true"
	 * @generated
	 */
	EnvironmentCompositeComponentInstance getAnalyzedComponent();

	/**
	 * Sets the value of the '{@link hu.bme.mit.gamma.analysis.AnalysisComponent#getAnalyzedComponent <em>Analyzed Component</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Analyzed Component</em>' containment reference.
	 * @see #getAnalyzedComponent()
	 * @generated
	 */
	void setAnalyzedComponent(EnvironmentCompositeComponentInstance value);

	/**
	 * Returns the value of the '<em><b>Conditions</b></em>' containment reference list.
	 * The list contents are of type {@link hu.bme.mit.gamma.analysis.AnalysisCondition}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Conditions</em>' containment reference list.
	 * @see hu.bme.mit.gamma.analysis.AnalysisPackage#getAnalysisComponent_Conditions()
	 * @model containment="true"
	 * @generated
	 */
	EList<AnalysisCondition> getConditions();

	/**
	 * Returns the value of the '<em><b>Aspect</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Aspect</em>' containment reference.
	 * @see #setAspect(AnalysisAspect)
	 * @see hu.bme.mit.gamma.analysis.AnalysisPackage#getAnalysisComponent_Aspect()
	 * @model containment="true" required="true"
	 * @generated
	 */
	AnalysisAspect getAspect();

	/**
	 * Sets the value of the '{@link hu.bme.mit.gamma.analysis.AnalysisComponent#getAspect <em>Aspect</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Aspect</em>' containment reference.
	 * @see #getAspect()
	 * @generated
	 */
	void setAspect(AnalysisAspect value);

	/**
	 * Returns the value of the '<em><b>Simulation Time</b></em>' attribute.
	 * The default value is <code>"1.0"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Simulation Time</em>' attribute.
	 * @see #setSimulationTime(double)
	 * @see hu.bme.mit.gamma.analysis.AnalysisPackage#getAnalysisComponent_SimulationTime()
	 * @model default="1.0" required="true"
	 * @generated
	 */
	double getSimulationTime();

	/**
	 * Sets the value of the '{@link hu.bme.mit.gamma.analysis.AnalysisComponent#getSimulationTime <em>Simulation Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Simulation Time</em>' attribute.
	 * @see #getSimulationTime()
	 * @generated
	 */
	void setSimulationTime(double value);

	/**
	 * Returns the value of the '<em><b>Simulation Number</b></em>' attribute.
	 * The default value is <code>"100"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Simulation Number</em>' attribute.
	 * @see #setSimulationNumber(BigInteger)
	 * @see hu.bme.mit.gamma.analysis.AnalysisPackage#getAnalysisComponent_SimulationNumber()
	 * @model default="100" required="true"
	 * @generated
	 */
	BigInteger getSimulationNumber();

	/**
	 * Sets the value of the '{@link hu.bme.mit.gamma.analysis.AnalysisComponent#getSimulationNumber <em>Simulation Number</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Simulation Number</em>' attribute.
	 * @see #getSimulationNumber()
	 * @generated
	 */
	void setSimulationNumber(BigInteger value);

} // AnalysisComponent
