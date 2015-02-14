package priorityQueue;

import elementarySort.Sort;

@SuppressWarnings("hiding")
public class UnorderedMaxPQ<Key extends Comparable<Key>> {
	private Key[] pq;
	private int N;
	
	@SuppressWarnings("unchecked")
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
		int maxIndex=0;
		
		for (int i = 1; i < N; i++) {
			if(isLess(maxIndex, i))
				maxIndex=i;
		}
		
		Sort.swap(pq, maxIndex, N-1);			//move the max element to last position
		return pq[--N];
	}

	private boolean isLess(int max, int i) {
		if(pq[max].compareTo(pq[i]) < 0)
			return true;
		return false;
	}
}
