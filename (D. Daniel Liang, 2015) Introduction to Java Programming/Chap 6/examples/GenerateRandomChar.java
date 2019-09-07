import java.util.*;

/**
 * Generate random character through generating random numbers
 */
public class GenerateRandomChar {

    public static void main(String[] args) {

        char c = generateRandomChar();
        System.out.println("Random char geneated: '" + c + "'");

        char d = generateRandomChar('a', 'e');
        System.out.println("Generate random char between 'a' and 'd': '" + d + "'");
    }

    /**
     * Generate random char
     * 
     * @return a random char
     */
    public static char generateRandomChar() {

        // char is encoded using unicode(hexadecimal 0 - FFFF/65535)
        char c;

        // generate int(unicode) that represents the char
        int n = new Random().nextInt(65535 + 1);

        c = (char) n;
        System.out.println(c);

        return c;
    }

    /**
     * Generate char between two chars (Exclusive)
     * 
     * @param a first char
     * @param b second char
     * @return random char between the two given chars
     */
    public static char generateRandomChar(char a, char b) {
        char c;
        int n;

        if (a > b) {
            n = b + 1 + new Random().nextInt(a - b - 2);
        } else if (a < b) {
            n = a + 1 + new Random().nextInt((b - a - 2));
        } else {
            return a;
        }

        c = (char) n;
        return c;
    }
}