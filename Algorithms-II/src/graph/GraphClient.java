package graph;

import java.util.Scanner;

public class GraphClient {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int v=sc.nextInt();		//scans no. of vertex
		
		//creates graph with v vertices without edges
		Graph G=new Graph(v);
		
		//add edges to graph
		while(sc.hasNext()){
			G.addEdge(sc.nextInt(), sc.nextInt());
		}
		
		//print graph
		for (int i = 0; i < G; i++) {
			
		}
	}
}
