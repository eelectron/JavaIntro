package week3;

import comp102x.*;

public class ColorImageDemo{

	   public Canvas canvas = new Canvas();
	     public ColorImage image1 = new ColorImage();

	     public void setAndOutput() {

	          canvas.add(image1, 0, 0);
	          image1.setX(50);
	          image1.setY(100);
	          image1.setRotation(45);
	          image1.setY(image1.getX() + 100);
	          image1.setRotation(image1.getRotation() - 90);

	          IO.output(" X: " + image1.getX());
	          IO.output(" Y: " + image1.getY());
	          IO.output(" A: " + image1.getRotation());
	     }
	     
	public static void main(String[] args) {
		ColorImageDemo ci = new ColorImageDemo();
		ci.setAndOutput();
		
	}
}
