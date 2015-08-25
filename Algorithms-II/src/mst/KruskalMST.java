package mst;

import java.util.ArrayList;

import priorityQueue.MinPQ;

public class KruskalMST {
	private ArrayList<DirectedEdge> mst=new ArrayList();
	
	public KruskalMST(EdgeWeightedGraph g){
		MinPQ<DirectedEdge> pq=new MinPQ(g.getV());
		for (int v = 0; v < g.getV(); v++) {
			for (Edge e : g.getAdj(v)) {
				if()
			}
		}
	}
	
	public Iterable<DirectedEdge> getEdges(){
		return null;
	}
}
