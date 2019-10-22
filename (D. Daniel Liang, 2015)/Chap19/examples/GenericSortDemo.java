import java.util.Arrays;

public class GenericSortDemo {
    public static void main(String[] args) {

        Integer[] numbers = new Integer[] { 1, 3, 8, 6, 7, 4, 5, 6 };
        genericSort(numbers);

        String[] names = new String[] { "Curtis", "Sharon", "Apple", "Orange", "Banana" };
        genericSort(names);
        System.out.println(Arrays.toString(numbers));
        System.out.println(Arrays.toString(names));
    }

    // Generic Selection Sort
    public static <T extends Comparable<T>> void genericSort(T[] arr) {

        int minIndex = 0;
        T minValue;

        // from left to right
        int rightMost = arr.length - 1;
        for (int l = 0; l < rightMost; l++) {
            minIndex = l;
            minValue = arr[l];

            for (int r = l + 1; r < arr.length; r++) {
                T currentValue = arr[r];
                if (currentValue.compareTo(minValue) < 0) {
                    minValue = currentValue;
                    minIndex = r;
                }
            }

            if (minIndex != l) {
                // swap
                T temp = arr[l];
                arr[l] = minValue;
                arr[minIndex] = temp;
            }
        }
    }
}
