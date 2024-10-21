package br.com.alura.ProjetoAlura.course;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import static br.com.alura.ProjetoAlura.course.StatusCourse.ACTIVE;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


public class NewCourseDTO {

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    @Length(min = 4, max = 10, message="Código do curso deve ter entre 4 as 10 caracteres")
    private String code;

    private String description;

    @NotNull
    @NotBlank
    @Email
    @ManyToOne
    @JsonIgnoreProperties("instructorEmail")
    private String instructorEmail;

    @Enumerated(EnumType.STRING)
    private StatusCourse statusCourse;
    
    public NewCourseDTO() {}

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    
    public String getInstructorEmail() {
        return instructorEmail;
    }

    public void setInstructorEmail(String instructorEmail) {
        this.instructorEmail = instructorEmail;
    }
    //String name, String code, String instructorEmail, String description
    public Course toModel() {
        return new Course(name, codeCorrect(code), instructorEmail, description, ACTIVE);
    }
    
    public static String codeCorrect(String code) {
    	String regex = "[\\d\\s,.!?]";
    	code = code.replaceAll(regex,"-").replaceAll("-+", "-");
    	return code.toLowerCase();
    }

	public StatusCourse getStatusCourse() {
		return statusCourse;
	}

	public void setStatusCourse(StatusCourse statusCourse) {
		this.statusCourse = statusCourse;
	}
    
}
