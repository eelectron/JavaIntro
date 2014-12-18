package priorityQueue;

public class Key implements Comparable<Key>{
	private float value;
	
	//Constructor
	public Key(float in){
		value=in;
	}
	
	public void setValue(float x){
		value=x;
	}
	
	public float getValue(){
		return value;
	}
	@Override
	public int compareTo(Key k) {
		float v = value - k.getValue();
		if(v < 0)		return -1;
		else if(v > 0)  return 1;
		else			return 0;
	}
}
