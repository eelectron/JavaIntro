package savingObjects;

public class QuizCard {
	String q,a;
	public QuizCard(String s1,String s2){
		q=s1;
		a=s2;
	}
	
	public String getQuestion(){
		return q;
	}
	
	public String getAnswer(){
		return a;
	}
}
