/**
 * Demonstrates the Math class
 */
public class MathExample {

    public static void main(String[] args) {

        /*
         * -----------------------------------------------
         * 
         * Constants in Math class
         * 
         * -----------------------------------------------
         */
        System.out.println("PI::: " + Math.PI);
        System.out.println("Natural base of natural logarithms::: " + Math.E + "\n");

        /*
         * -----------------------------------------------
         * 
         * Trigonometric Methods
         * 
         * -----------------------------------------------
         */
        System.out.println("Math.toDegrees(Math.PI / 2)::: " + Math.toDegrees(Math.PI / 2));

        // toRadians
        System.out.println("Math.toRadians(30)::: " + Math.toRadians(30));

        // sin
        System.out.println("Math.sin(0)::: " + Math.sin(0));

        // cos
        System.out.println("Math.cos(0)::: " + Math.cos(0));

        // asin (inverse sine)
        System.out.println("Math.asin(0.5)::: " + Math.asin(0.5));

        // acos (inverse cos)
        System.out.println("Math.acos(0.5)::: " + Math.acos(0.5));

        // atan (inverse tan)
        System.out.println("Math.atan(1.0)::: " + Math.atan(1.0) + "\n");

        /*
         * -----------------------------------------------
         * 
         * Exponent Methods
         * 
         * -----------------------------------------------
         */

        // exp() - e raised to the power of x(e to the power of x)
        System.out.println("Math.exp(1)::: " + Math.exp(1));

        // log()
        System.out.println("Math.log(Math.E)::: " + Math.log(Math.E));

        // log10()
        System.out.println("Math.log10(10)::: " + Math.log10(10));

        // pow(a,b)
        System.out.println("Math.pow(2, 3)::: " + Math.pow(2, 3));

        // sqrt()
        System.out.println("Math.sqrt(4)::: " + Math.sqrt(4) + "\n");

        /*
         * -----------------------------------------------
         * 
         * Rounding Methods
         * 
         * -----------------------------------------------
         */

        // ceil() - round up
        System.out.println("Math.ceil(2.1)::: " + Math.ceil(2.1));

        // floor() - round down
        System.out.println("Math.floor(2.1)::: " + Math.floor(2.1));

        // rint() - round up
        System.out.println("Math.rint(2.1)::: " + Math.rint(2.1));

        // round()
        System.out.println("Math.round(2.6)::: " + Math.round(2.6) + "\n");

        /*
         * -----------------------------------------------
         * 
         * min, max and abs Methods
         * 
         * -----------------------------------------------
         */

        // min
        System.out.println("Math.min(2,3)::: " + Math.min(2, 3));

        // max
        System.out.println("Math.max(2,3)::: " + Math.max(2, 3));

        // abs
        System.out.println("Math.abs(-10)::: " + Math.abs(-10) + "\n");

        /*
         * -----------------------------------------------
         * 
         * random Method
         * 
         * -----------------------------------------------
         */

        // random
        System.out.println("(int) (Math.random() * 10)::: " + (int) (Math.random() * 10));
        System.out.println("50 + (int) (Math.random() * 10)::: " + (50 + (int) (Math.random() * 10)));

    }
}