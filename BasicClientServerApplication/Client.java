package src.ClientServer;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import static java.lang.System.in;

public class Client  {
    public static void main(String args[]) throws IOException, ClassNotFoundException {
        InetAddress address = InetAddress.getByName("127.0.0.1");

        Socket client = null;
        try {
            client = new Socket(address,880);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ObjectOutputStream objectOutputStream=null;
        ObjectInputStream objectInputStream = null;
              String msg = "Hello";

        while(true){

            BufferedReader bf = new BufferedReader(new InputStreamReader(in));
            System.out.println("Writing CLient:  ");
            String line =bf.readLine();
            objectOutputStream= new ObjectOutputStream(client.getOutputStream());
            objectOutputStream.writeObject(line);
            objectOutputStream.flush();
            objectInputStream = new ObjectInputStream(client.getInputStream());

            try {
                msg = (String) objectInputStream.readObject();
                System.out.println("Reading Server out: "+msg);

            } catch (IOException e) {
                e.printStackTrace();
            }


            if(msg.equals("STOP")){
    break;
}
        }
        objectOutputStream.close();
        objectInputStream.close();

        client.close();
        }

    }


