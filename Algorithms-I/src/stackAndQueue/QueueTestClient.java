package stackAndQueue;

import java.util.Scanner;

public class QueueTestClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		QueueOfSongs qs=new QueueOfSongs();
		Scanner sc=new Scanner(System.in);
		
		String s;
		//enter songs in queue
		while(true){
			System.out.println("Enter a song name:");
			s=sc.nextLine();
			if(s == null ||s.equalsIgnoreCase("exit"))
				break;
			else if(s.equals("-")){
				s=qs.dequeue();
				System.out.println(s);
			}
			else
				qs.enqueue(s);
			
		}

	}

}
