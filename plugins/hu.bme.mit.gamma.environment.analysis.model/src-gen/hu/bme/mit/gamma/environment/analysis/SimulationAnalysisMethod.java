/**
 */
package hu.bme.mit.gamma.environment.analysis;

import hu.bme.mit.gamma.expression.model.Expression;
import hu.bme.mit.gamma.expression.model.IntegerLiteralExpression;
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
 *   <li>{@link hu.bme.mit.gamma.environment.analysis.SimulationAnalysisMethod#getSimulationNumber <em>Simulation Number</em>}</li>
 *   <li>{@link hu.bme.mit.gamma.environment.analysis.SimulationAnalysisMethod#getWarmupTime <em>Warmup Time</em>}</li>
 *   <li>{@link hu.bme.mit.gamma.environment.analysis.SimulationAnalysisMethod#getSimulationTime <em>Simulation Time</em>}</li>
 *   <li>{@link hu.bme.mit.gamma.environment.analysis.SimulationAnalysisMethod#getSamplingBatchSize <em>Sampling Batch Size</em>}</li>
 *   <li>{@link hu.bme.mit.gamma.environment.analysis.SimulationAnalysisMethod#getJointSampling <em>Joint Sampling</em>}</li>
 *   <li>{@link hu.bme.mit.gamma.environment.analysis.SimulationAnalysisMethod#isDebug <em>Debug</em>}</li>
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
	 * Returns the value of the '<em><b>Warmup Time</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Warmup Time</em>' containment reference.
	 * @see #setWarmupTime(Expression)
	 * @see hu.bme.mit.gamma.environment.analysis.AnalysisPackage#getSimulationAnalysisMethod_WarmupTime()
	 * @model containment="true"
	 * @generated
	 */
	Expression getWarmupTime();

	/**
	 * Sets the value of the '{@link hu.bme.mit.gamma.environment.analysis.SimulationAnalysisMethod#getWarmupTime <em>Warmup Time</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Warmup Time</em>' containment reference.
	 * @see #getWarmupTime()
	 * @generated
	 */
	void setWarmupTime(Expression value);

	/**
	 * Returns the value of the '<em><b>Simulation Time</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Simulation Time</em>' containment reference.
	 * @see #setSimulationTime(Expression)
	 * @see hu.bme.mit.gamma.environment.analysis.AnalysisPackage#getSimulationAnalysisMethod_SimulationTime()
	 * @model containment="true" required="true"
	 * @generated
	 */
	Expression getSimulationTime();

	/**
	 * Sets the value of the '{@link hu.bme.mit.gamma.environment.analysis.SimulationAnalysisMethod#getSimulationTime <em>Simulation Time</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Simulation Time</em>' containment reference.
	 * @see #getSimulationTime()
	 * @generated
	 */
	void setSimulationTime(Expression value);

	/**
	 * Returns the value of the '<em><b>Sampling Batch Size</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sampling Batch Size</em>' containment reference.
	 * @see #setSamplingBatchSize(IntegerLiteralExpression)
	 * @see hu.bme.mit.gamma.environment.analysis.AnalysisPackage#getSimulationAnalysisMethod_SamplingBatchSize()
	 * @model containment="true"
	 * @generated
	 */
	IntegerLiteralExpression getSamplingBatchSize();

	/**
	 * Sets the value of the '{@link hu.bme.mit.gamma.environment.analysis.SimulationAnalysisMethod#getSamplingBatchSize <em>Sampling Batch Size</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Sampling Batch Size</em>' containment reference.
	 * @see #getSamplingBatchSize()
	 * @generated
	 */
	void setSamplingBatchSize(IntegerLiteralExpression value);

	/**
	 * Returns the value of the '<em><b>Joint Sampling</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Joint Sampling</em>' attribute.
	 * @see #setJointSampling(Boolean)
	 * @see hu.bme.mit.gamma.environment.analysis.AnalysisPackage#getSimulationAnalysisMethod_JointSampling()
	 * @model default="false" required="true"
	 * @generated
	 */
	Boolean getJointSampling();

	/**
	 * Sets the value of the '{@link hu.bme.mit.gamma.environment.analysis.SimulationAnalysisMethod#getJointSampling <em>Joint Sampling</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Joint Sampling</em>' attribute.
	 * @see #getJointSampling()
	 * @generated
	 */
	void setJointSampling(Boolean value);

	/**
	 * Returns the value of the '<em><b>Debug</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Debug</em>' attribute.
	 * @see #setDebug(boolean)
	 * @see hu.bme.mit.gamma.environment.analysis.AnalysisPackage#getSimulationAnalysisMethod_Debug()
	 * @model default="false" required="true"
	 * @generated
	 */
	boolean isDebug();

	/**
	 * Sets the value of the '{@link hu.bme.mit.gamma.environment.analysis.SimulationAnalysisMethod#isDebug <em>Debug</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Debug</em>' attribute.
	 * @see #isDebug()
	 * @generated
	 */
	void setDebug(boolean value);

	/**
	 * Returns the value of the '<em><b>Simulation Number</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Simulation Number</em>' containment reference.
	 * @see #setSimulationNumber(IntegerLiteralExpression)
	 * @see hu.bme.mit.gamma.environment.analysis.AnalysisPackage#getSimulationAnalysisMethod_SimulationNumber()
	 * @model containment="true" required="true"
	 * @generated
	 */
	IntegerLiteralExpression getSimulationNumber();

	/**
	 * Sets the value of the '{@link hu.bme.mit.gamma.environment.analysis.SimulationAnalysisMethod#getSimulationNumber <em>Simulation Number</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Simulation Number</em>' containment reference.
	 * @see #getSimulationNumber()
	 * @generated
	 */
	void setSimulationNumber(IntegerLiteralExpression value);

} // SimulationAnalysisMethod
