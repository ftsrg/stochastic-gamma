/**
 */
package hu.bme.mit.gamma.stochastic.stochastic.util;

import hu.bme.mit.gamma.expression.model.Expression;
import hu.bme.mit.gamma.stochastic.stochastic.*;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see hu.bme.mit.gamma.stochastic.stochastic.StochasticPackage
 * @generated
 */
public class StochasticAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static StochasticPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StochasticAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = StochasticPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StochasticSwitch<Adapter> modelSwitch =
		new StochasticSwitch<Adapter>() {
			@Override
			public Adapter caseStochasticModel(StochasticModel object) {
				return createStochasticModelAdapter();
			}
			@Override
			public Adapter caseRandomVariable(RandomVariable object) {
				return createRandomVariableAdapter();
			}
			@Override
			public Adapter caseContinouosRandomVariable(ContinouosRandomVariable object) {
				return createContinouosRandomVariableAdapter();
			}
			@Override
			public Adapter caseStochasticProcess(StochasticProcess object) {
				return createStochasticProcessAdapter();
			}
			@Override
			public Adapter casePoissonProcess(PoissonProcess object) {
				return createPoissonProcessAdapter();
			}
			@Override
			public Adapter caseNormalRandomVariable(NormalRandomVariable object) {
				return createNormalRandomVariableAdapter();
			}
			@Override
			public Adapter caseExponentialRandomVariable(ExponentialRandomVariable object) {
				return createExponentialRandomVariableAdapter();
			}
			@Override
			public Adapter caseDiscreteRandomVariable(DiscreteRandomVariable object) {
				return createDiscreteRandomVariableAdapter();
			}
			@Override
			public Adapter caseBernoulliRandomVariable(BernoulliRandomVariable object) {
				return createBernoulliRandomVariableAdapter();
			}
			@Override
			public Adapter caseGaussianProcess(GaussianProcess object) {
				return createGaussianProcessAdapter();
			}
			@Override
			public Adapter caseKernel(Kernel object) {
				return createKernelAdapter();
			}
			@Override
			public Adapter caseLinearKernel(LinearKernel object) {
				return createLinearKernelAdapter();
			}
			@Override
			public Adapter caseBrownianKernel(BrownianKernel object) {
				return createBrownianKernelAdapter();
			}
			@Override
			public Adapter casePeriodicKernel(PeriodicKernel object) {
				return createPeriodicKernelAdapter();
			}
			@Override
			public Adapter caseRBFKernel(RBFKernel object) {
				return createRBFKernelAdapter();
			}
			@Override
			public Adapter caseSumKernel(SumKernel object) {
				return createSumKernelAdapter();
			}
			@Override
			public Adapter caseFittedGaussianProcess(FittedGaussianProcess object) {
				return createFittedGaussianProcessAdapter();
			}
			@Override
			public Adapter caseDataSource(DataSource object) {
				return createDataSourceAdapter();
			}
			@Override
			public Adapter caseInfluxDB(InfluxDB object) {
				return createInfluxDBAdapter();
			}
			@Override
			public Adapter caseDiracProcess(DiracProcess object) {
				return createDiracProcessAdapter();
			}
			@Override
			public Adapter casePythonSimulation(PythonSimulation object) {
				return createPythonSimulationAdapter();
			}
			@Override
			public Adapter caseCategoricalProbabaility(CategoricalProbabaility object) {
				return createCategoricalProbabailityAdapter();
			}
			@Override
			public Adapter caseHomogeneousPoissonProcess(HomogeneousPoissonProcess object) {
				return createHomogeneousPoissonProcessAdapter();
			}
			@Override
			public Adapter caseInhomogeneousPoissonProcess(InhomogeneousPoissonProcess object) {
				return createInhomogeneousPoissonProcessAdapter();
			}
			@Override
			public Adapter caseWeibullRandomVariable(WeibullRandomVariable object) {
				return createWeibullRandomVariableAdapter();
			}
			@Override
			public Adapter caseMarkovProcess(MarkovProcess object) {
				return createMarkovProcessAdapter();
			}
			@Override
			public Adapter caseProbabilisticProgram(ProbabilisticProgram object) {
				return createProbabilisticProgramAdapter();
			}
			@Override
			public Adapter caseFittedModel(FittedModel object) {
				return createFittedModelAdapter();
			}
			@Override
			public Adapter caseFittedNormalRandomVariable(FittedNormalRandomVariable object) {
				return createFittedNormalRandomVariableAdapter();
			}
			@Override
			public Adapter caseFittedExponentialRandomVariable(FittedExponentialRandomVariable object) {
				return createFittedExponentialRandomVariableAdapter();
			}
			@Override
			public Adapter caseFittedGammaRandomVariable(FittedGammaRandomVariable object) {
				return createFittedGammaRandomVariableAdapter();
			}
			@Override
			public Adapter caseGammaRandomVariable(GammaRandomVariable object) {
				return createGammaRandomVariableAdapter();
			}
			@Override
			public Adapter caseFittedContinuousRandomVariable(FittedContinuousRandomVariable object) {
				return createFittedContinuousRandomVariableAdapter();
			}
			@Override
			public Adapter caseCustomRandomVariable(CustomRandomVariable object) {
				return createCustomRandomVariableAdapter();
			}
			@Override
			public Adapter caseStochasticExpression(StochasticExpression object) {
				return createStochasticExpressionAdapter();
			}
			@Override
			public Adapter caseUniformRandomVariable(UniformRandomVariable object) {
				return createUniformRandomVariableAdapter();
			}
			@Override
			public Adapter caseBetaRandomVariable(BetaRandomVariable object) {
				return createBetaRandomVariableAdapter();
			}
			@Override
			public Adapter caseLogNormalRandomVariable(LogNormalRandomVariable object) {
				return createLogNormalRandomVariableAdapter();
			}
			@Override
			public Adapter caseParetoRandomVariable(ParetoRandomVariable object) {
				return createParetoRandomVariableAdapter();
			}
			@Override
			public Adapter caseExpression(Expression object) {
				return createExpressionAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.stochastic.stochastic.StochasticModel <em>Model</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.stochastic.stochastic.StochasticModel
	 * @generated
	 */
	public Adapter createStochasticModelAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.stochastic.stochastic.RandomVariable <em>Random Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.stochastic.stochastic.RandomVariable
	 * @generated
	 */
	public Adapter createRandomVariableAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.stochastic.stochastic.ContinouosRandomVariable <em>Continouos Random Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.stochastic.stochastic.ContinouosRandomVariable
	 * @generated
	 */
	public Adapter createContinouosRandomVariableAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.stochastic.stochastic.StochasticProcess <em>Process</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.stochastic.stochastic.StochasticProcess
	 * @generated
	 */
	public Adapter createStochasticProcessAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.stochastic.stochastic.PoissonProcess <em>Poisson Process</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.stochastic.stochastic.PoissonProcess
	 * @generated
	 */
	public Adapter createPoissonProcessAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.stochastic.stochastic.NormalRandomVariable <em>Normal Random Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.stochastic.stochastic.NormalRandomVariable
	 * @generated
	 */
	public Adapter createNormalRandomVariableAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.stochastic.stochastic.ExponentialRandomVariable <em>Exponential Random Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.stochastic.stochastic.ExponentialRandomVariable
	 * @generated
	 */
	public Adapter createExponentialRandomVariableAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.stochastic.stochastic.DiscreteRandomVariable <em>Discrete Random Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.stochastic.stochastic.DiscreteRandomVariable
	 * @generated
	 */
	public Adapter createDiscreteRandomVariableAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.stochastic.stochastic.BernoulliRandomVariable <em>Bernoulli Random Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.stochastic.stochastic.BernoulliRandomVariable
	 * @generated
	 */
	public Adapter createBernoulliRandomVariableAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.stochastic.stochastic.GaussianProcess <em>Gaussian Process</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.stochastic.stochastic.GaussianProcess
	 * @generated
	 */
	public Adapter createGaussianProcessAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.stochastic.stochastic.Kernel <em>Kernel</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.stochastic.stochastic.Kernel
	 * @generated
	 */
	public Adapter createKernelAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.stochastic.stochastic.LinearKernel <em>Linear Kernel</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.stochastic.stochastic.LinearKernel
	 * @generated
	 */
	public Adapter createLinearKernelAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.stochastic.stochastic.BrownianKernel <em>Brownian Kernel</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.stochastic.stochastic.BrownianKernel
	 * @generated
	 */
	public Adapter createBrownianKernelAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.stochastic.stochastic.PeriodicKernel <em>Periodic Kernel</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.stochastic.stochastic.PeriodicKernel
	 * @generated
	 */
	public Adapter createPeriodicKernelAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.stochastic.stochastic.RBFKernel <em>RBF Kernel</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.stochastic.stochastic.RBFKernel
	 * @generated
	 */
	public Adapter createRBFKernelAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.stochastic.stochastic.SumKernel <em>Sum Kernel</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.stochastic.stochastic.SumKernel
	 * @generated
	 */
	public Adapter createSumKernelAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.stochastic.stochastic.FittedGaussianProcess <em>Fitted Gaussian Process</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.stochastic.stochastic.FittedGaussianProcess
	 * @generated
	 */
	public Adapter createFittedGaussianProcessAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.stochastic.stochastic.DataSource <em>Data Source</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.stochastic.stochastic.DataSource
	 * @generated
	 */
	public Adapter createDataSourceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.stochastic.stochastic.InfluxDB <em>Influx DB</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.stochastic.stochastic.InfluxDB
	 * @generated
	 */
	public Adapter createInfluxDBAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.stochastic.stochastic.DiracProcess <em>Dirac Process</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.stochastic.stochastic.DiracProcess
	 * @generated
	 */
	public Adapter createDiracProcessAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.stochastic.stochastic.PythonSimulation <em>Python Simulation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.stochastic.stochastic.PythonSimulation
	 * @generated
	 */
	public Adapter createPythonSimulationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.stochastic.stochastic.CategoricalProbabaility <em>Categorical Probabaility</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.stochastic.stochastic.CategoricalProbabaility
	 * @generated
	 */
	public Adapter createCategoricalProbabailityAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.stochastic.stochastic.HomogeneousPoissonProcess <em>Homogeneous Poisson Process</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.stochastic.stochastic.HomogeneousPoissonProcess
	 * @generated
	 */
	public Adapter createHomogeneousPoissonProcessAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.stochastic.stochastic.InhomogeneousPoissonProcess <em>Inhomogeneous Poisson Process</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.stochastic.stochastic.InhomogeneousPoissonProcess
	 * @generated
	 */
	public Adapter createInhomogeneousPoissonProcessAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.stochastic.stochastic.WeibullRandomVariable <em>Weibull Random Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.stochastic.stochastic.WeibullRandomVariable
	 * @generated
	 */
	public Adapter createWeibullRandomVariableAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.stochastic.stochastic.MarkovProcess <em>Markov Process</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.stochastic.stochastic.MarkovProcess
	 * @generated
	 */
	public Adapter createMarkovProcessAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.stochastic.stochastic.ProbabilisticProgram <em>Probabilistic Program</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.stochastic.stochastic.ProbabilisticProgram
	 * @generated
	 */
	public Adapter createProbabilisticProgramAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.stochastic.stochastic.FittedModel <em>Fitted Model</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.stochastic.stochastic.FittedModel
	 * @generated
	 */
	public Adapter createFittedModelAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.stochastic.stochastic.FittedNormalRandomVariable <em>Fitted Normal Random Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.stochastic.stochastic.FittedNormalRandomVariable
	 * @generated
	 */
	public Adapter createFittedNormalRandomVariableAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.stochastic.stochastic.FittedExponentialRandomVariable <em>Fitted Exponential Random Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.stochastic.stochastic.FittedExponentialRandomVariable
	 * @generated
	 */
	public Adapter createFittedExponentialRandomVariableAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.stochastic.stochastic.FittedGammaRandomVariable <em>Fitted Gamma Random Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.stochastic.stochastic.FittedGammaRandomVariable
	 * @generated
	 */
	public Adapter createFittedGammaRandomVariableAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.stochastic.stochastic.GammaRandomVariable <em>Gamma Random Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.stochastic.stochastic.GammaRandomVariable
	 * @generated
	 */
	public Adapter createGammaRandomVariableAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.stochastic.stochastic.FittedContinuousRandomVariable <em>Fitted Continuous Random Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.stochastic.stochastic.FittedContinuousRandomVariable
	 * @generated
	 */
	public Adapter createFittedContinuousRandomVariableAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.stochastic.stochastic.CustomRandomVariable <em>Custom Random Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.stochastic.stochastic.CustomRandomVariable
	 * @generated
	 */
	public Adapter createCustomRandomVariableAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.stochastic.stochastic.StochasticExpression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.stochastic.stochastic.StochasticExpression
	 * @generated
	 */
	public Adapter createStochasticExpressionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.stochastic.stochastic.UniformRandomVariable <em>Uniform Random Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.stochastic.stochastic.UniformRandomVariable
	 * @generated
	 */
	public Adapter createUniformRandomVariableAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.stochastic.stochastic.BetaRandomVariable <em>Beta Random Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.stochastic.stochastic.BetaRandomVariable
	 * @generated
	 */
	public Adapter createBetaRandomVariableAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.stochastic.stochastic.LogNormalRandomVariable <em>Log Normal Random Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.stochastic.stochastic.LogNormalRandomVariable
	 * @generated
	 */
	public Adapter createLogNormalRandomVariableAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.stochastic.stochastic.ParetoRandomVariable <em>Pareto Random Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.stochastic.stochastic.ParetoRandomVariable
	 * @generated
	 */
	public Adapter createParetoRandomVariableAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.expression.model.Expression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.expression.model.Expression
	 * @generated
	 */
	public Adapter createExpressionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //StochasticAdapterFactory
