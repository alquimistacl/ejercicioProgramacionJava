package com.exercise.course.exception;

/**
 * Exception to handle when a student was not found
 * @author Luis San Martin
 *
 */
public class StudentNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6601525521658339912L;

	public StudentNotFoundException(String message) {
		super(message);
	}
}
