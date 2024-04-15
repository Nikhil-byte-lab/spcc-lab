package programs;
import java.util.*;

public class QuadruplesGenerator {
    public static void main(String[] args) {
        String expression = "( b * c ) - ( b * c )";
        List<Quadruple> quadruples = generateQuadruples(expression);
        System.out.println("Quadruples in Three Address Code:");
        for (Quadruple quadruple : quadruples) {
            System.out.println(quadruple);
        }
    }

    public static List<Quadruple> generateQuadruples(String expression) {
        List<Quadruple> quadruples = new ArrayList<>();
        String[] tokens = expression.split("\\s+");
        Stack<String> operators = new Stack<>();
        Stack<String> operands = new Stack<>();
        int tempCount = 1;

        for (String token : tokens) {
            if (token.equals("(")) {
                continue;
            } else if (token.equals(")")) {
                while (!operators.isEmpty()) {
                    String op = operators.pop();
                    if (op.equals("(")) {
                        break; // Exit the loop when encountering "("
                    }
                    String arg2 = operands.pop();
                    String arg1 = operands.pop();
                    String result = "t" + tempCount++;
                    quadruples.add(new Quadruple(op, arg1, arg2, result));
                    operands.push(result);
                }
            } else if (token.equals("=") || token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")) {
                operators.push(token);
            } else {
                operands.push(token);
            }
        }
        
        // Process remaining operators and operands
        while (!operators.isEmpty()) {
            String op = operators.pop();
            String arg2 = operands.pop();
            String arg1 = operands.pop();
            String result = "t" + tempCount++;
            quadruples.add(new Quadruple(op, arg1, arg2, result));
            operands.push(result);
        }

        return quadruples;
    }
}

class Quadruple {
    String op;
    String arg1;
    String arg2;
    String result;

    public Quadruple(String op, String arg1, String arg2, String result) {
        this.op = op;
        this.arg1 = arg1;
        this.arg2 = arg2;
        this.result = result;
    }

    @Override
    public String toString() {
        return "(" + op + ", " + arg1 + ", " + arg2 + ", " + result + ")";
    }
}