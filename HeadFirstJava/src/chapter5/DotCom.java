package chapter5;

public class DotCom {
	//location of dotcoms
	char row;
	int[] col = new int[3];
	int numOfHits=0;
	String name;
	
	public DotCom(String s) {
		name = s;
	}
	
	public int getNumOfHits(){
		return numOfHits;
	}
	
	public void setLocationCells(char r,int[] c){
		row=r;
		col=c;
	}
}
