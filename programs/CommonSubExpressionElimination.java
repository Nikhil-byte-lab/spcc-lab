package programs;

import java.util.*;

public class CommonSubExpressionElimination {
    public static void main(String[] args) {
        String[] expressions = {
            "a = b + c",
            "d = a + b",
            "e = b + c",
            "f = d + e"
        };

        Map<String, String> expressionMap = new HashMap<>();
        int variableCount = 1;

        for (String expr : expressions) {
            String[] parts = expr.split("\\s*=\\s*");
            String variable = parts[0].trim();
            String expression = parts[1].trim();

            // Check if expression already exists
            if (!expressionMap.containsValue(expression)) {
                String newVariable = "temp" + variableCount++;
                expressionMap.put(newVariable, expression);
                expression = newVariable;
            } else {
                for (Map.Entry<String, String> entry : expressionMap.entrySet()) {
                    if (entry.getValue().equals(expression)) {
                        expression = entry.getKey();
                        break;
                    }
                }
            }

            System.out.println(variable + " = " + expression);
        }
        
        // Print temp variables
        System.out.println("\nTemp Variables:");
        for (Map.Entry<String, String> entry : expressionMap.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }
    }
}

