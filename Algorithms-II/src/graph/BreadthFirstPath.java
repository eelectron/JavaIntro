package graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import digraph.Digraph;
/*
 * Performs a Breadth First Search on a given Graph and a source vertex
 * */
public class BreadthFirstPath {
	private boolean[] visited;
	private int[] parent;
	private int[] distTo;
	private int s,v;
	LinkedList<Integer> q;
	
	//PLEASE provide a Graph and a Source vertex from where we start BFS
	public  BreadthFirstPath(Digraph G,int s){
		//vertex count in given Digraph
		int n=G.getV();
		
		//initialize all three arrays
		visited=new boolean[n];
		parent=new int[n];
		distTo=new int[n];
		
		//QUEUE to store visited vertex
		q=new LinkedList<Integer>();		
		
		//add the source vertex to queue
		q.add(s);				
		
		//mark it visited
		visited[s]=true;
		
		while(!q.isEmpty()){
			v=q.removeFirst();			//get first element from queue
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
	
	/*
	 * Prints a breadth first path*/
	public void breadthFirstPath(){
		System.out.println(q);
	}
}
