import java.io.*;

public class RandomAccessFileDemo {
    public static void main(String[] args) {

        // create if not exist
        try (RandomAccessFile raf = new RandomAccessFile("randomAccessFile.dat", "rw");) {

            // clear the file by setting the length to 0
            raf.setLength(0);

            // wrtie testing data
            for (int i = 0; i < 200; i++)
                raf.writeInt(i);

            // 200 int * 4 bytes for each == 800 bytes in total
            System.out.println("Currect file length (in byte) is " + raf.length());

            // move pointer to the begining
            raf.seek(0); // index: 0

            // read an int and move pointer forward
            System.out.println("The first num (index: 0) is " + raf.readInt()); // index: 4

            // second num, 4 bytes for every int.
            raf.seek(4); // index: 4
            System.out.println("The second num (index: 4) is " + raf.readInt()); // index: 8

            // tenth num
            raf.seek(4 * 9); // index: 36 (tenth is because 4 * 9 + 4 == 40, which is the 10th integer)
            System.out.println("The tenth num (index: 36) is " + raf.readInt()); // index: 40

            // modify the eleventh num
            System.out.println("Write 555 to the eleventh num (index: 40)");
            raf.writeInt(555);

            // append a new number
            raf.seek(raf.length());
            raf.writeInt(999); // index: length() += 4

            System.out.println("The new length (in bytes) is " + raf.length() + " after adding one integer");

            // check the eleventh number
            raf.seek(4 * 10);
            System.out.println("The eleventh number (index: 40) is " + raf.readInt());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}