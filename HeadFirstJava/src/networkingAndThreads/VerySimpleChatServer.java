package networkingAndThreads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

public class VerySimpleChatServer {
	ArrayList<PrintWriter> clientOutputStream;
	
	public static void main(String[] args) {
		new VerySimpleChatServer().go();
	}
	
	
	public class ClientHandler implements Runnable{
		BufferedReader reader;
		Socket sock;
		
		public ClientHandler(Socket clientSocket){
			try{
				sock = clientSocket;
				InputStreamReader in=new InputStreamReader(sock.getInputStream());
				reader=new BufferedReader(in);
			}catch(Exception ex){}
		}
		@Override
		public void run() {
			String message;
			while(true){
				try {
					if((message=reader.readLine()) != null){
						System.out.println("read "+message);
						tellEveryOne(message);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}	
		}		
	}
	
	
	
	public void go(){
		clientOutputStream =new ArrayList<PrintWriter>();
		try {
			ServerSocket serverSock=new ServerSocket(5000);
			
			while(true){
				Socket clientSocket=serverSock.accept();
				PrintWriter writer=new PrintWriter(clientSocket.getOutputStream());
				clientOutputStream.add(writer);
				
				Thread t=new Thread(new ClientHandler(clientSocket));
				t.start();
				System.out.println("got connection");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	void tellEveryOne(String msg){
		Iterator it =clientOutputStream.iterator();
		while(it.hasNext()){
			PrintWriter writer=(PrintWriter)it.next();
			writer.println(msg);
			writer.flush();
		}
	}
}
