// Ex 15.4: Storing common file names.

public final class str { 
	public static final String OLDMAST = "oldmast.txt";
	public static final String OLDMAST_PROMPT =
		String.format("%s%n%s%n? ",
			"Enter account number, first name, last namea and account balance.",
			"Enter end-of-file indicator to end input.");

	public static final String TRANS = "trans.txt";
	public static final String TRANS_PROMPT =
		String.format("%s%n%s%n? ",
			"Enter account number and transaction amount.",
			"Enter end-of-file indicator to end input.");
	
	public static final String NEWMAST = "newmast.txt";
	public static final String LOG	   = "log.txt";
}
