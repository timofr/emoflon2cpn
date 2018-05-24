/**
 */
package EmoflonExampleCode;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see EmoflonExampleCode.EmoflonExampleCodePackage
 * @generated
 */
public interface EmoflonExampleCodeFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	EmoflonExampleCodeFactory eINSTANCE = EmoflonExampleCode.impl.EmoflonExampleCodeFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Container</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Container</em>'.
	 * @generated
	 */
	Container createContainer();

	/**
	 * Returns a new object of class '<em>Marker</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Marker</em>'.
	 * @generated
	 */
	Marker createMarker();

	/**
	 * Returns a new object of class '<em>Counter</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Counter</em>'.
	 * @generated
	 */
	Counter createCounter();

	/**
	 * Returns a new object of class '<em>One</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>One</em>'.
	 * @generated
	 */
	One createOne();

	/**
	 * Returns a new object of class '<em>Two</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Two</em>'.
	 * @generated
	 */
	Two createTwo();

	/**
	 * Returns a new object of class '<em>Three</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Three</em>'.
	 * @generated
	 */
	Three createThree();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	EmoflonExampleCodePackage getEmoflonExampleCodePackage();

} //EmoflonExampleCodeFactory
