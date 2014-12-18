package graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class GraphClient {
	public static void main(String[] args) {
		Scanner sc = null;
		try {
			sc = new Scanner(new FileReader(args[0]));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		int v=sc.nextInt();		//scans no. of vertex
		
		//creates graph with v vertices without edges
		Graph G=new Graph(v);
		
		//add edges to graph
		while(sc.hasNextInt()){
			G.addEdge(sc.nextInt(), sc.nextInt());
		}
		
		//print graphs edge
		for (int i = 0; i < G.getVertexCount(); i++) {
			for(int w: G.adj(i)){
				System.out.println(i+"--"+w);
			}
		}
	}
}
