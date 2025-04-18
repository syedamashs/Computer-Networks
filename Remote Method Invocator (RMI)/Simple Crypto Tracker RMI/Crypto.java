import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Crypto extends Remote{

	double getprice(String name) throws RemoteException;

}
