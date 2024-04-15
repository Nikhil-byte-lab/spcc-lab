package programs;

import java.util.*;

public class ThreeAddressCodeGenerator {
    private static int tempCount = 1;

    public static void main(String[] args) {
        String input = "a = b + c * d - e";
        List<String> triples = generateThreeAddressCode(input);
        
        System.out.println("Input Expression: " + input);
        System.out.println("Triples in Three Address Code:");
        for (String triple : triples) {
            System.out.println(triple);
        }
    }

    public static List<String> generateThreeAddressCode(String input) {
        List<String> triples = new ArrayList<>();
        Stack<Character> operators = new Stack<>();
        Stack<String> operands = new Stack<>();
        StringTokenizer tokenizer = new StringTokenizer(input, "+-*/=", true);

        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken().trim();

            if (token.isEmpty()) continue;

            if (isOperator(token.charAt(0))) {
                while (!operators.isEmpty() && precedence(operators.peek()) >= precedence(token.charAt(0))) {
                    String op2 = operands.pop();
                    String op1 = operands.pop();
                    String result = "t" + tempCount++;
                    triples.add(op1 + " " + operators.pop() + " " + op2 + " = " + result);
                    operands.push(result);
                }
                operators.push(token.charAt(0));
            } else {
                operands.push(token);
            }
        }

        while (!operators.isEmpty()) {
            String op2 = operands.pop();
            String op1 = operands.pop();
            String result = "t" + tempCount++;
            triples.add(op1 + " " + operators.pop() + " " + op2 + " = " + result);
            operands.push(result);
        }

        return triples;
    }

    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    private static int precedence(char op) {
        switch (op) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            default:
                return 0;
        }
    }
}
