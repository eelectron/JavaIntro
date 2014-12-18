package gui;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.sound.midi.ControllerEventListener;
import javax.sound.midi.ShortMessage;
import javax.swing.JPanel;

public class MyDrawPanel1 extends JPanel {
	int x,y,width,height;

	public void paintComponent(Graphics g){
		
		
		g.setColor(Color.WHITE);		//****
		g.fillRect(0, 0, 300, 300);		//****
		g.setColor(Color.BLUE);
		g.fillRect(x, y, width, height);
	}
}