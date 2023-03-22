/**
 */
package hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.util;

import hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.ReducerTracePackage
 * @generated
 */
public class ReducerTraceAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static ReducerTracePackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReducerTraceAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = ReducerTracePackage.eINSTANCE;
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
	protected ReducerTraceSwitch<Adapter> modelSwitch =
		new ReducerTraceSwitch<Adapter>() {
			@Override
			public Adapter caseReducerTrace(ReducerTrace object) {
				return createReducerTraceAdapter();
			}
			@Override
			public Adapter caseAbstractTrace(AbstractTrace object) {
				return createAbstractTraceAdapter();
			}
			@Override
			public Adapter caseElementaryTrace(ElementaryTrace object) {
				return createElementaryTraceAdapter();
			}
			@Override
			public Adapter caseAsynchronousCompositeInstanceTrace(AsynchronousCompositeInstanceTrace object) {
				return createAsynchronousCompositeInstanceTraceAdapter();
			}
			@Override
			public Adapter caseSynchronousCompositeInstanceTrace(SynchronousCompositeInstanceTrace object) {
				return createSynchronousCompositeInstanceTraceAdapter();
			}
			@Override
			public Adapter caseCascadeCompositeInstanceTrace(CascadeCompositeInstanceTrace object) {
				return createCascadeCompositeInstanceTraceAdapter();
			}
			@Override
			public Adapter caseAsynchronousCompositeComponentTrace(AsynchronousCompositeComponentTrace object) {
				return createAsynchronousCompositeComponentTraceAdapter();
			}
			@Override
			public Adapter caseSynchronousCompositeComponentTrace(SynchronousCompositeComponentTrace object) {
				return createSynchronousCompositeComponentTraceAdapter();
			}
			@Override
			public Adapter caseCascadeCompositeComponentTrace(CascadeCompositeComponentTrace object) {
				return createCascadeCompositeComponentTraceAdapter();
			}
			@Override
			public Adapter caseSimpleChannelTrace(SimpleChannelTrace object) {
				return createSimpleChannelTraceAdapter();
			}
			@Override
			public Adapter caseBindingTrace(BindingTrace object) {
				return createBindingTraceAdapter();
			}
			@Override
			public Adapter caseBroadcastChannelTrace(BroadcastChannelTrace object) {
				return createBroadcastChannelTraceAdapter();
			}
			@Override
			public Adapter casePackageTrace(PackageTrace object) {
				return createPackageTraceAdapter();
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
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.ReducerTrace <em>Reducer Trace</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.ReducerTrace
	 * @generated
	 */
	public Adapter createReducerTraceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.AbstractTrace <em>Abstract Trace</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.AbstractTrace
	 * @generated
	 */
	public Adapter createAbstractTraceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.ElementaryTrace <em>Elementary Trace</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.ElementaryTrace
	 * @generated
	 */
	public Adapter createElementaryTraceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.AsynchronousCompositeInstanceTrace <em>Asynchronous Composite Instance Trace</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.AsynchronousCompositeInstanceTrace
	 * @generated
	 */
	public Adapter createAsynchronousCompositeInstanceTraceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.SynchronousCompositeInstanceTrace <em>Synchronous Composite Instance Trace</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.SynchronousCompositeInstanceTrace
	 * @generated
	 */
	public Adapter createSynchronousCompositeInstanceTraceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.CascadeCompositeInstanceTrace <em>Cascade Composite Instance Trace</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.CascadeCompositeInstanceTrace
	 * @generated
	 */
	public Adapter createCascadeCompositeInstanceTraceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.AsynchronousCompositeComponentTrace <em>Asynchronous Composite Component Trace</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.AsynchronousCompositeComponentTrace
	 * @generated
	 */
	public Adapter createAsynchronousCompositeComponentTraceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.SynchronousCompositeComponentTrace <em>Synchronous Composite Component Trace</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.SynchronousCompositeComponentTrace
	 * @generated
	 */
	public Adapter createSynchronousCompositeComponentTraceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.CascadeCompositeComponentTrace <em>Cascade Composite Component Trace</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.CascadeCompositeComponentTrace
	 * @generated
	 */
	public Adapter createCascadeCompositeComponentTraceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.SimpleChannelTrace <em>Simple Channel Trace</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.SimpleChannelTrace
	 * @generated
	 */
	public Adapter createSimpleChannelTraceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.BindingTrace <em>Binding Trace</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.BindingTrace
	 * @generated
	 */
	public Adapter createBindingTraceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.BroadcastChannelTrace <em>Broadcast Channel Trace</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.BroadcastChannelTrace
	 * @generated
	 */
	public Adapter createBroadcastChannelTraceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.PackageTrace <em>Package Trace</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.PackageTrace
	 * @generated
	 */
	public Adapter createPackageTraceAdapter() {
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

} //ReducerTraceAdapterFactory
