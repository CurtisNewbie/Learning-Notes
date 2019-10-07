import java.util.*;

public class BucketSortDemo {

    public static void main(String[] args) {
        var demoArr = Util.getDemoArr();
        // var demoList = new ArrayList<Integer>();
        // for (int t : demoArr) {
        // demoList.add(t);
        // }
        // insertionSort(demoList);
        bucketSort(demoArr);
        System.out.println(Util.isSorted(demoArr));
        System.out.println(Arrays.toString(demoArr));
    }

    public static void bucketSort(Integer[] arr) {

        // size each bucket
        int bucketSize = 3;

        // find min an mix
        int len = arr.length;
        int max = arr[0];
        int min = arr[0];
        for (int i = 1; i < len; i++) {
            int current = arr[i];
            if (current > max)
                max = current;

            if (current < min)
                min = current;
        }

        // create buckets, plus 1 is because the index of buckets starts from 0.
        int numOfBuckets = (max - min) / bucketSize + 1;
        List<List<Integer>> buckets = new ArrayList<>(numOfBuckets);

        // fill each bucket with an another List
        for (int i = 0; i < numOfBuckets; i++)
            buckets.add(new ArrayList<Integer>());

        // put elements into associated buckets
        for (int i = 0; i < len; i++) {
            buckets.get((arr[i] - min) / bucketSize).add(arr[i]);
        }

        // sort each bucket individually using insertion sort, and return the sorted
        // elements
        int sortedArrayIndex = 0;
        for (int i = 0; i < numOfBuckets; i++) {
            List<Integer> eachBucket = buckets.get(i);
            insertionSort(eachBucket);

            // return the elements from each bucket
            for (int n : eachBucket) {
                arr[sortedArrayIndex] = n;
                sortedArrayIndex++;
            }
        }
    }

    public static void insertionSort(List<Integer> arr) {
        int len = arr.size();

        for (int l = 1; l < len; l++) {
            int currentElement = arr.get(l);

            int prev = l - 1;
            while (prev >= 0 && currentElement < arr.get(prev)) {

                // move prev forward
                arr.set(prev + 1, arr.get(prev));

                prev--;
            }
            arr.set(prev + 1, currentElement);
        }

    }
}