/**
 * This class demonstrates a common error of the equality test of two floating
 * point values. Floating point numbers have a limited precision and
 * calculations, the equality test of the floating points numbers is not
 * reliable.
 */
public class ComparingFloatingPointValue {

    public static void main(String[] args) {

        // you expect this to be 0.5
        double x = 1.0 - 0.1 - 0.1 - 0.1 - 0.1 - 0.1;

        // it is not true, because x is 0.5000000000001. This test is not reliable.
        System.out.println(x == 0.5);

        /*
         * this should be tested in such a way ---------------------------------------
         * EPSILON - is 10 to the power of negative 7.
         */
        final double EPSILON = 1E-14;
        if (Math.abs(x - 0.5) < EPSILON)
            System.out.println(x + " is approximately equal to 0.5");

        // the final result is "0.5000000000000001 is approximately equal to 0.5"
    }
}