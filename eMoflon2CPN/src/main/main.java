package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import simulation.Server;

import javacpn.EncodeDecode;
import javacpn.JavaCPN;
import javacpn.JavaCPNInterface;
import translation.Translation;
import translation.chooser.ChooserException;
import translation.chooser.ChooserImpl;
import translation.generator.GeneratorImpl;
import translation.lexer.LexerException;
import translation.lexer.LexerImpl;
import translation.mapper.MapperException;
import translation.mapper.MapperImpl;
import translation.mapper.XmlNodeFactory;
import translation.parser.ParserException;
import translation.parser.ParserImpl;

public class main {
	public static void main(String[] args) throws FileNotFoundException, IOException, LexerException, ParserException, ChooserException, MapperException {
	
//		IOHandler.write(new File("emoflonoutput.txt"), new GeneratorImpl(new ParserImpl(new LexerImpl(IOHandler.read(new File("src/resources/test2.txt"))).getTokenList()).getXmlTree()).generateCode());
		
//		Translation.translate(new File("src/resources/emoflon/EmoflonTestCode.ecore"), new File("cpnoutput.cpn"));
		
		
//		JavaCPNInterface cpn = new JavaCPN();
//		cpn.connect("localhost", 9000);
//		System.out.println(EncodeDecode.decodeString(cpn.receive()));
//		boolean send = Math.random() < 0.5;
//		cpn.send(EncodeDecode.encode(Boolean.toString(send)));
//		System.out.println("Send: " + send + " " + EncodeDecode.decodeString(cpn.receive()));
//		cpn.disconnect();
		
		Server server = new Server(9000);
		server.communicate();
	}
}
