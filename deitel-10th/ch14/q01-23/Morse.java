// Ex 14.22: Morse code

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public final class Morse {
	public static void main(String[] args){
		 if (args.length < 1)
			 return;

		 final String[] alpha =
			 {	".-"  , "-...", "-.-.", "-.." , "."   ,
				"..-.", "--." , "....", ".."  , ".---",
				"-.-" , ".-..", "--"  , "-."  , "---" ,
				".--.", "--.-", ".-." , "..." , "-"   ,
				"..-" , "...-", ".--" , "-..-", "-.--",
				"--.."	};
		 final String[] digit =
			 {	"-----",	".----",	"..---",	"...--",	"....-",
				".....",	"-....",	"--...",	"---..",	"----." };

		Matcher words = Pattern.compile("(\\w|\\d)+").matcher(args[0]);
		Pattern pat = Pattern.compile("\\w|\\d");
		while (words.find()) {
			Matcher mat = pat.matcher(words.group());
			while (mat.find()) {
				String t = mat.group().toLowerCase();
				if (t.matches("\\w"))
					System.out.print(alpha[t.charAt(0) - 'a']+" ");
				else // t.matches("\\d")
					System.out.print(digit[t.charAt(0) - '0']+" ");
			}
			System.out.print("  ");
		}
		System.out.println();
	}
}
