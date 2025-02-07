/**
 */
package hu.bme.mit.gamma.environment.analysis;

import hu.bme.mit.gamma.expression.model.IntegerLiteralExpression;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>MCMC</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link hu.bme.mit.gamma.environment.analysis.MCMC#getKernel <em>Kernel</em>}</li>
 *   <li>{@link hu.bme.mit.gamma.environment.analysis.MCMC#getWarmupStepNum <em>Warmup Step Num</em>}</li>
 * </ul>
 *
 * @see hu.bme.mit.gamma.environment.analysis.AnalysisPackage#getMCMC()
 * @model
 * @generated
 */
public interface MCMC extends SimulationAnalysisMethod {
	/**
	 * Returns the value of the '<em><b>Kernel</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Kernel</em>' containment reference.
	 * @see #setKernel(MCMCKernel)
	 * @see hu.bme.mit.gamma.environment.analysis.AnalysisPackage#getMCMC_Kernel()
	 * @model containment="true" required="true"
	 * @generated
	 */
	MCMCKernel getKernel();

	/**
	 * Sets the value of the '{@link hu.bme.mit.gamma.environment.analysis.MCMC#getKernel <em>Kernel</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Kernel</em>' containment reference.
	 * @see #getKernel()
	 * @generated
	 */
	void setKernel(MCMCKernel value);

	/**
	 * Returns the value of the '<em><b>Warmup Step Num</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Warmup Step Num</em>' containment reference.
	 * @see #setWarmupStepNum(IntegerLiteralExpression)
	 * @see hu.bme.mit.gamma.environment.analysis.AnalysisPackage#getMCMC_WarmupStepNum()
	 * @model containment="true" required="true"
	 * @generated
	 */
	IntegerLiteralExpression getWarmupStepNum();

	/**
	 * Sets the value of the '{@link hu.bme.mit.gamma.environment.analysis.MCMC#getWarmupStepNum <em>Warmup Step Num</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Warmup Step Num</em>' containment reference.
	 * @see #getWarmupStepNum()
	 * @generated
	 */
	void setWarmupStepNum(IntegerLiteralExpression value);

} // MCMC
