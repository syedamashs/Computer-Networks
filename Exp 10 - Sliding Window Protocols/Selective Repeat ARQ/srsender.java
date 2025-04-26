import java.util.*;
import java.net.*;
import java.io.*;

public class srsender{
 
 public static void main(String[] args) throws Exception{

	DatagramSocket ds = new DatagramSocket();
	InetAddress ip = InetAddress.getByName("localhost");
	Scanner sc = new Scanner(System.in);

 	System.out.println("Enter no of frames to be send:");
	int n = sc.nextInt();

	for(int i=0;i<n;i++){

	String msg= "Frame "+ i;
	byte[] buffer =msg.getBytes();
	ds.send(new DatagramPacket(buffer,buffer.length,ip,4000));
	System.out.println("sent: "+msg);


	byte[] ackbuf = new byte[1024];
	DatagramPacket rp = new DatagramPacket(ackbuf,ackbuf.length);
	ds.receive(rp);


	String ack = new String(rp.getData(),0,rp.getLength());
	System.out.println("Received Acknowledgement for Frame "+ack);

}

System.out.println("All frames sent successfully.");
ds.close();
sc.close();
}}
