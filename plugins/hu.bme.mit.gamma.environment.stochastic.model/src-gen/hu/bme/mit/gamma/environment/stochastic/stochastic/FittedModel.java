/**
 */
package hu.bme.mit.gamma.environment.stochastic.stochastic;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Fitted Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link hu.bme.mit.gamma.environment.stochastic.stochastic.FittedModel#getSource <em>Source</em>}</li>
 *   <li>{@link hu.bme.mit.gamma.environment.stochastic.stochastic.FittedModel#getLr <em>Lr</em>}</li>
 * </ul>
 *
 * @see hu.bme.mit.gamma.environment.stochastic.stochastic.StochasticPackage#getFittedModel()
 * @model abstract="true"
 * @generated
 */
public interface FittedModel extends EObject {
	/**
	 * Returns the value of the '<em><b>Source</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Source</em>' containment reference.
	 * @see #setSource(DataSource)
	 * @see hu.bme.mit.gamma.environment.stochastic.stochastic.StochasticPackage#getFittedModel_Source()
	 * @model containment="true" required="true"
	 * @generated
	 */
	DataSource getSource();

	/**
	 * Sets the value of the '{@link hu.bme.mit.gamma.environment.stochastic.stochastic.FittedModel#getSource <em>Source</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source</em>' containment reference.
	 * @see #getSource()
	 * @generated
	 */
	void setSource(DataSource value);

	/**
	 * Returns the value of the '<em><b>Lr</b></em>' attribute.
	 * The default value is <code>"0.05"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Lr</em>' attribute.
	 * @see #setLr(double)
	 * @see hu.bme.mit.gamma.environment.stochastic.stochastic.StochasticPackage#getFittedModel_Lr()
	 * @model default="0.05" required="true"
	 * @generated
	 */
	double getLr();

	/**
	 * Sets the value of the '{@link hu.bme.mit.gamma.environment.stochastic.stochastic.FittedModel#getLr <em>Lr</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Lr</em>' attribute.
	 * @see #getLr()
	 * @generated
	 */
	void setLr(double value);

} // FittedModel
