package mapper;

import java.util.HashMap;
import java.util.Map;

import parser.XmlNode;

public class XmlNodeFactory {
	public XmlNode generatePosattr(double x, double y) {
		Map<String, String> properties = new HashMap<String, String>();
		properties.put("x", Double.toString(x));
		properties.put("y", Double.toString(y));
		return new XmlNode("posattr", null, properties);
	}
	public XmlNode fillattr(String colour, String pattern, String filled) {
		Map<String, String> properties = new HashMap<String, String>();
		properties.put("colour", colour);
		properties.put("pattern", pattern);
		properties.put("filled", filled);
		return new XmlNode("filattr", null, properties);
	}
	public XmlNode generateLineattr(String colour, String pattern, String filled) {
		Map<String, String> properties = new HashMap<String, String>();
		properties.put("colour", colour);
		properties.put("pattern", pattern);
		properties.put("filled", filled);
		return new XmlNode("lineattr", null, properties);
	}
	public XmlNode generateTextattr(String colour, String bold) {
		Map<String, String> properties = new HashMap<String, String>();
		properties.put("colour", colour);
		properties.put("bold", bold);
		return new XmlNode("text", null, properties);
	}
	public XmlNode generatePosattr(String colour, String bold) {
		Map<String, String> properties = new HashMap<String, String>();
		properties.put("colour", colour);
		properties.put("bold", bold);
		return new XmlNode("text", null, properties);
	}
	public XmlNode generatePosattr(String colour, String bold) {
		Map<String, String> properties = new HashMap<String, String>();
		properties.put("colour", colour);
		properties.put("bold", bold);
		return new XmlNode("text", null, properties);
	}
	public XmlNode generatePosattr(String colour, String bold) {
		Map<String, String> properties = new HashMap<String, String>();
		properties.put("colour", colour);
		properties.put("bold", bold);
		return new XmlNode("text", null, properties);
	}
	public XmlNode generatePosattr(String colour, String bold) {
		Map<String, String> properties = new HashMap<String, String>();
		properties.put("colour", colour);
		properties.put("bold", bold);
		return new XmlNode("text", null, properties);
	}
	public XmlNode generatePosattr(String colour, String bold) {
		Map<String, String> properties = new HashMap<String, String>();
		properties.put("colour", colour);
		properties.put("bold", bold);
		return new XmlNode("text", null, properties);
	}
	
}
