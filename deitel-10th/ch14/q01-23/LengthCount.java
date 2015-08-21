// Ex 14.18 (b) - Measure the frequency of word length.

import java.util.regex.Pattern;
import java.util.regex.Matcher;
public final class LengthCount {
	public static void main(String[] args){
		if (args.length < 1)
			return;

		final int[] length = new int[29];
		// hypenated and apostrophe words, order is important
		final String token = "\\w+-\\w+|\\w+'\\w*|'\\w+|\\w+";
		Matcher mat = Pattern.compile(token).matcher(args[0]);
		while (mat.find()) {
			String s = mat.group();
			length[s.length() > 28 ? 28 : s.length() - 1]++;
		}

		for (int i = 0; i < 29; i++)
			System.out.println("Length: "+(i+1)+"\tFrequency: "+length[i]);
	}
}
