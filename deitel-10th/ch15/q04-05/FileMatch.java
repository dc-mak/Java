// Ex 15.4: Create a newmast.txt from matching oldmast.txt with trans.txt.

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.NoSuchElementException;
import java.util.Scanner;

public final class FileMatch {
	public static void main(String[] args){
		openFiles();
		matchRecords();
		writeNewMaster();
	}

	private static final ArrayList<TransactionRecord> transRec = new ArrayList<>();
	private static final ArrayList<Account> accounts = new ArrayList<>();

	private static void openFiles() {
		try (Scanner inOldMaster   = new Scanner(Paths.get(str.OLDMAST));
			 Scanner inTransaction = new Scanner(Paths.get(str.TRANS))) {

			while (inOldMaster.hasNext())
				accounts.add(new Account(
					inOldMaster.nextInt(), inOldMaster.next(),
					inOldMaster.next(), inOldMaster.nextDouble()));

			while (inTransaction.hasNext())
				transRec.add(new TransactionRecord(
					inTransaction.nextInt(), inTransaction.nextDouble()));

		} catch (NoSuchElementException | IllegalStateException | IOException e) {
			System.err.println("Error processing file. Terminating");
			System.exit(1);
		}
	}

	private static void matchRecords() {
		final ArrayList<TransactionRecord> toRem = new ArrayList<>();
		for (TransactionRecord tr : transRec) {
			final Account tmp = findAccount(tr.getAccount());
			if (tmp != null) {
				tmp.combine(tr);
				toRem.add(tr);
			}
		}

		for (TransactionRecord tr : toRem)
			transRec.remove(tr);
	}

	private static Account findAccount(int account) {
		for (Account acc : accounts)
			if (acc.getAccount() == account)
				return acc;
		return null;
	}

	private static void writeNewMaster() {
		try (Formatter outNewMaster = new Formatter("newmast.txt");
			 Formatter logFile      = new Formatter("log.txt");) {

			for (Account acc : accounts)
				outNewMaster.format("%d %s %s %.2f%n", acc.getAccount(),
					acc.getFirstName(), acc.getLastName(), acc.getBalance());

			for (TransactionRecord tr : transRec)
				logFile.format("%s %d.%n",
					"Unmatched transaction record for account number", 
					tr.getAccount());

		 } catch (SecurityException e) {
			 System.err.println("Write permission denied. Terminating");
			 System.exit(1);
		 } catch (FileNotFoundException e) {
			 System.err.println("Error processing file. Terminating");
			 System.exit(1);
		 }
	}
}
