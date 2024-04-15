package programs;

import java.util.*;

public class DeadCodeElimination {
    public static void main(String[] args) {
        List<String> code = Arrays.asList(
            "int x = 50;",
            "int y = 10;",
            "if (x > y) {",
            "    System.out.println(\"x is greater than y\");",
            "} else {",
            "    System.out.println(\"y is greater than x\");",
            "}"
        );

        System.out.println("Original code:");
        for (String line : code) {
            System.out.println(line);
        }

        List<String> optimizedCode = eliminateDeadCode(code);

        System.out.println("\nOptimized code:");
        for (String line : optimizedCode) {
            System.out.println(line);
        }
    }

    public static List<String> eliminateDeadCode(List<String> code) {
        List<String> optimizedCode = new ArrayList<>();
        Stack<Boolean> conditionStack = new Stack<>();

        for (String line : code) {
            if (line.contains("if")) {
                // Start of if block
                conditionStack.push(true);
            } else if (line.contains("else")) {
                // Start of else block
                if (!conditionStack.isEmpty()) {
                    conditionStack.pop();
                    conditionStack.push(false);
                }
            } else if (line.contains("}")) {
                // End of block
                conditionStack.pop();
            } else if (conditionStack.isEmpty() || conditionStack.peek()) {
                // If condition is true or no active if blocks
                optimizedCode.add(line);
            }
        }

        return optimizedCode;
    }
}
