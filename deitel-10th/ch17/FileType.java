// Ex 17.10: Summary of all file types in a directory.

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.regex.Matcher;

public final class FileType {
	public static void main(String[] args) throws IOException {
		Scanner input = new Scanner(System.in);

		System.out.println("Enter file or directory name:");
		Path path = Paths.get(input.nextLine());

		Pattern suffix = Pattern.compile("\\.\\w+");
		Function<Path, String> type = (p) -> {
			Matcher filetype = suffix.matcher(p.toString());
			if (filetype.find())
				return filetype.group();
			return "";};

		if (Files.exists(path) && Files.isDirectory(path)) {
			Files.list(path)
				 .collect(Collectors.groupingBy(type, Collectors.counting()))
				 .forEach((end, count) ->
					 System.out.printf("%s, %d files%n", end, count));
		} 
	}
} 
