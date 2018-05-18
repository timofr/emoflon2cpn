/**
 * 
 */
package translation;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLClassLoader;

import translation.chooser.ChooserException;
import translation.chooser.ChooserImpl;
import translation.generator.GeneratorImpl;
import translation.lexer.LexerImpl;
import translation.mapper.MapperImpl;
import translation.parser.ParserImpl;

/**
 * @author Timo Freitag
 *
 */
public class Translation {
	private Lexer lexer;
	private Parser parser;
	private Chooser chooser;
	private Mapper mapper;
	private Inserter inserter;
	private Generator generator;
	private Class<?> chosenClass;
	private URLClassLoader classLoader;
	private DirectoryHandler directoryHandler;
	private String projectName;
	
	private static Translation translation;
	private Translation() {}
	
	public static Translation getTranslation() {
		if(translation == null) translation = new Translation();
		return translation;
	}
	
	public String getProjectName() {
		return directoryHandler.getProject().getName();
	}
	
	public Lexer getLexer() {
		return lexer;
	}

	public Parser getParser() {
		return parser;
	}

	public Chooser getChooser() {
		return chooser;
	}

	public Mapper getMapper() {
		return mapper;
	}

	public Inserter getInserter() {
		return inserter;
	}

	public Generator getGenerator() {
		return generator;
	}

	public Class<?> getChosenClass() {
		return chosenClass;
	}
	
	public DirectoryHandler getDirectoryHandler() {
		return directoryHandler;
	}
	
	public URLClassLoader getClassLoader() {
		return classLoader;
	}
	
	public void translate(File directory) throws IOException, TranslationException, ChooserException, ClassNotFoundException { //TODO refactor this exception handling
		directoryHandler = TranslationFactory.getDirectoryHandler(directory);
		directoryHandler.initialize();
		String input = IOHandler.read(directoryHandler.getEcore());
		lexer = TranslationFactory.getLexer(input);
		parser = TranslationFactory.getParser(lexer.getTokenList());
		chooser = TranslationFactory.getChooser(parser.getXmlTree());
		System.out.println(chooser.getClasses());
		URL[] urls = new URL[1];
		urls[0] = directoryHandler.getBin().toURI().toURL();
		classLoader = new URLClassLoader(urls);
		chooser.chooseClass();
		chosenClass = classLoader.loadClass(directoryHandler.getFullClassName(chooser.getClassName()));
		System.out.println(chooser.getMethods());
		mapper = TranslationFactory.getMapper(chooser.chooseMethod(), chosenClass, chooser.getMethodName());
		inserter = TranslationFactory.getInserter(mapper.getMappedCpnTree());
		generator = TranslationFactory.getGenerator(inserter.getTree());
		IOHandler.write(directoryHandler.getCpn(), generator.generateCode());

	}
}
