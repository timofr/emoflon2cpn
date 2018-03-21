package main;

import chooser.ChooserException;
import parser.XmlNode;

public interface Chooser {
	public String getClasses() throws ChooserException;
	
	public String getMethods() throws ChooserException;

	public XmlNode chooseClass(String name) throws ChooserException;

	public XmlNode chooseMethod(String name) throws ChooserException;
}
