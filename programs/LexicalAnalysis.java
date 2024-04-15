package programs;

import java.util.*;

public class LexicalAnalysis {
    public static void main(String[] args) {
        String expression = "c = a * b + d";
        List<Token> tokens = tokenize(expression);

        System.out.println("Lexical Tokens:");
        for (Token token : tokens) {
            System.out.println(token);
        }
    }

    public static List<Token> tokenize(String expression) {
        List<Token> tokens = new ArrayList<>();
        String[] parts = expression.split("\\s+");
        for (String part : parts) {
            if (part.matches("[a-zA-Z]+")) {
                tokens.add(new Token(TokenType.IDENTIFIER, part));
            } else if (part.matches("[\\+\\-\\*\\/]")) {
                tokens.add(new Token(TokenType.OPERATOR, part));
            } else if (part.equals("=")) {
                tokens.add(new Token(TokenType.ASSIGNMENT, part));
            }
        }
        return tokens;
    }

    enum TokenType {
        IDENTIFIER, OPERATOR, ASSIGNMENT
    }

    static class Token {
        TokenType type;
        String value;

        public Token(TokenType type, String value) {
            this.type = type;
            this.value = value;
        }

        @Override
        public String toString() {
            return "Token{" +
                    "type=" + type +
                    ", value='" + value + '\'' +
                    '}';
        }
    }
}

//#include <stdio.h>
//
//#define DEBUG  // Comment this line to disable debugging
//
//#ifdef DEBUG
//#define LOG(msg) printf("Debug: %s\n", msg)
//#else
//#define LOG(msg) // Do nothing when debugging is disabled
//#endif
//
//int main() {
//    LOG("This is a debug message.");
//    printf("Hello, world!\n");
//    return 0;
//}

