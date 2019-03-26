import java.net.*;
import java.io.*;
import java.util.*;

public class Server {
    ArrayList<Client> global = new ArrayList<Client>();

    public static void main(String[] args) {
        try {
            ServerSocket servSock = new ServerSocket(7000);

            DataOutputStream send = new DataOutputStream(sock.getOutputStream());
            // DataInputStream receive = new DataInputStream(sock.getInputStream());

            BufferedReader d = new BufferedReader(new InputStreamReader(sock.getInputStream()));

            String test = d.readLine();
            System.out.println(test);

            while(true){
                Socket sock = servSock.accept();

                PrintWriter pout = new PrintWriter(client.getOutputStream(), true);

                servSock.close();
            }

        } catch (IOException e) {
            System.err.println(e);
        }

    }
}