
public class ThreadYieldSleepDemo {
    public static void main(String[] args) {

        // sleep method is used in class SpecialPrintChar
        Runnable printC = new SpecialPrintChar('C', 100);
        Runnable printB = new SpecialPrintChar('B', 50);

        Thread t1 = new Thread(printC);
        Thread t2 = new Thread(printB);

        t1.start();

        // as t1 and t2 are of same priority, t1 will resume till it terminates
        Thread.yield();
        t2.start();

    }
}
