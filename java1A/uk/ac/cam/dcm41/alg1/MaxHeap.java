package uk.ac.cam.dcm41.alg1;

import java.util.Arrays;

public class MaxHeap {
	private final int defArraySize = 3;
	private final char heapName;
	private int heapSize;
	private char[] heapArray;

	//DEBUG Method
	public void printHeap () {
		for (int i = 0; i < heapArray.length; i++) {
			System.out.print(heapArray[i]);
		}
		System.out.println();
	}

	public MaxHeap(char name) {
		heapName = name;
		heapArray = new char[defArraySize];	
		heapSize = 0;
	}

	public MaxHeap(char name, String str) {
		heapName = name;
		if (str.length()< 1) { 
			heapSize = 0;
			heapArray = new char[defArraySize];	
		} else {
			heapSize = str.length();
			// Assume string is non-empty, equiv. to ceil(log2(x))
			int arraySize = heapSize;
			if (arraySize > 2){
				arraySize = 32-Integer.numberOfLeadingZeros(heapSize-1);
				arraySize = (1<<arraySize) - 1;
			}
			heapArray = Arrays.copyOf(str.toCharArray(), arraySize);
				
			//LOOK HERE: Construction in O(n).
			for (int i = heapSize>>1; i > -1; i--) {
				heapify(i);
			}
		}
	}

	private void heapify(int index){
		int left = (index<<1) + 1;
		int right = left + 1;
		int max = 0;
		
		if ((left < heapSize) && (heapArray[left] > heapArray[index]))
			max = left;
		else
			max = index;

		if ((right < heapSize) && (heapArray[right] > heapArray[max]))
			max = right;

		if (max != index){
			char tmp = heapArray[index];
			heapArray[index] = heapArray[max];
			heapArray[max] = tmp;
			heapify(max);
		}
	}

	public void insert(char x) {
		heapSize += 1;
		if (heapSize > heapArray.length)
			heapArray = Arrays.copyOf(heapArray, heapArray.length*2);

		// Assume character is always greater than 0.
		int i = heapSize-1;
		int parent = (i-1)>>1;
		heapArray[i] = x;

		while ((i > 0) && (heapArray[parent] < heapArray[i])) {
			char tmp = heapArray[parent];
			heapArray[parent] = heapArray[i];
			heapArray[i] = tmp;
			i = parent;
			parent = (i-1)>>1;
		}
	}
			
	public char getMax() {
		if (heapSize == 0)
			return '_';
		
		char max = heapArray[0];
		heapArray[0] = heapArray[heapSize-1];
		heapSize -= 1;
		if ((heapArray.length > 3) && (heapSize < heapArray.length>>1)) {
			heapArray = Arrays.copyOf(heapArray, heapArray.length>>1);
		}
		heapify(0);

		return max;
	}

	public static void main(String[] args) {
		char c;
		MaxHeap h = new MaxHeap('h', "LORDAMAZEBALLS");
		c = h.getMax();
		System.out.println();
		for (int i = 0; i < "LORDAMAZEBALLS".length(); i++) {
			System.out.print(c);
			c = h.getMax();
		}
		char a[] = "LORDAMAZEBALLSISBACK!".toCharArray();
		for (int i = 0; i < a.length; i ++) {
			h.insert(a[i]);
		}
		System.out.println();
		for (int i = 0; i < a.length; i++) {
			System.out.print(c);
			c=h.getMax();
		}
		System.out.println();
	}
}
