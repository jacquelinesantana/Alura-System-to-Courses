package br.com.alura.ProjetoAlura.registration;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.ProjetoAlura.course.Course;
import br.com.alura.ProjetoAlura.course.CourseRepository;
import br.com.alura.ProjetoAlura.user.User;
import br.com.alura.ProjetoAlura.user.UserRepository;
import br.com.alura.ProjetoAlura.util.ErrorItemDTO;

import static br.com.alura.ProjetoAlura.course.StatusCourse.ACTIVE;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class RegistrationController {
	private final UserRepository userRepository;
	private final CourseRepository courseRepository;
	
	public RegistrationController(UserRepository userRepository, CourseRepository courseRepository) {
		this.userRepository = userRepository;
		this.courseRepository = courseRepository;
	}

    @PostMapping("/registration/new")
    public ResponseEntity createCourse(@Valid @RequestBody NewRegistrationDTO newRegistration) {
    	LocalDate registrationDate = LocalDate.now();
    	Optional<User> user = userRepository.findByEmail(newRegistration.getStudentEmail());
        Optional<Course> course = courseRepository.findByCodeAndActiveCourses(newRegistration.getCourseCode());
        
        if(user.isPresent() && course.isPresent()) {
        	Course courseX = course.get();
        	
        	User userX = user.get();
        	
        	if(userX.getCourses().contains(courseX)) {
        		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error, This student already belongs to this course.");
        	}
        	//newRegistration.setEnrollment_date(registrationDate);
        	userX.getCourses().add(courseX);
        	;
        	 
        	return ResponseEntity.status(HttpStatus.CREATED).body(userRepository.save(userX));
        	
        }
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: Course or Student not exists");
        
    }
    
    @GetMapping("/registration/report")
    public ResponseEntity<List<RegistrationReportItem>> report() {
        
    	
    	List<RegistrationReportItem> items = new ArrayList<>();

        // TODO: Implementar a Questão 4 - Relatório de Cursos Mais Acessados aqui...

        // Dados fictícios abaixo que devem ser substituídos
        items.add(new RegistrationReportItem(
                "Java para Iniciantes",//nome curso
                "java",//code
                "Charles",//instructor
                "charles@alura.com.br", //instructoremail
                10L//total de inscrições
        ));

        items.add(new RegistrationReportItem(
                "Spring para Iniciantes",
                "spring",
                "Charles",
                "charles@alura.com.br",
                9L
        ));

        items.add(new RegistrationReportItem(
                "Maven para Avançados",
                "maven",
                "Charles",
                "charles@alura.com.br",
                9L
        ));

        return ResponseEntity.ok(items);
    }

}
