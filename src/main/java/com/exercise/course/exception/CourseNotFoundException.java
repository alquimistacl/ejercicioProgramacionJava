package com.exercise.course.exception;

/**
 * Exception to handle when the course id was not found
 * @author Luis San Martin
 *
 */
public class CourseNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -719143607295692476L;

	public CourseNotFoundException(String message) {
		super(message);
	}

}
