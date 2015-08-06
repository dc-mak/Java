// Ex 8.7: Testing Tick.java

public class TickTest 
{
	public static void main(String[] args)
	{
		Tick t1 = new Tick(13, 25, 59);
		Tick t2 = new Tick(21, 59, 59);
		Tick t3 = new Tick(23, 59, 59);

		displayTime("t1: ", t1);
		displayTime("t2: ", t2);
		displayTime("t3: ", t3);

		t1.tick();
		t2.tick();
		t3.tick();

		displayTime("t1: ", t1);
		displayTime("t2: ", t2);
		displayTime("t3: ", t3);

		t1.tick();
		t2.tick();
		t3.tick();

		displayTime("t1: ", t1);
		displayTime("t2: ", t2);
		displayTime("t3: ", t3);
	} 

	private static void displayTime(String header, Tick t)
	{
		System.out.printf("%s%n   %s%n   %s%n",
				header, t.toUniversalString(), t.toString());
	} 
}
