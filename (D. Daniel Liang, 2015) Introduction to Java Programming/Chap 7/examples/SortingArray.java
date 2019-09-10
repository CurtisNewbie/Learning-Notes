import java.util.*;

import javax.swing.text.DefaultStyledDocument.ElementSpec;

public class SortingArray {
    public static void main(String[] args) {
        int[] arr = { 3, 1, 3, 2, 5, 4, 7, 1, 2, 4, 9 };
        // int[] arr = { 3, 1, 3, 2, 5, 9 };

        // selectionSort(arr);
        System.out.println(Arrays.toString(arr));
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
            int part = partition(arr, left, right);
            // System.out.println(Arrays.toString(arr) + " part:" + part + "From" + left + "
            // to " + right);
            quickSort(arr, part + 1, right);
            quickSort(arr, left, part - 1);
        }
    }

    /**
     * Parititioning means the values on the left are less than the pivot and the
     * values on the right are greater than the pivot so that the array is partially
     * sorted.
     */
    public static int partition(int[] arr, int low, int high) {
        // this is better to choose the high as the pivot, as the arr may be sorted.
        int pivot = arr[high];

        // ignores the pivot at first, and then swaps it with the ending pointer
        // (returned value)
        int pivotIndex = high;

        // the partition index (where the elements are seperated based on the value of
        // pivot)
        int partitionIndex = low;

        for (int x = low; x < pivotIndex; x++) {
            if (arr[x] <= pivot) {
                // push the element to the left, and move forward the partition index
                swap(arr, x, partitionIndex);
                partitionIndex++;
            }
        }
        swap(arr, partitionIndex, pivotIndex);
        return partitionIndex;
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