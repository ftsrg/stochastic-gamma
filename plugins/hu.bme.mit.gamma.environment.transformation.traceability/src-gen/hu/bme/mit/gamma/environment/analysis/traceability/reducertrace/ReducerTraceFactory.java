/**
 */
package hu.bme.mit.gamma.environment.analysis.traceability.reducertrace;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.ReducerTracePackage
 * @generated
 */
public interface ReducerTraceFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ReducerTraceFactory eINSTANCE = hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.impl.ReducerTraceFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Reducer Trace</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Reducer Trace</em>'.
	 * @generated
	 */
	ReducerTrace createReducerTrace();

	/**
	 * Returns a new object of class '<em>Elementary Trace</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Elementary Trace</em>'.
	 * @generated
	 */
	ElementaryTrace createElementaryTrace();

	/**
	 * Returns a new object of class '<em>Asynchronous Composite Instance Trace</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Asynchronous Composite Instance Trace</em>'.
	 * @generated
	 */
	AsynchronousCompositeInstanceTrace createAsynchronousCompositeInstanceTrace();

	/**
	 * Returns a new object of class '<em>Synchronous Composite Instance Trace</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Synchronous Composite Instance Trace</em>'.
	 * @generated
	 */
	SynchronousCompositeInstanceTrace createSynchronousCompositeInstanceTrace();

	/**
	 * Returns a new object of class '<em>Cascade Composite Instance Trace</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Cascade Composite Instance Trace</em>'.
	 * @generated
	 */
	CascadeCompositeInstanceTrace createCascadeCompositeInstanceTrace();

	/**
	 * Returns a new object of class '<em>Asynchronous Composite Component Trace</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Asynchronous Composite Component Trace</em>'.
	 * @generated
	 */
	AsynchronousCompositeComponentTrace createAsynchronousCompositeComponentTrace();

	/**
	 * Returns a new object of class '<em>Synchronous Composite Component Trace</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Synchronous Composite Component Trace</em>'.
	 * @generated
	 */
	SynchronousCompositeComponentTrace createSynchronousCompositeComponentTrace();

	/**
	 * Returns a new object of class '<em>Cascade Composite Component Trace</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Cascade Composite Component Trace</em>'.
	 * @generated
	 */
	CascadeCompositeComponentTrace createCascadeCompositeComponentTrace();

	/**
	 * Returns a new object of class '<em>Simple Channel Trace</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Simple Channel Trace</em>'.
	 * @generated
	 */
	SimpleChannelTrace createSimpleChannelTrace();

	/**
	 * Returns a new object of class '<em>Binding Trace</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Binding Trace</em>'.
	 * @generated
	 */
	BindingTrace createBindingTrace();

	/**
	 * Returns a new object of class '<em>Broadcast Channel Trace</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Broadcast Channel Trace</em>'.
	 * @generated
	 */
	BroadcastChannelTrace createBroadcastChannelTrace();

	/**
	 * Returns a new object of class '<em>Package Trace</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Package Trace</em>'.
	 * @generated
	 */
	PackageTrace createPackageTrace();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	ReducerTracePackage getReducerTracePackage();

} //ReducerTraceFactory
