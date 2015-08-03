package week1;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    
    private class Point{
        int x,y;
        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */      
        Solution s = new Solution();
        s.readInput();
    }
    
    void readInput(){
        int T = 0;
        int x1,y1,x2,y2;
        Scanner sc = new Scanner(System.in);
        T=sc.nextInt();
        
        while(T > 0){
            x1 = sc.nextInt();
            y1 = sc.nextInt();
            x2 = sc.nextInt();
            y2 = sc.nextInt();
            
            symmetric( new Point(x1,y1) , new Point(x2,y2));
            T--;
        }
    }
    
    void symmetric(Point p1, Point p2){
        if(p1.y == p2.y){
            System.out.println(2*p2.x - p1.x+" "+p1.y);
        }
        else if(p1.x == p2.x){
            System.out.println(p1.x +" "+ (2*p2.y - p1.y));
        }else{
            System.out.println((2*p2.x - p1.x) +" "+(2*p2.y - p1.y));
        }
    }
}