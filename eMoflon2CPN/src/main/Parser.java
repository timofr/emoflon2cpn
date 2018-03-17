package main;

import parser.ParserException;
import parser.XmlNode;


public interface Parser {

	public XmlNode getXmlTree() throws ParserException;
	
}
