package animal;

public class Dog {

	String name;
	int size;
	
	void bark(){
		if(size>60)
			System.out.println(" says woof woof!");
		else if(size>14)
			System.out.println("says ruff ruff");
		else
			System.out.println("says yipp yipp");
	}

}
