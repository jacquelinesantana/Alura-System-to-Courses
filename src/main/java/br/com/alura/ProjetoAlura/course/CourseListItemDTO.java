package br.com.alura.ProjetoAlura.course;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.annotations.Fetch;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.alura.ProjetoAlura.user.User;
import br.com.alura.ProjetoAlura.user.UserListItemDTO;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;

public class CourseListItemDTO implements Serializable {

    private String name;
    private String code;
    private String instructorEmail;
    private String description;
    private StatusCourse statusCourse;
    private LocalDateTime inactiveDate;
    private List<String> students;

    public CourseListItemDTO(Course course) {
        this.name = course.getName();
        this.code = course.getCode();
        this.instructorEmail = course.getInstructorEmail();
        this.description = course.getDescription();
        this.statusCourse = course.getStatusCourse();
        this.inactiveDate = course.getInactiveDate();
        this.students = course.getStudents().stream()
                .map(User::getEmail)
                .collect(Collectors.toList());
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getInstructorEmail() {
        return instructorEmail;
    }

    public String getDescription() {
        return description;
    }

    public StatusCourse getStatusCourse() {
        return statusCourse;
    }

    public LocalDateTime getInactiveDate() {
        return inactiveDate;
    }

	public List<String> getStudents() {
		return students;
	}

	public void setStudents(List<String> students) {
		this.students = students;
	}

   
}