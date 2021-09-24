/**
 */
package hu.bme.mit.gamma.stochastic.stochastic.util;

import hu.bme.mit.gamma.stochastic.stochastic.*;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see hu.bme.mit.gamma.stochastic.stochastic.StochasticPackage
 * @generated
 */
public class StochasticSwitch<T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static StochasticPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StochasticSwitch() {
		if (modelPackage == null) {
			modelPackage = StochasticPackage.eINSTANCE;
		}
	}

	/**
	 * Checks whether this is a switch for the given package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param ePackage the package in question.
	 * @return whether this is a switch for the given package.
	 * @generated
	 */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
		return ePackage == modelPackage;
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	@Override
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case StochasticPackage.STOCHASTIC_MODEL: {
				StochasticModel stochasticModel = (StochasticModel)theEObject;
				T result = caseStochasticModel(stochasticModel);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case StochasticPackage.RANDOM_VARIABLE: {
				RandomVariable randomVariable = (RandomVariable)theEObject;
				T result = caseRandomVariable(randomVariable);
				if (result == null) result = caseStochasticModel(randomVariable);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case StochasticPackage.CONTINOUOS_RANDOM_VARIABLE: {
				ContinouosRandomVariable continouosRandomVariable = (ContinouosRandomVariable)theEObject;
				T result = caseContinouosRandomVariable(continouosRandomVariable);
				if (result == null) result = caseRandomVariable(continouosRandomVariable);
				if (result == null) result = caseStochasticModel(continouosRandomVariable);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case StochasticPackage.STOCHASTIC_PROCESS: {
				StochasticProcess stochasticProcess = (StochasticProcess)theEObject;
				T result = caseStochasticProcess(stochasticProcess);
				if (result == null) result = caseStochasticModel(stochasticProcess);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case StochasticPackage.POISSON_PROCESS: {
				PoissonProcess poissonProcess = (PoissonProcess)theEObject;
				T result = casePoissonProcess(poissonProcess);
				if (result == null) result = caseStochasticProcess(poissonProcess);
				if (result == null) result = caseStochasticModel(poissonProcess);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case StochasticPackage.NORMAL_RANDOM_VARIABLE: {
				NormalRandomVariable normalRandomVariable = (NormalRandomVariable)theEObject;
				T result = caseNormalRandomVariable(normalRandomVariable);
				if (result == null) result = caseContinouosRandomVariable(normalRandomVariable);
				if (result == null) result = caseRandomVariable(normalRandomVariable);
				if (result == null) result = caseStochasticModel(normalRandomVariable);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case StochasticPackage.EXPONENTIAL_RANDOM_VARIABLE: {
				ExponentialRandomVariable exponentialRandomVariable = (ExponentialRandomVariable)theEObject;
				T result = caseExponentialRandomVariable(exponentialRandomVariable);
				if (result == null) result = caseContinouosRandomVariable(exponentialRandomVariable);
				if (result == null) result = caseRandomVariable(exponentialRandomVariable);
				if (result == null) result = caseStochasticModel(exponentialRandomVariable);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case StochasticPackage.DISCRETE_RANDOM_VARIABLE: {
				DiscreteRandomVariable discreteRandomVariable = (DiscreteRandomVariable)theEObject;
				T result = caseDiscreteRandomVariable(discreteRandomVariable);
				if (result == null) result = caseRandomVariable(discreteRandomVariable);
				if (result == null) result = caseStochasticModel(discreteRandomVariable);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case StochasticPackage.BERNOULLI_RANDOM_VARIABLE: {
				BernoulliRandomVariable bernoulliRandomVariable = (BernoulliRandomVariable)theEObject;
				T result = caseBernoulliRandomVariable(bernoulliRandomVariable);
				if (result == null) result = caseDiscreteRandomVariable(bernoulliRandomVariable);
				if (result == null) result = caseRandomVariable(bernoulliRandomVariable);
				if (result == null) result = caseStochasticModel(bernoulliRandomVariable);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case StochasticPackage.GAUSSIAN_PROCESS: {
				GaussianProcess gaussianProcess = (GaussianProcess)theEObject;
				T result = caseGaussianProcess(gaussianProcess);
				if (result == null) result = caseStochasticProcess(gaussianProcess);
				if (result == null) result = caseStochasticModel(gaussianProcess);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case StochasticPackage.KERNEL: {
				Kernel kernel = (Kernel)theEObject;
				T result = caseKernel(kernel);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case StochasticPackage.LINEAR_KERNEL: {
				LinearKernel linearKernel = (LinearKernel)theEObject;
				T result = caseLinearKernel(linearKernel);
				if (result == null) result = caseKernel(linearKernel);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case StochasticPackage.BROWNIAN_KERNEL: {
				BrownianKernel brownianKernel = (BrownianKernel)theEObject;
				T result = caseBrownianKernel(brownianKernel);
				if (result == null) result = caseKernel(brownianKernel);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case StochasticPackage.PERIODIC_KERNEL: {
				PeriodicKernel periodicKernel = (PeriodicKernel)theEObject;
				T result = casePeriodicKernel(periodicKernel);
				if (result == null) result = caseKernel(periodicKernel);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case StochasticPackage.RBF_KERNEL: {
				RBFKernel rbfKernel = (RBFKernel)theEObject;
				T result = caseRBFKernel(rbfKernel);
				if (result == null) result = caseKernel(rbfKernel);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case StochasticPackage.SUM_KERNEL: {
				SumKernel sumKernel = (SumKernel)theEObject;
				T result = caseSumKernel(sumKernel);
				if (result == null) result = caseKernel(sumKernel);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case StochasticPackage.FITTED_GAUSSIAN_PROCESS: {
				FittedGaussianProcess fittedGaussianProcess = (FittedGaussianProcess)theEObject;
				T result = caseFittedGaussianProcess(fittedGaussianProcess);
				if (result == null) result = caseGaussianProcess(fittedGaussianProcess);
				if (result == null) result = caseFittedModel(fittedGaussianProcess);
				if (result == null) result = caseStochasticProcess(fittedGaussianProcess);
				if (result == null) result = caseStochasticModel(fittedGaussianProcess);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case StochasticPackage.DATA_SOURCE: {
				DataSource dataSource = (DataSource)theEObject;
				T result = caseDataSource(dataSource);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case StochasticPackage.INFLUX_DB: {
				InfluxDB influxDB = (InfluxDB)theEObject;
				T result = caseInfluxDB(influxDB);
				if (result == null) result = caseDataSource(influxDB);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case StochasticPackage.DIRAC_PROCESS: {
				DiracProcess diracProcess = (DiracProcess)theEObject;
				T result = caseDiracProcess(diracProcess);
				if (result == null) result = caseStochasticProcess(diracProcess);
				if (result == null) result = caseStochasticModel(diracProcess);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case StochasticPackage.PYTHON_SIMULATION: {
				PythonSimulation pythonSimulation = (PythonSimulation)theEObject;
				T result = casePythonSimulation(pythonSimulation);
				if (result == null) result = caseDataSource(pythonSimulation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case StochasticPackage.CATEGORICAL_PROBABAILITY: {
				CategoricalProbabaility categoricalProbabaility = (CategoricalProbabaility)theEObject;
				T result = caseCategoricalProbabaility(categoricalProbabaility);
				if (result == null) result = caseStochasticModel(categoricalProbabaility);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case StochasticPackage.HOMOGENEOUS_POISSON_PROCESS: {
				HomogeneousPoissonProcess homogeneousPoissonProcess = (HomogeneousPoissonProcess)theEObject;
				T result = caseHomogeneousPoissonProcess(homogeneousPoissonProcess);
				if (result == null) result = casePoissonProcess(homogeneousPoissonProcess);
				if (result == null) result = caseStochasticProcess(homogeneousPoissonProcess);
				if (result == null) result = caseStochasticModel(homogeneousPoissonProcess);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case StochasticPackage.INHOMOGENEOUS_POISSON_PROCESS: {
				InhomogeneousPoissonProcess inhomogeneousPoissonProcess = (InhomogeneousPoissonProcess)theEObject;
				T result = caseInhomogeneousPoissonProcess(inhomogeneousPoissonProcess);
				if (result == null) result = casePoissonProcess(inhomogeneousPoissonProcess);
				if (result == null) result = caseStochasticProcess(inhomogeneousPoissonProcess);
				if (result == null) result = caseStochasticModel(inhomogeneousPoissonProcess);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case StochasticPackage.WEIBULL_RANDOM_VARIABLE: {
				WeibullRandomVariable weibullRandomVariable = (WeibullRandomVariable)theEObject;
				T result = caseWeibullRandomVariable(weibullRandomVariable);
				if (result == null) result = caseContinouosRandomVariable(weibullRandomVariable);
				if (result == null) result = caseRandomVariable(weibullRandomVariable);
				if (result == null) result = caseStochasticModel(weibullRandomVariable);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case StochasticPackage.MARKOV_PROCESS: {
				MarkovProcess markovProcess = (MarkovProcess)theEObject;
				T result = caseMarkovProcess(markovProcess);
				if (result == null) result = caseStochasticProcess(markovProcess);
				if (result == null) result = caseStochasticModel(markovProcess);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case StochasticPackage.PROBABILISTIC_PROGRAM: {
				ProbabilisticProgram probabilisticProgram = (ProbabilisticProgram)theEObject;
				T result = caseProbabilisticProgram(probabilisticProgram);
				if (result == null) result = caseStochasticModel(probabilisticProgram);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case StochasticPackage.FITTED_MODEL: {
				FittedModel fittedModel = (FittedModel)theEObject;
				T result = caseFittedModel(fittedModel);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case StochasticPackage.FITTED_NORMAL_RANDOM_VARIABLE: {
				FittedNormalRandomVariable fittedNormalRandomVariable = (FittedNormalRandomVariable)theEObject;
				T result = caseFittedNormalRandomVariable(fittedNormalRandomVariable);
				if (result == null) result = caseFittedContinuousRandomVariable(fittedNormalRandomVariable);
				if (result == null) result = caseContinouosRandomVariable(fittedNormalRandomVariable);
				if (result == null) result = caseFittedModel(fittedNormalRandomVariable);
				if (result == null) result = caseRandomVariable(fittedNormalRandomVariable);
				if (result == null) result = caseStochasticModel(fittedNormalRandomVariable);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case StochasticPackage.FITTED_EXPONENTIAL_RANDOM_VARIABLE: {
				FittedExponentialRandomVariable fittedExponentialRandomVariable = (FittedExponentialRandomVariable)theEObject;
				T result = caseFittedExponentialRandomVariable(fittedExponentialRandomVariable);
				if (result == null) result = caseFittedContinuousRandomVariable(fittedExponentialRandomVariable);
				if (result == null) result = caseContinouosRandomVariable(fittedExponentialRandomVariable);
				if (result == null) result = caseFittedModel(fittedExponentialRandomVariable);
				if (result == null) result = caseRandomVariable(fittedExponentialRandomVariable);
				if (result == null) result = caseStochasticModel(fittedExponentialRandomVariable);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case StochasticPackage.FITTED_GAMMA_RANDOM_VARIABLE: {
				FittedGammaRandomVariable fittedGammaRandomVariable = (FittedGammaRandomVariable)theEObject;
				T result = caseFittedGammaRandomVariable(fittedGammaRandomVariable);
				if (result == null) result = caseFittedContinuousRandomVariable(fittedGammaRandomVariable);
				if (result == null) result = caseContinouosRandomVariable(fittedGammaRandomVariable);
				if (result == null) result = caseFittedModel(fittedGammaRandomVariable);
				if (result == null) result = caseRandomVariable(fittedGammaRandomVariable);
				if (result == null) result = caseStochasticModel(fittedGammaRandomVariable);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case StochasticPackage.GAMMA_RANDOM_VARIABLE: {
				GammaRandomVariable gammaRandomVariable = (GammaRandomVariable)theEObject;
				T result = caseGammaRandomVariable(gammaRandomVariable);
				if (result == null) result = caseContinouosRandomVariable(gammaRandomVariable);
				if (result == null) result = caseRandomVariable(gammaRandomVariable);
				if (result == null) result = caseStochasticModel(gammaRandomVariable);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case StochasticPackage.FITTED_CONTINUOUS_RANDOM_VARIABLE: {
				FittedContinuousRandomVariable fittedContinuousRandomVariable = (FittedContinuousRandomVariable)theEObject;
				T result = caseFittedContinuousRandomVariable(fittedContinuousRandomVariable);
				if (result == null) result = caseContinouosRandomVariable(fittedContinuousRandomVariable);
				if (result == null) result = caseFittedModel(fittedContinuousRandomVariable);
				if (result == null) result = caseRandomVariable(fittedContinuousRandomVariable);
				if (result == null) result = caseStochasticModel(fittedContinuousRandomVariable);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Model</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Model</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStochasticModel(StochasticModel object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Random Variable</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Random Variable</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRandomVariable(RandomVariable object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Continouos Random Variable</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Continouos Random Variable</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseContinouosRandomVariable(ContinouosRandomVariable object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Process</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Process</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStochasticProcess(StochasticProcess object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Poisson Process</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Poisson Process</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePoissonProcess(PoissonProcess object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Normal Random Variable</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Normal Random Variable</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNormalRandomVariable(NormalRandomVariable object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Exponential Random Variable</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Exponential Random Variable</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseExponentialRandomVariable(ExponentialRandomVariable object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Discrete Random Variable</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Discrete Random Variable</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDiscreteRandomVariable(DiscreteRandomVariable object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Bernoulli Random Variable</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Bernoulli Random Variable</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBernoulliRandomVariable(BernoulliRandomVariable object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Gaussian Process</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Gaussian Process</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseGaussianProcess(GaussianProcess object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Kernel</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Kernel</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseKernel(Kernel object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Linear Kernel</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Linear Kernel</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLinearKernel(LinearKernel object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Brownian Kernel</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Brownian Kernel</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBrownianKernel(BrownianKernel object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Periodic Kernel</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Periodic Kernel</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePeriodicKernel(PeriodicKernel object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>RBF Kernel</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>RBF Kernel</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRBFKernel(RBFKernel object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Sum Kernel</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Sum Kernel</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSumKernel(SumKernel object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Fitted Gaussian Process</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Fitted Gaussian Process</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFittedGaussianProcess(FittedGaussianProcess object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Data Source</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Data Source</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDataSource(DataSource object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Influx DB</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Influx DB</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseInfluxDB(InfluxDB object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Dirac Process</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Dirac Process</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDiracProcess(DiracProcess object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Python Simulation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Python Simulation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePythonSimulation(PythonSimulation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Categorical Probabaility</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Categorical Probabaility</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCategoricalProbabaility(CategoricalProbabaility object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Homogeneous Poisson Process</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Homogeneous Poisson Process</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseHomogeneousPoissonProcess(HomogeneousPoissonProcess object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Inhomogeneous Poisson Process</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Inhomogeneous Poisson Process</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseInhomogeneousPoissonProcess(InhomogeneousPoissonProcess object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Weibull Random Variable</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Weibull Random Variable</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseWeibullRandomVariable(WeibullRandomVariable object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Markov Process</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Markov Process</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMarkovProcess(MarkovProcess object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Probabilistic Program</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Probabilistic Program</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseProbabilisticProgram(ProbabilisticProgram object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Fitted Model</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Fitted Model</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFittedModel(FittedModel object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Fitted Normal Random Variable</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Fitted Normal Random Variable</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFittedNormalRandomVariable(FittedNormalRandomVariable object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Fitted Exponential Random Variable</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Fitted Exponential Random Variable</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFittedExponentialRandomVariable(FittedExponentialRandomVariable object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Fitted Gamma Random Variable</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Fitted Gamma Random Variable</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFittedGammaRandomVariable(FittedGammaRandomVariable object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Gamma Random Variable</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Gamma Random Variable</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseGammaRandomVariable(GammaRandomVariable object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Fitted Continuous Random Variable</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Fitted Continuous Random Variable</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFittedContinuousRandomVariable(FittedContinuousRandomVariable object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	@Override
	public T defaultCase(EObject object) {
		return null;
	}

} //StochasticSwitch
