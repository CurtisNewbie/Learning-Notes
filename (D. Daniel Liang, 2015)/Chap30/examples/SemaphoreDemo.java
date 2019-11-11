import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Semaphore;

public class SemaphoreDemo {

    private Semaphore semaphore;
    private int balance;

    public SemaphoreDemo() {
        semaphore = new Semaphore(2);
        balance = 0;
    }

    public synchronized void update(int n) {
        try {

            // acquire permit
            semaphore.acquire();
            balance = n;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }

    public int getBalance() {
        return balance;
    }

    public static void main(String[] args) {

        SemaphoreDemo demo = new SemaphoreDemo();

        ExecutorService executor = Executors.newFixedThreadPool(5);
        executor.execute(new Runnable() {
            @Override
            public void run() {
                demo.update(50);
            }
        });
        executor.execute(new Runnable() {
            @Override
            public void run() {
                demo.update(100);
            }
        });
        executor.execute(new Runnable() {
            @Override
            public void run() {
                demo.update(1000);
            }
        });
        executor.execute(new Runnable() {
            @Override
            public void run() {
                demo.update(2000);
            }
        });
        executor.execute(new Runnable() {
            @Override
            public void run() {
                demo.update(3000);
            }
        });
        executor.shutdown();

        while (!executor.isTerminated())
            ;
        System.out.println(demo.getBalance());

    }
}