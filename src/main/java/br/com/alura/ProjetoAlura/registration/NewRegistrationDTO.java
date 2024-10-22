package br.com.alura.ProjetoAlura.registration;

import static br.com.alura.ProjetoAlura.course.StatusCourse.ACTIVE;

import java.time.LocalDate;

import org.hibernate.annotations.UpdateTimestamp;

import br.com.alura.ProjetoAlura.course.Course;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class NewRegistrationDTO {

    @NotBlank
    @NotNull
    private String courseCode;

    @NotBlank
    @NotNull
    @Email
    private String studentEmail;
    
    @UpdateTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDate created_at;

    public NewRegistrationDTO() {}

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

	public LocalDate getCreated_at() {
		return created_at;
	}

	public void setCreated_at(LocalDate created_at) {
		this.created_at = created_at;
	}

	public NewRegistrationDTO( String courseCode,  String studentEmail,
			LocalDate created_at) {
		this.courseCode = courseCode;
		this.studentEmail = studentEmail;
		this.created_at = created_at;
	}

    
  
     
}
