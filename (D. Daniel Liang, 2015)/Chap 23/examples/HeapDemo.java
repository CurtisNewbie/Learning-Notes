import java.util.ArrayList;

public class HeapDemo {
    public static void main(String[] args) {
        Heap<Integer> heap = new Heap<>(new Integer[] { 14, 33, 30, 17, 59, 32, 39, 44, 13, 22, 29, 62, 42 });
        System.out.println(heap.toString());
        heap.removeRoot();

        ArrayList<Integer> sortedArr = new ArrayList<>();
        // heap sort
        while (heap.size() > 0) {
            sortedArr.add(heap.removeRoot());
        }
        System.out.println(sortedArr);
    }
}