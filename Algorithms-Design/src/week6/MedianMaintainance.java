/*
 * Here we implement a program for MedianMaintainance using a already known
 * cool data structure - Max heap and Min Heap.*/
package week6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import priorityQueue.MaxPQ;
import priorityQueue.MinPQ;

public class MedianMaintainance {
    private MaxPQ<Integer> hlow = new MaxPQ<Integer>(1);
    private MinPQ<Integer> hhigh = new MinPQ<Integer>(1);
    int medianSum;
    public MedianMaintainance(File file) throws FileNotFoundException{
        Scanner sc = new Scanner(file);
        while(sc.hasNext()){
            insert(sc.nextInt());
            balanceHeap();
            medianSum += median();
        }
        System.out.println(medianSum%10000);
    }
    private int balanceFactor() {
        int x = hlow.size() - hhigh.size();
        if (x == 2)
            return 1; // hlow has 2 more item
        else if (x == -1)
            return -1; // hhigh has
        return 0; // equal
    }

    public int median() {
        return hlow.max();
    }

    public void insert(int x) {
        if (hhigh.isEmpty() ||x < hhigh.min())
            hlow.insert(x);
        else
            hhigh.insert(x);
    }

    private void balanceHeap() {
        int x=balanceFactor();
        if (x == 1)
            hhigh.insert(hlow.deleteMax());
        else if(x==-1)
            hlow.insert(hhigh.deleteMin());
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        File file=new File(args[0]);
        MedianMaintainance mm =new MedianMaintainance(file);
        
    }
}
