/**
 */
package hu.bme.mit.gamma.environment.analysis.util;

import hu.bme.mit.gamma.environment.analysis.*;

import hu.bme.mit.gamma.expression.model.NamedElement;
import hu.bme.mit.gamma.expression.model.ParametricElement;

import hu.bme.mit.gamma.statechart.interface_.Component;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see hu.bme.mit.gamma.environment.analysis.AnalysisPackage
 * @generated
 */
public class AnalysisAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static AnalysisPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AnalysisAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = AnalysisPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AnalysisSwitch<Adapter> modelSwitch =
		new AnalysisSwitch<Adapter>() {
			@Override
			public Adapter caseAnalysisComponent(AnalysisComponent object) {
				return createAnalysisComponentAdapter();
			}
			@Override
			public Adapter caseAnalysisCondition(AnalysisCondition object) {
				return createAnalysisConditionAdapter();
			}
			@Override
			public Adapter caseAssumeRaised(AssumeRaised object) {
				return createAssumeRaisedAdapter();
			}
			@Override
			public Adapter caseAssumeNotRaised(AssumeNotRaised object) {
				return createAssumeNotRaisedAdapter();
			}
			@Override
			public Adapter caseAnalysisAspect(AnalysisAspect object) {
				return createAnalysisAspectAdapter();
			}
			@Override
			public Adapter caseProbability(Probability object) {
				return createProbabilityAdapter();
			}
			@Override
			public Adapter caseTimedProbability(TimedProbability object) {
				return createTimedProbabilityAdapter();
			}
			@Override
			public Adapter caseMeanTime(MeanTime object) {
				return createMeanTimeAdapter();
			}
			@Override
			public Adapter caseTimeBoundedProbability(TimeBoundedProbability object) {
				return createTimeBoundedProbabilityAdapter();
			}
			@Override
			public Adapter caseRequirementComponent(RequirementComponent object) {
				return createRequirementComponentAdapter();
			}
			@Override
			public Adapter caseRequirementAspect(RequirementAspect object) {
				return createRequirementAspectAdapter();
			}
			@Override
			public Adapter caseLowerThan(LowerThan object) {
				return createLowerThanAdapter();
			}
			@Override
			public Adapter caseGreaterThan(GreaterThan object) {
				return createGreaterThanAdapter();
			}
			@Override
			public Adapter caseIsBetween(IsBetween object) {
				return createIsBetweenAdapter();
			}
			@Override
			public Adapter caseComponentPortEventReference(ComponentPortEventReference object) {
				return createComponentPortEventReferenceAdapter();
			}
			@Override
			public Adapter caseFrequency(Frequency object) {
				return createFrequencyAdapter();
			}
			@Override
			public Adapter caseMeanParameter(MeanParameter object) {
				return createMeanParameterAdapter();
			}
			@Override
			public Adapter caseParameterDistribution(ParameterDistribution object) {
				return createParameterDistributionAdapter();
			}
			@Override
			public Adapter caseRecursiveComponentReference(RecursiveComponentReference object) {
				return createRecursiveComponentReferenceAdapter();
			}
			@Override
			public Adapter caseMeanTimeBetweenEvents(MeanTimeBetweenEvents object) {
				return createMeanTimeBetweenEventsAdapter();
			}
			@Override
			public Adapter caseEventTimeRatio(EventTimeRatio object) {
				return createEventTimeRatioAdapter();
			}
			@Override
			public Adapter caseObserveParameter(ObserveParameter object) {
				return createObserveParameterAdapter();
			}
			@Override
			public Adapter casePrioryDistribution(PrioryDistribution object) {
				return createPrioryDistributionAdapter();
			}
			@Override
			public Adapter caseObserveCondition(ObserveCondition object) {
				return createObserveConditionAdapter();
			}
			@Override
			public Adapter caseAssumeCondition(AssumeCondition object) {
				return createAssumeConditionAdapter();
			}
			@Override
			public Adapter caseObserveTime(ObserveTime object) {
				return createObserveTimeAdapter();
			}
			@Override
			public Adapter caseEndCondition(EndCondition object) {
				return createEndConditionAdapter();
			}
			@Override
			public Adapter caseAnalysisMethod(AnalysisMethod object) {
				return createAnalysisMethodAdapter();
			}
			@Override
			public Adapter caseExactAnalysisMethod(ExactAnalysisMethod object) {
				return createExactAnalysisMethodAdapter();
			}
			@Override
			public Adapter caseSimulationAnalysisMethod(SimulationAnalysisMethod object) {
				return createSimulationAnalysisMethodAdapter();
			}
			@Override
			public Adapter caseImportanceSampling(ImportanceSampling object) {
				return createImportanceSamplingAdapter();
			}
			@Override
			public Adapter caseMCMC(MCMC object) {
				return createMCMCAdapter();
			}
			@Override
			public Adapter caseSVI(SVI object) {
				return createSVIAdapter();
			}
			@Override
			public Adapter caseMCMCKernel(MCMCKernel object) {
				return createMCMCKernelAdapter();
			}
			@Override
			public Adapter caseNUTS(NUTS object) {
				return createNUTSAdapter();
			}
			@Override
			public Adapter caseHMC(HMC object) {
				return createHMCAdapter();
			}
			@Override
			public Adapter caseSimulation(Simulation object) {
				return createSimulationAdapter();
			}
			@Override
			public Adapter caseNamedElement(NamedElement object) {
				return createNamedElementAdapter();
			}
			@Override
			public Adapter caseParametricElement(ParametricElement object) {
				return createParametricElementAdapter();
			}
			@Override
			public Adapter caseComponent(Component object) {
				return createComponentAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.environment.analysis.AnalysisComponent <em>Component</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.environment.analysis.AnalysisComponent
	 * @generated
	 */
	public Adapter createAnalysisComponentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.environment.analysis.AnalysisCondition <em>Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.environment.analysis.AnalysisCondition
	 * @generated
	 */
	public Adapter createAnalysisConditionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.environment.analysis.AssumeRaised <em>Assume Raised</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.environment.analysis.AssumeRaised
	 * @generated
	 */
	public Adapter createAssumeRaisedAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.environment.analysis.AssumeNotRaised <em>Assume Not Raised</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.environment.analysis.AssumeNotRaised
	 * @generated
	 */
	public Adapter createAssumeNotRaisedAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.environment.analysis.AnalysisAspect <em>Aspect</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.environment.analysis.AnalysisAspect
	 * @generated
	 */
	public Adapter createAnalysisAspectAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.environment.analysis.Probability <em>Probability</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.environment.analysis.Probability
	 * @generated
	 */
	public Adapter createProbabilityAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.environment.analysis.TimedProbability <em>Timed Probability</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.environment.analysis.TimedProbability
	 * @generated
	 */
	public Adapter createTimedProbabilityAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.environment.analysis.MeanTime <em>Mean Time</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.environment.analysis.MeanTime
	 * @generated
	 */
	public Adapter createMeanTimeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.environment.analysis.TimeBoundedProbability <em>Time Bounded Probability</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.environment.analysis.TimeBoundedProbability
	 * @generated
	 */
	public Adapter createTimeBoundedProbabilityAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.environment.analysis.RequirementComponent <em>Requirement Component</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.environment.analysis.RequirementComponent
	 * @generated
	 */
	public Adapter createRequirementComponentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.environment.analysis.RequirementAspect <em>Requirement Aspect</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.environment.analysis.RequirementAspect
	 * @generated
	 */
	public Adapter createRequirementAspectAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.environment.analysis.LowerThan <em>Lower Than</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.environment.analysis.LowerThan
	 * @generated
	 */
	public Adapter createLowerThanAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.environment.analysis.GreaterThan <em>Greater Than</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.environment.analysis.GreaterThan
	 * @generated
	 */
	public Adapter createGreaterThanAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.environment.analysis.IsBetween <em>Is Between</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.environment.analysis.IsBetween
	 * @generated
	 */
	public Adapter createIsBetweenAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.environment.analysis.ComponentPortEventReference <em>Component Port Event Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.environment.analysis.ComponentPortEventReference
	 * @generated
	 */
	public Adapter createComponentPortEventReferenceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.environment.analysis.Frequency <em>Frequency</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.environment.analysis.Frequency
	 * @generated
	 */
	public Adapter createFrequencyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.environment.analysis.MeanParameter <em>Mean Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.environment.analysis.MeanParameter
	 * @generated
	 */
	public Adapter createMeanParameterAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.environment.analysis.ParameterDistribution <em>Parameter Distribution</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.environment.analysis.ParameterDistribution
	 * @generated
	 */
	public Adapter createParameterDistributionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.environment.analysis.RecursiveComponentReference <em>Recursive Component Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.environment.analysis.RecursiveComponentReference
	 * @generated
	 */
	public Adapter createRecursiveComponentReferenceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.environment.analysis.MeanTimeBetweenEvents <em>Mean Time Between Events</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.environment.analysis.MeanTimeBetweenEvents
	 * @generated
	 */
	public Adapter createMeanTimeBetweenEventsAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.environment.analysis.EventTimeRatio <em>Event Time Ratio</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.environment.analysis.EventTimeRatio
	 * @generated
	 */
	public Adapter createEventTimeRatioAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.environment.analysis.ObserveParameter <em>Observe Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.environment.analysis.ObserveParameter
	 * @generated
	 */
	public Adapter createObserveParameterAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.environment.analysis.PrioryDistribution <em>Priory Distribution</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.environment.analysis.PrioryDistribution
	 * @generated
	 */
	public Adapter createPrioryDistributionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.environment.analysis.ObserveCondition <em>Observe Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.environment.analysis.ObserveCondition
	 * @generated
	 */
	public Adapter createObserveConditionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.environment.analysis.AssumeCondition <em>Assume Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.environment.analysis.AssumeCondition
	 * @generated
	 */
	public Adapter createAssumeConditionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.environment.analysis.ObserveTime <em>Observe Time</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.environment.analysis.ObserveTime
	 * @generated
	 */
	public Adapter createObserveTimeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.environment.analysis.EndCondition <em>End Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.environment.analysis.EndCondition
	 * @generated
	 */
	public Adapter createEndConditionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.environment.analysis.AnalysisMethod <em>Method</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.environment.analysis.AnalysisMethod
	 * @generated
	 */
	public Adapter createAnalysisMethodAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.environment.analysis.ExactAnalysisMethod <em>Exact Analysis Method</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.environment.analysis.ExactAnalysisMethod
	 * @generated
	 */
	public Adapter createExactAnalysisMethodAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.environment.analysis.SimulationAnalysisMethod <em>Simulation Analysis Method</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.environment.analysis.SimulationAnalysisMethod
	 * @generated
	 */
	public Adapter createSimulationAnalysisMethodAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.environment.analysis.ImportanceSampling <em>Importance Sampling</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.environment.analysis.ImportanceSampling
	 * @generated
	 */
	public Adapter createImportanceSamplingAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.environment.analysis.MCMC <em>MCMC</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.environment.analysis.MCMC
	 * @generated
	 */
	public Adapter createMCMCAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.environment.analysis.SVI <em>SVI</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.environment.analysis.SVI
	 * @generated
	 */
	public Adapter createSVIAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.environment.analysis.MCMCKernel <em>MCMC Kernel</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.environment.analysis.MCMCKernel
	 * @generated
	 */
	public Adapter createMCMCKernelAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.environment.analysis.NUTS <em>NUTS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.environment.analysis.NUTS
	 * @generated
	 */
	public Adapter createNUTSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.environment.analysis.HMC <em>HMC</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.environment.analysis.HMC
	 * @generated
	 */
	public Adapter createHMCAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.environment.analysis.Simulation <em>Simulation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.environment.analysis.Simulation
	 * @generated
	 */
	public Adapter createSimulationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.expression.model.NamedElement <em>Named Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.expression.model.NamedElement
	 * @generated
	 */
	public Adapter createNamedElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.expression.model.ParametricElement <em>Parametric Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.expression.model.ParametricElement
	 * @generated
	 */
	public Adapter createParametricElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.statechart.interface_.Component <em>Component</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.statechart.interface_.Component
	 * @generated
	 */
	public Adapter createComponentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //AnalysisAdapterFactory
