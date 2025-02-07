/**
 */
package hu.bme.mit.gamma.environment.analysis.traceability.reducertrace;

import hu.bme.mit.gamma.statechart.composite.PortBinding;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Binding Trace</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.BindingTrace#getTarget <em>Target</em>}</li>
 *   <li>{@link hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.BindingTrace#getSource <em>Source</em>}</li>
 * </ul>
 *
 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.ReducerTracePackage#getBindingTrace()
 * @model
 * @generated
 */
public interface BindingTrace extends AbstractTrace {
	/**
	 * Returns the value of the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target</em>' reference.
	 * @see #setTarget(PortBinding)
	 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.ReducerTracePackage#getBindingTrace_Target()
	 * @model required="true"
	 * @generated
	 */
	PortBinding getTarget();

	/**
	 * Sets the value of the '{@link hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.BindingTrace#getTarget <em>Target</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target</em>' reference.
	 * @see #getTarget()
	 * @generated
	 */
	void setTarget(PortBinding value);

	/**
	 * Returns the value of the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Source</em>' reference.
	 * @see #setSource(PortBinding)
	 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.ReducerTracePackage#getBindingTrace_Source()
	 * @model required="true"
	 * @generated
	 */
	PortBinding getSource();

	/**
	 * Sets the value of the '{@link hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.BindingTrace#getSource <em>Source</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source</em>' reference.
	 * @see #getSource()
	 * @generated
	 */
	void setSource(PortBinding value);

} // BindingTrace
