/**
 * Simplyfying the boolean variable assignment
 */
public class SimplyfyingBooleanAssignment {

    public static void main(String[] args) {

        int neededTime = 10;
        int actualTime = 10;
        boolean cooked;

        // not a good example:
        if (actualTime >= neededTime)
            cooked = true;
        else
            cooked = false;

        System.out.println(cooked);

        // a better example:
        cooked = false;
        cooked = actualTime >= neededTime;

        System.out.println(cooked);

    }
}