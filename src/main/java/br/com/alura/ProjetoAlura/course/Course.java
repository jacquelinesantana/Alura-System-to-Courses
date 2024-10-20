package br.com.alura.ProjetoAlura.course;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Course {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private String name;
    private String code;
    private String instructorEmail;
    private String description;
    @Enumerated(EnumType.STRING)
    private StatusCourse statusCourse;
    private LocalDateTime inactiveDate;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
	public String getInstructorEmail() {
		return instructorEmail;
	}
	public void setInstructorEmail(String instructorEmail) {
		this.instructorEmail = instructorEmail;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public StatusCourse getStatusCourse() {
		return statusCourse;
	}
	public void setStatusCourse(StatusCourse statusCourse) {
		this.statusCourse = statusCourse;
	}
	
	
	public LocalDateTime getInactiveDate() {
		return inactiveDate;
	}
	public void setInactiveDate(LocalDateTime inactiveDate) {
		this.inactiveDate = inactiveDate;
	}
	public Course( String name, String code, String instructorEmail, String description,
			StatusCourse statusCourse, LocalDateTime inactiveDate) {
		
		this.name = name;
		this.code = code;
		this.instructorEmail = instructorEmail;
		this.description = description;
		this.statusCourse = statusCourse;
		this.inactiveDate = inactiveDate;
	}
	public Course( String name, String code, String instructorEmail, String description,
			StatusCourse statusCourse) {
		
		this.name = name;
		this.code = code;
		this.instructorEmail = instructorEmail;
		this.description = description;
		this.statusCourse = statusCourse;
		
	}
    @Deprecated
	public Course() {}
    
    
}
