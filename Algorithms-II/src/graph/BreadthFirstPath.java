package graph;

import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstPath {
	private boolean[] isVisited;
	private int[] edgeTo;
	private int s;
	
	private void bfs(Graph G,int s){
		LinkedList<Integer> q=new LinkedList<Integer>();		//queue to store visited vertex
		q.add(s);									//add the source to queue
		isVisited[s]=true;
		while(!q.isEmpty()){
			int v=q.getFirst();			//get first element in queue
			for(int w: G.adj(v)){		//iterate over vertices adjacent to v
				if(!isVisited[w]){			
					q.add(w);
					isVisited[w]=true;
					edgeTo[w]=v;
				}
			}
		}
	}
}
