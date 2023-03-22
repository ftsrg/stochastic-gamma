/**
 */
package hu.bme.mit.gamma.fmeda.model.util;

import hu.bme.mit.gamma.fmeda.model.Channel;
import hu.bme.mit.gamma.fmeda.model.ChannelPortReference;
import hu.bme.mit.gamma.fmeda.model.ComponentReference;
import hu.bme.mit.gamma.fmeda.model.DiagnosticsReference;
import hu.bme.mit.gamma.fmeda.model.FMEDAComponent;
import hu.bme.mit.gamma.fmeda.model.FMEDAComponentInstance;
import hu.bme.mit.gamma.fmeda.model.FMEDADiagnostics;
import hu.bme.mit.gamma.fmeda.model.FailureMode;
import hu.bme.mit.gamma.fmeda.model.FailureModeReference;
import hu.bme.mit.gamma.fmeda.model.FailurePropagation;
import hu.bme.mit.gamma.fmeda.model.HardwarePartFailureModeReference;
import hu.bme.mit.gamma.fmeda.model.HardwarePartPortReference;
import hu.bme.mit.gamma.fmeda.model.Interface;
import hu.bme.mit.gamma.fmeda.model.NamedElement;
import hu.bme.mit.gamma.fmeda.model.PartFailureMode;
import hu.bme.mit.gamma.fmeda.model.Port;
import hu.bme.mit.gamma.fmeda.model.PortFailureModeReference;
import hu.bme.mit.gamma.fmeda.model.PortReference;
import hu.bme.mit.gamma.fmeda.model.ThisPartFailureModeReference;
import hu.bme.mit.gamma.fmeda.model.ThisPortFailureModeReference;
import hu.bme.mit.gamma.fmeda.model.ThisPortReference;
import hu.bme.mit.gamma.fmeda.model.fmedaPackage;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see hu.bme.mit.gamma.fmeda.model.fmedaPackage
 * @generated
 */
public class fmedaAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static fmedaPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public fmedaAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = fmedaPackage.eINSTANCE;
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
	protected fmedaSwitch<Adapter> modelSwitch =
		new fmedaSwitch<Adapter>() {
			@Override
			public Adapter caseFMEDAComponent(FMEDAComponent object) {
				return createFMEDAComponentAdapter();
			}
			@Override
			public Adapter caseFMEDAComponentInstance(FMEDAComponentInstance object) {
				return createFMEDAComponentInstanceAdapter();
			}
			@Override
			public Adapter caseFailureMode(FailureMode object) {
				return createFailureModeAdapter();
			}
			@Override
			public Adapter caseHardwarePartFailureModeReference(HardwarePartFailureModeReference object) {
				return createHardwarePartFailureModeReferenceAdapter();
			}
			@Override
			public Adapter caseFailurePropagation(FailurePropagation object) {
				return createFailurePropagationAdapter();
			}
			@Override
			public Adapter casePackage(hu.bme.mit.gamma.fmeda.model.Package object) {
				return createPackageAdapter();
			}
			@Override
			public Adapter caseNamedElement(NamedElement object) {
				return createNamedElementAdapter();
			}
			@Override
			public Adapter caseFailureModeReference(FailureModeReference object) {
				return createFailureModeReferenceAdapter();
			}
			@Override
			public Adapter caseThisPartFailureModeReference(ThisPartFailureModeReference object) {
				return createThisPartFailureModeReferenceAdapter();
			}
			@Override
			public Adapter caseFMEDADiagnostics(FMEDADiagnostics object) {
				return createFMEDADiagnosticsAdapter();
			}
			@Override
			public Adapter caseDiagnosticsReference(DiagnosticsReference object) {
				return createDiagnosticsReferenceAdapter();
			}
			@Override
			public Adapter casePortFailureModeReference(PortFailureModeReference object) {
				return createPortFailureModeReferenceAdapter();
			}
			@Override
			public Adapter caseInterface(Interface object) {
				return createInterfaceAdapter();
			}
			@Override
			public Adapter caseThisPortFailureModeReference(ThisPortFailureModeReference object) {
				return createThisPortFailureModeReferenceAdapter();
			}
			@Override
			public Adapter casePortReference(PortReference object) {
				return createPortReferenceAdapter();
			}
			@Override
			public Adapter caseComponentReference(ComponentReference object) {
				return createComponentReferenceAdapter();
			}
			@Override
			public Adapter caseThisPortReference(ThisPortReference object) {
				return createThisPortReferenceAdapter();
			}
			@Override
			public Adapter caseHardwarePartPortReference(HardwarePartPortReference object) {
				return createHardwarePartPortReferenceAdapter();
			}
			@Override
			public Adapter caseChannel(Channel object) {
				return createChannelAdapter();
			}
			@Override
			public Adapter caseChannelPortReference(ChannelPortReference object) {
				return createChannelPortReferenceAdapter();
			}
			@Override
			public Adapter casePort(Port object) {
				return createPortAdapter();
			}
			@Override
			public Adapter casePartFailureMode(PartFailureMode object) {
				return createPartFailureModeAdapter();
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
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.fmeda.model.FMEDAComponent <em>FMEDA Component</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.fmeda.model.FMEDAComponent
	 * @generated
	 */
	public Adapter createFMEDAComponentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.fmeda.model.FMEDAComponentInstance <em>FMEDA Component Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.fmeda.model.FMEDAComponentInstance
	 * @generated
	 */
	public Adapter createFMEDAComponentInstanceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.fmeda.model.FailureMode <em>Failure Mode</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.fmeda.model.FailureMode
	 * @generated
	 */
	public Adapter createFailureModeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.fmeda.model.HardwarePartFailureModeReference <em>Hardware Part Failure Mode Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.fmeda.model.HardwarePartFailureModeReference
	 * @generated
	 */
	public Adapter createHardwarePartFailureModeReferenceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.fmeda.model.FailurePropagation <em>Failure Propagation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.fmeda.model.FailurePropagation
	 * @generated
	 */
	public Adapter createFailurePropagationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.fmeda.model.Package <em>Package</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.fmeda.model.Package
	 * @generated
	 */
	public Adapter createPackageAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.fmeda.model.NamedElement <em>Named Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.fmeda.model.NamedElement
	 * @generated
	 */
	public Adapter createNamedElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.fmeda.model.FailureModeReference <em>Failure Mode Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.fmeda.model.FailureModeReference
	 * @generated
	 */
	public Adapter createFailureModeReferenceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.fmeda.model.ThisPartFailureModeReference <em>This Part Failure Mode Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.fmeda.model.ThisPartFailureModeReference
	 * @generated
	 */
	public Adapter createThisPartFailureModeReferenceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.fmeda.model.FMEDADiagnostics <em>FMEDA Diagnostics</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.fmeda.model.FMEDADiagnostics
	 * @generated
	 */
	public Adapter createFMEDADiagnosticsAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.fmeda.model.DiagnosticsReference <em>Diagnostics Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.fmeda.model.DiagnosticsReference
	 * @generated
	 */
	public Adapter createDiagnosticsReferenceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.fmeda.model.PortFailureModeReference <em>Port Failure Mode Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.fmeda.model.PortFailureModeReference
	 * @generated
	 */
	public Adapter createPortFailureModeReferenceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.fmeda.model.Interface <em>Interface</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.fmeda.model.Interface
	 * @generated
	 */
	public Adapter createInterfaceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.fmeda.model.ThisPortFailureModeReference <em>This Port Failure Mode Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.fmeda.model.ThisPortFailureModeReference
	 * @generated
	 */
	public Adapter createThisPortFailureModeReferenceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.fmeda.model.PortReference <em>Port Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.fmeda.model.PortReference
	 * @generated
	 */
	public Adapter createPortReferenceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.fmeda.model.ComponentReference <em>Component Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.fmeda.model.ComponentReference
	 * @generated
	 */
	public Adapter createComponentReferenceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.fmeda.model.ThisPortReference <em>This Port Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.fmeda.model.ThisPortReference
	 * @generated
	 */
	public Adapter createThisPortReferenceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.fmeda.model.HardwarePartPortReference <em>Hardware Part Port Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.fmeda.model.HardwarePartPortReference
	 * @generated
	 */
	public Adapter createHardwarePartPortReferenceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.fmeda.model.Channel <em>Channel</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.fmeda.model.Channel
	 * @generated
	 */
	public Adapter createChannelAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.fmeda.model.ChannelPortReference <em>Channel Port Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.fmeda.model.ChannelPortReference
	 * @generated
	 */
	public Adapter createChannelPortReferenceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.fmeda.model.Port <em>Port</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.fmeda.model.Port
	 * @generated
	 */
	public Adapter createPortAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link hu.bme.mit.gamma.fmeda.model.PartFailureMode <em>Part Failure Mode</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see hu.bme.mit.gamma.fmeda.model.PartFailureMode
	 * @generated
	 */
	public Adapter createPartFailureModeAdapter() {
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

} //fmedaAdapterFactory
