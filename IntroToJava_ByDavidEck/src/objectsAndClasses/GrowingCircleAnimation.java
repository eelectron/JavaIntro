package objectsAndClasses;

public class GrowingCircleAnimation {
	private CircleInfo[] circleData = new CircleInfo[100];
	int width=350,height=350;
	
	public static void main(String[] args) {
		
		
		
	}
	
	void createDisk(){
		GrowingCircleAnimation gca = new GrowingCircleAnimation();
		for (int i = 0; i < gca.circleData.length; i++) {
			gca.circleData[i] = new CircleInfo((int)(gca.width*Math.random()), (int)(gca.height*Math.random()), (int)(100*Math.random()));
			
		}
	}
	
	void drawDisk(){
		
	}
}
