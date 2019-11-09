
public class TaskThreadDemo {

    public static void main(String[] args) {
        // tasks / Runnable objects
        Runnable printC = new PrintChar('C', 100);
        Runnable printB = new PrintChar('B', 50);

        // threads for running tasks Runnable objects
        Thread t1 = new Thread(printC);
        Thread t2 = new Thread(printB);

        t1.start();
        t2.start();
    }
}
