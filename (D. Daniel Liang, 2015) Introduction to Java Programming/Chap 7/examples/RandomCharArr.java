import java.util.*;
import java.io.*;

/**
 * Get random characters using the built-in methods of RandomCharacter classs
 */
public class RandomCharArr {

    public static void main(String[] args) throws IOException {

        /**
         * Set the console to uft-8
         */
        PrintStream out = new PrintStream(System.out, true, "UTF-8");

        // create a char[] with random characters in it.
        char[] arr = getRandomArray(10);
        out.println(Arrays.toString(arr));

    }

    /**
     * Create random char array based on the speicified number of chars.<br>
     * Random char includes [0-9a-zA-Z]
     *
     * @param num length of the char[]
     * @return new char[] of random chars
     */
    public static char[] getRandomArray(int num) {
        var rand = new Random();
        var charArr = new char[num];

        for (int x = 0; x < num; x++) {
            int n = rand.nextInt(65535 + 1);
            char c = (char) n;
            charArr[x] = c;
        }
        return charArr;
    }
}