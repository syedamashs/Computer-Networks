import java.io.*;
import java.net.*;

public class TCPClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 6789);

        BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
        DataOutputStream outToServer = new DataOutputStream(socket.getOutputStream());
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        System.out.print("Enter two numbers (e.g., 5 7): ");
        String input = userInput.readLine();
        outToServer.writeBytes(input + "\n");

        String result = inFromServer.readLine();
        System.out.println("Server replied: " + result);

        socket.close();
    }
}