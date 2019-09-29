public class ComputingFactorials {

    public static void main(String[] args) {

        // modify this to compute the factorial
        int fact = 20;

        // factorial 1 * 2 * 3 * ... n-1 * n
        // (n * (n-1)!) and n > 0

        // Recursive way of doing factorial
        System.out.println("Recursion " + factorial(fact));

        // Iterative way of doing factorial (faster)
        long result = 1;
        for (int x = 1; x <= fact; x++) {
            result *= x;
        }
        System.out.println("Iteration " + result);
    }

    /** Be aware of the overflow */
    public static long factorial(long n) {

        if (n == 1)
            return 1;
        else {
            return n * factorial(n - 1);
        }
    }
}