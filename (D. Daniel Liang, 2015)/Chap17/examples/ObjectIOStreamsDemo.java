import java.io.*;
import java.util.Arrays;

public class ObjectIOStreamsDemo {

    public static void main(String[] args) {
        // Arrya implments Serializable
        try (ObjectOutputStream objectOut = new ObjectOutputStream(
                new FileOutputStream("examplesDemoFiles/objectTemp.dat"));) {

            objectOut.writeObject(new String[] { "Curtis", "Yongjie", "LoveProgramming" });

        } catch (NotSerializableException e) {
            System.out.println(e.toString());
        } catch (IOException e) {
            System.out.println(e.toString());
        }

        System.out.println("The Serialised Object looks like this in binary data:\n\"");
        // show how the object looks like
        try (BufferedInputStream bufferedIn = new BufferedInputStream(
                new FileInputStream("examplesDemoFiles/objectTemp.dat"));) {

            System.out.println(Arrays.toString(bufferedIn.readAllBytes()) + "\n\"");
        } catch (FileNotFoundException e) {
            System.out.println(e.toString());
        } catch (IOException e) {
            System.out.println(e.toString());
        }
        System.out.println("\n");

        try (ObjectInputStream objectIn = new ObjectInputStream(
                new FileInputStream("examplesDemoFiles/objectTemp.dat"));) {

            String[] serialisedObj = (String[]) objectIn.readObject();
            System.out.println("Object: " + Arrays.toString(serialisedObj));
        } catch (FileNotFoundException e) {
            System.out.println(e.toString());
        } catch (IOException e) {
            System.out.println(e.toString());
        } catch (ClassNotFoundException e) {
            System.out.println(e.toString());
        }
    }
}