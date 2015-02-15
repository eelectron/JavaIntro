/****************************************************************************
 * Compilation: javac Subset.java Execution: java Subset k(any integer)
 * Dependencies: RandomizedQueue.java StdIn.java
 * 
 * The purpose of this program is to print some random string among the sequence
 * of strings given by user. Here we have used RandomizedQueue data structure to
 * store(enqueue) strings and return random strings.
 * 
 * @author Prashant Singh Written: 15-Feb-2015
 ****************************************************************************/

public class Subset {
	/*
	 * This method expects a integer program argument k,which tells how many
	 * random strings to print.
	 */
	public static void main(String[] args) {
		// check whether user has provided a number as argument
		if (args[0] == null)
			throw new IllegalArgumentException(
					"usage: java Subset %any number%");

		// no. of random strings to print from given input
		int k = Integer.parseInt(args[0]);

		// To hold all the input string
		RandomizedQueue<String> rq = new RandomizedQueue<String>();

		// reads string from console
		while (!StdIn.isEmpty()) {
			String s = StdIn.readString();
			rq.enqueue(s); // put in queue
		}

		// outputs k random string
		for (int i = 0; i < k; i++) {
			StdOut.println(rq.dequeue());
		}
	}
}
