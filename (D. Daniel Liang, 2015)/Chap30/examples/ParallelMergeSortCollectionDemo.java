import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class ParallelMergeSortCollectionDemo {
    public static void main(String[] args) {
        int len = 200;
        List<Integer> demoList = createDemoArray(len);
        for (int i = 0; i < len; i++) {
            System.out.print(demoList.get(i) + " ");
        }
        System.out.println();

        long start = System.currentTimeMillis();
        // create ForkJoinPool to execute ForkJoinTask with 4 processors
        ForkJoinPool pool = new ForkJoinPool(4);
        pool.invoke(new ParallelMergeSortCollection(demoList));
        pool.shutdown();
        long end = System.currentTimeMillis();
        System.out.println("\n" + (end - start) + "mili seconds");

        while (!pool.isTerminated())
            ;
        System.out.println();
        for (int i = 0; i < len; i++) {
            System.out.print(demoList.get(i) + " ");
        }
    }

    public static List<Integer> createDemoArray(int size) {
        Random r = new Random();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(r.nextInt(100));
        }
        return list;
    }
}