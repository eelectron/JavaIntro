package unionFind;

import java.io.*;
import java.util.Scanner;
public class QuickUnion {
	private static int[] id;
	public QuickUnion(int N){
		id=new int[N];
		for (int i = 0; i < N; i++) {
			id[i] = i;
		}
	}
	
	public static void main(String[] args) {
		QuickUnion quf = new QuickUnion(12);
		Scanner sc = new Scanner(System.in);
		String temp="";
		int v1,v2;
		
		while(true){
			System.out.println("Want to enter a num?Y/n:");
			temp  = sc.next();
			
			if(temp.equalsIgnoreCase("n"))
				break;
			
			System.out.println("\nEnter two vertices to connect:");
			v1=sc.nextInt();
			v2=sc.nextInt();
			
			//Connects two node
			if(!quf.isConnected(v1, v2))
				quf.quickUnion(v1, v2);
			
			QuickFind.printGraph(quf.id);
		}
	}
	
	private int root(int i){
		while(i != id[i])i=id[i];
		return i;
	}
	
	//Checks the connection of two devices
	public boolean isConnected(int p, int q){
		return root(p) == root(q);
	}
	
	public void quickUnion(int p, int q){
		int i = root(p);
		int j = root(q);
		id[i] = j;
	} 
}
