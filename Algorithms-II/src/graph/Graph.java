package graph;

import java.util.LinkedList;

public class Graph {
	private final int noOfVertex;		//no. of vertex
	private LinkedList<Integer>[] adj;  //adjacency array holds vertex adjacent to it
	
	//constructor
	public Graph(int totalVertex){
		noOfVertex=totalVertex;
		adj = new LinkedList[noOfVertex];			//create empty array of linkList
		for (int v = 0; v < noOfVertex; v++) {		//initially we have no vertex adjacent to any vertex 
			adj[v]=new LinkedList<Integer>();
		}
	}
	
	//parallel edge allowed
	public void addEdge(int v, int w){
		adj[v].add(w);
		adj[w].add(v);
	}
	
	//iterator for vertices adjacent to v
	public Iterable<Integer> adj(int v){
		return adj[v];
	}
	
	public int getVertexCount(){
		return noOfVertex;
	}
}
