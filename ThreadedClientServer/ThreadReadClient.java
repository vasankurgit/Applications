package src.ClientServer;

import java.io.*;
import java.net.Socket;

import static java.lang.System.in;

public class ThreadReadClient implements Runnable {
    ObjectOutputStream objectOutputStream = null;
    Socket client = null;

    public ThreadReadClient(Socket client) {
        this.client = client;

    }

    public void run() {
        while (true) {
            ObjectInputStream objectInputStream = null;
            String msg1 = "hello delicious";
            String msg2 = "hello delicious";
            try {
                objectInputStream = new ObjectInputStream(client.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                msg2 = (String) objectInputStream.readObject();
                if (msg1.equals(msg2)) {
                } else {
                    System.out.println("Server says: " + msg2);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            if (msg2.equals("END..")) {
                break;
            }

        }
    }


}

