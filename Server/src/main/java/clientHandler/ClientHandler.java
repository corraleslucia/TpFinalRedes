package clientHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler extends Thread {

    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public ClientHandler(Socket socket, BufferedReader in, PrintWriter out) {
        this.socket = socket;
        this.in = in;
        this.out = out;
    }

    public void run() {
        String message;
        out.println("Hello ! What's your message?(You can press 'x' to leave anytime)");
        System.out.println("Server: Hello ! What's your message? (You can press 'x' to leave anytime) \n");
        while (true) {
            try {
                message = in.readLine();
                System.out.println(String.format("Message from client [%s]: %s\n", socket, message));
                if (message.equalsIgnoreCase("x")) {
                    System.out.println(String.format("Closing connection with client: %s ...\n", socket));
                    socket.close();
                    out.close();
                    in.close();
                    System.out.println(String.format("Connection with client [%s] is closed.\n",socket));
                    break;
                }
                out.println("Do you have another message? If not, please press 'x'");
                System.out.println("Server: Do you have another message? If not, please press 'x'\n");

            } catch (IOException e) {
                System.out.println("IOException: unexpected problem with client... : " + e.getMessage());
                break;
            }
        }

    }
}
