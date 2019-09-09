import java.util.*;

/**
 * Not copying arrays using loop. An example of arraycopy(...) method.
 */
public class CopyArray {

    public static void main(String[] args) {
        int[] arrOne = { 1, 2, 3, 4, 5 };
        int[] arrTwo = new int[3];

        // copy 2, 3, 4 to arrTwo
        java.lang.System.arraycopy(arrOne, 1, arrTwo, 0, 3);
        System.out.println("Arr two: " + Arrays.toString(arrTwo));
    }
}