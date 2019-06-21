package server;

import clientHandler.ClientHandler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;


public class Server extends Thread {
    private ServerSocket serverSocket;
    private int port = 3000;

    public Server() {
        try {
            this.serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            System.err.println(String.format("Cannot listen to port [%s] - Error : %s", port, e.getMessage()));
        }
    }


    public void run() {
        BufferedReader in = null;
        PrintWriter out = null;
        Socket socket = null;
        String hostname=null;
        try {
            hostname = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            System.out.println("Unknown Host: " + e.getMessage());
        }

        System.out.println("");
        System.out.println(String.format("Server: [%s] is available\n",hostname));
        System.out.println("Waiting for client...\n");
        try {
            while (true) {
                try {
                    socket = serverSocket.accept();
                } catch (IOException e1) {
                    System.out.println("Server closed.");
                    break;
                }
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
                System.out.println(String.format("New client: %s\n", socket));
                Thread t = new ClientHandler(socket, in, out);
                t.start();
            }
        } catch (IOException e) {
            System.err.println("IOException: " + e.getMessage());
        }
    }

    public void stopServer() {
        try {
            serverSocket.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}