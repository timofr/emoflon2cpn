package translation.chooser;

import java.util.Optional;

import translation.Chooser;
import translation.parser.XmlNode;

public class ChooserImpl implements Chooser {
	private XmlNode node;
	
	public ChooserImpl(XmlNode node) {
		this.node = node;
	}
	
	public String getClasses() throws ChooserException {
		if(!node.getIdentifier().equals("ecore:EPackage")) throw new ChooserException("Current node isnt a ecore:Package. It is a " + node.getIdentifier());
		StringBuilder builder = new StringBuilder();
		node.getChildren().stream().map(n -> n.getProperty("name")).forEach(str -> builder.append(str).append(' '));
		return builder.toString();
	}
	
	public String getMethods() throws ChooserException {
		if(!node.getIdentifier().equals("eClassifiers")) throw new ChooserException("Current node isnt a eClassifiers. It is a " + node.getIdentifier());
		StringBuilder builder = new StringBuilder();
		node.getChildren().stream().filter(n -> n.getIdentifier().equals("eOperations")).map(n -> n.getProperty("name")).forEach(str -> builder.append(str).append(' '));
		return builder.toString();
	}
	
	public XmlNode chooseClass(String name) throws ChooserException {
		if(!node.getIdentifier().equals("ecore:EPackage")) throw new ChooserException("Current node isnt a ecore:Package. It is a " + node.getIdentifier());
		return node = node.getChildren().stream().filter(n -> n.getProperty("name").equals(name)).findFirst().get();
	}
	
	public XmlNode chooseMethod(String name) throws ChooserException {
		if(!node.getIdentifier().equals("eClassifiers")) throw new ChooserException("Current node isnt a eClassifiers. It is a " + node.getIdentifier());
		return node = node.getChildren().stream().filter(n -> n.getProperty("name").equals(name)).findFirst().get();
	}
}
