/**
 */
package EmoflonExampleCode;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
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
 * @see EmoflonExampleCode.EmoflonExampleCodeFactory
 * @model kind="package"
 * @generated
 */
public interface EmoflonExampleCodePackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "EmoflonExampleCode";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "platform:/plugin/EmoflonExampleCode/model/EmoflonExampleCode.ecore";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "EmoflonExampleCode";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	EmoflonExampleCodePackage eINSTANCE = EmoflonExampleCode.impl.EmoflonExampleCodePackageImpl.init();

	/**
	 * The meta object id for the '{@link EmoflonExampleCode.impl.ContainerImpl <em>Container</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see EmoflonExampleCode.impl.ContainerImpl
	 * @see EmoflonExampleCode.impl.EmoflonExampleCodePackageImpl#getContainer()
	 * @generated
	 */
	int CONTAINER = 0;

	/**
	 * The feature id for the '<em><b>Counters</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER__COUNTERS = 0;

	/**
	 * The feature id for the '<em><b>Markers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER__MARKERS = 1;

	/**
	 * The number of structural features of the '<em>Container</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_FEATURE_COUNT = 2;

	/**
	 * The operation id for the '<em>Count</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER___COUNT = 0;

	/**
	 * The operation id for the '<em>Empty</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER___EMPTY = 1;

	/**
	 * The number of operations of the '<em>Container</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_OPERATION_COUNT = 2;

	/**
	 * The meta object id for the '{@link EmoflonExampleCode.impl.MarkerImpl <em>Marker</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see EmoflonExampleCode.impl.MarkerImpl
	 * @see EmoflonExampleCode.impl.EmoflonExampleCodePackageImpl#getMarker()
	 * @generated
	 */
	int MARKER = 1;

	/**
	 * The number of structural features of the '<em>Marker</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MARKER_FEATURE_COUNT = 0;

	/**
	 * The operation id for the '<em>Nothing</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MARKER___NOTHING = 0;

	/**
	 * The number of operations of the '<em>Marker</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MARKER_OPERATION_COUNT = 1;

	/**
	 * The meta object id for the '{@link EmoflonExampleCode.impl.CounterImpl <em>Counter</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see EmoflonExampleCode.impl.CounterImpl
	 * @see EmoflonExampleCode.impl.EmoflonExampleCodePackageImpl#getCounter()
	 * @generated
	 */
	int COUNTER = 2;

	/**
	 * The number of structural features of the '<em>Counter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COUNTER_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Counter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COUNTER_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link EmoflonExampleCode.impl.OneImpl <em>One</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see EmoflonExampleCode.impl.OneImpl
	 * @see EmoflonExampleCode.impl.EmoflonExampleCodePackageImpl#getOne()
	 * @generated
	 */
	int ONE = 3;

	/**
	 * The number of structural features of the '<em>One</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ONE_FEATURE_COUNT = COUNTER_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>One</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ONE_OPERATION_COUNT = COUNTER_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link EmoflonExampleCode.impl.TwoImpl <em>Two</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see EmoflonExampleCode.impl.TwoImpl
	 * @see EmoflonExampleCode.impl.EmoflonExampleCodePackageImpl#getTwo()
	 * @generated
	 */
	int TWO = 4;

	/**
	 * The number of structural features of the '<em>Two</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TWO_FEATURE_COUNT = COUNTER_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Two</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TWO_OPERATION_COUNT = COUNTER_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link EmoflonExampleCode.impl.ThreeImpl <em>Three</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see EmoflonExampleCode.impl.ThreeImpl
	 * @see EmoflonExampleCode.impl.EmoflonExampleCodePackageImpl#getThree()
	 * @generated
	 */
	int THREE = 5;

	/**
	 * The number of structural features of the '<em>Three</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int THREE_FEATURE_COUNT = COUNTER_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Three</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int THREE_OPERATION_COUNT = COUNTER_OPERATION_COUNT + 0;

	/**
	 * Returns the meta object for class '{@link EmoflonExampleCode.Container <em>Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Container</em>'.
	 * @see EmoflonExampleCode.Container
	 * @generated
	 */
	EClass getContainer();

	/**
	 * Returns the meta object for the containment reference '{@link EmoflonExampleCode.Container#getCounters <em>Counters</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Counters</em>'.
	 * @see EmoflonExampleCode.Container#getCounters()
	 * @see #getContainer()
	 * @generated
	 */
	EReference getContainer_Counters();

	/**
	 * Returns the meta object for the containment reference list '{@link EmoflonExampleCode.Container#getMarkers <em>Markers</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Markers</em>'.
	 * @see EmoflonExampleCode.Container#getMarkers()
	 * @see #getContainer()
	 * @generated
	 */
	EReference getContainer_Markers();

	/**
	 * Returns the meta object for the '{@link EmoflonExampleCode.Container#count() <em>Count</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Count</em>' operation.
	 * @see EmoflonExampleCode.Container#count()
	 * @generated
	 */
	EOperation getContainer__Count();

	/**
	 * Returns the meta object for the '{@link EmoflonExampleCode.Container#empty() <em>Empty</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Empty</em>' operation.
	 * @see EmoflonExampleCode.Container#empty()
	 * @generated
	 */
	EOperation getContainer__Empty();

	/**
	 * Returns the meta object for class '{@link EmoflonExampleCode.Marker <em>Marker</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Marker</em>'.
	 * @see EmoflonExampleCode.Marker
	 * @generated
	 */
	EClass getMarker();

	/**
	 * Returns the meta object for the '{@link EmoflonExampleCode.Marker#nothing() <em>Nothing</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Nothing</em>' operation.
	 * @see EmoflonExampleCode.Marker#nothing()
	 * @generated
	 */
	EOperation getMarker__Nothing();

	/**
	 * Returns the meta object for class '{@link EmoflonExampleCode.Counter <em>Counter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Counter</em>'.
	 * @see EmoflonExampleCode.Counter
	 * @generated
	 */
	EClass getCounter();

	/**
	 * Returns the meta object for class '{@link EmoflonExampleCode.One <em>One</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>One</em>'.
	 * @see EmoflonExampleCode.One
	 * @generated
	 */
	EClass getOne();

	/**
	 * Returns the meta object for class '{@link EmoflonExampleCode.Two <em>Two</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Two</em>'.
	 * @see EmoflonExampleCode.Two
	 * @generated
	 */
	EClass getTwo();

	/**
	 * Returns the meta object for class '{@link EmoflonExampleCode.Three <em>Three</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Three</em>'.
	 * @see EmoflonExampleCode.Three
	 * @generated
	 */
	EClass getThree();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	EmoflonExampleCodeFactory getEmoflonExampleCodeFactory();

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
		 * The meta object literal for the '{@link EmoflonExampleCode.impl.ContainerImpl <em>Container</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see EmoflonExampleCode.impl.ContainerImpl
		 * @see EmoflonExampleCode.impl.EmoflonExampleCodePackageImpl#getContainer()
		 * @generated
		 */
		EClass CONTAINER = eINSTANCE.getContainer();

		/**
		 * The meta object literal for the '<em><b>Counters</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTAINER__COUNTERS = eINSTANCE.getContainer_Counters();

		/**
		 * The meta object literal for the '<em><b>Markers</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTAINER__MARKERS = eINSTANCE.getContainer_Markers();

		/**
		 * The meta object literal for the '<em><b>Count</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation CONTAINER___COUNT = eINSTANCE.getContainer__Count();

		/**
		 * The meta object literal for the '<em><b>Empty</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation CONTAINER___EMPTY = eINSTANCE.getContainer__Empty();

		/**
		 * The meta object literal for the '{@link EmoflonExampleCode.impl.MarkerImpl <em>Marker</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see EmoflonExampleCode.impl.MarkerImpl
		 * @see EmoflonExampleCode.impl.EmoflonExampleCodePackageImpl#getMarker()
		 * @generated
		 */
		EClass MARKER = eINSTANCE.getMarker();

		/**
		 * The meta object literal for the '<em><b>Nothing</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation MARKER___NOTHING = eINSTANCE.getMarker__Nothing();

		/**
		 * The meta object literal for the '{@link EmoflonExampleCode.impl.CounterImpl <em>Counter</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see EmoflonExampleCode.impl.CounterImpl
		 * @see EmoflonExampleCode.impl.EmoflonExampleCodePackageImpl#getCounter()
		 * @generated
		 */
		EClass COUNTER = eINSTANCE.getCounter();

		/**
		 * The meta object literal for the '{@link EmoflonExampleCode.impl.OneImpl <em>One</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see EmoflonExampleCode.impl.OneImpl
		 * @see EmoflonExampleCode.impl.EmoflonExampleCodePackageImpl#getOne()
		 * @generated
		 */
		EClass ONE = eINSTANCE.getOne();

		/**
		 * The meta object literal for the '{@link EmoflonExampleCode.impl.TwoImpl <em>Two</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see EmoflonExampleCode.impl.TwoImpl
		 * @see EmoflonExampleCode.impl.EmoflonExampleCodePackageImpl#getTwo()
		 * @generated
		 */
		EClass TWO = eINSTANCE.getTwo();

		/**
		 * The meta object literal for the '{@link EmoflonExampleCode.impl.ThreeImpl <em>Three</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see EmoflonExampleCode.impl.ThreeImpl
		 * @see EmoflonExampleCode.impl.EmoflonExampleCodePackageImpl#getThree()
		 * @generated
		 */
		EClass THREE = eINSTANCE.getThree();

	}

} //EmoflonExampleCodePackage
