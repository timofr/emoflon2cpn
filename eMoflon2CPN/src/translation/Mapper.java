package translation;

import java.util.Map;

import translation.mapper.MapperException;
import translation.mapper.methodConstructor.EmoflonMethod;
import translation.parser.XmlNode;

public interface Mapper {
	public XmlNode getMappedCpnTree() throws TranslationException, ClassNotFoundException;
	public Map<String, EmoflonMethod> getMethods();
}
