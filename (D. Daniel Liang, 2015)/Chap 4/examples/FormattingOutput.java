/**
 * Demonstrating the use of printf
 */
public class FormattingOutput {

    public static void main(String[] args) {

        double amount = 1234.5;
        double interestRate = 0.0013;
        double interest = amount * interestRate;

        // printf(format, item1, item2, ... item k)
        System.out.printf("Amount is %f and the interestRate is %f\n", amount, interestRate);

        // with floating point precision 4 is the field width(before floating point), 2
        // is the precision (after point.)
        System.out.printf("Amount is %4.2f and the interestRate is %4.2f\n", amount, interestRate);

        // %b boolean
        System.out.printf("True or false? : %b \n", true);

        // %c character
        System.out.printf("First char is %c \n", 'A');

        // %d decimal integer
        System.out.printf("One is %d \n", 1);

        // %f floating-point number
        System.out.printf("Floating point number: %f \n", 1.11);

        // %e number in scientifc notation
        System.out.printf("Scientifc notation is %e \n", 4.55E+5);

        // %s String
        System.out.printf("Your name is %s \n\n", "curtis");

        /*
         * -------------------------------------------------------------------
         * 
         * Precision and Width
         * 
         * -------------------------------------------------------------------
         */

        // %5c (char): total spaces/width of 5, the rest will be filled with empty space
        System.out.printf("%5c\n", 'A');

        // %6b (boolean): total width of 6, add spaces before the boolean value
        System.out.printf("%6b\n", true);

        // %5d (int): total digits/width of 5, add spaces before the int or else
        // increase width
        System.out.printf("%5d\n", 543);

        // %10.2f (floating-point number): width of 10 and precision of 2
        System.out.printf("%10.2f\n", 333213.532);

        // %10.2e (scientific notation number): same as floating-point number, add space
        // before the number if the width is less than 10
        System.out.printf("%10.2e\n", 4.55E+5);

        // %12s (string): add spaces if the width is less than 12
        System.out.printf("%10s", "Curtis");
    }
}