import java.util.*;
import java.io.*;
import java.net.*;

public class UDPserver{
 public static void main(String[] args) throws Exception{

	DatagramSocket socket = new DatagramSocket(6000);

	byte[] receivedata = new byte[1024];

	DatagramPacket receivep = new DatagramPacket(receivedata,receivedata.length);
	
	socket.receive(receivep);
	String filename = new String(receivep.getData(),0, receivep.getLength());
	
	File file = new File(filename);
	InetAddress address = receivep.getAddress();
	int port = receivep.getPort();

	if(!file.exists()){
		String error = "File not Found!";
		socket.send(new DatagramPacket(error.getBytes(),error.length(),address,port));
	}
	else{
	FileInputStream fis = new FileInputStream(file);
	
 	int bytesRead;
	byte[] filebuffer = new byte[1024];

	while((bytesRead=fis.read(filebuffer))!=-1){
		socket.send(new DatagramPacket(filebuffer,bytesRead,address,port));	
		Thread.sleep(10);

}

 fis.close();
}
}}
