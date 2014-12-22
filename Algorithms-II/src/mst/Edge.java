package mst;

//EDGE of weighted graph
public class Edge implements Comparable<Edge>{
	int v,w;		//denotes terminal of a edge
	double weight;
	
	public Edge(int v1,int w1,int weight1){
		v=v1;
		w=w1;
		weight=weight1;
	}
	
	public int either(){			//returns first vertex
		return v;
	}
	
	public int other(int vertex){	//returns second vertex
		if(vertex==v)return w;
		return v;
	}
	
	@Override
	public int compareTo(Edge otherEdge) {					//compare based on EDGE WEIGHT
		if(this.weight < otherEdge.weight)		return -1;
		else if(this.weight > otherEdge.weight)	return 1;
		else 									return 0;	//both are equal
	}
}
