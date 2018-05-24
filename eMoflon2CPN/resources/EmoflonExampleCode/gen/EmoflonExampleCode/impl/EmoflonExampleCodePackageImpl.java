/**
 */
package EmoflonExampleCode.impl;

import EmoflonExampleCode.Counter;
import EmoflonExampleCode.EmoflonExampleCodeFactory;
import EmoflonExampleCode.EmoflonExampleCodePackage;
import EmoflonExampleCode.Marker;
import EmoflonExampleCode.One;
import EmoflonExampleCode.Three;
import EmoflonExampleCode.Two;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class EmoflonExampleCodePackageImpl extends EPackageImpl implements EmoflonExampleCodePackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass containerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass markerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass counterEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass oneEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass twoEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass threeEClass = null;

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
	 * @see EmoflonExampleCode.EmoflonExampleCodePackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private EmoflonExampleCodePackageImpl() {
		super(eNS_URI, EmoflonExampleCodeFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link EmoflonExampleCodePackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static EmoflonExampleCodePackage init() {
		if (isInited)
			return (EmoflonExampleCodePackage) EPackage.Registry.INSTANCE
					.getEPackage(EmoflonExampleCodePackage.eNS_URI);

		// Obtain or create and register package
		EmoflonExampleCodePackageImpl theEmoflonExampleCodePackage = (EmoflonExampleCodePackageImpl) (EPackage.Registry.INSTANCE
				.get(eNS_URI) instanceof EmoflonExampleCodePackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI)
						: new EmoflonExampleCodePackageImpl());

		isInited = true;

		// Create package meta-data objects
		theEmoflonExampleCodePackage.createPackageContents();

		// Initialize created meta-data
		theEmoflonExampleCodePackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theEmoflonExampleCodePackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(EmoflonExampleCodePackage.eNS_URI, theEmoflonExampleCodePackage);
		return theEmoflonExampleCodePackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getContainer() {
		return containerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getContainer_Counters() {
		return (EReference) containerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getContainer_Markers() {
		return (EReference) containerEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getContainer__Count() {
		return containerEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getContainer__Empty() {
		return containerEClass.getEOperations().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMarker() {
		return markerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getMarker__Nothing() {
		return markerEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCounter() {
		return counterEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getOne() {
		return oneEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTwo() {
		return twoEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getThree() {
		return threeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EmoflonExampleCodeFactory getEmoflonExampleCodeFactory() {
		return (EmoflonExampleCodeFactory) getEFactoryInstance();
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
		if (isCreated)
			return;
		isCreated = true;

		// Create classes and their features
		containerEClass = createEClass(CONTAINER);
		createEReference(containerEClass, CONTAINER__COUNTERS);
		createEReference(containerEClass, CONTAINER__MARKERS);
		createEOperation(containerEClass, CONTAINER___COUNT);
		createEOperation(containerEClass, CONTAINER___EMPTY);

		markerEClass = createEClass(MARKER);
		createEOperation(markerEClass, MARKER___NOTHING);

		counterEClass = createEClass(COUNTER);

		oneEClass = createEClass(ONE);

		twoEClass = createEClass(TWO);

		threeEClass = createEClass(THREE);
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
		if (isInitialized)
			return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		oneEClass.getESuperTypes().add(this.getCounter());
		twoEClass.getESuperTypes().add(this.getCounter());
		threeEClass.getESuperTypes().add(this.getCounter());

		// Initialize classes, features, and operations; add parameters
		initEClass(containerEClass, EmoflonExampleCode.Container.class, "Container", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getContainer_Counters(), this.getCounter(), null, "counters", null, 0, 1,
				EmoflonExampleCode.Container.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getContainer_Markers(), this.getMarker(), null, "markers", null, 0, -1,
				EmoflonExampleCode.Container.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEOperation(getContainer__Count(), null, "count", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEOperation(getContainer__Empty(), null, "empty", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(markerEClass, Marker.class, "Marker", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEOperation(getMarker__Nothing(), null, "nothing", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(counterEClass, Counter.class, "Counter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(oneEClass, One.class, "One", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(twoEClass, Two.class, "Two", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(threeEClass, Three.class, "Three", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);
	}

} //EmoflonExampleCodePackageImpl
