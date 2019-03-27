import java.net.*;
import java.io.*;
import java.util.*;

public class Server {
    static ServerSocket servSock;
    static Socket sock;

    static PrintWriter out;
    static BufferedReader in;

    ArrayList<Client> global = new ArrayList<Client>();

    public static void start(int port) {
        try {
            servSock = new ServerSocket(port);
            sock = servSock.accept();
            out = new PrintWriter(sock.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(sock.getInputStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                if (".".equals(inputLine)) {
                    out.println("good bye");
                    break;
                }
                out.println(inputLine);
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public static void main(String[] args) {
        
        try {
            Server server = new Server();
            server.start(7000);

            String test = in.readLine();
            System.out.println(test);
            
            servSock.close();

        } catch (IOException e) {
            System.err.println(e);
        }

    }
}