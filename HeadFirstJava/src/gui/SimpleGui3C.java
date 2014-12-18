package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class SimpleGui3C implements ActionListener{
	JFrame frame;
	
	public static void main(String[] args) {
		SimpleGui3C s=new SimpleGui3C();
		s.go();
	}
	
	void go(){
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 300);
		
		//get frame's container
		Container c=frame.getContentPane();
				
		//create button
		JButton button =new JButton("Click me");
		button.addActionListener(this);
		
		//add to container
		c.add(BorderLayout.SOUTH,button);
		
		//create panel
		MyDrawPanel mdp=new MyDrawPanel();
		c.add(BorderLayout.CENTER, mdp);
		
		//set the frame visible
		frame.setVisible(true);
		
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		frame.repaint();
	}
	
}
