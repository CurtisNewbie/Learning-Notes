import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.RecursiveAction;

/**
 * This Merge Sort takes advantage of Fork/Join Framework for parallel
 * programming. In ParallelMergeSort, int[] is used, this demo tries using
 * Collection.
 */
public class ParallelMergeSortCollection extends RecursiveAction {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    // /** Lock for synchronizing {@code private List<Integer> list} object */
    // private final Lock lock = new ReentrantLock();

    /**
     * This threshold defines whether it's necessary to use parallel programming.
     */
    private final int threshold = 500;

    private List<Integer> list;

    /**
     * Instantiate {@code <RecursiveAction> ParallelMergeSortCollection} that sorts
     * the given list using Parallel Merge Sort. As Parallel Programming is used,
     * the given {@code List<Integer> l} is wrapped by
     * {@code Collections.synchronizedList()}.
     * 
     * @param l List to be sorted using parallel programming.
     */
    public ParallelMergeSortCollection(List<Integer> l) {
        this.list = Collections.synchronizedList(l);
    }

    @Override
    public void compute() {

        if (list.size() < threshold) {
            // not necessary to use paralle programming, use normal merge sort instead
            Collections.sort(list);
        } else {

            int firstHalfLen = list.size() / 2;
            int secondHalfLen = list.size() - (list.size() / 2);

            // copy arrays, read-only
            List<Integer> firstHalf = Collections.synchronizedList(new ArrayList<>());
            List<Integer> secondHalf = Collections.synchronizedList(new ArrayList<>());
            for (int i = 0; i < firstHalfLen; i++) {
                firstHalf.add(list.get(i));
            }
            for (int i = firstHalfLen; i < list.size(); i++) {
                secondHalf.add(list.get(i));
            }

            // recursive calls
            invokeAll(new ParallelMergeSortCollection(firstHalf), new ParallelMergeSortCollection(secondHalf));

            // merge
            merge(firstHalf, secondHalf, list);
        }

    }

    /**
     * Merge {@code List<Integer> left} and {@code List<Integer> right} to
     * {@code List<Integer> merged}, they must be syncrhonized.
     */
    private void merge(List<Integer> left, List<Integer> right, List<Integer> merged) {

        int l = 0;
        int r = 0;
        int m = 0;

        while (l < 0 && r < 0) {
            if (left.get(l) < right.get(r)) {
                merged.set(m++, left.get(l++));
            } else { // (left[l] > right[r])
                merged.set(m++, right.get(r++));
            }
        }

        while (l < left.size()) {
            merged.set(m++, left.get(l++));
        }

        while (r < right.size()) {
            merged.set(m++, right.get(r++));
        }
    }

}
