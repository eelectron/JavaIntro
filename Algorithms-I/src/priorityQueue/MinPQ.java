/*
 * Creates min pq of objects which MUST be comparable.
 * Elements start filling from index 1 to simplify some calc.*/
package priorityQueue;

import java.util.NoSuchElementException;

import elementarySort.Sort;

public class MinPQ<T extends Comparable<T>> {
	private T[] pq;
	private int N=0;
	
	//creates object with given capacity
	public MinPQ(int capacity){
		pq=(T[])new Comparable[capacity+1];
	}
	
	//Check status of pq
	public boolean isEmpty(){
		return N==0;
	}
	
	public int size(){
	    return N;
	}
	
	public T min(){
        if(isEmpty())
            throw new NoSuchElementException("PQ is empty.");
        return pq[1];
    }
	
	//Insert
	public void insert(T x){
	 // double size of array if necessary
        if (N >= pq.length - 1) resize(2 * pq.length);
        
		pq[++N]=x;
		swim(N);				//SWIM UP
	}
	
	private T[] temp;
	// helper function to double the size of the heap array
    private void resize(int capacity) {
        assert capacity > N;
        temp = (T[])new Comparable[capacity];
        for (int i = 1; i <= N; i++) temp[i] = pq[i];
        pq = temp;
    }
    
	//Delete MAX
	public T deleteMin(){
		if(isEmpty())
			return null;
		
		T min=pq[1];
		Sort.swap(pq, 1, N);
		N--;
		sink(1);				//SINK UP
		return min;
	}
	
	//////////////////////////////////////
	public boolean contains(T e ){
		for (int i = 1; i <= N; i++) {
			if(e.compareTo(pq[i]) == 0)
			    return true;
		}
		return false;
	}
	
	//Swip up 
	private void swim(int child){
		int parent = child/2;
		
		while(parent >= 1 && Sort.isLess(pq[child], pq[parent])){
			Sort.swap(pq, parent, child);
			
			//parent becomes child
			child=parent;
			
			//it's parent
			parent = child/2;
		}
	}
	
	//Sink the root to its correct place
	private void sink(int parent){
	    int child = 2*parent;
		while(child<= N){	
			if(child+1 <= N && Sort.isLess(pq[child+1], pq[child]))
				child++;
			
			//Break from loop if Parent is bigger than child
			if(Sort.isLess( pq[child], pq[parent]))
			    Sort.swap(pq, parent, child);
		
			parent=child;
			child = 2*parent;
		}
	}
}