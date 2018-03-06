package lexer;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;

public class CharMatcher {
	
	
	
	private LinkedList<Matcher> matcher = new LinkedList<Matcher>();
	
	public CharMatcher(char c) {
		this.matcher.add(new SingleCharMatcher(c));
	}
	
	public CharMatcher(char... c) {
		this.matcher.add(new ArrayCharMatcher(c));
	}
	
//	public CharMatcher(CharRange range) {
//		this.matcher.add(new RangeCharMatcher(range));
//	}

	public CharMatcher(CharRange... range) {
		Arrays.stream(range).forEach(r -> this.matcher.add(new RangeCharMatcher(r)));
//		for(CharRange r: range) {
//			this.matcher.add(new RangeCharMatcher(r));
//		}
	}
	
	public boolean match(char charToMatch) {
		for(Matcher m: matcher) {
			if(m.match(charToMatch))
				return true;
		}
		return false;
	}
	
	
	
	private abstract class Matcher {
		
		public abstract boolean match(char c);
		
	}
	
	private class SingleCharMatcher extends Matcher {
		private char c;
		
		public SingleCharMatcher(char c) {
			this.c = c;
		}
		
		public boolean match(char charToMatch) {
			return this.c == charToMatch;
		}
	}
	
	private class ArrayCharMatcher extends Matcher{
		private char[] c;
		
		public ArrayCharMatcher(char[] c) {
			this.c = c;
		}
		
		public boolean match(char charToMatch) {
			for(int i = 0; i < c.length; i++) {
				if(c[i] == charToMatch)
					return true;
			}
			return false;
		}
	}
	
	private class RangeCharMatcher extends Matcher {
		private CharRange range;
		
		public RangeCharMatcher(CharRange range) {
			this.range = range;
		}
		
		public boolean match(char charToMatch) {
			return range.getStart() <= charToMatch && charToMatch <= range.getEnd();
		}
	}
	
	
	
}