package lexer;

public class CharRange {
	private char start;
	private char end;
	
	public CharRange(char start, char end) {
		this.start = start;
		this.end = end;
	}
	
	public char getStart() {
		return this.start;
	}
	
	public char getEnd() {
		return this.end;
	}
}
