package stackAndQueue;

import java.util.Scanner;

public class StackTestClient {

	
	public static void main(String[] args) {
		//create object 
		StackOfStrings sos = new StackOfStrings();
		
		
		Scanner sc = new Scanner(System.in);
		String s;
		
		while( !(s = sc.nextLine()).equals("exit") ){
			
			
			if(s.equals("-"))System.out.println(sos.pop());
			else			 sos.push(s);
		}
		
		System.out.println("game over.");
	}

}
