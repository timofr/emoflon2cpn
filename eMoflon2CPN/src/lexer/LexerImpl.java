package lexer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import main.Lexer;

public class LexerImpl implements Lexer {

	private String text;
	private char currentChar;
	private int currentPosition = -1;

	public final static char EOF = (char) -1;

	private LinkedList<Token> tokens;

	private HashMap<CharMatcher, TokenGetter> tokenMap = new HashMap<CharMatcher, TokenGetter>();

	private Iterator<Token> iterator;

	private CharMatcher whitespaceMatcher = new CharMatcher(' ', '\n', '\t', '\r');
	private CharMatcher slashMatcher = new CharMatcher('/');
	private CharMatcher angleBracketMatcher = new CharMatcher('<','>');
	private CharMatcher equalMatcher = new CharMatcher('=');
	private CharMatcher identifierMatcher = new CharMatcher(new CharRange('a', 'z'), new CharRange('A', 'Z'), new CharRange(':', ':'));
	private CharMatcher quotationMatcher = new CharMatcher('"');

	public LexerImpl(String text) {
		this.text = text + EOF;
	}

	private char getNextChar() {
		currentChar = text.charAt(++currentPosition);
		return currentChar;
	}

	private char getCurrentCharAndGoOn() {
		char tempChar = text.charAt(currentPosition);
		currentChar = text.charAt(++currentPosition);
		return tempChar;
	}

	@Override
	public List<Token> getTokenList() throws LexerException  {
		try {
			if (tokens == null) {
				tokens = new LinkedList<Token>();
				startLexing();
			}
		} catch(LexerException e) {
			e.printStackTrace();
			throw new LexerException("Lexer couldn't lex your code");
		}
		return tokens;
	}

	private void startLexing() throws LexerException  {
		initializeTokenMap();
		getNextChar();
		boolean illegalCharFound = true;

		while (currentChar != EOF) {
			illegalCharFound = true;

			for (CharMatcher matcher : tokenMap.keySet()) {
				if (matcher.match(currentChar)) {
					Token tokenToAdd = tokenMap.get(matcher).getToken("");
					if (tokenToAdd != null)
						tokens.add(tokenToAdd);
					illegalCharFound = false;

					break;
				}
			}
			if (illegalCharFound)
				throw new LexerException(
						"The Lexer found the char: '" + currentChar + "', but he isn't able to handle it.");
		}
		tokens.add(new Token("" + EOF, TokenType.EOFTOKEN));
	}

	private void initializeTokenMap() {
		tokenMap.put(whitespaceMatcher, this::handleWhitespace);
		tokenMap.put(slashMatcher, this::handleSlash);
		tokenMap.put(angleBracketMatcher, this::handleAngleBracket);
		tokenMap.put(equalMatcher, this::handleEqual);
		tokenMap.put(identifierMatcher, this::handleIdentifier);
		tokenMap.put(quotationMatcher, this::handleString);
	}

	private Token handleWhitespace(String str) {
		getNextChar();
		return null;
	}
	
	private Token handleSlash(String str) {
		getNextChar();
		return new Token(str, TokenType.SLASH);
	}
	
	private Token handleAngleBracket(String str) {
		getNextChar();
		return new Token(str, TokenType.ANGLEBRACKETTOKEN);
	}
	
	private Token handleEqual(String str) {
		getNextChar();
		return new Token(str, TokenType.EQUAL);
	}
	
	private Token handleIdentifier(String str) {
		getNextChar();
		if(identifierMatcher.match(currentChar))
			return handleIdentifier(str + currentChar);
		
		return new Token(str + currentChar, TokenType.IDENTIFIER);
	}
	
	private Token handleString(String str) throws LexerException {
		getNextChar();
		if(currentChar == EOF) throw new LexerException("String was not closed.");
		
		if(!quotationMatcher.match(currentChar) || str.length() == 0)
			return handleString(str + currentChar);
		
		getNextChar();
		return new Token(str.substring(1), TokenType.STRING);
	}
	
	private interface TokenGetter {
		public Token getToken(String str) throws LexerException;
	}
}
