import java.util.concurrent.*;

public class SynchronizedDemo {

    public static void main(String[] args) {

        ExecutorService threadPool = Executors.newFixedThreadPool(4);
        // Runnable task to adding (5 * 50.0)
        threadPool.execute(new Runnable() {

            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    Account.add(50.0);
                }
            }
        });

        // Runnable task to adding (5 * 50.0)
        threadPool.execute(new Runnable() {

            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    Account.add(50.0);
                }
            }
        });

        // Runnable task to minus (5 * 50.0)
        threadPool.execute(new Runnable() {

            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    Account.minus(50.0);
                }
            }
        });

        // Runnable task to minus (5 * 50.0)
        threadPool.execute(new Runnable() {

            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    Account.minus(50.0);
                }
            }
        });

        threadPool.shutdown();

        // if there is no racing condition, it should be 0. However, without
        // synchronized keyword or using other lock mechanism, it is only a problem of
        // probability.
        while (!threadPool.isTerminated()) {
            // wait until it finished
        }
        System.out.println(Account.showMoney());
    }

}

// Using synchronised keyword
class Account {

    private static double money = 0.0;

    public static synchronized void add(double d) {
        money += d;
    }

    public static synchronized void minus(double d) {
        money -= d;
    }

    public static double showMoney() {
        return money;
    }
}
