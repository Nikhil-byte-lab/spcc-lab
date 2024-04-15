package programs;
import java.util.*;

public class LexicalAnalyzer {
	public static void main(String[] args) {
		String input = "int a, b, c;";
		List<Token> tokens = tokenize(input);
		System.out.println("Lexical Analysis Result:");
		for(Token token: tokens) {
			System.out.println(token);
		}
			
	}
	
	public static List<Token> tokenize(String input){
		List<Token> tokens = new ArrayList<>();
		StringBuilder buffer = new StringBuilder();
		
		char[] chars = input.toCharArray();
		
		for(char c: chars) {
			if(Character.isLetter(c) || c == ',') {
				buffer.append(c);
				
			} else {
				if(buffer.length() > 0) {
					if(buffer.toString().equals("if")|| 
					   buffer.toString().equals("else")||
					   buffer.toString().equals("int")||
				       buffer.toString().equals("int") || 
			   	       buffer.toString().equals("while")) {
						tokens.add(new Token(buffer.toString(), TokenType.Keyword));
						buffer.setLength(0);
					} else {
						tokens.add(new Token(buffer.toString(), TokenType.Identifier));
						buffer.setLength(0);
					}
				}
				
				if(!Character.isWhitespace(c)) {
					tokens.add(new Token(String.valueOf(c), TokenType.Symbol));
				}
			}
			
		}
		if (buffer.length() > 0 ) {
			tokens.add(new Token(buffer.toString(), TokenType.Identifier));
			
		}
		return tokens;
		
	}
}

class Token{
	String lexeme;
	TokenType type;
	
	public Token(String lexeme, TokenType type) {
		this.lexeme = lexeme;
		this.type = type;
	}
	
	public String toString(){
		return "{" + lexeme + ", " + type + "}";
	}
}


enum TokenType{
	Identifier, Symbol, Keyword
}