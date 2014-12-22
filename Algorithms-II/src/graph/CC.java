package graph;

public class CC {
	private boolean[] visited;	//initialize with size equal to NO. OF VERTICES in Graph 
	private int[] id;			
	private int count=0;
	int vertexCount ;
	public CC(Graph G){
		vertexCount = G.getVertexCount();
		visited = new boolean[vertexCount];
		id = new int[vertexCount];
		for (int v = 0; v < vertexCount; v++) {
			//run dfs on each vertex if not previously visited
			if(!visited[v]){
				dfs(G,v);
				count++;
			}	
		}
	}
	
	/*
	 * Perform dfs on "v" of "g".
	 * Mark it visited and perform dfs recursively 
	 * on vertex adjacent to "v" iff they are not previously visited
	 * */
	private void dfs(Graph g, int v) {
		visited[v]=true;
		id[v]=count;
		for (int av : g.adj(v)) {
			if(!visited[av])
				dfs(g, av);
		}
	}
	
	public boolean isConnected(int v1, int v2){
		//they are connected if id of both vertices is same
		return id[v1]==id[v2];
	}
	
	public int getCount(){
		return count+1;
	}
	
	public int[] getId(){
		return id;
	}
}