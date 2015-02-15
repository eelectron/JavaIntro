package a;

public class Fruit {
	private int key;
	private String value;		//Fruit's name
	public Fruit(String s){
		value=s;
	}
	public int getKey() {
		return key;
	}
	public void setKey(int key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
}
