package com.exercise.course.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModelProperty;

/**
 * Entity describing the student
 * @author Luis San Martin
 *
 */
@Table(name = "student", uniqueConstraints = {
		@UniqueConstraint(name = "student_uk", columnNames = { "rut", "name", "lastName" }),
		@UniqueConstraint(name = "student_rut_uk", columnNames = { "rut" }) })
@Entity
public class StudentEntity extends Student {

	@ApiModelProperty("Id of the student")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ApiModelProperty("Course associated to the student")
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "course_id")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private CourseEntity course;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CourseEntity getCourse() {
		return course;
	}

	public void setCourse(CourseEntity course) {
		this.course = course;
	}
}
