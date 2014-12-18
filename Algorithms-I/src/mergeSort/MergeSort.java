package mergeSort;

import elementarySort.Sort;

public class MergeSort {
	
	public void sort(Comparable[] a){
		Comparable[] aux = new Comparable[a.length];
		divide(a,aux,0,a.length-1);
	}
	
	public void divide(Comparable[] a, Comparable[] aux, int lo, int hi){
		if(lo>=hi)return;
		
		//find mid point of array index
		int mid=(lo+hi)/2;
		
		//divides right half
		divide(a, aux, lo, mid);
		
		//divides left half
		divide(a, aux, mid+1, hi);
		
		//sort and merge in a[]
		mergeSort(a,aux,lo,mid,hi);
	}
	
	public void mergeSort(Comparable[] a,Comparable[] aux, int lo, int mid, int hi){
		//tracks position in a[]
		int k;
		
		//copy elements to aux[]
		for(k=lo; k<=hi; k++)
			aux[k]=a[k];
		
		int i=lo,j=mid+1;
		for(k=lo; k<=hi; k++){
			if(i>mid)	 					a[k]=aux[j++];
			else if(j>hi)					a[k]=aux[i++];
			else if(less(aux[j], aux[i])) 	a[k]=aux[j++];
			else							a[k]=aux[i++];
		}
	}
	
	public boolean less(Comparable a, Comparable b){
		if(a.compareTo(b) < 0) 	return true;
		else					return false;
	}
}