package digraph;
//Computes Topological Order of a graph

import java.util.ArrayList;
import java.util.Stack;
import java.util.Vector;

public class DepthFirstOrder {
    private boolean[] visited;
    private Stack<Integer> reversePost = new Stack<Integer>();;

    public DepthFirstOrder(Digraph g) {
        visited = new boolean[g.V()]; // init

        // perform DFS on each vertex if not already marked
        for (int v = 0; v < g.V(); v++) {
            if (!visited[v])
                dfs(g, v);
        }
    }

    private void dfs(Digraph g, int v) {
        visited[v] = true;
        for (int w : g.adj(v)) {
            if (!visited[w])
                dfs(g, w);
        }

        //push v on stack only when all reachable vetices are visited
        reversePost.push(v);
    }

    // Get the vertex in topological order
    public Iterable<Integer> reversePost() {
        return reversePost;
    }
}
