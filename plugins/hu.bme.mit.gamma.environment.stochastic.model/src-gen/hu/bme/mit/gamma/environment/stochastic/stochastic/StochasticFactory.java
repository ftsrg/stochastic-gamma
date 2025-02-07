/**
 */
package hu.bme.mit.gamma.environment.stochastic.stochastic;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see hu.bme.mit.gamma.environment.stochastic.stochastic.StochasticPackage
 * @generated
 */
public interface StochasticFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	StochasticFactory eINSTANCE = hu.bme.mit.gamma.environment.stochastic.stochastic.impl.StochasticFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Normal Random Variable</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Normal Random Variable</em>'.
	 * @generated
	 */
	NormalRandomVariable createNormalRandomVariable();

	/**
	 * Returns a new object of class '<em>Exponential Random Variable</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Exponential Random Variable</em>'.
	 * @generated
	 */
	ExponentialRandomVariable createExponentialRandomVariable();

	/**
	 * Returns a new object of class '<em>Bernoulli Random Variable</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Bernoulli Random Variable</em>'.
	 * @generated
	 */
	BernoulliRandomVariable createBernoulliRandomVariable();

	/**
	 * Returns a new object of class '<em>Gaussian Process</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Gaussian Process</em>'.
	 * @generated
	 */
	GaussianProcess createGaussianProcess();

	/**
	 * Returns a new object of class '<em>Linear Kernel</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Linear Kernel</em>'.
	 * @generated
	 */
	LinearKernel createLinearKernel();

	/**
	 * Returns a new object of class '<em>Brownian Kernel</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Brownian Kernel</em>'.
	 * @generated
	 */
	BrownianKernel createBrownianKernel();

	/**
	 * Returns a new object of class '<em>Periodic Kernel</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Periodic Kernel</em>'.
	 * @generated
	 */
	PeriodicKernel createPeriodicKernel();

	/**
	 * Returns a new object of class '<em>RBF Kernel</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>RBF Kernel</em>'.
	 * @generated
	 */
	RBFKernel createRBFKernel();

	/**
	 * Returns a new object of class '<em>Sum Kernel</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Sum Kernel</em>'.
	 * @generated
	 */
	SumKernel createSumKernel();

	/**
	 * Returns a new object of class '<em>Fitted Gaussian Process</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Fitted Gaussian Process</em>'.
	 * @generated
	 */
	FittedGaussianProcess createFittedGaussianProcess();

	/**
	 * Returns a new object of class '<em>Influx DB</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Influx DB</em>'.
	 * @generated
	 */
	InfluxDB createInfluxDB();

	/**
	 * Returns a new object of class '<em>Dirac Process</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Dirac Process</em>'.
	 * @generated
	 */
	DiracProcess createDiracProcess();

	/**
	 * Returns a new object of class '<em>Python Simulation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Python Simulation</em>'.
	 * @generated
	 */
	PythonSimulation createPythonSimulation();

	/**
	 * Returns a new object of class '<em>Categorical Probabaility</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Categorical Probabaility</em>'.
	 * @generated
	 */
	CategoricalProbabaility createCategoricalProbabaility();

	/**
	 * Returns a new object of class '<em>Homogeneous Poisson Process</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Homogeneous Poisson Process</em>'.
	 * @generated
	 */
	HomogeneousPoissonProcess createHomogeneousPoissonProcess();

	/**
	 * Returns a new object of class '<em>Inhomogeneous Poisson Process</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Inhomogeneous Poisson Process</em>'.
	 * @generated
	 */
	InhomogeneousPoissonProcess createInhomogeneousPoissonProcess();

	/**
	 * Returns a new object of class '<em>Weibull Random Variable</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Weibull Random Variable</em>'.
	 * @generated
	 */
	WeibullRandomVariable createWeibullRandomVariable();

	/**
	 * Returns a new object of class '<em>Markov Process</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Markov Process</em>'.
	 * @generated
	 */
	MarkovProcess createMarkovProcess();

	/**
	 * Returns a new object of class '<em>Probabilistic Program</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Probabilistic Program</em>'.
	 * @generated
	 */
	ProbabilisticProgram createProbabilisticProgram();

	/**
	 * Returns a new object of class '<em>Fitted Normal Random Variable</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Fitted Normal Random Variable</em>'.
	 * @generated
	 */
	FittedNormalRandomVariable createFittedNormalRandomVariable();

	/**
	 * Returns a new object of class '<em>Fitted Exponential Random Variable</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Fitted Exponential Random Variable</em>'.
	 * @generated
	 */
	FittedExponentialRandomVariable createFittedExponentialRandomVariable();

	/**
	 * Returns a new object of class '<em>Fitted Gamma Random Variable</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Fitted Gamma Random Variable</em>'.
	 * @generated
	 */
	FittedGammaRandomVariable createFittedGammaRandomVariable();

	/**
	 * Returns a new object of class '<em>Gamma Random Variable</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Gamma Random Variable</em>'.
	 * @generated
	 */
	GammaRandomVariable createGammaRandomVariable();

	/**
	 * Returns a new object of class '<em>Custom Random Variable</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Custom Random Variable</em>'.
	 * @generated
	 */
	CustomRandomVariable createCustomRandomVariable();

	/**
	 * Returns a new object of class '<em>Expression</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Expression</em>'.
	 * @generated
	 */
	StochasticExpression createStochasticExpression();

	/**
	 * Returns a new object of class '<em>Uniform Random Variable</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Uniform Random Variable</em>'.
	 * @generated
	 */
	UniformRandomVariable createUniformRandomVariable();

	/**
	 * Returns a new object of class '<em>Beta Random Variable</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Beta Random Variable</em>'.
	 * @generated
	 */
	BetaRandomVariable createBetaRandomVariable();

	/**
	 * Returns a new object of class '<em>Log Normal Random Variable</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Log Normal Random Variable</em>'.
	 * @generated
	 */
	LogNormalRandomVariable createLogNormalRandomVariable();

	/**
	 * Returns a new object of class '<em>Pareto Random Variable</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Pareto Random Variable</em>'.
	 * @generated
	 */
	ParetoRandomVariable createParetoRandomVariable();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	StochasticPackage getStochasticPackage();

} //StochasticFactory
