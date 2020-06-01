package com.exercise.course.exception;

public class StudentNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6601525521658339912L;

	public StudentNotFoundException(String message) {
		super(message);
	}
}
