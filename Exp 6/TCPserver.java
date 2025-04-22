import java.io.*;
import java.net.*;

public class TCPServer {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(6789)) {
            System.out.println("TCP Server is running...");
            Socket connectionSocket = serverSocket.accept();
            System.out.println("Client connected.");

            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());

            String clientMessage;
            while ((clientMessage = inFromClient.readLine()) != null) {
                System.out.println("Received: " + clientMessage);
                outToClient.writeBytes("Order Status: " + clientMessage + " is being processed\n");
            }

            connectionSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}