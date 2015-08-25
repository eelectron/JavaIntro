package maxflow;

public class FordFulkerson {
    private boolean[] marked;
    private FlowEdge[] edgeTo;  
    private double value;       //value of flow
    
    public FordFulkerson(FlowNetwork G, int s, int t){
        value = 0;
    }
    
    public boolean hasAugmentingPath(FlowNetwork G, int s, int t){
        
    }
    public double value(){
        return value;
    }
    
    public boolean inCut(int v){
        return marked[v];
    }
}
