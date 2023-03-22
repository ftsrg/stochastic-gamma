/**
 */
package hu.bme.mit.gamma.environment.analysis.traceability.reducertrace;

import hu.bme.mit.gamma.environment.model.ElementaryEnvironmentComponentInstance;

import hu.bme.mit.gamma.statechart.composite.ComponentInstance;
import hu.bme.mit.gamma.statechart.statechart.StatechartDefinition;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Elementary Trace</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.ElementaryTrace#getSource <em>Source</em>}</li>
 *   <li>{@link hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.ElementaryTrace#getTypeTarget <em>Type Target</em>}</li>
 *   <li>{@link hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.ElementaryTrace#getInstanceTarget <em>Instance Target</em>}</li>
 * </ul>
 *
 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.ReducerTracePackage#getElementaryTrace()
 * @model
 * @generated
 */
public interface ElementaryTrace extends AbstractTrace {
	/**
	 * Returns the value of the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Source</em>' reference.
	 * @see #setSource(ElementaryEnvironmentComponentInstance)
	 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.ReducerTracePackage#getElementaryTrace_Source()
	 * @model required="true"
	 * @generated
	 */
	ElementaryEnvironmentComponentInstance getSource();

	/**
	 * Sets the value of the '{@link hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.ElementaryTrace#getSource <em>Source</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source</em>' reference.
	 * @see #getSource()
	 * @generated
	 */
	void setSource(ElementaryEnvironmentComponentInstance value);

	/**
	 * Returns the value of the '<em><b>Type Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type Target</em>' reference.
	 * @see #setTypeTarget(StatechartDefinition)
	 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.ReducerTracePackage#getElementaryTrace_TypeTarget()
	 * @model required="true"
	 * @generated
	 */
	StatechartDefinition getTypeTarget();

	/**
	 * Sets the value of the '{@link hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.ElementaryTrace#getTypeTarget <em>Type Target</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type Target</em>' reference.
	 * @see #getTypeTarget()
	 * @generated
	 */
	void setTypeTarget(StatechartDefinition value);

	/**
	 * Returns the value of the '<em><b>Instance Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Instance Target</em>' reference.
	 * @see #setInstanceTarget(ComponentInstance)
	 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.ReducerTracePackage#getElementaryTrace_InstanceTarget()
	 * @model required="true"
	 * @generated
	 */
	ComponentInstance getInstanceTarget();

	/**
	 * Sets the value of the '{@link hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.ElementaryTrace#getInstanceTarget <em>Instance Target</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Instance Target</em>' reference.
	 * @see #getInstanceTarget()
	 * @generated
	 */
	void setInstanceTarget(ComponentInstance value);

} // ElementaryTrace
