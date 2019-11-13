import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class ParallelMergeSortDemo {
    public static void main(String[] args) {
        int[] demoList = createDemoArray(7000000);
        for (int i = 0; i < 500; i++) {
            System.out.print(demoList[i] + " ");
        }
        System.out.println();

        long start = System.currentTimeMillis();
        // create ForkJoinPool to execute ForkJoinTask with 4 processors
        ForkJoinPool pool = new ForkJoinPool(4);
        pool.invoke(new ParallelMergeSort(demoList));
        pool.shutdown();
        long end = System.currentTimeMillis();
        System.out.println("\n" + (end - start) + "mili seconds");

        while (!pool.isTerminated())
            ;
        System.out.println();
        for (int i = 0; i < 500; i++) {
            System.out.print(demoList[i] + " ");
        }
    }

    public static int[] createDemoArray(int size) {
        Random r = new Random();
        int[] list = new int[size];
        for (int i = 0; i < size; i++) {
            list[i] = r.nextInt(1000000);
        }
        return list;
    }
}