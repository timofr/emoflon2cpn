/**
 * 
 */
package translation;

import java.util.List;

import translation.chooser.ChooserImpl;
import translation.generator.GeneratorImpl;
import translation.inserter.InserterImpl;
import translation.lexer.LexerImpl;
import translation.lexer.Token;
import translation.mapper.MapperImpl;
import translation.parser.ParserImpl;
import translation.parser.XmlNode;

/**
 * @author Timo Freitag
 *
 */
public class TranslationFactory {
	public static Lexer getLexer(String input) {
		return new LexerImpl(input);
	}
	
	public static Parser getParser(List<Token> tokenList) {
		return new ParserImpl(tokenList);
	}
	
	public static Chooser getChooser(XmlNode node) {
		return new ChooserImpl(node);
	}
	
	public static Mapper getMapper(XmlNode node) {
		return new MapperImpl(node);
	}
	
	public static Inserter getInserter(XmlNode node) {
		return new InserterImpl(node);
	}
	
	public static Generator getGenerator(XmlNode node) {
		return new GeneratorImpl(node);
	}
}
