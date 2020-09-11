package com.nokk.editoon.exception;

public class ExpiredTokenException extends RuntimeException{
	
	public ExpiredTokenException(String msg) {
		super(msg);
	}
}
