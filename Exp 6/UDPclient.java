import java.net.*;
import java.io.*;

public class UDPClient {
    public static void main(String[] args) {
        try (DatagramSocket clientSocket = new DatagramSocket()) {
            InetAddress serverAddress = InetAddress.getByName("localhost");
            byte[] sendData;
            byte[] receiveData = new byte[1024];

            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                System.out.print("Enter order status to notify (type 'exit' to quit): ");
                String message = userInput.readLine();
                if (message.equalsIgnoreCase("exit")) break;

                sendData = message.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, 9876);
                clientSocket.send(sendPacket);

                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                clientSocket.receive(receivePacket);
                String response = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Response from server: " + response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}