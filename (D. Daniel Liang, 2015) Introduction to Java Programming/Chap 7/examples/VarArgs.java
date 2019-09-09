/**
 * Variable - length <br>
 * <br>
 * In the method declaration, you specify the type followed by an ellipsis
 * (...). Only one variable-length parameter may be specified in a method, and
 * this parameter must be the last parameter. Any regular parameters must
 * precede it.
 */
public class VarArgs {
    public static void main(String[] args) {

        printNums(new int[] { 1, 2, 3, 4, 5 });
    }

    public static void printNums(int... nums) {
        for (int n : nums) {
            System.out.println(n);
        }
    }
}