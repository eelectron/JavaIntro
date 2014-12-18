package exceptionHandling;

public class TestExceptions {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String test="yes";
		try {	
			System.out.println("start try");
			doRisky(test);
			System.out.println("end try");
		} catch (Exception e) {
			System.out.println("Exception.............");
		}finally{
			System.out.println("finally...............");
		}
	}
	
	static void doRisky(String test)throws Exception{
		System.out.println("start risky");
		if("yes".equals(test)){
			throw new Exception();
		}
		
		System.out.println("end risky");
		return;
	}

}
