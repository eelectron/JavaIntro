package week1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CountInversion {
    int[] a = new int[2];   //resizing array
    int[] aux ;             //auxillary array
    int size = 0;
    long inversions = 0;
    
    public CountInversion(File file){
        //create array from given file
        Scanner sc = null;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.err.println("File not found!!");
        }
        while(sc.hasNextInt()){
            
            if(size == a.length)
                resize(2*a.length);
            a[size] = sc.nextInt();
            size++;
        }
        
        //aux array created
        aux = new int[size];
        
        //count
        count(a);
        
        //print it
        System.out.println("Inversions = "+ inversions);
    }
    
   
    public long count(int[] a){
        return count(a, 0, size - 1);
    }
    
    private long count(int[] a, int lo, int hi) {
        //BASE CASE
        if(lo >= hi)
            return 0;
        
        int mid = (lo + hi)/2;
        
        count(a, lo, mid);
        count(a, mid+1, hi);
        countInversions(a, lo, hi);
        
        return inversions;
    }

    private void countInversions(int[] a, int lo, int hi) {
        int i=0;
        //copy to aux array
        for (i = lo; i <= hi; i++) {
            aux[i] = a[i];
        }
        
        int mid = (lo+hi)/2;
        i = lo;
        int j = mid + 1;
        
        for (int k = lo; k <= hi; k++) {
            if(i > mid)                 a[k] = aux[j++];
            else if(j > hi)             a[k] = aux[i++];
            else if(aux[i] <= aux[j])   a[k] = aux[i++];
            else{                                      //inversion occur
                inversions += (mid + 1) - i;
                a[k] = aux[j++];
            }
        }
    }

    //Resizes array
    private void resize(int capacity) {
        int[] copy = new int[capacity];   //new array of double capacity
        for (int i = 0; i < a.length; i++) {
            copy[i] = a[i];
        }
        a = copy;
    }
    
    //Unit testing 
    public static void main(String[] args) {
        File file = new File(args[0]);
        System.out.println(args[0]);
        CountInversion ci = new CountInversion(file);
    }
}