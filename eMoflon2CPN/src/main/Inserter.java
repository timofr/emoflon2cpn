package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import chooser.ChooserImpl;
import generator.GeneratorImpl;
import lexer.LexerException;
import lexer.LexerImpl;
import mapper.MapperImpl;
import mapper.XmlNodeFactory;
import parser.ParserException;
import parser.ParserImpl;
import parser.XmlNode;

public class Inserter {
	XmlNode node;
	XmlNode tree;
	public Inserter(XmlNode node) {
		this.node = node;
	}
	
	public XmlNode getTree() throws ParserException, LexerException, IOException {
		if(tree == null)
			tree = insert();
		return tree;
	}
	
	private XmlNode insert() throws ParserException, LexerException, IOException {
		String path = "src/resources/emptycpn.txt";
		String input;
//		try(Scanner scanner = new Scanner(new FileInputStream(path)).useDelimiter("\\A")) {     
//			input = scanner.hasNext() ? scanner.next() : "";
//		}
		input = IOHandler.read(new File(path));
		
		Lexer lexer = new LexerImpl(input);
		Parser parser = new ParserImpl(lexer.getTokenList());
		XmlNode tree = parser.getXmlTree();
		XmlNode cpnet = tree.getChildren().stream().filter(n -> filterChild(n, "cpnet")).findFirst().get();
		cpnet.getChildren().add(1, node);
		Map<String, String> instanceProperties = new HashMap<String, String>();
		instanceProperties.put("id", XmlNodeFactory.getFactory().getNextId());
		instanceProperties.put("page", node.getProperty("id"));
		List<XmlNode> instances = new ArrayList<XmlNode>();
		instances.add(new XmlNode("instance", null, null, instanceProperties));
		cpnet.getChildren().add(2, new XmlNode("instances", null, instances, null));
		return tree;
	}
	
	private boolean filterChild(XmlNode node, String identifier) {
		return node.getIdentifier().equals(identifier);
	}
}
