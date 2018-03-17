package lexer;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import main.Lexer;

public class LexerImpl implements Lexer {

	// TODO refactor methods with string builder
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

	private Token handleWhitespace(String str) {
		getNextChar();
		return null;
	}
	
	private Token handleSlash(String str) {
		return new Token(TokenType.SLASHTOKEN, Character.toString(getCurrentCharAndGoOn()));
	}
	
	private Token handleOpenAngleBracket(String str) {
		lastBracketSeen = true;
		return new Token( TokenType.OPENANGLEBRACKETTOKEN, Character.toString(getCurrentCharAndGoOn()));
	}
	
	private Token handleCloseAngleBracket(String str) {
		lastBracketSeen = false;
		return new Token(TokenType.CLOSEANGLEBRACKETTOKEN, Character.toString(getCurrentCharAndGoOn()));
	}
	
	private Token handleEqual(String str) {
		return new Token(TokenType.EQUALTOKEN, Character.toString(getCurrentCharAndGoOn()));
	}
	
	private Token handleIdentifier(String str) {
		if(identifierMatcher.match(currentChar))
			return handleIdentifier(str + "" + getCurrentCharAndGoOn());
		
		return new Token(TokenType.IDENTIFIERTOKEN, str);
	}
	
	private Token handleString(String str) throws LexerException {
		if(currentChar == EOF) throw new LexerException("String was not closed.");
		
		if(!quotationMatcher.match(currentChar) || str.length() == 0)
			return handleString(str + getCurrentCharAndGoOn());
		
		getNextChar();
		return new Token(TokenType.STRINGTOKEN, str.substring(1));
	}
	
	private boolean contentMatcher(char charToMatch) {
		return !lastBracketSeen && !openAngleBracketMatcher.match(currentChar) && !whitespaceMatcher.match(currentChar);
	}
	
	private Token handleContent(String str) throws LexerException {
		StringBuilder content = new StringBuilder();
		while(!openAngleBracketMatcher.match(currentChar)) {
			content.append(currentChar);
			getNextChar();
		}
		return new Token(TokenType.CONTENTTOKEN, content.toString());
	}
	
	private interface TokenGetter {
		public <T extends CharSequence> Token getToken(T str) throws LexerException;
	}
}
