package week4;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;
import java.util.Vector;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Collections;

import digraph.DepthFirstOrder;
import digraph.Digraph;

public class KosarajuSharirSCC {
    private boolean[] visited;
    private int[] id; // means id[u] contains set no. in which u present
    private int count = 0; // total scc in graph
    DescOrder desc = new DescOrder();

    public KosarajuSharirSCC(Digraph G) {
        visited = new boolean[G.V()];
        id = new int[G.V()];
        // get topo order of reverse of given graph
        DepthFirstOrder dfo = new DepthFirstOrder(G.reverse());
        Stack<Integer> rdfs = (Stack<Integer>) dfo.reversePost();
        int v = 0;
        // run dfs on original graph with the vertices in order
        while (!rdfs.isEmpty()) {
            v = rdfs.pop();
            if (!visited[v]) {
                dfs(G, v);
                count++;
            }
        }
    }

    private void dfs(Digraph G, int v) {
        visited[v] = true;
        id[v] = count;
        for (int w : G.adj(v)) {
            if (!visited[w])
                dfs(G, w);
        }
    }

    /*
     * Returns true iff given vertices present in same scc.
     */
    public boolean isStronglyConnected(int u, int v) {
        return id[u] == id[v];
    }

    /*
     * Returns id[]
     */
    public int[] id() {
        return id;
    }

    /*
     * Unit Testing.
     */
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        // Build a graph
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        Digraph g = null;
        try {
            fis = new FileInputStream(args[0]);
            bis = new BufferedInputStream(fis);
            // read |V| from file
            int v = 0;
            int u = 0;
            
            byte[] buf = new byte[20];
            
            byte b = 0,k=0;
            boolean spFlag=false;
            
            //Read first line
            for (k = 0; (b=(byte)bis.read())!='\n'; k++) {
                buf[k]=b;
            }
            v = toInt(buf, k);
            System.out.println("v="+v);
            k=0;
            //New graph
            g = new Digraph(v);
            
            
            while(true){
                
              //Read till End of file
                while((b = (byte)bis.read()) != -1){
                    if(Character.isDigit(b) && !spFlag){
                        buf[k++]=b;
                    }
                    else if(b == ' ' && !spFlag && k>0){
                        spFlag=true;
                        u=toInt(buf, k);
                        k=0;
                    }
                    else if(spFlag && Character.isDigit(b)){
                        buf[k++]=b;
                    }
                    else if(b == '\n'&& spFlag && k>0){
                        v=toInt(buf, k);
                        k=0;
                        spFlag=false;
                        break;
                    } 
                }
                if(b==-1)break;
                u--;
                v--;
                g.addEdge(u, v);
                System.out.println(u+" "+v);
            }
        bis.close();
        fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
            
        KosarajuSharirSCC ks = new KosarajuSharirSCC(g);
        System.out.println("count = " + ks.count);
        int[] sccSize = new int[ks.count + 1];
        int[] id = ks.id();
        for (int i = 0; i < id.length; i++) {
//            System.out.print(id[i] + " ");
            sccSize[id[i]]++;
        }
        System.out.println();
        Arrays.sort(sccSize);
        

        int li = sccSize.length - 1;
        // Print top five scc
        for (int i = li; i >= 0 && i > li - 5; i--) {
            System.out.print(sccSize[i] + " ");
        }
        
        //end time
        System.out.println("elapsed time = "+(System.currentTimeMillis() - startTime)/1000);
    }
    
    /*
     * Produces sorted array in DESC ORDER*/
    private class DescOrder implements Comparator<Integer>{

        @Override
        public int compare(Integer o1, Integer o2) {
            if(o1 < o2)         return 1;
            else if(o1 == o2)   return 0;
            return -1;
        }
        
    }
    
    /*
     * Convert bytes to int.*/
    private static int toInt(byte[] b, byte n) {
        int value=0;
        for (int i = 0; i < n; i++) {
            value = value*10 + b[i]- '0';
        }
        return value;
    }
}
