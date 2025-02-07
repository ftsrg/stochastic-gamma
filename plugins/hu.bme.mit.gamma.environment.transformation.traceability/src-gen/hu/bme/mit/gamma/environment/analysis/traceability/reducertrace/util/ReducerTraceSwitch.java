/**
 */
package hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.util;

import hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.*;

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
 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.ReducerTracePackage
 * @generated
 */
public class ReducerTraceSwitch<T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static ReducerTracePackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReducerTraceSwitch() {
		if (modelPackage == null) {
			modelPackage = ReducerTracePackage.eINSTANCE;
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
			case ReducerTracePackage.REDUCER_TRACE: {
				ReducerTrace reducerTrace = (ReducerTrace)theEObject;
				T result = caseReducerTrace(reducerTrace);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ReducerTracePackage.ABSTRACT_TRACE: {
				AbstractTrace abstractTrace = (AbstractTrace)theEObject;
				T result = caseAbstractTrace(abstractTrace);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ReducerTracePackage.ELEMENTARY_TRACE: {
				ElementaryTrace elementaryTrace = (ElementaryTrace)theEObject;
				T result = caseElementaryTrace(elementaryTrace);
				if (result == null) result = caseAbstractTrace(elementaryTrace);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ReducerTracePackage.ASYNCHRONOUS_COMPOSITE_INSTANCE_TRACE: {
				AsynchronousCompositeInstanceTrace asynchronousCompositeInstanceTrace = (AsynchronousCompositeInstanceTrace)theEObject;
				T result = caseAsynchronousCompositeInstanceTrace(asynchronousCompositeInstanceTrace);
				if (result == null) result = caseAbstractTrace(asynchronousCompositeInstanceTrace);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ReducerTracePackage.SYNCHRONOUS_COMPOSITE_INSTANCE_TRACE: {
				SynchronousCompositeInstanceTrace synchronousCompositeInstanceTrace = (SynchronousCompositeInstanceTrace)theEObject;
				T result = caseSynchronousCompositeInstanceTrace(synchronousCompositeInstanceTrace);
				if (result == null) result = caseAbstractTrace(synchronousCompositeInstanceTrace);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ReducerTracePackage.CASCADE_COMPOSITE_INSTANCE_TRACE: {
				CascadeCompositeInstanceTrace cascadeCompositeInstanceTrace = (CascadeCompositeInstanceTrace)theEObject;
				T result = caseCascadeCompositeInstanceTrace(cascadeCompositeInstanceTrace);
				if (result == null) result = caseAbstractTrace(cascadeCompositeInstanceTrace);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ReducerTracePackage.ASYNCHRONOUS_COMPOSITE_COMPONENT_TRACE: {
				AsynchronousCompositeComponentTrace asynchronousCompositeComponentTrace = (AsynchronousCompositeComponentTrace)theEObject;
				T result = caseAsynchronousCompositeComponentTrace(asynchronousCompositeComponentTrace);
				if (result == null) result = caseAbstractTrace(asynchronousCompositeComponentTrace);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ReducerTracePackage.SYNCHRONOUS_COMPOSITE_COMPONENT_TRACE: {
				SynchronousCompositeComponentTrace synchronousCompositeComponentTrace = (SynchronousCompositeComponentTrace)theEObject;
				T result = caseSynchronousCompositeComponentTrace(synchronousCompositeComponentTrace);
				if (result == null) result = caseAbstractTrace(synchronousCompositeComponentTrace);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ReducerTracePackage.CASCADE_COMPOSITE_COMPONENT_TRACE: {
				CascadeCompositeComponentTrace cascadeCompositeComponentTrace = (CascadeCompositeComponentTrace)theEObject;
				T result = caseCascadeCompositeComponentTrace(cascadeCompositeComponentTrace);
				if (result == null) result = caseAbstractTrace(cascadeCompositeComponentTrace);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ReducerTracePackage.SIMPLE_CHANNEL_TRACE: {
				SimpleChannelTrace simpleChannelTrace = (SimpleChannelTrace)theEObject;
				T result = caseSimpleChannelTrace(simpleChannelTrace);
				if (result == null) result = caseAbstractTrace(simpleChannelTrace);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ReducerTracePackage.BINDING_TRACE: {
				BindingTrace bindingTrace = (BindingTrace)theEObject;
				T result = caseBindingTrace(bindingTrace);
				if (result == null) result = caseAbstractTrace(bindingTrace);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ReducerTracePackage.BROADCAST_CHANNEL_TRACE: {
				BroadcastChannelTrace broadcastChannelTrace = (BroadcastChannelTrace)theEObject;
				T result = caseBroadcastChannelTrace(broadcastChannelTrace);
				if (result == null) result = caseAbstractTrace(broadcastChannelTrace);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ReducerTracePackage.PACKAGE_TRACE: {
				PackageTrace packageTrace = (PackageTrace)theEObject;
				T result = casePackageTrace(packageTrace);
				if (result == null) result = caseAbstractTrace(packageTrace);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Reducer Trace</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Reducer Trace</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseReducerTrace(ReducerTrace object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Abstract Trace</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Abstract Trace</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAbstractTrace(AbstractTrace object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Elementary Trace</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Elementary Trace</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseElementaryTrace(ElementaryTrace object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Asynchronous Composite Instance Trace</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Asynchronous Composite Instance Trace</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAsynchronousCompositeInstanceTrace(AsynchronousCompositeInstanceTrace object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Synchronous Composite Instance Trace</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Synchronous Composite Instance Trace</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSynchronousCompositeInstanceTrace(SynchronousCompositeInstanceTrace object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Cascade Composite Instance Trace</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Cascade Composite Instance Trace</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCascadeCompositeInstanceTrace(CascadeCompositeInstanceTrace object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Asynchronous Composite Component Trace</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Asynchronous Composite Component Trace</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAsynchronousCompositeComponentTrace(AsynchronousCompositeComponentTrace object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Synchronous Composite Component Trace</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Synchronous Composite Component Trace</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSynchronousCompositeComponentTrace(SynchronousCompositeComponentTrace object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Cascade Composite Component Trace</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Cascade Composite Component Trace</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCascadeCompositeComponentTrace(CascadeCompositeComponentTrace object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Simple Channel Trace</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Simple Channel Trace</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSimpleChannelTrace(SimpleChannelTrace object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Binding Trace</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Binding Trace</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBindingTrace(BindingTrace object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Broadcast Channel Trace</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Broadcast Channel Trace</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBroadcastChannelTrace(BroadcastChannelTrace object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Package Trace</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Package Trace</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePackageTrace(PackageTrace object) {
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

} //ReducerTraceSwitch
