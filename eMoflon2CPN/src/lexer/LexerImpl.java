package lexer;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import main.Lexer;

public class LexerImpl implements Lexer {

	private String text;
	private char currentChar;
	private int currentPosition = -1;
	private boolean lastBracketSeen = false; //false is closed, true is open
	
	public final static char EOF = (char) -1;

	private LinkedList<Token> tokens;

	private LinkedHashMap<Matcher, TokenGetter> tokenMap = new LinkedHashMap<Matcher, TokenGetter>();

	private CharMatcher whitespaceMatcher = new CharMatcher(' ', '\n', '\t', '\r');
	private CharMatcher slashMatcher = new CharMatcher('/');
	private CharMatcher openAngleBracketMatcher = new CharMatcher('<');
	private CharMatcher closeAngleBracketMatcher = new CharMatcher('>');
	private CharMatcher equalMatcher = new CharMatcher('=');
	private CharMatcher identifierMatcher = new CharMatcher(new CharRange('a', 'z'), new CharRange('A', 'Z'), new CharRange('0', '9'), new CharRange(':', ':'));
	private CharMatcher quotationMatcher = new CharMatcher('"');

	public LexerImpl(String text) {
		this.text = text + EOF;
	}

	private char getNextChar() {
		currentChar = text.charAt(++currentPosition);
		return currentChar;
	}
	
	private char getLookaheadChar(int n) throws LexerException {
		if(currentPosition + n < text.length() && 0 <= currentPosition + n)
			currentChar = text.charAt(currentPosition + n);
		else 
			throw new LexerException("Illegal lookahead: " + currentChar + "(" + currentPosition + ") + " + n);
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
			throw new LexerException("Lexer couldn't lex your code"); //TODO refactor this shit
		}
		return tokens;
	}

	private void startLexing() throws LexerException  {
		initializeTokenMap();
		getNextChar();
		boolean illegalCharFound = true;

		while (currentChar != EOF) {
			illegalCharFound = true;

			for (Matcher matcher : tokenMap.keySet()) {	
				if (matcher.match(currentChar)) {
					Token tokenToAdd = tokenMap.get(matcher).getToken(null);
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
		tokens.add(new Token(TokenType.EOFTOKEN, "" + EOF));
	}

	private void initializeTokenMap() {
		tokenMap.put(this::contentMatcher, this::handleContent);
		tokenMap.put(whitespaceMatcher, this::handleWhitespace);
		tokenMap.put(slashMatcher, this::handleSlash);
		tokenMap.put(openAngleBracketMatcher, this::handleOpenAngleBracket);
		tokenMap.put(closeAngleBracketMatcher, this::handleCloseAngleBracket);
		tokenMap.put(equalMatcher, this::handleEqual);
		tokenMap.put(identifierMatcher, this::handleIdentifier);
		tokenMap.put(quotationMatcher, this::handleString);
	}

	private Token handleWhitespace(StringBuilder builder) {
		getNextChar();
		return null;
	}
	
	private Token handleSlash(StringBuilder builder) {
		return new Token(TokenType.SLASHTOKEN, Character.toString(getCurrentCharAndGoOn()));
	}
	
	private Token handleOpenAngleBracket(StringBuilder builder) throws LexerException {
		char lookahead = getLookaheadChar(1);
		if(lookahead == '!'|| lookahead == '?') {
			getNextChar();
			while(!closeAngleBracketMatcher.match(currentChar) &&  currentChar!= EOF) {
				getNextChar();
			}
		}
		lastBracketSeen = true;
		return new Token( TokenType.OPENANGLEBRACKETTOKEN, Character.toString(getCurrentCharAndGoOn()));
	}
	
	private Token handleCloseAngleBracket(StringBuilder builder) {
		lastBracketSeen = false;
		return new Token(TokenType.CLOSEANGLEBRACKETTOKEN, Character.toString(getCurrentCharAndGoOn()));
	}
	
	private Token handleEqual(StringBuilder builder) {
		return new Token(TokenType.EQUALTOKEN, Character.toString(getCurrentCharAndGoOn()));
	}
	
	private Token handleIdentifier(StringBuilder builder) {
		if(builder == null) builder = new StringBuilder();
		if(identifierMatcher.match(currentChar))
			return handleIdentifier(builder.append(getCurrentCharAndGoOn()));
		
		return new Token(TokenType.IDENTIFIERTOKEN, builder.toString());
	}
	
	private Token handleString(StringBuilder builder) throws LexerException {
		if(currentChar == EOF) throw new LexerException("String was not closed.");
		if(builder == null) builder = new StringBuilder();
		
		if(!quotationMatcher.match(currentChar) || builder.length() == 0)
			return handleString(builder.append(getCurrentCharAndGoOn()));
		
		getNextChar();
		return new Token(TokenType.STRINGTOKEN, builder.substring(1));
	}
	
	private boolean contentMatcher(char charToMatch) {
		return !lastBracketSeen && !openAngleBracketMatcher.match(currentChar) && !whitespaceMatcher.match(currentChar);
	}
	
	private Token handleContent(StringBuilder builder) throws LexerException {
		boolean lastWhiteSpace = false;
		if(builder == null) builder = new StringBuilder();
		while(!openAngleBracketMatcher.match(currentChar) &&  currentChar!= EOF) {
			if(whitespaceMatcher.match(currentChar)) {
				if(!lastWhiteSpace) builder.append(currentChar); 					
				lastWhiteSpace = true;
			} else {
				builder.append(currentChar);
				lastWhiteSpace = false;
			}
			getNextChar();
		}
		return new Token(TokenType.CONTENTTOKEN, builder.toString());
	}
	
	private interface TokenGetter {
		public Token getToken(StringBuilder builder) throws LexerException;
	}
}
