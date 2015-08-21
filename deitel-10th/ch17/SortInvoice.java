// Ex 17.11: Sorting and displaying results.

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.TreeMap;
import java.util.Map;
import java.util.Comparator;

public final class SortInvoice {
	public static void main(String[] args){
		final Invoice[] invoiceArr = 
			{new Invoice(83,  "Electric sander",   7,   57.98),
			 new Invoice(24,  "Power saw",        18,   99.99),
			 new Invoice( 7,  "Sledge hammer",    76,   11.99),
			 new Invoice(39,  "Lawn mower",        3,   79.50),
			 new Invoice(68,  "Screwdriver",      106,  6.99),
			 new Invoice(56,  "Jig saw",          21,   11.00),
			 new Invoice( 3,  "Wrench",           34,   7.50)};
		List<Invoice> invoices = Arrays.asList(invoiceArr);

		Consumer<Comparator<Invoice>> sortPrint =
			compare -> invoices.stream()
							   .sorted(compare)
							   .forEach(System.out::println);
		// (a)
		System.out.println("By part description:");
		sortPrint.accept(Comparator.comparing(Invoice::getPartDescription));

		// (b)
		System.out.println("\nBy price: ");
		sortPrint.accept(Comparator.comparing(Invoice::getPartDescription));

		// (c) assuming it means map from PartDescription to Quantity.
		System.out.println("\nDescription sorted by quantity:");
		invoices.stream()
			.collect(Collectors.toMap(
				Invoice::getPartDescription,
				Invoice::getQuantity));
			.entrySet()
			.stream()
			.sorted(Map.Entry.comparingByValue())
			.forEach(entry -> System.out.printf(
				"%-15s %3d%n", entry.getKey(), entry.getValue()));

		// (d) I hate vague exercises. Also, I would define a function
		//	   to reduce duplication of similar code but 
		//			i) syntax for types is weirds
		//		   ii) int vs double (Map type? format specifier?)
		//		  iii) filter in this one
		System.out.println("\nDescription sorted by value:");
		invoices.stream()
			.collect(Collectors.toMap(
				Invoice::getPartDescription,
				inv -> inv.getQuantity()*inv.getPrice()));
			.entrySet()
			.stream()
			.sorted(Map.Entry.comparingByValue())
			.filter(entry -> {final double val = entry.getValue();
							  return val >= 200 && val <= 500;	})
			.forEach(entry -> System.out.printf(
				"%-15s %7.2f%n", entry.getKey(), entry.getValue()));
	}
}
