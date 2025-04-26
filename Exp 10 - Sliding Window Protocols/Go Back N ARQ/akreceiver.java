import java.util.*;
import java.net.*;
import java.io.*;

public class akreceiver{
 
 public static void main(String[] args) throws Exception{

	DatagramSocket ds = new DatagramSocket(3000);
	int expframe = 0;

	while(true){

	byte[] buff = new byte[1024];
	DatagramPacket rp = new DatagramPacket(buff,buff.length);
	ds.receive(rp);
	String msg = new String(rp.getData(),0,rp.getLength());
	
	int frameno = Integer.parseInt(msg.split(" ")[1]);
	
	if(frameno == expframe){

		System.out.println("Sending ack for the frame "+frameno);

	byte[] ack=String.valueOf(frameno).getBytes();
	ds.send(new DatagramPacket(ack,ack.length,rp.getAddress(),rp.getPort()));
	expframe++;
	
}else{
	System.out.println("Discarded Frame "+frameno+" Expected Frame "+expframe);

}}}}

 	
