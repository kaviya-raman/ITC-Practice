package junitITC;

public class calculator {
	public int add(int a, int b) {
		return a + b;
	}
	public int subtract(int a, int b) {
		return a - b;
	}
	public int divide(int a, int b) {
		if(b==0) {
			throw new IllegalArgumentException("Cannot divide by zero");
			
		}
		return a/b;
	}

}
