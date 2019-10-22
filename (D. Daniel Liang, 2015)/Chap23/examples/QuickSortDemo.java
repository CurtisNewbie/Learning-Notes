import java.util.Arrays;

public class QuickSortDemo {

    public static void main(String[] args) {

        var arr = Util.getDemoArr();
        System.out.println(Arrays.toString(arr));

        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
        System.out.println(Util.isSorted(arr));

    }

    public static void quickSort(Integer[] arr, int leftIndex, int rightIndex) {
        // not sorted yet
        if (leftIndex < rightIndex) {

            // return the correct position of the selected pivot
            int pivotIndex = partition(arr, leftIndex, rightIndex);

            // recursively sort the subarray
            quickSort(arr, leftIndex, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, rightIndex);
        }

    }

    public static int partition(Integer[] arr, int leftIndex, int rightIndex) {

        // it can be any element, tho it may affect the performance
        int pivotIndex = leftIndex;
        int pivot = arr[pivotIndex];

        /*
         * Sort the array from leftIndex +1 to rightIndex so that the elements that are
         * less than the pivot are on the left-hand side and the elements that are
         * greater than the pivot are on the right-hand side.
         */
        int l = leftIndex + 1;
        int h = rightIndex;

        while (l < h) {

            // so long as it's less than or equal to the pivot, continuing searching from
            // left
            while (l <= h && arr[l] <= pivot)
                l++;

            // so long as it's greater than the pivot, continuing searching from
            // right
            while (l <= h && arr[h] > pivot)
                h--;

            // if l < h, means that two elements at the incorrect positions on both
            // sides are found, swap them
            if (l < h) {
                var t = arr[l];
                arr[l] = arr[h];
                arr[h] = t;
            }
        }

        // System.out.println("[" + leftIndex + "-" + rightIndex + "] l:" + l + " h:" +
        // h + " :::" + Arrays.toString(arr));

        /*
         * l can be greater than h, as it increments by 1 when it is equal to h. Which
         * is the reason why the l is no longer the right position of the pivot. Both
         * the l and h increments by one when they are supposed to stop. So, the correct
         * position for h is h + 1, and for l is l - 1.
         */
        while (h > leftIndex && arr[h] >= pivot) {
            h--;
        }

        /*
         * 
         * as the elements on the left are less than or equal to the pivot, the pivot
         * may be greater than these elements thus it is needed to be swaped to the last
         * element that is found to be greater than or equal to the pivot.
         */
        if (pivot > arr[h]) {

            // System.out.println("[" + leftIndex + "-" + rightIndex + "] Swaped between " +
            // leftIndex + " and " + h
            // + " Pivot at :" + h + " " + Arrays.toString(arr));

            arr[leftIndex] = arr[h];
            arr[h] = pivot;
            return h;

            /*
             * if the pivot is not greater than this h (the last element that is found to be
             * greater than or equal to the pivot.) It means that the pivot is already at
             * the right position, i.e., at least all the elements on the right are greater
             * than or equal to the pivot.
             */
        } else {

            // System.out.println(
            // "[" + leftIndex + "-" + rightIndex + "] Pivot at :" + leftIndex + " " +
            // Arrays.toString(arr));

            return leftIndex;
        }
    }
}