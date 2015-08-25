package mst;

//EDGE of weighted graph
public class DirectedEdge implements Comparable<DirectedEdge> {
    int v, w; // endpoint of a edge
    int weight;

    public DirectedEdge(int u, int v, int weight) {
        this.v = u;
        this.w = v;
        this.weight = weight;
    }

    public int from() { // returns first vertex
        return v;
    }

    public int to() { // returns second vertex
        return w;
    }
    
    public int weight(){
        return weight;
    }
    
    public String toString(){
        return v+"->"+w;
    }
    @Override
    public int compareTo(DirectedEdge e) { // compare based on EDGE
                                                   // WEIGHT
        if (this.weight < e.weight)
            return -1;
        else if (this.weight > e.weight)
            return 1;
        else
            return 0; // both are equal
    }
}
