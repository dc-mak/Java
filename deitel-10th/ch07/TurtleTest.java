// Ex 7.21: Turtle Graphics.

public class TurtleTest {
	public static void main(String[] args) {
		final Turtle T = new Turtle();
		final int[] cmds = {5, 4, 3, 5, 4, 4, 2, 5, 12, 3, 5, 12, 3, 5, 12,
							3, 5, 12, 5, 1, 1, 6, 9};

		for (int c = 0; c < cmds.length && cmds[c] != 9; c++)
			switch (cmds[c]) {
				case  1: T.putPenUp();             break;
				case  2: T.putPenDown();           break;
				case  3: T.turnRight();            break;
				case  4: T.turnLeft();             break;
				case  5: T.moveForward(cmds[++c]); break;
				case  6: T.showBoard();			   break;
				default: System.out.println("Unrecognised command: "+cmds[c]+".");
			}
	}
}
