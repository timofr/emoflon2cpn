package main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.xml.stream.events.DTD;

import lexer.LexerException;
import lexer.LexerImpl;

public class main {
	public static void main(String[] args) throws FileNotFoundException, IOException, LexerException {
//		FileBuilder filebuilder = new FileBuilder();
//		filebuilder.scan();
//		filebuilder.findStartEndPoint();
		
		String path = "src/main/emoflon.txt";
		String input;
		try(Scanner scanner = new Scanner(new FileInputStream(path)).useDelimiter("\\A")) {     
			input = scanner.hasNext() ? scanner.next() : "";
		}
		//input = '"' + "test" + '"';
		Lexer lexer = new LexerImpl(input);
		System.out.println(lexer.getTokenList());
	}
}
