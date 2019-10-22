import java.io.*;
import java.util.Arrays;

public class FileIOStreamsDemo {

    public static void main(String[] args) {

        System.out.println("Bytes of \\n is " + Arrays.toString("\n".getBytes()) + "\n");

        try (FileOutputStream output = new FileOutputStream("examplesDemoFiles/temp.dat");) {

            for (int i = 0; i < 10; i++) {
                output.write(i);
            }

            System.out.println("Write To \"temp.dat\" Successfully");
        } catch (IOException e) {
            System.out.println(e.toString());
        }

        try (FileInputStream input = new FileInputStream("examplesDemoFiles/temp.dat");) {

            System.out.println("\nShowing the content in \"temp.dat\": \n");

            int buffer;
            while ((buffer = input.read()) != -1) {
                System.out.print(buffer + " ");
            }
            System.out.println();
        } catch (FileNotFoundException e1) {
            System.out.println(e1.toString());
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }
}