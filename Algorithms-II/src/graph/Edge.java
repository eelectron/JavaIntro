package graph;

public class Edge {
    int u,v;
    public Edge(int u,int v){
        this.u = u;
        this.v = v;
    }
    
    public int from(){
        return u;
    }
    
    public int to(){
        return v;
    }
    
    public String toString(){
        return u+"-"+v;
    }
}

