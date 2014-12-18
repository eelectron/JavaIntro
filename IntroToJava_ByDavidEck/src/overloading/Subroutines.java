package overloading;

public class Subroutines {
	public static void main(String[] args) {
		String s="";
		System.out.println(isPalindrome(s, 0, s.length()-1) );
	}
	
	static void printDivisors(int N){
		System.out.println("Divisors of "+N+":");
		for (int i = 1; i <= N; i++) {
			if(N%i == 0)
				System.out.print(i+" ");
		}
	}
	
	static boolean isPalindrome(String s,int lb, int ub){
		if(s == null || s.length() == 0){
			System.out.println("provide a string.");
			return false;
		}
		else if(lb == ub)
			return true;
		else if(s.charAt(lb) == s.charAt(ub))
			return isPalindrome(s, lb+1, ub-1);	
		else
			return false;
	}
}