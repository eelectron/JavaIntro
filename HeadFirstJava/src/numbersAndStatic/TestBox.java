package numbersAndStatic;

public class TestBox {
	Integer i;
	int j;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String s = String.format("%,.2f", 1234567890.9888);
		System.out.println(s);
	}
	
	void go(){
		j=i;
		System.out.println(i+" "+j);
	}
}
