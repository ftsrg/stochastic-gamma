/**
 */
package hu.bme.mit.gamma.environment.model.impl;

import hu.bme.mit.gamma.environment.model.EnvironmentModelPackage;
import hu.bme.mit.gamma.environment.model.EnvironmentSynchronousCompositeComponent;

import hu.bme.mit.gamma.expression.model.ExpressionModelPackage;
import hu.bme.mit.gamma.expression.model.FunctionDeclaration;
import hu.bme.mit.gamma.expression.model.NamedElement;
import hu.bme.mit.gamma.expression.model.ParameterDeclaration;
import hu.bme.mit.gamma.expression.model.ParametricElement;

import hu.bme.mit.gamma.statechart.composite.AbstractSynchronousCompositeComponent;
import hu.bme.mit.gamma.statechart.composite.Channel;
import hu.bme.mit.gamma.statechart.composite.CompositeComponent;
import hu.bme.mit.gamma.statechart.composite.CompositeModelPackage;
import hu.bme.mit.gamma.statechart.composite.PortBinding;
import hu.bme.mit.gamma.statechart.composite.SynchronousComponent;
import hu.bme.mit.gamma.statechart.composite.SynchronousComponentInstance;
import hu.bme.mit.gamma.statechart.composite.SynchronousCompositeComponent;

import hu.bme.mit.gamma.statechart.interface_.Component;
import hu.bme.mit.gamma.statechart.interface_.ComponentAnnotation;
import hu.bme.mit.gamma.statechart.interface_.InterfaceModelPackage;
import hu.bme.mit.gamma.statechart.interface_.Port;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Environment Synchronous Composite Component</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link hu.bme.mit.gamma.environment.model.impl.EnvironmentSynchronousCompositeComponentImpl#getName <em>Name</em>}</li>
 *   <li>{@link hu.bme.mit.gamma.environment.model.impl.EnvironmentSynchronousCompositeComponentImpl#getParameterDeclarations <em>Parameter Declarations</em>}</li>
 *   <li>{@link hu.bme.mit.gamma.environment.model.impl.EnvironmentSynchronousCompositeComponentImpl#getPorts <em>Ports</em>}</li>
 *   <li>{@link hu.bme.mit.gamma.environment.model.impl.EnvironmentSynchronousCompositeComponentImpl#getFunctionDeclarations <em>Function Declarations</em>}</li>
 *   <li>{@link hu.bme.mit.gamma.environment.model.impl.EnvironmentSynchronousCompositeComponentImpl#getAnnotations <em>Annotations</em>}</li>
 *   <li>{@link hu.bme.mit.gamma.environment.model.impl.EnvironmentSynchronousCompositeComponentImpl#getPortBindings <em>Port Bindings</em>}</li>
 *   <li>{@link hu.bme.mit.gamma.environment.model.impl.EnvironmentSynchronousCompositeComponentImpl#getChannels <em>Channels</em>}</li>
 *   <li>{@link hu.bme.mit.gamma.environment.model.impl.EnvironmentSynchronousCompositeComponentImpl#getComponents <em>Components</em>}</li>
 * </ul>
 *
 * @generated
 */
public class EnvironmentSynchronousCompositeComponentImpl extends AbstractEnvironmentCompositeComponentImpl implements EnvironmentSynchronousCompositeComponent {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getParameterDeclarations() <em>Parameter Declarations</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParameterDeclarations()
	 * @generated
	 * @ordered
	 */
	protected EList<ParameterDeclaration> parameterDeclarations;

	/**
	 * The cached value of the '{@link #getPorts() <em>Ports</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPorts()
	 * @generated
	 * @ordered
	 */
	protected EList<Port> ports;

	/**
	 * The cached value of the '{@link #getFunctionDeclarations() <em>Function Declarations</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFunctionDeclarations()
	 * @generated
	 * @ordered
	 */
	protected EList<FunctionDeclaration> functionDeclarations;

	/**
	 * The cached value of the '{@link #getAnnotations() <em>Annotations</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAnnotations()
	 * @generated
	 * @ordered
	 */
	protected EList<ComponentAnnotation> annotations;

	/**
	 * The cached value of the '{@link #getPortBindings() <em>Port Bindings</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPortBindings()
	 * @generated
	 * @ordered
	 */
	protected EList<PortBinding> portBindings;

	/**
	 * The cached value of the '{@link #getChannels() <em>Channels</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getChannels()
	 * @generated
	 * @ordered
	 */
	protected EList<Channel> channels;

	/**
	 * The cached value of the '{@link #getComponents() <em>Components</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getComponents()
	 * @generated
	 * @ordered
	 */
	protected EList<SynchronousComponentInstance> components;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EnvironmentSynchronousCompositeComponentImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EnvironmentModelPackage.Literals.ENVIRONMENT_SYNCHRONOUS_COMPOSITE_COMPONENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EnvironmentModelPackage.ENVIRONMENT_SYNCHRONOUS_COMPOSITE_COMPONENT__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ParameterDeclaration> getParameterDeclarations() {
		if (parameterDeclarations == null) {
			parameterDeclarations = new EObjectContainmentEList<ParameterDeclaration>(ParameterDeclaration.class, this, EnvironmentModelPackage.ENVIRONMENT_SYNCHRONOUS_COMPOSITE_COMPONENT__PARAMETER_DECLARATIONS);
		}
		return parameterDeclarations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Port> getPorts() {
		if (ports == null) {
			ports = new EObjectContainmentEList<Port>(Port.class, this, EnvironmentModelPackage.ENVIRONMENT_SYNCHRONOUS_COMPOSITE_COMPONENT__PORTS);
		}
		return ports;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<FunctionDeclaration> getFunctionDeclarations() {
		if (functionDeclarations == null) {
			functionDeclarations = new EObjectContainmentEList<FunctionDeclaration>(FunctionDeclaration.class, this, EnvironmentModelPackage.ENVIRONMENT_SYNCHRONOUS_COMPOSITE_COMPONENT__FUNCTION_DECLARATIONS);
		}
		return functionDeclarations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ComponentAnnotation> getAnnotations() {
		if (annotations == null) {
			annotations = new EObjectContainmentEList<ComponentAnnotation>(ComponentAnnotation.class, this, EnvironmentModelPackage.ENVIRONMENT_SYNCHRONOUS_COMPOSITE_COMPONENT__ANNOTATIONS);
		}
		return annotations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<PortBinding> getPortBindings() {
		if (portBindings == null) {
			portBindings = new EObjectContainmentEList<PortBinding>(PortBinding.class, this, EnvironmentModelPackage.ENVIRONMENT_SYNCHRONOUS_COMPOSITE_COMPONENT__PORT_BINDINGS);
		}
		return portBindings;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Channel> getChannels() {
		if (channels == null) {
			channels = new EObjectContainmentEList<Channel>(Channel.class, this, EnvironmentModelPackage.ENVIRONMENT_SYNCHRONOUS_COMPOSITE_COMPONENT__CHANNELS);
		}
		return channels;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<SynchronousComponentInstance> getComponents() {
		if (components == null) {
			components = new EObjectContainmentEList<SynchronousComponentInstance>(SynchronousComponentInstance.class, this, EnvironmentModelPackage.ENVIRONMENT_SYNCHRONOUS_COMPOSITE_COMPONENT__COMPONENTS);
		}
		return components;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case EnvironmentModelPackage.ENVIRONMENT_SYNCHRONOUS_COMPOSITE_COMPONENT__PARAMETER_DECLARATIONS:
				return ((InternalEList<?>)getParameterDeclarations()).basicRemove(otherEnd, msgs);
			case EnvironmentModelPackage.ENVIRONMENT_SYNCHRONOUS_COMPOSITE_COMPONENT__PORTS:
				return ((InternalEList<?>)getPorts()).basicRemove(otherEnd, msgs);
			case EnvironmentModelPackage.ENVIRONMENT_SYNCHRONOUS_COMPOSITE_COMPONENT__FUNCTION_DECLARATIONS:
				return ((InternalEList<?>)getFunctionDeclarations()).basicRemove(otherEnd, msgs);
			case EnvironmentModelPackage.ENVIRONMENT_SYNCHRONOUS_COMPOSITE_COMPONENT__ANNOTATIONS:
				return ((InternalEList<?>)getAnnotations()).basicRemove(otherEnd, msgs);
			case EnvironmentModelPackage.ENVIRONMENT_SYNCHRONOUS_COMPOSITE_COMPONENT__PORT_BINDINGS:
				return ((InternalEList<?>)getPortBindings()).basicRemove(otherEnd, msgs);
			case EnvironmentModelPackage.ENVIRONMENT_SYNCHRONOUS_COMPOSITE_COMPONENT__CHANNELS:
				return ((InternalEList<?>)getChannels()).basicRemove(otherEnd, msgs);
			case EnvironmentModelPackage.ENVIRONMENT_SYNCHRONOUS_COMPOSITE_COMPONENT__COMPONENTS:
				return ((InternalEList<?>)getComponents()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case EnvironmentModelPackage.ENVIRONMENT_SYNCHRONOUS_COMPOSITE_COMPONENT__NAME:
				return getName();
			case EnvironmentModelPackage.ENVIRONMENT_SYNCHRONOUS_COMPOSITE_COMPONENT__PARAMETER_DECLARATIONS:
				return getParameterDeclarations();
			case EnvironmentModelPackage.ENVIRONMENT_SYNCHRONOUS_COMPOSITE_COMPONENT__PORTS:
				return getPorts();
			case EnvironmentModelPackage.ENVIRONMENT_SYNCHRONOUS_COMPOSITE_COMPONENT__FUNCTION_DECLARATIONS:
				return getFunctionDeclarations();
			case EnvironmentModelPackage.ENVIRONMENT_SYNCHRONOUS_COMPOSITE_COMPONENT__ANNOTATIONS:
				return getAnnotations();
			case EnvironmentModelPackage.ENVIRONMENT_SYNCHRONOUS_COMPOSITE_COMPONENT__PORT_BINDINGS:
				return getPortBindings();
			case EnvironmentModelPackage.ENVIRONMENT_SYNCHRONOUS_COMPOSITE_COMPONENT__CHANNELS:
				return getChannels();
			case EnvironmentModelPackage.ENVIRONMENT_SYNCHRONOUS_COMPOSITE_COMPONENT__COMPONENTS:
				return getComponents();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case EnvironmentModelPackage.ENVIRONMENT_SYNCHRONOUS_COMPOSITE_COMPONENT__NAME:
				setName((String)newValue);
				return;
			case EnvironmentModelPackage.ENVIRONMENT_SYNCHRONOUS_COMPOSITE_COMPONENT__PARAMETER_DECLARATIONS:
				getParameterDeclarations().clear();
				getParameterDeclarations().addAll((Collection<? extends ParameterDeclaration>)newValue);
				return;
			case EnvironmentModelPackage.ENVIRONMENT_SYNCHRONOUS_COMPOSITE_COMPONENT__PORTS:
				getPorts().clear();
				getPorts().addAll((Collection<? extends Port>)newValue);
				return;
			case EnvironmentModelPackage.ENVIRONMENT_SYNCHRONOUS_COMPOSITE_COMPONENT__FUNCTION_DECLARATIONS:
				getFunctionDeclarations().clear();
				getFunctionDeclarations().addAll((Collection<? extends FunctionDeclaration>)newValue);
				return;
			case EnvironmentModelPackage.ENVIRONMENT_SYNCHRONOUS_COMPOSITE_COMPONENT__ANNOTATIONS:
				getAnnotations().clear();
				getAnnotations().addAll((Collection<? extends ComponentAnnotation>)newValue);
				return;
			case EnvironmentModelPackage.ENVIRONMENT_SYNCHRONOUS_COMPOSITE_COMPONENT__PORT_BINDINGS:
				getPortBindings().clear();
				getPortBindings().addAll((Collection<? extends PortBinding>)newValue);
				return;
			case EnvironmentModelPackage.ENVIRONMENT_SYNCHRONOUS_COMPOSITE_COMPONENT__CHANNELS:
				getChannels().clear();
				getChannels().addAll((Collection<? extends Channel>)newValue);
				return;
			case EnvironmentModelPackage.ENVIRONMENT_SYNCHRONOUS_COMPOSITE_COMPONENT__COMPONENTS:
				getComponents().clear();
				getComponents().addAll((Collection<? extends SynchronousComponentInstance>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case EnvironmentModelPackage.ENVIRONMENT_SYNCHRONOUS_COMPOSITE_COMPONENT__NAME:
				setName(NAME_EDEFAULT);
				return;
			case EnvironmentModelPackage.ENVIRONMENT_SYNCHRONOUS_COMPOSITE_COMPONENT__PARAMETER_DECLARATIONS:
				getParameterDeclarations().clear();
				return;
			case EnvironmentModelPackage.ENVIRONMENT_SYNCHRONOUS_COMPOSITE_COMPONENT__PORTS:
				getPorts().clear();
				return;
			case EnvironmentModelPackage.ENVIRONMENT_SYNCHRONOUS_COMPOSITE_COMPONENT__FUNCTION_DECLARATIONS:
				getFunctionDeclarations().clear();
				return;
			case EnvironmentModelPackage.ENVIRONMENT_SYNCHRONOUS_COMPOSITE_COMPONENT__ANNOTATIONS:
				getAnnotations().clear();
				return;
			case EnvironmentModelPackage.ENVIRONMENT_SYNCHRONOUS_COMPOSITE_COMPONENT__PORT_BINDINGS:
				getPortBindings().clear();
				return;
			case EnvironmentModelPackage.ENVIRONMENT_SYNCHRONOUS_COMPOSITE_COMPONENT__CHANNELS:
				getChannels().clear();
				return;
			case EnvironmentModelPackage.ENVIRONMENT_SYNCHRONOUS_COMPOSITE_COMPONENT__COMPONENTS:
				getComponents().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case EnvironmentModelPackage.ENVIRONMENT_SYNCHRONOUS_COMPOSITE_COMPONENT__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case EnvironmentModelPackage.ENVIRONMENT_SYNCHRONOUS_COMPOSITE_COMPONENT__PARAMETER_DECLARATIONS:
				return parameterDeclarations != null && !parameterDeclarations.isEmpty();
			case EnvironmentModelPackage.ENVIRONMENT_SYNCHRONOUS_COMPOSITE_COMPONENT__PORTS:
				return ports != null && !ports.isEmpty();
			case EnvironmentModelPackage.ENVIRONMENT_SYNCHRONOUS_COMPOSITE_COMPONENT__FUNCTION_DECLARATIONS:
				return functionDeclarations != null && !functionDeclarations.isEmpty();
			case EnvironmentModelPackage.ENVIRONMENT_SYNCHRONOUS_COMPOSITE_COMPONENT__ANNOTATIONS:
				return annotations != null && !annotations.isEmpty();
			case EnvironmentModelPackage.ENVIRONMENT_SYNCHRONOUS_COMPOSITE_COMPONENT__PORT_BINDINGS:
				return portBindings != null && !portBindings.isEmpty();
			case EnvironmentModelPackage.ENVIRONMENT_SYNCHRONOUS_COMPOSITE_COMPONENT__CHANNELS:
				return channels != null && !channels.isEmpty();
			case EnvironmentModelPackage.ENVIRONMENT_SYNCHRONOUS_COMPOSITE_COMPONENT__COMPONENTS:
				return components != null && !components.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == NamedElement.class) {
			switch (derivedFeatureID) {
				case EnvironmentModelPackage.ENVIRONMENT_SYNCHRONOUS_COMPOSITE_COMPONENT__NAME: return ExpressionModelPackage.NAMED_ELEMENT__NAME;
				default: return -1;
			}
		}
		if (baseClass == ParametricElement.class) {
			switch (derivedFeatureID) {
				case EnvironmentModelPackage.ENVIRONMENT_SYNCHRONOUS_COMPOSITE_COMPONENT__PARAMETER_DECLARATIONS: return ExpressionModelPackage.PARAMETRIC_ELEMENT__PARAMETER_DECLARATIONS;
				default: return -1;
			}
		}
		if (baseClass == Component.class) {
			switch (derivedFeatureID) {
				case EnvironmentModelPackage.ENVIRONMENT_SYNCHRONOUS_COMPOSITE_COMPONENT__PORTS: return InterfaceModelPackage.COMPONENT__PORTS;
				case EnvironmentModelPackage.ENVIRONMENT_SYNCHRONOUS_COMPOSITE_COMPONENT__FUNCTION_DECLARATIONS: return InterfaceModelPackage.COMPONENT__FUNCTION_DECLARATIONS;
				case EnvironmentModelPackage.ENVIRONMENT_SYNCHRONOUS_COMPOSITE_COMPONENT__ANNOTATIONS: return InterfaceModelPackage.COMPONENT__ANNOTATIONS;
				default: return -1;
			}
		}
		if (baseClass == CompositeComponent.class) {
			switch (derivedFeatureID) {
				case EnvironmentModelPackage.ENVIRONMENT_SYNCHRONOUS_COMPOSITE_COMPONENT__PORT_BINDINGS: return CompositeModelPackage.COMPOSITE_COMPONENT__PORT_BINDINGS;
				case EnvironmentModelPackage.ENVIRONMENT_SYNCHRONOUS_COMPOSITE_COMPONENT__CHANNELS: return CompositeModelPackage.COMPOSITE_COMPONENT__CHANNELS;
				default: return -1;
			}
		}
		if (baseClass == SynchronousComponent.class) {
			switch (derivedFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == AbstractSynchronousCompositeComponent.class) {
			switch (derivedFeatureID) {
				case EnvironmentModelPackage.ENVIRONMENT_SYNCHRONOUS_COMPOSITE_COMPONENT__COMPONENTS: return CompositeModelPackage.ABSTRACT_SYNCHRONOUS_COMPOSITE_COMPONENT__COMPONENTS;
				default: return -1;
			}
		}
		if (baseClass == SynchronousCompositeComponent.class) {
			switch (derivedFeatureID) {
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == NamedElement.class) {
			switch (baseFeatureID) {
				case ExpressionModelPackage.NAMED_ELEMENT__NAME: return EnvironmentModelPackage.ENVIRONMENT_SYNCHRONOUS_COMPOSITE_COMPONENT__NAME;
				default: return -1;
			}
		}
		if (baseClass == ParametricElement.class) {
			switch (baseFeatureID) {
				case ExpressionModelPackage.PARAMETRIC_ELEMENT__PARAMETER_DECLARATIONS: return EnvironmentModelPackage.ENVIRONMENT_SYNCHRONOUS_COMPOSITE_COMPONENT__PARAMETER_DECLARATIONS;
				default: return -1;
			}
		}
		if (baseClass == Component.class) {
			switch (baseFeatureID) {
				case InterfaceModelPackage.COMPONENT__PORTS: return EnvironmentModelPackage.ENVIRONMENT_SYNCHRONOUS_COMPOSITE_COMPONENT__PORTS;
				case InterfaceModelPackage.COMPONENT__FUNCTION_DECLARATIONS: return EnvironmentModelPackage.ENVIRONMENT_SYNCHRONOUS_COMPOSITE_COMPONENT__FUNCTION_DECLARATIONS;
				case InterfaceModelPackage.COMPONENT__ANNOTATIONS: return EnvironmentModelPackage.ENVIRONMENT_SYNCHRONOUS_COMPOSITE_COMPONENT__ANNOTATIONS;
				default: return -1;
			}
		}
		if (baseClass == CompositeComponent.class) {
			switch (baseFeatureID) {
				case CompositeModelPackage.COMPOSITE_COMPONENT__PORT_BINDINGS: return EnvironmentModelPackage.ENVIRONMENT_SYNCHRONOUS_COMPOSITE_COMPONENT__PORT_BINDINGS;
				case CompositeModelPackage.COMPOSITE_COMPONENT__CHANNELS: return EnvironmentModelPackage.ENVIRONMENT_SYNCHRONOUS_COMPOSITE_COMPONENT__CHANNELS;
				default: return -1;
			}
		}
		if (baseClass == SynchronousComponent.class) {
			switch (baseFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == AbstractSynchronousCompositeComponent.class) {
			switch (baseFeatureID) {
				case CompositeModelPackage.ABSTRACT_SYNCHRONOUS_COMPOSITE_COMPONENT__COMPONENTS: return EnvironmentModelPackage.ENVIRONMENT_SYNCHRONOUS_COMPOSITE_COMPONENT__COMPONENTS;
				default: return -1;
			}
		}
		if (baseClass == SynchronousCompositeComponent.class) {
			switch (baseFeatureID) {
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (name: ");
		result.append(name);
		result.append(')');
		return result.toString();
	}

} //EnvironmentSynchronousCompositeComponentImpl
