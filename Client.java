import java.net.*;
import java.io.*;
import java.util.*;

public class Client {
    String name;
    Socket sock;
    DataOutputStream send;
    DataInputStream receive;

    public Client(String newName) {
        this.name = newName;
    }

    public String getName() {
        return name;
    }

    public String joined(){
        String str = "";

        str += name + " has entered the chat";
        return str;
    }

    public static void main(String[] args) {
        try {
            Socket sock = new Socket("127.0.0.1", 7000);
            DataOutputStream send = new DataOutputStream(sock.getOutputStream());
            DataInputStream receive = new DataInputStream(sock.getInputStream());

            Scanner input = new Scanner(System.in);
            System.out.print("Enter your name: ");

            Client user = new Client(input.nextLine());

            send.writeChars(user.joined());
            send.flush();

            do{
                send.writeChars(input.nextLine());
                send.flush();
            }while(input.nextLine().equals("exit()"));

            sock.close();
            input.close();

        } catch (IOException e) {
            System.err.println(e);
        }
    }
}