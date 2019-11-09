
public class SpecialPrintChar extends PrintChar {

    public SpecialPrintChar(char c, int t) {
        super(c, t);
    }

    @Override
    public void run() {
        for (int i = 0; i < times; i++) {
            print();
            try {

                // 0.01 sec
                Thread.sleep(10);
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " interrupted\n");
                e.printStackTrace();
            }
        }
    }
}