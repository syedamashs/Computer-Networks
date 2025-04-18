import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.*;
import java.util.*;


public class CryptoServer extends UnicastRemoteObject implements Crypto{
	
	private Map<String,Double> prices;

	public CryptoServer() throws RemoteException{
		super();
		prices=new HashMap<>();
		prices.put("BTC",10000.00);
		prices.put("NC",0.003);
		prices.put("GRASS",1.5);
}

	public double getprice(String name){

		for(Map.Entry<String,Double> entry: prices.entrySet()){

		if(entry.getKey().equalsIgnoreCase(name)){
			return entry.getValue();
}}
		return -1;
		
}
	public static void main(String[] args){

	try{

		LocateRegistry.createRegistry(1112);
		CryptoServer server=new CryptoServer();

		Naming.rebind("rmi://localhost/CryptoService",server);
		System.out.println("The server is running !!!");

}catch(Exception e){
System.out.print(e.getMessage());}
}}