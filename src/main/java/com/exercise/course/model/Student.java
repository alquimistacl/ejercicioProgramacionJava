package com.exercise.course.model;

import javax.validation.constraints.Min;

import com.exercise.course.validator.Rut;

public class Student {

	@Rut(message = "The rut should be a valid one")
	private String rut;
	private String name;
	private String lastName;

	@Min(value = 18, message = "Age should not be less than 18")
	private Integer age;

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Student [getRut()=" + getRut() + ", getName()=" + getName() + ", getLastName()=" + getLastName()
				+ ", getAge()=" + getAge() + "]";
	}

}
