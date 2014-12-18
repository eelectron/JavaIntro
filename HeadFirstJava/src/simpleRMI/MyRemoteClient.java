package simpleRMI;
import java.rmi.Naming;


public class MyRemoteClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		

	}
	
	public void go(){
		MyRemote service =(MyRemote)Naming.lookup("rmi://127.0.0.0/Remote Hello");
		String s=service.sayHello();
		System.out.println(s);
	}

}
