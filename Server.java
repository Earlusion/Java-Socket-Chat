import java.net.*;
import java.io.*;
import java.util.*;

public class Server {
    static ServerSocket servSock;
    static Socket sock;

    static PrintWriter out;
    static BufferedReader in;

    ArrayList<Client> global = new ArrayList<Client>();

    public void start(int port) {
        try {
            servSock = new ServerSocket(port);
            sock = servSock.accept();
            out = new PrintWriter(sock.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(sock.getInputStream()));

            while (true) {
                
                String inputLine = in.readLine();

                if (".".equals(inputLine)) {
                    out.println("good bye");
                    break;
                }
                System.out.println(inputLine);
                out.println(inputLine);

                // System.out.println("Works!");
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public static void main(String[] args) {
        try {
            Server server = new Server();
            server.start(7000);

            // String test = in.readLine();
            // System.out.println(test);

            servSock.close();

        } catch (IOException e) {
            System.err.println(e);
        }

    }
}