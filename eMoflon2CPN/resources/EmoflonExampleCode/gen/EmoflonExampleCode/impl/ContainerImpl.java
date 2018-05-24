/**
 */
package EmoflonExampleCode.impl;

import EmoflonExampleCode.Container;
import EmoflonExampleCode.Counter;
import EmoflonExampleCode.EmoflonExampleCodeFactory;
import EmoflonExampleCode.EmoflonExampleCodePackage;
import EmoflonExampleCode.Marker;
import EmoflonExampleCode.One;
import EmoflonExampleCode.Three;
import EmoflonExampleCode.Two;

import java.lang.reflect.InvocationTargetException;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
// <-- [user defined imports]
// [user defined imports] -->

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Container</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link EmoflonExampleCode.impl.ContainerImpl#getCounters <em>Counters</em>}</li>
 *   <li>{@link EmoflonExampleCode.impl.ContainerImpl#getMarkers <em>Markers</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ContainerImpl extends EObjectImpl implements Container {
	/**
	 * The cached value of the '{@link #getCounters() <em>Counters</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCounters()
	 * @generated
	 * @ordered
	 */
	protected Counter counters;

	/**
	 * The cached value of the '{@link #getMarkers() <em>Markers</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMarkers()
	 * @generated
	 * @ordered
	 */
	protected EList<Marker> markers;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ContainerImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EmoflonExampleCodePackage.Literals.CONTAINER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Counter getCounters() {
		return counters;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCounters(Counter newCounters, NotificationChain msgs) {
		Counter oldCounters = counters;
		counters = newCounters;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					EmoflonExampleCodePackage.CONTAINER__COUNTERS, oldCounters, newCounters);
			if (msgs == null)
				msgs = notification;
			else
				msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCounters(Counter newCounters) {
		if (newCounters != counters) {
			NotificationChain msgs = null;
			if (counters != null)
				msgs = ((InternalEObject) counters).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - EmoflonExampleCodePackage.CONTAINER__COUNTERS, null, msgs);
			if (newCounters != null)
				msgs = ((InternalEObject) newCounters).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - EmoflonExampleCodePackage.CONTAINER__COUNTERS, null, msgs);
			msgs = basicSetCounters(newCounters, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EmoflonExampleCodePackage.CONTAINER__COUNTERS,
					newCounters, newCounters));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Marker> getMarkers() {
		if (markers == null) {
			markers = new EObjectContainmentEList<Marker>(Marker.class, this,
					EmoflonExampleCodePackage.CONTAINER__MARKERS);
		}
		return markers;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void count() {

		Object[] result1_black = ContainerImpl.pattern_Container_0_1_one_blackBF(this);
		if (result1_black == null) {
			throw new RuntimeException("Pattern matching failed." + " Variables: " + "[this] = " + this + ".");
		}
		Marker marker1 = (Marker) result1_black[1];
		Object[] result1_green = ContainerImpl.pattern_Container_0_1_one_greenBF(this);
		One one = (One) result1_green[1];

		// 
		Object[] result2_black = ContainerImpl.pattern_Container_0_2_two_blackBBFB(this, marker1, one);
		if (result2_black != null) {
			Marker marker2 = (Marker) result2_black[2];
			ContainerImpl.pattern_Container_0_2_two_redBB(this, one);

			Object[] result2_green = ContainerImpl.pattern_Container_0_2_two_greenBF(this);
			Two two = (Two) result2_green[1];
			EcoreUtil.delete(one);
			one = null;
			;

			// 
			Object[] result3_black = ContainerImpl.pattern_Container_0_3_three_blackBBBFB(this, marker1, marker2, two);
			if (result3_black != null) {
				//nothing Marker marker3 = (Marker) result3_black[3];
				ContainerImpl.pattern_Container_0_3_three_redBB(this, two);

				ContainerImpl.pattern_Container_0_3_three_greenBF(this);
				//nothing Three three = (Three) result3_green[1];
				EcoreUtil.delete(two);
				two = null;
				;

			} else {
			}

		} else {
		}
		return;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void empty() {
		// [user code injected with eMoflon]

		// TODO: implement this method here but do not remove the injection marker 
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case EmoflonExampleCodePackage.CONTAINER__COUNTERS:
			return basicSetCounters(null, msgs);
		case EmoflonExampleCodePackage.CONTAINER__MARKERS:
			return ((InternalEList<?>) getMarkers()).basicRemove(otherEnd, msgs);
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
		case EmoflonExampleCodePackage.CONTAINER__COUNTERS:
			return getCounters();
		case EmoflonExampleCodePackage.CONTAINER__MARKERS:
			return getMarkers();
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
		case EmoflonExampleCodePackage.CONTAINER__COUNTERS:
			setCounters((Counter) newValue);
			return;
		case EmoflonExampleCodePackage.CONTAINER__MARKERS:
			getMarkers().clear();
			getMarkers().addAll((Collection<? extends Marker>) newValue);
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
		case EmoflonExampleCodePackage.CONTAINER__COUNTERS:
			setCounters((Counter) null);
			return;
		case EmoflonExampleCodePackage.CONTAINER__MARKERS:
			getMarkers().clear();
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
		case EmoflonExampleCodePackage.CONTAINER__COUNTERS:
			return counters != null;
		case EmoflonExampleCodePackage.CONTAINER__MARKERS:
			return markers != null && !markers.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eInvoke(int operationID, EList<?> arguments) throws InvocationTargetException {
		switch (operationID) {
		case EmoflonExampleCodePackage.CONTAINER___COUNT:
			count();
			return null;
		case EmoflonExampleCodePackage.CONTAINER___EMPTY:
			empty();
			return null;
		}
		return super.eInvoke(operationID, arguments);
	}

	public static final Object[] pattern_Container_0_1_one_blackBF(Container _this) {
		for (Marker marker1 : _this.getMarkers()) {
			return new Object[] { _this, marker1 };
		}
		return null;
	}

	public static final Object[] pattern_Container_0_1_one_greenBF(Container _this) {
		One one = EmoflonExampleCodeFactory.eINSTANCE.createOne();
		_this.setCounters(one);
		return new Object[] { _this, one };
	}

	public static final Object[] pattern_Container_0_2_two_blackBBFB(Container _this, Marker marker1, One one) {
		if (_this.getMarkers().contains(marker1)) {
			if (one.equals(_this.getCounters())) {
				for (Marker marker2 : _this.getMarkers()) {
					if (!marker1.equals(marker2)) {
						return new Object[] { _this, marker1, marker2, one };
					}
				}
			}
		}
		return null;
	}

	public static final Object[] pattern_Container_0_2_two_redBB(Container _this, One one) {
		_this.setCounters(null);
		return new Object[] { _this, one };
	}

	public static final Object[] pattern_Container_0_2_two_greenBF(Container _this) {
		Two two = EmoflonExampleCodeFactory.eINSTANCE.createTwo();
		_this.setCounters(two);
		return new Object[] { _this, two };
	}

	public static final Object[] pattern_Container_0_3_three_blackBBBFB(Container _this, Marker marker1, Marker marker2,
			Two two) {
		if (!marker1.equals(marker2)) {
			if (_this.getMarkers().contains(marker1)) {
				if (_this.getMarkers().contains(marker2)) {
					if (two.equals(_this.getCounters())) {
						for (Marker marker3 : _this.getMarkers()) {
							if (!marker1.equals(marker3)) {
								if (!marker2.equals(marker3)) {
									return new Object[] { _this, marker1, marker2, marker3, two };
								}
							}
						}
					}
				}
			}
		}
		return null;
	}

	public static final Object[] pattern_Container_0_3_three_redBB(Container _this, Two two) {
		_this.setCounters(null);
		return new Object[] { _this, two };
	}

	public static final Object[] pattern_Container_0_3_three_greenBF(Container _this) {
		Three three = EmoflonExampleCodeFactory.eINSTANCE.createThree();
		_this.setCounters(three);
		return new Object[] { _this, three };
	}

	// <-- [user code injected with eMoflon]

	// [user code injected with eMoflon] -->
} //ContainerImpl
