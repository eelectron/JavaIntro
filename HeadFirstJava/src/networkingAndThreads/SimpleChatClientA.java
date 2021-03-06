package networkingAndThreads;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;


public class SimpleChatClientA {
	JTextArea incoming;
	JTextField outgoing;
	PrintWriter writer;
	Socket sock;
	BufferedReader reader;
	
	//make a gui and register a listener with the send button
	//call the setupNetworking() method
	public void go(){
		JFrame frame = new JFrame("Simple Chat Client");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 400);
		
		JPanel mainPanel =new JPanel();
		incoming=new JTextArea(15,20);
		incoming.setLineWrap(true);
		incoming.setWrapStyleWord(true);
		incoming.setEditable(false);
		JScrollPane inScroller=new JScrollPane(incoming);
		inScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		inScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		outgoing =new JTextField(20);
		
		JButton sendButton = new JButton("Send");
		sendButton.addActionListener(new SendButtonListener());
		mainPanel.add(inScroller);
		mainPanel.add(outgoing);
		mainPanel.add(sendButton);
		
		
		setUpNetworking();
		
		Thread readerThread=new Thread(new IncomingReader());
		readerThread.start();
		
		frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
		frame.setVisible(true);
	}
	
	//Make it private!
	private void setUpNetworking(){
		try{
			sock = new Socket("127.0.0.1",5000);
			
			//Use to read input from server
			InputStreamReader streamReader=new InputStreamReader(sock.getInputStream());
			reader=new BufferedReader(streamReader);
			
			//Whenever we want to write single line at a time we use PrintWriter object
			//otherwise we must use BufferedWriter object  :)
			writer = new PrintWriter(sock.getOutputStream());
			
			System.out.println("networking established");
		}catch(IOException ex){
			ex.printStackTrace();
		}
	}
	
	public class SendButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			try{
				writer.println(outgoing.getText());
				writer.flush();
			}catch(Exception ex){
				ex.printStackTrace();
			}
			outgoing.setText("");
			outgoing.requestFocus();
		}
	}
	
	public class IncomingReader implements Runnable{

		@Override
		public void run() {
			String message=null;
			try {
				while(true){
					if ((message=reader.readLine()) != null) {
						incoming.append(message+"\n");
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}