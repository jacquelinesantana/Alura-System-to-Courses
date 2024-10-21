package br.com.alura.ProjetoAlura.user;

import br.com.alura.ProjetoAlura.course.Course;
import br.com.alura.ProjetoAlura.course.CourseListItemDTO;
import br.com.alura.ProjetoAlura.util.EncryptUtil;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime createdAt = LocalDateTime.now();
    private String name;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "email", nullable = false, unique = true)
    private String email;
    
    private String password;
    
    @ManyToMany
    @JoinTable(
        name = "register", 
        joinColumns = @JoinColumn(name = "user_email", referencedColumnName = "email"), 
        inverseJoinColumns = @JoinColumn(name = "course_code", referencedColumnName = "code")
    )
    @JsonIgnoreProperties("students")
    private List<Course> courses = new ArrayList<>();

    @Deprecated
    public User() {}

    
    public User(String name, Role role, String email, String password) {
		
		this.name = name;
		this.role = role;
		this.email = email;
		this.password = password;
		this.courses = courses;
	}


    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
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

    public String getPassword() {
        return password;
    }
}