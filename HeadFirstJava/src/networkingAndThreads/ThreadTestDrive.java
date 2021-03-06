package networkingAndThreads;

import java.util.ArrayList;
import java.util.List;

public class ThreadTestDrive {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<Animal> a=new ArrayList<Animal>();
		ThreadOne one=new ThreadOne();
		ThreadTwo two=new ThreadTwo();
		Thread t1 = new Thread(one);
		Thread t2 = new Thread(two);
		t1.start();
		t2.start();
	}
}

//OBJECT can't be made of this class bcoz its having private constructor!
class Accum{
	private static Accum a = new Accum();
	private int counter=0;
	
	private Accum(){}
	
	public static Accum getAccum(){return a;}			
	
	public void updateCounter(int add){counter += add;}
	
	public int getCount(){return counter;}
	
	
}

class ThreadOne implements Runnable{
	Accum a = Accum.getAccum();
	@Override
	public void run() {
		for (int i = 0; i < 98; i++) {
			a.updateCounter(1000);
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("one"+a.getCount());
	}
}

class ThreadTwo implements Runnable{
	Accum a = Accum.getAccum();
	@Override
	public void run() {
		for (int i = 0; i < 99; i++) {
			a.updateCounter(1000);
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("two"+a.getCount());
	}
}

class Animal{}
class Dog extends Animal{}