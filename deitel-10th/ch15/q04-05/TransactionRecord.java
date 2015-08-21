// Ex 15.4: Create a transaction record class.

public final class TransactionRecord {
	private int account;
	private double transAmt;

	public TransactionRecord(int account, double transAmt) {
		this.account = account;
		this.transAmt   = transAmt;
	}

	public int  getAccount() { return account; }
	public double  getTransAmt() { return transAmt; }

	public void setAccount(int account) { this.account = account; }
	public void setTransAmt(double transAmt) { this.transAmt = transAmt; }
}
