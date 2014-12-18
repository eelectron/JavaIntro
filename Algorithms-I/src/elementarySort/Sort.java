package elementarySort;

import java.awt.Point;

public class Sort {
	public static int COMPARES = 0, SWAPS = 0;

	// Shell sort for object that implements Comparable interface
	public static void shellSort(Comparable[] a) {
		int N = a.length;
		//run till gap between items is greater than 0
		for (int gap = N/2; gap > 0; gap /= 2) {
			for (int p = gap; p < N; p++) {
				for (int i = p; (i > 0) && (i-gap >=0); i = i - gap) {
					//the current item should be compared with the item which is 
					//always at distance of "gap" before it
					if(compare(a[i], a[i-gap]) < 0)
						swap(a, i, i-gap);
				}
			}
		}
	}
	

	// Insertion Sort
	public static void insertionSort(Comparable[] a) {
		int N = a.length;
		for (int i = 0; i < N; i++) {
			for (int j = i; j > 0; j--) {
				COMPARES++;
				if (a[j].compareTo(a[j - 1]) < 0)
					swap(a, j, j - 1);
				else
					break;
			}
		}
	}

	// Selection sort
	public static void selectionSort(Comparable[] a) {
		int N = a.length, i = 0;

		// outer loop will execute for N-1 times
		for (int j = 0; j < N; j++) {
			// initially starting index has minimum item
			int min = j;
			for (i = j + 1; i < N; i++) {
				if (a[i].compareTo(a[min]) < 0)
					// make i the new min
					min = i;
			}

			// swap the min element with first element
			if (min != j) {
				swap(a, j, min);
			}
		}
	}
	
	//compare
	public static int compare(Comparable a, Comparable b){
		return a.compareTo(b); 
	}
	
	public static boolean isLess(Comparable parent, Comparable child) {
		
		return (parent.compareTo(child) < 0)?true:false;
	}
	// Swaps two item
	public static void swap(Comparable[] a, int i, int j) {
		Comparable c = a[i];
		a[i] = a[j];
		a[j] = c;
		SWAPS++;
	}
}