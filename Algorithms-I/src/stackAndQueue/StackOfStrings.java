package stackAndQueue;

//link list implementation of stack
public class StackOfStrings {
	//Inner class used as a node definition
	private class Node{
		String item;
		Node next;
	}
	
	//Starting pointing of list
	Node first=null;
	
	
	void push(String s){
		
		//save the address of first element of list
		Node oldFirst = first;
		
		//then create a new node at that address 
		first = new Node();
		
		//store the given string in new node
		first.item = s;
		
		//now attach the long list of item at the end of current item
		first.next = oldFirst;
	}
	
	String pop(){
		if(first == null)
			return null;
		
		String s = first.item;
		first = first.next;
		return s;
	}
}
