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

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see hu.bme.mit.gamma.environment.model.EnvironentPackage
 * @generated
 */
public class EnvironentAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static EnvironentPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EnvironentAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = EnvironentPackage.eINSTANCE;
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
	protected EnvironentSwitch<Adapter> modelSwitch =
		new EnvironentSwitch<Adapter>() {
			@Override
			public Adapter caseEnvironmentEventSource(EnvironmentEventSource object) {
				return createEnvironmentEventSourceAdapter();
			}
			@Override
			public Adapter caseEnvironmentCompositeComponent(EnvironmentCompositeComponent object) {
				return createEnvironmentCompositeComponentAdapter();
			}
			@Override
			public Adapter caseEnvironmentComponentInstance(EnvironmentComponentInstance object) {
				return createEnvironmentComponentInstanceAdapter();
			}
			@Override
			public Adapter caseEnvironmentCompositeComponentInstance(EnvironmentCompositeComponentInstance object) {
				return createEnvironmentCompositeComponentInstanceAdapter();
			}
			@Override
			public Adapter caseEnvironmentDelay(EnvironmentDelay object) {
				return createEnvironmentDelayAdapter();
			}
			@Override
			public Adapter caseEnvironmentSwitch(EnvironmentSwitch object) {
				return createEnvironmentSwitchAdapter();
			}
			@Override
			public Adapter caseElementaryEnvironmentComponentInstance(ElementaryEnvironmentComponentInstance object) {
				return createElementaryEnvironmentComponentInstanceAdapter();
			}
			@Override
			public Adapter caseEventFilter(EventFilter object) {
				return createEventFilterAdapter();
			}
			@Override
			public Adapter caseStochasticRule(StochasticRule object) {
				return createStochasticRuleAdapter();
			}
			@Override
			public Adapter casePortFilter(PortFilter object) {
				return createPortFilterAdapter();
			}
			@Override
			public Adapter caseComponentFilter(ComponentFilter object) {
				return createComponentFilterAdapter();
			}
			@Override
			public Adapter caseEnvironmentRule(EnvironmentRule object) {
				return createEnvironmentRuleAdapter();
			}
			@Override
			public Adapter caseEnvironmentSample(EnvironmentSample object) {
				return createEnvironmentSampleAdapter();
			}
			@Override
			public Adapter caseEnvironmentExternSimulation(EnvironmentExternSimulation object) {
				return createEnvironmentExternSimulationAdapter();
			}
			@Override
			public Adapter caseSimulationRule(SimulationRule object) {
				return createSimulationRuleAdapter();
			}
			@Override
			public Adapter caseFilter(Filter object) {
				return createFilterAdapter();
			}
			@Override
			public Adapter caseSimulation(Simulation object) {
				return createSimulationAdapter();
			}
			@Override
			public Adapter caseEnvironmentPeriodicEventSource(EnvironmentPeriodicEventSource object) {
				return createEnvironmentPeriodicEventSourceAdapter();
			}
			@Override
			public Adapter casePeriodicSimulation(PeriodicSimulation object) {
				return createPeriodicSimulationAdapter();
			}
			@Override
			public Adapter caseNamedElement(NamedElement object) {
				return createNamedElementAdapter();
			}
			@Override
			public Adapter caseArgumentedElement(ArgumentedElement object) {
				return createArgumentedElementAdapter();
			}
			@Override
			public Adapter caseComponentInstance(ComponentInstance object) {
				return createComponentInstanceAdapter();
			}
			@Override
			public Adapter caseParametricElement(ParametricElement object) {
				return createParametricElementAdapter();
			}
			@Override
			public Adapter caseComponent(Component object) {
				return createComponentAdapter();
			}
			@Override
			public Adapter caseCompositeComponent(CompositeComponent object) {
				return createCompositeComponentAdapter();
			}
			@Override
			public Adapter caseSynchronousComponent(SynchronousComponent object) {
				return createSynchronousComponentAdapter();
			}
			@Override
			public Adapter caseAbstractSynchronousCompositeComponent(AbstractSynchronousCompositeComponent object) {
				return createAbstractSynchronousCompositeComponentAdapter();
			}
			@Override
			public Adapter caseCascadeCompositeComponent(CascadeCompositeComponent object) {
				return createCascadeCompositeComponentAdapter();
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
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.environment.model.EnvironmentEventSource <em>Environment Event Source</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.environment.model.EnvironmentEventSource
	 * @generated
	 */
	public Adapter createEnvironmentEventSourceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.environment.model.EnvironmentCompositeComponent <em>Environment Composite Component</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.environment.model.EnvironmentCompositeComponent
	 * @generated
	 */
	public Adapter createEnvironmentCompositeComponentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.environment.model.EnvironmentComponentInstance <em>Environment Component Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.environment.model.EnvironmentComponentInstance
	 * @generated
	 */
	public Adapter createEnvironmentComponentInstanceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.environment.model.EnvironmentCompositeComponentInstance <em>Environment Composite Component Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.environment.model.EnvironmentCompositeComponentInstance
	 * @generated
	 */
	public Adapter createEnvironmentCompositeComponentInstanceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.environment.model.EnvironmentDelay <em>Environment Delay</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.environment.model.EnvironmentDelay
	 * @generated
	 */
	public Adapter createEnvironmentDelayAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.environment.model.EnvironmentSwitch <em>Environment Switch</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.environment.model.EnvironmentSwitch
	 * @generated
	 */
	public Adapter createEnvironmentSwitchAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.environment.model.ElementaryEnvironmentComponentInstance <em>Elementary Environment Component Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.environment.model.ElementaryEnvironmentComponentInstance
	 * @generated
	 */
	public Adapter createElementaryEnvironmentComponentInstanceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.environment.model.EventFilter <em>Event Filter</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.environment.model.EventFilter
	 * @generated
	 */
	public Adapter createEventFilterAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.environment.model.StochasticRule <em>Stochastic Rule</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.environment.model.StochasticRule
	 * @generated
	 */
	public Adapter createStochasticRuleAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.environment.model.PortFilter <em>Port Filter</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.environment.model.PortFilter
	 * @generated
	 */
	public Adapter createPortFilterAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.environment.model.ComponentFilter <em>Component Filter</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.environment.model.ComponentFilter
	 * @generated
	 */
	public Adapter createComponentFilterAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.environment.model.EnvironmentRule <em>Environment Rule</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.environment.model.EnvironmentRule
	 * @generated
	 */
	public Adapter createEnvironmentRuleAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.environment.model.EnvironmentSample <em>Environment Sample</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.environment.model.EnvironmentSample
	 * @generated
	 */
	public Adapter createEnvironmentSampleAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.environment.model.EnvironmentExternSimulation <em>Environment Extern Simulation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.environment.model.EnvironmentExternSimulation
	 * @generated
	 */
	public Adapter createEnvironmentExternSimulationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.environment.model.SimulationRule <em>Simulation Rule</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.environment.model.SimulationRule
	 * @generated
	 */
	public Adapter createSimulationRuleAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.environment.model.Filter <em>Filter</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.environment.model.Filter
	 * @generated
	 */
	public Adapter createFilterAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.environment.model.Simulation <em>Simulation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.environment.model.Simulation
	 * @generated
	 */
	public Adapter createSimulationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.environment.model.EnvironmentPeriodicEventSource <em>Environment Periodic Event Source</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.environment.model.EnvironmentPeriodicEventSource
	 * @generated
	 */
	public Adapter createEnvironmentPeriodicEventSourceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.environment.model.PeriodicSimulation <em>Periodic Simulation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.environment.model.PeriodicSimulation
	 * @generated
	 */
	public Adapter createPeriodicSimulationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.expression.model.NamedElement <em>Named Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.expression.model.NamedElement
	 * @generated
	 */
	public Adapter createNamedElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.expression.model.ArgumentedElement <em>Argumented Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.expression.model.ArgumentedElement
	 * @generated
	 */
	public Adapter createArgumentedElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.statechart.composite.ComponentInstance <em>Component Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.statechart.composite.ComponentInstance
	 * @generated
	 */
	public Adapter createComponentInstanceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.expression.model.ParametricElement <em>Parametric Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.expression.model.ParametricElement
	 * @generated
	 */
	public Adapter createParametricElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.statechart.interface_.Component <em>Component</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.statechart.interface_.Component
	 * @generated
	 */
	public Adapter createComponentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.statechart.composite.CompositeComponent <em>Composite Component</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.statechart.composite.CompositeComponent
	 * @generated
	 */
	public Adapter createCompositeComponentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.statechart.composite.SynchronousComponent <em>Synchronous Component</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.statechart.composite.SynchronousComponent
	 * @generated
	 */
	public Adapter createSynchronousComponentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.statechart.composite.AbstractSynchronousCompositeComponent <em>Abstract Synchronous Composite Component</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.statechart.composite.AbstractSynchronousCompositeComponent
	 * @generated
	 */
	public Adapter createAbstractSynchronousCompositeComponentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.statechart.composite.CascadeCompositeComponent <em>Cascade Composite Component</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.statechart.composite.CascadeCompositeComponent
	 * @generated
	 */
	public Adapter createCascadeCompositeComponentAdapter() {
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

} //EnvironentAdapterFactory