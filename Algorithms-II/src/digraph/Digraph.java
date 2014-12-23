package digraph;

import java.util.LinkedList;

public class Digraph {
	private final int V;
	private final LinkedList<Integer>[] adj;
	
	public Digraph(int V){
		this.V = V;
		adj=new LinkedList[V];
		for (int v = 0; v < V; v++) {
			adj[v]=new LinkedList<Integer>();
		}
	}
	
	public void addEdge(int v,int w){
		adj[v].add(w);
	}
	
	public Iterable<Integer> adj(int v){
		return adj[v];
	}
	
	public int getV(){
		return V;
	}
}
