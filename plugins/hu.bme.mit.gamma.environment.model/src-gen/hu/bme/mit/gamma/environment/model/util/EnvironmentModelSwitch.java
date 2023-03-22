/**
 */
package hu.bme.mit.gamma.environment.model.util;

import hu.bme.mit.gamma.environment.model.*;

import hu.bme.mit.gamma.expression.model.ArgumentedElement;
import hu.bme.mit.gamma.expression.model.NamedElement;
import hu.bme.mit.gamma.expression.model.ParametricElement;

import hu.bme.mit.gamma.statechart.composite.AbstractAsynchronousCompositeComponent;
import hu.bme.mit.gamma.statechart.composite.AbstractSynchronousCompositeComponent;
import hu.bme.mit.gamma.statechart.composite.AsynchronousComponent;
import hu.bme.mit.gamma.statechart.composite.AsynchronousCompositeComponent;
import hu.bme.mit.gamma.statechart.composite.CascadeCompositeComponent;
import hu.bme.mit.gamma.statechart.composite.ComponentInstance;
import hu.bme.mit.gamma.statechart.composite.CompositeComponent;
import hu.bme.mit.gamma.statechart.composite.SchedulableCompositeComponent;
import hu.bme.mit.gamma.statechart.composite.SynchronousComponent;
import hu.bme.mit.gamma.statechart.composite.SynchronousCompositeComponent;

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
 * @see hu.bme.mit.gamma.environment.model.EnvironmentModelPackage
 * @generated
 */
public class EnvironmentModelSwitch<T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static EnvironmentModelPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EnvironmentModelSwitch() {
		if (modelPackage == null) {
			modelPackage = EnvironmentModelPackage.eINSTANCE;
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
			case EnvironmentModelPackage.ENVIRONMENT_EVENT_SOURCE: {
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
			case EnvironmentModelPackage.ENVIRONMENT_CASCADE_COMPOSITE_COMPONENT: {
				EnvironmentCascadeCompositeComponent environmentCascadeCompositeComponent = (EnvironmentCascadeCompositeComponent)theEObject;
				T result = caseEnvironmentCascadeCompositeComponent(environmentCascadeCompositeComponent);
				if (result == null) result = caseCascadeCompositeComponent(environmentCascadeCompositeComponent);
				if (result == null) result = caseAbstractEnvironmentCompositeComponent(environmentCascadeCompositeComponent);
				if (result == null) result = caseAbstractSynchronousCompositeComponent(environmentCascadeCompositeComponent);
				if (result == null) result = caseSchedulableCompositeComponent(environmentCascadeCompositeComponent);
				if (result == null) result = caseEnvironmentComponent(environmentCascadeCompositeComponent);
				if (result == null) result = caseCompositeComponent(environmentCascadeCompositeComponent);
				if (result == null) result = caseSynchronousComponent(environmentCascadeCompositeComponent);
				if (result == null) result = caseComponent(environmentCascadeCompositeComponent);
				if (result == null) result = caseNamedElement(environmentCascadeCompositeComponent);
				if (result == null) result = caseParametricElement(environmentCascadeCompositeComponent);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EnvironmentModelPackage.ENVIRONMENT_COMPONENT_INSTANCE: {
				EnvironmentComponentInstance environmentComponentInstance = (EnvironmentComponentInstance)theEObject;
				T result = caseEnvironmentComponentInstance(environmentComponentInstance);
				if (result == null) result = caseComponentInstance(environmentComponentInstance);
				if (result == null) result = caseNamedElement(environmentComponentInstance);
				if (result == null) result = caseArgumentedElement(environmentComponentInstance);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EnvironmentModelPackage.ENVIRONMENT_CASCADE_COMPOSITE_COMPONENT_INSTANCE: {
				EnvironmentCascadeCompositeComponentInstance environmentCascadeCompositeComponentInstance = (EnvironmentCascadeCompositeComponentInstance)theEObject;
				T result = caseEnvironmentCascadeCompositeComponentInstance(environmentCascadeCompositeComponentInstance);
				if (result == null) result = caseAbstractEnvironmentCompositeComponentInstance(environmentCascadeCompositeComponentInstance);
				if (result == null) result = caseEnvironmentComponentInstance(environmentCascadeCompositeComponentInstance);
				if (result == null) result = caseComponentInstance(environmentCascadeCompositeComponentInstance);
				if (result == null) result = caseNamedElement(environmentCascadeCompositeComponentInstance);
				if (result == null) result = caseArgumentedElement(environmentCascadeCompositeComponentInstance);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EnvironmentModelPackage.ENVIRONMENT_DELAY: {
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
			case EnvironmentModelPackage.ENVIRONMENT_SWITCH: {
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
			case EnvironmentModelPackage.ELEMENTARY_ENVIRONMENT_COMPONENT_INSTANCE: {
				ElementaryEnvironmentComponentInstance elementaryEnvironmentComponentInstance = (ElementaryEnvironmentComponentInstance)theEObject;
				T result = caseElementaryEnvironmentComponentInstance(elementaryEnvironmentComponentInstance);
				if (result == null) result = caseEnvironmentComponentInstance(elementaryEnvironmentComponentInstance);
				if (result == null) result = caseComponentInstance(elementaryEnvironmentComponentInstance);
				if (result == null) result = caseNamedElement(elementaryEnvironmentComponentInstance);
				if (result == null) result = caseArgumentedElement(elementaryEnvironmentComponentInstance);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EnvironmentModelPackage.EVENT_FILTER: {
				EventFilter eventFilter = (EventFilter)theEObject;
				T result = caseEventFilter(eventFilter);
				if (result == null) result = caseFilter(eventFilter);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EnvironmentModelPackage.STOCHASTIC_RULE: {
				StochasticRule stochasticRule = (StochasticRule)theEObject;
				T result = caseStochasticRule(stochasticRule);
				if (result == null) result = caseEnvironmentRule(stochasticRule);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EnvironmentModelPackage.PORT_FILTER: {
				PortFilter portFilter = (PortFilter)theEObject;
				T result = casePortFilter(portFilter);
				if (result == null) result = caseFilter(portFilter);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EnvironmentModelPackage.COMPONENT_FILTER: {
				ComponentFilter componentFilter = (ComponentFilter)theEObject;
				T result = caseComponentFilter(componentFilter);
				if (result == null) result = caseFilter(componentFilter);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EnvironmentModelPackage.ENVIRONMENT_RULE: {
				EnvironmentRule environmentRule = (EnvironmentRule)theEObject;
				T result = caseEnvironmentRule(environmentRule);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EnvironmentModelPackage.ENVIRONMENT_SAMPLE: {
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
			case EnvironmentModelPackage.ENVIRONMENT_EXTERN_SIMULATION: {
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
			case EnvironmentModelPackage.SIMULATION_RULE: {
				SimulationRule simulationRule = (SimulationRule)theEObject;
				T result = caseSimulationRule(simulationRule);
				if (result == null) result = caseEnvironmentRule(simulationRule);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EnvironmentModelPackage.FILTER: {
				Filter filter = (Filter)theEObject;
				T result = caseFilter(filter);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EnvironmentModelPackage.SIMULATION: {
				Simulation simulation = (Simulation)theEObject;
				T result = caseSimulation(simulation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EnvironmentModelPackage.ENVIRONMENT_PERIODIC_EVENT_SOURCE: {
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
			case EnvironmentModelPackage.PERIODIC_SIMULATION: {
				PeriodicSimulation periodicSimulation = (PeriodicSimulation)theEObject;
				T result = casePeriodicSimulation(periodicSimulation);
				if (result == null) result = caseSimulation(periodicSimulation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EnvironmentModelPackage.ENVIRONMENT_SYNCHRONOUS_COMPOSITE_COMPONENT: {
				EnvironmentSynchronousCompositeComponent environmentSynchronousCompositeComponent = (EnvironmentSynchronousCompositeComponent)theEObject;
				T result = caseEnvironmentSynchronousCompositeComponent(environmentSynchronousCompositeComponent);
				if (result == null) result = caseAbstractEnvironmentCompositeComponent(environmentSynchronousCompositeComponent);
				if (result == null) result = caseSynchronousCompositeComponent(environmentSynchronousCompositeComponent);
				if (result == null) result = caseEnvironmentComponent(environmentSynchronousCompositeComponent);
				if (result == null) result = caseAbstractSynchronousCompositeComponent(environmentSynchronousCompositeComponent);
				if (result == null) result = caseCompositeComponent(environmentSynchronousCompositeComponent);
				if (result == null) result = caseSynchronousComponent(environmentSynchronousCompositeComponent);
				if (result == null) result = caseComponent(environmentSynchronousCompositeComponent);
				if (result == null) result = caseNamedElement(environmentSynchronousCompositeComponent);
				if (result == null) result = caseParametricElement(environmentSynchronousCompositeComponent);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EnvironmentModelPackage.ABSTRACT_ENVIRONMENT_COMPOSITE_COMPONENT: {
				AbstractEnvironmentCompositeComponent abstractEnvironmentCompositeComponent = (AbstractEnvironmentCompositeComponent)theEObject;
				T result = caseAbstractEnvironmentCompositeComponent(abstractEnvironmentCompositeComponent);
				if (result == null) result = caseEnvironmentComponent(abstractEnvironmentCompositeComponent);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EnvironmentModelPackage.ENVIRONMENT_COMPONENT: {
				EnvironmentComponent environmentComponent = (EnvironmentComponent)theEObject;
				T result = caseEnvironmentComponent(environmentComponent);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EnvironmentModelPackage.ENVIRONMENT_ASYNCHRONOUS_COMPOSITE_COMPONENT: {
				EnvironmentAsynchronousCompositeComponent environmentAsynchronousCompositeComponent = (EnvironmentAsynchronousCompositeComponent)theEObject;
				T result = caseEnvironmentAsynchronousCompositeComponent(environmentAsynchronousCompositeComponent);
				if (result == null) result = caseAbstractEnvironmentCompositeComponent(environmentAsynchronousCompositeComponent);
				if (result == null) result = caseAsynchronousCompositeComponent(environmentAsynchronousCompositeComponent);
				if (result == null) result = caseEnvironmentComponent(environmentAsynchronousCompositeComponent);
				if (result == null) result = caseAbstractAsynchronousCompositeComponent(environmentAsynchronousCompositeComponent);
				if (result == null) result = caseCompositeComponent(environmentAsynchronousCompositeComponent);
				if (result == null) result = caseAsynchronousComponent(environmentAsynchronousCompositeComponent);
				if (result == null) result = caseComponent(environmentAsynchronousCompositeComponent);
				if (result == null) result = caseNamedElement(environmentAsynchronousCompositeComponent);
				if (result == null) result = caseParametricElement(environmentAsynchronousCompositeComponent);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EnvironmentModelPackage.ABSTRACT_ENVIRONMENT_COMPOSITE_COMPONENT_INSTANCE: {
				AbstractEnvironmentCompositeComponentInstance abstractEnvironmentCompositeComponentInstance = (AbstractEnvironmentCompositeComponentInstance)theEObject;
				T result = caseAbstractEnvironmentCompositeComponentInstance(abstractEnvironmentCompositeComponentInstance);
				if (result == null) result = caseEnvironmentComponentInstance(abstractEnvironmentCompositeComponentInstance);
				if (result == null) result = caseComponentInstance(abstractEnvironmentCompositeComponentInstance);
				if (result == null) result = caseNamedElement(abstractEnvironmentCompositeComponentInstance);
				if (result == null) result = caseArgumentedElement(abstractEnvironmentCompositeComponentInstance);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EnvironmentModelPackage.ENVIRONMENT_SYNCHRONOUS_COMPOSITE_COMPONENT_INSTANCE: {
				EnvironmentSynchronousCompositeComponentInstance environmentSynchronousCompositeComponentInstance = (EnvironmentSynchronousCompositeComponentInstance)theEObject;
				T result = caseEnvironmentSynchronousCompositeComponentInstance(environmentSynchronousCompositeComponentInstance);
				if (result == null) result = caseAbstractEnvironmentCompositeComponentInstance(environmentSynchronousCompositeComponentInstance);
				if (result == null) result = caseEnvironmentComponentInstance(environmentSynchronousCompositeComponentInstance);
				if (result == null) result = caseComponentInstance(environmentSynchronousCompositeComponentInstance);
				if (result == null) result = caseNamedElement(environmentSynchronousCompositeComponentInstance);
				if (result == null) result = caseArgumentedElement(environmentSynchronousCompositeComponentInstance);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EnvironmentModelPackage.ENVIRONMENT_ASYNCHRONOUS_COMPOSITE_COMPONENT_INSTANCE: {
				EnvironmentAsynchronousCompositeComponentInstance environmentAsynchronousCompositeComponentInstance = (EnvironmentAsynchronousCompositeComponentInstance)theEObject;
				T result = caseEnvironmentAsynchronousCompositeComponentInstance(environmentAsynchronousCompositeComponentInstance);
				if (result == null) result = caseAbstractEnvironmentCompositeComponentInstance(environmentAsynchronousCompositeComponentInstance);
				if (result == null) result = caseEnvironmentComponentInstance(environmentAsynchronousCompositeComponentInstance);
				if (result == null) result = caseComponentInstance(environmentAsynchronousCompositeComponentInstance);
				if (result == null) result = caseNamedElement(environmentAsynchronousCompositeComponentInstance);
				if (result == null) result = caseArgumentedElement(environmentAsynchronousCompositeComponentInstance);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EnvironmentModelPackage.PARAMETER_FILTER: {
				ParameterFilter parameterFilter = (ParameterFilter)theEObject;
				T result = caseParameterFilter(parameterFilter);
				if (result == null) result = caseEventFilter(parameterFilter);
				if (result == null) result = caseFilter(parameterFilter);
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
	 * Returns the result of interpreting the object as an instance of '<em>Environment Cascade Composite Component</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Environment Cascade Composite Component</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEnvironmentCascadeCompositeComponent(EnvironmentCascadeCompositeComponent object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Environment Cascade Composite Component Instance</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Environment Cascade Composite Component Instance</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEnvironmentCascadeCompositeComponentInstance(EnvironmentCascadeCompositeComponentInstance object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Environment Synchronous Composite Component</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Environment Synchronous Composite Component</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEnvironmentSynchronousCompositeComponent(EnvironmentSynchronousCompositeComponent object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Abstract Environment Composite Component</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Abstract Environment Composite Component</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAbstractEnvironmentCompositeComponent(AbstractEnvironmentCompositeComponent object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Environment Component</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Environment Component</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEnvironmentComponent(EnvironmentComponent object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Environment Asynchronous Composite Component</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Environment Asynchronous Composite Component</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEnvironmentAsynchronousCompositeComponent(EnvironmentAsynchronousCompositeComponent object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Abstract Environment Composite Component Instance</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Abstract Environment Composite Component Instance</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAbstractEnvironmentCompositeComponentInstance(AbstractEnvironmentCompositeComponentInstance object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Environment Synchronous Composite Component Instance</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Environment Synchronous Composite Component Instance</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEnvironmentSynchronousCompositeComponentInstance(EnvironmentSynchronousCompositeComponentInstance object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Environment Asynchronous Composite Component Instance</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Environment Asynchronous Composite Component Instance</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEnvironmentAsynchronousCompositeComponentInstance(EnvironmentAsynchronousCompositeComponentInstance object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Parameter Filter</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Parameter Filter</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseParameterFilter(ParameterFilter object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Schedulable Composite Component</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Schedulable Composite Component</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSchedulableCompositeComponent(SchedulableCompositeComponent object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Synchronous Composite Component</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Synchronous Composite Component</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSynchronousCompositeComponent(SynchronousCompositeComponent object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Asynchronous Component</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Asynchronous Component</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAsynchronousComponent(AsynchronousComponent object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Abstract Asynchronous Composite Component</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Abstract Asynchronous Composite Component</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAbstractAsynchronousCompositeComponent(AbstractAsynchronousCompositeComponent object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Asynchronous Composite Component</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Asynchronous Composite Component</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAsynchronousCompositeComponent(AsynchronousCompositeComponent object) {
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

} //EnvironmentModelSwitch
