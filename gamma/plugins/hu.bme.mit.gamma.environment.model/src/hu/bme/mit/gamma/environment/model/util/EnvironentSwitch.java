/**
 */
package hu.bme.mit.gamma.environment.model.util;

import hu.bme.mit.gamma.environment.model.*;

import hu.bme.mit.gamma.expression.model.ArgumentedElement;
import hu.bme.mit.gamma.expression.model.NamedElement;
import hu.bme.mit.gamma.expression.model.ParametricElement;

import hu.bme.mit.gamma.statechart.composite.AbstractSynchronousCompositeComponent;
import hu.bme.mit.gamma.statechart.composite.CascadeCompositeComponent;
import hu.bme.mit.gamma.statechart.composite.ComponentInstance;
import hu.bme.mit.gamma.statechart.composite.CompositeComponent;
import hu.bme.mit.gamma.statechart.composite.SynchronousComponent;

import hu.bme.mit.gamma.statechart.interface_.Component;

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
 * @see hu.bme.mit.gamma.environment.model.EnvironentPackage
 * @generated
 */
public class EnvironentSwitch<T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static EnvironentPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EnvironentSwitch() {
		if (modelPackage == null) {
			modelPackage = EnvironentPackage.eINSTANCE;
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
			case EnvironentPackage.ENVIRONMENT_EVENT_SOURCE: {
				EnvironmentEventSource environmentEventSource = (EnvironmentEventSource)theEObject;
				T result = caseEnvironmentEventSource(environmentEventSource);
				if (result == null) result = caseElementaryEnvironmentComponentInstance(environmentEventSource);
				if (result == null) result = caseEnvironmentComponentInstance(environmentEventSource);
				if (result == null) result = caseComponentInstance(environmentEventSource);
				if (result == null) result = caseNamedElement(environmentEventSource);
				if (result == null) result = caseArgumentedElement(environmentEventSource);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EnvironentPackage.ENVIRONMENT_COMPOSITE_COMPONENT: {
				EnvironmentCompositeComponent environmentCompositeComponent = (EnvironmentCompositeComponent)theEObject;
				T result = caseEnvironmentCompositeComponent(environmentCompositeComponent);
				if (result == null) result = caseCascadeCompositeComponent(environmentCompositeComponent);
				if (result == null) result = caseAbstractSynchronousCompositeComponent(environmentCompositeComponent);
				if (result == null) result = caseCompositeComponent(environmentCompositeComponent);
				if (result == null) result = caseSynchronousComponent(environmentCompositeComponent);
				if (result == null) result = caseComponent(environmentCompositeComponent);
				if (result == null) result = caseNamedElement(environmentCompositeComponent);
				if (result == null) result = caseParametricElement(environmentCompositeComponent);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EnvironentPackage.ENVIRONMENT_COMPONENT_INSTANCE: {
				EnvironmentComponentInstance environmentComponentInstance = (EnvironmentComponentInstance)theEObject;
				T result = caseEnvironmentComponentInstance(environmentComponentInstance);
				if (result == null) result = caseComponentInstance(environmentComponentInstance);
				if (result == null) result = caseNamedElement(environmentComponentInstance);
				if (result == null) result = caseArgumentedElement(environmentComponentInstance);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EnvironentPackage.ENVIRONMENT_COMPOSITE_COMPONENT_INSTANCE: {
				EnvironmentCompositeComponentInstance environmentCompositeComponentInstance = (EnvironmentCompositeComponentInstance)theEObject;
				T result = caseEnvironmentCompositeComponentInstance(environmentCompositeComponentInstance);
				if (result == null) result = caseEnvironmentComponentInstance(environmentCompositeComponentInstance);
				if (result == null) result = caseComponentInstance(environmentCompositeComponentInstance);
				if (result == null) result = caseNamedElement(environmentCompositeComponentInstance);
				if (result == null) result = caseArgumentedElement(environmentCompositeComponentInstance);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EnvironentPackage.ENVIRONMENT_DELAY: {
				EnvironmentDelay environmentDelay = (EnvironmentDelay)theEObject;
				T result = caseEnvironmentDelay(environmentDelay);
				if (result == null) result = caseElementaryEnvironmentComponentInstance(environmentDelay);
				if (result == null) result = caseEnvironmentComponentInstance(environmentDelay);
				if (result == null) result = caseComponentInstance(environmentDelay);
				if (result == null) result = caseNamedElement(environmentDelay);
				if (result == null) result = caseArgumentedElement(environmentDelay);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EnvironentPackage.ENVIRONMENT_SWITCH: {
				EnvironmentSwitch environmentSwitch = (EnvironmentSwitch)theEObject;
				T result = caseEnvironmentSwitch(environmentSwitch);
				if (result == null) result = caseElementaryEnvironmentComponentInstance(environmentSwitch);
				if (result == null) result = caseEnvironmentComponentInstance(environmentSwitch);
				if (result == null) result = caseComponentInstance(environmentSwitch);
				if (result == null) result = caseNamedElement(environmentSwitch);
				if (result == null) result = caseArgumentedElement(environmentSwitch);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EnvironentPackage.ELEMENTARY_ENVIRONMENT_COMPONENT_INSTANCE: {
				ElementaryEnvironmentComponentInstance elementaryEnvironmentComponentInstance = (ElementaryEnvironmentComponentInstance)theEObject;
				T result = caseElementaryEnvironmentComponentInstance(elementaryEnvironmentComponentInstance);
				if (result == null) result = caseEnvironmentComponentInstance(elementaryEnvironmentComponentInstance);
				if (result == null) result = caseComponentInstance(elementaryEnvironmentComponentInstance);
				if (result == null) result = caseNamedElement(elementaryEnvironmentComponentInstance);
				if (result == null) result = caseArgumentedElement(elementaryEnvironmentComponentInstance);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EnvironentPackage.EVENT_FILTER: {
				EventFilter eventFilter = (EventFilter)theEObject;
				T result = caseEventFilter(eventFilter);
				if (result == null) result = caseFilter(eventFilter);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EnvironentPackage.STOCHASTIC_RULE: {
				StochasticRule stochasticRule = (StochasticRule)theEObject;
				T result = caseStochasticRule(stochasticRule);
				if (result == null) result = caseEnvironmentRule(stochasticRule);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EnvironentPackage.PORT_FILTER: {
				PortFilter portFilter = (PortFilter)theEObject;
				T result = casePortFilter(portFilter);
				if (result == null) result = caseFilter(portFilter);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EnvironentPackage.COMPONENT_FILTER: {
				ComponentFilter componentFilter = (ComponentFilter)theEObject;
				T result = caseComponentFilter(componentFilter);
				if (result == null) result = caseFilter(componentFilter);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EnvironentPackage.ENVIRONMENT_RULE: {
				EnvironmentRule environmentRule = (EnvironmentRule)theEObject;
				T result = caseEnvironmentRule(environmentRule);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EnvironentPackage.ENVIRONMENT_SAMPLE: {
				EnvironmentSample environmentSample = (EnvironmentSample)theEObject;
				T result = caseEnvironmentSample(environmentSample);
				if (result == null) result = caseElementaryEnvironmentComponentInstance(environmentSample);
				if (result == null) result = caseEnvironmentComponentInstance(environmentSample);
				if (result == null) result = caseComponentInstance(environmentSample);
				if (result == null) result = caseNamedElement(environmentSample);
				if (result == null) result = caseArgumentedElement(environmentSample);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EnvironentPackage.ENVIRONMENT_EXTERN_SIMULATION: {
				EnvironmentExternSimulation environmentExternSimulation = (EnvironmentExternSimulation)theEObject;
				T result = caseEnvironmentExternSimulation(environmentExternSimulation);
				if (result == null) result = caseElementaryEnvironmentComponentInstance(environmentExternSimulation);
				if (result == null) result = caseEnvironmentComponentInstance(environmentExternSimulation);
				if (result == null) result = caseComponentInstance(environmentExternSimulation);
				if (result == null) result = caseNamedElement(environmentExternSimulation);
				if (result == null) result = caseArgumentedElement(environmentExternSimulation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EnvironentPackage.SIMULATION_RULE: {
				SimulationRule simulationRule = (SimulationRule)theEObject;
				T result = caseSimulationRule(simulationRule);
				if (result == null) result = caseEnvironmentRule(simulationRule);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EnvironentPackage.FILTER: {
				Filter filter = (Filter)theEObject;
				T result = caseFilter(filter);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EnvironentPackage.SIMULATION: {
				Simulation simulation = (Simulation)theEObject;
				T result = caseSimulation(simulation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EnvironentPackage.ENVIRONMENT_PERIODIC_EVENT_SOURCE: {
				EnvironmentPeriodicEventSource environmentPeriodicEventSource = (EnvironmentPeriodicEventSource)theEObject;
				T result = caseEnvironmentPeriodicEventSource(environmentPeriodicEventSource);
				if (result == null) result = caseElementaryEnvironmentComponentInstance(environmentPeriodicEventSource);
				if (result == null) result = caseEnvironmentComponentInstance(environmentPeriodicEventSource);
				if (result == null) result = caseComponentInstance(environmentPeriodicEventSource);
				if (result == null) result = caseNamedElement(environmentPeriodicEventSource);
				if (result == null) result = caseArgumentedElement(environmentPeriodicEventSource);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EnvironentPackage.PERIODIC_SIMULATION: {
				PeriodicSimulation periodicSimulation = (PeriodicSimulation)theEObject;
				T result = casePeriodicSimulation(periodicSimulation);
				if (result == null) result = caseSimulation(periodicSimulation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Environment Event Source</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Environment Event Source</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEnvironmentEventSource(EnvironmentEventSource object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Environment Composite Component</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Environment Composite Component</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEnvironmentCompositeComponent(EnvironmentCompositeComponent object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Environment Component Instance</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Environment Component Instance</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEnvironmentComponentInstance(EnvironmentComponentInstance object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Environment Composite Component Instance</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Environment Composite Component Instance</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEnvironmentCompositeComponentInstance(EnvironmentCompositeComponentInstance object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Environment Delay</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Environment Delay</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEnvironmentDelay(EnvironmentDelay object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Environment Switch</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Environment Switch</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEnvironmentSwitch(EnvironmentSwitch object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Elementary Environment Component Instance</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Elementary Environment Component Instance</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseElementaryEnvironmentComponentInstance(ElementaryEnvironmentComponentInstance object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Event Filter</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Event Filter</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEventFilter(EventFilter object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Stochastic Rule</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Stochastic Rule</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStochasticRule(StochasticRule object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Port Filter</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Port Filter</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePortFilter(PortFilter object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Component Filter</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Component Filter</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseComponentFilter(ComponentFilter object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Environment Rule</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Environment Rule</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEnvironmentRule(EnvironmentRule object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Environment Sample</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Environment Sample</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEnvironmentSample(EnvironmentSample object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Environment Extern Simulation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Environment Extern Simulation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEnvironmentExternSimulation(EnvironmentExternSimulation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Simulation Rule</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Simulation Rule</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSimulationRule(SimulationRule object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Filter</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Filter</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFilter(Filter object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Simulation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Simulation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSimulation(Simulation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Environment Periodic Event Source</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Environment Periodic Event Source</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEnvironmentPeriodicEventSource(EnvironmentPeriodicEventSource object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Periodic Simulation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Periodic Simulation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePeriodicSimulation(PeriodicSimulation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Named Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Named Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNamedElement(NamedElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Argumented Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Argumented Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseArgumentedElement(ArgumentedElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Component Instance</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Component Instance</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseComponentInstance(ComponentInstance object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Parametric Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Parametric Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseParametricElement(ParametricElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Component</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Component</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseComponent(Component object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Composite Component</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Composite Component</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCompositeComponent(CompositeComponent object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Synchronous Component</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Synchronous Component</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSynchronousComponent(SynchronousComponent object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Abstract Synchronous Composite Component</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Abstract Synchronous Composite Component</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAbstractSynchronousCompositeComponent(AbstractSynchronousCompositeComponent object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Cascade Composite Component</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Cascade Composite Component</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCascadeCompositeComponent(CascadeCompositeComponent object) {
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

} //EnvironentSwitch
