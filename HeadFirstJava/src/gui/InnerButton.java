//Click on button to SEE random number appears on buttons!!!!!!!!!!!!
package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class InnerButton {
	JFrame f;
	JButton b;
	
	public void go(){
		f=new JFrame("NNNN");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(300, 300);
		Container c = f.getContentPane();
		
		b=new JButton("A");
		//Add a event listener object for button 
		b.addActionListener(new BListener());
		
		c.add(BorderLayout.CENTER, b);
		
		f.setVisible(true);
	}
	
	class BListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			Integer i=new Integer((int)(Math.random()*100));
			String s = i.toString();
			b.setText(s);
		}
		
	}
}
