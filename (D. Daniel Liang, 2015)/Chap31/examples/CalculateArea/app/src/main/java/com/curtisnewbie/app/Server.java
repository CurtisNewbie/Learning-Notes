package com.curtisnewbie.app;

import java.io.*;
import java.io.IOException;
import java.net.*;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * This server gets requests from client, calculates and returns the area of a
 * circle to the client. It doesn't run by Maven, run this individually using
 * CLI as this program doesn't have GUI. <br>
 * <br>
 * To stop this server, simply type in any string that contains "/q", it will
 * stop the ExecutorService (thread pool), and stops accepting connections.
 */
public class Server {

    private static final Scanner sc = new Scanner(System.in);

    private int port;
    private boolean accept;
    private ExecutorService pool;
    private ServerSocket serverSocket;

    public Server(int port) {
        this.port = port;
        this.accept = true;
        this.pool = Executors.newFixedThreadPool(1);
    }

    public boolean isRunning() {
        return accept;
    }

    /**
     * This will not terminate the server immediately. It only stops the server from
     * getting new connection.
     */
    public void closeServer() {
        this.accept = false;
        pool.shutdown();
    }

    private void startServer() {
        try {
            serverSocket = new ServerSocket(port);

            System.out.println("Server Running");

            while (accept) {
                establishConnection(serverSocket);
                if (sc.hasNextLine()) {
                    if (sc.nextLine().contains("/q"))
                        this.closeServer();
                    else
                        System.out.println("Type in \"/q\" to close server.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void establishConnection(ServerSocket server) {
        this.pool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Socket socket = server.accept();

                    System.out.println("Connection Established With : " + socket.getInetAddress());

                    // Get I/O streams
                    DataInputStream from = new DataInputStream(socket.getInputStream());
                    DataOutputStream to = new DataOutputStream(socket.getOutputStream());

                    // return the computed result
                    to.writeDouble(computeArea(from.readDouble()));
                    System.out.println("Result Returned, Terminating Connection.\n");
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private double computeArea(double r) {
        return r * r * Math.PI;
    }

    public static void main(String[] args) {
        final Scanner sc = new Scanner(System.in);
        Server server = new Server(8000);
        server.startServer();

        while (true) {
            if (!server.isRunning()) {
                System.out.print("Exiting in 5 seconds...");
                try {
                    Thread.sleep(1000 * 5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.exit(0);
                }

            }
        }
    }
}