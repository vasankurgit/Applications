package src.ClientServer;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class ThreadedClient {

    //thread 1 run
    //thread 2 run
    //while call thread1 and thread2

    public static void main(String args[]) throws IOException, ClassNotFoundException {
        InetAddress address = InetAddress.getByName("127.0.0.1");

        Socket client = null;
        try {
            client = new Socket(address, 880);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ObjectOutputStream objectOutputStream = null;
        ObjectInputStream objectInputStream = null;
        String msg = "Hello";

        {
            ThreadWriteClient threadWriteClient = new ThreadWriteClient(client);
            Thread thread1 = new Thread(threadWriteClient);
            thread1.start();
            ThreadReadClient threadRead = new ThreadReadClient(client);
            Thread thread2 = new Thread(threadRead);
            thread2.start();
        }

    }


}
