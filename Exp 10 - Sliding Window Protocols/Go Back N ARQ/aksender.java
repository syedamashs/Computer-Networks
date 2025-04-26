import java.util.*;
import java.net.*;
import java.io.*;

public class aksender{
 
 public static void main(String[] args) throws Exception{

	DatagramSocket ds = new DatagramSocket();
	InetAddress ip = InetAddress.getByName("localhost");
	Scanner sc = new Scanner(System.in);

 	System.out.println("Enter no of frames to be send:");
	int n = sc.nextInt();

	System.out.println("Enter window size:");
	int window = sc.nextInt();

	int base=0,nextseq=0;
  	while(base<n){
	
	while(nextseq<base+window && nextseq<n){

		String msg= "Frame "+ nextseq;
		byte[] buffer =msg.getBytes();
		ds.send(new DatagramPacket(buffer,buffer.length,ip,3000));
		System.out.println("sent: "+msg);
		nextseq++;
}

	byte[] ackbuf = new byte[1024];
	DatagramPacket rp = new DatagramPacket(ackbuf,ackbuf.length);
	ds.receive(rp);


	String ack = new String(rp.getData(),0,rp.getLength());
	System.out.println("Received Acknowledgement for Frame "+ack);

	base=Integer.parseInt(ack)+1;

}

System.out.println("All frames sent successfully.");
ds.close();
sc.close();
}}
