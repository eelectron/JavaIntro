package week6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.TreeSet;

public class TwoSum {
    long size ;  //size of array     
    Node[] ht ;  //array of long int
    boolean[] target = new boolean[20001];
    TreeSet<Integer> count =new TreeSet<Integer>(); //count=427
    //creates and populates array
    public TwoSum(int size, File file) throws FileNotFoundException{
        this.size = size;
        ht = new Node[size];
        
        //create and populate array from file
        Scanner sc = new Scanner(file);
        long x,i=0;
        while(sc.hasNext()){
            x=sc.nextLong();
            insert(x);
            System.out.println(i+" "+x);
            i++;
        }
        
        findTwoSum();
    }
    
    
    private class Node{
        long data;
        Node next;  
        Node(long data){
            this.data = data;
        }
    }
    
    /*
     * @return array index for storing x*/
    int hash(long x){
        int v=0;
        //hash -ve values in upper half of ht
        if(x<0){
            x = -1*x;
            return (int)((x/10000)%(size/2));
        }
        else{
            v = (int)((x/10000)%size);  
            if(v < size/2)      //+ve value in 2nd half of ht
                v += size/2;
            return v;
        }      
    }
    
    /*
     * Inserts a node in HASHTABLE.Skip if already present.*/
    boolean insert(long x){
        int i=hash(x);
        //if x already present then dont add
        for (Node j = ht[i]; j != null; j=j.next) {
            if(j.data == x){
                return false;
            }
        }
        Node node = new Node(x);
        node.next = ht[i];
        ht[i] = node;
        return true;
    }
    
    
    /*
     * @return true if a pair is present whose sum is equal to tgt.*/
    private boolean findTwoSum() {
        long x=0,y=0;
        //loop over hash table
        for (int i = 0; i < size/2; i++) {
            //loop over node list
            for(Node n=ht[i]; n != null; n = n.next){
                x=n.data;
                //find bucket of -x
                x = -1*x;
                findAndCheckBucket(x);
            }
        }
        return true;
    }
    
    
    private void findAndCheckBucket(long x) {
        int i = hash(x);        //onlyb possible matches are i-1,i,i+1
        int t=0;
        for(int j = i-1; j<= i+1 && j<size; j++){
            for(Node n = ht[j]; n!=null; n = n.next){
                t = (int)(n.data - x); 
                if(t>=-10000 && t<=10000){
                    System.out.println("target= "+t+",x= "+n.data+"y= -"+x);
                    count.add(t);
                }
                    
            }
        }
        
    }

    public boolean search(long y) {
        int i=hash(y);
        for(Node n=ht[i]; n!=null; n = n.next){
            if(n.data == y)
                return true;
        }
        return false;
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File(args[0]);
        TwoSum ts = new TwoSum(1000000, file);
        System.out.println("count= "+ts.count.size());
    }
}
