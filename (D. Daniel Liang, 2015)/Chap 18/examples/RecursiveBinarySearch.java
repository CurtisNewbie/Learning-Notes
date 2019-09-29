import java.util.*;

public class RecursiveBinarySearch {

    public static void main(String[] args) {

        int size = 20;

        // fill the array randomly
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = i;
        }
        System.out.println(Arrays.toString(arr));

        // find 14
        var index = binarySearch(arr, 14, 0, size - 1);
        System.out.println(index >= 0 ? "Found at: " + index : "Not Found: " + index);
    }

    public static int binarySearch(int[] arr, int k, int l, int h) {

        if (l > h)
            return -1;
        else {

            var m = h + (l - h) / 2;
            var mValue = arr[m];
            if (mValue == k)
                return m;
            else if (mValue < k)
                return binarySearch(arr, k, m + 1, h);
            else
                return binarySearch(arr, k, l, m - 1);
        }
    }
}