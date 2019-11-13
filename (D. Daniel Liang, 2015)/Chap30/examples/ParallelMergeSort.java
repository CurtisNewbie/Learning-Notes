
import java.util.Arrays;
import java.util.concurrent.RecursiveAction;

/**
 * This Merge Sort takes advantage of Fork/Join Framework for parallel
 * programming.
 */
public class ParallelMergeSort extends RecursiveAction {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * This threshold defines whether it's necessary to use parallel programming.
     */
    private final int threshold = 500;

    private int[] list;

    public ParallelMergeSort(int[] l) {
        list = l;
    }

    @Override
    public void compute() {

        if (list.length < threshold) {
            // not necessary to use paralle programming, use normal merge sort instead
            Arrays.sort(list);
        } else {

            int firstHalfLen = list.length / 2;
            int secondHalfLen = list.length - (list.length / 2);

            // divide
            int[] firstHalf = new int[firstHalfLen];
            int[] secondHalf = new int[secondHalfLen];
            System.arraycopy(list, 0, firstHalf, 0, firstHalfLen);
            System.arraycopy(list, 0, secondHalf, 0, secondHalfLen);

            // recursive calls
            invokeAll(new ParallelMergeSort(firstHalf), new ParallelMergeSort(secondHalf));

            // merge
            merge(firstHalf, secondHalf, list);
        }

    }

    private void merge(int[] left, int[] right, int[] merged) {
        int l = 0;
        int r = 0;
        int m = 0;

        while (l < 0 && r < 0) {
            if (left[l] < (right[r])) {
                merged[m++] = left[l++];
            } else { // (left[l] > right[r])
                merged[m++] = right[r++];
            }
        }

        while (l < left.length) {
            merged[m++] = left[l++];
        }

        while (r < right.length) {
            merged[m++] = right[r++];
        }
    }

}
