/**
 */
package hu.bme.mit.gamma.analysis.impl;

import hu.bme.mit.gamma.analysis.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class AnalysisFactoryImpl extends EFactoryImpl implements AnalysisFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static AnalysisFactory init() {
		try {
			AnalysisFactory theAnalysisFactory = (AnalysisFactory)EPackage.Registry.INSTANCE.getEFactory(AnalysisPackage.eNS_URI);
			if (theAnalysisFactory != null) {
				return theAnalysisFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new AnalysisFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AnalysisFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case AnalysisPackage.ANALYSIS_COMPONENT: return createAnalysisComponent();
			case AnalysisPackage.ASSUME_RAISED: return createAssumeRaised();
			case AnalysisPackage.ASSUME_NOT_RAISED: return createAssumeNotRaised();
			case AnalysisPackage.PROBABILITY: return createProbability();
			case AnalysisPackage.TIMED_PROBABILITY: return createTimedProbability();
			case AnalysisPackage.MEAN_TIME: return createMeanTime();
			case AnalysisPackage.TIME_BOUNDED_PROBABILITY: return createTimeBoundedProbability();
			case AnalysisPackage.REQUIREMENT_COMPONENT: return createRequirementComponent();
			case AnalysisPackage.LOWER_THAN: return createLowerThan();
			case AnalysisPackage.GREATER_THAN: return createGreaterThan();
			case AnalysisPackage.IS_BETWEEN: return createIsBetween();
			case AnalysisPackage.COMPONENT_PORT_EVENT_REFERENCE: return createComponentPortEventReference();
			case AnalysisPackage.FREQUENCY: return createFrequency();
			case AnalysisPackage.MEAN_PARAMETER: return createMeanParameter();
			case AnalysisPackage.PARAMETER_DISTRIBUTION: return createParameterDistribution();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AnalysisComponent createAnalysisComponent() {
		AnalysisComponentImpl analysisComponent = new AnalysisComponentImpl();
		return analysisComponent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AssumeRaised createAssumeRaised() {
		AssumeRaisedImpl assumeRaised = new AssumeRaisedImpl();
		return assumeRaised;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AssumeNotRaised createAssumeNotRaised() {
		AssumeNotRaisedImpl assumeNotRaised = new AssumeNotRaisedImpl();
		return assumeNotRaised;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Probability createProbability() {
		ProbabilityImpl probability = new ProbabilityImpl();
		return probability;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TimedProbability createTimedProbability() {
		TimedProbabilityImpl timedProbability = new TimedProbabilityImpl();
		return timedProbability;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MeanTime createMeanTime() {
		MeanTimeImpl meanTime = new MeanTimeImpl();
		return meanTime;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TimeBoundedProbability createTimeBoundedProbability() {
		TimeBoundedProbabilityImpl timeBoundedProbability = new TimeBoundedProbabilityImpl();
		return timeBoundedProbability;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RequirementComponent createRequirementComponent() {
		RequirementComponentImpl requirementComponent = new RequirementComponentImpl();
		return requirementComponent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LowerThan createLowerThan() {
		LowerThanImpl lowerThan = new LowerThanImpl();
		return lowerThan;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GreaterThan createGreaterThan() {
		GreaterThanImpl greaterThan = new GreaterThanImpl();
		return greaterThan;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IsBetween createIsBetween() {
		IsBetweenImpl isBetween = new IsBetweenImpl();
		return isBetween;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComponentPortEventReference createComponentPortEventReference() {
		ComponentPortEventReferenceImpl componentPortEventReference = new ComponentPortEventReferenceImpl();
		return componentPortEventReference;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Frequency createFrequency() {
		FrequencyImpl frequency = new FrequencyImpl();
		return frequency;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MeanParameter createMeanParameter() {
		MeanParameterImpl meanParameter = new MeanParameterImpl();
		return meanParameter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ParameterDistribution createParameterDistribution() {
		ParameterDistributionImpl parameterDistribution = new ParameterDistributionImpl();
		return parameterDistribution;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AnalysisPackage getAnalysisPackage() {
		return (AnalysisPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static AnalysisPackage getPackage() {
		return AnalysisPackage.eINSTANCE;
	}

} //AnalysisFactoryImpl
