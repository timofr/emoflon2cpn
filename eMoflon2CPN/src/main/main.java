package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

import simulation.Server;
import simulation.SimulationException;
import simulation.XmiReader;
import javacpn.EncodeDecode;
import javacpn.JavaCPN;
import javacpn.JavaCPNInterface;
import translation.Translation;
import translation.TranslationException;
import translation.chooser.ChooserException;
import translation.chooser.ChooserImpl;
import translation.generator.GeneratorImpl;
import translation.lexer.LexerException;
import translation.lexer.LexerImpl;
import translation.mapper.EmoflonAddressInterpreter;
import translation.mapper.MapperException;
import translation.mapper.MapperImpl;
import translation.mapper.XmlNodeFactory;
import translation.parser.ParserException;
import translation.parser.ParserImpl;

public class main {
	public static void main(String[] args) throws FileNotFoundException, IOException, LexerException, ParserException, ChooserException, MapperException, ClassNotFoundException, TranslationException, SimulationException {
		
		Translation translation = Translation.getTranslation();
		translation.translate(new File("C:\\Users\\timof\\Desktop\\Dateien\\Workspaces\\EclipseModelingWorkspace\\EmoflonExampleCode"));
		
		int seconds = 60;
		for(int i = seconds; i > 0; i--) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(i);
		}
		
		Server server = new Server(9000);
		server.communicate();
		
	}
}
