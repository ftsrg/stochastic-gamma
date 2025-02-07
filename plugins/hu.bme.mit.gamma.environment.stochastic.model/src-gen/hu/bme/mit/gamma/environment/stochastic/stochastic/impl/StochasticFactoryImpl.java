/**
 */
package hu.bme.mit.gamma.environment.stochastic.stochastic.impl;

import hu.bme.mit.gamma.environment.stochastic.stochastic.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class StochasticFactoryImpl extends EFactoryImpl implements StochasticFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static StochasticFactory init() {
		try {
			StochasticFactory theStochasticFactory = (StochasticFactory)EPackage.Registry.INSTANCE.getEFactory(StochasticPackage.eNS_URI);
			if (theStochasticFactory != null) {
				return theStochasticFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new StochasticFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StochasticFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case StochasticPackage.NORMAL_RANDOM_VARIABLE: return createNormalRandomVariable();
			case StochasticPackage.EXPONENTIAL_RANDOM_VARIABLE: return createExponentialRandomVariable();
			case StochasticPackage.BERNOULLI_RANDOM_VARIABLE: return createBernoulliRandomVariable();
			case StochasticPackage.GAUSSIAN_PROCESS: return createGaussianProcess();
			case StochasticPackage.LINEAR_KERNEL: return createLinearKernel();
			case StochasticPackage.BROWNIAN_KERNEL: return createBrownianKernel();
			case StochasticPackage.PERIODIC_KERNEL: return createPeriodicKernel();
			case StochasticPackage.RBF_KERNEL: return createRBFKernel();
			case StochasticPackage.SUM_KERNEL: return createSumKernel();
			case StochasticPackage.FITTED_GAUSSIAN_PROCESS: return createFittedGaussianProcess();
			case StochasticPackage.INFLUX_DB: return createInfluxDB();
			case StochasticPackage.DIRAC_PROCESS: return createDiracProcess();
			case StochasticPackage.PYTHON_SIMULATION: return createPythonSimulation();
			case StochasticPackage.CATEGORICAL_PROBABAILITY: return createCategoricalProbabaility();
			case StochasticPackage.HOMOGENEOUS_POISSON_PROCESS: return createHomogeneousPoissonProcess();
			case StochasticPackage.INHOMOGENEOUS_POISSON_PROCESS: return createInhomogeneousPoissonProcess();
			case StochasticPackage.WEIBULL_RANDOM_VARIABLE: return createWeibullRandomVariable();
			case StochasticPackage.MARKOV_PROCESS: return createMarkovProcess();
			case StochasticPackage.PROBABILISTIC_PROGRAM: return createProbabilisticProgram();
			case StochasticPackage.FITTED_NORMAL_RANDOM_VARIABLE: return createFittedNormalRandomVariable();
			case StochasticPackage.FITTED_EXPONENTIAL_RANDOM_VARIABLE: return createFittedExponentialRandomVariable();
			case StochasticPackage.FITTED_GAMMA_RANDOM_VARIABLE: return createFittedGammaRandomVariable();
			case StochasticPackage.GAMMA_RANDOM_VARIABLE: return createGammaRandomVariable();
			case StochasticPackage.CUSTOM_RANDOM_VARIABLE: return createCustomRandomVariable();
			case StochasticPackage.STOCHASTIC_EXPRESSION: return createStochasticExpression();
			case StochasticPackage.UNIFORM_RANDOM_VARIABLE: return createUniformRandomVariable();
			case StochasticPackage.BETA_RANDOM_VARIABLE: return createBetaRandomVariable();
			case StochasticPackage.LOG_NORMAL_RANDOM_VARIABLE: return createLogNormalRandomVariable();
			case StochasticPackage.PARETO_RANDOM_VARIABLE: return createParetoRandomVariable();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NormalRandomVariable createNormalRandomVariable() {
		NormalRandomVariableImpl normalRandomVariable = new NormalRandomVariableImpl();
		return normalRandomVariable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExponentialRandomVariable createExponentialRandomVariable() {
		ExponentialRandomVariableImpl exponentialRandomVariable = new ExponentialRandomVariableImpl();
		return exponentialRandomVariable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BernoulliRandomVariable createBernoulliRandomVariable() {
		BernoulliRandomVariableImpl bernoulliRandomVariable = new BernoulliRandomVariableImpl();
		return bernoulliRandomVariable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GaussianProcess createGaussianProcess() {
		GaussianProcessImpl gaussianProcess = new GaussianProcessImpl();
		return gaussianProcess;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LinearKernel createLinearKernel() {
		LinearKernelImpl linearKernel = new LinearKernelImpl();
		return linearKernel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BrownianKernel createBrownianKernel() {
		BrownianKernelImpl brownianKernel = new BrownianKernelImpl();
		return brownianKernel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PeriodicKernel createPeriodicKernel() {
		PeriodicKernelImpl periodicKernel = new PeriodicKernelImpl();
		return periodicKernel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RBFKernel createRBFKernel() {
		RBFKernelImpl rbfKernel = new RBFKernelImpl();
		return rbfKernel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SumKernel createSumKernel() {
		SumKernelImpl sumKernel = new SumKernelImpl();
		return sumKernel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FittedGaussianProcess createFittedGaussianProcess() {
		FittedGaussianProcessImpl fittedGaussianProcess = new FittedGaussianProcessImpl();
		return fittedGaussianProcess;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InfluxDB createInfluxDB() {
		InfluxDBImpl influxDB = new InfluxDBImpl();
		return influxDB;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DiracProcess createDiracProcess() {
		DiracProcessImpl diracProcess = new DiracProcessImpl();
		return diracProcess;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PythonSimulation createPythonSimulation() {
		PythonSimulationImpl pythonSimulation = new PythonSimulationImpl();
		return pythonSimulation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CategoricalProbabaility createCategoricalProbabaility() {
		CategoricalProbabailityImpl categoricalProbabaility = new CategoricalProbabailityImpl();
		return categoricalProbabaility;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public HomogeneousPoissonProcess createHomogeneousPoissonProcess() {
		HomogeneousPoissonProcessImpl homogeneousPoissonProcess = new HomogeneousPoissonProcessImpl();
		return homogeneousPoissonProcess;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InhomogeneousPoissonProcess createInhomogeneousPoissonProcess() {
		InhomogeneousPoissonProcessImpl inhomogeneousPoissonProcess = new InhomogeneousPoissonProcessImpl();
		return inhomogeneousPoissonProcess;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WeibullRandomVariable createWeibullRandomVariable() {
		WeibullRandomVariableImpl weibullRandomVariable = new WeibullRandomVariableImpl();
		return weibullRandomVariable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MarkovProcess createMarkovProcess() {
		MarkovProcessImpl markovProcess = new MarkovProcessImpl();
		return markovProcess;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProbabilisticProgram createProbabilisticProgram() {
		ProbabilisticProgramImpl probabilisticProgram = new ProbabilisticProgramImpl();
		return probabilisticProgram;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FittedNormalRandomVariable createFittedNormalRandomVariable() {
		FittedNormalRandomVariableImpl fittedNormalRandomVariable = new FittedNormalRandomVariableImpl();
		return fittedNormalRandomVariable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FittedExponentialRandomVariable createFittedExponentialRandomVariable() {
		FittedExponentialRandomVariableImpl fittedExponentialRandomVariable = new FittedExponentialRandomVariableImpl();
		return fittedExponentialRandomVariable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FittedGammaRandomVariable createFittedGammaRandomVariable() {
		FittedGammaRandomVariableImpl fittedGammaRandomVariable = new FittedGammaRandomVariableImpl();
		return fittedGammaRandomVariable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GammaRandomVariable createGammaRandomVariable() {
		GammaRandomVariableImpl gammaRandomVariable = new GammaRandomVariableImpl();
		return gammaRandomVariable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CustomRandomVariable createCustomRandomVariable() {
		CustomRandomVariableImpl customRandomVariable = new CustomRandomVariableImpl();
		return customRandomVariable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StochasticExpression createStochasticExpression() {
		StochasticExpressionImpl stochasticExpression = new StochasticExpressionImpl();
		return stochasticExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UniformRandomVariable createUniformRandomVariable() {
		UniformRandomVariableImpl uniformRandomVariable = new UniformRandomVariableImpl();
		return uniformRandomVariable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BetaRandomVariable createBetaRandomVariable() {
		BetaRandomVariableImpl betaRandomVariable = new BetaRandomVariableImpl();
		return betaRandomVariable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LogNormalRandomVariable createLogNormalRandomVariable() {
		LogNormalRandomVariableImpl logNormalRandomVariable = new LogNormalRandomVariableImpl();
		return logNormalRandomVariable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ParetoRandomVariable createParetoRandomVariable() {
		ParetoRandomVariableImpl paretoRandomVariable = new ParetoRandomVariableImpl();
		return paretoRandomVariable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StochasticPackage getStochasticPackage() {
		return (StochasticPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static StochasticPackage getPackage() {
		return StochasticPackage.eINSTANCE;
	}

} //StochasticFactoryImpl
