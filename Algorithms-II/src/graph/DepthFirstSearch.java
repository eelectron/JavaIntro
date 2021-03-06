package graph;

import java.util.Stack;

public class DepthFirstSearch {
	private boolean[] marked;	//stores visited status of  vertices
	private int[] edgeTo;		//edges adjacent to vertices
	private int s;				//source vertex of given graph
	
	//constructor
	public DepthFirstSearch(Graph G, int s){
		dfs(G,s);
	}
	
	private void dfs(Graph G,int v){
		marked[v]=true;
		for(int w: G.adj(v)){
			if(!marked[w]){
				dfs(G,w);
				edgeTo[w]=v;
			}
		}
	}
	
	public boolean hasPathTo(int v){
		return marked[v];
	}
	
	public Iterable<Integer> pathTo(int v){
		if(!hasPathTo(v))return null;			//vertex can't be reached from source
		Stack<Integer> path=new Stack<Integer>();
		for (int i = v; i != s ; i=edgeTo[i]) {
			path.add(i);
		}
		//push source vertex on top
		path.push(s);
		return path;
		
	}
}
