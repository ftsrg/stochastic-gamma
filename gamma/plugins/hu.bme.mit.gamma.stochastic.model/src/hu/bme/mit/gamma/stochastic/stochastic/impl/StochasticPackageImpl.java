/**
 */
package hu.bme.mit.gamma.stochastic.stochastic.impl;

import hu.bme.mit.gamma.expression.model.ExpressionModelPackage;
import hu.bme.mit.gamma.stochastic.stochastic.BernoulliRandomVariable;
import hu.bme.mit.gamma.stochastic.stochastic.BetaRandomVariable;
import hu.bme.mit.gamma.stochastic.stochastic.BrownianKernel;
import hu.bme.mit.gamma.stochastic.stochastic.CategoricalProbabaility;
import hu.bme.mit.gamma.stochastic.stochastic.ContinouosRandomVariable;
import hu.bme.mit.gamma.stochastic.stochastic.CustomRandomVariable;
import hu.bme.mit.gamma.stochastic.stochastic.DataSource;
import hu.bme.mit.gamma.stochastic.stochastic.DiracProcess;
import hu.bme.mit.gamma.stochastic.stochastic.DiscreteRandomVariable;
import hu.bme.mit.gamma.stochastic.stochastic.ExponentialRandomVariable;
import hu.bme.mit.gamma.stochastic.stochastic.FittedContinuousRandomVariable;
import hu.bme.mit.gamma.stochastic.stochastic.FittedExponentialRandomVariable;
import hu.bme.mit.gamma.stochastic.stochastic.FittedGammaRandomVariable;
import hu.bme.mit.gamma.stochastic.stochastic.FittedGaussianProcess;
import hu.bme.mit.gamma.stochastic.stochastic.FittedModel;
import hu.bme.mit.gamma.stochastic.stochastic.FittedNormalRandomVariable;
import hu.bme.mit.gamma.stochastic.stochastic.GammaRandomVariable;
import hu.bme.mit.gamma.stochastic.stochastic.GaussianProcess;
import hu.bme.mit.gamma.stochastic.stochastic.HomogeneousPoissonProcess;
import hu.bme.mit.gamma.stochastic.stochastic.InfluxDB;
import hu.bme.mit.gamma.stochastic.stochastic.InhomogeneousPoissonProcess;
import hu.bme.mit.gamma.stochastic.stochastic.Kernel;
import hu.bme.mit.gamma.stochastic.stochastic.LinearKernel;
import hu.bme.mit.gamma.stochastic.stochastic.LogNormalRandomVariable;
import hu.bme.mit.gamma.stochastic.stochastic.MarkovProcess;
import hu.bme.mit.gamma.stochastic.stochastic.NormalRandomVariable;
import hu.bme.mit.gamma.stochastic.stochastic.ParetoRandomVariable;
import hu.bme.mit.gamma.stochastic.stochastic.PeriodicKernel;
import hu.bme.mit.gamma.stochastic.stochastic.PoissonProcess;
import hu.bme.mit.gamma.stochastic.stochastic.ProbabilisticProgram;
import hu.bme.mit.gamma.stochastic.stochastic.PythonSimulation;
import hu.bme.mit.gamma.stochastic.stochastic.RBFKernel;
import hu.bme.mit.gamma.stochastic.stochastic.RandomVariable;
import hu.bme.mit.gamma.stochastic.stochastic.StochasticExpression;
import hu.bme.mit.gamma.stochastic.stochastic.StochasticFactory;
import hu.bme.mit.gamma.stochastic.stochastic.StochasticModel;
import hu.bme.mit.gamma.stochastic.stochastic.StochasticPackage;
import hu.bme.mit.gamma.stochastic.stochastic.StochasticProcess;
import hu.bme.mit.gamma.stochastic.stochastic.SumKernel;
import hu.bme.mit.gamma.stochastic.stochastic.UniformRandomVariable;
import hu.bme.mit.gamma.stochastic.stochastic.WeibullRandomVariable;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class StochasticPackageImpl extends EPackageImpl implements StochasticPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stochasticModelEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass randomVariableEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass continouosRandomVariableEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stochasticProcessEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass poissonProcessEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass normalRandomVariableEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass exponentialRandomVariableEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass discreteRandomVariableEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass bernoulliRandomVariableEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass gaussianProcessEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass kernelEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass linearKernelEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass brownianKernelEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass periodicKernelEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass rbfKernelEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass sumKernelEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass fittedGaussianProcessEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dataSourceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass influxDBEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass diracProcessEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass pythonSimulationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass categoricalProbabailityEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass homogeneousPoissonProcessEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass inhomogeneousPoissonProcessEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass weibullRandomVariableEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass markovProcessEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass probabilisticProgramEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass fittedModelEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass fittedNormalRandomVariableEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass fittedExponentialRandomVariableEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass fittedGammaRandomVariableEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass gammaRandomVariableEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass fittedContinuousRandomVariableEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass customRandomVariableEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stochasticExpressionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass uniformRandomVariableEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass betaRandomVariableEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass logNormalRandomVariableEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass paretoRandomVariableEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see hu.bme.mit.gamma.stochastic.stochastic.StochasticPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private StochasticPackageImpl() {
		super(eNS_URI, StochasticFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 *
	 * <p>This method is used to initialize {@link StochasticPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static StochasticPackage init() {
		if (isInited) return (StochasticPackage)EPackage.Registry.INSTANCE.getEPackage(StochasticPackage.eNS_URI);

		// Obtain or create and register package
		Object registeredStochasticPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
		StochasticPackageImpl theStochasticPackage = registeredStochasticPackage instanceof StochasticPackageImpl ? (StochasticPackageImpl)registeredStochasticPackage : new StochasticPackageImpl();

		isInited = true;

		// Initialize simple dependencies
		ExpressionModelPackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theStochasticPackage.createPackageContents();

		// Initialize created meta-data
		theStochasticPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theStochasticPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(StochasticPackage.eNS_URI, theStochasticPackage);
		return theStochasticPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStochasticModel() {
		return stochasticModelEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRandomVariable() {
		return randomVariableEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRandomVariable_Dimension() {
		return (EAttribute)randomVariableEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getContinouosRandomVariable() {
		return continouosRandomVariableEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStochasticProcess() {
		return stochasticProcessEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPoissonProcess() {
		return poissonProcessEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNormalRandomVariable() {
		return normalRandomVariableEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getNormalRandomVariable_Mean() {
		return (EReference)normalRandomVariableEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getNormalRandomVariable_Scale() {
		return (EReference)normalRandomVariableEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getExponentialRandomVariable() {
		return exponentialRandomVariableEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getExponentialRandomVariable_Rate() {
		return (EReference)exponentialRandomVariableEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDiscreteRandomVariable() {
		return discreteRandomVariableEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBernoulliRandomVariable() {
		return bernoulliRandomVariableEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBernoulliRandomVariable_Probability() {
		return (EReference)bernoulliRandomVariableEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGaussianProcess() {
		return gaussianProcessEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGaussianProcess_Kernel() {
		return (EReference)gaussianProcessEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getKernel() {
		return kernelEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLinearKernel() {
		return linearKernelEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBrownianKernel() {
		return brownianKernelEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPeriodicKernel() {
		return periodicKernelEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRBFKernel() {
		return rbfKernelEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSumKernel() {
		return sumKernelEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSumKernel_Kernels() {
		return (EReference)sumKernelEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFittedGaussianProcess() {
		return fittedGaussianProcessEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDataSource() {
		return dataSourceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getInfluxDB() {
		return influxDBEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getInfluxDB_Dbname() {
		return (EAttribute)influxDBEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getInfluxDB_Ip() {
		return (EAttribute)influxDBEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getInfluxDB_Port() {
		return (EAttribute)influxDBEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getInfluxDB_Query() {
		return (EAttribute)influxDBEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDiracProcess() {
		return diracProcessEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDiracProcess_Source() {
		return (EReference)diracProcessEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPythonSimulation() {
		return pythonSimulationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPythonSimulation_Script() {
		return (EAttribute)pythonSimulationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCategoricalProbabaility() {
		return categoricalProbabailityEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCategoricalProbabaility_Probability() {
		return (EReference)categoricalProbabailityEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getHomogeneousPoissonProcess() {
		return homogeneousPoissonProcessEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getInhomogeneousPoissonProcess() {
		return inhomogeneousPoissonProcessEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getWeibullRandomVariable() {
		return weibullRandomVariableEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWeibullRandomVariable_Scale() {
		return (EReference)weibullRandomVariableEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWeibullRandomVariable_Shape() {
		return (EReference)weibullRandomVariableEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMarkovProcess() {
		return markovProcessEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getProbabilisticProgram() {
		return probabilisticProgramEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFittedModel() {
		return fittedModelEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFittedModel_Source() {
		return (EReference)fittedModelEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFittedModel_Lr() {
		return (EAttribute)fittedModelEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFittedNormalRandomVariable() {
		return fittedNormalRandomVariableEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFittedExponentialRandomVariable() {
		return fittedExponentialRandomVariableEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFittedGammaRandomVariable() {
		return fittedGammaRandomVariableEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGammaRandomVariable() {
		return gammaRandomVariableEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGammaRandomVariable_Scale() {
		return (EReference)gammaRandomVariableEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGammaRandomVariable_Shape() {
		return (EReference)gammaRandomVariableEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFittedContinuousRandomVariable() {
		return fittedContinuousRandomVariableEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCustomRandomVariable() {
		return customRandomVariableEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCustomRandomVariable_Inverse_cdf() {
		return (EReference)customRandomVariableEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStochasticExpression() {
		return stochasticExpressionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStochasticExpression_Randomvariable() {
		return (EReference)stochasticExpressionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUniformRandomVariable() {
		return uniformRandomVariableEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getUniformRandomVariable_LowerBound() {
		return (EReference)uniformRandomVariableEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getUniformRandomVariable_UpperBound() {
		return (EReference)uniformRandomVariableEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBetaRandomVariable() {
		return betaRandomVariableEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBetaRandomVariable_Apha() {
		return (EReference)betaRandomVariableEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBetaRandomVariable_Beta() {
		return (EReference)betaRandomVariableEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLogNormalRandomVariable() {
		return logNormalRandomVariableEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLogNormalRandomVariable_Mean() {
		return (EReference)logNormalRandomVariableEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLogNormalRandomVariable_Scale() {
		return (EReference)logNormalRandomVariableEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getParetoRandomVariable() {
		return paretoRandomVariableEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getParetoRandomVariable_Scale() {
		return (EReference)paretoRandomVariableEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getParetoRandomVariable_Alpha() {
		return (EReference)paretoRandomVariableEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StochasticFactory getStochasticFactory() {
		return (StochasticFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		stochasticModelEClass = createEClass(STOCHASTIC_MODEL);

		randomVariableEClass = createEClass(RANDOM_VARIABLE);
		createEAttribute(randomVariableEClass, RANDOM_VARIABLE__DIMENSION);

		continouosRandomVariableEClass = createEClass(CONTINOUOS_RANDOM_VARIABLE);

		stochasticProcessEClass = createEClass(STOCHASTIC_PROCESS);

		poissonProcessEClass = createEClass(POISSON_PROCESS);

		normalRandomVariableEClass = createEClass(NORMAL_RANDOM_VARIABLE);
		createEReference(normalRandomVariableEClass, NORMAL_RANDOM_VARIABLE__MEAN);
		createEReference(normalRandomVariableEClass, NORMAL_RANDOM_VARIABLE__SCALE);

		exponentialRandomVariableEClass = createEClass(EXPONENTIAL_RANDOM_VARIABLE);
		createEReference(exponentialRandomVariableEClass, EXPONENTIAL_RANDOM_VARIABLE__RATE);

		discreteRandomVariableEClass = createEClass(DISCRETE_RANDOM_VARIABLE);

		bernoulliRandomVariableEClass = createEClass(BERNOULLI_RANDOM_VARIABLE);
		createEReference(bernoulliRandomVariableEClass, BERNOULLI_RANDOM_VARIABLE__PROBABILITY);

		gaussianProcessEClass = createEClass(GAUSSIAN_PROCESS);
		createEReference(gaussianProcessEClass, GAUSSIAN_PROCESS__KERNEL);

		kernelEClass = createEClass(KERNEL);

		linearKernelEClass = createEClass(LINEAR_KERNEL);

		brownianKernelEClass = createEClass(BROWNIAN_KERNEL);

		periodicKernelEClass = createEClass(PERIODIC_KERNEL);

		rbfKernelEClass = createEClass(RBF_KERNEL);

		sumKernelEClass = createEClass(SUM_KERNEL);
		createEReference(sumKernelEClass, SUM_KERNEL__KERNELS);

		fittedGaussianProcessEClass = createEClass(FITTED_GAUSSIAN_PROCESS);

		dataSourceEClass = createEClass(DATA_SOURCE);

		influxDBEClass = createEClass(INFLUX_DB);
		createEAttribute(influxDBEClass, INFLUX_DB__DBNAME);
		createEAttribute(influxDBEClass, INFLUX_DB__IP);
		createEAttribute(influxDBEClass, INFLUX_DB__PORT);
		createEAttribute(influxDBEClass, INFLUX_DB__QUERY);

		diracProcessEClass = createEClass(DIRAC_PROCESS);
		createEReference(diracProcessEClass, DIRAC_PROCESS__SOURCE);

		pythonSimulationEClass = createEClass(PYTHON_SIMULATION);
		createEAttribute(pythonSimulationEClass, PYTHON_SIMULATION__SCRIPT);

		categoricalProbabailityEClass = createEClass(CATEGORICAL_PROBABAILITY);
		createEReference(categoricalProbabailityEClass, CATEGORICAL_PROBABAILITY__PROBABILITY);

		homogeneousPoissonProcessEClass = createEClass(HOMOGENEOUS_POISSON_PROCESS);

		inhomogeneousPoissonProcessEClass = createEClass(INHOMOGENEOUS_POISSON_PROCESS);

		weibullRandomVariableEClass = createEClass(WEIBULL_RANDOM_VARIABLE);
		createEReference(weibullRandomVariableEClass, WEIBULL_RANDOM_VARIABLE__SCALE);
		createEReference(weibullRandomVariableEClass, WEIBULL_RANDOM_VARIABLE__SHAPE);

		markovProcessEClass = createEClass(MARKOV_PROCESS);

		probabilisticProgramEClass = createEClass(PROBABILISTIC_PROGRAM);

		fittedModelEClass = createEClass(FITTED_MODEL);
		createEReference(fittedModelEClass, FITTED_MODEL__SOURCE);
		createEAttribute(fittedModelEClass, FITTED_MODEL__LR);

		fittedNormalRandomVariableEClass = createEClass(FITTED_NORMAL_RANDOM_VARIABLE);

		fittedExponentialRandomVariableEClass = createEClass(FITTED_EXPONENTIAL_RANDOM_VARIABLE);

		fittedGammaRandomVariableEClass = createEClass(FITTED_GAMMA_RANDOM_VARIABLE);

		gammaRandomVariableEClass = createEClass(GAMMA_RANDOM_VARIABLE);
		createEReference(gammaRandomVariableEClass, GAMMA_RANDOM_VARIABLE__SCALE);
		createEReference(gammaRandomVariableEClass, GAMMA_RANDOM_VARIABLE__SHAPE);

		fittedContinuousRandomVariableEClass = createEClass(FITTED_CONTINUOUS_RANDOM_VARIABLE);

		customRandomVariableEClass = createEClass(CUSTOM_RANDOM_VARIABLE);
		createEReference(customRandomVariableEClass, CUSTOM_RANDOM_VARIABLE__INVERSE_CDF);

		stochasticExpressionEClass = createEClass(STOCHASTIC_EXPRESSION);
		createEReference(stochasticExpressionEClass, STOCHASTIC_EXPRESSION__RANDOMVARIABLE);

		uniformRandomVariableEClass = createEClass(UNIFORM_RANDOM_VARIABLE);
		createEReference(uniformRandomVariableEClass, UNIFORM_RANDOM_VARIABLE__LOWER_BOUND);
		createEReference(uniformRandomVariableEClass, UNIFORM_RANDOM_VARIABLE__UPPER_BOUND);

		betaRandomVariableEClass = createEClass(BETA_RANDOM_VARIABLE);
		createEReference(betaRandomVariableEClass, BETA_RANDOM_VARIABLE__APHA);
		createEReference(betaRandomVariableEClass, BETA_RANDOM_VARIABLE__BETA);

		logNormalRandomVariableEClass = createEClass(LOG_NORMAL_RANDOM_VARIABLE);
		createEReference(logNormalRandomVariableEClass, LOG_NORMAL_RANDOM_VARIABLE__MEAN);
		createEReference(logNormalRandomVariableEClass, LOG_NORMAL_RANDOM_VARIABLE__SCALE);

		paretoRandomVariableEClass = createEClass(PARETO_RANDOM_VARIABLE);
		createEReference(paretoRandomVariableEClass, PARETO_RANDOM_VARIABLE__SCALE);
		createEReference(paretoRandomVariableEClass, PARETO_RANDOM_VARIABLE__ALPHA);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		ExpressionModelPackage theExpressionModelPackage = (ExpressionModelPackage)EPackage.Registry.INSTANCE.getEPackage(ExpressionModelPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		randomVariableEClass.getESuperTypes().add(this.getStochasticModel());
		continouosRandomVariableEClass.getESuperTypes().add(this.getRandomVariable());
		stochasticProcessEClass.getESuperTypes().add(this.getStochasticModel());
		poissonProcessEClass.getESuperTypes().add(this.getStochasticProcess());
		normalRandomVariableEClass.getESuperTypes().add(this.getContinouosRandomVariable());
		exponentialRandomVariableEClass.getESuperTypes().add(this.getContinouosRandomVariable());
		discreteRandomVariableEClass.getESuperTypes().add(this.getRandomVariable());
		bernoulliRandomVariableEClass.getESuperTypes().add(this.getDiscreteRandomVariable());
		gaussianProcessEClass.getESuperTypes().add(this.getStochasticProcess());
		linearKernelEClass.getESuperTypes().add(this.getKernel());
		brownianKernelEClass.getESuperTypes().add(this.getKernel());
		periodicKernelEClass.getESuperTypes().add(this.getKernel());
		rbfKernelEClass.getESuperTypes().add(this.getKernel());
		sumKernelEClass.getESuperTypes().add(this.getKernel());
		fittedGaussianProcessEClass.getESuperTypes().add(this.getGaussianProcess());
		fittedGaussianProcessEClass.getESuperTypes().add(this.getFittedModel());
		influxDBEClass.getESuperTypes().add(this.getDataSource());
		diracProcessEClass.getESuperTypes().add(this.getStochasticProcess());
		pythonSimulationEClass.getESuperTypes().add(this.getDataSource());
		categoricalProbabailityEClass.getESuperTypes().add(this.getStochasticModel());
		homogeneousPoissonProcessEClass.getESuperTypes().add(this.getPoissonProcess());
		inhomogeneousPoissonProcessEClass.getESuperTypes().add(this.getPoissonProcess());
		weibullRandomVariableEClass.getESuperTypes().add(this.getContinouosRandomVariable());
		markovProcessEClass.getESuperTypes().add(this.getStochasticProcess());
		probabilisticProgramEClass.getESuperTypes().add(this.getStochasticModel());
		fittedNormalRandomVariableEClass.getESuperTypes().add(this.getFittedContinuousRandomVariable());
		fittedExponentialRandomVariableEClass.getESuperTypes().add(this.getFittedContinuousRandomVariable());
		fittedGammaRandomVariableEClass.getESuperTypes().add(this.getFittedContinuousRandomVariable());
		gammaRandomVariableEClass.getESuperTypes().add(this.getContinouosRandomVariable());
		fittedContinuousRandomVariableEClass.getESuperTypes().add(this.getContinouosRandomVariable());
		fittedContinuousRandomVariableEClass.getESuperTypes().add(this.getFittedModel());
		customRandomVariableEClass.getESuperTypes().add(this.getContinouosRandomVariable());
		stochasticExpressionEClass.getESuperTypes().add(theExpressionModelPackage.getExpression());
		uniformRandomVariableEClass.getESuperTypes().add(this.getContinouosRandomVariable());
		betaRandomVariableEClass.getESuperTypes().add(this.getContinouosRandomVariable());
		logNormalRandomVariableEClass.getESuperTypes().add(this.getContinouosRandomVariable());
		paretoRandomVariableEClass.getESuperTypes().add(this.getContinouosRandomVariable());

		// Initialize classes, features, and operations; add parameters
		initEClass(stochasticModelEClass, StochasticModel.class, "StochasticModel", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(randomVariableEClass, RandomVariable.class, "RandomVariable", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getRandomVariable_Dimension(), ecorePackage.getEInt(), "dimension", "1", 1, 1, RandomVariable.class, !IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(continouosRandomVariableEClass, ContinouosRandomVariable.class, "ContinouosRandomVariable", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(stochasticProcessEClass, StochasticProcess.class, "StochasticProcess", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(poissonProcessEClass, PoissonProcess.class, "PoissonProcess", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(normalRandomVariableEClass, NormalRandomVariable.class, "NormalRandomVariable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getNormalRandomVariable_Mean(), theExpressionModelPackage.getExpression(), null, "mean", null, 1, 1, NormalRandomVariable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getNormalRandomVariable_Scale(), theExpressionModelPackage.getExpression(), null, "scale", null, 1, 1, NormalRandomVariable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(exponentialRandomVariableEClass, ExponentialRandomVariable.class, "ExponentialRandomVariable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getExponentialRandomVariable_Rate(), theExpressionModelPackage.getExpression(), null, "rate", null, 1, 1, ExponentialRandomVariable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(discreteRandomVariableEClass, DiscreteRandomVariable.class, "DiscreteRandomVariable", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(bernoulliRandomVariableEClass, BernoulliRandomVariable.class, "BernoulliRandomVariable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getBernoulliRandomVariable_Probability(), theExpressionModelPackage.getExpression(), null, "probability", null, 1, 1, BernoulliRandomVariable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(gaussianProcessEClass, GaussianProcess.class, "GaussianProcess", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getGaussianProcess_Kernel(), this.getKernel(), null, "kernel", null, 1, 1, GaussianProcess.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(kernelEClass, Kernel.class, "Kernel", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(linearKernelEClass, LinearKernel.class, "LinearKernel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(brownianKernelEClass, BrownianKernel.class, "BrownianKernel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(periodicKernelEClass, PeriodicKernel.class, "PeriodicKernel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(rbfKernelEClass, RBFKernel.class, "RBFKernel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(sumKernelEClass, SumKernel.class, "SumKernel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSumKernel_Kernels(), this.getKernel(), null, "kernels", null, 2, 2, SumKernel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(fittedGaussianProcessEClass, FittedGaussianProcess.class, "FittedGaussianProcess", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(dataSourceEClass, DataSource.class, "DataSource", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(influxDBEClass, InfluxDB.class, "InfluxDB", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getInfluxDB_Dbname(), ecorePackage.getEString(), "dbname", null, 1, 1, InfluxDB.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getInfluxDB_Ip(), ecorePackage.getEString(), "ip", "\"localhost\"", 1, 1, InfluxDB.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getInfluxDB_Port(), ecorePackage.getEString(), "port", null, 1, 1, InfluxDB.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getInfluxDB_Query(), ecorePackage.getEString(), "query", null, 1, 1, InfluxDB.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(diracProcessEClass, DiracProcess.class, "DiracProcess", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDiracProcess_Source(), this.getDataSource(), null, "source", null, 1, 1, DiracProcess.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(pythonSimulationEClass, PythonSimulation.class, "PythonSimulation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPythonSimulation_Script(), ecorePackage.getEString(), "script", null, 1, 1, PythonSimulation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(categoricalProbabailityEClass, CategoricalProbabaility.class, "CategoricalProbabaility", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCategoricalProbabaility_Probability(), theExpressionModelPackage.getExpression(), null, "probability", null, 1, 1, CategoricalProbabaility.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(homogeneousPoissonProcessEClass, HomogeneousPoissonProcess.class, "HomogeneousPoissonProcess", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(inhomogeneousPoissonProcessEClass, InhomogeneousPoissonProcess.class, "InhomogeneousPoissonProcess", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(weibullRandomVariableEClass, WeibullRandomVariable.class, "WeibullRandomVariable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getWeibullRandomVariable_Scale(), theExpressionModelPackage.getExpression(), null, "scale", null, 1, 1, WeibullRandomVariable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getWeibullRandomVariable_Shape(), theExpressionModelPackage.getExpression(), null, "shape", null, 1, 1, WeibullRandomVariable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(markovProcessEClass, MarkovProcess.class, "MarkovProcess", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(probabilisticProgramEClass, ProbabilisticProgram.class, "ProbabilisticProgram", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(fittedModelEClass, FittedModel.class, "FittedModel", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getFittedModel_Source(), this.getDataSource(), null, "source", null, 1, 1, FittedModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFittedModel_Lr(), ecorePackage.getEDouble(), "lr", "0.05", 1, 1, FittedModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(fittedNormalRandomVariableEClass, FittedNormalRandomVariable.class, "FittedNormalRandomVariable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(fittedExponentialRandomVariableEClass, FittedExponentialRandomVariable.class, "FittedExponentialRandomVariable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(fittedGammaRandomVariableEClass, FittedGammaRandomVariable.class, "FittedGammaRandomVariable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(gammaRandomVariableEClass, GammaRandomVariable.class, "GammaRandomVariable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getGammaRandomVariable_Scale(), theExpressionModelPackage.getExpression(), null, "scale", null, 1, 1, GammaRandomVariable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getGammaRandomVariable_Shape(), theExpressionModelPackage.getExpression(), null, "shape", null, 1, 1, GammaRandomVariable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(fittedContinuousRandomVariableEClass, FittedContinuousRandomVariable.class, "FittedContinuousRandomVariable", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(customRandomVariableEClass, CustomRandomVariable.class, "CustomRandomVariable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCustomRandomVariable_Inverse_cdf(), theExpressionModelPackage.getFunctionDeclaration(), null, "inverse_cdf", null, 1, 1, CustomRandomVariable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(stochasticExpressionEClass, StochasticExpression.class, "StochasticExpression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getStochasticExpression_Randomvariable(), this.getRandomVariable(), null, "randomvariable", null, 1, 1, StochasticExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(uniformRandomVariableEClass, UniformRandomVariable.class, "UniformRandomVariable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getUniformRandomVariable_LowerBound(), theExpressionModelPackage.getExpression(), null, "lowerBound", null, 1, 1, UniformRandomVariable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getUniformRandomVariable_UpperBound(), theExpressionModelPackage.getExpression(), null, "upperBound", null, 1, 1, UniformRandomVariable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(betaRandomVariableEClass, BetaRandomVariable.class, "BetaRandomVariable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getBetaRandomVariable_Apha(), theExpressionModelPackage.getExpression(), null, "apha", null, 1, 1, BetaRandomVariable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getBetaRandomVariable_Beta(), theExpressionModelPackage.getExpression(), null, "beta", null, 1, 1, BetaRandomVariable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(logNormalRandomVariableEClass, LogNormalRandomVariable.class, "LogNormalRandomVariable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getLogNormalRandomVariable_Mean(), theExpressionModelPackage.getExpression(), null, "mean", null, 1, 1, LogNormalRandomVariable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getLogNormalRandomVariable_Scale(), theExpressionModelPackage.getExpression(), null, "scale", null, 1, 1, LogNormalRandomVariable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(paretoRandomVariableEClass, ParetoRandomVariable.class, "ParetoRandomVariable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getParetoRandomVariable_Scale(), theExpressionModelPackage.getExpression(), null, "scale", null, 1, 1, ParetoRandomVariable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getParetoRandomVariable_Alpha(), theExpressionModelPackage.getExpression(), null, "alpha", null, 1, 1, ParetoRandomVariable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

} //StochasticPackageImpl
