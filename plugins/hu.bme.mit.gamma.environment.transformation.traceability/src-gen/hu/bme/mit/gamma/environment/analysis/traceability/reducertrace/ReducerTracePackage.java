/**
 */
package hu.bme.mit.gamma.environment.analysis.traceability.reducertrace;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.ReducerTraceFactory
 * @model kind="package"
 * @generated
 */
public interface ReducerTracePackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "reducertrace";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.mit.bme.hu/environment/transformation/Traceability";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "reducertrace";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ReducerTracePackage eINSTANCE = hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.impl.ReducerTracePackageImpl.init();

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.impl.ReducerTraceImpl <em>Reducer Trace</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.impl.ReducerTraceImpl
	 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.impl.ReducerTracePackageImpl#getReducerTrace()
	 * @generated
	 */
	int REDUCER_TRACE = 0;

	/**
	 * The feature id for the '<em><b>Traces</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REDUCER_TRACE__TRACES = 0;

	/**
	 * The feature id for the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REDUCER_TRACE__SOURCE = 1;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REDUCER_TRACE__TARGET = 2;

	/**
	 * The number of structural features of the '<em>Reducer Trace</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REDUCER_TRACE_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Reducer Trace</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REDUCER_TRACE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.impl.AbstractTraceImpl <em>Abstract Trace</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.impl.AbstractTraceImpl
	 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.impl.ReducerTracePackageImpl#getAbstractTrace()
	 * @generated
	 */
	int ABSTRACT_TRACE = 1;

	/**
	 * The number of structural features of the '<em>Abstract Trace</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_TRACE_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Abstract Trace</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_TRACE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.impl.ElementaryTraceImpl <em>Elementary Trace</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.impl.ElementaryTraceImpl
	 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.impl.ReducerTracePackageImpl#getElementaryTrace()
	 * @generated
	 */
	int ELEMENTARY_TRACE = 2;

	/**
	 * The feature id for the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEMENTARY_TRACE__SOURCE = ABSTRACT_TRACE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Type Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEMENTARY_TRACE__TYPE_TARGET = ABSTRACT_TRACE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Instance Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEMENTARY_TRACE__INSTANCE_TARGET = ABSTRACT_TRACE_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Elementary Trace</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEMENTARY_TRACE_FEATURE_COUNT = ABSTRACT_TRACE_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Elementary Trace</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEMENTARY_TRACE_OPERATION_COUNT = ABSTRACT_TRACE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.impl.AsynchronousCompositeInstanceTraceImpl <em>Asynchronous Composite Instance Trace</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.impl.AsynchronousCompositeInstanceTraceImpl
	 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.impl.ReducerTracePackageImpl#getAsynchronousCompositeInstanceTrace()
	 * @generated
	 */
	int ASYNCHRONOUS_COMPOSITE_INSTANCE_TRACE = 3;

	/**
	 * The feature id for the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASYNCHRONOUS_COMPOSITE_INSTANCE_TRACE__SOURCE = ABSTRACT_TRACE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASYNCHRONOUS_COMPOSITE_INSTANCE_TRACE__TARGET = ABSTRACT_TRACE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Asynchronous Composite Instance Trace</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASYNCHRONOUS_COMPOSITE_INSTANCE_TRACE_FEATURE_COUNT = ABSTRACT_TRACE_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Asynchronous Composite Instance Trace</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASYNCHRONOUS_COMPOSITE_INSTANCE_TRACE_OPERATION_COUNT = ABSTRACT_TRACE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.impl.SynchronousCompositeInstanceTraceImpl <em>Synchronous Composite Instance Trace</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.impl.SynchronousCompositeInstanceTraceImpl
	 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.impl.ReducerTracePackageImpl#getSynchronousCompositeInstanceTrace()
	 * @generated
	 */
	int SYNCHRONOUS_COMPOSITE_INSTANCE_TRACE = 4;

	/**
	 * The feature id for the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYNCHRONOUS_COMPOSITE_INSTANCE_TRACE__SOURCE = ABSTRACT_TRACE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYNCHRONOUS_COMPOSITE_INSTANCE_TRACE__TARGET = ABSTRACT_TRACE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Synchronous Composite Instance Trace</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYNCHRONOUS_COMPOSITE_INSTANCE_TRACE_FEATURE_COUNT = ABSTRACT_TRACE_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Synchronous Composite Instance Trace</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYNCHRONOUS_COMPOSITE_INSTANCE_TRACE_OPERATION_COUNT = ABSTRACT_TRACE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.impl.CascadeCompositeInstanceTraceImpl <em>Cascade Composite Instance Trace</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.impl.CascadeCompositeInstanceTraceImpl
	 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.impl.ReducerTracePackageImpl#getCascadeCompositeInstanceTrace()
	 * @generated
	 */
	int CASCADE_COMPOSITE_INSTANCE_TRACE = 5;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CASCADE_COMPOSITE_INSTANCE_TRACE__TARGET = ABSTRACT_TRACE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CASCADE_COMPOSITE_INSTANCE_TRACE__SOURCE = ABSTRACT_TRACE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Cascade Composite Instance Trace</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CASCADE_COMPOSITE_INSTANCE_TRACE_FEATURE_COUNT = ABSTRACT_TRACE_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Cascade Composite Instance Trace</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CASCADE_COMPOSITE_INSTANCE_TRACE_OPERATION_COUNT = ABSTRACT_TRACE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.impl.AsynchronousCompositeComponentTraceImpl <em>Asynchronous Composite Component Trace</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.impl.AsynchronousCompositeComponentTraceImpl
	 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.impl.ReducerTracePackageImpl#getAsynchronousCompositeComponentTrace()
	 * @generated
	 */
	int ASYNCHRONOUS_COMPOSITE_COMPONENT_TRACE = 6;

	/**
	 * The feature id for the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASYNCHRONOUS_COMPOSITE_COMPONENT_TRACE__SOURCE = ABSTRACT_TRACE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASYNCHRONOUS_COMPOSITE_COMPONENT_TRACE__TARGET = ABSTRACT_TRACE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Asynchronous Composite Component Trace</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASYNCHRONOUS_COMPOSITE_COMPONENT_TRACE_FEATURE_COUNT = ABSTRACT_TRACE_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Asynchronous Composite Component Trace</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASYNCHRONOUS_COMPOSITE_COMPONENT_TRACE_OPERATION_COUNT = ABSTRACT_TRACE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.impl.SynchronousCompositeComponentTraceImpl <em>Synchronous Composite Component Trace</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.impl.SynchronousCompositeComponentTraceImpl
	 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.impl.ReducerTracePackageImpl#getSynchronousCompositeComponentTrace()
	 * @generated
	 */
	int SYNCHRONOUS_COMPOSITE_COMPONENT_TRACE = 7;

	/**
	 * The feature id for the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYNCHRONOUS_COMPOSITE_COMPONENT_TRACE__SOURCE = ABSTRACT_TRACE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYNCHRONOUS_COMPOSITE_COMPONENT_TRACE__TARGET = ABSTRACT_TRACE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Synchronous Composite Component Trace</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYNCHRONOUS_COMPOSITE_COMPONENT_TRACE_FEATURE_COUNT = ABSTRACT_TRACE_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Synchronous Composite Component Trace</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYNCHRONOUS_COMPOSITE_COMPONENT_TRACE_OPERATION_COUNT = ABSTRACT_TRACE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.impl.CascadeCompositeComponentTraceImpl <em>Cascade Composite Component Trace</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.impl.CascadeCompositeComponentTraceImpl
	 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.impl.ReducerTracePackageImpl#getCascadeCompositeComponentTrace()
	 * @generated
	 */
	int CASCADE_COMPOSITE_COMPONENT_TRACE = 8;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CASCADE_COMPOSITE_COMPONENT_TRACE__TARGET = ABSTRACT_TRACE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CASCADE_COMPOSITE_COMPONENT_TRACE__SOURCE = ABSTRACT_TRACE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Cascade Composite Component Trace</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CASCADE_COMPOSITE_COMPONENT_TRACE_FEATURE_COUNT = ABSTRACT_TRACE_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Cascade Composite Component Trace</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CASCADE_COMPOSITE_COMPONENT_TRACE_OPERATION_COUNT = ABSTRACT_TRACE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.impl.SimpleChannelTraceImpl <em>Simple Channel Trace</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.impl.SimpleChannelTraceImpl
	 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.impl.ReducerTracePackageImpl#getSimpleChannelTrace()
	 * @generated
	 */
	int SIMPLE_CHANNEL_TRACE = 9;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMPLE_CHANNEL_TRACE__TARGET = ABSTRACT_TRACE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMPLE_CHANNEL_TRACE__SOURCE = ABSTRACT_TRACE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Simple Channel Trace</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMPLE_CHANNEL_TRACE_FEATURE_COUNT = ABSTRACT_TRACE_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Simple Channel Trace</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMPLE_CHANNEL_TRACE_OPERATION_COUNT = ABSTRACT_TRACE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.impl.BindingTraceImpl <em>Binding Trace</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.impl.BindingTraceImpl
	 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.impl.ReducerTracePackageImpl#getBindingTrace()
	 * @generated
	 */
	int BINDING_TRACE = 10;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_TRACE__TARGET = ABSTRACT_TRACE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_TRACE__SOURCE = ABSTRACT_TRACE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Binding Trace</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_TRACE_FEATURE_COUNT = ABSTRACT_TRACE_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Binding Trace</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_TRACE_OPERATION_COUNT = ABSTRACT_TRACE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.impl.BroadcastChannelTraceImpl <em>Broadcast Channel Trace</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.impl.BroadcastChannelTraceImpl
	 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.impl.ReducerTracePackageImpl#getBroadcastChannelTrace()
	 * @generated
	 */
	int BROADCAST_CHANNEL_TRACE = 11;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BROADCAST_CHANNEL_TRACE__TARGET = ABSTRACT_TRACE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BROADCAST_CHANNEL_TRACE__SOURCE = ABSTRACT_TRACE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Broadcast Channel Trace</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BROADCAST_CHANNEL_TRACE_FEATURE_COUNT = ABSTRACT_TRACE_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Broadcast Channel Trace</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BROADCAST_CHANNEL_TRACE_OPERATION_COUNT = ABSTRACT_TRACE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.impl.PackageTraceImpl <em>Package Trace</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.impl.PackageTraceImpl
	 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.impl.ReducerTracePackageImpl#getPackageTrace()
	 * @generated
	 */
	int PACKAGE_TRACE = 12;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_TRACE__TARGET = ABSTRACT_TRACE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_TRACE__SOURCE = ABSTRACT_TRACE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Package Trace</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_TRACE_FEATURE_COUNT = ABSTRACT_TRACE_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Package Trace</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_TRACE_OPERATION_COUNT = ABSTRACT_TRACE_OPERATION_COUNT + 0;


	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.ReducerTrace <em>Reducer Trace</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Reducer Trace</em>'.
	 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.ReducerTrace
	 * @generated
	 */
	EClass getReducerTrace();

	/**
	 * Returns the meta object for the containment reference list '{@link hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.ReducerTrace#getTraces <em>Traces</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Traces</em>'.
	 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.ReducerTrace#getTraces()
	 * @see #getReducerTrace()
	 * @generated
	 */
	EReference getReducerTrace_Traces();

	/**
	 * Returns the meta object for the reference '{@link hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.ReducerTrace#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Source</em>'.
	 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.ReducerTrace#getSource()
	 * @see #getReducerTrace()
	 * @generated
	 */
	EReference getReducerTrace_Source();

	/**
	 * Returns the meta object for the reference '{@link hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.ReducerTrace#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Target</em>'.
	 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.ReducerTrace#getTarget()
	 * @see #getReducerTrace()
	 * @generated
	 */
	EReference getReducerTrace_Target();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.AbstractTrace <em>Abstract Trace</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Abstract Trace</em>'.
	 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.AbstractTrace
	 * @generated
	 */
	EClass getAbstractTrace();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.ElementaryTrace <em>Elementary Trace</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Elementary Trace</em>'.
	 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.ElementaryTrace
	 * @generated
	 */
	EClass getElementaryTrace();

	/**
	 * Returns the meta object for the reference '{@link hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.ElementaryTrace#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Source</em>'.
	 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.ElementaryTrace#getSource()
	 * @see #getElementaryTrace()
	 * @generated
	 */
	EReference getElementaryTrace_Source();

	/**
	 * Returns the meta object for the reference '{@link hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.ElementaryTrace#getTypeTarget <em>Type Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Type Target</em>'.
	 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.ElementaryTrace#getTypeTarget()
	 * @see #getElementaryTrace()
	 * @generated
	 */
	EReference getElementaryTrace_TypeTarget();

	/**
	 * Returns the meta object for the reference '{@link hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.ElementaryTrace#getInstanceTarget <em>Instance Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Instance Target</em>'.
	 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.ElementaryTrace#getInstanceTarget()
	 * @see #getElementaryTrace()
	 * @generated
	 */
	EReference getElementaryTrace_InstanceTarget();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.AsynchronousCompositeInstanceTrace <em>Asynchronous Composite Instance Trace</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Asynchronous Composite Instance Trace</em>'.
	 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.AsynchronousCompositeInstanceTrace
	 * @generated
	 */
	EClass getAsynchronousCompositeInstanceTrace();

	/**
	 * Returns the meta object for the reference '{@link hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.AsynchronousCompositeInstanceTrace#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Source</em>'.
	 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.AsynchronousCompositeInstanceTrace#getSource()
	 * @see #getAsynchronousCompositeInstanceTrace()
	 * @generated
	 */
	EReference getAsynchronousCompositeInstanceTrace_Source();

	/**
	 * Returns the meta object for the reference '{@link hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.AsynchronousCompositeInstanceTrace#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Target</em>'.
	 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.AsynchronousCompositeInstanceTrace#getTarget()
	 * @see #getAsynchronousCompositeInstanceTrace()
	 * @generated
	 */
	EReference getAsynchronousCompositeInstanceTrace_Target();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.SynchronousCompositeInstanceTrace <em>Synchronous Composite Instance Trace</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Synchronous Composite Instance Trace</em>'.
	 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.SynchronousCompositeInstanceTrace
	 * @generated
	 */
	EClass getSynchronousCompositeInstanceTrace();

	/**
	 * Returns the meta object for the reference '{@link hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.SynchronousCompositeInstanceTrace#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Source</em>'.
	 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.SynchronousCompositeInstanceTrace#getSource()
	 * @see #getSynchronousCompositeInstanceTrace()
	 * @generated
	 */
	EReference getSynchronousCompositeInstanceTrace_Source();

	/**
	 * Returns the meta object for the reference '{@link hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.SynchronousCompositeInstanceTrace#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Target</em>'.
	 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.SynchronousCompositeInstanceTrace#getTarget()
	 * @see #getSynchronousCompositeInstanceTrace()
	 * @generated
	 */
	EReference getSynchronousCompositeInstanceTrace_Target();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.CascadeCompositeInstanceTrace <em>Cascade Composite Instance Trace</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Cascade Composite Instance Trace</em>'.
	 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.CascadeCompositeInstanceTrace
	 * @generated
	 */
	EClass getCascadeCompositeInstanceTrace();

	/**
	 * Returns the meta object for the reference '{@link hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.CascadeCompositeInstanceTrace#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Target</em>'.
	 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.CascadeCompositeInstanceTrace#getTarget()
	 * @see #getCascadeCompositeInstanceTrace()
	 * @generated
	 */
	EReference getCascadeCompositeInstanceTrace_Target();

	/**
	 * Returns the meta object for the reference '{@link hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.CascadeCompositeInstanceTrace#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Source</em>'.
	 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.CascadeCompositeInstanceTrace#getSource()
	 * @see #getCascadeCompositeInstanceTrace()
	 * @generated
	 */
	EReference getCascadeCompositeInstanceTrace_Source();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.AsynchronousCompositeComponentTrace <em>Asynchronous Composite Component Trace</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Asynchronous Composite Component Trace</em>'.
	 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.AsynchronousCompositeComponentTrace
	 * @generated
	 */
	EClass getAsynchronousCompositeComponentTrace();

	/**
	 * Returns the meta object for the reference '{@link hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.AsynchronousCompositeComponentTrace#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Source</em>'.
	 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.AsynchronousCompositeComponentTrace#getSource()
	 * @see #getAsynchronousCompositeComponentTrace()
	 * @generated
	 */
	EReference getAsynchronousCompositeComponentTrace_Source();

	/**
	 * Returns the meta object for the reference '{@link hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.AsynchronousCompositeComponentTrace#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Target</em>'.
	 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.AsynchronousCompositeComponentTrace#getTarget()
	 * @see #getAsynchronousCompositeComponentTrace()
	 * @generated
	 */
	EReference getAsynchronousCompositeComponentTrace_Target();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.SynchronousCompositeComponentTrace <em>Synchronous Composite Component Trace</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Synchronous Composite Component Trace</em>'.
	 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.SynchronousCompositeComponentTrace
	 * @generated
	 */
	EClass getSynchronousCompositeComponentTrace();

	/**
	 * Returns the meta object for the reference '{@link hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.SynchronousCompositeComponentTrace#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Source</em>'.
	 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.SynchronousCompositeComponentTrace#getSource()
	 * @see #getSynchronousCompositeComponentTrace()
	 * @generated
	 */
	EReference getSynchronousCompositeComponentTrace_Source();

	/**
	 * Returns the meta object for the reference '{@link hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.SynchronousCompositeComponentTrace#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Target</em>'.
	 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.SynchronousCompositeComponentTrace#getTarget()
	 * @see #getSynchronousCompositeComponentTrace()
	 * @generated
	 */
	EReference getSynchronousCompositeComponentTrace_Target();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.CascadeCompositeComponentTrace <em>Cascade Composite Component Trace</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Cascade Composite Component Trace</em>'.
	 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.CascadeCompositeComponentTrace
	 * @generated
	 */
	EClass getCascadeCompositeComponentTrace();

	/**
	 * Returns the meta object for the reference '{@link hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.CascadeCompositeComponentTrace#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Target</em>'.
	 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.CascadeCompositeComponentTrace#getTarget()
	 * @see #getCascadeCompositeComponentTrace()
	 * @generated
	 */
	EReference getCascadeCompositeComponentTrace_Target();

	/**
	 * Returns the meta object for the reference '{@link hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.CascadeCompositeComponentTrace#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Source</em>'.
	 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.CascadeCompositeComponentTrace#getSource()
	 * @see #getCascadeCompositeComponentTrace()
	 * @generated
	 */
	EReference getCascadeCompositeComponentTrace_Source();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.SimpleChannelTrace <em>Simple Channel Trace</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Simple Channel Trace</em>'.
	 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.SimpleChannelTrace
	 * @generated
	 */
	EClass getSimpleChannelTrace();

	/**
	 * Returns the meta object for the reference '{@link hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.SimpleChannelTrace#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Target</em>'.
	 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.SimpleChannelTrace#getTarget()
	 * @see #getSimpleChannelTrace()
	 * @generated
	 */
	EReference getSimpleChannelTrace_Target();

	/**
	 * Returns the meta object for the reference '{@link hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.SimpleChannelTrace#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Source</em>'.
	 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.SimpleChannelTrace#getSource()
	 * @see #getSimpleChannelTrace()
	 * @generated
	 */
	EReference getSimpleChannelTrace_Source();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.BindingTrace <em>Binding Trace</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Binding Trace</em>'.
	 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.BindingTrace
	 * @generated
	 */
	EClass getBindingTrace();

	/**
	 * Returns the meta object for the reference '{@link hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.BindingTrace#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Target</em>'.
	 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.BindingTrace#getTarget()
	 * @see #getBindingTrace()
	 * @generated
	 */
	EReference getBindingTrace_Target();

	/**
	 * Returns the meta object for the reference '{@link hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.BindingTrace#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Source</em>'.
	 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.BindingTrace#getSource()
	 * @see #getBindingTrace()
	 * @generated
	 */
	EReference getBindingTrace_Source();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.BroadcastChannelTrace <em>Broadcast Channel Trace</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Broadcast Channel Trace</em>'.
	 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.BroadcastChannelTrace
	 * @generated
	 */
	EClass getBroadcastChannelTrace();

	/**
	 * Returns the meta object for the reference '{@link hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.BroadcastChannelTrace#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Target</em>'.
	 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.BroadcastChannelTrace#getTarget()
	 * @see #getBroadcastChannelTrace()
	 * @generated
	 */
	EReference getBroadcastChannelTrace_Target();

	/**
	 * Returns the meta object for the reference '{@link hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.BroadcastChannelTrace#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Source</em>'.
	 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.BroadcastChannelTrace#getSource()
	 * @see #getBroadcastChannelTrace()
	 * @generated
	 */
	EReference getBroadcastChannelTrace_Source();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.PackageTrace <em>Package Trace</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Package Trace</em>'.
	 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.PackageTrace
	 * @generated
	 */
	EClass getPackageTrace();

	/**
	 * Returns the meta object for the reference '{@link hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.PackageTrace#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Target</em>'.
	 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.PackageTrace#getTarget()
	 * @see #getPackageTrace()
	 * @generated
	 */
	EReference getPackageTrace_Target();

	/**
	 * Returns the meta object for the reference '{@link hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.PackageTrace#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Source</em>'.
	 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.PackageTrace#getSource()
	 * @see #getPackageTrace()
	 * @generated
	 */
	EReference getPackageTrace_Source();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ReducerTraceFactory getReducerTraceFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.impl.ReducerTraceImpl <em>Reducer Trace</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.impl.ReducerTraceImpl
		 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.impl.ReducerTracePackageImpl#getReducerTrace()
		 * @generated
		 */
		EClass REDUCER_TRACE = eINSTANCE.getReducerTrace();

		/**
		 * The meta object literal for the '<em><b>Traces</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REDUCER_TRACE__TRACES = eINSTANCE.getReducerTrace_Traces();

		/**
		 * The meta object literal for the '<em><b>Source</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REDUCER_TRACE__SOURCE = eINSTANCE.getReducerTrace_Source();

		/**
		 * The meta object literal for the '<em><b>Target</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REDUCER_TRACE__TARGET = eINSTANCE.getReducerTrace_Target();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.impl.AbstractTraceImpl <em>Abstract Trace</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.impl.AbstractTraceImpl
		 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.impl.ReducerTracePackageImpl#getAbstractTrace()
		 * @generated
		 */
		EClass ABSTRACT_TRACE = eINSTANCE.getAbstractTrace();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.impl.ElementaryTraceImpl <em>Elementary Trace</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.impl.ElementaryTraceImpl
		 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.impl.ReducerTracePackageImpl#getElementaryTrace()
		 * @generated
		 */
		EClass ELEMENTARY_TRACE = eINSTANCE.getElementaryTrace();

		/**
		 * The meta object literal for the '<em><b>Source</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ELEMENTARY_TRACE__SOURCE = eINSTANCE.getElementaryTrace_Source();

		/**
		 * The meta object literal for the '<em><b>Type Target</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ELEMENTARY_TRACE__TYPE_TARGET = eINSTANCE.getElementaryTrace_TypeTarget();

		/**
		 * The meta object literal for the '<em><b>Instance Target</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ELEMENTARY_TRACE__INSTANCE_TARGET = eINSTANCE.getElementaryTrace_InstanceTarget();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.impl.AsynchronousCompositeInstanceTraceImpl <em>Asynchronous Composite Instance Trace</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.impl.AsynchronousCompositeInstanceTraceImpl
		 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.impl.ReducerTracePackageImpl#getAsynchronousCompositeInstanceTrace()
		 * @generated
		 */
		EClass ASYNCHRONOUS_COMPOSITE_INSTANCE_TRACE = eINSTANCE.getAsynchronousCompositeInstanceTrace();

		/**
		 * The meta object literal for the '<em><b>Source</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ASYNCHRONOUS_COMPOSITE_INSTANCE_TRACE__SOURCE = eINSTANCE.getAsynchronousCompositeInstanceTrace_Source();

		/**
		 * The meta object literal for the '<em><b>Target</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ASYNCHRONOUS_COMPOSITE_INSTANCE_TRACE__TARGET = eINSTANCE.getAsynchronousCompositeInstanceTrace_Target();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.impl.SynchronousCompositeInstanceTraceImpl <em>Synchronous Composite Instance Trace</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.impl.SynchronousCompositeInstanceTraceImpl
		 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.impl.ReducerTracePackageImpl#getSynchronousCompositeInstanceTrace()
		 * @generated
		 */
		EClass SYNCHRONOUS_COMPOSITE_INSTANCE_TRACE = eINSTANCE.getSynchronousCompositeInstanceTrace();

		/**
		 * The meta object literal for the '<em><b>Source</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYNCHRONOUS_COMPOSITE_INSTANCE_TRACE__SOURCE = eINSTANCE.getSynchronousCompositeInstanceTrace_Source();

		/**
		 * The meta object literal for the '<em><b>Target</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYNCHRONOUS_COMPOSITE_INSTANCE_TRACE__TARGET = eINSTANCE.getSynchronousCompositeInstanceTrace_Target();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.impl.CascadeCompositeInstanceTraceImpl <em>Cascade Composite Instance Trace</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.impl.CascadeCompositeInstanceTraceImpl
		 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.impl.ReducerTracePackageImpl#getCascadeCompositeInstanceTrace()
		 * @generated
		 */
		EClass CASCADE_COMPOSITE_INSTANCE_TRACE = eINSTANCE.getCascadeCompositeInstanceTrace();

		/**
		 * The meta object literal for the '<em><b>Target</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CASCADE_COMPOSITE_INSTANCE_TRACE__TARGET = eINSTANCE.getCascadeCompositeInstanceTrace_Target();

		/**
		 * The meta object literal for the '<em><b>Source</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CASCADE_COMPOSITE_INSTANCE_TRACE__SOURCE = eINSTANCE.getCascadeCompositeInstanceTrace_Source();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.impl.AsynchronousCompositeComponentTraceImpl <em>Asynchronous Composite Component Trace</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.impl.AsynchronousCompositeComponentTraceImpl
		 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.impl.ReducerTracePackageImpl#getAsynchronousCompositeComponentTrace()
		 * @generated
		 */
		EClass ASYNCHRONOUS_COMPOSITE_COMPONENT_TRACE = eINSTANCE.getAsynchronousCompositeComponentTrace();

		/**
		 * The meta object literal for the '<em><b>Source</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ASYNCHRONOUS_COMPOSITE_COMPONENT_TRACE__SOURCE = eINSTANCE.getAsynchronousCompositeComponentTrace_Source();

		/**
		 * The meta object literal for the '<em><b>Target</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ASYNCHRONOUS_COMPOSITE_COMPONENT_TRACE__TARGET = eINSTANCE.getAsynchronousCompositeComponentTrace_Target();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.impl.SynchronousCompositeComponentTraceImpl <em>Synchronous Composite Component Trace</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.impl.SynchronousCompositeComponentTraceImpl
		 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.impl.ReducerTracePackageImpl#getSynchronousCompositeComponentTrace()
		 * @generated
		 */
		EClass SYNCHRONOUS_COMPOSITE_COMPONENT_TRACE = eINSTANCE.getSynchronousCompositeComponentTrace();

		/**
		 * The meta object literal for the '<em><b>Source</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYNCHRONOUS_COMPOSITE_COMPONENT_TRACE__SOURCE = eINSTANCE.getSynchronousCompositeComponentTrace_Source();

		/**
		 * The meta object literal for the '<em><b>Target</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYNCHRONOUS_COMPOSITE_COMPONENT_TRACE__TARGET = eINSTANCE.getSynchronousCompositeComponentTrace_Target();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.impl.CascadeCompositeComponentTraceImpl <em>Cascade Composite Component Trace</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.impl.CascadeCompositeComponentTraceImpl
		 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.impl.ReducerTracePackageImpl#getCascadeCompositeComponentTrace()
		 * @generated
		 */
		EClass CASCADE_COMPOSITE_COMPONENT_TRACE = eINSTANCE.getCascadeCompositeComponentTrace();

		/**
		 * The meta object literal for the '<em><b>Target</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CASCADE_COMPOSITE_COMPONENT_TRACE__TARGET = eINSTANCE.getCascadeCompositeComponentTrace_Target();

		/**
		 * The meta object literal for the '<em><b>Source</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CASCADE_COMPOSITE_COMPONENT_TRACE__SOURCE = eINSTANCE.getCascadeCompositeComponentTrace_Source();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.impl.SimpleChannelTraceImpl <em>Simple Channel Trace</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.impl.SimpleChannelTraceImpl
		 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.impl.ReducerTracePackageImpl#getSimpleChannelTrace()
		 * @generated
		 */
		EClass SIMPLE_CHANNEL_TRACE = eINSTANCE.getSimpleChannelTrace();

		/**
		 * The meta object literal for the '<em><b>Target</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SIMPLE_CHANNEL_TRACE__TARGET = eINSTANCE.getSimpleChannelTrace_Target();

		/**
		 * The meta object literal for the '<em><b>Source</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SIMPLE_CHANNEL_TRACE__SOURCE = eINSTANCE.getSimpleChannelTrace_Source();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.impl.BindingTraceImpl <em>Binding Trace</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.impl.BindingTraceImpl
		 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.impl.ReducerTracePackageImpl#getBindingTrace()
		 * @generated
		 */
		EClass BINDING_TRACE = eINSTANCE.getBindingTrace();

		/**
		 * The meta object literal for the '<em><b>Target</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BINDING_TRACE__TARGET = eINSTANCE.getBindingTrace_Target();

		/**
		 * The meta object literal for the '<em><b>Source</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BINDING_TRACE__SOURCE = eINSTANCE.getBindingTrace_Source();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.impl.BroadcastChannelTraceImpl <em>Broadcast Channel Trace</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.impl.BroadcastChannelTraceImpl
		 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.impl.ReducerTracePackageImpl#getBroadcastChannelTrace()
		 * @generated
		 */
		EClass BROADCAST_CHANNEL_TRACE = eINSTANCE.getBroadcastChannelTrace();

		/**
		 * The meta object literal for the '<em><b>Target</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BROADCAST_CHANNEL_TRACE__TARGET = eINSTANCE.getBroadcastChannelTrace_Target();

		/**
		 * The meta object literal for the '<em><b>Source</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BROADCAST_CHANNEL_TRACE__SOURCE = eINSTANCE.getBroadcastChannelTrace_Source();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.impl.PackageTraceImpl <em>Package Trace</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.impl.PackageTraceImpl
		 * @see hu.bme.mit.gamma.environment.analysis.traceability.reducertrace.impl.ReducerTracePackageImpl#getPackageTrace()
		 * @generated
		 */
		EClass PACKAGE_TRACE = eINSTANCE.getPackageTrace();

		/**
		 * The meta object literal for the '<em><b>Target</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PACKAGE_TRACE__TARGET = eINSTANCE.getPackageTrace_Target();

		/**
		 * The meta object literal for the '<em><b>Source</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PACKAGE_TRACE__SOURCE = eINSTANCE.getPackageTrace_Source();

	}

} //ReducerTracePackage
