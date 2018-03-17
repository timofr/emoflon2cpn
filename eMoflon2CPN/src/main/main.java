package main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

import javax.xml.stream.events.DTD;

import generator.GeneratorImpl;
import lexer.LexerException;
import lexer.LexerImpl;
import parser.ParserException;
import parser.ParserImpl;

public class main {
	public static void main(String[] args) throws FileNotFoundException, IOException, LexerException, ParserException {
//		FileBuilder filebuilder = new FileBuilder();
//		filebuilder.scan();
//		filebuilder.findStartEndPoint();
		
		String path = "src/main/test.txt";
		String input;
		try(Scanner scanner = new Scanner(new FileInputStream(path)).useDelimiter("\\A")) {     
			input = scanner.hasNext() ? scanner.next() : "";
		}
		
		Lexer lexer = new LexerImpl(input);
		Parser parser = new ParserImpl(lexer.getTokenList());
		Generator generator = new GeneratorImpl(parser.getXmlTree());
		System.out.println(generator.generateCode());
	}
}
