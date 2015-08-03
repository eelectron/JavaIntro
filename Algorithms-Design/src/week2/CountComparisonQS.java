package week2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CountComparisonQS {
    int[] a;
    int c;      //no. of comparisons in quick sort
                //170004    162085
                //167871    164123
                //145854    138382
    public CountComparisonQS(File file, int n){
        readInput(file, n);
        quicksort(a, 0, n-1);
        System.out.println("com = "+c);
        for (int i = 0; i < n; i++) {
            System.out.print(a[i]+" ");
        }
    }
    
    /*
     * Reads integer from file and put them in array.*/
    void readInput(File file, int n){
        try {
            //create array
            a = new int[n];
            Scanner s = new Scanner(file);
            
            //populate array
            for (int i = 0; i < n; i++) {
                a[i] = s.nextInt();
            }
            
        } catch (FileNotFoundException e) {
            System.err.println("File not Found!");
        }
    }
    
    void quicksort(int[] a1, int lo, int hi){
        if(lo >= hi)return;
        c = c + (hi - lo);    //count comparisons
        int med = median(a1, lo, hi);
        exch(a1, lo, med);
        int j = partition(a1, lo, hi);
        quicksort(a1, lo, j-1);
        quicksort(a1, j+1, hi);
    }
    
    /*
     * Takes a sub array and find final position of pivot element*/
    private int partition(int[] a1, int lo, int hi) {
        //pivot
        int p = a1[lo];  //first element as pivot element
        int j=lo+1;    //initial value of j which demarcate array
        
        //Iterate from lo+1 to hi index NOT 1-hi!
        for (int i = lo+1; i <= hi; i++) {
            if(a1[i] < p){
                exch(a1, i, j);
                j++;
            }
        }
        exch(a1, lo, j-1);
        return j-1;
    }
    
    private int median(int[] a, int lo, int hi) {
        int mid = (lo+hi)/2;
        int med = a[mid];
        
        //lo points to min, hi points to max
        if(a[lo] > a[hi]){
            int t = lo;
            lo = hi;
            hi = t;
        }
        
        if(med < a[lo])
            return lo;
        else if(med > a[hi])
            return hi;
        else
            return mid;
            
    }

    void exch(int[] a, int i, int j){
        int c = a[i];
        a[i] = a[j];
        a[j] = c;
    }
    
    public static void main(String[] args) {
        File f = new File(args[0]);
        CountComparisonQS c = new CountComparisonQS(f, 10000);
    }
}
