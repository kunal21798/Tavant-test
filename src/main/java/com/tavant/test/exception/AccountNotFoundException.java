package com.tavant.test.exception;

public class AccountNotFoundException extends Exception{
	public AccountNotFoundException(String msg) {
		// TODO Auto-generated constructor stub
		super(msg);
	}
	@Override
	public String toString() {
		return super.toString()+super.getMessage();
	}
}
