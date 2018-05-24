package translation.mapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import translation.Mapper;
import translation.TranslationException;
import translation.mapper.methodConstructor.EmoflonMethod;
import translation.mapper.methodConstructor.MethodConstructor;
import translation.mapper.methodConstructor.MethodConstructorException;
import translation.mapper.methodConstructor.MethodNameConstructorException;
import translation.parser.XmlNode;

public class MapperImpl implements Mapper {
	private boolean onlyCpnFile;
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
	private List<StopNode> stopNodes = new ArrayList<StopNode>();
	private XmlNode startNode;
	private int port;
	private Map<String, EmoflonMethod> methods = new HashMap<String, EmoflonMethod>();
	private MethodConstructor methodConstructor;
	private int disconnectCounter = 1;
	
	public MapperImpl(XmlNode emoflonTree, Class<?> chosenClass, String chosenMethod, boolean onlyCpnFile) {
		this(emoflonTree, 9000, chosenClass, chosenMethod, onlyCpnFile);
	}
	
	public MapperImpl(XmlNode emoflonTree, int port, Class<?> chosenClass, String chosenMethod, boolean onlyCpnFile) {
		this.emoflonTree = emoflonTree;
		this.port = port;
		this.onlyCpnFile = onlyCpnFile;
		methodConstructor = new MethodConstructor(chosenClass, chosenMethod);
	}
	
	public Map<String, EmoflonMethod> getMethods() {
		return methods;
	}
	
	public XmlNode getMappedCpnTree() {
		if(cpnTree == null)
			cpnTree = startMapping();
		
		return cpnTree;
	}
	
	private XmlNode startMapping() {
		if(!onlyCpnFile)
			methodConstructor.initialize();
		
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
					if(!onlyCpnFile) {
						EmoflonMethod method = methodConstructor.constructMethod(xmlNode);
						methods.put(method.getName(), method);
					}
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
		
		String startId = getProperty(startNode, "id");
		XmlNode startPlace = factory.place("Start", "1`true");
		XmlNode connectTrans = factory.trans(false,"connect", "action\nacceptConnection(\"Emoflon2Cpn\"," + port +  ")");
		cpnChildren.add(startPlace);
		cpnChildren.add(connectTrans);
		arcs.add(factory.arc("PtoT", 1, getProperty(connectTrans, "id"), getProperty(startPlace, "id"), connectTrans, startPlace, "true"));
		arcs.add(factory.arc("TtoP", 1, getProperty(connectTrans, "id"), startId, connectTrans, startNode, "true"));
		for(StopNode stopNode: stopNodes) {
			XmlNode place = stopNode.getNode();
			String arcGuard = stopNode.getArcGuard();
			String id = getProperty(place, "id");
			XmlNode endPlace = factory.place("End" + disconnectCounter, null);
			XmlNode disconnectTrans = factory.trans(false, "disconnect" + disconnectCounter, "action\ncloseConnection(\"Emoflon2Cpn\")");
			disconnectCounter++;
			cpnChildren.add(endPlace);
			cpnChildren.add(disconnectTrans);
			arcs.add(factory.arc("TtoP", 1, getProperty(disconnectTrans, "id"), getProperty(endPlace, "id"), disconnectTrans, endPlace, "true"));
			arcs.add(factory.arc("PtoT", 1, getProperty(disconnectTrans, "id"), id, disconnectTrans, place, arcGuard));
		}
		
		cpnChildren.addAll(places);
		cpnChildren.addAll(trans);
		cpnChildren.addAll(arcs);
		cpnChildren.add(factory.constraints());
		return cpn;
	}
	
	private XmlNode mapEmoflonEdgeToCpnPlace(XmlNode edge) {
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
			if(targetId != null) {
				arcs.add(factory.arc("PtoT", 1, targetId, existingPlaceId, targetTrans, existingPlace, arcGuard));
			}
			else {
				stopNodes.add(new StopNode(existingPlace, arcGuard));	
			}
			XmlNode child = existingPlace.getChild("text");
			String currentNamePart = null;
			child.setContent(child.getContent() + "&amp;" +
					((currentNamePart = targetNode.getProperty("name")) == null ? "StopNode" : currentNamePart));
			return null;
		}
		
		if(existingTargetArc != null) {
			String existingPlaceId = getProperty(existingTargetArc.getChild("placeend"), "idref");
			XmlNode existingPlace = places.stream().filter(p ->p.getProperty("id").equals(existingPlaceId)).findFirst().get();
			arcs.add(factory.arc("TtoP", 1, targetId, existingPlaceId, targetTrans, existingPlace, "receive()"));
			XmlNode child = existingPlace.getChild("text");
			String currentNamePart = null;
			child.setContent(((currentNamePart = sourceNode.getProperty("name")) == null ? "StopNode" : currentNamePart)
					+ "&amp;" +  child.getContent());
			return null;
		}
		StringBuilder nameBuilder = new StringBuilder();
		String currentNamePart = null;
		nameBuilder.append((currentNamePart = sourceNode.getProperty("name")) == null ? "StartNode" : currentNamePart);
		nameBuilder.append('-');
		nameBuilder.append((currentNamePart = targetNode.getProperty("name")) == null ? "StopNode" : currentNamePart);
		XmlNode place = factory.place(nameBuilder.toString(), null);
		String placeId = getProperty(place, "id");
		if(sourceId != null) {
			arcs.add(factory.arc("TtoP", 1, sourceId, placeId, sourceTrans, place, "receive()"));
		} else {
			startNode = place;
		}
		if(targetId != null) {
			arcs.add(factory.arc("PtoT", 1, targetId, placeId, targetTrans, place, arcGuard));
		} else {
			stopNodes.add(new StopNode(place, arcGuard));
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
	
	private class StopNode {
		XmlNode node;
		String arcGuard;
		
		public StopNode(XmlNode node, String arcGuard) {
			this.node = node;
			this.arcGuard = arcGuard;
		}
		public XmlNode getNode() {
			return node;
		}
		public String getArcGuard() {
			return arcGuard;
		}
	}
}
