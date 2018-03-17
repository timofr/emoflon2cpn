package parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * A node of a xml tree
 * 
 * @author Timo Freitag
 *
 */
public class XmlNode extends Object {
	private String identifier;
	private String content;
	private List<XmlNode> children;
	private Map<String, String> properties;
	
	/**
	 * Constructs a xml node with an identifier, no children and no properties.
	 * 
	 * @param identifier identifier of the node
	 */
	public XmlNode(String identifier) {
		this(identifier, (List<XmlNode>) null, null);
	}
	
	/**
	 * Constructs a xml node with an identifier, content and properties.
	 * Thus it got no children.
	 * 
	 * @param identifier identifier of the node
	 * @param content content of the node
	 * @param properties properties of the node
	 */
	public XmlNode(String identifier, String content, Map<String, String> properties) {
		this(identifier, (List<XmlNode>) null, properties);
		this.content = content;
	}

	/**
	 * Constructs a xml node with an identifier, children and properties.
	 * Thus it got no content.
	 * 
	 * @param identifier identifier of the node
	 * @param children children of the node
	 * @param properties properties of the node
	 */
	public XmlNode(String identifier, List<XmlNode> children, Map<String, String> properties) {
		this.identifier = identifier;
		this.children = children != null ? children : new ArrayList<XmlNode>();
		this.properties = properties != null ? properties : new HashMap<String, String>();
	}
	
	/**
	 * Adds a child to the node.
	 * If the child != null, it return true, otherwise false.
	 * 
	 * @param child child to add
	 * @return success of the adding
	 */
	public boolean addChild(XmlNode child) {
		return  child == null ? false : children.add(child);
	}

	/**
	 * Adds a property to the node.
	 * 
	 * @param identifier identifier of the property
	 * @param content the content of the property
	 * @return the success of the adding
	 */
	public boolean addProperties(String identifier, String content) {
		return properties.put(identifier, content) == null;
	}
	
	/**
	 * Returns the identifier of the node.
	 * 
	 * @return identifier of the node
	 */
	public String getIdentifier() {
		return this.identifier;
	}
	
	/**
	 * Returns the content of the node.
	 * 
	 * @return content of the node
	 */
	public String getContent() {
		return this.content;
	}
	
	/**
	 * Return the children of the node as a list.
	 * 
	 * @return List of children
	 */
	public List<XmlNode> getChildren() {
		return children;
	}

	/**
	 * Return the properties of the node as a list.
	 * 
	 * @return list of properties
	 */
	public Map<String, String> getProperties() {
		return properties;
	}

	@Override
	public String toString() {
		return "XmlNode [identifier=" + identifier + ", properties=" + properties + ", children=" + children + "]";
	}

	/**
	 * Sets the content of the node
	 * 
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
}
