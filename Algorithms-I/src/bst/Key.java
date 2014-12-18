package bst;

public class Key implements Comparable<Key>{
	private long keyValue;
	
	//Constructor
	public Key(long in){
		keyValue=in;
	}
	
	public void setValue(long x){
		keyValue=x;
	}
	
	public long getValue(){
		return keyValue;
	}
	
	@Override
	public int compareTo(Key k) {
		long v = this.keyValue - k.getValue();
		if(v < 0)		return -1;
		else if(v > 0)  return 1;
		else			return 0;
	}
	
	public String toString(){
		return keyValue+"";
	}
}
