// Ex 7.39: Enhanced GradeBookTest.

import java.util.Scanner;

public class GradeBookTest {
	public static final Scanner input = new Scanner(System.in);

	public static void main(String[] args) {

		System.out.print("Enter number of students: ");
		int students = getPos();

		System.out.print("Enter number of exams: ");
		int exams    = getPos();

		GradeBook myGradeBook = new GradeBook(
				"CS101 Introduction to Java Programming", students, exams);

		System.out.printf("Welcome to the grade book for%n%s%n%n", 
				myGradeBook.getCourseName());

		for (int stu = 0; stu < students; stu++)
			for (int exa = 0; exa < exams; exa++) {
				System.out.printf("Enter grade of student %d in exam %d: ",
						stu+1, exa+1);
				myGradeBook.setGrade(stu, exa, getPos());
			} 

		myGradeBook.processGrades();
	}

	private static int getPos() {
		int n = input.nextInt();
		while (n < 1) {
			System.out.println("Positve numbers only.");
			n = input.nextInt();
		}
		return n;
	}
}
