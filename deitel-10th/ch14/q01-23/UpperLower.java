// Ex 14.11: Upper and Lower case.

public final class UpperLower {
	public static void main(String[] args){
		if (args.length < 0)
			return;
		System.out.println("Upper: "+args[0].toUpperCase());
		System.out.println("Lower: "+args[0].toLowerCase());
	}
}
