package heapSort;

import elementarySort.Sort;

public class Heap {
	public static void sort(Comparable[] pq){
		//index of last element
		int N=pq.length-1;
		
		//First make a MAX heap of array
		for (int i = N/2; i >= 1; i--) {
			sink(pq, i, N);  //starts to sink every element from 2nd last level to 0th level.
		}
	
		while(N > 1){
			
			//shift max item at end of array
			Sort.swap(pq, 1, N);
			N--;
			
			//keep remaing item in Max heap order
			sink(pq, 1, N);
		}
	}
	
	//Sink the root to its correct place
	private static void sink(Comparable[] pq, int parent, int N){
		while(2*parent<= N){
			int child = 2*parent;
			if(child+1 <= N && Sort.isLess(pq[child], pq[child+1]))
				child++;
			
			//Break from loop if Parent is bigger than child
			if(!Sort.isLess(pq[parent], pq[child]))
				break;
			
			Sort.swap(pq, parent, child);
			parent=child;
		}
	}
}
