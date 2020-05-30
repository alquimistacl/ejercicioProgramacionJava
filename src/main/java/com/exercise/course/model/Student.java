package com.exercise.course.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;

import com.exercise.course.validator.Rut;

@Entity
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Rut(message = "The rut should be a valid one")
	private String rut;
	private String name;
	private String lastName;

	@Min(value = 18, message = "Age should not be less than 18")
	private Integer age;
	private Course course;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	@Override
	public String toString() {
		return "Student [getId()=" + getId() + ", getRut()=" + getRut() + ", getName()=" + getName()
				+ ", getLastName()=" + getLastName() + ", getAge()=" + getAge() + ", getCourse()=" + getCourse() + "]";
	}

}
