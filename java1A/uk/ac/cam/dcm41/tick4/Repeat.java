package uk.ac.cam.dcm41.tick4;

public class Repeat {	
	public static void main(String[] args) {
		System.out.println(parseAndRep(args));
	}

	/*
	 * Return the first string repeated by the number of times
	 * specified by the integer in the second string, for example 
	 *
	 *	parseAndRep(new String[] {"one","3"})
	 *
	 *	should return "one one one". Adjacent copies of the repeated string
	 *	should be separated by a single space.
	 *
	 *	Return a suitable error message in a string when there are
	 *	not enough arguments in "args" or the second argument is not a
	 *	valid positive integer. For example:
	 *
	 *		- parseAndRep(new String[] {"one"}) should return
	 *		"Error: insufficient arguments"
	 *
	 *		- parseAndRep(new String[]{"one","five"}) should return
	 *		"Error: second argument is not a positive integer"
	 */
	public static String parseAndRep(String[] args) {
		try {
			String result = "";
			if (Integer.parseInt(args[1]) < 1) throw new NumberFormatException();
			for(int i = 0; i < Integer.parseInt(args[1]); i++){
				if (i == 0) result = args[0];
				else result = result + " " + args[0];
			}
			return result;
		} catch (ArrayIndexOutOfBoundsException A) {
			return "Error: insufficient arguments";
		} catch (NumberFormatException N) {
			return "Error: second argument is not a positive integer";	
		}
	}
}
