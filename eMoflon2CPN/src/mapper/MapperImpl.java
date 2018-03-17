package mapper;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parser.XmlNode;

public class MapperImpl {
	private XmlNode emoflonTree;
	private XmlNode cpnTree;
	private Map<String, List<String>> propertyMap = new HashMap<String, List<String>>();
	
	
	public MapperImpl(XmlNode emoflonTree) {
		this.emoflonTree = cpnTree;
	}
	
	public XmlNode getMappedCpnTree() {
		if(cpnTree == null)
			cpnTree = startMapping();
		
		return cpnTree;
	}
	
	private XmlNode startMapping() {
		XmlNode cpn = new XmlNode("page");
		List<XmlNode> emoflonChildren = emoflonTree.getChildren();
		
		
		
		return cpn;
	}
	
	private XmlNode mapEmoflonEdgeToCpnPlace(XmlNode edge) {
		XmlNode place = new XmlNode("place");
		
		return place;
	}
	
	private XmlNode mapEmoflonNodeToCpnTransition(XmlNode node) {
		XmlNode trans = new XmlNode("trans");
		XmlNode posattr = new XmlNode("posattr", null, new HashMap<String, String>());
		
		return trans;
	}
	
	private XmlNode generateXmlNode(String identifier, String... content) {
		Map<String, String> properties = new HashMap<String, String>();
		propertyMap.get(identifier);
		//Arrays.stream(content).forEach(c -> properties.);
		return new XmlNode(identifier, null, properties);
	}
	
	private void initializePropertyMap() {
		try(InputStream input = new FileInputStream("src/resources/propertyMap.bin")) {
			
		}
	}
}
