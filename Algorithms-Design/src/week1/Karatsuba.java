package week1;

public class Karatsuba {
    int multiply(int m, int n){
        int diff=0,len=0,a,b,c,d;
        int output=0;
        int mLen = countDigit(m);
        int nLen = countDigit(n);
        
        //BASE CASE
        if(mLen == 0 || nLen == 0)
            return 0;
        if(mLen == 1 || nLen == 1)
            return m*n;
        
        diff = mLen - nLen;
        //Make m and n of equal length
        if(diff > 0)
            n = n*(int)Math.pow(10, diff);  
        else
            m = m*(int)Math.pow(10, -1*diff);
            
        //Length of m, n
        if(diff > 0)
            len = mLen; // m's len is more
        else
            len = nLen;
        
        //Get a,b,c,d
        a = m/(int)Math.pow(10, len/2);
        b = m%(int)Math.pow(10, len/2);
        c = n/(int)Math.pow(10, len/2);
        d = n%(int)Math.pow(10, len/2);
        
        int first = multiply(a,c);  //recursive call
        int last = multiply(b,d);
        int trick = (a+b)*(c+d) - first - last;
        output = (int)Math.pow(10, len)*first + (int)Math.pow(10, len/2)*trick + last;
        
        return output/(int)Math.pow(10, Math.abs(diff));
    }
    
    //Count digit in an integer
    int countDigit(int m){
        int mLen=0;
        //find len of m
        while(m != 0){
            mLen++;
            
            //remove unit place digit
            m /= 10;
        }
        return mLen;
    }
    
    public static void main(String[] args) {
        Karatsuba k = new Karatsuba();
        System.out.println(k.multiply(95, 95));
    }
}
