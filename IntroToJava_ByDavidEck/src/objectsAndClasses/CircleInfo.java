package objectsAndClasses;

import java.awt.Color;
import java.awt.Graphics;

public class CircleInfo {
	int radius;
	int x,y;
	public Color color;
	
	public CircleInfo(int cx, int cy, int rad){
		x=cx;
		y=cy;
		radius=rad;
		int red = (int)(255*Math.random());
		int green=(int)(255*Math.random());
		int blue=(int)(255*Math.random());
		color=new Color(red, green, blue, 100);
	}
	
	public void draw(Graphics g){
		g.setColor(color);
		g.fillOval(x-radius, y-radius, 2*radius, 2*radius);
		g.setColor(Color.BLACK);
		g.drawOval(x-radius, y-radius, 2*radius, 2*radius);
	}
}
