// Ex 6.35: CAI - Arithmetic quiz test harness.

import java.util.Scanner;
import java.security.SecureRandom;

public class QuizTest {
	public static void main(String[] args) {
		Quiz q = new Quiz(new Scanner(System.in), new SecureRandom());
		q.start();
	}
}
