package main;

import server.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        String serverMessage = null;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        Server server = new Server();
        System.out.println("");
        System.out.println("Starting Server connection...");
        server.start();
        try {
            serverMessage = bufferedReader.readLine();
            System.out.println("");
            if (serverMessage.equalsIgnoreCase("x")) {
                System.out.println("You've pressed 'x'. Starting to close all connections...");
                server.stopServer();
                server.join();
                System.out.println("");
                System.out.println("Bye bye !");
                System.exit(-1);
            }
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

    }
}
