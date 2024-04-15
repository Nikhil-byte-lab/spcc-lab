package programs;

import java.util.*;

public class MacroProcessor {
    public static void main(String[] args) {
        // Sample ALP
        List<String> alp = Arrays.asList(
            "START",
            "MACRO",
            "ADD A,B",
            "SUB C,D",
            "MEND",
            "ADD X,Y",
            "SUB M,N",
            "END"
        );

        // Display MNT and MDT
        Map<String, Integer> mnt = new HashMap<>();
        List<String> mdt = new ArrayList<>();
        processALP(alp, mnt, mdt);

        System.out.println("Macro Name Table (MNT):");
        System.out.println("Macro Name\tIndex");
        for (Map.Entry<String, Integer> entry : mnt.entrySet()) {
            System.out.println(entry.getKey() + "\t\t" + entry.getValue());
        }

        System.out.println("\nMacro Definition Table (MDT):");
        for (int i = 0; i < mdt.size(); i++) {
            System.out.println(i + "\t" + mdt.get(i));
        }
    }

    public static void processALP(List<String> alp, Map<String, Integer> mnt, List<String> mdt) {
        int mdtIndex = -1;

        for (int i = 0; i < alp.size(); i++) {
            String line = alp.get(i);
            if (line.equals("MACRO")) {
                // Start of macro definition
                String macroName = alp.get(++i);
                mnt.put(macroName, mdtIndex + 1);
                mdt.add(macroName);
                mdtIndex++;

                // Process until MEND
                while (!alp.get(++i).equals("MEND")) {
                    mdt.add(alp.get(i));
                    mdtIndex++;
                }
            }
        }
    }
}



//#include <stdio.h>
//
//#define MAX(a, b) ((a) > (b) ? (a) : (b))
//
//int main() {
//    int x = 10, y = 20;
//    int max_value = MAX(x, y);
//    printf("Max value: %d\n", max_value);
//    return 0;
//}



//#include <stdio.h>
//
//int main() {
//    int i, sum = 0;
//    int array[] = {1, 2, 3, 4, 5};
//
//    // Original code
//    for (i = 0; i < 5; i++) {
//        sum += array[i];
//    }
//    printf("Sum (original): %d\n", sum);
//
//    // Code with loop invariant code motion
//    sum = 0; // Reset sum
//    int n = 5; // Move loop-invariant calculation outside the loop
//    for (i = 0; i < n; i++) {
//        sum += array[i];
//    }
//    printf("Sum (optimized): %d\n", sum);
//
//    return 0;
//}





