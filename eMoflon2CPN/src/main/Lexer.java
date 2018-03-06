package main;

import java.util.List;

import lexer.LexerException;
import lexer.Token;

public interface Lexer {
	public List<Token> getTokenList() throws LexerException;
}
