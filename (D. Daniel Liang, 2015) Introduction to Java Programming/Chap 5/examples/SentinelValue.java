/**
 * Sentinel value is a value that indicates the end of the file or loop
 */
public class SentinelValue {

    public static void main(String[] args) {

        int[] num = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 0 };

        int sentinel = 0;

        int index = 0;
        while (num[index] != sentinel) {

            System.out.println(num[index]);
            index++;
        }
    }
}