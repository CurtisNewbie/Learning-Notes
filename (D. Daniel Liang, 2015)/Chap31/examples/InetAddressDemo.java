import java.net.*;

public class InetAddressDemo {
    public static void main(String[] args) {

        try {
            InetAddress add = InetAddress.getByName("localhost");
            System.out.println("Host Name: " + add.getHostName() + "\nIP Address: " + add.getHostAddress());

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

    }
}