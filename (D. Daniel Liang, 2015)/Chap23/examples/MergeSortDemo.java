import java.util.*;

public class MergeSortDemo {

    public static void main(String[] args) {

        var arr = Util.getDemoArr();
        mergeSort(arr);
        System.out.println(Util.isSorted(arr));
        System.out.println(Arrays.toString(arr));

    }

    public static void mergeSort(Integer[] arr) {
        int len = arr.length;
        int mid = len / 2;

        if (len > 1) {
            // divide
            Integer[] left = new Integer[mid];
            Integer[] right = new Integer[len - mid];
            System.arraycopy(arr, 0, left, 0, mid);
            System.arraycopy(arr, mid, right, 0, len - mid);

            // mergeSort() recursively
            mergeSort(left);
            mergeSort(right);

            // conquer and merge
            merge(left, right, arr);
        }
    }

    public static void merge(Integer[] left, Integer[] right, Integer[] merArr) {

        int leftIndex = 0;
        int rightIndex = 0;
        int merIndex = 0;

        while (leftIndex < left.length && rightIndex < right.length) {

            var l = left[leftIndex];
            var r = right[rightIndex];

            if (l < r) {
                merArr[merIndex++] = l;
                leftIndex++;
            } else { // r > l || r == l
                merArr[merIndex++] = r;
                rightIndex++;
            }
        }

        while (leftIndex < left.length)
            merArr[merIndex++] = left[leftIndex++];
        while (rightIndex < right.length)
            merArr[merIndex++] = right[rightIndex++];
    }
}