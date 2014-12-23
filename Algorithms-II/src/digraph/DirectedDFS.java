package digraph;
/*
 * Performs a Depth First Search on a Digraph(same as Undirected graph)
 * */
public class DirectedDFS {
	private boolean[] visited;		//for keeping track which vertex is visited and which is not
	
	public DirectedDFS(Digraph g, int s){ 	//START by taking a graph "g" and a source vertex s
		visited =new boolean[g.getV()];
		dfs(g,s);
	}

	private void dfs(Digraph g, int s) {
		visited[s]=true;
		for (int w : g.adj(s)) {
			if(!visited[w])
				dfs(g,w);
		}
	}
	
	//Checks whether a given vertex is visited is not
	public boolean isVisited(int v){
		return visited[v];
	}
}
