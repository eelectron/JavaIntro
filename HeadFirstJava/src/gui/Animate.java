package gui;

import java.awt.Container;

import javax.swing.JFrame;

public class Animate {
	JFrame f;
	public void go(){
		f=new JFrame("NNNN");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(300, 300);
		f.setVisible(true);
		Container c = f.getContentPane();
		
		MyDrawPanel1 ml=new MyDrawPanel1();
		ml.x =50;
		ml.y = 50;
		ml.width = 100;
		ml.height  = 100;
		c.add(ml);
		
		while(ml.width > 0){
			ml.width--;
			ml.height--;
			
			//TRICK
			//when this method executed, every time a the current blue rect is
			//covered by white rect and new blue rect is printed on it :)
			ml.repaint();
			
			//To slow down the animation 
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
	}
}
