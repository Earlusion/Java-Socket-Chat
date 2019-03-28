import java.net.*;
import java.io.*;
import java.util.*;

public class Server {
    // private static ServerSocket servSock;
    // static Socket sock;

    private static Set<String> allClient = new HashSet<>();
    private static ArrayList<PrintWriter> clients = new ArrayList<PrintWriter>();

    // public void start(int port) {
    // try {
    // servSock = new ServerSocket(port);
    // sock = servSock.accept();
    // out = new PrintWriter(sock.getOutputStream(), true);
    // in = new BufferedReader(new InputStreamReader(sock.getInputStream()));

    // while (true) {
    // String inputLine = in.readLine();

    // if (inputLine.equals(null)) {
    // out.println("good bye");
    // break;
    // }
    // System.out.println(inputLine);
    // out.println(inputLine);

    // }
    // } catch (IOException e) {
    // System.err.println(e);
    // } finally {

    // }
    // }

    public static void main(String[] args) {
        ServerSocket servSock;
        Socket sock;

        try {
            servSock = new ServerSocket(7000);

            System.out.println("Server started at " + new Date() + '\n');
            while (true) {
                // Listen for a connection request
                sock = servSock.accept();
                ChatRoom room = new ChatRoom(sock);
                Thread clinet = new Thread(room);
                clinet.start();
            }
        } catch (IOException ioe) {
            System.err.println(ioe);
        }

        // try {
        // Server server = new Server();
        // server.start(7000);

        // servSock.close();

        // } catch (IOException e) {
        // System.err.println(e);
        // }

    }

    private static class ChatRoom implements Runnable {
        private static Socket sock;
        private String name;

        private static PrintWriter out;
        private static BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));

        out = new PrintWriter(sock.getOutputStream(), true);
        //in = new BufferedReader(new InputStreamReader(sock.getInputStream()));

        public ChatRoom(Socket socket) {
            this.sock = socket;
        }

        public void run() {
            try {
                // create data input and data output streams
                // input4mclient = new DataInputStream(socket.getInputStream());
                // output2client = new DataOutputStream(socket.getOutputStream());

                while (true) {
                    // output2client.writeUTF("YOURNAME");
                     name = in.readLine();
                     if (name == null)
                     return;
                    synchronized (allClient) {
                        if (name != null && !allClient.contains(name)) {
                            allClient.add(name);
                            break;
                        }
                    }
                }

                // write.println("JOINGROUP" + name);
                // output2client.writeUTF("JOINGROUP" + name);
                // output2client.writeUTF("NAME" + name);

                for (PrintWriter w : clients) {
                    w.println("JOINGROUP" + name);
                }
                // clients.add(output2client);

                while (true) {
                    // output2client.writeUTF("Message");
                    String message = in.readLine();
                    if (message == "LOGOUT") {
                        return;
                    }
                    for (PrintWriter w : clients) {

                        w.println("Message" + name + ":" + message);
                    }
                    // System.out.println(message);
                    // output2client.writeUTF("Message" + name +":"+ message);
                }

            } catch (Exception e) {
                System.out.println(e);
            } finally {

                try {
                    // System.out.println(name + " someone has left");

                    if (out != null) {
                        clients.remove(out);
                    }
                    /*
                     * if (name != null) { allClient.remove(name); for (DataOutputStream w :
                     * clients) { w.writeUTF("Message" + name + ":" + "has left the chat"); } }
                     */
                    sock.close();

                } catch (Exception e) {
                    System.err.print(e);
                }
            }
        }
    }
}