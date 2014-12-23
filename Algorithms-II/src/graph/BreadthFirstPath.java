package graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import digraph.Digraph;

public class BreadthFirstPath {
	private boolean[] visited;
	private int[] parent;
	private int[] distTo;
	private int s;
	
	public  BreadthFirstPath(Digraph G,int s){
		//initialize all three arrays
		int n=G.getV();
		visited=new boolean[n];
		parent=new int[n];
		distTo=new int[n];
		LinkedList<Integer> q=new LinkedList<Integer>();		//queue to store visited vertex
		q.add(s);												//add the source to queue
		visited[s]=true;										//mark it visited
		while(!q.isEmpty()){
			int v=q.removeFirst();			//get first element from queue
			for(int w: G.adj(v)){			//iterate over vertices adjacent to v
				if(!visited[w]){			
					q.add(w);
					visited[w]=true;
					parent[w]=v;
					distTo[w]=distTo[v]+1;	//distance of a vertex is one more than its parent
				}
			}
		}
	}
	public void printDistanceArray(){
		System.out.println(Arrays.toString(distTo));
	}
	
	//Tells whether a vertex is visited ?
	public boolean isVisited(int v){
		return visited[v];
	}
}
