/**
 */
package hu.bme.mit.gamma.environment.analysis;

import java.math.BigInteger;

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
	 * Returns the value of the '<em><b>Warmup Step Num</b></em>' attribute.
	 * The default value is <code>"0"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Warmup Step Num</em>' attribute.
	 * @see #setWarmupStepNum(BigInteger)
	 * @see hu.bme.mit.gamma.environment.analysis.AnalysisPackage#getMCMC_WarmupStepNum()
	 * @model default="0" required="true"
	 * @generated
	 */
	BigInteger getWarmupStepNum();

	/**
	 * Sets the value of the '{@link hu.bme.mit.gamma.environment.analysis.MCMC#getWarmupStepNum <em>Warmup Step Num</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Warmup Step Num</em>' attribute.
	 * @see #getWarmupStepNum()
	 * @generated
	 */
	void setWarmupStepNum(BigInteger value);

} // MCMC
