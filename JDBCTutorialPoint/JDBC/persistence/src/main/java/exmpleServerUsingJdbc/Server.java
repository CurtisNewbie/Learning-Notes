package exmpleServerUsingJdbc;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * Server
 * 
 * @author Curtis
 *
 */
public class Server {

	private ServerSocket serverSocket;

	public void iniServer() throws IOException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");

		serverSocket = new ServerSocket(4446);

		Thread t = new ClientConn(serverSocket.accept());
		t.start();

		if (!t.isAlive())
			serverSocket.close();
	}

	private class ClientConn extends Thread {

		private Socket socket;
		private DataOutputStream out;
		private DataInputStream in;
		private String clientName;

		public ClientConn(Socket socket) {
			this.socket = socket;
			this.clientName = "Client:[" + socket.getInetAddress() + "]";
			System.out.println(clientName);
		}

		public void run() {
			try {
				out = new DataOutputStream(socket.getOutputStream());
				in = new DataInputStream(socket.getInputStream());

				String result = getCustomers();
				if (result != null) {
					out.writeUTF(result);
					out.flush();
				}

				in.close();
				out.close();
				socket.close();

			} catch (IOException e) {
				System.out.println(clientName + " " + e.getMessage());
			}
		}

		private String getCustomers() {

			try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hiber", "hiber",
					"hiberPW")) {

				Statement stmt = conn.createStatement();
				ResultSet resultSet = stmt.executeQuery("SELECT * FROM customer");

				// metadata
				ResultSetMetaData meta = resultSet.getMetaData();
				int numOfCol = meta.getColumnCount();

				StringBuffer buffer = new StringBuffer();

				// write column name
				for (int x = 0; x < numOfCol; x++) {
					buffer.append(meta.getColumnName(x + 1) + " ");
				}
				buffer.append("\n");

				// writer result
				while (resultSet.next()) {
					buffer.append(resultSet.getString(1) + ":::" + resultSet.getString(2) + ":::" + resultSet.getInt(3)
							+ ":::\n");
				}

				System.out.println(buffer.toString());

				return buffer.toString();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}

	}

	public static void main(String[] args) throws ClassNotFoundException, IOException {
		Server server = new Server();
		server.iniServer();
	}

}
