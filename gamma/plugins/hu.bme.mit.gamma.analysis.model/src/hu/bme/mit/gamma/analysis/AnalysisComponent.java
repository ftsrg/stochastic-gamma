/**
 */
package hu.bme.mit.gamma.analysis;

import hu.bme.mit.gamma.environment.model.EnvironmentAsynchronousCompositeComponentInstance;
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
 *   <li>{@link hu.bme.mit.gamma.analysis.AnalysisComponent#getPriorydistribution <em>Priorydistribution</em>}</li>
 *   <li>{@link hu.bme.mit.gamma.analysis.AnalysisComponent#getEndcondition <em>Endcondition</em>}</li>
 *   <li>{@link hu.bme.mit.gamma.analysis.AnalysisComponent#getWarmupTime <em>Warmup Time</em>}</li>
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
	 * @see #setAnalyzedComponent(EnvironmentAsynchronousCompositeComponentInstance)
	 * @see hu.bme.mit.gamma.analysis.AnalysisPackage#getAnalysisComponent_AnalyzedComponent()
	 * @model containment="true" required="true"
	 * @generated
	 */
	EnvironmentAsynchronousCompositeComponentInstance getAnalyzedComponent();

	/**
	 * Sets the value of the '{@link hu.bme.mit.gamma.analysis.AnalysisComponent#getAnalyzedComponent <em>Analyzed Component</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Analyzed Component</em>' containment reference.
	 * @see #getAnalyzedComponent()
	 * @generated
	 */
	void setAnalyzedComponent(EnvironmentAsynchronousCompositeComponentInstance value);

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
	 * Returns the value of the '<em><b>Aspect</b></em>' containment reference list.
	 * The list contents are of type {@link hu.bme.mit.gamma.analysis.AnalysisAspect}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Aspect</em>' containment reference list.
	 * @see hu.bme.mit.gamma.analysis.AnalysisPackage#getAnalysisComponent_Aspect()
	 * @model containment="true" required="true"
	 * @generated
	 */
	EList<AnalysisAspect> getAspect();

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

	/**
	 * Returns the value of the '<em><b>Priorydistribution</b></em>' containment reference list.
	 * The list contents are of type {@link hu.bme.mit.gamma.analysis.PrioryDistribution}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Priorydistribution</em>' containment reference list.
	 * @see hu.bme.mit.gamma.analysis.AnalysisPackage#getAnalysisComponent_Priorydistribution()
	 * @model containment="true"
	 * @generated
	 */
	EList<PrioryDistribution> getPriorydistribution();

	/**
	 * Returns the value of the '<em><b>Endcondition</b></em>' containment reference list.
	 * The list contents are of type {@link hu.bme.mit.gamma.analysis.EndCondition}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Endcondition</em>' containment reference list.
	 * @see hu.bme.mit.gamma.analysis.AnalysisPackage#getAnalysisComponent_Endcondition()
	 * @model containment="true"
	 * @generated
	 */
	EList<EndCondition> getEndcondition();

	/**
	 * Returns the value of the '<em><b>Warmup Time</b></em>' attribute.
	 * The default value is <code>"0.0"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Warmup Time</em>' attribute.
	 * @see #setWarmupTime(double)
	 * @see hu.bme.mit.gamma.analysis.AnalysisPackage#getAnalysisComponent_WarmupTime()
	 * @model default="0.0" required="true"
	 * @generated
	 */
	double getWarmupTime();

	/**
	 * Sets the value of the '{@link hu.bme.mit.gamma.analysis.AnalysisComponent#getWarmupTime <em>Warmup Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Warmup Time</em>' attribute.
	 * @see #getWarmupTime()
	 * @generated
	 */
	void setWarmupTime(double value);

} // AnalysisComponent
