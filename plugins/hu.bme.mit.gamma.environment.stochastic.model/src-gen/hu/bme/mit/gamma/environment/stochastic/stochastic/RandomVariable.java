/**
 */
package hu.bme.mit.gamma.environment.stochastic.stochastic;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Random Variable</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link hu.bme.mit.gamma.environment.stochastic.stochastic.RandomVariable#getDimension <em>Dimension</em>}</li>
 * </ul>
 *
 * @see hu.bme.mit.gamma.environment.stochastic.stochastic.StochasticPackage#getRandomVariable()
 * @model abstract="true"
 * @generated
 */
public interface RandomVariable extends StochasticModel {
	/**
	 * Returns the value of the '<em><b>Dimension</b></em>' attribute.
	 * The default value is <code>"1"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Dimension</em>' attribute.
	 * @see hu.bme.mit.gamma.environment.stochastic.stochastic.StochasticPackage#getRandomVariable_Dimension()
	 * @model default="1" unique="false" required="true" changeable="false" ordered="false"
	 * @generated
	 */
	int getDimension();

} // RandomVariable
