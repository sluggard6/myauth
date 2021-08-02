package com.github.myauth.core;

public class AuthFailedException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3708099757435637791L;

	public AuthFailedException() {
		super();
	}

	public AuthFailedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public AuthFailedException(String message, Throwable cause) {
		super(message, cause);
	}

	public AuthFailedException(String message) {
		super(message);
	}

	public AuthFailedException(Throwable cause) {
		super(cause);
	}
	
	

}
