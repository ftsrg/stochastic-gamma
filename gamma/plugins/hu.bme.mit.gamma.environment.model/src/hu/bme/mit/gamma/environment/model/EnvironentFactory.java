/**
 */
package hu.bme.mit.gamma.environment.model;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see hu.bme.mit.gamma.environment.model.EnvironentPackage
 * @generated
 */
public interface EnvironentFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	EnvironentFactory eINSTANCE = hu.bme.mit.gamma.environment.model.impl.EnvironentFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Environment Event Source</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Environment Event Source</em>'.
	 * @generated
	 */
	EnvironmentEventSource createEnvironmentEventSource();

	/**
	 * Returns a new object of class '<em>Environment Composite Component</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Environment Composite Component</em>'.
	 * @generated
	 */
	EnvironmentCompositeComponent createEnvironmentCompositeComponent();

	/**
	 * Returns a new object of class '<em>Environment Composite Component Instance</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Environment Composite Component Instance</em>'.
	 * @generated
	 */
	EnvironmentCompositeComponentInstance createEnvironmentCompositeComponentInstance();

	/**
	 * Returns a new object of class '<em>Environment Delay</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Environment Delay</em>'.
	 * @generated
	 */
	EnvironmentDelay createEnvironmentDelay();

	/**
	 * Returns a new object of class '<em>Environment Switch</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Environment Switch</em>'.
	 * @generated
	 */
	EnvironmentSwitch createEnvironmentSwitch();

	/**
	 * Returns a new object of class '<em>Event Filter</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Event Filter</em>'.
	 * @generated
	 */
	EventFilter createEventFilter();

	/**
	 * Returns a new object of class '<em>Stochastic Rule</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Stochastic Rule</em>'.
	 * @generated
	 */
	StochasticRule createStochasticRule();

	/**
	 * Returns a new object of class '<em>Port Filter</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Port Filter</em>'.
	 * @generated
	 */
	PortFilter createPortFilter();

	/**
	 * Returns a new object of class '<em>Component Filter</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Component Filter</em>'.
	 * @generated
	 */
	ComponentFilter createComponentFilter();

	/**
	 * Returns a new object of class '<em>Environment Sample</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Environment Sample</em>'.
	 * @generated
	 */
	EnvironmentSample createEnvironmentSample();

	/**
	 * Returns a new object of class '<em>Environment Extern Simulation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Environment Extern Simulation</em>'.
	 * @generated
	 */
	EnvironmentExternSimulation createEnvironmentExternSimulation();

	/**
	 * Returns a new object of class '<em>Simulation Rule</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Simulation Rule</em>'.
	 * @generated
	 */
	SimulationRule createSimulationRule();

	/**
	 * Returns a new object of class '<em>Simulation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Simulation</em>'.
	 * @generated
	 */
	Simulation createSimulation();

	/**
	 * Returns a new object of class '<em>Environment Periodic Event Source</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Environment Periodic Event Source</em>'.
	 * @generated
	 */
	EnvironmentPeriodicEventSource createEnvironmentPeriodicEventSource();

	/**
	 * Returns a new object of class '<em>Periodic Simulation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Periodic Simulation</em>'.
	 * @generated
	 */
	PeriodicSimulation createPeriodicSimulation();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	EnvironentPackage getEnvironentPackage();

} //EnvironentFactory
