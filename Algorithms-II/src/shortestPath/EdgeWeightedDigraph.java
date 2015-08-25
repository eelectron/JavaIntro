package shortestPath;
//Undirected edge weighted digraphs
import java.util.ArrayList;

import mst.DirectedEdge;

public class EdgeWeightedDigraph {
	private final int V;
	private final ArrayList<DirectedEdge>[] adj;
	
	public EdgeWeightedDigraph(int V){
		this.V = V;
		adj = new ArrayList[V];
		for (int v = 0; v < V; v++) {
			adj[v]=new ArrayList<DirectedEdge>();
		}
	}
	
	//Directed edges !
	public void addEdge(DirectedEdge e){
		int u=e.from();
		int v=e.to();
		adj[u].add(e);
		DirectedEdge ne=new DirectedEdge(v, u, e.weight());
		adj[v].add(ne);
	}
	
	public Iterable<DirectedEdge> getAdj(int v){
		return adj[v];
	}
	
	public int getV(){
		return V;
	}
}
