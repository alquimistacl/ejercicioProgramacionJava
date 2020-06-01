package com.exercise.course.model;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@MappedSuperclass
public class Course implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3726151988015272324L;

	@NotBlank(message = "Name is mandatory")
	private String name;

	@NotBlank(message = "Code is mandatory")
	@Size(min = 2, max = 4, message = "The code should be 4 characters long")
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
