package programs;

public class FactorialCalculator {
    public static void main(String[] args) {
        int num = 5;

        // Calculate factorial of num
        int result = factorial(num);

        System.out.println("Factorial of " + num + " is: " + result);
    }

    // Recursive method to calculate factorial
    public static int factorial(int n) {
        if (n <= 1) {
            return 1; // Base case: factorial of 0 and 1 is 1
        } else {
            return n * factorial(n - 1); // Recursive call to calculate factorial
        }
    }
}
