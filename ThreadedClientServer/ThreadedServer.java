package src.ClientServer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ThreadedServer {


    public static void main(String args[]) throws ClassNotFoundException, IOException {
        //  address = InetAddress.getByName("78.46.84.171");
        InetAddress address = InetAddress.getByName("127.0.0.1");


        ServerSocket serverSocket = null;

        serverSocket = new ServerSocket(880, 21, address);


        ///  System.out.println(serverSocket.getInetAddress().getHostName());
        Socket serviceSocket = serverSocket.accept();


        System.out.println(serviceSocket.getInetAddress().getHostName());
        System.out.println("accepted a connection");
        ObjectOutputStream objectOutputStream = null;
        ObjectInputStream objectInputStream = null;
        String msg = "hello";

        ThreadWriteClient threadWriteClient = new ThreadWriteClient(serviceSocket);
        Thread thread1 = new Thread(threadWriteClient);
        thread1.start();
        ThreadReadClient threadRead = new ThreadReadClient(serviceSocket);
        Thread thread2 = new Thread(threadRead);
        thread2.start();
    }
}
