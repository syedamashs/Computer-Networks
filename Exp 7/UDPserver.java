import java.net.*;

public class UDPServer {
    public static void main(String[] args) throws Exception {
        DatagramSocket serverSocket = new DatagramSocket(9876);
        System.out.println("UDP Server is running...");

        while (true) {
            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket); // Wait for client message

            // Start a new thread to handle this message
            new Thread(() -> {
                try {
                    String message = new String(receivePacket.getData(), 0, receivePacket.getLength());
                    System.out.println("Received: " + message);

                    String reply = "Notification: " + message + " received!";
                    byte[] sendData = reply.getBytes();

                    InetAddress clientIP = receivePacket.getAddress();
                    int clientPort = receivePacket.getPort();

                    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientIP, clientPort);
                    serverSocket.send(sendPacket);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}