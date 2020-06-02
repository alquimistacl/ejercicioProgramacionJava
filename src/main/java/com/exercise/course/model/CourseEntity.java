package com.exercise.course.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import io.swagger.annotations.ApiModelProperty;

/**
 * Entity describing the course
 * @author Luis San Martin
 *
 */
@Table(name = "course", uniqueConstraints = { @UniqueConstraint(name = "course_uk", columnNames = { "name", "code" }),
		@UniqueConstraint(name = "course_code_uk", columnNames = { "code" }) })
@Entity
public class CourseEntity extends Course {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5992435986534509627L;
	
	@ApiModelProperty("Id of the course")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
