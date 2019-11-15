import java.net.*;
import java.io.*;

public class ObjectIOClient {

    public static final int PORT = 8000;

    public static void main(String[] args) {

        try (Socket socket = new Socket("localhost", PORT)) {

            try (ObjectInputStream in = new ObjectInputStream(socket.getInputStream());) {

                InetAddress add = socket.getInetAddress();
                System.out.println("Connected to " + add.getHostAddress() + " " + add.getHostName());
                System.out.println(in.readObject().toString());
            }

        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
