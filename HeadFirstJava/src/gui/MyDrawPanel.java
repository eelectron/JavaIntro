package gui;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.sound.midi.ControllerEventListener;
import javax.sound.midi.ShortMessage;
import javax.swing.JPanel;

public class MyDrawPanel extends JPanel implements ControllerEventListener{
	int x,y,width,height;
	
	@Override
	public void controlChange(ShortMessage event) {
		repaint();	
	}
	
	public void paintComponent(Graphics g){
		
		//get blue color
		Color sc=new Color(0,0,255);
		g.setColor(sc);
		g.fillRect(x, y, width, height);
	}
}