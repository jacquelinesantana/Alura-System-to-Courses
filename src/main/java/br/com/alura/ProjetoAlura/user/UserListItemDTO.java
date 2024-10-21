package br.com.alura.ProjetoAlura.user;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "emailInstructor" , cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties("emailInstructor")
    @JoinColumn(name="emailInstructor")
    private NewCourseDTO newCourseDTO; 
    
    @ManyToMany
    @JoinTable(name = "courseStudents", 
	   joinColumns = @JoinColumn(name = "userEmail"), 
	   inverseJoinColumns = @JoinColumn(name = "courseCode"))
    @JsonIgnoreProperties("courseStudents")
    private List<CourseListItemDTO> courseStudents;

    public UserListItemDTO(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.role = user.getRole();
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

	public NewCourseDTO getNewCourseDTO() {
		return newCourseDTO;
	}

	public void setNewCourseDTO(NewCourseDTO newCourseDTO) {
		this.newCourseDTO = newCourseDTO;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<CourseListItemDTO> getCourseStudents() {
		return courseStudents;
	}

	public void setCourseStudents(List<CourseListItemDTO> courseStudents) {
		this.courseStudents = courseStudents;
	}
    
	
}
