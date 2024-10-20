package br.com.alura.ProjetoAlura.course;

import java.io.Serializable;
import java.time.LocalDateTime;



public class CourseListItemDTO implements Serializable {
	
	private String name;
	private String code;
    private String instructorEmail;
    private String description;
    private StatusCourse statusCourse;
    private LocalDateTime inactiveDate;
    
    public CourseListItemDTO(Course course) {
    	this.name = course.getName();
    	this.code = course.getCode();
    	this.instructorEmail = course.getInstructorEmail();
    	this.description = course.getDescription();
    	this.statusCourse = course.getStatusCourse();
    	this.inactiveDate = course.getInactiveDate(); 
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

    
}
