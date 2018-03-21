package mapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.Mapper;
import parser.XmlNode;

public class MapperImpl implements Mapper {
	private XmlNodeFactory factory = XmlNodeFactory.getFactory();;
	private XmlNode emoflonTree;
	private XmlNode cpnTree;
	private Map<String, List<String>> propertyMap = new HashMap<String, List<String>>();
	private List<XmlNode> nodes = new ArrayList<XmlNode>();
	private List<XmlNode> edges = new ArrayList<XmlNode>();
	private List<XmlNode> cpnChildren = new ArrayList<XmlNode>();
	private Map<Integer, XmlNode> nodeMapping = new HashMap<Integer, XmlNode>();
	private Map<String, String> cpnProperties = new HashMap<String, String>();
	private List<XmlNode> trans = new ArrayList<XmlNode>();
	private List<XmlNode> arcs = new ArrayList<XmlNode>();
	
	public MapperImpl(XmlNode emoflonTree) {
		this.emoflonTree = emoflonTree;
	}
	
	public XmlNode getMappedCpnTree() throws MapperException {
		if(cpnTree == null)
			cpnTree = startMapping();
		
		return cpnTree;
	}
	
	private XmlNode startMapping() throws MapperException {
		cpnChildren.add(factory.pageattr(emoflonTree.getProperty("name")));
		int nodeCounter = 0;
		cpnProperties.put("id", factory.getNextId());
		XmlNode cpn = new XmlNode("page", null, cpnChildren, cpnProperties);
		List<XmlNode> emoflonChildren = emoflonTree.getChildren().stream().filter(n -> n.getIdentifier().equals("activity")).findFirst().get().getChildren();
		for(XmlNode xmlNode : emoflonChildren) {
			if(xmlNode.getIdentifier().equals("ownedActivityNode")) {
				nodes.add(xmlNode);
				String xsi_type = getProperty(xmlNode, "xsi:type");
				if(xsi_type.equals("activities:StartNode")) {
					
				}	
				else if(xsi_type.equals("activities:StoryNode")) {
					XmlNode child = mapEmoflonNodeToCpnTransition(getProperty(xmlNode, "name"));
					trans.add(child);
					nodeMapping.put(nodeCounter, child);
				}	
				else if(xsi_type.equals("activities:StatementNode")) {
					XmlNode child = mapEmoflonNodeToCpnTransition(getProperty(xmlNode, "name"));
					trans.add(child);
					nodeMapping.put(nodeCounter, child);
				}
				else if(xsi_type.equals("activities:StopNode")) {
					
				}
				nodeCounter++;	
			}
			else if(xmlNode.getIdentifier().equals("ownedActivityEdge"))
				edges.add(xmlNode);
			else
				throw new MapperException("Mapper can't handle the node " + xmlNode.getIdentifier() + ": " + xmlNode.getProperties().toString());
		}
		
		for(XmlNode xmlNode : edges) {
			cpnChildren.add(mapEmoflonEdgeToCpnPlace(xmlNode));
		}
		cpnChildren.addAll(trans);
		cpnChildren.addAll(arcs);
		cpnChildren.add(factory.constraints());
		return cpn;
	}
	
	private XmlNode mapEmoflonEdgeToCpnPlace(XmlNode edge) throws MapperException {
		int source = EmoflonAddressInterpreter.addressToNumber(getProperty(edge, "source"));
		int target = EmoflonAddressInterpreter.addressToNumber(getProperty(edge, "target"));
		XmlNode sourceNode = nodes.get(source);
		XmlNode targetNode = nodes.get(target);
		XmlNode sourceTrans = nodeMapping.get(source);
		XmlNode targetTrans = nodeMapping.get(target);
		String sourceId = getProperty(sourceTrans, "id");
		String targetId = getProperty(targetTrans, "id");
		StringBuilder nameBuilder = new StringBuilder();
		String currentNamePart = null;
		nameBuilder.append((currentNamePart = sourceNode.getProperty("name")) == null ? "StartNode" : currentNamePart);
		nameBuilder.append('2');
		nameBuilder.append((currentNamePart = targetNode.getProperty("name")) == null ? "StopNode" : currentNamePart);
		XmlNode place = factory.place(nameBuilder.toString());
		String placeId = getProperty(place, "id");
		if(sourceId != null)
			arcs.add(factory.arc("TtoP", 1, sourceId, placeId, sourceTrans, place));
		if(targetId != null)
			arcs.add(factory.arc("PtoT", 1, targetId, placeId, place, targetTrans));
		return place;
		
		/*
			int source = EmoflonAddressInterpreter.addressToNumber(getProperty(edge, "source"));
		int target = EmoflonAddressInterpreter.addressToNumber(getProperty(edge, "target"));
		XmlNode sourceNode = nodes.get(source);
		XmlNode targetNode = nodes.get(target);
		
		String sourceId = getProperty(sourceNode, "id");
		String targetId = getProperty(targetNode, "id");
		StringBuilder nameBuilder = new StringBuilder();
		String currentNamePart = null;
		nameBuilder.append((currentNamePart = sourceNode.getProperty("name")) == null ? "StartNode" : currentNamePart);
		nameBuilder.append('2');
		nameBuilder.append((currentNamePart = targetNode.getProperty("name")) == null ? "StopNode" : currentNamePart);
		XmlNode place = factory.place(nameBuilder.toString());
		String placeId = getProperty(place, "id");
		if(sourceId != null)
			arcs.add(factory.arc("TtoP", 1, sourceId, placeId, sourcePlace, place));
		if(targetId != null)
			arcs.add(factory.arc("PtoT", 1, placeId, targetId, place, targetPlace));
		return place;
		 */
	}
	
	private XmlNode mapEmoflonNodeToCpnTransition(String name) throws MapperException {
		return factory.trans(false, name);
	}
	
	private String getProperty(XmlNode node, String property) throws MapperException {
		if(node == null) return null;
		String value = node.getProperty(property);
		if(value == null) throw new MapperException("Mapper found no property " + property + " in node " + node.getIdentifier());
		return value;
	}
}
