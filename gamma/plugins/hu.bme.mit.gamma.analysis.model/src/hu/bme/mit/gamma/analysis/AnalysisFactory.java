/**
 */
package hu.bme.mit.gamma.analysis;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see hu.bme.mit.gamma.analysis.AnalysisPackage
 * @generated
 */
public interface AnalysisFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	AnalysisFactory eINSTANCE = hu.bme.mit.gamma.analysis.impl.AnalysisFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Component</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Component</em>'.
	 * @generated
	 */
	AnalysisComponent createAnalysisComponent();

	/**
	 * Returns a new object of class '<em>Assume Raised</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Assume Raised</em>'.
	 * @generated
	 */
	AssumeRaised createAssumeRaised();

	/**
	 * Returns a new object of class '<em>Assume Not Raised</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Assume Not Raised</em>'.
	 * @generated
	 */
	AssumeNotRaised createAssumeNotRaised();

	/**
	 * Returns a new object of class '<em>Probability</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Probability</em>'.
	 * @generated
	 */
	Probability createProbability();

	/**
	 * Returns a new object of class '<em>Timed Probability</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Timed Probability</em>'.
	 * @generated
	 */
	TimedProbability createTimedProbability();

	/**
	 * Returns a new object of class '<em>Mean Time</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Mean Time</em>'.
	 * @generated
	 */
	MeanTime createMeanTime();

	/**
	 * Returns a new object of class '<em>Time Bounded Probability</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Time Bounded Probability</em>'.
	 * @generated
	 */
	TimeBoundedProbability createTimeBoundedProbability();

	/**
	 * Returns a new object of class '<em>Requirement Component</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Requirement Component</em>'.
	 * @generated
	 */
	RequirementComponent createRequirementComponent();

	/**
	 * Returns a new object of class '<em>Lower Than</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Lower Than</em>'.
	 * @generated
	 */
	LowerThan createLowerThan();

	/**
	 * Returns a new object of class '<em>Greater Than</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Greater Than</em>'.
	 * @generated
	 */
	GreaterThan createGreaterThan();

	/**
	 * Returns a new object of class '<em>Is Between</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Is Between</em>'.
	 * @generated
	 */
	IsBetween createIsBetween();

	/**
	 * Returns a new object of class '<em>Component Port Event Reference</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Component Port Event Reference</em>'.
	 * @generated
	 */
	ComponentPortEventReference createComponentPortEventReference();

	/**
	 * Returns a new object of class '<em>Frequency</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Frequency</em>'.
	 * @generated
	 */
	Frequency createFrequency();

	/**
	 * Returns a new object of class '<em>Mean Parameter</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Mean Parameter</em>'.
	 * @generated
	 */
	MeanParameter createMeanParameter();

	/**
	 * Returns a new object of class '<em>Parameter Distribution</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Parameter Distribution</em>'.
	 * @generated
	 */
	ParameterDistribution createParameterDistribution();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	AnalysisPackage getAnalysisPackage();

} //AnalysisFactory
