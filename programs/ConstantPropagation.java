package programs;
import java.util.*;

public class ConstantPropagation {
    public static void main(String[] args) {
        // Input code with assignments and expressions
        List<String> code = Arrays.asList(
            "int a = 5;",
            "int b = a * 2;",
            "int c = b - 10;",
            "int d = c + a;"
        );

        System.out.println("Original code:");
        for (String line : code) {
            System.out.println(line);
        }

        optimizeCode(code);

        System.out.println("\nOptimized code:");
        for (String line : code) {
            System.out.println(line);
        }
    }

    public static void optimizeCode(List<String> code) {
        Map<String, Integer> constants = new HashMap<>();

        // First pass: Identify constants and their values
        for (String line : code) {
            if (line.matches("^\\s*int\\s+\\w+\\s*=\\s*\\d+\\s*;$")) {
                String[] parts = line.split("\\s*=\\s*");
                String variable = parts[0].split("\\s+")[1];
                int value = Integer.parseInt(parts[1].replaceAll("[^\\d-]", ""));
                constants.put(variable, value);
            }
        }

        // Second pass: Replace variables with constant values
        for (int i = 0; i < code.size(); i++) {
            String line = code.get(i);
            for (Map.Entry<String, Integer> entry : constants.entrySet()) {
                String variable = entry.getKey();
                int value = entry.getValue();
                line = line.replaceAll("\\b" + variable + "\\b", String.valueOf(value));
            }
            code.set(i, line);
        }
    }
}
