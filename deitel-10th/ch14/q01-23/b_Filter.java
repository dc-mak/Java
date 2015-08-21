// Ex 14.13: Output only those words that begin with a b.

public final class b_Filter {
	public static void main(String[] args){
		if (args.length == 0)
			return;
		final String[] tokens = args[0].split("\\s+");

		for (String s : tokens)
			if (s.startsWith("b"))
				System.out.print(s+" ");
		System.out.println();
	}
}
