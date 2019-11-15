import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ObjectIOServer {

    public static final int PORT = 8000;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT); Socket socket = serverSocket.accept();) {
            InetAddress clientAdd = socket.getInetAddress();

            try (ObjectOutputStream to = new ObjectOutputStream(socket.getOutputStream());) {

                System.out.println("Connected to localhost, " + clientAdd.getHostAddress());

                // send the Serializable object to the client
                Student stu = new Student("Curtis", "123-123-123-123", 24, "abc");
                to.writeObject(stu);
                System.out.println("Object Sent, Terminating...");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }
}