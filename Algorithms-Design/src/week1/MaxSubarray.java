package week1;
/*
 * Find the largest contiguous subarray whose sum is maximum.*/
public class MaxSubarray {
    int lo,hi,maxSum = Integer.MIN_VALUE;  
    
    int[] findMaxSubArray(int[] a, int low, int high){
        int r[] = new int[3];
        if(low == high){
            r[0]=low;
            r[1]=high;
            r[2]=a[low];
            return r;
        }
        
        //divide array
        int mid = (low+high)/2;
        
        int[] leftSum = findMaxSubArray(a, low, mid);
        int[] rightSum = findMaxSubArray(a, mid+1, high);
        int[] cross = findMaxCrossSum(a, low, mid, high);
        
        if(leftSum[2] >= maxSum){
            lo = leftSum[0];
            hi = leftSum[1];
            maxSum = leftSum[2];   //update max sum
            r = leftSum;
        }
        if(rightSum[2] >= maxSum){
            lo = rightSum[0];
            hi = rightSum[1];
            maxSum = rightSum[2];
            r = rightSum;
        }
        if(cross[2] >= maxSum){
            lo = cross[0];
            hi = cross[1];
            maxSum = cross[2];
            r = cross;
        }
        
        return r;
    }
    
    //Find max cross sum
    int[] findMaxCrossSum(int[] a, int low, int mid, int high){
        int[] r = new int[3]; //will be return as result
        int leftSum = Integer.MIN_VALUE;
        int sum = 0;
        int maxLeft = mid;
        //calculate max sum from left
        for (int i = mid; i >= low; i--) {
            sum += a[i];
            if(sum > leftSum){
                leftSum = sum;
                maxLeft = i;
            }
        }
        
        int rightSum = Integer.MIN_VALUE;
        sum =0;
        int maxRight= mid+1;
        for (int i = mid+1; i <= high; i++) {
            sum += a[i];
            if(sum > rightSum){
                rightSum = sum;
                maxRight = i;
            }
        }
        
        r[0]=maxLeft;
        r[1]=maxRight;
        r[2]=leftSum + rightSum;
        return r;
    }
    
    //Unit test
    public static void main(String[] args) {
        MaxSubarray mss = new MaxSubarray();
        int[] a = {-1,1,-1,-1,8};
        System.out.println(mss.findMaxSubArray(a, 0, a.length-1)+" "+mss.maxSum+" lo="+mss.lo+" hi="+mss.hi);
    }
}