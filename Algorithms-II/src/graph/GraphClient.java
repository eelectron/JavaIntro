package graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

import digraph.Digraph;

public class GraphClient {
	public static void main(String[] args) {
		Scanner sc = null;
		try {
			sc = new Scanner(new FileReader(args[0]));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		int v=sc.nextInt();		//Take vertex count from input FILE
		
		//Creates a empty graph
		Digraph G=new Digraph(v);
		
		//Add edges to graph
		while(sc.hasNextInt()){
			G.addEdge(sc.nextInt(), sc.nextInt());
		}
		
		//Print graph
		for (int i = 0; i < G.getV(); i++) {
			System.out.println(i+":"+G.adj(i));
		}
		
		System.out.println();
		
		//Prints reverse graph 
		Digraph g=G.reverse();
		//Print graph
		for (int i = 0; i < G.getV(); i++) {
			System.out.println(i+":"+ g.adj(i));
		}
		
//		BreadthFirstPath bfs=new BreadthFirstPath(G, 0);
//		bfs.printDistanceArray();
//		bfs.breadthFirstPath();
		//Find connected component of G
//		CC cc=new CC(G);
//		System.out.println(Arrays.toString(cc.getId()));
	}
}
