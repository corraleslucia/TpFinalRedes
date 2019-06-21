package client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;


public class Client {
    private Socket socket;
    private String host;
    private int port;

    public Client(String host, int port) {
        this.host = host;
        this.port = port;

    }

    public boolean startClient(){
        try {
            socket = new Socket(host, port);
            return true;
        } catch (UnknownHostException e) {
            System.err.println(String.format("Does not recognize host [%s] - Error : %s", host, e.getMessage()));
            return false;
        } catch (IOException e) {
            System.err.println("Cannot establish I/O channels for the connection (port error)  - Error: " + e.getMessage());
            return false;
        } catch (IllegalArgumentException e){
            System.err.println("The port is wrong (port error)  - Error: " + e.getMessage());
            return false;
        }
    }

    public void connect() {
        BufferedReader in;
        PrintWriter out;
        BufferedReader scan;
        String message;
        String answer;
        System.out.println("Client available.\n");
        try {
            System.out.println("Connected to server.\n");
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
            scan = new BufferedReader(new InputStreamReader(System.in));
            answer = in.readLine();
            System.out.println("Message from server: " + answer);
            while (true) {
                System.out.print("\nINPUT: ");
                message = scan.readLine();
                out.println(message);
                answer = in.readLine();
                if (message.equalsIgnoreCase("x")) {
                    System.out.println("\nClosing connection...");
                    out.println("\nClosing connection...");
                    break;
                }
                System.out.println("\nMessage from server: " + answer);
            }
            out.close();
            in.close();
            scan.close();
            socket.close();
        } catch (IOException e) {
            System.out.println("Server down. Closing connection...");
            try {
                socket.close();
            } catch (IOException e1) {
                System.out.println("IOException: " + e1.getMessage());
            }
        }

    }

}
