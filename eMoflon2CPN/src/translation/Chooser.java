package translation;

import java.io.File;

import translation.chooser.ChooserException;
import translation.parser.XmlNode;

public interface Chooser {
	public String getClasses() throws ChooserException;
	
	public String getMethods() throws ChooserException;

	public XmlNode chooseClass() throws ChooserException;

	public XmlNode chooseMethod() throws ChooserException;
	
	public String getXmiFiles(File[] xmis);
	
	public File chooseXmiFile();
	
	public XmlNode getMethodNode();
	
	public String getMethodName();
	
	public String getClassName();
	
	public Class<?> getChosenClass();
}
