
public class HashingPrimitive {

    /**
     * Hashing byte type. Cast byte to int
     * 
     * @param b byte
     * @return hash code in int
     */
    public static int hashToInt(byte b) {
        return (int) b;
    }

    /**
     * Hashing short type. Cast short to int
     * 
     * @param s short
     * @return hash code in int
     */
    public static int hashToInt(short s) {
        return (int) s;
    }

    /**
     * Hashing int type. Return the same value
     * 
     * @param int
     * @return the same value in int
     */
    public static int hashToInt(int i) {
        return i;
    }

    /**
     * Hashing char type. Cast char to int
     * 
     * @param c char
     * @return hash code in int
     */
    public static int hashToInt(char c) {
        return (int) c;
    }

    /**
     * Hashing float type. Convert float to int.
     * 
     * @param f float
     * @return hash code in int
     */
    public static int hashToFloat(float f) {
        return Float.floatToIntBits(f);
    }

    /**
     * Hashing long type. 1. Right shift 32 bits, retain only the high-bits. 2. Use
     * XOR operator to compare the original 64 bits long to the right shifted 32
     * bits. 3. Retain only the first 32 bits (low-bits) and return it as int.
     * 
     * @param l long
     * @return hash code in int
     */
    public static int hashToInt(long l) {
        return (int) (l ^ (l >> 32));
    }

    /**
     * Hash double type. 1. Convert double to long. 2. Right shift 32 bits, retain
     * only the high-bits. 3. Use XOR operator to compare the original 64 bits long
     * to the right shifted 32 bits. 4. Retain only the first 32 bits (low-bits) and
     * return it as int.
     * 
     * @param d double
     * @return hash code in int
     */
    public static int hashToInt(double d) {
        long l = Double.doubleToLongBits(d);
        return (int) (l ^ (l >> 32));
    }

    /**
     * Using the built-in polynomial hash code implementation in String class.
     */
    public static int hashToInt(String s) {
        return (int) s.hashCode();
    }

}