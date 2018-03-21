package generator;

import main.Generator;
import parser.XmlNode;

public class GeneratorImpl implements Generator {
	private String code;
	private XmlNode tree;
	
	public GeneratorImpl(XmlNode tree) {
		this.tree = tree;
	}
	
	public String generateCode()  {
		if (code == null)
			code = startGenerate();
		
//		try {
//			
//		} catch(GeneratorException e) {
//			e.printStackTrace();
//			throw new GeneratorException("Lexer couldn't lex your code"); TODO refactor this shit
//		}
		return code;
	}
	
	private String startGenerate() {
		StringBuilder contentBuilder = new StringBuilder();
		StringBuilder tabBuilder = new StringBuilder();
		xmlNodeIntoStringBuilder(tree, contentBuilder, tabBuilder, 0);
		return contentBuilder.toString();
	}
	
	private void xmlNodeIntoStringBuilder(XmlNode node, StringBuilder contentBuilder, StringBuilder tabBuilder, int tabCount) {
		String tabs = tabBuilder.toString();
		tabs = tabs.length() <= tabCount ? tabs : tabs.substring(0, tabCount);
		contentBuilder.append(tabs);
		contentBuilder.append('<');
		contentBuilder.append(node.getIdentifier());
		node.getProperties().forEach((identifer, content) 
				-> contentBuilder.append(' ').append(identifer)
				.append("=\"").append(content).append('"'));
	
		if(node.getChildren().isEmpty() && node.getContent() == null) {
			contentBuilder.append("/>");
		} else {
			contentBuilder.append('>');
			if(node.getContent() != null) {
				contentBuilder.append(node.getContent());
			}
			if (tabs.length() <= tabCount)
				tabBuilder.append("  ");
			node.getChildren().forEach(
					child -> xmlNodeIntoStringBuilder(child, contentBuilder.append("\n"), tabBuilder, tabCount + 2));

			if(node.getChildren().isEmpty()) {
				contentBuilder.append('<').append('/').append(node.getIdentifier()).append(">");
			} else {
				contentBuilder.append('\n');
				contentBuilder.append(tabs);
				contentBuilder.append('<').append('/').append(node.getIdentifier()).append(">");
			}
			
		}
	}
}
