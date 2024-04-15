package programs;

import java.util.*;

public class LeftRecursionRemoval {
    public static void main(String[] args) {
        // Input grammar
        Map<String, List<String>> grammar = new LinkedHashMap<>();
        grammar.put("S", Arrays.asList("S A", "A"));
        grammar.put("A", Arrays.asList("A a", "b"));

        // Remove left recursion
        Map<String, List<String>> modifiedGrammar = removeLeftRecursion(grammar);

        // Display modified grammar
        System.out.println("Modified Grammar:");
        for (Map.Entry<String, List<String>> entry : modifiedGrammar.entrySet()) {
            System.out.print(entry.getKey() + " -> ");
            System.out.println(String.join(" | ", entry.getValue()));
        }
    }

    public static Map<String, List<String>> removeLeftRecursion(Map<String, List<String>> grammar) {
        Map<String, List<String>> modifiedGrammar = new LinkedHashMap<>();

        for (Map.Entry<String, List<String>> entry : grammar.entrySet()) {
            String nonTerminal = entry.getKey();
            List<String> productions = entry.getValue();
            List<String> newProductions = new ArrayList<>();

            List<String> leftRecursiveProductions = new ArrayList<>();
            List<String> rightRecursiveProductions = new ArrayList<>();

            // Separate left and right recursive productions
            for (String production : productions) {
                String[] symbols = production.split("\\s+");
                if (symbols[0].equals(nonTerminal)) {
                    leftRecursiveProductions.add(production.substring(nonTerminal.length() + 1));
                } else {
                    rightRecursiveProductions.add(production);
                }
            }

            // If there is left recursion, modify the grammar
            if (!leftRecursiveProductions.isEmpty()) {
                String newNonTerminal = nonTerminal + "'";
                for (String rightProduction : rightRecursiveProductions) {
                    newProductions.add(rightProduction + " " + newNonTerminal);
                }
                for (String leftProduction : leftRecursiveProductions) {
                    newProductions.add(leftProduction + " " + newNonTerminal);
                }
                newProductions.add("0"); // Epsilon production
                modifiedGrammar.put(nonTerminal, newProductions);

                List<String> newNonTerminalProductions = new ArrayList<>();
                for (String leftProduction : leftRecursiveProductions) {
                    newNonTerminalProductions.add(leftProduction.substring(1) + " " + newNonTerminal);
                }
                newNonTerminalProductions.add("0"); // Epsilon production
                modifiedGrammar.put(newNonTerminal, newNonTerminalProductions);
            } else {
                modifiedGrammar.put(nonTerminal, productions);
            }
        }

        return modifiedGrammar;
    }
}
