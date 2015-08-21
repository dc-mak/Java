// Ex 14.19: Converting dates from one format to another.

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public final class Dates {
	public static void main(String[] args){
		final String[] month =
			{"January",		"February",	"March",	"April",
			 "May",			"June",		"July",		"August",
			 "September",	"October",	"November",	"December"};

		if (args.length < 1)
			return;
		Matcher mat  = Pattern.compile(
							"(\\d{2})/(\\d{2})/(\\d{4})")
							.matcher(args[0]);
		if (mat.matches())
			System.out.printf("%s %s, %s%n",
				month[(Integer.parseInt(mat.group(2)) - 1) % 12],
				mat.group(1), mat.group(3));
	}
}
