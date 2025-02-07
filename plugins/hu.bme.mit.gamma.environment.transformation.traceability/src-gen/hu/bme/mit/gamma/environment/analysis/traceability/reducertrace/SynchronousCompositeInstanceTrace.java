/**
 */
package hu.bme.mit.gamma.environment.analysis.traceability.reducertrace;

import hu.bme.mit.gamma.environment.model.EnvironmentSynchronousCompositeComponentInstance;

import hu.bme.mit.gamma.statechart.composite.SynchronousComponentInstance;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Synchronous Composite Instance Trace</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.SynchronousCompositeInstanceTrace#getSource <em>Source</em>}</li>
 *   <li>{@link hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.SynchronousCompositeInstanceTrace#getTarget <em>Target</em>}</li>
 * </ul>
 *
 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.ReducerTracePackage#getSynchronousCompositeInstanceTrace()
 * @model
 * @generated
 */
public interface SynchronousCompositeInstanceTrace extends AbstractTrace {
	/**
	 * Returns the value of the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Source</em>' reference.
	 * @see #setSource(EnvironmentSynchronousCompositeComponentInstance)
	 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.ReducerTracePackage#getSynchronousCompositeInstanceTrace_Source()
	 * @model required="true"
	 * @generated
	 */
	EnvironmentSynchronousCompositeComponentInstance getSource();

	/**
	 * Sets the value of the '{@link hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.SynchronousCompositeInstanceTrace#getSource <em>Source</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source</em>' reference.
	 * @see #getSource()
	 * @generated
	 */
	void setSource(EnvironmentSynchronousCompositeComponentInstance value);

	/**
	 * Returns the value of the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target</em>' reference.
	 * @see #setTarget(SynchronousComponentInstance)
	 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.ReducerTracePackage#getSynchronousCompositeInstanceTrace_Target()
	 * @model required="true"
	 * @generated
	 */
	SynchronousComponentInstance getTarget();

	/**
	 * Sets the value of the '{@link hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.SynchronousCompositeInstanceTrace#getTarget <em>Target</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target</em>' reference.
	 * @see #getTarget()
	 * @generated
	 */
	void setTarget(SynchronousComponentInstance value);

} // SynchronousCompositeInstanceTrace
