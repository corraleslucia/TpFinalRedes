package main;

import client.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {
        System.out.println("");
        System.out.println("Welcome Client! To connect to a Server, please indicate [HOST] and [PORT]: ");
        String host = null;
        int port = 0;
        boolean started = false;

        while (!started) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            try {
                System.out.println("");
                System.out.print("Insert host: ");
                host = bufferedReader.readLine();
                System.out.println("");
                System.out.print("Insert port: ");
                try{
                    port = Integer.parseInt(bufferedReader.readLine());
                    System.out.println("");
                }catch(NumberFormatException e){
                    System.out.println("");
                    System.err.println("The port input has forbidden characters. Please use ONLY numbers.");
                    System.out.println("");
                    port = 0;
                }

                Client clientRun = new Client(host, port);
                started = clientRun.startClient();
                if (started) {
                    clientRun.connect();
                    System.out.println("Connection closed.");
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


}
