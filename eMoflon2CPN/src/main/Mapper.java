package main;

import mapper.MapperException;
import parser.XmlNode;

public interface Mapper {
	public XmlNode getMappedCpnTree() throws MapperException;
}
