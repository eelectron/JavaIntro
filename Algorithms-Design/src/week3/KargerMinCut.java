package week3;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;
import java.util.TreeSet;

import graph.*;
//Mincut has to be <= 20
public class KargerMinCut {
    private int                     vc;         //vertex count
    private int                     value = 0;     //no. of edges in min cut
    private TreeSet<Integer>[]    equalTo;    
    private ArrayList<Edge>         edges;      //list of edges
    private boolean[]               removed;
    private Graph                   cg;
    private int                     U,V;        //remaining two edges
    
    
    public KargerMinCut(Graph g){
        vc = g.V();
        //create a new graph
        this.cg = new Graph(vc);
        for (int i = 0; i < vc; i++) {
            for(int v: g.adj(i))
                if(v > i)
                    cg.addEdge(i, v);
        }
        //initialize
        removed = new boolean[vc];
        edges   = (ArrayList) g.edges();
        equalTo = new TreeSet[vc];
        for (int i = 0; i < vc; i++) {
            equalTo[i] = new TreeSet<Integer>();
        }
        
        
        //begin
        contract();
        vertexRemain();
        settle(U);
        settle(V);
        cutEdges();
    }
    
    
    /*Randomly select a Edge and contract it.*/
    public void contract(){
        int vertexCount = vc;
        Random r = new Random();
        
        while(vertexCount > 2){
            //select a random edge
            Edge e = (Edge)edges.remove(r.nextInt(vertexCount));
            System.out.println(e);
            
            //remove u
            int u = e.from();
            int v = e.to();
            
            //self loop does not count towards min cut
            boolean sl = isSelfLoop(equalTo[u], equalTo[v]);
            if(sl){
                if(!removed[u]){
                    removed[u] = true;
                    
                }
                else if(!removed[v]){
                    removed[v] = true;
                }
            }else{
                if(!removed[u]){
                    removed[u] = true;
                   
                }
                else if(!removed[v]){
                    removed[v] = true;
                    
                }
//                else{
//                    //means both end points merge with other two vertices
//                    //so remve any one of those.
//                    boolean rmv = remove(equalTo[u]);
//                    if(!rmv)
//                        remove(equalTo[v]);
//                    
//                    
//                }
                vertexCount--;
                
            }
          //merge u 
            equalTo[v].add(u);
            equalTo[u].add(v);
            
        }
    }
    
    private boolean remove(TreeSet<Integer> ts) {
      //ver merged with u
        for(int x: ts){
            if(!removed[x]){
                removed[x] = true;
                return true;
            }
        }
        return false;
    }

    //CAN USE FIND-UNION
    private boolean isSelfLoop(TreeSet<Integer> ts1, TreeSet<Integer> ts2) {
        for(int x: ts1){
            for(int y: ts2){
                //is x present in y's bag
                if(x == y)
                    return true;
            }
        }
        
        for(int x: ts1){
            for(int y: ts2){
                if(equalTo[y].contains(x))
                    return true;
            }
        }
        return false;
    }


    private void settle(int t){
        int size = equalTo[t].size();
        if(size == 0)
            return;
        
        //get all vertices in a separate array
        //to avoid coModification error
        int[] ta = new int[size];
        int i=0;
        for (int v : equalTo[t]) {
            ta[i++] = v;
        }
        
        //bag will contain all vertices equal to u or v
        TreeSet<Integer> bag = new TreeSet();
        for(int x : ta){
            for(int y : equalTo[x]){
                bag.add(y);
                getAllEquals(y, bag);
            }
            
        }
        System.out.println("bag = "+bag);//////
        for(int x : bag){
            equalTo[t].add(x);
        }
    }
    
    /*Recursive method.
     * Adds all vertices to bag which are merged with y.*/
    private void getAllEquals(int y, TreeSet<Integer> bag) {
        for(int x : equalTo[y]){
            if(!bag.contains(x)){
                bag.add(x);
                getAllEquals(x, bag);
                System.out.println("adds "+x);
            }
        }
    }


    /*
     * Sets U and V*/
    public void vertexRemain(){
        int count=0;
        for (int i = 0; i < vc; i++) {
            if(removed[i] == false){
                if(count==0){
                    U = i;
                    count++;
                }    
                else{
                    V = i;
                }
            }
        }
        System.out.println("vertexRemain = "+U+" "+V);
    }
    
    public void cutEdges(){
        
        //If any of u,v is not equalTo to ver then make it 
        //equal to itsekf
        if(!equalTo[U].contains(U))
            equalTo[U].add(U);
        
        if(!equalTo[V].contains(V))
            equalTo[V].add(V);
        
        System.out.println(equalTo[U]);
        System.out.println(equalTo[V]);////////////////////////
        
        //iterate over two set of vertices
        for (int x : equalTo[U]) {
            for(int y : equalTo[V]){
                if(isAdjacent(x, y)){
                    value++;
                }
            }
        }
        System.out.println("MinCut = "+value);
    }
    
    private boolean isAdjacent(int x, int y) {
        for(int t : cg.adj(x)){
            if(t == y)
                return true;
        }
        return false;
    }


    public static void main(String[] args) {
        //get the file
        File file = new File(args[0]);
        Scanner sc = null;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.err.println("File not found!!");;
        }
        
        //read vertex count from file
        int v = Integer.parseInt(sc.nextLine());
        
        //create empty graph
        Graph g = new Graph(v);
        
        //read edges from text and add edges to graph
        while(sc.hasNextLine()){
            //scan a line
            String s = sc.nextLine();
            System.out.println(s);
            //process the line
            Scanner scl = new Scanner(s);
            //scan vertex
            int u = scl.nextInt();
            u--;
            while(scl.hasNext()){
                v = scl.nextInt();
                //add edge u-v
                
                v--;
                g.addEdge(u, v);
            }  
        }
        
        //Find min cut
        KargerMinCut k ;
        int mincut = Integer.MAX_VALUE;
        for(int i=0; i<v*v; i++){
            k = new KargerMinCut(g);
            if(k.value < mincut)
                mincut = k.value;
        }
        
        System.out.println("Final min = "+mincut);
    }
}