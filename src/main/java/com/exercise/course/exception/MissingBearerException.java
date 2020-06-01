package com.exercise.course.exception;

public class MissingBearerException extends RuntimeException {


	/**
	 * 
	 */
	private static final long serialVersionUID = 6710767315010175806L;

	public MissingBearerException(String message) {
		super(message);
	}
}
