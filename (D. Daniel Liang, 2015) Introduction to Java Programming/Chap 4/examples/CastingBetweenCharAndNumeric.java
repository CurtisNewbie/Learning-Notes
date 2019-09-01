public class CastingBetweenCharAndNumeric {

    public static void main(String[] args) {
        char ch = (char) 0XAB0041;
        System.out.println("(char) 0XAB0041::: " + ch);

        ch = (char) 65;
        System.out.println("(char) 65::: " + ch);

        int n = (int) 'c';
        System.out.println("(int) c::: " + n);

        byte b = 'a';
        System.out.println("(byte) a::: " + b);

        /*
         * this doesn't work because the size of the data is not within the size of a
         * byte. (a byte = 8 bit and can represent the maximum value of 256.)
         */
        // byte d = '\uFFF4';
        // System.out.println("(byte)'\uFFF4'::: " + d);

        // instead, use the explicit casting before assignment
        byte e = (byte) '\uFFF4';
        int size = '\uFFF4';
        System.out.println("Explicit Casting, (byte)'\uFFF4'::: " + e);
        System.out.println("Size of '\uFFF4'::: " + size);

    }
}