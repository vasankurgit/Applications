package src.ClientServer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ThreadReadServer {

    ObjectOutputStream objectOutputStream = null;
    Socket client = null;

    public ThreadReadServer(Socket client) {
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
                    System.out.println("Client says: " + msg2);
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
