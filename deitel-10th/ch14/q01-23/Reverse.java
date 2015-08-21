// Ex 14.9: Reversing tokens

public final class Reverse {
	public static void main(String[] args){
		if (args.length < 0)
			return;
		String[] tokens = args[0].split("\\s+");
		for (int i = tokens.length - 1; i >= 0; i--)
			System.out.print(tokens[i]+" ");
		System.out.println();
	}
}
