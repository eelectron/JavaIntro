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
    private TreeSet<Integer>[]    contractTo;    
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
                cg.addEdge(i, v);
                    
        }
        //initialize
        removed = new boolean[vc];
        edges   = (ArrayList) g.edges();
        contractTo = new TreeSet[vc];
        for (int i = 0; i < vc; i++) {
            contractTo[i] = new TreeSet<Integer>();
            contractTo[i].add(i);
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
        int edgeCount = edges.size();
        Random r = new Random();
        
        while(vertexCount > 2){
            //select a random edge
            Edge e = (Edge)edges.remove(r.nextInt(edgeCount--));
            System.out.println(e);
            
            //remove u
            int u = e.from();
            int v = e.to();
            
            //self loop does not count towards min cut
            boolean sl = isSelfLoop(contractTo[u], contractTo[v]);
            if(!sl){
              //means both end points merge with other two vertices
                //so remve any one of those.
                int x = remove(contractTo[u]);
                int y = remove(contractTo[v]);
                if(x != -1){
                    removed[x]=true;
                  //merge u 
                    contractTo[v].addAll(contractTo[x]);
                    contractTo[x].addAll(contractTo[v]);
                    
                    vertexCount--;
                }   
                else if(y != -1){
                    removed[y]=true;
                  //merge u 
                    contractTo[y].addAll(contractTo[u]);
                    contractTo[u].addAll(contractTo[y]);
                    
                    vertexCount--;
                }
            }
          
        }
    }
    
    private int remove(TreeSet<Integer> ts) {
      //ver merged with u
        for(int x: ts){
            if(!removed[x]){
                return x;
            }
        }
        return -1;
    }

    //CAN USE FIND-UNION
    private boolean isSelfLoop(TreeSet<Integer> ts1, TreeSet<Integer> ts2) {        
        for(int x: ts1){
            for(int y: ts2){
                if(contractTo[y].contains(x))
                    return true;
            }
        }
        return false;
    }


    private void settle(int t){
        int size = contractTo[t].size();
        if(size == 0)
            return;
        
        //get all vertices in a separate array
        //to avoid coModification error
        int[] ta = new int[size];
        int i=0;
        for (int v : contractTo[t]) {
            ta[i++] = v;
        }
        
        //bag will contain all vertices equal to u or v
        TreeSet<Integer> bag = new TreeSet();
        for(int x : ta){
            for(int y : contractTo[x]){
                bag.add(y);
                getAllEquals(y, bag);
            }   
        }
        
        contractTo[t].addAll(bag);
    }
    
    /*Recursive method.
     * Adds all vertices to bag which are merged with y.*/
    private void getAllEquals(int y, TreeSet<Integer> bag) {
        for(int x : contractTo[y]){
            if(!bag.contains(x)){
                bag.add(x);
                getAllEquals(x, bag);
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
        System.out.println(contractTo[U]);
        System.out.println(contractTo[V]);////////////////////////
        
        //iterate over two set of vertices
        for (int x : contractTo[U]) {
            for(int y : contractTo[V]){
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
                if(u < v)
                    g.addEdge(u, v);
            }  
        }
        
        //Experiment
        KargerMinCut k ;
        int mincut = Integer.MAX_VALUE;
        for(int i=0; i<v; i++){
            k = new KargerMinCut(g);
            if(k.value < mincut)
                mincut = k.value;
        }
        System.out.println("Final min = "+mincut);
    }
}