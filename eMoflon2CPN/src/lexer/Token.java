package lexer;

public class Token {

	//Identifier: names of variables and functions
	//Keywords: while, for, if, else, return, int, char
	//Separators: ; , ( ) { }
	//Operators: + - * / = < > 
	//Literal 0 1 2 3 4 5 6 7 8 9 -
	
	private String content;
	private TokenType type;
	
	public Token(String content, TokenType type) {
		this.content = content;
		this.type = type;
	}
	
	public String getContent() {
		return this.content;
	}
	
	public TokenType getType() {
		return this.type;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Token other = (Token) obj;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Token [content=" + content + ", type=" + type + "]";
	}
}
