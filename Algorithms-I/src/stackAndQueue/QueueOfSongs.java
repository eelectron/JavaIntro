package stackAndQueue;

public class QueueOfSongs {
	//Node definition
	private class Node{
		String item;
		Node next;
	}
	
	//Starting point of queue
	Node first=null;
	
	//Empty
		boolean isEmpty(){
			return first == null;
		}
		
	//Dequeue
	String dequeue(){
		if(isEmpty()){
			return null;
		}
		//save the first song
		String item = first.item;
		
		//delete from queue
		first = first.next;
		
		//return the song
		return item;
	}
	
	//Enqueue
	void enqueue(String s){
		Node temp=first,newNode;
		
		//create new node
		 newNode = new Node();
		newNode.item = s;
		newNode.next = null;
		
		if(isEmpty()){
			first=newNode;
		}
		else{
			//else get to the last node
			while(temp.next != null){
				temp=temp.next;
			}
			temp.next = newNode;
		}
	}
	
	
}
