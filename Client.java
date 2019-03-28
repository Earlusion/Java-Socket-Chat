import java.net.*;
import java.io.*;
import java.util.*;

public class Client {
    String name;

    private static Socket sock;
    private static PrintWriter out;
    private static BufferedReader in;

    public Client(String newName) {
        this.name = newName;
    }

    public String getName() {
        return name;
    }

    public void joined() {
        String str = "";

        str += "*** " + name + " has entered the chat! ***\n";
        out.println(str);
    }

    public void leave(){
        String str = "";

        str += "*** " + name + " has left the chat! ***\n";
        out.println(str);
    }

    public static void startConnection(String ip, int port) throws IOException {
        sock = new Socket(ip, port);
        out = new PrintWriter(sock.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
    }

    public String sendMessage(String msg) throws IOException {
        String str = name + ":\n" + msg + "\n";

        out.println(str);
        String resp = in.readLine();
        return resp;
    }

    public static void stopConnection() throws IOException {
        in.close();
        out.close();
        sock.close();
    }

    public static void main(String[] args) {
        try {
            startConnection("127.0.0.1", 7000);

            Scanner input = new Scanner(System.in);
            System.out.print("Enter your name: ");

            Client user = new Client(input.nextLine());
            user.joined();

            String line = "";
            while(true){
                line = input.nextLine();

                if(line.equals("exit()")){
                    user.leave();
                    break;
                }

                user.sendMessage(line);
            }

            stopConnection();
            input.close();

        } catch (IOException e) {
            System.err.println(e);
        }
    }
}