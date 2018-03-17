package parser;

import java.util.Arrays;
import java.util.List;

import lexer.LexerImpl;
import lexer.Token;
import lexer.TokenType;
import main.Parser;

public class ParserImpl implements Parser {

	private List<Token> listOfTokens;
	private int tokenListIndex = -1;
	private Token currentToken;
	
	private XmlNode tree;

	public ParserImpl(List<Token> listOfTokens) {
		this.listOfTokens = listOfTokens;
	}
	
	@Override
	public XmlNode getXmlTree() throws ParserException {

		try {
			if(tree == null) {
				tree = startParsing();
			}
		} catch(ParserException e) {
			e.printStackTrace();
			throw new ParserException("The Parser couldn't parse your code.\n" + e.getMessage()); //TODO refactor this shit
		}
		return tree;

	}
	
	private XmlNode startParsing() throws ParserException {
		return handleNode();
	}
	
	private XmlNode handleNode() throws ParserException {
		checkNextTokenType(TokenType.OPENANGLEBRACKETTOKEN);
		
		if(isNextTypeEqual(TokenType.SLASHTOKEN)) return null;
		
		checkTokenType(TokenType.IDENTIFIERTOKEN);
		XmlNode node = new XmlNode(currentToken.getContent());
		
		while(!isNextTypeEqual(TokenType.CLOSEANGLEBRACKETTOKEN)) {
			if(isTypeEqual(TokenType.SLASHTOKEN)) {
				checkNextTokenType(TokenType.CLOSEANGLEBRACKETTOKEN);
				return node;
			}
			
			checkTokenType(TokenType.IDENTIFIERTOKEN);
			String identifier = currentToken.getContent();
			checkNextTokenType(TokenType.EQUALTOKEN);
			checkNextTokenType(TokenType.STRINGTOKEN);
			if(!node.addProperties(identifier, currentToken.getContent()))
				throw new ParserException("The property " + identifier +  " was used twice in the node " + node.getIdentifier());
		}
		
		if(getLookAheadToken(1).getType() == TokenType.CONTENTTOKEN) {
			getNextToken();
			node.setContent(currentToken.getContent());
			checkNextTokenType(TokenType.OPENANGLEBRACKETTOKEN);
			checkNextTokenType(TokenType.SLASHTOKEN);
		} else {
			XmlNode child = null;
			do {
				child = handleNode();
			} while(node.addChild(child));
		}
		
		checkNextToken(TokenType.IDENTIFIERTOKEN, node.getIdentifier());
		checkNextTokenType(TokenType.CLOSEANGLEBRACKETTOKEN);
		
		return node;
	}
	
	
	
	/************************************Utility Functions*******************************************************/
	
	private boolean isTokenEqual(Token token) {
		return currentToken.equals(token);
	}

	private boolean isNextTokenEqual(Token token) throws ParserException {
		getNextToken();
		return isTokenEqual(token);
	}
	
	private boolean isTokenEqual(TokenType type, String content) {
		return currentToken.getType() == type && currentToken.getContent().equals(content);
	}
	
	private boolean isNextTokenEqual(TokenType type, String content) throws ParserException {
		getNextToken();
		return isTokenEqual(type, content);
	}
	
	private boolean isTypeEqual(TokenType type) {
		return currentToken.getType() == type;
	}
	
	private boolean isNextTypeEqual(TokenType type) throws ParserException {
		getNextToken();
		return isTypeEqual(type);
	}

	private boolean isContentEqual(String content) {
		return currentToken.getContent().equals(content);
	}

	private boolean isNextContentEqual(String content) throws ParserException {
		getNextToken();
		return isContentEqual(content);
	}
	
	private void checkToken(Token token) throws ParserException {
		if (!isTokenEqual(token))
			throwException(token.getContent());
	}

	private void checkNextToken(Token token) throws ParserException {
		getNextToken();
		checkToken(token);
	}
	
	private void checkToken(TokenType type, String content) throws ParserException {
		if (!isTokenEqual(type, content))
			throwException(content);
	}

	private void checkNextToken(TokenType type, String content) throws ParserException {
		getNextToken();
		checkToken(type, content);
	}
	
	private void checkTokenType(TokenType type) throws ParserException {
		if (!isTypeEqual(type))
			throwException(type.toString());
	}
	
	private void checkNextTokenType(TokenType typeToCheckWith) throws ParserException {
		getNextToken();
		checkTokenType(typeToCheckWith);
	}
	
	private void checkTokenContent(String content) throws ParserException {
		if(!isContentEqual(content))
			throwException(content);
	}

	private void checkNextTokenContent(String contentToCheckWith) throws ParserException {
		getNextToken();
		checkTokenContent(contentToCheckWith);
	}
	
	private void throwException(String expectedString) throws ParserException {
		throw new ParserException(
				"The Parser found " + currentToken.getContent() + " but expected " + expectedString + ".");
	}
	
	private Token getNextToken() throws ParserException {
		currentToken = listOfTokens.get(++tokenListIndex);
		if(!isTypeEqual(TokenType.EOFTOKEN))
			return currentToken;
		throw new ParserException("Parser found nothing more. The programm is not complete.");
	}
	
	private Token getCurrentTokenAndGoOn() throws ParserException {
		Token tempToken = currentToken;
		getNextToken();
		return tempToken;
	}
	
	private Token getLookAheadToken(int n) throws ParserException {
		if (tokenListIndex + n < listOfTokens.size() && 0 <= tokenListIndex + n) 
			return listOfTokens.get(tokenListIndex + n);
		
		throw new ParserException("Parser found nothing more. The programm is not complete.");
	}
}
