/**
 */
package hu.bme.mit.gamma.environment.analysis;

import java.math.BigInteger;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Simulation Analysis Method</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link hu.bme.mit.gamma.environment.analysis.SimulationAnalysisMethod#getEndcondition <em>Endcondition</em>}</li>
 *   <li>{@link hu.bme.mit.gamma.environment.analysis.SimulationAnalysisMethod#getWarmupTime <em>Warmup Time</em>}</li>
 *   <li>{@link hu.bme.mit.gamma.environment.analysis.SimulationAnalysisMethod#getSimulationTime <em>Simulation Time</em>}</li>
 *   <li>{@link hu.bme.mit.gamma.environment.analysis.SimulationAnalysisMethod#getSimulationNumber <em>Simulation Number</em>}</li>
 * </ul>
 *
 * @see hu.bme.mit.gamma.environment.analysis.AnalysisPackage#getSimulationAnalysisMethod()
 * @model abstract="true"
 * @generated
 */
public interface SimulationAnalysisMethod extends AnalysisMethod {
	/**
	 * Returns the value of the '<em><b>Endcondition</b></em>' containment reference list.
	 * The list contents are of type {@link hu.bme.mit.gamma.environment.analysis.EndCondition}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Endcondition</em>' containment reference list.
	 * @see hu.bme.mit.gamma.environment.analysis.AnalysisPackage#getSimulationAnalysisMethod_Endcondition()
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
	 * @see hu.bme.mit.gamma.environment.analysis.AnalysisPackage#getSimulationAnalysisMethod_WarmupTime()
	 * @model default="0.0" required="true"
	 * @generated
	 */
	double getWarmupTime();

	/**
	 * Sets the value of the '{@link hu.bme.mit.gamma.environment.analysis.SimulationAnalysisMethod#getWarmupTime <em>Warmup Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Warmup Time</em>' attribute.
	 * @see #getWarmupTime()
	 * @generated
	 */
	void setWarmupTime(double value);

	/**
	 * Returns the value of the '<em><b>Simulation Time</b></em>' attribute.
	 * The default value is <code>"1.0"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Simulation Time</em>' attribute.
	 * @see #setSimulationTime(double)
	 * @see hu.bme.mit.gamma.environment.analysis.AnalysisPackage#getSimulationAnalysisMethod_SimulationTime()
	 * @model default="1.0" required="true"
	 * @generated
	 */
	double getSimulationTime();

	/**
	 * Sets the value of the '{@link hu.bme.mit.gamma.environment.analysis.SimulationAnalysisMethod#getSimulationTime <em>Simulation Time</em>}' attribute.
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
	 * @see hu.bme.mit.gamma.environment.analysis.AnalysisPackage#getSimulationAnalysisMethod_SimulationNumber()
	 * @model default="100" required="true"
	 * @generated
	 */
	BigInteger getSimulationNumber();

	/**
	 * Sets the value of the '{@link hu.bme.mit.gamma.environment.analysis.SimulationAnalysisMethod#getSimulationNumber <em>Simulation Number</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Simulation Number</em>' attribute.
	 * @see #getSimulationNumber()
	 * @generated
	 */
	void setSimulationNumber(BigInteger value);

} // SimulationAnalysisMethod
