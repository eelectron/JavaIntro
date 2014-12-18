package chapter5;

import java.util.Scanner;
//Using 7X7 grid
public class DotComBust {
	DotCom[] dotcoms = new DotCom[3];
	public static void main(String[] args) {
		DotComBust dcb = new DotComBust();		
		dcb.setUpGame();
		dcb.startPlayingGame();
		
	}
	
	void setUpGame(){
		
		//creating DotCom objects
				dotcoms[0] = new DotCom("pets.com");
				char row=getRandomRow();
				int[] col = getRandomCols();
				dotcoms[0].setLocationCells(row, col);
				
				dotcoms[1] = new DotCom("dog.com");
				char row1= getRandomRow();
				int[] col1 = getRandomCols();
				dotcoms[1].setLocationCells(row1, col1);
				
				dotcoms[2] = new DotCom("cat.com");
				char row2=getRandomRow();
				int[] col2 = getRandomCols();
				dotcoms[2].setLocationCells(row2, col2);
	}
	
	void startPlayingGame(){
		//User input
				Scanner sc = new Scanner(System.in);
				int numOfGuesses=0;
				boolean isAlive=true;
				String guess;
				int dotComsKill=0;
				
				while(isAlive){
					System.out.println("enter a number");
					guess = sc.next();
					numOfGuesses++;
					
					for (int i = 0; i < dotcoms.length; i++) {
						String result=checkUserGuess(guess, dotcoms[i]) ;
						System.out.println(result);
						if("kil".equalsIgnoreCase(result.substring(0, 3)))
							dotComsKill++;
					}

					if(dotComsKill == dotcoms.length)
						isAlive = false;
				}
	}
	
	public String checkUserGuess(String guess,DotCom dc){
		char r = guess.charAt(0);
		int c = guess.charAt(1) - '0' ;
		
		if(r!=dc.row)return "miss";
		
		for (int i = 0; i < dc.col.length; i++) {
			if(dc.col[i] == c ){
				//remove the col from array once got hit
				dc.col[i]=-1;
				
				//increment numOfHits
				dc.numOfHits++;
				
				//got killed when all location got hit
				if(dc.numOfHits == dc.col.length)return "killed...."+dc.name;
				
				else return "hit";
			}
		}
		return "miss";
	}
	
	//Problem with this code is that two dotcom objects can get same location :(
	private int[] getRandomCols() {
//		int r=(int)(5*Math.random());
		int r=3;
		int[] col=new int[3];
		col[0]=r;
		col[1]=r+1;
		col[2]=r+2;
		return col;
	}

	char getRandomRow(){
		String rows="abcdefg";	
		return rows.charAt((int)(rows.length()*Math.random()));
	}
}
