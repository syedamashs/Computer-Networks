import java.util.*;
import java.util.Scanner.*;
import java.rmi.*;

public class CryptoClient{

public static void main(String[] args){


	try{

		Crypto c=(Crypto) Naming.lookup("rmi://localhost/CryptoService");

	Scanner sc=new Scanner(System.in);

	String name =sc.next();

	System.out.print(c.getprice(name));

}catch(Exception e){
		System.out.print(e.getMessage());
}

}}