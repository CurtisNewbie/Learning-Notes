public class HexdecimalToDecimal {

    public static void main(String[] args) {

        String hexadicimals = "af71";
        long decimals;

        if (hexadicimals != null && hexadicimals.matches("[a-fA-F0-9]{1,}")) {

            System.out.println("Hexdicimals is: \"" + hexadicimals + "\"");

            decimals = hexToDecimal(hexadicimals.toUpperCase());
            System.out.println("Converted into deicimals: \"" + decimals + "\"");

        } else {
            System.out.println(
                    "Hexadecimals string must not be null, it must have at least one char, and its char must be [0-9a-zA-Z]");
        }
    }

    /**
     * Convert hexadecimals to decimals.<br>
     * <br>
     * Hexadecimals are of a base of 16, while decimals have a base of 10. To
     * convert hexadecimals to decimals, simply calculating the power of 16.
     * 
     * @param hex hexadecimals as a string
     * @return converted decimals
     * 
     */
    public static long hexToDecimal(String hex) {

        int len = hex.length();

        long decimal = 0L;
        for (int x = 0; x < len; x++) {
            int decValue = hexCharToDecimal(hex.charAt(len - 1 - x));
            long powValue = (long) Math.pow(16, x);
            decimal += (decValue * powValue);
        }
        return decimal;
    }

    /**
     * Convert the hexadecimal char to decimals
     * 
     * @param hexChar hexadecimal character
     * @return decimal value
     */
    public static int hexCharToDecimal(char hexChar) {
        if (Character.isDigit(hexChar)) {
            return Character.getNumericValue(hexChar);
        } else {
            return 10 + hexChar - 'A';
        }
    }

    /**
     * decimal to hexadecimal
     * 
     * @param n decimal value
     * @return hexadeciaml as a string
     */
    public static String convertToHexadecimal(int n) {
        if (n <= 9) {
            return "" + n;
        } else {
            int power = n / 16;
            int remaining = n % 16;
            if (remaining > 9) {
                char[] hexChar = { 'A', 'B', 'C', 'D', 'E' };
                int gap = 16 - remaining;
                return power + "" + hexChar[gap];
            } else {
                return power + "" + remaining;
            }
        }
    }

}