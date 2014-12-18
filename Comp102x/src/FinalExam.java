
public class FinalExam {

	public static void main(String[] args) {
		printHourGlass(11, '@');
		System.out.println(3%2*-+2 + 2* 1/3);
		
	}

	static void transpose(int[][] arr) {
		// Please write your code after this line
		int row = arr.length;
		int col = arr[0].length;

		int i, j, temp;

		for (i = 0; i < row; i++) {
			for (j = i; j < col; j++) {

				// if row index is equal to col then skip that execution
				if (j == i)
					continue;

				// swap
				temp = arr[i][j];
				arr[i][j] = arr[j][i];
				arr[j][i] = temp;
			}
		}

	}

	static String decipher(String cipher) {
		// Validation
		if (cipher == "")
			return "no cipher";

		int len = cipher.length();
		if (len % 6 != 0)
			return "invalid cipher";

		// Processing
		String originalText = "";
		char ch;

		for (int i = 0; i < len; i = i + 6) {
			ch = decipherChar(cipher.substring(i, i + 6));
			originalText = originalText.concat(String.valueOf(ch));
		}

		return originalText;
	}

	public static char decipherChar(String inputStr) {
		// implementation not shown here
		String s1, s2;

		s1 = inputStr.substring(0, 3);
		s2 = inputStr.substring(3, 6);

		if (s1.compareToIgnoreCase(s2) < 0)
			return s1.charAt(1);
		else
			return s2.charAt(1);
	}

	/*
	 * @@@@@@@@@@@
	 * 
	 *  @@@@@@@@@
	 * 
	 *   @@@@@@@
	 * 
	 *    @@@@@
	 * 
	 *     @@@
	 * 
	 *      @
	 * 
	 *     @@@
	 * 
	 *    @@@@@
	 * 
	 *   @@@@@@@
	 * 
	 *  @@@@@@@@@
	 * 
	 * @@@@@@@@@@@
	 */
	static void printHourGlass(int size, char symbol) {
		// write your code after this line
		int spaceCounter,noOfSymbol,noOfSpaces;

		// UPPER HALF + 1////////////////////////////////////////////
		// for each row
		for (int i = 0; i < (size + 1) / 2; i++) {

			// print spaceCounter " "
			spaceCounter = 0;
			while (spaceCounter < i) {
				System.out.print(" ");
				spaceCounter++;
			}
			
			//For each row calculate no of symbol '@' to print
			noOfSymbol = (size - (i * 2));
			
			// within each row each print "@"
			for (int j = 0; j < noOfSymbol; j++) {
				System.out.print(symbol);
			}

			// print spaceCounter
			spaceCounter = 0;
			while (spaceCounter < i) {
				System.out.print(" ");
				spaceCounter++;
			}

			// print new line
			System.out.print("\n");
		}

		////Lower Half//////////////////////////////////////////
		// for each row
		for (int i = 0; i < size / 2; i++) {
			// print single space(" ")
			spaceCounter = 0;
			while (spaceCounter < size / 2 - (i + 1)) {
				System.out.print(" ");
				spaceCounter++;
			}

			// within each row each
			for (int j = 0; j < 3 + i * 2; j++) {

				System.out.print(symbol);
			}

			// print spaceCounter
			spaceCounter = 0;
			while (spaceCounter < size / 2 - (i + 1)) {
				System.out.print(" ");
				spaceCounter++;
			}

			// print new line
			System.out.print("\n");
		}
	}

}
