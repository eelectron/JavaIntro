package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class TwoButtons {
	JFrame frame;
	JLabel label;
	
	public static void main(String[] args) {
		TwoButtons tw=new TwoButtons();
		tw.go();
	}
	
	void go(){
		frame=new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 400);
		
		//get frame's container
		Container c=frame.getContentPane();
		
		//Add label button to EAST
		JButton labelButton=new JButton("change label");
		labelButton.addActionListener(new LabelListener());
		c.add(BorderLayout.EAST, labelButton);
		
		//Add color Button
		JButton colorButton=new JButton("change color");
		colorButton.addActionListener(new ColorListener());
		c.add(BorderLayout.SOUTH,colorButton);
		
		//Add label
		label=new JLabel("I am label");
		c.add(BorderLayout.WEST, label);
		
		//Add panel
		MyDrawPanel drawPanel=new MyDrawPanel();
		c.add(BorderLayout.CENTER, drawPanel);
		
		//set visible
		frame.setVisible(true);
	}
	
	class LabelListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			label.setText("OOUCH");
			
		}
		
	}
	
	class ColorListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			frame.repaint();
			
		}
		
	}
}