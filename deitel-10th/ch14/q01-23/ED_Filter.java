// Ex 14.13: Filter out words that do not end with "ED".

public final class ED_Filter {
	public static void main(String[] args){
		if (args.length < 1)
			return;
		final String[] tokens = args[0].split("\\s+");

		for (String s : tokens)
			if (s.endsWith("ED"))
				System.out.print(s + " ");
		System.out.println();
	}
}
