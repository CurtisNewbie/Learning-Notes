
/**
 * Runnable object responsible for printing given char at for the speicified
 * times
 */
public class PrintChar implements Runnable {

    protected char c;
    protected int times;

    public PrintChar(char c, int t) {
        this.c = c;
        this.times = t;
    }

    protected void print() {
        System.out.println(c);
    }

    @Override
    public void run() {
        if (times > 0) {
            for (int i = 0; i < times; i++) {
                print();
            }
        }
    }
}