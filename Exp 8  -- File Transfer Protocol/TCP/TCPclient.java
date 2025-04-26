import java.util.*;
import java.io.*;
import java.net.*;


public class TCPclient{

 public static void main(String[] args) throws Exception{

	Socket connectionSocket = new Socket("localhost",5000);

	DataInputStream dis = new DataInputStream(connectionSocket.getInputStream());
	DataOutputStream dos = new DataOutputStream(connectionSocket.getOutputStream());
	BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

	String filename= input.readLine();
	dos.writeUTF(filename);
	
	String response= dis.readUTF();

 if(response.equals("File not Found!")){
	System.out.println("Response -- File not found ");
}
 else{
	FileOutputStream fos = new FileOutputStream("downloaded_"+filename);
	byte[] buffer = new byte[1024];
	int bytesRead;
	
	while((bytesRead = dis.read(buffer))!=-1){
		fos.write(buffer,0,bytesRead);
}

	fos.close();
}

dis.close();
dos.close();
connectionSocket.close();

}}
