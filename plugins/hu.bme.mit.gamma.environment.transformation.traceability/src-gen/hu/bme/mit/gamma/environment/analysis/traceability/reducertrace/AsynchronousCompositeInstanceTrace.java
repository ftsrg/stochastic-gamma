/**
 */
package hu.bme.mit.gamma.environment.analysis.traceability.reducertrace;

import hu.bme.mit.gamma.environment.model.EnvironmentAsynchronousCompositeComponentInstance;

import hu.bme.mit.gamma.statechart.composite.AsynchronousComponentInstance;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Asynchronous Composite Instance Trace</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.AsynchronousCompositeInstanceTrace#getSource <em>Source</em>}</li>
 *   <li>{@link hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.AsynchronousCompositeInstanceTrace#getTarget <em>Target</em>}</li>
 * </ul>
 *
 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.ReducerTracePackage#getAsynchronousCompositeInstanceTrace()
 * @model
 * @generated
 */
public interface AsynchronousCompositeInstanceTrace extends AbstractTrace {
	/**
	 * Returns the value of the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Source</em>' reference.
	 * @see #setSource(EnvironmentAsynchronousCompositeComponentInstance)
	 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.ReducerTracePackage#getAsynchronousCompositeInstanceTrace_Source()
	 * @model required="true"
	 * @generated
	 */
	EnvironmentAsynchronousCompositeComponentInstance getSource();

	/**
	 * Sets the value of the '{@link hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.AsynchronousCompositeInstanceTrace#getSource <em>Source</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source</em>' reference.
	 * @see #getSource()
	 * @generated
	 */
	void setSource(EnvironmentAsynchronousCompositeComponentInstance value);

	/**
	 * Returns the value of the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target</em>' reference.
	 * @see #setTarget(AsynchronousComponentInstance)
	 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.ReducerTracePackage#getAsynchronousCompositeInstanceTrace_Target()
	 * @model required="true"
	 * @generated
	 */
	AsynchronousComponentInstance getTarget();

	/**
	 * Sets the value of the '{@link hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.AsynchronousCompositeInstanceTrace#getTarget <em>Target</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target</em>' reference.
	 * @see #getTarget()
	 * @generated
	 */
	void setTarget(AsynchronousComponentInstance value);

} // AsynchronousCompositeInstanceTrace
