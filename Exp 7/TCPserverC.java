import java.io.*;
import java.net.*;

public class TCPServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(6789);
        System.out.println("TCP Concurrent Server is running...");

        while (true) {
            Socket clientSocket = serverSocket.accept(); // Accept a client
            new ClientHandler(clientSocket).start(); // New thread for each client
        }
    }
}

class ClientHandler extends Thread {
    private Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            DataOutputStream outToClient = new DataOutputStream(socket.getOutputStream());

            String line = inFromClient.readLine(); // Expects: "5 7"
            String[] nums = line.split(" ");
            int a = Integer.parseInt(nums[0]);
            int b = Integer.parseInt(nums[1]);
            int sum = a + b;

            outToClient.writeBytes("Sum = " + sum + "\n");

            socket.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}