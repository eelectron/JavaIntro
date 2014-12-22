package graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.Arrays;
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
		
		//Creates graph with v vertices without edges
		Graph G=new Graph(v);
		
		//Add edges to graph
		while(sc.hasNextInt()){
			G.addEdge(sc.nextInt(), sc.nextInt());
		}
		
		//Print adjacency list edge
		for (int i = 0; i < G.getVertexCount(); i++) {
			System.out.println(i+":"+G.adj(i));
		}
		
		//Find connected component of G
		CC cc=new CC(G);
		System.out.println(Arrays.toString(cc.getId()));
	}
}
