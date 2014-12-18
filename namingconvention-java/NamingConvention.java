public class NamingConvention {

	public String toCamelCase(String variableName) {
		
		String[] arr = variableName.split("_");
		String ret = arr[0];
		for (int i = 1; i < arr.length; i++) {
			ret = ret + capitalize(arr[i]);
		}
		return ret;
	}
	
	private String capitalize(String s) {
		
		StringBuffer sb = new StringBuffer(s);
		sb.setCharAt(0, Character.toUpperCase(s.charAt(0)));
		return sb.toString();
	}

}
