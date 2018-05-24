/**
 */
package EmoflonExampleCode;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;
// <-- [user defined imports]
// [user defined imports] -->

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Container</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link EmoflonExampleCode.Container#getCounters <em>Counters</em>}</li>
 *   <li>{@link EmoflonExampleCode.Container#getMarkers <em>Markers</em>}</li>
 * </ul>
 * </p>
 *
 * @see EmoflonExampleCode.EmoflonExampleCodePackage#getContainer()
 * @model
 * @generated
 */
public interface Container extends EObject {
	/**
	 * Returns the value of the '<em><b>Counters</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Counters</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Counters</em>' containment reference.
	 * @see #setCounters(Counter)
	 * @see EmoflonExampleCode.EmoflonExampleCodePackage#getContainer_Counters()
	 * @model containment="true"
	 * @generated
	 */
	Counter getCounters();

	/**
	 * Sets the value of the '{@link EmoflonExampleCode.Container#getCounters <em>Counters</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Counters</em>' containment reference.
	 * @see #getCounters()
	 * @generated
	 */
	void setCounters(Counter value);

	/**
	 * Returns the value of the '<em><b>Markers</b></em>' containment reference list.
	 * The list contents are of type {@link EmoflonExampleCode.Marker}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Markers</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Markers</em>' containment reference list.
	 * @see EmoflonExampleCode.EmoflonExampleCodePackage#getContainer_Markers()
	 * @model containment="true"
	 * @generated
	 */
	EList<Marker> getMarkers();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	void count();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	void empty();
	// <-- [user code injected with eMoflon]

	// [user code injected with eMoflon] -->
} // Container
