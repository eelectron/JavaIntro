

public class FlowEdge {
    private int v,w;                //from and to
    private final double capacity;  //capacity
    private double flow;            //flow
    
    public FlowEdge(int v, int w, double capacity){
        this.v = v;
        this.w = w;
        this.capacity = capacity;
    }
    
    public int from()           { return v;}
    public int to()             { return w;}
    public double capacity()    { return capacity;}
    public double flow()        { return flow;}
    
    public int other(int vertex){
        if      (vertex == v) return w;
        else if (vertex == w) return v;
        else throw new IllegalArgumentException();
    }
    
    public double residualCapacityTo(int vertex){
        if      (vertex == v) return flow;              //forward edge
        else if (vertex == w) return capacity - flow;   //backward edge
        else throw new IllegalArgumentException();
    }
    
    public void addResidualFlowTo(int vertex , double delta){
        if      (vertex == v) flow -= delta;
        else if (vertex == w) flow += delta;
    }
}
