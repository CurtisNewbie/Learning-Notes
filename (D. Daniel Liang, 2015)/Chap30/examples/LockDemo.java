import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LockDemo {
    public static void main(String[] args) {
        // account
        BankAccount account = new BankAccount();

        // Thread pool
        ExecutorService executor = Executors.newFixedThreadPool(2);

        // Runnable task to adding (5 * 50.0)
        executor.execute(new Runnable() {

            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    account.add(50.0);
                }
            }
        });

        // Runnable task to adding (5 * 50.0)
        executor.execute(new Runnable() {

            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    account.add(50.0);
                }
            }
        });

        // Runnable task to minus (5 * 50.0)
        executor.execute(new Runnable() {

            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    account.minus(50.0);
                }
            }
        });

        // Runnable task to minus (5 * 50.0)
        executor.execute(new Runnable() {

            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    account.minus(50.0);
                }
            }
        });

        executor.shutdown();

        while (!executor.isTerminated()) {
            // wait until it finished
        }
        System.out.println(account.showDeposit());

    }
}

class BankAccount {

    protected final Lock lock;
    protected double deposit;

    public BankAccount() {
        lock = new ReentrantLock();
        deposit = 0.0;
    }

    public void add(double d) {
        lock.lock();

        try {
            deposit += d;
        } finally {
            lock.unlock();
        }
    }

    public void minus(double d) {
        lock.lock();
        try {
            deposit -= d;
        } finally {
            lock.unlock();
        }
    }

    public double showDeposit() {
        return deposit;
    }
}