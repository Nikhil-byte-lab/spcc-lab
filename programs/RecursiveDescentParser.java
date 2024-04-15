package programs;


//S -> AB
//A -> aA | ε
//B -> bB | ε


public class RecursiveDescentParser {
    private static String input;
    private static int index;

    public static void main(String[] args) {
        input = "aabbb";
        index = 0;

        if (parseS()) {
            System.out.println("Parsing successful.");
        } else {
            System.out.println("Parsing failed.");
        }
    }

    private static boolean parseS() {
        if (index < input.length() && parseA() && parseB()) {
            return true;
        }
        return false;
    }

    private static boolean parseA() {
        if (index < input.length() && input.charAt(index) == 'a') {
            index++;
            return parseA();
        }
        return true; // epsilon
    }

    private static boolean parseB() {
        if (index < input.length() && input.charAt(index) == 'b') {
            index++;
            return parseB();
        }
        return true; // epsilon
    }
}

