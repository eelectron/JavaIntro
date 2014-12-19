package simpleRMI;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;


public class MyRemoteClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		

	}
	
	public void go(){
		MyRemote service;
		try {
			service = (MyRemote)Naming.lookup("rmi://127.0.0.0/Remote Hello");
			String s=service.sayHello();
			System.out.println(s);
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
		
	}

}
