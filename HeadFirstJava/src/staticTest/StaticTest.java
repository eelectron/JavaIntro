package staticTest;

class StaticSuper{
	static{
		System.out.println("super static block");
	}
	
	StaticSuper() {
		System.out.println("super static constructor");
	}
}
public class StaticTest extends StaticSuper{
	static int rand;
	
	static{
		rand=(int)(Math.random());
		System.out.println("static block "+rand);
	}
	
	StaticTest() {
		System.out.println("constructor");
	}
	
	public static void main(String[] args) {
		System.out.println("In main..");
		StaticTest st=new StaticTest();
	}

}