public class ComputingFibonacci {
    public static void main(String[] args) {

        // Fibnocacci 0 1 1 2 3 5 8 13
        long fibNum = 6;
        System.out.println(fib(fibNum));

    }

    public static long fib(long n) {
        if (n == 0)
            return 0;
        else if (n == 1)
            return 1;
        else {
            return fib(n - 1) + fib(n - 2);
        }
    }
}