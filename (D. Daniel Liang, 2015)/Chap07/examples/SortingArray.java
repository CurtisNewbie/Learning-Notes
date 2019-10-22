import java.util.*;

import javax.swing.text.DefaultStyledDocument.ElementSpec;

public class SortingArray {
    public static void main(String[] args) {
        int[] arr = { 3, 1, 3, 2, 5, 4, 7, 1, 2, 4, 9 };
        System.out.println("Original: " + Arrays.toString(arr));

        // selectionSort(arr);
        // quickSort(arr, 0, arr.length - 1);
        mergeSort(arr, 0, arr.length - 1);

        // built in sorting: Arrays.sort() adopts Dual-Pivot Quicksort
        // Arrays.sort(arr);

        // built in sorting: Arrays.parallelSort() adopts multi-threading (may cause
        // overhead )
        // Arrays.parallelSort(arr);

        System.out.println("Sorted: " + Arrays.toString(arr));

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
     * Split to two parts if possible, merge them when the recursion ends.
     * 
     * @param arr
     * @param left
     * @param right
     */
    public static void mergeSort(int[] arr, int left, int right) {

        if (left < right) {
            // split
            int mid = left + (right - left) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);

            merge(arr, left, mid, right);
        }
    }

    /**
     * merge the two parts.
     * 
     * @param arr
     * @param left
     * @param mid
     * @param right
     */
    public static void merge(int arr[], int left, int mid, int right) {

        // merge two arrays using the temp arrays.
        int leftLen = mid - left + 1; // mid inclusive
        int rightLen = right - mid; // mid exclusive

        int[] leftArr = new int[leftLen];
        int[] rightArr = new int[rightLen];

        // load elements into the temp arrays
        for (int x = 0; x < leftLen; x++) {
            leftArr[x] = arr[x + left];
        }

        for (int x = 0; x < rightLen; x++) {
            rightArr[x] = arr[x + mid + 1];
        }

        // compare and merge, leftArr pointer and rightArr pointer
        int l = 0;
        int r = 0;

        // pointer of arr[]
        int m = left;

        while (l < leftLen && r < rightLen) {
            int leftValue = leftArr[l];
            int rightValue = rightArr[r];

            if (leftValue < rightValue) {
                // put the left element in the arr[mergedArrPointer] first
                arr[m] = leftValue;
                l++;
                m++;
            } else if (leftValue > rightValue) {
                // put the right element in the arr[mergedArrPointer] first
                arr[m] = rightValue;
                r++;
                m++;
            } else {
                // doesn't matter which first
                arr[m] = leftValue;
                m++;
                arr[m] = rightValue;
                m++;

                l++;
                r++;
            }
        }

        // the leftArr or rightArr may be larger than the other, there may be remaining.
        while (l < leftLen) {
            arr[m] = leftArr[l];
            l++;
            m++;
        }

        while (r < rightLen) {
            arr[m] = rightArr[r];
            r++;
            m++;
        }
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