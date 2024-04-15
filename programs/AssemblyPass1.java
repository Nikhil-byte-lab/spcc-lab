package programs;

import java.util.*;

public class AssemblyPass1 {
    public static void main(String[] args) {
        String[] assemblyCode = {
            "START 100",
            "MOV A, B",
            "LOOP: ADD A, 1",
            "CMP A, 10",
            "JNZ LOOP",
            "END"
        };

        Map<String, Integer> symbolTable = new HashMap<>();

        int locationCounter = 100; // Starting address

        for (String instruction : assemblyCode) {
            String[] parts = instruction.split("\\s+");
            String opcode = parts[0];

            if (opcode.endsWith(":")) { // Label detected
                String label = opcode.substring(0, opcode.length() - 1);
                symbolTable.put(label, locationCounter);
            }

            locationCounter += getInstructionSize(opcode);
        }

        // Display symbol table
        System.out.println("Symbol Table:");
        for (Map.Entry<String, Integer> entry : symbolTable.entrySet()) {
            System.out.println(entry.getKey() + "\t" + entry.getValue());
        }
    }

    // Method to get size of instruction based on opcode
    private static int getInstructionSize(String opcode) {
        switch (opcode.toUpperCase()) {
            case "START":
            case "END":
                return 0;
            case "MOV":
            case "ADD":
            case "CMP":
            case "JNZ":
                return 1;
            default:
                return 0; // Assuming other instructions have size 0
        }
    }
}


//#include <stdio.h>
//
//#define SQUARE(x) ((x) * (x))
//#define CUBE(x) (SQUARE(x) * (x))
//
//int main() {
//    int num = 5;
//    printf("Square of %d is %d\n", num, SQUARE(num));
//    printf("Cube of %d is %d\n", num, CUBE(num));
//    return 0;
//}


//#include <stdio.h>
//
//int main() {
//    int a = 5, b = 10, c = 3;
//    int x, y, z;
//
//    // Original expressions
//    x = a * b + c;
//    y = a * b + c;
//    z = a * b + c;
//
//    // Common subexpression elimination
//    int temp = a * b + c;
//    x = temp;
//    y = temp;
//    z = temp;
//
//    // Print results
//    printf("Original expressions:\n");
//    printf("x = %d\n", x);
//    printf("y = %d\n", y);
//    printf("z = %d\n\n", z);
//
//    printf("Expressions after common subexpression elimination:\n");
//    printf("x = %d\n", x);
//    printf("y = %d\n", y);
//    printf("z = %d\n", z);
//
//    return 0;
//}


