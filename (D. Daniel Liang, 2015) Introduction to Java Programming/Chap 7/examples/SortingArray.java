import java.util.*;

public class SortingArray {
    public static void main(String[] args) {
        int[] arr = { 3, 1, 3, 2, 5, 4, 7, 1, 2, 4, 9 };
        // selectionSort(arr);
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * Selection sort
     */
    public static void selectionSort(int[] arr) {

        // from left to right and find smallest
        int len = arr.length;

        for (int x = 0; x < len; x++) {
            int min = arr[x];
            int minIndex = x;
            for (int y = x + 1; y < len; y++) {
                if (arr[y] < min) {
                    min = arr[y];
                    minIndex = y;
                }
            }

            // after iterating through the whole array, the min should be found.
            if (minIndex != x) {
                swap(arr, x, minIndex);
            } else {
                // no need to swap
            }
        }
    }

    public static void quickSort(int[] arr, int left, int right) {

        if (left < right) {
            // partially sorted
            int partition = partition(arr, left, right);
            quickSort(arr, partition + 1, right);
            quickSort(arr, left, partition - 1);
        }
    }

    public static int partition(int[] arr, int low, int high) {
        int pivot = arr[low];
        int left = low;
        int right = high;

        // move the values that are less than pivot to the left, and the values that are
        // greateer than pivot to the right
        while (left < right) {

            // left in the right position that it is less than pivot
            while (arr[left] < pivot && left < right) {
                left++;
            }

            // the current left is greater than pivot, tries to find right
            while (arr[right] > pivot && left < right) {
                right--;
            }

            if (left < right) {
                swap(arr, left, right);
            }

        }
        return left;
    }

    /**
     * Swap
     * 
     * @param arr
     * @param a
     * @param b
     */
    public static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}