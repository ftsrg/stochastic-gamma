/**
 */
package hu.bme.mit.gamma.environment.stochastic.stochastic.impl;

import hu.bme.mit.gamma.environment.stochastic.stochastic.GammaRandomVariable;
import hu.bme.mit.gamma.environment.stochastic.stochastic.StochasticPackage;

import hu.bme.mit.gamma.expression.model.Expression;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Gamma Random Variable</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link hu.bme.mit.gamma.environment.stochastic.stochastic.impl.GammaRandomVariableImpl#getScale <em>Scale</em>}</li>
 *   <li>{@link hu.bme.mit.gamma.environment.stochastic.stochastic.impl.GammaRandomVariableImpl#getShape <em>Shape</em>}</li>
 * </ul>
 *
 * @generated
 */
public class GammaRandomVariableImpl extends ContinouosRandomVariableImpl implements GammaRandomVariable {
	/**
	 * The cached value of the '{@link #getScale() <em>Scale</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getScale()
	 * @generated
	 * @ordered
	 */
	protected Expression scale;

	/**
	 * The cached value of the '{@link #getShape() <em>Shape</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getShape()
	 * @generated
	 * @ordered
	 */
	protected Expression shape;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GammaRandomVariableImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return StochasticPackage.Literals.GAMMA_RANDOM_VARIABLE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression getScale() {
		return scale;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetScale(Expression newScale, NotificationChain msgs) {
		Expression oldScale = scale;
		scale = newScale;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, StochasticPackage.GAMMA_RANDOM_VARIABLE__SCALE, oldScale, newScale);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setScale(Expression newScale) {
		if (newScale != scale) {
			NotificationChain msgs = null;
			if (scale != null)
				msgs = ((InternalEObject)scale).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - StochasticPackage.GAMMA_RANDOM_VARIABLE__SCALE, null, msgs);
			if (newScale != null)
				msgs = ((InternalEObject)newScale).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - StochasticPackage.GAMMA_RANDOM_VARIABLE__SCALE, null, msgs);
			msgs = basicSetScale(newScale, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StochasticPackage.GAMMA_RANDOM_VARIABLE__SCALE, newScale, newScale));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression getShape() {
		return shape;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetShape(Expression newShape, NotificationChain msgs) {
		Expression oldShape = shape;
		shape = newShape;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, StochasticPackage.GAMMA_RANDOM_VARIABLE__SHAPE, oldShape, newShape);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setShape(Expression newShape) {
		if (newShape != shape) {
			NotificationChain msgs = null;
			if (shape != null)
				msgs = ((InternalEObject)shape).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - StochasticPackage.GAMMA_RANDOM_VARIABLE__SHAPE, null, msgs);
			if (newShape != null)
				msgs = ((InternalEObject)newShape).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - StochasticPackage.GAMMA_RANDOM_VARIABLE__SHAPE, null, msgs);
			msgs = basicSetShape(newShape, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StochasticPackage.GAMMA_RANDOM_VARIABLE__SHAPE, newShape, newShape));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case StochasticPackage.GAMMA_RANDOM_VARIABLE__SCALE:
				return basicSetScale(null, msgs);
			case StochasticPackage.GAMMA_RANDOM_VARIABLE__SHAPE:
				return basicSetShape(null, msgs);
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
			case StochasticPackage.GAMMA_RANDOM_VARIABLE__SCALE:
				return getScale();
			case StochasticPackage.GAMMA_RANDOM_VARIABLE__SHAPE:
				return getShape();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case StochasticPackage.GAMMA_RANDOM_VARIABLE__SCALE:
				setScale((Expression)newValue);
				return;
			case StochasticPackage.GAMMA_RANDOM_VARIABLE__SHAPE:
				setShape((Expression)newValue);
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
			case StochasticPackage.GAMMA_RANDOM_VARIABLE__SCALE:
				setScale((Expression)null);
				return;
			case StochasticPackage.GAMMA_RANDOM_VARIABLE__SHAPE:
				setShape((Expression)null);
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
			case StochasticPackage.GAMMA_RANDOM_VARIABLE__SCALE:
				return scale != null;
			case StochasticPackage.GAMMA_RANDOM_VARIABLE__SHAPE:
				return shape != null;
		}
		return super.eIsSet(featureID);
	}

} //GammaRandomVariableImpl
