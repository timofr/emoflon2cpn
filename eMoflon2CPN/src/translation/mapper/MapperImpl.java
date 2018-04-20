package translation.mapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import translation.Mapper;
import translation.TranslationException;
import translation.parser.XmlNode;

public class MapperImpl implements Mapper {
	private XmlNodeFactory factory = XmlNodeFactory.getFactory();;
	private XmlNode emoflonTree;
	private XmlNode cpnTree;
	private List<XmlNode> nodes = new ArrayList<XmlNode>();
	private List<XmlNode> edges = new ArrayList<XmlNode>();
	private List<XmlNode> cpnChildren = new ArrayList<XmlNode>();
	private Map<XmlNode, XmlNode> nodeMapping = new HashMap<XmlNode, XmlNode>();
	private Map<String, String> cpnProperties = new HashMap<String, String>();
	private List<XmlNode> places = new ArrayList<XmlNode>();
	private List<XmlNode> trans = new ArrayList<XmlNode>();
	private List<XmlNode> arcs = new ArrayList<XmlNode>();
	private List<XmlNode> startstopNodes = new ArrayList<XmlNode>();
	int port;
	
	public MapperImpl(XmlNode emoflonTree) {
		this(emoflonTree,9000);
	}
	
	public MapperImpl(XmlNode emoflonTree, int port) {
		this.emoflonTree = emoflonTree;
		this.port = port;
	}
	
	public XmlNode getMappedCpnTree() throws TranslationException {
		try {
			if(cpnTree == null)
				cpnTree = startMapping();
		}
		catch (MapperException e) {
			e.printStackTrace();
			throw new TranslationException("Mapper could not handle xml tree");
		}
		return cpnTree;
	}
	
	private XmlNode startMapping() throws MapperException {
		cpnChildren.add(factory.pageattr(emoflonTree.getProperty("name")));
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
					nodeMapping.put(xmlNode, child);
				}	
				else if(xsi_type.equals("activities:StatementNode")) {
					XmlNode child = mapEmoflonNodeToCpnTransition(getProperty(xmlNode, "name"));
					trans.add(child);
					nodeMapping.put(xmlNode, child);
				}
				else if(xsi_type.equals("activities:StopNode")) {
					
				}
			}
			else if(xmlNode.getIdentifier().equals("ownedActivityEdge"))
				edges.add(xmlNode);
			else
				throw new MapperException("Mapper can't handle the node " + xmlNode.getIdentifier() + ": " + xmlNode.getProperties().toString());
		}
		
		for(XmlNode xmlNode : edges) {
			XmlNode place = mapEmoflonEdgeToCpnPlace(xmlNode);
			if(place != null)
				places.add(place);
		}
		
		for(XmlNode place: startstopNodes) {
			String id = getProperty(place, "id");
			if(place == startstopNodes.get(0)) {
				XmlNode startPlace = factory.place("Start", "1`true");
				XmlNode connectTrans = factory.trans(false,"connect", "action\nacceptConnection(\"Emoflon2Cpn\"," + port +  ")");
				cpnChildren.add(startPlace);
				cpnChildren.add(connectTrans);
				arcs.add(factory.arc("PtoT", 1, getProperty(connectTrans, "id"), getProperty(startPlace, "id"), connectTrans, startPlace, "true"));
				arcs.add(factory.arc("TtoP", 1, getProperty(connectTrans, "id"), id, connectTrans, place, "true"));
			}
			else {
				XmlNode endPlace = factory.place("End", null);
				XmlNode disconnectTrans = factory.trans(false, "disconnect", "action\ncloseConnection(\"Emoflon2Cpn\")");
				cpnChildren.add(endPlace);
				cpnChildren.add(disconnectTrans);
				arcs.add(factory.arc("TtoP", 1, getProperty(disconnectTrans, "id"), getProperty(endPlace, "id"), disconnectTrans, endPlace, "true"));
				arcs.add(factory.arc("PtoT", 1, getProperty(disconnectTrans, "id"), id, disconnectTrans, place, "true"));
			}
		}
		
		cpnChildren.addAll(places);
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
		XmlNode sourceTrans = nodeMapping.get(sourceNode);
		XmlNode targetTrans = nodeMapping.get(targetNode);
		String sourceId = getProperty(sourceTrans, "id");
		String targetId = getProperty(targetTrans, "id");
		XmlNode existingSourceArc = getArcWithSameTrans(sourceId, false);
		XmlNode existingTargetArc = getArcWithSameTrans(targetId, true);
		
		String edgeGuard = edge.getProperty("guard");
		String arcGuard = null;
		if(edgeGuard != null) {
			if(edgeGuard.equals("SUCCESS")) arcGuard= Boolean.toString(true);
			else if(edgeGuard.equals("FAILURE")) arcGuard  = Boolean.toString(false);
			else throw new MapperException("Unknown edge guard between " + source + " and " + target);
		}
		else arcGuard= Boolean.toString(true);
		
		if(existingSourceArc != null) {
			String existingPlaceId = getProperty(existingSourceArc.getChild("placeend"), "idref");
			XmlNode existingPlace = places.stream().filter(p ->p.getProperty("id").equals(existingPlaceId)).findFirst().get();
			arcs.add(factory.arc("PtoT", 1, targetId, existingPlaceId, targetTrans, existingPlace, arcGuard));
			XmlNode child = existingPlace.getChild("text");
			child.setContent(child.getContent() + "&amp;" +  targetNode.getProperty("name"));
			return null;
		}
		
		if(existingTargetArc != null) {
			String existingPlaceId = getProperty(existingTargetArc.getChild("placeend"), "idref");
			XmlNode existingPlace = places.stream().filter(p ->p.getProperty("id").equals(existingPlaceId)).findFirst().get();
			arcs.add(factory.arc("TtoP", 1, targetId, existingPlaceId, targetTrans, existingPlace, "receive()"));
			XmlNode child = existingPlace.getChild("text");
			child.setContent(sourceNode.getProperty("name") + "&amp;" +  child.getContent());
			return null;
		}
		StringBuilder nameBuilder = new StringBuilder();
		String currentNamePart = null;
		nameBuilder.append((currentNamePart = sourceNode.getProperty("name")) == null ? "StartNode" : currentNamePart);
		nameBuilder.append('2');
		nameBuilder.append((currentNamePart = targetNode.getProperty("name")) == null ? "StopNode" : currentNamePart);
		XmlNode place = factory.place(nameBuilder.toString(), null);
		String placeId = getProperty(place, "id");
		if(sourceId != null) {
			arcs.add(factory.arc("TtoP", 1, sourceId, placeId, sourceTrans, place, "receive()"));
		} else {
			startstopNodes.add(0, place);
		}
		if(targetId != null) {
			arcs.add(factory.arc("PtoT", 1, targetId, placeId, targetTrans, place, arcGuard));
		} else {
			startstopNodes.add(place);
		}
		return place;
	}
	
	private XmlNode mapEmoflonNodeToCpnTransition(String name) throws MapperException {
		return factory.trans(false, name, "action\nsend(\"" + name + "\")");
	}
	
	private String getProperty(XmlNode node, String property) throws MapperException {
		if(node == null) return null;
		String value = node.getProperty(property);
		if(value == null) throw new MapperException("Mapper found no property " + property + " in node " + node.getIdentifier());
		return value;
	}
	
	private XmlNode getArcWithSameTrans(String transId, boolean TtoP) {
		try {
			return arcs.stream().filter(a -> checkIdRef(a, transId, TtoP)).findFirst().get();
		}
		catch(NoSuchElementException e) {
			return null;
		}
	}
	
	private boolean checkIdRef(XmlNode arc, String transId, boolean TtoP) {
		XmlNode child = arc.getChild("transend");
		if(TtoP) 
			return arc.getProperty("orientation").equals("TtoP")? child.getProperty("idref").equals(transId) : false;
		else
			return arc.getProperty("orientation").equals("TtoP")? child.getProperty("idref").equals(transId) : false;
	}
}