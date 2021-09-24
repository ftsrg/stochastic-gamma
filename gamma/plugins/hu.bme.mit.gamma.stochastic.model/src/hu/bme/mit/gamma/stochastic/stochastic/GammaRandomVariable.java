/**
 */
package hu.bme.mit.gamma.stochastic.stochastic;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Gamma Random Variable</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link hu.bme.mit.gamma.stochastic.stochastic.GammaRandomVariable#getScale <em>Scale</em>}</li>
 *   <li>{@link hu.bme.mit.gamma.stochastic.stochastic.GammaRandomVariable#getShape <em>Shape</em>}</li>
 * </ul>
 *
 * @see hu.bme.mit.gamma.stochastic.stochastic.StochasticPackage#getGammaRandomVariable()
 * @model
 * @generated
 */
public interface GammaRandomVariable extends ContinouosRandomVariable {
	/**
	 * Returns the value of the '<em><b>Scale</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Scale</em>' attribute.
	 * @see #setScale(double)
	 * @see hu.bme.mit.gamma.stochastic.stochastic.StochasticPackage#getGammaRandomVariable_Scale()
	 * @model required="true"
	 * @generated
	 */
	double getScale();

	/**
	 * Sets the value of the '{@link hu.bme.mit.gamma.stochastic.stochastic.GammaRandomVariable#getScale <em>Scale</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Scale</em>' attribute.
	 * @see #getScale()
	 * @generated
	 */
	void setScale(double value);

	/**
	 * Returns the value of the '<em><b>Shape</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Shape</em>' attribute.
	 * @see #setShape(double)
	 * @see hu.bme.mit.gamma.stochastic.stochastic.StochasticPackage#getGammaRandomVariable_Shape()
	 * @model required="true"
	 * @generated
	 */
	double getShape();

	/**
	 * Sets the value of the '{@link hu.bme.mit.gamma.stochastic.stochastic.GammaRandomVariable#getShape <em>Shape</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Shape</em>' attribute.
	 * @see #getShape()
	 * @generated
	 */
	void setShape(double value);

} // GammaRandomVariable
