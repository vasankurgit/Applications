package src.ClientServer;

import java.io.*;
import java.net.*;
import java.util.Scanner;

import static java.lang.System.in;

public class Server {
    public static void main(String args[]) throws ClassNotFoundException, IOException {
        InetAddress address = null;
        try {
          //  address = InetAddress.getByName("78.46.84.171");
            address = InetAddress.getByName("127.0.0.1");

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(880, 21,address);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        System.out.println(serverSocket.getInetAddress().getHostName());
        Socket serviceSocket = null;
        try {
            serviceSocket = serverSocket.accept();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(serviceSocket.getInetAddress().getHostName());

        System.out.println("accepted a connection");
        ObjectOutputStream objectOutputStream=null;
        ObjectInputStream objectInputStream = null;



        String msg = "hello";

        while (!msg.equals("END")) {

            try {
                objectInputStream = new ObjectInputStream(serviceSocket.getInputStream());
                msg = (String) objectInputStream.readObject();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Reading the Client:   " + msg);

            BufferedReader bf = new BufferedReader(new InputStreamReader(in));
            System.out.println("Writing Server:  ");
            String line =bf.readLine();
            objectOutputStream= new ObjectOutputStream(serviceSocket.getOutputStream());

            objectOutputStream.writeObject(line);
            objectOutputStream.flush();


        }


        try {
            serviceSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

