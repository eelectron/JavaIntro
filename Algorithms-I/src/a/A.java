package a;

import heapSort.Heap;

import java.io.File;
import java.util.Arrays;
import java.util.Stack;

import javax.swing.Box;

import priorityQueue.Key;
import priorityQueue.MaxPQ;

import quickSort.QuickSort;

import unionFind.QuickFind;

import elementarySort.ConvexHull;
import elementarySort.MyPoint;
import elementarySort.Sort;

public class A {

	public static void main(String[] args) {
		Integer[] x={0,10,1,8};
		Heap.sort(x);
		
		System.out.println(Arrays.toString(x));
	}
}
