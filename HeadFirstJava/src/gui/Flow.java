package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class Flow implements ActionListener{
	JTextArea text;
	
	public static void main(String[] args) {
		new Flow().go();
	}
	
	public void go(){
		JFrame f=new JFrame("DDDD");
		f.setSize(333, 333);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c=f.getContentPane();
		
		
		JPanel p=new JPanel();
		JButton b=new JButton("Just click it");
		b.addActionListener(new Flow());
		c.add(BorderLayout.SOUTH, b);
		
		
//		p.setBackground(Color.CYAN);
//		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
		
		
		text = new JTextArea(10,20);
		text.setLineWrap(true);
		
		JScrollPane scroller =new JScrollPane(text);
		scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		JCheckBox check=new JCheckBox("Goes to 11");
		p.add(scroller);
		p.add(check);
		c.add(BorderLayout.CENTER, p);
		f.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		text.append("button clicked\n");
	}

}
