/**
 */
package hu.bme.mit.gamma.fmeda.model.impl;

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
import hu.bme.mit.gamma.fmeda.model.fmedaFactory;
import hu.bme.mit.gamma.fmeda.model.fmedaPackage;

import org.eclipse.emf.ecore.EAttribute;
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
public class fmedaPackageImpl extends EPackageImpl implements fmedaPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass fmedaComponentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass fmedaComponentInstanceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass failureModeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass hardwarePartFailureModeReferenceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass failurePropagationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass packageEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass namedElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass failureModeReferenceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass thisPartFailureModeReferenceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass fmedaDiagnosticsEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass diagnosticsReferenceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass portFailureModeReferenceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass interfaceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass thisPortFailureModeReferenceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass portReferenceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass componentReferenceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass thisPortReferenceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass hardwarePartPortReferenceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass channelEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass channelPortReferenceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass portEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass partFailureModeEClass = null;

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
	 * @see hu.bme.mit.gamma.fmeda.model.fmedaPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private fmedaPackageImpl() {
		super(eNS_URI, fmedaFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link fmedaPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static fmedaPackage init() {
		if (isInited) return (fmedaPackage)EPackage.Registry.INSTANCE.getEPackage(fmedaPackage.eNS_URI);

		// Obtain or create and register package
		Object registeredfmedaPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
		fmedaPackageImpl thefmedaPackage = registeredfmedaPackage instanceof fmedaPackageImpl ? (fmedaPackageImpl)registeredfmedaPackage : new fmedaPackageImpl();

		isInited = true;

		// Create package meta-data objects
		thefmedaPackage.createPackageContents();

		// Initialize created meta-data
		thefmedaPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		thefmedaPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(fmedaPackage.eNS_URI, thefmedaPackage);
		return thefmedaPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFMEDAComponent() {
		return fmedaComponentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFMEDAComponent_Failuremodes() {
		return (EReference)fmedaComponentEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFMEDAComponent_Subcomponents() {
		return (EReference)fmedaComponentEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFMEDAComponent_Failurepropagations() {
		return (EReference)fmedaComponentEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFMEDAComponent_Channels() {
		return (EReference)fmedaComponentEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFMEDAComponent_Ports() {
		return (EReference)fmedaComponentEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFMEDAComponentInstance() {
		return fmedaComponentInstanceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFMEDAComponentInstance_Type() {
		return (EReference)fmedaComponentInstanceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFailureMode() {
		return failureModeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFailureMode_Diagnostics() {
		return (EReference)failureModeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getHardwarePartFailureModeReference() {
		return hardwarePartFailureModeReferenceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFailurePropagation() {
		return failurePropagationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFailurePropagation_Cause() {
		return (EReference)failurePropagationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFailurePropagation_Effect() {
		return (EReference)failurePropagationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPackage() {
		return packageEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPackage_Components() {
		return (EReference)packageEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPackage_Diagnostics() {
		return (EReference)packageEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPackage_Interfaces() {
		return (EReference)packageEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNamedElement() {
		return namedElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getNamedElement_Name() {
		return (EAttribute)namedElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFailureModeReference() {
		return failureModeReferenceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFailureModeReference_Failuremode() {
		return (EReference)failureModeReferenceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getThisPartFailureModeReference() {
		return thisPartFailureModeReferenceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFMEDADiagnostics() {
		return fmedaDiagnosticsEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDiagnosticsReference() {
		return diagnosticsReferenceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDiagnosticsReference_Coverage() {
		return (EAttribute)diagnosticsReferenceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDiagnosticsReference_Fmedadiagnostics() {
		return (EReference)diagnosticsReferenceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPortFailureModeReference() {
		return portFailureModeReferenceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getInterface() {
		return interfaceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInterface_Failuremodes() {
		return (EReference)interfaceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getThisPortFailureModeReference() {
		return thisPortFailureModeReferenceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPortReference() {
		return portReferenceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPortReference_Port() {
		return (EReference)portReferenceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getComponentReference() {
		return componentReferenceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getComponentReference_Part() {
		return (EReference)componentReferenceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getThisPortReference() {
		return thisPortReferenceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getHardwarePartPortReference() {
		return hardwarePartPortReferenceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getChannel() {
		return channelEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getChannel_From() {
		return (EReference)channelEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getChannel_To() {
		return (EReference)channelEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getChannelPortReference() {
		return channelPortReferenceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPort() {
		return portEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPort_Type() {
		return (EReference)portEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPartFailureMode() {
		return partFailureModeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPartFailureMode_FailureRate() {
		return (EAttribute)partFailureModeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public fmedaFactory getfmedaFactory() {
		return (fmedaFactory)getEFactoryInstance();
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
		fmedaComponentEClass = createEClass(FMEDA_COMPONENT);
		createEReference(fmedaComponentEClass, FMEDA_COMPONENT__FAILUREMODES);
		createEReference(fmedaComponentEClass, FMEDA_COMPONENT__SUBCOMPONENTS);
		createEReference(fmedaComponentEClass, FMEDA_COMPONENT__FAILUREPROPAGATIONS);
		createEReference(fmedaComponentEClass, FMEDA_COMPONENT__CHANNELS);
		createEReference(fmedaComponentEClass, FMEDA_COMPONENT__PORTS);

		fmedaComponentInstanceEClass = createEClass(FMEDA_COMPONENT_INSTANCE);
		createEReference(fmedaComponentInstanceEClass, FMEDA_COMPONENT_INSTANCE__TYPE);

		failureModeEClass = createEClass(FAILURE_MODE);
		createEReference(failureModeEClass, FAILURE_MODE__DIAGNOSTICS);

		hardwarePartFailureModeReferenceEClass = createEClass(HARDWARE_PART_FAILURE_MODE_REFERENCE);

		failurePropagationEClass = createEClass(FAILURE_PROPAGATION);
		createEReference(failurePropagationEClass, FAILURE_PROPAGATION__CAUSE);
		createEReference(failurePropagationEClass, FAILURE_PROPAGATION__EFFECT);

		packageEClass = createEClass(PACKAGE);
		createEReference(packageEClass, PACKAGE__COMPONENTS);
		createEReference(packageEClass, PACKAGE__DIAGNOSTICS);
		createEReference(packageEClass, PACKAGE__INTERFACES);

		namedElementEClass = createEClass(NAMED_ELEMENT);
		createEAttribute(namedElementEClass, NAMED_ELEMENT__NAME);

		failureModeReferenceEClass = createEClass(FAILURE_MODE_REFERENCE);
		createEReference(failureModeReferenceEClass, FAILURE_MODE_REFERENCE__FAILUREMODE);

		thisPartFailureModeReferenceEClass = createEClass(THIS_PART_FAILURE_MODE_REFERENCE);

		fmedaDiagnosticsEClass = createEClass(FMEDA_DIAGNOSTICS);

		diagnosticsReferenceEClass = createEClass(DIAGNOSTICS_REFERENCE);
		createEAttribute(diagnosticsReferenceEClass, DIAGNOSTICS_REFERENCE__COVERAGE);
		createEReference(diagnosticsReferenceEClass, DIAGNOSTICS_REFERENCE__FMEDADIAGNOSTICS);

		portFailureModeReferenceEClass = createEClass(PORT_FAILURE_MODE_REFERENCE);

		interfaceEClass = createEClass(INTERFACE);
		createEReference(interfaceEClass, INTERFACE__FAILUREMODES);

		thisPortFailureModeReferenceEClass = createEClass(THIS_PORT_FAILURE_MODE_REFERENCE);

		portReferenceEClass = createEClass(PORT_REFERENCE);
		createEReference(portReferenceEClass, PORT_REFERENCE__PORT);

		componentReferenceEClass = createEClass(COMPONENT_REFERENCE);
		createEReference(componentReferenceEClass, COMPONENT_REFERENCE__PART);

		thisPortReferenceEClass = createEClass(THIS_PORT_REFERENCE);

		hardwarePartPortReferenceEClass = createEClass(HARDWARE_PART_PORT_REFERENCE);

		channelEClass = createEClass(CHANNEL);
		createEReference(channelEClass, CHANNEL__FROM);
		createEReference(channelEClass, CHANNEL__TO);

		channelPortReferenceEClass = createEClass(CHANNEL_PORT_REFERENCE);

		portEClass = createEClass(PORT);
		createEReference(portEClass, PORT__TYPE);

		partFailureModeEClass = createEClass(PART_FAILURE_MODE);
		createEAttribute(partFailureModeEClass, PART_FAILURE_MODE__FAILURE_RATE);
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

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		fmedaComponentEClass.getESuperTypes().add(this.getNamedElement());
		fmedaComponentInstanceEClass.getESuperTypes().add(this.getNamedElement());
		failureModeEClass.getESuperTypes().add(this.getNamedElement());
		hardwarePartFailureModeReferenceEClass.getESuperTypes().add(this.getFailureModeReference());
		hardwarePartFailureModeReferenceEClass.getESuperTypes().add(this.getComponentReference());
		packageEClass.getESuperTypes().add(this.getNamedElement());
		thisPartFailureModeReferenceEClass.getESuperTypes().add(this.getFailureModeReference());
		fmedaDiagnosticsEClass.getESuperTypes().add(this.getNamedElement());
		portFailureModeReferenceEClass.getESuperTypes().add(this.getFailureModeReference());
		portFailureModeReferenceEClass.getESuperTypes().add(this.getPortReference());
		portFailureModeReferenceEClass.getESuperTypes().add(this.getComponentReference());
		interfaceEClass.getESuperTypes().add(this.getNamedElement());
		thisPortFailureModeReferenceEClass.getESuperTypes().add(this.getFailureModeReference());
		thisPortFailureModeReferenceEClass.getESuperTypes().add(this.getPortReference());
		thisPortReferenceEClass.getESuperTypes().add(this.getChannelPortReference());
		hardwarePartPortReferenceEClass.getESuperTypes().add(this.getComponentReference());
		hardwarePartPortReferenceEClass.getESuperTypes().add(this.getChannelPortReference());
		channelPortReferenceEClass.getESuperTypes().add(this.getPortReference());
		portEClass.getESuperTypes().add(this.getNamedElement());
		partFailureModeEClass.getESuperTypes().add(this.getFailureMode());

		// Initialize classes, features, and operations; add parameters
		initEClass(fmedaComponentEClass, FMEDAComponent.class, "FMEDAComponent", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getFMEDAComponent_Failuremodes(), this.getFailureMode(), null, "failuremodes", null, 0, -1, FMEDAComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFMEDAComponent_Subcomponents(), this.getFMEDAComponentInstance(), null, "subcomponents", null, 0, -1, FMEDAComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFMEDAComponent_Failurepropagations(), this.getFailurePropagation(), null, "failurepropagations", null, 0, -1, FMEDAComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFMEDAComponent_Channels(), this.getChannel(), null, "channels", null, 0, -1, FMEDAComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFMEDAComponent_Ports(), this.getPort(), null, "ports", null, 0, -1, FMEDAComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(fmedaComponentInstanceEClass, FMEDAComponentInstance.class, "FMEDAComponentInstance", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getFMEDAComponentInstance_Type(), this.getFMEDAComponent(), null, "type", null, 1, 1, FMEDAComponentInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(failureModeEClass, FailureMode.class, "FailureMode", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getFailureMode_Diagnostics(), this.getDiagnosticsReference(), null, "diagnostics", null, 0, 1, FailureMode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(hardwarePartFailureModeReferenceEClass, HardwarePartFailureModeReference.class, "HardwarePartFailureModeReference", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(failurePropagationEClass, FailurePropagation.class, "FailurePropagation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getFailurePropagation_Cause(), this.getFailureModeReference(), null, "cause", null, 1, 1, FailurePropagation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFailurePropagation_Effect(), this.getFailureModeReference(), null, "effect", null, 1, -1, FailurePropagation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(packageEClass, hu.bme.mit.gamma.fmeda.model.Package.class, "Package", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getPackage_Components(), this.getFMEDAComponent(), null, "components", null, 0, -1, hu.bme.mit.gamma.fmeda.model.Package.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPackage_Diagnostics(), this.getFMEDADiagnostics(), null, "diagnostics", null, 0, -1, hu.bme.mit.gamma.fmeda.model.Package.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPackage_Interfaces(), this.getInterface(), null, "interfaces", null, 0, -1, hu.bme.mit.gamma.fmeda.model.Package.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(namedElementEClass, NamedElement.class, "NamedElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getNamedElement_Name(), ecorePackage.getEString(), "name", null, 1, 1, NamedElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(failureModeReferenceEClass, FailureModeReference.class, "FailureModeReference", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getFailureModeReference_Failuremode(), this.getFailureMode(), null, "failuremode", null, 1, 1, FailureModeReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(thisPartFailureModeReferenceEClass, ThisPartFailureModeReference.class, "ThisPartFailureModeReference", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(fmedaDiagnosticsEClass, FMEDADiagnostics.class, "FMEDADiagnostics", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(diagnosticsReferenceEClass, DiagnosticsReference.class, "DiagnosticsReference", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDiagnosticsReference_Coverage(), ecorePackage.getEDouble(), "coverage", null, 1, 1, DiagnosticsReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDiagnosticsReference_Fmedadiagnostics(), this.getFMEDADiagnostics(), null, "fmedadiagnostics", null, 0, -1, DiagnosticsReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(portFailureModeReferenceEClass, PortFailureModeReference.class, "PortFailureModeReference", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(interfaceEClass, Interface.class, "Interface", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getInterface_Failuremodes(), this.getFailureMode(), null, "failuremodes", null, 1, -1, Interface.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(thisPortFailureModeReferenceEClass, ThisPortFailureModeReference.class, "ThisPortFailureModeReference", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(portReferenceEClass, PortReference.class, "PortReference", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getPortReference_Port(), this.getPort(), null, "port", null, 1, 1, PortReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(componentReferenceEClass, ComponentReference.class, "ComponentReference", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getComponentReference_Part(), this.getFMEDAComponentInstance(), null, "part", null, 1, 1, ComponentReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(thisPortReferenceEClass, ThisPortReference.class, "ThisPortReference", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(hardwarePartPortReferenceEClass, HardwarePartPortReference.class, "HardwarePartPortReference", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(channelEClass, Channel.class, "Channel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getChannel_From(), this.getChannelPortReference(), null, "from", null, 1, 1, Channel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getChannel_To(), this.getChannelPortReference(), null, "to", null, 1, -1, Channel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(channelPortReferenceEClass, ChannelPortReference.class, "ChannelPortReference", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(portEClass, Port.class, "Port", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getPort_Type(), this.getInterface(), null, "type", null, 1, 1, Port.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(partFailureModeEClass, PartFailureMode.class, "PartFailureMode", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPartFailureMode_FailureRate(), ecorePackage.getEDouble(), "failureRate", null, 1, 1, PartFailureMode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

} //fmedaPackageImpl
