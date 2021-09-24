/**
 */
package hu.bme.mit.gamma.fmeda.model;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>FMEDA Component</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link hu.bme.mit.gamma.fmeda.model.FMEDAComponent#getFailuremodes <em>Failuremodes</em>}</li>
 *   <li>{@link hu.bme.mit.gamma.fmeda.model.FMEDAComponent#getSubcomponents <em>Subcomponents</em>}</li>
 *   <li>{@link hu.bme.mit.gamma.fmeda.model.FMEDAComponent#getFailurepropagations <em>Failurepropagations</em>}</li>
 *   <li>{@link hu.bme.mit.gamma.fmeda.model.FMEDAComponent#getChannels <em>Channels</em>}</li>
 *   <li>{@link hu.bme.mit.gamma.fmeda.model.FMEDAComponent#getPorts <em>Ports</em>}</li>
 * </ul>
 *
 * @see hu.bme.mit.gamma.fmeda.model.fmedaPackage#getFMEDAComponent()
 * @model
 * @generated
 */
public interface FMEDAComponent extends NamedElement {
	/**
	 * Returns the value of the '<em><b>Failuremodes</b></em>' containment reference list.
	 * The list contents are of type {@link hu.bme.mit.gamma.fmeda.model.FailureMode}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Failuremodes</em>' containment reference list.
	 * @see hu.bme.mit.gamma.fmeda.model.fmedaPackage#getFMEDAComponent_Failuremodes()
	 * @model containment="true"
	 * @generated
	 */
	EList<FailureMode> getFailuremodes();

	/**
	 * Returns the value of the '<em><b>Subcomponents</b></em>' containment reference list.
	 * The list contents are of type {@link hu.bme.mit.gamma.fmeda.model.FMEDAComponentInstance}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Subcomponents</em>' containment reference list.
	 * @see hu.bme.mit.gamma.fmeda.model.fmedaPackage#getFMEDAComponent_Subcomponents()
	 * @model containment="true"
	 * @generated
	 */
	EList<FMEDAComponentInstance> getSubcomponents();

	/**
	 * Returns the value of the '<em><b>Failurepropagations</b></em>' containment reference list.
	 * The list contents are of type {@link hu.bme.mit.gamma.fmeda.model.FailurePropagation}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Failurepropagations</em>' containment reference list.
	 * @see hu.bme.mit.gamma.fmeda.model.fmedaPackage#getFMEDAComponent_Failurepropagations()
	 * @model containment="true"
	 * @generated
	 */
	EList<FailurePropagation> getFailurepropagations();

	/**
	 * Returns the value of the '<em><b>Channels</b></em>' containment reference list.
	 * The list contents are of type {@link hu.bme.mit.gamma.fmeda.model.Channel}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Channels</em>' containment reference list.
	 * @see hu.bme.mit.gamma.fmeda.model.fmedaPackage#getFMEDAComponent_Channels()
	 * @model containment="true"
	 * @generated
	 */
	EList<Channel> getChannels();

	/**
	 * Returns the value of the '<em><b>Ports</b></em>' containment reference list.
	 * The list contents are of type {@link hu.bme.mit.gamma.fmeda.model.Port}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ports</em>' containment reference list.
	 * @see hu.bme.mit.gamma.fmeda.model.fmedaPackage#getFMEDAComponent_Ports()
	 * @model containment="true"
	 * @generated
	 */
	EList<Port> getPorts();

} // FMEDAComponent
