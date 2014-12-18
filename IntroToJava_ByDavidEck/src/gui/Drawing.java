package gui;

import java.awt.Color;
import java.awt.Graphics;

public class Drawing {
	public static void main(String[] args) {
		
	}
	
	static void drawFrame(Graphics g, int fn, int w,int h){
		int cx,cy,color,count;
		
		for (count = 0; count < 500; count++) {
			color = (int)(Math.random()*3);
			switch(color){
				case 0:	g.setColor(Color.RED);break;
				case 1: g.setColor(Color.GREEN);break;
				case 2: g.setColor(Color.BLUE);break;
			}
			
			cx=(int)(Math.random()*w);
			cy=(int)(Math.random()*h);
			
			g.fillOval(cx-50, cy-50, 100, 100);
			g.setColor(Color.BLACK);
			g.drawOval(cx-50, cy-50, 100, 100);
		}
	}
}
