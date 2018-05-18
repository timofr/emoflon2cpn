package translation.chooser;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Optional;

import translation.Chooser;
import translation.parser.XmlNode;

public class ChooserImpl implements Chooser {
	private XmlNode node;
	private String chosenClassName;
	private String chosenMethodName;
	private String chosenXmiFileName;
	private XmlNode methodNode;
	private Class<?> chosenClass;
	private File[] xmlFiles;
	private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	
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
	
	public XmlNode chooseClass() throws ChooserException {
		try {
			chosenClassName = reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(!node.getIdentifier().equals("ecore:EPackage")) throw new ChooserException("Current node isnt a ecore:Package. It is a " + node.getIdentifier());
		return node = node.getChildren().stream().filter(n -> n.getProperty("name").equals(chosenClassName)).findFirst().get();
	}
	
	public XmlNode chooseMethod() throws ChooserException {
		try {
			chosenMethodName = reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(!node.getIdentifier().equals("eClassifiers")) throw new ChooserException("Current node isnt a eClassifiers. It is a " + node.getIdentifier());
		return node = node.getChildren().stream().filter(n -> n.getProperty("name").equals(chosenMethodName)).findFirst().get();
	}
	
	public String getXmiFiles(File[] xmis) {
		this.xmlFiles = xmis;
		StringBuilder builder = new StringBuilder();
		Arrays.stream(xmis).map(f -> f.getName()).forEach(str -> builder.append(str).append(' '));
		return builder.toString();
	}
	
	public File chooseXmiFile() {
		try {
			chosenXmiFileName = reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Arrays.stream(xmlFiles).filter(f -> f.getName().equals(chosenXmiFileName)).findFirst().get();
	}
	
	public XmlNode getMethodNode() {
		return this.methodNode;
	}
	
	public String getMethodName() {
		return this.chosenMethodName;
	}
	
	public String getClassName() {
		return this.chosenClassName;
	}
	
	public Class<?> getChosenClass() {
		return this.chosenClass;
	}
}
