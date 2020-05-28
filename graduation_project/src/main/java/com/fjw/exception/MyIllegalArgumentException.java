package com.fjw.exception;

public class MyIllegalArgumentException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public MyIllegalArgumentException() {
		super();
	}
	
	public MyIllegalArgumentException(String message) {
		super(message);
	}
	
}
