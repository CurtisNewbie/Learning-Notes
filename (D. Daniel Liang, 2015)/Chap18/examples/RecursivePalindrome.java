
public class RecursivePalindrome {

    public static void main(String[] args) {
        String str1 = "MicroFlow";
        String str2 = "MicroorciM";
        System.out.println(isPalindromeUsingSubstring(str1));
        System.out.println(isPalindromeUsingSubstring(str2));

        System.out.println(isPalindromeUsingIndices(str1, 0, str1.length() - 1));
        System.out.println(isPalindromeUsingIndices(str2, 0, str2.length() - 1));

    }

    /**
     * Check whether it's palindrome. Recursive call using substring() method
     * 
     * @param s string
     * @return whether it's palindrome
     */
    public static boolean isPalindromeUsingSubstring(String s) {

        int len = s.length();

        // base case: if the length == 1
        if (len <= 1) {
            return true;
        } else if (s.charAt(0) != s.charAt(len - 1)) {
            return false;
        } else {

            // substring, so that the scope reduces in each recursion
            return isPalindromeUsingSubstring(s.substring(1, len - 1));
        }
    }

    /**
     * Check whether it's palindrome. Recursive call using indices
     * 
     * @param s string
     * @param h high index
     * @param l low index
     * @return whether it's palindrome
     */
    public static boolean isPalindromeUsingIndices(String s, int h, int l) {

        int len = l - h;

        if (len <= 1)
            return true;
        else if (s.charAt(h) != s.charAt(l))
            return false;
        else
            return isPalindromeUsingIndices(s, h + 1, l - 1);
    }

}