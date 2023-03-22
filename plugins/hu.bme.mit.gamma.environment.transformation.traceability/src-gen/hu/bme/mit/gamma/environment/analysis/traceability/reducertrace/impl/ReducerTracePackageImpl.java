/**
 */
package hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.impl;

import hu.bme.mit.gamma.action.model.ActionModelPackage;

import hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.AbstractTrace;
import hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.AsynchronousCompositeComponentTrace;
import hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.AsynchronousCompositeInstanceTrace;
import hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.BindingTrace;
import hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.BroadcastChannelTrace;
import hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.CascadeCompositeComponentTrace;
import hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.CascadeCompositeInstanceTrace;
import hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.ElementaryTrace;
import hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.PackageTrace;
import hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.ReducerTrace;
import hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.ReducerTraceFactory;
import hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.ReducerTracePackage;
import hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.SimpleChannelTrace;
import hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.SynchronousCompositeComponentTrace;
import hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.SynchronousCompositeInstanceTrace;

import hu.bme.mit.gamma.environment.model.EnvironmentModelPackage;

import hu.bme.mit.gamma.environment.stochastic.stochastic.StochasticPackage;

import hu.bme.mit.gamma.expression.model.ExpressionModelPackage;

import hu.bme.mit.gamma.statechart.composite.CompositeModelPackage;

import hu.bme.mit.gamma.statechart.interface_.InterfaceModelPackage;

import hu.bme.mit.gamma.statechart.statechart.StatechartModelPackage;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ReducerTracePackageImpl extends EPackageImpl implements ReducerTracePackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass reducerTraceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass abstractTraceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass elementaryTraceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass asynchronousCompositeInstanceTraceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass synchronousCompositeInstanceTraceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cascadeCompositeInstanceTraceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass asynchronousCompositeComponentTraceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass synchronousCompositeComponentTraceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cascadeCompositeComponentTraceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass simpleChannelTraceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass bindingTraceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass broadcastChannelTraceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass packageTraceEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.ReducerTracePackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private ReducerTracePackageImpl() {
		super(eNS_URI, ReducerTraceFactory.eINSTANCE);
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 *
	 * <p>This method is used to initialize {@link ReducerTracePackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static ReducerTracePackage init() {
		if (isInited) return (ReducerTracePackage)EPackage.Registry.INSTANCE.getEPackage(ReducerTracePackage.eNS_URI);

		// Obtain or create and register package
		Object registeredReducerTracePackage = EPackage.Registry.INSTANCE.get(eNS_URI);
		ReducerTracePackageImpl theReducerTracePackage = registeredReducerTracePackage instanceof ReducerTracePackageImpl ? (ReducerTracePackageImpl)registeredReducerTracePackage : new ReducerTracePackageImpl();

		isInited = true;

		// Initialize simple dependencies
		ActionModelPackage.eINSTANCE.eClass();
		EnvironmentModelPackage.eINSTANCE.eClass();
		ExpressionModelPackage.eINSTANCE.eClass();
		CompositeModelPackage.eINSTANCE.eClass();
		InterfaceModelPackage.eINSTANCE.eClass();
		StatechartModelPackage.eINSTANCE.eClass();
		StochasticPackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theReducerTracePackage.createPackageContents();

		// Initialize created meta-data
		theReducerTracePackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theReducerTracePackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(ReducerTracePackage.eNS_URI, theReducerTracePackage);
		return theReducerTracePackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getReducerTrace() {
		return reducerTraceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getReducerTrace_Traces() {
		return (EReference)reducerTraceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getReducerTrace_Source() {
		return (EReference)reducerTraceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getReducerTrace_Target() {
		return (EReference)reducerTraceEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAbstractTrace() {
		return abstractTraceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getElementaryTrace() {
		return elementaryTraceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getElementaryTrace_Source() {
		return (EReference)elementaryTraceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getElementaryTrace_TypeTarget() {
		return (EReference)elementaryTraceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getElementaryTrace_InstanceTarget() {
		return (EReference)elementaryTraceEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAsynchronousCompositeInstanceTrace() {
		return asynchronousCompositeInstanceTraceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAsynchronousCompositeInstanceTrace_Source() {
		return (EReference)asynchronousCompositeInstanceTraceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAsynchronousCompositeInstanceTrace_Target() {
		return (EReference)asynchronousCompositeInstanceTraceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSynchronousCompositeInstanceTrace() {
		return synchronousCompositeInstanceTraceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSynchronousCompositeInstanceTrace_Source() {
		return (EReference)synchronousCompositeInstanceTraceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSynchronousCompositeInstanceTrace_Target() {
		return (EReference)synchronousCompositeInstanceTraceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCascadeCompositeInstanceTrace() {
		return cascadeCompositeInstanceTraceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCascadeCompositeInstanceTrace_Target() {
		return (EReference)cascadeCompositeInstanceTraceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCascadeCompositeInstanceTrace_Source() {
		return (EReference)cascadeCompositeInstanceTraceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAsynchronousCompositeComponentTrace() {
		return asynchronousCompositeComponentTraceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAsynchronousCompositeComponentTrace_Source() {
		return (EReference)asynchronousCompositeComponentTraceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAsynchronousCompositeComponentTrace_Target() {
		return (EReference)asynchronousCompositeComponentTraceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSynchronousCompositeComponentTrace() {
		return synchronousCompositeComponentTraceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSynchronousCompositeComponentTrace_Source() {
		return (EReference)synchronousCompositeComponentTraceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSynchronousCompositeComponentTrace_Target() {
		return (EReference)synchronousCompositeComponentTraceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCascadeCompositeComponentTrace() {
		return cascadeCompositeComponentTraceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCascadeCompositeComponentTrace_Target() {
		return (EReference)cascadeCompositeComponentTraceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCascadeCompositeComponentTrace_Source() {
		return (EReference)cascadeCompositeComponentTraceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSimpleChannelTrace() {
		return simpleChannelTraceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSimpleChannelTrace_Target() {
		return (EReference)simpleChannelTraceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSimpleChannelTrace_Source() {
		return (EReference)simpleChannelTraceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBindingTrace() {
		return bindingTraceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBindingTrace_Target() {
		return (EReference)bindingTraceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBindingTrace_Source() {
		return (EReference)bindingTraceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBroadcastChannelTrace() {
		return broadcastChannelTraceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBroadcastChannelTrace_Target() {
		return (EReference)broadcastChannelTraceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBroadcastChannelTrace_Source() {
		return (EReference)broadcastChannelTraceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPackageTrace() {
		return packageTraceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPackageTrace_Target() {
		return (EReference)packageTraceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPackageTrace_Source() {
		return (EReference)packageTraceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReducerTraceFactory getReducerTraceFactory() {
		return (ReducerTraceFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		reducerTraceEClass = createEClass(REDUCER_TRACE);
		createEReference(reducerTraceEClass, REDUCER_TRACE__TRACES);
		createEReference(reducerTraceEClass, REDUCER_TRACE__SOURCE);
		createEReference(reducerTraceEClass, REDUCER_TRACE__TARGET);

		abstractTraceEClass = createEClass(ABSTRACT_TRACE);

		elementaryTraceEClass = createEClass(ELEMENTARY_TRACE);
		createEReference(elementaryTraceEClass, ELEMENTARY_TRACE__SOURCE);
		createEReference(elementaryTraceEClass, ELEMENTARY_TRACE__TYPE_TARGET);
		createEReference(elementaryTraceEClass, ELEMENTARY_TRACE__INSTANCE_TARGET);

		asynchronousCompositeInstanceTraceEClass = createEClass(ASYNCHRONOUS_COMPOSITE_INSTANCE_TRACE);
		createEReference(asynchronousCompositeInstanceTraceEClass, ASYNCHRONOUS_COMPOSITE_INSTANCE_TRACE__SOURCE);
		createEReference(asynchronousCompositeInstanceTraceEClass, ASYNCHRONOUS_COMPOSITE_INSTANCE_TRACE__TARGET);

		synchronousCompositeInstanceTraceEClass = createEClass(SYNCHRONOUS_COMPOSITE_INSTANCE_TRACE);
		createEReference(synchronousCompositeInstanceTraceEClass, SYNCHRONOUS_COMPOSITE_INSTANCE_TRACE__SOURCE);
		createEReference(synchronousCompositeInstanceTraceEClass, SYNCHRONOUS_COMPOSITE_INSTANCE_TRACE__TARGET);

		cascadeCompositeInstanceTraceEClass = createEClass(CASCADE_COMPOSITE_INSTANCE_TRACE);
		createEReference(cascadeCompositeInstanceTraceEClass, CASCADE_COMPOSITE_INSTANCE_TRACE__TARGET);
		createEReference(cascadeCompositeInstanceTraceEClass, CASCADE_COMPOSITE_INSTANCE_TRACE__SOURCE);

		asynchronousCompositeComponentTraceEClass = createEClass(ASYNCHRONOUS_COMPOSITE_COMPONENT_TRACE);
		createEReference(asynchronousCompositeComponentTraceEClass, ASYNCHRONOUS_COMPOSITE_COMPONENT_TRACE__SOURCE);
		createEReference(asynchronousCompositeComponentTraceEClass, ASYNCHRONOUS_COMPOSITE_COMPONENT_TRACE__TARGET);

		synchronousCompositeComponentTraceEClass = createEClass(SYNCHRONOUS_COMPOSITE_COMPONENT_TRACE);
		createEReference(synchronousCompositeComponentTraceEClass, SYNCHRONOUS_COMPOSITE_COMPONENT_TRACE__SOURCE);
		createEReference(synchronousCompositeComponentTraceEClass, SYNCHRONOUS_COMPOSITE_COMPONENT_TRACE__TARGET);

		cascadeCompositeComponentTraceEClass = createEClass(CASCADE_COMPOSITE_COMPONENT_TRACE);
		createEReference(cascadeCompositeComponentTraceEClass, CASCADE_COMPOSITE_COMPONENT_TRACE__TARGET);
		createEReference(cascadeCompositeComponentTraceEClass, CASCADE_COMPOSITE_COMPONENT_TRACE__SOURCE);

		simpleChannelTraceEClass = createEClass(SIMPLE_CHANNEL_TRACE);
		createEReference(simpleChannelTraceEClass, SIMPLE_CHANNEL_TRACE__TARGET);
		createEReference(simpleChannelTraceEClass, SIMPLE_CHANNEL_TRACE__SOURCE);

		bindingTraceEClass = createEClass(BINDING_TRACE);
		createEReference(bindingTraceEClass, BINDING_TRACE__TARGET);
		createEReference(bindingTraceEClass, BINDING_TRACE__SOURCE);

		broadcastChannelTraceEClass = createEClass(BROADCAST_CHANNEL_TRACE);
		createEReference(broadcastChannelTraceEClass, BROADCAST_CHANNEL_TRACE__TARGET);
		createEReference(broadcastChannelTraceEClass, BROADCAST_CHANNEL_TRACE__SOURCE);

		packageTraceEClass = createEClass(PACKAGE_TRACE);
		createEReference(packageTraceEClass, PACKAGE_TRACE__TARGET);
		createEReference(packageTraceEClass, PACKAGE_TRACE__SOURCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		InterfaceModelPackage theInterfaceModelPackage = (InterfaceModelPackage)EPackage.Registry.INSTANCE.getEPackage(InterfaceModelPackage.eNS_URI);
		EnvironmentModelPackage theEnvironmentModelPackage = (EnvironmentModelPackage)EPackage.Registry.INSTANCE.getEPackage(EnvironmentModelPackage.eNS_URI);
		StatechartModelPackage theStatechartModelPackage = (StatechartModelPackage)EPackage.Registry.INSTANCE.getEPackage(StatechartModelPackage.eNS_URI);
		CompositeModelPackage theCompositeModelPackage = (CompositeModelPackage)EPackage.Registry.INSTANCE.getEPackage(CompositeModelPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		elementaryTraceEClass.getESuperTypes().add(this.getAbstractTrace());
		asynchronousCompositeInstanceTraceEClass.getESuperTypes().add(this.getAbstractTrace());
		synchronousCompositeInstanceTraceEClass.getESuperTypes().add(this.getAbstractTrace());
		cascadeCompositeInstanceTraceEClass.getESuperTypes().add(this.getAbstractTrace());
		asynchronousCompositeComponentTraceEClass.getESuperTypes().add(this.getAbstractTrace());
		synchronousCompositeComponentTraceEClass.getESuperTypes().add(this.getAbstractTrace());
		cascadeCompositeComponentTraceEClass.getESuperTypes().add(this.getAbstractTrace());
		simpleChannelTraceEClass.getESuperTypes().add(this.getAbstractTrace());
		bindingTraceEClass.getESuperTypes().add(this.getAbstractTrace());
		broadcastChannelTraceEClass.getESuperTypes().add(this.getAbstractTrace());
		packageTraceEClass.getESuperTypes().add(this.getAbstractTrace());

		// Initialize classes, features, and operations; add parameters
		initEClass(reducerTraceEClass, ReducerTrace.class, "ReducerTrace", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getReducerTrace_Traces(), this.getAbstractTrace(), null, "traces", null, 0, -1, ReducerTrace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getReducerTrace_Source(), theInterfaceModelPackage.getPackage(), null, "source", null, 1, 1, ReducerTrace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getReducerTrace_Target(), theInterfaceModelPackage.getPackage(), null, "target", null, 1, 1, ReducerTrace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(abstractTraceEClass, AbstractTrace.class, "AbstractTrace", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(elementaryTraceEClass, ElementaryTrace.class, "ElementaryTrace", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getElementaryTrace_Source(), theEnvironmentModelPackage.getElementaryEnvironmentComponentInstance(), null, "source", null, 1, 1, ElementaryTrace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getElementaryTrace_TypeTarget(), theStatechartModelPackage.getStatechartDefinition(), null, "typeTarget", null, 1, 1, ElementaryTrace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getElementaryTrace_InstanceTarget(), theCompositeModelPackage.getComponentInstance(), null, "instanceTarget", null, 1, 1, ElementaryTrace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(asynchronousCompositeInstanceTraceEClass, AsynchronousCompositeInstanceTrace.class, "AsynchronousCompositeInstanceTrace", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAsynchronousCompositeInstanceTrace_Source(), theEnvironmentModelPackage.getEnvironmentAsynchronousCompositeComponentInstance(), null, "source", null, 1, 1, AsynchronousCompositeInstanceTrace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAsynchronousCompositeInstanceTrace_Target(), theCompositeModelPackage.getAsynchronousComponentInstance(), null, "target", null, 1, 1, AsynchronousCompositeInstanceTrace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(synchronousCompositeInstanceTraceEClass, SynchronousCompositeInstanceTrace.class, "SynchronousCompositeInstanceTrace", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSynchronousCompositeInstanceTrace_Source(), theEnvironmentModelPackage.getEnvironmentSynchronousCompositeComponentInstance(), null, "source", null, 1, 1, SynchronousCompositeInstanceTrace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSynchronousCompositeInstanceTrace_Target(), theCompositeModelPackage.getSynchronousComponentInstance(), null, "target", null, 1, 1, SynchronousCompositeInstanceTrace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(cascadeCompositeInstanceTraceEClass, CascadeCompositeInstanceTrace.class, "CascadeCompositeInstanceTrace", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCascadeCompositeInstanceTrace_Target(), theCompositeModelPackage.getSynchronousComponentInstance(), null, "target", null, 1, 1, CascadeCompositeInstanceTrace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCascadeCompositeInstanceTrace_Source(), theEnvironmentModelPackage.getEnvironmentCascadeCompositeComponentInstance(), null, "source", null, 1, 1, CascadeCompositeInstanceTrace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(asynchronousCompositeComponentTraceEClass, AsynchronousCompositeComponentTrace.class, "AsynchronousCompositeComponentTrace", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAsynchronousCompositeComponentTrace_Source(), theEnvironmentModelPackage.getEnvironmentAsynchronousCompositeComponent(), null, "source", null, 1, 1, AsynchronousCompositeComponentTrace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAsynchronousCompositeComponentTrace_Target(), theCompositeModelPackage.getAsynchronousCompositeComponent(), null, "target", null, 1, 1, AsynchronousCompositeComponentTrace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(synchronousCompositeComponentTraceEClass, SynchronousCompositeComponentTrace.class, "SynchronousCompositeComponentTrace", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSynchronousCompositeComponentTrace_Source(), theEnvironmentModelPackage.getEnvironmentSynchronousCompositeComponent(), null, "source", null, 1, 1, SynchronousCompositeComponentTrace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSynchronousCompositeComponentTrace_Target(), theCompositeModelPackage.getSynchronousCompositeComponent(), null, "target", null, 1, 1, SynchronousCompositeComponentTrace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(cascadeCompositeComponentTraceEClass, CascadeCompositeComponentTrace.class, "CascadeCompositeComponentTrace", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCascadeCompositeComponentTrace_Target(), theCompositeModelPackage.getCascadeCompositeComponent(), null, "target", null, 1, 1, CascadeCompositeComponentTrace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCascadeCompositeComponentTrace_Source(), theEnvironmentModelPackage.getEnvironmentCascadeCompositeComponent(), null, "source", null, 1, 1, CascadeCompositeComponentTrace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(simpleChannelTraceEClass, SimpleChannelTrace.class, "SimpleChannelTrace", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSimpleChannelTrace_Target(), theCompositeModelPackage.getSimpleChannel(), null, "target", null, 1, 1, SimpleChannelTrace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSimpleChannelTrace_Source(), theCompositeModelPackage.getSimpleChannel(), null, "source", null, 1, 1, SimpleChannelTrace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(bindingTraceEClass, BindingTrace.class, "BindingTrace", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getBindingTrace_Target(), theCompositeModelPackage.getPortBinding(), null, "target", null, 1, 1, BindingTrace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getBindingTrace_Source(), theCompositeModelPackage.getPortBinding(), null, "source", null, 1, 1, BindingTrace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(broadcastChannelTraceEClass, BroadcastChannelTrace.class, "BroadcastChannelTrace", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getBroadcastChannelTrace_Target(), theCompositeModelPackage.getBroadcastChannel(), null, "target", null, 1, 1, BroadcastChannelTrace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getBroadcastChannelTrace_Source(), theCompositeModelPackage.getBroadcastChannel(), null, "source", null, 1, 1, BroadcastChannelTrace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(packageTraceEClass, PackageTrace.class, "PackageTrace", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getPackageTrace_Target(), theInterfaceModelPackage.getPackage(), null, "target", null, 1, 1, PackageTrace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPackageTrace_Source(), theInterfaceModelPackage.getPackage(), null, "source", null, 1, 1, PackageTrace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

} //ReducerTracePackageImpl
