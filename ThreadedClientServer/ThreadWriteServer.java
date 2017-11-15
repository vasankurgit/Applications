package src.ClientServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import static java.lang.System.in;

public class ThreadWriteServer {

    ObjectOutputStream objectOutputStream = null;
    Socket client = null;

    public ThreadWriteServer(Socket serverSocket) {
        this.client = serverSocket;

    }

    public void run() {
        System.out.println("You write:  ");

        while (true) {
            try {
                objectOutputStream = new ObjectOutputStream(client.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            BufferedReader bf = new BufferedReader(new InputStreamReader(in));
            try {
                String line = null;
                if ((line = bf.readLine()) != null) {
                    System.out.println("You write:  ");


                    try {
                        objectOutputStream.writeObject(line);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        objectOutputStream.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
