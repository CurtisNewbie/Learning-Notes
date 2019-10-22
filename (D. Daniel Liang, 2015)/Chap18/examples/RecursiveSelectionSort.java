import java.util.Arrays;
import java.util.Random;

public class RecursiveSelectionSort {
    public static void main(String[] args) {

        int size = 100;

        // fill the array randomly
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = new Random().nextInt(100);
        }
        System.out.println(Arrays.toString(arr));

        // sort the array using recursive selection sort
        sort(arr, 0, size - 1);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * Recursive Selection Sort
     * 
     * @param arr array to be sorted
     * @param l   low index
     * @param h   high index
     */
    public static void sort(int[] arr, int l, int h) {
        // not sorted yet
        if (l <= h) {
            int indexOfMin = l;
            int valueOfMin = arr[l];

            for (int i = l + 1; i <= h; i++) {
                var value = arr[i];

                if (value < valueOfMin) {
                    indexOfMin = i;
                    valueOfMin = value;
                }
            }

            // swap
            if (indexOfMin != l) {
                int temp = arr[l];
                arr[l] = valueOfMin;
                arr[indexOfMin] = temp;
            }

            // another recursion (except that the first sorted element is excluded)
            sort(arr, l + 1, h);
        }
    }
}