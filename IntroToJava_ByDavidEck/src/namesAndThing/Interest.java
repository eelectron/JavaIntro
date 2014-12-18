package namesAndThing;

import java.util.Scanner;

public class Interest {
	public static void main(String[] args) {
		System.out.println(Double.MAX_VALUE);
		System.out.println(Double.POSITIVE_INFINITY);
	}
	
	void f(){
		Scanner stdin = new Scanner(System.in);
		double p,i,r;;
		
		System.out.println("Enter prncipal:");
		p=stdin.nextDouble();
		
		System.out.println("Enter annual interest rate:");
		r=stdin.nextDouble();
		
		i=p*r;
		p+=i;
		System.out.println("Ammount after one year:"+p);
	}
}
