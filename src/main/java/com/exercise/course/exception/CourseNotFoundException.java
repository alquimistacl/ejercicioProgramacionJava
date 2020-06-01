package com.exercise.course.exception;

public class CourseNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -719143607295692476L;

	public CourseNotFoundException(String message) {
		super(message);
	}

}
