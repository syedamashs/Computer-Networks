import java.util.*;
import java.io.*;
import java.net.*;

public class UDPclient{
 public static void main(String[] args) throws Exception{

	DatagramSocket socket = new DatagramSocket();
	InetAddress address = InetAddress.getByName("localhost");

	BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

	String filename=input.readLine();
	byte[] senddata=filename.getBytes();
	socket.send(new DatagramPacket(senddata,senddata.length,address,6000));

	FileOutputStream fos = new FileOutputStream("Received_"+filename);
	byte[] receivedata = new byte[1024];
	socket.setSoTimeout(2000);
	
	while(true){

	DatagramPacket receivep = new DatagramPacket(receivedata,receivedata.length);
	socket.receive(receivep);
	fos.write(receivep.getData(),0,receivep.getLength());

	if(receivep.getLength()<1024) break;
}

 fos.close();

}}
