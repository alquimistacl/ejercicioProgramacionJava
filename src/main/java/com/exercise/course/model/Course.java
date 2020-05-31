package com.exercise.course.model;

import javax.validation.constraints.Size;

public class Course {

	private String name;

	@Size(min = 4, max = 4, message = "The code should be 4 characters long")
	private String code;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "Course [getName()=" + getName() + ", getCode()=" + getCode() + "]";
	}

	
}
