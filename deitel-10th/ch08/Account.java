// Ex 8.18: BigDecimal

import java.math.BigDecimal;
import java.text.NumberFormat;

public class Account {   
	private String name;
	private BigDecimal balance;

	public Account(String name, double balance)  {
		this.name = name;

		if (balance > 0.0)
			this.balance = BigDecimal.valueOf(1000.0);
		else
			this.balance = BigDecimal.valueOf(0.0);
	}

	public void deposit(double depositAmount)  {      
		if (depositAmount > 0.0)
			balance = balance.add(BigDecimal.valueOf(depositAmount));
	}

	public BigDecimal getBalance() {
		return balance; 
	} 

	public void setName(String name) {
		this.name = name; 
	} 

	public String getName() {
		return name; 
	} 
}
