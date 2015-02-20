package priorityQueue;

import elementarySort.Sort;

public class MinPQ<Edge extends Comparable<Edge>> {
	private Edge[] pq;
	private int N=0;
	
	//CONSTRUCTOR
	public MinPQ(int capacity){
		pq=(Edge[])new Comparable[capacity+1];
	}
	
	//Check status of pq
	public boolean isEmpty(){
		return N==0;
	}
	
	//Insert
	public void insert(Edge x){
		pq[++N]=x;
		swim(N);				//SWIM UP
	}
	
	//Delete MAX
	public Edge deleteMax(){
		if(isEmpty())
			return null;
		
		Edge max=pq[1];
		Sort.swap(pq, 1, N);
		N--;
		sink(1);				//SINK UP
		return max;
	}
	
	//////////////////////////////////////
	public boolean contains(Edge e ){
		for (int i = 0; i < N; i++) {
			
		}
		return false;
	}
	
	//Swip up 
	private void swim(int child){
		int parent = child/2;
		
		while(parent >= 1 && Sort.isLess(pq[child], pq[parent])){
			Sort.swap(pq, parent, child);
			
			//child becomes parent
			child=parent;
			
			//it's parent
			parent = child/2;
		}
	}
	
	//Sink the root to its correct place
	private void sink(int parent){
		while(2*parent<= N){
			
			int child = 2*parent;
			if(child+1 <= N && Sort.isLess(pq[child+1], pq[child]))
				child++;
			
			//Break from loop if Parent is bigger than child
			if(Sort.isLess(pq[parent], pq[child]))
				break;
			
			Sort.swap(pq, parent, child);
			parent=child;
		}
	}
}