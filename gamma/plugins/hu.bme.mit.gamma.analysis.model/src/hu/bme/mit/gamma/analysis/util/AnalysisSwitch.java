/**
 */
package hu.bme.mit.gamma.analysis.util;

import hu.bme.mit.gamma.analysis.*;

import hu.bme.mit.gamma.expression.model.NamedElement;
import hu.bme.mit.gamma.expression.model.ParametricElement;

import hu.bme.mit.gamma.statechart.interface_.Component;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see hu.bme.mit.gamma.analysis.AnalysisPackage
 * @generated
 */
public class AnalysisSwitch<T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static AnalysisPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AnalysisSwitch() {
		if (modelPackage == null) {
			modelPackage = AnalysisPackage.eINSTANCE;
		}
	}

	/**
	 * Checks whether this is a switch for the given package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param ePackage the package in question.
	 * @return whether this is a switch for the given package.
	 * @generated
	 */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
		return ePackage == modelPackage;
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	@Override
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case AnalysisPackage.ANALYSIS_COMPONENT: {
				AnalysisComponent analysisComponent = (AnalysisComponent)theEObject;
				T result = caseAnalysisComponent(analysisComponent);
				if (result == null) result = caseComponent(analysisComponent);
				if (result == null) result = caseNamedElement(analysisComponent);
				if (result == null) result = caseParametricElement(analysisComponent);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AnalysisPackage.ANALYSIS_CONDITION: {
				AnalysisCondition analysisCondition = (AnalysisCondition)theEObject;
				T result = caseAnalysisCondition(analysisCondition);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AnalysisPackage.ASSUME_RAISED: {
				AssumeRaised assumeRaised = (AssumeRaised)theEObject;
				T result = caseAssumeRaised(assumeRaised);
				if (result == null) result = caseAnalysisCondition(assumeRaised);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AnalysisPackage.ASSUME_NOT_RAISED: {
				AssumeNotRaised assumeNotRaised = (AssumeNotRaised)theEObject;
				T result = caseAssumeNotRaised(assumeNotRaised);
				if (result == null) result = caseAnalysisCondition(assumeNotRaised);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AnalysisPackage.ANALYSIS_ASPECT: {
				AnalysisAspect analysisAspect = (AnalysisAspect)theEObject;
				T result = caseAnalysisAspect(analysisAspect);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AnalysisPackage.PROBABILITY: {
				Probability probability = (Probability)theEObject;
				T result = caseProbability(probability);
				if (result == null) result = caseAnalysisAspect(probability);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AnalysisPackage.TIMED_PROBABILITY: {
				TimedProbability timedProbability = (TimedProbability)theEObject;
				T result = caseTimedProbability(timedProbability);
				if (result == null) result = caseAnalysisAspect(timedProbability);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AnalysisPackage.MEAN_TIME: {
				MeanTime meanTime = (MeanTime)theEObject;
				T result = caseMeanTime(meanTime);
				if (result == null) result = caseAnalysisAspect(meanTime);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AnalysisPackage.TIME_BOUNDED_PROBABILITY: {
				TimeBoundedProbability timeBoundedProbability = (TimeBoundedProbability)theEObject;
				T result = caseTimeBoundedProbability(timeBoundedProbability);
				if (result == null) result = caseAnalysisAspect(timeBoundedProbability);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AnalysisPackage.REQUIREMENT_COMPONENT: {
				RequirementComponent requirementComponent = (RequirementComponent)theEObject;
				T result = caseRequirementComponent(requirementComponent);
				if (result == null) result = caseAnalysisComponent(requirementComponent);
				if (result == null) result = caseComponent(requirementComponent);
				if (result == null) result = caseNamedElement(requirementComponent);
				if (result == null) result = caseParametricElement(requirementComponent);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AnalysisPackage.REQUIREMENT_ASPECT: {
				RequirementAspect requirementAspect = (RequirementAspect)theEObject;
				T result = caseRequirementAspect(requirementAspect);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AnalysisPackage.LOWER_THAN: {
				LowerThan lowerThan = (LowerThan)theEObject;
				T result = caseLowerThan(lowerThan);
				if (result == null) result = caseRequirementAspect(lowerThan);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AnalysisPackage.GREATER_THAN: {
				GreaterThan greaterThan = (GreaterThan)theEObject;
				T result = caseGreaterThan(greaterThan);
				if (result == null) result = caseRequirementAspect(greaterThan);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AnalysisPackage.IS_BETWEEN: {
				IsBetween isBetween = (IsBetween)theEObject;
				T result = caseIsBetween(isBetween);
				if (result == null) result = caseRequirementAspect(isBetween);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AnalysisPackage.COMPONENT_PORT_EVENT_REFERENCE: {
				ComponentPortEventReference componentPortEventReference = (ComponentPortEventReference)theEObject;
				T result = caseComponentPortEventReference(componentPortEventReference);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AnalysisPackage.FREQUENCY: {
				Frequency frequency = (Frequency)theEObject;
				T result = caseFrequency(frequency);
				if (result == null) result = caseAnalysisAspect(frequency);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AnalysisPackage.MEAN_PARAMETER: {
				MeanParameter meanParameter = (MeanParameter)theEObject;
				T result = caseMeanParameter(meanParameter);
				if (result == null) result = caseAnalysisAspect(meanParameter);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AnalysisPackage.PARAMETER_DISTRIBUTION: {
				ParameterDistribution parameterDistribution = (ParameterDistribution)theEObject;
				T result = caseParameterDistribution(parameterDistribution);
				if (result == null) result = caseAnalysisAspect(parameterDistribution);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Component</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Component</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAnalysisComponent(AnalysisComponent object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Condition</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Condition</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAnalysisCondition(AnalysisCondition object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Assume Raised</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Assume Raised</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAssumeRaised(AssumeRaised object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Assume Not Raised</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Assume Not Raised</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAssumeNotRaised(AssumeNotRaised object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Aspect</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Aspect</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAnalysisAspect(AnalysisAspect object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Probability</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Probability</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseProbability(Probability object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Timed Probability</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Timed Probability</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTimedProbability(TimedProbability object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Mean Time</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Mean Time</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMeanTime(MeanTime object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Time Bounded Probability</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Time Bounded Probability</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTimeBoundedProbability(TimeBoundedProbability object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Requirement Component</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Requirement Component</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRequirementComponent(RequirementComponent object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Requirement Aspect</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Requirement Aspect</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRequirementAspect(RequirementAspect object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Lower Than</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Lower Than</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLowerThan(LowerThan object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Greater Than</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Greater Than</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseGreaterThan(GreaterThan object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Is Between</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Is Between</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIsBetween(IsBetween object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Component Port Event Reference</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Component Port Event Reference</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseComponentPortEventReference(ComponentPortEventReference object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Frequency</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Frequency</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFrequency(Frequency object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Mean Parameter</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Mean Parameter</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMeanParameter(MeanParameter object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Parameter Distribution</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Parameter Distribution</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseParameterDistribution(ParameterDistribution object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Named Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Named Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNamedElement(NamedElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Parametric Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Parametric Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseParametricElement(ParametricElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Component</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Component</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseComponent(Component object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	@Override
	public T defaultCase(EObject object) {
		return null;
	}

} //AnalysisSwitch