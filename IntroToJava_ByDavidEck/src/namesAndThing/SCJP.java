package namesAndThing;

class Mango{
	final int a=5;
}

class Fruit extends Mango {
	final int a=10;
}

class DynamicDispatch extends Fruit{
	final int a=15;

	public static void main(String[] args){
		
		System.out.print(false);
		
	}
}

