/**
 */
package hu.bme.mit.gamma.environment.analysis.traceability.reducertrace;

import hu.bme.mit.gamma.environment.model.EnvironmentCascadeCompositeComponentInstance;

import hu.bme.mit.gamma.statechart.composite.SynchronousComponentInstance;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Cascade Composite Instance Trace</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.CascadeCompositeInstanceTrace#getTarget <em>Target</em>}</li>
 *   <li>{@link hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.CascadeCompositeInstanceTrace#getSource <em>Source</em>}</li>
 * </ul>
 *
 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.ReducerTracePackage#getCascadeCompositeInstanceTrace()
 * @model
 * @generated
 */
public interface CascadeCompositeInstanceTrace extends AbstractTrace {
	/**
	 * Returns the value of the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target</em>' reference.
	 * @see #setTarget(SynchronousComponentInstance)
	 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.ReducerTracePackage#getCascadeCompositeInstanceTrace_Target()
	 * @model required="true"
	 * @generated
	 */
	SynchronousComponentInstance getTarget();

	/**
	 * Sets the value of the '{@link hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.CascadeCompositeInstanceTrace#getTarget <em>Target</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target</em>' reference.
	 * @see #getTarget()
	 * @generated
	 */
	void setTarget(SynchronousComponentInstance value);

	/**
	 * Returns the value of the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Source</em>' reference.
	 * @see #setSource(EnvironmentCascadeCompositeComponentInstance)
	 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.ReducerTracePackage#getCascadeCompositeInstanceTrace_Source()
	 * @model required="true"
	 * @generated
	 */
	EnvironmentCascadeCompositeComponentInstance getSource();

	/**
	 * Sets the value of the '{@link hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.CascadeCompositeInstanceTrace#getSource <em>Source</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source</em>' reference.
	 * @see #getSource()
	 * @generated
	 */
	void setSource(EnvironmentCascadeCompositeComponentInstance value);

} // CascadeCompositeInstanceTrace
