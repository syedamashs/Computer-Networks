import java.rmi.*;
import java.util.Scanner.*;

public class CalculatorClient{
	public static void main(String[] args){


		try{
	Calculator calc=(Calculator) Naming.lookup("rmi://localhost/CalculatorService");
		
	int a=5,b=5;

	System.out.println(calc.add(a,b));

	System.out.println(calc.subtract(a,b));

	System.out.println(calc.multiply(a,b));

	System.out.println(calc.divide(a,b));

}catch(Exception e){
	System.out.println(e.getMessage());
}

}}
