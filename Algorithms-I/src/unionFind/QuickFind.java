package unionFind;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;


public class QuickFind {
	private static int[] id;
	public QuickFind(int N){
		
		id = new int[N];
		
		//initially no node are connected
		for(int i=0; i<N; i++){
			id[i]=i;
		}
	}
	public static void main(String[] args) throws IOException{
		QuickFind uf =new QuickFind(10);
		
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
			if(!uf.isConnected(v1, v2))
				uf.union(v1, v2);
			
			uf.printGraph(id);
		}
	}
	
	public boolean isConnected(int p, int q){
		return (id[p] == id[q]);
	}
	
	public void union(int p,int q){
		int pid = id[p];
		int qid = id[q];
		for(int i=0; i<id.length; i++){
			if(id[i]==pid)
				id[i]=qid;
		}
	}
	
	//Prints status of graph
	public static void printGraph(int[] id){
		for (int i = 0; i < id.length; i++) {
			System.out.print(i+"  ");
		}
		System.out.println();
		for (int i = 0; i < id.length; i++) {
			System.out.print("| "+id[i]);
		}
		System.out.println("|");
	}
}
