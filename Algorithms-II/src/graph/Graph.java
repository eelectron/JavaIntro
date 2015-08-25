package graph;
/*Undirected Graph*/
import java.util.ArrayList;
import java.util.LinkedList;

public class Graph {
	private final int              V;	//no. of vertex
	private ArrayList<Integer>[]   adj; //adjacency list
    
	//CONSTRUCTOR
	public Graph(int vertexCount){
		V = vertexCount;
		adj = new ArrayList[V];			//creates empty adjacency list
		for (int v = 0; v < V; v++) {		 
			adj[v]=new ArrayList<Integer>();	//vertex holder's
		}
	}
	
	//Used to add undirected edge to this graph's instance
	public void addEdge(int v, int w){
	    adj[v].add(w);
		adj[w].add(v);
	}
	
	//vertices adjacent to v
	public Iterable<Integer> adj(int v){
		return adj[v];
	}
	
	public int V(){
		return V;
	}
	
	/*Returns a list of edges in the graph.
	 * */
	public Iterable<Edge> edges(){
	    //Stores edge
	    ArrayList<Edge> edges = new ArrayList<Edge>();
	    
	    //Iterate over vertices and create edge 
	    //iff v < w to avoid duplicate edge 1-2 and 2-1
	    for (int v = 0; v < V; v++) {
            for (int w : adj(v)) {
                if(v < w)
                   edges.add(new Edge(v,w));
            }
        }
	    return edges;
	}
}