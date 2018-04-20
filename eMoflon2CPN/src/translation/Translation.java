/**
 * 
 */
package translation;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

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
	public static void translate(File inputFile, File outputFile) {
		try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
			String input = IOHandler.read(inputFile);
			Lexer lexer = TranslationFactory.getLexer(input);
			Parser parser = TranslationFactory.getParser(lexer.getTokenList());
			//System.out.println(parser.getXmlTree().toString());
			Chooser chooser = TranslationFactory.getChooser(parser.getXmlTree());
			System.out.println("Parser finished");
			System.out.println(chooser.getClasses());
			chooser.chooseClass("TestClass");//reader.readLine());
			System.out.println(chooser.getMethods());
			Mapper mapper = TranslationFactory.getMapper(chooser.chooseMethod("statement"));//reader.readLine()));
			System.out.println("Chooser finished");
			Inserter inserter = TranslationFactory.getInserter(mapper.getMappedCpnTree());
			System.out.println("Mapper finished");
			Generator generator = TranslationFactory.getGenerator(inserter.getTree());
			System.out.println("Inserter finished");
			IOHandler.write(outputFile, generator.generateCode());
			System.out.println("Generator finished");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
