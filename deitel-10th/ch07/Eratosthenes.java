// Ex. 7.27: Sieve of Eratosthenes array.

import java.util.Arrays;

public class Eratosthenes {
	public static void main(String[] args){
		 final int SIZE = 1000;
		 final boolean[] nums = new boolean[SIZE+2];
		 Arrays.fill(nums, true);

		 for (int i = 2; i < nums.length; i++) {
			 int i_next    = i;
			 boolean first = false;
			 for (int j = i+1; j < nums.length; j++) {
				 if (first && j % i != 0) {
					 i_next = j;
					 first = false;
					 nums[j] = true;
					 continue;
				 } else if (nums[j])
					 nums[j] = j % i != 0;
			 }
			 i = i_next;
		 }
		 
		 for (int i = 2; i < nums.length; i++)
			 if (nums[i])
				 System.out.println(i);
	}
}
