package gui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class SimpleGui1B implements ActionListener {
	JButton button;
	
	public void go(){
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 300);
		
		button = new JButton("Click me");
		
		//add this class as a listener of button
		button.addActionListener(this);
		
		//add rectangle
		MyDrawPanel mdp=new MyDrawPanel();
		Container container = frame.getContentPane();
		
		//add the panel to frame
		container.add(mdp);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
			button.setText("You clicked me");
	}
}