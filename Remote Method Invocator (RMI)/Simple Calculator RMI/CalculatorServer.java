import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.*;

public class CalculatorServer extends UnicastRemoteObject implements Calculator{

	public CalculatorServer() throws RemoteException{
		super();
	}

	public int add(int a,int b){
		return a+b; }


	public int subtract(int a,int b){
		return a-b; }


	public int multiply(int a,int b){
		return a*b; }


	public int divide(int a,int b){
		if(b==0){return 0;}
		return a/b; }


	public static void main(String[] args){

	try{

		LocateRegistry.createRegistry(1111);
		CalculatorServer server=new CalculatorServer();

		Naming.rebind("rmi://localhost/CalculatorService",server);

		System.out.println("Calculator Server is running!!!");
}catch(Exception e){
		System.out.println(e.getMessage());
}

	}}
