package com.revature;

import com.revature.exceptions.DivideBy0;
import com.revature.exceptions.Illegal13;

public class Calculator {
	
	public int add(int a, int b) {
		if((a+b) == 13) {
			throw new Illegal13();
		}
		return a+b;
	}
	
	public int subtract(int a, int b) {
		if((a-b) == 13) {
			throw new Illegal13();
		}
		return a-b;
	}
	
	public int multiply(int a, int b) {
		return a*b;
	}
	
	public int divide(int a, int b) {
		if(b == 0) {
			throw new DivideBy0();
		}
		if((a/b) == 13) {
			throw new Illegal13();
		}
		return a/b;
	}
	
	public int sumArray(int[] arr) {
		int total = 0;
		for(int i = 0; i<arr.length; i++) {
			total+=arr[i];
		}
		if(total == 13) {
			throw new Illegal13();
		}
		return total;
	}
}
