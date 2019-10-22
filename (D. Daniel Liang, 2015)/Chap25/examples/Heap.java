import java.util.ArrayList;
import java.util.Arrays;

/** Heap implementation from Chap 23 */
public class Heap<T extends Comparable<T>> {

    private ArrayList<T> list = new ArrayList<>();

    public Heap() {

    }

    /**
     * @param arr Array of Comparable objects
     */
    public Heap(T[] arr) {
        for (T t : arr) {
            this.add(t);
        }
    }

    /**
     * Add one new node
     */
    public void add(T node) {
        // append to the list as the last element and node
        list.add(node);
        int current = list.size() - 1;

        // while it's not the root yet
        while (current > 0) {
            // minus 1 for (double -> int) convertion to get the same index for left and
            // right children
            int parent = (current - 1) / 2;

            if (list.get(current).compareTo(list.get(parent)) > 0) {
                swap(current, parent);
                current = parent;
            } else
                break;
        }

    }

    /**
     * Remove root node
     */
    public T removeRoot() {
        if (list.size() == 0)
            return null;
        else {
            T root = list.get(0);
            int lastNodeIndex = list.size() - 1;

            // replace the root with the last node
            list.set(0, list.get(lastNodeIndex));
            list.remove(lastNodeIndex);

            // let the root be the current (which is the last node that is just moved to the
            // root)
            int current = 0;

            // while it is not the last node, move downward until it's at the right position
            while (current < list.size()) {

                int leftChild = current * 2 + 1;
                int rightChild = leftChild + 1;

                // this is already the last node, and it is a heap: when there is no node on
                // the left, there shouldn't be any node on the right-hand side.
                if (leftChild >= list.size())
                    break;

                // max among leftChild and rightChild
                int max = leftChild;

                // if there is a rightChild
                if (rightChild < list.size()) {

                    // compare the left and right
                    if (list.get(leftChild).compareTo(list.get(rightChild)) < 0)
                        max = rightChild;
                }

                // compare the max (of left and right) with the current
                if (list.get(current).compareTo(list.get(max)) < 0) {
                    swap(max, current);
                    current = max;
                } else {
                    break; // already a heap or the current is at the right position
                }
            }
            return root;
        }
    }

    private void swap(int indexOne, int indexTwo) {
        var temp = list.get(indexOne);
        list.set(indexOne, list.get(indexTwo));
        list.set(indexTwo, temp);
    }

    @Override
    public String toString() {
        return "Root:" + list.get(0) + "_" + list.toString();
    }

    public int size() {
        return list.size();
    }
}