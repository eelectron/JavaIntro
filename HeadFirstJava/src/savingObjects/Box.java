package savingObjects;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Box implements Serializable{
	int width,height;
	public static int v=9;
	transient Duck d=new Duck();
	
	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public void f(){
		Box b=new Box();
		b.v=99;
		b.setWidth(10);
		b.setHeight(20);
		try {
			FileOutputStream fos=new FileOutputStream("box.ser");
			ObjectOutputStream oos=new ObjectOutputStream(fos);
			oos.writeObject(b);
			oos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

class Duck{}
