package bst;

public class Value {
	private String username;
	
	//CONSTRUCTOR
	public Value(String s){
		username = s;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String toString(){
		return username;
	}
}
