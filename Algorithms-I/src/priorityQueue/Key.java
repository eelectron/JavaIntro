package priorityQueue;

public class Key implements Comparable<Key>{
	private int value;
	
	//Constructor
	public Key(int in){
		value=in;
	}
	
	public void setValue(int x){
		value=x;
	}
	
	public int getValue(){
		return value;
	}
	@Override
	public int compareTo(Key k) {
		if(this.value < k.value)		return -1;
		else if(this.value > k.value)  	return 1;
		else							return 0;
	}
}
