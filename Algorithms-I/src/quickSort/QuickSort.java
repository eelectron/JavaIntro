package quickSort;

public class QuickSort {
	
	public void quickSort(Comparable[] a,int lo, int hi){
		
		//BASE CASE
		if(lo >= hi)return;
		
		//intializing partition counter
		int partitionCounter=lo;
		Comparable pivot = a[lo];
		
		for (int i = lo+1; i <= hi; i++) {
			if(isLess(a[i], pivot))	
				swap(a, i, ++partitionCounter);
		}
		
		//swapping pivot to its correct position
		swap(a, lo, partitionCounter);
		
		//sort element present left to partion
		quickSort(a, lo, partitionCounter-1);
		
		//sort element present right to partion
		quickSort(a, partitionCounter+1, hi);
	}
	
	//Checks whether first "COMPARABLE object" is less than second or not
	boolean isLess(Comparable a, Comparable b){
		if(a.compareTo(b) < 0)
			return true;
		else 
			return false;
	}
	
	void swap(Comparable[] a, int i, int j){
		Comparable x=a[i];
		a[i]=a[j];
		a[j]=x;
	}
}
