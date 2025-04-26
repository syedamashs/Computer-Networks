import java.util.*;
import java.io.*;
import java.net.*;

public class TCPserver{

 public static void main(String[] args) throws Exception{

	ServerSocket serverSocket = new ServerSocket(5000);
	System.out.println("Server started!!!");
	Socket connectionSocket = serverSocket.accept();

	DataInputStream dis = new DataInputStream(connectionSocket.getInputStream());
	DataOutputStream dos = new DataOutputStream(connectionSocket.getOutputStream());

	String filename= dis.readUTF();
	
	File file = new File(filename);
 if(!file.exists()){
	dos.writeUTF("File not Found!");
}

 else{

	dos.writeUTF("File Found");
	
	byte[] buffer = new byte[1024];
	int bytesRead;
	FileInputStream fis = new FileInputStream(file);


	while((bytesRead = fis.read(buffer))!=-1){
		dos.write(buffer,0,bytesRead);
}

	fis.close();
}

dis.close();
dos.close();
connectionSocket.close();

}}
