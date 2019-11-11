import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.locks.Condition;

public class ThreadCooperation {

    public static SpecialBankAccount account = new SpecialBankAccount();

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.execute(new WithdrawTask());
        executor.execute(new DepositTask());
        executor.shutdown();
    }

    /** Runnable task that deposits 50 from the account for every second. */
    public static class DepositTask implements Runnable {

        @Override
        public void run() {
            try {
                int count = 20;
                while (count-- > 0) {
                    account.add(50);
                    Thread.sleep(2000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /** Runnable task that withdraws 50 from the account for every second. */
    public static class WithdrawTask implements Runnable {
        @Override
        public void run() {

            int count = 5;
            while (count-- > 0) {
                account.minus(200);
            }
        }
    }
}

/**
 * With Condition of Lock stored as object variable and modified methods for
 * using the Condition
 */
class SpecialBankAccount extends BankAccount {

    private Condition newDeposit;

    public SpecialBankAccount() {
        super();
        newDeposit = lock.newCondition();
    }

    @Override
    public void add(double d) {
        lock.lock();

        try {
            deposit += d;
            newDeposit.signalAll();
        } finally {
            lock.unlock();
        }
    }

    /**
     * With the use of Condition object, it waits for signal (of money adding to the
     * acount) when there is not enough money to withdraw.
     */
    @Override
    public void minus(double d) {
        lock.lock();
        try {
            while (deposit < d) {
                System.out.println("Not enough money, waiting for enough deposit. " + (deposit - d));
                newDeposit.await();
            }
            System.out.println("Money deposited!, now " + showDeposit() + " Withdrawing " + d);

            deposit -= d;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
