// Fig. 6.33: Modify Craps.java to allow wagering and chatter.

import java.util.Scanner;
import java.security.SecureRandom;

public class Craps2
{
   // create secure random number generator for use in method rollDice
   private static final SecureRandom rand = new SecureRandom();

   // enum type with constants that represent the game status
   private enum Status {CONTINUE, WON, LOST};

   // constants that represent common rolls of the dice
   private static final int SNAKE_EYES = 2;
   private static final int TREY = 3;
   private static final int SEVEN = 7;
   private static final int YO_LEVEN = 11;
   private static final int BOX_CARS = 12;

   // plays one game of craps
   public static void main(String[] args)
   {
	  final Scanner input = new Scanner(System.in);
      int myPoint = 0; // point if no win or loss on first roll
      Status gameStatus; // can contain CONTINUE, WON or LOST

	  final int initial = 1000;
	  int balance = initial;

	  System.out.print("Wager time! How much would you like to bet? ");
	  int wager = getWager(input, balance);
	  chatter(balance, initial);

      int sumOfDice = rollDice(); // first roll of the dice

      // determine game status and point based on first roll 
      switch (sumOfDice) 
      {
         case SEVEN: // win with 7 on first roll
         case YO_LEVEN: // win with 11 on first roll           
            gameStatus = Status.WON;
			balance += wager;
            break;
         case SNAKE_EYES: // lose with 2 on first roll
         case TREY: // lose with 3 on first roll
         case BOX_CARS: // lose with 12 on first roll
            gameStatus = Status.LOST;
			balance -= wager;
            break;
         default: // did not win or lose, so remember point         
            gameStatus = Status.CONTINUE; // game is not over
            myPoint = sumOfDice; // remember the point
            System.out.printf("Point is %d%n", myPoint);
			balance -= wager;
            break;
      } 

      // while game is not complete
      while (gameStatus == Status.CONTINUE) // not WON or LOST
      { 
		 System.out.print("How much would you like to add to your wager? ");
		 wager = getWager(input, balance);
		 balance -= wager;
		 chatter(balance, initial);
         sumOfDice = rollDice(); // roll dice again

         // determine game status
         if (sumOfDice == myPoint) // win by making point
            gameStatus = Status.WON;
         else 
            if (sumOfDice == SEVEN) // lose by rolling 7 before point
               gameStatus = Status.LOST;
      } 

      // display won or lost message
      if (gameStatus == Status.WON)
         System.out.println("Winning!");
      else
         System.out.println("Sorry, you busted!");

	  System.out.println("Balance: "+balance+".");
   }

   // roll dice, calculate sum and display results
   private static int rollDice()
   {
      // pick random die values
      int die1 = 1 + rand.nextInt(6); // first die roll
      int die2 = 1 + rand.nextInt(6); // second die roll

      int sum = die1 + die2; // sum of die values

      // display results of this roll
      System.out.printf("Player rolled %d + %d = %d%n", 
         die1, die2, sum);

      return sum; 
   }

   private static int getWager(Scanner input, int limit)
   {
	  int inVal = input.nextInt();
	  if (limit == 0)
		 return 0;
	  while (inVal < 1 || limit < inVal) {
		 System.out.print("Wager between 1 and "+limit+": ");
		 inVal = input.nextInt();
	  }
	  return inVal;
   }

   private static void chatter(int balance, int initial)
   {
	  boolean first = rand.nextInt(2) == 0;
	 if (balance == 0)
		if (first)
		   System.out.println("All in!");
		else
		   System.out.println("Up big!");
	 else if (balance < initial/4) 
		if (first)
		   System.out.println("Now it's time for some fun");
		else
		   System.out.println("Game's become interesting");
	 else if (balance < initial/2)
		if (first)
		   System.out.println("C'mon, take a chance!");
		else
		   System.out.println("You can do better than this...");
	 else
		if (first)
		   System.out.println("Where's the fun in a bet like this?");
		else
		   System.out.println("What's the point of even playing?");
   }

} // end class Craps

/**************************************************************************
 * (C) Copyright 1992-2014 by Deitel & Associates, Inc. and               *
 * Pearson Education, Inc. All Rights Reserved.                           *
 *                                                                        *
 * DISCLAIMER: The authors and publisher of this book have used their     *
 * best efforts in preparing the book. These efforts include the          *
 * development, research, and testing of the theories and programs        *
 * to determine their effectiveness. The authors and publisher make       *
 * no warranty of any kind, expressed or implied, with regard to these    *
 * programs or to the documentation contained in these books. The authors *
 * and publisher shall not be liable in any event for incidental or       *
 * consequential damages in connection with, or arising out of, the       *
 * furnishing, performance, or use of these programs.                     *
 *************************************************************************/
