package br.com.alura.ProjetoAlura.course;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.alura.ProjetoAlura.user.UserListItemDTO;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;



public class CourseListItemDTO implements Serializable {
	
	private String name;
	private String code;
    private String instructorEmail;
    private String description;
    private StatusCourse statusCourse;
    private LocalDateTime inactiveDate;
    
    @ManyToMany(mappedBy="courseStudents")
    @JsonIgnoreProperties("students")
    private List<UserListItemDTO> students = new ArrayList<>();
    
    public List<UserListItemDTO> getStudents() {
		return students;
	}

	public void setStudents(List<UserListItemDTO> students) {
		this.students = students;
	}

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
