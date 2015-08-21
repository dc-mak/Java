// Ex 14.23: Metric conversions!
//	centimetres, kilometres, litres, grams, kilograms
//	inches,	miles,	pint/gallons/quarts, pounds, stones
// "How many \w+ are in \w+?"

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public final class Conversion {
	public static void main(String[] args) {
		if (args.length < 1)
			return;

		final double[] DIST_dbl = {1, 2.54, 1000.0, 160934.4};
		final String[] DIST_str = {"centimetre", "inch", "kilometre", "mile"};

		final double[] VOL_dbl = {1, 1.75975399, 2.00000088, 8.00000352};
		final String[] VOL_str = {"pint", "litre", "quart", "gallon"};

		final double[] MASS_dbl = {1, 453.59237, 1000.0, 6350.29318};
		final String[] MASS_str = {"gram", "pound", "kilogram", "stone"};

		final String regex =
			"How many ((\\w+?)(e??s)?) in (\\d+(\\.\\d+)?|a) ((\\w+?)(e??s)?)\\?";
		final Matcher mat = Pattern.compile(regex).matcher(args[0]);

		if (!mat.matches())
			return;

		final String first = mat.group(2).toLowerCase();
		final String qStr  = mat.group(4);
		final double quant = qStr.equals("a") ?  1.0 : Double.parseDouble(qStr);
		final String secnd = mat.group(7).toLowerCase();

		System.out.println(first + " " + secnd + " " + qStr);

		int f_ind, s_ind;
		if ((f_ind = indexOf(DIST_str, first)) != -1
				&& ((s_ind = indexOf(DIST_str,  secnd)) != -1))

			System.out.printf("%f%n", quant*DIST_dbl[s_ind]/DIST_dbl[f_ind]);

		else if ((f_ind = indexOf(VOL_str, first)) != -1
				&& ((s_ind = indexOf(VOL_str, secnd)) != -1))

			System.out.printf("%.2f%n", quant*VOL_dbl[s_ind]/VOL_dbl[f_ind]);

		else if ((f_ind = indexOf(MASS_str, first)) != -1
				&& ((s_ind = indexOf(MASS_str, secnd)) != -1))

			System.out.printf("%.2f%n", quant*MASS_dbl[s_ind]/MASS_dbl[f_ind]);
	}

	// If I put more effort in the es-s-none plurals I could use equals
	// but differentiating between inches and kilometres in regex is not on
	public static int indexOf(String[] arr, String e) {
		for (int i = 0; i < arr.length; i++)
			if (arr[i].startsWith(e))
				return i;
		return -1;
	}
}
