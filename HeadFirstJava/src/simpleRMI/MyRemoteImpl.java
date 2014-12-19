package simpleRMI;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class MyRemoteImpl extends UnicastRemoteObject implements MyRemote{
	
	public MyRemoteImpl() throws RemoteException{}
	
	@Override
	public String sayHello() {
		return "Hello RMI";
	}
	
	public static void main(String[] args) {
		MyRemote service;
		try {
			service = new MyRemoteImpl();
			Naming.rebind("RemoteHello", service);
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
	}
}
