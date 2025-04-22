import java.net.*;

public class UDPServer {
    public static void main(String[] args) {
        try (DatagramSocket serverSocket = new DatagramSocket(9876)) {
            byte[] receiveData = new byte[1024];
            byte[] sendData;
            System.out.println("UDP Server is running...");

            while (true) {
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);
                String clientMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Received: " + clientMessage);

                String response = "Notification: " + clientMessage + " is shipped!";
                sendData = response.getBytes();
                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();

                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
                serverSocket.send(sendPacket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}