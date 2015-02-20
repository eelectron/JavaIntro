package graph;

import java.util.ArrayList;
import java.util.LinkedList;

public class Graph {
	private final int totalVertex;		//no. of vertex
	private ArrayList<Integer>[] adj;  //adjacency list
	
	//constructor
	public Graph(int vertices){
		totalVertex=vertices;
		adj = new ArrayList[totalVertex];			//creates empty adjacency list
		for (int v = 0; v < totalVertex; v++) {		 
			adj[v]=new ArrayList<Integer>();		//put vertex holder's
		}
	}
	
	//Used to add undirected edge to this graph's instance
	public void addEdge(int v, int w){
		adj[v].add(w);
		adj[w].add(v);
	}
	
	//iterator for vertices adjacent to v
	public Iterable<Integer> adj(int v){
		return adj[v];
	}
	
	public int getVertexCount(){
		return totalVertex;
	}
}