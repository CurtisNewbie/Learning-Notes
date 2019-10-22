import java.io.*;
import java.util.*;

public class BufferedIOStreamsDemo {

    public static void main(String[] args) {

        // the buffer size can be specified
        try (BufferedOutputStream bufferedOut = new BufferedOutputStream(
                new FileOutputStream("examplesDemoFiles/bufferTemp.dat"));) {

            // Note: if you can read the entire file in one go, it's essentially the same.
            byte[] data = new byte[1000];
            for (int i = 0; i < 1000; i++) {
                data[i] = (byte) new Random().nextInt(10);
            }

            bufferedOut.write(data);

        } catch (IOException e) {
            System.out.println(e.toString());
        }

        try (BufferedInputStream bufferedIn = new BufferedInputStream(
                new FileInputStream("examplesDemoFiles/bufferTemp.dat"), 8000);) {

            System.out.println(Arrays.toString(bufferedIn.readAllBytes()));

        } catch (FileNotFoundException e) {
            System.out.println(e.toString());
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }
}