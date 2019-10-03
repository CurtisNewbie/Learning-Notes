import java.util.Comparator;

public class ComparatorDemo {

    public static void main(String[] args) {

        // make a custom-made Comparator that compares the length
        String shortStr = "Apple";
        String longStr = "BBBBBBBBB";

        System.out.println("Compare \"Apple\" and \"BBBBBBBBB\" using CustomComparator: "
                + new CustomComparator().compare(shortStr, longStr));
    }
}

class CustomComparator implements Comparator<String> {

    /**
     * Compare length of two strings
     */
    @Override
    public int compare(String str1, String str2) {

        int result;
        int lenStr1 = str1.length();
        int lenStr2 = str2.length();

        if (lenStr1 > lenStr2)
            result = 1;
        else if (lenStr1 == lenStr2)
            result = 0;
        else
            result = -1;
        return result;
    }

}