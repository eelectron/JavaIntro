package week5;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;

import priorityQueue.MinPQ;
import shortestPath.EdgeWeightedDigraph;
import mst.DirectedEdge;

public class DijkstraSP {
    private DirectedEdge[] edgeTo; // last edge to vertices
    private double[] distTo; // shortest dist of vertices from src
    private MinPQ<DirectedEdge> pq;

    public DijkstraSP(EdgeWeightedDigraph G, int s) {
        // initialization
        edgeTo = new DirectedEdge[G.getV()];
        distTo = new double[G.getV()];
        pq = new MinPQ<DirectedEdge>(G.getV()*G.getV());

        for (int v = 0; v < G.getV(); v++) {
            distTo[v] = Double.MAX_VALUE;
        }
        distTo[s] = 0;
        pq.insert(new DirectedEdge(0, 0, 0));
        while (!pq.isEmpty()) {
            DirectedEdge se = pq.deleteMin();
            int v = se.to();
            for (DirectedEdge e : G.getAdj(v)) {
                relax(e);
            }
        }
    }

    private void relax(DirectedEdge e) {
        int v = e.to();
        int u = e.from();

        if (distTo[v] > distTo[u] + e.weight()) {
            distTo[v] = distTo[u] + e.weight();
            edgeTo[v] = e;
            pq.insert(e);
        }

    }

    public double distTo(int v) {
        return distTo[v];
    }

    public boolean hasPathTo(int v) {
        return distTo[v] < Double.POSITIVE_INFINITY;
    }

    public Iterable<DirectedEdge> pathTo(int v) {
        if (!hasPathTo(v))
            return null;
        Stack<DirectedEdge> path = new Stack<DirectedEdge>();
        for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]) {
            path.push(e);
        }
        return path;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(args[0]));
        String str;
        int v = 200;
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(v);

        Scanner sc;
        int u, weight;
        // make graph
        while ((str = br.readLine()) != null) {
            System.out.println(str);
            sc = new Scanner(str).useDelimiter("[,\\s+]");     //use comma and zero or more space as delimiter
            u = sc.nextInt();
            u--;
            while (sc.hasNext()) {
                v = sc.nextInt();
                v--;
                weight = sc.nextInt();

                DirectedEdge e = new DirectedEdge(u, v, weight);
                G.addEdge(e);
                System.out.println(u+" "+v+" "+weight);
            }

        }

        // SP
        DijkstraSP dik = new DijkstraSP(G, 0);
        Stack<DirectedEdge> s = (Stack) dik.pathTo(6);
        while (!s.isEmpty())
            System.out.println(s.pop() + " ");

        System.out.println(dik.distTo(6) + "," + dik.distTo(36) + ","
                + dik.distTo(58) + "," + dik.distTo(81) + "," + dik.distTo(98)
                + "," + dik.distTo(114) + "," + dik.distTo(132) + ","
                + dik.distTo(164) + "," + dik.distTo(187)+","+dik.distTo(196));
    }
    
    //2599,2610,2947,2052,2367,2399,2029,2442,2505,3068
}
