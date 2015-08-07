// Ex 10.17

import java.util.ArrayList;

public class Greenhouse {
	public static void main(String[] args){
		 ArrayList<CarbonFootprint> emissions = new ArrayList<CarbonFootprint>();
		 emissions.add(new Bicycle(2000));
		 emissions.add(new Building(2340000));
		 emissions.add(new Car(17000));

		 for (CarbonFootprint e : emissions)
			 System.out.printf("%-10s%.2f%n", e.getClass().getName(),
					e.getCarbonFootprint());
	}
}
