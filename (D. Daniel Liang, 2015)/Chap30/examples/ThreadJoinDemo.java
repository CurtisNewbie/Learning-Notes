public class ThreadJoinDemo {
    public static void main(String[] args) {
        // Example of using Thread.join() that forces one waiting until the previous one
        // is finished

        Thread t3 = new Thread() {

            @Override
            public void run() {
                for (int i = 0; i < 10; i++)
                    System.out.println("T3 Running");
            }
        };

        Thread t4 = new Thread() {

            @Override
            public void run() {
                for (int i = 0; i < 10; i++)
                    System.out.println("T4 Running");
            }
        };
        t3.start();
        try {
            // start next thread (t4) until t3 dies
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t4.start();
    }
}