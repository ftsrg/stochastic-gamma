/**
 */
package hu.bme.mit.gamma.fmeda.model.impl;

import hu.bme.mit.gamma.fmeda.model.Channel;
import hu.bme.mit.gamma.fmeda.model.FMEDAComponent;
import hu.bme.mit.gamma.fmeda.model.FMEDAComponentInstance;
import hu.bme.mit.gamma.fmeda.model.FailureMode;
import hu.bme.mit.gamma.fmeda.model.FailurePropagation;
import hu.bme.mit.gamma.fmeda.model.Port;
import hu.bme.mit.gamma.fmeda.model.fmedaPackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>FMEDA Component</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link hu.bme.mit.gamma.fmeda.model.impl.FMEDAComponentImpl#getFailuremodes <em>Failuremodes</em>}</li>
 *   <li>{@link hu.bme.mit.gamma.fmeda.model.impl.FMEDAComponentImpl#getSubcomponents <em>Subcomponents</em>}</li>
 *   <li>{@link hu.bme.mit.gamma.fmeda.model.impl.FMEDAComponentImpl#getFailurepropagations <em>Failurepropagations</em>}</li>
 *   <li>{@link hu.bme.mit.gamma.fmeda.model.impl.FMEDAComponentImpl#getChannels <em>Channels</em>}</li>
 *   <li>{@link hu.bme.mit.gamma.fmeda.model.impl.FMEDAComponentImpl#getPorts <em>Ports</em>}</li>
 * </ul>
 *
 * @generated
 */
public class FMEDAComponentImpl extends NamedElementImpl implements FMEDAComponent {
	/**
	 * The cached value of the '{@link #getFailuremodes() <em>Failuremodes</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFailuremodes()
	 * @generated
	 * @ordered
	 */
	protected EList<FailureMode> failuremodes;

	/**
	 * The cached value of the '{@link #getSubcomponents() <em>Subcomponents</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSubcomponents()
	 * @generated
	 * @ordered
	 */
	protected EList<FMEDAComponentInstance> subcomponents;

	/**
	 * The cached value of the '{@link #getFailurepropagations() <em>Failurepropagations</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFailurepropagations()
	 * @generated
	 * @ordered
	 */
	protected EList<FailurePropagation> failurepropagations;

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
	 * The cached value of the '{@link #getPorts() <em>Ports</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPorts()
	 * @generated
	 * @ordered
	 */
	protected EList<Port> ports;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FMEDAComponentImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return fmedaPackage.Literals.FMEDA_COMPONENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<FailureMode> getFailuremodes() {
		if (failuremodes == null) {
			failuremodes = new EObjectContainmentEList<FailureMode>(FailureMode.class, this, fmedaPackage.FMEDA_COMPONENT__FAILUREMODES);
		}
		return failuremodes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<FMEDAComponentInstance> getSubcomponents() {
		if (subcomponents == null) {
			subcomponents = new EObjectContainmentEList<FMEDAComponentInstance>(FMEDAComponentInstance.class, this, fmedaPackage.FMEDA_COMPONENT__SUBCOMPONENTS);
		}
		return subcomponents;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<FailurePropagation> getFailurepropagations() {
		if (failurepropagations == null) {
			failurepropagations = new EObjectContainmentEList<FailurePropagation>(FailurePropagation.class, this, fmedaPackage.FMEDA_COMPONENT__FAILUREPROPAGATIONS);
		}
		return failurepropagations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Channel> getChannels() {
		if (channels == null) {
			channels = new EObjectContainmentEList<Channel>(Channel.class, this, fmedaPackage.FMEDA_COMPONENT__CHANNELS);
		}
		return channels;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Port> getPorts() {
		if (ports == null) {
			ports = new EObjectContainmentEList<Port>(Port.class, this, fmedaPackage.FMEDA_COMPONENT__PORTS);
		}
		return ports;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case fmedaPackage.FMEDA_COMPONENT__FAILUREMODES:
				return ((InternalEList<?>)getFailuremodes()).basicRemove(otherEnd, msgs);
			case fmedaPackage.FMEDA_COMPONENT__SUBCOMPONENTS:
				return ((InternalEList<?>)getSubcomponents()).basicRemove(otherEnd, msgs);
			case fmedaPackage.FMEDA_COMPONENT__FAILUREPROPAGATIONS:
				return ((InternalEList<?>)getFailurepropagations()).basicRemove(otherEnd, msgs);
			case fmedaPackage.FMEDA_COMPONENT__CHANNELS:
				return ((InternalEList<?>)getChannels()).basicRemove(otherEnd, msgs);
			case fmedaPackage.FMEDA_COMPONENT__PORTS:
				return ((InternalEList<?>)getPorts()).basicRemove(otherEnd, msgs);
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
			case fmedaPackage.FMEDA_COMPONENT__FAILUREMODES:
				return getFailuremodes();
			case fmedaPackage.FMEDA_COMPONENT__SUBCOMPONENTS:
				return getSubcomponents();
			case fmedaPackage.FMEDA_COMPONENT__FAILUREPROPAGATIONS:
				return getFailurepropagations();
			case fmedaPackage.FMEDA_COMPONENT__CHANNELS:
				return getChannels();
			case fmedaPackage.FMEDA_COMPONENT__PORTS:
				return getPorts();
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
			case fmedaPackage.FMEDA_COMPONENT__FAILUREMODES:
				getFailuremodes().clear();
				getFailuremodes().addAll((Collection<? extends FailureMode>)newValue);
				return;
			case fmedaPackage.FMEDA_COMPONENT__SUBCOMPONENTS:
				getSubcomponents().clear();
				getSubcomponents().addAll((Collection<? extends FMEDAComponentInstance>)newValue);
				return;
			case fmedaPackage.FMEDA_COMPONENT__FAILUREPROPAGATIONS:
				getFailurepropagations().clear();
				getFailurepropagations().addAll((Collection<? extends FailurePropagation>)newValue);
				return;
			case fmedaPackage.FMEDA_COMPONENT__CHANNELS:
				getChannels().clear();
				getChannels().addAll((Collection<? extends Channel>)newValue);
				return;
			case fmedaPackage.FMEDA_COMPONENT__PORTS:
				getPorts().clear();
				getPorts().addAll((Collection<? extends Port>)newValue);
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
			case fmedaPackage.FMEDA_COMPONENT__FAILUREMODES:
				getFailuremodes().clear();
				return;
			case fmedaPackage.FMEDA_COMPONENT__SUBCOMPONENTS:
				getSubcomponents().clear();
				return;
			case fmedaPackage.FMEDA_COMPONENT__FAILUREPROPAGATIONS:
				getFailurepropagations().clear();
				return;
			case fmedaPackage.FMEDA_COMPONENT__CHANNELS:
				getChannels().clear();
				return;
			case fmedaPackage.FMEDA_COMPONENT__PORTS:
				getPorts().clear();
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
			case fmedaPackage.FMEDA_COMPONENT__FAILUREMODES:
				return failuremodes != null && !failuremodes.isEmpty();
			case fmedaPackage.FMEDA_COMPONENT__SUBCOMPONENTS:
				return subcomponents != null && !subcomponents.isEmpty();
			case fmedaPackage.FMEDA_COMPONENT__FAILUREPROPAGATIONS:
				return failurepropagations != null && !failurepropagations.isEmpty();
			case fmedaPackage.FMEDA_COMPONENT__CHANNELS:
				return channels != null && !channels.isEmpty();
			case fmedaPackage.FMEDA_COMPONENT__PORTS:
				return ports != null && !ports.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //FMEDAComponentImpl
