
public class Util {

    public static <T extends Comparable<T>> boolean isSorted(T[] arr) {
        T current = arr[0];
        int len = arr.length;
        for (int i = 1; i < len; i++) {
            if (current.compareTo(arr[i]) > 0)
                return false;
            current = arr[i];
        }
        return true;
    }

    public static Integer[] getDemoArr() {
        return new Integer[] { 1, 8, 5, 6, 7, 5, 6, 6, 5, 3, 7, 3, 6, 2, 9, 0, 8, 1 };
    }
}