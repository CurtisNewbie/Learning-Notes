import java.util.concurrent.*;

public class ThreadPoolDemo {

    public static void main(String[] args) {

        // Create a thread pool with fixed amount of threads
        ExecutorService pool = Executors.newFixedThreadPool(3);

        // add Runnable tasks to the pool, which are then executed by 3 threads
        pool.execute(new PrintChar('a', 100));
        pool.execute(new PrintChar('b', 100));
        pool.execute(new PrintChar('c', 100));

        // shut down the pool or executor, but existing tasks will continue until
        // finished
        pool.shutdown();
    }
}
