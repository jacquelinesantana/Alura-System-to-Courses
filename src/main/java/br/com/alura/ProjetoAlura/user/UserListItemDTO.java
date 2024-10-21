package br.com.alura.ProjetoAlura.user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.alura.ProjetoAlura.course.Course;
import br.com.alura.ProjetoAlura.course.CourseListItemDTO;
import br.com.alura.ProjetoAlura.course.NewCourseDTO;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

public class UserListItemDTO implements Serializable {

    private String name;
    private String email;
    private Role role;
    private List<String> courses;

    public UserListItemDTO(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.role = user.getRole();
        this.courses = user.getCourses().stream()
                .map(Course::getCode)
                .collect(Collectors.toList());
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Role getRole() {
        return role;
    }

	public List<String> getCourses() {
		return courses;
	}

	public void setCourses(List<String> courses) {
		this.courses = courses;
	}

   
}