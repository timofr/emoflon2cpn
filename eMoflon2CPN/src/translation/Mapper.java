package translation;

import translation.mapper.MapperException;
import translation.parser.XmlNode;

public interface Mapper {
	public XmlNode getMappedCpnTree() throws TranslationException;
}
