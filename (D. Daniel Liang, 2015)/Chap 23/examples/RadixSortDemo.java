import java.util.*;

public class RadixSortDemo {

    public static void main(String[] args) {
        var arr = Util.getDemoArr();
        radixSort(arr);
        System.out.println(Util.isSorted(arr));
        System.out.println(Arrays.toString(arr));

        var twoDigitArr = new Integer[] { 123, 56, 86, 37, 5, 96, 8 };
        radixSort(twoDigitArr);
        System.out.println(Util.isSorted(twoDigitArr));
        System.out.println(Arrays.toString(twoDigitArr));

    }

    public static void radixSort(Integer[] arr) {
        // 0 - 9
        final int NUM_OF_BUCKETS = 10;

        // find number of digits (thus how many times the bucket sort is needed)
        int max = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > arr[max])
                max = i;
        }
        int digits = arr[max].toString().length();

        // compare all the digits and put the elements to the right busket
        for (int i = 0; i < digits; i++) {
            int placeValue = (int) Math.pow(10, i);

            // set up buckets
            ArrayList<ArrayList<Integer>> buckets = new ArrayList<>(NUM_OF_BUCKETS);
            for (int j = 0; j < NUM_OF_BUCKETS; j++)
                buckets.add(new ArrayList<Integer>());

            // load elements into buckets
            for (int k = 0; k < arr.length; k++) {
                int extractedDigit = (arr[k] / placeValue) % 10;
                buckets.get(extractedDigit).add(arr[k]);
            }

            int arrIndex = 0;
            // sort individual bucket
            for (int k = 0; k < buckets.size(); k++) {
                digitInsertionSort(buckets.get(k), placeValue);

                // return elements of each bucket back to arr
                for (int j : buckets.get(k)) {
                    arr[arrIndex] = j;
                    arrIndex++;
                }
            }
        }
    }

    public static void digitInsertionSort(List<Integer> arr, int placeValue) {
        int len = arr.size();

        for (int l = 1; l < len; l++) {
            int currentElement = arr.get(l);

            int prev = l - 1;
            //
            while (prev >= 0 && (currentElement / placeValue) % 10 < (arr.get(prev) / placeValue) % 10) {

                // move prev forward
                arr.set(prev + 1, arr.get(prev));

                prev--;
            }
            arr.set(prev + 1, currentElement);
        }

    }
}