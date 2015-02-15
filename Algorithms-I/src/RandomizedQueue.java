import java.util.Iterator;
import java.util.NoSuchElementException;

/****************************************************************************
 * Compilation: javac RandomizedQueue.java 
 * Execution: java RandomizedQueue
 * Dependencies: Iterator.java StdRandom.java
 * 
 * The purpose of this program is to store  items in queue given by the user.
 * But items will be removed at random not sequentially.
 * Resizing array is used to implement this random queue.
 * 
 * @author Prashant Singh Written: 15-Feb-2015
 ****************************************************************************/

public class RandomizedQueue<Item> implements Iterable<Item> {
	@SuppressWarnings("unchecked")
	private Item[] rqueue = (Item[]) new Object[1]; // random queue
	private int N = 0; // position of last item

	/*
	 * @return true if queue is empty.
	 */
	public boolean isEmpty() {
		return N == 0;
	}

	/*
	 * @return size of queue
	 */
	public int size() {
		return N;
	}

	/*
	 * This method puts a item into the queue. As the queue is getting full, new
	 * queue of double capacity is created to store more items.
	 */
	public void enqueue(Item item) {
		if(item == null)
			throw new NullPointerException("Can't add a null item. :(");
		
		if (N == rqueue.length)
			resize(2 * rqueue.length); // create new array of double size if its
										// full
		rqueue[N++] = item; // enter item in queue
	}

	/*
	 * It removes and return a random item from queue.If no. of item in the queue is
	 * 1/4 of total length of array then we reduce the size of array by half to save 
	 * space.  
	 */
	public Item dequeue() {
		Item itemToReturn = null;

		if (isEmpty() == true)
			throw new NoSuchElementException("Queue is empty.");

		// select a random number
		int randomNum = StdRandom.uniform(N);
		Item item = rqueue[randomNum]; // swap it with last item
		rqueue[randomNum] = rqueue[N - 1];
		rqueue[N - 1] = item;
		itemToReturn = rqueue[--N];

		if (N <= rqueue.length / 4) // if queue is quarter full then make half
			resize(rqueue.length / 2);

		return itemToReturn;
	}
	
	/*
	 * @return Item from queue at random without removing it.*/
	public Item sample() {
		if (isEmpty() == true)
			throw new NoSuchElementException("Queue is empty.");
		
		return rqueue[StdRandom.uniform(N)];
	}
	
	/*
	 * This is a private helper method used to resize the array to avoid memory wastage.*/
	private void resize(int capacity) {
		@SuppressWarnings("unchecked")
		Item[] a = (Item[]) new Object[capacity]; // creates new array of new capacity
		for (int i = 0; i < N; i++) {
			a[i] = rqueue[i]; // copy all item in new array
		}
		rqueue = a; // now 'a' becomes our new queue
	}
	
	/*
	 * @return Iterator object,which is used to easily iterate over queue.
	 * This is a randomized queue iterator ie it will iterate the queue 
	 * in random order not sequentially.*/
	public Iterator<Item> iterator() {
		return new RandomizedQueueIterator();
	}

	/*
	 * This private inner class is used to iterate over queue in a random order.
	 */
	private class RandomizedQueueIterator implements Iterator<Item> {
		private int[] rarr = new int[N]; // array of random numbers
		private int counter = 0;
		
		/*Constructs a new array with 0 to N-1 integers in it and
		 * then shuffles it, which will then be used to randomly 
		 * iterate over te queue.*/
		public RandomizedQueueIterator() {
			for (int i = 0; i < N; i++) { // contains 0 - (N-1) number serially
				rarr[i] = i;
			}
			StdRandom.shuffle(rarr); // shuffle the array
		}
		
		/*
		 * @return true if we have 1 or more item in queue.*/
		public boolean hasNext() {
			return counter != N;
		}
		
		/*
		 * @return Item selected at random from queue.*/
		public Item next() {
			if(hasNext() == false)
				throw new NoSuchElementException("No more item exist in queue.");
			
			int r = rarr[counter++];	//get a random no. from rarr[]
			return rqueue[r];
		}
		
		/*Not supported operation.*/
		public void remove() {
			throw new UnsupportedOperationException();
		}

	}

	/*
	 * Unit test public static void main(String[] args) { RandomizedQueue<Fruit>
	 * rq = new RandomizedQueue<Fruit>(); while (!StdIn.isEmpty()) {
	 * StdOut.print("Enter a name"); String s = StdIn.readString();
	 * rq.enqueue(new Fruit(s)); }
	 * 
	 * }
	 */
}
