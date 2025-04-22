import java.io.*;
import java.net.*;

public class TCPClient {
    public static void main(String[] args) {
        try (
            Socket clientSocket = new Socket("localhost", 6789);
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
        ) {
            System.out.print("Enter order details: ");
            String orderDetails = userInput.readLine();
            outToServer.writeBytes(orderDetails + '\n');

            String response = inFromServer.readLine();
            System.out.println("Response from server: " + response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}