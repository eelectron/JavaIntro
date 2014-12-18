package unionFind;

import java.util.Scanner;

public class WeightedQuickUnion {
	//id array's index represents the node and its value represent its parent
	private int[] id;
	private int[] sz;
	
	public WeightedQuickUnion(int N){
		//create and initialize id[]
		id=new int[N];
		for (int i = 0; i < N; i++) {
			id[i] = i;
		}
		
		//create and initialize id[]
		sz=new int[N];
		for (int i = 0; i < N; i++) {
			sz[i] = 1;
		}
	}
	
	public static void main(String[] args) {
		WeightedQuickUnion wqu = new WeightedQuickUnion(7);
		Scanner sc = new Scanner(System.in);
		String temp="";
		int v1,v2;
		
		while(true){
			System.out.println("Want to enter a num?y/n:");
			temp  = sc.next();
			
			if(temp.equalsIgnoreCase("n"))
				break;
			
			System.out.println("\nEnter two vertices to connect:");
			v1=sc.nextInt();
			v2=sc.nextInt();
			
			//Connects two node
			if(!wqu.isConnected(v1, v2))
				wqu.weightedQuickUnion(v1, v2);
			
			QuickFind.printGraph(wqu.id);
		}
	}
	
	//Finds root of a given node
	private int root(int i){
		while(i != id[i])i=id[i];
		return i;
	}
	
	//Checks the connection of two nodes(PC or vertices)
	public boolean isConnected(int p, int q){
		return root(p) == root(q);
	}
	
	public void weightedQuickUnion(int p, int q){
		int i = root(p);
		int j = root(q);
		
		//there are more objects with root i
		if(sz[i] > sz[j]){
			//set i(it has more objects) as parent of j
			id[j] = i;
			sz[i] += sz[j];
			
		}
		else{
			id[i]=j;
			sz[j] += sz[i];
		}
	} 
}
