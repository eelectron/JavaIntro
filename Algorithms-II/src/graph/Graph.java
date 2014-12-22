package graph;

import java.util.LinkedList;

public class Graph {
	private final int totalVertex;		//no. of vertex
	private LinkedList<Integer>[] adj;  //adjacency array holds vertex adjacent to each index
	
	//constructor
	public Graph(int vertices){
		totalVertex=vertices;
		adj = new LinkedList[totalVertex];			//create empty array of linkList
		for (int v = 0; v < totalVertex; v++) {		//initially we have no vertex adjacent to any vertex 
			adj[v]=new LinkedList<Integer>();		//attach a empty list to each vertex(ie. index)
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