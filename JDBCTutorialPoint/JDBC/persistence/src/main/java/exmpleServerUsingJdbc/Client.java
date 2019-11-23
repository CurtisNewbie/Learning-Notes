package exmpleServerUsingJdbc;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * It is just an example of client
 * 
 * @author Curtis
 *
 */
public class Client {

	public static void main(String[] args) throws UnknownHostException, IOException {

		Socket socket = new Socket("localhost", 4446);
		System.out.println("Connected to server");

		DataInputStream in = new DataInputStream(socket.getInputStream());

		String result = in.readUTF();
		System.out.println(result);
		in.close();
		socket.close();
	}
}
