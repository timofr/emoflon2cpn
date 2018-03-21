package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import chooser.ChooserException;
import chooser.ChooserImpl;
import generator.GeneratorImpl;
import lexer.LexerException;
import lexer.LexerImpl;
import mapper.MapperException;
import mapper.MapperImpl;
import mapper.XmlNodeFactory;
import parser.ParserException;
import parser.ParserImpl;

public class main {
	public static void main(String[] args) throws FileNotFoundException, IOException, LexerException, ParserException, ChooserException, MapperException {
		IOHandler ioHandler = new IOHandler();
		
		String path = "src/resources/emoflon2.txt";
		String input;
		try(Scanner scanner = new Scanner(new FileInputStream(path)).useDelimiter("\\A")) {     
			input = scanner.hasNext() ? scanner.next() : "";
		}
		
		try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
			Lexer lexer = new LexerImpl(input);
			Parser parser = new ParserImpl(lexer.getTokenList());
			//System.out.println(parser.getXmlTree().toString());
			Chooser chooser = new ChooserImpl(parser.getXmlTree());
			System.out.println("Parser finished");
			System.out.println(chooser.getClasses());
			chooser.chooseClass("TestClass");//reader.readLine());
			System.out.println(chooser.getMethods());
			
			Mapper mapper = new MapperImpl(chooser.chooseMethod("operation"));//reader.readLine()));
			System.out.println("Chooser finished");
			Inserter inserter = new Inserter(mapper.getMappedCpnTree());
			System.out.println("Mapper finished");
			Generator generator = new GeneratorImpl(mapper.getMappedCpnTree());
			//Generator generator = new GeneratorImpl(inserter.getTree());
			//System.out.println("Inserter finished");
			ioHandler.write(new File("newCpn.txt"), generator.generateCode());
			System.out.println("Generator finished");
		}
		

		
		
	}
}
