import java.util.*;

public class RandomShuffle {

    public static void main(String[] args) {

        int[] arr = new int[] { 1, 2, 3, 4, 5, 6, 7 };
        System.out.println("Original Array is " + Arrays.toString(arr));

        // shuffle the array randomly.
        int len = arr.length;
        var r = new Random();
        for (int x = 0; x < len; x++) {

            // for each [x], randomly generate an index [y] and swap them
            int y = r.nextInt(len - 1);
            swap(arr, x, y);
        }

        System.out.println("Shuffled Array is " + Arrays.toString(arr));

    }

    public static void swap(int[] arr, int x, int y) {
        int xValue = arr[x];
        arr[x] = arr[y];
        arr[y] = xValue;
    }
}