import java.util.Iterator;
import java.util.NoSuchElementException;

//import a.Fruit;
/****************************************************************************
 * Compilation: javac Deque.java 
 * Execution: java Deque
 * Dependencies: Iterator.java
 * 
 * Doubly Link List is used to implement the Deque which supports O(1),constant
 * worst case running time  for all given operations on Deque.
 * 
 * @author Prashant Singh Written: 15-Feb-2015
 ****************************************************************************/
public class Deque<Item> implements Iterable<Item>{
	private Node first;		//first always points to first item
	private Node last;		//last always points to last item
	private int size;		//Total item in deque
	
	//Java Inner class represents a item in link list
	private class Node{
		Item item;			//denotes item of list
		Node next;			//points to next item
		Node prev;			//points to previous node
	}
	
	/*
	 * @returns true if there is no item present in list
	 * */
	public boolean isEmpty(){
		return first == null;
	}
	
	/*
	 * @return size of deque.*/
	public int size(){
		return size;
	}
	
	/*
	 * Adds a item at the beginning of list.*/
	public void addFirst(Item item){
		if(item == null)
			throw new NullPointerException("Can't add a null item. :(");
		
		Node newNode = new Node();
		newNode.item = item;
		if(first == null){
			first = newNode;
			last = newNode;
		}else{
			newNode.next = first;
			first.prev = newNode;
			first = newNode;
		}
		size++;						//increment deque size by one
	}
	
	/*
	 * Adds a item to the end.*/
	public void addLast(Item item){
		if(item == null)
			throw new NullPointerException("Can't add a null item. :(");
		
		Node newNode = new Node();
		newNode.item = item;
		
		if(last == null){			//when no item is present 
			last = newNode;
			first = newNode;
		}else{
			last.next = newNode;	//newNode become last node
			newNode.prev = last;	//newNode points to previous node
			last = newNode;
		}
		
		size++;
	}
	
	/*
	 * Removes the first item.*/
	public Item removeFirst(){
		if(isEmpty() == true)
			throw new NoSuchElementException("Can't remove item from a empty deque. :(");	
		
		Item item = first.item;		//get item to be return to user
		
		if(first.next == null){		//only one item is present
			first=null;
			last=null;
		}else{
			first = first.next;
			first.prev = null;
		}		
		size--;		//decrement list size
		return item;	//finally return the item
	}
	
	/*
	 * Removes the last item.*/
	public Item removeLast(){
		if(isEmpty() == true)
			throw new NoSuchElementException("Can't remove item from a empty deque. :(");
		
		Item item = last.item;
		if(last.prev == null){
			last = null;
			first = null;
		}else{
			last = last.prev;
			last.next = null;
		}
		size--;
		return item;
	}
	
	/*@return Iterator object*/
	public Iterator<Item> iterator() {
		return new ListIterator();
	}
	
	
	private class ListIterator implements Iterator<Item>{
		private Node current = first;	//current also points to first node

		/*
		 * @return true if have 1 or more item in deque.*/
		public boolean hasNext() {
			return current != null;
		}

		/*
		 * @return Item from deque in sequential order.*/
		public Item next() {
			if(hasNext() == false)
				throw new NoSuchElementException("No more item exist in deque.");
			
			Item item = current.item;
			current = current.next;
			return item;
		}

		/*Unsupported operation.*/
		public void remove() {	
			throw new UnsupportedOperationException("This method is not implemented!");
		}	
	}
	
	/*public static void main(String[] args) {
		Deque<Fruit> de=new Deque<Fruit>();
		Scanner sc=new Scanner(System.in);
		String s;
		StdOut.print("Enter your name");
		s = sc.next();
		Fruit item=new Fruit(s);
		de.addFirst(item);
		 s= sc.next();
		 
		 Fruit item1=new Fruit(s);
		 de.addLast(item1);
		 for(Fruit i: de)
			 StdOut.print(i.getValue());
		 
	}*/
}
