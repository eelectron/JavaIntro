/* NOT SPACE EFFICIENT---------
public class RecursiveMin{
   public static int min(int[] a, int lb, int ub){
      if(lb==ub)
         return a[lb];
      else{
         int x=min(a, lb+1, ub);
         if(x<a[lb])
            return x;
         else
            return a[lb];
      }
   }

   public static void main(String[] arg){
      int[] a={9,-5,0,-10,99};
      System.out.println(min(a, 0, 4));
   }
}
*/

//More space efficient than above version
public class RecursiveMin{
   public static int min(int[] a, int lb, int ub){
      //base case
      if(lb==ub)
         return a[lb];
      //cal mid index of array
      int mid = (lb+ub)/2;
      int x=min(a, lb, mid);
      int y=min(a, mid+1, ub);

      if(x<y)
         return x;
      else
         return y;
   }
   
    public static void main(String[] arg){
      int[] a={9,-5,0,0,0};
      System.out.println(min(a, 0, a.length-1));
   }
}
