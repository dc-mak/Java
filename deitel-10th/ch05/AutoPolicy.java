// Ex 5.30
public class AutoPolicy {
   private int accountNumber;
   private String makeAndModel;
   private String state;


   public AutoPolicy(int accountNumber, String makeAndModel, String state) {
      this.accountNumber = accountNumber;
      this.makeAndModel = makeAndModel;
	   if ("CT".equals (state) ||
		   "MA".equals(state) ||
		   "ME".equals(state) ||
		   "NH".equals(state) ||
		   "NJ".equals(state) ||
		   "NY".equals(state) ||
		   "PA".equals(state) )
			   this.state = state;
	   else
		   System.out.println("Error: invalide state code.");
   }


   public void setAccountNumber(int accountNumber) { this.accountNumber = accountNumber; }


   public int getAccountNumber() { return accountNumber; }


   public void setMakeAndModel(String makeAndModel) {
      this.makeAndModel = makeAndModel;
   }


   public String getMakeAndModel() { return makeAndModel; }


   public void setState(String state) {
	   if ("CT".equals (state) ||
		   "MA".equals(state) ||
		   "ME".equals(state) ||
		   "NH".equals(state) ||
		   "NJ".equals(state) ||
		   "NY".equals(state) ||
		   "PA".equals(state) )
			   this.state = state;
	   else
		   System.out.println("Error: invalide state code.");
   }


   public String getState() { return state; }


   public boolean isNoFaultState() {
      boolean noFaultState;

      switch (getState())
      {
         case "MA": case "NJ": case "NY": case "PA":
            noFaultState = true;
            break;
         default:
            noFaultState = false;
            break;
      }

      return noFaultState;
   }
}
