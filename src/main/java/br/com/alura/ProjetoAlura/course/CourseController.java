package br.com.alura.ProjetoAlura.course;

import jakarta.validation.Valid;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.alura.ProjetoAlura.user.Role;
import br.com.alura.ProjetoAlura.user.UserListItemDTO;
import br.com.alura.ProjetoAlura.user.UserRepository;
import br.com.alura.ProjetoAlura.util.ErrorItemDTO;

@RestController
public class CourseController {
	private final UserRepository userRepository;
	private final CourseRepository courseRepository;
	
	private CourseController(CourseRepository courseRepository, UserRepository userRepository ) {
		this.courseRepository = courseRepository;
		this.userRepository = userRepository;
	}
	
    @PostMapping("/course/new")
    public ResponseEntity createCourse(@Valid @RequestBody NewCourseDTO newCourse) {
        // TODO: Implementar a Questão 1 - Cadastro de Cursos aqui...
    	
    	Course course = newCourse.toModel();
    	if(courseRepository.existsByCode(course.getCode())) {
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
    				.body(new ErrorItemDTO("code", "Código do curso já existe"));
    	}
    	if(userRepository.existsByEmail(newCourse.getInstructorEmail())) {
    		if(userRepository.findByEmail(newCourse.getInstructorEmail()).get()
    				.getRole() == Role.INSTRUCTOR) {
    			return ResponseEntity.status(HttpStatus.CREATED).body(courseRepository.save(course));
    			
    		}throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "E-mail não existe ou não é de um instrutor válido");
    	}
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    /*
    @PutMapping("/course/{code}/inactive")
    public ResponseEntity createCourse(@PathVariable("code") String courseCode) {

        if(courseRepository.existsByCode(courseCode)) {
        	Optional<Course> course = courseRepository.findByCode(courseCode);
        	Course courseFinal = course.get();
        	courseFinal .setStatusCourse(StatusCourse.INACTIVE);
        	courseFinal.setInactiveDate(LocalDateTime.now());
        	return ResponseEntity.status(HttpStatus.OK).body(courseRepository.save(courseFinal));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    */
    @GetMapping("/course/all")
    public List<CourseListItemDTO> listAllCourses(){
    	return courseRepository.findAll().stream().map(CourseListItemDTO::new).toList();
    }
    
    @PutMapping("/course/{code}/inactive")
    public ResponseEntity inactiveCourse(@PathVariable("code") String code) {
    	if(courseRepository.existsByCode(code)) {
    		Optional<Course> course = courseRepository.findByCode(code); 
    		
    		Course courseFinal = course.get();
    		courseFinal.setStatusCourse(StatusCourse.INACTIVE);
    		courseFinal.setInactiveDate(LocalDateTime.now());
    		return ResponseEntity.status(HttpStatus.OK).body(courseRepository.save(courseFinal));
    	}
    	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: Do not exist this code!");
    }

}
