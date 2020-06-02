package com.exercise.course.model;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import com.exercise.course.validator.Rut;

import io.swagger.annotations.ApiModelProperty;

@MappedSuperclass
public class Student {

	@ApiModelProperty("Rut of the student")
	@NotBlank(message = "Rut is mandatory")
	@Rut(message = "The rut should be a valid one")
	private String rut;
	
	@ApiModelProperty("Name of the student")
	@NotBlank(message = "Name is mandatory")
	private String name;
	
	@ApiModelProperty("Last name of the student")
	@NotBlank(message = "LastName is mandatory")
	private String lastName;

	@ApiModelProperty("Age of the student")
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
