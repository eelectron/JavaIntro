package priorityQueue;

import elementarySort.Sort;

public class UnorderedMaxPQ<Key extends Comparable<Key>> {
	private Key[] pq;
	private int N;
	
	public UnorderedMaxPQ(int capacity){
		pq=(Key[])new Comparable[capacity];
	}
	
	public boolean isEmpty(){
		return N==0;
	}
	
	public void insert(Key k){
		pq[N++]=k;
	}
	
	public Key delMax(){
		//initially assume 0th position has max element
		int max=0;
		
		for (int i = 0; i < N; i++) {
			if(isLess(max, i))
				max=i;
		}
		
		//move the max element to last position
		Sort.swap(pq, max, N-1);
		return pq[--N];
	}

	private boolean isLess(int max, int i) {
		if(pq[max].compareTo(pq[i]) < 0)
			return true;
		
		return false;
	}
}
