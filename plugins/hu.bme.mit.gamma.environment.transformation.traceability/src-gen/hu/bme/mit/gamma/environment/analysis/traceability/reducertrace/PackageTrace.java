/**
 */
package hu.bme.mit.gamma.environment.analysis.traceability.reducertrace;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Package Trace</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.PackageTrace#getTarget <em>Target</em>}</li>
 *   <li>{@link hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.PackageTrace#getSource <em>Source</em>}</li>
 * </ul>
 *
 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.ReducerTracePackage#getPackageTrace()
 * @model
 * @generated
 */
public interface PackageTrace extends AbstractTrace {
	/**
	 * Returns the value of the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target</em>' reference.
	 * @see #setTarget(hu.bme.mit.gamma.statechart.interface_.Package)
	 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.ReducerTracePackage#getPackageTrace_Target()
	 * @model required="true"
	 * @generated
	 */
	hu.bme.mit.gamma.statechart.interface_.Package getTarget();

	/**
	 * Sets the value of the '{@link hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.PackageTrace#getTarget <em>Target</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target</em>' reference.
	 * @see #getTarget()
	 * @generated
	 */
	void setTarget(hu.bme.mit.gamma.statechart.interface_.Package value);

	/**
	 * Returns the value of the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Source</em>' reference.
	 * @see #setSource(hu.bme.mit.gamma.statechart.interface_.Package)
	 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.ReducerTracePackage#getPackageTrace_Source()
	 * @model required="true"
	 * @generated
	 */
	hu.bme.mit.gamma.statechart.interface_.Package getSource();

	/**
	 * Sets the value of the '{@link hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.PackageTrace#getSource <em>Source</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source</em>' reference.
	 * @see #getSource()
	 * @generated
	 */
	void setSource(hu.bme.mit.gamma.statechart.interface_.Package value);

} // PackageTrace
