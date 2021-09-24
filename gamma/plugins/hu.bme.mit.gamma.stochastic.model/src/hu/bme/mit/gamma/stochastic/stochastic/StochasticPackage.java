/**
 */
package hu.bme.mit.gamma.stochastic.stochastic;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see hu.bme.mit.gamma.stochastic.stochastic.StochasticFactory
 * @model kind="package"
 * @generated
 */
public interface StochasticPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "stochastic";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "hu.bme.mit.gamma.stochastic.model";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "stochastic";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	StochasticPackage eINSTANCE = hu.bme.mit.gamma.stochastic.stochastic.impl.StochasticPackageImpl.init();

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.stochastic.stochastic.impl.StochasticModelImpl <em>Model</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.StochasticModelImpl
	 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.StochasticPackageImpl#getStochasticModel()
	 * @generated
	 */
	int STOCHASTIC_MODEL = 0;

	/**
	 * The number of structural features of the '<em>Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STOCHASTIC_MODEL_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STOCHASTIC_MODEL_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.stochastic.stochastic.impl.RandomVariableImpl <em>Random Variable</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.RandomVariableImpl
	 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.StochasticPackageImpl#getRandomVariable()
	 * @generated
	 */
	int RANDOM_VARIABLE = 1;

	/**
	 * The feature id for the '<em><b>Dimension</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RANDOM_VARIABLE__DIMENSION = STOCHASTIC_MODEL_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Random Variable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RANDOM_VARIABLE_FEATURE_COUNT = STOCHASTIC_MODEL_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Random Variable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RANDOM_VARIABLE_OPERATION_COUNT = STOCHASTIC_MODEL_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.stochastic.stochastic.impl.ContinouosRandomVariableImpl <em>Continouos Random Variable</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.ContinouosRandomVariableImpl
	 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.StochasticPackageImpl#getContinouosRandomVariable()
	 * @generated
	 */
	int CONTINOUOS_RANDOM_VARIABLE = 2;

	/**
	 * The feature id for the '<em><b>Dimension</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTINOUOS_RANDOM_VARIABLE__DIMENSION = RANDOM_VARIABLE__DIMENSION;

	/**
	 * The number of structural features of the '<em>Continouos Random Variable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTINOUOS_RANDOM_VARIABLE_FEATURE_COUNT = RANDOM_VARIABLE_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Continouos Random Variable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTINOUOS_RANDOM_VARIABLE_OPERATION_COUNT = RANDOM_VARIABLE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.stochastic.stochastic.impl.StochasticProcessImpl <em>Process</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.StochasticProcessImpl
	 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.StochasticPackageImpl#getStochasticProcess()
	 * @generated
	 */
	int STOCHASTIC_PROCESS = 3;

	/**
	 * The number of structural features of the '<em>Process</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STOCHASTIC_PROCESS_FEATURE_COUNT = STOCHASTIC_MODEL_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Process</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STOCHASTIC_PROCESS_OPERATION_COUNT = STOCHASTIC_MODEL_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.stochastic.stochastic.impl.PoissonProcessImpl <em>Poisson Process</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.PoissonProcessImpl
	 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.StochasticPackageImpl#getPoissonProcess()
	 * @generated
	 */
	int POISSON_PROCESS = 4;

	/**
	 * The number of structural features of the '<em>Poisson Process</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POISSON_PROCESS_FEATURE_COUNT = STOCHASTIC_PROCESS_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Poisson Process</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POISSON_PROCESS_OPERATION_COUNT = STOCHASTIC_PROCESS_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.stochastic.stochastic.impl.NormalRandomVariableImpl <em>Normal Random Variable</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.NormalRandomVariableImpl
	 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.StochasticPackageImpl#getNormalRandomVariable()
	 * @generated
	 */
	int NORMAL_RANDOM_VARIABLE = 5;

	/**
	 * The feature id for the '<em><b>Dimension</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NORMAL_RANDOM_VARIABLE__DIMENSION = CONTINOUOS_RANDOM_VARIABLE__DIMENSION;

	/**
	 * The feature id for the '<em><b>Mean</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NORMAL_RANDOM_VARIABLE__MEAN = CONTINOUOS_RANDOM_VARIABLE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Scale</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NORMAL_RANDOM_VARIABLE__SCALE = CONTINOUOS_RANDOM_VARIABLE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Normal Random Variable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NORMAL_RANDOM_VARIABLE_FEATURE_COUNT = CONTINOUOS_RANDOM_VARIABLE_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Normal Random Variable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NORMAL_RANDOM_VARIABLE_OPERATION_COUNT = CONTINOUOS_RANDOM_VARIABLE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.stochastic.stochastic.impl.ExponentialRandomVariableImpl <em>Exponential Random Variable</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.ExponentialRandomVariableImpl
	 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.StochasticPackageImpl#getExponentialRandomVariable()
	 * @generated
	 */
	int EXPONENTIAL_RANDOM_VARIABLE = 6;

	/**
	 * The feature id for the '<em><b>Dimension</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPONENTIAL_RANDOM_VARIABLE__DIMENSION = CONTINOUOS_RANDOM_VARIABLE__DIMENSION;

	/**
	 * The feature id for the '<em><b>Rate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPONENTIAL_RANDOM_VARIABLE__RATE = CONTINOUOS_RANDOM_VARIABLE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Exponential Random Variable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPONENTIAL_RANDOM_VARIABLE_FEATURE_COUNT = CONTINOUOS_RANDOM_VARIABLE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Exponential Random Variable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPONENTIAL_RANDOM_VARIABLE_OPERATION_COUNT = CONTINOUOS_RANDOM_VARIABLE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.stochastic.stochastic.impl.DiscreteRandomVariableImpl <em>Discrete Random Variable</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.DiscreteRandomVariableImpl
	 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.StochasticPackageImpl#getDiscreteRandomVariable()
	 * @generated
	 */
	int DISCRETE_RANDOM_VARIABLE = 7;

	/**
	 * The feature id for the '<em><b>Dimension</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCRETE_RANDOM_VARIABLE__DIMENSION = RANDOM_VARIABLE__DIMENSION;

	/**
	 * The number of structural features of the '<em>Discrete Random Variable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCRETE_RANDOM_VARIABLE_FEATURE_COUNT = RANDOM_VARIABLE_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Discrete Random Variable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCRETE_RANDOM_VARIABLE_OPERATION_COUNT = RANDOM_VARIABLE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.stochastic.stochastic.impl.BernoulliRandomVariableImpl <em>Bernoulli Random Variable</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.BernoulliRandomVariableImpl
	 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.StochasticPackageImpl#getBernoulliRandomVariable()
	 * @generated
	 */
	int BERNOULLI_RANDOM_VARIABLE = 8;

	/**
	 * The feature id for the '<em><b>Dimension</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BERNOULLI_RANDOM_VARIABLE__DIMENSION = DISCRETE_RANDOM_VARIABLE__DIMENSION;

	/**
	 * The feature id for the '<em><b>Probability</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BERNOULLI_RANDOM_VARIABLE__PROBABILITY = DISCRETE_RANDOM_VARIABLE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Bernoulli Random Variable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BERNOULLI_RANDOM_VARIABLE_FEATURE_COUNT = DISCRETE_RANDOM_VARIABLE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Bernoulli Random Variable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BERNOULLI_RANDOM_VARIABLE_OPERATION_COUNT = DISCRETE_RANDOM_VARIABLE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.stochastic.stochastic.impl.GaussianProcessImpl <em>Gaussian Process</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.GaussianProcessImpl
	 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.StochasticPackageImpl#getGaussianProcess()
	 * @generated
	 */
	int GAUSSIAN_PROCESS = 9;

	/**
	 * The feature id for the '<em><b>Kernel</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GAUSSIAN_PROCESS__KERNEL = STOCHASTIC_PROCESS_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Gaussian Process</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GAUSSIAN_PROCESS_FEATURE_COUNT = STOCHASTIC_PROCESS_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Gaussian Process</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GAUSSIAN_PROCESS_OPERATION_COUNT = STOCHASTIC_PROCESS_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.stochastic.stochastic.impl.KernelImpl <em>Kernel</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.KernelImpl
	 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.StochasticPackageImpl#getKernel()
	 * @generated
	 */
	int KERNEL = 10;

	/**
	 * The number of structural features of the '<em>Kernel</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KERNEL_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Kernel</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KERNEL_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.stochastic.stochastic.impl.LinearKernelImpl <em>Linear Kernel</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.LinearKernelImpl
	 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.StochasticPackageImpl#getLinearKernel()
	 * @generated
	 */
	int LINEAR_KERNEL = 11;

	/**
	 * The number of structural features of the '<em>Linear Kernel</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINEAR_KERNEL_FEATURE_COUNT = KERNEL_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Linear Kernel</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINEAR_KERNEL_OPERATION_COUNT = KERNEL_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.stochastic.stochastic.impl.BrownianKernelImpl <em>Brownian Kernel</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.BrownianKernelImpl
	 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.StochasticPackageImpl#getBrownianKernel()
	 * @generated
	 */
	int BROWNIAN_KERNEL = 12;

	/**
	 * The number of structural features of the '<em>Brownian Kernel</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BROWNIAN_KERNEL_FEATURE_COUNT = KERNEL_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Brownian Kernel</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BROWNIAN_KERNEL_OPERATION_COUNT = KERNEL_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.stochastic.stochastic.impl.PeriodicKernelImpl <em>Periodic Kernel</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.PeriodicKernelImpl
	 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.StochasticPackageImpl#getPeriodicKernel()
	 * @generated
	 */
	int PERIODIC_KERNEL = 13;

	/**
	 * The number of structural features of the '<em>Periodic Kernel</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERIODIC_KERNEL_FEATURE_COUNT = KERNEL_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Periodic Kernel</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERIODIC_KERNEL_OPERATION_COUNT = KERNEL_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.stochastic.stochastic.impl.RBFKernelImpl <em>RBF Kernel</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.RBFKernelImpl
	 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.StochasticPackageImpl#getRBFKernel()
	 * @generated
	 */
	int RBF_KERNEL = 14;

	/**
	 * The number of structural features of the '<em>RBF Kernel</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RBF_KERNEL_FEATURE_COUNT = KERNEL_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>RBF Kernel</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RBF_KERNEL_OPERATION_COUNT = KERNEL_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.stochastic.stochastic.impl.SumKernelImpl <em>Sum Kernel</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.SumKernelImpl
	 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.StochasticPackageImpl#getSumKernel()
	 * @generated
	 */
	int SUM_KERNEL = 15;

	/**
	 * The feature id for the '<em><b>Kernels</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUM_KERNEL__KERNELS = KERNEL_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Sum Kernel</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUM_KERNEL_FEATURE_COUNT = KERNEL_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Sum Kernel</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUM_KERNEL_OPERATION_COUNT = KERNEL_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.stochastic.stochastic.impl.FittedGaussianProcessImpl <em>Fitted Gaussian Process</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.FittedGaussianProcessImpl
	 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.StochasticPackageImpl#getFittedGaussianProcess()
	 * @generated
	 */
	int FITTED_GAUSSIAN_PROCESS = 16;

	/**
	 * The feature id for the '<em><b>Kernel</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FITTED_GAUSSIAN_PROCESS__KERNEL = GAUSSIAN_PROCESS__KERNEL;

	/**
	 * The feature id for the '<em><b>Source</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FITTED_GAUSSIAN_PROCESS__SOURCE = GAUSSIAN_PROCESS_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Lr</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FITTED_GAUSSIAN_PROCESS__LR = GAUSSIAN_PROCESS_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Fitted Gaussian Process</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FITTED_GAUSSIAN_PROCESS_FEATURE_COUNT = GAUSSIAN_PROCESS_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Fitted Gaussian Process</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FITTED_GAUSSIAN_PROCESS_OPERATION_COUNT = GAUSSIAN_PROCESS_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.stochastic.stochastic.impl.DataSourceImpl <em>Data Source</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.DataSourceImpl
	 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.StochasticPackageImpl#getDataSource()
	 * @generated
	 */
	int DATA_SOURCE = 17;

	/**
	 * The number of structural features of the '<em>Data Source</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_SOURCE_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Data Source</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_SOURCE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.stochastic.stochastic.impl.InfluxDBImpl <em>Influx DB</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.InfluxDBImpl
	 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.StochasticPackageImpl#getInfluxDB()
	 * @generated
	 */
	int INFLUX_DB = 18;

	/**
	 * The feature id for the '<em><b>Dbname</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFLUX_DB__DBNAME = DATA_SOURCE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Ip</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFLUX_DB__IP = DATA_SOURCE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Port</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFLUX_DB__PORT = DATA_SOURCE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Query</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFLUX_DB__QUERY = DATA_SOURCE_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Influx DB</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFLUX_DB_FEATURE_COUNT = DATA_SOURCE_FEATURE_COUNT + 4;

	/**
	 * The number of operations of the '<em>Influx DB</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFLUX_DB_OPERATION_COUNT = DATA_SOURCE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.stochastic.stochastic.impl.DiracProcessImpl <em>Dirac Process</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.DiracProcessImpl
	 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.StochasticPackageImpl#getDiracProcess()
	 * @generated
	 */
	int DIRAC_PROCESS = 19;

	/**
	 * The feature id for the '<em><b>Source</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIRAC_PROCESS__SOURCE = STOCHASTIC_PROCESS_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Dirac Process</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIRAC_PROCESS_FEATURE_COUNT = STOCHASTIC_PROCESS_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Dirac Process</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIRAC_PROCESS_OPERATION_COUNT = STOCHASTIC_PROCESS_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.stochastic.stochastic.impl.PythonSimulationImpl <em>Python Simulation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.PythonSimulationImpl
	 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.StochasticPackageImpl#getPythonSimulation()
	 * @generated
	 */
	int PYTHON_SIMULATION = 20;

	/**
	 * The feature id for the '<em><b>Script</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PYTHON_SIMULATION__SCRIPT = DATA_SOURCE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Python Simulation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PYTHON_SIMULATION_FEATURE_COUNT = DATA_SOURCE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Python Simulation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PYTHON_SIMULATION_OPERATION_COUNT = DATA_SOURCE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.stochastic.stochastic.impl.CategoricalProbabailityImpl <em>Categorical Probabaility</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.CategoricalProbabailityImpl
	 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.StochasticPackageImpl#getCategoricalProbabaility()
	 * @generated
	 */
	int CATEGORICAL_PROBABAILITY = 21;

	/**
	 * The feature id for the '<em><b>Probability</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CATEGORICAL_PROBABAILITY__PROBABILITY = STOCHASTIC_MODEL_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Categorical Probabaility</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CATEGORICAL_PROBABAILITY_FEATURE_COUNT = STOCHASTIC_MODEL_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Categorical Probabaility</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CATEGORICAL_PROBABAILITY_OPERATION_COUNT = STOCHASTIC_MODEL_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.stochastic.stochastic.impl.HomogeneousPoissonProcessImpl <em>Homogeneous Poisson Process</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.HomogeneousPoissonProcessImpl
	 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.StochasticPackageImpl#getHomogeneousPoissonProcess()
	 * @generated
	 */
	int HOMOGENEOUS_POISSON_PROCESS = 22;

	/**
	 * The number of structural features of the '<em>Homogeneous Poisson Process</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HOMOGENEOUS_POISSON_PROCESS_FEATURE_COUNT = POISSON_PROCESS_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Homogeneous Poisson Process</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HOMOGENEOUS_POISSON_PROCESS_OPERATION_COUNT = POISSON_PROCESS_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.stochastic.stochastic.impl.InhomogeneousPoissonProcessImpl <em>Inhomogeneous Poisson Process</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.InhomogeneousPoissonProcessImpl
	 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.StochasticPackageImpl#getInhomogeneousPoissonProcess()
	 * @generated
	 */
	int INHOMOGENEOUS_POISSON_PROCESS = 23;

	/**
	 * The number of structural features of the '<em>Inhomogeneous Poisson Process</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INHOMOGENEOUS_POISSON_PROCESS_FEATURE_COUNT = POISSON_PROCESS_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Inhomogeneous Poisson Process</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INHOMOGENEOUS_POISSON_PROCESS_OPERATION_COUNT = POISSON_PROCESS_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.stochastic.stochastic.impl.WeibullRandomVariableImpl <em>Weibull Random Variable</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.WeibullRandomVariableImpl
	 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.StochasticPackageImpl#getWeibullRandomVariable()
	 * @generated
	 */
	int WEIBULL_RANDOM_VARIABLE = 24;

	/**
	 * The feature id for the '<em><b>Dimension</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIBULL_RANDOM_VARIABLE__DIMENSION = CONTINOUOS_RANDOM_VARIABLE__DIMENSION;

	/**
	 * The feature id for the '<em><b>Scale</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIBULL_RANDOM_VARIABLE__SCALE = CONTINOUOS_RANDOM_VARIABLE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Shape</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIBULL_RANDOM_VARIABLE__SHAPE = CONTINOUOS_RANDOM_VARIABLE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Weibull Random Variable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIBULL_RANDOM_VARIABLE_FEATURE_COUNT = CONTINOUOS_RANDOM_VARIABLE_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Weibull Random Variable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIBULL_RANDOM_VARIABLE_OPERATION_COUNT = CONTINOUOS_RANDOM_VARIABLE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.stochastic.stochastic.impl.MarkovProcessImpl <em>Markov Process</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.MarkovProcessImpl
	 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.StochasticPackageImpl#getMarkovProcess()
	 * @generated
	 */
	int MARKOV_PROCESS = 25;

	/**
	 * The number of structural features of the '<em>Markov Process</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MARKOV_PROCESS_FEATURE_COUNT = STOCHASTIC_PROCESS_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Markov Process</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MARKOV_PROCESS_OPERATION_COUNT = STOCHASTIC_PROCESS_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.stochastic.stochastic.impl.ProbabilisticProgramImpl <em>Probabilistic Program</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.ProbabilisticProgramImpl
	 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.StochasticPackageImpl#getProbabilisticProgram()
	 * @generated
	 */
	int PROBABILISTIC_PROGRAM = 26;

	/**
	 * The number of structural features of the '<em>Probabilistic Program</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROBABILISTIC_PROGRAM_FEATURE_COUNT = STOCHASTIC_MODEL_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Probabilistic Program</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROBABILISTIC_PROGRAM_OPERATION_COUNT = STOCHASTIC_MODEL_OPERATION_COUNT + 0;


	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.stochastic.stochastic.impl.FittedModelImpl <em>Fitted Model</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.FittedModelImpl
	 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.StochasticPackageImpl#getFittedModel()
	 * @generated
	 */
	int FITTED_MODEL = 27;

	/**
	 * The feature id for the '<em><b>Source</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FITTED_MODEL__SOURCE = 0;

	/**
	 * The feature id for the '<em><b>Lr</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FITTED_MODEL__LR = 1;

	/**
	 * The number of structural features of the '<em>Fitted Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FITTED_MODEL_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Fitted Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FITTED_MODEL_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.stochastic.stochastic.impl.FittedContinuousRandomVariableImpl <em>Fitted Continuous Random Variable</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.FittedContinuousRandomVariableImpl
	 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.StochasticPackageImpl#getFittedContinuousRandomVariable()
	 * @generated
	 */
	int FITTED_CONTINUOUS_RANDOM_VARIABLE = 32;

	/**
	 * The feature id for the '<em><b>Dimension</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FITTED_CONTINUOUS_RANDOM_VARIABLE__DIMENSION = CONTINOUOS_RANDOM_VARIABLE__DIMENSION;

	/**
	 * The feature id for the '<em><b>Source</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FITTED_CONTINUOUS_RANDOM_VARIABLE__SOURCE = CONTINOUOS_RANDOM_VARIABLE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Lr</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FITTED_CONTINUOUS_RANDOM_VARIABLE__LR = CONTINOUOS_RANDOM_VARIABLE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Fitted Continuous Random Variable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FITTED_CONTINUOUS_RANDOM_VARIABLE_FEATURE_COUNT = CONTINOUOS_RANDOM_VARIABLE_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Fitted Continuous Random Variable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FITTED_CONTINUOUS_RANDOM_VARIABLE_OPERATION_COUNT = CONTINOUOS_RANDOM_VARIABLE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.stochastic.stochastic.impl.FittedNormalRandomVariableImpl <em>Fitted Normal Random Variable</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.FittedNormalRandomVariableImpl
	 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.StochasticPackageImpl#getFittedNormalRandomVariable()
	 * @generated
	 */
	int FITTED_NORMAL_RANDOM_VARIABLE = 28;

	/**
	 * The feature id for the '<em><b>Dimension</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FITTED_NORMAL_RANDOM_VARIABLE__DIMENSION = FITTED_CONTINUOUS_RANDOM_VARIABLE__DIMENSION;

	/**
	 * The feature id for the '<em><b>Source</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FITTED_NORMAL_RANDOM_VARIABLE__SOURCE = FITTED_CONTINUOUS_RANDOM_VARIABLE__SOURCE;

	/**
	 * The feature id for the '<em><b>Lr</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FITTED_NORMAL_RANDOM_VARIABLE__LR = FITTED_CONTINUOUS_RANDOM_VARIABLE__LR;

	/**
	 * The number of structural features of the '<em>Fitted Normal Random Variable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FITTED_NORMAL_RANDOM_VARIABLE_FEATURE_COUNT = FITTED_CONTINUOUS_RANDOM_VARIABLE_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Fitted Normal Random Variable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FITTED_NORMAL_RANDOM_VARIABLE_OPERATION_COUNT = FITTED_CONTINUOUS_RANDOM_VARIABLE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.stochastic.stochastic.impl.FittedExponentialRandomVariableImpl <em>Fitted Exponential Random Variable</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.FittedExponentialRandomVariableImpl
	 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.StochasticPackageImpl#getFittedExponentialRandomVariable()
	 * @generated
	 */
	int FITTED_EXPONENTIAL_RANDOM_VARIABLE = 29;

	/**
	 * The feature id for the '<em><b>Dimension</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FITTED_EXPONENTIAL_RANDOM_VARIABLE__DIMENSION = FITTED_CONTINUOUS_RANDOM_VARIABLE__DIMENSION;

	/**
	 * The feature id for the '<em><b>Source</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FITTED_EXPONENTIAL_RANDOM_VARIABLE__SOURCE = FITTED_CONTINUOUS_RANDOM_VARIABLE__SOURCE;

	/**
	 * The feature id for the '<em><b>Lr</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FITTED_EXPONENTIAL_RANDOM_VARIABLE__LR = FITTED_CONTINUOUS_RANDOM_VARIABLE__LR;

	/**
	 * The number of structural features of the '<em>Fitted Exponential Random Variable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FITTED_EXPONENTIAL_RANDOM_VARIABLE_FEATURE_COUNT = FITTED_CONTINUOUS_RANDOM_VARIABLE_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Fitted Exponential Random Variable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FITTED_EXPONENTIAL_RANDOM_VARIABLE_OPERATION_COUNT = FITTED_CONTINUOUS_RANDOM_VARIABLE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.stochastic.stochastic.impl.FittedGammaRandomVariableImpl <em>Fitted Gamma Random Variable</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.FittedGammaRandomVariableImpl
	 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.StochasticPackageImpl#getFittedGammaRandomVariable()
	 * @generated
	 */
	int FITTED_GAMMA_RANDOM_VARIABLE = 30;

	/**
	 * The feature id for the '<em><b>Dimension</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FITTED_GAMMA_RANDOM_VARIABLE__DIMENSION = FITTED_CONTINUOUS_RANDOM_VARIABLE__DIMENSION;

	/**
	 * The feature id for the '<em><b>Source</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FITTED_GAMMA_RANDOM_VARIABLE__SOURCE = FITTED_CONTINUOUS_RANDOM_VARIABLE__SOURCE;

	/**
	 * The feature id for the '<em><b>Lr</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FITTED_GAMMA_RANDOM_VARIABLE__LR = FITTED_CONTINUOUS_RANDOM_VARIABLE__LR;

	/**
	 * The number of structural features of the '<em>Fitted Gamma Random Variable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FITTED_GAMMA_RANDOM_VARIABLE_FEATURE_COUNT = FITTED_CONTINUOUS_RANDOM_VARIABLE_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Fitted Gamma Random Variable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FITTED_GAMMA_RANDOM_VARIABLE_OPERATION_COUNT = FITTED_CONTINUOUS_RANDOM_VARIABLE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.stochastic.stochastic.impl.GammaRandomVariableImpl <em>Gamma Random Variable</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.GammaRandomVariableImpl
	 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.StochasticPackageImpl#getGammaRandomVariable()
	 * @generated
	 */
	int GAMMA_RANDOM_VARIABLE = 31;

	/**
	 * The feature id for the '<em><b>Dimension</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GAMMA_RANDOM_VARIABLE__DIMENSION = CONTINOUOS_RANDOM_VARIABLE__DIMENSION;

	/**
	 * The feature id for the '<em><b>Scale</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GAMMA_RANDOM_VARIABLE__SCALE = CONTINOUOS_RANDOM_VARIABLE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Shape</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GAMMA_RANDOM_VARIABLE__SHAPE = CONTINOUOS_RANDOM_VARIABLE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Gamma Random Variable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GAMMA_RANDOM_VARIABLE_FEATURE_COUNT = CONTINOUOS_RANDOM_VARIABLE_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Gamma Random Variable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GAMMA_RANDOM_VARIABLE_OPERATION_COUNT = CONTINOUOS_RANDOM_VARIABLE_OPERATION_COUNT + 0;


	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.stochastic.stochastic.StochasticModel <em>Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Model</em>'.
	 * @see hu.bme.mit.gamma.stochastic.stochastic.StochasticModel
	 * @generated
	 */
	EClass getStochasticModel();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.stochastic.stochastic.RandomVariable <em>Random Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Random Variable</em>'.
	 * @see hu.bme.mit.gamma.stochastic.stochastic.RandomVariable
	 * @generated
	 */
	EClass getRandomVariable();

	/**
	 * Returns the meta object for the attribute '{@link hu.bme.mit.gamma.stochastic.stochastic.RandomVariable#getDimension <em>Dimension</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Dimension</em>'.
	 * @see hu.bme.mit.gamma.stochastic.stochastic.RandomVariable#getDimension()
	 * @see #getRandomVariable()
	 * @generated
	 */
	EAttribute getRandomVariable_Dimension();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.stochastic.stochastic.ContinouosRandomVariable <em>Continouos Random Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Continouos Random Variable</em>'.
	 * @see hu.bme.mit.gamma.stochastic.stochastic.ContinouosRandomVariable
	 * @generated
	 */
	EClass getContinouosRandomVariable();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.stochastic.stochastic.StochasticProcess <em>Process</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Process</em>'.
	 * @see hu.bme.mit.gamma.stochastic.stochastic.StochasticProcess
	 * @generated
	 */
	EClass getStochasticProcess();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.stochastic.stochastic.PoissonProcess <em>Poisson Process</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Poisson Process</em>'.
	 * @see hu.bme.mit.gamma.stochastic.stochastic.PoissonProcess
	 * @generated
	 */
	EClass getPoissonProcess();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.stochastic.stochastic.NormalRandomVariable <em>Normal Random Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Normal Random Variable</em>'.
	 * @see hu.bme.mit.gamma.stochastic.stochastic.NormalRandomVariable
	 * @generated
	 */
	EClass getNormalRandomVariable();

	/**
	 * Returns the meta object for the attribute '{@link hu.bme.mit.gamma.stochastic.stochastic.NormalRandomVariable#getMean <em>Mean</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Mean</em>'.
	 * @see hu.bme.mit.gamma.stochastic.stochastic.NormalRandomVariable#getMean()
	 * @see #getNormalRandomVariable()
	 * @generated
	 */
	EAttribute getNormalRandomVariable_Mean();

	/**
	 * Returns the meta object for the attribute '{@link hu.bme.mit.gamma.stochastic.stochastic.NormalRandomVariable#getScale <em>Scale</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Scale</em>'.
	 * @see hu.bme.mit.gamma.stochastic.stochastic.NormalRandomVariable#getScale()
	 * @see #getNormalRandomVariable()
	 * @generated
	 */
	EAttribute getNormalRandomVariable_Scale();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.stochastic.stochastic.ExponentialRandomVariable <em>Exponential Random Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Exponential Random Variable</em>'.
	 * @see hu.bme.mit.gamma.stochastic.stochastic.ExponentialRandomVariable
	 * @generated
	 */
	EClass getExponentialRandomVariable();

	/**
	 * Returns the meta object for the attribute '{@link hu.bme.mit.gamma.stochastic.stochastic.ExponentialRandomVariable#getRate <em>Rate</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Rate</em>'.
	 * @see hu.bme.mit.gamma.stochastic.stochastic.ExponentialRandomVariable#getRate()
	 * @see #getExponentialRandomVariable()
	 * @generated
	 */
	EAttribute getExponentialRandomVariable_Rate();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.stochastic.stochastic.DiscreteRandomVariable <em>Discrete Random Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Discrete Random Variable</em>'.
	 * @see hu.bme.mit.gamma.stochastic.stochastic.DiscreteRandomVariable
	 * @generated
	 */
	EClass getDiscreteRandomVariable();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.stochastic.stochastic.BernoulliRandomVariable <em>Bernoulli Random Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Bernoulli Random Variable</em>'.
	 * @see hu.bme.mit.gamma.stochastic.stochastic.BernoulliRandomVariable
	 * @generated
	 */
	EClass getBernoulliRandomVariable();

	/**
	 * Returns the meta object for the attribute '{@link hu.bme.mit.gamma.stochastic.stochastic.BernoulliRandomVariable#getProbability <em>Probability</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Probability</em>'.
	 * @see hu.bme.mit.gamma.stochastic.stochastic.BernoulliRandomVariable#getProbability()
	 * @see #getBernoulliRandomVariable()
	 * @generated
	 */
	EAttribute getBernoulliRandomVariable_Probability();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.stochastic.stochastic.GaussianProcess <em>Gaussian Process</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Gaussian Process</em>'.
	 * @see hu.bme.mit.gamma.stochastic.stochastic.GaussianProcess
	 * @generated
	 */
	EClass getGaussianProcess();

	/**
	 * Returns the meta object for the containment reference '{@link hu.bme.mit.gamma.stochastic.stochastic.GaussianProcess#getKernel <em>Kernel</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Kernel</em>'.
	 * @see hu.bme.mit.gamma.stochastic.stochastic.GaussianProcess#getKernel()
	 * @see #getGaussianProcess()
	 * @generated
	 */
	EReference getGaussianProcess_Kernel();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.stochastic.stochastic.Kernel <em>Kernel</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Kernel</em>'.
	 * @see hu.bme.mit.gamma.stochastic.stochastic.Kernel
	 * @generated
	 */
	EClass getKernel();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.stochastic.stochastic.LinearKernel <em>Linear Kernel</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Linear Kernel</em>'.
	 * @see hu.bme.mit.gamma.stochastic.stochastic.LinearKernel
	 * @generated
	 */
	EClass getLinearKernel();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.stochastic.stochastic.BrownianKernel <em>Brownian Kernel</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Brownian Kernel</em>'.
	 * @see hu.bme.mit.gamma.stochastic.stochastic.BrownianKernel
	 * @generated
	 */
	EClass getBrownianKernel();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.stochastic.stochastic.PeriodicKernel <em>Periodic Kernel</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Periodic Kernel</em>'.
	 * @see hu.bme.mit.gamma.stochastic.stochastic.PeriodicKernel
	 * @generated
	 */
	EClass getPeriodicKernel();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.stochastic.stochastic.RBFKernel <em>RBF Kernel</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>RBF Kernel</em>'.
	 * @see hu.bme.mit.gamma.stochastic.stochastic.RBFKernel
	 * @generated
	 */
	EClass getRBFKernel();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.stochastic.stochastic.SumKernel <em>Sum Kernel</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sum Kernel</em>'.
	 * @see hu.bme.mit.gamma.stochastic.stochastic.SumKernel
	 * @generated
	 */
	EClass getSumKernel();

	/**
	 * Returns the meta object for the containment reference list '{@link hu.bme.mit.gamma.stochastic.stochastic.SumKernel#getKernels <em>Kernels</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Kernels</em>'.
	 * @see hu.bme.mit.gamma.stochastic.stochastic.SumKernel#getKernels()
	 * @see #getSumKernel()
	 * @generated
	 */
	EReference getSumKernel_Kernels();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.stochastic.stochastic.FittedGaussianProcess <em>Fitted Gaussian Process</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Fitted Gaussian Process</em>'.
	 * @see hu.bme.mit.gamma.stochastic.stochastic.FittedGaussianProcess
	 * @generated
	 */
	EClass getFittedGaussianProcess();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.stochastic.stochastic.DataSource <em>Data Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Data Source</em>'.
	 * @see hu.bme.mit.gamma.stochastic.stochastic.DataSource
	 * @generated
	 */
	EClass getDataSource();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.stochastic.stochastic.InfluxDB <em>Influx DB</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Influx DB</em>'.
	 * @see hu.bme.mit.gamma.stochastic.stochastic.InfluxDB
	 * @generated
	 */
	EClass getInfluxDB();

	/**
	 * Returns the meta object for the attribute '{@link hu.bme.mit.gamma.stochastic.stochastic.InfluxDB#getDbname <em>Dbname</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Dbname</em>'.
	 * @see hu.bme.mit.gamma.stochastic.stochastic.InfluxDB#getDbname()
	 * @see #getInfluxDB()
	 * @generated
	 */
	EAttribute getInfluxDB_Dbname();

	/**
	 * Returns the meta object for the attribute '{@link hu.bme.mit.gamma.stochastic.stochastic.InfluxDB#getIp <em>Ip</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ip</em>'.
	 * @see hu.bme.mit.gamma.stochastic.stochastic.InfluxDB#getIp()
	 * @see #getInfluxDB()
	 * @generated
	 */
	EAttribute getInfluxDB_Ip();

	/**
	 * Returns the meta object for the attribute '{@link hu.bme.mit.gamma.stochastic.stochastic.InfluxDB#getPort <em>Port</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Port</em>'.
	 * @see hu.bme.mit.gamma.stochastic.stochastic.InfluxDB#getPort()
	 * @see #getInfluxDB()
	 * @generated
	 */
	EAttribute getInfluxDB_Port();

	/**
	 * Returns the meta object for the attribute '{@link hu.bme.mit.gamma.stochastic.stochastic.InfluxDB#getQuery <em>Query</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Query</em>'.
	 * @see hu.bme.mit.gamma.stochastic.stochastic.InfluxDB#getQuery()
	 * @see #getInfluxDB()
	 * @generated
	 */
	EAttribute getInfluxDB_Query();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.stochastic.stochastic.DiracProcess <em>Dirac Process</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Dirac Process</em>'.
	 * @see hu.bme.mit.gamma.stochastic.stochastic.DiracProcess
	 * @generated
	 */
	EClass getDiracProcess();

	/**
	 * Returns the meta object for the containment reference '{@link hu.bme.mit.gamma.stochastic.stochastic.DiracProcess#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Source</em>'.
	 * @see hu.bme.mit.gamma.stochastic.stochastic.DiracProcess#getSource()
	 * @see #getDiracProcess()
	 * @generated
	 */
	EReference getDiracProcess_Source();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.stochastic.stochastic.PythonSimulation <em>Python Simulation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Python Simulation</em>'.
	 * @see hu.bme.mit.gamma.stochastic.stochastic.PythonSimulation
	 * @generated
	 */
	EClass getPythonSimulation();

	/**
	 * Returns the meta object for the attribute '{@link hu.bme.mit.gamma.stochastic.stochastic.PythonSimulation#getScript <em>Script</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Script</em>'.
	 * @see hu.bme.mit.gamma.stochastic.stochastic.PythonSimulation#getScript()
	 * @see #getPythonSimulation()
	 * @generated
	 */
	EAttribute getPythonSimulation_Script();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.stochastic.stochastic.CategoricalProbabaility <em>Categorical Probabaility</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Categorical Probabaility</em>'.
	 * @see hu.bme.mit.gamma.stochastic.stochastic.CategoricalProbabaility
	 * @generated
	 */
	EClass getCategoricalProbabaility();

	/**
	 * Returns the meta object for the attribute '{@link hu.bme.mit.gamma.stochastic.stochastic.CategoricalProbabaility#getProbability <em>Probability</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Probability</em>'.
	 * @see hu.bme.mit.gamma.stochastic.stochastic.CategoricalProbabaility#getProbability()
	 * @see #getCategoricalProbabaility()
	 * @generated
	 */
	EAttribute getCategoricalProbabaility_Probability();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.stochastic.stochastic.HomogeneousPoissonProcess <em>Homogeneous Poisson Process</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Homogeneous Poisson Process</em>'.
	 * @see hu.bme.mit.gamma.stochastic.stochastic.HomogeneousPoissonProcess
	 * @generated
	 */
	EClass getHomogeneousPoissonProcess();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.stochastic.stochastic.InhomogeneousPoissonProcess <em>Inhomogeneous Poisson Process</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Inhomogeneous Poisson Process</em>'.
	 * @see hu.bme.mit.gamma.stochastic.stochastic.InhomogeneousPoissonProcess
	 * @generated
	 */
	EClass getInhomogeneousPoissonProcess();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.stochastic.stochastic.WeibullRandomVariable <em>Weibull Random Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Weibull Random Variable</em>'.
	 * @see hu.bme.mit.gamma.stochastic.stochastic.WeibullRandomVariable
	 * @generated
	 */
	EClass getWeibullRandomVariable();

	/**
	 * Returns the meta object for the attribute '{@link hu.bme.mit.gamma.stochastic.stochastic.WeibullRandomVariable#getScale <em>Scale</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Scale</em>'.
	 * @see hu.bme.mit.gamma.stochastic.stochastic.WeibullRandomVariable#getScale()
	 * @see #getWeibullRandomVariable()
	 * @generated
	 */
	EAttribute getWeibullRandomVariable_Scale();

	/**
	 * Returns the meta object for the attribute '{@link hu.bme.mit.gamma.stochastic.stochastic.WeibullRandomVariable#getShape <em>Shape</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Shape</em>'.
	 * @see hu.bme.mit.gamma.stochastic.stochastic.WeibullRandomVariable#getShape()
	 * @see #getWeibullRandomVariable()
	 * @generated
	 */
	EAttribute getWeibullRandomVariable_Shape();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.stochastic.stochastic.MarkovProcess <em>Markov Process</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Markov Process</em>'.
	 * @see hu.bme.mit.gamma.stochastic.stochastic.MarkovProcess
	 * @generated
	 */
	EClass getMarkovProcess();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.stochastic.stochastic.ProbabilisticProgram <em>Probabilistic Program</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Probabilistic Program</em>'.
	 * @see hu.bme.mit.gamma.stochastic.stochastic.ProbabilisticProgram
	 * @generated
	 */
	EClass getProbabilisticProgram();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.stochastic.stochastic.FittedModel <em>Fitted Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Fitted Model</em>'.
	 * @see hu.bme.mit.gamma.stochastic.stochastic.FittedModel
	 * @generated
	 */
	EClass getFittedModel();

	/**
	 * Returns the meta object for the containment reference '{@link hu.bme.mit.gamma.stochastic.stochastic.FittedModel#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Source</em>'.
	 * @see hu.bme.mit.gamma.stochastic.stochastic.FittedModel#getSource()
	 * @see #getFittedModel()
	 * @generated
	 */
	EReference getFittedModel_Source();

	/**
	 * Returns the meta object for the attribute '{@link hu.bme.mit.gamma.stochastic.stochastic.FittedModel#getLr <em>Lr</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Lr</em>'.
	 * @see hu.bme.mit.gamma.stochastic.stochastic.FittedModel#getLr()
	 * @see #getFittedModel()
	 * @generated
	 */
	EAttribute getFittedModel_Lr();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.stochastic.stochastic.FittedNormalRandomVariable <em>Fitted Normal Random Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Fitted Normal Random Variable</em>'.
	 * @see hu.bme.mit.gamma.stochastic.stochastic.FittedNormalRandomVariable
	 * @generated
	 */
	EClass getFittedNormalRandomVariable();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.stochastic.stochastic.FittedExponentialRandomVariable <em>Fitted Exponential Random Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Fitted Exponential Random Variable</em>'.
	 * @see hu.bme.mit.gamma.stochastic.stochastic.FittedExponentialRandomVariable
	 * @generated
	 */
	EClass getFittedExponentialRandomVariable();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.stochastic.stochastic.FittedGammaRandomVariable <em>Fitted Gamma Random Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Fitted Gamma Random Variable</em>'.
	 * @see hu.bme.mit.gamma.stochastic.stochastic.FittedGammaRandomVariable
	 * @generated
	 */
	EClass getFittedGammaRandomVariable();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.stochastic.stochastic.GammaRandomVariable <em>Gamma Random Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Gamma Random Variable</em>'.
	 * @see hu.bme.mit.gamma.stochastic.stochastic.GammaRandomVariable
	 * @generated
	 */
	EClass getGammaRandomVariable();

	/**
	 * Returns the meta object for the attribute '{@link hu.bme.mit.gamma.stochastic.stochastic.GammaRandomVariable#getScale <em>Scale</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Scale</em>'.
	 * @see hu.bme.mit.gamma.stochastic.stochastic.GammaRandomVariable#getScale()
	 * @see #getGammaRandomVariable()
	 * @generated
	 */
	EAttribute getGammaRandomVariable_Scale();

	/**
	 * Returns the meta object for the attribute '{@link hu.bme.mit.gamma.stochastic.stochastic.GammaRandomVariable#getShape <em>Shape</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Shape</em>'.
	 * @see hu.bme.mit.gamma.stochastic.stochastic.GammaRandomVariable#getShape()
	 * @see #getGammaRandomVariable()
	 * @generated
	 */
	EAttribute getGammaRandomVariable_Shape();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.stochastic.stochastic.FittedContinuousRandomVariable <em>Fitted Continuous Random Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Fitted Continuous Random Variable</em>'.
	 * @see hu.bme.mit.gamma.stochastic.stochastic.FittedContinuousRandomVariable
	 * @generated
	 */
	EClass getFittedContinuousRandomVariable();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	StochasticFactory getStochasticFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.stochastic.stochastic.impl.StochasticModelImpl <em>Model</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.StochasticModelImpl
		 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.StochasticPackageImpl#getStochasticModel()
		 * @generated
		 */
		EClass STOCHASTIC_MODEL = eINSTANCE.getStochasticModel();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.stochastic.stochastic.impl.RandomVariableImpl <em>Random Variable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.RandomVariableImpl
		 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.StochasticPackageImpl#getRandomVariable()
		 * @generated
		 */
		EClass RANDOM_VARIABLE = eINSTANCE.getRandomVariable();

		/**
		 * The meta object literal for the '<em><b>Dimension</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RANDOM_VARIABLE__DIMENSION = eINSTANCE.getRandomVariable_Dimension();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.stochastic.stochastic.impl.ContinouosRandomVariableImpl <em>Continouos Random Variable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.ContinouosRandomVariableImpl
		 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.StochasticPackageImpl#getContinouosRandomVariable()
		 * @generated
		 */
		EClass CONTINOUOS_RANDOM_VARIABLE = eINSTANCE.getContinouosRandomVariable();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.stochastic.stochastic.impl.StochasticProcessImpl <em>Process</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.StochasticProcessImpl
		 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.StochasticPackageImpl#getStochasticProcess()
		 * @generated
		 */
		EClass STOCHASTIC_PROCESS = eINSTANCE.getStochasticProcess();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.stochastic.stochastic.impl.PoissonProcessImpl <em>Poisson Process</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.PoissonProcessImpl
		 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.StochasticPackageImpl#getPoissonProcess()
		 * @generated
		 */
		EClass POISSON_PROCESS = eINSTANCE.getPoissonProcess();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.stochastic.stochastic.impl.NormalRandomVariableImpl <em>Normal Random Variable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.NormalRandomVariableImpl
		 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.StochasticPackageImpl#getNormalRandomVariable()
		 * @generated
		 */
		EClass NORMAL_RANDOM_VARIABLE = eINSTANCE.getNormalRandomVariable();

		/**
		 * The meta object literal for the '<em><b>Mean</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NORMAL_RANDOM_VARIABLE__MEAN = eINSTANCE.getNormalRandomVariable_Mean();

		/**
		 * The meta object literal for the '<em><b>Scale</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NORMAL_RANDOM_VARIABLE__SCALE = eINSTANCE.getNormalRandomVariable_Scale();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.stochastic.stochastic.impl.ExponentialRandomVariableImpl <em>Exponential Random Variable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.ExponentialRandomVariableImpl
		 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.StochasticPackageImpl#getExponentialRandomVariable()
		 * @generated
		 */
		EClass EXPONENTIAL_RANDOM_VARIABLE = eINSTANCE.getExponentialRandomVariable();

		/**
		 * The meta object literal for the '<em><b>Rate</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXPONENTIAL_RANDOM_VARIABLE__RATE = eINSTANCE.getExponentialRandomVariable_Rate();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.stochastic.stochastic.impl.DiscreteRandomVariableImpl <em>Discrete Random Variable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.DiscreteRandomVariableImpl
		 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.StochasticPackageImpl#getDiscreteRandomVariable()
		 * @generated
		 */
		EClass DISCRETE_RANDOM_VARIABLE = eINSTANCE.getDiscreteRandomVariable();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.stochastic.stochastic.impl.BernoulliRandomVariableImpl <em>Bernoulli Random Variable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.BernoulliRandomVariableImpl
		 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.StochasticPackageImpl#getBernoulliRandomVariable()
		 * @generated
		 */
		EClass BERNOULLI_RANDOM_VARIABLE = eINSTANCE.getBernoulliRandomVariable();

		/**
		 * The meta object literal for the '<em><b>Probability</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BERNOULLI_RANDOM_VARIABLE__PROBABILITY = eINSTANCE.getBernoulliRandomVariable_Probability();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.stochastic.stochastic.impl.GaussianProcessImpl <em>Gaussian Process</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.GaussianProcessImpl
		 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.StochasticPackageImpl#getGaussianProcess()
		 * @generated
		 */
		EClass GAUSSIAN_PROCESS = eINSTANCE.getGaussianProcess();

		/**
		 * The meta object literal for the '<em><b>Kernel</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GAUSSIAN_PROCESS__KERNEL = eINSTANCE.getGaussianProcess_Kernel();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.stochastic.stochastic.impl.KernelImpl <em>Kernel</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.KernelImpl
		 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.StochasticPackageImpl#getKernel()
		 * @generated
		 */
		EClass KERNEL = eINSTANCE.getKernel();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.stochastic.stochastic.impl.LinearKernelImpl <em>Linear Kernel</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.LinearKernelImpl
		 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.StochasticPackageImpl#getLinearKernel()
		 * @generated
		 */
		EClass LINEAR_KERNEL = eINSTANCE.getLinearKernel();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.stochastic.stochastic.impl.BrownianKernelImpl <em>Brownian Kernel</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.BrownianKernelImpl
		 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.StochasticPackageImpl#getBrownianKernel()
		 * @generated
		 */
		EClass BROWNIAN_KERNEL = eINSTANCE.getBrownianKernel();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.stochastic.stochastic.impl.PeriodicKernelImpl <em>Periodic Kernel</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.PeriodicKernelImpl
		 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.StochasticPackageImpl#getPeriodicKernel()
		 * @generated
		 */
		EClass PERIODIC_KERNEL = eINSTANCE.getPeriodicKernel();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.stochastic.stochastic.impl.RBFKernelImpl <em>RBF Kernel</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.RBFKernelImpl
		 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.StochasticPackageImpl#getRBFKernel()
		 * @generated
		 */
		EClass RBF_KERNEL = eINSTANCE.getRBFKernel();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.stochastic.stochastic.impl.SumKernelImpl <em>Sum Kernel</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.SumKernelImpl
		 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.StochasticPackageImpl#getSumKernel()
		 * @generated
		 */
		EClass SUM_KERNEL = eINSTANCE.getSumKernel();

		/**
		 * The meta object literal for the '<em><b>Kernels</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SUM_KERNEL__KERNELS = eINSTANCE.getSumKernel_Kernels();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.stochastic.stochastic.impl.FittedGaussianProcessImpl <em>Fitted Gaussian Process</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.FittedGaussianProcessImpl
		 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.StochasticPackageImpl#getFittedGaussianProcess()
		 * @generated
		 */
		EClass FITTED_GAUSSIAN_PROCESS = eINSTANCE.getFittedGaussianProcess();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.stochastic.stochastic.impl.DataSourceImpl <em>Data Source</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.DataSourceImpl
		 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.StochasticPackageImpl#getDataSource()
		 * @generated
		 */
		EClass DATA_SOURCE = eINSTANCE.getDataSource();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.stochastic.stochastic.impl.InfluxDBImpl <em>Influx DB</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.InfluxDBImpl
		 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.StochasticPackageImpl#getInfluxDB()
		 * @generated
		 */
		EClass INFLUX_DB = eINSTANCE.getInfluxDB();

		/**
		 * The meta object literal for the '<em><b>Dbname</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INFLUX_DB__DBNAME = eINSTANCE.getInfluxDB_Dbname();

		/**
		 * The meta object literal for the '<em><b>Ip</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INFLUX_DB__IP = eINSTANCE.getInfluxDB_Ip();

		/**
		 * The meta object literal for the '<em><b>Port</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INFLUX_DB__PORT = eINSTANCE.getInfluxDB_Port();

		/**
		 * The meta object literal for the '<em><b>Query</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INFLUX_DB__QUERY = eINSTANCE.getInfluxDB_Query();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.stochastic.stochastic.impl.DiracProcessImpl <em>Dirac Process</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.DiracProcessImpl
		 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.StochasticPackageImpl#getDiracProcess()
		 * @generated
		 */
		EClass DIRAC_PROCESS = eINSTANCE.getDiracProcess();

		/**
		 * The meta object literal for the '<em><b>Source</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DIRAC_PROCESS__SOURCE = eINSTANCE.getDiracProcess_Source();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.stochastic.stochastic.impl.PythonSimulationImpl <em>Python Simulation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.PythonSimulationImpl
		 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.StochasticPackageImpl#getPythonSimulation()
		 * @generated
		 */
		EClass PYTHON_SIMULATION = eINSTANCE.getPythonSimulation();

		/**
		 * The meta object literal for the '<em><b>Script</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PYTHON_SIMULATION__SCRIPT = eINSTANCE.getPythonSimulation_Script();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.stochastic.stochastic.impl.CategoricalProbabailityImpl <em>Categorical Probabaility</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.CategoricalProbabailityImpl
		 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.StochasticPackageImpl#getCategoricalProbabaility()
		 * @generated
		 */
		EClass CATEGORICAL_PROBABAILITY = eINSTANCE.getCategoricalProbabaility();

		/**
		 * The meta object literal for the '<em><b>Probability</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CATEGORICAL_PROBABAILITY__PROBABILITY = eINSTANCE.getCategoricalProbabaility_Probability();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.stochastic.stochastic.impl.HomogeneousPoissonProcessImpl <em>Homogeneous Poisson Process</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.HomogeneousPoissonProcessImpl
		 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.StochasticPackageImpl#getHomogeneousPoissonProcess()
		 * @generated
		 */
		EClass HOMOGENEOUS_POISSON_PROCESS = eINSTANCE.getHomogeneousPoissonProcess();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.stochastic.stochastic.impl.InhomogeneousPoissonProcessImpl <em>Inhomogeneous Poisson Process</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.InhomogeneousPoissonProcessImpl
		 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.StochasticPackageImpl#getInhomogeneousPoissonProcess()
		 * @generated
		 */
		EClass INHOMOGENEOUS_POISSON_PROCESS = eINSTANCE.getInhomogeneousPoissonProcess();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.stochastic.stochastic.impl.WeibullRandomVariableImpl <em>Weibull Random Variable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.WeibullRandomVariableImpl
		 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.StochasticPackageImpl#getWeibullRandomVariable()
		 * @generated
		 */
		EClass WEIBULL_RANDOM_VARIABLE = eINSTANCE.getWeibullRandomVariable();

		/**
		 * The meta object literal for the '<em><b>Scale</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WEIBULL_RANDOM_VARIABLE__SCALE = eINSTANCE.getWeibullRandomVariable_Scale();

		/**
		 * The meta object literal for the '<em><b>Shape</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WEIBULL_RANDOM_VARIABLE__SHAPE = eINSTANCE.getWeibullRandomVariable_Shape();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.stochastic.stochastic.impl.MarkovProcessImpl <em>Markov Process</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.MarkovProcessImpl
		 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.StochasticPackageImpl#getMarkovProcess()
		 * @generated
		 */
		EClass MARKOV_PROCESS = eINSTANCE.getMarkovProcess();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.stochastic.stochastic.impl.ProbabilisticProgramImpl <em>Probabilistic Program</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.ProbabilisticProgramImpl
		 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.StochasticPackageImpl#getProbabilisticProgram()
		 * @generated
		 */
		EClass PROBABILISTIC_PROGRAM = eINSTANCE.getProbabilisticProgram();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.stochastic.stochastic.impl.FittedModelImpl <em>Fitted Model</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.FittedModelImpl
		 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.StochasticPackageImpl#getFittedModel()
		 * @generated
		 */
		EClass FITTED_MODEL = eINSTANCE.getFittedModel();

		/**
		 * The meta object literal for the '<em><b>Source</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FITTED_MODEL__SOURCE = eINSTANCE.getFittedModel_Source();

		/**
		 * The meta object literal for the '<em><b>Lr</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FITTED_MODEL__LR = eINSTANCE.getFittedModel_Lr();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.stochastic.stochastic.impl.FittedNormalRandomVariableImpl <em>Fitted Normal Random Variable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.FittedNormalRandomVariableImpl
		 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.StochasticPackageImpl#getFittedNormalRandomVariable()
		 * @generated
		 */
		EClass FITTED_NORMAL_RANDOM_VARIABLE = eINSTANCE.getFittedNormalRandomVariable();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.stochastic.stochastic.impl.FittedExponentialRandomVariableImpl <em>Fitted Exponential Random Variable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.FittedExponentialRandomVariableImpl
		 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.StochasticPackageImpl#getFittedExponentialRandomVariable()
		 * @generated
		 */
		EClass FITTED_EXPONENTIAL_RANDOM_VARIABLE = eINSTANCE.getFittedExponentialRandomVariable();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.stochastic.stochastic.impl.FittedGammaRandomVariableImpl <em>Fitted Gamma Random Variable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.FittedGammaRandomVariableImpl
		 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.StochasticPackageImpl#getFittedGammaRandomVariable()
		 * @generated
		 */
		EClass FITTED_GAMMA_RANDOM_VARIABLE = eINSTANCE.getFittedGammaRandomVariable();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.stochastic.stochastic.impl.GammaRandomVariableImpl <em>Gamma Random Variable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.GammaRandomVariableImpl
		 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.StochasticPackageImpl#getGammaRandomVariable()
		 * @generated
		 */
		EClass GAMMA_RANDOM_VARIABLE = eINSTANCE.getGammaRandomVariable();

		/**
		 * The meta object literal for the '<em><b>Scale</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GAMMA_RANDOM_VARIABLE__SCALE = eINSTANCE.getGammaRandomVariable_Scale();

		/**
		 * The meta object literal for the '<em><b>Shape</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GAMMA_RANDOM_VARIABLE__SHAPE = eINSTANCE.getGammaRandomVariable_Shape();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.stochastic.stochastic.impl.FittedContinuousRandomVariableImpl <em>Fitted Continuous Random Variable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.FittedContinuousRandomVariableImpl
		 * @see hu.bme.mit.gamma.stochastic.stochastic.impl.StochasticPackageImpl#getFittedContinuousRandomVariable()
		 * @generated
		 */
		EClass FITTED_CONTINUOUS_RANDOM_VARIABLE = eINSTANCE.getFittedContinuousRandomVariable();

	}

} //StochasticPackage
