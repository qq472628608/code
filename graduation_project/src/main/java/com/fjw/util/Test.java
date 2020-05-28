package com.fjw.util;




public class Test {
	public static void main(String[] args) {
		System.out.println(new Test().num(3));
		//System.out.println(new Test().num1(100));
		//System.out.println("lakdjflakjdf".indexOf("lakd"));
		//System.out.println(Math.round(-1.5));
	}
	
	public int num(int n) {
		if(n == 1) {
			return 1;
		}
		if(n == 2) {
			return 1;
		}
		return num(n-1) + num(n-2);
		
	}
	
	public int num1(int n) {
		int num1 = 0;
		int num2 = 1;
		int numn = 0;
		for(int i = 3;i <= n;i++) {
			numn = num1 + num2;
			num1 = num2;
			num2 = numn;
		}
		return numn;
	}
	
}
