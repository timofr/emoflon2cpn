/**
 */
package EmoflonExampleCode.impl;

import EmoflonExampleCode.*;

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
public class EmoflonExampleCodeFactoryImpl extends EFactoryImpl implements EmoflonExampleCodeFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static EmoflonExampleCodeFactory init() {
		try {
			EmoflonExampleCodeFactory theEmoflonExampleCodeFactory = (EmoflonExampleCodeFactory) EPackage.Registry.INSTANCE
					.getEFactory(EmoflonExampleCodePackage.eNS_URI);
			if (theEmoflonExampleCodeFactory != null) {
				return theEmoflonExampleCodeFactory;
			}
		} catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new EmoflonExampleCodeFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EmoflonExampleCodeFactoryImpl() {
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
		case EmoflonExampleCodePackage.CONTAINER:
			return createContainer();
		case EmoflonExampleCodePackage.MARKER:
			return createMarker();
		case EmoflonExampleCodePackage.COUNTER:
			return createCounter();
		case EmoflonExampleCodePackage.ONE:
			return createOne();
		case EmoflonExampleCodePackage.TWO:
			return createTwo();
		case EmoflonExampleCodePackage.THREE:
			return createThree();
		default:
			throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EmoflonExampleCode.Container createContainer() {
		ContainerImpl container = new ContainerImpl();
		return container;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Marker createMarker() {
		MarkerImpl marker = new MarkerImpl();
		return marker;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Counter createCounter() {
		CounterImpl counter = new CounterImpl();
		return counter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public One createOne() {
		OneImpl one = new OneImpl();
		return one;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Two createTwo() {
		TwoImpl two = new TwoImpl();
		return two;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Three createThree() {
		ThreeImpl three = new ThreeImpl();
		return three;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EmoflonExampleCodePackage getEmoflonExampleCodePackage() {
		return (EmoflonExampleCodePackage) getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static EmoflonExampleCodePackage getPackage() {
		return EmoflonExampleCodePackage.eINSTANCE;
	}

} //EmoflonExampleCodeFactoryImpl
