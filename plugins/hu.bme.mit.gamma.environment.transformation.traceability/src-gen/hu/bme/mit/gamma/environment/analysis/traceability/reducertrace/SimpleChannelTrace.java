/**
 */
package hu.bme.mit.gamma.environment.analysis.traceability.reducertrace;

import hu.bme.mit.gamma.statechart.composite.SimpleChannel;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Simple Channel Trace</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.SimpleChannelTrace#getTarget <em>Target</em>}</li>
 *   <li>{@link hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.SimpleChannelTrace#getSource <em>Source</em>}</li>
 * </ul>
 *
 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.ReducerTracePackage#getSimpleChannelTrace()
 * @model
 * @generated
 */
public interface SimpleChannelTrace extends AbstractTrace {
	/**
	 * Returns the value of the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target</em>' reference.
	 * @see #setTarget(SimpleChannel)
	 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.ReducerTracePackage#getSimpleChannelTrace_Target()
	 * @model required="true"
	 * @generated
	 */
	SimpleChannel getTarget();

	/**
	 * Sets the value of the '{@link hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.SimpleChannelTrace#getTarget <em>Target</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target</em>' reference.
	 * @see #getTarget()
	 * @generated
	 */
	void setTarget(SimpleChannel value);

	/**
	 * Returns the value of the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Source</em>' reference.
	 * @see #setSource(SimpleChannel)
	 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.ReducerTracePackage#getSimpleChannelTrace_Source()
	 * @model required="true"
	 * @generated
	 */
	SimpleChannel getSource();

	/**
	 * Sets the value of the '{@link hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.SimpleChannelTrace#getSource <em>Source</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source</em>' reference.
	 * @see #getSource()
	 * @generated
	 */
	void setSource(SimpleChannel value);

} // SimpleChannelTrace
