// Ex 12.16: Make a GUI version of the Craps game from Chapter 6.

import java.util.Random;

public final class Craps {
	private static final Random rand = new Random();

	private int die1;
	private int die2;
	private int myPoint;
	private boolean firstTurn;
	private Status status;

	public int getDie1() { return die1; }
	public int getDie2() { return die2; }
	public int getSum()  { return die1 + die2; }
	public int getPoint() { return myPoint; }
	public Status getStatus() { return status; }
	public boolean isFirstTurn() { return firstTurn; }

	private void rollDice() {
		die1 = 1 + rand.nextInt(6); 
		die2 = 1 + rand.nextInt(6); 
	}

	public void reset() { firstTurn = true; }

	public Craps() { firstTurn = true; nextTurn(); }

	private static Status firstTurn(int point) {
		switch (point) {
			case SEVEN: 
			case YO_LEVEN: 
				return Status.WON;
			case SNAKE_EYES: 
			case TREY: 
			case BOX_CARS: 
				return Status.LOST;
			default: 
				return Status.CONTINUE; 
		}
	}

	// I could use setter methods, but conciseness.
	public void nextTurn() {
		rollDice();
		if (firstTurn) {
			myPoint = die1+die2; 
			status = firstTurn(myPoint);
			firstTurn = false;
		} else if (status == Status.CONTINUE) {
			if (die1 + die2 == myPoint)
				status = Status.WON;
			if (die1 + die2 == SEVEN)
				status = Status.LOST;
		}
	}

	private static final int SNAKE_EYES = 2;
	private static final int TREY = 3;
	private static final int SEVEN = 7;
	private static final int YO_LEVEN = 11;
	private static final int BOX_CARS = 12;
} 
