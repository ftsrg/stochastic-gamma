/**
 */
package hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.impl;

import hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.*;

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
public class ReducerTraceFactoryImpl extends EFactoryImpl implements ReducerTraceFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ReducerTraceFactory init() {
		try {
			ReducerTraceFactory theReducerTraceFactory = (ReducerTraceFactory)EPackage.Registry.INSTANCE.getEFactory(ReducerTracePackage.eNS_URI);
			if (theReducerTraceFactory != null) {
				return theReducerTraceFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new ReducerTraceFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReducerTraceFactoryImpl() {
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
			case ReducerTracePackage.REDUCER_TRACE: return createReducerTrace();
			case ReducerTracePackage.ELEMENTARY_TRACE: return createElementaryTrace();
			case ReducerTracePackage.ASYNCHRONOUS_COMPOSITE_INSTANCE_TRACE: return createAsynchronousCompositeInstanceTrace();
			case ReducerTracePackage.SYNCHRONOUS_COMPOSITE_INSTANCE_TRACE: return createSynchronousCompositeInstanceTrace();
			case ReducerTracePackage.CASCADE_COMPOSITE_INSTANCE_TRACE: return createCascadeCompositeInstanceTrace();
			case ReducerTracePackage.ASYNCHRONOUS_COMPOSITE_COMPONENT_TRACE: return createAsynchronousCompositeComponentTrace();
			case ReducerTracePackage.SYNCHRONOUS_COMPOSITE_COMPONENT_TRACE: return createSynchronousCompositeComponentTrace();
			case ReducerTracePackage.CASCADE_COMPOSITE_COMPONENT_TRACE: return createCascadeCompositeComponentTrace();
			case ReducerTracePackage.SIMPLE_CHANNEL_TRACE: return createSimpleChannelTrace();
			case ReducerTracePackage.BINDING_TRACE: return createBindingTrace();
			case ReducerTracePackage.BROADCAST_CHANNEL_TRACE: return createBroadcastChannelTrace();
			case ReducerTracePackage.PACKAGE_TRACE: return createPackageTrace();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReducerTrace createReducerTrace() {
		ReducerTraceImpl reducerTrace = new ReducerTraceImpl();
		return reducerTrace;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ElementaryTrace createElementaryTrace() {
		ElementaryTraceImpl elementaryTrace = new ElementaryTraceImpl();
		return elementaryTrace;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AsynchronousCompositeInstanceTrace createAsynchronousCompositeInstanceTrace() {
		AsynchronousCompositeInstanceTraceImpl asynchronousCompositeInstanceTrace = new AsynchronousCompositeInstanceTraceImpl();
		return asynchronousCompositeInstanceTrace;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SynchronousCompositeInstanceTrace createSynchronousCompositeInstanceTrace() {
		SynchronousCompositeInstanceTraceImpl synchronousCompositeInstanceTrace = new SynchronousCompositeInstanceTraceImpl();
		return synchronousCompositeInstanceTrace;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CascadeCompositeInstanceTrace createCascadeCompositeInstanceTrace() {
		CascadeCompositeInstanceTraceImpl cascadeCompositeInstanceTrace = new CascadeCompositeInstanceTraceImpl();
		return cascadeCompositeInstanceTrace;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AsynchronousCompositeComponentTrace createAsynchronousCompositeComponentTrace() {
		AsynchronousCompositeComponentTraceImpl asynchronousCompositeComponentTrace = new AsynchronousCompositeComponentTraceImpl();
		return asynchronousCompositeComponentTrace;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SynchronousCompositeComponentTrace createSynchronousCompositeComponentTrace() {
		SynchronousCompositeComponentTraceImpl synchronousCompositeComponentTrace = new SynchronousCompositeComponentTraceImpl();
		return synchronousCompositeComponentTrace;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CascadeCompositeComponentTrace createCascadeCompositeComponentTrace() {
		CascadeCompositeComponentTraceImpl cascadeCompositeComponentTrace = new CascadeCompositeComponentTraceImpl();
		return cascadeCompositeComponentTrace;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SimpleChannelTrace createSimpleChannelTrace() {
		SimpleChannelTraceImpl simpleChannelTrace = new SimpleChannelTraceImpl();
		return simpleChannelTrace;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BindingTrace createBindingTrace() {
		BindingTraceImpl bindingTrace = new BindingTraceImpl();
		return bindingTrace;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BroadcastChannelTrace createBroadcastChannelTrace() {
		BroadcastChannelTraceImpl broadcastChannelTrace = new BroadcastChannelTraceImpl();
		return broadcastChannelTrace;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PackageTrace createPackageTrace() {
		PackageTraceImpl packageTrace = new PackageTraceImpl();
		return packageTrace;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReducerTracePackage getReducerTracePackage() {
		return (ReducerTracePackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static ReducerTracePackage getPackage() {
		return ReducerTracePackage.eINSTANCE;
	}

} //ReducerTraceFactoryImpl
