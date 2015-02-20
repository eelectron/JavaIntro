package digraph;

import java.util.LinkedList;

public class Digraph {
	private final int V;
	
	//Graph is represented by Adjacency List
	private final LinkedList<Integer>[] adj;
	
	public Digraph(int V){
		this.V = V;
		adj=new LinkedList[V];
		for (int v = 0; v < V; v++) {
			adj[v]=new LinkedList<Integer>();
		}
	}
	
	//Add directed edges to graph
	public void addEdge(int v,int w){
		adj[v].add(w);
	}
	
	//Get adjacent vertices of a given vertex 
	public Iterable<Integer> adj(int v){
		return adj[v];
	}
	
	public int getV(){
		return V;
	}
	
	//Returns a new graph which is reverse of this one
	public Digraph reverse(){
		/*
		 * Get the adjacent vertices of current vertex and 
		 * add the current vertex to their list and remove the 
		 * adj vertex from current vertex's list*/
		Digraph g=new Digraph(V);
		for (int v = 0; v < V; v++) {
			for (int  w : adj[v]) {
				g.addEdge(w, v);
			}
		}
		
		//Returns 
		return g;
	}
}