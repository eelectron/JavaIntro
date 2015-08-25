package mst;

//Undirected edge weighted digraphs
import java.util.ArrayList;

public class EdgeWeightedGraph {
	private final int V;
	private final ArrayList<DirectedEdge>[] adj;
	
	public EdgeWeightedGraph(int V){
		this.V = V;
		adj = new ArrayList[V];
		for (int v = 0; v < V; v++) {
			adj[v]=new ArrayList<DirectedEdge>();
		}
	}
	
	public void addEdge(DirectedEdge e){
		int v=e.from(),w=e.to(v);
		adj[v].add(e);
		adj[w].add(e);
	}
	
	public Iterable<DirectedEdge> getAdj(int v){
		return adj[v];
	}
	
	public int getV(){
		return V;
	}
}
